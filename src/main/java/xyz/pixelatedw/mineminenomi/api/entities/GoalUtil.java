/*     */ package xyz.pixelatedw.mineminenomi.api.entities;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityPredicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GoalUtil {
/*  31 */   private static final EntityPredicate VISIBLE_TARGET_IN_RANGE = (new EntityPredicate()).func_221013_a(16.0D);
/*  32 */   private static final EntityPredicate ALIVE_TARGET = (new EntityPredicate()).func_221014_c().func_221010_e();
/*  33 */   private static final TargetsPredicate NEARBY_TARGETS_PREDICATE = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean lastHurtCheckBefore(LivingEntity entity, int time) {
/*  39 */     if (entity.func_142015_aE() <= 0) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     IEntityStats props = EntityStatsCapability.get(entity);
/*  44 */     if (props == null) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     long timestamp = props.getLastAttackTimestamp();
/*  49 */     if (entity.field_70170_p.func_82737_E() <= timestamp + time) {
/*  50 */       return true;
/*     */     }
/*     */     
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean lastHurtCheckAfter(LivingEntity entity, int time) {
/*  60 */     if (entity.func_142015_aE() <= 0) {
/*  61 */       return false;
/*     */     }
/*     */     
/*  64 */     IEntityStats props = EntityStatsCapability.get(entity);
/*  65 */     if (props == null) {
/*  66 */       return false;
/*     */     }
/*     */     
/*  69 */     long timestamp = props.getLastAttackTimestamp();
/*  70 */     if (entity.field_70170_p.func_82737_E() <= timestamp + time) {
/*  71 */       return false;
/*     */     }
/*     */     
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canMove(LivingEntity entity) {
/*  81 */     if (!AbilityHelper.canUseMomentumAbilities(entity)) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean shouldMove(MobEntity entity) {
/*  92 */     if (!canMove((LivingEntity)entity)) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     if (hasAliveTarget(entity)) {
/*  97 */       return true;
/*     */     }
/*     */     
/* 100 */     if (entity.func_70661_as().func_75505_d() != null) {
/* 101 */       return true;
/*     */     }
/*     */     
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void startGCD(LivingEntity entity) {
/* 109 */     GCDCapability.startGCD(entity);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static boolean isGCDActive(LivingEntity entity) {
/* 114 */     return GCDCapability.isOnGCD(entity);
/*     */   }
/*     */   
/*     */   public static List<LivingEntity> getNearbyVisibleEntities(LivingEntity entity, double distance, @Nullable Predicate<Entity> predicate) {
/* 118 */     return (List<LivingEntity>)WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, distance, predicate).stream().filter(target -> (target != entity && VISIBLE_TARGET_IN_RANGE.func_221015_a(entity, target))).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public static boolean hasAliveOwner(TameableEntity entity) {
/* 122 */     LivingEntity owner = entity.func_70902_q();
/* 123 */     if (owner == null || !owner.func_70089_S()) {
/* 124 */       return false;
/*     */     }
/* 126 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean hasAliveTarget(MobEntity entity) {
/* 130 */     LivingEntity target = entity.func_70638_az();
/* 131 */     if (target == null || !target.func_70089_S()) {
/* 132 */       return false;
/*     */     }
/* 134 */     return ALIVE_TARGET.func_221015_a((LivingEntity)entity, target);
/*     */   }
/*     */   
/*     */   public static boolean isAtSameHeight(LivingEntity entity, LivingEntity target, double variation) {
/* 138 */     return (Math.abs((entity.func_213303_ch().func_178788_d(target.func_213303_ch())).field_72448_b) < 1.0D + variation);
/*     */   }
/*     */   
/*     */   public static boolean isOutsideDistance(LivingEntity entity, LivingEntity target, double distance) {
/* 142 */     return (entity.func_70068_e((Entity)target) > distance * distance);
/*     */   }
/*     */   
/*     */   public static boolean isWithinDistance(LivingEntity entity, LivingEntity target, double distance) {
/* 146 */     return (entity.func_70068_e((Entity)target) <= distance * distance);
/*     */   }
/*     */   
/*     */   public static boolean isWithinMeleeAttackRange(LivingEntity entity, LivingEntity target, float bonus) {
/* 150 */     double d0 = entity.func_70092_e(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/* 151 */     double d1 = (entity.func_213311_cf() * 2.0F * entity.func_213311_cf() * 2.0F + target.func_213311_cf());
/* 152 */     d1 += bonus;
/* 153 */     return (d0 <= d1);
/*     */   }
/*     */   
/*     */   public static boolean isWithinMeleeAttackRange(LivingEntity entity, LivingEntity target) {
/* 157 */     double d0 = entity.func_70092_e(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/* 158 */     double d1 = (entity.func_213311_cf() * 2.0F * entity.func_213311_cf() * 2.0F + target.func_213311_cf());
/* 159 */     return (d0 <= d1);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <E extends LivingEntity & IGoalMemoriesEntity> boolean isOtherTargetMuchFurtherAwayThanCurrentAttackTarget(E entity, LivingEntity target, double distance) {
/* 164 */     Optional<LivingEntity> optional = ((IGoalMemoriesEntity)entity).getMemory(MemoryModuleType.field_234103_o_);
/* 165 */     if (!optional.isPresent()) {
/* 166 */       return false;
/*     */     }
/*     */     
/* 169 */     double d0 = entity.func_195048_a(((LivingEntity)optional.get()).func_213303_ch());
/* 170 */     double d1 = entity.func_195048_a(target.func_213303_ch());
/* 171 */     return (d1 > d0 + distance * distance);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canSee(MobEntity entity, LivingEntity target) {
/* 176 */     return entity.func_70635_at().func_75522_a((Entity)target);
/*     */   }
/*     */   
/*     */   public static boolean isVisibleFrom(LivingEntity entity, Entity target, Vector3d lookVec) {
/* 180 */     Vector3d targetVec = new Vector3d(target.func_226277_ct_(), target.func_226280_cw_(), target.func_226281_cx_());
/* 181 */     if (target.field_70170_p != entity.field_70170_p || targetVec.func_72436_e(lookVec) > 16384.0D) {
/* 182 */       return false;
/*     */     }
/* 184 */     return (entity.field_70170_p.func_217299_a(new RayTraceContext(lookVec, targetVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)entity)).func_216346_c() == RayTraceResult.Type.MISS);
/*     */   }
/*     */   
/*     */   public static boolean isEntityInView(LivingEntity entity, Entity target) {
/* 188 */     return TargetHelper.isEntityInView(entity, target, 60.0F);
/*     */   }
/*     */   
/*     */   public static LivingEntity getNearestTarget(LivingEntity entity, Optional<LivingEntity> optionalTarget, LivingEntity target) {
/* 192 */     return !optionalTarget.isPresent() ? target : getTargetNearestMe(entity, optionalTarget.get(), target);
/*     */   }
/*     */   
/*     */   public static LivingEntity getTargetNearestMe(LivingEntity entity, LivingEntity target1, LivingEntity target2) {
/* 196 */     Vector3d target1Position = target1.func_213303_ch();
/* 197 */     Vector3d target2Position = target2.func_213303_ch();
/* 198 */     return (entity.func_195048_a(target1Position) < entity.func_195048_a(target2Position)) ? target1 : target2;
/*     */   }
/*     */   
/*     */   public static void lookAt(MobEntity entity, Vector3d position) {
/* 202 */     entity.func_200602_a(EntityAnchorArgument.Type.EYES, position);
/*     */   }
/*     */   
/*     */   public static void lookAtEachOther(MobEntity entity1, MobEntity entity2) {
/* 206 */     lookAtEntity(entity1, (Entity)entity2);
/* 207 */     lookAtEntity(entity2, (Entity)entity1);
/*     */   }
/*     */   
/*     */   public static void lookAtEntity(MobEntity entity, @Nullable Entity target) {
/* 211 */     if (target == null) {
/*     */       return;
/*     */     }
/* 214 */     entity.func_200602_a(EntityAnchorArgument.Type.EYES, target.func_213303_ch().func_72441_c(0.0D, target.func_70047_e(), 0.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasEnoughTargetsAround(MobEntity entity, int minEnemies, float distance) {
/* 219 */     List<LivingEntity> targets = TargetHelper.getEntitiesInArea((LivingEntity)entity, distance, NEARBY_TARGETS_PREDICATE, new Class[0]);
/* 220 */     int confirmedTargets = 0;
/* 221 */     for (LivingEntity target : targets) {
/* 222 */       if (target != null && target.func_70089_S() && entity.func_70068_e((Entity)target) <= (distance * distance) && canSee(entity, target)) {
/* 223 */         confirmedTargets++;
/*     */       }
/* 225 */       if (confirmedTargets >= minEnemies) {
/* 226 */         return true;
/*     */       }
/*     */     } 
/* 229 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean hasHealthAbovePercentage(LivingEntity entity, double percentage) {
/* 233 */     return (entity.func_110143_aJ() >= WyHelper.percentage(percentage, entity.func_110138_aP()));
/*     */   }
/*     */   
/*     */   public static boolean hasHealthUnderPercentage(LivingEntity entity, double percentage) {
/* 237 */     return (entity.func_110143_aJ() < WyHelper.percentage(percentage, entity.func_110138_aP()));
/*     */   }
/*     */   
/*     */   public static boolean hasSolidBlockAbove(LivingEntity entity) {
/* 241 */     if (entity instanceof MobEntity) {
/* 242 */       LivingEntity target = ((MobEntity)entity).func_70638_az();
/* 243 */       int checkDist = (int)Math.min((target.func_213303_ch().func_178788_d(entity.func_213303_ch())).field_72448_b, 10.0D);
/* 244 */       if (checkDist > 1) {
/* 245 */         for (int i = 1; i < checkDist + 1; i++) {
/* 246 */           BlockState blockState = entity.field_70170_p.func_180495_p(entity.func_233580_cy_().func_177981_b(i));
/* 247 */           if (blockState.func_224755_d((IBlockReader)entity.field_70170_p, entity.func_233580_cy_().func_177981_b(i + 2), Direction.DOWN)) {
/* 248 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 254 */     BlockState above = entity.field_70170_p.func_180495_p(entity.func_233580_cy_().func_177984_a());
/* 255 */     if (!above.func_224755_d((IBlockReader)entity.field_70170_p, entity.func_233580_cy_().func_177981_b(2), Direction.DOWN)) {
/* 256 */       return false;
/*     */     }
/*     */     
/* 259 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean hasBlockInFace(LivingEntity entity) {
/* 263 */     Vector3d start = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/* 264 */     Direction dir = entity.func_174811_aO();
/* 265 */     Vector3d end = start.func_72441_c(dir.func_82601_c(), 0.0D, dir.func_82599_e());
/* 266 */     BlockRayTraceResult result = entity.field_70170_p.func_217299_a(new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null));
/* 267 */     if (result == null || result.func_216346_c() == RayTraceResult.Type.MISS) {
/* 268 */       return false;
/*     */     }
/*     */     
/* 271 */     return true;
/*     */   }
/*     */   
/*     */   public static int getFrontWallHeight(LivingEntity entity) {
/* 275 */     BlockPos start = entity.func_233580_cy_();
/* 276 */     BlockPos check = start.func_177971_a(entity.func_174811_aO().func_176730_m());
/* 277 */     Direction dir = entity.func_174811_aO().func_176734_d();
/*     */     
/* 279 */     int blocks = 0;
/* 280 */     for (int i = 0; i < 4; i++) {
/* 281 */       BlockPos pos = check.func_177981_b(i);
/* 282 */       BlockState state = entity.field_70170_p.func_180495_p(pos);
/* 283 */       if (state.func_224755_d((IBlockReader)entity.field_70170_p, pos, dir)) {
/* 284 */         blocks++;
/*     */       }
/*     */     } 
/*     */     
/* 288 */     return blocks;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\GoalUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */