/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackHoleParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     double motionX = WyHelper.randomDouble() / 5.0D;
/* 16 */     double motionY = 0.05D + MathHelper.func_76135_e((float)WyHelper.randomDouble() / 12.0F);
/* 17 */     double motionZ = WyHelper.randomDouble() / 5.0D;
/*    */     
/* 19 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DARKNESS.get());
/* 20 */     data.setLife(21);
/* 21 */     data.setAnimationSpeed(3);
/* 22 */     data.setSize(20.0F);
/* 23 */     data.setMotion(motionX, motionY, motionZ);
/* 24 */     data.setRotation(0.0F, 0.0F, 1.0F);
/* 25 */     world.func_195590_a((IParticleData)data, true, posX + WyHelper.randomDouble(), posY, posZ + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\BlackHoleParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */