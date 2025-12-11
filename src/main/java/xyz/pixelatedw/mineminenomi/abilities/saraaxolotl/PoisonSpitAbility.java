/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.saraaxolotl.PoisonSpitProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class PoisonSpitAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "poison_spit", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Spits a small dose of poison.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 140;
/* 27 */   public static final AbilityCore<PoisonSpitAbility> INSTANCE = (new AbilityCore.Builder("Poison Spit", AbilityCategory.DEVIL_FRUITS, PoisonSpitAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 30 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 33 */     .build();
/*    */   
/* 35 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 36 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.AXOLOTL_HEAVY.get(), new MorphInfo[] { (MorphInfo)ModMorphs.AXOLOTL_WALK.get() });
/*    */   
/*    */   public PoisonSpitAbility(AbilityCore<PoisonSpitAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     this.isNew = true;
/* 42 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 44 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 48 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 49 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*    */   }
/*    */   
/*    */   private PoisonSpitProjectile createProjectile(LivingEntity entity) {
/* 53 */     PoisonSpitProjectile proj = new PoisonSpitProjectile(entity.field_70170_p, entity, this);
/* 54 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\PoisonSpitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */