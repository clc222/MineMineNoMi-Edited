/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
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
/*    */ public class SablesPesadoChargingParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double multiplier = 1.0D;
/* 17 */     double phi = 0.0D;
/*    */ 
/*    */     
/* 20 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 21 */     data.setLife(20);
/* 22 */     data.setSize(1.3F);
/*    */     
/* 24 */     while (phi < 6.283185307179586D) {
/*    */       
/* 26 */       phi += 0.19634954084936207D; double t;
/* 27 */       for (t = 0.0D; t <= 6.283185307179586D; t += 0.19634954084936207D) {
/*    */         
/* 29 */         double x = 0.45D * multiplier * (6.283185307179586D + t) * Math.cos(t * phi * Math.PI) + WyHelper.randomDouble();
/* 30 */         double y = 1.5D * multiplier * t;
/* 31 */         double z = 0.45D * multiplier * (6.283185307179586D + t) * Math.sin(t * phi * Math.PI) + WyHelper.randomDouble();
/*    */         
/* 33 */         data.setMotion(0.0D, 0.01D + Math.abs(WyHelper.randomDouble()) / 5.0D, 0.0D);
/*    */         
/* 35 */         entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\SablesPesadoChargingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */