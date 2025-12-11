/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GroundDeathAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*     */   private static final float RANGE_BONUS = 1.5F;
/*  43 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ground_death", new Pair[] {
/*  44 */         (Pair)ImmutablePair.of("Dries out the surrounding ground turning everything into sand.", null), 
/*  45 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s and range is increased by %s.", new Object[] {
/*  46 */             "§a" + Math.round(19.999998F) + "%§r", "§a" + 
/*  47 */             Math.round(Math.abs(-0.5F) * 100.0F) + "%§r"
/*     */           })
/*     */       });
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int RANGE = 32;
/*  53 */   public static final AbilityCore<GroundDeathAbility> INSTANCE = (new AbilityCore.Builder("Ground Death", AbilityCategory.DEVIL_FRUITS, GroundDeathAbility::new))
/*  54 */     .addDescriptionLine(DESCRIPTION)
/*  55 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ChargeComponent.getTooltip(60.0F), RangeComponent.getTooltip(32.0F, RangeComponent.RangeType.AOE)
/*  56 */       }).build();
/*     */   
/*  58 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, CoreBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE })).build();
/*     */   
/*     */   private final ChargeComponent chargeComponent;
/*     */   
/*     */   private final HitTrackerComponent hitTrackerComponent;
/*     */   private final RangeComponent rangeComponent;
/*     */   private final BlockPlacingHelper blockPlacingHelper;
/*     */   
/*     */   public GroundDeathAbility(AbilityCore<GroundDeathAbility> core) {
/*  67 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() > 0.2D))).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent); this.hitTrackerComponent = new HitTrackerComponent((IAbility)this); this.rangeComponent = new RangeComponent((IAbility)this);
/*     */     this.blockPlacingHelper = new BlockPlacingHelper();
/*  69 */     this.isNew = true;
/*     */     
/*  71 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  73 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  74 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  75 */     addCanUseCheck(SunaHelper::requiresInactiveDesertGirasole);
/*     */     
/*  77 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  81 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  85 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  89 */     this.blockPlacingHelper.clearList();
/*     */     
/*  91 */     this.rangeComponent.getBonusManager().removeBonus(SunaHelper.DESERT_RANGE_BONUS);
/*  92 */     if (SunaHelper.isFruitBoosted(entity)) {
/*  93 */       this.rangeComponent.getBonusManager().addBonus(SunaHelper.DESERT_RANGE_BONUS, "Desert Range Bonus", BonusOperation.MUL, 1.5F);
/*     */     }
/*     */     
/*  96 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  97 */     for (int j = -8; j < 6; j++) {
/*  98 */       float i; for (i = -32.0F; i < 32.0F; i++) {
/*  99 */         float k; for (k = -32.0F; k < 32.0F; k++) {
/*     */           
/* 101 */           double posX = entity.func_226277_ct_() + i + ((i < -WyHelper.randomWithRange(16, 24) || i > WyHelper.randomWithRange(16, 24)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/* 102 */           double posY = entity.func_226278_cu_() + j;
/*     */           
/* 104 */           double posZ = entity.func_226281_cx_() + k + ((k < -WyHelper.randomWithRange(16, 24) || k > WyHelper.randomWithRange(16, 24)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/* 106 */           if (AbilityHelper.canPlaceBlock(entity.field_70170_p, posX, posY, posZ, Blocks.field_150354_m.func_176223_P(), 3, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID)) {
/*     */ 
/*     */ 
/*     */             
/* 110 */             mutpos.func_189532_c(posX, posY, posZ);
/* 111 */             this.blockPlacingHelper.addBlockPos((BlockPos)mutpos, (int)(i * i + (j * j) + k * k));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 118 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 122 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/*     */     
/* 124 */     Set<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/* 126 */     int finished = blockList.size() / 100;
/*     */     
/* 128 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/* 129 */       BlockPos blockPos = iterator.next();
/*     */       
/* 131 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/*     */       
/* 135 */       boolean isAir = entity.field_70170_p.func_180495_p(blockPos).func_196958_f();
/* 136 */       if (!isAir) {
/* 137 */         boolean isWater = (entity.field_70170_p.func_180495_p(blockPos).func_185904_a() == Material.field_151586_h);
/* 138 */         AbilityHelper.placeBlockIfAllowed(entity, blockPos, isWater ? Blocks.field_150350_a.func_176223_P() : Blocks.field_150354_m.func_176223_P(), 3, GRIEF_RULE);
/*     */       } 
/*     */       
/* 141 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, blockPos, 1.5F);
/*     */       
/* 143 */       for (LivingEntity target : targets) {
/* 144 */         if (this.hitTrackerComponent.canHit((Entity)target) && target.func_110124_au() != entity.func_110124_au()) {
/* 145 */           AbilityHelper.createFilledCube(target.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 1, (int)Math.round(target.func_70047_e() - target.func_226278_cu_()), 1, Blocks.field_150354_m, GRIEF_RULE);
/* 146 */           target.func_195064_c(new EffectInstance(Effects.field_76419_f, 200, 3, false, false));
/* 147 */           target.func_195064_c(new EffectInstance((Effect)ModEffects.DEHYDRATION.get(), 200, 3, false, false));
/* 148 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.GROUND_DEATH.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */         } 
/*     */       } 
/*     */       
/* 152 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 157 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 162 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 163 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 166 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\GroundDeathAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */