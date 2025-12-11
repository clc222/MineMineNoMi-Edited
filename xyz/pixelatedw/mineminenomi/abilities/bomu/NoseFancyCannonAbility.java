/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bomu.NoseFancyCannonProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class NoseFancyCannonAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nose_fancy_cannon", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Shoots dried mucus at the opponent, which explodes on impact", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 80.0F;
/*    */   private static final int ANIMATION_TICKS = 7;
/* 29 */   public static final AbilityCore<NoseFancyCannonAbility> INSTANCE = (new AbilityCore.Builder("Nose Fancy Cannon", AbilityCategory.DEVIL_FRUITS, NoseFancyCannonAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 34 */     .setSourceElement(SourceElement.EXPLOSION)
/* 35 */     .build();
/*    */   
/* 37 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 38 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public NoseFancyCannonAbility(AbilityCore<NoseFancyCannonAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 46 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 51 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/* 52 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER, 7);
/*    */   }
/*    */   
/*    */   private NoseFancyCannonProjectile createProjectile(LivingEntity entity) {
/* 56 */     NoseFancyCannonProjectile proj = new NoseFancyCannonProjectile(entity.field_70170_p, entity, this);
/* 57 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\NoseFancyCannonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */