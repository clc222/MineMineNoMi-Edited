/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ 
/*     */ public class BaraFestivalEntity
/*     */   extends Entity {
/*  22 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(BaraFestivalEntity.class, DataSerializers.field_187203_m);
/*     */   
/*     */   private LivingEntity target;
/*     */ 
/*     */   
/*     */   public BaraFestivalEntity(EntityType type, World world) {
/*  28 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public BaraFestivalEntity(World world) {
/*  33 */     super((EntityType)BaraProjectiles.BARA_FESTIVAL.get(), world);
/*  34 */     func_189654_d(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  40 */     this.field_70180_af.func_187214_a(OWNER, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  46 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  48 */       if (getOwnerUUID() == null) {
/*     */         
/*  50 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  54 */       if (getOwner() != null && !getOwner().func_70089_S()) {
/*  55 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  59 */       if (this.target == null || !this.target.func_70089_S()) {
/*     */         
/*  61 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  65 */       func_70634_a(this.target.func_226277_ct_(), this.target.func_226278_cu_(), this.target.func_226281_cx_());
/*     */     } 
/*  67 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT compound) {
/*  73 */     if (((Optional)func_184212_Q().func_187225_a(OWNER)).isPresent()) {
/*  74 */       compound.func_74778_a("OwnerUUID", ((UUID)((Optional<UUID>)func_184212_Q().func_187225_a(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT compound) {
/*  80 */     func_184212_Q().func_187227_b(OWNER, Optional.of(UUID.fromString(compound.func_74779_i("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(LivingEntity target) {
/*  85 */     this.target = target;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(UUID uuid) {
/*  90 */     this.field_70180_af.func_187227_b(OWNER, Optional.of(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/*  96 */     return ((Optional<UUID>)this.field_70180_af.func_187225_a(OWNER)).orElseGet(() -> null);
/*     */   }
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 100 */     return ((Optional)this.field_70180_af.func_187225_a(OWNER)).isPresent() ? this.field_70170_p.func_217371_b(((Optional<UUID>)this.field_70180_af.func_187225_a(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean func_145770_h(double pX, double pY, double pZ) {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 112 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\BaraFestivalEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */