/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.structures.vanilla;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class StrongholdLootTable {
/* 14 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 15 */     .name("mineminenomi:items")
/* 16 */     .func_216046_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))
/* 17 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ItemStack.field_190927_a.func_77973_b())
/* 18 */       .func_216086_a(20))
/* 19 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BROADSWORD.get())
/* 20 */       .func_216086_a(20))
/* 21 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.BISENTO.get())
/* 22 */       .func_216086_a(20))
/* 23 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.KATANA.get())
/* 24 */       .func_216086_a(20))
/* 25 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModWeapons.CLEAVER.get())
/* 26 */       .func_216086_a(20))
/* 27 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BOTTLE_OF_RUM.get())
/* 28 */       .func_216086_a(10))
/* 29 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.BULLET.get())
/* 30 */       .func_216086_a(50)
/* 31 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(5.0F, 10.0F))))
/* 32 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_BULLET.get())
/* 33 */       .func_216086_a(20)
/* 34 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 5.0F))))
/* 35 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.NORMAL_HANDCUFFS.get())
/* 36 */       .func_216086_a(20))
/* 37 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.KAIROSEKI_NET.get())
/* 38 */       .func_216086_a(5))
/* 39 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)ModItems.ROPE_NET.get())
/* 40 */       .func_216086_a(5));
/*    */   
/*    */   public static LootTable.Builder getPool() {
/* 43 */     return LootTable.func_216119_b()
/* 44 */       .func_216040_a(ITEMS);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\structures\vanilla\StrongholdLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */