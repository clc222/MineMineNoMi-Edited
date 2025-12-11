/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PearlFireAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "pearl_fire", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Sets the pearl armor on fire causing any enemy that hits you to take damage and any enemy you hit to be set on fire.", null)
/*     */       });
/*     */   
/*     */   private static final int HOLD_TIME = 1200;
/*     */   private static final int MIN_COOLDOWN = 100;
/*     */   private static final int MAX_COOLDOWN = 1300;
/*  39 */   public static final AbilityCore<PearlFireAbility> INSTANCE = (new AbilityCore.Builder("Pearl Fire", AbilityCategory.EQUIPMENT, PearlFireAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 1300.0F), ContinuousComponent.getTooltip(1200.0F)
/*  42 */       }).setUnlockCheck(PearlFireAbility::canUnlock)
/*  43 */     .build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  46 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnHurtEvent(this::damageTakenEvent);
/*  47 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  48 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::onHit).addTryHitEvent(this::tryHitEvent);
/*     */   
/*     */   public PearlFireAbility(AbilityCore<PearlFireAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*     */     
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.hitTriggerComponent });
/*     */     
/*  57 */     addCanUseCheck(this::canUse);
/*  58 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  62 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  66 */     if (!entity.field_70170_p.field_72995_K) {
/*  67 */       for (int i = 0; i < 2; i++) {
/*  68 */         double x = WyHelper.randomDouble();
/*  69 */         double y = WyHelper.randomDouble() / 3.0D;
/*  70 */         double z = WyHelper.randomDouble();
/*  71 */         SimpleParticleData data = new SimpleParticleData((ParticleType)ModParticleTypes.MERA.get());
/*  72 */         data.setLife(35);
/*  73 */         data.setSize(1.0F + entity.func_70681_au().nextFloat());
/*  74 */         data.setMotion(x / 200.0D, 0.02D, z / 200.0D);
/*  75 */         data.setColor(1.0F, 1.0F, 1.0F, 0.4F);
/*  76 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)entity.field_70170_p, entity.func_226277_ct_() + x, entity.func_226278_cu_() + y + 0.5D, entity.func_226281_cx_() + z);
/*  77 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)entity.field_70170_p, entity.func_226277_ct_() + x, entity.func_226278_cu_() + y + 1.5D, entity.func_226281_cx_() + z);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  83 */     this.cooldownComponent.startCooldown(entity, 100.0F + this.continuousComponent.getContinueTime());
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  87 */     if (!this.continuousComponent.isContinuous()) {
/*  88 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/*  91 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   public boolean onHit(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  95 */     AbilityHelper.setSecondsOnFireBy((Entity)target, 2, entity);
/*     */     
/*  97 */     return true;
/*     */   }
/*     */   
/*     */   private float damageTakenEvent(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 101 */     if (this.continuousComponent.isContinuous() && damageSource.func_76364_f() != null && damageSource.func_76364_f() instanceof LivingEntity) {
/* 102 */       this.dealDamageComponent.hurtTarget(user, (LivingEntity)damageSource.func_76364_f(), damage / 3.0F);
/* 103 */       return damage - damage / 3.0F;
/*     */     } 
/*     */     
/* 106 */     return damage;
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 110 */     if (hasArmorSetEquipped(entity)) {
/* 111 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 114 */     return AbilityUseResult.fail(null);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 118 */     return hasArmorSetEquipped(entity);
/*     */   }
/*     */   
/*     */   private static boolean hasArmorSetEquipped(LivingEntity entity) {
/* 122 */     ItemStack headStack = entity.func_184582_a(EquipmentSlotType.HEAD);
/* 123 */     if (headStack.func_190926_b() || headStack.func_77973_b() != ModArmors.PEARL_HAT.get()) {
/* 124 */       return false;
/*     */     }
/*     */     
/* 127 */     ItemStack chestStack = entity.func_184582_a(EquipmentSlotType.CHEST);
/* 128 */     if (chestStack.func_190926_b() || chestStack.func_77973_b() != ModArmors.PEARL_ARMOR.get()) {
/* 129 */       return false;
/*     */     }
/*     */     
/* 132 */     ItemStack legsStack = entity.func_184582_a(EquipmentSlotType.LEGS);
/* 133 */     if (legsStack.func_190926_b() || legsStack.func_77973_b() != ModArmors.PEARL_LEGS.get()) {
/* 134 */       return false;
/*     */     }
/*     */     
/* 137 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\PearlFireAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */