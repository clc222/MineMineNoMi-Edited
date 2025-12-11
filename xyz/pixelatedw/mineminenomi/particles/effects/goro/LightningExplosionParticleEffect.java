/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LightningExplosionParticleEffect
/*    */   extends ParticleEffect {
/*    */   private int explosionSize;
/*    */   
/*    */   public LightningExplosionParticleEffect() {
/* 19 */     this(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public LightningExplosionParticleEffect(int explosionSize) {
/* 24 */     this.explosionSize = explosionSize;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 30 */     ParticleType<SimpleParticleData> goro_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/* 31 */     ParticleType<SimpleParticleData> goro2_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/*    */     
/* 33 */     for (int i = 0; i < this.explosionSize * 2; i++) {
/*    */       
/* 35 */       double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/* 36 */       double y = posY + WyHelper.randomDouble();
/* 37 */       double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/*    */       
/* 39 */       motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 40 */       motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 41 */       motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 43 */       double middlePoint = 0.5D / (5.0D / this.explosionSize + 0.1D);
/* 44 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 46 */       motionX *= middlePoint / 2.0D;
/* 47 */       motionY *= middlePoint / 2.0D;
/* 48 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 50 */       float scale = (float)(1.0D + WyHelper.randomWithRange(2, 5));
/*    */       
/* 52 */       ParticleType<SimpleParticleData> particle = goro_particle;
/* 53 */       if (i % 2 == 0) {
/* 54 */         particle = goro2_particle;
/*    */       }
/*    */       
/* 57 */       SimpleParticleData data = new SimpleParticleData(particle);
/* 58 */       data.setLife(30);
/* 59 */       data.setSize(scale);
/* 60 */       data.setMotion(motionX, motionY, motionZ);
/* 61 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)world, x, y + 1.0D, z);
/* 62 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197598_I, (ServerWorld)world, posX, posY + 1.0D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\LightningExplosionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */