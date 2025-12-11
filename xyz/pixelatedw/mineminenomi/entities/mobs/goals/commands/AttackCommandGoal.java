/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*    */ 
/*    */ public class AttackCommandGoal<E extends MobEntity & ICommandReceiver>
/*    */   extends TickedGoal<E>
/*    */ {
/*    */   public AttackCommandGoal(E entity) {
/* 14 */     super((MobEntity)entity);
/* 15 */     func_220684_a(EnumSet.of(Goal.Flag.TARGET));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 20 */     if (((ICommandReceiver)this.entity).getLastCommandSender() == null) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.ATTACK)) {
/* 25 */       return false;
/*    */     }
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 33 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.ATTACK)) {
/* 34 */       return false;
/*    */     }
/* 36 */     if (this.entity.func_70638_az() == null || !this.entity.func_70638_az().func_70089_S()) {
/* 37 */       return false;
/*    */     }
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 44 */     super.func_75249_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 49 */     super.func_75246_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 54 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\commands\AttackCommandGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */