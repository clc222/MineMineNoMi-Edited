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
/*    */ public class KnockdownAbility
/*    */   extends PassiveAbility2 {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "knockdown", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("When active, enemies will be left unconscious instead of killed.", null)
/*    */       });
/* 23 */   public static final AbilityCore<KnockdownAbility> INSTANCE = (new AbilityCore.Builder("Knockdown", AbilityCategory.STYLE, AbilityType.PASSIVE, KnockdownAbility::new))
/* 24 */     .addDescriptionLine(DESCRIPTION)
/* 25 */     .setUnlockCheck(KnockdownAbility::canUnlock)
/* 26 */     .build();
/*    */   
/*    */   public KnockdownAbility(AbilityCore<KnockdownAbility> core) {
/* 29 */     super(core);
/*    */     
/* 31 */     addEquipEvent(this::onEquipEvent);
/*    */   }
/*    */   
/*    */   private void onEquipEvent(LivingEntity entity, IAbility ability) {
/* 35 */     if (entity != null && !entity.field_70170_p.field_72995_K) {
/* 36 */       this.pauseTickComponent.setPause(entity, true);
/*    */     }
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 41 */     if (!(entity instanceof PlayerEntity)) {
/* 42 */       return false;
/*    */     }
/*    */     
/* 45 */     PlayerEntity player = (PlayerEntity)entity;
/* 46 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 48 */     return questProps.hasFinishedQuest(ModQuests.KNOCKDOWN_TRIAL);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\KnockdownAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */