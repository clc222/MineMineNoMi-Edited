/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.moku;
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
/*    */ 
/*    */ public class SmokeDebuffParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     for (int i = 0; i < 80; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 20 */       double offsetY = 1.0D + WyHelper.randomDouble() / 2.0D;
/* 21 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 23 */       ParticleType<SimpleParticleData> particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU2.get();
/* 24 */       if (i % 3 == 0) {
/* 25 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU.get();
/*    */       }
/* 27 */       SimpleParticleData data = new SimpleParticleData(particle);
/* 28 */       data.setLife((int)WyHelper.randomWithRange(1, 10));
/* 29 */       data.setSize((float)WyHelper.randomWithRange(1, 4));
/* 30 */       world.func_195590_a((IParticleData)data, true, entity.func_226277_ct_() + offsetX, entity.func_226278_cu_() + offsetY, entity.func_226281_cx_() + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\moku\SmokeDebuffParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */