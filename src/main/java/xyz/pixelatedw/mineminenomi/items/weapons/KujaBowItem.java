/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.enchantment.EnchantmentType;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BowItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.ForgeEventFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class KujaBowItem extends BowItem {
/*    */   public KujaBowItem(int maxDamage) {
/* 25 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.WEAPONS).func_200917_a(1).func_200918_c(maxDamage));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77615_a(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
/* 31 */     if (entityLiving instanceof PlayerEntity) {
/*    */       
/* 33 */       PlayerEntity player = (PlayerEntity)entityLiving;
/* 34 */       boolean hasInfinite = (player.func_184812_l_() || EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, itemStack) > 0);
/* 35 */       ItemStack arrowStack = player.func_213356_f(itemStack);
/*    */       
/* 37 */       int i = func_77626_a(itemStack) - timeLeft;
/* 38 */       i = ForgeEventFactory.onArrowLoose(itemStack, world, player, i, (!arrowStack.func_190926_b() || hasInfinite));
/* 39 */       if (i < 0) {
/*    */         return;
/*    */       }
/* 42 */       if (!arrowStack.func_190926_b() || hasInfinite) {
/*    */         
/* 44 */         if (arrowStack.func_190926_b()) {
/* 45 */           arrowStack = new ItemStack((IItemProvider)ModItems.KUJA_ARROW.get());
/*    */         }
/* 47 */         float f = func_185059_b(i);
/* 48 */         if (f > 0.4F) {
/*    */           
/* 50 */           if (!world.field_72995_K) {
/*    */             
/* 52 */             KujaArrowProjectile proj = new KujaArrowProjectile(world, entityLiving);
/* 53 */             proj.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, f * 3.0F, 1.0F);
/*    */             
/* 55 */             if (EnchantmentHelper.func_77506_a(Enchantments.field_185311_w, itemStack) > 0) {
/* 56 */               proj.func_70015_d(3);
/*    */             }
/* 58 */             world.func_217376_c((Entity)proj);
/* 59 */             if (!hasInfinite) {
/* 60 */               arrowStack.func_190918_g(1);
/*    */             }
/* 62 */             player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/* 63 */             itemStack.func_222118_a(1, (LivingEntity)player, user -> user.func_213334_d(player.func_184600_cs()));
/*    */           } 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 69 */           world.func_184148_a((PlayerEntity)null, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), SoundEvents.field_187737_v, SoundCategory.PLAYERS, 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate<ItemStack> func_220004_b() {
/* 78 */     return itemStack -> (itemStack.func_77973_b() == ModItems.KUJA_ARROW.get());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 83 */     return (enchantment.field_77351_y == EnchantmentType.BOW || enchantment.field_77351_y == EnchantmentType.BREAKABLE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\KujaBowItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */