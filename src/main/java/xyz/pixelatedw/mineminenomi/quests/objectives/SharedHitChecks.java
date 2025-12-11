/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ 
/*    */ public class SharedHitChecks {
/*    */   public static final HitEntityObjective.ICheckHit HAS_SWORD;
/*    */   
/*    */   static {
/* 13 */     HAS_SWORD = ((player, target, source, amount) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/*    */         return ItemsHelper.isSword(heldItem);
/*    */       });
/*    */     
/* 19 */     HAS_BOW = ((player, target, source, amount) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/*    */         return ItemsHelper.isBow(heldItem);
/*    */       });
/*    */     
/* 25 */     IS_KICKING = ((player, target, source, amount) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/* 28 */         return (heldItem.func_190926_b() && EntityStatsCapability.get((LivingEntity)player).isBlackLeg());
/*    */       });
/*    */     
/* 31 */     SWEEP_ATTACK_CHECK = ((player, target, source, amount) -> ((ISweep)source).isSweeping());
/*    */   }
/*    */   public static final HitEntityObjective.ICheckHit HAS_BOW;
/*    */   public static final HitEntityObjective.ICheckHit IS_KICKING;
/*    */   public static final HitEntityObjective.ICheckHit SWEEP_ATTACK_CHECK;
/*    */   
/*    */   public static final HitEntityObjective.ICheckHit checkAbilitySource(AbilityCore ability) {
/* 38 */     return (player, target, source, amount) -> 
/*    */       
/* 40 */       (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\SharedHitChecks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */