/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IRevengeEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhaseManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public interface IChallengeBoss
/*    */   extends IRevengeEntity, IPhasesEntity {
/* 18 */   public static final UUID GROUP_SCALE_HP_UUID = UUID.fromString("a66a0da1-286f-4772-9684-1b30810152f5");
/* 19 */   public static final UUID GROUP_SCALE_TOUGHNESS_UUID = UUID.fromString("64c28e45-7adf-41b5-8d64-933ca6b8288b");
/* 20 */   public static final UUID GROUP_SCALE_ATTACK_UUID = UUID.fromString("378cc4c7-d7a2-47f8-8b94-123ef5229196");
/* 21 */   public static final UUID GROUP_SCALE_GCD_UUID = UUID.fromString("94233c8f-9ca6-427b-a2d1-9745df50c4ba");
/*    */ 
/*    */   
/*    */   ChallengeInfo getChallengeInfo();
/*    */   
/*    */   default void applyDifficultyModifiers(LivingEntity entity) {
/* 27 */     float scale = getChallengeInfo().getScaling();
/* 28 */     if (scale > 0.0F) {
/* 29 */       ModifiableAttributeInstance hpAttr = entity.func_110148_a(Attributes.field_233818_a_);
/* 30 */       if (hpAttr != null) {
/* 31 */         hpAttr.func_233769_c_(new AttributeModifier(GROUP_SCALE_HP_UUID, "Group Scaling Health Modifier", getHealthScalingModifier(scale), AttributeModifier.Operation.MULTIPLY_BASE));
/*    */       }
/*    */       
/* 34 */       ModifiableAttributeInstance toughnessAttr = entity.func_110148_a((Attribute)ModAttributes.TOUGHNESS.get());
/* 35 */       if (toughnessAttr != null) {
/* 36 */         toughnessAttr.func_233769_c_(new AttributeModifier(GROUP_SCALE_TOUGHNESS_UUID, "Group Scaling Toughness Modifier", getToughnessScalingModifier(scale), AttributeModifier.Operation.ADDITION));
/*    */       }
/*    */       
/* 39 */       ModifiableAttributeInstance attackAttr = entity.func_110148_a(Attributes.field_233823_f_);
/* 40 */       if (attackAttr != null) {
/* 41 */         attackAttr.func_233769_c_(new AttributeModifier(GROUP_SCALE_ATTACK_UUID, "Group Scaling Health Modifier", getAttackScalingModifier(scale), AttributeModifier.Operation.ADDITION));
/*    */       }
/*    */       
/* 44 */       ModifiableAttributeInstance gcdAttr = entity.func_110148_a((Attribute)ModAttributes.GCD.get());
/* 45 */       if (gcdAttr != null) {
/* 46 */         gcdAttr.func_233769_c_(new AttributeModifier(GROUP_SCALE_GCD_UUID, "Group Scaling GCD Modifier", getGCDScalingModifier(scale), AttributeModifier.Operation.ADDITION));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default NPCPhaseManager getPhaseManager() {
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default RevengeMeter getRevengeMeter() {
/* 60 */     return null;
/*    */   }
/*    */   
/*    */   default boolean isDifficultyStandard() {
/* 64 */     return getChallengeInfo().isDifficultyStandard();
/*    */   }
/*    */   
/*    */   default boolean isDifficultyHard() {
/* 68 */     return getChallengeInfo().isDifficultyHard();
/*    */   }
/*    */   
/*    */   default boolean isDifficultyHardOrAbove() {
/* 72 */     return (getChallengeInfo().getDifficulty().ordinal() >= ChallengeDifficulty.HARD.ordinal());
/*    */   }
/*    */   
/*    */   default boolean isDifficultyUltimate() {
/* 76 */     return getChallengeInfo().isDifficultyUltimate();
/*    */   }
/*    */   
/*    */   default double getHealthScalingModifier(float scale) {
/* 80 */     return scale;
/*    */   }
/*    */   
/*    */   default double getToughnessScalingModifier(float scale) {
/* 84 */     return (4.0F * scale);
/*    */   }
/*    */   
/*    */   default double getAttackScalingModifier(float scale) {
/* 88 */     return (3.0F * scale);
/*    */   }
/*    */   
/*    */   default double getGCDScalingModifier(float scale) {
/* 92 */     return -(15.0F * scale);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\IChallengeBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */