/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
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
/*    */ 
/*    */ public class HeatDashParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 1.3D;
/* 20 */       double offsetY = WyHelper.randomDouble() / 1.3D;
/* 21 */       double offsetZ = WyHelper.randomDouble() / 1.3D;
/*    */       
/* 23 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 24 */       data.setLife(10);
/* 25 */       data.setSize(1.3F);
/* 26 */       data.setMotion(offsetX / 5.0D, offsetY / 5.0D + 0.05D, offsetZ / 5.0D);
/* 27 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\HeatDashParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */