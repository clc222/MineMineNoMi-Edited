/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.Stats;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EntityPredicates;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceContext;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.UnicycleEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class UnicycleItem extends Item {
/* 21 */   private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.field_180132_d.and(Entity::func_70067_L);
/*    */   
/*    */   public UnicycleItem() {
/* 24 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World level, PlayerEntity player, Hand hand) {
/* 29 */     ItemStack heldItem = player.func_184586_b(Hand.MAIN_HAND);
/* 30 */     BlockRayTraceResult blockRayTraceResult = func_219968_a(level, player, RayTraceContext.FluidMode.ANY);
/* 31 */     if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.MISS) {
/* 32 */       return ActionResult.func_226250_c_(heldItem);
/*    */     }
/*    */     
/* 35 */     Vector3d viewVec = player.func_70676_i(1.0F);
/* 36 */     double scale = 5.0D;
/* 37 */     if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.BLOCK) {
/* 38 */       UnicycleEntity unicycle = new UnicycleEntity(level);
/* 39 */       unicycle.func_70107_b((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c);
/* 40 */       unicycle.field_70177_z = player.field_70177_z;
/* 41 */       if (!level.field_72995_K) {
/* 42 */         level.func_217376_c((Entity)unicycle);
/* 43 */         if (!player.field_71075_bZ.field_75098_d) {
/* 44 */           heldItem.func_190918_g(1);
/*    */         }
/*    */       } 
/* 47 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/* 48 */       return ActionResult.func_233538_a_(heldItem, level.func_201670_d());
/*    */     } 
/*    */     
/* 51 */     return ActionResult.func_226250_c_(heldItem);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\UnicycleItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */