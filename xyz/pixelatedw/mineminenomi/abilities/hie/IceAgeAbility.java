/*     */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.SnowBlock;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class IceAgeAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ice_age", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("Freezes a large area around the user and everyone inside of it", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 100;
/*     */   private static final int MIN_COOLDOWN = 200;
/*     */   private static final int MAX_COOLDOWN = 300;
/*     */   private static final int ICE_RANGE = 64;
/*     */   private static final float ENTITY_FREEZE_RANGE = 1.5F;
/*  45 */   public static final AbilityCore<IceAgeAbility> INSTANCE = (new AbilityCore.Builder("Ice Age", AbilityCategory.DEVIL_FRUITS, IceAgeAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 300.0F), ChargeComponent.getTooltip(100.0F), RangeComponent.getTooltip(64.0F, RangeComponent.RangeType.AOE)
/*  48 */       }).setSourceElement(SourceElement.ICE)
/*  49 */     .build();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final BlockProtectionRule PROTECTION_RULE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  59 */     PROTECTION_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID })).addReplaceRules((world, pos, state) -> { if (state.func_177230_c().equals(Blocks.field_150433_aE) && ((Integer)state.func_177229_b((Property)SnowBlock.field_176315_a)).intValue() > 5) { world.func_180501_a(pos, Blocks.field_205164_gk.func_176223_P(), 3); return true; }  return false; }).build();
/*     */   }
/*  61 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */   
/*     */   private final ChargeComponent chargeComponent;
/*     */   private final RangeComponent rangeComponent;
/*     */   private final HitTrackerComponent hitTrackerComponent;
/*     */   
/*     */   public IceAgeAbility(AbilityCore<IceAgeAbility> core) {
/*  68 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() > 0.5D))).addStartEvent(100, this::startChargeEvent).addTickEvent(100, this::duringChargeEvent).addEndEvent(100, this::stopChargeEvent); this.rangeComponent = new RangeComponent((IAbility)this);
/*     */     this.hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*  70 */     this.isNew = true;
/*     */     
/*  72 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.hitTrackerComponent });
/*     */     
/*  74 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  78 */     this.chargeComponent.startCharging(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  82 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     this.hitTrackerComponent.clearHits();
/*  87 */     this.blockPlacingHelper.clearList();
/*  88 */     double radiusXZ = 64.0D;
/*  89 */     double radiusY = 9.0D;
/*     */     
/*  91 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.ICE_AGE_SFX.get(), SoundCategory.PLAYERS, 10.0F, 1.0F);
/*  92 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ICE_AGE.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */     
/*  94 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(); double y;
/*  95 */     for (y = -radiusY; y < radiusY; y++) {
/*  96 */       double x; for (x = -radiusXZ; x < radiusXZ; x++) {
/*  97 */         double z; for (z = -radiusXZ; z < radiusXZ; z++) {
/*     */           
/*  99 */           double posX = entity.func_226277_ct_() + x + ((x < -WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D)) || x > WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/* 100 */           double posY = entity.func_226278_cu_() + y;
/*     */           
/* 102 */           double posZ = entity.func_226281_cx_() + z + ((z < -WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D)) || z > WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/* 104 */           if (AbilityHelper.canPlaceBlock(entity.field_70170_p, posX, posY, posZ, Blocks.field_205164_gk.func_176223_P(), 3, PROTECTION_RULE)) {
/*     */ 
/*     */ 
/*     */             
/* 108 */             mutpos.func_189532_c(posX, posY, posZ);
/* 109 */             this.blockPlacingHelper.addBlockPos((BlockPos)mutpos, (int)(x * x + y * y + z * z));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 116 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/*     */     
/* 118 */     Set<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/* 120 */     int finished = blockList.size() / 100;
/*     */     
/* 122 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/* 123 */       BlockPos blockPos = iterator.next();
/*     */       
/* 125 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/*     */       
/* 129 */       AbilityHelper.placeBlockIfAllowed(entity, blockPos, Blocks.field_205164_gk.func_176223_P(), 3, PROTECTION_RULE);
/*     */       
/* 131 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, blockPos, 1.5F);
/*     */       
/* 133 */       for (LivingEntity target : targets) {
/* 134 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 135 */           EffectInstance instance = new EffectInstance((Effect)ModEffects.FROZEN.get(), 100, 0);
/* 136 */           target.func_195064_c(instance);
/*     */         } 
/*     */       } 
/*     */       
/* 140 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void stopChargeEvent(LivingEntity entity, IAbility ability) {
/* 145 */     this.cooldownComponent.startCooldown(entity, 200.0F + this.chargeComponent.getChargeTime());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceAgeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */