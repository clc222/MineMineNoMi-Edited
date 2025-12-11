/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.DarkMatterProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DarkMatterAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "dark_matter", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Launches a ball of darkness that engulfs the opponent.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 280;
/*    */   private static final int CHARGE_TIME = 80;
/* 32 */   public static final AbilityCore<DarkMatterAbility> INSTANCE = (new AbilityCore.Builder("Dark Matter", AbilityCategory.DEVIL_FRUITS, DarkMatterAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(280.0F), ChargeComponent.getTooltip(80.0F)
/* 35 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 36 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 37 */     .setSourceElement(SourceElement.GRAVITY)
/* 38 */     .build();
/*    */   
/* 40 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 42 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/* 44 */   private DarkMatterProjectile proj = null;
/*    */   
/* 46 */   private Interval particleInterval = new Interval(2);
/*    */   
/*    */   public DarkMatterAbility(AbilityCore<DarkMatterAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 54 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.chargeComponent.startCharging(entity, 80.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 62 */     this.proj = null;
/* 63 */     this.particleInterval.restartIntervalToZero();
/* 64 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM);
/*    */   }
/*    */   
/*    */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 68 */     if (this.proj == null) {
/* 69 */       this.proj = (DarkMatterProjectile)this.projectileComponent.getNewProjectile(entity);
/* 70 */       entity.field_70170_p.func_217376_c((Entity)this.proj);
/*    */     } else {
/*    */       
/* 73 */       this.proj.setLife(this.proj.getMaxLife());
/* 74 */       this.proj.func_70107_b(entity.func_226277_ct_(), entity.func_226280_cw_() + 3.0D, entity.func_226281_cx_());
/*    */     } 
/*    */     
/* 77 */     if (this.particleInterval.canTick()) {
/* 78 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DARK_MATTER_CHARGING.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     }
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 83 */     this.animationComponent.stop(entity);
/* 84 */     this.proj.func_234612_a_((Entity)entity, entity.field_70125_A + 10.0F, entity.field_70177_z, 0.0F, 3.0F, 1.0F);
/* 85 */     this.cooldownComponent.startCooldown(entity, 280.0F);
/*    */   }
/*    */   
/*    */   private DarkMatterProjectile createProjectile(LivingEntity entity) {
/* 89 */     DarkMatterProjectile proj = new DarkMatterProjectile(entity.field_70170_p, entity, this);
/* 90 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\DarkMatterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */