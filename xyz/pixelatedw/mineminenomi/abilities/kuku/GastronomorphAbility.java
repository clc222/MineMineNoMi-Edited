/*     */ package xyz.pixelatedw.mineminenomi.abilities.kuku;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GastronomorphAbility extends Ability {
/*  32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gastronomorph", new Pair[] {
/*  33 */         (Pair)ImmutablePair.of("Turns the surroundings into cake sponge blocks.", null)
/*     */       });
/*     */   
/*     */   private static final int CHARGE_TIME = 60;
/*     */   private static final int COOLDOWN = 300;
/*     */   private static final int RANGE = 32;
/*  39 */   public static final AbilityCore<GastronomorphAbility> INSTANCE = (new AbilityCore.Builder("Gastronomorph", AbilityCategory.DEVIL_FRUITS, GastronomorphAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ChargeComponent.getTooltip(60.0F), RangeComponent.getTooltip(32.0F, RangeComponent.RangeType.LINE)
/*  42 */       }).build();
/*     */   
/*  44 */   public static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/*     */   
/*  46 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */   
/*  48 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*     */   
/*     */   public GastronomorphAbility(AbilityCore<GastronomorphAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent });
/*     */     
/*  56 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  60 */     this.chargeComponent.startCharging(entity, 60.0F);
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/*  64 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  68 */     this.blockPlacingHelper.clearList();
/*     */     
/*  70 */     double radiusXZ = 32.0D;
/*  71 */     double radiusY = 9.0D;
/*     */     
/*  73 */     BlockPos.Mutable mutpos = new BlockPos.Mutable(); double y;
/*  74 */     for (y = -radiusY; y < radiusY; y++) {
/*  75 */       double x; for (x = -radiusXZ; x < radiusXZ; x++) {
/*  76 */         double z; for (z = -radiusXZ; z < radiusXZ; z++) {
/*     */           
/*  78 */           double posX = entity.func_226277_ct_() + x + ((x < -WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D)) || x > WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*  79 */           double posY = entity.func_226278_cu_() + y;
/*     */           
/*  81 */           double posZ = entity.func_226281_cx_() + z + ((z < -WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D)) || z > WyHelper.randomWithRange((int)(radiusXZ * 0.5D), (int)(radiusXZ * 0.75D))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/*  83 */           if (AbilityHelper.canPlaceBlock(entity.field_70170_p, posX, posY, posZ, Blocks.field_205164_gk.func_176223_P(), 3, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID)) {
/*     */ 
/*     */ 
/*     */             
/*  87 */             mutpos.func_189532_c(posX, posY, posZ);
/*  88 */             this.blockPlacingHelper.addBlockPos((BlockPos)mutpos, (int)(x * x + y * y + z * z));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/*  95 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  99 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 1, false, false));
/*     */     
/* 101 */     Set<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/* 103 */     int finished = blockList.size() / 100;
/* 104 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/* 105 */       BlockPos blockPos = iterator.next();
/* 106 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/*     */       
/* 110 */       AbilityHelper.placeBlockIfAllowed(entity, blockPos, ((Block)ModBlocks.SPONGE_CAKE.get()).func_176223_P(), 3, DefaultProtectionRules.CORE_FOLIAGE_ORE);
/*     */       
/* 112 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 117 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kuku\GastronomorphAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */