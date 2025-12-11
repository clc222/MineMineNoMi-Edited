/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gasu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KoroParticleEffect extends ParticleEffect<KoroParticleEffect.Details> {
/*    */   public KoroParticleEffect() {
/* 16 */     super(Details::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 21 */     for (int i = 0; i < 25; i++) {
/* 22 */       double offsetX = WyHelper.randomDouble() / 1.3D;
/* 23 */       double offsetY = WyHelper.randomDouble() / 1.3D;
/* 24 */       double offsetZ = WyHelper.randomDouble() / 1.3D;
/*    */       
/* 26 */       Vector3d targetPos = new Vector3d(posX, posY, posZ);
/*    */       
/* 28 */       Vector3d dir = targetPos.func_178788_d(entity.func_213303_ch()).func_72432_b();
/*    */       
/* 30 */       int color = details.effect.func_188419_a().func_76401_j();
/* 31 */       float r = (color >> 16 & 0xFF) / 255.0F;
/* 32 */       float g = (color >> 8 & 0xFF) / 255.0F;
/* 33 */       float b = (color >> 0 & 0xFF) / 255.0F;
/*    */       
/* 35 */       SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MOKU2.get());
/* 36 */       data.setLife(10);
/* 37 */       data.setSize(1.3F);
/* 38 */       data.setMotion(-dir.field_72450_a / 5.0D, -dir.field_72448_b / 5.0D, -dir.field_72449_c / 5.0D);
/* 39 */       data.setRotation(0.0F, 0.0F, 1.0F);
/* 40 */       data.setColor(r, g, b);
/* 41 */       world.func_195590_a((IParticleData)data, true, targetPos.field_72450_a + offsetX, targetPos.field_72448_b + 1.0D + offsetY, targetPos.field_72449_c + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Details
/*    */     extends ParticleEffect.Details {
/*    */     private EffectInstance effect;
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 50 */       this.effect.func_82719_a(nbt);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 55 */       this.effect = EffectInstance.func_82722_b(nbt);
/*    */     }
/*    */     
/*    */     public void setEffect(EffectInstance effect) {
/* 59 */       this.effect = effect;
/*    */     }
/*    */     
/*    */     public EffectInstance getEffect() {
/* 63 */       return this.effect;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gasu\KoroParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */