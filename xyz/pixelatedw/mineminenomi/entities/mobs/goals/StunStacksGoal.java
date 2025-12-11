/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class StunStacksGoal
/*    */   extends TickedGoal<MobEntity> {
/*    */   private static final int COOLDOWN = 20;
/*    */   private int stacks;
/*    */   private final int stacksNeeded;
/*    */   private final int stunPower;
/*    */   
/*    */   public StunStacksGoal(MobEntity entity, int stacksNeeded, int stunPower) {
/* 17 */     super(entity);
/* 18 */     this.stacksNeeded = stacksNeeded;
/* 19 */     this.stunPower = Math.max(1, stunPower);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 24 */     if (!hasTimePassedSinceLastEnd(20.0F)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     if (this.stacks <= this.stacksNeeded) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 37 */     this.entity.func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 20 * this.stunPower));
/* 38 */     this.stacks = 0;
/*    */   }
/*    */   
/*    */   public void addStack() {
/* 42 */     addStack(1);
/*    */   }
/*    */   
/*    */   public void addStack(int i) {
/* 46 */     this.stacks += i;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\StunStacksGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */