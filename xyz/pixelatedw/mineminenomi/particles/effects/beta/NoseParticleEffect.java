/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.beta;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NoseParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 18 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BETA.get());
/* 19 */       data.setLife(20);
/* 20 */       data.setSize(0.6F);
/* 21 */       data.setMotion(motionX, motionY - 0.03D, motionZ);
/* 22 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)world, posX - 0.35D, posY + 1.45D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\beta\NoseParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */