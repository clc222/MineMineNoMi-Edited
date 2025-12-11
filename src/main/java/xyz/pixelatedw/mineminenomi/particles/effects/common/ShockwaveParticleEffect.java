/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ShockwaveParticleEffect
/*    */   extends ParticleEffect {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 19 */     for (int i = 0; i < 120; i++) {
/*    */       SimpleParticleData data;
/* 21 */       double y = WyHelper.randomDouble() / 2.0D;
/* 22 */       BlockState blockState = world.func_180495_p(new BlockPos(posX, posY, posZ));
/* 23 */       ((ServerWorld)world).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState), posX + 
/* 24 */           WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, posY + y + 0.5D, posZ + 
/*    */           
/* 26 */           WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */ 
/*    */       
/* 29 */       double offsetX = WyHelper.randomDouble() * 2.0D;
/* 30 */       double offsetY = WyHelper.randomDouble() * 2.0D;
/* 31 */       double offsetZ = WyHelper.randomDouble() * 2.0D;
/*    */ 
/*    */       
/* 34 */       if (i % 5 == 0) {
/* 35 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU2.get());
/*    */       } else {
/* 37 */         data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/* 38 */       }  data.setMotion(-offsetX / 20.0D, 0.015D, -offsetZ / 20.0D);
/* 39 */       data.setLife(10);
/* 40 */       data.setSize(1.0F);
/* 41 */       WyHelper.spawnParticles((IParticleData)data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\ShockwaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */