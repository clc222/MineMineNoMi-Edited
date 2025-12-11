/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChargeMH5ParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     Vector3d spawnPoint = entity.func_70040_Z().func_216372_d(1.0D, 1.0D, 1.0D).func_72441_c(0.0D, entity.func_213302_cg() / 1.3D, 0.0D);
/*    */     
/* 18 */     double r = 0.8D; double phi;
/* 19 */     for (phi = 0.0D; phi <= 6.283185307179586D; phi += 0.39269908169872414D) {
/*    */       
/* 21 */       if (entity.field_70170_p.func_201674_k().nextInt(20) == 0) {
/*    */ 
/*    */ 
/*    */         
/* 25 */         double x = r * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
/* 26 */         double y = r * Math.cos(phi) + WyHelper.randomDouble() / 2.0D;
/* 27 */         double z = 0.0D;
/*    */         
/* 29 */         Vector3d vec = new Vector3d(x, y, z);
/*    */         
/* 31 */         vec = VectorHelper.rotateAroundX(vec, Math.toRadians(entity.field_70125_A));
/* 32 */         vec = VectorHelper.rotateAroundY(vec, Math.toRadians(-entity.field_70177_z));
/*    */         
/* 34 */         Vector3d spawnPos = entity.func_213303_ch().func_178787_e(spawnPoint.func_72441_c(0.0D, -(entity.func_213302_cg() / 1.3D), 0.0D)).func_178787_e(vec);
/* 35 */         Vector3d moveVec = entity.func_213303_ch().func_178788_d(spawnPos);
/*    */         
/* 37 */         double motionX = moveVec.field_72450_a * 0.03D;
/* 38 */         double motionY = moveVec.field_72448_b * 0.03D;
/* 39 */         double motionZ = moveVec.field_72449_c * 0.03D;
/*    */         
/* 41 */         int type = entity.field_70170_p.func_201674_k().nextInt(4);
/* 42 */         if (type <= 1) {
/* 43 */           SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 44 */           part.setLife(20);
/* 45 */           part.setSize(1.0F);
/* 46 */           part.setRotation(Vector3f.field_229181_d_);
/* 47 */           part.setMotion(motionX, motionY, motionZ);
/* 48 */           world.func_195594_a((IParticleData)part, posX + spawnPoint.field_72450_a + vec.field_72450_a, posY + spawnPoint.field_72448_b + vec.field_72448_b, posZ + spawnPoint.field_72449_c + vec.field_72449_c, 0.0D, 0.0D, 0.0D);
/*    */         }
/* 50 */         else if (type == 2) {
/* 51 */           SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.AWA_FOAM.get());
/* 52 */           part.setLife(20);
/* 53 */           part.setSize(1.5F);
/* 54 */           part.setColor(1.0F, 0.0F, 1.0F, 0.5F);
/* 55 */           part.setMotion(motionX, motionY, motionZ);
/* 56 */           part.setRotation(Vector3f.field_229183_f_);
/* 57 */           part.setRotationSpeed(0.1F);
/* 58 */           world.func_195594_a((IParticleData)part, posX + spawnPoint.field_72450_a + vec.field_72450_a, posY + spawnPoint.field_72448_b + vec.field_72448_b, posZ + spawnPoint.field_72449_c + vec.field_72449_c, 0.0D, 0.0D, 0.0D);
/*    */         }
/* 60 */         else if (type == 3) {
/* 61 */           SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/* 62 */           part.setLife(20);
/* 63 */           part.setSize(1.5F);
/* 64 */           part.setColor(1.0F, 0.0F, 1.0F, 0.5F);
/* 65 */           part.setMotion(motionX, motionY, motionZ);
/* 66 */           part.setRotation(Vector3f.field_229183_f_);
/* 67 */           part.setRotationSpeed(0.05F);
/* 68 */           world.func_195594_a((IParticleData)part, posX + spawnPoint.field_72450_a + vec.field_72450_a, posY + spawnPoint.field_72448_b + vec.field_72448_b, posZ + spawnPoint.field_72449_c + vec.field_72449_c, 0.0D, 0.0D, 0.0D);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\ChargeMH5ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */