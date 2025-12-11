/*    */ package xyz.pixelatedw.mineminenomi.api.events.onefruit;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ public class LostDevilFruitEvent extends EntityEvent {
/*    */   private final String reason;
/*    */   private final Item devilFruit;
/*    */   
/*    */   public LostDevilFruitEvent(@Nullable LivingEntity entity, @Nonnull Item devilFruit, String reason) {
/* 15 */     super((Entity)entity);
/* 16 */     this.devilFruit = devilFruit;
/* 17 */     this.reason = reason;
/*    */   }
/*    */   
/*    */   public Item getItem() {
/* 21 */     return this.devilFruit;
/*    */   }
/*    */   
/*    */   public String getReason() {
/* 25 */     return this.reason;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\onefruit\LostDevilFruitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */