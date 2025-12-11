/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.ICureEffectObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class CureEffectObjective
/*    */   extends Objective
/*    */   implements ICureEffectObjective
/*    */ {
/*    */   private Effect[] effects;
/*    */   
/*    */   public CureEffectObjective(String title, int count, Effect effect) {
/* 17 */     this(title, count, new Effect[] { effect });
/*    */   }
/*    */ 
/*    */   
/*    */   public CureEffectObjective(String title, int count, Effect[] effects) {
/* 22 */     super(title);
/* 23 */     setMaxProgress(count);
/* 24 */     this.effects = effects;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkEffect(PlayerEntity player, EffectInstance effectInstance) {
/* 30 */     return Arrays.<Effect>stream(this.effects).anyMatch(effect -> (effectInstance.func_188419_a() == effect));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\CureEffectObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */