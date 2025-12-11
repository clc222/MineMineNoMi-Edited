/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.kachi;
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
/*    */ public class EvaporateParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 3; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 22 */       double motionX = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
/* 23 */       double motionY = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
/* 24 */       double motionZ = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
/*    */       
/* 26 */       double middlePoint = 0.05D;
/* 27 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 29 */       motionX *= middlePoint / 2.0D;
/* 30 */       motionY *= middlePoint / 2.0D;
/* 31 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 33 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 34 */       data.setLife(10);
/* 35 */       data.setSize(2.5F);
/* 36 */       data.setMotion(motionX, motionY + 0.05D, motionZ);
/* 37 */       world.func_195590_a((IParticleData)data, true, posX + 1.5D + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       
/* 39 */       offsetX = WyHelper.randomDouble();
/* 40 */       offsetY = WyHelper.randomDouble();
/* 41 */       offsetZ = WyHelper.randomDouble();
/*    */       
/* 43 */       data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 44 */       data.setLife(10);
/* 45 */       data.setSize(2.5F);
/* 46 */       data.setMotion(motionX, motionY + 0.05D, motionZ);
/* 47 */       world.func_195590_a((IParticleData)data, true, posX + 1.5D + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\kachi\EvaporateParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */