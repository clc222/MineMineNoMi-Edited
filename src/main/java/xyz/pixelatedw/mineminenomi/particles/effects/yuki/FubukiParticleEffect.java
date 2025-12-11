/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yuki;
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
/*    */ public class FubukiParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 1024; i++) {
/* 17 */       double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/* 18 */       double offsetY = WyHelper.randomWithRange(0, 20) + WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/*    */       
/* 21 */       double motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 22 */       double motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 24 */       double middlePoint = 1.2D;
/*    */       
/* 26 */       motionX *= middlePoint / 25.0D;
/* 27 */       motionZ *= middlePoint / 25.0D;
/*    */       
/* 29 */       float scale = (float)(1.0D + WyHelper.randomWithRange(0, 3));
/*    */       
/* 31 */       ParticleType<SimpleParticleData> particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI.get();
/* 32 */       if (i % 5 == 0) {
/* 33 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI3.get();
/*    */       }
/* 35 */       else if (i % 2 == 0) {
/* 36 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI2.get();
/*    */       } else {
/*    */         
/* 39 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI.get();
/*    */       } 
/*    */       
/* 42 */       float rotation = world.func_201674_k().nextFloat() / 4.0F;
/*    */       
/* 44 */       SimpleParticleData part = new SimpleParticleData(particle);
/* 45 */       part.setLife(300);
/* 46 */       part.setSize(scale);
/* 47 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 48 */       part.setRotationSpeed(rotation);
/* 49 */       part.setMotion(motionX, -0.05D, motionZ);
/* 50 */       part.setHasMotionDecay(false);
/* 51 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yuki\FubukiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */