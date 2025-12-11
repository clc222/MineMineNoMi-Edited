/*     */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ChargedTempoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class WeatherBallAbility
/*     */   extends Ability {
/*     */   private static final float COOLDOWN = 10.0F;
/*  32 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.WEATHER_BALLS, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public WeatherBallAbility(AbilityCore core) {
/*  35 */     super(core);
/*     */     
/*  37 */     this.isNew = true;
/*  38 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.poolComponent });
/*     */     
/*  40 */     addCanUseCheck(AbilityHelper::requiresClimaTact);
/*  41 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  45 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, 1.5D);
/*  46 */     ItemStack stack = entity.func_184614_ca();
/*  47 */     if (stack.func_190926_b()) {
/*  48 */       this.cooldownComponent.startCooldown(entity, 10.0F);
/*     */       return;
/*     */     } 
/*  51 */     ClimaTactItem climaTact = (ClimaTactItem)stack.func_77973_b();
/*     */     
/*  53 */     entity.func_184614_ca().func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */ 
/*     */ 
/*     */     
/*  57 */     if (entity.func_213453_ef()) {
/*  58 */       climaTact.chargeWeatherBall(stack, getKind());
/*  59 */       checkForChargeTempo(entity, trace);
/*     */     } else {
/*     */       
/*  62 */       WeatherBallProjectile proj = getWeatherBallEntity(entity, trace);
/*  63 */       entity.field_70170_p.func_217376_c((Entity)proj);
/*     */     } 
/*     */     
/*  66 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkForChargeTempo(LivingEntity entity, EntityRayTraceResult trace) {
/*  74 */     ItemStack stack = entity.func_184614_ca();
/*  75 */     ClimaTactItem climaTact = (ClimaTactItem)stack.func_77973_b();
/*  76 */     IAbilityData props = AbilityDataCapability.get(entity);
/*     */     
/*  78 */     WyHelper.spawnParticleEffect(getParticleEffect(), (Entity)entity, trace.func_216347_e().func_82615_a(), entity.func_226278_cu_(), trace.func_216347_e().func_82616_c());
/*     */     
/*  80 */     WeatherBallKind[] charge = climaTact.checkCharge(stack);
/*  81 */     if (charge.length > 0) {
/*  82 */       Predicate<IAbility> check = abl -> 
/*  83 */         (AbilityCategory.STYLE.isAbilityPartofCategory().test(abl) && abl instanceof ChargedTempoAbility);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       Set<ChargedTempoAbility> availableTempos = props.getPassiveAbilities(check);
/*  90 */       if (availableTempos.size() <= 0) {
/*  91 */         failedTempo(entity, trace, stack);
/*     */         
/*     */         return;
/*     */       } 
/*  95 */       boolean failedTempo = true;
/*  96 */       for (ChargedTempoAbility tempo : availableTempos) {
/*  97 */         if (Arrays.equals((Object[])charge, (Object[])tempo.getTempoOrder()) && tempo.canUse(entity).isSuccess()) {
/*  98 */           tempo.use(entity);
/*  99 */           climaTact.emptyCharge(stack);
/* 100 */           failedTempo = false;
/*     */         } 
/*     */       } 
/*     */       
/* 104 */       if (failedTempo) {
/* 105 */         failedTempo(entity, trace, stack);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void failedTempo(LivingEntity entity, EntityRayTraceResult trace, ItemStack stack) {
/* 111 */     ClimaTactItem climaTact = (ClimaTactItem)stack.func_77973_b();
/* 112 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FAILED_TEMPO.get(), (Entity)entity, trace.func_216347_e().func_82615_a(), entity.func_226278_cu_(), trace.func_216347_e().func_82616_c());
/* 113 */     climaTact.emptyCharge(stack);
/*     */   }
/*     */   
/*     */   public abstract WeatherBallKind getKind();
/*     */   
/*     */   public abstract ParticleEffect<?> getParticleEffect();
/*     */   
/*     */   public abstract WeatherBallProjectile getWeatherBallEntity(LivingEntity paramLivingEntity, EntityRayTraceResult paramEntityRayTraceResult);
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\WeatherBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */