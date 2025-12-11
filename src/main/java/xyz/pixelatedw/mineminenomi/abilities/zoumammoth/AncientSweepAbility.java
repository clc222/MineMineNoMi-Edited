/*     */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AncientSweepAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ancient_sweep", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Hits all enemies in a frontal cone with your trunk.", null) });
/*     */   
/*     */   private static final int COOLDOWN = 160;
/*     */   
/*     */   private static final int CHARGE_TIME = 40;
/*     */   private static final int HEAVY_RANGE = 3;
/*     */   private static final int GUARD_RANGE = 6;
/*     */   
/*     */   static {
/*  42 */     RANGE_TOOLTIP = ((entity, ability) -> {
/*     */         int range = 3;
/*     */         
/*     */         if (((MorphInfo)ModMorphs.MAMMOTH_GUARD.get()).isActive(entity)) {
/*     */           range = 6;
/*     */         }
/*     */         
/*     */         return RangeComponent.getTooltip(range, RangeComponent.RangeType.LINE).expand(entity, (IAbility)ability);
/*     */       });
/*     */     
/*  52 */     DAMAGE_TOOLTIP = ((entity, ability) -> {
/*     */         int damage = 15;
/*     */         if (((MorphInfo)ModMorphs.MAMMOTH_GUARD.get()).isActive(entity))
/*     */           damage = 20; 
/*     */         return DealDamageComponent.getTooltip(damage).expand(entity, (IAbility)ability);
/*     */       });
/*     */   }
/*     */   private static final int HEAVY_DAMAGE = 15; private static final int GUARD_DAMAGE = 20;
/*     */   private static final AbilityDescriptionLine.IDescriptionLine<AncientSweepAbility> RANGE_TOOLTIP;
/*     */   private static final AbilityDescriptionLine.IDescriptionLine<AncientSweepAbility> DAMAGE_TOOLTIP;
/*  62 */   public static final AbilityCore<AncientSweepAbility> INSTANCE = (new AbilityCore.Builder("Ancient Sweep", AbilityCategory.DEVIL_FRUITS, AncientSweepAbility::new))
/*  63 */     .addDescriptionLine(DESCRIPTION)
/*  64 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  65 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ChargeComponent.getTooltip(40.0F), RANGE_TOOLTIP, DAMAGE_TOOLTIP
/*  66 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  67 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  68 */       }).build();
/*     */   
/*  70 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addEndEvent(100, this::endChargeEvent);
/*  71 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  72 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  73 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.MAMMOTH_GUARD.get(), new MorphInfo[] { (MorphInfo)ModMorphs.MAMMOTH_HEAVY.get() });
/*     */   
/*     */   public AncientSweepAbility(AbilityCore<AncientSweepAbility> core) {
/*  76 */     super(core);
/*     */     
/*  78 */     this.isNew = true;
/*  79 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.requireMorphComponent });
/*     */     
/*  81 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  85 */     this.chargeComponent.startCharging(entity, 40.0F);
/*     */   }
/*     */   
/*     */   public void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  89 */     float damage = 15.0F;
/*  90 */     float radius = 3.0F;
/*     */     
/*  92 */     if (((MorphInfo)ModMorphs.MAMMOTH_GUARD.get()).isActive(entity)) {
/*  93 */       radius *= 2.0F;
/*  94 */       damage += 5.0F;
/*     */     } 
/*     */     
/*  97 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, radius, 3.0F);
/*     */     
/*  99 */     for (LivingEntity target : targets) {
/* 100 */       if (this.dealDamageComponent.hurtTarget(entity, target, damage)) {
/* 101 */         Vector3d speed = WyHelper.propulsion(entity, 3.0D, 3.0D);
/* 102 */         AbilityHelper.setDeltaMovement((Entity)target, speed.field_72450_a, entity.func_213322_ci().func_82617_b() + 0.5D, speed.field_72449_c);
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     if (!entity.field_70170_p.field_72995_K) {
/* 107 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */     }
/*     */     
/* 110 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\AncientSweepAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */