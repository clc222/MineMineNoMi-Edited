/*    */ package xyz.pixelatedw.mineminenomi.api.events.stats;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ 
/*    */ public class DorikiEvent
/*    */   extends StatEvent
/*    */ {
/*    */   private double doriki;
/*    */   
/*    */   public DorikiEvent(PlayerEntity player, double doriki, StatChangeSource source) {
/* 13 */     super(player, source);
/* 14 */     this.doriki = doriki;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getDoriki() {
/* 22 */     return this.doriki;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDoriki(double amount) {
/* 27 */     this.doriki = amount;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends DorikiEvent
/*    */   {
/*    */     public Pre(PlayerEntity player, double doriki, StatChangeSource source) {
/* 35 */       super(player, doriki, source);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends DorikiEvent
/*    */   {
/*    */     public Post(PlayerEntity player, double doriki, StatChangeSource source) {
/* 43 */       super(player, doriki, source);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\stats\DorikiEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */