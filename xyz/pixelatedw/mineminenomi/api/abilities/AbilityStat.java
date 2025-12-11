/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.FloatRange;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.INumberRange;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.IntRange;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ 
/*     */ public class AbilityStat {
/*     */   private ITextComponent name;
/*     */   @Nullable
/*     */   private ITextComponent unit;
/*     */   private INumberRange<?> value;
/*     */   private INumberRange<?> bonus;
/*     */   private AbilityStatType bonusType;
/*     */   private boolean isBuff;
/*     */   private String sign;
/*     */   
/*     */   private AbilityStat(ITextComponent name, INumberRange<?> value) {
/*  23 */     this.name = name;
/*  24 */     this.value = value;
/*     */   }
/*     */   
/*     */   private void setUnit(ITextComponent unit) {
/*  28 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   private void setBonus(INumberRange<?> bonus, AbilityStatType bonusType) {
/*  32 */     this.bonus = bonus;
/*  33 */     this.bonusType = bonusType;
/*     */   }
/*     */   
/*     */   private void setBuff(boolean flag) {
/*  37 */     this.isBuff = flag;
/*     */   }
/*     */   
/*     */   private void setSign(String sign) {
/*  41 */     this.sign = sign;
/*     */   }
/*     */   
/*     */   public ITextComponent getStatDescription() {
/*  45 */     return getStatDescription(0);
/*     */   }
/*     */   
/*     */   public ITextComponent getStatDescription(int indent) {
/*  49 */     Object[] params = new Object[20];
/*  50 */     int paramId = 0;
/*     */     
/*  52 */     StringBuilder sb = new StringBuilder();
/*  53 */     sb.append("%s%s§r");
/*  54 */     params[paramId++] = this.isBuff ? "§a" : "§c";
/*  55 */     params[paramId++] = this.name.getString();
/*     */     
/*  57 */     NumberFormat nf = NumberFormat.getInstance();
/*  58 */     nf.setMaximumFractionDigits(2);
/*     */ 
/*     */     
/*  61 */     float minBonus = 0.0F;
/*  62 */     float maxBonus = 0.0F;
/*  63 */     String bonusType = "";
/*  64 */     if (this.bonus != null && ClientConfig.INSTANCE.isAbilityBonusesMergeEnable()) {
/*  65 */       bonusType = this.bonusType.getColorCode();
/*  66 */       minBonus = this.bonus.getMin().floatValue();
/*  67 */       if (this.bonus.isFixed()) {
/*  68 */         maxBonus = minBonus;
/*     */       }
/*  70 */       else if (this.bonus.isRange()) {
/*  71 */         maxBonus = this.bonus.getMax().floatValue();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  76 */     if (this.value.isInfinite()) {
/*  77 */       sb.append(" ∞");
/*     */     }
/*  79 */     else if (this.value.isRange()) {
/*  80 */       sb.append(" %s%s%s-%s§r");
/*  81 */       params[paramId++] = bonusType;
/*  82 */       params[paramId++] = this.sign;
/*  83 */       params[paramId++] = nf.format((this.value.getMin().floatValue() + minBonus));
/*  84 */       params[paramId++] = nf.format((this.value.getMax().floatValue() + maxBonus));
/*     */     } else {
/*     */       
/*  87 */       sb.append(" %s%s%s§r");
/*  88 */       params[paramId++] = this.sign;
/*  89 */       params[paramId++] = bonusType;
/*  90 */       params[paramId++] = nf.format((this.value.getMin().floatValue() + minBonus));
/*     */     } 
/*     */ 
/*     */     
/*  94 */     if (this.unit != null) {
/*  95 */       sb.append(" %s");
/*  96 */       params[paramId++] = this.unit.getString();
/*     */     } 
/*     */     
/*  99 */     if (indent > 0) {
/* 100 */       while (indent > 0) {
/* 101 */         sb.insert(0, " ");
/* 102 */         indent--;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 107 */     if (this.bonus != null && !ClientConfig.INSTANCE.isAbilityBonusesMergeEnable()) {
/* 108 */       float min = this.bonus.getMin().floatValue();
/* 109 */       float max = this.bonus.getMax().floatValue();
/* 110 */       if (this.bonus.isRange() && (min != 0.0F || max != 0.0F)) {
/* 111 */         sb.append(" (%s%s§r-%s%s§r)");
/* 112 */         params[paramId++] = this.bonusType.getColorCode() + "" + ((min == 0.0F) ? "" : ((min > 0.0F) ? "+" : ""));
/* 113 */         params[paramId++] = nf.format(this.bonus.getMin());
/* 114 */         params[paramId++] = this.bonusType.getColorCode() + "" + ((max == 0.0F) ? "" : ((max > 0.0F) ? "+" : ""));
/* 115 */         params[paramId++] = nf.format(this.bonus.getMax());
/*     */       }
/* 117 */       else if (this.bonus.isFixed() && min != 0.0F) {
/* 118 */         sb.append(" (%s%s§r)");
/* 119 */         params[paramId++] = this.bonusType.getColorCode() + "" + ((min > 0.0F) ? "+" : "");
/* 120 */         params[paramId++] = nf.format(this.bonus.getMin());
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return (ITextComponent)new StringTextComponent(String.format(sb.toString(), params));
/*     */   }
/*     */   
/*     */   public static class Builder {
/*     */     private ITextComponent name;
/*     */     @Nullable
/*     */     private ITextComponent unit;
/*     */     private INumberRange<?> value;
/*     */     private INumberRange<?> bonus;
/* 133 */     private AbilityStat.AbilityStatType bonusType = AbilityStat.AbilityStatType.NEUTRAL;
/*     */     private boolean isStatBuff = true;
/* 135 */     private String sign = "";
/*     */     
/*     */     public Builder(ITextComponent name, float value) {
/* 138 */       this.name = name;
/* 139 */       this.value = (INumberRange<?>)new FloatRange(value);
/*     */     }
/*     */     
/*     */     public Builder(ITextComponent name, float min, float max) {
/* 143 */       this.name = name;
/* 144 */       this.value = (INumberRange<?>)new FloatRange(min, max);
/*     */     }
/*     */     
/*     */     public Builder(ITextComponent name, int value) {
/* 148 */       this.name = name;
/* 149 */       this.value = (INumberRange<?>)new IntRange(value);
/*     */     }
/*     */     
/*     */     public Builder(ITextComponent name, int min, int max) {
/* 153 */       this.name = name;
/* 154 */       this.value = (INumberRange<?>)new IntRange(min, max);
/*     */     }
/*     */     
/*     */     public Builder withUnit(ITextComponent unit) {
/* 158 */       this.unit = unit;
/* 159 */       return this;
/*     */     }
/*     */     
/*     */     public Builder withBonus(float bonus, AbilityStat.AbilityStatType type) {
/* 163 */       this.bonus = (INumberRange<?>)new FloatRange(bonus);
/* 164 */       this.bonusType = type;
/* 165 */       return this;
/*     */     }
/*     */     
/*     */     public Builder withBonus(float minBonus, float maxBonus, AbilityStat.AbilityStatType type) {
/* 169 */       this.bonus = (INumberRange<?>)new FloatRange(minBonus, maxBonus);
/* 170 */       this.bonusType = type;
/* 171 */       return this;
/*     */     }
/*     */     
/*     */     public Builder withBuff(boolean flag) {
/* 175 */       this.isStatBuff = flag;
/* 176 */       return this;
/*     */     }
/*     */     
/*     */     public Builder withSign(String sign) {
/* 180 */       this.sign = sign;
/* 181 */       return this;
/*     */     }
/*     */     
/*     */     public AbilityStat build() {
/* 185 */       AbilityStat stat = new AbilityStat(this.name, this.value);
/* 186 */       stat.setUnit(this.unit);
/* 187 */       stat.setBonus(this.bonus, this.bonusType);
/* 188 */       stat.setBuff(this.isStatBuff);
/* 189 */       stat.setSign(this.sign);
/* 190 */       return stat;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum AbilityStatType {
/* 195 */     NEUTRAL(""),
/* 196 */     BUFF("§a"),
/* 197 */     DEBUFF("§c");
/*     */     
/*     */     private String color;
/*     */ 
/*     */     
/*     */     AbilityStatType(String color) {
/* 203 */       this.color = color;
/*     */     }
/*     */     
/*     */     public String getColorCode() {
/* 207 */       return this.color;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */