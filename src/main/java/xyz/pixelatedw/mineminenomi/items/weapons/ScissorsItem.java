/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IForgeShearable;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.KageGiriAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ScissorsItem extends ModSwordItem {
/*     */   public ScissorsItem() {
/*  36 */     super(5, -2.8F, 500);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77644_a(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/*  42 */     if (!(attacker instanceof PlayerEntity)) {
/*  43 */       return super.func_77644_a(itemStack, target, attacker);
/*     */     }
/*     */     
/*  46 */     KageGiriAbility passive = (KageGiriAbility)AbilityDataCapability.get(attacker).getPassiveAbility(KageGiriAbility.INSTANCE);
/*  47 */     boolean hasPassive = (passive != null && passive.canUse(attacker).isSuccess());
/*     */     
/*  49 */     IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */     
/*  51 */     if (!hasPassive || itemStack == null || itemStack.func_77973_b() != ModWeapons.SCISSORS.get() || !targetProps.hasShadow()) {
/*  52 */       return super.func_77644_a(itemStack, target, attacker);
/*     */     }
/*     */     
/*  55 */     if (passive.addIfValid(target.func_110124_au())) {
/*  56 */       targetProps.setShadow(false);
/*  57 */       ((PlayerEntity)attacker).field_71071_by.func_70441_a(new ItemStack((IItemProvider)ModItems.SHADOW.get()));
/*  58 */       WyNetwork.sendToAllAround(new SSyncEntityStatsPacket(target.func_145782_y(), targetProps), (Entity)target);
/*     */     } 
/*     */     
/*  61 */     return super.func_77644_a(itemStack, target, attacker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_179218_a(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entityLiving) {
/*  69 */     if (!world.field_72995_K)
/*     */     {
/*  71 */       stack.func_222118_a(1, entityLiving, entity -> entity.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     Block block = state.func_177230_c();
/*  78 */     return (!state.func_235714_a_((ITag)BlockTags.field_206952_E) && block != Blocks.field_196553_aF && block != Blocks.field_150349_c && block != Blocks.field_196554_aH && block != Blocks.field_196555_aI && block != Blocks.field_150395_bd && block != Blocks.field_150473_bD && !block.func_203417_a((ITag)BlockTags.field_199897_a)) ? super.func_179218_a(stack, world, state, pos, entityLiving) : true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_150897_b(BlockState blockIn) {
/*  84 */     Block block = blockIn.func_177230_c();
/*  85 */     return (block == Blocks.field_196553_aF || block == Blocks.field_150488_af || block == Blocks.field_150473_bD);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_150893_a(ItemStack stack, BlockState state) {
/*  91 */     Block block = state.func_177230_c();
/*  92 */     if (block != Blocks.field_196553_aF && !state.func_235714_a_((ITag)BlockTags.field_206952_E)) {
/*  93 */       return block.func_203417_a((ITag)BlockTags.field_199897_a) ? 5.0F : super.func_150893_a(stack, state);
/*     */     }
/*  95 */     return 15.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType func_111207_a(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
/* 101 */     if (entity.field_70170_p.field_72995_K)
/* 102 */       return ActionResultType.PASS; 
/* 103 */     if (entity instanceof IForgeShearable) {
/*     */       
/* 105 */       IForgeShearable target = (IForgeShearable)entity;
/* 106 */       BlockPos pos = new BlockPos(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 107 */       if (target.isShearable(stack, entity.field_70170_p, pos)) {
/*     */         
/* 109 */         List<ItemStack> drops = target.onSheared(playerIn, stack, entity.field_70170_p, pos, EnchantmentHelper.func_77506_a(Enchantments.field_185308_t, stack));
/* 110 */         Random rand = new Random();
/* 111 */         drops.forEach(d -> {
/*     */               ItemEntity ent = entity.func_70099_a(d, 1.0F);
/*     */               
/*     */               AbilityHelper.setDeltaMovement((Entity)ent, ent.func_213322_ci().func_72441_c(((rand.nextFloat() - rand.nextFloat()) * 0.1F), (rand.nextFloat() * 0.05F), ((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
/*     */             });
/* 116 */         stack.func_222118_a(1, entity, e -> e.func_213334_d(hand));
/*     */       } 
/* 118 */       return ActionResultType.SUCCESS;
/*     */     } 
/* 120 */     return ActionResultType.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ScissorsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */