/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KarakusagawaraSeikenChargeParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     int i = 0;
/* 18 */     double t = 0.0D;
/*    */     
/* 20 */     Random rand = world.field_73012_v;
/*    */     
/* 22 */     while (t < 1.0D) {
/* 23 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/* 26 */         double x = t * Math.cos(theta);
/* 27 */         double y = rand.nextInt(1);
/* 28 */         double z = t * Math.sin(theta);
/*    */         
/* 30 */         double motionX = -x / 20.0D;
/* 31 */         double motionY = 0.05D + rand.nextDouble() / 10.0D;
/* 32 */         double motionZ = -z / 20.0D;
/*    */         
/* 34 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.AWA.get());
/*    */         
/* 36 */         if (i % 2 == 0) {
/* 37 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.AWA.get());
/*    */         }
/* 39 */         double offsetX = world.field_73012_v.nextDouble() * WyHelper.randomWithRange(7, 9);
/* 40 */         double offsetY = 1.0D;
/* 41 */         double offsetZ = world.field_73012_v.nextDouble() * WyHelper.randomWithRange(7, 9);
/*    */         
/* 43 */         world.func_195590_a((IParticleData)ParticleTypes.field_197618_k, true, posX - 4.0D + offsetX, posY - 1.0D + offsetY, posZ - 4.0D + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */         
/* 45 */         data.setLife(1);
/* 46 */         data.setSize(1.0F);
/* 47 */         data.setMotion(motionX, motionY, motionZ);
/* 48 */         data.setColor(0.0F, 0.0F, 1.0F, 0.8F);
/* 49 */         world.func_195590_a((IParticleData)data, true, posX + x * 1.25D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.25D + WyHelper.randomDouble() * 2.0D, 0.0D, 0.0D, 0.0D);
/*    */         
/* 51 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\fishkarate\KarakusagawaraSeikenChargeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */