/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doku;
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
/*    */ public class VenomDemonParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 3; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(0, 2) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 22 */       int age = (int)(5.0D + WyHelper.randomWithRange(0, 5));
/* 23 */       double motionY = 0.015D + Math.abs(WyHelper.randomDouble() / 8.0D);
/*    */       
/* 25 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/* 26 */       data.setLife(age);
/* 27 */       data.setSize(3.0F);
/* 28 */       data.setColor(1.0F, 0.0F, 0.0F);
/* 29 */       data.setMotion(0.0D, motionY, 0.0D);
/* 30 */       entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.25D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\VenomDemonParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */