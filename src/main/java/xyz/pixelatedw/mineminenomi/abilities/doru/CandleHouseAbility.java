/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
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
/*    */ public class CandleHouseAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candle_house", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Creates a big house-like structure made of wax, to protect the user", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 1000.0F;
/* 30 */   public static final AbilityCore<CandleHouseAbility> INSTANCE = (new AbilityCore.Builder("Candle House", AbilityCategory.DEVIL_FRUITS, CandleHouseAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1000.0F)
/* 33 */       }).build();
/*    */   
/* 35 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/*    */   
/*    */   public CandleHouseAbility(AbilityCore<CandleHouseAbility> core) {
/* 38 */     super(core);
/*    */     
/* 40 */     this.isNew = true;
/*    */     
/* 42 */     addUseEvent(100, this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility abiltiy) {
/* 46 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 50 */     int thiccness = 1;
/* 51 */     CandleChampionAbility candleChampionAbility = (CandleChampionAbility)AbilityDataCapability.get(entity).getEquippedAbility(CandleChampionAbility.INSTANCE);
/* 52 */     if (candleChampionAbility != null) {
/* 53 */       boolean isAcitve = ((Boolean)candleChampionAbility.getComponent(ModAbilityKeys.CONTINUOUS).map(ContinuousComponent::isContinuous).orElse(Boolean.valueOf(false))).booleanValue();
/* 54 */       if (isAcitve) {
/* 55 */         thiccness = 3;
/*    */       }
/*    */     } 
/*    */     
/* 59 */     BlockState defaultWaxState = ((Block)ModBlocks.WAX.get()).func_176223_P();
/*    */     
/* 61 */     for (int y = -3; y <= 3; y++) {
/* 62 */       int i; for (i = 0; i < thiccness; i++) {
/* 63 */         for (int z = -5; z < 5; z++) {
/* 64 */           AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177982_a(6 - i, y, -z), defaultWaxState, GRIEF_RULE);
/*    */         }
/*    */       } 
/* 67 */       for (i = 0; i < thiccness; i++) {
/* 68 */         for (int z = -5; z < 5; z++) {
/* 69 */           AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177982_a(-5 - i, y, -z), defaultWaxState, GRIEF_RULE);
/*    */         }
/*    */       } 
/* 72 */       for (i = -5; i < 5; i++) {
/* 73 */         for (int z = 0; z < thiccness; z++) {
/* 74 */           AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177982_a(-i, y, 6 - z), defaultWaxState, GRIEF_RULE);
/*    */         }
/*    */       } 
/* 77 */       for (i = -5; i < 5; i++) {
/* 78 */         for (int z = 0; z < thiccness; z++) {
/* 79 */           AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177982_a(-i, y, -5 - z), defaultWaxState, GRIEF_RULE);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 84 */     for (int x = -5; x < 5; x++) {
/* 85 */       for (int z = -5; z < 5; z++) {
/* 86 */         AbilityHelper.placeBlockIfAllowed(entity, entity.func_233580_cy_().func_177982_a(-x, 4, -z), defaultWaxState, GRIEF_RULE);
/*    */       }
/*    */     } 
/*    */     
/* 90 */     this.cooldownComponent.startCooldown(entity, 1000.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleHouseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */