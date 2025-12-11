/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
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
/*    */ public class ConcasseDiableParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double radius = 0.5D;
/* 17 */     double phi = 0.0D;
/*    */     
/* 19 */     while (phi < Math.PI) {
/*    */       
/* 21 */       phi += 1.5707963267948966D;
/*    */       double theta;
/* 23 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 1.5707963267948966D) {
/*    */         SimpleParticleData data;
/* 25 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
/* 26 */         double y = radius * Math.cos(phi) + WyHelper.randomDouble() / 2.0D;
/* 27 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
/*    */ 
/*    */ 
/*    */         
/* 31 */         if (world.field_73012_v.nextInt(10) % 2 == 0) {
/* 32 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*    */         } else {
/* 34 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA2.get());
/* 35 */         }  data.setLife((int)WyHelper.randomWithRange(5, 10));
/* 36 */         data.setSize((float)WyHelper.randomWithRange(1, 3));
/* 37 */         data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 38 */         world.func_195594_a((IParticleData)data, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\ConcasseDiableParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */