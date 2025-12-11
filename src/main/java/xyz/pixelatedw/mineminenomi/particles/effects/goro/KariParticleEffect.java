/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.vector.Vector3f;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KariParticleEffect extends ParticleEffect<KariParticleEffect.Details> {
/*    */   public KariParticleEffect() {
/* 17 */     super(Details::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 22 */     ParticleType<SimpleParticleData> goro_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO_YELLOW.get();
/* 23 */     ParticleType<SimpleParticleData> goro2_particle = ClientConfig.INSTANCE.isGoroBlue() ? (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2.get() : (ParticleType<SimpleParticleData>)ModParticleTypes.GORO2_YELLOW.get();
/*    */     
/* 25 */     for (int i = 0; i < 16 * details.getRange(); i++) {
/* 26 */       double offsetX = WyHelper.randomWithRange(-details.getRange(), details.getRange()) + WyHelper.randomDouble();
/* 27 */       double offsetY = WyHelper.randomWithRange(-details.getRange(), 2 + details.getRange()) + WyHelper.randomDouble();
/* 28 */       double offsetZ = WyHelper.randomWithRange(-details.getRange(), details.getRange()) + WyHelper.randomDouble();
/*    */       
/* 30 */       SimpleParticleData data = new SimpleParticleData(goro2_particle);
/* 31 */       data.setLife(5);
/* 32 */       data.setSize(details.getSize());
/* 33 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */       
/* 35 */       SimpleParticleData data2 = new SimpleParticleData(goro_particle);
/* 36 */       data2.setLife(5);
/* 37 */       data2.setSize(details.getSize());
/* 38 */       data.setRotation(Vector3f.field_229181_d_);
/* 39 */       world.func_195590_a((IParticleData)data, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Details
/*    */     extends ParticleEffect.Details {
/*    */     private int range;
/*    */     private float size;
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 49 */       nbt.func_74768_a("range", this.range);
/* 50 */       nbt.func_74776_a("size", this.size);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 55 */       this.range = nbt.func_74762_e("range");
/* 56 */       this.size = nbt.func_74760_g("size");
/*    */     }
/*    */     
/*    */     public int getRange() {
/* 60 */       return this.range;
/*    */     }
/*    */     
/*    */     public float getSize() {
/* 64 */       return this.size;
/*    */     }
/*    */     
/*    */     public void setRange(int range) {
/* 68 */       this.range = range;
/*    */     }
/*    */     
/*    */     public void setSize(float range) {
/* 72 */       this.size = range;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\KariParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */