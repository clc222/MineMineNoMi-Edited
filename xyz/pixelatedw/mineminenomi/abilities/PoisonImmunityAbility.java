/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class PoisonImmunityAbility
/*    */   extends PotionPassiveAbility {
/* 18 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "poison_immunity", new Pair[] {
/* 19 */         (Pair)ImmutablePair.of("Provides poison immunity.", null)
/*    */       });
/* 21 */   public static final AbilityCore<PoisonImmunityAbility> INSTANCE = (new AbilityCore.Builder("Poison Immunity", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, PoisonImmunityAbility::new))
/* 22 */     .addDescriptionLine(DESCRIPTION)
/* 23 */     .build();
/*    */   
/*    */   public PoisonImmunityAbility(AbilityCore<PoisonImmunityAbility> ability) {
/* 26 */     super(ability);
/*    */     
/* 28 */     this.checkPotionEvent = this::checkPotionEvent;
/*    */   }
/*    */   
/*    */   private boolean checkPotionEvent(PlayerEntity player, EffectInstance effect) {
/* 32 */     if (effect.func_188419_a().equals(Effects.field_76436_u) || effect.func_188419_a().equals(ModEffects.DOKU_POISON.get())) {
/* 33 */       return false;
/*    */     }
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\PoisonImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */