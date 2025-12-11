/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RaigoParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     ParticleType<SimpleParticleData> goro2_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/* 17 */     ParticleType<SimpleParticleData> goro3_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO3.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO3_YELLOW.get();
/*    */     
/* 19 */     int range = 128; int i;
/* 20 */     for (i = 0; i < range; i++) {
/* 21 */       double offsetX = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
/* 22 */       double offsetY = 40.0D + WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 23 */       double offsetZ = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
/*    */       
/* 25 */       SimpleParticleData data = new SimpleParticleData(goro3_particle);
/* 26 */       data.setLife(100);
/* 27 */       data.setSize(100.0F);
/* 28 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       
/* 30 */       if (i % 2 == 0) {
/* 31 */         data.setColor(0.4F, 0.4F, 0.4F);
/*    */       } else {
/* 33 */         data.setColor(0.3F, 0.3F, 0.3F);
/*    */       } 
/*    */     } 
/* 36 */     for (i = 0; i < range / 2; i++) {
/* 37 */       double offsetX = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
/* 38 */       double offsetY = 30.0D + WyHelper.randomWithRange(-5, 0) + WyHelper.randomDouble();
/* 39 */       double offsetZ = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
/*    */       
/* 41 */       SimpleParticleData data = new SimpleParticleData(goro2_particle);
/* 42 */       data.setLife(10);
/* 43 */       data.setSize(40.0F);
/* 44 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\RaigoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */