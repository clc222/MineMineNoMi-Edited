/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class ShingenNoIchigekiAbility extends Ability {
/*  45 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shingen_no_ichigeki", new Pair[] {
/*  46 */         (Pair)ImmutablePair.of("The user focuses vibrations around their fist in an spherical bubble, releasing when hitting an enemy.", null), 
/*  47 */         (Pair)ImmutablePair.of("The user slams their fist on the ground pushing all nearby enemies.", null)
/*     */       });
/*  49 */   private static final TranslationTextComponent SINGLE_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.shingen_no_ichigeki", "Shingen no Ichigeki"));
/*  50 */   private static final TranslationTextComponent CROWD_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.shingen_no_ichigeki_crowd", "Shingen no Ichigeki: Crowd"));
/*     */   
/*  52 */   private static final ResourceLocation SHINGEN_NO_ICHIGEKI_SINGLE_ICON = new ResourceLocation("mineminenomi", "textures/abilities/shingen_no_ichigeki.png");
/*  53 */   private static final ResourceLocation SHINGEN_NO_ICHIGEKI_CROWD_ICON = new ResourceLocation("mineminenomi", "textures/abilities/shingen_no_ichigeki_crowd.png");
/*     */   
/*     */   private static final float COOLDOWN = 200.0F;
/*     */   
/*     */   private static final float DAMAGE = 40.0F;
/*  58 */   public static final AbilityCore<ShingenNoIchigekiAbility> INSTANCE = (new AbilityCore.Builder("Shingen no Ichigeki", AbilityCategory.DEVIL_FRUITS, ShingenNoIchigekiAbility::new))
/*  59 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/*  62 */         (e, a) -> SINGLE_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[0], CooldownComponent.getTooltip(200.0F), 
/*  63 */         DealDamageComponent.getTooltip(40.0F)
/*  64 */       }).addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */ 
/*     */         
/*  67 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> CROWD_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[1], CooldownComponent.getTooltip(200.0F), 
/*  68 */         DealDamageComponent.getTooltip(5.0F)
/*  69 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  70 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  71 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/*  72 */     .build();
/*     */   
/*  74 */   private static final AbilityAttributeModifier PUNCH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("65b21fdd-0490-49fb-9138-9627bc8bccf9"), INSTANCE, "Shingeki no Ichi Punch Damage Modifier", 40.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  76 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::onContinuityStart).addTickEvent(this::duringContinuityEvent).addEndEvent(this::onContinuityEnd);
/*  77 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::onHitTrigger).addTryHitEvent(this::tryHitEvent);
/*  78 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.SINGLE)).addChangeModeEvent(this::onAltModeChange);
/*  79 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  80 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/*     */   
/*     */   private boolean markForStopping = false;
/*     */   
/*     */   public ShingenNoIchigekiAbility(AbilityCore<ShingenNoIchigekiAbility> core) {
/*  85 */     super(core);
/*     */     
/*  87 */     this.isNew = true;
/*     */     
/*  89 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.statsComponent });
/*     */     
/*  91 */     this.statsComponent.addAttributeModifier((Supplier)ModAttributes.PUNCH_DAMAGE, (AttributeModifier)PUNCH_MODIFIER, e -> this.continuousComponent.isContinuous());
/*     */     
/*  93 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  97 */     if (this.altModeComponent.getCurrentMode() == Mode.SINGLE) {
/*  98 */       this.continuousComponent.triggerContinuity(entity);
/*  99 */     } else if (this.altModeComponent.getCurrentMode() == Mode.CROWD) {
/* 100 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 3.0F);
/*     */       
/* 102 */       explosion.setStaticDamage(5.0F);
/* 103 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 104 */       explosion.doExplosion();
/*     */       
/* 106 */       List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 10.0D, ModEntityPredicates.getEnemyFactions(entity));
/*     */       
/* 108 */       targets.remove(entity);
/*     */       
/* 110 */       for (LivingEntity target : targets) {
/* 111 */         if (this.dealDamageComponent.hurtTarget(entity, target, 5.0F)) {
/* 112 */           Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(4.0D, 1.0D, 4.0D);
/*     */           
/* 114 */           AbilityHelper.setDeltaMovement((Entity)target, -dirVec.field_72450_a, 0.5D + dirVec.field_72448_b, -dirVec.field_72449_c);
/*     */         } 
/*     */       } 
/*     */       
/* 118 */       this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 123 */     this.markForStopping = false;
/* 124 */     this.statsComponent.applyModifiers(entity);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 128 */     if (this.markForStopping) {
/* 129 */       this.continuousComponent.stopContinuity(entity);
/* 130 */       this.markForStopping = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 135 */     this.statsComponent.removeModifiers(entity);
/* 136 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 140 */     if (!this.continuousComponent.isContinuous() || !entity.func_184614_ca().func_190926_b()) {
/* 141 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 144 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitTrigger(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 148 */     source.setPiercing(0.75F).bypassLogia();
/*     */     
/* 150 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 2.0F);
/*     */     
/* 152 */     explosion.setStaticDamage(10.0F);
/* 153 */     explosion.setDestroyBlocks(false);
/* 154 */     explosion.setDamageEntities(false);
/* 155 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 156 */     explosion.doExplosion();
/*     */     
/* 158 */     Vector3d dirVec = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(2.0D, 1.0D, 2.0D);
/*     */     
/* 160 */     AbilityHelper.setDeltaMovement((Entity)target, -dirVec.field_72450_a, 0.5D + dirVec.field_72448_b, -dirVec.field_72449_c);
/*     */     
/* 162 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 100, 0, false, false));
/*     */     
/* 164 */     this.markForStopping = true;
/*     */     
/* 166 */     return true;
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 170 */     if (mode == Mode.SINGLE) {
/* 171 */       setDisplayIcon(SHINGEN_NO_ICHIGEKI_SINGLE_ICON);
/* 172 */     } else if (mode == Mode.CROWD) {
/* 173 */       setDisplayIcon(SHINGEN_NO_ICHIGEKI_CROWD_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 178 */     SINGLE, CROWD;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\ShingenNoIchigekiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */