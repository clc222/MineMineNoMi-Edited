/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gasu;
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
/*    */ public class GastanetParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 256; i++) {
/*    */       SimpleParticleData data;
/* 18 */       double offsetX = WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 22 */       double motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 23 */       double motionY = WyHelper.randomWithRange(0, 5) + WyHelper.randomDouble();
/* 24 */       double motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/*    */       
/* 26 */       double middlePoint = 2.8D;
/*    */       
/* 28 */       motionX *= middlePoint / 15.0D;
/* 29 */       motionY *= middlePoint / 25.0D;
/* 30 */       motionZ *= middlePoint / 15.0D;
/*    */       
/* 32 */       motionY = Math.abs(motionY);
/*    */ 
/*    */       
/* 35 */       if (i % 4 == 0) {
/* 36 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.GASU.get());
/*    */       } else {
/* 38 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.GASU2.get());
/*    */       } 
/* 40 */       data.setLife(7);
/* 41 */       data.setSize(1.5F);
/* 42 */       data.setMotion(motionX, motionY, motionZ);
/* 43 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.25D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gasu\GastanetParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */