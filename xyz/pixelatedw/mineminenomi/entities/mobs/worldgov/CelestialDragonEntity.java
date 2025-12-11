/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.worldgov;
/*     */ import java.awt.Color;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityPredicates;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DespawnAfterTimeGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RandomWalkingAroundHomeGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunTowardsLocationGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.IGoalSelectorMixin;
/*     */ 
/*     */ public class CelestialDragonEntity extends OPEntity {
/*     */   private static final byte MOUNT_EVENT = 100;
/*  53 */   private Color hairColor = Color.BLACK; private static final byte UNMOUNT_EVENT = 101;
/*     */   private boolean ridingSlave = true;
/*     */   private boolean secondChance = false;
/*     */   private boolean gotHitOnce = false;
/*     */   
/*     */   public CelestialDragonEntity(EntityType type, World world) {
/*  59 */     super(type, world, MobsHelper.CELESTIAL_DRAGONS_TEXTURES);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184651_r() {
/*  64 */     super.func_184651_r();
/*     */     
/*  66 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/*  67 */     this.field_70714_bg.func_75776_a(3, (Goal)new RandomWalkingAroundHomeGoal((CreatureEntity)this, 0.6D));
/*  68 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, VillagerEntity.class, 8.0F));
/*  69 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  71 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  72 */     props.setFaction(ModValues.MARINE);
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  76 */     return OPEntity.createAttributes()
/*  77 */       .func_233815_a_(Attributes.field_233819_b_, 30.0D)
/*  78 */       .func_233815_a_(Attributes.field_233821_d_, 0.20000000298023224D)
/*  79 */       .func_233815_a_(Attributes.field_233823_f_, 0.1D)
/*  80 */       .func_233815_a_(Attributes.field_233818_a_, 20.0D)
/*  81 */       .func_233815_a_(Attributes.field_233826_i_, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  87 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  89 */     float r = func_70681_au().nextFloat();
/*  90 */     float g = func_70681_au().nextFloat();
/*  91 */     float b = func_70681_au().nextFloat();
/*  92 */     Color color = new Color(r, g, b);
/*  93 */     this.hairColor = color;
/*     */     
/*  95 */     ItemStack bubbleStack = ((Item)ModArmors.CELESTIAL_DRAGON_BUBBLE.get()).func_190903_i();
/*  96 */     bubbleStack.func_190925_c("display").func_74768_a("color", this.hairColor.getRGB());
/*     */     
/*  98 */     func_184201_a(EquipmentSlotType.HEAD, bubbleStack);
/*     */     
/* 100 */     mountSlave();
/*     */     
/* 102 */     return spawnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 107 */     if (super.func_70097_a(source, amount)) {
/* 108 */       Entity attacker = source.func_76364_f();
/* 109 */       if (attacker != null && attacker instanceof PlayerEntity) {
/* 110 */         PlayerEntity player = (PlayerEntity)attacker;
/*     */         
/* 112 */         IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */         
/* 114 */         if (!this.gotHitOnce) {
/* 115 */           props.alterBounty(10000L, StatChangeSource.KILL_NPC);
/* 116 */           this.gotHitOnce = true;
/* 117 */           if (props.isMarine() || props.isBountyHunter()) {
/* 118 */             props.alterLoyalty(-100.0D, StatChangeSource.KILL_NPC);
/*     */           }
/*     */           
/* 121 */           if (EntityPredicates.field_188444_d.test(player)) {
/* 122 */             EventsWorldData.get().addSpecialBusterCall((ServerWorld)this.field_70170_p, (LivingEntity)player);
/*     */           }
/*     */         } 
/*     */         
/* 126 */         if (!this.secondChance && func_110143_aJ() - amount <= 0.0F) {
/* 127 */           func_70606_j(1.0F);
/* 128 */           this.field_70725_aQ = 0;
/* 129 */           this.secondChance = true;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 134 */         double knockbackDistance = 0.5D;
/* 135 */         double knockbackHeight = 0.0D;
/* 136 */         if (HakiHelper.hasHardeningActive((LivingEntity)player, false, false)) {
/* 137 */           knockbackDistance += 0.2D;
/*     */         }
/* 139 */         if (HakiHelper.hasAdvancedBusoActive((LivingEntity)player)) {
/* 140 */           knockbackDistance++;
/*     */         }
/* 142 */         if (HakiHelper.hasInfusionActive((LivingEntity)player)) {
/* 143 */           knockbackDistance++;
/*     */         }
/*     */         
/* 146 */         if (knockbackDistance > 1.0D) {
/*     */           
/* 148 */           knockbackHeight = 0.5D;
/* 149 */           unmountSlave((LivingEntity)player);
/* 150 */           player.func_195064_c(new EffectInstance((Effect)ModEffects.IMPACT_FRAME.get(), 2, 0));
/*     */         } 
/*     */         
/* 153 */         Vector3d push = player.func_70040_Z().func_72441_c(0.0D, knockbackHeight, 0.0D).func_216372_d(knockbackDistance, 1.0D, knockbackDistance);
/* 154 */         AbilityHelper.setDeltaMovement((Entity)this, push);
/*     */       } 
/* 156 */       return true;
/*     */     } 
/*     */     
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 165 */     switch (id) {
/*     */       case 100:
/* 167 */         this.ridingSlave = true;
/*     */         break;
/*     */       case 101:
/* 170 */         this.field_70725_aQ = 0;
/* 171 */         this.ridingSlave = false;
/*     */         break;
/*     */     } 
/* 174 */     super.func_70103_a(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 179 */     super.func_213281_b(nbt);
/* 180 */     nbt.func_74757_a("hitOnce", this.gotHitOnce);
/* 181 */     nbt.func_74757_a("ridingSlave", this.ridingSlave);
/* 182 */     nbt.func_74768_a("hairColor", this.hairColor.getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 187 */     super.func_70037_a(nbt);
/* 188 */     this.gotHitOnce = nbt.func_74767_n("hitOnce");
/* 189 */     this.ridingSlave = nbt.func_74767_n("ridingSlave");
/* 190 */     this.hairColor = new Color(nbt.func_74762_e("hairColor"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 195 */     super.writeSpawnData(buffer);
/* 196 */     buffer.writeInt(this.hairColor.getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 201 */     super.readSpawnData(buffer);
/* 202 */     this.hairColor = new Color(buffer.readInt());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184178_b(ServerPlayerEntity player) {
/* 207 */     super.func_184178_b(player);
/* 208 */     if (!this.ridingSlave) {
/* 209 */       this.field_70170_p.func_72960_a((Entity)this, (byte)101);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/* 215 */     super.func_70645_a(cause);
/* 216 */     LivingEntity attacker = func_94060_bK();
/* 217 */     if (attacker != null && attacker.func_70089_S() && this.ridingSlave) {
/* 218 */       unmountSlave(attacker);
/*     */     }
/*     */   }
/*     */   
/*     */   public void mountSlave() {
/* 223 */     this.ridingSlave = true;
/* 224 */     this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/*     */   }
/*     */   
/*     */   public void unmountSlave(LivingEntity attacker) {
/* 228 */     if (!this.ridingSlave) {
/*     */       return;
/*     */     }
/*     */     
/* 232 */     this.ridingSlave = false;
/*     */     
/* 234 */     this.field_70170_p.func_72960_a((Entity)this, (byte)101);
/*     */     
/* 236 */     spawnFreedSlave(attacker);
/*     */   }
/*     */   
/*     */   private void spawnFreedSlave(LivingEntity attacker) {
/* 240 */     VillagerEntity slave = new VillagerEntity(EntityType.field_200756_av, this.field_70170_p);
/* 241 */     slave.func_233576_c_(func_213303_ch());
/* 242 */     IGoalSelectorMixin goalSelector = (IGoalSelectorMixin)slave.field_70714_bg;
/* 243 */     goalSelector.getAvailableGoals().clear();
/*     */     
/* 245 */     int xOffset = 50 + this.field_70146_Z.nextInt(50);
/* 246 */     if (this.field_70146_Z.nextBoolean()) {
/* 247 */       xOffset *= -1;
/*     */     }
/* 249 */     int zOffset = 50 + this.field_70146_Z.nextInt(50);
/* 250 */     if (this.field_70146_Z.nextBoolean()) {
/* 251 */       zOffset *= -1;
/*     */     }
/* 253 */     Vector3d runLocation = func_213303_ch().func_72441_c(xOffset, 0.0D, zOffset);
/*     */     
/* 255 */     slave.field_70714_bg.func_75776_a(0, (Goal)new RunTowardsLocationGoal((CreatureEntity)slave, 1.5D, runLocation));
/* 256 */     slave.field_70714_bg.func_75776_a(0, (Goal)new DespawnAfterTimeGoal((MobEntity)slave, 40));
/*     */     
/* 258 */     this.field_70170_p.func_217376_c((Entity)slave);
/*     */     
/* 260 */     IEntityStats props = EntityStatsCapability.get(attacker);
/* 261 */     props.addFreedSlaves(1);
/* 262 */     if (props.isRevolutionary()) {
/* 263 */       props.alterLoyalty(0.5D, StatChangeSource.KILL_NPC);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isRidingSlave() {
/* 268 */     return this.ridingSlave;
/*     */   }
/*     */   
/*     */   public Color getHairColor() {
/* 272 */     return this.hairColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 277 */     return (distance > 1024.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\worldgov\CelestialDragonEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */