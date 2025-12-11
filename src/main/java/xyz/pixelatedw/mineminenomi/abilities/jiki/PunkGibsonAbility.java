/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ 
/*     */ public class PunkGibsonAbility extends Ability {
/*     */   private static final int REQUIRED_IRON = 100;
/*  41 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "punk_gibson", new Pair[] {
/*  42 */         (Pair)ImmutablePair.of("Uses %s magnetic items from the user's inventory to create a large arm increasing their punch power and reach.", new Object[] { "§a100§r" })
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 2400;
/*     */   private static final int MIN_COOLDOWN = 60;
/*     */   private static final int MAX_COOLDOWN = 600;
/*  48 */   public static final AbilityCore<PunkGibsonAbility> INSTANCE = (new AbilityCore.Builder("Punk Gibson", AbilityCategory.DEVIL_FRUITS, PunkGibsonAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(60.0F, 600.0F), ContinuousComponent.getTooltip(2400.0F), ChangeStatsComponent.getTooltip()
/*  51 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  52 */     .setSourceElement(SourceElement.METAL)
/*  53 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  54 */       }).build();
/*     */   
/*  56 */   private static final AbilityAttributeModifier PUNCH_DAMAGE_MODIFIER = new AbilityAttributeModifier(UUID.randomUUID(), INSTANCE, "Punk Gibson Strength Modifier", 6.0D, AttributeModifier.Operation.ADDITION);
/*  57 */   private static final AbilityAttributeModifier REACH_MODIFIER = new AbilityAttributeModifier(UUID.randomUUID(), INSTANCE, "Punk Gibson Reach Modifier", 2.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  59 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  60 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::hitEvent);
/*  61 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/*  62 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*     */   
/*  64 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */   private boolean dropItems = true;
/*     */   
/*     */   public PunkGibsonAbility(AbilityCore<PunkGibsonAbility> core) {
/*  68 */     super(core);
/*     */     
/*  70 */     this.isNew = true;
/*  71 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.statsComponent, (AbilityComponent)this.morphComponent });
/*     */     
/*  73 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)PUNCH_DAMAGE_MODIFIER);
/*  74 */     this.statsComponent.addAttributeModifier((Supplier)ForgeMod.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/*  75 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*     */     
/*  77 */     addCanUseCheck(JikiHelper.getMetalicItemsCheck(100));
/*     */     
/*  79 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  83 */     this.continuousComponent.triggerContinuity(entity, 2400.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  87 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.PUNK_GIBSON.get());
/*  88 */     this.statsComponent.applyModifiers(entity);
/*     */     
/*  90 */     if (!this.magneticItems.isEmpty()) {
/*  91 */       this.magneticItems.clear();
/*     */     }
/*     */     
/*  94 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*     */     
/*  96 */     this.magneticItems = JikiHelper.getMagneticItemsNeeded(inventory, 100.0F);
/*     */     
/*  98 */     this.dropItems = true;
/*     */     
/* 100 */     JikiHelper.spawnAttractEffect((Entity)entity);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 104 */     this.morphComponent.stopMorph(entity);
/* 105 */     this.statsComponent.removeModifiers(entity);
/*     */     
/* 107 */     if (this.dropItems && this.magneticItems.size() > 0) {
/* 108 */       ItemStack stack = this.magneticItems.get(0);
/* 109 */       ItemsHelper.itemBreakParticles(entity, 100, stack);
/* 110 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), SoundEvents.field_187635_cQ, entity.func_184176_by(), 0.5F, 1.0F);
/* 111 */       JikiHelper.dropComponentItems(entity, entity.func_213303_ch(), this.magneticItems);
/*     */     } 
/*     */     
/* 114 */     float cooldown = MathHelper.func_76131_a(this.continuousComponent.getContinueTime(), 60.0F, 600.0F);
/* 115 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 119 */     if (this.continuousComponent.isContinuous()) {
/* 120 */       source.setSourceElement(SourceElement.METAL).setFistDamage();
/*     */     }
/*     */     
/* 123 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */   
/*     */   public void activateDamnedPunk(LivingEntity entity) {
/* 127 */     this.statsComponent.removeModifiers(entity);
/*     */   }
/*     */   
/*     */   public void activatePunkGibson(LivingEntity entity) {
/* 131 */     this.statsComponent.applyModifiers(entity);
/*     */   }
/*     */   
/*     */   public void stopItemDrops() {
/* 135 */     this.dropItems = false;
/*     */   }
/*     */   
/*     */   public List<ItemStack> getMagneticItems() {
/* 139 */     return this.magneticItems;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkGibsonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */