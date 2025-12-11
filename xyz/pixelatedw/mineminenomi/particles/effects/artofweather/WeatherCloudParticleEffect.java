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
/*    */ public class WeatherCloudParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     int i = 0;
/* 17 */     double phi = 0.0D;
/* 18 */     double radius = 15.0D;
/*    */ 
/*    */     
/* 21 */     while (phi < Math.PI) {
/* 22 */       phi += 2.0943951023931953D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
/* 25 */         float red, green, blue; ParticleType<SimpleParticleData> particle; double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/* 26 */         double y = radius * Math.cos(phi);
/* 27 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/*    */ 
/*    */         
/* 30 */         if (i % 2 == 0) {
/* 31 */           red = 0.5F;
/* 32 */           green = 0.5F;
/* 33 */           blue = 0.5F;
/* 34 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU2.get();
/*    */         } else {
/*    */           
/* 37 */           red = 0.3F;
/* 38 */           green = 0.3F;
/* 39 */           blue = 0.3F;
/* 40 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU.get();
/*    */         } 
/*    */         
/* 43 */         SimpleParticleData part = new SimpleParticleData(particle);
/* 44 */         part.setLife((int)(50.0D + WyHelper.randomDouble()));
/* 45 */         part.setSize(45.0F);
/* 46 */         part.setColor(red, green, blue);
/* 47 */         part.setHasScaleDecay(true);
/*    */         
/* 49 */         world.func_195590_a((IParticleData)part, true, posX + x, posY + 7.0D + y, posZ + z, 0.0D, 0.0D, 0.0D);
/* 50 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\WeatherCloudParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */