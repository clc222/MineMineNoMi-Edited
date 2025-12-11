/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GoldDenDenMushiItem extends Item {
/*     */   public GoldDenDenMushiItem() {
/*  35 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
/*  40 */     super.func_77663_a(stack, world, entity, itemSlot, isSelected);
/*     */     
/*  42 */     if (!stack.func_77942_o() || !stack.func_77978_p().func_74767_n("inUse") || world.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  46 */     CompoundNBT nbt = stack.func_77978_p();
/*  47 */     nbt.func_74768_a("countdown", nbt.func_74762_e("countdown") - 1);
/*  48 */     int t = Math.max((stack.func_196082_o().func_74762_e("countdown") - 180) / 20, 0);
/*  49 */     if (entity instanceof PlayerEntity) {
/*  50 */       ((PlayerEntity)entity).func_146105_b((ITextComponent)new StringTextComponent("§6Countdown: " + t + " seconds§r"), true);
/*     */     }
/*  52 */     int[] pos = nbt.func_74759_k("coords");
/*     */     
/*  54 */     if (nbt.func_74762_e("countdown") > 40 && nbt.func_74762_e("countdown") < 180 && nbt.func_74762_e("countdown") % 5 == 0) {
/*  55 */       for (int i = 0; i < 20; i++) {
/*  56 */         CannonBallProjectile projectile = new CannonBallProjectile(world, (LivingEntity)entity);
/*  57 */         projectile.func_70107_b(pos[0] + WyHelper.randomWithRange(-50, 50), (pos[1] + 100), pos[2] + WyHelper.randomWithRange(-50, 50));
/*  58 */         projectile.setDamage(50.0F);
/*  59 */         projectile.setMaxLife(60);
/*  60 */         world.func_217376_c((Entity)projectile);
/*  61 */         projectile.func_234612_a_(entity, 90.0F, 0.0F, 0.0F, 3.0F, 0.0F);
/*     */       } 
/*     */     }
/*     */     
/*  65 */     if (nbt.func_74762_e("countdown") == 0) {
/*  66 */       stack.func_190918_g(1);
/*     */       
/*  68 */       EntityType captainEntity = (EntityType)ModEntities.MARINE_CAPTAIN.get();
/*  69 */       EntityType gruntEntity = (EntityType)ModEntities.MARINE_GRUNT.get();
/*  70 */       EntityType pacifistaEntity = (EntityType)ModEntities.PACIFISTA.get();
/*     */       
/*  72 */       int nrPacifistas = (int)WyHelper.randomWithRange(1, 5);
/*  73 */       int nrCaptains = (int)WyHelper.randomWithRange(10, 20);
/*  74 */       int nrGrunts = (int)WyHelper.randomWithRange(100, 200);
/*     */       
/*     */       int i;
/*  77 */       for (i = 0; i < nrPacifistas; i++) {
/*  78 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, captainEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
/*  79 */         if (spawnPos != null) {
/*  80 */           pacifistaEntity.func_220342_a((ServerWorld)world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  85 */       for (i = 0; i < nrCaptains; i++) {
/*  86 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, captainEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
/*  87 */         if (spawnPos != null) {
/*  88 */           captainEntity.func_220342_a((ServerWorld)world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  93 */       for (i = 0; i < nrGrunts; i++) {
/*  94 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, gruntEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
/*  95 */         if (spawnPos != null) {
/*  96 */           gruntEntity.func_220342_a((ServerWorld)world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 104 */     if (!player.func_184586_b(hand).func_196082_o().func_74767_n("inUse") && !world.field_72995_K) {
/* 105 */       if (WyHelper.isInChallengeDimension(world)) {
/* 106 */         return ActionResult.func_226251_d_(player.func_184586_b(hand));
/*     */       }
/*     */       
/* 109 */       CompoundNBT compoundNBT = player.func_184586_b(hand).func_196082_o();
/* 110 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */       
/* 112 */       boolean hasPermission = WyPermissions.hasPermission(player, WyPermissions.BUSTER_CALL_ITEM);
/* 113 */       if (!props.hasMarineRank(FactionHelper.MarineRank.ADMIRAL) && !hasPermission) {
/* 114 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_REQUIREMENT), Util.field_240973_b_);
/* 115 */         return ActionResult.func_226251_d_(player.func_184586_b(hand));
/*     */       } 
/*     */       
/* 118 */       compoundNBT.func_74768_a("countdown", 1000);
/* 119 */       compoundNBT.func_74757_a("inUse", true);
/* 120 */       compoundNBT.func_74783_a("coords", new int[] { player.func_233580_cy_().func_177958_n(), player.func_233580_cy_().func_177956_o(), player.func_233580_cy_().func_177952_p() });
/* 121 */       world.func_217369_A().stream().filter(target -> EntityStatsCapability.get((LivingEntity)target).isMarine()).forEach(target -> target.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_LAUNCHED, new Object[] { Long.valueOf(Math.round(player.func_226277_ct_())), Long.valueOf(Math.round(player.func_226281_cx_())), player.func_145748_c_().getString() }), Util.field_240973_b_));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     return ActionResult.func_226248_a_(player.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack stack, @Nullable World world, List<ITextComponent> lore, ITooltipFlag tooltip) {
/* 133 */     super.func_77624_a(stack, world, lore, tooltip);
/* 134 */     lore.add(new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_INFO));
/* 135 */     if (stack.func_196082_o().func_74764_b("countdown")) {
/* 136 */       int t = Math.max((stack.func_196082_o().func_74762_e("countdown") - 180) / 20, 0);
/* 137 */       lore.add(new StringTextComponent("§6Countdown: " + t + " seconds§r"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\GoldDenDenMushiItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */