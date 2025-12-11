/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootParameterSets;
/*     */ import net.minecraft.loot.LootParameters;
/*     */ import net.minecraft.loot.LootTable;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.onefruit.InventoryDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ public class AkumaNoMiBoxItem extends Item {
/*  36 */   public static final Pair<Integer, ResourceLocation> TIER_1_FRUITS = (Pair<Integer, ResourceLocation>)ImmutablePair.of(Integer.valueOf(1), new ResourceLocation("mineminenomi", "dfboxes/wooden_box"));
/*  37 */   public static final Pair<Integer, ResourceLocation> TIER_2_FRUITS = (Pair<Integer, ResourceLocation>)ImmutablePair.of(Integer.valueOf(2), new ResourceLocation("mineminenomi", "dfboxes/iron_box"));
/*  38 */   public static final Pair<Integer, ResourceLocation> TIER_3_FRUITS = (Pair<Integer, ResourceLocation>)ImmutablePair.of(Integer.valueOf(3), new ResourceLocation("mineminenomi", "dfboxes/golden_box"));
/*     */   
/*     */   private Pair<Integer, ResourceLocation> tier;
/*     */   
/*     */   public AkumaNoMiBoxItem(Pair<Integer, ResourceLocation> tier) {
/*  43 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*  44 */     this.tier = tier;
/*     */   }
/*     */   
/*     */   public int getKeySlot(PlayerEntity player) {
/*  48 */     if (!player.field_71071_by.func_213902_a((Set)ImmutableSet.of(ModItems.KEY.get()))) {
/*  49 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_NEED_KEY), Util.field_240973_b_);
/*  50 */       return -1;
/*     */     } 
/*     */     
/*  53 */     for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*  54 */       ItemStack stack = player.field_71071_by.func_70301_a(i);
/*  55 */       if (stack != null && !stack.func_190926_b() && stack.func_77973_b() == ModItems.KEY.get()) {
/*  56 */         return i;
/*     */       }
/*     */     } 
/*     */     
/*  60 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  65 */     ItemStack itemStack = player.func_184586_b(hand);
/*     */     
/*  67 */     if (!world.field_72995_K) {
/*  68 */       int keySlot = getKeySlot(player);
/*     */       
/*  70 */       if (keySlot < 0) {
/*  71 */         return ActionResult.func_226251_d_(itemStack);
/*     */       }
/*     */       
/*  74 */       if (hand == Hand.OFF_HAND) {
/*  75 */         return ActionResult.func_226251_d_(itemStack);
/*     */       }
/*     */       
/*  78 */       player.field_71071_by.func_70298_a(keySlot, 1);
/*  79 */       player.field_71071_by.func_184437_d(itemStack);
/*     */       
/*  81 */       OFPWWorldData worldData = OFPWWorldData.get();
/*     */       
/*  83 */       LootTable lootTable = player.field_70170_p.func_73046_m().func_200249_aQ().func_186521_a((ResourceLocation)this.tier.getValue());
/*  84 */       LootContext.Builder builder = (new LootContext.Builder((ServerWorld)player.field_70170_p)).func_216015_a(LootParameters.field_216281_a, player);
/*  85 */       LootContext context = builder.func_216022_a(LootParameterSets.field_216260_a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       List<ItemStack> stacks = lootTable.func_216113_a(context);
/*  93 */       ItemStack boxItemStack = ItemStack.field_190927_a;
/*  94 */       for (ItemStack stack : stacks) {
/*  95 */         if (stack == null || stack.func_190926_b()) {
/*     */           continue;
/*     */         }
/*     */         
/*  99 */         boxItemStack = stack;
/*     */       } 
/*     */ 
/*     */       
/* 103 */       if (boxItemStack == null) {
/* 104 */         player.field_71071_by.func_184437_d(itemStack);
/* 105 */         return ActionResult.func_226248_a_(player.func_184586_b(hand));
/*     */       } 
/*     */       
/* 108 */       if (!(boxItemStack.func_77973_b() instanceof AkumaNoMiItem)) {
/* 109 */         player.field_71071_by.func_70441_a(boxItemStack);
/* 110 */         return new ActionResult(ActionResultType.SUCCESS, player.func_184586_b(hand));
/*     */       } 
/*     */       
/* 113 */       if (DevilFruitHelper.hasDFLimitInInventory(player)) {
/* 114 */         player.func_71019_a(boxItemStack, true);
/* 115 */         return new ActionResult(ActionResultType.SUCCESS, player.func_184586_b(hand));
/*     */       } 
/*     */       
/* 118 */       AkumaNoMiItem fruit = (AkumaNoMiItem)boxItemStack.func_77973_b();
/* 119 */       player.field_71071_by.func_70441_a(boxItemStack);
/* 120 */       OFPWWorldData worldProps = OFPWWorldData.get();
/* 121 */       worldProps.updateOneFruit(fruit.getRegistryName(), player.func_110124_au(), OneFruitEntry.Status.INVENTORY, "Obtained from " + itemStack.func_151000_E().getString());
/*     */       
/* 123 */       InventoryDevilFruitEvent event = new InventoryDevilFruitEvent((LivingEntity)player, fruit, "Obtained from " + itemStack.func_151000_E().getString());
/* 124 */       MinecraftForge.EVENT_BUS.post((Event)event);
/*     */       
/* 126 */       return new ActionResult(ActionResultType.SUCCESS, player.func_184586_b(hand));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 131 */     return ActionResult.func_226248_a_(itemStack);
/*     */   }
/*     */   
/*     */   public int getTierLevel() {
/* 135 */     return ((Integer)this.tier.getKey()).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\AkumaNoMiBoxItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */