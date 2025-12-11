/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.List;
/*    */ import net.minecraft.advancements.AdvancementProgress;
/*    */ import net.minecraft.advancements.DisplayInfo;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.advancements.AdvancementEntryGui;
/*    */ import net.minecraft.client.gui.advancements.AdvancementState;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.AbilityDisplayInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({AdvancementEntryGui.class})
/*    */ public abstract class AdvancementEntryGuiMixin
/*    */ {
/* 26 */   private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation("textures/gui/advancements/widgets.png");
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private DisplayInfo field_191830_h;
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private int field_191837_o;
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private int field_191826_p;
/*    */   
/*    */   @Shadow
/*    */   private AdvancementProgress field_191836_n;
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   private List<AdvancementEntryGui> field_191835_m;
/*    */ 
/*    */   
/*    */   @Inject(method = {"draw"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void draw(MatrixStack matrixStack, int pX, int pY, CallbackInfo callback) {
/* 50 */     AdvancementEntryGui entry = (AdvancementEntryGui)this;
/* 51 */     if (this.field_191830_h instanceof AbilityDisplayInfo) {
/*    */       
/* 53 */       AbilityDisplayInfo ablInfo = (AbilityDisplayInfo)this.field_191830_h;
/* 54 */       if (ablInfo.getAbility() != null) {
/*    */         
/* 56 */         if (!this.field_191830_h.func_193224_j() || (this.field_191836_n != null && this.field_191836_n.func_192105_a())) {
/*    */           AdvancementState advancementstate;
/* 58 */           float f = (this.field_191836_n == null) ? 0.0F : this.field_191836_n.func_192103_c();
/*    */           
/* 60 */           if (f >= 1.0F) {
/*    */             
/* 62 */             advancementstate = AdvancementState.OBTAINED;
/*    */           }
/*    */           else {
/*    */             
/* 66 */             advancementstate = AdvancementState.UNOBTAINED;
/*    */           } 
/*    */           
/* 69 */           Minecraft.func_71410_x().func_110434_K().func_110577_a(WIDGETS_LOCATION);
/* 70 */           entry.func_238474_b_(matrixStack, pX + this.field_191837_o + 3, pY + this.field_191826_p, this.field_191830_h.func_192291_d().func_192309_b(), 128 + advancementstate.func_192667_a() * 26, 26, 26);
/* 71 */           RendererHelper.drawAbilityIcon(ablInfo.getAbility(), matrixStack, (pX + this.field_191837_o + 8), (pY + this.field_191826_p + 5), 1, 16.0F, 16.0F);
/*    */         } 
/*    */         
/* 74 */         for (AdvancementEntryGui advancemententrygui : this.field_191835_m)
/*    */         {
/* 76 */           advancemententrygui.func_238688_a_(matrixStack, pX, pY);
/*    */         }
/* 78 */         callback.cancel();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"drawHover"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;renderAndDecorateFakeItem(Lnet/minecraft/item/ItemStack;II)V", shift = At.Shift.BEFORE)}, cancellable = true)
/*    */   public void drawHover(MatrixStack matrixStack, int pX, int pY, float pFade, int pWidth, int pHeight, CallbackInfo callback) {
/* 86 */     if (this.field_191830_h instanceof AbilityDisplayInfo) {
/*    */       
/* 88 */       AbilityDisplayInfo ablInfo = (AbilityDisplayInfo)this.field_191830_h;
/* 89 */       if (ablInfo.getAbility() != null) {
/*    */         
/* 91 */         RendererHelper.drawAbilityIcon(ablInfo.getAbility(), matrixStack, (pX + this.field_191837_o + 8), (pY + this.field_191826_p + 5), 1, 16.0F, 16.0F);
/* 92 */         callback.cancel();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\AdvancementEntryGuiMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */