/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HibashiraParticleEffect extends ParticleEffect<HibashiraParticleEffect.Details> {
/* 14 */   public static final Details NO_DETAILS = new Details();
/*    */   
/*    */   public HibashiraParticleEffect() {
/* 17 */     super(Details::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 22 */     double t = 0.0D;
/*    */     
/* 24 */     double size = 2.5D * details.getRadiusScale();
/*    */     
/* 26 */     Random rand = world.field_73012_v;
/*    */     
/* 28 */     while (t < 1.0D) {
/* 29 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 31 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += Math.PI / 8.0D * details.getRadiusScale()) {
/* 32 */         double x = t * Math.cos(theta);
/* 33 */         double z = t * Math.sin(theta);
/*    */         
/* 35 */         double motionX = Math.sin(theta) / 10.0D * details.getMotionScale();
/* 36 */         double motionY = 0.3D + rand.nextDouble() / 10.0D * details.getMotionScale();
/* 37 */         double motionZ = -Math.cos(theta) / 10.0D * details.getMotionScale();
/*    */         
/* 39 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */         
/* 41 */         part.setLife((int)WyHelper.randomWithRange(40, 50));
/* 42 */         part.setSize((float)WyHelper.randomWithRange(7, 10) * details.getDensityScale());
/*    */         
/* 44 */         float c = WyHelper.colorTolerance(0.7F);
/*    */         
/* 46 */         part.setColor(c, c, c, 0.5F);
/* 47 */         part.setMotion(motionX, motionY, motionZ);
/*    */         
/* 49 */         world.func_195590_a((IParticleData)part, true, posX + x * size + WyHelper.randomDouble() / 2.0D, posY - 10.0D, posZ + z * size + WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/* 50 */         world.func_195590_a((IParticleData)part, true, posX + x * size + WyHelper.randomDouble() / 2.0D, posY - 5.0D, posZ + z * size + WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/* 51 */         world.func_195590_a((IParticleData)part, true, posX + x * size + WyHelper.randomDouble() / 2.0D, posY, posZ + z * size + WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/* 52 */         world.func_195590_a((IParticleData)part, true, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 5.0D + WyHelper.randomWithRange(-2, 0), posZ + z * size + WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/* 53 */         world.func_195590_a((IParticleData)part, true, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 10.0D + WyHelper.randomWithRange(-2, 0), posZ + z * size + WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Details extends ParticleEffect.Details {
/* 59 */     private float radiusScale = 1.0F;
/* 60 */     private float densityScale = 1.0F;
/* 61 */     private float motionScale = 1.0F;
/*    */     
/*    */     public Details() {}
/*    */     
/*    */     public Details(float radiusScale, float densityScale, float motionScale) {
/* 66 */       this.radiusScale = radiusScale;
/* 67 */       this.densityScale = densityScale;
/* 68 */       this.motionScale = motionScale;
/*    */     }
/*    */ 
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 73 */       nbt.func_74776_a("radiusScale", this.radiusScale);
/* 74 */       nbt.func_74776_a("densityScale", this.densityScale);
/* 75 */       nbt.func_74776_a("motionScale", this.motionScale);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 80 */       this.radiusScale = nbt.func_74760_g("radiusScale");
/* 81 */       this.densityScale = nbt.func_74760_g("densityScale");
/* 82 */       this.motionScale = nbt.func_74760_g("motionScale");
/*    */     }
/*    */     
/*    */     public float getRadiusScale() {
/* 86 */       return this.radiusScale;
/*    */     }
/*    */     
/*    */     public float getDensityScale() {
/* 90 */       return this.densityScale;
/*    */     }
/*    */     
/*    */     public float getMotionScale() {
/* 94 */       return this.motionScale;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\HibashiraParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */