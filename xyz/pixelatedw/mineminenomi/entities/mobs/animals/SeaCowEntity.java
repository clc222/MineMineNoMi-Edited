/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.ai.controller.LookController;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.FindWaterGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.pathfinding.PathNavigator;
/*     */ import net.minecraft.pathfinding.SwimmerPathNavigator;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.entity.PartEntity;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.TailSpinAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.TackleAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhaseManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.FishSwimMoveController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.LandFishLookController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.EatNearbyFishGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.StunStacksGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BellyFlopWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.TailSpinWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TackleWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ 
/*     */ public class SeaCowEntity extends TameableEntity {
/*  65 */   private static final UUID TAIL_SPIN_RANGE_BONUS = UUID.fromString("3da76e8f-b539-4a87-b764-8edab430cd77");
/*  66 */   private static final AttributeModifier SWIM_SPEED_MOD = new AttributeModifier(UUID.fromString("7da9447a-362c-4c83-bdde-32e07a939b7e"), "Swim Speed Bonus", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*     */   private TargetsPredicate tailSpinAOECheck;
/*     */   
/*     */   private float size;
/*     */   
/*     */   private static final float MIN_SIZE = 2.0F;
/*     */   
/*     */   private static final float MAX_SIZE = 3.5F;
/*     */   
/*     */   private final SeaCowPartEntity head;
/*     */   private final SeaCowPartEntity tail;
/*     */   private final SeaCowPartEntity[] subEntities;
/*     */   private MovementController groundMovementController;
/*     */   private MovementController waterMovementController;
/*     */   private LookController groundLookControl;
/*     */   private LookController waterLookControl;
/*     */   private PathNavigator groundNavigator;
/*     */   private PathNavigator waterNavigator;
/*  85 */   private final NPCPhaseManager phaseManager = new NPCPhaseManager((MobEntity)this);
/*     */   
/*     */   private NPCPhase<SeaCowEntity> groundPhase;
/*     */   
/*     */   private NPCPhase<SeaCowEntity> waterPhase;
/*     */   private StunStacksGoal stunStackGoal;
/*     */   private TackleWrapperGoal tackleWrapper;
/*     */   
/*     */   public SeaCowEntity(EntityType type, World world) {
/*  94 */     super(type, world);
/*  95 */     this.head = new SeaCowPartEntity(this, "head", 2.5F, 2.5F);
/*  96 */     this.tail = new SeaCowPartEntity(this, "tail", 3.0F, 3.0F);
/*  97 */     this.subEntities = new SeaCowPartEntity[] { this.head, this.tail };
/*     */     
/*  99 */     this.tailSpinAOECheck = TargetsPredicate.DEFAULT_AREA_CHECK.selector(entity -> 
/* 100 */         (func_70638_az() != null && func_70638_az() == entity) ? true : (
/*     */ 
/*     */ 
/*     */         
/* 104 */         !(entity instanceof YagaraBullEntity || entity instanceof SeaCowEntity || entity instanceof PandaSharkEntity || entity instanceof net.minecraft.entity.passive.DolphinEntity)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.groundMovementController = new MovementController((MobEntity)this);
/* 112 */     this.waterMovementController = (MovementController)new FishSwimMoveController((MobEntity)this);
/* 113 */     this.groundLookControl = new LookController((MobEntity)this);
/* 114 */     this.waterLookControl = (LookController)new LandFishLookController((MobEntity)this, 10);
/* 115 */     this.groundNavigator = (PathNavigator)new GroundPathNavigator((MobEntity)this, world);
/* 116 */     this.waterNavigator = (PathNavigator)new SwimmerPathNavigator((MobEntity)this, world);
/*     */     
/* 118 */     func_145769_d(field_213331_b.getAndAdd(this.subEntities.length + 1) + 1);
/*     */     
/* 120 */     if (world != null && !world.field_72995_K) {
/* 121 */       this.groundPhase = (NPCPhase<SeaCowEntity>)new SimplePhase("Ground Phase", (MobEntity)this, this::startGroundPhaseEvent);
/* 122 */       this.waterPhase = (NPCPhase<SeaCowEntity>)new SimplePhase("Water Phase", (MobEntity)this, this::startWaterPhaseEvent);
/*     */       
/* 124 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/* 125 */       func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(4.0D);
/*     */       
/* 127 */       this.stunStackGoal = new StunStacksGoal((MobEntity)this, 3, 10);
/*     */       
/* 129 */       TailSpinWrapperGoal tailSpinWrapper = new TailSpinWrapperGoal((MobEntity)this);
/* 130 */       ((TailSpinAbility)tailSpinWrapper.getAbility()).getComponent(ModAbilityKeys.RANGE).ifPresent(comp -> {
/*     */             comp.setAreaCheck(this.tailSpinAOECheck);
/*     */             
/*     */             comp.getBonusManager().addBonus(TAIL_SPIN_RANGE_BONUS, "Tail Spin Range Bonus", BonusOperation.MUL, 4.0F);
/*     */           });
/* 135 */       this.tackleWrapper = new TackleWrapperGoal((MobEntity)this);
/*     */       
/* 137 */       this.field_70714_bg.func_75776_a(0, (Goal)this.stunStackGoal);
/* 138 */       this.field_70714_bg.func_75776_a(0, (Goal)new AvoidBlockTagGoal((CreatureEntity)this, (ITag.INamedTag)ModTags.Blocks.KAIROSEKI));
/* 139 */       this.field_70714_bg.func_75776_a(0, (Goal)new AvoidCoatedBoatGoal((CreatureEntity)this));
/* 140 */       this.field_70714_bg.func_75776_a(0, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.25D, true));
/* 141 */       this.field_70714_bg.func_75776_a(0, (Goal)new EatNearbyFishGoal((MobEntity)this));
/* 142 */       this.field_70714_bg.func_75776_a(1, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/* 143 */       this.field_70714_bg.func_75776_a(2, (Goal)tailSpinWrapper);
/* 144 */       this.field_70714_bg.func_75776_a(2, (Goal)this.tackleWrapper);
/* 145 */       this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 146 */       this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */       
/* 148 */       this.groundPhase.addGoal(2, (Goal)new BellyFlopWrapperGoal((MobEntity)this));
/* 149 */       this.groundPhase.addGoal(4, (Goal)new RandomWalkingGoal((CreatureEntity)this, 0.8D));
/*     */       
/* 151 */       this.waterPhase.addGoal(0, (Goal)new FindWaterGoal((CreatureEntity)this));
/* 152 */       this.waterPhase.addGoal(4, (Goal)new RandomSwimmingGoal((CreatureEntity)this, 1.0D, 10));
/*     */       
/* 154 */       this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*     */       
/* 156 */       this.phaseManager.setPhase(this.groundPhase);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145769_d(int pId) {
/* 162 */     super.func_145769_d(pId);
/* 163 */     for (int i = 0; i < this.subEntities.length; i++) {
/* 164 */       this.subEntities[i].func_145769_d(pId + i + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 169 */     return MobEntity.func_233666_p_()
/* 170 */       .func_233815_a_(Attributes.field_233823_f_, 4.0D)
/* 171 */       .func_233815_a_(Attributes.field_233820_c_, 0.699999988079071D)
/* 172 */       .func_233815_a_(Attributes.field_233821_d_, 0.27000001072883606D)
/* 173 */       .func_233815_a_(Attributes.field_233819_b_, 70.0D)
/* 174 */       .func_233815_a_(Attributes.field_233818_a_, 100.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 179 */     super.func_70636_d();
/*     */     
/* 181 */     Vector3d[] avector3d = new Vector3d[this.subEntities.length];
/* 182 */     for (int j = 0; j < this.subEntities.length; j++) {
/* 183 */       avector3d[j] = new Vector3d(this.subEntities[j].func_226277_ct_(), this.subEntities[j].func_226278_cu_(), this.subEntities[j].func_226281_cx_());
/*     */     }
/*     */     
/* 186 */     float headRot = MathHelper.func_219799_g(1.0F, this.field_70759_as, this.field_70761_aq);
/* 187 */     float rot = headRot * 0.017453292F;
/* 188 */     float x = MathHelper.func_76126_a(rot);
/* 189 */     float z = MathHelper.func_76134_b(rot);
/* 190 */     tickPart(this.head, (-x * 3.5F), 1.5D, (z * 3.5F));
/* 191 */     tickPart(this.tail, (x * 4.0F), -0.25D, (-z * 4.0F));
/*     */     
/* 193 */     for (int l = 0; l < this.subEntities.length; l++) {
/* 194 */       (this.subEntities[l]).field_70169_q = (avector3d[l]).field_72450_a;
/* 195 */       (this.subEntities[l]).field_70167_r = (avector3d[l]).field_72448_b;
/* 196 */       (this.subEntities[l]).field_70166_s = (avector3d[l]).field_72449_c;
/* 197 */       (this.subEntities[l]).field_70142_S = (avector3d[l]).field_72450_a;
/* 198 */       (this.subEntities[l]).field_70137_T = (avector3d[l]).field_72448_b;
/* 199 */       (this.subEntities[l]).field_70136_U = (avector3d[l]).field_72449_c;
/*     */     } 
/*     */     
/* 202 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 203 */       if (func_70090_H()) {
/* 204 */         this.phaseManager.setPhase(this.waterPhase);
/*     */       } else {
/*     */         
/* 207 */         this.phaseManager.setPhase(this.groundPhase);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 214 */     this.phaseManager.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public CreatureAttribute func_70668_bt() {
/* 219 */     return CreatureAttribute.field_203100_e;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184639_G() {
/* 224 */     return SoundEvents.field_187558_ak;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource pDamageSource) {
/* 229 */     return SoundEvents.field_187562_am;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184615_bR() {
/* 234 */     return SoundEvents.field_187560_al;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 239 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 244 */     return func_70631_g_() ? ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) : ((this.field_70146_Z
/* 245 */       .nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathNavigator func_175447_b(World level) {
/* 250 */     return (PathNavigator)new SwimmerPathNavigator((MobEntity)this, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d moveVector) {
/* 255 */     if (func_70613_aW() && func_70090_H()) {
/* 256 */       func_213309_a(func_70689_ay(), moveVector);
/* 257 */       func_213315_a(MoverType.SELF, func_213322_ci());
/* 258 */       AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_186678_a(0.9D));
/* 259 */       if (func_70638_az() == null) {
/* 260 */         AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_72441_c(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/*     */       
/* 264 */       super.func_213352_e(moveVector);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70646_bf() {
/* 270 */     if (this.phaseManager.getCurrentPhase() != null && this.phaseManager.getCurrentPhase().equals(this.waterPhase)) {
/* 271 */       return 1;
/*     */     }
/* 273 */     return super.func_70646_bf();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_184649_cE() {
/* 278 */     if (this.phaseManager.getCurrentPhase() != null && this.phaseManager.getCurrentPhase().equals(this.waterPhase)) {
/* 279 */       return 1;
/*     */     }
/* 281 */     return super.func_184649_cE();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 286 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96092_aw() {
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/* 296 */     return super.func_70097_a(source, amount);
/*     */   }
/*     */   
/*     */   public boolean hurt(SeaCowPartEntity part, DamageSource source, float amount) {
/* 300 */     if (!this.field_70170_p.field_72995_K && part.equals(this.head) && ((TackleAbility)this.tackleWrapper.getAbility()).isContinuous()) {
/* 301 */       amount *= 1.5F;
/* 302 */       ((TackleAbility)this.tackleWrapper.getAbility()).getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity((LivingEntity)this));
/*     */ 
/*     */       
/* 305 */       this.stunStackGoal.addStack(5);
/*     */     } 
/* 307 */     return func_70097_a(source, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 312 */     super.func_70088_a();
/* 313 */     this.size = Math.min(2.0F + this.field_70146_Z.nextFloat(), 3.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 318 */     float scale = getSize() * ((func_70874_b() < 0) ? 0.5F : 1.0F);
/* 319 */     EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(scale);
/* 320 */     return newSize;
/*     */   }
/*     */   
/*     */   private void tickPart(SeaCowPartEntity part, double offsetX, double offsetY, double offsetZ) {
/* 324 */     part.func_70107_b(func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT compound) {
/* 329 */     super.func_213281_b(compound);
/* 330 */     compound.func_74776_a("size", this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT compound) {
/* 335 */     super.func_70037_a(compound);
/* 336 */     this.size = compound.func_74760_g("size");
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 340 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMultipartEntity() {
/* 345 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PartEntity<?>[] getParts() {
/* 351 */     return (PartEntity<?>[])this.subEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/* 356 */     SeaCowEntity offspring = (SeaCowEntity)((EntityType)ModEntities.SEA_COW.get()).func_200721_a((World)world);
/* 357 */     return (AgeableEntity)offspring;
/*     */   }
/*     */   
/*     */   private void startWaterPhaseEvent(SeaCowEntity entity) {
/* 361 */     this.field_70765_h = this.waterMovementController;
/* 362 */     this.field_70749_g = this.waterLookControl;
/* 363 */     this.field_70699_by.func_75499_g();
/* 364 */     this.field_70699_by = this.waterNavigator;
/* 365 */     ModifiableAttributeInstance attr = func_110148_a(Attributes.field_233821_d_);
/* 366 */     if (attr != null && !attr.func_180374_a(SWIM_SPEED_MOD)) {
/* 367 */       attr.func_233767_b_(SWIM_SPEED_MOD);
/*     */     }
/*     */   }
/*     */   
/*     */   private void startGroundPhaseEvent(SeaCowEntity entity) {
/* 372 */     this.field_70765_h = this.groundMovementController;
/* 373 */     this.field_70749_g = this.groundLookControl;
/* 374 */     this.field_70699_by.func_75499_g();
/* 375 */     this.field_70699_by = this.groundNavigator;
/* 376 */     ModifiableAttributeInstance attr = func_110148_a(Attributes.field_233821_d_);
/* 377 */     if (attr != null)
/* 378 */       attr.func_111124_b(SWIM_SPEED_MOD); 
/*     */   }
/*     */   
/*     */   private class SeaCowPartEntity
/*     */     extends PartEntity<SeaCowEntity> {
/*     */     public final SeaCowEntity parentMob;
/*     */     public final String name;
/*     */     private final EntitySize size;
/*     */     
/*     */     public SeaCowPartEntity(SeaCowEntity parent, String name, float width, float height) {
/* 388 */       super((Entity)parent);
/* 389 */       this.size = EntitySize.func_220314_b(width, height);
/* 390 */       func_213323_x_();
/* 391 */       this.parentMob = parent;
/* 392 */       this.name = name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_70037_a(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */     
/*     */     protected void func_213281_b(CompoundNBT pCompound) {}
/*     */ 
/*     */     
/*     */     public boolean func_70067_L() {
/* 409 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_70097_a(DamageSource source, float amount) {
/* 414 */       return func_180431_b(source) ? false : this.parentMob.hurt(this, source, amount);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_70028_i(Entity entity) {
/* 419 */       return (this == entity || this.parentMob == entity);
/*     */     }
/*     */ 
/*     */     
/*     */     public EntitySize func_213305_a(Pose pPose) {
/* 424 */       return this.size;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\SeaCowEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */