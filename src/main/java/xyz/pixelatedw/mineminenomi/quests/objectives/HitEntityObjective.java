/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class HitEntityObjective
/*    */   extends Objective
/*    */   implements IHitEntityObjective {
/*    */   protected ICheckHit hitEvent = (player, target, source, amount) -> true;
/*    */   
/*    */   public HitEntityObjective(String title, int count) {
/* 17 */     this(title, count, (ICheckHit)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public HitEntityObjective(String title, int count, EntityType entityType) {
/* 22 */     this(title, count, (player, target, source, amount) -> (target.func_200600_R() == entityType));
/*    */   }
/*    */ 
/*    */   
/*    */   public HitEntityObjective(String title, int count, @Nullable ICheckHit check) {
/* 27 */     super(title);
/* 28 */     setMaxProgress(count);
/* 29 */     if (check != null) {
/* 30 */       this.hitEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount) {
/* 36 */     return this.hitEvent.test(player, target, source, amount);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckHit
/*    */   {
/*    */     default ICheckHit and(ICheckHit check) {
/* 46 */       return (player, target, source, amount) -> 
/* 47 */         (test(player, target, source, amount) && check.test(player, target, source, amount));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckHit or(ICheckHit check) {
/* 53 */       return (player, target, source, amount) -> 
/* 54 */         (test(player, target, source, amount) || check.test(player, target, source, amount));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, DamageSource param1DamageSource, float param1Float);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\HitEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */