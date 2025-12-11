/*     */ package xyz.pixelatedw.mineminenomi.abilities.kuku;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GourmetSnipeAbility extends Ability {
/*  43 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gourmet_snipe", new Pair[] {
/*  44 */         (Pair)ImmutablePair.of("Launches the user forward and converts everything cut into food.", null)
/*     */       });
/*  46 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/*  47 */   private static final ArrayList<Item> FOODS = new ArrayList<>(Arrays.asList(new Item[] { Items.field_151077_bg, Items.field_179559_bp, Items.field_196102_ba, Items.field_151034_e, Items.field_179557_bn, Items.field_196104_bb, Items.field_226638_pX_, Items.field_151157_am }));
/*     */   
/*     */   private static final int HOLD_TIME = 10;
/*     */   
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final float RANGE = 2.0F;
/*     */   private static final float DAMAGE = 20.0F;
/*  54 */   public static final AbilityCore<GourmetSnipeAbility> INSTANCE = (new AbilityCore.Builder("Gourmet Snipe", AbilityCategory.DEVIL_FRUITS, GourmetSnipeAbility::new))
/*  55 */     .addDescriptionLine(DESCRIPTION)
/*  56 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), RangeComponent.getTooltip(2.0F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(20.0F)
/*  57 */       }).setSourceType(new SourceType[] { SourceType.PHYSICAL
/*  58 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  59 */     .build();
/*     */   
/*  61 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  62 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  63 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  64 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  65 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*     */   
/*  67 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(20);
/*     */   private int initialY;
/*     */   
/*     */   public GourmetSnipeAbility(AbilityCore<GourmetSnipeAbility> core) {
/*  71 */     super(core);
/*     */     
/*  73 */     this.isNew = true;
/*  74 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.hitTrackerComponent });
/*     */     
/*  76 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  77 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*  78 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  86 */     this.initialY = (int)entity.func_226278_cu_();
/*     */     
/*  88 */     Vector3d speed = entity.func_70040_Z().func_216372_d(6.0D, 6.0D, 6.0D);
/*  89 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, speed.field_72448_b, speed.field_72449_c);
/*     */     
/*  91 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*  92 */     this.animationComponent.start(entity, ModAnimations.SHOOT_SELF_FORWARD);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  96 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     Vector3d speed = entity.func_70040_Z().func_216372_d(1.6D, 1.0D, 1.6D);
/* 101 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, entity.func_213322_ci().func_82617_b(), speed.field_72449_c);
/*     */     
/* 103 */     if (entity.func_70089_S()) {
/* 104 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 2.0F);
/* 105 */       for (LivingEntity target : targets) {
/* 106 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 107 */           this.dealDamageComponent.hurtTarget(entity, target, 20.0F);
/*     */         }
/*     */       } 
/*     */       
/* 111 */       List<BlockPos> positions = new ArrayList<>();
/* 112 */       for (BlockPos location : WyHelper.getNearbyBlocks((Entity)entity, 2)) {
/* 113 */         if (location.func_177956_o() >= this.initialY && AbilityHelper.placeBlockIfAllowed(entity, location, Blocks.field_150350_a.func_176223_P(), GRIEF_RULE)) {
/* 114 */           if (entity.field_70170_p.field_73012_v.nextDouble() > 0.4D) {
/* 115 */             ItemStack foodStack = new ItemStack((IItemProvider)FOODS.get((int)WyHelper.randomWithRange(0, FOODS.size() - 1)));
/* 116 */             entity.field_70170_p.func_217376_c((Entity)new ItemEntity(entity.field_70170_p, location.func_177958_n(), location.func_177956_o(), location.func_177952_p(), foodStack));
/*     */           } 
/* 118 */           positions.add(location);
/*     */         } 
/*     */       } 
/*     */       
/* 122 */       if (positions.size() > 0) {
/* 123 */         this.details.setPositions(positions);
/* 124 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 130 */     this.animationComponent.stop(entity);
/* 131 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kuku\GourmetSnipeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */