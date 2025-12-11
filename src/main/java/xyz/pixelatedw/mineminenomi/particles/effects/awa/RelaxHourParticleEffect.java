/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.awa;
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
/*    */ public class RelaxHourParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/*    */     int i;
/* 15 */     for (i = 0; i < 15; i++) {
/* 16 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 17 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 18 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 20 */       ParticleType<SimpleParticleData> particle = (ParticleType<SimpleParticleData>)ModParticleTypes.AWA.get();
/* 21 */       if (i % 3 == 0) {
/* 22 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.AWA3.get();
/*    */       }
/* 24 */       SimpleParticleData data = new SimpleParticleData(particle);
/* 25 */       data.setLife(7);
/* 26 */       data.setSize(1.3F);
/* 27 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 30 */     for (i = 0; i < 5; i++) {
/* 31 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 32 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 33 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 35 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.AWA_FOAM.get());
/* 36 */       data.setLife(7);
/* 37 */       data.setSize(1.3F);
/* 38 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\awa\RelaxHourParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */