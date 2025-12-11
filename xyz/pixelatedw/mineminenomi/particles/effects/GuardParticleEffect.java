/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GuardParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 13 */     for (int i = 0; i < 25; i++) {
/*    */       
/* 15 */       double x = posX + WyHelper.randomDouble() / 1.2D;
/* 16 */       double y = posY + WyHelper.randomDouble() / 1.2D;
/* 17 */       double z = posZ + WyHelper.randomDouble() / 1.2D;
/*    */       
/* 19 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197614_g, (ServerWorld)world, x, y + 1.0D, z);
/* 20 */       WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197622_o, (ServerWorld)world, x, y + 1.0D, z);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\GuardParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */