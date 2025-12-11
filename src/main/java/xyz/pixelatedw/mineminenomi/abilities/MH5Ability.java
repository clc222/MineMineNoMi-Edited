/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.MH5CloudEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MH5Ability extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mh5", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("Charges a deathly poison bomb causing all within its radius to die when inhaling the resulting gas.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 1200;
/*     */   private static final int CHARGE_TIME = 200;
/*     */   public static final int RANGE = 100;
/*  43 */   public static final AbilityCore<MH5Ability> INSTANCE = (new AbilityCore.Builder("MH5", AbilityCategory.EQUIPMENT, MH5Ability::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1200.0F), ChargeComponent.getTooltip(200.0F), RangeComponent.getTooltip(100.0F, RangeComponent.RangeType.AOE)
/*  46 */       }).setUnlockCheck(MH5Ability::canUnlock)
/*  47 */     .build();
/*     */   
/*  49 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   private BlockPos targetPosition;
/*     */   private float initialHealth;
/*     */   private double hpThreshold;
/*     */   
/*     */   public MH5Ability(AbilityCore<MH5Ability> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  62 */     addCanUseCheck(this::canUse);
/*  63 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  67 */     this.chargeComponent.startCharging(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.animationComponent.start(entity, ModAnimations.MH5_CHARGING);
/*     */     
/*  73 */     this.initialHealth = entity.func_110143_aJ();
/*  74 */     this.hpThreshold = WyHelper.percentage(20.0D, entity.func_110138_aP());
/*     */     
/*  76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 200, 0));
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  80 */     if (entity.func_110143_aJ() <= this.initialHealth - this.hpThreshold) {
/*  81 */       this.chargeComponent.forceStopCharging(entity);
/*  82 */       entity.func_195063_d((Effect)ModEffects.MOVEMENT_BLOCKED.get());
/*  83 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 100, 0));
/*  84 */       this.cooldownComponent.startCooldown(entity, 1200.0F);
/*     */       
/*     */       return;
/*     */     } 
/*  88 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHARGE_MH5_BOMB.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  92 */     this.animationComponent.stop(entity);
/*     */     
/*  94 */     if (this.targetPosition == null) {
/*  95 */       Vector3d startVec = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*  96 */       Vector3d endVec = startVec.func_178787_e(entity.func_70040_Z().func_186678_a(128.0D));
/*  97 */       BlockRayTraceResult result = entity.field_70170_p.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)entity));
/*  98 */       this.targetPosition = result.func_216350_a();
/*     */     } 
/*     */     
/* 101 */     MH5CloudEntity cloud = new MH5CloudEntity(entity.field_70170_p);
/* 102 */     cloud.setLife(100);
/* 103 */     cloud.func_174828_a(this.targetPosition, 0.0F, 0.0F);
/* 104 */     AbilityHelper.setDeltaMovement((Entity)cloud, 0.0D, 0.0D, 0.0D);
/* 105 */     entity.field_70170_p.func_217376_c((Entity)cloud);
/*     */     
/* 107 */     this.cooldownComponent.startCooldown(entity, 1200.0F);
/* 108 */     this.targetPosition = null;
/*     */   }
/*     */   
/*     */   public void setTargetPosition(BlockPos targetPos) {
/* 112 */     this.targetPosition = targetPos;
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 116 */     if (hasArmorSetEquipped(entity)) {
/* 117 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 120 */     return AbilityUseResult.fail(null);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 124 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/* 125 */       return false;
/*     */     }
/*     */     
/* 128 */     return hasArmorSetEquipped(entity);
/*     */   }
/*     */   
/*     */   private static boolean hasArmorSetEquipped(LivingEntity entity) {
/* 132 */     ItemStack chestStack = entity.func_184582_a(EquipmentSlotType.CHEST);
/* 133 */     if (chestStack.func_190926_b() || chestStack.func_77973_b() != ModArmors.WOOTZ_STEEL_ARMOR.get()) {
/* 134 */       return false;
/*     */     }
/*     */     
/* 137 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\MH5Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */