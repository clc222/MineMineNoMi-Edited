/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class CandyEscalatorProjectile extends AbilityProjectileEntity {
/* 14 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE })).build();
/*    */ 
/*    */   
/*    */   public CandyEscalatorProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyEscalatorProjectile(World world, LivingEntity player) {
/* 23 */     super((EntityType)PeroProjectiles.CANDY_ESCALATOR.get(), world, player);
/*    */     
/* 25 */     setMaxLife(30);
/* 26 */     setPhysical();
/* 27 */     setPassThroughEntities();
/* 28 */     setPassThroughBlocks();
/* 29 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 34 */     AbilityHelper.createFilledCube(this.field_70170_p, func_226277_ct_(), func_226278_cu_() - 2.0D, func_226281_cx_(), 1, 1, 1, (Block)ModBlocks.CANDY.get(), GRIEF_RULE);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\CandyEscalatorProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */