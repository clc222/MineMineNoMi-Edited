/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class SpecialFlyingParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   private Supplier<ParticleType<SimpleParticleData>> particle;
/*    */   
/*    */   public SpecialFlyingParticleEffect(Supplier<ParticleType<SimpleParticleData>> particle) {
/* 18 */     this.particle = particle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 24 */     for (int i = 0; i < WyHelper.randomWithRange(5, 10); i++) {
/*    */       
/* 26 */       double offsetX = WyHelper.randomDouble() / 1.7D;
/* 27 */       double offsetY = -0.55D + WyHelper.randomDouble() / 3.0D;
/* 28 */       double offsetZ = WyHelper.randomDouble() / 1.7D;
/*    */       
/* 30 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 20));
/* 31 */       double motionY = WyHelper.randomDouble() / 10.0D * -1.0D;
/* 32 */       if (motionY > 0.0D) {
/* 33 */         motionY = -0.005D;
/*    */       }
/* 35 */       SimpleParticleData data = new SimpleParticleData(this.particle.get());
/* 36 */       data.setLife(age);
/* 37 */       data.setSize(2.5F);
/* 38 */       data.setMotion(0.0D, motionY, 0.0D);
/* 39 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\SpecialFlyingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */