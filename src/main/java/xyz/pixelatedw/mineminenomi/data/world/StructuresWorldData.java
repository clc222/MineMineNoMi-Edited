/*    */ package xyz.pixelatedw.mineminenomi.data.world;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3i;
/*    */ import net.minecraft.world.storage.WorldSavedData;
/*    */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*    */ 
/*    */ public class StructuresWorldData extends WorldSavedData {
/* 14 */   private Set<BlockPos> snowMountains = new HashSet<>(); private static final String IDENTIFIER = "mineminenomi-structures";
/* 15 */   private Set<BlockPos> hugeSabaodyTrees = new HashSet<>();
/*    */   
/*    */   public StructuresWorldData() {
/* 18 */     super("mineminenomi-structures");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static StructuresWorldData get() {
/* 27 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/* 28 */       return (StructuresWorldData)ServerLifecycleHooks.getCurrentServer().func_241755_D_().func_217481_x().func_215752_a(StructuresWorldData::new, "mineminenomi-structures");
/*    */     }
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76184_a(CompoundNBT nbt) {}
/*    */ 
/*    */   
/*    */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/* 38 */     return nbt;
/*    */   }
/*    */   
/*    */   public void addSnowMountain(BlockPos pos) {
/* 42 */     this.snowMountains.add(pos);
/*    */   }
/*    */   
/*    */   public boolean isNearSnowMountain(BlockPos pos, double radius) {
/* 46 */     for (BlockPos checkPos : this.snowMountains) {
/* 47 */       if (checkPos.func_177951_i((Vector3i)pos) < radius * radius) {
/* 48 */         return true;
/*    */       }
/*    */     } 
/* 51 */     return false;
/*    */   }
/*    */   
/*    */   public int countSnowMountains() {
/* 55 */     return this.snowMountains.size();
/*    */   }
/*    */   
/*    */   public void addHugeSabaodyTree(BlockPos pos) {
/* 59 */     this.hugeSabaodyTrees.add(pos);
/*    */   }
/*    */   
/*    */   public boolean isNearHugeSabaodyTree(BlockPos pos, double radius) {
/* 63 */     for (BlockPos checkPos : this.hugeSabaodyTrees) {
/* 64 */       if (checkPos.func_177951_i((Vector3i)pos) < radius * radius) {
/* 65 */         return true;
/*    */       }
/*    */     } 
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   public int countHugeSabaodyTree() {
/* 72 */     return this.hugeSabaodyTrees.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\StructuresWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */