/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
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
/*    */ public class BlueFlamesParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 19 */       double offsetY = WyHelper.randomDouble() / 1.25D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 22 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BLUE_FLAME.get());
/* 23 */       data.setLife(10);
/* 24 */       data.setSize(2.5F);
/* 25 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\BlueFlamesParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */