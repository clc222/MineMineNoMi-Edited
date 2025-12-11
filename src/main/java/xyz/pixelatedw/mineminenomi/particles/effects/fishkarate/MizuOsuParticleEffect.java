/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.EasingDirection;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class MizuOsuParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails>
/*    */ {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 18 */     Vector3d lookVec1 = entity.func_70040_Z();
/*    */     
/* 20 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.SIMPLE_CIRCLE.get());
/* 21 */     data.setFunction(EasingFunction.SINE_IN_OUT);
/* 22 */     data.setEaseDirection(EasingDirection.POSITIVE);
/* 23 */     data.setEaseStrength(10.0F);
/* 24 */     data.setLookVec(entity.field_70125_A, entity.field_70177_z);
/* 25 */     data.setColor(0.2F, 0.7F, 0.7F, 0.75F);
/*    */     
/* 27 */     data.setLife(12);
/* 28 */     data.setSize(1.0F);
/* 29 */     data.setMotion(lookVec1.field_72450_a / 9.0D, 0.0D, lookVec1.field_72449_c / 9.0D);
/* 30 */     world.func_195590_a((IParticleData)data, true, lookVec1.field_72450_a + posX, lookVec1.field_72448_b + posY, lookVec1.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */     
/* 32 */     data.setLife(10);
/* 33 */     data.setSize(2.0F);
/* 34 */     data.setMotion(lookVec1.field_72450_a / 7.0D, 0.0D, lookVec1.field_72449_c / 7.0D);
/* 35 */     world.func_195590_a((IParticleData)data, true, lookVec1.field_72450_a + posX, lookVec1.field_72448_b + posY, lookVec1.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */     
/* 37 */     data.setLife(8);
/* 38 */     data.setSize(3.0F);
/* 39 */     data.setMotion(lookVec1.field_72450_a / 5.0D, 0.0D, lookVec1.field_72449_c / 5.0D);
/* 40 */     world.func_195590_a((IParticleData)data, true, lookVec1.field_72450_a + posX, lookVec1.field_72448_b + posY, lookVec1.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\fishkarate\MizuOsuParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */