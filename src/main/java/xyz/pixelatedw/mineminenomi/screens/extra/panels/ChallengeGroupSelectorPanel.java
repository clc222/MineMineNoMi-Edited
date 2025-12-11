/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.panels;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CSendChallengeInvitationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ChallengeGroupSelectorPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private static final int ENTRY_HEIGHT = 55;
/*     */   private static final int ENTRY_WIDTH = 120;
/*     */   private static final int SETUP_TIME = 20;
/*     */   private final Color topColor;
/*  26 */   private static final Color BOTTOM_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.8F);
/*     */   
/*     */   private final PlayerEntity player;
/*     */   private final ChallengesScreen parent;
/*     */   private final List<LivingEntity> nearbyGroupMembers;
/*     */   private int setupTime;
/*     */   
/*     */   public ChallengeGroupSelectorPanel(ChallengesScreen parent, PlayerEntity player, List<Integer> nearbyIds) {
/*  34 */     super(parent.getMinecraft(), 260, 170, parent.field_230709_l_ / 2 - 70, parent.field_230708_k_ / 2 - 46);
/*     */     
/*  36 */     this.player = player;
/*  37 */     this.parent = parent;
/*  38 */     this.setupTime = 20;
/*  39 */     this.topColor = FactionHelper.getFactionColor(EntityStatsCapability.get((LivingEntity)player));
/*     */     
/*  41 */     this.nearbyGroupMembers = (List<LivingEntity>)nearbyIds.stream().map(id -> player.field_70170_p.func_73045_a(id.intValue())).filter(e -> e instanceof LivingEntity).map(e -> (LivingEntity)e).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231048_c_(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  51 */     return this.nearbyGroupMembers.size() * 27 + 55 - 25;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  56 */     return 55;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGradientRect(MatrixStack matrixStack, int left, int top, int right, int bottom, int color1, int color2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  66 */     if (this.setupTime > 0) {
/*  67 */       this.setupTime--;
/*     */     } else {
/*     */       
/*  70 */       super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawPanel(MatrixStack matrixStack, int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  76 */     int y = relativeY + 65;
/*  77 */     int x = this.parent.field_230708_k_ / 2 + 20;
/*     */     
/*  79 */     LivingEntity entity = findEntry(mouseX, mouseY);
/*     */     
/*  81 */     int row = 0;
/*  82 */     int xOffset = 0;
/*  83 */     int yOffset = 0;
/*  84 */     for (LivingEntity target : this.nearbyGroupMembers) {
/*  85 */       if (row == 2) {
/*  86 */         xOffset = 0;
/*  87 */         yOffset += 55;
/*  88 */         row = 0;
/*     */       } 
/*     */       
/*  91 */       boolean isHovered = (entity != null && entity.equals(target) && func_231047_b_(mouseX, mouseY));
/*     */       
/*  93 */       if (isHovered) {
/*  94 */         GuiUtils.drawGradientRect(matrixStack.func_227866_c_().func_227870_a_(), 0, x - 60 + xOffset, relativeY + yOffset, x + 60 + xOffset, relativeY + yOffset + 55, this.topColor.getRGB(), BOTTOM_COLOR.getRGB());
/*  95 */         matrixStack.func_227860_a_();
/*  96 */         matrixStack.func_227861_a_(0.0D, -2.0D, 0.0D);
/*     */       } 
/*     */       
/*  99 */       if (y + yOffset > this.top + 55 && y + yOffset < this.bottom + 55) {
/* 100 */         this.parent.renderEntityBust(target, matrixStack, x + xOffset, y + 5 + yOffset);
/*     */       }
/*     */       
/* 103 */       if (isHovered) {
/* 104 */         matrixStack.func_227865_b_();
/*     */       }
/*     */       
/* 107 */       xOffset += 120;
/* 108 */       row++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private LivingEntity findEntry(int mouseX, int mouseY) {
/* 113 */     double yOffset = ((mouseY - this.top - 4) + this.scrollDistance);
/* 114 */     double xOffset = (mouseX - this.left - 6);
/*     */     
/* 116 */     if (yOffset <= 0.0D) {
/* 117 */       return null;
/*     */     }
/*     */     
/* 120 */     if (xOffset <= 0.0D) {
/* 121 */       return null;
/*     */     }
/*     */     
/* 124 */     int lineIdx = (int)(yOffset / 55.0D);
/* 125 */     int rowIdx = (int)(xOffset / 120.0D);
/*     */     
/* 127 */     if (rowIdx > 1) {
/* 128 */       return null;
/*     */     }
/*     */     
/* 131 */     int id = lineIdx * 2 + rowIdx;
/*     */     
/* 133 */     if (id < 0 || id >= this.nearbyGroupMembers.size()) {
/* 134 */       return null;
/*     */     }
/*     */     
/* 137 */     LivingEntity entry = this.nearbyGroupMembers.get(id);
/* 138 */     if (entry != null) {
/* 139 */       return entry;
/*     */     }
/*     */     
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231044_a_(double mouseX, double mouseY, int button) {
/* 147 */     LivingEntity entity = findEntry((int)mouseX, (int)mouseY);
/*     */     
/* 149 */     if (entity != null && func_231047_b_(mouseX, mouseY) && button == 0 && this.setupTime <= 0) {
/* 150 */       if (entity instanceof PlayerEntity) {
/* 151 */         WyNetwork.sendToServer(new CSendChallengeInvitationPacket((PlayerEntity)entity, this.parent.getSelectedChallenge().getCore(), this.parent.getSelectedMemberSlot()));
/* 152 */         this.parent.showGroupStep();
/*     */       } else {
/*     */         
/* 155 */         this.parent.setGroupMember(entity);
/*     */       } 
/* 157 */       return true;
/*     */     } 
/*     */     
/* 160 */     return super.func_231044_a_(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231047_b_(double mouseX, double mouseY) {
/* 165 */     return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width) && mouseY < (this.top + this.height));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\panels\ChallengeGroupSelectorPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */