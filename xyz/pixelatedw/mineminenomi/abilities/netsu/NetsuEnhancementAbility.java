/*     */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class NetsuEnhancementAbility extends PunchAbility2 {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "netsu_enhancement", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Increases the user's attacks and body functions through heat.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 800;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 500;
/*  39 */   public static final AbilityCore<NetsuEnhancementAbility> INSTANCE = (new AbilityCore.Builder("Netsu Enhancement", AbilityCategory.DEVIL_FRUITS, NetsuEnhancementAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 500.0F), ContinuousComponent.getTooltip(800.0F), ChangeStatsComponent.getTooltip()
/*  42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  43 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  44 */       }).build();
/*     */   
/*  46 */   private static final AbilityAttributeModifier MULTIPLIER = new AbilityAttributeModifier(UUID.fromString("efa08cbd-57e5-478f-b15c-6295eb1b375e"), INSTANCE, "Netsu Enhancement Modifier", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE);
/*  47 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(WyHelper.hexToRGB("#962A2AAA")).build();
/*     */   
/*  49 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/*     */   
/*     */   public NetsuEnhancementAbility(AbilityCore<NetsuEnhancementAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.skinOverlayComponent });
/*     */     
/*  56 */     this.statsComponent.addAttributeModifier(Attributes.field_233825_h_, (AttributeModifier)MULTIPLIER);
/*     */     
/*  58 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/*  59 */     this.continuousComponent.addTickEvent(this::duringContinuityEvent);
/*  60 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.skinOverlayComponent.showAll(entity);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  68 */     if (!entity.field_70170_p.field_72995_K && this.continuousComponent.getContinueTime() % 5.0F == 0.0F) {
/*  69 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.NETSU_ENCHANTMENT.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  74 */     this.skinOverlayComponent.hideAll(entity);
/*     */     
/*  76 */     float cooldown = 100.0F + this.continuousComponent.getContinueTime() / 2.0F;
/*  77 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchDamage() {
/*  82 */     return 10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  87 */     if (canActivate().test(entity)) {
/*  88 */       AbilityHelper.setSecondsOnFireBy((Entity)target, 5, entity);
/*     */     }
/*     */     
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isParallel() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/* 101 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 106 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 111 */     return -1.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\NetsuEnhancementAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */