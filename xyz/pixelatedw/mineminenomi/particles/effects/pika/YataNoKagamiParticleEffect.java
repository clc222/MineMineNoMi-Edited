/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.pika;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ public class YataNoKagamiParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 20 */       double offsetX = ((new Random()).nextInt(40) + 1.0D - 20.0D) / 20.0D;
/* 21 */       double offsetY = ((new Random()).nextInt(40) + 1.0D) / 20.0D;
/* 22 */       double offsetZ = ((new Random()).nextInt(40) + 1.0D - 20.0D) / 20.0D;
/*    */       
/* 24 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 25 */       data.setLife(20);
/* 26 */       data.setSize(4.0F);
/* 27 */       data.setRotation(Vector3f.field_229181_d_);
/* 28 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\pika\YataNoKagamiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */