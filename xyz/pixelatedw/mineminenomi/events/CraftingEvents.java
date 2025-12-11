/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.CraftResultInventory;
/*     */ import net.minecraft.inventory.CraftingInventory;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.ICraftingRecipe;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.IRecipeType;
/*     */ import net.minecraft.network.play.server.SSetSlotPacket;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SmithingTableEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.FlagItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.WantedPosterItem;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class CraftingEvents {
/*  36 */   private static final ResourceLocation FLAG_UPGRADE_RECIPE = new ResourceLocation("mineminenomi", "flag_upgrade");
/*  37 */   private static final ResourceLocation WANTED_POSTER_UPGRADE_RECIPE = new ResourceLocation("mineminenomi", "wanted_poster_upgrade");
/*  38 */   private static final ResourceLocation ENCYCLOPEDIA_MERGE_RECIPE = new ResourceLocation("mineminenomi", "devil_fruit_encyclopedia_merge");
/*     */   
/*     */   public static boolean onGridChanged(int containerId, World level, PlayerEntity player, CraftingInventory container, CraftResultInventory resultContainer) {
/*  41 */     if (!level.field_72995_K) {
/*  42 */       ServerPlayerEntity serverplayer = (ServerPlayerEntity)player;
/*     */       
/*  44 */       Optional<ICraftingRecipe> optional = level.func_73046_m().func_199529_aN().func_215371_a(IRecipeType.field_222149_a, (IInventory)container, level);
/*  45 */       ItemStack stack = ItemStack.field_190927_a;
/*     */       
/*  47 */       if (optional.isPresent()) {
/*  48 */         ICraftingRecipe icraftingrecipe = optional.get();
/*     */         
/*  50 */         if (icraftingrecipe.func_199560_c().equals(FLAG_UPGRADE_RECIPE)) {
/*  51 */           if (resultContainer.func_201561_a(level, serverplayer, (IRecipe)icraftingrecipe)) {
/*  52 */             stack = container.func_70301_a(4).func_77946_l();
/*  53 */             stack = handleFlagUpgrades(stack);
/*  54 */             if (stack.func_190926_b()) {
/*  55 */               return true;
/*     */             }
/*     */           }
/*     */         
/*  59 */         } else if (icraftingrecipe.func_199560_c().equals(WANTED_POSTER_UPGRADE_RECIPE)) {
/*  60 */           if (resultContainer.func_201561_a(level, serverplayer, (IRecipe)icraftingrecipe)) {
/*  61 */             stack = container.func_70301_a(4).func_77946_l();
/*  62 */             stack = handleWantedPosterUpgrades(stack);
/*  63 */             if (stack.func_190926_b()) {
/*  64 */               return true;
/*     */             }
/*     */           }
/*     */         
/*  68 */         } else if (icraftingrecipe.func_199560_c().equals(ENCYCLOPEDIA_MERGE_RECIPE) && 
/*  69 */           resultContainer.func_201561_a(level, serverplayer, (IRecipe)icraftingrecipe)) {
/*  70 */           ItemStack stack2 = ItemStack.field_190927_a;
/*  71 */           for (int i = 0; i < container.func_70302_i_(); i++) {
/*  72 */             ItemStack containerStack = container.func_70301_a(i);
/*  73 */             if (!containerStack.func_190926_b() && containerStack.func_77973_b().equals(ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get())) {
/*  74 */               if (stack.func_190926_b()) {
/*  75 */                 stack = containerStack.func_77946_l();
/*     */               } else {
/*  77 */                 stack2 = containerStack;
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/*  82 */           if (!stack.func_190926_b() && !stack2.func_190926_b()) {
/*  83 */             stack = handleEncyclopediaMerge(stack, stack2);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/*  88 */         if (!stack.func_190926_b()) {
/*  89 */           resultContainer.func_70299_a(0, stack);
/*  90 */           serverplayer.field_71135_a.func_147359_a((IPacket)new SSetSlotPacket(containerId, 0, stack));
/*  91 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   private static ItemStack handleEncyclopediaMerge(ItemStack output, ItemStack other) {
/* 100 */     for (DFEncyclopediaEntry entry : DFEncyclopediaItem.getEntries(other)) {
/* 101 */       DFEncyclopediaItem.addFruitClues(output, entry.getDevilFruit().getRegistryName(), entry);
/*     */     }
/* 103 */     return output;
/*     */   }
/*     */   
/*     */   private static ItemStack handleWantedPosterUpgrades(ItemStack output) {
/* 107 */     if (WantedPosterItem.upgradeCanvasSize(output)) {
/* 108 */       return output;
/*     */     }
/* 110 */     return ItemStack.field_190927_a;
/*     */   }
/*     */   
/*     */   private static ItemStack handleFlagUpgrades(ItemStack output) {
/* 114 */     if (FlagItem.upgradeCanvasSize(output)) {
/* 115 */       return output;
/*     */     }
/* 117 */     return ItemStack.field_190927_a;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onSmithingUpdate(SmithingTableEvent event) {
/* 122 */     if (ItemsHelper.isBow(event.getBaseSlot())) {
/* 123 */       applyEnchantment(event, ((Block)ModBlocks.FLAME_DIAL.get()).func_199767_j(), Enchantments.field_185311_w, 1, 3);
/* 124 */       applyEnchantment(event, ((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j(), Enchantments.field_185310_v, 1, 3);
/* 125 */       applyEnchantment(event, ((Block)ModBlocks.EISEN_DIAL.get()).func_199767_j(), Enchantments.field_185309_u, 1, 3);
/*     */     }
/* 127 */     else if (ItemsHelper.isSword(event.getBaseSlot())) {
/* 128 */       applyEnchantment(event, (Item)ModItems.KAIROSEKI.get(), (Enchantment)ModEnchantments.KAIROSEKI.get(), 1, 10);
/* 129 */       applyEnchantment(event, ((Block)ModBlocks.FLAME_DIAL.get()).func_199767_j(), Enchantments.field_77334_n, 3, 3);
/* 130 */       applyEnchantment(event, ((Block)ModBlocks.EISEN_DIAL.get()).func_199767_j(), Enchantments.field_185302_k, 3, 3);
/* 131 */       applyEnchantment(event, ((Block)ModBlocks.FLASH_DIAL.get()).func_199767_j(), (Enchantment)ModEnchantments.DIAL_FLASH.get(), 3, 3);
/* 132 */       applyEnchantment(event, ((Block)ModBlocks.IMPACT_DIAL.get()).func_199767_j(), (Enchantment)ModEnchantments.DIAL_IMPACT.get(), 2, 5);
/* 133 */       applyEnchantment(event, ((Block)ModBlocks.BREATH_DIAL.get()).func_199767_j(), Enchantments.field_180313_o, 3, 3);
/*     */     } 
/*     */     
/* 136 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)Items.field_151008_G, 1, 8, new ItemStack((IItemProvider)ModBlocks.BREATH_DIAL.get()));
/* 137 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)Items.field_151145_ak, 1, 10, new ItemStack((IItemProvider)ModBlocks.AXE_DIAL.get()));
/* 138 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)Items.field_151042_j, 1, 8, new ItemStack((IItemProvider)ModBlocks.EISEN_DIAL.get()));
/* 139 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)Items.field_151065_br, 1, 6, new ItemStack((IItemProvider)ModBlocks.FLAME_DIAL.get()));
/* 140 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)Items.field_151114_aO, 1, 10, new ItemStack((IItemProvider)ModBlocks.FLASH_DIAL.get()));
/* 141 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)Items.field_151016_H, 1, 10, new ItemStack((IItemProvider)ModBlocks.IMPACT_DIAL.get()));
/* 142 */     craftDial(event, (IItemProvider)Items.field_205157_eZ, (IItemProvider)ModBlocks.SKY_BLOCK.get(), 1, 4, new ItemStack((IItemProvider)ModBlocks.MILKY_DIAL.get()));
/*     */   }
/*     */   
/*     */   private static void craftDial(SmithingTableEvent event, IItemProvider base, IItemProvider add, int baseQty, int addQty, ItemStack result) {
/* 146 */     if (event.getBaseSlot().func_77973_b().equals(base.func_199767_j()) && event.getBaseSlot().func_190916_E() >= baseQty && event.getAdditionSlot().func_77973_b().equals(add.func_199767_j()) && event.getAdditionSlot().func_190916_E() >= addQty) {
/* 147 */       event.setResultRecipe(result, baseQty, addQty);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void applyEnchantment(SmithingTableEvent event, Item enchItem, Enchantment ench, int maxLevel, int addQty) {
/* 152 */     int enchLevel = EnchantmentHelper.func_77506_a(ench, event.getBaseSlot());
/* 153 */     if (enchLevel >= maxLevel) {
/*     */       return;
/*     */     }
/* 156 */     if (event.getAdditionSlot().func_77973_b() == enchItem && event.getAdditionSlot().func_190916_E() >= addQty && event
/* 157 */       .getAdditionSlot().func_190916_E() / addQty > enchLevel) {
/* 158 */       int level = event.getAdditionSlot().func_190916_E() / addQty;
/*     */       
/* 160 */       if (level > maxLevel) {
/* 161 */         level = maxLevel;
/*     */       }
/*     */       
/* 164 */       ItemStack stack = event.getBaseSlot().func_77946_l();
/* 165 */       Map<Enchantment, Integer> map = EnchantmentHelper.func_82781_a(event.getBaseSlot());
/* 166 */       if (map.containsKey(ench)) {
/* 167 */         map.replace(ench, Integer.valueOf(level));
/* 168 */         EnchantmentHelper.func_82782_a(map, stack);
/*     */       } else {
/*     */         
/* 171 */         EnchantmentHelper.func_82782_a(map, stack);
/* 172 */         stack.func_77966_a(ench, level);
/*     */       } 
/*     */       
/* 175 */       event.setResultRecipe(stack, 1, addQty);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\CraftingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */