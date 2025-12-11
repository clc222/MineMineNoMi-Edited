/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityStairsAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class BarrierbilityStairsProjectile extends AbilityProjectileEntity {
/* 17 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE })).build();
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsProjectile(World world, LivingEntity player) {
/* 26 */     super((EntityType)BariProjectiles.BARRIERBILITY_STAIRS.get(), world, player);
/*    */     
/* 28 */     setMaxLife(40);
/* 29 */     setPhysical();
/* 30 */     setPassThroughEntities();
/* 31 */     setPassThroughBlocks();
/* 32 */     setPhysical();
/*    */     
/* 34 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 39 */     IAbilityData abilityProps = AbilityDataCapability.get(getThrower());
/* 40 */     BarrierbilityStairsAbility ability = (BarrierbilityStairsAbility)abilityProps.getEquippedAbility(BarrierbilityStairsAbility.INSTANCE);
/*    */     
/* 42 */     if (ability != null && ability.isContinuous())
/* 43 */       ability.fillBlocksList(AbilityHelper.createFilledCube(this.field_70170_p, func_226277_ct_(), func_226278_cu_() - 2.0D, func_226281_cx_(), 1, 1, 1, (Block)ModBlocks.BARRIER.get(), GRIEF_RULE)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bari\BarrierbilityStairsProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */