/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.tags.EntityTypeTags;
/*    */ import net.minecraft.tags.ItemTags;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.Tags;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.data.MappedTag;
/*    */ 
/*    */ public class ModTags
/*    */ {
/*    */   public static void init() {
/* 17 */     Blocks.init();
/* 18 */     Items.init();
/* 19 */     Entities.init();
/*    */   }
/*    */   
/*    */   public static class Items {
/*    */     private static void init() {}
/*    */     
/* 25 */     public static final MappedTag<Item> IRON = new MappedTag(new ResourceLocation("mineminenomi", "iron_values"), "mapped_tags/items", ForgeRegistries.ITEMS);
/* 26 */     public static final MappedTag<Item> CONDUCTIVE = new MappedTag(new ResourceLocation("mineminenomi", "conductive_values"), "mapped_tags/items", ForgeRegistries.ITEMS);
/*    */     
/* 28 */     public static final Tags.IOptionalNamedTag<Item> KAIROSEKI = tag("kairoseki");
/* 29 */     public static final Tags.IOptionalNamedTag<Item> MAGNETIC = tag("magnetic");
/* 30 */     public static final Tags.IOptionalNamedTag<Item> RUSTY = tag("rusty");
/* 31 */     public static final Tags.IOptionalNamedTag<Item> PARAMECIA = tag("paramecia");
/* 32 */     public static final Tags.IOptionalNamedTag<Item> LOGIA = tag("logia");
/* 33 */     public static final Tags.IOptionalNamedTag<Item> ZOAN = tag("zoan");
/* 34 */     public static final Tags.IOptionalNamedTag<Item> DEVIL_FRUIT = tag("devil_fruit");
/* 35 */     public static final Tags.IOptionalNamedTag<Item> MANGROVE_LOGS = tag("mangrove_logs");
/* 36 */     public static final Tags.IOptionalNamedTag<Item> SUPREME_GRADE = tag("supreme_grade");
/* 37 */     public static final Tags.IOptionalNamedTag<Item> GREAT_GRADE = tag("great_grade");
/* 38 */     public static final Tags.IOptionalNamedTag<Item> FRUIT_REINCARNATION = tag("fruit_reincarnation");
/* 39 */     public static final Tags.IOptionalNamedTag<Item> BANNED_ITEMS_CHALLANGES = tag("banned_items_challenges");
/*    */     
/*    */     private static Tags.IOptionalNamedTag<Item> tag(String id) {
/* 42 */       return ItemTags.createOptional(new ResourceLocation("mineminenomi", id));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Blocks {
/*    */     private static void init() {}
/*    */     
/* 49 */     public static final Tags.IOptionalNamedTag<Block> KAIROSEKI = tag("kairoseki");
/* 50 */     public static final Tags.IOptionalNamedTag<Block> NO_PHASE = tag("nophase");
/* 51 */     public static final Tags.IOptionalNamedTag<Block> RUSTY = tag("rusty");
/* 52 */     public static final Tags.IOptionalNamedTag<Block> MANGROVE_LOGS = tag("mangrove_logs");
/*    */     
/* 54 */     public static final Tags.IOptionalNamedTag<Block> LOGIA_BLOCK_PASS_HIE = tag("logia_block_pass/hie");
/* 55 */     public static final Tags.IOptionalNamedTag<Block> LOGIA_BLOCK_PASS_GORO = tag("logia_block_pass/goro");
/* 56 */     public static final Tags.IOptionalNamedTag<Block> LOGIA_BLOCK_PASS_PIKA = tag("logia_block_pass/pika");
/* 57 */     public static final Tags.IOptionalNamedTag<Block> LOGIA_BLOCK_PASS_SUNA = tag("logia_block_pass/suna");
/* 58 */     public static final Tags.IOptionalNamedTag<Block> LOGIA_BLOCK_PASS_YUKI = tag("logia_block_pass/yuki");
/*    */     
/*    */     private static Tags.IOptionalNamedTag<Block> tag(String id) {
/* 61 */       return BlockTags.createOptional(new ResourceLocation("mineminenomi", id));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Entities {
/*    */     private static void init() {}
/*    */     
/* 68 */     public static final MappedTag<EntityType<?>> CONDUCTIVE = new MappedTag(new ResourceLocation("mineminenomi", "conductive_values"), "mapped_tags/entity_types", ForgeRegistries.ENTITIES);
/*    */     
/* 70 */     public static final Tags.IOptionalNamedTag<EntityType<?>> MAGNETIC = tag("magnetic");
/* 71 */     public static final Tags.IOptionalNamedTag<EntityType<?>> KAIROSEKI = tag("kairoseki");
/*    */     
/*    */     private static Tags.IOptionalNamedTag<EntityType<?>> tag(String id) {
/* 74 */       return EntityTypeTags.createOptional(new ResourceLocation("mineminenomi", id));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */