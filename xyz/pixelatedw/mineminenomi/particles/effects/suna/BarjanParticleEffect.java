/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
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
/*    */ public class BarjanParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 14 */     for (int i = 0; i < 40; i++) {
/* 15 */       double offsetX = WyHelper.randomDouble() / 1.3D;
/* 16 */       double offsetY = WyHelper.randomDouble() / 1.3D;
/* 17 */       double offsetZ = WyHelper.randomDouble() / 1.3D;
/*    */       
/* 19 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 20 */       data.setLife(10);
/* 21 */       data.setSize(1.3F);
/* 22 */       data.setMotion(offsetX / 5.0D, offsetY / 5.0D + 0.05D, offsetZ / 5.0D);
/* 23 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\BarjanParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */