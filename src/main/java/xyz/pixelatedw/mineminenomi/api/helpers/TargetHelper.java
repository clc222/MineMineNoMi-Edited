/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ 
/*     */ 
/*     */ public class TargetHelper
/*     */ {
/*     */   public static boolean isEntityInView(LivingEntity entity, Entity target, float fov) {
/*  18 */     double dx = target.func_226277_ct_() - entity.func_226277_ct_();
/*     */     double dz;
/*  20 */     for (dz = target.func_226281_cx_() - entity.func_226281_cx_(); dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
/*  21 */       dx = (Math.random() - Math.random()) * 0.01D;
/*     */     }
/*  23 */     float yaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - entity.field_70177_z - 90.0F;
/*  24 */     for (; yaw < -180.0F; yaw += 360.0F);
/*  25 */     for (; yaw >= 180.0F; yaw -= 360.0F);
/*  26 */     return (yaw < fov && yaw > -fov);
/*     */   }
/*     */   
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInArea(LivingEntity entity, double size, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*  30 */     return getEntitiesInArea(entity, size, size, size, targetPredicate, clazzez);
/*     */   }
/*     */   
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInArea(LivingEntity entity, BlockPos centerPos, double size, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*  34 */     return getEntitiesInArea(entity, centerPos, size, size, size, targetPredicate, clazzez);
/*     */   }
/*     */   
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInArea(LivingEntity entity, double sizeX, double sizeY, double sizeZ, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*  38 */     return getEntitiesInArea(entity, entity.func_233580_cy_(), sizeX, sizeY, sizeZ, targetPredicate, clazzez);
/*     */   }
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInArea(LivingEntity entity, BlockPos centerPos, double sizeX, double sizeY, double sizeZ, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*     */     Class[] arrayOfClass;
/*  42 */     if (clazzez.length <= 0) {
/*  43 */       arrayOfClass = new Class[] { LivingEntity.class };
/*     */     }
/*     */     
/*  46 */     if (targetPredicate == null) {
/*  47 */       targetPredicate = TargetsPredicate.DEFAULT_AREA_CHECK;
/*     */     }
/*     */     
/*  50 */     List<T> targets = new ArrayList<>();
/*     */     
/*  52 */     AxisAlignedBB area = (new AxisAlignedBB(centerPos)).func_72314_b(sizeX, sizeY, sizeZ);
/*     */     
/*  54 */     TargetsPredicate finalPredicate = targetPredicate;
/*     */     
/*  56 */     for (Class<? extends T> clz : arrayOfClass) {
/*  57 */       entity.field_70170_p.func_217357_a(clz, area).stream()
/*  58 */         .filter(target -> (!targets.contains(target) && finalPredicate.test(entity, target)))
/*  59 */         .forEach(targets::add);
/*     */     }
/*     */     
/*  62 */     return targets;
/*     */   }
/*     */   
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInLine(LivingEntity entity, float distance, float size, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*  66 */     return getEntitiesInLine(entity, distance, size, size, size, targetPredicate, clazzez);
/*     */   }
/*     */   
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInLine(LivingEntity entity, BlockPos centerPos, float distance, float size, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*  70 */     return getEntitiesInLine(entity, centerPos, distance, size, size, size, targetPredicate, clazzez);
/*     */   }
/*     */   
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInLine(LivingEntity entity, float distance, float sizeX, float sizeY, float sizeZ, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*  74 */     return getEntitiesInLine(entity, entity.func_233580_cy_(), distance, sizeX, sizeY, sizeZ, targetPredicate, clazzez);
/*     */   }
/*     */   public static <T extends LivingEntity> List<T> getEntitiesInLine(LivingEntity entity, BlockPos centerPos, float distance, float sizeX, float sizeY, float sizeZ, @Nullable TargetsPredicate targetPredicate, Class<? extends T>... clazzez) {
/*     */     Class[] arrayOfClass;
/*  78 */     if (clazzez.length <= 0) {
/*  79 */       arrayOfClass = new Class[] { LivingEntity.class };
/*     */     }
/*     */     
/*  82 */     if (targetPredicate == null) {
/*  83 */       targetPredicate = TargetsPredicate.DEFAULT_LINE_CHECK;
/*     */     }
/*     */     
/*  86 */     List<T> targets = new ArrayList<>();
/*     */     
/*  88 */     Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/*  89 */     Vector3d pos = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*     */     
/*  91 */     double distanceTraveled = 0.0D;
/*     */     
/*  93 */     while (distanceTraveled < distance) {
/*  94 */       distanceTraveled += lookVec.func_72433_c();
/*     */       
/*  96 */       pos = pos.func_178787_e(lookVec.func_186678_a(lookVec.func_72433_c()));
/*     */       
/*  98 */       double xOffset = sizeX / 2.0D;
/*  99 */       double yOffset = sizeY / 2.0D;
/* 100 */       double zOffset = sizeZ / 2.0D;
/*     */       
/* 102 */       AxisAlignedBB aabb = new AxisAlignedBB(pos.field_72450_a - xOffset, pos.field_72448_b - yOffset, pos.field_72449_c - zOffset, pos.field_72450_a + xOffset, pos.field_72448_b + yOffset, pos.field_72449_c + zOffset);
/*     */ 
/*     */ 
/*     */       
/* 106 */       TargetsPredicate finalPredicate = targetPredicate;
/*     */       
/* 108 */       for (Class<? extends T> clz : arrayOfClass) {
/* 109 */         List<T> entities = entity.field_70170_p.func_217357_a(clz, aabb);
/*     */         
/* 111 */         entities.stream()
/* 112 */           .filter(target -> (!targets.contains(target) && finalPredicate.test(entity, target)))
/* 113 */           .forEach(targets::add);
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     targets.sort((Comparator)closestComparator(entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D)));
/*     */     
/* 119 */     return targets;
/*     */   }
/*     */   
/*     */   public static Comparator<Entity> closestComparator(final Vector3d pos) {
/* 123 */     return new Comparator<Entity>()
/*     */       {
/*     */         public int compare(Entity e1, Entity e2) {
/* 126 */           return (int)(e1.func_195048_a(pos) - e2.func_195048_a(pos));
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\TargetHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */