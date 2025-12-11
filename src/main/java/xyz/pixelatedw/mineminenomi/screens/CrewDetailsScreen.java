/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CLeaveCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenJollyRogerCreatorScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.PlankButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.panels.CrewMembersListPanel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class CrewDetailsScreen extends Screen {
/*     */   private PlayerEntity player;
/*     */   private JollyRoger jollyRoger;
/*     */   
/*     */   public CrewDetailsScreen(Crew crew) {
/*  36 */     super((ITextComponent)new StringTextComponent(""));
/*  37 */     this.field_230706_i_ = Minecraft.func_71410_x();
/*  38 */     this.player = (PlayerEntity)this.field_230706_i_.field_71439_g;
/*  39 */     this.crew = crew;
/*     */   }
/*     */   private final Crew crew; private CrewMembersListPanel membersListPanel;
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/*  44 */     func_230446_a_(matrixStack);
/*  45 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  47 */     int posX = (this.field_230708_k_ - 256) / 2;
/*  48 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */ 
/*     */     
/*  51 */     posY -= 20;
/*     */     
/*  53 */     String nameString = (new TranslationTextComponent(ModI18n.GUI_NAME)).getString();
/*  54 */     String jollyRogerString = (new TranslationTextComponent(ModI18n.GUI_CREW_JOLLY_ROGER)).getString();
/*  55 */     String membersString = (new TranslationTextComponent(ModI18n.GUI_CREW_MEMBERS)).getString();
/*     */     
/*  57 */     String crewActual = "";
/*  58 */     if (this.crew == null) {
/*     */       return;
/*     */     }
/*  61 */     crewActual = this.crew.getName();
/*     */     
/*  63 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + nameString + ": " + TextFormatting.RESET + crewActual, posX - 50, posY + 50, -1);
/*     */     
/*  65 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + jollyRogerString + ": ", posX - 50, posY + 70, -1);
/*  66 */     WyHelper.drawStringWithBorder(this.field_230712_o_, matrixStack, TextFormatting.BOLD + membersString + ": ", posX + 150, posY + 50, -1);
/*     */     
/*  68 */     matrixStack.func_227860_a_();
/*     */     
/*  70 */     RenderSystem.enableBlend();
/*  71 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  73 */     float scale = 0.4F;
/*  74 */     matrixStack.func_227861_a_((posX - 110), (posY + 15), 1.0D);
/*  75 */     matrixStack.func_227861_a_(128.0D, 128.0D, 0.0D);
/*  76 */     matrixStack.func_227862_a_(scale, scale, scale);
/*  77 */     matrixStack.func_227861_a_(-128.0D, -128.0D, 0.0D);
/*     */     
/*  79 */     if (this.jollyRoger != null) {
/*  80 */       RendererHelper.drawPlayerJollyRoger(this.jollyRoger, matrixStack);
/*     */     }
/*  82 */     RenderSystem.disableBlend();
/*     */     
/*  84 */     matrixStack.func_227865_b_();
/*     */     
/*  86 */     if (this.membersListPanel != null) {
/*  87 */       this.membersListPanel.func_230430_a_(matrixStack, x, y, f);
/*     */     }
/*     */     
/*  90 */     super.func_230430_a_(matrixStack, x, y, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_231160_c_() {
/*  95 */     this.field_230705_e_.clear();
/*  96 */     this.field_230710_m_.clear();
/*     */     
/*  98 */     if (this.crew == null) {
/*     */       return;
/*     */     }
/*     */     
/* 102 */     this.membersListPanel = new CrewMembersListPanel(this, this.crew.getMembers());
/* 103 */     this.field_230705_e_.add(this.membersListPanel);
/* 104 */     func_231035_a_((IGuiEventListener)this.membersListPanel);
/*     */     
/* 106 */     UUID captainUUID = this.crew.getCaptain().getUUID();
/* 107 */     PlayerEntity crewCaptain = this.field_230706_i_.field_71441_e.func_217371_b(captainUUID);
/*     */     
/* 109 */     this.jollyRoger = this.crew.getJollyRoger();
/*     */     
/* 111 */     int posX = (this.field_230708_k_ - 256) / 2 - 50;
/* 112 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */     
/* 114 */     posY -= 20;
/* 115 */     posX += 80;
/* 116 */     func_230480_a_((Widget)new PlankButton(posX, posY + 240, 80, 26, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_LEAVE), b -> {
/*     */             WyNetwork.sendToServer(new CLeaveCrewPacket());
/*     */             
/*     */             Minecraft.func_71410_x().func_147108_a(null);
/*     */           }));
/* 121 */     if (crewCaptain != null && this.player == crewCaptain) {
/* 122 */       posX += 90;
/* 123 */       func_230480_a_((Widget)new PlankButton(posX, posY + 240, 120, 26, (ITextComponent)new TranslationTextComponent(ModI18n.GUI_CHANGE_JOLLY_ROGER), b -> WyNetwork.sendToServer(new COpenJollyRogerCreatorScreenPacket())));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void open(Crew crew) {
/* 128 */     Minecraft.func_71410_x().func_147108_a(new CrewDetailsScreen(crew));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\CrewDetailsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */