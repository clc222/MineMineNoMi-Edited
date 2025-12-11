/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.pathfinding.PathType;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.shapes.VoxelShapes;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.FlagDestroyedEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.FlagPlacedEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BlocksHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.items.FlagItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSetFlagOnFirePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class FlagBlock extends Block {
/*  54 */   private static final int[] SCALE_XZ = new int[] { 1, 3, 6, 9 };
/*  55 */   private static final int[] SCALE_Y = new int[] { 1, 2, 4, 6 };
/*     */   
/*  57 */   public static final DirectionProperty FACING = HorizontalBlock.field_185512_D;
/*  58 */   public static final EnumProperty<CanvasSize> SIZE = BlocksHelper.SIZE;
/*     */   
/*     */   public FlagBlock() {
/*  61 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151580_n).func_200943_b(0.2F).func_226896_b_().func_235842_b_(BlocksHelper::never));
/*  62 */     func_180632_j((BlockState)((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)FACING, (Comparable)Direction.NORTH)).func_206870_a((Property)SIZE, (Comparable)CanvasSize.SMALL));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape func_220053_a(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
/*  67 */     return VoxelShapes.func_197868_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape func_220071_b(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
/*  72 */     Entity entity = context.getEntity();
/*  73 */     if (entity instanceof ProjectileEntity) {
/*  74 */       return state.func_196954_c(level, pos);
/*     */     }
/*     */     
/*  77 */     return VoxelShapes.func_197880_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_196260_a(BlockState state, IWorldReader world, BlockPos pos) {
/*  82 */     BlockState supportBlock = world.func_180495_p(pos.func_177971_a(((Direction)state.func_177229_b((Property)FACING)).func_176734_d().func_176730_m()));
/*  83 */     return supportBlock.func_185904_a().func_76218_k();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState func_196258_a(BlockItemUseContext context) {
/*  89 */     BlockState blockstate = func_176223_P();
/*  90 */     BlockPos blockpos = context.func_195995_a();
/*  91 */     Direction[] adirection = context.func_196009_e();
/*  92 */     CanvasSize size = CanvasSize.values()[context.func_195996_i().func_196082_o().func_74762_e("canvasSize")];
/*  93 */     blockstate = (BlockState)blockstate.func_206870_a((Property)SIZE, (Comparable)size);
/*     */     
/*  95 */     for (Direction direction : adirection) {
/*  96 */       if (direction.func_176740_k().func_176722_c()) {
/*  97 */         Direction direction1 = direction.func_176734_d();
/*  98 */         blockstate = (BlockState)blockstate.func_206870_a((Property)FACING, (Comparable)direction1);
/*  99 */         BlockState supportBlock = context.func_195991_k().func_180495_p(blockpos.func_177971_a(((Direction)blockstate.func_177229_b((Property)FACING)).func_176734_d().func_176730_m()));
/*     */ 
/*     */         
/* 102 */         if (supportBlock.func_177230_c() == ModBlocks.FLAG.get()) {
/* 103 */           return null;
/*     */         }
/*     */         
/* 106 */         if (blockstate.func_196955_c((IWorldReader)context.func_195991_k(), blockpos)) {
/* 107 */           return blockstate;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176208_a(World level, BlockPos pos, BlockState state, PlayerEntity player) {
/* 117 */     breakFlag((IWorld)level, pos, (LivingEntity)player);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 122 */     return (TileEntity)new FlagTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public List<ItemStack> func_220076_a(BlockState state, LootContext.Builder builder) {
/* 131 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_220066_a(World level, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
/* 147 */     if (level.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     boolean fireFlag = projectile.func_70027_ad();
/* 152 */     boolean waterFlag = false;
/*     */     
/* 154 */     if (projectile instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)projectile).getParent() != null) {
/* 155 */       fireFlag = (projectile.func_70027_ad() || ((AbilityProjectileEntity)projectile).getParent().getSourceElement().equals(SourceElement.FIRE));
/* 156 */       waterFlag = ((AbilityProjectileEntity)projectile).getParent().getSourceElement().equals(SourceElement.WATER);
/*     */     } 
/*     */     
/* 159 */     if (fireFlag || waterFlag) {
/* 160 */       TileEntity tileEntity = level.func_175625_s(hit.func_216350_a());
/* 161 */       if (tileEntity != null && tileEntity instanceof FlagTileEntity) {
/* 162 */         FlagTileEntity flagTile = (FlagTileEntity)tileEntity;
/* 163 */         if (flagTile.isSub()) {
/* 164 */           flagTile = flagTile.getMaster();
/*     */         }
/* 166 */         BlockPos masterTilePos = flagTile.func_174877_v();
/* 167 */         if (fireFlag) {
/* 168 */           flagTile.setOnFire(true);
/* 169 */           flagTile.setLastAttacker((Entity)projectile);
/* 170 */           WyNetwork.sendToAllAroundDistance(new SSetFlagOnFirePacket(masterTilePos, true), level, hit.func_216347_e(), 256);
/*     */         }
/* 172 */         else if (waterFlag) {
/* 173 */           flagTile.setOnFire(false);
/* 174 */           WyNetwork.sendToAllAroundDistance(new SSetFlagOnFirePacket(masterTilePos, false), level, hit.func_216347_e(), 256);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180633_a(World level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
/* 182 */     TileEntity tileEntity = level.func_175625_s(pos);
/* 183 */     if (tileEntity != null && tileEntity instanceof FlagTileEntity) {
/* 184 */       FlagTileEntity flagTile = (FlagTileEntity)tileEntity;
/* 185 */       flagTile.setOwner(placer);
/*     */       
/* 187 */       if (stack.func_77942_o()) {
/* 188 */         Crew crew = Crew.from(stack.func_77978_p().func_74775_l("crew"));
/* 189 */         flagTile.setCrew(crew);
/*     */       } 
/*     */       
/* 192 */       boolean drop = false;
/* 193 */       if (placer != null && placer instanceof PlayerEntity) {
/* 194 */         drop = ((PlayerEntity)placer).field_71075_bZ.field_75098_d;
/*     */       }
/* 196 */       placeSubs((IWorld)level, pos, state, drop);
/*     */       
/* 198 */       if (placer != null && placer instanceof PlayerEntity) {
/* 199 */         FlagPlacedEvent event = new FlagPlacedEvent((PlayerEntity)placer, state, pos, flagTile);
/* 200 */         if (MinecraftForge.EVENT_BUS.post((Event)event)) {
/* 201 */           level.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void placeSubs(IWorld world, BlockPos pos, BlockState state, boolean drop) {
/* 208 */     FlagTileEntity flagTile = (FlagTileEntity)world.func_175625_s(pos);
/*     */     
/* 210 */     Direction dir = (Direction)state.func_177229_b((Property)FACING);
/* 211 */     CanvasSize size = (CanvasSize)state.func_177229_b((Property)SIZE);
/*     */     
/* 213 */     if (size.ordinal() > CanvasSize.SMALL.ordinal()) {
/* 214 */       int xzSize = SCALE_XZ[size.ordinal()];
/* 215 */       int ySize = SCALE_Y[size.ordinal()];
/*     */ 
/*     */       
/* 218 */       for (int y = 0; y < ySize; y++) {
/* 219 */         for (int xz = 0; xz < xzSize; xz++) {
/* 220 */           if (y != 0 || xz != 0) {
/*     */ 
/*     */             
/* 223 */             BlockPos pos2 = pos.func_177967_a(dir, xz).func_177982_a(0, -y, 0);
/* 224 */             boolean isAir = world.func_180495_p(pos2).func_196958_f();
/* 225 */             if (isAir && world.func_180501_a(pos2, ((Block)ModBlocks.FLAG.get()).func_176223_P(), 3)) {
/* 226 */               FlagTileEntity subFlagTile = (FlagTileEntity)world.func_175625_s(pos2);
/* 227 */               if (subFlagTile != null) {
/* 228 */                 subFlagTile.setMaster(flagTile);
/* 229 */                 flagTile.addSub(pos2);
/*     */               } 
/*     */             } else {
/*     */               
/* 233 */               flagTile.breakAllBlocks(drop);
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void breakFlag(IWorld level, BlockPos pos, @Nullable LivingEntity entity) {
/* 242 */     TileEntity tileEntity = level.func_175625_s(pos);
/*     */     
/* 244 */     if (tileEntity != null && tileEntity instanceof FlagTileEntity) {
/* 245 */       FlagTileEntity flagEntity = (FlagTileEntity)tileEntity;
/*     */       
/* 247 */       if (flagEntity.isSub()) {
/* 248 */         flagEntity = flagEntity.getMaster();
/*     */       }
/*     */       
/* 251 */       if (flagEntity == null) {
/*     */         return;
/*     */       }
/*     */       
/* 255 */       flagEntity = flagEntity.getMaster();
/*     */       
/* 257 */       BlockState masterState = level.func_180495_p(flagEntity.func_174877_v());
/*     */       
/* 259 */       if (masterState.func_196958_f()) {
/*     */         return;
/*     */       }
/*     */       
/* 263 */       ItemStack flagStack = new ItemStack((IItemProvider)ModBlocks.FLAG.get());
/*     */       
/* 265 */       if (masterState.func_235901_b_((Property)SIZE)) {
/* 266 */         FlagItem.setCanvasSize(flagStack, (CanvasSize)masterState.func_177229_b((Property)SIZE));
/*     */       }
/*     */       
/* 269 */       boolean drop = false;
/*     */       
/* 271 */       if (entity != null && entity instanceof PlayerEntity) {
/* 272 */         drop = ((PlayerEntity)entity).field_71075_bZ.field_75098_d;
/*     */       }
/*     */       
/* 275 */       if (level instanceof World && !drop) {
/* 276 */         ItemEntity itemDrop = new ItemEntity((World)level, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), flagStack);
/*     */         
/* 278 */         level.func_217376_c((Entity)itemDrop);
/*     */       } 
/*     */       
/* 281 */       flagEntity.breakAllBlocks(drop);
/*     */       
/* 283 */       FlagDestroyedEvent event = new FlagDestroyedEvent(masterState, pos, flagEntity.getLastAttacker(), flagEntity);
/*     */       
/* 285 */       MinecraftForge.EVENT_BUS.post((Event)event);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 291 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/* 296 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_200122_a(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 302 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_196271_a(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
/* 307 */     if (facing.func_176734_d() == state.func_177229_b((Property)FACING) && !state.func_196955_c((IWorldReader)world, currentPos)) {
/* 308 */       breakFlag(world, currentPos, (LivingEntity)null);
/* 309 */       return Blocks.field_150350_a.func_176223_P();
/*     */     } 
/*     */     
/* 312 */     return state;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_185499_a(BlockState state, Rotation rot) {
/* 317 */     return (BlockState)state.func_206870_a((Property)FACING, (Comparable)rot.func_185831_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState func_185471_a(BlockState state, Mirror mirrorIn) {
/* 322 */     return state.func_185907_a(mirrorIn.func_185800_a((Direction)state.func_177229_b((Property)FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 327 */     builder.func_206894_a(new Property[] { (Property)FACING });
/* 328 */     builder.func_206894_a(new Property[] { (Property)SIZE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_196266_a(BlockState pState, IBlockReader pLevel, BlockPos pPos, PathType pType) {
/* 333 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\FlagBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */