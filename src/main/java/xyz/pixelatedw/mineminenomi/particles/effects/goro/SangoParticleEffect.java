/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SangoParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     ParticleType<SimpleParticleData> goro_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/* 18 */     ParticleType<SimpleParticleData> goro2_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/*    */     
/* 20 */     for (int i = 0; i < 16; i++) {
/* 21 */       double offsetX = WyHelper.randomDouble();
/* 22 */       double offsetY = WyHelper.randomDouble();
/* 23 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 25 */       SimpleParticleData data = new SimpleParticleData(goro2_particle);
/* 26 */       data.setLife(5);
/* 27 */       data.setSize(2.0F);
/* 28 */       data.setColor(1.0F, 1.0F, 1.0F, 0.7F);
/* 29 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       
/* 31 */       SimpleParticleData data2 = new SimpleParticleData(goro_particle);
/* 32 */       data2.setLife(5);
/* 33 */       data2.setSize(2.0F);
/* 34 */       data2.setColor(1.0F, 1.0F, 1.0F, 0.7F);
/* 35 */       data2.setRotation(Vector3f.field_229183_f_);
/* 36 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\SangoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */