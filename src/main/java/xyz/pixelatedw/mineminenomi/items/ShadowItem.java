/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Foods;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class ShadowItem
/*    */   extends Item
/*    */ {
/*    */   public ShadowItem() {
/* 25 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_221540_a(Foods.field_221443_s));
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
/*    */   
/*    */   public ItemStack func_77654_b(ItemStack itemStack, World world, LivingEntity entity) {
/* 39 */     if (!world.field_72995_K && entity instanceof PlayerEntity) {
/*    */       
/* 41 */       IEntityStats props = EntityStatsCapability.get(entity);
/* 42 */       if (!props.hasShadow()) {
/*    */         
/* 44 */         props.setShadow(true);
/* 45 */         WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(entity.func_145782_y(), props), (Entity)entity);
/*    */ 
/*    */       
/*    */       }
/* 49 */       else if (entity.func_70660_b(Effects.field_76420_g) == null || entity.func_70660_b(Effects.field_76429_m) == null) {
/*    */         
/* 51 */         entity.func_195064_c(new EffectInstance(Effects.field_76420_g, 100, 0, false, false));
/* 52 */         entity.func_195064_c(new EffectInstance(Effects.field_76429_m, 100, 0, false, false));
/*    */       }
/*    */       else {
/*    */         
/* 56 */         int duration = entity.func_70660_b(Effects.field_76420_g).func_76459_b();
/* 57 */         int amplifier = 0;
/*    */         
/* 59 */         duration += 100;
/*    */         
/* 61 */         if (duration > 500) {
/* 62 */           amplifier = 1;
/*    */         }
/* 64 */         if (duration > 1200) {
/* 65 */           duration = 1200;
/*    */         }
/* 67 */         entity.func_195063_d(Effects.field_76420_g);
/* 68 */         entity.func_195064_c(new EffectInstance(Effects.field_76420_g, duration, amplifier));
/*    */         
/* 70 */         duration = entity.func_70660_b(Effects.field_76429_m).func_76459_b();
/* 71 */         amplifier = 0;
/*    */         
/* 73 */         duration += 100;
/*    */         
/* 75 */         if (duration > 500) {
/* 76 */           amplifier = 1;
/*    */         }
/* 78 */         if (duration > 1200) {
/* 79 */           duration = 1200;
/*    */         }
/* 81 */         entity.func_195063_d(Effects.field_76429_m);
/* 82 */         entity.func_195064_c(new EffectInstance(Effects.field_76429_m, duration, amplifier));
/*    */       } 
/*    */ 
/*    */       
/* 86 */       itemStack.func_190918_g(1);
/*    */     } 
/*    */     
/* 89 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction func_77661_b(ItemStack stack) {
/* 96 */     return UseAction.EAT;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\ShadowItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */