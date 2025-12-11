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
/*    */ public class ChloroBallCloudParticleEffect extends ParticleEffect<DokuParticleEffectDetails> {
/*    */   public ChloroBallCloudParticleEffect() {
/* 14 */     super(DokuParticleEffectDetails::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, DokuParticleEffectDetails details) {
/* 20 */     for (int i = 0; i < 32; i++) {
/*    */       
/* 22 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 23 */       double offsetY = WyHelper.randomWithRange(-1, 2) + WyHelper.randomDouble();
/* 24 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 26 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/*    */       
/* 28 */       if (details.hasVenomDemon()) {
/* 29 */         data.setColor(1.0F, 0.0F, 0.0F);
/*    */       }
/* 31 */       data.setLife(10);
/* 32 */       data.setSize(2.5F);
/* 33 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 34 */       entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\ChloroBallCloudParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */