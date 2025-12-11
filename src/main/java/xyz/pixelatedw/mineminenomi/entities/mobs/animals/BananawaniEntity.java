/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.FollowParentGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.ChickenEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.DyeColor;
/*     */ import net.minecraft.item.DyeItem;
/*     */ import net.minecraft.item.Food;
/*     */ import net.minecraft.item.Foods;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BananawaniEntity extends TameableEntity implements ICommandReceiver {
/*  72 */   private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.func_187226_a(BananawaniEntity.class, DataSerializers.field_187198_h);
/*  73 */   private static final DataParameter<Integer> SADDLE_COLOR = EntityDataManager.func_187226_a(BananawaniEntity.class, DataSerializers.field_187192_b);
/*     */   
/*     */   private static final String SADDLED_TAG = "isSaddled";
/*     */   
/*     */   private static final String SADDLE_COLOR_TAG = "saddleColor";
/*     */   private static final double TAME_CHANCE = 0.25D;
/*  79 */   private static final Food[] FISH_FOODS = new Food[] { Foods.field_221434_j, Foods.field_221420_H, Foods.field_221424_L, Foods.field_221437_m, Foods.field_221441_q, Foods.field_221415_C };
/*     */   
/*  81 */   private static final Item[] SADDLES = new Item[] { Items.field_151141_av };
/*     */   
/*     */   private static final byte BITE_EVENT = 100;
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  86 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   private long lastMountTime;
/*     */   private long lastBiteTime;
/*     */   private int clientBiteTime;
/*     */   
/*     */   public BananawaniEntity(EntityType type, World world) {
/*  92 */     super(type, world);
/*     */     
/*  94 */     func_70661_as().func_212239_d(true);
/*     */     
/*  96 */     if (world != null && !world.field_72995_K) {
/*  97 */       func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 102 */     return OPEntity.createAttributes()
/* 103 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/* 108 */     CommandAbility.addCommandGoals((MobEntity)this);
/*     */     
/* 110 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/* 111 */     this.field_70714_bg.func_75776_a(2, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/* 112 */     this.field_70714_bg.func_75776_a(3, (Goal)new SitGoal(this));
/* 113 */     this.field_70714_bg.func_75776_a(3, (Goal)new FollowParentGoal((AnimalEntity)this, 1.15D));
/* 114 */     this.field_70714_bg.func_75776_a(4, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.0D, false));
/* 115 */     this.field_70714_bg.func_75776_a(5, (Goal)new RandomWalkingGoal((CreatureEntity)this, 0.8D));
/* 116 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 117 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 119 */     this.field_70715_bh.func_75776_a(1, (Goal)new OwnerHurtByTargetGoal(this));
/* 120 */     this.field_70715_bh.func_75776_a(1, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/* 121 */     this.field_70715_bh.func_75776_a(1, (Goal)new OwnerHurtTargetGoal(this));
/* 122 */     this.field_70715_bh.func_75776_a(1, (Goal)new PersonalSpaceTargetGoal((MobEntity)this));
/* 123 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, ChickenEntity.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 128 */     super.func_70088_a();
/* 129 */     func_184212_Q().func_187214_a(IS_SADDLED, Boolean.valueOf(false));
/* 130 */     func_184212_Q().func_187214_a(SADDLE_COLOR, Integer.valueOf(DyeColor.RED.func_196059_a()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 135 */     super.func_213281_b(nbt);
/* 136 */     nbt.func_74757_a("isSaddled", ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue());
/* 137 */     nbt.func_74768_a("saddleColor", ((Integer)this.field_70180_af.func_187225_a(SADDLE_COLOR)).intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 142 */     super.func_70037_a(nbt);
/* 143 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(nbt.func_74767_n("isSaddled")));
/* 144 */     this.field_70180_af.func_187227_b(SADDLE_COLOR, Integer.valueOf(nbt.func_74762_e("saddleColor")));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos pos, BlockState block) {
/* 149 */     func_184185_a(SoundEvents.field_187709_dP, 0.15F, 1.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 154 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 159 */     return func_70631_g_() ? (1.5F + this.field_70146_Z.nextFloat() / 2.0F) : (1.25F + this.field_70146_Z.nextFloat() / 2.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 165 */     super.func_70071_h_();
/*     */     
/* 167 */     if (func_184186_bw() && 
/* 168 */       func_70090_H()) {
/* 169 */       double waterHeight = func_226278_cu_() - func_233571_b_((ITag)FluidTags.field_206959_a);
/* 170 */       double upforce = (func_226278_cu_() - waterHeight) / func_213302_cg() - 0.35D;
/*     */       
/* 172 */       Vector3d lookVec = func_70040_Z().func_216372_d(2.0D, 1.0D, 2.0D);
/* 173 */       BlockPos frontPos = func_233580_cy_().func_177963_a(lookVec.field_72450_a, 0.0D, lookVec.field_72449_c);
/* 174 */       BlockState state = this.field_70170_p.func_180495_p(frontPos);
/*     */ 
/*     */       
/* 177 */       if (!state.func_196958_f() && state.func_204520_s().func_206888_e()) {
/* 178 */         upforce += 0.35D;
/*     */       }
/*     */       
/* 181 */       if (upforce > 0.0D) {
/* 182 */         double swimSpeed = func_233637_b_((Attribute)ForgeMod.SWIM_SPEED.get());
/* 183 */         Vector3d movement = func_213322_ci();
/* 184 */         AbilityHelper.setDeltaMovement((Entity)this, movement.field_72450_a * swimSpeed, (movement.field_72448_b + upforce * 0.061D) * 0.75D, movement.field_72449_c * swimSpeed);
/*     */       } 
/*     */       
/* 187 */       func_213315_a(MoverType.SELF, func_213322_ci());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 194 */     if (!player.field_70170_p.field_72995_K && hand == Hand.MAIN_HAND) {
/* 195 */       ItemStack stack = player.func_184586_b((player.func_184600_cs() != null) ? player.func_184600_cs() : Hand.MAIN_HAND);
/* 196 */       boolean isSaddle = Arrays.<Item>stream(SADDLES).anyMatch(stack.func_77973_b()::equals);
/* 197 */       boolean isDye = (!stack.func_190926_b() && stack.func_77973_b() instanceof DyeItem);
/*     */       
/* 199 */       if (!func_70909_n()) {
/* 200 */         if (!stack.func_190926_b() && func_70877_b(stack)) {
/* 201 */           func_175505_a(player, stack);
/* 202 */           double chance = this.field_70146_Z.nextDouble();
/* 203 */           if (chance < 0.25D && !ForgeEventFactory.onAnimalTame((AnimalEntity)this, player)) {
/* 204 */             this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 205 */             func_193101_c(player);
/*     */           } 
/* 207 */           return ActionResultType.SUCCESS;
/*     */         }
/*     */       
/*     */       }
/* 211 */       else if (isDye) {
/* 212 */         DyeColor dyecolor = ((DyeItem)stack.func_77973_b()).func_195962_g();
/* 213 */         if (dyecolor != getSaddleColor()) {
/* 214 */           setSaddleColor(dyecolor);
/* 215 */           func_175505_a(player, stack);
/* 216 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } else {
/* 219 */         if (func_70877_b(stack) && func_110143_aJ() < func_110138_aP()) {
/* 220 */           func_70691_i(4.0F);
/* 221 */           func_175505_a(player, stack);
/* 222 */           this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 223 */           return ActionResultType.SUCCESS;
/*     */         } 
/* 225 */         if (isSaddle && !isSaddled() && !func_70631_g_()) {
/* 226 */           setSaddled(true);
/* 227 */           this.field_70170_p.func_217384_a((PlayerEntity)null, (Entity)this, SoundEvents.field_187726_cu, SoundCategory.PLAYERS, 0.5F, 1.0F);
/* 228 */           func_175505_a(player, stack);
/* 229 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */         
/* 232 */         if ((!func_70877_b(stack) || (func_70877_b(stack) && !func_204701_dC())) && player == func_70902_q() && !func_70631_g_()) {
/* 233 */           player.func_184220_m((Entity)this);
/* 234 */           this.lastMountTime = this.field_70170_p.func_82737_E();
/* 235 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 241 */     return super.func_230254_b_(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d travelVector) {
/* 246 */     if (func_70089_S()) {
/* 247 */       if (func_184207_aI() && func_82171_bF() && isSaddled()) {
/* 248 */         LivingEntity livingentity = (LivingEntity)func_184179_bs();
/* 249 */         this.field_70177_z = livingentity.field_70177_z;
/* 250 */         this.field_70126_B = this.field_70177_z;
/* 251 */         this.field_70125_A = livingentity.field_70125_A * 0.5F;
/* 252 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/* 253 */         this.field_70761_aq = this.field_70177_z;
/* 254 */         this.field_70759_as = this.field_70761_aq;
/* 255 */         float f = livingentity.field_70702_br * 0.1F;
/* 256 */         float f1 = livingentity.field_191988_bg;
/* 257 */         if (f1 <= 0.0F) {
/* 258 */           f1 *= 0.25F;
/*     */         }
/*     */         
/* 261 */         if (func_184186_bw()) {
/* 262 */           func_70659_e((float)func_233637_b_(Attributes.field_233821_d_) * 0.7F);
/* 263 */           super.func_213352_e(new Vector3d(f, travelVector.field_72448_b, f1));
/*     */         }
/* 265 */         else if (livingentity instanceof PlayerEntity) {
/* 266 */           AbilityHelper.setDeltaMovement((Entity)this, Vector3d.field_186680_a);
/*     */         } 
/*     */         
/* 269 */         func_233629_a_((LivingEntity)this, false);
/*     */       } else {
/*     */         
/* 272 */         super.func_213352_e(travelVector);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 279 */     super.func_70636_d();
/* 280 */     if (this.clientBiteTime > 0) {
/* 281 */       this.clientBiteTime--;
/*     */     }
/* 283 */     if (!this.field_70170_p.field_72995_K && func_70909_n() && func_184207_aI() && func_184188_bt().contains(func_70902_q()) && func_70902_q() != null && 
/* 284 */       (func_70902_q()).field_82175_bq && this.field_70170_p.func_82737_E() >= this.lastMountTime + 20L && this.field_70170_p.func_82737_E() >= this.lastBiteTime + 20L) {
/* 285 */       func_184609_a(Hand.MAIN_HAND);
/* 286 */       Vector3d look = func_213303_ch().func_178787_e(func_70902_q().func_70040_Z().func_216372_d(4.0D, 1.0D, 4.0D));
/* 287 */       Vector3d ogPos = new Vector3d(look.func_82615_a(), func_226278_cu_(), look.func_82616_c());
/* 288 */       List<LivingEntity> targets = WyHelper.getNearbyLiving(ogPos, (IWorld)this.field_70170_p, 1.0D, ModEntityPredicates.getEnemyFactions(func_70902_q()));
/* 289 */       targets.remove(this);
/* 290 */       targets.remove(func_70902_q());
/* 291 */       this.lastBiteTime = this.field_70170_p.func_82737_E();
/* 292 */       this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/* 293 */       for (Entity e : targets) {
/* 294 */         e.func_70097_a(DamageSource.func_76358_a((LivingEntity)this), (float)func_233637_b_(Attributes.field_233823_f_));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_145775_I() {
/* 301 */     AxisAlignedBB axisalignedbb = func_174813_aQ();
/* 302 */     BlockPos blockpos = new BlockPos(axisalignedbb.field_72340_a + 0.001D, axisalignedbb.field_72338_b + 0.001D, axisalignedbb.field_72339_c + 0.001D);
/* 303 */     BlockPos blockpos1 = new BlockPos(axisalignedbb.field_72336_d - 0.001D, axisalignedbb.field_72337_e - 0.001D, axisalignedbb.field_72334_f - 0.001D);
/* 304 */     BlockPos.Mutable pos = new BlockPos.Mutable();
/* 305 */     if (this.field_70170_p.func_175707_a(blockpos, blockpos1)) {
/* 306 */       for (int i = blockpos.func_177958_n(); i <= blockpos1.func_177958_n(); i++) {
/* 307 */         for (int j = blockpos.func_177956_o(); j <= blockpos1.func_177956_o(); j++) {
/* 308 */           for (int k = blockpos.func_177952_p(); k <= blockpos1.func_177952_p(); k++) {
/* 309 */             pos.func_181079_c(i, j, k);
/* 310 */             BlockState state = this.field_70170_p.func_180495_p((BlockPos)pos);
/*     */             
/* 312 */             if (state.func_177230_c() == Blocks.field_196651_dG) {
/* 313 */               this.field_70170_p.func_225521_a_((BlockPos)pos, true, (Entity)this);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_226292_a_(Hand hand, boolean updateSelf) {
/* 323 */     if (func_70638_az() != null) {
/* 324 */       this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/*     */     }
/* 326 */     super.func_226292_a_(hand, updateSelf);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld pLevel, DifficultyInstance pDifficulty, SpawnReason pReason, @Nullable ILivingEntityData pSpawnData, @Nullable CompoundNBT pDataTag) {
/*     */     AgeableEntity.AgeableData ageableData;
/* 332 */     if (pSpawnData == null) {
/* 333 */       ageableData = new AgeableEntity.AgeableData(0.2F);
/*     */     }
/*     */ 
/*     */     
/* 337 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/* 338 */     func_110148_a(Attributes.field_233826_i_).func_111128_a(generateRandomArmor());
/* 339 */     func_110148_a(Attributes.field_233823_f_).func_111128_a(generateRandomAttack());
/* 340 */     func_110148_a(Attributes.field_233821_d_).func_111128_a(generateRandomSpeed());
/* 341 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(generateRandomSwimSpeed());
/*     */     
/* 343 */     func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     
/* 345 */     return super.func_213386_a(pLevel, pDifficulty, pReason, (ILivingEntityData)ageableData, pDataTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/* 350 */     BananawaniEntity offspring = (BananawaniEntity)((EntityType)ModEntities.BANANAWANI.get()).func_200721_a((World)world);
/* 351 */     setOffspringAttributes(mate, offspring);
/* 352 */     return (AgeableEntity)offspring;
/*     */   }
/*     */   
/*     */   protected void setOffspringAttributes(AgeableEntity mate, BananawaniEntity offspring) {
/* 356 */     double hp = func_233638_c_(Attributes.field_233818_a_) + mate.func_233638_c_(Attributes.field_233818_a_) + generateRandomHealth();
/* 357 */     offspring.func_110148_a(Attributes.field_233818_a_).func_111128_a(hp / 3.0D);
/*     */     
/* 359 */     double armor = func_233638_c_(Attributes.field_233826_i_) + mate.func_233638_c_(Attributes.field_233826_i_) + generateRandomArmor();
/* 360 */     offspring.func_110148_a(Attributes.field_233826_i_).func_111128_a(armor / 3.0D);
/*     */     
/* 362 */     double speed = func_233638_c_(Attributes.field_233821_d_) + mate.func_233638_c_(Attributes.field_233821_d_) + generateRandomSpeed();
/* 363 */     offspring.func_110148_a(Attributes.field_233821_d_).func_111128_a(speed / 3.0D);
/*     */     
/* 365 */     double damage = func_233638_c_(Attributes.field_233823_f_) + mate.func_233638_c_(Attributes.field_233823_f_) + generateRandomAttack();
/* 366 */     offspring.func_110148_a(Attributes.field_233823_f_).func_111128_a(damage / 3.0D);
/*     */     
/* 368 */     double swimSpeed = func_233638_c_((Attribute)ForgeMod.SWIM_SPEED.get()) + mate.func_233638_c_((Attribute)ForgeMod.SWIM_SPEED.get()) + generateRandomSwimSpeed();
/* 369 */     offspring.func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(swimSpeed / 3.0D);
/*     */   }
/*     */   
/*     */   protected double generateRandomArmor() {
/* 373 */     return 2.0D + this.field_70146_Z.nextDouble() * 2.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomAttack() {
/* 377 */     return 7.0D + this.field_70146_Z.nextDouble() * 2.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomHealth() {
/* 381 */     return 50.0D + this.field_70146_Z.nextDouble() * 10.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSpeed() {
/* 385 */     return 0.25D + this.field_70146_Z.nextDouble() * 0.07D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSwimSpeed() {
/* 389 */     return 1.0D + this.field_70146_Z.nextDouble() * 0.15D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 394 */     return (!func_70909_n() && distance > 1024.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 399 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack stack) {
/* 404 */     if (stack.func_190926_b()) {
/* 405 */       return false;
/*     */     }
/* 407 */     Food food = stack.func_77973_b().func_219967_s();
/* 408 */     return (food != null && (food.func_221467_c() || Arrays.<Food>stream(FISH_FOODS).anyMatch(food::equals)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_70610_aX() {
/* 413 */     return (super.func_70610_aX() && func_184207_aI() && isSaddled());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82171_bF() {
/* 418 */     return func_184179_bs() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96092_aw() {
/* 423 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 429 */     switch (id) {
/*     */       case 100:
/* 431 */         this.clientBiteTime = 20;
/*     */         break;
/*     */     } 
/* 434 */     super.func_70103_a(id);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 440 */     List<Entity> list = func_184188_bt();
/* 441 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 446 */     return 1.4D;
/*     */   }
/*     */   
/*     */   public void setSaddled(boolean flag) {
/* 450 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isSaddled() {
/* 454 */     return ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 459 */     return func_152114_e(commandSender);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 464 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 465 */     this.lastCommandSender = commandSender;
/* 466 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 471 */     return this.currentCommand;
/*     */   }
/*     */   
/*     */   public DyeColor getSaddleColor() {
/* 475 */     return DyeColor.func_196056_a(((Integer)this.field_70180_af.func_187225_a(SADDLE_COLOR)).intValue());
/*     */   }
/*     */   
/*     */   public void setSaddleColor(DyeColor pCollarColor) {
/* 479 */     this.field_70180_af.func_187227_b(SADDLE_COLOR, Integer.valueOf(pCollarColor.func_196059_a()));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 485 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 490 */     return this.lastCommandTime;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getBiteAnimationProgress(float partialTicks) {
/* 495 */     return ((20 - this.clientBiteTime) + partialTicks) / 20.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\BananawaniEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */