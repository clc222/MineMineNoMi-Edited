/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.extra;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MH5GasParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     for (int i = 0; i < 2048; i++) {
/* 18 */       double offsetX = WyHelper.randomWithRange(-100, 100) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(0, 20) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-100, 100) + WyHelper.randomDouble();
/*    */       
/* 22 */       int age = (int)(10.0D + WyHelper.randomWithRange(0, 15));
/* 23 */       double motionY = WyHelper.randomDouble() / 50.0D;
/* 24 */       if (motionY < 0.0D) {
/* 25 */         motionY = 0.02D;
/*    */       }
/*    */       
/* 28 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.DOKU.get());
/*    */       
/* 30 */       if (world.func_201674_k().nextInt(3) == 0) {
/* 31 */         part = new SimpleParticleData((ParticleType)ModParticleTypes.AWA_FOAM.get());
/* 32 */         part.setColor(1.0F, 0.0F, 1.0F, 0.5F);
/*    */       } 
/*    */       
/* 35 */       part.setLife(age);
/* 36 */       part.setSize(2.5F);
/* 37 */       part.setMotion(0.0D, motionY / 2.0D, 0.0D);
/* 38 */       part.setRotation(entity.field_70170_p.func_201674_k().nextBoolean() ? Vector3f.field_229183_f_ : Vector3f.field_229182_e_);
/* 39 */       part.setRotationSpeed(0.05F);
/*    */       
/* 41 */       world.func_195594_a((IParticleData)part, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\extra\MH5GasParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */