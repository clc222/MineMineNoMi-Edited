/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenNewCrewScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class SakeBottleItem extends Item {
/*     */   public SakeBottleItem() {
/*  35 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200918_c(5).func_221540_a(ModFoods.ALCOHOL));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  40 */     ItemStack itemstack = player.func_184586_b(hand);
/*  41 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  42 */     if (world.field_72995_K) {
/*  43 */       return ActionResult.func_226251_d_(itemstack);
/*     */     }
/*     */     
/*  46 */     if (player.func_213453_ef() && props.isPirate()) {
/*  47 */       ExtendedWorldData worldProps = ExtendedWorldData.get();
/*  48 */       boolean isInCrew = (worldProps.getCrewWithMember(player.func_110124_au()) != null);
/*  49 */       if (isInCrew) {
/*  50 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_ALREADY_IN_CREW), Util.field_240973_b_);
/*  51 */         return ActionResult.func_226251_d_(itemstack);
/*     */       } 
/*     */       
/*  54 */       if (props.getBounty() < CommonConfig.INSTANCE.getBountyRequirementForCrews()) {
/*  55 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_BOUNTY_REQUIREMENT), Util.field_240973_b_);
/*  56 */         return ActionResult.func_226251_d_(itemstack);
/*     */       } 
/*     */       
/*  59 */       WyNetwork.sendTo(new SOpenNewCrewScreenPacket(), player);
/*     */     } else {
/*     */       
/*  62 */       player.func_184598_c(hand);
/*     */     } 
/*     */     
/*  65 */     return ActionResult.func_226248_a_(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/*  76 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/*     */       
/*  78 */       PlayerEntity player = (PlayerEntity)entity;
/*  79 */       player.func_71024_bL().func_221410_a(itemStack.func_77973_b(), itemStack);
/*  80 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(itemStack.func_77973_b()));
/*  81 */       world.func_184148_a((PlayerEntity)null, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), SoundEvents.field_187739_dZ, SoundCategory.PLAYERS, 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*  82 */       if (player instanceof ServerPlayerEntity)
/*  83 */         CriteriaTriggers.field_193138_y.func_193148_a((ServerPlayerEntity)player, itemStack); 
/*  84 */       if (entity.func_70644_a((Effect)ModEffects.DRUNK.get())) {
/*     */         
/*  86 */         EffectInstance effect = entity.func_70660_b((Effect)ModEffects.DRUNK.get());
/*     */         
/*  88 */         int amp = effect.func_76458_c();
/*  89 */         int duration = effect.func_76459_b();
/*     */         
/*  91 */         if (amp < 4) {
/*  92 */           amp++;
/*     */         }
/*  94 */         entity.func_195063_d((Effect)ModEffects.DRUNK.get());
/*  95 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.DRUNK.get(), duration + 200, amp));
/*     */       }
/*     */       else {
/*     */         
/*  99 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.DRUNK.get(), 400, 0));
/*     */       } 
/*     */       
/* 102 */       itemStack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 107 */     return itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAction func_77661_b(ItemStack stack) {
/* 113 */     return UseAction.DRINK;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\SakeBottleItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */