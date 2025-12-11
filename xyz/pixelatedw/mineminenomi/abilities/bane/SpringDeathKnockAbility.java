/*    */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.Hand;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bane.SpringDeathKnockProjectile;
/*    */ 
/*    */ public class SpringDeathKnockAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "spring_death_knock", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("By turning the user's arm into a spring and compressing it, they can launch a powerful punch", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 80;
/* 26 */   public static final AbilityCore<SpringDeathKnockAbility> INSTANCE = (new AbilityCore.Builder("Spring Death Knock", AbilityCategory.DEVIL_FRUITS, SpringDeathKnockAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CooldownComponent.getTooltip(80.0F)
/* 29 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 30 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 31 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 32 */       }).build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public SpringDeathKnockAbility(AbilityCore<SpringDeathKnockAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 42 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 46 */     this.projectileComponent.shoot(entity, 3.0F, 1.0F);
/* 47 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/* 48 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*    */   }
/*    */   
/*    */   private SpringDeathKnockProjectile createProjectile(LivingEntity entity) {
/* 52 */     SpringDeathKnockProjectile proj = new SpringDeathKnockProjectile(entity.field_70170_p, entity, this);
/* 53 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringDeathKnockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */