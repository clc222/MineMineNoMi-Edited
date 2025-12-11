/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class BubblyCoralItem extends Item {
/*    */   public BubblyCoralItem() {
/* 19 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200918_c(3));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 25 */     ItemStack itemStack = player.func_184586_b(hand);
/*    */     
/* 27 */     if (player.func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/* 28 */       return new ActionResult(ActionResultType.PASS, player.func_184586_b(hand));
/*    */     }
/* 30 */     player.func_195064_c(new EffectInstance((Effect)ModEffects.BUBBLY_CORAL.get(), 3600, 0));
/* 31 */     itemStack.func_222118_a(1, (LivingEntity)player, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*    */     
/* 33 */     return ActionResult.func_226248_a_(itemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\BubblyCoralItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */