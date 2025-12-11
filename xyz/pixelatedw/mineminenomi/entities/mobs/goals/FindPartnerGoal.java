/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.IChallengeBoss;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FindPartnerGoal<P extends MobEntity, T extends MobEntity & IGoalPartner<P>>
/*    */   extends TickedGoal<T>
/*    */ {
/*    */   private static final int COOLDOWN = 100;
/*    */   private Predicate<Entity> partnerTest;
/*    */   private InProgressChallenge challenge;
/*    */   private boolean setPartnerBack;
/*    */   
/*    */   public FindPartnerGoal(T entity, EntityType<P> partnerType) {
/* 25 */     super((MobEntity)entity);
/*    */     
/* 27 */     this.partnerTest = (target -> target.func_200600_R().equals(partnerType));
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
/*    */ 
/*    */   
/*    */   public FindPartnerGoal<P, T> setPartnerBack(boolean flag) {
/* 43 */     this.setPartnerBack = flag;
/* 44 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 49 */     if (((IGoalPartner)this.entity).getPartner() != null && ((MobEntity)((IGoalPartner<MobEntity>)this.entity).getPartner()).func_70089_S()) {
/* 50 */       return false;
/*    */     }
/*    */     
/* 53 */     if (this.entity instanceof IChallengeBoss) {
/* 54 */       this.challenge = ((IChallengeBoss)this.entity).getChallengeInfo().getInProgressChallengeData();
/*    */     }
/*    */     
/* 57 */     if (!hasTimePassedSinceLastEnd(100.0F)) {
/* 58 */       return false;
/*    */     }
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 74 */     if (this.challenge != null) {
/* 75 */       Optional<LivingEntity> optTarget = this.challenge.getEnemies().stream().filter(this.partnerTest).findFirst();
/* 76 */       if (optTarget.isPresent()) {
/* 77 */         setPartner((P)optTarget.get());
/*    */       }
/*    */     } else {
/*    */       
/* 81 */       List<LivingEntity> list = WyHelper.getNearbyEntities(this.entity.func_213303_ch(), (IWorld)this.entity.func_130014_f_(), 20.0D, 20.0D, 20.0D, this.partnerTest, new Class[] { LivingEntity.class });
/* 82 */       list.remove(this.entity);
/* 83 */       setPartner((P)list.stream().findAny().orElse(null));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setPartner(P partner) {
/* 88 */     if (partner != null && partner.func_70089_S()) {
/*    */ 
/*    */       
/* 91 */       ((IGoalPartner<P>)this.entity).setPartner(partner);
/* 92 */       if (this.setPartnerBack)
/* 93 */         ((IGoalPartner<MobEntity>)partner).setPartner(this.entity); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\FindPartnerGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */