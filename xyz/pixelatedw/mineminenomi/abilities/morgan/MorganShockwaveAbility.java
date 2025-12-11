/*    */ package xyz.pixelatedw.mineminenomi.abilities.morgan;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ShockwaveProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class MorganShockwaveAbility extends Ability {
/*    */   private static final int COOLDOWN = 100;
/*    */   private static final int CHARGE_TIME = 40;
/* 20 */   public static final AbilityCore<MorganShockwaveAbility> INSTANCE = (new AbilityCore.Builder("Morgan's Shockwave", AbilityCategory.STYLE, MorganShockwaveAbility::new))
/* 21 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 22 */     .setSourceElement(SourceElement.SHOCKWAVE)
/* 23 */     .build();
/*    */   
/* 25 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 26 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/* 28 */   private float speed = 2.0F;
/* 29 */   private float inaccuracy = 1.0F;
/* 30 */   private float damage = 10.0F;
/* 31 */   private int shockwaveSize = 2;
/*    */   private boolean canBreakBlocks = false;
/*    */   
/*    */   public MorganShockwaveAbility(AbilityCore<MorganShockwaveAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.isNew = true;
/* 38 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 40 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void setHardForm() {
/* 44 */     this.speed = 4.0F;
/* 45 */     this.inaccuracy = 0.0F;
/* 46 */     this.damage = 20.0F;
/* 47 */     this.shockwaveSize = 4;
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.chargeComponent.startCharging(entity, 40.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.animationComponent.start(entity, ModAnimations.LOWERING_RIGHT_ARM);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.animationComponent.stop(entity);
/*    */     
/* 61 */     ShockwaveProjectile proj = new ShockwaveProjectile(entity.field_70170_p, entity, INSTANCE, true);
/* 62 */     proj.setSize(this.shockwaveSize);
/* 63 */     proj.setDamage(this.damage);
/* 64 */     proj.setBreakBlocks(this.canBreakBlocks);
/* 65 */     proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, this.speed, this.inaccuracy);
/* 66 */     entity.field_70170_p.func_217376_c((Entity)proj);
/*    */     
/* 68 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\morgan\MorganShockwaveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */