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
/*    */ public class DesertSpadaParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     for (int i = 0; i < 70; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() * 0.5D;
/* 19 */       double offsetZ = WyHelper.randomDouble() * 0.5D;
/*    */       
/* 21 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SUNA2.get());
/* 22 */       data.setLife(20);
/* 23 */       data.setSize(1.5F);
/* 24 */       data.setMotion((entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b + 0.25D + Math.abs(WyHelper.randomDouble()) / 7.0D, (entity.func_213322_ci()).field_72449_c);
/* 25 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/* 26 */       data.setLife(60);
/* 27 */       data.setMotion(0.0D, 0.05D + Math.abs(WyHelper.randomDouble()) / 8.0D, 0.0D);
/* 28 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertSpadaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */