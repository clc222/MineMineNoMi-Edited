/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.item.BucketItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SunaHelper {
/*  30 */   public static final UUID DESERT_COOLDOWN_BONUS = UUID.fromString("6d311e6b-84b8-4c9d-b67b-f93116bbc397");
/*  31 */   public static final UUID DESERT_DAMAGE_BONUS = UUID.fromString("9f5c3cf8-d241-4895-a33d-a2f1be34e163");
/*  32 */   public static final UUID DESERT_CHARGE_BONUS = UUID.fromString("415a4bf3-1c81-4b93-a705-5e5661dd0bae");
/*  33 */   public static final UUID DESERT_RANGE_BONUS = UUID.fromString("e5e79903-2ee4-49e5-b462-dfa51a68af0a");
/*     */   
/*     */   public static AbilityUseResult requiresInactiveDesertGirasole(LivingEntity entity, IAbility ability) {
/*  36 */     DesertGirasoleAbility desertGirasole = (DesertGirasoleAbility)AbilityDataCapability.get(entity).getEquippedAbility(DesertGirasoleAbility.INSTANCE);
/*  37 */     if (desertGirasole != null && desertGirasole.isCharging()) {
/*  38 */       return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_TOGETHER, new Object[] { ability.getDisplayName().getString(), desertGirasole.getDisplayName().getString() }));
/*     */     }
/*     */     
/*  41 */     return AbilityUseResult.success();
/*     */   }
/*     */   
/*     */   public static boolean isWet(LivingEntity entity) {
/*  45 */     BlockPos blockpos = entity.func_233580_cy_();
/*     */     
/*  47 */     boolean isRaining = (entity.field_70170_p.func_175727_C(blockpos) || entity.field_70170_p.func_175727_C(new BlockPos(blockpos.func_177958_n(), (entity.func_174813_aQ()).field_72337_e, blockpos.func_177952_p())));
/*  48 */     boolean checkMainHand = (!entity.func_184614_ca().func_190926_b() && entity.func_184614_ca().func_77973_b() == ModWeapons.UMBRELLA.get());
/*  49 */     boolean checkOffHand = (!entity.func_184592_cb().func_190926_b() && entity.func_184592_cb().func_77973_b() == ModWeapons.UMBRELLA.get());
/*  50 */     boolean holdsUmbrella = (checkMainHand || checkOffHand);
/*     */     
/*  52 */     if (isRaining && holdsUmbrella) {
/*  53 */       return false;
/*     */     }
/*  55 */     if (entity.func_70026_G()) {
/*  56 */       return true;
/*     */     }
/*     */     
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static int getReducedCooldown(LivingEntity player, int defaultValue) {
/*  64 */     if (isFruitBoosted(player)) {
/*  65 */       return (int)(defaultValue - WyHelper.percentage(20.0D, defaultValue));
/*     */     }
/*  67 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public static boolean isFruitBoosted(LivingEntity player) {
/*  71 */     return (player.func_130014_f_().func_226691_t_(player.func_233580_cy_()).func_201856_r().equals(Biome.Category.DESERT) || player
/*  72 */       .func_130014_f_().func_226691_t_(player.func_233580_cy_()).func_201856_r().equals(Biome.Category.BEACH));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drainLiquids(LivingEntity entity, int effects, int potions, int buckets) {
/*  77 */     if (entity == null || !entity.func_70089_S()) {
/*     */       return;
/*     */     }
/*  80 */     int cleanBottles = 0;
/*  81 */     int cleanBuckets = 0;
/*  82 */     int cleanEffects = 0;
/*     */     
/*  84 */     for (EffectInstance eff : entity.func_70651_bq()) {
/*     */       
/*  86 */       if (eff == null) {
/*     */         continue;
/*     */       }
/*  89 */       if (effects > cleanEffects && eff.func_188419_a().func_188408_i() && 1 >= eff.func_76458_c() && !(eff.func_188419_a() instanceof xyz.pixelatedw.mineminenomi.api.effects.IColorOverlayEffect)) {
/*     */         
/*  91 */         cleanEffects++;
/*  92 */         Effect potion = eff.func_188419_a();
/*  93 */         entity.func_195063_d(potion);
/*  94 */         if (entity instanceof ServerPlayerEntity) {
/*  95 */           ((ServerPlayerEntity)entity).field_71135_a.func_147359_a((IPacket)new SRemoveEntityEffectPacket(entity.func_145782_y(), potion));
/*     */         }
/*     */       } 
/*     */     } 
/*  99 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 101 */       PlayerEntity player = (PlayerEntity)entity;
/* 102 */       ArrayList<Item> items = new ArrayList<>(Arrays.asList(new Item[] { Items.field_151068_bn, Items.field_185156_bI, Items.field_185155_bH }));
/*     */       
/* 104 */       for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*     */         
/* 106 */         if (items.contains(player.field_71071_by.func_70301_a(i).func_77973_b()) && potions > cleanBottles) {
/*     */           
/* 108 */           player.field_71071_by.func_70299_a(i, new ItemStack((IItemProvider)Items.field_190931_a));
/* 109 */           cleanBottles++;
/* 110 */         } else if (player.field_71071_by.func_70301_a(i).func_77973_b() instanceof BucketItem && buckets > cleanBuckets) {
/*     */           
/* 112 */           BucketItem item = (BucketItem)player.field_71071_by.func_70301_a(i).func_77973_b();
/* 113 */           if (item.getFluid() != Fluids.field_204541_a && item.getFluid() != Fluids.field_204547_b) {
/*     */             
/* 115 */             player.field_71071_by.func_70299_a(i, new ItemStack((IItemProvider)Items.field_190931_a));
/* 116 */             cleanBuckets++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 121 */       if (cleanBottles > 0) {
/* 122 */         player.func_191521_c(new ItemStack((IItemProvider)Items.field_151069_bo, cleanBottles));
/*     */       }
/* 124 */       if (cleanBuckets > 0)
/* 125 */         player.func_191521_c(new ItemStack((IItemProvider)Items.field_151133_ar, cleanBuckets)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SunaHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */