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
/*    */ 
/*    */ public class ElThorParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     ParticleType<SimpleParticleData> goro2_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/* 19 */     ParticleType<SimpleParticleData> goro3_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO3.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO3_YELLOW.get();
/*    */     int i;
/* 21 */     for (i = 0; i < 30; i++) {
/*    */       
/* 23 */       double offsetX = WyHelper.randomWithRange(-32, 32) + WyHelper.randomDouble();
/* 24 */       double offsetY = 72.0D + WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 25 */       double offsetZ = WyHelper.randomWithRange(-32, 32) + WyHelper.randomDouble();
/*    */       
/* 27 */       SimpleParticleData data = new SimpleParticleData(goro3_particle);
/* 28 */       data.setLife(140);
/* 29 */       data.setSize(50.0F);
/*    */       
/* 31 */       if (i % 2 == 0) {
/* 32 */         data.setColor(0.4F, 0.4F, 0.4F);
/*    */       } else {
/* 34 */         data.setColor(0.3F, 0.3F, 0.3F);
/*    */       } 
/* 36 */       world.func_195590_a((IParticleData)data, true, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 39 */     for (i = 0; i < 16; i++) {
/*    */       
/* 41 */       double offsetX = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/* 42 */       double offsetY = 72.0D + WyHelper.randomDouble();
/* 43 */       double offsetZ = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/*    */       
/* 45 */       SimpleParticleData data = new SimpleParticleData(goro2_particle);
/* 46 */       data.setLife(100);
/* 47 */       data.setSize(15.0F);
/*    */       
/* 49 */       world.func_195590_a((IParticleData)data, true, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\ElThorParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */