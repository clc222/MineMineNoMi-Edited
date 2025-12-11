/*     */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.DarknessBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BlackHoleAbility extends Ability {
/*  42 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "black_hole", new Pair[] {
/*  43 */         (Pair)ImmutablePair.of("The user spreads darkness over the target area, which engulfs and suffocates anyone and anything inside of it.", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 100;
/*     */   private static final int MAX_COOLDOWN = 400;
/*     */   private static final float MIN_COOLDOWN = 200.0F;
/*     */   private static final int RANGE = 32;
/*  50 */   public static final AbilityCore<BlackHoleAbility> INSTANCE = (new AbilityCore.Builder("Black Hole", AbilityCategory.DEVIL_FRUITS, BlackHoleAbility::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 400.0F), ChargeComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), RangeComponent.getTooltip(32.0F, RangeComponent.RangeType.AOE)
/*  53 */       }).build();
/*     */   
/*  55 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this))
/*  56 */     .addStartEvent(this::onChargeStart)
/*  57 */     .addTickEvent(this::onChargeTick)
/*  58 */     .addEndEvent(this::onChargeEnd);
/*     */   
/*  60 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/*     */   
/*  62 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  63 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*     */   
/*  65 */   private List<LivingEntity> targets = new ArrayList<>();
/*     */   
/*  67 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */   
/*  69 */   private int amountToFix = 0;
/*     */   
/*  71 */   private double timeToFix = 0.0D;
/*     */   
/*  73 */   private State state = State.ABSORBING;
/*     */   
/*     */   public BlackHoleAbility(AbilityCore<BlackHoleAbility> core) {
/*  76 */     super(core);
/*     */     
/*  78 */     this.isNew = true;
/*     */     
/*  80 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  82 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  86 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     if (this.state == State.ABSORBING) {
/*  91 */       if (!this.chargeComponent.isCharging()) {
/*  92 */         this.chargeComponent.startCharging(entity, 100.0F);
/*  93 */       } else if (this.chargeComponent.getChargePercentage() >= 0.2F) {
/*  94 */         this.chargeComponent.stopCharging(entity);
/*     */       } 
/*  96 */     } else if (this.state == State.RELEASING && 
/*  97 */       this.continuousComponent.isContinuous()) {
/*  98 */       this.continuousComponent.stopContinuity(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 104 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (this.state == State.ABSORBING) {
/* 111 */       this.blockPlacingHelper.clearList();
/*     */       
/* 113 */       float spread = 0.65F;
/*     */       
/* 115 */       BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 116 */       for (int i = -32; i < 32; i++) {
/* 117 */         for (int j = -6; j < 3; j++) {
/* 118 */           for (int k = -32; k < 32; k++) {
/*     */             
/* 120 */             double posX = entity.func_226277_ct_() + i + ((i < -WyHelper.randomWithRange((int)(32.0F * spread), (int)(32.0F * (spread + 0.35F))) || i > WyHelper.randomWithRange((int)(32.0F * spread), (int)(32.0F * (spread + 0.35F)))) ? WyHelper.randomWithRange(-2, 2) : 0.0D);
/* 121 */             double posY = entity.func_226278_cu_() + j;
/*     */             
/* 123 */             double posZ = entity.func_226281_cx_() + k + ((k < -WyHelper.randomWithRange((int)(32.0F * spread), (int)(32.0F * (spread + 0.35F))) || k > WyHelper.randomWithRange((int)(32.0F * spread), (int)(32.0F * (spread + 0.35F)))) ? WyHelper.randomWithRange(-2, 2) : 0.0D);
/*     */             
/* 125 */             if (AbilityHelper.canPlaceBlock(entity.field_70170_p, posX, posY, posZ, ((Block)ModBlocks.DARKNESS.get()).func_176223_P(), 3, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/*     */ 
/*     */ 
/*     */               
/* 129 */               mutpos.func_189532_c(posX, posY, posZ);
/* 130 */               this.blockPlacingHelper.addBlockPos((BlockPos)mutpos, i * i + j * j + k * k);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void onChargeTick(LivingEntity entity, IAbility ability) {
/* 138 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 142 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 144 */     AbsorbedBlocksAbility absorbedBlocksAbility = (AbsorbedBlocksAbility)abilityDataProps.getPassiveAbility(AbsorbedBlocksAbility.INSTANCE);
/*     */     
/* 146 */     if (this.state == State.ABSORBING) {
/* 147 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/*     */       
/* 149 */       Set<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */       
/* 151 */       int finished = blockList.size() / 100;
/*     */       
/* 153 */       int i = 0;
/* 154 */       for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/* 155 */         BlockPos blockPos = iterator.next();
/*     */         
/* 157 */         if (finished-- < 0) {
/*     */           break;
/*     */         }
/*     */         
/* 161 */         BlockState oldState = entity.field_70170_p.func_180495_p(blockPos);
/*     */         
/* 163 */         if (AbilityHelper.placeBlockIfAllowed(entity, blockPos, ((Block)ModBlocks.DARKNESS.get()).func_176223_P(), 3, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/* 164 */           absorbedBlocksAbility.addAbsorbedBlock(oldState, blockPos);
/*     */         }
/*     */         
/* 167 */         if (!entity.field_70170_p.func_180495_p(blockPos.func_177984_a()).func_185904_a().func_76218_k()) {
/* 168 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLACK_HOLE.get(), (Entity)entity, blockPos.func_177958_n(), blockPos.func_177956_o() + 0.5D, blockPos.func_177952_p());
/*     */         }
/*     */         
/* 171 */         List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, blockPos, 1.5F);
/* 172 */         for (LivingEntity target : targets) {
/* 173 */           if (!this.targets.contains(target) && target.func_110124_au() != entity.func_110124_au()) {
/* 174 */             this.targets.add(target);
/*     */             
/* 176 */             EffectInstance instance = new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1);
/*     */             
/* 178 */             target.func_195064_c(instance);
/*     */             
/* 180 */             ((ServerPlayerEntity)entity).field_71135_a.func_147359_a((IPacket)new SPlayEntityEffectPacket(target.func_145782_y(), instance));
/*     */             
/* 182 */             WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLACK_HOLE.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*     */           } 
/*     */         } 
/*     */         
/* 186 */         i++;
/* 187 */         iterator.remove();
/*     */       } 
/* 189 */     } else if (this.state == State.RELEASING) {
/* 190 */       List<AbsorbedBlocksAbility.BlockData> uncompressedBlocks = absorbedBlocksAbility.getUncompressedBlocks();
/*     */       
/* 192 */       double batchSize = Math.ceil(this.amountToFix / this.timeToFix);
/*     */       
/* 194 */       int index = uncompressedBlocks.size();
/*     */       
/* 196 */       int i = 0;
/* 197 */       while (--index >= 0 && batchSize-- > 0.0D) {
/* 198 */         AbsorbedBlocksAbility.BlockData blockData = uncompressedBlocks.get(index);
/*     */         
/* 200 */         BlockPos blockPos = blockData.getBlockPos();
/* 201 */         BlockState blockState = blockData.getBlockState();
/*     */         
/* 203 */         if (AbilityHelper.placeBlockIfAllowed(entity, blockPos, blockState, 3, DarknessBlockProtectionRule.INSTANCE)) {
/* 204 */           if (!entity.field_70170_p.func_180495_p(blockPos.func_177984_a()).func_185904_a().func_76218_k()) {
/* 205 */             WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLACK_HOLE.get(), (Entity)entity, blockPos.func_177958_n(), (blockPos.func_177956_o() + 0.5F), blockPos.func_177952_p());
/*     */           }
/*     */           
/* 208 */           absorbedBlocksAbility.removeAbsorbedBlock(blockData);
/*     */         } 
/*     */         
/* 211 */         i++;
/*     */       } 
/*     */       
/* 214 */       if (uncompressedBlocks.isEmpty()) {
/* 215 */         this.chargeComponent.stopCharging(entity);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 221 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 227 */     if (this.state == State.ABSORBING) {
/* 228 */       this.targets.clear();
/*     */       
/* 230 */       this.timeToFix = this.chargeComponent.getChargeTime();
/*     */       
/* 232 */       IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */       
/* 234 */       AbsorbedBlocksAbility absorbedBlocksAbility = (AbsorbedBlocksAbility)abilityDataProps.getPassiveAbility(AbsorbedBlocksAbility.INSTANCE);
/*     */       
/* 236 */       this.amountToFix = absorbedBlocksAbility.getUncompressedBlocks().size();
/*     */       
/* 238 */       this.state = State.RELEASING;
/*     */       
/* 240 */       this.continuousComponent.startContinuity(entity, -1.0F);
/* 241 */     } else if (this.state == State.RELEASING) {
/* 242 */       this.state = State.ABSORBING;
/*     */       
/* 244 */       this.cooldownComponent.startCooldown(entity, (float)(400.0D * Math.max(0.5D, this.chargeComponent.getChargePercentage())));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 249 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 253 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/* 255 */     AbsorbedBlocksAbility absorbedBlocksAbility = (AbsorbedBlocksAbility)abilityDataProps.getPassiveAbility(AbsorbedBlocksAbility.INSTANCE);
/*     */     
/* 257 */     if (absorbedBlocksAbility.getUncompressedBlocks().isEmpty()) {
/* 258 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 263 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 267 */     this.chargeComponent.startCharging(entity, (int)this.timeToFix);
/*     */   }
/*     */   
/*     */   private enum State {
/* 271 */     ABSORBING, RELEASING;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\BlackHoleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */