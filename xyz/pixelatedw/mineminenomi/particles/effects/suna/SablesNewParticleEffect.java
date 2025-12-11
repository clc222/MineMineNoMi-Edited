/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SablesNewParticleEffect extends ParticleEffect<SablesNewParticleEffect.Details> {
/*    */   public SablesNewParticleEffect() {
/* 15 */     super(Details::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 21 */     int angle = 0;
/* 22 */     int maxHeight = (int)(details.size * 1.25F);
/* 23 */     double minRadius = (details.size / 7.0F);
/* 24 */     double maxRadius = (details.size * 3.0F);
/* 25 */     int lines = 6;
/* 26 */     double heightIncrease = 0.15D;
/* 27 */     double radiusIncrement = maxRadius / maxHeight / 4.0D;
/* 28 */     for (int l = 0; l < lines; l++) {
/*    */       double y;
/* 30 */       for (y = 0.0D; y < maxHeight; y += heightIncrease) {
/*    */         
/* 32 */         double radius = y * radiusIncrement;
/* 33 */         double v = (360.0F / lines * l) + y * 25.0D;
/* 34 */         double x = Math.cos(Math.toRadians(v - angle)) * Math.max(radius, minRadius);
/* 35 */         double z = Math.sin(Math.toRadians(v - angle)) * Math.max(radius, minRadius);
/*    */         
/* 37 */         float t = WyHelper.colorTolerance(0.1F);
/*    */         
/* 39 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 40 */         part.setLife(100);
/* 41 */         part.setSize(2.5F * details.size / 15.0F);
/* 42 */         part.setMotion(0.0D, 0.1D + Math.abs(WyHelper.randomDouble() / 15.0D), 0.0D);
/* 43 */         part.setColor(1.0F - t, 1.0F - t, 1.0F - t);
/* 44 */         entity.field_70170_p.func_195590_a((IParticleData)part, true, posX + x, posY + y, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/* 47 */     angle += 2;
/*    */   }
/*    */   
/*    */   public static class Details
/*    */     extends ParticleEffect.Details
/*    */   {
/*    */     private float size;
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 56 */       nbt.func_74776_a("size", this.size);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 61 */       this.size = nbt.func_74760_g("size");
/*    */     }
/*    */     
/*    */     public float getSize() {
/* 65 */       return this.size;
/*    */     }
/*    */     
/*    */     public void setSize(float size) {
/* 69 */       this.size = size;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\SablesNewParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */