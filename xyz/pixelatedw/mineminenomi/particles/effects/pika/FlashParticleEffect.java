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
/*    */ public class FlashParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 20 */       double offsetY = WyHelper.randomDouble() * 1.25D;
/* 21 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 23 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 24 */       data.setLife(5);
/* 25 */       data.setSize(4.0F);
/* 26 */       data.setRotation(Vector3f.field_229181_d_);
/* 27 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\pika\FlashParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */