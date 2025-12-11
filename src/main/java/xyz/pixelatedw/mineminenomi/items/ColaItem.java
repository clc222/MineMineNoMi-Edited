/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ColaItem extends Item {
/*     */   public static final int COLA_REFILL = 25;
/*     */   
/*     */   public ColaItem() {
/*  26 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16).func_221540_a(ModFoods.COLA));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack pStack, World world, Entity pEntity, int pItemSlot, boolean pIsSelected) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  40 */     if (!WyDebug.isDebug() || !world.field_72995_K);
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
/* 103 */     ItemStack itemstack = player.func_184586_b(hand);
/* 104 */     player.func_184598_c(hand);
/* 105 */     return ActionResult.func_226248_a_(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 110 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/* 111 */       PlayerEntity player = (PlayerEntity)entity;
/* 112 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */       
/* 114 */       if (props.isCyborg()) {
/* 115 */         if (props.getCola() + 25 > props.getMaxCola()) {
/* 116 */           props.setCola(props.getMaxCola());
/*     */         } else {
/*     */           
/* 119 */           props.alterCola(25);
/*     */         } 
/*     */       }
/*     */       
/* 123 */       player.func_213357_a(world, itemStack);
/* 124 */       if (!player.field_71075_bZ.field_75098_d) {
/* 125 */         player.func_191521_c(new ItemStack((IItemProvider)ModItems.EMPTY_COLA.get()));
/*     */       }
/*     */       
/* 128 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*     */     } 
/*     */     
/* 131 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public UseAction func_77661_b(ItemStack stack) {
/* 136 */     return UseAction.DRINK;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\ColaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */