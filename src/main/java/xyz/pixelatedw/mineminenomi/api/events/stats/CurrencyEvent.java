/*    */ package xyz.pixelatedw.mineminenomi.api.events.stats;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ 
/*    */ public class CurrencyEvent
/*    */   extends StatEvent
/*    */ {
/*    */   private long amount;
/*    */   private Currency currency;
/*    */   
/*    */   public CurrencyEvent(PlayerEntity player, long amount, Currency currency, StatChangeSource source) {
/* 15 */     super(player, source);
/* 16 */     this.amount = amount;
/* 17 */     this.currency = currency;
/*    */   }
/*    */ 
/*    */   
/*    */   public Currency getCurrency() {
/* 22 */     return this.currency;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getAmount() {
/* 27 */     return this.amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAmount(long amount) {
/* 32 */     this.amount = amount;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends CurrencyEvent
/*    */   {
/*    */     public Pre(PlayerEntity player, long amount, Currency currency, StatChangeSource source) {
/* 40 */       super(player, amount, currency, source);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends CurrencyEvent
/*    */   {
/*    */     public Post(PlayerEntity player, long amount, Currency currency, StatChangeSource source) {
/* 48 */       super(player, amount, currency, source);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\stats\CurrencyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */