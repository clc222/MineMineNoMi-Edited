/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.TemptGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.shapes.VoxelShapes;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IRandomTexture;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class YagaraBullEntity extends TameableEntity implements IRandomTexture {
/*  68 */   private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.func_187226_a(YagaraBullEntity.class, DataSerializers.field_187198_h);
/*  69 */   private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.func_187226_a(YagaraBullEntity.class, DataSerializers.field_187192_b);
/*     */   private static final double TAME_CHANCE = 0.35D;
/*  71 */   private static final Item[] SADDLES = new Item[] { Items.field_151141_av };
/*  72 */   private static final Item[] TAMING_FOOD = new Item[] { Items.field_196088_aY, Items.field_221600_aB, Items.field_221601_aC };
/*  73 */   private static final Item[] FOOD = new Item[] { Items.field_196102_ba, Items.field_196104_bb, Items.field_196086_aW, Items.field_196087_aX, Items.field_196089_aZ, Items.field_196088_aY };
/*     */   
/*     */   private Status status;
/*     */   private Status oldStatus;
/*     */   private double waterLevel;
/*     */   
/*     */   public YagaraBullEntity(EntityType type, World world) {
/*  80 */     super(type, world);
/*     */     
/*  82 */     func_70661_as().func_212239_d(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  87 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/*  88 */     this.field_70714_bg.func_75776_a(0, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/*  89 */     this.field_70714_bg.func_75776_a(1, (Goal)new RandomWalkingGoal((CreatureEntity)this, 1.0D));
/*  90 */     this.field_70714_bg.func_75776_a(1, (Goal)new RandomSwimmingGoal((CreatureEntity)this, 1.5D, 20));
/*  91 */     this.field_70714_bg.func_75776_a(1, (Goal)new PanicGoal((CreatureEntity)this, 1.25D));
/*  92 */     this.field_70714_bg.func_75776_a(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  93 */     this.field_70714_bg.func_75776_a(2, (Goal)new LookRandomlyGoal((MobEntity)this));
/*  94 */     this.field_70714_bg.func_75776_a(3, (Goal)new TemptGoal((CreatureEntity)this, 1.25D, Ingredient.func_199804_a(new IItemProvider[] { (IItemProvider)Items.field_196088_aY }, ), true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  99 */     super.func_70088_a();
/* 100 */     func_184212_Q().func_187214_a(IS_SADDLED, Boolean.valueOf(false));
/* 101 */     func_184212_Q().func_187214_a(TEXTURE_ID, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 105 */     return MobEntity.func_233666_p_()
/* 106 */       .func_233815_a_(Attributes.field_233821_d_, 0.20000000298023224D)
/* 107 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D)
/* 108 */       .func_233815_a_(Attributes.field_233818_a_, 30.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public CreatureAttribute func_70668_bt() {
/* 113 */     return CreatureAttribute.field_203100_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96092_aw() {
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 123 */     this.oldStatus = this.status;
/* 124 */     this.status = getStatus();
/*     */     
/* 126 */     super.func_70071_h_();
/*     */     
/* 128 */     if (func_184186_bw()) {
/* 129 */       double d1 = func_189652_ae() ? 0.0D : -0.004999999888241291D;
/* 130 */       double d2 = 0.0D;
/* 131 */       float invFriction = 0.05F;
/*     */       
/* 133 */       if (this.status == Status.IN_WATER) {
/* 134 */         d2 = (this.waterLevel - func_226278_cu_()) / func_213302_cg();
/* 135 */         invFriction = (float)func_233637_b_((Attribute)ForgeMod.SWIM_SPEED.get());
/*     */       }
/* 137 */       else if (this.status == Status.UNDER_WATER) {
/* 138 */         d2 = 0.009999999776482582D;
/* 139 */         invFriction = 0.45F;
/*     */       }
/* 141 */       else if (this.status == Status.IN_AIR) {
/* 142 */         invFriction = 0.9F;
/*     */       } 
/* 144 */       Vector3d vector3d = func_213322_ci();
/* 145 */       AbilityHelper.setDeltaMovement((Entity)this, vector3d.field_72450_a * invFriction, vector3d.field_72448_b + d1, vector3d.field_72449_c * invFriction);
/* 146 */       if (d2 > 0.0D) {
/* 147 */         Vector3d vector3d1 = func_213322_ci();
/* 148 */         AbilityHelper.setDeltaMovement((Entity)this, vector3d1.field_72450_a, (vector3d1.field_72448_b + d2 * 0.06153846016296973D) * 0.75D, vector3d1.field_72449_c);
/*     */       } 
/*     */       
/* 151 */       func_213315_a(MoverType.SELF, func_213322_ci());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_145775_I() {
/* 157 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 158 */     BlockPos blockpos = new BlockPos(axisalignedbb.field_72340_a + 0.001D, axisalignedbb.field_72338_b + 0.001D, axisalignedbb.field_72339_c + 0.001D);
/* 159 */     BlockPos blockpos1 = new BlockPos(axisalignedbb.field_72336_d - 0.001D, axisalignedbb.field_72337_e - 0.001D, axisalignedbb.field_72334_f - 0.001D);
/* 160 */     BlockPos.Mutable pos = new BlockPos.Mutable();
/* 161 */     if (this.field_70170_p.func_175707_a(blockpos, blockpos1)) {
/* 162 */       for (int i = blockpos.func_177958_n(); i <= blockpos1.func_177958_n(); i++) {
/* 163 */         for (int j = blockpos.func_177956_o(); j <= blockpos1.func_177956_o(); j++) {
/* 164 */           for (int k = blockpos.func_177952_p(); k <= blockpos1.func_177952_p(); k++) {
/* 165 */             pos.func_181079_c(i, j, k);
/* 166 */             BlockState state = this.field_70170_p.func_180495_p((BlockPos)pos);
/*     */             
/* 168 */             if (state.func_177230_c() == Blocks.field_196651_dG) {
/* 169 */               this.field_70170_p.func_225521_a_((BlockPos)pos, true, (Entity)this);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d pTravelVector) {
/* 179 */     if (func_70089_S()) {
/* 180 */       if (func_184207_aI() && func_82171_bF() && isSaddled()) {
/* 181 */         LivingEntity livingentity = (LivingEntity)func_184179_bs();
/* 182 */         this.field_70177_z = livingentity.field_70177_z;
/* 183 */         this.field_70126_B = this.field_70177_z;
/* 184 */         this.field_70125_A = livingentity.field_70125_A * 0.5F;
/* 185 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/* 186 */         this.field_70761_aq = this.field_70177_z;
/* 187 */         this.field_70759_as = this.field_70761_aq;
/* 188 */         float f = livingentity.field_70702_br * 0.3F;
/* 189 */         float f1 = livingentity.field_191988_bg;
/* 190 */         if (f1 <= 0.0F) {
/* 191 */           f1 *= 0.25F;
/*     */         }
/*     */         
/* 194 */         if (func_184186_bw()) {
/* 195 */           func_70659_e((float)func_233637_b_(Attributes.field_233821_d_));
/* 196 */           super.func_213352_e(new Vector3d(f, pTravelVector.field_72448_b, f1));
/*     */         }
/* 198 */         else if (livingentity instanceof PlayerEntity) {
/* 199 */           AbilityHelper.setDeltaMovement((Entity)this, Vector3d.field_186680_a);
/*     */         } 
/*     */         
/* 202 */         func_233629_a_((LivingEntity)this, false);
/*     */       } else {
/*     */         
/* 205 */         super.func_213352_e(pTravelVector);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*     */     AgeableEntity.AgeableData ageableData;
/* 213 */     if (spawnData == null) {
/* 214 */       ageableData = new AgeableEntity.AgeableData(0.2F);
/*     */     }
/*     */     
/* 217 */     setTexture(this.field_70146_Z.nextInt(MobsHelper.YAGARA_BULL_TEXTURES.length));
/*     */     
/* 219 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/* 220 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(generateRandomSwimSpeed());
/*     */     
/* 222 */     func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     
/* 224 */     return super.func_213386_a(world, difficulty, reason, (ILivingEntityData)ageableData, dataTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/* 229 */     YagaraBullEntity offspring = (YagaraBullEntity)((EntityType)ModEntities.YAGARA_BULL.get()).func_200721_a((World)world);
/* 230 */     setOffspringAttributes(mate, offspring);
/* 231 */     return (AgeableEntity)offspring;
/*     */   }
/*     */   
/*     */   protected void setOffspringAttributes(AgeableEntity mate, YagaraBullEntity offspring) {
/* 235 */     double hp = func_233638_c_(Attributes.field_233818_a_) + mate.func_233638_c_(Attributes.field_233818_a_) + generateRandomHealth();
/* 236 */     offspring.func_110148_a(Attributes.field_233818_a_).func_111128_a(hp / 3.0D);
/*     */     
/* 238 */     double swimSpeed = func_233638_c_((Attribute)ForgeMod.SWIM_SPEED.get()) + mate.func_233638_c_((Attribute)ForgeMod.SWIM_SPEED.get()) + generateRandomSwimSpeed();
/* 239 */     offspring.func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(swimSpeed / 3.0D);
/*     */   }
/*     */   
/*     */   protected double generateRandomHealth() {
/* 243 */     return 30.0D + this.field_70146_Z.nextDouble() * 10.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSwimSpeed() {
/* 247 */     return 1.1D + this.field_70146_Z.nextDouble() * 0.15D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_70610_aX() {
/* 252 */     return (super.func_70610_aX() && func_184207_aI() && isSaddled());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82171_bF() {
/* 257 */     return func_184179_bs() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 262 */     if (player.field_70170_p.field_72995_K || hand != Hand.MAIN_HAND) {
/* 263 */       return ActionResultType.FAIL;
/*     */     }
/*     */ 
/*     */     
/* 267 */     if (func_70909_n() && player == func_70902_q()) {
/* 268 */       ItemStack stack = player.func_184614_ca();
/*     */       
/* 270 */       if (!stack.func_190926_b()) {
/* 271 */         boolean isFood = Arrays.<Item>stream(FOOD).anyMatch(stack.func_77973_b()::equals);
/* 272 */         boolean isSaddle = Arrays.<Item>stream(SADDLES).anyMatch(stack.func_77973_b()::equals);
/*     */         
/* 274 */         if (isFood && func_110143_aJ() < func_110138_aP()) {
/* 275 */           stack.func_190918_g(1);
/* 276 */           func_70691_i(4.0F);
/* 277 */           spawnHeartParticles();
/* 278 */           return ActionResultType.SUCCESS;
/*     */         } 
/* 280 */         if (isSaddle && !isSaddled()) {
/* 281 */           setSaddled(true);
/* 282 */           this.field_70170_p.func_184133_a((PlayerEntity)null, func_233580_cy_(), SoundEvents.field_187726_cu, SoundCategory.PLAYERS, 0.5F, 1.0F);
/* 283 */           stack.func_190918_g(1);
/*     */         } 
/*     */       } else {
/*     */         
/* 287 */         player.func_184220_m((Entity)this);
/*     */       }
/*     */     
/* 290 */     } else if (!func_70909_n()) {
/*     */       
/* 292 */       ItemStack stack = player.func_184614_ca();
/*     */       
/* 294 */       if (!stack.func_190926_b()) {
/* 295 */         boolean isFood = Arrays.<Item>stream(TAMING_FOOD).anyMatch(stack.func_77973_b()::equals);
/*     */         
/* 297 */         if (isFood) {
/* 298 */           stack.func_190918_g(1);
/* 299 */           double chance = this.field_70146_Z.nextDouble();
/*     */           
/* 301 */           if (chance < 0.35D && !ForgeEventFactory.onAnimalTame((AnimalEntity)this, player)) {
/* 302 */             spawnHeartParticles();
/* 303 */             func_193101_c(player);
/*     */           } 
/*     */           
/* 306 */           return ActionResultType.FAIL;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 311 */     return ActionResultType.FAIL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 316 */     super.func_213281_b(nbt);
/* 317 */     nbt.func_74768_a("Texture", getTextureId());
/* 318 */     nbt.func_74757_a("IsSaddled", ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 323 */     super.func_70037_a(nbt);
/* 324 */     setTexture(nbt.func_74762_e("Texture"));
/* 325 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(nbt.func_74767_n("IsSaddled")));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 331 */     List<Entity> list = func_184188_bt();
/* 332 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_241845_aY() {
/* 337 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 342 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 347 */     return 0.6D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 352 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 357 */     return (!func_70909_n() && distance > 1024.0D);
/*     */   }
/*     */   
/*     */   public void spawnHeartParticles() {
/* 361 */     for (int i = 0; i < 5; i++) {
/* 362 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 363 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 364 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */       
/* 366 */       ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)ParticleTypes.field_197633_z, func_226277_ct_() + offsetX, func_226278_cu_() + 1.0D + offsetY, func_226281_cx_() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSaddled(boolean flag) {
/* 371 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isSaddled() {
/* 375 */     return ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue();
/*     */   }
/*     */   
/*     */   public int getTextureId() {
/* 379 */     return ((Integer)func_184212_Q().func_187225_a(TEXTURE_ID)).intValue();
/*     */   }
/*     */   
/*     */   protected void setTexture(int texture) {
/* 383 */     func_184212_Q().func_187227_b(TEXTURE_ID, Integer.valueOf(texture));
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getCurrentTexture() {
/* 388 */     return MobsHelper.YAGARA_BULL_TEXTURES[getTextureId()];
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getDefaultTexture() {
/* 393 */     return MobsHelper.YAGARA_BULL_TEXTURES[0];
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Status isUnderwater() {
/* 398 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 399 */     double d0 = axisalignedbb.field_72337_e + 0.002D;
/* 400 */     int i = MathHelper.func_76128_c(axisalignedbb.field_72340_a);
/* 401 */     int j = MathHelper.func_76143_f(axisalignedbb.field_72336_d);
/* 402 */     int k = MathHelper.func_76128_c(axisalignedbb.field_72338_b);
/* 403 */     int l = MathHelper.func_76143_f(d0);
/* 404 */     int i1 = MathHelper.func_76128_c(axisalignedbb.field_72339_c);
/* 405 */     int j1 = MathHelper.func_76143_f(axisalignedbb.field_72334_f);
/* 406 */     boolean flag = false;
/* 407 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 409 */     for (int k1 = i; k1 < j; k1++) {
/* 410 */       for (int l1 = k; l1 < l; l1++) {
/* 411 */         for (int i2 = i1; i2 < j1; i2++) {
/* 412 */           blockpos$mutable.func_181079_c(k1, l1, i2);
/* 413 */           FluidState fluidstate = this.field_70170_p.func_204610_c((BlockPos)blockpos$mutable);
/* 414 */           if (fluidstate.func_206884_a((ITag)FluidTags.field_206959_a) && d0 < (blockpos$mutable.func_177956_o() + fluidstate.func_215679_a((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable))) {
/* 415 */             if (!fluidstate.func_206889_d()) {
/* 416 */               return Status.UNDER_FLOWING_WATER;
/*     */             }
/*     */             
/* 419 */             flag = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 425 */     return flag ? Status.UNDER_WATER : null;
/*     */   }
/*     */   private boolean checkInWater() {
/*     */     int m;
/* 429 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 430 */     int i = MathHelper.func_76128_c(axisalignedbb.field_72340_a);
/* 431 */     int j = MathHelper.func_76143_f(axisalignedbb.field_72336_d);
/* 432 */     int k = MathHelper.func_76128_c(axisalignedbb.field_72338_b);
/* 433 */     int l = MathHelper.func_76143_f(axisalignedbb.field_72338_b + 0.001D);
/* 434 */     int i1 = MathHelper.func_76128_c(axisalignedbb.field_72339_c);
/* 435 */     int j1 = MathHelper.func_76143_f(axisalignedbb.field_72334_f);
/* 436 */     boolean flag = false;
/* 437 */     this.waterLevel = Double.MIN_VALUE;
/* 438 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 440 */     for (int k1 = i; k1 < j; k1++) {
/* 441 */       for (int l1 = k; l1 < l; l1++) {
/* 442 */         for (int i2 = i1; i2 < j1; i2++) {
/* 443 */           blockpos$mutable.func_181079_c(k1, l1, i2);
/* 444 */           FluidState fluidstate = this.field_70170_p.func_204610_c((BlockPos)blockpos$mutable);
/* 445 */           if (fluidstate.func_206884_a((ITag)FluidTags.field_206959_a)) {
/* 446 */             float f = l1 + fluidstate.func_215679_a((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable);
/* 447 */             this.waterLevel = Math.max(f, this.waterLevel);
/* 448 */             m = flag | ((axisalignedbb.field_72338_b < f) ? 1 : 0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 454 */     return m;
/*     */   }
/*     */   
/*     */   public float getGroundFriction() {
/* 458 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 459 */     AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b - 0.001D, axisalignedbb.field_72339_c, axisalignedbb.field_72336_d, axisalignedbb.field_72338_b, axisalignedbb.field_72334_f);
/*     */     
/* 461 */     int i = MathHelper.func_76128_c(axisalignedbb1.field_72340_a) - 1;
/* 462 */     int j = MathHelper.func_76143_f(axisalignedbb1.field_72336_d) + 1;
/* 463 */     int k = MathHelper.func_76128_c(axisalignedbb1.field_72338_b) - 1;
/* 464 */     int l = MathHelper.func_76143_f(axisalignedbb1.field_72337_e) + 1;
/* 465 */     int i1 = MathHelper.func_76128_c(axisalignedbb1.field_72339_c) - 1;
/* 466 */     int j1 = MathHelper.func_76143_f(axisalignedbb1.field_72334_f) + 1;
/* 467 */     VoxelShape voxelshape = VoxelShapes.func_197881_a(axisalignedbb1);
/* 468 */     float f = 0.0F;
/* 469 */     int k1 = 0;
/* 470 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/*     */     
/* 472 */     for (int l1 = i; l1 < j; l1++) {
/* 473 */       for (int i2 = i1; i2 < j1; i2++) {
/* 474 */         int j2 = ((l1 != i && l1 != j - 1) ? 0 : 1) + ((i2 != i1 && i2 != j1 - 1) ? 0 : 1);
/* 475 */         if (j2 != 2) {
/* 476 */           for (int k2 = k; k2 < l; k2++) {
/* 477 */             if (j2 <= 0 || (k2 != k && k2 != l - 1)) {
/* 478 */               blockpos$mutable.func_181079_c(l1, k2, i2);
/* 479 */               BlockState blockstate = this.field_70170_p.func_180495_p((BlockPos)blockpos$mutable);
/* 480 */               if (!(blockstate.func_177230_c() instanceof net.minecraft.block.LilyPadBlock) && VoxelShapes.func_197879_c(blockstate
/* 481 */                   .func_196952_d((IBlockReader)this.field_70170_p, (BlockPos)blockpos$mutable).func_197751_a(l1, k2, i2), voxelshape, IBooleanFunction.field_223238_i_)) {
/*     */ 
/*     */                 
/* 484 */                 f += blockstate.getSlipperiness((IWorldReader)this.field_70170_p, (BlockPos)blockpos$mutable, (Entity)this);
/* 485 */                 k1++;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 493 */     return f / k1;
/*     */   }
/*     */   
/*     */   private Status getStatus() {
/* 497 */     Status status = isUnderwater();
/* 498 */     if (status != null) {
/* 499 */       this.waterLevel = (func_174813_aQ()).field_72337_e;
/* 500 */       return status;
/*     */     } 
/* 502 */     if (checkInWater()) {
/* 503 */       return Status.IN_WATER;
/*     */     }
/*     */     
/* 506 */     float f = getGroundFriction();
/* 507 */     if (f > 0.0F)
/*     */     {
/* 509 */       return Status.ON_LAND;
/*     */     }
/*     */     
/* 512 */     return Status.IN_AIR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<YagaraBullEntity> entity, IWorld world, SpawnReason spawnType, BlockPos pos, Random random) {
/* 518 */     return world.func_180495_p(pos.func_177977_b()).func_204520_s().func_206884_a((ITag)FluidTags.field_206959_a);
/*     */   }
/*     */   
/*     */   public enum Status
/*     */   {
/* 523 */     IN_WATER, UNDER_WATER, UNDER_FLOWING_WATER, ON_LAND, IN_AIR;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\YagaraBullEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */