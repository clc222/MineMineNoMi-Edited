/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
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
/*    */ 
/*    */ public class DaiEnkaiParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 20 */       double offsetY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 21 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 23 */       double motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 24 */       double motionY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 25 */       double motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 27 */       double middlePoint = 0.05D;
/* 28 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 30 */       motionX *= middlePoint / 2.0D;
/* 31 */       motionY *= middlePoint / 2.0D;
/* 32 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 34 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 35 */       data.setLife(20);
/* 36 */       data.setSize(1.3F);
/* 37 */       data.setMotion(motionX, motionY + 0.05D, motionZ);
/* 38 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\DaiEnkaiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */