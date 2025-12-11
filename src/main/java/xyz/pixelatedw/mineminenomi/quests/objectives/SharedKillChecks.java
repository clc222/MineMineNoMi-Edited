/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ 
/*    */ public class SharedKillChecks {
/*    */   public static final KillEntityObjective.ICheckKill HAS_SWORD;
/*    */   public static final KillEntityObjective.ICheckKill HAS_BOW;
/*    */   
/*    */   static {
/* 13 */     HAS_SWORD = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/*    */         return ItemsHelper.isSword(heldItem);
/*    */       });
/*    */     
/* 19 */     HAS_BOW = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/*    */         return ItemsHelper.isBow(heldItem);
/*    */       });
/*    */     
/* 25 */     HAS_EMPTY_HAND = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/*    */         return heldItem.func_190926_b();
/*    */       });
/*    */     
/* 31 */     IS_KICKING = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/* 34 */         return (heldItem.func_190926_b() && EntityStatsCapability.get((LivingEntity)player).isBlackLeg());
/*    */       });
/*    */     
/* 37 */     HAS_CANNON_BALL = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         return (heldItem.func_77973_b() == ModItems.CANNON_BALL.get());
/*    */       });
/*    */   }
/*    */   public static final KillEntityObjective.ICheckKill HAS_EMPTY_HAND; public static final KillEntityObjective.ICheckKill IS_KICKING; public static final KillEntityObjective.ICheckKill HAS_CANNON_BALL;
/* 43 */   public static final KillEntityObjective.ICheckKill HAS_BRALWER_HAND_CHECK = HAS_EMPTY_HAND.or(HAS_CANNON_BALL); public static final KillEntityObjective.ICheckKill AIRBORNE_ENEMY_CHECK; @Deprecated
/*    */   public static final KillEntityObjective.ICheckKill PLAYER_RUNNING_CHECK; public static final KillEntityObjective.ICheckKill CRITICAL_KILL_CHECK; public static final KillEntityObjective.ICheckKill ON_FIRE_ENEMY_CHECK; public static final KillEntityObjective.ICheckKill ON_FIRE_PLAYER_CHECK; static {
/* 45 */     AIRBORNE_ENEMY_CHECK = ((player, target, source) -> 
/*    */       
/* 47 */       (!target.func_233570_aj_() && !target.func_70090_H()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     PLAYER_RUNNING_CHECK = ((player, target, source) -> player.func_70051_ag());
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     CRITICAL_KILL_CHECK = ((player, target, source) -> 
/*    */       
/* 61 */       (player.field_70143_R > 0.0F && !player.func_233570_aj_() && !player.func_70617_f_() && !player.func_70090_H() && !player.func_184218_aH()));
/*    */ 
/*    */ 
/*    */     
/* 65 */     ON_FIRE_ENEMY_CHECK = ((player, target, source) -> (target.func_223314_ad() > 0));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     ON_FIRE_PLAYER_CHECK = ((player, target, source) -> (player.func_223314_ad() > 0));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final KillEntityObjective.ICheckKill checkAbilitySource(AbilityCore ability) {
/* 77 */     return (player, target, source) -> 
/*    */       
/* 79 */       (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\SharedKillChecks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */