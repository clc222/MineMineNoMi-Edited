/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ public class HiryuKaenParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     double t = 0.0D;
/*    */     
/* 19 */     Random rand = world.field_73012_v;
/*    */     
/* 21 */     while (t < 1.0D) {
/*    */       
/* 23 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
/*    */         
/* 27 */         double x = t * Math.cos(theta);
/* 28 */         double z = t * Math.sin(theta);
/*    */         
/* 30 */         double motionX = x / 8.0D;
/* 31 */         double motionY = 0.01D + rand.nextDouble() / 4.0D;
/* 32 */         double motionZ = z / 8.0D;
/*    */         
/* 34 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 35 */         part.setLife(7);
/* 36 */         part.setAnimationSpeed(1);
/* 37 */         part.setSize(2.0F);
/* 38 */         part.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 39 */         part.setMotion(motionX, motionY, motionZ);
/* 40 */         world.func_195590_a((IParticleData)part, true, posX + x * 0.85D, posY + 1.2D, posZ + z * 0.85D, 0.0D, 0.0D, 0.0D);
/* 41 */         world.func_195590_a((IParticleData)part, true, posX + x * 0.45D, posY + 1.8D, posZ + z * 0.45D, 0.0D, 0.0D, 0.0D);
/* 42 */         world.func_195590_a((IParticleData)part, true, posX + x * 0.85D, posY + 2.2D, posZ + z * 0.85D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\swordsman\HiryuKaenParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */