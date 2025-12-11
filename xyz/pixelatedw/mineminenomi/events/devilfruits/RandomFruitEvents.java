/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetFruitSeedsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetRandomizedFruitsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class RandomFruitEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     public static boolean HAS_RANDOMIZED_FRUIT = false;
/*     */     public static boolean DIRTY = false;
/*  41 */     public static HashMap<Integer, Long> FRUIT_SEEDS = new HashMap<>();
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onTooltipRendered(ItemTooltipEvent event) {
/*  46 */       if (HAS_RANDOMIZED_FRUIT && event.getItemStack() != null && event.getItemStack().func_77973_b() instanceof AkumaNoMiItem) {
/*     */         IFormattableTextComponent iFormattableTextComponent;
/*  48 */         ItemStack stack = event.getItemStack();
/*  49 */         boolean isFound = stack.func_196082_o().func_74767_n("deciphered");
/*     */         
/*  51 */         event.getToolTip().clear();
/*  52 */         TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18n.ITEM_GENERIC_FRUIT);
/*  53 */         if (isFound) {
/*  54 */           iFormattableTextComponent = (new StringTextComponent("")).func_230529_a_(event.getItemStack().func_200301_q()).func_240699_a_((event.getItemStack().func_77953_t()).field_77937_e);
/*     */         }
/*  56 */         event.getToolTip().add(iFormattableTextComponent);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT, Dist.DEDICATED_SERVER})
/*     */   public static class Common
/*     */   {
/*     */     public static long SEED;
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/*  69 */       if (event.getEntity() instanceof PlayerEntity && !(event.getEntity()).field_70170_p.field_72995_K) {
/*     */         
/*  71 */         PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */         
/*  73 */         WyNetwork.sendTo(new SSetRandomizedFruitsPacket(CommonConfig.INSTANCE.getRandomizedFruits()), player);
/*  74 */         if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/*     */ 
/*     */           
/*  77 */           HashMap<Integer, Long> seeds = new HashMap<>();
/*  78 */           Random rand = new Random(((ServerWorld)player.field_70170_p).func_72905_C());
/*  79 */           long worldSeed = rand.nextLong();
/*  80 */           for (AkumaNoMiItem fruit : ModValues.DEVIL_FRUITS) {
/*     */             
/*  82 */             ResourceLocation res = fruit.getRegistryName();
/*  83 */             if (res == null)
/*     */               continue; 
/*  85 */             int key = res.hashCode();
/*  86 */             long seed = worldSeed + key;
/*  87 */             seeds.put(Integer.valueOf(key), Long.valueOf(seed));
/*     */           } 
/*  89 */           SEED = worldSeed;
/*  90 */           WyNetwork.sendTo(new SSetFruitSeedsPacket(seeds, worldSeed), player);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerUsesItem(PlayerInteractEvent.RightClickItem event) {
/*  98 */       if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/* 101 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 102 */       ItemStack stack = event.getItemStack();
/*     */       
/* 104 */       if (stack.func_190926_b() || stack.func_77973_b() != Items.field_151121_aF || stack.func_77978_p() == null || stack.func_77978_p().isEmpty() || player.field_70170_p.field_72995_K) {
/*     */         return;
/*     */       }
/* 107 */       if (stack.func_179543_a("fruitClues") == null) {
/*     */         return;
/*     */       }
/* 110 */       if (!CommonConfig.INSTANCE.getRandomizedFruits()) {
/*     */         
/* 112 */         player.func_145747_a((ITextComponent)ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, Util.field_240973_b_);
/*     */         
/*     */         return;
/*     */       } 
/* 116 */       CompoundNBT nbt = stack.func_179543_a("fruitClues");
/* 117 */       ResourceLocation key = new ResourceLocation(nbt.func_74779_i("key"));
/* 118 */       DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(nbt);
/* 119 */       boolean flag = ItemsHelper.updateEncyclopediae(player, key, entry);
/* 120 */       if (flag) {
/* 121 */         stack.func_190918_g(1);
/* 122 */         ItemStack fruit = DevilFruitHelper.getDevilFruitItemStack(key);
/* 123 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_GAINED_FRUIT_CLUE, new Object[] { fruit.func_200301_q().getString() }), Util.field_240973_b_);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\RandomFruitEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */