/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
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
/*    */ public class PartyTableKickCourseParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double t = 0.0D;
/*    */     
/* 18 */     Random rand = world.field_73012_v;
/* 19 */     double size = 1.0D;
/*    */     
/* 21 */     while (t < 1.0D) {
/* 22 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/* 25 */         SimpleParticleData data; double x = t * Math.cos(theta);
/* 26 */         double z = t * Math.sin(theta);
/*    */         
/* 28 */         double motionX = Math.sin(theta) / 10.0D;
/* 29 */         double motionY = 0.01D + rand.nextDouble() / 10.0D;
/* 30 */         double motionZ = -Math.cos(theta) / 10.0D;
/*    */ 
/*    */ 
/*    */         
/* 34 */         if (rand.nextInt(10) % 2 == 0) {
/* 35 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */         } else {
/* 37 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA2.get());
/* 38 */         }  data.setLife((int)WyHelper.randomWithRange(5, 10));
/* 39 */         data.setSize((float)WyHelper.randomWithRange(1, 3));
/* 40 */         data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 41 */         data.setMotion(motionX, motionY, motionZ);
/*    */         
/* 43 */         world.func_195590_a((IParticleData)data, true, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 1.4D, posZ + z * size + 
/* 44 */             WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/* 45 */         world.func_195590_a((IParticleData)data, true, posX + x * size / 2.0D + WyHelper.randomDouble() / 2.0D, posY + 1.4D, posZ + z * size / 2.0D + 
/* 46 */             WyHelper.randomDouble() / 2.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\PartyTableKickCourseParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */