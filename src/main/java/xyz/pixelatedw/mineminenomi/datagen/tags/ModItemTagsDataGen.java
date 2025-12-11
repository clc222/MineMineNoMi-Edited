/*     */ package xyz.pixelatedw.mineminenomi.datagen.tags;
/*     */ import net.minecraft.data.BlockTagsProvider;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.ItemTagsProvider;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraftforge.common.data.ExistingFileHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.FruitType;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ 
/*     */ public class ModItemTagsDataGen extends ItemTagsProvider {
/*     */   public ModItemTagsDataGen(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
/*  18 */     super(generator, blockTagsProvider, "mineminenomi", existingFileHelper);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_200432_c() {
/*  23 */     func_240522_a_((ITag.INamedTag)ModTags.Items.KAIROSEKI).func_240534_a_((Object[])new Item[] { (Item)ModItems.KAIROSEKI.get(), (Item)ModItems.KAIROSEKI_BULLET.get(), (Item)ModItems.KAIROSEKI_HANDCUFFS.get(), (Item)ModItems.KAIROSEKI_NET.get(), (Item)ModItems.DENSE_KAIROSEKI.get() });
/*  24 */     func_240521_a_((ITag.INamedTag)ModTags.Blocks.KAIROSEKI, (ITag.INamedTag)ModTags.Items.KAIROSEKI);
/*     */     
/*  26 */     func_240522_a_((ITag.INamedTag)ModTags.Items.MAGNETIC).func_240534_a_((Object[])new Item[] { Items.field_151042_j, Items.field_221552_E, Items.field_221698_bk, Items.field_191525_da, Items.field_221790_de, Items.field_221805_eM, Items.field_221844_ef, Items.field_222013_iM, Items.field_222089_ms, Items.field_221862_eo, Items.field_151143_au, Items.field_151108_aI, Items.field_151095_cc, Items.field_151109_aJ, Items.field_151140_bW, Items.field_151142_bV, Items.field_221742_cg, Items.field_151036_c, Items.field_151019_K, Items.field_151035_b, Items.field_151037_a, Items.field_151040_l, Items.field_151133_ar, Items.field_151111_aL, Items.field_185159_cQ, Items.field_151033_d, Items.field_151028_Y, Items.field_151030_Z, Items.field_151165_aa, Items.field_151167_ab, Items.field_151138_bX, Items.field_151097_aZ, Items.field_234729_dO_, (Item)ModItems.NORMAL_HANDCUFFS
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  60 */           .get(), (Item)ModItems.EXPLOSIVE_HANDCUFFS
/*  61 */           .get(), (Item)ModItems.TIER_2_BOX
/*  62 */           .get(), (Item)ModItems.BULLET
/*  63 */           .get(), (Item)ModItems.CANNON_BALL
/*  64 */           .get(), (Item)ModWeapons.MARINE_SWORD
/*  65 */           .get(), (Item)ModWeapons.PIRATE_CUTLASS
/*  66 */           .get(), (Item)ModWeapons.PIPE
/*  67 */           .get(), (Item)ModWeapons.SCISSORS
/*  68 */           .get(), (Item)ModWeapons.KIKOKU
/*  69 */           .get(), (Item)ModWeapons.KIRIBACHI
/*  70 */           .get(), (Item)ModWeapons.YORU
/*  71 */           .get(), (Item)ModWeapons.MURAKUMOGIRI
/*  72 */           .get(), (Item)ModWeapons.HOOK
/*  73 */           .get(), (Item)ModWeapons.TONFA
/*  74 */           .get(), (Item)ModWeapons.BANDIT_KNIFE
/*  75 */           .get(), (Item)ModWeapons.BANDIT_KNIFE
/*  76 */           .get(), (Item)ModWeapons.MIHAWKS_KNIFE
/*  77 */           .get(), (Item)ModWeapons.SANDAI_KITETSU
/*  78 */           .get(), (Item)ModWeapons.WADO_ICHIMONJI
/*  79 */           .get(), (Item)ModWeapons.NIDAI_KITETSU
/*  80 */           .get(), (Item)ModWeapons.SHUSUI
/*  81 */           .get(), (Item)ModWeapons.ENMA
/*  82 */           .get(), (Item)ModWeapons.AME_NO_HABAKIRI
/*  83 */           .get(), (Item)ModWeapons.SOUL_SOLID
/*  84 */           .get(), (Item)ModWeapons.DURANDAL
/*  85 */           .get(), (Item)ModWeapons.MACE
/*  86 */           .get(), (Item)ModWeapons.DAISENSO
/*  87 */           .get(), (Item)ModWeapons.ACE
/*  88 */           .get(), (Item)ModWeapons.MOGURA
/*  89 */           .get(), (Item)ModWeapons.DALTONS_SPADE
/*  90 */           .get(), (Item)ModWeapons.FLINTLOCK
/*  91 */           .get(), (Item)ModWeapons.SENRIKU
/*  92 */           .get(), (Item)ModWeapons.BAZOOKA
/*  93 */           .get(), (Item)ModWeapons.WALKER
/*  94 */           .get(), (Item)ModWeapons.KATANA
/*  95 */           .get(), (Item)ModWeapons.BROADSWORD
/*  96 */           .get(), (Item)ModWeapons.SPEAR
/*  97 */           .get(), (Item)ModWeapons.CLEAVER
/*  98 */           .get(), (Item)ModWeapons.DAGGER
/*  99 */           .get(), (Item)ModWeapons.BISENTO
/* 100 */           .get(), (Item)ModWeapons.AXE
/* 101 */           .get(), (Item)ModWeapons.CUTLASS
/* 102 */           .get(), (Item)ModWeapons.SAMEKIRI_BOCHO
/* 103 */           .get(), (Item)ModWeapons.CAT_CLAWS
/* 104 */           .get(), (Item)ModWeapons.GRYPHON
/* 105 */           .get() });
/*     */     
/* 107 */     func_240522_a_((ITag.INamedTag)ModTags.Items.RUSTY).func_240531_a_((ITag.INamedTag)ModTags.Items.MAGNETIC);
/* 108 */     func_240521_a_((ITag.INamedTag)ModTags.Blocks.RUSTY, (ITag.INamedTag)ModTags.Items.RUSTY);
/*     */     
/* 110 */     ModValues.DEVIL_FRUITS.stream().filter(fruit -> (fruit.getType() == FruitType.PARAMECIA)).forEach(fruit -> func_240522_a_((ITag.INamedTag)ModTags.Items.PARAMECIA).func_240532_a_(fruit));
/* 111 */     ModValues.DEVIL_FRUITS.stream().filter(fruit -> (fruit.getType() == FruitType.LOGIA)).forEach(fruit -> func_240522_a_((ITag.INamedTag)ModTags.Items.LOGIA).func_240532_a_(fruit));
/* 112 */     ModValues.DEVIL_FRUITS.stream().filter(fruit -> (fruit.getType() == FruitType.ZOAN || fruit.getType() == FruitType.ANCIENT_ZOAN || fruit.getType() == FruitType.MYTHICAL_ZOAN)).forEach(fruit -> func_240522_a_((ITag.INamedTag)ModTags.Items.ZOAN).func_240532_a_(fruit));
/*     */     
/* 114 */     func_240522_a_((ITag.INamedTag)ModTags.Items.DEVIL_FRUIT).func_240531_a_((ITag.INamedTag)ModTags.Items.PARAMECIA).func_240531_a_((ITag.INamedTag)ModTags.Items.LOGIA).func_240531_a_((ITag.INamedTag)ModTags.Items.ZOAN);
/*     */     
/* 116 */     func_240522_a_((ITag.INamedTag)ModTags.Items.SUPREME_GRADE).func_240534_a_((Object[])new Item[] { (Item)ModWeapons.YORU
/* 117 */           .get(), (Item)ModWeapons.MURAKUMOGIRI
/* 118 */           .get(), (Item)ModWeapons.ACE
/* 119 */           .get() });
/*     */ 
/*     */     
/* 122 */     func_240522_a_((ITag.INamedTag)ModTags.Items.GREAT_GRADE).func_240534_a_((Object[])new Item[] { (Item)ModWeapons.NIDAI_KITETSU
/* 123 */           .get(), (Item)ModWeapons.WADO_ICHIMONJI
/* 124 */           .get(), (Item)ModWeapons.ENMA
/* 125 */           .get(), (Item)ModWeapons.SHUSUI
/* 126 */           .get(), (Item)ModWeapons.AME_NO_HABAKIRI
/* 127 */           .get() });
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\tags\ModItemTagsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */