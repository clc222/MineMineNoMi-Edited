/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChangeStatsComponent
/*     */   extends AbilityComponent<IAbility>
/*     */ {
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip() {
/*  44 */     return (e, a) -> {
/*     */         Optional<ChangeStatsComponent> comp = a.getComponent(ModAbilityKeys.CHANGE_STATS);
/*     */         if (comp.isPresent()) {
/*     */           ITextComponent[] lines = new ITextComponent[((ChangeStatsComponent)comp.get()).modifiers.size() + 1];
/*     */           int lineId = 0;
/*     */           lines[lineId++] = (ITextComponent)new StringTextComponent("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_STATS.getString() + "§r");
/*     */           for (Map.Entry<Attribute, Pair<Predicate<LivingEntity>, AttributeModifier>> entry : ((ChangeStatsComponent)comp.get()).modifiers.entrySet()) {
/*     */             double base;
/*     */             AttributeModifier mod = (AttributeModifier)((Pair)entry.getValue()).getRight();
/*     */             float value = (float)mod.func_111164_d();
/*     */             boolean isBuff = (value > 0.0F);
/*     */             TranslationTextComponent translationTextComponent = new TranslationTextComponent(((Attribute)entry.getKey()).func_233754_c_());
/*     */             String op = "";
/*     */             switch (mod.func_220375_c()) {
/*     */               case ADDITION:
/*     */                 op = isBuff ? "+" : "";
/*     */                 break;
/*     */               case MULTIPLY_BASE:
/*     */                 base = ((Attribute)entry.getKey()).func_111110_b();
/*     */                 value = (float)(base * mod.func_111164_d());
/*     */                 op = isBuff ? "+" : "";
/*     */                 break;
/*     */               case MULTIPLY_TOTAL:
/*     */                 op = "x";
/*     */                 value = (float)(1.0D + mod.func_111164_d());
/*     */                 break;
/*     */             } 
/*     */             ITextComponent statText = (new AbilityStat.Builder((ITextComponent)translationTextComponent, value)).withSign(op).withBuff(isBuff).build().getStatDescription(2);
/*     */             lines[lineId++] = statText;
/*     */           } 
/*     */           StringBuilder sb = new StringBuilder();
/*     */           lineId = 0;
/*     */           for (ITextComponent text : lines) {
/*     */             boolean hasFollowingLine = (lineId++ < lines.length - 1);
/*     */             sb.append(text.getString() + (hasFollowingLine ? "\n" : ""));
/*     */           } 
/*     */           return (ITextComponent)new StringTextComponent(sb.toString());
/*     */         } 
/*     */         return null;
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private Map<Attribute, Pair<Predicate<LivingEntity>, AttributeModifier>> modifiers = new HashMap<>();
/*     */   private boolean hasModsApplied;
/*     */   
/*     */   public ChangeStatsComponent(IAbility ability) {
/*  93 */     super(ModAbilityKeys.CHANGE_STATS, ability);
/*     */   }
/*     */   
/*     */   public ChangeStatsComponent addAttributeModifier(Supplier<Attribute> attr, AttributeModifier modifier) {
/*  97 */     return addAttributeModifier(attr, modifier, (Predicate<LivingEntity>)null);
/*     */   }
/*     */   
/*     */   public ChangeStatsComponent addAttributeModifier(Attribute attr, AttributeModifier modifier) {
/* 101 */     return addAttributeModifier(attr, modifier, (Predicate<LivingEntity>)null);
/*     */   }
/*     */   
/*     */   public ChangeStatsComponent addAttributeModifier(Attribute attr, AttributeModifier modifier, @Nullable Predicate<LivingEntity> test) {
/* 105 */     removeAttributeModifier(attr);
/* 106 */     this.modifiers.put(attr, ImmutablePair.of(test, modifier));
/* 107 */     return this;
/*     */   }
/*     */   
/*     */   public ChangeStatsComponent addAttributeModifier(Supplier<Attribute> attr, AttributeModifier modifier, @Nullable Predicate<LivingEntity> test) {
/* 111 */     return addAttributeModifier(attr.get(), modifier, test);
/*     */   }
/*     */   
/*     */   public ChangeStatsComponent removeAttributeModifier(Attribute attr) {
/* 115 */     this.modifiers.remove(attr);
/* 116 */     return this;
/*     */   }
/*     */   
/*     */   public ChangeStatsComponent removeAttributeModifier(Supplier<Attribute> attr) {
/* 120 */     return removeAttributeModifier(attr.get());
/*     */   }
/*     */   
/*     */   public void clearAttributeModifiers() {
/* 124 */     this.modifiers.clear();
/*     */   }
/*     */   
/*     */   public void applyModifiers(LivingEntity entity) {
/* 128 */     for (Map.Entry<Attribute, Pair<Predicate<LivingEntity>, AttributeModifier>> entry : this.modifiers.entrySet()) {
/* 129 */       applyModifier(entity, entry.getKey(), (AttributeModifier)((Pair)entry.getValue()).getRight());
/*     */     }
/* 131 */     this.hasModsApplied = true;
/*     */   }
/*     */   
/*     */   private void applyMissingModifier(LivingEntity entity, Attribute attr, AttributeModifier modifier) {
/* 135 */     if (!hasModifier(entity, attr, modifier.func_111167_a())) {
/* 136 */       applyModifier(entity, attr, modifier);
/*     */     }
/* 138 */     this.hasModsApplied = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void applyModifier(LivingEntity entity, Attribute attr, AttributeModifier modifier) {
/* 146 */     ensureIsRegistered();
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (modifier instanceof AbilityAttributeModifier && !((AbilityAttributeModifier)modifier).getAbilityCore().equals(getAbility().getCore())) {
/*     */       return;
/*     */     }
/* 153 */     ModifiableAttributeInstance modAttr = entity.func_110148_a(attr);
/* 154 */     if (modAttr != null) {
/* 155 */       modAttr.func_111124_b(modifier);
/* 156 */       modAttr.func_233767_b_(modifier);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeModifiers(LivingEntity entity) {
/* 161 */     if (!this.hasModsApplied) {
/*     */       return;
/*     */     }
/* 164 */     for (Map.Entry<Attribute, Pair<Predicate<LivingEntity>, AttributeModifier>> entry : this.modifiers.entrySet()) {
/* 165 */       removeModifier(entity, entry.getKey(), (AttributeModifier)((Pair)entry.getValue()).getRight());
/*     */     }
/* 167 */     this.hasModsApplied = false;
/*     */   }
/*     */   
/*     */   public void removeModifier(LivingEntity entity, Attribute attr, AttributeModifier modifier) {
/* 171 */     removeModifier(entity, attr, modifier.func_111167_a());
/*     */   }
/*     */   
/*     */   public void removeModifier(LivingEntity entity, Attribute attr, UUID id) {
/* 175 */     ModifiableAttributeInstance modAttr = entity.func_110148_a(attr);
/* 176 */     if (this.hasModsApplied && modAttr != null) {
/* 177 */       AttributeModifier modifier = modAttr.func_111127_a(id);
/*     */ 
/*     */ 
/*     */       
/* 181 */       if (modifier instanceof AbilityAttributeModifier && !((AbilityAttributeModifier)modifier).getAbilityCore().equals(getAbility().getCore())) {
/*     */         return;
/*     */       }
/*     */       
/* 185 */       modAttr.func_188479_b(id);
/*     */ 
/*     */ 
/*     */       
/* 189 */       if (attr.equals(Attributes.field_233818_a_)) {
/* 190 */         float leftHp = entity.func_110143_aJ() - entity.func_110138_aP();
/* 191 */         if (leftHp > 0.0F) {
/* 192 */           entity.func_70606_j(entity.func_110143_aJ() - leftHp);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean hasModifier(LivingEntity entity, Attribute attr, UUID id) {
/* 199 */     ModifiableAttributeInstance modAttr = entity.func_110148_a(attr);
/* 200 */     if (modAttr == null) {
/* 201 */       return false;
/*     */     }
/* 203 */     return (modAttr.func_111127_a(id) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doTick(LivingEntity entity) {
/* 208 */     this.modifiers.forEach((attr, pair) -> {
/*     */           if (pair.getLeft() != null) {
/*     */             if (((Predicate<LivingEntity>)pair.getLeft()).test(entity)) {
/*     */               applyMissingModifier(entity, attr, (AttributeModifier)pair.getRight());
/*     */             } else {
/*     */               removeModifier(entity, attr, (AttributeModifier)pair.getRight());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 223 */     CompoundNBT nbt = super.save();
/* 224 */     nbt.func_74757_a("hasModsApplied", this.hasModsApplied);
/* 225 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 230 */     super.load(nbt);
/* 231 */     this.hasModsApplied = nbt.func_74767_n("hasModsApplied");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\ChangeStatsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */