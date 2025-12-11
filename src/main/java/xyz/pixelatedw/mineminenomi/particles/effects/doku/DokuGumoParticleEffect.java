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
/*    */ public class DokuGumoParticleEffect extends ParticleEffect<DokuParticleEffectDetails> {
/*    */   public DokuGumoParticleEffect() {
/* 14 */     super(DokuParticleEffectDetails::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, DokuParticleEffectDetails details) {
/* 20 */     boolean hasVenomDemon = details.hasVenomDemon();
/* 21 */     int width = hasVenomDemon ? 5 : 3;
/* 22 */     int height = hasVenomDemon ? 6 : 3;
/* 23 */     for (int i = 0; i < (hasVenomDemon ? 50 : 30); i++) {
/*    */       
/* 25 */       double offsetX = WyHelper.randomWithRange(-width, width) + WyHelper.randomDouble();
/* 26 */       double offsetY = WyHelper.randomWithRange(-height, height) + WyHelper.randomDouble();
/* 27 */       double offsetZ = WyHelper.randomWithRange(-width, width) + WyHelper.randomDouble();
/*    */       
/* 29 */       int age = (int)(5.0D + WyHelper.randomWithRange(0, 5));
/*    */       
/* 31 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/* 32 */       if (hasVenomDemon) {
/* 33 */         data.setColor(1.0F, 0.0F, 0.0F);
/*    */       }
/* 35 */       data.setLife(age);
/* 36 */       data.setSize(2.0F);
/* 37 */       entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\DokuGumoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */