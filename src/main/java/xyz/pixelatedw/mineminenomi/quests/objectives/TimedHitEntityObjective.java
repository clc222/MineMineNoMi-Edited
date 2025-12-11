/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ 
/*    */ public class TimedHitEntityObjective
/*    */   extends HitEntityObjective
/*    */ {
/* 11 */   private long lastHit = 0L;
/* 12 */   private int seconds = 0;
/*    */ 
/*    */   
/*    */   public TimedHitEntityObjective(String title, int count, int seconds) {
/* 16 */     super(title, count, (HitEntityObjective.ICheckHit)null);
/* 17 */     this.seconds = seconds * 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public TimedHitEntityObjective(String title, int count, int seconds, HitEntityObjective.ICheckHit check) {
/* 22 */     super(title, count, check);
/* 23 */     this.seconds = seconds * 20;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount) {
/* 29 */     if (this.lastHit == 0L) {
/* 30 */       this.lastHit = player.field_70170_p.func_82737_E();
/*    */     }
/* 32 */     long hitTime = player.field_70170_p.func_82737_E();
/*    */     
/* 34 */     if (hitTime - this.seconds <= this.lastHit) {
/*    */       
/* 36 */       this.lastHit = hitTime;
/* 37 */       return super.checkHit(player, target, source, amount);
/*    */     } 
/*    */ 
/*    */     
/* 41 */     setProgress(player, 0.0D, false);
/* 42 */     this.lastHit = 0L;
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 50 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective.mineminenomi.%s", new Object[] { getId() }))).func_150268_i();
/* 51 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()), Integer.valueOf(this.seconds / 20) })).getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\TimedHitEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */