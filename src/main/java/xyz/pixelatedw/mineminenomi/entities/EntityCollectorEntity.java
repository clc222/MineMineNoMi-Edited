/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCollectorEntity
/*     */   extends ThrowableEntity
/*     */ {
/*  26 */   private double collisionSize = 3.0D;
/*  27 */   private List<Entity> targets = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public EntityCollectorEntity(EntityType type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityCollectorEntity(EntityType type, World world, double x, double y, double z) {
/*  36 */     super(type, x, y, z, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityCollectorEntity(World world, LivingEntity thrower, double size) {
/*  41 */     super((EntityType)ModEntities.ENTITY_COLLECTOR.get(), thrower, world);
/*  42 */     this.collisionSize = size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  48 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  50 */       double sizeX = this.collisionSize;
/*  51 */       double sizeY = this.collisionSize;
/*  52 */       double sizeZ = this.collisionSize;
/*     */       
/*  54 */       AxisAlignedBB aabb = (new AxisAlignedBB(func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), func_226277_ct_(), func_226278_cu_(), func_226281_cx_())).func_72314_b(sizeX, sizeY, sizeZ);
/*     */       
/*  56 */       List<Entity> targets = this.field_70170_p.func_217357_a(Entity.class, aabb);
/*  57 */       targets.remove(this);
/*  58 */       targets.remove(func_234616_v_());
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
/*     */       
/*  76 */       targets = (List<Entity>)targets.stream().filter(target -> { if (this.targets.contains(target)) return false;  boolean flag = true; if (func_234616_v_() instanceof LivingEntity) flag = ((LivingEntity)func_234616_v_()).func_70685_l(target);  return (target.func_241845_aY() && target != func_234616_v_() && flag) ? ((target instanceof LivingEntity) ? ((LivingEntity)target).func_70685_l((Entity)this) : true) : true; }).collect(Collectors.toList());
/*  77 */       if (!targets.isEmpty()) {
/*  78 */         this.targets.addAll(targets);
/*     */       }
/*  80 */       Vector3d vec31 = new Vector3d(func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*  81 */       Vector3d vec3 = new Vector3d(func_226277_ct_() + (func_213322_ci()).field_72450_a, func_226278_cu_() + (func_213322_ci()).field_72448_b, func_226281_cx_() + (func_213322_ci()).field_72449_c);
/*  82 */       BlockRayTraceResult blockRayTraceResult = this.field_70170_p.func_217299_a(new RayTraceContext(vec3, vec31, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, (Entity)this));
/*     */       
/*  84 */       if (blockRayTraceResult.func_216346_c() == RayTraceResult.Type.BLOCK) {
/*     */         
/*  86 */         BlockRayTraceResult blockHit = blockRayTraceResult;
/*  87 */         BlockState state = this.field_70170_p.func_180495_p(blockHit.func_216350_a());
/*  88 */         if (state.func_185904_a().func_76220_a()) {
/*     */           
/*  90 */           func_70106_y();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*  96 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Entity> getTargets() {
/* 101 */     return this.targets;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_70185_h() {
/* 107 */     return 1.0E-5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 119 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\EntityCollectorEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */