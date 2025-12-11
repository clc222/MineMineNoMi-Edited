/*    */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.awt.Color;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupMemberButton
/*    */   extends Button
/*    */ {
/*    */   private FontRenderer font;
/*    */   private LivingEntity entity;
/*    */   private LivingEntity dummy;
/* 24 */   private int fontColor = Color.WHITE.getRGB();
/*    */   
/*    */   public GroupMemberButton(int x, int y, int width, int height, LivingEntity entity, LivingEntity dummy, Button.IPressable onPress) {
/* 27 */     super(x, y, width, height, StringTextComponent.field_240750_d_, onPress);
/* 28 */     this.font = (Minecraft.func_71410_x()).field_71466_p;
/* 29 */     this.entity = entity;
/* 30 */     this.dummy = dummy;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/* 35 */     matrixStack.func_227860_a_();
/* 36 */     if (this.field_230694_p_) {
/* 37 */       this.field_230692_n_ = (this.field_230693_o_ && mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/*    */       
/* 39 */       this.fontColor = Color.WHITE.getRGB();
/* 40 */       if (this.field_230692_n_) {
/* 41 */         matrixStack.func_227861_a_(0.0D, -0.5D, 0.0D);
/* 42 */         this.fontColor = Color.ORANGE.getRGB();
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 48 */       renderEntityBust(this.entity, matrixStack, this.field_230690_l_ + this.field_230688_j_ / 2, this.field_230691_m_ + this.field_230689_k_ / 2 + 42);
/*    */     } 
/* 50 */     matrixStack.func_227865_b_();
/*    */   }
/*    */   
/*    */   private void renderEntityBust(@Nullable LivingEntity entity, MatrixStack matrixStack, int posX, int posY) {
/* 54 */     if (entity != null) {
/* 55 */       RendererHelper.drawLivingBust(entity, matrixStack, posX, posY, 30, -30, 10, false);
/* 56 */       String entityName = getEntityName(entity);
/* 57 */       WyHelper.drawStringWithBorder(this.font, matrixStack, entityName, posX - this.font.func_78256_a(entityName) / 2, posY - 30, this.fontColor);
/*    */     } else {
/*    */       
/* 60 */       RendererHelper.drawLivingBust(this.dummy, matrixStack, posX, posY, 30, -30, 10, true);
/* 61 */       String entityName = ModI18n.GUI_SELECT_ONE.getString();
/* 62 */       WyHelper.drawStringWithBorder(this.font, matrixStack, entityName, posX - this.font.func_78256_a(entityName) / 2, posY - 30, this.fontColor);
/*    */     } 
/*    */   }
/*    */   
/*    */   private String getEntityName(LivingEntity entity) {
/* 67 */     if (entity instanceof PlayerEntity) {
/* 68 */       return ((PlayerEntity)entity).func_146103_bH().getName();
/*    */     }
/* 70 */     return entity.func_145748_c_().getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\GroupMemberButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */