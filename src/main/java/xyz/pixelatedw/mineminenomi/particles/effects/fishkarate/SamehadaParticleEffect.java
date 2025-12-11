/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ public class SamehadaParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     posY += 1.5D;
/*    */     
/* 16 */     double radius = 1.0D;
/* 17 */     double phi = 0.0D;
/*    */     
/* 19 */     while (phi < Math.PI) {
/*    */       
/* 21 */       phi += 0.7853981633974483D;
/*    */       double theta;
/* 23 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.5235987755982988D) {
/*    */         
/* 25 */         double x = radius * Math.cos(theta) * Math.sin(phi);
/* 26 */         double y = radius * Math.cos(phi);
/* 27 */         double z = radius * Math.sin(theta) * Math.sin(phi);
/*    */         
/* 29 */         world.func_195590_a((IParticleData)ParticleTypes.field_218422_X, true, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\fishkarate\SamehadaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */