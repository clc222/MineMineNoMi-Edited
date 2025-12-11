/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.block.DispenserBlock;
/*    */ import net.minecraft.dispenser.IDispenseItemBehavior;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.dispenser.ProjectileDispenseBehavior;
/*    */ import net.minecraft.entity.projectile.ProjectileEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.NetEntity;
/*    */ 
/*    */ public class ModDispenseBehaviors {
/*    */   public static void init() {
/* 14 */     DispenserBlock.func_199774_a((IItemProvider)ModItems.ROPE_NET.get(), (IDispenseItemBehavior)new ProjectileDispenseBehavior()
/*    */         {
/*    */           protected ProjectileEntity func_82499_a(World level, IPosition position, ItemStack stack) {
/* 17 */             NetEntity netEntity = NetEntity.createNormalNet(level, null);
/* 18 */             netEntity.func_70107_b(position.func_82615_a(), position.func_82617_b(), position.func_82616_c());
/* 19 */             return (ProjectileEntity)netEntity;
/*    */           }
/*    */         });
/*    */     
/* 23 */     DispenserBlock.func_199774_a((IItemProvider)ModItems.KAIROSEKI_NET.get(), (IDispenseItemBehavior)new ProjectileDispenseBehavior()
/*    */         {
/*    */           protected ProjectileEntity func_82499_a(World level, IPosition position, ItemStack stack) {
/* 26 */             NetEntity netEntity = NetEntity.createKairosekiNet(level, null);
/* 27 */             netEntity.func_70107_b(position.func_82615_a(), position.func_82617_b(), position.func_82616_c());
/* 28 */             return (ProjectileEntity)netEntity;
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModDispenseBehaviors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */