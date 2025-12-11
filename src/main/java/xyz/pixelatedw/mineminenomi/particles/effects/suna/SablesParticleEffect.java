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
/*    */ public class SablesParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/* 13 */   private float size = 1.0F;
/*    */ 
/*    */   
/*    */   public SablesParticleEffect() {
/* 17 */     this.size = 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public SablesParticleEffect(float size) {
/* 22 */     this.size = size;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 28 */     int angle = 0;
/* 29 */     int maxHeight = 35 + (int)(2.0F * this.size);
/* 30 */     double minRadius = (1.0F * this.size);
/* 31 */     double maxRadius = (20.0F * this.size);
/* 32 */     int lines = 12;
/* 33 */     double heightIncrease = 0.25D;
/* 34 */     double radiusIncrement = maxRadius / maxHeight * (1.0F - this.size / 2.0F);
/* 35 */     for (int l = 0; l < lines; l++) {
/*    */       double y;
/* 37 */       for (y = 0.0D; y < maxHeight; y += heightIncrease) {
/*    */         
/* 39 */         double radius = y * radiusIncrement;
/* 40 */         double v = (360.0F / lines * l) + y * 25.0D;
/* 41 */         double x = Math.cos(Math.toRadians(v - angle)) * Math.max(radius, minRadius);
/* 42 */         double z = Math.sin(Math.toRadians(v - angle)) * Math.max(radius, minRadius);
/*    */         
/* 44 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 45 */         data.setLife(70);
/* 46 */         data.setSize(2.5F * this.size);
/* 47 */         data.setMotion((entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b + 0.1D - Math.abs(WyHelper.randomDouble() / 5.0D), (entity.func_213322_ci()).field_72449_c);
/* 48 */         entity.field_70170_p.func_195590_a((IParticleData)data, true, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/* 51 */     angle += 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\SablesParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */