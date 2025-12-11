/*     */ package xyz.pixelatedw.mineminenomi.api.crew;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class JollyRogerElement
/*     */   extends ForgeRegistryEntry<JollyRogerElement>
/*     */ {
/*     */   private boolean canBeColored = false;
/*     */   @Deprecated
/*  22 */   private String color = "#FFFFFF";
/*     */   
/*  24 */   private int red = 255;
/*  25 */   private int green = 255;
/*  26 */   private int blue = 255;
/*  27 */   private Color fullColor = new Color(this.red, this.green, this.blue);
/*     */   private ResourceLocation texture;
/*  29 */   private List<ICanUse> canUseChecks = new ArrayList<>();
/*     */   private LayerType type;
/*     */   
/*     */   @Deprecated
/*     */   public JollyRogerElement(LayerType type) {
/*  34 */     this.type = type;
/*     */   }
/*     */   
/*     */   public JollyRogerElement(LayerType type, String texture) {
/*  38 */     this.type = type;
/*  39 */     String category = "bases";
/*  40 */     if (type == LayerType.BACKGROUND) {
/*  41 */       category = "backgrounds";
/*     */     }
/*  43 */     else if (type == LayerType.DETAIL) {
/*  44 */       category = "details";
/*     */     } 
/*  46 */     setTexture(new ResourceLocation("mineminenomi", "textures/jolly_rogers/" + category + "/" + texture + ".png"));
/*  47 */     ModJollyRogers.registerElement(this);
/*     */   }
/*     */   
/*     */   public static JollyRogerElement of(ResourceLocation res) {
/*  51 */     return (JollyRogerElement)ModRegistries.JOLLY_ROGER_ELEMENTS.getValue(res);
/*     */   }
/*     */   
/*     */   public boolean canBeColored() {
/*  55 */     return this.canBeColored;
/*     */   }
/*     */   
/*     */   public JollyRogerElement setCanBeColored() {
/*  59 */     this.canBeColored = true;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getColor() {
/*  65 */     return this.color;
/*     */   }
/*     */   
/*     */   public Color getFullColor() {
/*  69 */     return this.fullColor;
/*     */   }
/*     */   
/*     */   public int getRed() {
/*  73 */     return this.red;
/*     */   }
/*     */   
/*     */   public int getGreen() {
/*  77 */     return this.green;
/*     */   }
/*     */   
/*     */   public int getBlue() {
/*  81 */     return this.blue;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public JollyRogerElement setColor(String color) {
/*  86 */     this.color = color;
/*  87 */     return this;
/*     */   }
/*     */   
/*     */   public JollyRogerElement setColor(int red, int green, int blue) {
/*  91 */     this.red = red;
/*  92 */     this.green = green;
/*  93 */     this.blue = blue;
/*  94 */     this.fullColor = new Color(this.red, this.green, this.blue);
/*  95 */     return this;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  99 */     return this.texture;
/*     */   }
/*     */   
/*     */   public JollyRogerElement setTexture(ResourceLocation texture) {
/* 103 */     this.texture = texture;
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public JollyRogerElement addUseCheck(ICanUse check) {
/* 108 */     this.canUseChecks.add(check);
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public boolean canUse(PlayerEntity player, Crew crew) {
/* 113 */     boolean flag = true;
/* 114 */     for (ICanUse check : this.canUseChecks) {
/* 115 */       if (check != null && !check.canUse(player, crew)) {
/* 116 */         flag = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 121 */     return flag;
/*     */   }
/*     */   
/*     */   public LayerType getLayerType() {
/* 125 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object element) {
/* 130 */     if (!(element instanceof JollyRogerElement) || getTexture() == null || ((JollyRogerElement)element).getTexture() == null) {
/* 131 */       return false;
/*     */     }
/*     */     
/* 134 */     return getTexture().toString().equalsIgnoreCase(((JollyRogerElement)element).getTexture().toString());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public JollyRogerElement create() {
/*     */     try {
/* 140 */       return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */     }
/* 142 */     catch (Exception ex) {
/* 143 */       ex.printStackTrace();
/*     */       
/* 145 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(CompoundNBT nbt) {
/* 150 */     nbt.func_74757_a("canBeColored", canBeColored());
/* 151 */     nbt.func_74768_a("red", this.red);
/* 152 */     nbt.func_74768_a("green", this.green);
/* 153 */     nbt.func_74768_a("blue", this.blue);
/*     */   }
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 157 */     if (nbt.func_74767_n("canBeColored")) {
/* 158 */       setCanBeColored();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (nbt.func_150297_b("color", 8)) {
/* 164 */       Color color = WyHelper.hexToRGB(nbt.func_74779_i("color"));
/* 165 */       this.color = null;
/* 166 */       setColor(color.getRed(), color.getGreen(), color.getBlue());
/*     */     } else {
/*     */       
/* 169 */       int red = nbt.func_74762_e("red");
/* 170 */       int green = nbt.func_74762_e("green");
/* 171 */       int blue = nbt.func_74762_e("blue");
/* 172 */       setColor(red, green, blue);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum LayerType
/*     */   {
/* 181 */     BASE, BACKGROUND, DETAIL;
/*     */   }
/*     */   
/*     */   public static interface ICanUse extends Serializable {
/*     */     boolean canUse(PlayerEntity param1PlayerEntity, Crew param1Crew);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\crew\JollyRogerElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */