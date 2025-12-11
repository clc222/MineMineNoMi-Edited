/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
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
/*    */ public class BienCuitGrillShotParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     double motionX = (entity.func_213322_ci()).field_72450_a;
/* 17 */     double motionY = (entity.func_213322_ci()).field_72448_b;
/* 18 */     double motionZ = (entity.func_213322_ci()).field_72449_c;
/*    */     
/* 20 */     for (int k = -2; k <= 2; k++) {
/*    */       
/* 22 */       for (int i = -10; i <= 10; i++) {
/*    */         
/* 24 */         for (int j = 0; j < 3; j++) {
/*    */           
/* 26 */           double offsetX = WyHelper.randomDouble() / 2.0D;
/* 27 */           double offsetY = WyHelper.randomDouble() / 2.0D;
/* 28 */           double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */           
/* 30 */           SimpleParticleData simpleParticleData = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 31 */           simpleParticleData.setLife(10);
/* 32 */           simpleParticleData.setSize(1.0F);
/* 33 */           simpleParticleData.setMotion(motionX / 1.7D, motionY / 1.7D, motionZ / 1.7D);
/* 34 */           world.func_195590_a((IParticleData)simpleParticleData, true, posX - (i / 5) + offsetX, posY + 1.5D + k / 1.2D + offsetY, posZ - (i / 5) + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 39 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.GRILL.get());
/* 40 */     data.setLife(10);
/* 41 */     data.setSize(40.0F);
/* 42 */     data.setMotion(motionX / 1.4D, motionY / 1.4D, motionZ / 1.4D);
/* 43 */     world.func_195590_a((IParticleData)data, true, posX, posY + 1.5D, posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\BienCuitGrillShotParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */