/*     */ package xyz.pixelatedw.mineminenomi.abilities.gura;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class TenchiMeidoAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tenchi_meido", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("The user grabs the air and pulls it downwards, after which all of the opponents are tossed into the air.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 400;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final int RANGE = 26;
/*  37 */   public static final AbilityCore<TenchiMeidoAbility> INSTANCE = (new AbilityCore.Builder("Tenchi Meido", AbilityCategory.DEVIL_FRUITS, TenchiMeidoAbility::new))
/*  38 */     .addDescriptionLine(DESCRIPTION)
/*  39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ChargeComponent.getTooltip(20.0F), RangeComponent.getTooltip(26.0F, RangeComponent.RangeType.AOE)
/*  40 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/*  41 */     .build();
/*     */   
/*  43 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  44 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*  46 */   private BreakingBlocksParticleEffect.Details details = new BreakingBlocksParticleEffect.Details(40);
/*     */   
/*     */   public TenchiMeidoAbility(AbilityCore<TenchiMeidoAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     this.isNew = true;
/*  52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  54 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/*  55 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  59 */     this.chargeComponent.startCharging(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  63 */     if (this.chargeComponent.getChargeTime() % 2.0F == 0.0F) {
/*  64 */       List<Vector3d> positions = new ArrayList<>();
/*  65 */       int range = (int)Math.ceil(26.0D);
/*     */       
/*  67 */       for (int x = -range; x < range; x++) {
/*  68 */         for (int z = -range; z < range; z++) {
/*  69 */           if (entity.func_70681_au().nextInt(100) == 0) {
/*  70 */             double posX = entity.func_226277_ct_() + x;
/*  71 */             double posY = entity.func_226278_cu_() - 1.0D;
/*  72 */             double posZ = entity.func_226281_cx_() + z;
/*  73 */             Vector3d pos = new Vector3d(posX, posY, posZ);
/*  74 */             positions.add(pos);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  79 */       if (positions.size() > 0) {
/*  80 */         this.details.setVecPositions(positions);
/*     */       }
/*     */       
/*  83 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BREAKING_BLOCKS.get(), (Entity)entity, 0.0D, 0.0D, 0.0D, (ParticleEffect.Details)this.details);
/*     */     } 
/*     */     
/*  86 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 10, 0));
/*     */     
/*  88 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 26.0F);
/*     */     
/*  90 */     for (LivingEntity target : targets) {
/*  91 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 10, 0, false, false));
/*     */     }
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity player, IAbility ability) {
/*  96 */     if (player.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(player, 26.0F);
/*     */     
/* 102 */     for (LivingEntity target : targets) {
/* 103 */       Vector3d dirVec = player.func_213303_ch().func_178788_d(target.func_213303_ch()).func_72432_b().func_216372_d(15.0D, 1.0D, 15.0D);
/* 104 */       AbilityHelper.setDeltaMovement((Entity)target, -dirVec.field_72450_a, 3.0D, -dirVec.field_72449_c);
/*     */     } 
/*     */     
/* 107 */     this.cooldownComponent.startCooldown(player, 400.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gura\TenchiMeidoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */