/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.kage;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChargeableKageParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 2; i++) {
/* 17 */       double offsetX = WyHelper.randomDouble() * 0.55D;
/* 18 */       double offsetY = WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomDouble() * 0.55D;
/*    */       
/* 21 */       SimpleParticleData particle = new SimpleParticleData((ParticleType)ModParticleTypes.DARKNESS_STATIC.get());
/* 22 */       particle.setRotation(Vector3f.field_229183_f_);
/* 23 */       particle.setRotationSpeed((i % 2 == 0) ? 0.15F : -0.15F);
/* 24 */       particle.setLife(5);
/* 25 */       particle.setSize(2.0F);
/* 26 */       particle.setMotion(0.0D, 0.1D, 0.0D);
/* 27 */       world.func_195590_a((IParticleData)particle, true, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\kage\ChargeableKageParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */