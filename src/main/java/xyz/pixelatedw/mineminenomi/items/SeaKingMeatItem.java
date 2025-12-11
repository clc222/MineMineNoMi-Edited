/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*    */ 
/*    */ public class SeaKingMeatItem extends Item {
/*    */   public SeaKingMeatItem() {
/* 15 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_221540_a(ModFoods.SEA_KING_MEAT));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 20 */     ItemStack itemstack = player.func_184586_b(hand);
/* 21 */     player.func_184598_c(hand);
/* 22 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 27 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/* 28 */       PlayerEntity player = (PlayerEntity)entity;
/* 29 */       player.func_213357_a(world, itemStack);
/*    */     } 
/*    */     
/* 32 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\SeaKingMeatItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */