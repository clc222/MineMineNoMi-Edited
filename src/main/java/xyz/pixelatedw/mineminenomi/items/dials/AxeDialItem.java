/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.AxeDialProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class AxeDialItem extends BlockItem {
/*    */   public AxeDialItem(Block block) {
/* 17 */     super(block, (new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 23 */     ItemStack itemstack = player.func_184586_b(hand);
/*    */     
/* 25 */     if (!world.field_72995_K) {
/*    */       
/* 27 */       AxeDialProjectile proj = new AxeDialProjectile(player.field_70170_p, (LivingEntity)player);
/*    */       
/* 29 */       world.func_217376_c((Entity)proj);
/* 30 */       proj.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 2.0F, 1.0F);
/* 31 */       player.func_184811_cZ().func_185145_a(getItem(), 10);
/* 32 */       itemstack.func_190918_g(1);
/*    */     } 
/*    */     
/* 35 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\dials\AxeDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */