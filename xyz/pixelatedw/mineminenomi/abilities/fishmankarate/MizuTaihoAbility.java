/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.MizuTaihoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MizuTaihoAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mizu_taiho", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Launches a giant bubble, dealing massive damage and exploding on contact.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 340;
/*    */   private static final int CHARGE_TIME = 30;
/* 32 */   public static final AbilityCore<MizuTaihoAbility> INSTANCE = (new AbilityCore.Builder("Mizu Taiho", AbilityCategory.RACIAL, MizuTaihoAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(340.0F), ChargeComponent.getTooltip(30.0F)
/* 35 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 36 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 37 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 38 */       }).build();
/*    */   
/* 40 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(100, this::startChargeEvent).addEndEvent(100, this::endChargeEvent);
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 42 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public MizuTaihoAbility(AbilityCore<MizuTaihoAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 50 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.chargeComponent.startCharging(entity, 30.0F);
/*    */   }
/*    */   
/*    */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 62 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.MIZU_TAIHO.get(), (Entity)entity, entity.func_226277_ct_(), 1.5D + entity.func_226278_cu_(), entity.func_226281_cx_());
/* 63 */     this.animationComponent.stop(entity);
/* 64 */     this.projectileComponent.shoot(entity, 2.0F, 0.0F);
/* 65 */     this.cooldownComponent.startCooldown(entity, 340.0F);
/*    */   }
/*    */   
/*    */   private MizuTaihoProjectile createProjectile(LivingEntity entity) {
/* 69 */     MizuTaihoProjectile projectile = new MizuTaihoProjectile(entity.field_70170_p, entity);
/* 70 */     return projectile;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\MizuTaihoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */