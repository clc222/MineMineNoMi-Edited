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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.MeroMeroMellowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class MeroMeroMellowAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mero_mero_mellow", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Fires a heart-shaped beam, turning every enemy in its path into stone.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/* 27 */   public static final AbilityCore<MeroMeroMellowAbility> INSTANCE = (new AbilityCore.Builder("Mero Mero Mellow", AbilityCategory.DEVIL_FRUITS, MeroMeroMellowAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F)
/* 30 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 31 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 32 */     .build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 35 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public MeroMeroMellowAbility(AbilityCore<MeroMeroMellowAbility> core) {
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
/* 48 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 49 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */   
/*    */   private MeroMeroMellowProjectile createProjectile(LivingEntity entity) {
/* 53 */     MeroMeroMellowProjectile proj = new MeroMeroMellowProjectile(entity.field_70170_p, entity, this);
/* 54 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\MeroMeroMellowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */