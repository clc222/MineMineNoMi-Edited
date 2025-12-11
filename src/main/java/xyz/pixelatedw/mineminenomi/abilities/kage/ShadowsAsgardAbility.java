/*     */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ 
/*     */ public class ShadowsAsgardAbility
/*     */   extends MorphAbility2
/*     */ {
/*  39 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shadows_asgard", new Pair[] {
/*  40 */         (Pair)ImmutablePair.of("By continuously absorbing Shadows the user's strenght increases as well as their size", null)
/*     */       });
/*     */   
/*     */   private static final float HOLD_TIME = 1200.0F;
/*     */   
/*     */   private static final float MIN_COOLDOWN = 200.0F;
/*     */   private static final float MAX_COOLDOWN = 1400.0F;
/*     */   private static final int MAX_SHADOWS = 30;
/*     */   private static final float MIN_STAT = 0.0F;
/*     */   private static final float MAX_ARMOR_STAT = 10.0F;
/*     */   private static final float MAX_ATTACK_STAT = 10.0F;
/*     */   private static final float MAX_REACH_STAT = 2.0F;
/*     */   private static final float MAX_TOUGHNESS_STAT = 5.0F;
/*     */   private static final float MAX_STEP_ASSIST_STAT = 1.0F;
/*     */   private static final float MAX_KNOCKBACK_RES_STAT = 1.0F;
/*     */   private static final float MAX_HEALTH_STAT = 1.0F;
/*  56 */   private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("c9f0e77d-6cd6-498e-b032-6641daaa1081");
/*  57 */   private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("ad79a8f6-0e4e-4cfb-9df0-1ce833eee85c");
/*  58 */   private static final UUID REACH_MODIFIER_UUID = UUID.fromString("5d8c2020-c5f7-48c5-8825-b482bc8efff4");
/*  59 */   private static final UUID TOUGHNESS_MODIFIER_UUID = UUID.fromString("80f4aecd-effc-4843-8d53-945de5b0c251");
/*  60 */   private static final UUID STEP_ASSIST_MODIFIER_UUID = UUID.fromString("bf11a9e2-ffb5-4eca-8c01-9068338a838d");
/*  61 */   private static final UUID KNOCKBACK_RES_MODIFIER_UUID = UUID.fromString("c774bb23-72f4-48d3-84e4-e3aff157f1e7");
/*  62 */   private static final UUID MAX_HEALTH_MODIFIER_UUID = UUID.fromString("6f12c985-570c-420d-97d7-f972b0875f98");
/*     */ 
/*     */   
/*     */   private static final AbilityDescriptionLine.IDescriptionLine STATS_TOOLTIP;
/*     */ 
/*     */   
/*     */   static {
/*  69 */     STATS_TOOLTIP = ((entity, ability) -> {
/*     */         TranslationTextComponent translationTextComponent1 = new TranslationTextComponent(Attributes.field_233826_i_.func_233754_c_());
/*     */         ITextComponent armorStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent1, 0.0F, 10.0F)).build().getStatDescription(2);
/*     */         TranslationTextComponent translationTextComponent2 = new TranslationTextComponent(Attributes.field_233823_f_.func_233754_c_());
/*     */         ITextComponent attackStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent2, 0.0F, 10.0F)).build().getStatDescription(2);
/*     */         TranslationTextComponent translationTextComponent3 = new TranslationTextComponent(((Attribute)ModAttributes.ATTACK_RANGE.get()).func_233754_c_());
/*     */         ITextComponent reachStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent3, 0.0F, 2.0F)).build().getStatDescription(2);
/*     */         TranslationTextComponent translationTextComponent4 = new TranslationTextComponent(((Attribute)ModAttributes.TOUGHNESS.get()).func_233754_c_());
/*     */         ITextComponent toughnessStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent4, 0.0F, 5.0F)).build().getStatDescription(2);
/*     */         TranslationTextComponent translationTextComponent5 = new TranslationTextComponent(((Attribute)ModAttributes.STEP_HEIGHT.get()).func_233754_c_());
/*     */         ITextComponent stepAssistStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent5, 0.0F, 1.0F)).build().getStatDescription(2);
/*     */         TranslationTextComponent translationTextComponent6 = new TranslationTextComponent(Attributes.field_233820_c_.func_233754_c_());
/*     */         ITextComponent knockbackResStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent6, 0.0F, 1.0F)).build().getStatDescription(2);
/*     */         TranslationTextComponent translationTextComponent7 = new TranslationTextComponent(Attributes.field_233818_a_.func_233754_c_());
/*     */         ITextComponent maxHealthStatText = (new AbilityStat.Builder((ITextComponent)translationTextComponent7, 0.0F, 1.0F)).build().getStatDescription(2);
/*     */         StringBuilder sb = new StringBuilder();
/*     */         sb.append("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_STATS.getString() + "§r\n");
/*     */         sb.append(armorStatText.getString() + "\n");
/*     */         sb.append(attackStatText.getString() + "\n");
/*     */         sb.append(reachStatText.getString() + "\n");
/*     */         sb.append(toughnessStatText.getString() + "\n");
/*     */         sb.append(stepAssistStatText.getString() + "\n");
/*     */         sb.append(knockbackResStatText.getString() + "\n");
/*     */         sb.append(maxHealthStatText.getString());
/*     */         return (ITextComponent)new StringTextComponent(sb.toString());
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public static final AbilityCore<ShadowsAsgardAbility> INSTANCE = (new AbilityCore.Builder("Shadow's Asgard", AbilityCategory.DEVIL_FRUITS, ShadowsAsgardAbility::new))
/* 105 */     .addDescriptionLine(DESCRIPTION)
/* 106 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 1400.0F), ContinuousComponent.getTooltip(1200.0F), STATS_TOOLTIP
/* 107 */       }).build();
/*     */   
/* 109 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*     */   
/* 111 */   private int shadows = 0;
/*     */   
/*     */   public ShadowsAsgardAbility(AbilityCore<ShadowsAsgardAbility> core) {
/* 114 */     super(core);
/*     */     
/* 116 */     this.isNew = true;
/* 117 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.stackComponent });
/*     */     
/* 119 */     this.continuousComponent.addStartEvent(120, this::onStartContinuityEvent);
/* 120 */     this.continuousComponent.addTickEvent(120, this::onTickContinuityEvent);
/* 121 */     this.continuousComponent.addEndEvent(120, this::onEndContinuityEvent);
/*     */   }
/*     */   
/*     */   private void onStartContinuityEvent(LivingEntity entity, IAbility ability) {
/* 125 */     this.shadows = 0;
/* 126 */     this.morphComponent.updateMorphSize(entity);
/*     */   }
/*     */   
/*     */   private void onTickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 130 */     if (KageHelper.hasEnoughShadows(entity, (IAbility)this, 1).isFail()) {
/* 131 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 135 */     if (this.continuousComponent.getContinueTime() % 10.0F == 0.0F && this.shadows < 30) {
/*     */       
/* 137 */       this.statsComponent.removeModifiers(entity);
/* 138 */       this.statsComponent.clearAttributeModifiers();
/* 139 */       for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)getAttributes().entries()) {
/* 140 */         this.statsComponent.addAttributeModifier(entry.getKey(), entry.getValue());
/*     */       }
/* 142 */       this.statsComponent.applyModifiers(entity);
/*     */       
/* 144 */       this.shadows++;
/* 145 */       this.stackComponent.setStacks(entity, (IAbility)this, this.shadows);
/* 146 */       KageHelper.removeShadows(entity, 1);
/* 147 */       this.morphComponent.updateMorphSize(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onEndContinuityEvent(LivingEntity entity, IAbility ability) {
/* 152 */     this.stackComponent.setStacks(entity, ability, 0);
/* 153 */     this.cooldownComponent.startCooldown(entity, 200.0F + this.continuousComponent.getContinueTime());
/*     */   }
/*     */   
/*     */   private Multimap<Attribute, AttributeModifier> getAttributes() {
/* 157 */     HashMultimap hashMultimap = HashMultimap.create();
/*     */     
/* 159 */     hashMultimap.put(Attributes.field_233826_i_, new AbilityAttributeModifier(ARMOR_MODIFIER_UUID, INSTANCE, "Shadows Asgard Armor Modifier", getArmorValue(), AttributeModifier.Operation.ADDITION));
/* 160 */     hashMultimap.put(Attributes.field_233823_f_, new AbilityAttributeModifier(ATTACK_MODIFIER_UUID, INSTANCE, "Shadows Asgard Attack Modifier", getDamageValue(), AttributeModifier.Operation.ADDITION));
/* 161 */     AbilityAttributeModifier reachAttribute = new AbilityAttributeModifier(REACH_MODIFIER_UUID, INSTANCE, "Shadows Asgard Reach Modifier", getReachValue(), AttributeModifier.Operation.ADDITION);
/* 162 */     hashMultimap.put(ForgeMod.REACH_DISTANCE.get(), reachAttribute);
/* 163 */     hashMultimap.put(ModAttributes.ATTACK_RANGE.get(), reachAttribute);
/* 164 */     hashMultimap.put(ModAttributes.TOUGHNESS.get(), new AbilityAttributeModifier(TOUGHNESS_MODIFIER_UUID, INSTANCE, "Shadows Asgard Toughness Modifier", getToughnessValue(), AttributeModifier.Operation.ADDITION));
/* 165 */     hashMultimap.put(ModAttributes.STEP_HEIGHT.get(), new AbilityAttributeModifier(STEP_ASSIST_MODIFIER_UUID, INSTANCE, "Shadows Asgard Step Assist Modifier", getStepAssistValue(), AttributeModifier.Operation.ADDITION));
/* 166 */     hashMultimap.put(Attributes.field_233820_c_, new AbilityAttributeModifier(KNOCKBACK_RES_MODIFIER_UUID, INSTANCE, "Shadows Asgard Knockback Resistance Modifier", getKnockbackResAssistValue(), AttributeModifier.Operation.ADDITION));
/* 167 */     hashMultimap.put(Attributes.field_233818_a_, new AbilityAttributeModifier(MAX_HEALTH_MODIFIER_UUID, INSTANCE, "Shadows Asgard Max Health Modifier", getMaxHealthValue(), AttributeModifier.Operation.ADDITION));
/*     */     
/* 169 */     return (Multimap<Attribute, AttributeModifier>)hashMultimap;
/*     */   }
/*     */   
/*     */   private double getArmorValue() {
/* 173 */     return Math.min((this.shadows + 1) / 30.0F * 10.0F, 10.0F);
/*     */   }
/*     */   
/*     */   private double getDamageValue() {
/* 177 */     return Math.min((this.shadows + 1) / 30.0F * 10.0F, 10.0F);
/*     */   }
/*     */   
/*     */   private double getReachValue() {
/* 181 */     return Math.min((this.shadows + 1) / 30.0F * 2.0F, 2.0F);
/*     */   }
/*     */   
/*     */   private double getToughnessValue() {
/* 185 */     return Math.min((this.shadows + 1) / 30.0F * 5.0F, 5.0F);
/*     */   }
/*     */   
/*     */   private double getStepAssistValue() {
/* 189 */     return Math.min((this.shadows + 1 + 15) / 30.0F * 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private double getKnockbackResAssistValue() {
/* 193 */     return Math.min((this.shadows + 1 + 15) / 30.0F * 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private double getMaxHealthValue() {
/* 197 */     return Math.min((this.shadows + 1) / 30.0F * 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 202 */     nbt = super.save(nbt);
/*     */     
/* 204 */     nbt.func_74768_a("shadows", this.shadows);
/*     */     
/* 206 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 211 */     super.load(nbt);
/*     */     
/* 213 */     this.shadows = nbt.func_74762_e("shadows");
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 218 */     return (MorphInfo)ModMorphs.SHADOWS_ASGARD.get();
/*     */   }
/*     */   
/*     */   public int getShadows() {
/* 222 */     return this.shadows;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getContinuityHoldTime() {
/* 227 */     return 1200.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\ShadowsAsgardAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */