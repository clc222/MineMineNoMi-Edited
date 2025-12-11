/*    */ package xyz.pixelatedw.mineminenomi.datagen.loottables.entities;
/*    */ import net.minecraft.advancements.criterion.EntityFlagsPredicate;
/*    */ import net.minecraft.advancements.criterion.EntityPredicate;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.ItemLootEntry;
/*    */ import net.minecraft.loot.LootEntry;
/*    */ import net.minecraft.loot.LootPool;
/*    */ import net.minecraft.loot.LootTable;
/*    */ import net.minecraft.loot.RandomValueRange;
/*    */ import net.minecraft.loot.conditions.EntityHasProperty;
/*    */ import net.minecraft.loot.conditions.KilledByPlayer;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.loot.functions.LootingEnchantBonus;
/*    */ import net.minecraft.loot.functions.SetCount;
/*    */ import net.minecraft.loot.functions.Smelt;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ 
/*    */ public class LapahnDrops {
/* 21 */   private static final EntityPredicate ON_FIRE_CHECK = EntityPredicate.Builder.func_203996_a().func_217987_a(EntityFlagsPredicate.Builder.func_217967_a().func_217968_a(Boolean.valueOf(true)).func_217966_b()).func_204000_b();
/*    */   
/* 23 */   private static final LootPool.Builder ITEMS = LootPool.func_216096_a()
/* 24 */     .name("mineminenomi:items")
/* 25 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 26 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_151126_ay)
/* 27 */       .func_216086_a(100)
/* 28 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(0.0F, 5.0F))))
/* 29 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179555_bs)
/* 30 */       .func_216086_a(80)
/* 31 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 4.0F)))
/* 32 */       .func_212841_b_((ILootFunction.IBuilder)LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 2.0F))))
/* 33 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179558_bo)
/* 34 */       .func_216086_a(80)
/* 35 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(2.0F, 4.0F)))
/* 36 */       .func_212841_b_((ILootFunction.IBuilder)Smelt.func_215953_b()
/* 37 */         .func_212840_b_(EntityHasProperty.func_237477_a_(LootContext.EntityTarget.THIS, ON_FIRE_CHECK)))
/* 38 */       .func_212841_b_((ILootFunction.IBuilder)LootingEnchantBonus.func_215915_a(RandomValueRange.func_215837_a(0.0F, 2.0F))));
/*    */   
/* 40 */   private static final LootPool.Builder ITEMS2 = LootPool.func_216096_a()
/* 41 */     .name("mineminenomi:items2")
/* 42 */     .func_216046_a((IRandomRange)ConstantRange.func_215835_a(1))
/* 43 */     .func_212840_b_(KilledByPlayer.func_215994_b())
/* 44 */     .func_212840_b_(RandomChanceWithLooting.func_216003_a(0.3F, 0.03F))
/* 45 */     .func_216045_a((LootEntry.Builder)ItemLootEntry.func_216168_a((IItemProvider)Items.field_179556_br)
/* 46 */       .func_216086_a(100)
/* 47 */       .func_212841_b_((ILootFunction.IBuilder)SetCount.func_215932_a((IRandomRange)RandomValueRange.func_215837_a(1.0F, 2.0F))));
/*    */ 
/*    */ 
/*    */   
/*    */   public static LootTable.Builder getTable() {
/* 52 */     LootTable.Builder drops = LootTable.func_216119_b().func_216040_a(ITEMS).func_216040_a(ITEMS2);
/*    */     
/* 54 */     return drops;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\loottables\entities\LapahnDrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */