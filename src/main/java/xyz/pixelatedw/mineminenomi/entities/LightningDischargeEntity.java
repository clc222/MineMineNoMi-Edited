/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.GoroProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.LightningBallEntityRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class LightningDischargeEntity
/*     */   extends Entity
/*     */ {
/*  29 */   public List<LightningBallEntityRenderer.LightningRendererPropieties> entities = new ArrayList<>();
/*  30 */   protected static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187203_m);
/*  31 */   protected static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187193_c);
/*  32 */   protected static final DataParameter<Integer> ALIVE_TICKS = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  33 */   protected static final DataParameter<Float> LIGHTNING_LENGTH = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187193_c);
/*  34 */   protected static final DataParameter<Integer> LIGHTNING_DENSITY = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  35 */   protected static final DataParameter<Integer> LIGHTNING_DETAIL = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  36 */   protected static final DataParameter<Integer> COLOR = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  37 */   protected static final DataParameter<Integer> ALPHA = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  38 */   protected static final DataParameter<Integer> RENDER_MODE = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  39 */   protected static final DataParameter<Integer> OUTLINE_COLOR = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  40 */   protected static final DataParameter<Integer> OUTLINE_ALPHA = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  41 */   protected static final DataParameter<Boolean> IS_SPLIT = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187198_h);
/*  42 */   protected static final DataParameter<Integer> UPDATE_RATE = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*  43 */   protected static final DataParameter<Integer> SKIP_SEGMENTS = EntityDataManager.func_187226_a(LightningDischargeEntity.class, DataSerializers.field_187192_b);
/*     */   
/*  45 */   private static final Color DEFAULT_COLOR = WyHelper.hexToRGB("#F0EC7155");
/*     */ 
/*     */   
/*     */   public LightningDischargeEntity(World worldIn) {
/*  49 */     super((EntityType)GoroProjectiles.LIGHTNING_BALL.get(), worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public LightningDischargeEntity(EntityType entityType, World world) {
/*  54 */     super(entityType, world);
/*  55 */     this.field_70158_ak = true;
/*     */   }
/*     */   
/*     */   public LightningDischargeEntity(Entity entity, double posX, double posY, double posZ, float rotationYaw, float rotationPitch) {
/*  59 */     this((EntityType)GoroProjectiles.LIGHTNING_BALL.get(), entity.field_70170_p);
/*     */     
/*  61 */     setOwner(entity.func_110124_au());
/*     */     
/*  63 */     func_70012_b(posX, posY, posZ, rotationYaw, rotationPitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  68 */     return (new Random()).nextLong();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  74 */     super.func_70071_h_();
/*     */     
/*  76 */     if (!getOwnerId().isPresent()) {
/*     */       
/*  78 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/*  82 */     if (getAliveTicks() > 0 && this.field_70173_aa >= getAliveTicks())
/*     */     {
/*  84 */       func_70106_y();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_70112_a(double distance) {
/*  92 */     double d0 = 64.0D * Entity.func_184183_bd();
/*  93 */     return (distance < d0 * d0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  99 */     this.field_70180_af.func_187214_a(OWNER, Optional.empty());
/*     */     
/* 101 */     this.field_70180_af.func_187214_a(SIZE, Float.valueOf(0.2F));
/* 102 */     this.field_70180_af.func_187214_a(ALIVE_TICKS, Integer.valueOf(-1));
/* 103 */     this.field_70180_af.func_187214_a(LIGHTNING_LENGTH, Float.valueOf(1.0F));
/* 104 */     this.field_70180_af.func_187214_a(LIGHTNING_DENSITY, Integer.valueOf(8));
/* 105 */     this.field_70180_af.func_187214_a(LIGHTNING_DETAIL, Integer.valueOf(6));
/* 106 */     this.field_70180_af.func_187214_a(IS_SPLIT, Boolean.valueOf(false));
/* 107 */     this.field_70180_af.func_187214_a(UPDATE_RATE, Integer.valueOf(5));
/* 108 */     this.field_70180_af.func_187214_a(SKIP_SEGMENTS, Integer.valueOf(0));
/*     */     
/* 110 */     this.field_70180_af.func_187214_a(COLOR, Integer.valueOf(DEFAULT_COLOR.getRGB()));
/* 111 */     this.field_70180_af.func_187214_a(ALPHA, Integer.valueOf(77));
/*     */     
/* 113 */     this.field_70180_af.func_187214_a(OUTLINE_COLOR, Integer.valueOf(0));
/* 114 */     this.field_70180_af.func_187214_a(OUTLINE_ALPHA, Integer.valueOf(0));
/*     */     
/* 116 */     this.field_70180_af.func_187214_a(RENDER_MODE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 134 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkipSegments(int skip) {
/* 139 */     this.field_70180_af.func_187227_b(SKIP_SEGMENTS, Integer.valueOf(skip));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(@Nullable UUID uuid) {
/* 144 */     this.field_70180_af.func_187227_b(OWNER, Optional.ofNullable(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUpdateRate(int updateRate) {
/* 149 */     this.field_70180_af.func_187227_b(UPDATE_RATE, Integer.valueOf(updateRate));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSplit() {
/* 154 */     this.field_70180_af.func_187227_b(IS_SPLIT, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRenderTransparent() {
/* 159 */     this.field_70180_af.func_187227_b(RENDER_MODE, Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetails(int detail) {
/* 164 */     this.field_70180_af.func_187227_b(LIGHTNING_DETAIL, Integer.valueOf(detail));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDensity(int density) {
/* 169 */     this.field_70180_af.func_187227_b(LIGHTNING_DENSITY, Integer.valueOf(density));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 174 */     this.field_70180_af.func_187227_b(SIZE, Float.valueOf(size));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 179 */     this.field_70180_af.func_187227_b(COLOR, Integer.valueOf(color.getRGB()));
/* 180 */     this.field_70180_af.func_187227_b(ALPHA, Integer.valueOf(color.getAlpha()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOutlineColor(Color color) {
/* 185 */     this.field_70180_af.func_187227_b(OUTLINE_COLOR, Integer.valueOf(color.getRGB()));
/* 186 */     this.field_70180_af.func_187227_b(OUTLINE_ALPHA, Integer.valueOf(color.getAlpha()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLightningLength(float size) {
/* 191 */     this.field_70180_af.func_187227_b(LIGHTNING_LENGTH, Float.valueOf(size));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSkipSegmnets() {
/* 196 */     return ((Integer)this.field_70180_af.func_187225_a(SKIP_SEGMENTS)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<UUID> getOwnerId() {
/* 201 */     return (Optional<UUID>)this.field_70180_af.func_187225_a(OWNER);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUpdateRate() {
/* 206 */     return ((Integer)this.field_70180_af.func_187225_a(UPDATE_RATE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSplit() {
/* 211 */     return ((Boolean)this.field_70180_af.func_187225_a(IS_SPLIT)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRenderingTransparent() {
/* 216 */     return (((Integer)this.field_70180_af.func_187225_a(RENDER_MODE)).intValue() == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDetails() {
/* 221 */     return ((Integer)this.field_70180_af.func_187225_a(LIGHTNING_DETAIL)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDensity() {
/* 226 */     return ((Integer)this.field_70180_af.func_187225_a(LIGHTNING_DENSITY)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 231 */     return ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/* 236 */     return ((Integer)this.field_70180_af.func_187225_a(COLOR)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOutlineColor() {
/* 241 */     return ((Integer)this.field_70180_af.func_187225_a(OUTLINE_COLOR)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAlpha() {
/* 246 */     return ((Integer)this.field_70180_af.func_187225_a(ALPHA)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOutlineAlpha() {
/* 251 */     return ((Integer)this.field_70180_af.func_187225_a(OUTLINE_ALPHA)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLightningLength() {
/* 256 */     return ((Float)this.field_70180_af.func_187225_a(LIGHTNING_LENGTH)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAliveTicks() {
/* 261 */     return ((Integer)this.field_70180_af.func_187225_a(ALIVE_TICKS)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAliveTicks(int ticks) {
/* 266 */     this.field_70180_af.func_187227_b(ALIVE_TICKS, Integer.valueOf((ticks > 0) ? (ticks + this.field_70173_aa) : ticks));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\LightningDischargeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */