/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.event.ItemAttributeModifierEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FightingStyleHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class FightingStylesEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onAttributesChange(ItemAttributeModifierEvent event) {
/*  33 */     ItemStack stack = event.getItemStack();
/*     */     
/*  35 */     if (event.getSlotType() == EquipmentSlotType.MAINHAND && !stack.func_190926_b() && ItemsHelper.isSword(stack) && stack.func_77942_o()) {
/*  36 */       double bonus = stack.func_77978_p().func_74769_h("swordsmanBonus");
/*     */       
/*  38 */       AttributeModifier mod = new AttributeModifier(FightingStyleHelper.SWORDSMAN_ATTACK_BONUS_ID, "Swordsman Bonus", bonus, AttributeModifier.Operation.MULTIPLY_BASE);
/*     */       
/*  40 */       if (bonus > 0.0D) {
/*  41 */         event.addModifier(Attributes.field_233823_f_, mod);
/*  42 */       } else if (bonus == 0.0D) {
/*  43 */         event.removeModifier(Attributes.field_233823_f_, mod);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemChange(LivingEquipmentChangeEvent event) {
/*  50 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  52 */     ItemStack stack = event.getTo();
/*     */     
/*  54 */     if (!stack.func_190926_b() && ItemsHelper.isSword(stack)) {
/*  55 */       if (!stack.func_77942_o()) {
/*  56 */         stack.func_196082_o();
/*     */       }
/*     */       
/*  59 */       if (EntityStatsCapability.get(entity).isSwordsman()) {
/*  60 */         stack.func_77978_p().func_74780_a("swordsmanBonus", EntityStatsCapability.get(entity).getDoriki() * 2.5E-5D);
/*     */       } else {
/*  62 */         stack.func_77978_p().func_82580_o("swordsmanBonus");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void onTooltipRender(ItemTooltipEvent event) {
/*  70 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  72 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     if (ItemsHelper.isSword(event.getItemStack()) && EntityStatsCapability.get((LivingEntity)event.getPlayer()).isSwordsman()) {
/*  77 */       StringTextComponent damageBonus = new StringTextComponent(TextFormatting.GREEN + "" + (new TranslationTextComponent(ModI18n.ITEM_SWORDSMAN_BONUS)).getString());
/*  78 */       if (!event.getToolTip().contains(damageBonus)) {
/*  79 */         event.getToolTip().add(new StringTextComponent(""));
/*  80 */         event.getToolTip().add(damageBonus);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsChoose(SetPlayerDetailsEvent event) {
/*  87 */     FightingStyleHelper.removeBrawlerModifiers((LivingEntity)event.getPlayer());
/*  88 */     FightingStyleHelper.removeBlackLegModifiers((LivingEntity)event.getPlayer());
/*  89 */     if (event.getEntityStats().isBlackLeg()) {
/*  90 */       FightingStyleHelper.applyBlackLegModifiers((LivingEntity)event.getPlayer());
/*     */     }
/*  92 */     if (event.getEntityStats().isBrawler()) {
/*  93 */       FightingStyleHelper.applyBrawlerModifiers((LivingEntity)event.getPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void edgeCasesChecks(TickEvent.PlayerTickEvent event) {
/*  99 */     if (event.phase != TickEvent.Phase.START || event.side != LogicalSide.SERVER || event.player.field_70173_aa % 10 != 0) {
/*     */       return;
/*     */     }
/*     */     
/* 103 */     PlayerEntity player = event.player;
/* 104 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 105 */     boolean isHandEmpty = player.func_184614_ca().func_190926_b();
/*     */     
/* 107 */     if (props.isBlackLeg()) {
/* 108 */       if (!isHandEmpty && FightingStyleHelper.hasBlackLegModifiers((LivingEntity)player)) {
/* 109 */         FightingStyleHelper.removeBrawlerModifiers((LivingEntity)player);
/*     */       }
/* 111 */       double possibleBonus = FightingStyleHelper.getBlackLegBonus((LivingEntity)player);
/* 112 */       double appliedBonus = FightingStyleHelper.getAppliedBlackLegBonus((LivingEntity)player);
/* 113 */       if (possibleBonus != appliedBonus) {
/* 114 */         FightingStyleHelper.applyBlackLegModifiers((LivingEntity)player);
/*     */       }
/*     */     }
/* 117 */     else if (props.isBrawler()) {
/* 118 */       if (!isHandEmpty && FightingStyleHelper.hasBrawlerModifiers((LivingEntity)player)) {
/* 119 */         FightingStyleHelper.removeBrawlerModifiers((LivingEntity)player);
/*     */       }
/* 121 */       double possibleBonus = FightingStyleHelper.getBrawlerBonus((LivingEntity)player);
/* 122 */       double appliedBonus = FightingStyleHelper.getAppliedBrawlerBonus((LivingEntity)player);
/* 123 */       if (possibleBonus != appliedBonus) {
/* 124 */         FightingStyleHelper.applyBrawlerModifiers((LivingEntity)player);
/*     */       }
/*     */     } else {
/*     */       
/* 128 */       if (FightingStyleHelper.hasBrawlerModifiers((LivingEntity)player)) {
/* 129 */         FightingStyleHelper.removeBrawlerModifiers((LivingEntity)player);
/*     */       }
/*     */       
/* 132 */       if (FightingStyleHelper.hasBlackLegModifiers((LivingEntity)player))
/* 133 */         FightingStyleHelper.removeBlackLegModifiers((LivingEntity)player); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\FightingStylesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */