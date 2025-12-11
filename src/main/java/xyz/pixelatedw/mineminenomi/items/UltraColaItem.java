/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class UltraColaItem
/*    */   extends Item {
/*    */   public static final int ULTRA_COLA_BONUS = 20;
/*    */   public static final int COLA_REFILL = 100;
/*    */   
/*    */   public UltraColaItem() {
/* 28 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16).func_221540_a(ModFoods.COLA));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 33 */     ItemStack itemstack = player.func_184586_b(hand);
/* 34 */     player.func_184598_c(hand);
/* 35 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 40 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/* 41 */       PlayerEntity player = (PlayerEntity)entity;
/* 42 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 44 */       if (props.isCyborg()) {
/* 45 */         if (props.getUltraCola() <= 19) {
/* 46 */           props.addUltraCola(1);
/* 47 */           props.alterCola(50);
/*    */         } else {
/*    */           
/* 50 */           player.func_195064_c(new EffectInstance(Effects.field_76428_l, 100, 0, false, false));
/* 51 */           player.func_195064_c(new EffectInstance(Effects.field_76420_g, 200, 0, false, false));
/* 52 */           player.func_195064_c(new EffectInstance(Effects.field_76424_c, 200, 0, false, false));
/* 53 */           props.alterCola(100);
/*    */         } 
/*    */         
/* 56 */         WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/*    */       } else {
/*    */         
/* 59 */         player.func_195064_c(new EffectInstance(Effects.field_76424_c, 250, 0));
/*    */       } 
/*    */       
/* 62 */       player.func_213357_a(world, itemStack);
/* 63 */       if (!player.field_71075_bZ.field_75098_d) {
/* 64 */         player.func_191521_c(new ItemStack((IItemProvider)ModItems.EMPTY_ULTRA_COLA.get()));
/*    */       }
/*    */     } 
/*    */     
/* 68 */     return itemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public UseAction func_77661_b(ItemStack stack) {
/* 73 */     return UseAction.DRINK;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\UltraColaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */