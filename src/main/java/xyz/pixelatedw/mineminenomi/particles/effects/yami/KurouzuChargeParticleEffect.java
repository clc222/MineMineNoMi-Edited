/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class KurouzuChargeParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.KUROUZU.get());
/* 17 */     data.setColor(1.0F, 1.0F, 1.0F, 0.25F);
/* 18 */     data.setLife(1);
/* 19 */     data.setSize(10.0F);
/* 20 */     data.setHasScaleDecay(false);
/* 21 */     data.setRotation(Vector3f.field_229183_f_);
/* 22 */     world.func_195590_a((IParticleData)data, true, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\KurouzuChargeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */