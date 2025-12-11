/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.passive.TameableEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*    */ 
/*    */ public class FollowCommandGoal<E extends MobEntity & ICommandReceiver>
/*    */   extends TickedGoal<E> {
/*    */   public FollowCommandGoal(E entity) {
/* 15 */     super((MobEntity)entity);
/* 16 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.LOOK, Goal.Flag.MOVE, Goal.Flag.TARGET));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 21 */     if (((ICommandReceiver)this.entity).getLastCommandSender() == null) {
/* 22 */       return false;
/*    */     }
/*    */     
/* 25 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.FOLLOW)) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 34 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.FOLLOW)) {
/* 35 */       return false;
/*    */     }
/* 37 */     if (((ICommandReceiver)this.entity).getLastCommandSender() == null || !((ICommandReceiver)this.entity).getLastCommandSender().func_70089_S()) {
/* 38 */       return false;
/*    */     }
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 45 */     super.func_75249_e();
/* 46 */     if (this.entity instanceof TameableEntity) {
/* 47 */       ((TameableEntity)this.entity).func_233687_w_(false);
/*    */     }
/* 49 */     this.entity.func_70624_b(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 54 */     super.func_75246_d();
/* 55 */     this.entity.func_70624_b(null);
/* 56 */     this.entity.func_70661_as().func_75497_a((Entity)((ICommandReceiver)this.entity).getLastCommandSender(), 1.5D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 61 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\commands\FollowCommandGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */