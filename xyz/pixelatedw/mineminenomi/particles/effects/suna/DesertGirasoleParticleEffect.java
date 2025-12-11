/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DesertGirasoleParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 64; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/*    */       
/* 21 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 22 */       data.setLife((int)(80.0D + 0.2D * i));
/* 23 */       data.setSize(4.0F);
/* 24 */       data.setMotion(0.0D, 0.3D, 0.0D);
/* 25 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 0.5D - 0.15D * i, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertGirasoleParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */