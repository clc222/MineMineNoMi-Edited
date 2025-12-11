/*    */ package xyz.pixelatedw.mineminenomi.api.events.stats;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ 
/*    */ public class BountyEvent
/*    */   extends StatEvent
/*    */ {
/*    */   private long bounty;
/*    */   
/*    */   public BountyEvent(PlayerEntity player, long bounty, StatChangeSource source) {
/* 13 */     super(player, source);
/* 14 */     this.bounty = bounty;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getBounty() {
/* 19 */     return this.bounty;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBounty(long amount) {
/* 24 */     this.bounty = amount;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends BountyEvent
/*    */   {
/*    */     public Pre(PlayerEntity player, long bounty, StatChangeSource source) {
/* 32 */       super(player, bounty, source);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends BountyEvent
/*    */   {
/*    */     public Post(PlayerEntity player, long bounty, StatChangeSource source) {
/* 40 */       super(player, bounty, source);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\stats\BountyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */