/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hana;
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
/*    */ public class BloomParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 15; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 19 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 22 */       double motionX = WyHelper.randomDouble();
/* 23 */       double motionY = WyHelper.randomDouble();
/* 24 */       double motionZ = WyHelper.randomDouble();
/*    */       
/* 26 */       double middlePoint = 0.15D;
/* 27 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 29 */       motionX *= middlePoint / 5.0D;
/* 30 */       motionY *= middlePoint / 8.0D;
/* 31 */       motionZ *= middlePoint / 5.0D;
/*    */       
/* 33 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.HANA.get());
/* 34 */       data.setLife(20);
/* 35 */       data.setSize(3.0F);
/* 36 */       data.setMotion(Math.sin(motionX), motionY - 0.015D, Math.sin(motionZ));
/* 37 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY + 1.25D, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hana\BloomParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */