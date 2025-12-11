/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CommonExplosionParticleEffect
/*    */   extends ParticleEffect {
/*    */   private int explosionSize;
/*    */   
/*    */   public CommonExplosionParticleEffect() {
/* 14 */     this(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommonExplosionParticleEffect(int explosionSize) {
/* 19 */     this.explosionSize = explosionSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 24 */     if (world.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 28 */     for (int i = 0; i < this.explosionSize * 2; i++) {
/* 29 */       double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/* 30 */       double y = posY + WyHelper.randomDouble();
/* 31 */       double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/*    */       
/* 33 */       motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 34 */       motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 35 */       motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 37 */       double middlePoint = 0.5D / ((5 / this.explosionSize) + 0.1D);
/* 38 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 40 */       motionX *= middlePoint / 2.0D;
/* 41 */       motionY *= middlePoint / 2.0D;
/* 42 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 44 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197627_t, (ServerWorld)world, x, y + 1.0D, z);
/* 45 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197598_I, (ServerWorld)world, posX, posY + 1.0D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\CommonExplosionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */