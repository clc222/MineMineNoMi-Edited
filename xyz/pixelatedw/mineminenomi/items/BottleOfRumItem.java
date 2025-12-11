/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.advancements.CriteriaTriggers;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*    */ 
/*    */ public class BottleOfRumItem
/*    */   extends Item {
/*    */   public BottleOfRumItem() {
/* 25 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200918_c(5).func_221540_a(ModFoods.ALCOHOL));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 31 */     ItemStack itemstack = player.func_184586_b(hand);
/* 32 */     player.func_184598_c(hand);
/* 33 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 44 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/*    */       
/* 46 */       PlayerEntity player = (PlayerEntity)entity;
/*    */       
/* 48 */       player.func_71024_bL().func_221410_a(itemStack.func_77973_b(), itemStack);
/* 49 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(itemStack.func_77973_b()));
/* 50 */       world.func_184148_a((PlayerEntity)null, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), SoundEvents.field_187739_dZ, SoundCategory.PLAYERS, 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/* 51 */       if (player instanceof ServerPlayerEntity)
/* 52 */         CriteriaTriggers.field_193138_y.func_193148_a((ServerPlayerEntity)player, itemStack); 
/* 53 */       if (entity.func_70644_a((Effect)ModEffects.DRUNK.get())) {
/*    */         
/* 55 */         EffectInstance effect = entity.func_70660_b((Effect)ModEffects.DRUNK.get());
/*    */         
/* 57 */         int amp = effect.func_76458_c();
/* 58 */         int duration = effect.func_76459_b();
/*    */         
/* 60 */         if (amp < 4) {
/* 61 */           amp++;
/*    */         }
/* 63 */         entity.func_195063_d((Effect)ModEffects.DRUNK.get());
/* 64 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.DRUNK.get(), duration + 200, amp));
/*    */       }
/*    */       else {
/*    */         
/* 68 */         entity.func_195064_c(new EffectInstance((Effect)ModEffects.DRUNK.get(), 400, 0));
/*    */       } 
/*    */       
/* 71 */       itemStack.func_222118_a(1, entity, user -> user.func_213334_d(player.func_184600_cs()));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 76 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction func_77661_b(ItemStack stack) {
/* 82 */     return UseAction.DRINK;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\BottleOfRumItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */