/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DesertGirasole2ParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double t = 0.0D;
/*    */ 
/*    */     
/* 19 */     while (t < 1.0D) {
/*    */       
/* 21 */       t += 0.3141592653589793D;
/*    */       double theta;
/* 23 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 25 */         double x = t * Math.cos(theta);
/* 26 */         double y = WyHelper.randomDouble();
/* 27 */         double z = t * Math.sin(theta);
/*    */         
/* 29 */         double motionX = WyHelper.randomDouble() / 2.0D;
/* 30 */         double motionY = 0.0D;
/* 31 */         double motionZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 33 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA.get());
/* 34 */         data.setLife(35);
/* 35 */         data.setSize(3.0F);
/* 36 */         data.setMotion(motionX, motionY, motionZ);
/* 37 */         world.func_195590_a((IParticleData)data, true, posX + x * 2.25D, posY + 0.5D + y, posZ + z * 2.25D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertGirasole2ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */