/*    */ package xyz.pixelatedw.mineminenomi.api.challenges;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChallengeInfo
/*    */ {
/* 13 */   private static final ImmutableList<LivingEntity> EMPTY_GROUP = ImmutableList.of();
/*    */   
/*    */   @Nullable
/*    */   private InProgressChallenge challengeData;
/*    */   
/*    */   public ChallengeDifficulty getDifficulty() {
/* 19 */     if (this.challengeData == null) {
/* 20 */       return ChallengeDifficulty.STANDARD;
/*    */     }
/* 22 */     return this.challengeData.getCore().getDifficulty();
/*    */   }
/*    */   
/*    */   public List<LivingEntity> getChallengerGroup() {
/* 26 */     if (this.challengeData == null) {
/* 27 */       return (List<LivingEntity>)EMPTY_GROUP;
/*    */     }
/* 29 */     return this.challengeData.getGroup();
/*    */   }
/*    */   
/*    */   public boolean isDifficultyStandard() {
/* 33 */     return (getDifficulty() == ChallengeDifficulty.STANDARD);
/*    */   }
/*    */   
/*    */   public boolean isDifficultyHard() {
/* 37 */     return (getDifficulty() == ChallengeDifficulty.HARD);
/*    */   }
/*    */   
/*    */   public boolean isDifficultyUltimate() {
/* 41 */     return (getDifficulty() == ChallengeDifficulty.ULTIMATE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getScaling() {
/* 49 */     float scale = ((getChallengerGroup().size() - 1) * 50 + getDifficulty().ordinal() * 75);
/* 50 */     scale = Math.min(scale, 300.0F);
/* 51 */     return scale / 300.0F;
/*    */   }
/*    */   
/*    */   public void setInProgressChallenge(@Nullable InProgressChallenge data) {
/* 55 */     this.challengeData = data;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public InProgressChallenge getInProgressChallengeData() {
/* 60 */     return this.challengeData;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\challenges\ChallengeInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */