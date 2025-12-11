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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon.TempuraudonProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class TempuraudonAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tempuraudon", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Stretches its head back, releasing it really fast and acting as a sniper.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/*    */   private static final int CHARGE_TIME = 60;
/* 32 */   public static final AbilityCore<TempuraudonAbility> INSTANCE = (new AbilityCore.Builder("Tempuraudon", AbilityCategory.DEVIL_FRUITS, TempuraudonAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 35 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(60.0F)
/* 36 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 37 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 38 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 39 */       }).build();
/*    */   
/* 41 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addEndEvent(this::onChargeEnd);
/* 42 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 43 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PTERA_ASSAULT.get(), new MorphInfo[] { (MorphInfo)ModMorphs.PTERA_FLY.get() });
/*    */   
/*    */   public TempuraudonAbility(AbilityCore<TempuraudonAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/*    */     
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 52 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 56 */     this.chargeComponent.startCharging(entity, 60.0F);
/*    */   }
/*    */   
/*    */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 60 */     this.projectileComponent.shoot(entity, 5.0F, 0.0F);
/*    */     
/* 62 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 64 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private TempuraudonProjectile createProjectile(LivingEntity entity) {
/* 68 */     TempuraudonProjectile proj = new TempuraudonProjectile(entity.field_70170_p, entity, this);
/* 69 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\TempuraudonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */