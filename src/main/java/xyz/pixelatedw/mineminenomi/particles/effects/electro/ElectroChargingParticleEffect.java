/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.electro;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElectroChargingParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double t = 0.0D;
/*    */     
/* 18 */     Random rand = world.field_73012_v;
/* 19 */     double size = 0.75D;
/*    */     
/* 21 */     while (t < 1.0D) {
/* 22 */       t += size * Math.PI;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/* 25 */         SimpleParticleData data; double x = t * Math.cos(theta);
/* 26 */         double z = t * Math.sin(theta);
/*    */         
/* 28 */         double motionX = -x / 20.0D;
/* 29 */         double motionY = 0.01D + rand.nextDouble() / 15.0D;
/* 30 */         double motionZ = -z / 20.0D;
/*    */ 
/*    */ 
/*    */         
/* 34 */         if (rand.nextInt(10) % 2 == 0) {
/* 35 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.GORO.get());
/*    */         } else {
/* 37 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.GORO2.get());
/* 38 */         }  data.setLife((int)WyHelper.randomWithRange(5, 10));
/* 39 */         data.setSize((float)WyHelper.randomWithRange(3, 5));
/* 40 */         data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 41 */         data.setMotion(motionX, motionY, motionZ);
/* 42 */         world.func_195590_a((IParticleData)data, true, posX + x + WyHelper.randomDouble() / 2.0D, posY + 0.25D, posZ + z + WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\electro\ElectroChargingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */