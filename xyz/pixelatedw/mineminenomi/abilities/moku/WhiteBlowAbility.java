/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteBlowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class WhiteBlowAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "white_blow", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Shoots a cloud of smoke to engulf the opponent and trap them", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 28 */   public static final AbilityCore<WhiteBlowAbility> INSTANCE = (new AbilityCore.Builder("White Blow", AbilityCategory.DEVIL_FRUITS, WhiteBlowAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 33 */     .setSourceElement(SourceElement.SMOKE)
/* 34 */     .build();
/*    */   
/* 36 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 37 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public WhiteBlowAbility(AbilityCore<WhiteBlowAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 45 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 49 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER, 7);
/* 50 */     this.projectileComponent.shoot(entity, 2.75F, 1.0F);
/* 51 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private WhiteBlowProjectile createProjectile(LivingEntity entity) {
/* 55 */     WhiteBlowProjectile proj = new WhiteBlowProjectile(entity.field_70170_p, entity, this);
/* 56 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteBlowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */