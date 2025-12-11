/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gura;
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
/*    */ public class AirCrackParticleEffect
/*    */   extends ParticleEffect {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.GURA.get());
/* 16 */     data.setLife(20);
/* 17 */     data.setSize(16.0F);
/* 18 */     data.setColor(1.0F, 1.0F, 1.0F, 0.7F);
/* 19 */     WyHelper.spawnParticles((IParticleData)data, (ServerWorld)world, posX + Math.random() / 2.0D, posY + Math.random() / 2.0D, posZ + Math.random() / 2.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gura\AirCrackParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */