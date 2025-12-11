/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.EntityPredicates;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import xyz.pixelatedw.mineminenomi.entities.CannonEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class CannonItem extends BlockItem {
/* 25 */   private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.field_180132_d.and(Entity::func_70067_L);
/*    */   
/*    */   public CannonItem(Block block) {
/* 28 */     super(block, (new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResultType func_195942_a(BlockItemUseContext ctx) {
/* 33 */     PlayerEntity player = ctx.func_195999_j();
/* 34 */     ItemStack heldItem = player.func_184586_b(Hand.MAIN_HAND);
/* 35 */     BlockRayTraceResult blockRayTraceResult = func_219968_a(ctx.func_195991_k(), player, RayTraceContext.FluidMode.ANY);
/* 36 */     if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.MISS) {
/* 37 */       return ActionResult.func_226250_c_(heldItem).func_188397_a();
/*    */     }
/*    */     
/* 40 */     Vector3d viewVec = player.func_70676_i(1.0F);
/* 41 */     double scale = 5.0D;
/* 42 */     List<Entity> list = ctx.func_195991_k().func_175674_a((Entity)player, player.func_174813_aQ().func_216361_a(viewVec.func_186678_a(scale)).func_186662_g(1.0D), ENTITY_PREDICATE);
/* 43 */     if (!list.isEmpty()) {
/* 44 */       Vector3d vector3d1 = player.func_174824_e(1.0F);
/*    */       
/* 46 */       for (Entity entity : list) {
/* 47 */         AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_186662_g(entity.func_70111_Y());
/* 48 */         if (axisalignedbb.func_72318_a(vector3d1)) {
/* 49 */           return ActionResult.func_226250_c_(heldItem).func_188397_a();
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.BLOCK) {
/* 55 */       CannonEntity cannonEntity = new CannonEntity(ctx.func_195991_k());
/* 56 */       cannonEntity.func_70107_b((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c);
/* 57 */       cannonEntity.field_70177_z = player.field_70177_z;
/* 58 */       if (!ctx.func_195991_k().func_226665_a__((Entity)cannonEntity, cannonEntity.func_174813_aQ().func_186662_g(-0.1D))) {
/* 59 */         return ActionResult.func_226251_d_(heldItem).func_188397_a();
/*    */       }
/*    */       
/* 62 */       if (!(ctx.func_195991_k()).field_72995_K) {
/* 63 */         ctx.func_195991_k().func_217376_c((Entity)cannonEntity);
/* 64 */         if (!player.field_71075_bZ.field_75098_d) {
/* 65 */           heldItem.func_190918_g(1);
/*    */         }
/*    */       } 
/*    */       
/* 69 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/* 70 */       return ActionResult.func_233538_a_(heldItem, ctx.func_195991_k().func_201670_d()).func_188397_a();
/*    */     } 
/*    */ 
/*    */     
/* 74 */     return ActionResult.func_226250_c_(heldItem).func_188397_a();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\CannonItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */