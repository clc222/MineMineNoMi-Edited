/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CKickFromCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.CrewDetailsScreen;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.buttons.SimpleButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CrewMembersListPanel extends ScrollPanel {
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   private final CrewDetailsScreen parent;
/*     */   private final List<Crew.Member> displayedMembers;
/*  24 */   private final Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*     */   public CrewMembersListPanel(CrewDetailsScreen parent, List<Crew.Member> list) {
/*  27 */     super(parent.getMinecraft(), 220, 160, parent.field_230709_l_ / 2 - 80, parent.field_230708_k_ / 2 + 10);
/*     */     
/*  29 */     this.parent = parent;
/*  30 */     this.displayedMembers = list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  40 */     return this.displayedMembers.size() * 20 - 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  45 */     return 12;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGradientRect(MatrixStack matrixStack, int left, int top, int right, int bottom, int color1, int color2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  55 */     super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  60 */     int y = relativeY;
/*  61 */     int x = this.parent.field_230708_k_ / 2 + 40;
/*     */     
/*  63 */     int yOffset = 0;
/*  64 */     for (Crew.Member member : this.displayedMembers) {
/*  65 */       String memberName = member.getUsername();
/*  66 */       if (memberName.length() >= 20) {
/*  67 */         memberName = memberName.substring(0, 20) + "...";
/*     */       }
/*  69 */       memberName = memberName + (member.isCaptain() ? (" (" + I18n.func_135052_a(ModI18n.CREW_CAPTAIN, new Object[0]) + ")") : "");
/*  70 */       WyHelper.drawStringWithBorder(this.mc.field_71466_p, matrixStack, memberName, x, y + 1 + yOffset, -1);
/*     */       
/*  72 */       if (!member.isCaptain()) {
/*  73 */         SimpleButton kickButton = new SimpleButton(x - 20, y + yOffset, 10, 10, (ITextComponent)new StringTextComponent("X"), b -> { 
/*  74 */             }); kickButton.setHoverTextColor("#FF0000");
/*  75 */         kickButton.func_230430_a_(matrixStack, mouseX, mouseY, 0.0F);
/*     */       } 
/*     */       
/*  78 */       yOffset += 20;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Crew.Member findEntry(int mouseX, int mouseY) {
/*  83 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/*     */     
/*  85 */     if (offset <= 0.0D) {
/*  86 */       return null;
/*     */     }
/*     */     
/*  89 */     int lineIdx = (int)(offset / 20.0D);
/*  90 */     if (lineIdx >= this.displayedMembers.size()) {
/*  91 */       return null;
/*     */     }
/*     */     
/*  94 */     Crew.Member entry = this.displayedMembers.get(lineIdx);
/*  95 */     if (entry != null) {
/*  96 */       return entry;
/*     */     }
/*     */     
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/* 104 */     Crew.Member member = findEntry((int)mouseX, (int)mouseY);
/*     */     
/* 106 */     if (member != null && func_231047_b_(mouseX, mouseY) && button == 0)
/*     */     {
/* 108 */       if (mouseX > (this.left + 10) && mouseX < (this.left + 20)) {
/* 109 */         WyNetwork.sendToServer(new CKickFromCrewPacket(member.getUUID()));
/* 110 */         this.displayedMembers.removeIf(mem -> mem.getUUID().equals(member.getUUID()));
/*     */       } 
/*     */     }
/*     */     
/* 114 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231047_b_(double mouseX, double mouseY) {
/* 119 */     return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\CrewMembersListPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */