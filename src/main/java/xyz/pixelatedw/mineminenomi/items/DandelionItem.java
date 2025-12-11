/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Foods;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class DandelionItem extends Item {
/*    */   public DandelionItem() {
/* 18 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1).func_221540_a(Foods.field_221443_s));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 23 */     ItemStack itemstack = player.func_184586_b(hand);
/*    */     
/* 25 */     player.func_184598_c(hand);
/*    */     
/* 27 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 33 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/* 34 */       ((PlayerEntity)entity).func_184811_cZ().func_185145_a(getItem(), 200);
/*    */       
/* 36 */       entity.func_184602_cy();
/*    */       
/* 38 */       entity.field_70170_p.func_72960_a((Entity)entity, (byte)30);
/*    */       
/* 40 */       entity.func_70691_i(getHealAmount(itemStack));
/*    */       
/* 42 */       itemStack.func_190918_g(1);
/*    */     } 
/*    */     
/* 45 */     return itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAction func_77661_b(ItemStack stack) {
/* 50 */     return UseAction.EAT;
/*    */   }
/*    */   
/*    */   public static float getHealAmount(ItemStack itemStack) {
/* 54 */     return itemStack.func_196082_o().func_74760_g("amount");
/*    */   }
/*    */   
/*    */   public static void setHealAmount(ItemStack itemStack, float amount) {
/* 58 */     float maxHealth = (CommonConfig.INSTANCE.getDorikiLimit() / CommonConfig.INSTANCE.getHealthGainFrequency());
/*    */     
/* 60 */     itemStack.func_196082_o().func_74776_a("amount", MathHelper.func_76131_a(amount, 0.0F, maxHealth));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\DandelionItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */