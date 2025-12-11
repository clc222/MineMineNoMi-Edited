/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class GomuHelper {
/* 19 */   private static final AbilityCore<?>[] GEARS = new AbilityCore[] { GearSecondAbility.INSTANCE, GearThirdAbility.INSTANCE, GearFourthAbility.INSTANCE };
/*    */   
/* 21 */   public static final AbilityDescriptionLine.IDescriptionLine SECOND_GEAR_REQ = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { StringTextComponent.field_240750_d_, AbilityHelper.mentionAbility(GearSecondAbility.INSTANCE) }));
/* 22 */   public static final AbilityDescriptionLine.IDescriptionLine THIRD_GEAR_REQ = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { StringTextComponent.field_240750_d_, AbilityHelper.mentionAbility(GearThirdAbility.INSTANCE) }));
/* 23 */   public static final AbilityDescriptionLine.IDescriptionLine FOURTH_GEAR_REQ = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { StringTextComponent.field_240750_d_, AbilityHelper.mentionAbility(GearFourthAbility.INSTANCE) }));
/* 24 */   public static final AbilityDescriptionLine.IDescriptionLine FIFTH_GEAR_REQ = AbilityDescriptionLine.IDescriptionLine.of((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { StringTextComponent.field_240750_d_, AbilityHelper.mentionAbility(GearFifthAbility.INSTANCE) }));
/*    */   
/*    */   public static Ability.ICanUseEvent<LivingEntity, IAbility> canUseGearCheck(AbilityCore<?> gear) {
/* 27 */     return (entity, ability) -> {
/*    */         IAbilityData props = AbilityDataCapability.get(entity);
/*    */         boolean hasOtherGearActive = false;
/*    */         for (AbilityCore<?> otherGear : GEARS) {
/*    */           if (!gear.equals(otherGear)) {
/*    */             IAbility gearAbility = props.getEquippedAbility(otherGear);
/*    */             if (gearAbility != null) {
/*    */               boolean isActive = ((Boolean)gearAbility.getComponent(ModAbilityKeys.CONTINUOUS).map(()).orElse(Boolean.valueOf(false))).booleanValue();
/*    */               if (isActive) {
/*    */                 hasOtherGearActive = true;
/*    */                 break;
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */         return hasOtherGearActive ? AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_GEAR_ACTIVE) : AbilityUseResult.success();
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasGearSecondActive(IAbilityData props) {
/* 57 */     Ability ability = (Ability)props.getEquippedAbility(GearSecondAbility.INSTANCE);
/* 58 */     boolean isActive = (ability != null && ability.isContinuous());
/* 59 */     return isActive;
/*    */   }
/*    */   
/*    */   public static boolean hasGearThirdActive(IAbilityData props) {
/* 63 */     Ability ability = (Ability)props.getEquippedAbility(GearThirdAbility.INSTANCE);
/* 64 */     boolean isActive = (ability != null && ability.isContinuous());
/* 65 */     return isActive;
/*    */   }
/*    */   
/*    */   public static boolean hasGearFourthActive(IAbilityData props) {
/* 69 */     Ability ability = (Ability)props.getEquippedAbility(GearFourthAbility.INSTANCE);
/* 70 */     boolean isActive = (ability != null && ability.isContinuous());
/* 71 */     return isActive;
/*    */   }
/*    */   
/*    */   public static boolean hasGearFifthActive(IAbilityData props) {
/* 75 */     Ability ability = (Ability)props.getEquippedAbility(GearFifthAbility.INSTANCE);
/* 76 */     boolean isActive = (ability != null && ability.isContinuous());
/* 77 */     return isActive;
/*    */   }
/*    */   
/*    */   public enum Gears {
/* 81 */     NO_GEAR,
/* 82 */     GEAR_2,
/* 83 */     GEAR_3,
/* 84 */     GEAR_4,
/* 85 */     GEAR_5;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */