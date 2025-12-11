/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.sniper;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class KemuriBoshiParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 512; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/* 18 */       double offsetY = WyHelper.randomWithRange(-2, 3) + WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/*    */       
/* 21 */       if (i % 2 == 0) {
/* 22 */         world.func_195590_a((IParticleData)ParticleTypes.field_197613_f, true, posX + offsetX + WyHelper.randomWithRange(-7, 7), posY + 0.5D + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 0.0D, 0.0D, 0.0D);
/*    */       } else {
/* 24 */         world.func_195590_a((IParticleData)ParticleTypes.field_197613_f, true, posX + offsetX + WyHelper.randomWithRange(-7, 7), posY + 0.5D + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\sniper\KemuriBoshiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */