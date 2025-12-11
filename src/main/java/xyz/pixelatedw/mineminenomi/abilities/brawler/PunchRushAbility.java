/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.Hand;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PunchRushAbility extends Ability {
/* 20 */   public static final AbilityCore<PunchRushAbility> INSTANCE = (new AbilityCore.Builder("Punch Rush", AbilityCategory.STYLE, PunchRushAbility::new))
/* 21 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 22 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 23 */       }).build();
/*    */   
/* 25 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent);
/* 26 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::repeaterTriggerEvent).addStopEvent(this::repeaterStopEvent);
/*    */   
/* 28 */   private int waves = 30;
/* 29 */   private int punchesPerWave = 5;
/*    */   
/*    */   public PunchRushAbility(AbilityCore<PunchRushAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.isNew = true;
/* 35 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent });
/*    */     
/* 37 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/* 38 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 42 */     if (this.continuousComponent.isContinuous()) {
/* 43 */       this.repeaterComponent.stop(entity);
/*    */       return;
/*    */     } 
/* 46 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 50 */     if (!entity.field_70170_p.field_72995_K) {
/* 51 */       this.repeaterComponent.start(entity, this.waves, 1);
/*    */     }
/*    */   }
/*    */   
/*    */   private void repeaterTriggerEvent(LivingEntity entity, IAbility ability) {
/* 56 */     float speed = 2.2F;
/* 57 */     int projectileSpace = 2;
/* 58 */     float projDmageReduction = 0.6F;
/*    */     
/* 60 */     for (int i = 0; i < this.punchesPerWave; i++) {
/* 61 */       GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(entity.field_70170_p, entity);
/* 62 */       gomuGomuNoPistolProjectile.setEntityCollisionSize(1.25D);
/* 63 */       gomuGomuNoPistolProjectile.setMaxLife(3);
/* 64 */       gomuGomuNoPistolProjectile.setDamage(gomuGomuNoPistolProjectile.getDamage() * (1.0F - projDmageReduction));
/* 65 */       gomuGomuNoPistolProjectile.setMaxLife((int)(gomuGomuNoPistolProjectile.getMaxLife() * 0.75D));
/* 66 */       double px = entity.func_226277_ct_() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble();
/* 67 */       double py = entity.func_226280_cw_() + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble();
/* 68 */       double pz = entity.func_226281_cx_() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble();
/* 69 */       gomuGomuNoPistolProjectile.func_70012_b(px, py, pz, 0.0F, 0.0F);
/* 70 */       entity.field_70170_p.func_217376_c((Entity)gomuGomuNoPistolProjectile);
/* 71 */       gomuGomuNoPistolProjectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, speed, 3.0F);
/*    */     } 
/* 73 */     entity.func_226292_a_(Hand.MAIN_HAND, true);
/*    */   }
/*    */   
/*    */   private void repeaterStopEvent(LivingEntity entity, IAbility ability) {
/* 77 */     this.continuousComponent.stopContinuity(entity);
/* 78 */     this.cooldownComponent.startCooldown(entity, WyHelper.secondsToTicks(10.0F));
/*    */   }
/*    */   
/*    */   public void setWaveDetails(int waves, int punchesPerWave) {
/* 82 */     this.waves = waves;
/* 83 */     this.punchesPerWave = punchesPerWave;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\PunchRushAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */