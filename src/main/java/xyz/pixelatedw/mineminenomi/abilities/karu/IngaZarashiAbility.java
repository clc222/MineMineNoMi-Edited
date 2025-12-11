/*     */ package xyz.pixelatedw.mineminenomi.abilities.karu;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.MorphAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ 
/*     */ public class IngaZarashiAbility
/*     */   extends MorphAbility2
/*     */ {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "inga_zarashi", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Increases your physical prowess depending on how much damage you have in your %s counter", new Object[] { AbilityHelper.mentionText(ModI18n.GUI_KARMA) })
/*     */       });
/*     */   
/*     */   private static final int MIN_COOLDOWN = 20;
/*     */   private static final int MAX_COOLDOWN = 240;
/*  44 */   public static final AbilityCore<IngaZarashiAbility> INSTANCE = (new AbilityCore.Builder("Inga Zarashi", AbilityCategory.DEVIL_FRUITS, IngaZarashiAbility::new))
/*  45 */     .addDescriptionLine(DESCRIPTION)
/*  46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 240.0F), ContinuousComponent.getTooltip()
/*  47 */       }).build();
/*     */   
/*  49 */   private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("06141405-6e5c-4b98-a8f7-230e0ffb96bc");
/*  50 */   private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("7ddb710f-a497-4f64-b272-8fcc9955b401");
/*  51 */   private static final UUID REACH_MODIFIER_UUID = UUID.fromString("dc0d06d6-ffd6-49d8-b484-da232b78fd41");
/*     */   
/*  53 */   private Optional<KarmaAbility> karmaAbility = Optional.empty();
/*     */   
/*     */   public IngaZarashiAbility(AbilityCore<IngaZarashiAbility> core) {
/*  56 */     super(core);
/*     */     
/*  58 */     addCanUseCheck(this::canUse);
/*     */     
/*  60 */     this.continuousComponent.addTickEvent(this::duringContinuousEvent);
/*  61 */     this.continuousComponent.addEndEvent(this::endContinuousEvent);
/*     */   }
/*     */   
/*     */   private void duringContinuousEvent(LivingEntity entity, IAbility ability) {
/*  65 */     if (this.continuousComponent.getContinueTime() % 20.0F == 0.0F && this.karmaAbility.isPresent()) {
/*  66 */       updateKarma(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuousEvent(LivingEntity entity, IAbility ability) {
/*  71 */     float cooldown = MathHelper.func_76131_a(this.continuousComponent.getContinueTime(), 20.0F, 240.0F);
/*  72 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/*  76 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  77 */     KarmaAbility karma = (KarmaAbility)props.getPassiveAbility(KarmaAbility.INSTANCE);
/*  78 */     if (karma == null) {
/*  79 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/*  82 */     this.karmaAbility = Optional.ofNullable(karma);
/*     */     
/*  84 */     return AbilityUseResult.success();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateKarma(LivingEntity entity) {
/*  90 */     if (((KarmaAbility)this.karmaAbility.get()).getPrevKarma() != ((KarmaAbility)this.karmaAbility.get()).getKarma()) {
/*  91 */       this.statsComponent.removeModifiers(entity);
/*  92 */       for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)getAttributes().entries()) {
/*  93 */         this.statsComponent.removeAttributeModifier(entry.getKey());
/*  94 */         this.statsComponent.addAttributeModifier(entry.getKey(), entry.getValue());
/*     */       } 
/*  96 */       this.statsComponent.applyModifiers(entity);
/*  97 */       this.morphComponent.updateMorphSize(entity);
/*     */       
/*  99 */       ((KarmaAbility)this.karmaAbility.get()).setPrevKarma(((KarmaAbility)this.karmaAbility.get()).getKarma());
/*     */     } 
/*     */     
/* 102 */     ((KarmaAbility)this.karmaAbility.get()).addKarma(entity, -(((KarmaAbility)this.karmaAbility.get()).getKarma() / 100.0F));
/*     */   }
/*     */   
/*     */   private Multimap<Attribute, AttributeModifier> getAttributes() {
/* 106 */     HashMultimap hashMultimap = HashMultimap.create();
/*     */     
/* 108 */     float karma = ((KarmaAbility)this.karmaAbility.get()).getKarma();
/*     */     
/* 110 */     double armorMod = Math.min(karma / 100.0F * 8.0F, 8.0F);
/* 111 */     double attackMod = Math.min(karma / 100.0F * 10.0F, 10.0F);
/* 112 */     double reachMod = Math.min((karma / 100.0F) * 2.5D, 2.5D);
/*     */     
/* 114 */     hashMultimap.put(Attributes.field_233826_i_, new AbilityAttributeModifier(ARMOR_MODIFIER_UUID, INSTANCE, "Karma Armor Modifier", armorMod, AttributeModifier.Operation.ADDITION));
/* 115 */     hashMultimap.put(ModAttributes.PUNCH_DAMAGE.get(), new AbilityAttributeModifier(ATTACK_MODIFIER_UUID, INSTANCE, "Karma Attack Modifier", attackMod, AttributeModifier.Operation.ADDITION));
/* 116 */     AbilityAttributeModifier reachAttribute = new AbilityAttributeModifier(REACH_MODIFIER_UUID, INSTANCE, "Karma Reach Modifier", reachMod, AttributeModifier.Operation.ADDITION);
/* 117 */     hashMultimap.put(ForgeMod.REACH_DISTANCE.get(), reachAttribute);
/* 118 */     hashMultimap.put(ModAttributes.ATTACK_RANGE.get(), reachAttribute);
/*     */     
/* 120 */     return (Multimap<Attribute, AttributeModifier>)hashMultimap;
/*     */   }
/*     */ 
/*     */   
/*     */   public MorphInfo getTransformation() {
/* 125 */     return (MorphInfo)ModMorphs.INGA_ZARASHI.get();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\karu\IngaZarashiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */