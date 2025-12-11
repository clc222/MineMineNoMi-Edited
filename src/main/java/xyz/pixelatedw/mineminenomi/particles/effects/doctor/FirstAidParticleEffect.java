/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doctor;
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
/*    */ public class FirstAidParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 45; i++) {
/*    */       SimpleParticleData data;
/* 18 */       double motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 19 */       double motionY = WyHelper.randomWithRange(1, 2);
/* 20 */       double motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/*    */       
/* 22 */       double middlePoint = 0.1D;
/* 23 */       middlePoint *= (world.field_73012_v.nextFloat() * 2.0F + 0.3F);
/*    */       
/* 25 */       motionX *= middlePoint / 50.0D;
/* 26 */       motionY *= middlePoint / 2.0D;
/* 27 */       motionZ *= middlePoint / 50.0D;
/*    */       
/* 29 */       double offsetX = WyHelper.randomDouble() / 1.2D;
/* 30 */       double offsetZ = WyHelper.randomDouble() / 1.2D;
/*    */ 
/*    */ 
/*    */       
/* 34 */       if (i % 2 == 0) {
/* 35 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/*    */       } else {
/* 37 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/*    */       } 
/* 39 */       data.setLife(10);
/* 40 */       data.setSize(1.5F);
/* 41 */       data.setColor(0.0F, 0.8F, 0.0F);
/* 42 */       data.setMotion(motionX, motionY, motionZ);
/* 43 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.5D, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doctor\FirstAidParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */