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
/*    */ public class MizuTaihoParticleEffect
/*    */   extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 17 */     Vector3d lookVec1 = entity.func_70040_Z().func_186678_a(2.0D);
/* 18 */     Vector3d lookVec2 = entity.func_70040_Z().func_186678_a(3.0D);
/* 19 */     Vector3d lookVec3 = entity.func_70040_Z().func_186678_a(4.0D);
/*    */     
/* 21 */     SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.DOUBLE_CIRCLE.get());
/* 22 */     data.setFunction(EasingFunction.ELASTIC_IN_OUT);
/* 23 */     data.setEaseDirection(EasingDirection.NEGATIVE);
/* 24 */     data.setLookVec(entity.field_70125_A, entity.field_70177_z);
/* 25 */     data.setColor(0.2F, 0.7F, 0.7F, 0.75F);
/*    */     
/* 27 */     data.setLife(30);
/* 28 */     data.setSize(30.0F);
/* 29 */     world.func_195590_a((IParticleData)data, true, lookVec3.field_72450_a + posX, lookVec3.field_72448_b + posY, lookVec3.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */     
/* 31 */     data.setLife(20);
/* 32 */     data.setSize(20.0F);
/* 33 */     world.func_195590_a((IParticleData)data, true, lookVec2.field_72450_a + posX, lookVec2.field_72448_b + posY, lookVec2.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */     
/* 35 */     data.setLife(10);
/* 36 */     data.setSize(10.0F);
/* 37 */     world.func_195590_a((IParticleData)data, true, lookVec1.field_72450_a + posX, lookVec1.field_72448_b + posY, lookVec1.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\fishkarate\MizuTaihoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */