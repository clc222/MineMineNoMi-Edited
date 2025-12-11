/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.ushibison;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KokuteiCrossParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double t = 0.0D;
/*    */     
/* 18 */     Random rand = world.field_73012_v;
/*    */     
/* 20 */     while (t < 1.0D) {
/*    */       
/* 22 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
/*    */         
/* 26 */         double x = t * Math.cos(theta);
/* 27 */         double z = t * Math.sin(theta);
/*    */         
/* 29 */         double offsetX = x / 6.0D;
/* 30 */         double offsetY = -0.1D + rand.nextDouble() / 10.0D;
/* 31 */         double offsetZ = z / 6.0D;
/*    */         
/* 33 */         world.func_195590_a((IParticleData)ParticleTypes.field_197613_f, true, posX + x * 1.85D + offsetX, posY + 1.2D + offsetY, posZ + z * 1.85D + offsetZ, 0.0D, 0.0D, 0.0D);
/* 34 */         world.func_195590_a((IParticleData)ParticleTypes.field_197613_f, true, posX + x * 1.85D + offsetX, posY + 2.2D + offsetY, posZ + z * 1.85D + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effect\\ushibison\KokuteiCrossParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */