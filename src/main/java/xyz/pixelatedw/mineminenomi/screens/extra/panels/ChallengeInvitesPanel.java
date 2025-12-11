/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeInvitation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CAcceptChallengeInvitationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class ChallengeInvitesPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private static final int ENTRY_HEIGHT = 55;
/*  23 */   private static final Color SELECTION_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.2F);
/*     */   
/*     */   private final PlayerEntity player;
/*     */   private final World world;
/*     */   private final ChallengesScreen parent;
/*     */   private List<ChallengeInvitation> invites;
/*     */   
/*     */   public ChallengeInvitesPanel(ChallengesScreen parent, PlayerEntity player, List<ChallengeInvitation> invites) {
/*  31 */     super(parent.getMinecraft(), 260, 170, parent.field_230709_l_ / 2 - 70, parent.field_230708_k_ / 2 - 46);
/*     */     
/*  33 */     this.world = player.field_70170_p;
/*  34 */     this.player = player;
/*  35 */     this.parent = parent;
/*  36 */     this.invites = invites;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  46 */     return this.invites.size() * 55 - 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  51 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawGradientRect(MatrixStack matrixStack, int left, int top, int right, int bottom, int color1, int color2) {
/*  56 */     GuiUtils.drawGradientRect(matrixStack.func_227866_c_().func_227870_a_(), 0, left, top, right, bottom, color1, color2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  61 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  66 */     int y = relativeY + 10;
/*  67 */     int x = this.parent.field_230708_k_ / 2 + 20;
/*     */     
/*  69 */     ChallengeInvitation hoverTarget = findEntry(mouseX, mouseY);
/*     */     
/*  71 */     int yOffset = 0;
/*  72 */     for (ChallengeInvitation invite : this.invites) {
/*  73 */       boolean isHovered = (hoverTarget != null && hoverTarget.equals(invite) && func_231047_b_(mouseX, mouseY));
/*     */       
/*  75 */       if (isHovered) {
/*  76 */         GuiUtils.drawGradientRect(matrixStack.func_227866_c_().func_227870_a_(), 0, x - this.right, relativeY + yOffset, x + this.width, relativeY + yOffset + 55, SELECTION_COLOR.getRGB(), SELECTION_COLOR.getRGB());
/*  77 */         matrixStack.func_227860_a_();
/*  78 */         matrixStack.func_227861_a_(0.0D, -2.0D, 0.0D);
/*     */       } 
/*     */       
/*  81 */       World world = this.player.func_130014_f_();
/*  82 */       PlayerEntity player = world.func_217371_b(invite.getSenderId());
/*  83 */       if (player == null) {
/*     */         continue;
/*     */       }
/*     */       
/*  87 */       RendererHelper.drawPlayerFace(player, matrixStack, x - 53, y + yOffset + 3, 32, 32);
/*  88 */       WyHelper.drawStringWithBorder((this.parent.getMinecraft()).field_71466_p, matrixStack, player.func_146103_bH().getName(), x - 10, y + yOffset, -1);
/*  89 */       WyHelper.drawStringWithBorder((this.parent.getMinecraft()).field_71466_p, matrixStack, invite.getChallenge().getLocalizedTitle().getString(), x - 10, y + yOffset + 15, -1);
/*  90 */       WyHelper.drawStringWithBorder((this.parent.getMinecraft()).field_71466_p, matrixStack, invite.getChallenge().getLocalizedObjective().getString(), x - 10, y + yOffset + 30, -1);
/*     */       
/*  92 */       if (isHovered) {
/*  93 */         matrixStack.func_227865_b_();
/*     */       }
/*     */       
/*  96 */       yOffset += 55;
/*     */     } 
/*     */   }
/*     */   
/*     */   private ChallengeInvitation findEntry(int mouseX, int mouseY) {
/* 101 */     double yOffset = ((mouseY - this.top - 4) + this.scrollDistance);
/*     */     
/* 103 */     if (yOffset <= 0.0D) {
/* 104 */       return null;
/*     */     }
/*     */     
/* 107 */     int lineIdx = (int)(yOffset / 55.0D);
/*     */     
/* 109 */     if (lineIdx < 0 || lineIdx >= this.invites.size()) {
/* 110 */       return null;
/*     */     }
/*     */     
/* 113 */     ChallengeInvitation entry = this.invites.get(lineIdx);
/* 114 */     if (entry != null) {
/* 115 */       return entry;
/*     */     }
/*     */     
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/* 123 */     ChallengeInvitation entry = findEntry((int)mouseX, (int)mouseY);
/*     */     
/* 125 */     if (entry != null && func_231047_b_(mouseX, mouseY) && button == 0) {
/* 126 */       WyNetwork.sendToServer(new CAcceptChallengeInvitationPacket(entry));
/* 127 */       this.parent.getMinecraft().func_147108_a(null);
/* 128 */       return true;
/*     */     } 
/*     */     
/* 131 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231047_b_(double mouseX, double mouseY) {
/* 136 */     return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width) && mouseY < (this.top + this.height));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\ChallengeInvitesPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */