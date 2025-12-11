/*    */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ShockwaveParticleEffect extends ParticleEffect<ShockwaveParticleEffect.Details> {
/*    */   public ShockwaveParticleEffect() {
/* 14 */     super(Details::new);
/*    */   }
/*    */ 
/*    */   
/*    */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/* 19 */     int sizeOffset = details.getSize();
/* 20 */     for (int x = -sizeOffset; x < sizeOffset; x++) {
/* 21 */       for (int z = -sizeOffset; z < sizeOffset; z++) {
/* 22 */         for (int i = 0; i < 60; i++) {
/* 23 */           double y = WyHelper.randomDouble() / 2.0D;
/*    */           
/* 25 */           BlockState blockState = world.func_180495_p((new BlockPos(posX + x, posY, posZ + z)).func_177977_b());
/* 26 */           BlockParticleData particleData = new BlockParticleData(ParticleTypes.field_197611_d, blockState);
/*    */           
/* 28 */           world.func_195590_a((IParticleData)particleData, true, posX + WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, posY + y + 0.5D, posZ + WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, 0.0D, 0.0D, 0.0D);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class Details extends ParticleEffect.Details {
/*    */     private int size;
/*    */     
/*    */     public Details() {}
/*    */     
/*    */     public Details(int size) {
/* 40 */       this.size = size;
/*    */     }
/*    */ 
/*    */     
/*    */     public void save(CompoundNBT nbt) {
/* 45 */       nbt.func_74768_a("size", this.size);
/*    */     }
/*    */ 
/*    */     
/*    */     public void load(CompoundNBT nbt) {
/* 50 */       this.size = nbt.func_74762_e("size");
/*    */     }
/*    */     
/*    */     public int getSize() {
/* 54 */       return this.size;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\ShockwaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */