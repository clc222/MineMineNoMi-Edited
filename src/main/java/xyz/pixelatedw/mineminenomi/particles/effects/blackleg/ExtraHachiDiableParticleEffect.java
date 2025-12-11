/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
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
/*    */ public class ExtraHachiDiableParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 4; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 3.0D;
/* 19 */       double offsetY = WyHelper.randomDouble() / 3.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 3.0D;
/*    */       
/* 22 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 23 */       data.setLife(10);
/* 24 */       data.setSize(1.0F);
/* 25 */       data.setMotion(offsetX / 12.0D, offsetY / 5.0D + 0.05D, offsetZ / 12.0D);
/*    */       
/* 27 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\ExtraHachiDiableParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */