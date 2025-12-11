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
/*    */ public class SandBladeParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public SandBladeParticleEffect(boolean isActive) {
/* 15 */     this.isActive = isActive;
/*    */   }
/*    */   private boolean isActive = false;
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 20 */     int area = 7;
/* 21 */     for (int i = 0; i < 128; i++) {
/* 22 */       double motionY = 0.0D;
/* 23 */       if (this.isActive) {
/* 24 */         motionY = (WyHelper.randomWithRange(1, 10) + world.func_201674_k().nextDouble()) * 0.05D;
/*    */       } else {
/*    */         
/* 27 */         motionY = WyHelper.randomDouble() / 30.0D;
/* 28 */         area = 5;
/*    */       } 
/*    */       
/* 31 */       posX += WyHelper.randomDouble() / area;
/* 32 */       posZ += WyHelper.randomDouble() / area;
/*    */       
/* 34 */       float particleSize = (world.func_201674_k().nextFloat() + 2.5F) / 2.0F;
/* 35 */       float color = WyHelper.colorTolerance(0.65F);
/*    */       
/* 37 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 38 */       part.setMotion(0.0D, motionY, 0.0D);
/* 39 */       part.setColor(color, color, color);
/* 40 */       part.setLife(20);
/* 41 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 42 */       part.setSize(particleSize);
/* 43 */       world.func_195590_a((IParticleData)part, true, posX, posY + 0.2D, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\SandBladeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */