/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mero;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class SlaveArrowParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     SimpleParticleData particle = new SimpleParticleData((ParticleType)ModParticleTypes.MERO.get());
/* 15 */     particle.setLife(60);
/* 16 */     particle.setSize(20.0F);
/* 17 */     particle.setColor(1.0F, 1.0F, 1.0F, 0.4F);
/* 18 */     particle.setHasScaleDecay(false);
/* 19 */     particle.setFunction(EasingFunction.ELASTIC_IN);
/* 20 */     world.func_195590_a((IParticleData)particle, true, posX, posY + 1.0D, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mero\SlaveArrowParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */