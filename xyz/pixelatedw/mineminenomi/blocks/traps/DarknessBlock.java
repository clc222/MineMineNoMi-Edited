/*    */ package xyz.pixelatedw.mineminenomi.blocks.traps;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.yami.BlackHoleAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class DarknessBlock extends TrapAbilityBlock {
/*    */   public DarknessBlock() {
/* 26 */     super(AbstractBlock.Properties.func_200945_a(ModBlocks.DARKNESS_MATERIAL).func_200948_a(-1.0F, 10000.0F).func_222380_e());
/*    */     
/* 28 */     setDamageDealt(6);
/* 29 */     setHorizontalFallSpeed(0.03D);
/* 30 */     setVerticalFallSpeed(0.3D);
/* 31 */     setDamageSource((DamageSource)ModDamageSource.TOO_MUCH_GRAVITY);
/* 32 */     setPotionEffect(() -> new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 2, 0, false, false, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/* 38 */     boolean hasFruit = DevilFruitCapability.get(entity).hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI);
/*    */     
/* 40 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*    */     
/* 42 */     if (abilityDataProps == null) {
/* 43 */       return hasFruit;
/*    */     }
/*    */     
/* 46 */     boolean hasAbility = abilityDataProps.hasUnlockedAbility(BlackHoleAbility.INSTANCE);
/*    */     
/* 48 */     return (hasFruit || hasAbility);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState func_196271_a(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
/* 54 */     return super.func_196271_a(state, facing, facingState, world, pos, facingPos);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_220082_b(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random rand) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_196260_a(BlockState state, IWorldReader world, BlockPos pos) {
/* 73 */     return world.func_180495_p(pos.func_177977_b()).func_185904_a().func_76220_a();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\traps\DarknessBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */