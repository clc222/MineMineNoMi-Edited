/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.conditions.RandomChance;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import xyz.pixelatedw.mineminenomi.data.functions.FruitAlreadyExistsFunction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiBoxItem;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ public class DFBoxesLootTablesDataGen extends LootTablesDataGen {
/* 23 */   private final Map<AkumaNoMiBoxItem, LootTable.Builder> lootTables = new HashMap<>();
/*    */   
/*    */   public DFBoxesLootTablesDataGen(DataGenerator dataGenerator) {
/* 26 */     super(dataGenerator);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_200398_a(DirectoryCache cache) {
/* 31 */     addLootTable((AkumaNoMiBoxItem)ModItems.TIER_1_BOX.get());
/* 32 */     addLootTable((AkumaNoMiBoxItem)ModItems.TIER_2_BOX.get());
/* 33 */     addLootTable((AkumaNoMiBoxItem)ModItems.TIER_3_BOX.get());
/*    */     
/* 35 */     Map<ResourceLocation, LootTable> tables = new HashMap<>();
/* 36 */     this.lootTables.forEach((item, builder) -> {
/*    */           ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey((IForgeRegistryEntry)item);
/*    */           
/*    */           ResourceLocation key = new ResourceLocation(resourcelocation.func_110624_b(), "dfboxes/" + resourcelocation.func_110623_a());
/*    */           tables.put(key, builder.func_216038_b());
/*    */         });
/* 42 */     writeTables(cache, tables);
/*    */   }
/*    */   
/*    */   protected void addLootTable(AkumaNoMiBoxItem item) {
/* 46 */     LootTable.Builder builder = LootTable.func_216119_b();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     LootPool.Builder sameTierFruits = LootPool.func_216096_a().name("mineminenomi:fruits").func_216046_a((IRandomRange)ConstantRange.func_215835_a(5)).func_212840_b_(RandomChance.func_216004_a(0.95F));
/*    */     
/* 53 */     ModValues.DEVIL_FRUITS.stream()
/* 54 */       .filter(df -> (df.getTier() == item.getTierLevel()))
/* 55 */       .forEach(df -> sameTierFruits.func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)df).func_216086_a(100).func_212841_b_((ILootFunction.IBuilder)FruitAlreadyExistsFunction.builder())));
/*    */     
/* 57 */     if (item.getTierLevel() < 3) {
/*    */ 
/*    */ 
/*    */       
/* 61 */       LootPool.Builder nextTierFruits = LootPool.func_216096_a().name("mineminenomi:next_tier_fruits").func_216046_a((IRandomRange)ConstantRange.func_215835_a(1)).func_212840_b_(RandomChance.func_216004_a(0.05F));
/*    */       
/* 63 */       ModValues.DEVIL_FRUITS.stream()
/* 64 */         .filter(df -> (df.getTier() == item.getTierLevel() + 1))
/* 65 */         .forEach(df -> nextTierFruits.func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)df).func_216086_a(100).func_212841_b_((ILootFunction.IBuilder)FruitAlreadyExistsFunction.builder())));
/*    */       
/* 67 */       builder.func_216040_a(nextTierFruits);
/*    */     } 
/*    */     
/* 70 */     builder.func_216040_a(sameTierFruits);
/*    */     
/* 72 */     this.lootTables.put(item, builder);
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_200397_b() {
/* 77 */     return "DF Boxes Loot Pools";
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\DFBoxesLootTablesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */