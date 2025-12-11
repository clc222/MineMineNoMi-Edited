/*     */ package xyz.pixelatedw.mineminenomi.data.world;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.fml.server.ServerLifecycleHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ 
/*     */ public class ProtectedAreasData
/*     */   extends WorldSavedData
/*     */ {
/*     */   private static final String IDENTIFIER = "mineminenomi-protected-areas";
/*  21 */   private HashMap<String, ProtectedArea> abilityProtections = new HashMap<>();
/*     */   
/*     */   public ProtectedAreasData() {
/*  24 */     super("mineminenomi-protected-areas");
/*     */   }
/*     */   
/*     */   public ProtectedAreasData(String identifier) {
/*  28 */     super(identifier);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ProtectedAreasData get(World level) {
/*  34 */     if (ServerLifecycleHooks.getCurrentServer() != null) {
/*  35 */       return (ProtectedAreasData)ServerLifecycleHooks.getCurrentServer().func_71218_a(level.func_234923_W_()).func_217481_x().func_215752_a(ProtectedAreasData::new, "mineminenomi-protected-areas");
/*     */     }
/*  37 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189551_b(CompoundNBT nbt) {
/*  43 */     ListNBT protectedAreas = new ListNBT();
/*  44 */     if (this.abilityProtections.size() > 0) {
/*  45 */       for (Map.Entry<String, ProtectedArea> entry : this.abilityProtections.entrySet()) {
/*  46 */         CompoundNBT entryNBT = ((ProtectedArea)entry.getValue()).save();
/*  47 */         protectedAreas.add(entryNBT);
/*     */       } 
/*     */     }
/*  50 */     nbt.func_218657_a("protectedAreas", (INBT)protectedAreas);
/*     */     
/*  52 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76184_a(CompoundNBT nbt) {
/*  57 */     ListNBT protectedAreas = nbt.func_150295_c("protectedAreas", 10);
/*  58 */     this.abilityProtections.clear();
/*  59 */     for (int i = 0; i < protectedAreas.size(); i++) {
/*  60 */       CompoundNBT entryNBT = protectedAreas.func_150305_b(i);
/*  61 */       ProtectedArea area = ProtectedArea.from(entryNBT);
/*  62 */       this.abilityProtections.put(area.getLabel(), area);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isInsideRestrictedArea(int posX, int posY, int posZ) {
/*  67 */     if (this.abilityProtections.size() <= 0) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     for (ProtectedArea area : this.abilityProtections.values()) {
/*  72 */       if (area.isInside(posX, posY, posZ)) {
/*  73 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ProtectedArea getProtectedArea(String label) {
/*  82 */     return this.abilityProtections.get(label);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ProtectedArea getProtectedArea(int posX, int posY, int posZ) {
/*  87 */     for (ProtectedArea area : this.abilityProtections.values()) {
/*  88 */       if (area.isInside(posX, posY, posZ)) {
/*  89 */         return area;
/*     */       }
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   public void addRestrictedArea(ProtectedArea area) {
/*  96 */     this.abilityProtections.put(area.getLabel(), area);
/*  97 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void addRestrictedArea(BlockPos center, int size, String label) {
/* 101 */     ProtectedArea area = new ProtectedArea(center, size, label);
/* 102 */     this.abilityProtections.put(area.getLabel(), area);
/* 103 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void resizeRestrictedArea(String label, int size) {
/* 107 */     ((ProtectedArea)this.abilityProtections.get(label)).setSize(size);
/* 108 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void removeRestrictedArea(String label) {
/* 112 */     this.abilityProtections.remove(label);
/* 113 */     func_76185_a();
/*     */   }
/*     */   
/*     */   public void removeRestrictedArea(int midX, int midY, int midZ) {
/* 117 */     for (ProtectedArea area : this.abilityProtections.values()) {
/* 118 */       if (midX == area.getCenter().func_177958_n() && midY == area.getCenter().func_177956_o() && midZ == area.getCenter().func_177952_p()) {
/* 119 */         this.abilityProtections.remove(area.getLabel());
/* 120 */         func_76185_a();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<String, ProtectedArea> getAllRestrictions() {
/* 127 */     return Collections.unmodifiableMap(this.abilityProtections);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\world\ProtectedAreasData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */