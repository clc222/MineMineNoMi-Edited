/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yuki;
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
/*    */ public class KamakuraParticleEffect
/*    */   extends ParticleEffect<KamakuraParticleEffect.Details> {
/*    */   public KamakuraParticleEffect() {
/* 16 */     super(Details::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 22 */     int size = details.getSize();
/* 23 */     for (int i = 0; i < 60; i++) {
/*    */       
/* 25 */       double offsetX = 2.0D * WyHelper.randomWithRange(-size, size) + WyHelper.randomDouble();
/* 26 */       double offsetY = 2.0D * WyHelper.randomWithRange(0, size) + WyHelper.randomDouble();
/* 27 */       double offsetZ = 2.0D * WyHelper.randomWithRange(-size, size) + WyHelper.randomDouble();
/*    */       
/* 29 */       double motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 30 */       double motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 32 */       double middlePoint = 1.2D;
/*    */       
/* 34 */       motionX *= middlePoint / 25.0D;
/* 35 */       motionZ *= middlePoint / 25.0D;
/*    */       
/* 37 */       float scale = (float)(1.0D + WyHelper.randomWithRange(5, 7));
/*    */       
/* 39 */       ParticleType<SimpleParticleData> particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI.get();
/* 40 */       if (i % 5 == 0) {
/* 41 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI3.get();
/* 42 */       } else if (i % 2 == 0) {
/* 43 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI2.get();
/*    */       } else {
/* 45 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.YUKI.get();
/*    */       } 
/* 47 */       float rotation = world.func_201674_k().nextFloat() / 4.0F;
/*    */       
/* 49 */       SimpleParticleData part = new SimpleParticleData(particle);
/* 50 */       part.setLife(100);
/* 51 */       part.setSize(scale);
/* 52 */       part.setMotion(motionX, -0.05D, motionZ);
/* 53 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 54 */       part.setRotationSpeed(rotation);
/* 55 */       part.setHasMotionDecay(false);
/* 56 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Details
/*    */     extends ParticleEffect.Details
/*    */   {
/*    */     private int size;
/*    */     
/*    */     public Details() {}
/*    */     
/*    */     public Details(int size) {
/* 68 */       this.size = size;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 74 */       nbt.func_74768_a("size", this.size);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 80 */       this.size = nbt.func_74762_e("size");
/*    */     }
/*    */ 
/*    */     
/*    */     public int getSize() {
/* 85 */       return this.size;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yuki\KamakuraParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */