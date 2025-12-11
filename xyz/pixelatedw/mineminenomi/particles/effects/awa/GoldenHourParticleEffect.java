/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.awa;
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
/*    */ public class GoldenHourParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < WyHelper.randomWithRange(6, 9); i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 1.7D;
/* 19 */       double offsetY = WyHelper.randomDouble() * 1.55D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 1.7D;
/*    */       
/* 22 */       int age = (int)(3.0D + WyHelper.randomWithRange(0, 4));
/* 23 */       double motionY = WyHelper.randomDouble() / 50.0D;
/* 24 */       if (motionY < 0.0D) {
/* 25 */         motionY = 0.02D;
/*    */       }
/* 27 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.AWA2.get());
/* 28 */       data.setLife(age);
/* 29 */       data.setSize(1.5F);
/* 30 */       data.setMotion(0.0D, motionY, 0.0D);
/* 31 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.25D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       
/* 33 */       data = new SimpleParticleData((ParticleType)ModParticleTypes.AWA_FOAM.get());
/* 34 */       data.setLife(age);
/* 35 */       data.setSize(1.5F);
/* 36 */       data.setMotion(0.0D, motionY / 2.0D, 0.0D);
/* 37 */       data.setColor(1.0F, 1.0F, 0.0F, 0.5F);
/* 38 */       world.func_195590_a((IParticleData)data, true, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\awa\GoldenHourParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */