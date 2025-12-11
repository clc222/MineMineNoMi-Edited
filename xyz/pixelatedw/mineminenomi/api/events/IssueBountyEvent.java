/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Event.HasResult;
/*    */ 
/*    */ 
/*    */ @HasResult
/*    */ public class IssueBountyEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   @Nullable
/*    */   private PlayerEntity issuer;
/*    */   private long bounty;
/*    */   
/*    */   public IssueBountyEvent(PlayerEntity target, long bounty, @Nullable PlayerEntity issuer) {
/* 18 */     super(target);
/* 19 */     this.issuer = issuer;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public PlayerEntity getIssuer() {
/* 25 */     return this.issuer;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getBounty() {
/* 30 */     return this.bounty;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\IssueBountyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */