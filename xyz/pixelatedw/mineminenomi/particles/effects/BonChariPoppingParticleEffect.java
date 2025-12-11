/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BonChariPoppingParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     for (int i = 0; i < 12; i++) {
/* 15 */       double motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 16 */       double motionY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 17 */       double motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 19 */       double middlePoint = 0.25D;
/* 20 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 22 */       motionX *= middlePoint / 2.0D;
/* 23 */       motionY *= middlePoint / 2.0D;
/* 24 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 26 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.AWA.get());
/* 27 */       part.setLife(15);
/* 28 */       part.setSize(7.0F);
/* 29 */       part.setMotion(motionX, motionY, motionZ);
/* 30 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 31 */       part.setRotationSpeed(1.0F);
/* 32 */       world.func_195590_a((IParticleData)part, true, posX, posY + 0.3D, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\BonChariPoppingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */