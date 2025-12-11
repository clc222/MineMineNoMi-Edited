/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HakiGuardParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 12 */     for (int i = 0; i < 25; i++) {
/* 13 */       double x = posX + WyHelper.randomDouble() / 1.2D;
/* 14 */       double y = posY + WyHelper.randomDouble() / 1.2D;
/* 15 */       double z = posZ + WyHelper.randomDouble() / 1.2D;
/*    */       
/* 17 */       if (i % 2 == 0) {
/* 18 */         world.func_195594_a((IParticleData)ParticleTypes.field_197614_g, x, y + 1.0D, z, 0.0D, 0.0D, 0.0D);
/*    */       }
/* 20 */       world.func_195594_a((IParticleData)ParticleTypes.field_197607_R, x, y + 1.0D, z, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\HakiGuardParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */