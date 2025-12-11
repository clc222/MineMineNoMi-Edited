/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class BellyPouchItem
/*    */   extends Item
/*    */ {
/*    */   public BellyPouchItem() {
/* 30 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
/* 35 */     if (!world.field_72995_K && 
/* 36 */       !itemStack.func_77942_o()) {
/* 37 */       itemStack.func_196082_o().func_74772_a("belly", (int)WyHelper.randomWithRange(5, 250));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 44 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 45 */     ItemStack itemstack = player.func_184586_b(hand);
/*    */     
/* 47 */     if (!world.field_72995_K) {
/* 48 */       long amount = itemstack.func_196082_o().func_74763_f("belly");
/* 49 */       boolean obtainedFullAmount = (props.getBelly() + amount <= 999999999L);
/*    */       
/* 51 */       if (obtainedFullAmount) {
/* 52 */         props.alterBelly(amount, StatChangeSource.NATURAL);
/* 53 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*    */         
/* 55 */         player.field_71071_by.func_184437_d(itemstack);
/* 56 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_BELLY_GAINED, new Object[] { Long.valueOf(amount) }), Util.field_240973_b_);
/* 57 */         return ActionResult.func_226248_a_(itemstack);
/*    */       } 
/* 59 */       if (props.getBelly() < 999999999L) {
/* 60 */         long freeAmount = 999999999L - props.getBelly();
/* 61 */         long leftoverAmount = amount - freeAmount;
/* 62 */         if (leftoverAmount < 0L) {
/* 63 */           return ActionResult.func_226251_d_(itemstack);
/*    */         }
/* 65 */         props.alterBelly(freeAmount, StatChangeSource.NATURAL);
/* 66 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*    */         
/* 68 */         itemstack.func_196082_o().func_74772_a("belly", leftoverAmount);
/* 69 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_BELLY_GAINED, new Object[] { Long.valueOf(amount) }), Util.field_240973_b_);
/* 70 */         return ActionResult.func_226248_a_(itemstack);
/*    */       } 
/*    */       
/* 73 */       player.func_145747_a((ITextComponent)ModI18n.ITEM_MESSAGE_BELLY_FULL, Util.field_240973_b_);
/* 74 */       return ActionResult.func_226251_d_(itemstack);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 79 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
/* 84 */     if (itemStack.func_77942_o())
/* 85 */       list.add(new TranslationTextComponent(ModI18n.ITEM_MESSAGE_BELLY_POUCH_AMOUNT, new Object[] { Long.valueOf(itemStack.func_196082_o().func_74763_f("belly")) })); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\BellyPouchItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */