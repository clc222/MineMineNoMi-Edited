/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OpenDoorGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.GroundPathNavigator;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiEmissionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiInternalDestructionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.BlackBoxAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.DoppelmanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiEmissionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kage.BlackBoxWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.kage.BrickBatWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.KageProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ public class DoppelmanEntity extends CreatureEntity implements ICommandReceiver, IEntityAdditionalSpawnData, ITamableEntity {
/*  74 */   private static final UUID BLACK_BOX_COOLDOWN_BONUS_UUID = UUID.fromString("8459d1b9-2362-4c63-b89c-af761a6c9f18");
/*  75 */   private static final DataParameter<Integer> SHADOWS = EntityDataManager.func_187226_a(DoppelmanEntity.class, DataSerializers.field_187192_b);
/*     */   
/*     */   @Nullable
/*     */   private UUID ownerId;
/*     */   
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  84 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   public DoppelmanEntity(EntityType type, World world) {
/*  87 */     super(type, world);
/*     */   }
/*     */   
/*     */   public DoppelmanEntity(World world, LivingEntity owner) {
/*  91 */     super((EntityType)KageProjectiles.DOPPELMAN.get(), world);
/*     */     
/*  93 */     if (world != null && !world.field_72995_K) {
/*  94 */       setOwner(owner);
/*  95 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */       
/*  97 */       props.setHeart(false);
/*  98 */       props.setShadow(true);
/*     */       
/* 100 */       IHakiData ownerHakiProps = HakiDataCapability.get(owner);
/* 101 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)this);
/* 102 */       hakiProps.setBusoshokuHakiExp(ownerHakiProps.getBusoshokuHakiExp());
/*     */       
/* 104 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 105 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(10.0D);
/* 106 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 107 */       func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(2.0D);
/*     */       
/* 109 */       ((GroundPathNavigator)func_70661_as()).func_179688_b(true);
/*     */ 
/*     */       
/* 112 */       BlackBoxWrapperGoal blackBoxWrapper = new BlackBoxWrapperGoal((MobEntity)this);
/* 113 */       ((BlackBoxAbility)blackBoxWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(BLACK_BOX_COOLDOWN_BONUS_UUID, "Black Box Cooldown Bonus", BonusOperation.ADD, 80.0F));
/*     */ 
/*     */ 
/*     */       
/* 117 */       this.field_70714_bg.func_75776_a(2, (Goal)new BrickBatWrapperGoal((MobEntity)this));
/* 118 */       this.field_70714_bg.func_75776_a(2, (Goal)blackBoxWrapper);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/* 124 */     CommandAbility.addCommandGoals((MobEntity)this);
/*     */     
/* 126 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/* 127 */     this.field_70714_bg.func_75776_a(0, (Goal)new OpenDoorGoal((MobEntity)this, false));
/*     */     
/* 129 */     this.field_70714_bg.func_75776_a(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/* 130 */     this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 131 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 132 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractMarineEntity.class, 8.0F));
/* 133 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractPirateEntity.class, 8.0F));
/* 134 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractBanditEntity.class, 8.0F));
/* 135 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 137 */     this.field_70715_bh.func_75776_a(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 141 */     return OPEntity.createAttributes()
/* 142 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 143 */       .func_233815_a_(Attributes.field_233821_d_, 0.2800000011920929D)
/* 144 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 145 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 146 */       .func_233815_a_(Attributes.field_233820_c_, 1.0D)
/* 147 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 152 */     super.func_70088_a();
/* 153 */     this.field_70180_af.func_187214_a(SHADOWS, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(boolean keepData) {
/* 158 */     super.remove(keepData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damageValue) {
/* 163 */     if (damageSource.func_76346_g() != null && damageSource.func_76346_g() instanceof PlayerEntity && damageSource.func_76346_g() == getOwner()) {
/* 164 */       return false;
/*     */     }
/*     */     
/* 167 */     return super.func_70097_a(damageSource, damageValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70652_k(Entity target) {
/* 172 */     float damage = (float)func_110148_a(Attributes.field_233823_f_).func_111126_e() + (getShadows() * 4);
/* 173 */     int knockback = 0;
/*     */     
/* 175 */     if (target instanceof LivingEntity) {
/* 176 */       damage += EnchantmentHelper.func_152377_a(func_184614_ca(), ((LivingEntity)target).func_70668_bt());
/* 177 */       knockback = (int)(knockback + EnchantmentHelper.func_77501_a((LivingEntity)this));
/*     */     } 
/*     */     
/* 180 */     boolean flag = target.func_70097_a(DamageSource.func_76358_a((LivingEntity)this), damage);
/*     */     
/* 182 */     if (flag && 
/* 183 */       knockback > 0) {
/* 184 */       target.func_70024_g((-MathHelper.func_76126_a(this.field_70125_A * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.func_76134_b(this.field_70125_A * 3.1415927F / 180.0F) * knockback * 0.5F));
/*     */       
/* 186 */       AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_216372_d(0.6D, 1.0D, 0.6D));
/*     */     } 
/*     */ 
/*     */     
/* 190 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 196 */     func_82168_bl();
/* 197 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 202 */     if (!this.field_70170_p.field_72995_K) {
/* 203 */       if (getOwner() == null) {
/* 204 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/* 208 */       if (func_110143_aJ() <= 0.0F && 
/* 209 */         getOwner() != null) {
/* 210 */         LivingEntity owner = getOwner();
/* 211 */         AbilityDataCapability.getLazy(owner).ifPresent(props -> {
/*     */               DoppelmanAbility abl = (DoppelmanAbility)props.getEquippedAbility(DoppelmanAbility.INSTANCE);
/*     */               
/*     */               if (abl != null) {
/*     */                 abl.doppelmanDeathTrigger(owner);
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/*     */     
/* 221 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 226 */     if (player == getOwner()) {
/* 227 */       ItemStack itemStack = player.func_184586_b(hand);
/*     */       
/* 229 */       if (itemStack != null && !itemStack.func_190926_b() && itemStack.func_77973_b() == ModItems.SHADOW.get() && getShadows() < 15) {
/* 230 */         itemStack.func_190920_e(itemStack.func_190916_E());
/* 231 */         addShadow();
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     return ActionResultType.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184206_a(DataParameter<?> key) {
/* 240 */     if (key.equals(SHADOWS)) {
/* 241 */       func_213323_x_();
/*     */     }
/*     */     
/* 244 */     super.func_184206_a(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 249 */     float shadowsUsed = getShadows();
/* 250 */     if (shadowsUsed > 0.0F) {
/* 251 */       EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(1.0F + shadowsUsed / 6.0F);
/* 252 */       return newSize;
/*     */     } 
/* 254 */     return super.func_213305_a(pose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 259 */     super.func_213281_b(nbt);
/* 260 */     if (this.ownerId != null) {
/* 261 */       nbt.func_186854_a("ownerId", this.ownerId);
/*     */     }
/* 263 */     nbt.func_74768_a("shadows", ((Integer)this.field_70180_af.func_187225_a(SHADOWS)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 268 */     super.func_70037_a(nbt);
/* 269 */     if (nbt.func_74764_b("ownerId")) {
/* 270 */       this.ownerId = nbt.func_186857_a("ownerId");
/*     */     }
/* 272 */     this.field_70180_af.func_187227_b(SHADOWS, Integer.valueOf(nbt.func_74762_e("shadows")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 277 */     buffer.func_179252_a(this.ownerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer data) {
/* 282 */     this.ownerId = data.func_179253_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 287 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 291 */     this.owner = owner;
/* 292 */     this.ownerId = owner.func_110124_au();
/* 293 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)this);
/* 294 */     stats.setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */     
/* 296 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this);
/* 297 */     Predicate<Entity> notSame = entity -> !(entity instanceof NightmareSoldierEntity);
/*     */     
/* 299 */     if (factionScope != null) {
/* 300 */       this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal(this, factionScope, new Class[0]));
/* 301 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, factionScope.and(notSame)));
/*     */     } 
/*     */     
/* 304 */     IAbilityData abilityProps = AbilityDataCapability.get(owner);
/* 305 */     if (abilityProps.hasUnlockedAbility(BusoshokuHakiInternalDestructionAbility.INSTANCE)) {
/* 306 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/*     */     }
/* 308 */     else if (abilityProps.hasUnlockedAbility(BusoshokuHakiEmissionAbility.INSTANCE)) {
/* 309 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiEmissionWrapperGoal((MobEntity)this));
/*     */     }
/* 311 */     else if (abilityProps.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE)) {
/* 312 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiHardeningWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/* 318 */     if (this.owner == null && this.ownerId != null) {
/* 319 */       this.owner = (LivingEntity)this.field_70170_p.func_217371_b(this.ownerId);
/*     */     }
/* 321 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void addShadow() {
/* 325 */     this.field_70180_af.func_187227_b(SHADOWS, Integer.valueOf(((Integer)this.field_70180_af.func_187225_a(SHADOWS)).intValue() + 1));
/*     */   }
/*     */   
/*     */   public void setShadow(int value) {
/* 329 */     this.field_70180_af.func_187227_b(SHADOWS, Integer.valueOf(value));
/*     */   }
/*     */   
/*     */   public int getShadows() {
/* 333 */     return ((Integer)this.field_70180_af.func_187225_a(SHADOWS)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 338 */     return getOwner().equals(commandSender);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 343 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 344 */     this.lastCommandSender = commandSender;
/* 345 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 350 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 356 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 361 */     return this.lastCommandTime;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\DoppelmanEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */