/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EntityPredicates;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.StrikerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class StrikerItem extends Item {
/* 23 */   private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.field_180132_d.and(Entity::func_70067_L);
/*    */   
/*    */   public StrikerItem() {
/* 26 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World level, PlayerEntity player, Hand hand) {
/* 31 */     ItemStack heldItem = player.func_184586_b(Hand.MAIN_HAND);
/* 32 */     BlockRayTraceResult blockRayTraceResult = func_219968_a(level, player, RayTraceContext.FluidMode.ANY);
/* 33 */     if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.MISS) {
/* 34 */       return ActionResult.func_226250_c_(heldItem);
/*    */     }
/*    */     
/* 37 */     Vector3d viewVec = player.func_70676_i(1.0F);
/* 38 */     double scale = 5.0D;
/* 39 */     List<Entity> list = level.func_175674_a((Entity)player, player.func_174813_aQ().func_216361_a(viewVec.func_186678_a(scale)).func_186662_g(1.0D), ENTITY_PREDICATE);
/* 40 */     if (!list.isEmpty()) {
/* 41 */       Vector3d vector3d1 = player.func_174824_e(1.0F);
/*    */       
/* 43 */       for (Entity entity : list) {
/* 44 */         AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_186662_g(entity.func_70111_Y());
/* 45 */         if (axisalignedbb.func_72318_a(vector3d1)) {
/* 46 */           return ActionResult.func_226250_c_(heldItem);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 51 */     if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.BLOCK) {
/* 52 */       StrikerEntity striker = new StrikerEntity(level);
/* 53 */       striker.func_70107_b((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c);
/* 54 */       striker.field_70177_z = player.field_70177_z;
/* 55 */       if (!level.func_226665_a__((Entity)striker, striker.func_174813_aQ().func_186662_g(-0.1D))) {
/* 56 */         return ActionResult.func_226251_d_(heldItem);
/*    */       }
/*    */       
/* 59 */       if (!level.field_72995_K) {
/* 60 */         level.func_217376_c((Entity)striker);
/* 61 */         if (!player.field_71075_bZ.field_75098_d) {
/* 62 */           heldItem.func_190918_g(1);
/*    */         }
/*    */       } 
/*    */       
/* 66 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/* 67 */       return ActionResult.func_233538_a_(heldItem, level.func_201670_d());
/*    */     } 
/*    */ 
/*    */     
/* 71 */     return ActionResult.func_226250_c_(heldItem);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\StrikerItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */