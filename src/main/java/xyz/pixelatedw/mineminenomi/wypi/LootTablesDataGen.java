/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Map;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.DirectoryCache;
/*    */ import net.minecraft.data.IDataProvider;
/*    */ import net.minecraft.data.LootTableProvider;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.LootTableManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public abstract class LootTablesDataGen
/*    */   extends LootTableProvider
/*    */ {
/* 20 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*    */   private DataGenerator generator;
/*    */   
/*    */   public LootTablesDataGen(DataGenerator dataGenerator) {
/* 24 */     super(dataGenerator);
/* 25 */     this.generator = dataGenerator;
/*    */   }
/*    */   
/*    */   public void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
/* 29 */     Path outputFolder = this.generator.func_200391_b().resolve("data/mineminenomi/loot_tables/");
/* 30 */     tables.forEach((res, lootTable) -> {
/*    */           Path path = outputFolder.resolve(res.func_110623_a() + ".json");
/*    */           
/*    */           try {
/*    */             IDataProvider.func_218426_a(GSON, cache, LootTableManager.func_215301_a(lootTable), path);
/* 35 */           } catch (IOException e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */         });
/*    */   }
/*    */   
/*    */   public void writeVanillaTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
/* 42 */     Path outputFolder = this.generator.func_200391_b().resolve("data/minecraft/loot_tables/");
/* 43 */     tables.forEach((res, lootTable) -> {
/*    */           Path path = outputFolder.resolve(res.func_110623_a() + ".json");
/*    */           
/*    */           try {
/*    */             IDataProvider.func_218426_a(GSON, cache, LootTableManager.func_215301_a(lootTable), path);
/* 48 */           } catch (IOException e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */         });
/*    */   }
/*    */   
/*    */   public abstract String func_200397_b();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\LootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */