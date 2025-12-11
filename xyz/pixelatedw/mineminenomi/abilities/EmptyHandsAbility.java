/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class EmptyHandsAbility
/*    */   extends PassiveAbility2 {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "empty_hands", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("When active items picked from ground will never be put in the user's main hand.", null), 
/* 22 */         (Pair)ImmutablePair.of("If there's space in the inventory it will be put there, othewise it won't get picked up at all.", null)
/*    */       });
/* 24 */   public static final AbilityCore<EmptyHandsAbility> INSTANCE = (new AbilityCore.Builder("Empty Hands", AbilityCategory.STYLE, AbilityType.PASSIVE, EmptyHandsAbility::new))
/* 25 */     .addDescriptionLine(DESCRIPTION)
/* 26 */     .setUnlockCheck(EmptyHandsAbility::canUnlock)
/* 27 */     .build();
/*    */   
/*    */   public EmptyHandsAbility(AbilityCore<EmptyHandsAbility> core) {
/* 30 */     super(core);
/*    */     
/* 32 */     addEquipEvent(this::onEquipEvent);
/*    */   }
/*    */   
/*    */   private void onEquipEvent(LivingEntity entity, IAbility ability) {
/* 36 */     if (entity != null && !entity.field_70170_p.field_72995_K) {
/* 37 */       this.pauseTickComponent.setPause(entity, true);
/*    */     }
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 42 */     if (!(entity instanceof PlayerEntity)) {
/* 43 */       return false;
/*    */     }
/*    */     
/* 46 */     PlayerEntity player = (PlayerEntity)entity;
/* 47 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 49 */     return questProps.hasFinishedQuest(ModQuests.EMPTY_HANDS_TRIAL);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\EmptyHandsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */