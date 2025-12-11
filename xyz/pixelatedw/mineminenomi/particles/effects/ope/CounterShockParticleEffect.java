/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.ope;
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
/*    */ public class CounterShockParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 18 */       double x = WyHelper.randomDouble();
/* 19 */       double y = WyHelper.randomDouble();
/* 20 */       double z = WyHelper.randomDouble();
/*    */       
/* 22 */       double motionY = 0.005D + Math.abs(WyHelper.randomDouble() / 8.0D);
/*    */       
/* 24 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.GORO2.get());
/* 25 */       data.setLife(5);
/* 26 */       data.setSize(3.5F);
/* 27 */       data.setMotion(0.0D, motionY, 0.0D);
/* 28 */       world.func_195590_a((IParticleData)data, true, posX + x, posY + 1.5D + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\ope\CounterShockParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */