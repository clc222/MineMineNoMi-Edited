/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public class RangeComponent
/*     */   extends AbilityComponent<IAbility>
/*     */ {
/*  20 */   private static final UUID RANGE_BONUS_MANAGER_UUID = UUID.fromString("c3bc77bd-2f17-4d99-bcb6-e2305b5c075f");
/*     */   
/*  22 */   public static final TranslationTextComponent RANGE_STAT = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_STAT_NAME_RANGE); private float previousRange;
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float range, RangeType type) {
/*  25 */     return getTooltip(range, range, type);
/*     */   }
/*     */   private float previousDistance;
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float min, float max, RangeType type) {
/*  29 */     return (e, a) -> {
/*     */         AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)RANGE_STAT, min, max);
/*     */         a.getComponent(ModAbilityKeys.RANGE).ifPresent(());
/*     */         TranslationTextComponent translationTextComponent = ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_BLOCKS;
/*     */         switch (type) {
/*     */           case AOE:
/*     */             translationTextComponent = ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_AOE_BLOCKS;
/*     */             break;
/*     */           case CONE:
/*     */             translationTextComponent = ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_CONE_BLOCKS;
/*     */             break;
/*     */           case LINE:
/*     */             translationTextComponent = ModI18n.ABILITY_DESCRIPTION_STAT_UNIT_LINE_BLOCKS;
/*     */             break;
/*     */         } 
/*     */         return statBuilder.withUnit((ITextComponent)translationTextComponent).build().getStatDescription();
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   private TargetsPredicate areaCheck = TargetsPredicate.DEFAULT_AREA_CHECK;
/*  57 */   private TargetsPredicate lineCheck = TargetsPredicate.DEFAULT_LINE_CHECK;
/*     */   
/*  59 */   private final BonusManager bonusManager = new BonusManager(RANGE_BONUS_MANAGER_UUID);
/*     */   
/*     */   public RangeComponent(IAbility ability) {
/*  62 */     super(ModAbilityKeys.RANGE, ability);
/*  63 */     addBonusManager(this.bonusManager);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInArea(LivingEntity entity, float range) {
/*  67 */     return getTargetsInArea(entity, range, this.areaCheck);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInArea(LivingEntity entity, BlockPos centerPos, float range) {
/*  71 */     return getTargetsInArea(entity, centerPos, range, this.areaCheck);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInArea(LivingEntity entity, float range, TargetsPredicate predicate) {
/*  75 */     return getTargetsInArea(entity, entity.func_233580_cy_(), range, predicate);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInArea(LivingEntity entity, BlockPos centerPos, float range, TargetsPredicate predicate) {
/*  79 */     ensureIsRegistered();
/*  80 */     this.previousRange = range;
/*  81 */     range = this.bonusManager.applyBonus(range);
/*  82 */     return TargetHelper.getEntitiesInArea(entity, centerPos, range, predicate, new Class[0]);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInLine(LivingEntity entity, float distance, float width) {
/*  86 */     return getTargetsInLine(entity, distance, width, this.lineCheck);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInLine(LivingEntity entity, BlockPos centerPos, float distance, float width) {
/*  90 */     return getTargetsInLine(entity, centerPos, distance, width, this.lineCheck);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInLine(LivingEntity entity, float distance, float width, TargetsPredicate predicate) {
/*  94 */     return getTargetsInLine(entity, entity.func_233580_cy_(), distance, width, predicate);
/*     */   }
/*     */   
/*     */   public List<LivingEntity> getTargetsInLine(LivingEntity entity, BlockPos centerPos, float distance, float width, TargetsPredicate predicate) {
/*  98 */     ensureIsRegistered();
/*  99 */     this.previousRange = width;
/* 100 */     this.previousDistance = distance;
/* 101 */     distance = this.bonusManager.applyBonus(distance);
/* 102 */     width = this.bonusManager.applyBonus(width);
/* 103 */     return TargetHelper.getEntitiesInLine(entity, centerPos, distance, width, predicate, new Class[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPreviousRange() {
/* 113 */     return this.previousRange;
/*     */   }
/*     */   
/*     */   public float getRange() {
/* 117 */     return this.bonusManager.applyBonus(this.previousRange);
/*     */   }
/*     */   
/*     */   public float getPreviousDistance() {
/* 121 */     return this.previousDistance;
/*     */   }
/*     */   
/*     */   public float getDistance() {
/* 125 */     return this.bonusManager.applyBonus(this.previousDistance);
/*     */   }
/*     */   
/*     */   public void setAreaCheck(TargetsPredicate check) {
/* 129 */     this.areaCheck = check;
/*     */   }
/*     */   
/*     */   public void setLineCheck(TargetsPredicate check) {
/* 133 */     this.lineCheck = check;
/*     */   }
/*     */   
/*     */   public BonusManager getBonusManager() {
/* 137 */     return this.bonusManager;
/*     */   }
/*     */   
/*     */   public enum RangeType {
/* 141 */     AOE,
/* 142 */     CONE,
/* 143 */     LINE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\RangeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */