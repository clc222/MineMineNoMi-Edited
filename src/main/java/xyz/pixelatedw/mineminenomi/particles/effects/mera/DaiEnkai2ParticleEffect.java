/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
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
/*    */ 
/*    */ 
/*    */ public class DaiEnkai2ParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 19 */     double t = 0.0D;
/*    */     
/* 21 */     Random rand = world.field_73012_v;
/*    */     
/* 23 */     while (t < 1.0D) {
/*    */       
/* 25 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 27 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 29 */         double x = t * Math.cos(theta);
/* 30 */         double y = rand.nextInt(1);
/* 31 */         double z = t * Math.sin(theta);
/*    */         
/* 33 */         double motionX = x / 10.0D;
/* 34 */         double motionY = 0.05D + rand.nextDouble() / 10.0D;
/* 35 */         double motionZ = z / 10.0D;
/*    */         
/* 37 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 38 */         data.setLife(1);
/* 39 */         data.setSize(1.3F);
/* 40 */         data.setMotion(motionX, motionY, motionZ);
/* 41 */         world.func_195590_a((IParticleData)data, true, posX + x * 1.25D + WyHelper.randomDouble(), posY + y, posZ + z * 1.25D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         
/* 43 */         data.setLife(3);
/* 44 */         data.setSize(1.3F);
/* 45 */         data.setMotion(motionX, motionY + 0.15D, motionZ);
/* 46 */         world.func_195590_a((IParticleData)data, true, posX + x * 2.0D + WyHelper.randomDouble(), posY + y, posZ + z * 2.0D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         
/* 48 */         data.setLife(5);
/* 49 */         data.setSize(1.3F);
/* 50 */         data.setMotion(motionX, motionY + 0.25D, motionZ);
/* 51 */         world.func_195590_a((IParticleData)data, true, posX + x * 3.25D + WyHelper.randomDouble(), posY + y, posZ + z * 3.25D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\DaiEnkai2ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */