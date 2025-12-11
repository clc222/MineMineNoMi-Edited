/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.SlashDamageImmunityAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public class StringWallBlock extends Block {
/* 25 */   protected static final VoxelShape COLLISION_SHAPE = Block.func_208617_a(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
/* 26 */   protected static final VoxelShape OUTLINE_SHAPE = Block.func_208617_a(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
/*    */ 
/*    */   
/*    */   public StringWallBlock() {
/* 30 */     super(AbstractBlock.Properties.func_200945_a(Material.field_175972_I).func_200948_a(-1.0F, 10000.0F).func_222380_e());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape func_220071_b(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
/* 36 */     Entity entity = context.getEntity();
/* 37 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 39 */       PlayerEntity player = (PlayerEntity)entity;
/* 40 */       boolean hasBara = DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.BARA_BARA_NO_MI);
/* 41 */       boolean hasSlashImmunity = AbilityDataCapability.get((LivingEntity)player).hasUnlockedAbility(SlashDamageImmunityAbility.BARA_INSTANCE);
/* 42 */       if (hasBara || hasSlashImmunity)
/*    */       {
/* 44 */         return VoxelShapes.func_197880_a();
/*    */       }
/*    */     } 
/*    */     
/* 48 */     return COLLISION_SHAPE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape func_220053_a(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
/* 54 */     return OUTLINE_SHAPE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 67 */     return (adjacentBlockState.func_177230_c() == this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_200011_d(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 73 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_196262_a(BlockState state, World worldIn, BlockPos pos, Entity entity) {
/* 79 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 81 */       PlayerEntity player = (PlayerEntity)entity;
/* 82 */       boolean hasBara = DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.BARA_BARA_NO_MI);
/* 83 */       boolean hasSlashImmunity = AbilityDataCapability.get((LivingEntity)player).hasUnlockedAbility(SlashDamageImmunityAbility.BARA_INSTANCE);
/* 84 */       if (hasBara || hasSlashImmunity) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 90 */     entity.func_70097_a(DamageSource.field_76376_m, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\StringWallBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */