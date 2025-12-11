/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.EnchantmentType;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BowItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HissatsuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PopGreenBowItem extends BowItem {
/*     */   public PopGreenBowItem(int maxDamage) {
/*  34 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.WEAPONS).func_200917_a(1).func_200918_c(maxDamage));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
/*  40 */     if (entityLiving instanceof PlayerEntity) {
/*     */       
/*  42 */       PlayerEntity player = (PlayerEntity)entityLiving;
/*  43 */       boolean hasInfinite = (player.func_184812_l_() || EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, itemStack) > 0);
/*  44 */       ItemStack arrowStack = player.func_213356_f(itemStack);
/*     */       
/*  46 */       int i = func_77626_a(itemStack) - timeLeft;
/*  47 */       i = ForgeEventFactory.onArrowLoose(itemStack, world, player, i, (!arrowStack.func_190926_b() || hasInfinite));
/*  48 */       if (i < 0) {
/*     */         return;
/*     */       }
/*  51 */       if (!arrowStack.func_190926_b() || hasInfinite) {
/*     */         
/*  53 */         float f = func_185059_b(i);
/*  54 */         if (f > 0.4D) {
/*     */           
/*  56 */           if (!world.field_72995_K) {
/*     */             
/*  58 */             boolean isHitScan = HissatsuAbility.checkHitScan((LivingEntity)player);
/*  59 */             int amount = (EnchantmentHelper.func_77506_a(Enchantments.field_222192_G, itemStack) > 0) ? 3 : 1;
/*     */             
/*  61 */             for (int n = 0; n < amount; n++) {
/*  62 */               PopGreenProjectile proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.NONE);
/*     */               
/*  64 */               if (arrowStack.func_77973_b().equals(ModItems.POP_GREEN.get())) {
/*  65 */                 proj = new PopGreenProjectile(world, entityLiving, PopGreenProjectile.PopGreenType.NONE);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  83 */               int j = EnchantmentHelper.func_77506_a(Enchantments.field_185309_u, itemStack);
/*  84 */               if (j > 0) {
/*  85 */                 proj.setDamage((float)(proj.getDamage() + j * 0.5D + 0.5D));
/*     */               }
/*     */               
/*  88 */               int k = EnchantmentHelper.func_77506_a(Enchantments.field_185310_v, itemStack);
/*  89 */               if (k > 0) {
/*  90 */                 proj.setKnockbackStrength(k);
/*     */               }
/*     */               
/*  93 */               if (EnchantmentHelper.func_77506_a(Enchantments.field_185311_w, itemStack) > 0) {
/*  94 */                 proj.func_70015_d(100);
/*     */               }
/*     */               
/*  97 */               if (isHitScan) {
/*  98 */                 float distance = proj.getMaxLife();
/*  99 */                 EntityRayTraceResult result = WyHelper.rayTraceEntities((Entity)player, distance, 1.25D, ProjectileComponent.TARGET_CHECK);
/*     */                 
/* 101 */                 if (result.func_216348_a() != null && result.func_216348_a() instanceof LivingEntity) {
/* 102 */                   proj.onModHit((RayTraceResult)result);
/*     */                 }
/*     */               }
/*     */               else {
/*     */                 
/* 107 */                 world.func_217376_c((Entity)proj);
/*     */                 
/* 109 */                 if (n > 0) {
/* 110 */                   Vector3d vector3d1 = player.func_213286_i(1.0F);
/* 111 */                   Quaternion quaternion = new Quaternion(new Vector3f(vector3d1), (n == 1) ? -10.0F : 10.0F, true);
/* 112 */                   Vector3d vector3d = player.func_70676_i(1.0F);
/* 113 */                   Vector3f vector3f = new Vector3f(vector3d);
/* 114 */                   vector3f.func_214905_a(quaternion);
/* 115 */                   proj.func_70186_c(vector3f.func_195899_a(), vector3f.func_195900_b(), vector3f.func_195902_c(), f * 3.0F, 1.0F);
/*     */                 } else {
/*     */                   
/* 118 */                   proj.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, f * 3.0F, 1.0F);
/*     */                 } 
/*     */               } 
/* 121 */             }  if (!hasInfinite) {
/* 122 */               arrowStack.func_190918_g(1);
/*     */             }
/* 124 */             player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/* 125 */             itemStack.func_222118_a(1, (LivingEntity)player, user -> user.func_213334_d(player.func_184600_cs()));
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 131 */           world.func_184148_a((PlayerEntity)null, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), SoundEvents.field_187737_v, SoundCategory.PLAYERS, 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 139 */     return (enchantment.field_77351_y == EnchantmentType.BOW || enchantment.field_77351_y == EnchantmentType.BREAKABLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate<ItemStack> func_220004_b() {
/* 146 */     return itemStack -> (new ArrayList(Arrays.asList((Object[])new Item[] { (Item)ModItems.POP_GREEN.get() }))).contains(itemStack.func_77973_b());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\PopGreenBowItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */