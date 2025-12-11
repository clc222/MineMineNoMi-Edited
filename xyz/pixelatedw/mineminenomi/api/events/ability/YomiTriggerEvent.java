/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ 
/*    */ public class YomiTriggerEvent
/*    */   extends PlayerEvent {
/*    */   public IDevilFruit oldPlayerData;
/*    */   public IDevilFruit newPlayerData;
/*    */   
/*    */   public YomiTriggerEvent(PlayerEntity player, IDevilFruit oldPlayerData, IDevilFruit newPlayerData) {
/* 13 */     super(player);
/* 14 */     this.oldPlayerData = oldPlayerData;
/* 15 */     this.newPlayerData = newPlayerData;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\YomiTriggerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */