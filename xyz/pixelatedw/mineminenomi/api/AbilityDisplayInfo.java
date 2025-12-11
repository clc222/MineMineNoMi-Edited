/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.advancements.DisplayInfo;
/*    */ import net.minecraft.advancements.FrameType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ 
/*    */ public class AbilityDisplayInfo
/*    */   extends DisplayInfo {
/*    */   public AbilityDisplayInfo(AbilityCore pIcon, ITextComponent pTitle, ITextComponent pDescription, ResourceLocation pBackground, FrameType pFrame, boolean pShowToast, boolean pAnnounceChat, boolean pHidden) {
/* 15 */     super(new ItemStack((IItemProvider)Items.field_151121_aF), pTitle, pDescription, pBackground, pFrame, pShowToast, pAnnounceChat, pHidden);
/* 16 */     this.icon = pIcon;
/*    */   }
/*    */   private AbilityCore icon;
/*    */   public AbilityDisplayInfo(AbilityCore pIcon, ItemStack fallback, ITextComponent pTitle, ITextComponent pDescription, ResourceLocation pBackground, FrameType pFrame, boolean pShowToast, boolean pAnnounceChat, boolean pHidden) {
/* 20 */     super(fallback, pTitle, pDescription, pBackground, pFrame, pShowToast, pAnnounceChat, pHidden);
/* 21 */     this.icon = pIcon;
/*    */   }
/*    */   
/*    */   public AbilityCore getAbility() {
/* 25 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\AbilityDisplayInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */