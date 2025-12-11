/*     */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HanamizuShinkenAbility
/*     */   extends Ability
/*     */ {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hanamizu_shinken_shirahadori", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Creates a protecting wall of mucus, protecting the user from incoming physical attacks and physical projectiles.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 60;
/*     */   private static final int COOLDOWN = 100;
/*  40 */   public static final AbilityCore<HanamizuShinkenAbility> INSTANCE = (new AbilityCore.Builder("Hanamizu Shinken Shirahadori", AbilityCategory.DEVIL_FRUITS, HanamizuShinkenAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(60.0F)
/*  43 */       }).build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  46 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnHurtEvent(this::damageTakenEvent);
/*     */   
/*     */   public HanamizuShinkenAbility(AbilityCore<HanamizuShinkenAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     this.isNew = true;
/*  52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent });
/*     */     
/*  54 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  58 */     this.continuousComponent.triggerContinuity(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  62 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 0, false, false));
/*     */     
/*  64 */     Vector3d lookVec = entity.func_70040_Z();
/*     */     
/*  66 */     int range = 2;
/*  67 */     double boxSize = 1.2D;
/*  68 */     for (int i = 0; i < range * 2; i++) {
/*  69 */       double distance = i / 2.0D;
/*  70 */       Vector3d pos = new Vector3d(entity.func_226277_ct_() + lookVec.field_72450_a * distance, entity.func_226278_cu_() + entity.func_70047_e() + lookVec.field_72448_b * distance, entity.func_226281_cx_() + lookVec.field_72449_c * distance);
/*  71 */       List<Entity> targets = entity.field_70170_p.func_175674_a((Entity)entity, new AxisAlignedBB(pos.field_72450_a - boxSize, pos.field_72448_b - boxSize, pos.field_72449_c - boxSize, pos.field_72450_a + boxSize, pos.field_72448_b + boxSize * 2.0D, pos.field_72449_c + boxSize), target -> (target != entity));
/*     */       
/*  73 */       for (Entity target : targets) {
/*  74 */         if (target instanceof AbilityProjectileEntity) {
/*  75 */           AbilityProjectileEntity projectile = (AbilityProjectileEntity)target;
/*  76 */           if (projectile.isPhysical())
/*  77 */             reflectProjectile((Entity)projectile); 
/*     */           continue;
/*     */         } 
/*  80 */         if (target instanceof net.minecraft.entity.projectile.ThrowableEntity || target instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
/*  81 */           reflectProjectile(target);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     if (this.continuousComponent.getContinueTime() % 5.0F == 0.0F) {
/*  87 */       lookVec.func_216372_d(2.5D, 1.0D, 2.5D);
/*  88 */       Vector3d particlesPos = entity.func_213303_ch().func_178787_e(lookVec);
/*  89 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HANAMIZU_SHINKEN_SHIRADORI.get(), (Entity)entity, particlesPos.field_72450_a, particlesPos.field_72448_b, particlesPos.field_72449_c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void reflectProjectile(Entity target) {
/*  94 */     AbilityHelper.setDeltaMovement(target, -(target.func_213322_ci()).field_72450_a / 2.0D, (target.func_213322_ci()).field_72448_b, -(target.func_213322_ci()).field_72449_c / 2.0D);
/*     */   }
/*     */   
/*     */   private float damageTakenEvent(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  98 */     if (this.continuousComponent.isContinuous() && damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isPhysical()) {
/*  99 */       Entity attacker = damageSource.func_76364_f();
/* 100 */       if (attacker != null && attacker instanceof LivingEntity) {
/* 101 */         Vector3d lookVec = entity.func_70040_Z().func_216372_d(4.0D, 4.0D, 4.0D);
/* 102 */         AbilityHelper.setDeltaMovement(attacker, lookVec);
/*     */       } 
/*     */       
/* 105 */       return 0.0F;
/*     */     } 
/*     */     
/* 108 */     return damage;
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 112 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\HanamizuShinkenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */