/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
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
/*    */ public class DesertEncierroParticleEffect
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
/* 30 */         double motionX = -x / 6.0D;
/* 31 */         double motionY = -0.1D + rand.nextDouble() / 10.0D;
/* 32 */         double motionZ = -z / 6.0D;
/*    */         
/* 34 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA.get());
/* 35 */         data.setLife(5);
/* 36 */         data.setSize(3.3F);
/* 37 */         data.setMotion(motionX, motionY, motionZ);
/* 38 */         entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + x * 1.25D, posY + 1.2D, posZ + z * 1.25D, 0.0D, 0.0D, 0.0D);
/*    */         
/* 40 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA.get());
/* 41 */         data.setLife(5);
/* 42 */         data.setSize(3.3F);
/* 43 */         data.setMotion(motionX, motionY, motionZ);
/* 44 */         entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + x * 1.25D, posY + 2.2D, posZ + z * 1.25D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertEncierroParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */