/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon.BarizodonProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BarizodonAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barizodon", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Shoots out a barrage of elliptic air projectiles using the user's wings.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 140;
/* 33 */   public static final AbilityCore<BarizodonAbility> INSTANCE = (new AbilityCore.Builder("Barizodon", AbilityCategory.DEVIL_FRUITS, BarizodonAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 36 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip()
/* 37 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 38 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 39 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 40 */       }).build();
/*    */   
/* 42 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/* 43 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 44 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 45 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PTERA_ASSAULT.get(), new MorphInfo[] { (MorphInfo)ModMorphs.PTERA_FLY.get() });
/*    */   
/*    */   public BarizodonAbility(AbilityCore<BarizodonAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/*    */     
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 54 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 58 */     if (this.continuousComponent.isContinuous()) {
/* 59 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 64 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 68 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 72 */     this.repeaterComponent.start(entity, 8, 2);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 76 */     if (canUse(entity).isFail()) {
/* 77 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 82 */     for (int i = 0; i < WyHelper.randomWithRange(1, 4); i++) {
/* 83 */       this.projectileComponent.shoot(entity, 3.0F, 10.0F);
/*    */     }
/*    */     
/* 86 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 90 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 92 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*    */   }
/*    */   
/*    */   private BarizodonProjectile createProjectile(LivingEntity entity) {
/* 96 */     BarizodonProjectile proj = new BarizodonProjectile(entity.field_70170_p, entity);
/* 97 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\BarizodonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */