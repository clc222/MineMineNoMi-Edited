/*    */ package xyz.pixelatedw.mineminenomi.abilities.buki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class LegmorphoseAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 11 */   public static final AbilityCore<LegmorphoseAbility> INSTANCE = (new AbilityCore.Builder("Legmorphose", AbilityCategory.DEVIL_FRUITS, LegmorphoseAbility::new))
/* 12 */     .addDescriptionLine("Allows the user to skate around using bladed feet", new Object[0])
/* 13 */     .build();
/*    */   
/*    */   public LegmorphoseAbility(AbilityCore<LegmorphoseAbility> core) {
/* 16 */     super(core);
/* 17 */     setMaxCooldown(5.0D);
/* 18 */     needsClientSide();
/*    */     
/* 20 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int continueTime) {
/* 24 */     if (player.func_233570_aj_() && (
/* 25 */       Math.abs(player.func_213322_ci().func_82615_a()) < 0.2D || Math.abs(player.func_213322_ci().func_82616_c()) < 0.2D))
/* 26 */       AbilityHelper.setDeltaMovement((Entity)player, player.func_213322_ci().func_82615_a() * 1.6D, player.func_213322_ci().func_82617_b(), player.func_213322_ci().func_82616_c() * 1.6D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\buki\LegmorphoseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */