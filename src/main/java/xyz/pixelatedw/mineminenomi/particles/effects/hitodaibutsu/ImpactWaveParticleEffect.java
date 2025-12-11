/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hitodaibutsu;
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
/*    */ public class ImpactWaveParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double radius = 1.0D;
/*    */     
/* 18 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 20 */       double phi = 0.0D;
/*    */       
/* 22 */       while (phi < Math.PI) {
/*    */         
/* 24 */         phi += 0.19634954084936207D;
/*    */         double theta;
/* 26 */         for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.19634954084936207D) {
/*    */           
/* 28 */           double x = radius * Math.cos(theta) * Math.sin(phi);
/* 29 */           double y = radius * Math.cos(phi);
/* 30 */           double z = radius * Math.sin(theta) * Math.sin(phi);
/*    */           
/* 32 */           SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 33 */           data.setLife(50);
/*    */           
/* 35 */           double motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 36 */           double motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */           
/* 38 */           double middlePoint = 1.2D;
/*    */           
/* 40 */           motionX *= middlePoint / 25.0D;
/* 41 */           motionZ *= middlePoint / 25.0D;
/* 42 */           data.setMotion(motionX, 0.10000000149011612D, motionZ);
/* 43 */           data.setSize(10.0F);
/* 44 */           data.setColor(1.0F, 1.0F, 0.2F, 0.5F);
/* 45 */           world.func_195590_a((IParticleData)data, true, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */         } 
/*    */       } 
/*    */       
/* 49 */       radius += 0.8D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hitodaibutsu\ImpactWaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */