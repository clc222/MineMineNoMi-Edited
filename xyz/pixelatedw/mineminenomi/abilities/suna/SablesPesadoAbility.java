/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.SablesPesadoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SablesPesadoAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sables_pesado", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("The user compresses a sandstorm to its limits and shoots it at extreme speeds.", null), 
/* 32 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s and charge time is reduced by %s.", new Object[] {
/* 33 */             "§a" + Math.round(19.999998F) + "%§r", "§a" + 
/* 34 */             Math.round(50.0F) + "%§r" }) });
/*    */   private static final float COOLDOWN_BONUS = 0.8F;
/*    */   private static final float CHARGE_BONUS = 0.5F;
/*    */   private static final int COOLDOWN = 500;
/*    */   private static final int CHARGE_TIME = 100;
/* 39 */   public static final AbilityCore<SablesPesadoAbility> INSTANCE = (new AbilityCore.Builder("Sables: Pesado", AbilityCategory.DEVIL_FRUITS, SablesPesadoAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(500.0F), ChargeComponent.getTooltip(100.0F)
/* 42 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 43 */     .build();
/*    */   
/* 45 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/* 46 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/* 48 */   private Interval particleInterval = new Interval(5);
/*    */ 
/*    */   
/*    */   public SablesPesadoAbility(AbilityCore<SablesPesadoAbility> core) {
/* 52 */     super(core);
/*    */     
/* 54 */     this.isNew = true;
/* 55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 57 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*    */     
/* 59 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 63 */     if (!this.chargeComponent.isCharging()) {
/* 64 */       this.chargeComponent.getMaxChargeBonusManager().removeBonus(SunaHelper.DESERT_CHARGE_BONUS);
/* 65 */       if (SunaHelper.isFruitBoosted(entity)) {
/* 66 */         this.chargeComponent.getMaxChargeBonusManager().addBonus(SunaHelper.DESERT_CHARGE_BONUS, "Desert Charge Bonus", BonusOperation.MUL, 0.5F);
/*    */       }
/*    */       
/* 69 */       this.particleInterval.restartIntervalToZero();
/*    */       
/* 71 */       this.chargeComponent.startCharging(entity, 100.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/* 77 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.REDUCED_FALL.get(), 2, 1, false, false));
/* 78 */     if (this.particleInterval.canTick()) {
/* 79 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SABLES_PESADO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     }
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 84 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.SABLES_PESADO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 86 */     this.projectileComponent.shoot(entity, 3.25F, 4.0F);
/*    */     
/* 88 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 89 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 90 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*    */     }
/*    */     
/* 93 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*    */   }
/*    */   
/*    */   private SablesPesadoProjectile createProjectile(LivingEntity entity) {
/* 97 */     SablesPesadoProjectile proj = new SablesPesadoProjectile(entity.field_70170_p, entity);
/* 98 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SablesPesadoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */