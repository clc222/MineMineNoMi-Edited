/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.zushi;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class GraviZoneParticleEffect extends ParticleEffect<GraviZoneParticleEffect.Details> {
/*    */   public GraviZoneParticleEffect() {
/* 13 */     super(Details::new);
/*    */   }
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/*    */     double z;
/* 18 */     for (z = 0.0D; z < 7.283185307179586D; z += 0.09817477042468103D) {
/* 19 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.GASU.get());
/* 20 */       part.setLife(12);
/* 21 */       part.setSize(2.0F);
/* 22 */       double offsetX = Math.cos(z) * details.getRange();
/* 23 */       double offsetZ = Math.sin(z) * details.getRange();
/* 24 */       part.setMotion(offsetX / 20.0D, 0.0D, offsetZ / 20.0D);
/* 25 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 26 */       part.setRotationSpeed(world.func_201674_k().nextFloat() / 2.0F);
/* 27 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + 1.0D + details.getYOffset(), posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Details
/*    */     extends ParticleEffect.Details
/*    */   {
/*    */     private int range;
/*    */     private int yOffset;
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 38 */       nbt.func_74768_a("range", this.range);
/* 39 */       nbt.func_74768_a("yOffset", this.yOffset);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 44 */       this.range = nbt.func_74762_e("range");
/* 45 */       this.yOffset = nbt.func_74762_e("yOffset");
/*    */     }
/*    */     
/*    */     public void setYOffset(int offset) {
/* 49 */       this.yOffset = offset;
/*    */     }
/*    */     
/*    */     public int getYOffset() {
/* 53 */       return this.yOffset;
/*    */     }
/*    */     
/*    */     public void setRange(int range) {
/* 57 */       this.range = range;
/*    */     }
/*    */     
/*    */     public int getRange() {
/* 61 */       return this.range;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\zushi\GraviZoneParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */