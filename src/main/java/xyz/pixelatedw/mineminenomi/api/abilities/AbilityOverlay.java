/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AbilityOverlay
/*     */ {
/*     */   private int id;
/*  11 */   private ResourceLocation texture = null;
/*  12 */   private RenderType renderType = RenderType.NORMAL;
/*  13 */   private Color color = WyHelper.hexToRGB("#FFFFFFFF");
/*  14 */   private OverlayPart overlayPart = OverlayPart.BODY;
/*     */   
/*     */   private AbilityOverlay() {
/*  17 */     this.id = (new Random()).nextInt();
/*     */   }
/*     */   
/*     */   private AbilityOverlay setTexture(ResourceLocation texture) {
/*  21 */     this.texture = texture;
/*  22 */     return this;
/*     */   }
/*     */   
/*     */   private AbilityOverlay setColor(Color color) {
/*  26 */     this.color = color;
/*  27 */     return this;
/*     */   }
/*     */   
/*     */   private AbilityOverlay setRenderType(RenderType type) {
/*  31 */     this.renderType = type;
/*  32 */     return this;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  36 */     return this.texture;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/*  40 */     return this.color;
/*     */   }
/*     */   
/*     */   public RenderType getRenderType() {
/*  44 */     return this.renderType;
/*     */   }
/*     */   
/*     */   private void setOverlayPart(OverlayPart part) {
/*  48 */     this.overlayPart = part;
/*     */   }
/*     */   
/*     */   public OverlayPart getOverlayPart() {
/*  52 */     return this.overlayPart;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  57 */     int result = this.id ^ this.id >>> 32;
/*  58 */     result = 31 * result + ((this.texture == null) ? 0 : this.texture.hashCode());
/*  59 */     result = 31 * result + this.color.hashCode();
/*  60 */     result = 31 * result + this.overlayPart.hashCode();
/*  61 */     result = 31 * result + this.renderType.hashCode();
/*  62 */     return result;
/*     */   }
/*     */   
/*     */   public enum RenderType {
/*  66 */     NORMAL, ENERGY;
/*     */   }
/*     */   
/*     */   public enum OverlayPart {
/*  70 */     BODY,
/*  71 */     LIMB,
/*  72 */     HEAD,
/*  73 */     ARM,
/*  74 */     LEG;
/*     */   }
/*     */   
/*     */   public static class Builder {
/*  78 */     private ResourceLocation texture = null;
/*  79 */     private AbilityOverlay.RenderType renderType = AbilityOverlay.RenderType.NORMAL;
/*  80 */     private Color color = WyHelper.hexToRGB("#FFFFFFFF");
/*  81 */     private AbilityOverlay.OverlayPart overlayPart = AbilityOverlay.OverlayPart.BODY;
/*     */     
/*     */     public Builder setTexture(ResourceLocation texture) {
/*  84 */       this.texture = texture;
/*  85 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setColor(Color color) {
/*  89 */       this.color = color;
/*  90 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setColor(String hex) {
/*  94 */       this.color = WyHelper.hexToRGB(hex);
/*  95 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setRenderType(AbilityOverlay.RenderType type) {
/*  99 */       this.renderType = type;
/* 100 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setOverlayPart(AbilityOverlay.OverlayPart part) {
/* 104 */       this.overlayPart = part;
/* 105 */       return this;
/*     */     }
/*     */     
/*     */     public AbilityOverlay build() {
/* 109 */       AbilityOverlay overlay = new AbilityOverlay();
/* 110 */       overlay.setTexture(this.texture);
/* 111 */       overlay.setColor(this.color);
/* 112 */       overlay.setRenderType(this.renderType);
/* 113 */       overlay.setOverlayPart(this.overlayPart);
/* 114 */       return overlay;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */