/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class CandleWallAbility
/*    */   extends WallAbility {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candle_wall", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Creates a wax wall in front of the user", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 120.0F;
/* 30 */   public static final AbilityCore<CandleWallAbility> INSTANCE = (new AbilityCore.Builder("Candle Wall", AbilityCategory.DEVIL_FRUITS, CandleWallAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F)
/* 33 */       }).build();
/*    */   
/* 35 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/*    */   
/* 37 */   private int thiccness = 1;
/*    */   
/*    */   public CandleWallAbility(AbilityCore<CandleWallAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/*    */     
/* 44 */     addUseEvent(100, this::onUseEvent);
/* 45 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 49 */     CandleChampionAbility candleChampionAbility = (CandleChampionAbility)AbilityDataCapability.get(entity).getEquippedAbility(CandleChampionAbility.INSTANCE);
/* 50 */     if (candleChampionAbility != null) {
/* 51 */       boolean isAcitve = ((Boolean)candleChampionAbility.getComponent(ModAbilityKeys.CONTINUOUS).map(ContinuousComponent::isContinuous).orElse(Boolean.valueOf(false))).booleanValue();
/* 52 */       if (isAcitve) {
/* 53 */         this.thiccness = 2;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getThickness() {
/* 64 */     return this.thiccness;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 69 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 74 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getWallBlock() {
/* 79 */     return (Block)ModBlocks.WAX.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockProtectionRule getGriefingRule() {
/* 84 */     return GRIEF_RULE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean stopAfterUse() {
/* 89 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleWallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */