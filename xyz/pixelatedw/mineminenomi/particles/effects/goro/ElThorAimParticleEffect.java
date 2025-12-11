/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElThorAimParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     ParticleType<SimpleParticleData> goro_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/*    */     
/* 18 */     for (int i = 0; i < 3; i++) {
/* 19 */       double offsetX = WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 22 */       SimpleParticleData data = new SimpleParticleData(goro_particle);
/* 23 */       data.setLife(1);
/* 24 */       data.setSize(10.0F);
/* 25 */       world.func_195590_a((IParticleData)data, true, posX + 0.5D + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\ElThorAimParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */