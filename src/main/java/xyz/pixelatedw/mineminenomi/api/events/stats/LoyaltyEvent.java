/*    */ package xyz.pixelatedw.mineminenomi.api.events.stats;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ 
/*    */ public class LoyaltyEvent
/*    */   extends StatEvent
/*    */ {
/*    */   private double loyalty;
/*    */   
/*    */   public LoyaltyEvent(PlayerEntity player, double loyalty, StatChangeSource source) {
/* 13 */     super(player, source);
/* 14 */     this.loyalty = loyalty;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getLoyalty() {
/* 19 */     return this.loyalty;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLoyalty(double amount) {
/* 24 */     this.loyalty = amount;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends LoyaltyEvent
/*    */   {
/*    */     public Pre(PlayerEntity player, double loyalty, StatChangeSource source) {
/* 32 */       super(player, loyalty, source);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends LoyaltyEvent
/*    */   {
/*    */     public Post(PlayerEntity player, double loyalty, StatChangeSource source) {
/* 40 */       super(player, loyalty, source);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\stats\LoyaltyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */