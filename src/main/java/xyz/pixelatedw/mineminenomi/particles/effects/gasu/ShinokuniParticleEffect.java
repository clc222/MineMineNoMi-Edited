/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gasu;
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
/*    */ public class ShinokuniParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 10; i++) {
/* 16 */       ParticleType type; double offsetX = WyHelper.randomDouble() * 2.0D;
/* 17 */       double offsetY = WyHelper.randomDouble() * 2.0D;
/* 18 */       double offsetZ = WyHelper.randomDouble() * 2.0D;
/*    */ 
/*    */       
/* 21 */       if (i % 5 == 0) {
/* 22 */         type = (ParticleType)ModParticleTypes.GASU2.get();
/*    */       } else {
/* 24 */         type = (ParticleType)ModParticleTypes.GASU.get();
/*    */       } 
/* 26 */       SimpleParticleData data = new SimpleParticleData(type);
/* 27 */       data.setMotion(0.0D, 0.1D, 0.0D);
/* 28 */       data.setLife(40);
/* 29 */       data.setSize(3.0F);
/* 30 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gasu\ShinokuniParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */