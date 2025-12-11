/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.pero;
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
/*    */ public class CandyWaveParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() * 3.0D;
/* 19 */       double offsetZ = WyHelper.randomDouble() * 3.0D;
/*    */       
/* 21 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 22 */       data.setLife(8);
/* 23 */       data.setSize(1.4F);
/* 24 */       data.setMotion(0.0D, 0.2D + Math.abs(WyHelper.randomDouble()) / 3.0D, 0.0D);
/* 25 */       data.setColor(0.5F, 0.0F, 0.5F);
/* 26 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\pero\CandyWaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */