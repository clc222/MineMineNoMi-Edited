/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.settings.PointOfView;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LogiaParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   private Supplier<ParticleType<SimpleParticleData>> particle;
/*    */   
/*    */   public LogiaParticleEffect(Supplier<ParticleType<SimpleParticleData>> particle) {
/* 19 */     this.particle = particle;
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 24 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 26 */     boolean isOwner = mc.field_71439_g.equals(entity);
/* 27 */     boolean isFirstPerson = (mc.field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/* 28 */     if (isOwner && isFirstPerson) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     for (int i = 0; i < WyHelper.randomWithRange(7, 12); i++) {
/* 33 */       double offsetX = WyHelper.randomDouble() / 1.7D;
/* 34 */       double offsetY = 0.25D + WyHelper.randomDouble() / 3.0D;
/* 35 */       double offsetZ = WyHelper.randomDouble() / 1.7D;
/*    */       
/* 37 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 4));
/* 38 */       double motionY = WyHelper.randomDouble() / 50.0D;
/* 39 */       if (motionY < 0.0D) {
/* 40 */         motionY = 0.005D;
/*    */       }
/* 42 */       SimpleParticleData part = new SimpleParticleData(this.particle.get());
/* 43 */       part.setLife(age);
/* 44 */       if (this.particle == ModParticleTypes.HIE) {
/* 45 */         part.setLife(7);
/* 46 */         part.setAnimationSpeed(1);
/*    */       } 
/* 48 */       part.setSize(1.5F);
/* 49 */       part.setMotion(0.0D, motionY, 0.0D);
/*    */       
/* 51 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\LogiaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */