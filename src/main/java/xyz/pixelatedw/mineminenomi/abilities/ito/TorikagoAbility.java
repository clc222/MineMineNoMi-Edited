/*     */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ 
/*     */ public class TorikagoAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "torikago", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Creates an indestructible dome made of strings, that damage anyone who touches them", null)
/*     */       });
/*     */   
/*     */   public static final int MAX_CAGE_SIZE = 60;
/*     */   private static final int MIN_CAGE_SIZE = 8;
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int MAX_COOLDOWN = 200;
/*     */   private static final float MIN_COOLDOWN = 26.666668F;
/*  41 */   public static final AbilityCore<TorikagoAbility> INSTANCE = (new AbilityCore.Builder("Torikago", AbilityCategory.DEVIL_FRUITS, TorikagoAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(26.666668F, 200.0F), ChargeComponent.getTooltip(60.0F), ContinuousComponent.getTooltip()
/*  44 */       }).build();
/*     */   
/*  46 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new RegistryObject[] { ModBlocks.STRING_WALL }).setBypassGriefRule().build();
/*     */   
/*  48 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this, true)).addStartEvent(100, this::startChargeEvent).addEndEvent(100, this::endChargeEvent);
/*  49 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(100, this::tickContinuousEvent).addEndEvent(100, this::endContinuousEvent);
/*  50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*  52 */   private List<BlockPos> blockList = new ArrayList<>();
/*  53 */   private List<BlockPos> placedBlockList = new ArrayList<>();
/*  54 */   public int cageSize = 0;
/*     */   private BlockPos centerPos;
/*  56 */   private Interval checkPositionInterval = new Interval(20);
/*     */   
/*     */   public TorikagoAbility(AbilityCore<TorikagoAbility> core) {
/*  59 */     super(core);
/*     */     
/*  61 */     this.isNew = true;
/*  62 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.continuousComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  64 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  68 */     if (isContinuous()) {
/*  69 */       this.continuousComponent.stopContinuity(entity);
/*     */       return;
/*     */     } 
/*  72 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  76 */     this.checkPositionInterval.restartIntervalToZero();
/*  77 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM, 60);
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/*  81 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     if (this.blockList.isEmpty()) {
/*  86 */       this.cageSize = Math.max(8, (int)(60.0F * this.chargeComponent.getChargeTime() / this.chargeComponent.getMaxChargeTime()));
/*     */       
/*  88 */       this.centerPos = new BlockPos(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       
/*  90 */       this.blockList.addAll(AbilityHelper.createSphere(entity.field_70170_p, entity.func_233580_cy_(), this.cageSize, true, (Block)ModBlocks.STRING_WALL.get(), 0, GRIEF_RULE));
/*     */       
/*  92 */       this.placedBlockList.addAll(this.blockList);
/*     */     } 
/*     */     
/*  95 */     this.continuousComponent.startContinuity(entity, -1.0F);
/*  96 */     this.animationComponent.stop(entity);
/*     */   }
/*     */   
/*     */   private void tickContinuousEvent(LivingEntity entity, IAbility ability) {
/* 100 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 104 */     if (this.centerPos != null && this.checkPositionInterval.canTick() && !isPositionInTorikago(entity.func_233580_cy_())) {
/* 105 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */     
/* 108 */     int placedBlocks = 0;
/*     */     
/* 110 */     Iterator<BlockPos> iter = this.placedBlockList.iterator();
/*     */     
/* 112 */     while (iter.hasNext()) {
/* 113 */       BlockPos pos = iter.next();
/* 114 */       entity.field_70170_p.func_184138_a(pos, Blocks.field_150350_a.func_176223_P(), ((Block)ModBlocks.STRING_WALL.get()).func_176223_P(), 0);
/* 115 */       iter.remove();
/* 116 */       placedBlocks++;
/* 117 */       if (placedBlocks > 512) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuousEvent(LivingEntity entity, IAbility ability) {
/* 124 */     for (BlockPos pos : this.blockList) {
/* 125 */       Block currentBlock = entity.field_70170_p.func_180495_p(pos).func_177230_c();
/*     */       
/* 127 */       if (currentBlock == ModBlocks.STRING_WALL.get()) {
/* 128 */         entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*     */       }
/*     */     } 
/*     */     
/* 132 */     this.centerPos = null;
/*     */     
/* 134 */     this.blockList.clear();
/* 135 */     this.placedBlockList.clear();
/*     */     
/* 137 */     float cooldown = 200.0F * this.cageSize / 60.0F;
/*     */     
/* 139 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPositionInTorikago(BlockPos pos) {
/* 144 */     if (this.centerPos == null) {
/* 145 */       return false;
/*     */     }
/* 147 */     return pos.func_218141_a((Vector3i)this.centerPos, this.cageSize);
/*     */   }
/*     */   
/*     */   public BlockPos getCenter() {
/* 151 */     return this.centerPos;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\TorikagoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */