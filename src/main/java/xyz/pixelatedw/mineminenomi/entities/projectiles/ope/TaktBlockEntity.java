/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.block.AnvilBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.FallingBlock;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.DirectionalPlaceContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tags.BlockTags;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ 
/*     */ public class TaktBlockEntity
/*     */   extends Entity {
/*     */   public int time;
/*     */   public boolean dropItem = true;
/*  55 */   private int fallDamageMax = 40; private boolean cancelDrop; private boolean hurtEntities;
/*  56 */   private float fallDamageAmount = 2.0F;
/*     */   public CompoundNBT blockData;
/*  58 */   protected static final DataParameter<BlockPos> DATA_START_POS = EntityDataManager.func_187226_a(TaktBlockEntity.class, DataSerializers.field_187200_j);
/*  59 */   protected static final DataParameter<Optional<BlockState>> BLOCK_STATE = EntityDataManager.func_187226_a(TaktBlockEntity.class, DataSerializers.field_187197_g);
/*     */ 
/*     */   
/*     */   public TaktBlockEntity(EntityType type, World world) {
/*  63 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public TaktBlockEntity(World world, double x, double y, double z, BlockState state) {
/*  68 */     super((EntityType)OpeProjectiles.TAKT_BLOCK.get(), world);
/*  69 */     setBlockState(state);
/*  70 */     this.field_70156_m = true;
/*  71 */     func_70107_b(x, y + ((1.0F - func_213302_cg()) / 2.0F), z);
/*  72 */     AbilityHelper.setDeltaMovement(this, Vector3d.field_186680_a);
/*  73 */     this.field_70169_q = x;
/*  74 */     this.field_70167_r = y;
/*  75 */     this.field_70166_s = z;
/*  76 */     setStartPos(func_233580_cy_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70075_an() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStartPos(BlockPos pStartPos) {
/*  90 */     this.field_70180_af.func_187227_b(DATA_START_POS, pStartPos);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public BlockPos getStartPos() {
/*  96 */     return (BlockPos)this.field_70180_af.func_187225_a(DATA_START_POS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_225502_at_() {
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 108 */     this.field_70180_af.func_187214_a(DATA_START_POS, BlockPos.field_177992_a);
/* 109 */     this.field_70180_af.func_187214_a(BLOCK_STATE, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 118 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 127 */     if (getBlockState().func_196958_f()) {
/*     */       
/* 129 */       func_70106_y();
/*     */     }
/*     */     else {
/*     */       
/* 133 */       Block block = getBlockState().func_177230_c();
/* 134 */       if (this.time++ == 0) {
/*     */         
/* 136 */         BlockPos blockpos = func_233580_cy_();
/* 137 */         if (this.field_70170_p.func_180495_p(blockpos).func_203425_a(block)) {
/*     */           
/* 139 */           this.field_70170_p.func_217377_a(blockpos, false);
/*     */         }
/* 141 */         else if (!this.field_70170_p.field_72995_K) {
/*     */           
/* 143 */           func_70106_y();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 148 */       if (!func_189652_ae()) {
/* 149 */         AbilityHelper.setDeltaMovement(this, func_213322_ci().func_72441_c(0.0D, -0.04D, 0.0D));
/*     */       }
/*     */       
/* 152 */       func_213315_a(MoverType.SELF, func_213322_ci());
/* 153 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 155 */         BlockPos blockpos1 = func_233580_cy_();
/* 156 */         boolean flag = getBlockState().func_177230_c() instanceof net.minecraft.block.ConcretePowderBlock;
/* 157 */         boolean flag1 = (flag && this.field_70170_p.func_204610_c(blockpos1).func_206884_a((ITag)FluidTags.field_206959_a));
/* 158 */         double d0 = func_213322_ci().func_189985_c();
/* 159 */         if (flag && d0 > 1.0D) {
/*     */           
/* 161 */           BlockRayTraceResult blockraytraceresult = this.field_70170_p.func_217299_a(new RayTraceContext(new Vector3d(this.field_70169_q, this.field_70167_r, this.field_70166_s), func_213303_ch(), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.SOURCE_ONLY, this));
/* 162 */           if (blockraytraceresult.func_216346_c() != RayTraceResult.Type.MISS && this.field_70170_p.func_204610_c(blockraytraceresult.func_216350_a()).func_206884_a((ITag)FluidTags.field_206959_a)) {
/*     */             
/* 164 */             blockpos1 = blockraytraceresult.func_216350_a();
/* 165 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 169 */         if (!func_233570_aj_() && !flag1) {
/*     */           
/* 171 */           if (!this.field_70170_p.field_72995_K && ((this.time > 100 && (blockpos1.func_177956_o() < 1 || blockpos1.func_177956_o() > 256)) || this.time > 600))
/*     */           {
/* 173 */             if (this.dropItem && this.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223604_g))
/*     */             {
/* 175 */               func_199703_a((IItemProvider)block);
/*     */             }
/*     */             
/* 178 */             func_70106_y();
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 183 */           BlockState blockstate = this.field_70170_p.func_180495_p(blockpos1);
/* 184 */           AbilityHelper.setDeltaMovement(this, func_213322_ci().func_216372_d(0.7D, -0.5D, 0.7D));
/* 185 */           if (!blockstate.func_203425_a(Blocks.field_196603_bb)) {
/*     */             
/* 187 */             func_70106_y();
/* 188 */             if (!this.cancelDrop) {
/*     */               
/* 190 */               boolean flag2 = blockstate.func_196953_a((BlockItemUseContext)new DirectionalPlaceContext(this.field_70170_p, blockpos1, Direction.DOWN, ItemStack.field_190927_a, Direction.UP));
/* 191 */               boolean flag3 = (FallingBlock.func_185759_i(this.field_70170_p.func_180495_p(blockpos1.func_177977_b())) && (!flag || !flag1));
/* 192 */               boolean flag4 = (getBlockState().func_196955_c((IWorldReader)this.field_70170_p, blockpos1) && !flag3);
/* 193 */               if (flag2 && flag4) {
/*     */                 
/* 195 */                 if (getBlockState().func_235901_b_((Property)BlockStateProperties.field_208198_y) && this.field_70170_p.func_204610_c(blockpos1).func_206886_c() == Fluids.field_204546_a)
/*     */                 {
/* 197 */                   setBlockState((BlockState)getBlockState().func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(true)));
/*     */                 }
/*     */                 
/* 200 */                 if (this.field_70170_p.func_180501_a(blockpos1, getBlockState(), 3))
/*     */                 {
/* 202 */                   if (this.blockData != null && getBlockState().hasTileEntity()) {
/*     */                     
/* 204 */                     TileEntity tileentity = this.field_70170_p.func_175625_s(blockpos1);
/* 205 */                     if (tileentity != null) {
/*     */                       
/* 207 */                       CompoundNBT compoundnbt = tileentity.func_189515_b(new CompoundNBT());
/*     */                       
/* 209 */                       for (String s : this.blockData.func_150296_c()) {
/*     */                         
/* 211 */                         INBT inbt = this.blockData.func_74781_a(s);
/* 212 */                         if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s))
/*     */                         {
/* 214 */                           compoundnbt.func_218657_a(s, inbt.func_74737_b());
/*     */                         }
/*     */                       } 
/*     */                       
/* 218 */                       tileentity.func_230337_a_(getBlockState(), compoundnbt);
/* 219 */                       tileentity.func_70296_d();
/*     */                     } 
/*     */                   } 
/* 222 */                   ProtectedArea area = ProtectedAreasData.get(this.field_70170_p).getProtectedArea(blockpos1.func_177958_n(), blockpos1.func_177956_o(), blockpos1.func_177952_p());
/* 223 */                   if (area != null) {
/* 224 */                     area.queueForRestoration(this.field_70170_p, blockpos1);
/*     */                   }
/*     */                 }
/* 227 */                 else if (this.dropItem && this.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223604_g))
/*     */                 {
/* 229 */                   func_199703_a((IItemProvider)block);
/*     */                 }
/*     */               
/* 232 */               } else if (this.dropItem && this.field_70170_p.func_82736_K().func_223586_b(GameRules.field_223604_g)) {
/*     */                 
/* 234 */                 func_199703_a((IItemProvider)block);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 241 */       AbilityHelper.setDeltaMovement(this, func_213322_ci().func_186678_a(0.98D));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_225503_b_(float pFallDistance, float pDamageMultiplier) {
/* 248 */     if (this.hurtEntities) {
/*     */       
/* 250 */       int i = MathHelper.func_76123_f(pFallDistance - 1.0F);
/* 251 */       if (i > 0) {
/*     */         
/* 253 */         List<Entity> list = Lists.newArrayList(this.field_70170_p.func_72839_b(this, func_174813_aQ()));
/* 254 */         boolean flag = getBlockState().func_235714_a_((ITag)BlockTags.field_200572_k);
/* 255 */         DamageSource damagesource = flag ? DamageSource.field_82728_o : DamageSource.field_82729_p;
/*     */         
/* 257 */         for (Entity entity : list)
/*     */         {
/* 259 */           entity.func_70097_a(damagesource, Math.min(MathHelper.func_76141_d(i * this.fallDamageAmount), this.fallDamageMax));
/*     */         }
/*     */         
/* 262 */         if (flag && this.field_70146_Z.nextFloat() < 0.05000000074505806D + i * 0.05D) {
/*     */           
/* 264 */           BlockState blockstate = AnvilBlock.func_196433_f(getBlockState());
/* 265 */           if (blockstate == null) {
/*     */             
/* 267 */             this.cancelDrop = true;
/*     */           }
/*     */           else {
/*     */             
/* 271 */             setBlockState(blockstate);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT pCompound) {
/* 283 */     pCompound.func_218657_a("BlockState", (INBT)NBTUtil.func_190009_a(getBlockState()));
/* 284 */     pCompound.func_74768_a("Time", this.time);
/* 285 */     pCompound.func_74757_a("DropItem", this.dropItem);
/* 286 */     pCompound.func_74757_a("HurtEntities", this.hurtEntities);
/* 287 */     pCompound.func_74776_a("FallHurtAmount", this.fallDamageAmount);
/* 288 */     pCompound.func_74768_a("FallHurtMax", this.fallDamageMax);
/* 289 */     if (this.blockData != null)
/*     */     {
/* 291 */       pCompound.func_218657_a("TileEntityData", (INBT)this.blockData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT pCompound) {
/* 302 */     setBlockState(NBTUtil.func_190008_d(pCompound.func_74775_l("BlockState")));
/* 303 */     this.time = pCompound.func_74762_e("Time");
/* 304 */     if (pCompound.func_150297_b("HurtEntities", 99)) {
/*     */       
/* 306 */       this.hurtEntities = pCompound.func_74767_n("HurtEntities");
/* 307 */       this.fallDamageAmount = pCompound.func_74760_g("FallHurtAmount");
/* 308 */       this.fallDamageMax = pCompound.func_74762_e("FallHurtMax");
/*     */     }
/* 310 */     else if (getBlockState().func_235714_a_((ITag)BlockTags.field_200572_k)) {
/*     */       
/* 312 */       this.hurtEntities = true;
/*     */     } 
/*     */     
/* 315 */     if (pCompound.func_150297_b("DropItem", 99))
/*     */     {
/* 317 */       this.dropItem = pCompound.func_74767_n("DropItem");
/*     */     }
/*     */     
/* 320 */     if (pCompound.func_150297_b("TileEntityData", 10))
/*     */     {
/* 322 */       this.blockData = pCompound.func_74775_l("TileEntityData");
/*     */     }
/*     */     
/* 325 */     if (getBlockState().func_196958_f())
/*     */     {
/* 327 */       setBlockState(Blocks.field_150354_m.func_176223_P());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public World getLevel() {
/* 335 */     return this.field_70170_p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurtsEntities(boolean pHurtEntities) {
/* 340 */     this.hurtEntities = pHurtEntities;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_90999_ad() {
/* 350 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_85029_a(CrashReportCategory pCategory) {
/* 356 */     super.func_85029_a(pCategory);
/* 357 */     pCategory.func_71507_a("Immitating BlockState", getBlockState().toString());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_184213_bq() {
/* 376 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockState(BlockState state) {
/* 381 */     func_184212_Q().func_187227_b(BLOCK_STATE, Optional.of(state));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState getBlockState() {
/* 386 */     if (!((Optional)func_184212_Q().func_187225_a(BLOCK_STATE)).isPresent())
/* 387 */       return Blocks.field_150350_a.func_176223_P(); 
/* 388 */     return ((Optional<BlockState>)func_184212_Q().func_187225_a(BLOCK_STATE)).get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 394 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\TaktBlockEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */