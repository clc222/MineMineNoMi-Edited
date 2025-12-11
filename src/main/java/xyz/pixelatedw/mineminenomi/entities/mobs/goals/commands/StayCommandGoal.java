/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*    */ 
/*    */ public class StayCommandGoal<E extends MobEntity & ICommandReceiver>
/*    */   extends TickedGoal<E>
/*    */ {
/*    */   public StayCommandGoal(E entity) {
/* 14 */     super((MobEntity)entity);
/* 15 */     func_220684_a(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.TARGET));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 20 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.STAY)) {
/* 21 */       return false;
/*    */     }
/*    */     
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75253_b() {
/* 29 */     if (!((ICommandReceiver)this.entity).getCurrentCommand().equals(NPCCommand.STAY)) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (this.entity.func_70643_av() != null) {
/* 34 */       this.entity.func_70624_b(this.entity.func_70643_av());
/* 35 */       return false;
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 43 */     super.func_75249_e();
/* 44 */     this.entity.func_70624_b(null);
/* 45 */     this.entity.func_70604_c(null);
/* 46 */     this.entity.func_70661_as().func_75499_g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 51 */     super.func_75246_d();
/* 52 */     this.entity.func_70624_b(null);
/* 53 */     this.entity.func_70661_as().func_75499_g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 58 */     super.func_75251_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\commands\StayCommandGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */