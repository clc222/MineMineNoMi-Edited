/*    */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*    */ 
/*    */ public class ElectroHelper {
/* 18 */   public static final UUID SULONG_DAMAGE_BONUS = UUID.fromString("b8ee5e5c-3dee-4ca6-93ae-8340172ae72b");
/* 19 */   public static final UUID SULONG_COOLDOWN_BONUS = UUID.fromString("7fbdd64f-bc93-4bf5-aa59-f877eb014c9e");
/* 20 */   public static final UUID SULONG_RANGE_BONUS = UUID.fromString("009c1be2-c51e-4a0d-8952-5556bbe4a75d");
/*    */   
/*    */   public static AbilityUseResult canTransformInSulong(LivingEntity entity, IAbility ability) {
/* 23 */     if (!canTransform(entity.field_70170_p)) {
/* 24 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NEED_MOON);
/*    */     }
/*    */     
/* 27 */     return AbilityUseResult.success();
/*    */   }
/*    */   
/*    */   public static Ability.ICanUseEvent<LivingEntity, IAbility> requireEleclaw(int neededStacks) {
/* 31 */     return (entity, ability) -> {
/*    */         EleclawAbility eleclaw = (EleclawAbility)AbilityDataCapability.get(entity).getEquippedAbility(EleclawAbility.INSTANCE);
/*    */         if (eleclaw == null || !eleclaw.isContinuous()) {
/*    */           return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NEED_ELECLAW);
/*    */         }
/*    */         int eleclawStacks = ((Integer)eleclaw.getComponent(ModAbilityKeys.STACK).map(()).orElse(Integer.valueOf(0))).intValue();
/*    */         if (eleclawStacks - neededStacks < 0) {
/*    */           TranslationTextComponent message = new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_ABILITY_STACKS, new Object[] { Integer.valueOf(neededStacks), EleclawAbility.INSTANCE.getLocalizedName().getString() });
/*    */           return AbilityUseResult.fail((ITextComponent)message);
/*    */         } 
/*    */         return AbilityUseResult.success();
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasSulongActive(LivingEntity entity) {
/* 48 */     SulongAbility sulong = (SulongAbility)AbilityDataCapability.get(entity).getEquippedAbility(SulongAbility.INSTANCE);
/* 49 */     if (sulong != null && sulong.isContinuous()) {
/* 50 */       return true;
/*    */     }
/*    */     
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean canTransform(World world) {
/* 57 */     if (WyDebug.isDebug() && WyPatreon.BUILD_MODE == WyPatreon.BuildMode.DEV && world.func_226690_K_()) {
/* 58 */       return true;
/*    */     }
/* 60 */     return (world.func_230315_m_().func_236035_c_(world.func_72820_D()) == 0 && !world.func_72896_J() && !world.func_230315_m_().func_236037_d_() && world.func_230315_m_().func_218272_d() && world.func_226690_K_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectroHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */