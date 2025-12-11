/*    */ package xyz.pixelatedw.mineminenomi.abilities.mandemontactics;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class DemonicSmashAbility
/*    */   extends Ability
/*    */ {
/*    */   private static final int COOLDOWN = 160;
/*    */   private static final float RANGE = 1.25F;
/*    */   private static final float DAMAGE = 20.0F;
/* 31 */   public static final TargetsPredicate TARGET_TEST = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*    */   
/* 33 */   public static final AbilityCore<DemonicSmashAbility> INSTANCE = (new AbilityCore.Builder("Demonic Smash", AbilityCategory.STYLE, DemonicSmashAbility::new))
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), RangeComponent.getTooltip(1.25F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(20.0F)
/* 35 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 37 */       }).build();
/*    */   
/* 39 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 40 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 41 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   private boolean isLookingRight = true;
/*    */   
/*    */   public DemonicSmashAbility(AbilityCore<DemonicSmashAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 51 */     addCanUseCheck(AbilityHelper::requiresTonfaWeapon);
/*    */     
/* 53 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 57 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.25F, 1.5F);
/*    */ 
/*    */     
/* 60 */     this.isLookingRight = entity.func_70681_au().nextBoolean();
/*    */     
/* 62 */     this.animationComponent.start(entity, ModAnimations.DEMONIC_SMASH, 7);
/*    */     
/* 64 */     Vector3d dir = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70177_z, this.isLookingRight ? -5.0D : 5.0D, 0.0D, 0.0D);
/* 65 */     Vector3d move = entity.func_213303_ch().func_178788_d(dir);
/*    */     
/* 67 */     for (LivingEntity target : targets) {
/* 68 */       ItemsHelper.stopShieldAndStartCooldown(target, 100);
/* 69 */       if (this.dealDamageComponent.hurtTarget(entity, target, 20.0F)) {
/* 70 */         AbilityHelper.setDeltaMovement((Entity)target, move);
/*    */       }
/*    */     } 
/*    */     
/* 74 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mandemontactics\DemonicSmashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */