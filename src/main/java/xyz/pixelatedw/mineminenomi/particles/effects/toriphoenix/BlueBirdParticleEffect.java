/*     */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class BlueBirdParticleEffect extends ParticleEffect<BlueBirdParticleEffect.Details> {
/*     */   public BlueBirdParticleEffect() {
/*  15 */     super(Details::new);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/*  21 */     float incrementPI = 0.049087387F;
/*  22 */     float radius1 = (float)fromRangeToRange(0.0D, details.getMaxChargeTime(), 0.25D, 1.25D, details.getMaxChargeTime() - details.getCurrentChargeTime()) * 1.6F;
/*  23 */     float radius2 = (float)fromRangeToRange(0.0D, details.getMaxChargeTime(), 0.15D, 0.3D, details.getMaxChargeTime() - details.getCurrentChargeTime()) * 1.8F;
/*  24 */     float rotation = (float)(incrementPI * fromRangeToRange(0.0D, details.getMaxChargeTime(), -128.0D, 128.0D, details.getCurrentChargeTime()));
/*  25 */     float rotation2 = (float)(incrementPI * fromRangeToRange(0.0D, details.getMaxChargeTime(), -128.0D, 128.0D, details.getCurrentChargeTime()) + 2.0943951023931953D);
/*  26 */     float rotation3 = (float)(incrementPI * fromRangeToRange(0.0D, details.getMaxChargeTime(), -128.0D, 128.0D, details.getCurrentChargeTime()) - 2.0943951023931953D);
/*     */     
/*  28 */     Vector3d normalizedH = getPerpendicularHorizontalLine(Vector3d.field_186680_a, entity.func_70040_Z(), radius1).func_216372_d(2.0D, 2.0D, 2.0D);
/*  29 */     Vector3d normalizedV = getPerpendicularVerticalLine(Vector3d.field_186680_a, entity.func_70040_Z(), normalizedH.func_216372_d(-3.141592653589793D, -3.141592653589793D, -3.141592653589793D), radius2).func_216372_d(2.0D, 2.0D, 2.0D);
/*     */     
/*  31 */     Vector3d finalPosition1 = getParticlePositionInSpiral(rotation, normalizedH, normalizedV);
/*     */     
/*  33 */     Vector3d finalPosition2 = getParticlePositionInSpiral(rotation2, normalizedH, normalizedV);
/*     */     
/*  35 */     Vector3d finalPosition3 = getParticlePositionInSpiral(rotation3, normalizedH, normalizedV);
/*     */     
/*  37 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BLUE_FLAME.get());
/*  38 */     data.setLife(15);
/*  39 */     data.setSize(4.0F);
/*  40 */     data.setMotion((entity.func_70040_Z()).field_72450_a / 10.0D, (entity.func_70040_Z()).field_72448_b / 10.0D, (entity.func_70040_Z()).field_72449_c / 10.0D);
/*     */     
/*  42 */     double posX1 = entity.func_226277_ct_() + finalPosition1.field_72450_a;
/*  43 */     double posY1 = entity.func_226280_cw_() + finalPosition1.field_72448_b;
/*  44 */     double posZ1 = entity.func_226281_cx_() + finalPosition1.field_72449_c;
/*     */     
/*  46 */     double posX2 = entity.func_226277_ct_() + finalPosition2.field_72450_a;
/*  47 */     double posY2 = entity.func_226280_cw_() + finalPosition2.field_72448_b;
/*  48 */     double posZ2 = entity.func_226281_cx_() + finalPosition2.field_72449_c;
/*     */     
/*  50 */     double posX3 = entity.func_226277_ct_() + finalPosition3.field_72450_a;
/*  51 */     double posY3 = entity.func_226280_cw_() + finalPosition3.field_72448_b;
/*  52 */     double posZ3 = entity.func_226281_cx_() + finalPosition3.field_72449_c;
/*     */     
/*  54 */     world.func_195590_a((IParticleData)data, true, posX1, posY1, posZ1, 0.0D, 0.0D, 0.0D);
/*  55 */     world.func_195590_a((IParticleData)data, true, posX2, posY2, posZ2, 0.0D, 0.0D, 0.0D);
/*  56 */     world.func_195590_a((IParticleData)data, true, posX3, posY3, posZ3, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vector3d getPerpendicularHorizontalLine(Vector3d startPoint, Vector3d endPoint, float radius) {
/*  61 */     Vector3d axis = new Vector3d(1.0D, 0.0D, 1.0D);
/*  62 */     Vector3d newStart = startPoint.func_216369_h(axis);
/*  63 */     Vector3d newEnd = endPoint.func_216369_h(axis);
/*  64 */     Vector3d line = newEnd.func_178788_d(newStart);
/*  65 */     line.func_72441_c((line.func_82615_a() == 0.0D) ? 0.001D : 0.0D, 0.0D, (line.func_82616_c() == 0.0D) ? 0.001D : 0.0D);
/*     */     
/*  67 */     Vector3d newLine = new Vector3d(line.field_72449_c, 0.0D, -line.field_72450_a);
/*     */     
/*  69 */     return newLine.func_72432_b().func_216372_d(radius, radius, radius);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vector3d getPerpendicularVerticalLine(Vector3d startPoint, Vector3d endPoint, Vector3d horizontalPoint, float radius) {
/*  74 */     Vector3d vec1 = endPoint.func_178788_d(startPoint);
/*  75 */     Vector3d vec2 = horizontalPoint.func_178788_d(startPoint);
/*  76 */     Vector3d newLine = vec1.func_72431_c(vec2);
/*     */     
/*  78 */     return newLine.func_72432_b().func_216372_d(radius, radius, radius);
/*     */   }
/*     */ 
/*     */   
/*     */   private static double fromRangeToRange(double oldMin, double oldMax, double min, double max, double oldValue) {
/*  83 */     return (oldValue - oldMin) * (max - min) / (oldMax - oldMin) + min;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Vector3d getParticlePositionInSpiral(float rotation, Vector3d normalizedH, Vector3d normalizedV) {
/*  88 */     Vector3d incrementH = normalizedH.func_216372_d(Math.cos(rotation), Math.cos(rotation), Math.cos(rotation));
/*  89 */     Vector3d incrementV = normalizedV.func_216372_d(Math.sin(rotation), Math.sin(rotation), Math.sin(rotation));
/*     */     
/*  91 */     Vector3d HVVec = incrementV.func_178788_d(incrementH);
/*     */     
/*  93 */     return incrementV.func_178787_e(HVVec.func_216372_d(0.5D, 0.5D, 0.5D));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Details
/*     */     extends ParticleEffect.Details
/*     */   {
/*     */     private double currentChargeTime;
/*     */     private double maxChargeTime;
/*     */     
/*     */     public void save(CompoundNBT nbt) {
/* 104 */       nbt.func_74780_a("currentChargeTime", this.currentChargeTime);
/* 105 */       nbt.func_74780_a("maxChargeTime", this.maxChargeTime);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void load(CompoundNBT nbt) {
/* 111 */       this.currentChargeTime = nbt.func_74769_h("currentChargeTime");
/* 112 */       this.maxChargeTime = nbt.func_74769_h("maxChargeTime");
/*     */     }
/*     */ 
/*     */     
/*     */     public void setCurrentChargeTime(double currChargeTime) {
/* 117 */       this.currentChargeTime = currChargeTime;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setMaxChargeTime(double maxChargeTime) {
/* 122 */       this.maxChargeTime = maxChargeTime;
/*     */     }
/*     */ 
/*     */     
/*     */     public double getMaxChargeTime() {
/* 127 */       return this.maxChargeTime;
/*     */     }
/*     */ 
/*     */     
/*     */     public double getCurrentChargeTime() {
/* 132 */       return this.currentChargeTime;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\BlueBirdParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */