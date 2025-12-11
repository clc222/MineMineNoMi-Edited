/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GroundParticlesEffect
/*    */   extends ParticleEffect {
/*    */   private int offset;
/*    */   private int amount;
/*    */   
/*    */   public GroundParticlesEffect(int offset, int amount) {
/* 18 */     this.offset = offset;
/* 19 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 25 */     for (int i = 0; i < this.amount; i++) {
/*    */       
/* 27 */       double offsetX = WyHelper.randomWithRange(-this.offset, this.offset) + WyHelper.randomDouble();
/* 28 */       double offsetZ = WyHelper.randomWithRange(-this.offset, this.offset) + WyHelper.randomDouble();
/*    */       
/* 30 */       for (int j = 0; j < 2; j++) {
/*    */         
/* 32 */         BlockState blockState = world.func_180495_p((new BlockPos(posX + offsetX, posY - j, posZ + offsetZ)).func_177977_b());
/* 33 */         ((ServerWorld)world).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState), posX + offsetX, posY, posZ + offsetZ, 1, 0.0D, 0.5D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\GroundParticlesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */