/*     */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KurouzuAbility extends Ability {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kurouzu", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Creates a strong gravitational force, that pulls the opponent towards the user.", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 240.0F;
/*     */   private static final float DAMAGE = 30.0F;
/*     */   private static final float CONTINUITY_TIME = 200.0F;
/*     */   private static final float CHARGE_TIME = 20.0F;
/*  46 */   public static final AbilityCore<KurouzuAbility> INSTANCE = (new AbilityCore.Builder("Kurouzu", AbilityCategory.DEVIL_FRUITS, KurouzuAbility::new))
/*  47 */     .addDescriptionLine(DESCRIPTION)
/*  48 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(200.0F), ChargeComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(30.0F)
/*  49 */       }).setSourceType(new SourceType[] { SourceType.FIST
/*  50 */       }).build();
/*     */   
/*  52 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true))
/*  53 */     .addStartEvent(this::onContinuityStart)
/*  54 */     .addTickEvent(this::onContinuityTick)
/*  55 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*  57 */   private final GrabEntityComponent grabEntityComponent = new GrabEntityComponent((IAbility)this, false, false, 1.0F);
/*     */   
/*  59 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  60 */     .addStartEvent(this::onChargeStart)
/*  61 */     .addTickEvent(this::onChargeTick)
/*  62 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  64 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*  66 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  68 */   private Interval particleInterval = new Interval(5);
/*     */   
/*     */   public KurouzuAbility(AbilityCore<KurouzuAbility> core) {
/*  71 */     super(core);
/*     */     
/*  73 */     this.isNew = true;
/*     */     
/*  75 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.grabEntityComponent, (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  77 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  81 */     if (this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     this.continuousComponent.triggerContinuity(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  89 */     this.animationComponent.start(entity, ModAnimations.POINT_LEFT_ARM);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  93 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  97 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 128.0D);
/*     */     
/*  99 */     double i = (mop.func_216347_e()).field_72450_a;
/* 100 */     double j = (mop.func_216347_e()).field_72448_b - ((mop instanceof net.minecraft.util.math.EntityRayTraceResult) ? 1.0D : 0.0D);
/* 101 */     double k = (mop.func_216347_e()).field_72449_c;
/*     */     
/* 103 */     boolean canTick = this.particleInterval.canTick();
/*     */     
/* 105 */     if (canTick) {
/* 106 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KUROUZU.get(), (Entity)entity, i, j, k);
/*     */       
/* 108 */       Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/* 109 */       Vector3d particlePos = entity.func_213303_ch().func_72441_c(lookVec.field_72450_a, lookVec.field_72448_b + 1.5D, lookVec.field_72449_c);
/*     */       
/* 111 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KUROUZU_CHARGING.get(), (Entity)entity, particlePos.func_82615_a(), particlePos.func_82617_b(), particlePos.func_82616_c());
/*     */     } 
/*     */     
/* 114 */     if (mop.func_216346_c() == RayTraceResult.Type.MISS) {
/*     */       return;
/*     */     }
/*     */     
/* 118 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(new Vector3d(i, j, k), (IWorld)entity.field_70170_p, 5.0D, ModEntityPredicates.getEnemyFactions(entity));
/*     */     
/* 120 */     for (LivingEntity target : targets) {
/* 121 */       Vector3d pos = entity.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b();
/*     */       
/* 123 */       AbilityHelper.setDeltaMovement((Entity)target, pos);
/*     */       
/* 125 */       if (canTick) {
/* 126 */         target.func_195064_c(new EffectInstance(Effects.field_76421_d, 10, 5));
/* 127 */         target.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 10, 5));
/*     */         
/* 129 */         if (this.grabEntityComponent.grabNearest(entity, false)) {
/* 130 */           this.continuousComponent.stopContinuity(entity);
/* 131 */           this.chargeComponent.startCharging(entity, 20.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 138 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 142 */     if (!this.grabEntityComponent.hasGrabbedEntity()) {
/* 143 */       this.animationComponent.stop(entity);
/* 144 */       this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 149 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 153 */     if (!this.grabEntityComponent.canContinueGrab(entity)) {
/* 154 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 159 */     LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */     
/* 161 */     target.func_195064_c(new EffectInstance(Effects.field_76419_f, 100, 5));
/*     */     
/* 163 */     if (target instanceof net.minecraft.entity.player.PlayerEntity) {
/* 164 */       AbilityHelper.disableAbilities(target, 20, abl -> (abl.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*     */     }
/*     */   }
/*     */   
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 169 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 173 */     if (!this.grabEntityComponent.canContinueGrab(entity)) {
/* 174 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 179 */     LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */     
/* 181 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, -0.5D, -target.func_213302_cg() / 2.0D, target.func_213311_cf() - 0.2D);
/*     */     
/* 183 */     AbilityHelper.setDeltaMovement((Entity)target, pos.func_178788_d(target.func_213303_ch()), true);
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 187 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 191 */     if (this.grabEntityComponent.canContinueGrab(entity)) {
/* 192 */       LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/*     */       
/* 194 */       if (this.dealDamageComponent.hurtTarget(entity, target, 30.0F)) {
/* 195 */         Vector3d dir = entity.func_70040_Z().func_216372_d(3.0D, 0.0D, 3.0D);
/*     */         
/* 197 */         AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(dir.field_72450_a, 0.2D, dir.field_72449_c));
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     this.animationComponent.stop(entity);
/* 202 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\KurouzuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */