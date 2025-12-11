/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.chiyu;
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
/*    */ public class ChiyupopoParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     double t = 0.0D;
/*    */     
/* 20 */     Random rand = world.field_73012_v;
/*    */     
/* 22 */     while (t < 1.0D) {
/*    */       
/* 24 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.3141592653589793D) {
/*    */         
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double y = 0.2D + rand.nextInt(1);
/* 30 */         double z = t * Math.sin(theta);
/*    */         
/* 32 */         double motionX = x / 4.0D;
/* 33 */         double motionY = 0.05D + rand.nextDouble() / 7.0D;
/* 34 */         double motionZ = z / 4.0D;
/*    */         
/* 36 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/* 37 */         data.setLife(4);
/* 38 */         data.setSize(2.0F);
/* 39 */         data.setMotion(motionX, motionY, motionZ);
/* 40 */         world.func_195590_a((IParticleData)data, true, posX + x * 0.75D + WyHelper.randomDouble(), posY + y, posZ + z * 0.75D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         
/* 42 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/* 43 */         data.setLife(7);
/* 44 */         data.setSize(2.5F);
/* 45 */         data.setMotion(motionX, motionY, motionZ);
/* 46 */         world.func_195590_a((IParticleData)data, true, posX + x * 2.0D + WyHelper.randomDouble(), posY + y, posZ + z * 2.0D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         
/* 48 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/* 49 */         data.setLife(10);
/* 50 */         data.setSize(4.5F);
/* 51 */         data.setMotion(motionX, motionY * 2.25D, motionZ);
/* 52 */         world.func_195590_a((IParticleData)data, true, posX + x * 3.25D + WyHelper.randomDouble(), posY + y, posZ + z * 3.25D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\chiyu\ChiyupopoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */