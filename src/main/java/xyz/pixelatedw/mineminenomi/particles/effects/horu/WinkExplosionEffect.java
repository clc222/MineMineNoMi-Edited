/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.horu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WinkExplosionEffect
/*    */   extends ParticleEffect {
/*    */   private int explosionSize;
/*    */   
/*    */   public WinkExplosionEffect() {
/* 18 */     this(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public WinkExplosionEffect(int explosionSize) {
/* 23 */     this.explosionSize = explosionSize;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 29 */     for (int i = 0; i < this.explosionSize * 2; i++) {
/*    */       
/* 31 */       double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/* 32 */       double y = posY + WyHelper.randomDouble();
/* 33 */       double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/*    */       
/* 35 */       motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 36 */       motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 37 */       motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 39 */       double middlePoint = 0.5D / (5.0D / this.explosionSize + 0.1D);
/* 40 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 42 */       motionX *= middlePoint / 2.0D;
/* 43 */       motionY *= middlePoint / 2.0D;
/* 44 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 46 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.HORU.get());
/* 47 */       data.setLife(20);
/* 48 */       data.setSize(6.0F);
/* 49 */       data.setMotion(motionX, motionY, motionZ);
/* 50 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)world, x, y + 1.0D, z);
/* 51 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197627_t, (ServerWorld)world, x, y, z);
/* 52 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197598_I, (ServerWorld)world, posX, posY + 1.0D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\horu\WinkExplosionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */