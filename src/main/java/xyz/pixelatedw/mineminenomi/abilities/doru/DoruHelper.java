/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr3Entity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class DoruHelper {
/*    */   public static boolean hasColorPalette(LivingEntity entity) {
/* 11 */     if (ItemsHelper.countItemInInventory(entity, (Item)ModItems.COLOR_PALETTE.get()) > 0) {
/* 12 */       return true;
/*    */     }
/*    */     
/* 15 */     if (entity instanceof Mr3Entity && ((Mr3Entity)entity).hasColorPaletteBonus()) {
/* 16 */       return true;
/*    */     }
/*    */     
/* 19 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */