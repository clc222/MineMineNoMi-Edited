/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.netsu;
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
/*    */ public class NetsuEnhancementParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     for (int i = 0; i < 8; i++) {
/* 16 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 17 */       double offsetY = WyHelper.randomDouble() / 1.25D;
/* 18 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 20 */       ParticleType<SimpleParticleData> particle = (ParticleType<SimpleParticleData>)ModParticleTypes.NETSU.get();
/* 21 */       if (i % 3 == 0)
/* 22 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.NETSU2.get(); 
/* 23 */       if (i % 7 == 0) {
/* 24 */         particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MERA.get();
/*    */       }
/* 26 */       SimpleParticleData data = new SimpleParticleData(particle);
/* 27 */       data.setLife(10);
/* 28 */       data.setSize(1.3F);
/* 29 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\netsu\NetsuEnhancementParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */