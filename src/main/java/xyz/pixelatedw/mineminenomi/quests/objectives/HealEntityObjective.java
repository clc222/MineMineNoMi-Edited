/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHealEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class HealEntityObjective
/*    */   extends Objective
/*    */   implements IHealEntityObjective
/*    */ {
/*    */   protected ICheckHeal healEvent = (player, target, amount) -> true;
/*    */   
/*    */   public HealEntityObjective(String title, int count) {
/* 17 */     this(title, count, (ICheckHeal)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public HealEntityObjective(String title, int count, EntityType entityType) {
/* 22 */     this(title, count, (player, target, amount) -> (target.func_200600_R() == entityType));
/*    */   }
/*    */ 
/*    */   
/*    */   public HealEntityObjective(String title, int count, @Nullable ICheckHeal check) {
/* 27 */     super(title);
/* 28 */     setMaxProgress(count);
/* 29 */     if (check != null) {
/* 30 */       this.healEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkHeal(PlayerEntity player, LivingEntity target, float amount) {
/* 36 */     return this.healEvent.test(player, target, amount);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckHeal
/*    */   {
/*    */     default ICheckHeal and(ICheckHeal check) {
/* 46 */       return (player, target, amount) -> 
/* 47 */         (test(player, target, amount) && check.test(player, target, amount));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckHeal or(ICheckHeal check) {
/* 53 */       return (player, target, amount) -> 
/* 54 */         (test(player, target, amount) || check.test(player, target, amount));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, float param1Float);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\HealEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */