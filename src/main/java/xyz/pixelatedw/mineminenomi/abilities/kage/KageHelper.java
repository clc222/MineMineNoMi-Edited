/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class KageHelper {
/* 16 */   public static final ITextComponent NOT_ENOUGH_SHADOWS_WARN = (ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_SHADOWS);
/*    */   
/*    */   public static AbilityUseResult hasEnoughShadows(LivingEntity entity, IAbility ability, int amount) {
/* 19 */     if (ItemsHelper.countItemInInventory(entity, (Item)ModItems.SHADOW.get()) < amount) {
/* 20 */       return AbilityUseResult.fail(NOT_ENOUGH_SHADOWS_WARN);
/*    */     }
/* 22 */     return AbilityUseResult.success();
/*    */   }
/*    */   
/*    */   public static void removeShadows(LivingEntity entity, int amount) {
/* 26 */     List<ItemStack> slots = ItemsHelper.getAllInventoryItems(entity);
/* 27 */     for (ItemStack stack : slots) {
/* 28 */       if (amount <= 0) {
/*    */         break;
/*    */       }
/* 31 */       if (stack.func_77973_b().equals(ModItems.SHADOW.get())) {
/* 32 */         if (stack.func_190916_E() >= amount) {
/* 33 */           stack.func_190918_g(amount);
/*    */           
/*    */           break;
/*    */         } 
/* 37 */         amount -= stack.func_190916_E();
/* 38 */         stack.func_190918_g(amount);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public static int getAvailableShadows(LivingEntity entity) {
/* 49 */     return ItemsHelper.countItemInInventory(entity, (Item)ModItems.SHADOW.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KageHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */