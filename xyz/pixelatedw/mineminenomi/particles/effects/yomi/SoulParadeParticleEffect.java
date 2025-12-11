/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yomi;
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
/*    */ public class SoulParadeParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 64; i++) {
/* 16 */       double motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 17 */       double motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 18 */       double motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 20 */       double middlePoint = 0.1D;
/* 21 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 23 */       motionX *= middlePoint / 2.0D;
/* 24 */       motionY *= middlePoint / 2.0D;
/* 25 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 27 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.HIE.get());
/* 28 */       part.setLife(14);
/* 29 */       part.setAnimationSpeed(2);
/* 30 */       part.setRotation(Vector3f.field_229183_f_);
/* 31 */       part.setRotationSpeed((i % 2 == 0) ? 0.07F : -0.07F);
/* 32 */       part.setSize(1.0F);
/* 33 */       part.setMotion(motionX, motionY, motionZ);
/* 34 */       world.func_195590_a((IParticleData)part, true, posX, posY + 1.0D, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yomi\SoulParadeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */