/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PhoenixGoenParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 15 */     float mult = 1.0F;
/* 16 */     if (entity instanceof AbilityProjectileEntity) {
/* 17 */       mult = ((AbilityProjectileEntity)entity).getLife() / ((AbilityProjectileEntity)entity).getMaxLife() * 1.25F;
/*    */     }
/* 19 */     for (int i = 0; i < 25.0F * mult; i++) {
/* 20 */       double offsetX = WyHelper.randomDouble() * mult;
/* 21 */       double offsetY = WyHelper.randomDouble() * mult;
/* 22 */       double offsetZ = WyHelper.randomDouble() * mult;
/*    */       
/* 24 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.BLUE_FLAME.get());
/* 25 */       data.setLife(8);
/* 26 */       data.setSize(3.0F * mult);
/* 27 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\PhoenixGoenParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */