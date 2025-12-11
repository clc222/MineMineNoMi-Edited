/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ public class SphereThread
/*    */   extends Thread
/*    */ {
/*    */   public boolean isComplete = false;
/*    */   public final BlockPos center;
/*    */   public final int radius;
/* 12 */   public HashSet<BlockPos> blockList = new HashSet<>();
/*    */   
/*    */   public final boolean hollow;
/*    */   
/*    */   public SphereThread(BlockPos pos, int radius, boolean hollow) {
/* 17 */     this.radius = radius;
/* 18 */     this.center = pos;
/* 19 */     this.hollow = hollow;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 25 */     super.run();
/*    */     
/* 27 */     int bx = this.center.func_177958_n();
/* 28 */     int by = this.center.func_177956_o();
/* 29 */     int bz = this.center.func_177952_p();
/*    */     
/* 31 */     for (int x = bx - this.radius; x <= bx + this.radius; x++) {
/*    */       
/* 33 */       for (int y = by - this.radius; y <= by + this.radius; y++) {
/*    */         
/* 35 */         for (int z = bz - this.radius; z <= bz + this.radius; z++) {
/*    */           
/* 37 */           double distance = ((bx - x) * (bx - x) + (bz - z) * (bz - z) + (by - y) * (by - y));
/*    */           
/* 39 */           if (distance < (this.radius * this.radius) && (!this.hollow || distance >= ((this.radius - 1) * (this.radius - 1)))) {
/* 40 */             this.blockList.add(new BlockPos(x, y, z));
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 45 */     this.isComplete = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\SphereThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */