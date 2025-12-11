/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GrabEntityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class BaraBaraFestivalAbility extends Ability {
/*  51 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bara_bara_festival", new Pair[] {
/*  52 */         (Pair)ImmutablePair.of("Splits the user's body in several small parts that constantly hurt and slow down the target.", null), 
/*  53 */         (Pair)ImmutablePair.of("Splits the user's body in several small parts that swarm around the user acting as a shield.", null)
/*     */       });
/*  55 */   private static final ITextComponent ATTACK_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.bara_bara_festival", "Bara Bara Festival: Attack"));
/*  56 */   private static final ITextComponent SHIELD_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.bara_bara_festival_shield", "Bara Bara Festival: Shield"));
/*     */   
/*  58 */   private static final ResourceLocation BARA_BARA_FESTIVAL_ATTACK_ICON = new ResourceLocation("mineminenomi", "textures/abilities/bara_bara_festival.png");
/*  59 */   private static final ResourceLocation BARA_BARA_FESTIVAL_SHIELD_ICON = new ResourceLocation("mineminenomi", "textures/abilities/bara_bara_festival_shield.png");
/*     */   
/*     */   private static final int COOLDOWN = 300;
/*     */   
/*     */   private static final int HOLD_TIME = 160;
/*     */   private static final int RANGE = 3;
/*     */   private static final int DAMAGE_ATTACK = 6;
/*     */   private static final int DAMAGE_SHIELD = 4;
/*  67 */   public static final AbilityCore<BaraBaraFestivalAbility> INSTANCE = (new AbilityCore.Builder("Bara Bara Festival", AbilityCategory.DEVIL_FRUITS, BaraBaraFestivalAbility::new))
/*  68 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*  69 */         CooldownComponent.getTooltip(300.0F), 
/*  70 */         ContinuousComponent.getTooltip(160.0F)
/*  71 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*  72 */         AbilityDescriptionLine.NEW_LINE, AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(ATTACK_NAME)), 
/*  73 */         AbilityDescriptionLine.IDescriptionLine.of(DESCRIPTION[0]), 
/*  74 */         DealDamageComponent.getTooltip(6.0F)
/*  75 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*  76 */         AbilityDescriptionLine.NEW_LINE, AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)AbilityHelper.mentionText(SHIELD_NAME)), 
/*  77 */         AbilityDescriptionLine.IDescriptionLine.of(DESCRIPTION[1]), 
/*  78 */         RangeComponent.getTooltip(3.0F, RangeComponent.RangeType.AOE), 
/*  79 */         DealDamageComponent.getTooltip(4.0F)
/*  80 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  81 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  82 */       }).build();
/*     */   
/*  84 */   private static final AbilityAttributeModifier STEP_HEIGHT = new AbilityAttributeModifier(AttributeHelper.MORPH_STEP_HEIGHT_UUID, INSTANCE, "Bara Bara Festival Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  86 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::continuityStart).addTickEvent(this::continuityTick).addEndEvent(this::continuityEnd);
/*  87 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.ATTACK)).addChangeModeEvent(this::altModeChange);
/*  88 */   private final ChangeStatsComponent changeStatsComponent = new ChangeStatsComponent((IAbility)this);
/*  89 */   private final GrabEntityComponent grabEntityComponent = new GrabEntityComponent((IAbility)this, false, false, 0.0F);
/*  90 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  91 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  92 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*  93 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.GRAB_ABILITY, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*  95 */   private Interval hurtInterval = new Interval(20);
/*     */   private BaraFestivalEntity baraFestivalEntity;
/*     */   
/*     */   public BaraBaraFestivalAbility(AbilityCore<BaraBaraFestivalAbility> core) {
/*  99 */     super(core);
/*     */     
/* 101 */     this.isNew = true;
/*     */     
/* 103 */     this.changeStatsComponent.addAttributeModifier((Supplier)ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/*     */     
/* 105 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.changeStatsComponent, (AbilityComponent)this.grabEntityComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.morphComponent, (AbilityComponent)this.poolComponent });
/*     */     
/* 107 */     addCanUseCheck(BaraHelper::hasLimbs);
/* 108 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 112 */     if (this.continuousComponent.isContinuous()) {
/* 113 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 117 */     if (this.altModeComponent.getCurrentMode() == Mode.ATTACK) {
/* 118 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 12.0D);
/*     */       
/* 120 */       if (mop instanceof EntityRayTraceResult) {
/* 121 */         EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
/*     */         
/* 123 */         if (entityRayTraceResult.func_216348_a() instanceof LivingEntity) {
/* 124 */           LivingEntity target = (LivingEntity)entityRayTraceResult.func_216348_a();
/*     */           
/* 126 */           if (!target.func_70089_S() || (DevilFruitCapability.get(target).isLogia() && !HakiHelper.hasHardeningActive(entity)) || 
/* 127 */             AbilityHelper.isTargetBlocking(entity, target)) {
/*     */             return;
/*     */           }
/*     */           
/* 131 */           this.grabEntityComponent.grabManually(entity, target);
/*     */         } 
/*     */       } else {
/*     */         
/* 135 */         entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET, new Object[] { getName() }), Util.field_240973_b_);
/*     */         
/*     */         return;
/*     */       } 
/* 139 */     } else if (this.altModeComponent.getCurrentMode() == Mode.SHIELD) {
/* 140 */       this.grabEntityComponent.grabManually(entity, entity);
/*     */     } 
/*     */     
/* 143 */     if (!this.grabEntityComponent.hasGrabbedEntity()) {
/* 144 */       entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET, new Object[] { getName() }), Util.field_240973_b_);
/*     */       
/*     */       return;
/*     */     } 
/* 148 */     this.continuousComponent.startContinuity(entity, 160.0F);
/*     */   }
/*     */   
/*     */   private void continuityStart(LivingEntity entity, IAbility ability) {
/* 152 */     this.hurtInterval.restartIntervalToZero();
/*     */     
/* 154 */     if (this.grabEntityComponent.hasGrabbedEntity()) {
/* 155 */       this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.BARA_FESTIVAL.get());
/* 156 */       this.changeStatsComponent.applyModifiers(entity);
/*     */       
/* 158 */       LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/* 159 */       this.baraFestivalEntity = new BaraFestivalEntity(entity.field_70170_p);
/* 160 */       this.baraFestivalEntity.func_70012_b(target.func_226277_ct_(), target.func_226278_cu_() + 1.0D, target.func_226281_cx_(), 0.0F, 0.0F);
/* 161 */       this.baraFestivalEntity.setTarget(target);
/* 162 */       this.baraFestivalEntity.setOwner(entity.func_110124_au());
/*     */       
/* 164 */       entity.field_70170_p.func_217376_c((Entity)this.baraFestivalEntity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void continuityTick(LivingEntity entity, IAbility ability) {
/* 169 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 173 */     if (this.altModeComponent.getCurrentMode() == Mode.ATTACK) {
/* 174 */       LivingEntity target = this.grabEntityComponent.getGrabbedEntity();
/* 175 */       if (target == null || !target.func_70089_S() || entity.func_70068_e((Entity)target) > 1500.0D) {
/* 176 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/* 180 */       if (this.hurtInterval.canTick() && 
/* 181 */         this.dealDamageComponent.hurtTarget(entity, target, 6.0F)) {
/* 182 */         target.func_195064_c(new EffectInstance(Effects.field_76421_d, 10, 1));
/*     */       
/*     */       }
/*     */     }
/* 186 */     else if (this.altModeComponent.getCurrentMode() == Mode.SHIELD) {
/* 187 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 3.0F);
/* 188 */       for (LivingEntity target : targets) {
/* 189 */         if (this.dealDamageComponent.hurtTarget(entity, target, 4.0F)) {
/* 190 */           Vector3d dist = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D).func_72432_b();
/*     */           
/* 192 */           double power = 2.0D;
/* 193 */           double xSpeed = -dist.field_72450_a * power;
/* 194 */           double zSpeed = -dist.field_72449_c * power;
/*     */           
/* 196 */           AbilityHelper.setDeltaMovement((Entity)target, -xSpeed, 0.20000000298023224D, -zSpeed);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.NO_HANDS.get(), 5, 0));
/*     */   }
/*     */   
/*     */   private void continuityEnd(LivingEntity entity, IAbility ability) {
/* 205 */     if (this.baraFestivalEntity != null) {
/* 206 */       this.baraFestivalEntity.func_70106_y();
/*     */     }
/*     */     
/* 209 */     this.changeStatsComponent.removeModifiers(entity);
/* 210 */     this.grabEntityComponent.release(entity);
/* 211 */     this.morphComponent.stopMorph(entity);
/* 212 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */   
/*     */   private void altModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 216 */     if (mode == Mode.ATTACK) {
/* 217 */       setDisplayName(ATTACK_NAME);
/* 218 */       setDisplayIcon(BARA_BARA_FESTIVAL_ATTACK_ICON);
/*     */     }
/* 220 */     else if (mode == Mode.SHIELD) {
/* 221 */       setDisplayName(SHIELD_NAME);
/* 222 */       setDisplayIcon(BARA_BARA_FESTIVAL_SHIELD_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void switchModeToAttack(LivingEntity entity) {
/* 227 */     if (this.altModeComponent.isMode(Mode.ATTACK)) {
/*     */       return;
/*     */     }
/* 230 */     this.altModeComponent.setMode(entity, Mode.ATTACK);
/*     */   }
/*     */   
/*     */   public void switchModeToShield(LivingEntity entity) {
/* 234 */     if (this.altModeComponent.isMode(Mode.SHIELD)) {
/*     */       return;
/*     */     }
/* 237 */     this.altModeComponent.setMode(entity, Mode.SHIELD);
/*     */   }
/*     */   
/*     */   public boolean isAttackMode() {
/* 241 */     return this.altModeComponent.isMode(Mode.ATTACK);
/*     */   }
/*     */   
/*     */   public boolean isShieldMode() {
/* 245 */     return this.altModeComponent.isMode(Mode.SHIELD);
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 249 */     ATTACK, SHIELD;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraBaraFestivalAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */