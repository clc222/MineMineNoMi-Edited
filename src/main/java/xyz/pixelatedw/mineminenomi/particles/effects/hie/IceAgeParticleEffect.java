/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hie;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class IceAgeParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 20 */     double t = 0.0D;
/*    */     
/* 22 */     Random rand = world.field_73012_v;
/*    */     
/* 24 */     while (t < 3.0D) {
/*    */       
/* 26 */       t += 0.3141592653589793D;
/*    */       double theta;
/* 28 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 30 */         double x = t * Math.cos(theta);
/* 31 */         double y = rand.nextInt(1);
/* 32 */         double z = t * Math.sin(theta);
/*    */         
/* 34 */         double motionX = x / 4.0D;
/* 35 */         double motionY = 0.05D + MathHelper.func_76135_e((float)WyHelper.randomDouble() / 12.0F);
/* 36 */         double motionZ = z / 4.0D;
/*    */         
/* 38 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.HIE.get());
/* 39 */         part.setLife(14);
/* 40 */         part.setAnimationSpeed(2);
/* 41 */         part.setRotation(Vector3f.field_229183_f_);
/* 42 */         part.setRotationSpeed((t % 2.0D == 0.0D) ? 0.07F : -0.07F);
/* 43 */         part.setSize(2.0F);
/* 44 */         part.setMotion(motionX, motionY, motionZ);
/* 45 */         world.func_195590_a((IParticleData)part, true, posX + x * 1.25D + WyHelper.randomDouble(), posY + y, posZ + z * 1.25D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hie\IceAgeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */