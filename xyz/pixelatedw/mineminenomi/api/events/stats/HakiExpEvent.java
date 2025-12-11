/*    */ package xyz.pixelatedw.mineminenomi.api.events.stats;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ 
/*    */ public class HakiExpEvent
/*    */   extends StatEvent
/*    */ {
/*    */   private float hakiExp;
/*    */   private HakiType hakiType;
/*    */   
/*    */   public HakiExpEvent(PlayerEntity player, float hakiExp, HakiType hakiType, StatChangeSource source) {
/* 15 */     super(player, source);
/* 16 */     this.hakiExp = hakiExp;
/* 17 */     this.hakiType = hakiType;
/*    */   }
/*    */ 
/*    */   
/*    */   public HakiType getHakiType() {
/* 22 */     return this.hakiType;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHakiExp() {
/* 27 */     return this.hakiExp;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHakiExp(float amount) {
/* 32 */     this.hakiExp = amount;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends HakiExpEvent
/*    */   {
/*    */     public Pre(PlayerEntity player, float hakiExp, HakiType hakiType, StatChangeSource source) {
/* 40 */       super(player, hakiExp, hakiType, source);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends HakiExpEvent
/*    */   {
/*    */     public Post(PlayerEntity player, float hakiExp, HakiType hakiType, StatChangeSource source) {
/* 48 */       super(player, hakiExp, hakiType, source);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\stats\HakiExpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */