/*     */ package xyz.pixelatedw.mineminenomi.blocks.traps;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.shapes.VoxelShapes;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ 
/*     */ public abstract class TrapAbilityBlock
/*     */   extends Block {
/*  23 */   private int damageDealt = 0;
/*     */   
/*  25 */   private double horizontalFallSpeed = 0.05D;
/*     */   
/*  27 */   private double verticalFallSpeed = 0.25D;
/*     */   
/*  29 */   private DamageSource damageSource = (DamageSource)(new ModDamageSource(DamageSource.field_76368_d)).setUnavoidable();
/*     */   
/*  31 */   private Supplier<EffectInstance> potionEffect = null;
/*     */   
/*     */   public TrapAbilityBlock(AbstractBlock.Properties properties) {
/*  34 */     super(properties);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_196262_a(BlockState state, World worldIn, BlockPos pos, Entity entity) {
/*  39 */     if (entity instanceof LivingEntity) {
/*  40 */       if (!check((LivingEntity)entity)) {
/*  41 */         entity.func_213295_a(state, new Vector3d(getHorizontalFallSpeed(), getVerticalFallSpeed(), getHorizontalFallSpeed()));
/*     */       }
/*     */     }
/*  44 */     else if (entity instanceof net.minecraft.entity.item.BoatEntity) {
/*  45 */       entity.func_70106_y();
/*     */     } else {
/*     */       
/*  48 */       entity.func_213295_a(state, new Vector3d(getVerticalFallSpeed(), getHorizontalFallSpeed(), getVerticalFallSpeed()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape func_220071_b(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  54 */     Entity entity = context.getEntity();
/*  55 */     if (entity instanceof LivingEntity) {
/*  56 */       LivingEntity living = (LivingEntity)entity;
/*  57 */       if (check(living)) {
/*  58 */         return state.func_196954_c(worldIn, pos);
/*     */       }
/*     */     } 
/*     */     
/*  62 */     return VoxelShapes.func_197880_a();
/*     */   }
/*     */   
/*     */   public int getDamageDealt() {
/*  66 */     return this.damageDealt;
/*     */   }
/*     */   
/*     */   public void setDamageDealt(int damageDealt) {
/*  70 */     this.damageDealt = damageDealt;
/*     */   }
/*     */   
/*     */   public double getVerticalFallSpeed() {
/*  74 */     return this.verticalFallSpeed;
/*     */   }
/*     */   
/*     */   public void setVerticalFallSpeed(double verticalFallSpeed) {
/*  78 */     this.verticalFallSpeed = verticalFallSpeed;
/*     */   }
/*     */   
/*     */   public DamageSource getDamageSource() {
/*  82 */     return this.damageSource;
/*     */   }
/*     */   
/*     */   public void setDamageSource(DamageSource damageSource) {
/*  86 */     this.damageSource = damageSource;
/*     */   }
/*     */   
/*     */   public EffectInstance getPotionEffect() {
/*  90 */     return this.potionEffect.get();
/*     */   }
/*     */   
/*     */   public void setPotionEffect(Supplier<EffectInstance> potionEffect) {
/*  94 */     this.potionEffect = potionEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean check(LivingEntity paramLivingEntity);
/*     */   
/*     */   public boolean func_181623_g() {
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public double getHorizontalFallSpeed() {
/* 105 */     return this.horizontalFallSpeed;
/*     */   }
/*     */   
/*     */   public void setHorizontalFallSpeed(double horizontalFallSpeed) {
/* 109 */     this.horizontalFallSpeed = horizontalFallSpeed;
/*     */   }
/*     */   
/*     */   public static boolean isEntityInsideOpaqueBlock(LivingEntity e, int offSet) {
/* 113 */     BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
/* 114 */     for (int i = 0; i < 8; i++) {
/* 115 */       int j = MathHelper.func_76128_c(e.func_226278_cu_() - offSet + ((((i >> 0) % 2) - 0.5F) * 0.1F) + e.func_70047_e());
/* 116 */       int k = MathHelper.func_76128_c(e.func_226277_ct_() + ((((i >> 1) % 2) - 0.5F) * e.func_213302_cg() * 0.8F));
/* 117 */       int l = MathHelper.func_76128_c(e.func_226281_cx_() + ((((i >> 2) % 2) - 0.5F) * e.func_213311_cf() * 0.8F));
/* 118 */       if (blockpos$mutable.func_177958_n() != k || blockpos$mutable.func_177956_o() != j || blockpos$mutable.func_177952_p() != l) {
/* 119 */         blockpos$mutable.func_181079_c(k, j, l);
/* 120 */         if (e.field_70170_p.func_180495_p((BlockPos)blockpos$mutable).func_177230_c() instanceof TrapAbilityBlock) {
/* 121 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\traps\TrapAbilityBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */