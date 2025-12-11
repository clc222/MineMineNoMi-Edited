/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.horu;
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
/*    */ public class WinkParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     double motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 15 */     double motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 16 */     double motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */     
/* 18 */     double middlePoint = 0.1D;
/* 19 */     middlePoint *= WyHelper.randomDouble() * 2.0D + 0.25D;
/*    */     
/* 21 */     motionX *= middlePoint / 5.0D;
/* 22 */     motionY *= middlePoint / 5.0D;
/* 23 */     motionZ *= middlePoint / 5.0D;
/*    */     
/* 25 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.HORU.get());
/* 26 */     data.setLife(5);
/* 27 */     data.setSize(5.0F);
/* 28 */     data.setMotion(motionX, motionY, motionZ);
/* 29 */     world.func_195594_a((IParticleData)data, posX, posY + 1.5D, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\horu\WinkParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */