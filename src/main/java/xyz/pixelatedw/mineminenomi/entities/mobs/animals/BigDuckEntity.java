/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.IJumpingMount;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.FollowParentGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.TemptGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BigDuckEntity extends TameableEntity implements IJumpingMount {
/*  63 */   private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.func_187226_a(BigDuckEntity.class, DataSerializers.field_187198_h);
/*     */   
/*     */   private static final String SADDLED_TAG = "isSaddled";
/*     */   private static final double TAME_CHANCE = 0.2D;
/*  67 */   private static final Item[] SADDLES = new Item[] { Items.field_151141_av };
/*  68 */   private static final Item[] FOOD = new Item[] { Items.field_222112_pR, Items.field_185163_cU, Items.field_151081_bc, Items.field_151080_bb, Items.field_151014_N };
/*     */   
/*     */   private static final int QUACK_ANIM_CYCLE = 20;
/*     */   private static final byte QUACK_EVENT = 100;
/*     */   private float playerJumpPendingScale;
/*     */   private boolean isJumping;
/*     */   private double floating;
/*  75 */   private int clientQuackTick = 0;
/*     */   
/*     */   public BigDuckEntity(EntityType type, World world) {
/*  78 */     super(type, world);
/*     */     
/*  80 */     if (world != null && !world.field_72995_K) {
/*  81 */       func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  86 */     return OPEntity.createAttributes()
/*  87 */       .func_233814_a_((Attribute)ModAttributes.FLOATING_TIME.get())
/*  88 */       .func_233814_a_(Attributes.field_233830_m_)
/*  89 */       .func_233815_a_(Attributes.field_233823_f_, 1.0D)
/*  90 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D)
/*  91 */       .func_233815_a_(Attributes.field_233821_d_, 0.27D)
/*  92 */       .func_233815_a_(Attributes.field_233818_a_, 25.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  97 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*  98 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/*  99 */     this.field_70714_bg.func_75776_a(2, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/* 100 */     this.field_70714_bg.func_75776_a(3, (Goal)new TemptGoal((CreatureEntity)this, 1.2D, false, Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_222112_pR })));
/* 101 */     this.field_70714_bg.func_75776_a(3, (Goal)new SitGoal(this));
/* 102 */     this.field_70714_bg.func_75776_a(3, (Goal)new FollowParentGoal((AnimalEntity)this, 1.15D));
/* 103 */     this.field_70714_bg.func_75776_a(4, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.0D, false));
/* 104 */     this.field_70714_bg.func_75776_a(5, (Goal)new RandomWalkingGoal((CreatureEntity)this, 0.6D));
/* 105 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 106 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 108 */     this.field_70715_bh.func_75776_a(1, (Goal)new OwnerHurtByTargetGoal(this));
/* 109 */     this.field_70715_bh.func_75776_a(2, (Goal)new OwnerHurtTargetGoal(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 114 */     super.func_70088_a();
/* 115 */     func_184212_Q().func_187214_a(IS_SADDLED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 120 */     super.func_213281_b(nbt);
/* 121 */     nbt.func_74757_a("isSaddled", ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 126 */     super.func_70037_a(nbt);
/* 127 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(nbt.func_74767_n("isSaddled")));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184639_G() {
/* 132 */     return (SoundEvent)ModSounds.BIG_DUCK_QUACK.get();
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource pDamageSource) {
/* 137 */     return (SoundEvent)ModSounds.BIG_DUCK_HURT.get();
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184615_bR() {
/* 142 */     return (SoundEvent)ModSounds.BIG_DUCK_HURT.get();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos pos, BlockState block) {
/* 147 */     func_184185_a(SoundEvents.field_187538_aa, 0.2F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 152 */     return 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 157 */     return func_70631_g_() ? (1.5F + this.field_70146_Z.nextFloat() / 3.0F) : (1.0F + this.field_70146_Z.nextFloat() / 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70642_aH() {
/* 162 */     this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/* 163 */     super.func_70642_aH();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 168 */     super.func_70636_d();
/*     */     
/* 170 */     if (this.field_70170_p.field_72995_K && 
/* 171 */       this.clientQuackTick > 0) {
/* 172 */       this.clientQuackTick--;
/*     */     }
/*     */ 
/*     */     
/* 176 */     Vector3d vector3d = func_213322_ci();
/* 177 */     if (!this.field_70122_E && this.floating > 0.0D) {
/* 178 */       if (vector3d.field_72448_b < 0.0D) {
/* 179 */         AbilityHelper.setDeltaMovement((Entity)this, vector3d.func_216372_d(1.0D, 0.6D, 1.0D));
/*     */       }
/* 181 */       this.field_70143_R = 0.0F;
/* 182 */       this.floating--;
/*     */     }
/* 184 */     else if (this.field_70122_E && !isJumping()) {
/* 185 */       this.floating = func_233637_b_((Attribute)ModAttributes.FLOATING_TIME.get());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 193 */     switch (id) {
/*     */       case 100:
/* 195 */         this.clientQuackTick = 20;
/*     */         break;
/*     */     } 
/* 198 */     super.func_70103_a(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_225503_b_(float distance, float damage) {
/* 203 */     boolean dealDamage = (damage > 0.0F && this.floating <= 0.0D);
/* 204 */     this.floating = func_233637_b_((Attribute)ModAttributes.FLOATING_TIME.get());
/* 205 */     if (dealDamage) {
/* 206 */       return super.func_225503_b_(distance, damage);
/*     */     }
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70665_d(DamageSource source, float amount) {
/* 213 */     this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/* 214 */     super.func_70665_d(source, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 219 */     if (!player.field_70170_p.field_72995_K && hand == Hand.MAIN_HAND) {
/* 220 */       ItemStack stack = player.func_184586_b((player.func_184600_cs() != null) ? player.func_184600_cs() : Hand.MAIN_HAND);
/* 221 */       boolean isSaddle = Arrays.<Item>stream(SADDLES).anyMatch(stack.func_77973_b()::equals);
/*     */       
/* 223 */       if (!func_70909_n()) {
/* 224 */         if (!stack.func_190926_b() && func_70877_b(stack)) {
/* 225 */           func_175505_a(player, stack);
/* 226 */           double chance = this.field_70146_Z.nextDouble();
/* 227 */           if (chance < 0.2D && !ForgeEventFactory.onAnimalTame((AnimalEntity)this, player)) {
/* 228 */             this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 229 */             func_193101_c(player);
/*     */           } 
/* 231 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } else {
/*     */         
/* 235 */         if (func_70877_b(stack) && func_110143_aJ() < func_110138_aP()) {
/* 236 */           func_175505_a(player, stack);
/* 237 */           func_70691_i(4.0F);
/* 238 */           this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 239 */           return ActionResultType.SUCCESS;
/*     */         } 
/* 241 */         if (isSaddle && !isSaddled() && !func_70631_g_()) {
/* 242 */           setSaddled(true);
/* 243 */           this.field_70170_p.func_217384_a((PlayerEntity)null, (Entity)this, SoundEvents.field_187726_cu, SoundCategory.PLAYERS, 0.5F, 1.0F);
/* 244 */           func_175505_a(player, stack);
/* 245 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */         
/* 248 */         if ((!func_70877_b(stack) || (func_70877_b(stack) && !func_204701_dC())) && player == func_70902_q() && !func_70631_g_()) {
/* 249 */           player.func_184220_m((Entity)this);
/* 250 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 256 */     return super.func_230254_b_(player, hand);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld pLevel, DifficultyInstance pDifficulty, SpawnReason pReason, @Nullable ILivingEntityData pSpawnData, @Nullable CompoundNBT pDataTag) {
/*     */     AgeableEntity.AgeableData ageableData;
/* 262 */     if (pSpawnData == null) {
/* 263 */       ageableData = new AgeableEntity.AgeableData(0.2F);
/*     */     }
/*     */ 
/*     */     
/* 267 */     func_110148_a(Attributes.field_233830_m_).func_111128_a(generateRandomJumpStrength());
/* 268 */     func_110148_a(Attributes.field_233821_d_).func_111128_a(generateRandomSpeed());
/* 269 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/*     */     
/* 271 */     double floatingTime = generateRandomFloatingTime();
/* 272 */     func_110148_a((Attribute)ModAttributes.FLOATING_TIME.get()).func_111128_a(floatingTime);
/* 273 */     this.floating = floatingTime;
/*     */     
/* 275 */     func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     
/* 277 */     return super.func_213386_a(pLevel, pDifficulty, pReason, (ILivingEntityData)ageableData, pDataTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/* 282 */     BigDuckEntity duck = (BigDuckEntity)((EntityType)ModEntities.BIG_DUCK.get()).func_200721_a((World)world);
/* 283 */     setOffspringAttributes(mate, duck);
/* 284 */     return (AgeableEntity)duck;
/*     */   }
/*     */   
/*     */   protected void setOffspringAttributes(AgeableEntity mate, BigDuckEntity offspring) {
/* 288 */     double hp = func_233638_c_(Attributes.field_233818_a_) + mate.func_233638_c_(Attributes.field_233818_a_) + generateRandomHealth();
/* 289 */     offspring.func_110148_a(Attributes.field_233818_a_).func_111128_a(hp / 3.0D);
/*     */     
/* 291 */     double jump = func_233638_c_(Attributes.field_233830_m_) + mate.func_233638_c_(Attributes.field_233830_m_) + generateRandomJumpStrength();
/* 292 */     offspring.func_110148_a(Attributes.field_233830_m_).func_111128_a(jump / 3.0D);
/*     */     
/* 294 */     double speed = func_233638_c_(Attributes.field_233821_d_) + mate.func_233638_c_(Attributes.field_233821_d_) + generateRandomSpeed();
/* 295 */     offspring.func_110148_a(Attributes.field_233821_d_).func_111128_a(speed / 3.0D);
/*     */     
/* 297 */     double floating = func_233638_c_((Attribute)ModAttributes.FLOATING_TIME.get()) + mate.func_233638_c_((Attribute)ModAttributes.FLOATING_TIME.get()) + generateRandomFloatingTime();
/* 298 */     offspring.func_110148_a((Attribute)ModAttributes.FLOATING_TIME.get()).func_111128_a(floating / 3.0D);
/*     */   }
/*     */   
/*     */   protected double generateRandomJumpStrength() {
/* 302 */     return 0.6D + this.field_70146_Z.nextDouble() * 0.4D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSpeed() {
/* 306 */     return 0.28D + this.field_70146_Z.nextDouble() * 0.08D;
/*     */   }
/*     */   
/*     */   protected double generateRandomHealth() {
/* 310 */     return 20.0D + this.field_70146_Z.nextDouble() * 10.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomFloatingTime() {
/* 314 */     return 50.0D + WyHelper.randomDouble(this.field_70146_Z) * 0.5D * 30.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d pTravelVector) {
/* 319 */     if (func_70089_S()) {
/* 320 */       if (func_184207_aI() && func_82171_bF() && isSaddled()) {
/* 321 */         LivingEntity livingentity = (LivingEntity)func_184179_bs();
/* 322 */         this.field_70177_z = livingentity.field_70177_z;
/* 323 */         this.field_70126_B = this.field_70177_z;
/* 324 */         this.field_70125_A = livingentity.field_70125_A * 0.5F;
/* 325 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/* 326 */         this.field_70761_aq = this.field_70177_z;
/* 327 */         this.field_70759_as = this.field_70761_aq;
/* 328 */         float f = livingentity.field_70702_br * 0.5F;
/* 329 */         float f1 = livingentity.field_191988_bg;
/* 330 */         if (f1 <= 0.0F) {
/* 331 */           f1 *= 0.25F;
/*     */         }
/*     */         
/* 334 */         if (this.playerJumpPendingScale > 0.0F && !isJumping() && this.field_70122_E) {
/* 335 */           double d1, d0 = func_233637_b_(Attributes.field_233830_m_) * this.playerJumpPendingScale * func_226269_ah_();
/*     */           
/* 337 */           if (func_70644_a(Effects.field_76430_j)) {
/* 338 */             d1 = d0 + ((func_70660_b(Effects.field_76430_j).func_76458_c() + 1) * 0.1F);
/*     */           } else {
/*     */             
/* 341 */             d1 = d0;
/*     */           } 
/*     */           
/* 344 */           Vector3d vector3d = func_213322_ci();
/* 345 */           AbilityHelper.setDeltaMovement((Entity)this, vector3d.field_72450_a, d1, vector3d.field_72449_c);
/* 346 */           setIsJumping(true);
/* 347 */           this.field_70160_al = true;
/* 348 */           ForgeHooks.onLivingJump((LivingEntity)this);
/* 349 */           if (f1 > 0.0F) {
/* 350 */             float f2 = MathHelper.func_76126_a(this.field_70177_z * 0.017453292F);
/* 351 */             float f3 = MathHelper.func_76134_b(this.field_70177_z * 0.017453292F);
/* 352 */             AbilityHelper.setDeltaMovement((Entity)this, 
/*     */                 
/* 354 */                 func_213322_ci().func_72441_c((-0.4F * f2 * this.playerJumpPendingScale), 0.0D, (0.4F * f3 * this.playerJumpPendingScale)));
/*     */           } 
/*     */           
/* 357 */           this.playerJumpPendingScale = 0.0F;
/*     */         } 
/*     */         
/* 360 */         if (func_184186_bw()) {
/* 361 */           func_70659_e((float)func_233637_b_(Attributes.field_233821_d_));
/* 362 */           super.func_213352_e(new Vector3d(f, pTravelVector.field_72448_b, f1));
/*     */         }
/* 364 */         else if (livingentity instanceof PlayerEntity) {
/* 365 */           AbilityHelper.setDeltaMovement((Entity)this, Vector3d.field_186680_a);
/*     */         } 
/*     */         
/* 368 */         if (this.field_70122_E) {
/* 369 */           this.playerJumpPendingScale = 0.0F;
/* 370 */           setIsJumping(false);
/*     */         } 
/*     */         
/* 373 */         func_233629_a_((LivingEntity)this, false);
/*     */       } else {
/*     */         
/* 376 */         super.func_213352_e(pTravelVector);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 383 */     return (!func_70909_n() && distance > 1024.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack stack) {
/* 388 */     return (!stack.func_190926_b() && Arrays.<Item>stream(FOOD).anyMatch(stack.func_77973_b()::equals));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_70610_aX() {
/* 393 */     return (super.func_70610_aX() && func_184207_aI() && isSaddled());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82171_bF() {
/* 398 */     return func_184179_bs() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 404 */     List<Entity> list = func_184188_bt();
/* 405 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 410 */     return 1.2D;
/*     */   }
/*     */   
/*     */   public void setSaddled(boolean flag) {
/* 414 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isSaddled() {
/* 418 */     return ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_110206_u(int jumpPower) {
/* 423 */     if (isSaddled()) {
/* 424 */       if (jumpPower < 0) {
/* 425 */         jumpPower = 0;
/*     */       }
/*     */       
/* 428 */       if (jumpPower >= 90) {
/* 429 */         this.playerJumpPendingScale = 1.0F;
/*     */       } else {
/*     */         
/* 432 */         this.playerJumpPendingScale = 0.4F + 0.4F * jumpPower / 90.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setIsJumping(boolean isJumping) {
/* 438 */     this.isJumping = isJumping;
/*     */   }
/*     */   
/*     */   public boolean isJumping() {
/* 442 */     return this.isJumping;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_184776_b() {
/* 447 */     return isSaddled();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184775_b(int power) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184777_r_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getQuackAnimationProgress(float partialTicks) {
/* 462 */     return ((20 - this.clientQuackTick) + partialTicks) / 20.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\BigDuckEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */