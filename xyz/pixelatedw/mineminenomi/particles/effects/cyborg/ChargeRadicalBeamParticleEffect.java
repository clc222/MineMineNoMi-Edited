/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.cyborg;
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
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChargeRadicalBeamParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     Vector3d spawnPoint = entity.func_70040_Z().func_216372_d(1.0D, 1.0D, 1.0D).func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*    */     
/* 19 */     double r = 0.8D; double phi;
/* 20 */     for (phi = 0.0D; phi <= 6.283185307179586D; phi += 0.39269908169872414D) {
/*    */       
/* 22 */       if (entity.field_70170_p.func_201674_k().nextInt(20) == 0) {
/*    */ 
/*    */ 
/*    */         
/* 26 */         double x = r * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
/* 27 */         double y = r * Math.cos(phi) + WyHelper.randomDouble() / 2.0D;
/* 28 */         double z = 0.0D;
/*    */         
/* 30 */         Vector3d vec = new Vector3d(x, y, z);
/*    */         
/* 32 */         vec = VectorHelper.rotateAroundX(vec, Math.toRadians(entity.field_70125_A));
/* 33 */         vec = VectorHelper.rotateAroundY(vec, Math.toRadians(-entity.field_70177_z));
/*    */         
/* 35 */         Vector3d spawnPos = entity.func_213303_ch().func_178787_e(spawnPoint.func_72441_c(0.0D, -entity.func_70047_e(), 0.0D)).func_178787_e(vec);
/* 36 */         Vector3d moveVec = entity.func_213303_ch().func_178788_d(spawnPos);
/*    */         
/* 38 */         double motionX = moveVec.field_72450_a * 0.03D;
/* 39 */         double motionY = moveVec.field_72448_b * 0.03D;
/* 40 */         double motionZ = moveVec.field_72449_c * 0.03D;
/*    */         
/* 42 */         SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.PIKA.get());
/* 43 */         part.setLife(20);
/* 44 */         part.setSize(1.0F);
/* 45 */         part.setRotation(Vector3f.field_229181_d_);
/* 46 */         part.setMotion(motionX, motionY, motionZ);
/* 47 */         world.func_195590_a((IParticleData)part, true, posX + spawnPoint.field_72450_a + vec.field_72450_a, posY + spawnPoint.field_72448_b + vec.field_72448_b, posZ + spawnPoint.field_72449_c + vec.field_72449_c, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\cyborg\ChargeRadicalBeamParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */