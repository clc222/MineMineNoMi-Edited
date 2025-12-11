/*    */ package xyz.pixelatedw.mineminenomi.api.events.onefruit;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ 
/*    */ public class EatDevilFruitEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private final ItemStack devilFruit;
/*    */   
/*    */   private EatDevilFruitEvent(PlayerEntity entity, @Nonnull ItemStack devilFruit) {
/* 16 */     super(entity);
/* 17 */     this.devilFruit = devilFruit;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 22 */     return this.devilFruit;
/*    */   }
/*    */   
/*    */   @Cancelable
/*    */   public static class Pre
/*    */     extends EatDevilFruitEvent
/*    */   {
/*    */     public Pre(PlayerEntity entity, @Nonnull ItemStack devilFruit) {
/* 30 */       super(entity, devilFruit);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Post
/*    */     extends EatDevilFruitEvent
/*    */   {
/*    */     public Post(PlayerEntity entity, @Nonnull ItemStack devilFruit) {
/* 38 */       super(entity, devilFruit);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\onefruit\EatDevilFruitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */