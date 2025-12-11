/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.brawler;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KingPunchChargeParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 23 */     int i = 0;
/* 24 */     double t = 0.0D;
/*    */     
/* 26 */     Random rand = world.field_73012_v;
/*    */     
/* 28 */     while (t < 1.0D) {
/*    */       
/* 30 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 32 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 34 */         double x = t * Math.cos(theta);
/* 35 */         double y = rand.nextInt(1);
/* 36 */         double z = t * Math.sin(theta);
/*    */         
/* 38 */         double motionX = -x / 20.0D;
/* 39 */         double motionY = 0.05D + rand.nextDouble() / 10.0D;
/* 40 */         double motionZ = -z / 20.0D;
/*    */         
/* 42 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU.get());
/*    */         
/* 44 */         if (i % 2 == 0) {
/* 45 */           data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU2.get());
/*    */         }
/* 47 */         double offsetX = world.field_73012_v.nextDouble() * WyHelper.randomWithRange(7, 9);
/* 48 */         double offsetY = 1.0D;
/* 49 */         double offsetZ = world.field_73012_v.nextDouble() * WyHelper.randomWithRange(7, 9);
/*    */         
/* 51 */         BlockState blockState = world.func_180495_p((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).func_177977_b());
/*    */         
/* 53 */         BlockParticleData blockParticle = new BlockParticleData(ParticleTypes.field_197611_d, blockState);
/* 54 */         world.func_195594_a((IParticleData)blockParticle, posX - 4.0D + offsetX, posY - 1.0D + offsetY, posZ - 4.0D + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */         
/* 56 */         data.setLife(1);
/* 57 */         data.setSize(1.0F);
/* 58 */         data.setMotion(motionX, motionY, motionZ);
/* 59 */         world.func_195590_a((IParticleData)data, true, posX + x * 1.25D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.25D + WyHelper.randomDouble() * 2.0D, 0.0D, 0.0D, 0.0D);
/*    */         
/* 61 */         data.setLife(3);
/* 62 */         data.setSize(1.0F);
/* 63 */         data.setMotion(motionX, motionY + 0.001D, motionZ);
/* 64 */         world.func_195590_a((IParticleData)data, true, posX + x * 1.5D + WyHelper.randomDouble(), posY + y, posZ + z * 1.5D + WyHelper.randomDouble(), 0.0D, 0.0D, 0.0D);
/*    */         
/* 66 */         data.setLife(5);
/* 67 */         data.setSize(1.0F);
/* 68 */         data.setMotion(motionX, motionY + 0.005D, motionZ);
/* 69 */         world.func_195590_a((IParticleData)data, true, posX + x * 1.75D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.75D + WyHelper.randomDouble() * 2.0D, 0.0D, 0.0D, 0.0D);
/*    */         
/* 71 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\brawler\KingPunchChargeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */