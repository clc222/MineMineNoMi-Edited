/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.chiyu;
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
/*    */ public class HealingTouchParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 64; i++) {
/*    */       
/* 18 */       double motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 19 */       double motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 20 */       double motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 22 */       double middlePoint = 0.1D;
/* 23 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 25 */       motionX *= middlePoint / 2.0D;
/* 26 */       motionY *= middlePoint / 2.0D;
/* 27 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 29 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/* 30 */       data.setLife(10);
/* 31 */       data.setSize(1.5F);
/* 32 */       data.setMotion(motionX, motionY, motionZ);
/* 33 */       world.func_195590_a((IParticleData)data, true, posX, posY + 1.0D, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\chiyu\HealingTouchParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */