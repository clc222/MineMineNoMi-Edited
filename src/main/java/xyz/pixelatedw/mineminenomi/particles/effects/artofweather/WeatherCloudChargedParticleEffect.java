/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;
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
/*    */ public class WeatherCloudChargedParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     int i = 0;
/* 17 */     double phi = 0.0D;
/* 18 */     double radius = 15.0D;
/*    */     
/* 20 */     while (phi < Math.PI) {
/* 21 */       phi += 2.0943951023931953D;
/*    */       double theta;
/* 23 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
/* 24 */         ParticleType<SimpleParticleData> particle; double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/* 25 */         double y = radius * Math.cos(phi);
/* 26 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/*    */ 
/*    */         
/* 29 */         if (i % 4 == 0) {
/* 30 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/*    */         } else {
/* 32 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/*    */         } 
/* 34 */         SimpleParticleData part = new SimpleParticleData(particle);
/* 35 */         part.setLife((int)(20.0D + WyHelper.randomDouble()));
/* 36 */         part.setSize(10.0F);
/* 37 */         part.setHasScaleDecay(true);
/*    */         
/* 39 */         world.func_195590_a((IParticleData)part, true, posX + x, posY + 5.0D + y, posZ + z, 0.0D, 0.0D, 0.0D);
/* 40 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\WeatherCloudChargedParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */