/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.zou;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GreatStompParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     double phi = 0.0D;
/*    */ 
/*    */     
/* 21 */     while (phi < 10.0D) {
/* 22 */       phi += 0.3141592653589793D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/* 25 */         double x = phi * Math.cos(theta);
/* 26 */         double y = WyHelper.randomDouble();
/* 27 */         double z = phi * Math.sin(theta);
/*    */         
/* 29 */         BlockState blockState = world.func_180495_p((new BlockPos(posX, posY, posZ)).func_177977_b());
/*    */         
/* 31 */         if (blockState.func_185904_a() == Material.field_151579_a) {
/* 32 */           blockState = Blocks.field_150346_d.func_176223_P();
/*    */         }
/* 34 */         BlockParticleData particleData = new BlockParticleData(ParticleTypes.field_197611_d, blockState);
/* 35 */         world.func_195590_a((IParticleData)particleData, true, posX + WyHelper.randomWithRange(-3, 3) + x, posY + y, posZ + WyHelper.randomWithRange(-3, 3) + z, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\zou\GreatStompParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */