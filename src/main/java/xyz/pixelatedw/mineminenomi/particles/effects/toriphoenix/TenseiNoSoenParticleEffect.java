/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
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
/*    */ public class TenseiNoSoenParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     posY += 1.5D;
/*    */     
/* 18 */     double radius = 1.0D;
/* 19 */     double phi = 0.0D;
/*    */     
/* 21 */     while (phi < Math.PI) {
/*    */       
/* 23 */       phi += 0.7853981633974483D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 1.5707963267948966D) {
/*    */         
/* 27 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
/* 28 */         double y = radius * Math.cos(phi) + WyHelper.randomDouble();
/* 29 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
/*    */         
/* 31 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BLUE_FLAME.get());
/* 32 */         data.setLife(20);
/* 33 */         data.setSize(2.0F);
/* 34 */         data.setMotion(0.0D, 0.02D, 0.0D);
/* 35 */         world.func_195590_a((IParticleData)data, true, posX + x, posY - 1.0D + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\TenseiNoSoenParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */