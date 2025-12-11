/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.pika;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChargingPikaParticleEvent extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 2; i++) {
/* 16 */       double offsetX = WyHelper.randomDouble() * 1.55D;
/* 17 */       double offsetY = WyHelper.randomDouble();
/* 18 */       double offsetZ = WyHelper.randomDouble() * 1.55D;
/*    */       
/* 20 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 21 */       data.setLife(5);
/* 22 */       data.setSize(3.0F);
/* 23 */       data.setMotion(0.0D, 0.15D, 0.0D);
/* 24 */       data.setRotation(Vector3f.field_229181_d_);
/* 25 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\pika\ChargingPikaParticleEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */