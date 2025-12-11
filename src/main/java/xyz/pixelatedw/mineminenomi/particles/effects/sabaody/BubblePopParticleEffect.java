/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.sabaody;
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
/*    */ public class BubblePopParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 15; i++) {
/* 16 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 17 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 18 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 20 */       ParticleType<SimpleParticleData> particleStart = (ParticleType<SimpleParticleData>)ModParticleTypes.AWA.get();
/*    */       
/* 22 */       SimpleParticleData data = new SimpleParticleData(particleStart);
/* 23 */       data.setLife(13);
/* 24 */       data.setSize(1.3F);
/* 25 */       data.setMotion(0.0D, 0.2D, 0.0D);
/* 26 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\sabaody\BubblePopParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */