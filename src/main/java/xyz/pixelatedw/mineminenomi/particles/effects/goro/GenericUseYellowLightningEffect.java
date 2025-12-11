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
/*    */ public class GenericUseYellowLightningEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     ParticleType<SimpleParticleData> goro_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/*    */     
/* 19 */     for (int i = 0; i < 25; i++) {
/*    */       
/* 21 */       double offsetX = WyHelper.randomDouble();
/* 22 */       double offsetY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 2.0D;
/* 23 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 25 */       int age = (int)WyHelper.randomWithRange(2, 8);
/*    */       
/* 27 */       SimpleParticleData data = new SimpleParticleData(goro_particle);
/* 28 */       data.setLife(age);
/* 29 */       data.setSize(0.4F);
/* 30 */       data.setMotion(0.0D, 0.01D, 0.0D);
/* 31 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\GenericUseYellowLightningEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */