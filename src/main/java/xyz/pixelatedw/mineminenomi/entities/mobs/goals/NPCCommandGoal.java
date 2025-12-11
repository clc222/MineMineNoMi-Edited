/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*    */ 
/*    */ public class NPCCommandGoal<E extends MobEntity & ICommandReceiver>
/*    */   extends TickedGoal<E> {
/* 10 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*    */   
/*    */   public NPCCommandGoal(E entity) {
/* 13 */     super((MobEntity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\NPCCommandGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */