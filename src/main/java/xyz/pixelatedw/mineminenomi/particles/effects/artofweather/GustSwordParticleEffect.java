/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;
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
/*    */ public class GustSwordParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 6; i++) {
/* 17 */       ParticleType<SimpleParticleData> particle; double offsetX = WyHelper.randomDouble() / 3.0D;
/* 18 */       double offsetY = WyHelper.randomDouble() / 3.0D;
/* 19 */       double offsetZ = WyHelper.randomDouble() / 3.0D;
/*    */ 
/*    */       
/* 22 */       if (i % 2 == 0) {
/* 23 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU.get();
/*    */       } else {
/* 25 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MOKU2.get();
/*    */       } 
/* 27 */       SimpleParticleData data = new SimpleParticleData(particle);
/* 28 */       data.setLife(10);
/* 29 */       data.setSize(1.5F);
/* 30 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\GustSwordParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */