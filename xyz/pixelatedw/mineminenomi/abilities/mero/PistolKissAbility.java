/*    */ package xyz.pixelatedw.mineminenomi.abilities.mero;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.PistolKissProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class PistolKissAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "pistol_kiss", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("A weaker but faster variant of %s.", new Object[] { MeroMeroMellowAbility.INSTANCE })
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 27 */   public static final AbilityCore<PistolKissAbility> INSTANCE = (new AbilityCore.Builder("Pistol Kiss", AbilityCategory.DEVIL_FRUITS, PistolKissAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 30 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 31 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 32 */     .build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 35 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public PistolKissAbility(AbilityCore<PistolKissAbility> core) {
/* 38 */     super(core);
/*    */     
/* 40 */     this.isNew = true;
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 43 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 47 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER, 7);
/* 48 */     this.projectileComponent.shoot(entity, 3.5F, 1.0F);
/* 49 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private PistolKissProjectile createProjectile(LivingEntity entity) {
/* 53 */     PistolKissProjectile proj = new PistolKissProjectile(entity.field_70170_p, entity, this);
/* 54 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\PistolKissAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */