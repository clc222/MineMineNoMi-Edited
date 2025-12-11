/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.sabi;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RustBreakParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 55; i++) {
/* 16 */       double offsetX = 0.5D + WyHelper.randomDouble() / 2.0D;
/* 17 */       double offsetY = 0.5D + WyHelper.randomDouble() / 2.0D;
/* 18 */       double offsetZ = 0.5D + WyHelper.randomDouble() / 2.0D;
/*    */       
/* 20 */       double motionX = world.func_201674_k().nextGaussian() * 0.02D;
/* 21 */       double motionY = world.func_201674_k().nextGaussian() * 0.02D;
/* 22 */       double motionZ = world.func_201674_k().nextGaussian() * 0.02D;
/*    */       
/* 24 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.RUST.get());
/* 25 */       data.setMotion(motionX, motionY, motionZ);
/* 26 */       data.setLife(30);
/* 27 */       data.setSize(4.0F);
/*    */       
/* 29 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/* 30 */       world.func_195590_a((IParticleData)ParticleTypes.field_197598_I, true, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\sabi\RustBreakParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */