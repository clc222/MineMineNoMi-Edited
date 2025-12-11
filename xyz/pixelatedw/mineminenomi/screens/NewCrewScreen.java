/*    */ package xyz.pixelatedw.mineminenomi.screens;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.IGuiEventListener;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.client.gui.widget.TextFieldWidget;
/*    */ import net.minecraft.client.gui.widget.Widget;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CCreateCrewPacket;
/*    */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.SimpleButton;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class NewCrewScreen
/*    */   extends Screen {
/*    */   private PlayerEntity player;
/*    */   private TextFieldWidget nameEdit;
/*    */   
/*    */   public NewCrewScreen() {
/* 28 */     super((ITextComponent)new StringTextComponent(""));
/* 29 */     this.player = (PlayerEntity)(Minecraft.func_71410_x()).field_71439_g;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/* 35 */     func_230446_a_(matrixStack);
/* 36 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 38 */     this.nameEdit.func_230430_a_(matrixStack, x, y, f);
/*    */     
/* 40 */     super.func_230430_a_(matrixStack, x, y, f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_231160_c_() {
/* 46 */     this.field_230706_i_.field_195559_v.func_197967_a(true);
/* 47 */     int posX = this.field_230708_k_ / 2;
/* 48 */     int posY = this.field_230709_l_ / 2;
/*    */     
/* 50 */     this.nameEdit = new TextFieldWidget(this.field_230712_o_, posX - 152, posY - 10, 300, 20, (ITextComponent)new StringTextComponent(""));
/* 51 */     this.nameEdit.func_146203_f(255);
/* 52 */     this.nameEdit.func_146191_b(this.player.func_200200_C_().getString() + "'s Crew");
/* 53 */     this.field_230705_e_.add(this.nameEdit);
/* 54 */     func_231035_a_((IGuiEventListener)this.nameEdit);
/*    */     
/* 56 */     String createString = "Create";
/* 57 */     SimpleButton createCrewButton = new SimpleButton(posX - 30, posY + 50, 60, 16, (ITextComponent)new TranslationTextComponent(createString), btn -> createCrew());
/* 58 */     func_230480_a_((Widget)createCrewButton);
/*    */   }
/*    */ 
/*    */   
/*    */   private void createCrew() {
/* 63 */     WyNetwork.sendToServer(new CCreateCrewPacket(this.nameEdit.func_146179_b()));
/* 64 */     func_231175_as__();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_231152_a_(Minecraft minecraft, int x, int y) {
/* 70 */     String crewName = this.nameEdit.func_146179_b();
/* 71 */     func_231158_b_(minecraft, x, y);
/* 72 */     this.nameEdit.func_146191_b(crewName);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_231023_e_() {
/* 78 */     this.nameEdit.func_146178_a();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void open() {
/* 83 */     Minecraft.func_71410_x().func_147108_a(new NewCrewScreen());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\NewCrewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */