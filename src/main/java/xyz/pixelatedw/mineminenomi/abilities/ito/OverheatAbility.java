/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.OverheatProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class OverheatAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "overheat", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user shoots a rope made of heated strings at the opponent, exploding upon impact", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 300.0F;
/* 28 */   public static final AbilityCore<OverheatAbility> INSTANCE = (new AbilityCore.Builder("Overheat", AbilityCategory.DEVIL_FRUITS, OverheatAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 33 */     .setSourceElement(SourceElement.EXPLOSION)
/* 34 */     .build();
/*    */   
/* 36 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 37 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public OverheatAbility(AbilityCore<OverheatAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/*    */     
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 46 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.projectileComponent.shoot(entity, 3.25F, 0.0F);
/* 51 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM, 7);
/*    */     
/* 53 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */   
/*    */   private OverheatProjectile createProjectile(LivingEntity entity) {
/* 57 */     OverheatProjectile proj = new OverheatProjectile(entity.field_70170_p, entity, this);
/* 58 */     proj.func_70107_b(proj.func_226277_ct_(), entity.func_226278_cu_() + entity.func_70047_e() - 0.4000000059604645D, proj.func_226281_cx_());
/* 59 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\OverheatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */