/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*     */ import net.minecraft.entity.ai.goal.FollowOwnerGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SitGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalMemories;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalUtil;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IGoalMemoriesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.TameableHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongCheerGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongHappyGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongRageGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongRestGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongTrainGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMemoryModuleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class AbstractDugongEntity extends TameableEntity implements ICommandReceiver, IGoalMemoriesEntity {
/*  63 */   private static final Item[] FOOD = new Item[] { Items.field_151083_be, Items.field_151077_bg, Items.field_196102_ba, Items.field_179557_bn, Items.field_151157_am, Items.field_179559_bp, Items.field_196104_bb, Items.field_222066_kO, Items.field_203180_bP };
/*     */ 
/*     */   
/*  66 */   private static final DataParameter<Byte> FLAGS = EntityDataManager.func_187226_a(AbstractDugongEntity.class, DataSerializers.field_187191_a);
/*  67 */   private static final DataParameter<Integer> TRAINING_MODE = EntityDataManager.func_187226_a(AbstractDugongEntity.class, DataSerializers.field_187192_b);
/*     */   
/*  69 */   private final GoalMemories goalMemories = new GoalMemories();
/*     */   
/*     */   private LivingEntity cheerTarget;
/*     */   
/*     */   private LivingEntity rageTarget;
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  76 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   protected AbstractDugongEntity(EntityType<? extends TameableEntity> type, World world) {
/*  79 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  84 */     super.func_184651_r();
/*     */     
/*  86 */     CommandAbility.addCommandGoals((MobEntity)this);
/*     */     
/*  88 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*  89 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/*  90 */     this.field_70714_bg.func_75776_a(0, (Goal)new SitGoal(this));
/*  91 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongRageGoal(this));
/*  92 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongHappyGoal(this));
/*  93 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongCheerGoal(this));
/*  94 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongRestGoal(this));
/*  95 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongTrainGoal(this));
/*     */     
/*  97 */     this.field_70714_bg.func_75776_a(3, (Goal)new FollowOwnerGoal(this, 1.0D, 6.0F, 2.0F, false));
/*  98 */     this.field_70714_bg.func_75776_a(4, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 0.8D));
/*  99 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 100 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractDugongEntity.class, 8.0F));
/* 101 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 103 */     this.field_70715_bh.func_75776_a(1, (Goal)new TameableHurtByTargetGoal(this, new Class[0]));
/* 104 */     this.field_70715_bh.func_75776_a(1, (Goal)new OwnerHurtByTargetGoal(this));
/* 105 */     this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/* 106 */     this.field_70715_bh.func_75776_a(2, (Goal)new OwnerHurtTargetGoal(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 111 */     super.func_70088_a();
/* 112 */     func_184212_Q().func_187214_a(FLAGS, Byte.valueOf((byte)0));
/* 113 */     func_184212_Q().func_187214_a(TRAINING_MODE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT compound) {
/* 118 */     super.func_213281_b(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT compound) {
/* 123 */     super.func_70037_a(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource pDamageSource) {
/* 128 */     return SoundEvents.field_203262_iB;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184615_bR() {
/* 133 */     return SoundEvents.field_203264_iD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos pos, BlockState block) {
/* 138 */     func_184185_a(SoundEvents.field_203267_iG, 0.2F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 143 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 148 */     return func_70631_g_() ? (0.6F + this.field_70146_Z.nextFloat() / 4.0F) : (0.4F + this.field_70146_Z.nextFloat() / 4.0F);
/*     */   }
/*     */   
/*     */   private void broadcastAngerTarget(LivingEntity target) {
/* 152 */     getNearbyDugongs().forEach(other -> {
/*     */           if (other.func_152114_e(target)) {
/*     */             return;
/*     */           }
/*     */           other.setEnraged(target);
/*     */         });
/*     */   }
/*     */   
/*     */   private void broadcastCheeringTarget(LivingEntity target) {
/* 161 */     getNearbyDugongs().forEach(other -> {
/*     */           if (other.isEnraged() || GoalUtil.hasAliveTarget((MobEntity)other)) {
/*     */             return;
/*     */           }
/*     */           GoalUtil.lookAtEntity((MobEntity)other, (Entity)this);
/*     */           other.setCheering((LivingEntity)this);
/*     */         });
/*     */   }
/*     */   
/*     */   public List<AbstractDugongEntity> getNearbyDugongs() {
/* 171 */     if (hasMemoryValue((MemoryModuleType)ModMemoryModuleTypes.NEARBY_ADULT_DUGONGS.get())) {
/* 172 */       return getMemory((MemoryModuleType)ModMemoryModuleTypes.NEARBY_ADULT_DUGONGS.get()).get();
/*     */     }
/*     */     
/* 175 */     List<AbstractDugongEntity> targets = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 16.0D, AbstractDugongEntity.class::isInstance);
/* 176 */     targets.remove(this);
/* 177 */     setMemoryWithExpiry((MemoryModuleType)ModMemoryModuleTypes.NEARBY_ADULT_DUGONGS.get(), targets, 600L);
/* 178 */     return targets;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 184 */     this.goalMemories.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 189 */     if (func_70909_n() && func_152114_e((LivingEntity)player) && !player.field_70170_p.field_72995_K && hand == Hand.MAIN_HAND) {
/* 190 */       ItemStack stack = player.func_184586_b((player.func_184600_cs() != null) ? player.func_184600_cs() : Hand.MAIN_HAND);
/*     */       
/* 192 */       if (!stack.func_190926_b() && func_110143_aJ() < func_110138_aP() && func_70877_b(stack)) {
/* 193 */         stack.func_190918_g(1);
/* 194 */         func_70691_i(4.0F);
/* 195 */         this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 196 */         return ActionResultType.SUCCESS;
/*     */       } 
/* 198 */       if (stack.func_190926_b()) {
/* 199 */         func_233687_w_(!func_233685_eM_());
/* 200 */         this.field_70703_bu = false;
/* 201 */         this.field_70699_by.func_75499_g();
/* 202 */         func_70624_b((LivingEntity)null);
/* 203 */         return ActionResultType.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     return super.func_230254_b_(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 212 */     super.func_70071_h_();
/* 213 */     if (!this.field_70170_p.field_72995_K);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 219 */     func_82168_bl();
/* 220 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 225 */     return func_152114_e(commandSender);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float amount) {
/* 230 */     boolean flag = super.func_70097_a(damageSource, amount);
/* 231 */     if (!flag) {
/* 232 */       return false;
/*     */     }
/*     */     
/* 235 */     Entity entity = damageSource.func_76346_g();
/*     */     
/* 237 */     if (!this.field_70170_p.field_72995_K && !func_70909_n() && entity instanceof LivingEntity) {
/* 238 */       LivingEntity living = (LivingEntity)entity;
/* 239 */       boolean hasEmptyHand = living.func_184586_b(living.func_184600_cs()).func_190926_b();
/* 240 */       if (hasEmptyHand) {
/* 241 */         if (living instanceof PlayerEntity) {
/* 242 */           broadcastCheeringTarget(living);
/* 243 */           PlayerEntity player = (PlayerEntity)living;
/* 244 */           if (!isEnraged() && func_110143_aJ() < func_110138_aP() / 2.0F && !ForgeEventFactory.onAnimalTame((AnimalEntity)this, player)) {
/* 245 */             func_193101_c(player);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 250 */         setEnraged(living);
/* 251 */         broadcastAngerTarget(living);
/*     */       } 
/*     */     } 
/*     */     
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_233685_eM_() {
/* 261 */     return super.func_233685_eM_();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_142018_a(LivingEntity target, LivingEntity owner) {
/* 266 */     if (target instanceof net.minecraft.entity.monster.GhastEntity || target instanceof net.minecraft.entity.monster.BlazeEntity) {
/* 267 */       return false;
/*     */     }
/*     */     
/* 270 */     LivingEntity entityTamer = MobsHelper.getTamer(target);
/*     */     
/* 272 */     if (entityTamer != null) {
/* 273 */       LivingEntity targetTamer = MobsHelper.getTamer(target);
/* 274 */       if (targetTamer.equals(entityTamer)) {
/* 275 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 279 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_193101_c(PlayerEntity player) {
/* 284 */     super.func_193101_c(player);
/* 285 */     this.field_70699_by.func_75499_g();
/* 286 */     func_233687_w_(true);
/* 287 */     func_70624_b(null);
/* 288 */     broadcastCheeringTarget((LivingEntity)null);
/* 289 */     this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld pServerLevel, AgeableEntity pMate) {
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 299 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack stack) {
/* 304 */     return Arrays.<Item>stream(FOOD).anyMatch(stack.func_77973_b()::equals);
/*     */   }
/*     */   
/*     */   public boolean hasHakiLearned() {
/* 308 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)this);
/* 309 */     boolean hasHardening = props.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
/* 310 */     boolean hasFullbody = props.hasUnlockedAbility(BusoshokuHakiFullBodyHardeningAbility.INSTANCE);
/* 311 */     return (hasHardening || hasFullbody);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 316 */     return (!func_70909_n() && distance > 1024.0D);
/*     */   }
/*     */   
/*     */   public boolean isTraining() {
/* 320 */     return (((Integer)this.field_70180_af.func_187225_a(TRAINING_MODE)).intValue() > 0);
/*     */   }
/*     */   
/*     */   public boolean isIdling() {
/* 324 */     return (!isTraining() && !isResting() && !isCheering());
/*     */   }
/*     */   
/*     */   public TrainingMethod getTrainingMode() {
/* 328 */     return TrainingMethod.values()[((Integer)this.field_70180_af.func_187225_a(TRAINING_MODE)).intValue() - 1];
/*     */   }
/*     */   
/*     */   public void stopTraining() {
/* 332 */     setTraining(0);
/*     */   }
/*     */   
/*     */   public void setTraining(int mode) {
/* 336 */     this.field_70180_af.func_187227_b(TRAINING_MODE, Integer.valueOf(mode));
/*     */   }
/*     */   
/*     */   public void setCheering(@Nullable LivingEntity target) {
/* 340 */     boolean set = true;
/* 341 */     if (target == null || func_152114_e(target)) {
/* 342 */       set = false;
/* 343 */       target = null;
/*     */     } 
/* 345 */     setFlag(0, set);
/* 346 */     this.cheerTarget = target;
/*     */   }
/*     */   
/*     */   public void setHappy(boolean flag) {
/* 350 */     setFlag(1, flag);
/*     */   }
/*     */   
/*     */   public void setResting(boolean flag) {
/* 354 */     setFlag(2, flag);
/*     */   }
/*     */   
/*     */   public void setEnraged(@Nullable LivingEntity attacker) {
/* 358 */     boolean set = true;
/* 359 */     if (attacker == null || func_152114_e(attacker)) {
/* 360 */       set = false;
/* 361 */       attacker = null;
/*     */     } 
/* 363 */     setFlag(3, set);
/* 364 */     this.rageTarget = attacker;
/*     */   }
/*     */   
/*     */   private void setFlag(int flag, boolean set) {
/* 368 */     byte b0 = ((Byte)this.field_70180_af.func_187225_a(FLAGS)).byteValue();
/* 369 */     if (set) {
/* 370 */       this.field_70180_af.func_187227_b(FLAGS, Byte.valueOf((byte)(b0 | 1 << flag)));
/*     */     } else {
/*     */       
/* 373 */       this.field_70180_af.func_187227_b(FLAGS, Byte.valueOf((byte)(b0 & (1 << flag ^ 0xFFFFFFFF))));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean getFlag(int id) {
/* 378 */     return ((((Byte)this.field_70180_af.func_187225_a(FLAGS)).byteValue() & 1 << id) != 0);
/*     */   }
/*     */   
/*     */   public boolean isCheering() {
/* 382 */     return getFlag(0);
/*     */   }
/*     */   
/*     */   public boolean isHappy() {
/* 386 */     return getFlag(1);
/*     */   }
/*     */   
/*     */   public boolean isResting() {
/* 390 */     return getFlag(2);
/*     */   }
/*     */   
/*     */   public boolean isEnraged() {
/* 394 */     return getFlag(3);
/*     */   }
/*     */   
/*     */   public boolean isWandering() {
/* 398 */     return getFlag(4);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getCheerTarget() {
/* 403 */     return this.cheerTarget;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getRageTarget() {
/* 408 */     return this.rageTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 413 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 414 */     this.lastCommandSender = commandSender;
/* 415 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 420 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 426 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 431 */     return this.lastCommandTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public GoalMemories getGoalMemories() {
/* 436 */     return this.goalMemories;
/*     */   }
/*     */   
/*     */   public enum TrainingMethod {
/* 440 */     SHADOW_BOXING,
/* 441 */     PUSHUPS;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\AbstractDugongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */