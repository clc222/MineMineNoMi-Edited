/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.enchantment.EnchantmentType;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.projectile.AbstractArrowEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.ChakramEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class ChakramItem extends ModSwordItem {
/*    */   public ChakramItem() {
/* 25 */     super(8, -3.2F, 600);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 30 */     ItemStack itemstack = player.func_184586_b(hand);
/* 31 */     if (itemstack.func_77952_i() >= itemstack.func_77958_k() - 1) {
/* 32 */       return ActionResult.func_226251_d_(itemstack);
/*    */     }
/* 34 */     if (!world.field_72995_K) {
/* 35 */       WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation((LivingEntity)player, ModAnimations.THROW_CHAKRAM, func_77626_a(itemstack), true), (Entity)player);
/*    */     }
/* 37 */     player.func_184598_c(hand);
/* 38 */     return ActionResult.func_226249_b_(itemstack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77615_a(ItemStack itemStack, World world, LivingEntity entity, int timeLeft) {
/* 44 */     if (entity instanceof PlayerEntity) {
/* 45 */       PlayerEntity player = (PlayerEntity)entity;
/* 46 */       if (!world.field_72995_K) {
/* 47 */         WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation((LivingEntity)player, ModAnimations.THROW_CHAKRAM), (Entity)player);
/*    */       }
/* 49 */       int i = func_77626_a(itemStack) - timeLeft;
/* 50 */       if (i >= 4) {
/* 51 */         float extraPower = MathHelper.func_76125_a(i, 4, 20) / 10.0F;
/*    */         
/* 53 */         itemStack.func_222118_a(1, (LivingEntity)player, p -> p.func_213334_d(entity.func_184600_cs()));
/*    */ 
/*    */         
/* 56 */         player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/*    */         
/* 58 */         ChakramEntity chakram = new ChakramEntity((LivingEntity)player, itemStack);
/* 59 */         chakram.setAttackDamage(1.0F + extraPower + func_200894_d());
/* 60 */         chakram.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 1.0F + extraPower, 1.0F);
/* 61 */         if (player.field_71075_bZ.field_75098_d) {
/* 62 */           chakram.field_70251_a = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
/*    */         }
/*    */         
/* 65 */         world.func_217376_c((Entity)chakram);
/* 66 */         world.func_217384_a(player, (Entity)chakram, SoundEvents.field_187612_G, SoundCategory.PLAYERS, 1.0F, 1.5F);
/* 67 */         if (!player.field_71075_bZ.field_75098_d) {
/* 68 */           player.field_71071_by.func_184437_d(itemStack);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAction func_77661_b(ItemStack pStack) {
/* 76 */     return UseAction.BOW;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_77626_a(ItemStack pStack) {
/* 81 */     return 72000;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_77619_b() {
/* 86 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 91 */     return (enchantment.field_77351_y == EnchantmentType.BREAKABLE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ChakramItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */