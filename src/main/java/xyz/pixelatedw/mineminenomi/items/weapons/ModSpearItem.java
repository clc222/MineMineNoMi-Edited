/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ 
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.EnchantmentType;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.AbstractArrowEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.entities.SpearEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ModSpearItem extends ModSwordItem {
/*     */   public ModSpearItem(int damage, float attackSpeed, int durability) {
/*  29 */     super(damage, attackSpeed, durability);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  34 */     ItemStack itemstack = player.func_184586_b(hand);
/*  35 */     if (itemstack.func_77952_i() >= itemstack.func_77958_k() - 1) {
/*  36 */       return ActionResult.func_226251_d_(itemstack);
/*     */     }
/*  38 */     if (EnchantmentHelper.func_203190_g(itemstack) > 0 && !player.func_70026_G()) {
/*  39 */       return ActionResult.func_226251_d_(itemstack);
/*     */     }
/*     */     
/*  42 */     if (!world.field_72995_K) {
/*  43 */       WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation((LivingEntity)player, ModAnimations.THROW_SPEAR, func_77626_a(itemstack), true), (Entity)player);
/*     */     }
/*  45 */     player.func_184598_c(hand);
/*  46 */     return ActionResult.func_226249_b_(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack itemStack, World world, LivingEntity entity, int timeLeft) {
/*  52 */     if (entity instanceof PlayerEntity) {
/*  53 */       PlayerEntity player = (PlayerEntity)entity;
/*  54 */       if (!world.field_72995_K) {
/*  55 */         WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation((LivingEntity)player, ModAnimations.THROW_SPEAR), (Entity)player);
/*     */       }
/*  57 */       int i = func_77626_a(itemStack) - timeLeft;
/*  58 */       if (i >= 10) {
/*  59 */         int j = EnchantmentHelper.func_203190_g(itemStack);
/*  60 */         if (j <= 0 || player.func_70026_G()) {
/*  61 */           itemStack.func_222118_a(1, (LivingEntity)player, p -> p.func_213334_d(entity.func_184600_cs()));
/*     */ 
/*     */           
/*  64 */           player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/*     */           
/*  66 */           if (j == 0) {
/*     */ 
/*     */             
/*  69 */             SpearEntity spear = new SpearEntity(player, itemStack);
/*  70 */             spear.setAttackDamage(5.0F + func_200894_d());
/*  71 */             spear.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 2.5F + j * 0.5F, 1.0F);
/*  72 */             if (player.field_71075_bZ.field_75098_d) {
/*  73 */               spear.field_70251_a = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
/*     */             }
/*     */             
/*  76 */             world.func_217376_c((Entity)spear);
/*  77 */             world.func_217384_a(player, (Entity)spear, SoundEvents.field_203274_ip, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  78 */             if (!player.field_71075_bZ.field_75098_d) {
/*  79 */               player.field_71071_by.func_184437_d(itemStack);
/*     */             }
/*     */           }
/*  82 */           else if (j > 0) {
/*     */             SoundEvent soundevent;
/*     */             
/*  85 */             float f7 = player.field_70177_z;
/*  86 */             float f = player.field_70125_A;
/*  87 */             float f1 = -MathHelper.func_76126_a(f7 * 0.017453292F) * MathHelper.func_76134_b(f * 0.017453292F);
/*  88 */             float f2 = -MathHelper.func_76126_a(f * 0.017453292F);
/*  89 */             float f3 = MathHelper.func_76134_b(f7 * 0.017453292F) * MathHelper.func_76134_b(f * 0.017453292F);
/*  90 */             float f4 = MathHelper.func_76129_c(f1 * f1 + f2 * f2 + f3 * f3);
/*  91 */             float f5 = 3.0F * (1.0F + j) / 4.0F;
/*  92 */             f1 *= f5 / f4;
/*  93 */             f2 *= f5 / f4;
/*  94 */             f3 *= f5 / f4;
/*  95 */             player.func_70024_g(f1, f2, f3);
/*  96 */             player.func_204803_n(20);
/*  97 */             if (player.func_233570_aj_()) {
/*  98 */               player.func_213315_a(MoverType.SELF, new Vector3d(0.0D, 1.1999999284744263D, 0.0D));
/*     */             }
/*     */ 
/*     */             
/* 102 */             if (j >= 3) {
/* 103 */               soundevent = SoundEvents.field_203273_io;
/*     */             }
/* 105 */             else if (j == 2) {
/* 106 */               soundevent = SoundEvents.field_203272_in;
/*     */             } else {
/*     */               
/* 109 */               soundevent = SoundEvents.field_203271_im;
/*     */             } 
/*     */             
/* 112 */             world.func_217384_a((PlayerEntity)null, (Entity)player, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public UseAction func_77661_b(ItemStack pStack) {
/* 121 */     return UseAction.NONE;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack pStack) {
/* 126 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_77619_b() {
/* 131 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 136 */     return (enchantment.field_77351_y == EnchantmentType.TRIDENT || enchantment.field_77351_y == EnchantmentType.BREAKABLE);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ModSpearItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */