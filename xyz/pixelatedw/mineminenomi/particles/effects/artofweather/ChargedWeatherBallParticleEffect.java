/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChargedWeatherBallParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   private float red;
/*    */   private float green;
/*    */   
/*    */   public ChargedWeatherBallParticleEffect(Color color, Supplier<ParticleType<SimpleParticleData>> particle) {
/* 19 */     this.red = color.getRed() / 255.0F;
/* 20 */     this.green = color.getGreen() / 255.0F;
/* 21 */     this.blue = color.getBlue() / 255.0F;
/* 22 */     this.particle = particle;
/*    */   }
/*    */   private float blue; private Supplier<ParticleType<SimpleParticleData>> particle;
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 27 */     for (int i = 0; i < 10; i++) {
/* 28 */       double offsetX = WyHelper.randomDouble() / 1.5D;
/* 29 */       double offsetY = WyHelper.randomDouble() / 1.5D;
/* 30 */       double offsetZ = WyHelper.randomDouble() / 1.5D;
/*    */       
/* 32 */       SimpleParticleData data = new SimpleParticleData(this.particle.get());
/* 33 */       data.setLife(4);
/* 34 */       data.setSize(2.0F);
/* 35 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 36 */       data.setColor(this.red, this.green, this.blue);
/* 37 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\ChargedWeatherBallParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */