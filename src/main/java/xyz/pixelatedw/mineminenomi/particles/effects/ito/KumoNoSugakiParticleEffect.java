/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.ito;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class KumoNoSugakiParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 13 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.ITO.get());
/* 14 */     data.setLife(2);
/* 15 */     data.setSize(30.0F);
/* 16 */     data.setHasScaleDecay(false);
/* 17 */     world.func_195590_a((IParticleData)data, true, posX, posY + 1.2D, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\ito\KumoNoSugakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */