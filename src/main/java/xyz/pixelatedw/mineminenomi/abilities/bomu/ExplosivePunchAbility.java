/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class ExplosivePunchAbility extends Ability {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "explosive_punch", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("The user punches and creates an explosion around their fist", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/* 37 */   public static final AbilityCore<ExplosivePunchAbility> INSTANCE = (new AbilityCore.Builder("Explosive Punch", AbilityCategory.DEVIL_FRUITS, ExplosivePunchAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip()
/* 40 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 41 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 42 */       }).setSourceElement(SourceElement.EXPLOSION)
/* 43 */     .build();
/*    */   
/* 45 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = new AbilityAttributeModifier(UUID.fromString("a7c4aa73-d369-46c1-ace0-161695eb3ec8"), INSTANCE, "Explosive Punch Strength Modifier", 20.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 47 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 48 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent((IAbility)this);
/* 49 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*    */   
/*    */   public ExplosivePunchAbility(AbilityCore<ExplosivePunchAbility> core) {
/* 52 */     super(core);
/*    */     
/* 54 */     this.isNew = true;
/*    */     
/* 56 */     this.statsComponent.addAttributeModifier(Attributes.field_233823_f_, (AttributeModifier)STRENGTH_MODIFIER, entity -> this.continuousComponent.isContinuous());
/*    */     
/* 58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.statsComponent });
/*    */     
/* 60 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/* 61 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 65 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 69 */     if (this.continuousComponent.isContinuous()) {
/* 70 */       return HitTriggerComponent.HitResult.HIT;
/*    */     }
/*    */     
/* 73 */     return HitTriggerComponent.HitResult.PASS;
/*    */   }
/*    */   
/*    */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 77 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 4.0F);
/*    */     
/* 79 */     explosion.setStaticDamage(35.0F);
/* 80 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 81 */     explosion.doExplosion();
/*    */     
/* 83 */     this.continuousComponent.stopContinuity(entity);
/* 84 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */     
/* 86 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\ExplosivePunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */