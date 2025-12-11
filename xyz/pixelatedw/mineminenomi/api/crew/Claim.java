/*    */ package xyz.pixelatedw.mineminenomi.api.crew;
/*    */ 
/*    */ import net.minecraft.util.RegistryKey;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class Claim extends ChunkPos {
/*    */   private ResourceLocation dimensionId;
/*    */   
/*    */   public Claim(ChunkPos pos, RegistryKey<World> dimension) {
/* 13 */     this(pos.func_201841_a(), dimension);
/*    */   }
/*    */   
/*    */   public Claim(long pos, RegistryKey<World> dimension) {
/* 17 */     super(pos);
/* 18 */     this.dimensionId = dimension.func_240901_a_();
/*    */   }
/*    */   
/*    */   public Claim(long pos, ResourceLocation dimensionId) {
/* 22 */     super(pos);
/* 23 */     this.dimensionId = dimensionId;
/*    */   }
/*    */   
/*    */   public Claim(BlockPos pos, RegistryKey<World> dimension) {
/* 27 */     super(pos);
/* 28 */     this.dimensionId = dimension.func_240901_a_();
/*    */   }
/*    */   
/*    */   public ResourceLocation getDimension() {
/* 32 */     return this.dimensionId;
/*    */   }
/*    */   
/*    */   public boolean isInside(World world, int posX, int posZ) {
/* 36 */     if (!world.func_234923_W_().func_240901_a_().equals(this.dimensionId)) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     if (posX > func_180334_c() && posX < func_180332_e() && posZ > func_180333_d() && posZ < func_180330_f()) {
/* 41 */       return true;
/*    */     }
/*    */     
/* 44 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\crew\Claim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */