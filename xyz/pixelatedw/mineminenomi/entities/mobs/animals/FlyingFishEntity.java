/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.controller.LookController;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.FindWaterGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Food;
/*     */ import net.minecraft.item.Foods;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.PathNavigator;
/*     */ import net.minecraft.pathfinding.SwimmerPathNavigator;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.FishSwimMoveController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.LandFishLookController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.EatNearbyFishGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ 
/*     */ public class FlyingFishEntity extends TameableEntity {
/*  58 */   private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.func_187226_a(FlyingFishEntity.class, DataSerializers.field_187198_h);
/*     */   private static final String SADDLED_TAG = "isSaddled";
/*     */   private static final double TAME_CHANCE = 0.25D;
/*  61 */   private static final Item[] SADDLES = new Item[] { Items.field_151141_av };
/*  62 */   private static final Food[] FOODS = new Food[] { Foods.field_221434_j, Foods.field_221420_H, Foods.field_221424_L, Foods.field_221437_m, Foods.field_221441_q, Foods.field_221415_C };
/*     */   
/*     */   public FlyingFishEntity(EntityType type, World world) {
/*  65 */     super(type, world);
/*     */     
/*  67 */     this.field_70765_h = (MovementController)new FishSwimMoveController((MobEntity)this);
/*  68 */     this.field_70749_g = (LookController)new LandFishLookController((MobEntity)this, 10);
/*  69 */     func_184644_a(PathNodeType.WATER, 0.0F);
/*     */     
/*  71 */     func_70661_as().func_212239_d(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  76 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindWaterGoal((CreatureEntity)this));
/*  77 */     this.field_70714_bg.func_75776_a(0, (Goal)new AvoidBlockTagGoal((CreatureEntity)this, (ITag.INamedTag)ModTags.Blocks.KAIROSEKI));
/*  78 */     this.field_70714_bg.func_75776_a(0, (Goal)new AvoidCoatedBoatGoal((CreatureEntity)this));
/*  79 */     this.field_70714_bg.func_75776_a(0, (Goal)new EatNearbyFishGoal((MobEntity)this));
/*  80 */     this.field_70714_bg.func_75776_a(1, (Goal)new RandomSwimmingGoal((CreatureEntity)this, 1.0D, 10));
/*  81 */     this.field_70714_bg.func_75776_a(1, (Goal)new LookRandomlyGoal((MobEntity)this));
/*  82 */     this.field_70714_bg.func_75776_a(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 12.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  87 */     super.func_70088_a();
/*  88 */     func_184212_Q().func_187214_a(IS_SADDLED, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  92 */     return MobEntity.func_233666_p_()
/*  93 */       .func_233815_a_(Attributes.field_233821_d_, 1.2999999523162842D)
/*  94 */       .func_233815_a_(Attributes.field_233819_b_, 55.0D)
/*  95 */       .func_233815_a_(Attributes.field_233823_f_, 0.0D)
/*  96 */       .func_233815_a_(Attributes.field_233818_a_, 20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 102 */     List<Entity> list = func_184188_bt();
/* 103 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 108 */     return super.func_70042_X();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeRiddenInWater(Entity rider) {
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184232_k(Entity passenger) {
/* 118 */     if (func_184196_w(passenger)) {
/* 119 */       double y = func_226278_cu_() + func_70042_X() + passenger.func_70033_W();
/* 120 */       Vector3d posVec = (new Vector3d(0.4D, 0.0D, 0.0D)).func_178785_b(-this.field_70177_z * 0.017453292F - 1.5707964F);
/* 121 */       passenger.func_70107_b(func_226277_ct_() + posVec.field_72450_a, y, func_226281_cx_() + posVec.field_72449_c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 127 */     if (!player.field_70170_p.field_72995_K && hand == Hand.MAIN_HAND) {
/* 128 */       ItemStack stack = player.func_184586_b((player.func_184600_cs() != null) ? player.func_184600_cs() : Hand.MAIN_HAND);
/* 129 */       boolean isSaddle = Arrays.<Item>stream(SADDLES).anyMatch(stack.func_77973_b()::equals);
/*     */       
/* 131 */       if (!func_70909_n()) {
/* 132 */         if (!stack.func_190926_b() && func_70877_b(stack)) {
/* 133 */           func_175505_a(player, stack);
/* 134 */           double chance = this.field_70146_Z.nextDouble();
/* 135 */           if (chance < 0.25D && !ForgeEventFactory.onAnimalTame((AnimalEntity)this, player)) {
/* 136 */             this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 137 */             func_193101_c(player);
/*     */           } 
/* 139 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } else {
/*     */         
/* 143 */         if (func_70877_b(stack) && func_110143_aJ() < func_110138_aP()) {
/* 144 */           func_70691_i(4.0F);
/* 145 */           func_175505_a(player, stack);
/* 146 */           this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 147 */           return ActionResultType.SUCCESS;
/* 148 */         }  if (isSaddle && !isSaddled() && !func_70631_g_()) {
/* 149 */           setSaddled(true);
/* 150 */           this.field_70170_p.func_217384_a((PlayerEntity)null, (Entity)this, SoundEvents.field_187726_cu, SoundCategory.PLAYERS, 0.5F, 1.0F);
/* 151 */           func_175505_a(player, stack);
/* 152 */           return ActionResultType.SUCCESS;
/*     */         } 
/* 154 */         if (isSaddled() && (
/* 155 */           !func_70877_b(stack) || (func_70877_b(stack) && !func_204701_dC())) && player == func_70902_q() && !func_70631_g_()) {
/* 156 */           player.func_184220_m((Entity)this);
/* 157 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 164 */     return super.func_230254_b_(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack stack) {
/* 169 */     if (stack.func_190926_b()) {
/* 170 */       return false;
/*     */     }
/* 172 */     Food food = stack.func_77973_b().func_219967_s();
/* 173 */     return (food != null && Arrays.<Food>stream(FOODS).anyMatch(food::equals));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82171_bF() {
/* 178 */     return func_184179_bs() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d travelVector) {
/* 183 */     if (func_70089_S()) {
/* 184 */       if (func_184207_aI() && func_82171_bF() && isSaddled()) {
/* 185 */         LivingEntity controller = (LivingEntity)func_184179_bs();
/* 186 */         this.field_70177_z = controller.field_70177_z;
/* 187 */         this.field_70126_B = this.field_70177_z;
/* 188 */         this.field_70125_A = controller.field_70125_A * 0.5F;
/* 189 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/* 190 */         this.field_70761_aq = this.field_70177_z;
/* 191 */         this.field_70759_as = this.field_70761_aq;
/*     */         
/* 193 */         if (func_184186_bw()) {
/* 194 */           func_70659_e((float)func_233637_b_(Attributes.field_233821_d_) * 0.7F);
/*     */           
/* 196 */           int isMoving = (controller.field_191988_bg > 0.0F) ? 0 : 1;
/* 197 */           if (isMoving == 0) {
/* 198 */             float speed = 0.5F;
/* 199 */             Vector3d vec = controller.func_70040_Z();
/* 200 */             double x = vec.field_72450_a * speed;
/* 201 */             double y = vec.field_72448_b * speed;
/* 202 */             double z = vec.field_72449_c * speed;
/* 203 */             AbilityHelper.setDeltaMovement((Entity)this, x, y, z);
/*     */           } else {
/*     */             
/* 206 */             double y = 0.0D;
/* 207 */             if (!func_204231_K()) {
/* 208 */               y = (func_213322_ci()).field_72448_b * 0.99D;
/* 209 */               y = Math.max(y, -1.0D);
/*     */             } 
/* 211 */             AbilityHelper.setDeltaMovement((Entity)this, (func_213322_ci()).field_72450_a, y, (func_213322_ci()).field_72449_c);
/*     */           } 
/*     */           
/* 214 */           super.func_213352_e(func_213322_ci());
/*     */         }
/* 216 */         else if (controller instanceof PlayerEntity) {
/* 217 */           AbilityHelper.setDeltaMovement((Entity)this, Vector3d.field_186680_a);
/*     */         } 
/*     */         
/* 220 */         func_233629_a_((LivingEntity)this, false);
/*     */       } else {
/*     */         
/* 223 */         super.func_213352_e(travelVector);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70646_bf() {
/* 230 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_184649_cE() {
/* 235 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 240 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreatureAttribute func_70668_bt() {
/* 245 */     return CreatureAttribute.field_203100_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_205019_a(IWorldReader pLevel) {
/* 250 */     return pLevel.func_226668_i_((Entity)this);
/*     */   }
/*     */   
/*     */   protected void handleAirSupply(int airSupply) {
/* 254 */     if (func_70089_S() && !func_203005_aq()) {
/* 255 */       func_70050_g(airSupply - 1);
/* 256 */       if (func_70086_ai() == -20) {
/* 257 */         func_70050_g(0);
/* 258 */         func_70097_a(DamageSource.field_76369_e, 2.0F);
/*     */       } 
/*     */     } else {
/* 261 */       func_70050_g(300);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70030_z() {
/* 268 */     int i = func_70086_ai();
/* 269 */     super.func_70030_z();
/* 270 */     handleAirSupply(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96092_aw() {
/* 275 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_184652_a(PlayerEntity pPlayer) {
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 285 */     return (!func_70909_n() && distance > 1024.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathNavigator func_175447_b(World level) {
/* 290 */     return (PathNavigator)new SwimmerPathNavigator((MobEntity)this, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 295 */     super.func_213281_b(nbt);
/* 296 */     nbt.func_74757_a("isSaddled", ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 301 */     super.func_70037_a(nbt);
/* 302 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(nbt.func_74767_n("isSaddled")));
/*     */   }
/*     */   
/*     */   public void setSaddled(boolean flag) {
/* 306 */     this.field_70180_af.func_187227_b(IS_SADDLED, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isSaddled() {
/* 310 */     return ((Boolean)this.field_70180_af.func_187225_a(IS_SADDLED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld pServerLevel, AgeableEntity pMate) {
/* 315 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 320 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<FlyingFishEntity> entity, IWorld world, SpawnReason spawnType, BlockPos pos, Random random) {
/* 324 */     return world.func_180495_p(pos.func_177977_b()).func_204520_s().func_206884_a((ITag)FluidTags.field_206959_a);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\FlyingFishEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */