/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.kobu;
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
/*    */ public class ShoureiParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 19 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 22 */       SimpleParticleData particleData = new SimpleParticleData((ParticleType)ModParticleTypes.CHIYU.get());
/* 23 */       particleData.setLife(7);
/* 24 */       particleData.setSize(1.0F);
/* 25 */       double y = world.field_73012_v.nextDouble() / 15.0D;
/* 26 */       particleData.setMotion(0.0D, y, 0.0D);
/* 27 */       particleData.setColor(0.5F, 1.0F, 0.5F, 0.7F);
/* 28 */       world.func_195590_a((IParticleData)particleData, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\kobu\ShoureiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */