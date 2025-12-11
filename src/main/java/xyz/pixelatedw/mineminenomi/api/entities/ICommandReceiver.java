/*    */ package xyz.pixelatedw.mineminenomi.api.entities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*    */ 
/*    */ public interface ICommandReceiver
/*    */ {
/*    */   void setCurrentCommand(@Nullable LivingEntity paramLivingEntity, NPCCommand paramNPCCommand);
/*    */   
/*    */   NPCCommand getCurrentCommand();
/*    */   
/*    */   boolean canReceiveCommandFrom(LivingEntity paramLivingEntity);
/*    */   
/*    */   default boolean canMaintainCommand() {
/* 16 */     return true;
/*    */   }
/*    */   
/*    */   long getLastCommandTime();
/*    */   
/*    */   @Nullable
/*    */   LivingEntity getLastCommandSender();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ICommandReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */