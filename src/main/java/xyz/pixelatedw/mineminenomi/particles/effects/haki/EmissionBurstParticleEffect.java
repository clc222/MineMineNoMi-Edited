/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.haki;
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
/*    */ public class EmissionBurstParticleEffect extends ParticleEffect<ParticleEffect.NoDetails> {
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, ParticleEffect.NoDetails details) {
/* 16 */     Vector3d lookVec = entity.func_70040_Z().func_186678_a(1.6D);
/* 17 */     Vector3d lookVec2 = entity.func_70040_Z().func_186678_a(1.4D);
/*    */     
/* 19 */     SimpleParticleData part = new SimpleParticleData((ParticleType)ModParticleTypes.DOUBLE_CIRCLE.get());
/* 20 */     part.setFunction(EasingFunction.ELASTIC_OUT);
/* 21 */     part.setEaseDirection(EasingDirection.POSITIVE);
/* 22 */     part.setLookVec(entity.field_70125_A, entity.field_70177_z);
/* 23 */     part.setColor(0.9F, 0.7F, 0.15F, 0.25F);
/*    */     
/* 25 */     Vector3d moveVec = entity.func_70040_Z().func_72432_b().func_216372_d(0.12D, 0.12D, 0.12D);
/*    */     
/* 27 */     part.setLife(15);
/* 28 */     part.setSize(7.0F);
/* 29 */     part.setMotion(moveVec.field_72450_a, moveVec.field_72448_b, moveVec.field_72449_c);
/* 30 */     world.func_195590_a((IParticleData)part, true, lookVec.field_72450_a + posX, lookVec.field_72448_b + posY, lookVec.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */     
/* 32 */     part.setLife(14);
/* 33 */     part.setSize(4.0F);
/* 34 */     part.setMotion(moveVec.field_72450_a, moveVec.field_72448_b, moveVec.field_72449_c);
/* 35 */     world.func_195590_a((IParticleData)part, true, lookVec2.field_72450_a + posX, lookVec2.field_72448_b + posY, lookVec2.field_72449_c + posZ, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\haki\EmissionBurstParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */