/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class BlockPlacingHelper
/*    */ {
/*  9 */   private Set<BlockPos> blockList = new HashSet<>();
/*    */ 
/*    */   
/*    */   public void setBlockList(Set<BlockPos> list) {
/* 13 */     this.blockList = list;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addBlockPos(BlockPos pos) {
/* 18 */     this.blockList.add(new DistanceBlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addBlockPos(BlockPos pos, int hash) {
/* 23 */     this.blockList.add(new DistanceBlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), hash));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<BlockPos> getBlockList() {
/* 33 */     return this.blockList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearList() {
/* 38 */     this.blockList = new HashSet<>();
/*    */   }
/*    */   
/*    */   public static class DistanceBlockPos
/*    */     extends BlockPos
/*    */   {
/*    */     public int hash;
/*    */     
/*    */     public DistanceBlockPos(double x, double y, double z) {
/* 47 */       super(x, y, z);
/* 48 */       this.hash = (int)((y * 31.0D + z) * 17.0D + x);
/*    */     }
/*    */ 
/*    */     
/*    */     public DistanceBlockPos(double x, double y, double z, int hash) {
/* 53 */       super(x, y, z);
/* 54 */       this.hash = hash;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int hashCode() {
/* 60 */       return this.hash;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\BlockPlacingHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */