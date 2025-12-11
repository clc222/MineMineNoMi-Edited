/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.projectile.SmallFireballEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class FlameDialItem extends BlockItem {
/*    */   public FlameDialItem(Block block) {
/* 18 */     super(block, (new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 24 */     ItemStack itemstack = player.func_184586_b(hand);
/*    */     
/* 26 */     if (!world.field_72995_K)
/*    */     {
/* 28 */       if (!player.func_213453_ef()) {
/*    */         
/* 30 */         Vector3d look = player.func_70040_Z();
/* 31 */         SmallFireballEntity fireball = new SmallFireballEntity(world, (LivingEntity)player, 1.0D, 1.0D, 1.0D);
/* 32 */         fireball.func_70107_b(player
/* 33 */             .func_226277_ct_(), player
/* 34 */             .func_226278_cu_() + 1.5D, player
/* 35 */             .func_226281_cx_());
/* 36 */         fireball.field_70232_b = look.field_72450_a * 0.2D;
/* 37 */         fireball.field_70233_c = look.field_72448_b * 0.2D;
/* 38 */         fireball.field_70230_d = look.field_72449_c * 0.2D;
/* 39 */         world.func_217376_c((Entity)fireball);
/*    */         
/* 41 */         player.func_184811_cZ().func_185145_a(getItem(), 10);
/* 42 */         itemstack.func_190918_g(1);
/*    */       } 
/*    */     }
/*    */     
/* 46 */     return ActionResult.func_226248_a_(itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\dials\FlameDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */