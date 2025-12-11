/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class KairosekiTooltipEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static void onKairosekiCheck(ItemTooltipEvent event) {
/* 22 */     if (event.getPlayer() == null || event.getItemStack().func_190926_b()) {
/*    */       return;
/*    */     }
/* 25 */     if (event.getItemStack().func_77973_b().func_206844_a((ITag)ModTags.Items.KAIROSEKI)) {
/*    */       
/* 27 */       StringTextComponent kairosekiString = new StringTextComponent(TextFormatting.YELLOW + "" + (new TranslationTextComponent(ModI18n.ITEM_KAIROSEKI_ITEM)).getString());
/* 28 */       if (!event.getToolTip().contains(kairosekiString)) {
/*    */         
/* 30 */         event.getToolTip().add(new StringTextComponent(""));
/* 31 */         event.getToolTip().add(kairosekiString);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\KairosekiTooltipEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */