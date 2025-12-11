/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.container.SmithingTableContainer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ 
/*    */ public class SmithingTableEvent
/*    */   extends Event
/*    */ {
/*    */   private PlayerEntity player;
/*    */   private SmithingTableContainer container;
/* 14 */   private ItemStack leftSlot = ItemStack.field_190927_a;
/* 15 */   private ItemStack rightSlot = ItemStack.field_190927_a;
/* 16 */   private ItemStack resultSlot = ItemStack.field_190927_a;
/*    */   private int baseQuantity;
/*    */   private int additionQuantity;
/*    */   
/*    */   public SmithingTableEvent(SmithingTableContainer container, PlayerEntity player, @Nullable ItemStack leftSlot, @Nullable ItemStack rightSlot) {
/* 21 */     this.container = container;
/* 22 */     this.player = player;
/* 23 */     this.leftSlot = (leftSlot != null) ? leftSlot : ItemStack.field_190927_a;
/* 24 */     this.rightSlot = (rightSlot != null) ? rightSlot : ItemStack.field_190927_a;
/*    */   }
/*    */   
/*    */   public PlayerEntity getPlayer() {
/* 28 */     return this.player;
/*    */   }
/*    */   
/*    */   public ItemStack getBaseSlot() {
/* 32 */     return this.leftSlot;
/*    */   }
/*    */   
/*    */   public ItemStack getAdditionSlot() {
/* 36 */     return this.rightSlot;
/*    */   }
/*    */   
/*    */   public ItemStack getResultSlot() {
/* 40 */     return this.resultSlot;
/*    */   }
/*    */   
/*    */   public int getBaseQuantity() {
/* 44 */     return this.baseQuantity;
/*    */   }
/*    */   
/*    */   public int getAdditionQuantity() {
/* 48 */     return this.additionQuantity;
/*    */   }
/*    */   
/*    */   public void setBaseSlot(@Nullable ItemStack slot) {
/* 52 */     this.leftSlot = slot;
/*    */   }
/*    */   
/*    */   public void setAdditionSlot(@Nullable ItemStack slot) {
/* 56 */     this.rightSlot = slot;
/*    */   }
/*    */   
/*    */   public void setResultRecipe(@Nullable ItemStack result, int baseQuantity, int additionQuantity) {
/* 60 */     this.resultSlot = result;
/* 61 */     this.baseQuantity = baseQuantity;
/* 62 */     this.additionQuantity = additionQuantity;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\SmithingTableEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */