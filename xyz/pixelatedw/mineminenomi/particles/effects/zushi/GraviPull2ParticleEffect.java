/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.zushi;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class GraviPull2ParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/*    */     double i;
/* 14 */     for (i = 0.0D; i < 7.283185307179586D; i += 0.09817477042468103D) {
/* 15 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.GASU.get());
/* 16 */       part.setLife(10);
/* 17 */       part.setSize(4.0F);
/* 18 */       double offsetX = Math.cos(i) * 20.0D;
/* 19 */       double offsetZ = Math.sin(i) * 20.0D;
/* 20 */       part.setMotion(-offsetX / 10.0D, 0.0D, -offsetZ / 10.0D);
/* 21 */       part.setHasMotionDecay(false);
/* 22 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 23 */       part.setRotationSpeed(world.func_201674_k().nextFloat() / 2.0F);
/* 24 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + 1.0D, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\zushi\GraviPull2ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */