/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DiableJambeParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     if (entity instanceof LivingEntity) {
/* 18 */       LivingEntity living = (LivingEntity)entity;
/*    */       
/* 20 */       float f = living.field_70760_ar + living.field_70761_aq - living.field_70760_ar + 30.0F;
/* 21 */       double x = MathHelper.func_76126_a(f * 3.1415927F / -180.0F) * 0.2D;
/* 22 */       double z = MathHelper.func_76134_b(f * 3.1415927F / -180.0F) * 0.2D;
/*    */       
/* 24 */       double offsetX = x + WyHelper.randomDouble() / 5.0D;
/* 25 */       double offsetY = 0.6D + WyHelper.randomDouble() / 2.5D;
/* 26 */       double offsetZ = z + WyHelper.randomDouble() / 5.0D;
/*    */       
/* 28 */       int age = (int)(3.0D + WyHelper.randomWithRange(0, 2));
/*    */       
/* 30 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/* 31 */       data.setLife(age);
/* 32 */       data.setSize(age / 2.5F);
/* 33 */       data.setMotion(0.0D, 0.002D, 0.0D);
/*    */       
/* 35 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\DiableJambeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */