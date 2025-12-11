/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CharacterCreatorItem
/*    */   extends Item
/*    */ {
/*    */   public CharacterCreatorItem() {
/* 20 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 26 */     if (!world.field_72995_K) {
/*    */       
/* 28 */       boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
/* 29 */       boolean allowMinkRaceSelect = CommonConfig.INSTANCE.getAllowMinkRaceSelect();
/* 30 */       WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace, allowMinkRaceSelect), player);
/*    */     } 
/* 32 */     return new ActionResult(ActionResultType.SUCCESS, player.func_184586_b(hand));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\CharacterCreatorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */