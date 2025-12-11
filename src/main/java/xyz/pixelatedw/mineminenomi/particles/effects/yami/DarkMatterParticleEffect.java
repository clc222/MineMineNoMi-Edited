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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DarkMatterParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 19 */     double t = 0.0D;
/*    */     
/* 21 */     Random rand = world.field_73012_v;
/*    */     
/* 23 */     while (t < 1.0D) {
/*    */       
/* 25 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 27 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
/*    */         
/* 29 */         double x = t * Math.cos(theta);
/* 30 */         double z = t * Math.sin(theta);
/*    */         
/* 32 */         double motionX = -x / 3.0D;
/* 33 */         double motionY = -0.1D + rand.nextDouble() / 10.0D;
/* 34 */         double motionZ = -z / 3.0D;
/*    */         
/* 36 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.DARKNESS.get());
/* 37 */         part.setLife(14);
/* 38 */         part.setAnimationSpeed(2);
/* 39 */         part.setRotation(Vector3f.field_229183_f_);
/* 40 */         part.setRotationSpeed((t % 2.0D == 0.0D) ? 0.07F : -0.07F);
/* 41 */         part.setSize(3.3F);
/* 42 */         part.setMotion(motionX, motionY, motionZ);
/* 43 */         world.func_195590_a((IParticleData)part, true, posX + x * 5.25D, posY + 1.2D, posZ + z * 5.25D, 0.0D, 0.0D, 0.0D);
/*    */         
/* 45 */         part.setLife(14);
/* 46 */         part.setAnimationSpeed(2);
/* 47 */         part.setRotation(Vector3f.field_229183_f_);
/* 48 */         part.setRotationSpeed((t % 2.0D == 0.0D) ? 0.07F : -0.07F);
/* 49 */         part.setSize(3.3F);
/* 50 */         part.setMotion(motionX, motionY, motionZ);
/* 51 */         world.func_195590_a((IParticleData)part, true, posX + x * 5.25D, posY + 3.2D, posZ + z * 5.25D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\DarkMatterParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */