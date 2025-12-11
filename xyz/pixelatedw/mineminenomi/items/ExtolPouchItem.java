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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class ExtolPouchItem
/*    */   extends Item
/*    */ {
/*    */   public ExtolPouchItem() {
/* 31 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
/* 36 */     if (!world.field_72995_K && 
/* 37 */       !itemStack.func_77942_o()) {
/* 38 */       long extol = CurrencyHelper.getExtolFromBelly((int)WyHelper.randomWithRange(5, 250));
/* 39 */       itemStack.func_196082_o().func_74772_a("extol", extol);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 46 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 47 */     ItemStack itemstack = player.func_184586_b(hand);
/*    */     
/* 49 */     if (!world.field_72995_K) {
/* 50 */       long amount = itemstack.func_196082_o().func_74763_f("extol");
/* 51 */       boolean obtainedFullAmount = (props.getExtol() + amount <= 999999999L);
/*    */       
/* 53 */       if (obtainedFullAmount) {
/* 54 */         props.alterExtol(amount, StatChangeSource.NATURAL);
/* 55 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*    */         
/* 57 */         player.field_71071_by.func_184437_d(itemstack);
/* 58 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_EXTOL_GAINED, new Object[] { Long.valueOf(amount) }), Util.field_240973_b_);
/* 59 */         return ActionResult.func_226248_a_(itemstack);
/*    */       } 
/* 61 */       if (props.getExtol() < 999999999L) {
/* 62 */         long freeAmount = 999999999L - props.getExtol();
/* 63 */         long leftoverAmount = amount - freeAmount;
/* 64 */         if (leftoverAmount < 0L) {
/* 65 */           return ActionResult.func_226251_d_(itemstack);
/*    */         }
/* 67 */         props.alterExtol(freeAmount, StatChangeSource.NATURAL);
/* 68 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*    */         
/* 70 */         itemstack.func_196082_o().func_74772_a("extol", leftoverAmount);
/* 71 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_EXTOL_GAINED, new Object[] { Long.valueOf(amount) }), Util.field_240973_b_);
/* 72 */         return ActionResult.func_226248_a_(itemstack);
/*    */       } 
/*    */       
/* 75 */       player.func_145747_a((ITextComponent)ModI18n.ITEM_MESSAGE_EXTOL_FULL, Util.field_240973_b_);
/* 76 */       return ActionResult.func_226251_d_(itemstack);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 81 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
/* 86 */     if (itemStack.func_77942_o())
/* 87 */       list.add(new TranslationTextComponent(ModI18n.ITEM_MESSAGE_EXTOL_POUCH_AMOUNT, new Object[] { Long.valueOf(itemStack.func_196082_o().func_74763_f("extol")) })); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\ExtolPouchItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */