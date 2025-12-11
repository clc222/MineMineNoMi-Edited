/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gasu;
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
/*    */ public class GastilleParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     for (int i = 0; i < 2; i++) {
/* 15 */       double offsetX = WyHelper.randomDouble() / 5.0D;
/* 16 */       double offsetY = WyHelper.randomDouble() / 5.0D;
/* 17 */       double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */       
/* 19 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.GASU2.get());
/* 20 */       data.setLife(5);
/* 21 */       data.setSize(0.8F);
/* 22 */       data.setColor(0.4F, 0.7F, 1.0F);
/* 23 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gasu\GastilleParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */