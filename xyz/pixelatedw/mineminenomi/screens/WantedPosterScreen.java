/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class WantedPosterScreen
/*     */   extends Screen
/*     */ {
/*     */   private WantedPosterData wantedPosterData;
/*     */   private String name;
/*     */   private JollyRoger jollyRoger;
/*     */   private ResourceLocation background;
/*     */   private ResourceLocation skin;
/*     */   
/*     */   public WantedPosterScreen(WantedPosterData wantedData) {
/*  35 */     super(StringTextComponent.field_240750_d_);
/*     */     
/*  37 */     this.field_230706_i_ = Minecraft.func_71410_x();
/*     */     
/*  39 */     this.wantedPosterData = wantedData;
/*     */     
/*  41 */     this.name = this.wantedPosterData.getOwnerName();
/*     */     
/*  43 */     String background = this.wantedPosterData.getBackground();
/*  44 */     this.background = new ResourceLocation("mineminenomi", "textures/gui/wantedposters/backgrounds/" + background + ".jpg");
/*     */     
/*  46 */     this.wantedPosterData.getOwnerCrew().ifPresent(crew -> this.jollyRoger = crew.getJollyRoger());
/*     */     
/*  48 */     if (this.wantedPosterData.getOwnerProfile().isPresent()) {
/*  49 */       Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.func_71410_x().func_152342_ad().func_152788_a(this.wantedPosterData.getOwnerProfile().get());
/*  50 */       if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/*  51 */         this.skin = Minecraft.func_71410_x().func_152342_ad().func_152792_a(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*     */       } else {
/*  53 */         this.skin = DefaultPlayerSkin.func_177334_a(this.wantedPosterData.getOwnerId());
/*     */       }
/*     */     
/*  56 */     } else if (this.wantedPosterData.getOwnerTexture().isPresent()) {
/*  57 */       this.skin = this.wantedPosterData.getOwnerTexture().get();
/*     */     } else {
/*  59 */       this.skin = DefaultPlayerSkin.func_177334_a(this.wantedPosterData.getOwnerId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int x, int y, float f) {
/*  67 */     func_230446_a_(matrixStack);
/*  68 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  70 */     int posX = (this.field_230708_k_ - 256) / 2;
/*  71 */     int posY = (this.field_230709_l_ - 256) / 2;
/*     */ 
/*     */     
/*  74 */     GL11.glTranslated((posX + 60), (posY + 10), 0.0D);
/*  75 */     GL11.glTranslated(128.0D, 128.0D, 512.0D);
/*  76 */     GL11.glScaled(1.0D, 0.9D, 0.0D);
/*  77 */     GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */ 
/*     */     
/*  80 */     this.field_230706_i_.func_110434_K().func_110577_a(ModResources.BOUNTY_POSTER_LARGE);
/*  81 */     GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 0, 0, 220, 250, 0.0F);
/*     */ 
/*     */     
/*  84 */     GL11.glTranslated(67.0D, 150.0D, 0.0D);
/*  85 */     GL11.glTranslated(128.0D, 128.0D, 512.0D);
/*  86 */     GL11.glScaled(1.5D, 1.6D, 0.0D);
/*  87 */     GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */ 
/*     */     
/*  90 */     GL11.glPushMatrix();
/*     */     
/*  92 */     this.field_230706_i_.func_110434_K().func_110577_a(this.background);
/*     */     
/*  94 */     GL11.glScaled(0.34D, 0.245D, 0.0D);
/*  95 */     GuiUtils.drawTexturedModalRect(matrixStack, 23, -57, 0, 0, 256, 256, 2.0F);
/*  96 */     GL11.glDisable(3042);
/*     */     
/*  98 */     this.field_230706_i_.func_110434_K().func_110577_a(this.skin);
/*     */     
/* 100 */     GL11.glScaled(4.25D, 5.5D, 0.0D);
/* 101 */     GuiUtils.drawTexturedModalRect(matrixStack, 20, -3, 32, 32, 32, 32, 3.0F);
/* 102 */     GuiUtils.drawTexturedModalRect(matrixStack, 20, -3, 160, 32, 32, 32, 4.0F);
/*     */     
/* 104 */     RenderSystem.pushMatrix();
/*     */     
/* 106 */     RenderSystem.enableBlend();
/* 107 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/* 109 */     double d = 0.08D;
/* 110 */     RenderSystem.scaled(d, d, d);
/* 111 */     RenderSystem.translated(550.0D, 190.0D, 0.0D);
/*     */     
/* 113 */     if (this.wantedPosterData.getFaction().equals(ModValues.PIRATE)) {
/*     */       
/* 115 */       if (this.jollyRoger != null) {
/* 116 */         RendererHelper.drawPlayerJollyRoger(this.jollyRoger, matrixStack);
/*     */       }
/* 118 */     } else if (this.wantedPosterData.getFaction().equals(ModValues.REVOLUTIONARY)) {
/*     */       
/* 120 */       this.field_230706_i_.func_110434_K().func_110577_a(ModResources.REVOLUTIONARY_ARMY_ICON);
/* 121 */       RenderSystem.scaled(2.0D, 2.0D, 2.0D);
/* 122 */       RenderSystem.translated(-55.0D, -40.0D, 0.0D);
/* 123 */       GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 256, 256, 256, 256, 0.0F);
/*     */     } 
/*     */     
/* 126 */     RenderSystem.disableBlend();
/*     */     
/* 128 */     RenderSystem.popMatrix();
/*     */     
/* 130 */     if (this.wantedPosterData.isExpired()) {
/*     */       
/* 132 */       RenderSystem.pushMatrix();
/*     */       
/* 134 */       this.field_230706_i_.func_110434_K().func_110577_a(ModResources.EXPIRED);
/*     */       
/* 136 */       d = 0.2D;
/* 137 */       RenderSystem.scaled(d + 0.022D, d, d);
/* 138 */       RenderSystem.translated(50.0D, -47.0D, 0.0D);
/* 139 */       GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 16, 16, 256, 256, 0.0F);
/*     */       
/* 141 */       RenderSystem.popMatrix();
/*     */     } 
/*     */     
/* 144 */     GL11.glPopMatrix();
/*     */     
/* 146 */     this.field_230706_i_.func_110434_K().func_110577_a(ModResources.CURRENCIES);
/* 147 */     GuiUtils.drawTexturedModalRect(matrixStack, -2, 63, 0, 0, 32, 32, 1.0F);
/*     */     
/* 149 */     float scale = 1.0F;
/*     */     
/* 151 */     int nameLength = this.name.length();
/* 152 */     if (nameLength > 13) {
/* 153 */       int extraLetters = nameLength - 11;
/* 154 */       float exp = extraLetters / 2.3F;
/* 155 */       float extraSpace = (float)Math.pow(0.8899999856948853D, exp);
/* 156 */       scale = 1.0F * extraSpace;
/*     */     } 
/*     */     
/* 159 */     GL11.glPushMatrix();
/* 160 */     GL11.glTranslated(45.0D - this.field_230706_i_.field_71466_p.func_78256_a(this.name) / 2.0D * scale, 62.0D, 0.0D);
/* 161 */     GL11.glScaled(scale, scale, scale);
/* 162 */     this.field_230706_i_.field_71466_p.func_238421_b_(matrixStack, TextFormatting.BOLD + this.name, 0.0F, 0.0F, 5321747);
/* 163 */     GL11.glPopMatrix();
/*     */     
/* 165 */     boolean flag = (this.wantedPosterData.getBountyString().length() > 10);
/* 166 */     if (flag) {
/*     */       
/* 168 */       GL11.glPushMatrix();
/* 169 */       GL11.glTranslated(-21.0D, -5.0D, 0.0D);
/* 170 */       GL11.glTranslated(128.0D, 128.0D, 512.0D);
/* 171 */       GL11.glScaled(0.82D, 0.89D, 0.0D);
/* 172 */       GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */     } 
/*     */     
/* 175 */     this.field_230706_i_.field_71466_p.func_238421_b_(matrixStack, TextFormatting.BOLD + this.wantedPosterData.getBountyString(), 22.0F, 76.0F, WyHelper.hexToRGB("513413").getRGB());
/* 176 */     if (flag) {
/* 177 */       GL11.glPopMatrix();
/*     */     }
/*     */     
/* 180 */     GL11.glTranslated(-24.0D, -2.0D, 0.0D);
/* 181 */     GL11.glTranslated(128.0D, 128.0D, 512.0D);
/* 182 */     GL11.glScaled(0.78D, 0.92D, 0.0D);
/* 183 */     GL11.glTranslated(-128.0D, -128.0D, -512.0D);
/*     */     
/* 185 */     this.field_230706_i_.field_71466_p.func_238421_b_(matrixStack, TextFormatting.BOLD + this.wantedPosterData.getDate(), (36 - this.field_230706_i_.field_71466_p.func_78256_a(this.wantedPosterData.getDate()) / 2), 90.0F, WyHelper.hexToRGB("513413").getRGB());
/*     */     
/* 187 */     super.func_230430_a_(matrixStack, x, y, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_231177_au__() {
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   public static void open(WantedPosterData wantedData) {
/* 196 */     Minecraft.func_71410_x().func_147108_a(new WantedPosterScreen(wantedData));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\WantedPosterScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */