/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*    */ 
/*    */ public class CookedSeaKingMeatItem extends Item {
/*    */   public CookedSeaKingMeatItem() {
/* 17 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_221540_a(ModFoods.COOKED_SEA_KING_MEAT));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 22 */     ItemStack itemstack = player.func_184586_b(hand);
/* 23 */     player.func_184598_c(hand);
/* 24 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 29 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/* 30 */       PlayerEntity player = (PlayerEntity)entity;
/* 31 */       player.func_195064_c(new EffectInstance(Effects.field_76428_l, 100, 0));
/* 32 */       player.func_70691_i(10.0F + player.func_110138_aP() / 10.0F);
/* 33 */       player.func_213357_a(world, itemStack);
/*    */     } 
/*    */     
/* 36 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\CookedSeaKingMeatItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */