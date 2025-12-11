/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DarkMatterChargingParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     double t = 0.0D;
/*    */     
/* 20 */     Random rand = world.field_73012_v;
/*    */     
/* 22 */     while (t < 1.0D) {
/* 23 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/* 26 */         double x = t * Math.cos(theta);
/* 27 */         double y = rand.nextInt(1);
/* 28 */         double z = t * Math.sin(theta);
/*    */         
/* 30 */         double motionX = x / 10.0D;
/* 31 */         double motionY = 0.05D + rand.nextDouble() / 10.0D;
/* 32 */         double motionZ = z / 10.0D;
/*    */         
/* 34 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.DARKNESS.get());
/* 35 */         part.setLife(8);
/* 36 */         part.setAnimationSpeed(1);
/* 37 */         part.setRotation(Vector3f.field_229183_f_);
/* 38 */         part.setRotationSpeed((t % 2.0D == 0.0D) ? 0.07F : -0.07F);
/* 39 */         part.setSize(1.3F);
/* 40 */         part.setMotion(motionX, motionY, motionZ);
/* 41 */         world.func_195590_a((IParticleData)part, true, posX + x * 1.25D + WyHelper.randomDouble(), posY + y, posZ + z * 1.25D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         
/* 43 */         part.setLife(14);
/* 44 */         part.setAnimationSpeed(2);
/* 45 */         part.setRotation(Vector3f.field_229183_f_);
/* 46 */         part.setRotationSpeed((t % 2.0D == 0.0D) ? 0.07F : -0.07F);
/* 47 */         part.setSize(1.3F);
/* 48 */         part.setMotion(motionX, motionY + 0.15D, motionZ);
/* 49 */         world.func_195590_a((IParticleData)part, true, posX + x * 2.0D + WyHelper.randomDouble(), posY + y, posZ + z * 2.0D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\DarkMatterChargingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */