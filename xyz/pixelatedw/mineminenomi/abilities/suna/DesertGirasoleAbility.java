/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DesertGirasoleAbility extends Ability {
/*     */   private static final float COOLDOWN_BONUS = 0.8F;
/*  40 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "desert_girasole", new Pair[] {
/*  41 */         (Pair)ImmutablePair.of("A giant pit of quicksand will be formed with the sand being taken away by underground rivers.", null), 
/*  42 */         (Pair)ImmutablePair.of("Can only be used on sand.", null), 
/*  43 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s and range is increased by %s.", new Object[] {
/*  44 */             "§a" + Math.round(19.999998F) + "%§r", "§a" + 
/*  45 */             Math.round(Math.abs(-1.0F) * 100.0F) + "%§r" }) });
/*     */   
/*     */   private static final float RANGE_BONUS = 2.0F;
/*     */   private static final int COOLDOWN = 600;
/*     */   private static final int CHARGE_TIME = 100;
/*     */   private static final int RANGE = 12;
/*  51 */   public static final AbilityCore<DesertGirasoleAbility> INSTANCE = (new AbilityCore.Builder("Desert Girasole", AbilityCategory.DEVIL_FRUITS, DesertGirasoleAbility::new))
/*  52 */     .addDescriptionLine(DESCRIPTION)
/*  53 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ChargeComponent.getTooltip(100.0F), RangeComponent.getTooltip(12.0F, RangeComponent.RangeType.AOE)
/*  54 */       }).build();
/*     */   
/*  56 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargingEvent);
/*  57 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*  59 */   private BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */   
/*     */   public DesertGirasoleAbility(AbilityCore<DesertGirasoleAbility> core) {
/*  62 */     super(core);
/*     */     
/*  64 */     this.isNew = true;
/*  65 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.chargeComponent });
/*     */     
/*  67 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  68 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  69 */     addCanUseCheck(DesertGirasoleAbility::requiresInactiveGroundDeath);
/*     */     
/*  71 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.chargeComponent.startCharging(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  79 */     this.rangeComponent.getBonusManager().removeBonus(SunaHelper.DESERT_RANGE_BONUS);
/*  80 */     if (SunaHelper.isFruitBoosted(entity)) {
/*  81 */       this.rangeComponent.getBonusManager().addBonus(SunaHelper.DESERT_RANGE_BONUS, "Desert Range Bonus", BonusOperation.MUL, 2.0F);
/*     */     }
/*     */     
/*  84 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(); float i;
/*  85 */     for (i = -12.0F; i < 12.0F; i++) {
/*  86 */       for (int j = -8; j < 8; j++) {
/*  87 */         float k; for (k = -12.0F; k < 12.0F; k++) {
/*  88 */           double posX = entity.func_226277_ct_() + i + ((i < -WyHelper.randomWithRange(8, 12) || i > WyHelper.randomWithRange(8, 12)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*  89 */           double posY = entity.func_226278_cu_() + j;
/*  90 */           double posZ = entity.func_226281_cx_() + k + ((k < -WyHelper.randomWithRange(8, 12) || k > WyHelper.randomWithRange(8, 12)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/*  92 */           mutpos.func_189532_c(posX, posY, posZ);
/*  93 */           if (entity.field_70170_p.func_180495_p((BlockPos)mutpos).func_177230_c().equals(Blocks.field_150354_m)) {
/*  94 */             this.blockPlacingHelper.addBlockPos((BlockPos)mutpos, (int)(i * i + (j * j) + k * k));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     if (this.blockPlacingHelper.getBlockList().size() > 0) {
/* 101 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DESERT_GIRASOLE_1.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 106 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 110 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */     
/* 112 */     Set<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/* 114 */     int finished = blockList.size() / 100;
/* 115 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/* 116 */       BlockPos blockPos = iterator.next();
/* 117 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/* 120 */       AbilityHelper.placeBlockIfAllowed(entity, blockPos, ((Block)ModBlocks.SUNA_SAND.get()).func_176223_P(), DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
/* 121 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/* 126 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DESERT_GIRASOLE_2.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     
/* 128 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 129 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 130 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*     */     }
/*     */     
/* 133 */     this.cooldownComponent.startCooldown(entity, 600.0F);
/*     */   }
/*     */   
/*     */   private static AbilityUseResult requiresInactiveGroundDeath(LivingEntity entity, IAbility ability) {
/* 137 */     GroundDeathAbility groundDeathAbility = (GroundDeathAbility)AbilityDataCapability.get(entity).getEquippedAbility(GroundDeathAbility.INSTANCE);
/*     */     
/* 139 */     if (groundDeathAbility != null && groundDeathAbility.isCharging()) {
/* 140 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_TOGETHER, new Object[] { ability.getDisplayName().getString(), groundDeathAbility.getDisplayName().getString() }));
/*     */     }
/*     */     
/* 143 */     return AbilityUseResult.success();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertGirasoleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */