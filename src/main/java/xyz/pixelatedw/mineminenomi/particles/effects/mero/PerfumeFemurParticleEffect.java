/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mero;
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
/*    */ public class PerfumeFemurParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 64; i++) {
/* 16 */       double motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 17 */       double motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 18 */       double motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 20 */       double middlePoint = 0.1D;
/* 21 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 23 */       motionX *= middlePoint / 2.0D;
/* 24 */       motionY *= middlePoint / 2.0D;
/* 25 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 27 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERO.get());
/* 28 */       data.setLife(10);
/* 29 */       data.setSize(1.0F);
/* 30 */       data.setMotion(motionX, motionY, motionZ);
/* 31 */       world.func_195590_a((IParticleData)data, true, posX, posY + 1.0D, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mero\PerfumeFemurParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */