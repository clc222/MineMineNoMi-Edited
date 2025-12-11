/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class WaterExplosionParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 14 */     for (int i = 0; i < 12; i++) {
/*    */       
/* 16 */       motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 17 */       motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 18 */       motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 20 */       double middlePoint = 0.25D;
/* 21 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 23 */       motionX *= middlePoint / 2.0D;
/* 24 */       motionY *= middlePoint / 2.0D;
/* 25 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 27 */       ((ServerWorld)world).func_195598_a((IParticleData)ParticleTypes.field_197630_w, posX, posY, posZ, 1, motionX, motionY, motionZ, 0.1D);
/* 28 */       ((ServerWorld)world).func_195598_a((IParticleData)ParticleTypes.field_197630_w, posX, posY, posZ, 1, motionX, motionY, motionZ, 0.1D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\WaterExplosionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */