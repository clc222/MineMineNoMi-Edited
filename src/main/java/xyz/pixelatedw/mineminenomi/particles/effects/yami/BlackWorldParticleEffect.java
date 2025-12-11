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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackWorldParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 500; i++) {
/* 16 */       double offsetX = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
/* 17 */       double offsetY = WyHelper.randomWithRange(0, 2) + WyHelper.randomDouble();
/* 18 */       double offsetZ = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
/*    */       
/* 20 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.DARKNESS.get());
/* 21 */       part.setLife(14);
/* 22 */       part.setAnimationSpeed(2);
/* 23 */       part.setRotation(Vector3f.field_229183_f_);
/* 24 */       part.setRotationSpeed((i % 2 == 0) ? 0.07F : -0.07F);
/* 25 */       part.setSize(1.2F);
/* 26 */       part.setMotion(0.0D, 0.02D, 0.0D);
/* 27 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       
/* 29 */       part.setLife(7);
/* 30 */       part.setAnimationSpeed(1);
/* 31 */       part.setRotation(Vector3f.field_229183_f_);
/* 32 */       part.setRotationSpeed((i % 2 == 0) ? 0.15F : -0.15F);
/* 33 */       part.setSize(1.2F);
/* 34 */       part.setMotion(0.0D, 0.02D, 0.0D);
/* 35 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\BlackWorldParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */