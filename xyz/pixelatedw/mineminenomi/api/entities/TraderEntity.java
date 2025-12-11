/*     */ package xyz.pixelatedw.mineminenomi.api.entities;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.PanicGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootParameterSets;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SelfHealEatGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public abstract class TraderEntity extends OPEntity {
/*  49 */   protected List<TradeEntry> tradeEntries = new ArrayList<>();
/*     */   private static final float SPAWNER_DESPAWN_DISTANCE = 40000.0F;
/*     */   private boolean isTrading = false;
/*     */   private boolean canBuy;
/*     */   
/*     */   public TraderEntity(EntityType type, World world) {
/*  55 */     this(type, world, (ResourceLocation[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TraderEntity(EntityType type, World world, ResourceLocation[] textures) {
/*  60 */     super(type, world, textures);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  66 */     super.func_184651_r();
/*  67 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/*  68 */     this.field_70714_bg.func_75776_a(1, (Goal)new PanicGoal((CreatureEntity)this, 2.0D));
/*  69 */     this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 0.8D));
/*  70 */     this.field_70714_bg.func_75776_a(4, (Goal)new SelfHealEatGoal((MobEntity)this));
/*  71 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, MobEntity.class, 8.0F));
/*  72 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  77 */     return OPEntity.createAttributes()
/*  78 */       .func_233815_a_(Attributes.field_233819_b_, 25.0D)
/*  79 */       .func_233815_a_(Attributes.field_233821_d_, 0.20000000298023224D)
/*  80 */       .func_233815_a_(Attributes.field_233823_f_, 1.0D)
/*  81 */       .func_233815_a_(Attributes.field_233818_a_, 20.0D)
/*  82 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  88 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/*  93 */     if (isSpawnedViaSpawner() && distance < 40000.0D) {
/*  94 */       return false;
/*     */     }
/*     */     
/*  97 */     if (func_145818_k_() && !CommonConfig.INSTANCE.getDespawnWithNametag()) {
/*  98 */       return false;
/*     */     }
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean canTrade(PlayerEntity paramPlayerEntity);
/*     */ 
/*     */   
/*     */   public abstract String getTradeFailMessage();
/*     */   
/*     */   public abstract ResourceLocation getTradeTable();
/*     */   
/*     */   public abstract Currency getCurrency();
/*     */   
/*     */   public void func_70071_h_() {
/* 115 */     super.func_70071_h_();
/*     */     
/* 117 */     List<PlayerEntity> customers = WyHelper.getNearbyPlayers(func_213303_ch(), (IWorld)this.field_70170_p, 3.0D, null);
/* 118 */     if (customers.size() > 0) {
/*     */       
/* 120 */       func_200602_a(EntityAnchorArgument.Type.EYES, ((PlayerEntity)customers.get(0)).func_174824_e(0.0F));
/* 121 */       func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 40, 0, false, false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 128 */     super.func_213281_b(nbt);
/*     */     
/* 130 */     ListNBT items = new ListNBT();
/* 131 */     for (TradeEntry stack : this.tradeEntries) {
/*     */       
/* 133 */       CompoundNBT nbtTrade = new CompoundNBT();
/* 134 */       nbtTrade = stack.getItemStack().func_77955_b(nbtTrade);
/* 135 */       items.add(nbtTrade);
/*     */     } 
/* 137 */     nbt.func_218657_a("Items", (INBT)items);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 143 */     super.func_70037_a(nbt);
/*     */     
/* 145 */     ListNBT tradeList = nbt.func_150295_c("Items", 10);
/* 146 */     for (int i = 0; i < tradeList.size(); i++) {
/*     */       
/* 148 */       CompoundNBT nbtTrade = tradeList.func_150305_b(i);
/* 149 */       ItemStack stack = ItemStack.func_199557_a(nbtTrade);
/* 150 */       this.tradeEntries.add(new TradeEntry(stack));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStacksFromNBT(CompoundNBT nbt) {
/* 157 */     ListNBT tradeList = nbt.func_150295_c("Items", 10);
/* 158 */     for (int i = 0; i < tradeList.size(); i++) {
/*     */       
/* 160 */       CompoundNBT nbtTrade = tradeList.func_150305_b(i);
/* 161 */       ItemStack stack = ItemStack.func_199557_a(nbtTrade);
/* 162 */       this.tradeEntries.add(new TradeEntry(stack));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 170 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*     */ 
/*     */     
/* 173 */     LootTable lootTable = this.field_70170_p.func_73046_m().func_200249_aQ().func_186521_a(getTradeTable());
/* 174 */     LootContext.Builder builder = new LootContext.Builder((ServerWorld)this.field_70170_p);
/*     */     
/* 176 */     LootContext context = builder.func_216022_a(LootParameterSets.field_216260_a);
/* 177 */     List<ItemStack> stacks = lootTable.func_216113_a(context);
/*     */     
/* 179 */     for (ItemStack stack : stacks)
/*     */     {
/* 181 */       this.tradeEntries.add(new TradeEntry(stack));
/*     */     }
/*     */     
/* 184 */     return spawnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TradeEntry> getTradingItems() {
/* 189 */     return this.tradeEntries;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsTrading(boolean flag) {
/* 194 */     this.isTrading = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTradingItems(List<TradeEntry> entries) {
/* 199 */     this.tradeEntries = entries;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBuyFromPlayers() {
/* 204 */     return this.canBuy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanBuyFromPlayers() {
/* 209 */     this.canBuy = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 215 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 217 */       WyNetwork.sendTo(new SOpenTraderScreenPacket(func_145782_y()), player);
/* 218 */       WyNetwork.sendTo(new SUpdateTraderOffersPacket(func_145782_y(), this.tradeEntries), player);
/* 219 */       return ActionResultType.SUCCESS;
/*     */     } 
/* 221 */     return ActionResultType.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\TraderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */