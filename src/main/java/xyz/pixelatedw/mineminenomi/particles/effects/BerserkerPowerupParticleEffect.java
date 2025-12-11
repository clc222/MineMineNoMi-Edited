/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BerserkerPowerupParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     Vector3d playerPos = new Vector3d(posX, posY, posZ);
/*    */     
/* 18 */     double r = 2.0D; double phi;
/* 19 */     for (phi = 0.0D; phi <= 6.283185307179586D; phi += 0.09817477042468103D) {
/*    */       
/* 21 */       double x = r * Math.cos(phi) + WyHelper.randomDouble() / 5.0D;
/* 22 */       double z = r * Math.sin(phi) + WyHelper.randomDouble() / 5.0D;
/*    */       
/* 24 */       Vector3d pos = playerPos.func_178787_e(new Vector3d(x, posY, z));
/* 25 */       Vector3d dirVec = playerPos.func_178788_d(pos).func_72432_b().func_216372_d(1.25D, 1.0D, 1.25D);
/*    */       
/* 27 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 28 */       part.setLife(15);
/* 29 */       part.setSize(7.0F);
/* 30 */       part.setMotion(-dirVec.field_72450_a, 0.0D, -dirVec.field_72449_c);
/* 31 */       part.setRotation(0.0F, 0.0F, 1.0F);
/* 32 */       part.setRotationSpeed(1.0F);
/* 33 */       world.func_195590_a((IParticleData)part, true, posX + x, posY + 0.3D, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */       
/* 35 */       part = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU2.get());
/* 36 */       part.setLife(10);
/* 37 */       part.setSize(5.0F);
/* 38 */       part.setMotion(-dirVec.field_72450_a, 0.0D, -dirVec.field_72449_c);
/* 39 */       world.func_195590_a((IParticleData)part, true, posX + x, posY + 0.3D, posZ + z, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\BerserkerPowerupParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */