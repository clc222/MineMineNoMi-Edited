/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class JikiHelper {
/*     */   public static Ability.ICanUseEvent getMetalicItemsCheck(int amount) {
/*  29 */     return (entity, ability) -> {
/*     */         List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*     */         return !hasEnoughIron(inventory, amount) ? AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS) : AbilityUseResult.success();
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void spawnAttractEffect(Entity source) {
/*  41 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ATTRACT.get(), source, source.func_226277_ct_(), source.func_226278_cu_(), source.func_226281_cx_());
/*     */   }
/*     */   
/*     */   public static List<ItemStack> getMagneticItemsOnPlayer(PlayerEntity player) {
/*  45 */     List<ItemStack> inventory = new ArrayList<>();
/*  46 */     inventory.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_70462_a);
/*  47 */     inventory.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_184439_c);
/*  48 */     inventory.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_70460_b);
/*  49 */     return inventory;
/*     */   }
/*     */   
/*     */   public static List<ItemStack> getMagneticItemsNeeded(List<ItemStack> inventory, float threshold) {
/*  53 */     List<ItemStack> stacksUsed = new ArrayList<>();
/*  54 */     for (ItemStack stack : inventory) {
/*  55 */       if (threshold <= 0.0F) {
/*     */         break;
/*     */       }
/*     */       
/*  59 */       if (stack == null || stack.func_190926_b() || !stack.func_77973_b().func_206844_a((ITag)ModTags.Items.MAGNETIC) || EnchantmentHelper.func_82781_a(stack).containsKey(ModEnchantments.KAIROSEKI.get())) {
/*     */         continue;
/*     */       }
/*     */       
/*  63 */       float amount = ModTags.Items.IRON.getValue((IForgeRegistryEntry)stack.func_77973_b());
/*  64 */       ItemStack clone = ItemStack.func_199557_a(stack.func_77955_b(new CompoundNBT()));
/*     */       
/*  66 */       if (stack.func_77984_f()) {
/*  67 */         stacksUsed.add(clone);
/*  68 */         stack.func_190918_g(1);
/*  69 */         threshold -= amount;
/*     */         continue;
/*     */       } 
/*  72 */       int qty = stack.func_190916_E();
/*  73 */       int neededQty = (int)Math.ceil((threshold / amount));
/*  74 */       if (qty < neededQty) {
/*  75 */         stacksUsed.add(clone);
/*  76 */         stack.func_190918_g(qty);
/*  77 */         threshold -= qty * amount;
/*     */         continue;
/*     */       } 
/*  80 */       ItemStack split = stack.func_77979_a(neededQty);
/*  81 */       stacksUsed.add(split);
/*  82 */       threshold -= split.func_190916_E() * amount;
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return stacksUsed;
/*     */   }
/*     */   
/*     */   public static boolean hasEnoughIron(List<ItemStack> list, float threshold) {
/*  90 */     return (getIronAmount(list) >= threshold);
/*     */   }
/*     */   
/*     */   public static float getIronAmount(List<ItemStack> list) {
/*  94 */     float totalAmount = 0.0F;
/*  95 */     for (ItemStack stack : list) {
/*     */       
/*  97 */       if (stack == null || stack.func_190926_b() || EnchantmentHelper.func_82781_a(stack).containsKey(ModEnchantments.KAIROSEKI.get())) {
/*     */         continue;
/*     */       }
/*     */       
/* 101 */       if (stack.func_77973_b().func_206844_a((ITag)ModTags.Items.MAGNETIC)) {
/* 102 */         float value = ModTags.Items.IRON.getValue((IForgeRegistryEntry)stack.func_77973_b());
/* 103 */         totalAmount += value * stack.func_190916_E();
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return totalAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void dropComponentItems(PlayerEntity player, BlockPos pos, List<ItemStack> list) {
/* 115 */     dropComponentItems((LivingEntity)player, new Vector3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), list);
/*     */   }
/*     */   
/*     */   public static void dropComponentItems(LivingEntity entity, Vector3d pos, List<ItemStack> list) {
/* 119 */     damageMagneticItems(entity, list);
/* 120 */     for (ItemStack stack : list) {
/* 121 */       int splits = entity.func_70681_au().nextInt(4) + 2;
/* 122 */       for (int i = 0; i < splits; i++) {
/* 123 */         ItemStack split = stack.func_77979_a(stack.func_190916_E() / splits);
/* 124 */         ItemEntity itemEntity = new ItemEntity(entity.field_70170_p, pos.func_82615_a(), pos.func_82617_b() + 0.4000000059604645D, pos.func_82616_c(), split);
/* 125 */         itemEntity.func_174867_a(20);
/* 126 */         entity.field_70170_p.func_217376_c((Entity)itemEntity);
/*     */       } 
/* 128 */       ItemEntity item = new ItemEntity(entity.field_70170_p, pos.func_82615_a(), pos.func_82617_b() + 0.4000000059604645D, pos.func_82616_c(), stack);
/* 129 */       item.func_174867_a(20);
/* 130 */       entity.field_70170_p.func_217376_c((Entity)item);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void damageMagneticItems(LivingEntity entity, List<ItemStack> list) {
/* 135 */     for (ItemStack stack : list) {
/* 136 */       if (stack == null || stack.func_190926_b()) {
/*     */         continue;
/*     */       }
/*     */       
/* 140 */       float value = ModTags.Items.IRON.getValue((IForgeRegistryEntry)stack.func_77973_b());
/* 141 */       float chance = MathHelper.func_76131_a(10.0F / value, 0.0F, 50.0F);
/*     */       
/* 143 */       boolean canBreak = (entity.func_70681_au().nextInt(100) + entity.func_70681_au().nextFloat() < chance);
/* 144 */       if (!canBreak) {
/*     */         continue;
/*     */       }
/*     */       
/* 148 */       if (stack.func_77984_f()) {
/* 149 */         int maxDamage = stack.func_77958_k();
/* 150 */         int damage = (int)WyHelper.clamp(entity.func_70681_au().nextInt(maxDamage), 50L, maxDamage);
/* 151 */         stack.func_222118_a(damage, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND)); continue;
/* 152 */       }  if (value > 0.0F) {
/* 153 */         int maxCount = 1 + (int)(stack.func_190916_E() / value);
/* 154 */         int destroy = (int)WyHelper.clamp(entity.func_70681_au().nextInt(maxCount), 1L, maxCount);
/* 155 */         stack.func_190918_g(destroy);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\JikiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */