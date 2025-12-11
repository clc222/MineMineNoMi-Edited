/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.cyborg;
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
/*    */ public class CoupDeBooParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 200; i++) {
/* 16 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 17 */       data.setColor(0.48F, 0.36F, 0.0F);
/* 18 */       data.setLife(30);
/* 19 */       data.setSize(3.0F);
/* 20 */       double motionY = world.func_201674_k().nextDouble() / 2.0D;
/* 21 */       data.setMotion(WyHelper.randomDouble() / 14.0D, motionY, WyHelper.randomDouble() / 14.0D);
/* 22 */       double offsetX = WyHelper.randomWithRange(-3, 3) * WyHelper.randomDouble();
/* 23 */       double offsetY = WyHelper.randomWithRange(-2, 2) * WyHelper.randomDouble();
/* 24 */       double offsetZ = WyHelper.randomWithRange(-3, 3) * WyHelper.randomDouble();
/* 25 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\cyborg\CoupDeBooParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */