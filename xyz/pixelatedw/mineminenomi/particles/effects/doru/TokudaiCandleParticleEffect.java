/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doru;
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
/*    */ public class TokudaiCandleParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 2048; i++) {
/* 16 */       double offsetX = WyHelper.randomWithRange(-50, 50) + WyHelper.randomDouble();
/* 17 */       double offsetY = WyHelper.randomWithRange(0, 50) + WyHelper.randomDouble();
/* 18 */       double offsetZ = WyHelper.randomWithRange(-50, 50) + WyHelper.randomDouble();
/*    */       
/* 20 */       double motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 21 */       double motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 23 */       double middlePoint = 1.2D;
/*    */       
/* 25 */       motionX *= middlePoint / 25.0D;
/* 26 */       motionZ *= middlePoint / 25.0D;
/*    */       
/* 28 */       float scale = (float)(1.0D + WyHelper.randomWithRange(0, 2));
/*    */       
/* 30 */       float rotation = world.func_201674_k().nextFloat() / 4.0F;
/*    */       
/* 32 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 33 */       part.setLife(300);
/* 34 */       part.setSize(scale);
/* 35 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 36 */       part.setRotationSpeed(rotation);
/* 37 */       part.setMotion(motionX, -0.05D, motionZ);
/* 38 */       part.setHasMotionDecay(false);
/* 39 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doru\TokudaiCandleParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */