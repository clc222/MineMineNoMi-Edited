/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.awt.Color;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenEncyclopediaScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class DFEncyclopediaItem
/*     */   extends Item
/*     */ {
/*  39 */   private static final ITextComponent CONFIG_ERROR = (ITextComponent)ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS.func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.RED));
/*     */   
/*     */   public DFEncyclopediaItem() {
/*  42 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  47 */     ItemStack itemStack = player.func_184586_b(hand);
/*     */     
/*  49 */     if (world.field_72995_K) {
/*  50 */       return ActionResult.func_226250_c_(itemStack);
/*     */     }
/*     */     
/*  53 */     if (!CommonConfig.INSTANCE.getRandomizedFruits()) {
/*  54 */       player.func_145747_a((ITextComponent)ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, Util.field_240973_b_);
/*  55 */       return ActionResult.func_226251_d_(itemStack);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     WyNetwork.sendTo(new SOpenEncyclopediaScreenPacket(itemStack), player);
/*     */     
/*  65 */     return ActionResult.func_226248_a_(itemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/*  70 */     if (CommonConfig.INSTANCE.getRandomizedFruits() || RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*  71 */       int count = itemStack.func_190925_c("unlocked").func_186856_d();
/*  72 */       list.add(new TranslationTextComponent(ModI18n.ITEM_DF_ENCYCLOPEDIA_ENTRIES, new Object[] { Integer.valueOf(count) }));
/*     */       
/*  74 */       float completeness = getCompletion(itemStack);
/*  75 */       if (completeness > 0.0F) {
/*  76 */         list.add(new TranslationTextComponent(ModI18n.ITEM_DF_ENCYCLOPEDIA_COMPLETION, new Object[] { String.format("%.2f", new Object[] { Float.valueOf(completeness * 100.0F) }) + "%" }));
/*     */       }
/*     */     } else {
/*     */       
/*  80 */       list.add(CONFIG_ERROR);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addFruitClues(ItemStack itemStack, ResourceLocation fruit, DFEncyclopediaEntry clue) {
/*  85 */     if (itemStack.func_190926_b() || itemStack.func_77973_b() != ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get()) {
/*     */       return;
/*     */     }
/*     */     
/*  89 */     CompoundNBT nbt = new CompoundNBT();
/*  90 */     if (clue.getShape().isPresent()) {
/*  91 */       nbt.func_74768_a("shape", ((Integer)clue.getShape().get()).intValue());
/*     */     }
/*  93 */     if (clue.getBaseColor().isPresent()) {
/*  94 */       nbt.func_74768_a("baseColor", ((Color)clue.getBaseColor().get()).getRGB());
/*     */     }
/*  96 */     if (clue.getStemColor().isPresent()) {
/*  97 */       nbt.func_74768_a("stemColor", ((Color)clue.getStemColor().get()).getRGB());
/*     */     }
/*  99 */     itemStack.func_190925_c("unlocked").func_218657_a(fruit.toString(), (INBT)nbt);
/*     */   }
/*     */   
/*     */   public static Set<DFEncyclopediaEntry> getEntries(ItemStack itemStack) {
/* 103 */     if (itemStack.func_190926_b() || itemStack.func_77973_b() != ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get() || !itemStack.func_77942_o() || !itemStack.func_77978_p().func_74764_b("unlocked")) {
/* 104 */       return Sets.newHashSet();
/*     */     }
/*     */     
/* 107 */     Set<DFEncyclopediaEntry> entries = new HashSet<>();
/* 108 */     CompoundNBT nbt = itemStack.func_190925_c("unlocked");
/* 109 */     for (AkumaNoMiItem fruit : ModValues.DEVIL_FRUITS) {
/* 110 */       String key = fruit.getFruitKey();
/* 111 */       if (nbt.func_74764_b(key)) {
/* 112 */         CompoundNBT fruitNbt = nbt.func_74775_l(key);
/* 113 */         Optional<Integer> shape = fruitNbt.func_74764_b("shape") ? Optional.<Integer>of(Integer.valueOf(fruitNbt.func_74762_e("shape"))) : Optional.<Integer>empty();
/* 114 */         Optional<Color> baseColor = fruitNbt.func_74764_b("baseColor") ? Optional.<Color>of(new Color(fruitNbt.func_74762_e("baseColor"))) : Optional.<Color>empty();
/* 115 */         Optional<Color> stemColor = fruitNbt.func_74764_b("stemColor") ? Optional.<Color>of(new Color(fruitNbt.func_74762_e("stemColor"))) : Optional.<Color>empty();
/* 116 */         DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(shape, baseColor, stemColor);
/* 117 */         entry.setDevilFruit(fruit);
/* 118 */         entries.add(entry);
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return entries;
/*     */   }
/*     */   
/*     */   public static float getCompletion(ItemStack itemStack) {
/* 126 */     if (itemStack.func_190926_b() || itemStack.func_77973_b() != ModItems.DEVIL_FRUIT_ENCYCLOPEDIA.get() || !itemStack.func_77942_o() || !itemStack.func_77978_p().func_74764_b("unlocked")) {
/* 127 */       return -1.0F;
/*     */     }
/*     */     
/* 130 */     int completed = 0;
/* 131 */     CompoundNBT unlockedElements = itemStack.func_179543_a("unlocked");
/* 132 */     for (String key : unlockedElements.func_150296_c()) {
/* 133 */       CompoundNBT nbt = unlockedElements.func_74775_l(key);
/* 134 */       DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(nbt);
/* 135 */       completed += entry.getCompletion();
/*     */     } 
/* 137 */     return completed / (ModValues.DEVIL_FRUITS.size() * 3);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\DFEncyclopediaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */