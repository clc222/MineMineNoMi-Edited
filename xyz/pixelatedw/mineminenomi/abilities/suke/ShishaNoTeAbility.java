/*    */ package xyz.pixelatedw.mineminenomi.abilities.suke;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suke.ShishaNoTeProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class ShishaNoTeAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shisha_no_te", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Shoots invisible projectiles that explode upon impact.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/* 28 */   public static final AbilityCore<ShishaNoTeAbility> INSTANCE = (new AbilityCore.Builder("Shisha no Te", AbilityCategory.DEVIL_FRUITS, ShishaNoTeAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 33 */     .setSourceElement(SourceElement.EXPLOSION)
/* 34 */     .build();
/*    */   
/* 36 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 37 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public ShishaNoTeAbility(AbilityCore<ShishaNoTeAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 45 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 49 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER, 6);
/* 50 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 51 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */   
/*    */   private ShishaNoTeProjectile createProjectile(LivingEntity entity) {
/* 55 */     ShishaNoTeProjectile proj = new ShishaNoTeProjectile(entity.field_70170_p, entity, this);
/* 56 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suke\ShishaNoTeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */