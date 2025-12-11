/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class MilkyDialProjectile extends AbilityProjectileEntity {
/*    */   public MilkyDialProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */   
/*    */   public MilkyDialProjectile(World world, LivingEntity player) {
/* 20 */     super((EntityType)ExtraProjectiles.MILKY_DIAL_PROJECTILE.get(), world, player);
/*    */     
/* 22 */     setMaxLife(40);
/* 23 */     setPhysical();
/* 24 */     setPassThroughBlocks();
/*    */     
/* 26 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 30 */     for (BlockPos blockpos : BlockPos.func_218278_a(func_233580_cy_().func_177982_a(-1, -1, -1), func_233580_cy_().func_177982_a(1, 0, 1))) {
/* 31 */       BlockState state = (BlockState)((Block)ModBlocks.SKY_BLOCK.get()).func_176223_P().func_206870_a((Property)SkyBlockBlock.TYPE, Integer.valueOf(this.field_70146_Z.nextInt(4)));
/* 32 */       AbilityHelper.placeBlockIfAllowed(getThrower(), blockpos, state, 3, AirBlockProtectionRule.INSTANCE);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\MilkyDialProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */