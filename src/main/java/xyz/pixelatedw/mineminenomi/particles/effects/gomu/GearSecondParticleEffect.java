/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gomu;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GearSecondParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 13 */     for (int i = 0; i < 8; i++) {
/* 14 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 15 */       double offsetY = WyHelper.randomDouble() / 1.25D;
/* 16 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 18 */       world.func_195590_a((IParticleData)ParticleTypes.field_197598_I, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gomu\GearSecondParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */