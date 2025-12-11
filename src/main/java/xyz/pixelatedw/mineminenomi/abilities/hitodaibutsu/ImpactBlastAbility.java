/*    */ package xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hitodaibutsu.ImpactBlastProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class ImpactBlastAbility extends PunchAbility2 {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "impact_blast", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Launches a golden shock wave forward when punching an enemy or the air, hurting every entity in its path.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 120;
/* 32 */   public static final AbilityCore<ImpactBlastAbility> INSTANCE = (new AbilityCore.Builder("Impact Blast", AbilityCategory.DEVIL_FRUITS, ImpactBlastAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 35 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F)
/* 36 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 37 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 38 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 39 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/* 40 */     .build();
/*    */   
/* 42 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 43 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.DAIBUTSU.get(), new MorphInfo[0]);
/*    */   
/*    */   public ImpactBlastAbility(AbilityCore<ImpactBlastAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 50 */     this.continuousComponent.addTickEvent(100, this::tickContinuityEvent);
/*    */   }
/*    */   
/*    */   private ImpactBlastProjectile createProjectile(LivingEntity entity) {
/* 54 */     ImpactBlastProjectile proj = new ImpactBlastProjectile(entity.field_70170_p, entity, (Ability)this);
/* 55 */     return proj;
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     if (!entity.field_70170_p.field_72995_K && entity.field_82175_bq) {
/* 60 */       this.projectileComponent.shoot(entity, 2.0F, 0.0F);
/* 61 */       this.continuousComponent.stopContinuity(entity);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 67 */     return 120.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 72 */     this.projectileComponent.shoot(entity, 2.0F, 0.0F);
/*    */     
/* 74 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 79 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 84 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hitodaibutsu\ImpactBlastAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */