/*     */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ParasiteAbility extends Ability {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "parasite", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("By attaching your strings to nearby enemies, they get completely immobilized", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 600;
/*  36 */   public static final AbilityCore<ParasiteAbility> INSTANCE = (new AbilityCore.Builder("Parasite", AbilityCategory.DEVIL_FRUITS, ParasiteAbility::new))
/*  37 */     .addDescriptionLine(DESCRIPTION)
/*  38 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  39 */     .setSourceType(new SourceType[] { SourceType.PHYSICAL
/*  40 */       }).build();
/*     */   
/*  42 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*     */   public ParasiteAbility(AbilityCore<ParasiteAbility> core) {
/*  45 */     super(core);
/*     */     
/*  47 */     this.isNew = true;
/*  48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*     */     
/*  50 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability2) {
/*  54 */     TorikagoAbility torikago = (TorikagoAbility)AbilityDataCapability.get(entity).getEquippedAbility(TorikagoAbility.INSTANCE);
/*     */     
/*  56 */     if (torikago != null && torikago.isPositionInTorikago(entity.func_233580_cy_())) {
/*  57 */       BlockPos centerPos = torikago.getCenter();
/*     */       
/*  59 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, centerPos, 60.0F);
/*     */       
/*  61 */       for (LivingEntity target : targets) {
/*  62 */         if (torikago.isPositionInTorikago(target.func_233580_cy_())) {
/*  63 */           inflictParasiteStun(entity, target);
/*     */         }
/*     */       } 
/*     */     } else {
/*  67 */       EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, 32.0D);
/*     */       
/*  69 */       if (trace.func_216346_c().equals(RayTraceResult.Type.ENTITY) && trace.func_216348_a() instanceof LivingEntity) {
/*  70 */         LivingEntity target = (LivingEntity)trace.func_216348_a();
/*     */         
/*  72 */         inflictParasiteStun(entity, target);
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     this.cooldownComponent.startCooldown(entity, 600.0F);
/*     */   }
/*     */   
/*     */   public static void inflictParasiteStun(LivingEntity attacker, LivingEntity target) {
/*  80 */     if (target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity) {
/*     */       return;
/*     */     }
/*     */     
/*  84 */     if (AbilityHelper.isTargetBlocking(attacker, target)) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     IEntityStats attackerEntityStatsData = EntityStatsCapability.get(attacker);
/*  89 */     IEntityStats targetEntityStatsData = EntityStatsCapability.get(target);
/*     */     
/*  91 */     double attackerDoriki = (attackerEntityStatsData != null) ? attackerEntityStatsData.getDoriki() : 0.0D;
/*  92 */     double targetDoriki = (targetEntityStatsData != null) ? targetEntityStatsData.getDoriki() : 0.0D;
/*     */     
/*  94 */     if (attackerDoriki < targetDoriki) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     if (attackerDoriki == targetDoriki) {
/*  99 */       target.func_195064_c(new EffectInstance(Effects.field_76440_q, 100, 0, false, false));
/* 100 */       target.func_195064_c(new EffectInstance(Effects.field_76421_d, 100, 2, false, false));
/*     */     } else {
/* 102 */       target.func_195064_c(new EffectInstance(Effects.field_76440_q, 300, 0, false, false));
/* 103 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 300, 0, false, false));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\ParasiteAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */