/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class WateringCanItem
/*    */   extends Item
/*    */ {
/*    */   private static final int MAX_TEARS = 250;
/*    */   
/*    */   public WateringCanItem() {
/* 25 */     super((new Item.Properties()).func_200917_a(1).func_200916_a(ModCreativeTabs.MISC));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 30 */     ItemStack itemStack = player.func_184586_b(hand);
/*    */     
/* 32 */     float currentHealth = player.func_110143_aJ();
/* 33 */     float maxHealth = player.func_110138_aP();
/*    */     
/* 35 */     if (currentHealth >= maxHealth) {
/* 36 */       return ActionResult.func_226251_d_(itemStack);
/*    */     }
/*    */     
/* 39 */     int requiredTears = 0;
/*    */     
/* 41 */     if (currentHealth > maxHealth - maxHealth * 0.05F) {
/* 42 */       requiredTears = (int)(maxHealth - currentHealth);
/*    */     } else {
/* 44 */       requiredTears = (int)(maxHealth * 0.05F);
/*    */     } 
/*    */     
/* 47 */     int currentTears = getTearAmount(itemStack);
/*    */     
/* 49 */     if (currentTears > 0 && currentTears >= requiredTears) {
/* 50 */       player.func_184598_c(hand);
/*    */     }
/*    */     
/* 53 */     return ActionResult.func_226248_a_(itemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 58 */     float currentHealth = entity.func_110143_aJ();
/* 59 */     float maxHealth = entity.func_110138_aP();
/* 60 */     float healAmount = 0.0F;
/*    */     
/* 62 */     if (currentHealth > maxHealth - maxHealth * 0.05F) {
/* 63 */       healAmount = maxHealth - currentHealth;
/*    */     } else {
/* 65 */       healAmount = maxHealth * 0.05F;
/*    */     } 
/*    */     
/* 68 */     entity.func_70691_i(healAmount);
/*    */     
/* 70 */     alterTearAmount(itemStack, (int)-healAmount);
/*    */     
/* 72 */     return itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAction func_77661_b(ItemStack stack) {
/* 77 */     return UseAction.DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_77626_a(ItemStack stack) {
/* 82 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 87 */     int currentTears = getTearAmount(itemStack);
/*    */     
/* 89 */     list.add(new StringTextComponent("Tears: " + currentTears));
/*    */   }
/*    */   
/*    */   public static int getTearAmount(ItemStack itemStack) {
/* 93 */     return itemStack.func_196082_o().func_74762_e("tears");
/*    */   }
/*    */   
/*    */   public static void alterTearAmount(ItemStack itemStack, int amount) {
/* 97 */     int currentTears = getTearAmount(itemStack);
/*    */     
/* 99 */     itemStack.func_77978_p().func_74768_a("tears", MathHelper.func_76125_a(currentTears + amount, 0, 250));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\WateringCanItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */