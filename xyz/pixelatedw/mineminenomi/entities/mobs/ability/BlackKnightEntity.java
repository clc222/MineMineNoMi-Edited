/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
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
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.pathfinding.GroundPathNavigator;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiEmissionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiInternalDestructionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ito.TamaitoAbility;
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
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ito.OverheatWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ito.TamaitoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.ItoProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ 
/*     */ public class BlackKnightEntity extends CreatureEntity implements ICommandReceiver, IEntityAdditionalSpawnData, ITamableEntity {
/*  63 */   private static final UUID OVERHEAT_COOLDOWN_MODIFIER_UUID = UUID.fromString("1c155dbe-ddc4-4a0c-a189-eb617e0ad55b");
/*  64 */   private static final UUID TAMAITO_COOLDOWN_MODIFIER_UUID = UUID.fromString("01afb328-256f-46d6-a240-ddb1112466a5");
/*     */   
/*     */   @Nullable
/*     */   private UUID ownerId;
/*     */   
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  73 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   public BlackKnightEntity(EntityType type, World world) {
/*  76 */     super(type, world);
/*     */   }
/*     */   
/*     */   public BlackKnightEntity(World world, LivingEntity owner) {
/*  80 */     super((EntityType)ItoProjectiles.BLACK_KNIGHT.get(), world);
/*     */     
/*  82 */     if (world != null && !world.field_72995_K) {
/*  83 */       setOwner(owner);
/*  84 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */       
/*  86 */       props.setHeart(false);
/*  87 */       props.setShadow(true);
/*     */       
/*  89 */       IHakiData ownerHakiProps = HakiDataCapability.get(owner);
/*  90 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)this);
/*  91 */       hakiProps.setBusoshokuHakiExp(ownerHakiProps.getBusoshokuHakiExp());
/*     */       
/*  93 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/*  94 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(10.0D);
/*  95 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/*  96 */       func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(2.0D);
/*     */       
/*  98 */       ((GroundPathNavigator)func_70661_as()).func_179688_b(true);
/*     */       
/* 100 */       OverheatWrapperGoal overheatWrapper = new OverheatWrapperGoal((MobEntity)this);
/* 101 */       ((CooldownComponent)((OverheatAbility)overheatWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).get()).getBonusManager().addBonus(OVERHEAT_COOLDOWN_MODIFIER_UUID, "Overheat Cooldown Modifier", BonusOperation.MUL, 2.0F);
/*     */       
/* 103 */       TamaitoWrapperGoal tamaitoWrapper = new TamaitoWrapperGoal((MobEntity)this);
/* 104 */       ((CooldownComponent)((TamaitoAbility)tamaitoWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).get()).getBonusManager().addBonus(TAMAITO_COOLDOWN_MODIFIER_UUID, "Tamaito Cooldown Modifier", BonusOperation.MUL, 2.0F);
/*     */ 
/*     */       
/* 107 */       this.field_70714_bg.func_75776_a(2, (Goal)new FullbrightWrapperGoal((MobEntity)this));
/*     */       
/* 109 */       this.field_70714_bg.func_75776_a(2, (Goal)tamaitoWrapper);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/* 115 */     CommandAbility.addCommandGoals((MobEntity)this);
/*     */     
/* 117 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/* 118 */     this.field_70714_bg.func_75776_a(0, (Goal)new OpenDoorGoal((MobEntity)this, false));
/*     */     
/* 120 */     this.field_70714_bg.func_75776_a(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/* 121 */     this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 122 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 123 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractMarineEntity.class, 8.0F));
/* 124 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractPirateEntity.class, 8.0F));
/* 125 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractBanditEntity.class, 8.0F));
/* 126 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 128 */     this.field_70715_bh.func_75776_a(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 132 */     return OPEntity.createAttributes()
/* 133 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 134 */       .func_233815_a_(Attributes.field_233821_d_, 0.2800000011920929D)
/* 135 */       .func_233815_a_(Attributes.field_233823_f_, 10.0D)
/* 136 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 137 */       .func_233815_a_(Attributes.field_233820_c_, 1.0D)
/* 138 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 143 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damageValue) {
/* 148 */     if (damageSource.func_76346_g() != null && damageSource.func_76346_g() instanceof PlayerEntity && damageSource.func_76346_g() == getOwner()) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     return super.func_70097_a(damageSource, damageValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70652_k(Entity target) {
/* 157 */     float damage = (float)func_110148_a(Attributes.field_233823_f_).func_111126_e();
/* 158 */     int knockback = 0;
/*     */     
/* 160 */     if (target instanceof LivingEntity) {
/* 161 */       damage += EnchantmentHelper.func_152377_a(func_184614_ca(), ((LivingEntity)target).func_70668_bt());
/* 162 */       knockback = (int)(knockback + EnchantmentHelper.func_77501_a((LivingEntity)this));
/*     */     } 
/*     */     
/* 165 */     boolean flag = target.func_70097_a(DamageSource.func_76358_a((LivingEntity)this), damage);
/*     */     
/* 167 */     if (flag && 
/* 168 */       knockback > 0) {
/* 169 */       target.func_70024_g((-MathHelper.func_76126_a(this.field_70125_A * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.func_76134_b(this.field_70125_A * 3.1415927F / 180.0F) * knockback * 0.5F));
/*     */       
/* 171 */       AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_216372_d(0.6D, 1.0D, 0.6D));
/*     */     } 
/*     */ 
/*     */     
/* 175 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213333_a(DamageSource source, int looting, boolean recentlyHitIn) {}
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 184 */     func_82168_bl();
/* 185 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 190 */     if (!this.field_70170_p.field_72995_K && 
/* 191 */       getOwner() == null) {
/* 192 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 197 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 202 */     super.func_213281_b(nbt);
/* 203 */     if (this.ownerId != null) {
/* 204 */       nbt.func_186854_a("ownerId", this.ownerId);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 210 */     super.func_70037_a(nbt);
/* 211 */     if (nbt.func_74764_b("ownerId")) {
/* 212 */       this.ownerId = nbt.func_186857_a("ownerId");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 218 */     buffer.func_179252_a(this.ownerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer data) {
/* 223 */     this.ownerId = data.func_179253_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 228 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 232 */     this.owner = owner;
/* 233 */     this.ownerId = owner.func_110124_au();
/* 234 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)this);
/* 235 */     stats.setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */     
/* 237 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this);
/* 238 */     Predicate<Entity> notSame = entity -> !(entity instanceof NightmareSoldierEntity);
/*     */     
/* 240 */     if (factionScope != null) {
/* 241 */       this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal(this, factionScope, new Class[0]));
/* 242 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, factionScope.and(notSame)));
/*     */     } 
/*     */     
/* 245 */     IAbilityData abilityProps = AbilityDataCapability.get(owner);
/* 246 */     if (abilityProps.hasUnlockedAbility(BusoshokuHakiInternalDestructionAbility.INSTANCE)) {
/* 247 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/*     */     }
/* 249 */     else if (abilityProps.hasUnlockedAbility(BusoshokuHakiEmissionAbility.INSTANCE)) {
/* 250 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiEmissionWrapperGoal((MobEntity)this));
/*     */     }
/* 252 */     else if (abilityProps.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE)) {
/* 253 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiHardeningWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/* 260 */     if (this.owner == null && this.ownerId != null) {
/* 261 */       this.owner = (LivingEntity)this.field_70170_p.func_217371_b(this.ownerId);
/*     */     }
/* 263 */     return this.owner;
/*     */   }
/*     */   
/*     */   public UUID getOwnerUUID() {
/* 267 */     return this.ownerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 272 */     return getOwner().equals(commandSender);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 277 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 278 */     this.lastCommandSender = commandSender;
/* 279 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 284 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 290 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 295 */     return this.lastCommandTime;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\BlackKnightEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */