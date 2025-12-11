/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.TickPriority;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MucusBlock extends Block {
/*  34 */   protected static final VoxelShape SHAPE = Block.func_208617_a(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
/*     */   
/*  36 */   private int ticks = 1200;
/*     */   
/*     */   public MucusBlock() {
/*  39 */     super(AbstractBlock.Properties.func_200945_a(ModMaterials.MUCUS).func_200943_b(8.0F).func_200944_c().func_222380_e());
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  44 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape func_220053_a(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  49 */     return SHAPE;
/*     */   }
/*     */   
/*     */   public boolean isFullCube(BlockState state) {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_196260_a(BlockState state, IWorldReader world, BlockPos pos) {
/*  58 */     return world.func_180495_p(pos.func_177977_b()).func_185904_a().func_76220_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
/*  63 */     return !state.func_196955_c((IWorldReader)world, pos) ? Blocks.field_150350_a.func_176223_P() : state;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/*  69 */     return (adjacentBlockState.func_177230_c() == this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_196262_a(BlockState state, World world, BlockPos pos, Entity entity) {
/*  74 */     if (entity instanceof LivingEntity && 
/*  75 */       !((LivingEntity)entity).func_70644_a((Effect)ModEffects.STICKY.get())) {
/*  76 */       ((LivingEntity)entity).func_195064_c(new EffectInstance((Effect)ModEffects.STICKY.get(), 2, 0, false, false, false));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_220082_b(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
/*  83 */     world.func_205220_G_().func_205360_a(pos, this, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
/*  88 */     if (world.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  92 */     if (this.ticks > 0) {
/*  93 */       this.ticks--;
/*     */       
/*  95 */       if (areFlamesClose((World)world, pos)) {
/*  96 */         world.func_217398_a(null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 4.0F, true, CommonConfig.INSTANCE.isAbilityGriefingEnabled() ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
/*     */       }
/*     */     } else {
/*  99 */       world.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*     */       
/* 101 */       this.ticks = 1200 + rand.nextInt(400);
/*     */     } 
/*     */     
/* 104 */     world.func_205220_G_().func_205362_a(pos, this, 1, TickPriority.EXTREMELY_HIGH);
/*     */   }
/*     */   
/*     */   private boolean areFlamesClose(World world, BlockPos pos) {
/* 108 */     int range = 2;
/*     */     
/* 110 */     for (int i = -range; i < range; i++) {
/* 111 */       for (int j = -range; j < range; j++) {
/* 112 */         for (int k = -range; k < range; k++) {
/* 113 */           double posX = (pos.func_177958_n() + i);
/* 114 */           double posY = (pos.func_177956_o() + j);
/* 115 */           double posZ = (pos.func_177952_p() + k);
/*     */           
/* 117 */           Block currentBlock = world.func_180495_p(new BlockPos(posX, posY, posZ)).func_177230_c();
/*     */           
/* 119 */           if (currentBlock == Blocks.field_150480_ab) {
/* 120 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     List<LivingEntity> list = WyHelper.getNearbyLiving(new Vector3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), (IWorld)world, range, null);
/*     */     
/* 128 */     if (!list.isEmpty()) {
/* 129 */       for (LivingEntity target : list) {
/* 130 */         if (target.func_70027_ad()) {
/* 131 */           return true;
/*     */         }
/*     */         
/* 134 */         if (target instanceof net.minecraft.entity.player.PlayerEntity) {
/* 135 */           DiableJambeAbility abl = (DiableJambeAbility)AbilityDataCapability.get(target).getEquippedAbility(DiableJambeAbility.INSTANCE);
/*     */           
/* 137 */           if (abl != null && abl.isContinuous()) {
/* 138 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 149 */     return 600;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
/* 154 */     return 1000;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\MucusBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */