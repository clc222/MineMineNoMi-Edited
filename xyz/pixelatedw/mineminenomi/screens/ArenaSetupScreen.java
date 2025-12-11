/*    */ package xyz.pixelatedw.mineminenomi.screens;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class ArenaSetupScreen
/*    */   extends Screen
/*    */ {
/* 19 */   private String generatedArena = "";
/*    */ 
/*    */   
/*    */   protected ArenaSetupScreen(String arenaName) {
/* 23 */     super((ITextComponent)new StringTextComponent(""));
/* 24 */     this.generatedArena = arenaName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*    */     String s;
/* 30 */     int posX = (this.field_230708_k_ - 256) / 2;
/* 31 */     int posY = (this.field_230709_l_ - 256) / 2;
/*    */     
/* 33 */     func_231165_f_(0);
/* 34 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 36 */     String message = "Generating \"" + this.generatedArena + "\" Arena...";
/* 37 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + message, posX - this.field_230712_o_.func_78256_a(message) / 2 + 120, posY + 120, -1);
/*    */ 
/*    */     
/* 40 */     switch ((int)(Util.func_211177_b() / 300L % 4L)) {
/*    */       
/*    */       default:
/* 43 */         s = "O o o";
/*    */         break;
/*    */       case 1:
/*    */       case 3:
/* 47 */         s = "o O o";
/*    */         break;
/*    */       case 2:
/* 50 */         s = "o o O";
/*    */         break;
/*    */     } 
/* 53 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + s, this.field_230708_k_ / 2 - 10, posY + 140, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void open(String arenaName) {
/* 58 */     Minecraft.func_71410_x().func_147108_a(new ArenaSetupScreen(arenaName));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\ArenaSetupScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */