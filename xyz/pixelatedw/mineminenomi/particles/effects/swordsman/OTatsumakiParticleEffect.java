/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class OTatsumakiParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     double t = 0.0D;
/*    */ 
/*    */     
/* 18 */     while (t < 3.0D) {
/*    */       
/* 20 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 22 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 24 */         double x = t * Math.cos(theta);
/* 25 */         double z = t * Math.sin(theta);
/*    */         
/* 27 */         world.func_195590_a((IParticleData)ParticleTypes.field_197603_N, true, posX + x * 1.85D, posY + 1.2D + WyHelper.randomDouble(), posZ + z * 1.85D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\swordsman\OTatsumakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */