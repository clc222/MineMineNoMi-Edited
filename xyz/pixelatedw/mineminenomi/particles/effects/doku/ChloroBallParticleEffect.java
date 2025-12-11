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
/*    */ public class ChloroBallParticleEffect extends ParticleEffect<DokuParticleEffectDetails> {
/*    */   public ChloroBallParticleEffect() {
/* 14 */     super(DokuParticleEffectDetails::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, DokuParticleEffectDetails details) {
/* 20 */     for (int i = 0; i < 12; i++) {
/*    */       
/* 22 */       double motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 23 */       double motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 24 */       double motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 26 */       double middlePoint = 0.25D;
/* 27 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 29 */       motionX *= middlePoint / 2.0D;
/* 30 */       motionY *= middlePoint / 2.0D;
/* 31 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 33 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/*    */       
/* 35 */       if (details.hasVenomDemon()) {
/* 36 */         data.setColor(1.0F, 0.0F, 0.0F);
/*    */       }
/* 38 */       data.setLife(8);
/* 39 */       data.setSize(0.8F);
/* 40 */       data.setMotion(motionX, motionY, motionZ);
/* 41 */       entity.field_70170_p.func_195590_a((IParticleData)data, true, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\ChloroBallParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */