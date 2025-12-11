/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.supa;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class AtomicSpurtParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 2; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 22 */       BlockState BlockState = world.func_180495_p((new BlockPos(posX, posY, posZ)).func_177977_b());
/*    */       
/* 24 */       world.func_195594_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, BlockState), posX + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\supa\AtomicSpurtParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */