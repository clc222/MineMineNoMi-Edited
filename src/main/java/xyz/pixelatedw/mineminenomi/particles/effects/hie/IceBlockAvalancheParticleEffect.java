/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hie;
/*    */ 
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
/*    */ 
/*    */ public class IceBlockAvalancheParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     double phi = 0.0D;
/*    */     
/* 20 */     while (phi < Math.PI) {
/*    */       
/* 22 */       phi += 0.7853981633974483D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.5235987755982988D) {
/*    */         
/* 26 */         double x = 8.0D * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
/* 27 */         double y = posY - 3.0D - 1.0D;
/* 28 */         double z = 8.0D * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
/* 29 */         double motionX = x / 40.0D;
/* 30 */         double motionY = -0.2D;
/* 31 */         double motionZ = z / 40.0D;
/*    */         
/* 33 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.HIE.get());
/* 34 */         part.setLife(14);
/* 35 */         part.setAnimationSpeed(2);
/* 36 */         part.setRotation(Vector3f.field_229183_f_);
/* 37 */         part.setRotationSpeed((theta % 2.0D == 0.0D) ? 0.07F : -0.07F);
/* 38 */         part.setSize(10.0F);
/*    */         
/* 40 */         part.setMotion(-motionX, motionY, -motionZ);
/* 41 */         if (Math.random() > 0.7D)
/*    */         {
/* 43 */           world.func_195590_a((IParticleData)part, true, posX + x + WyHelper.randomDouble(), y, posZ + z + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hie\IceBlockAvalancheParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */