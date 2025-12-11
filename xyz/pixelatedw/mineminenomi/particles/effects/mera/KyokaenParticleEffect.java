/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
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
/*    */ public class KyokaenParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 1.2D;
/* 19 */       double offsetY = WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomDouble() / 1.2D;
/*    */       
/* 22 */       SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 23 */       part.setLife(5);
/* 24 */       part.setSize(1.0F);
/* 25 */       part.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 26 */       part.setMotion(0.0D, 0.05D, 0.0D);
/* 27 */       part.setHasScaleDecay(true);
/* 28 */       world.func_195590_a((IParticleData)part, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\KyokaenParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */