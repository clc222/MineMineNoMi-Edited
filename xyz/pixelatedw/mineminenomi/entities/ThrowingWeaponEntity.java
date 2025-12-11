/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileHelper;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.tileentity.EndGatewayTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ 
/*     */ public class ThrowingWeaponEntity extends AbilityProjectileEntity {
/*  30 */   private ItemStack itemStack = ItemStack.field_190927_a;
/*     */   private boolean isStuck;
/*     */   private float zRot;
/*     */   
/*     */   public ThrowingWeaponEntity(EntityType type, World world) {
/*  35 */     super(type, world);
/*     */   }
/*     */   
/*     */   public ThrowingWeaponEntity(World world, LivingEntity thrower) {
/*  39 */     this(world, thrower, thrower.func_184614_ca().func_77946_l());
/*     */   }
/*     */   
/*     */   public ThrowingWeaponEntity(World world, LivingEntity thrower, ItemStack stack) {
/*  43 */     super((EntityType)ModEntities.THROWING_WEAPON.get(), world, thrower, WeaponThrowAbility.INSTANCE);
/*  44 */     this.itemStack = stack;
/*  45 */     float damage = ItemsHelper.getItemDamage(this.itemStack);
/*  46 */     if (damage < 10.0F) {
/*  47 */       damage = 10.0F;
/*     */     }
/*  49 */     setDamage(damage);
/*  50 */     setMaxLife(1200);
/*  51 */     setAffectedByImbuing();
/*  52 */     setCanGetStuckInGround();
/*  53 */     setPassThroughEntities();
/*  54 */     setEntityCollisionSize(1.5D, 1.0D, 1.5D);
/*     */     
/*  56 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  63 */     RayTraceResult raytraceresult = ProjectileHelper.func_234618_a_((Entity)this, this::func_230298_a_);
/*  64 */     boolean flag = false;
/*  65 */     if (raytraceresult.func_216346_c() == RayTraceResult.Type.BLOCK) {
/*  66 */       BlockPos blockPos = ((BlockRayTraceResult)raytraceresult).func_216350_a();
/*  67 */       BlockState blockState = this.field_70170_p.func_180495_p(blockPos);
/*  68 */       if (blockState.func_203425_a(Blocks.field_150427_aO)) {
/*  69 */         func_181015_d(blockPos);
/*  70 */         flag = true;
/*     */       }
/*  72 */       else if (blockState.func_203425_a(Blocks.field_185775_db)) {
/*  73 */         TileEntity tileentity = this.field_70170_p.func_175625_s(blockPos);
/*  74 */         if (tileentity instanceof EndGatewayTileEntity && EndGatewayTileEntity.func_242690_a((Entity)this)) {
/*  75 */           ((EndGatewayTileEntity)tileentity).func_195496_a((Entity)this);
/*     */         }
/*     */         
/*  78 */         flag = true;
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     if (raytraceresult.func_216346_c() == RayTraceResult.Type.ENTITY) {
/*  83 */       func_70107_b(func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*  84 */       AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/*  87 */     if (raytraceresult.func_216346_c() != RayTraceResult.Type.MISS && !flag && !ForgeEventFactory.onProjectileImpact((ThrowableEntity)this, raytraceresult)) {
/*  88 */       onModHit(raytraceresult);
/*     */     }
/*     */     
/*  91 */     BlockPos blockpos = func_233580_cy_();
/*  92 */     BlockState blockstate = this.field_70170_p.func_180495_p(blockpos);
/*  93 */     if (!blockstate.isAir((IBlockReader)this.field_70170_p, blockpos)) {
/*  94 */       VoxelShape voxelshape = blockstate.func_196952_d((IBlockReader)this.field_70170_p, blockpos);
/*  95 */       if (!voxelshape.func_197766_b()) {
/*  96 */         Vector3d vector3d1 = func_213303_ch();
/*     */         
/*  98 */         for (AxisAlignedBB axisalignedbb : voxelshape.func_197756_d()) {
/*  99 */           if (axisalignedbb.func_186670_a(blockpos).func_72318_a(vector3d1)) {
/* 100 */             func_70107_b(func_226277_ct_(), func_226278_cu_() + 1.0D, func_226281_cx_());
/* 101 */             this.isStuck = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 108 */     if (!this.isStuck) {
/* 109 */       this.zRot = (this.field_70173_aa * 50 % 360);
/*     */       
/* 111 */       Vector3d motion = func_213322_ci();
/* 112 */       double motionX = motion.field_72450_a;
/* 113 */       double motionY = motion.field_72448_b;
/* 114 */       double motionZ = motion.field_72449_c;
/* 115 */       float horDist = MathHelper.func_76133_a(func_213296_b(motion));
/*     */       
/* 117 */       double newPosX = func_226277_ct_() + motionX;
/* 118 */       double newPosY = func_226278_cu_() + motionY;
/* 119 */       double newPosZ = func_226281_cx_() + motionZ;
/*     */       
/* 121 */       this.field_70177_z = (float)(MathHelper.func_181159_b(motionX, motionZ) * 57.2957763671875D);
/* 122 */       this.field_70125_A = (float)(MathHelper.func_181159_b(motionY, horDist) * 57.2957763671875D);
/* 123 */       this.field_70125_A = func_234614_e_(this.field_70127_C, this.field_70125_A);
/* 124 */       this.field_70177_z = func_234614_e_(this.field_70126_B, this.field_70177_z);
/*     */       
/* 126 */       AbilityHelper.setDeltaMovement((Entity)this, motion.func_186678_a(0.9900000095367432D));
/* 127 */       motion = func_213322_ci();
/* 128 */       AbilityHelper.setDeltaMovement((Entity)this, motion.field_72450_a, motion.field_72448_b - func_70185_h(), motion.field_72449_c);
/*     */       
/* 130 */       func_70107_b(newPosX, newPosY, newPosZ);
/*     */     } else {
/*     */       
/* 133 */       tickDespawn();
/*     */       
/* 135 */       this.zRot = -170.0F;
/*     */       
/* 137 */       if (!this.field_70170_p.field_72995_K) {
/* 138 */         boolean shouldFall = this.field_70170_p.func_226664_a_((new AxisAlignedBB(func_213303_ch(), func_213303_ch())).func_186662_g(0.5D));
/* 139 */         if (shouldFall) {
/* 140 */           this.isStuck = false;
/* 141 */           Vector3d vector3d = func_213322_ci();
/* 142 */           AbilityHelper.setDeltaMovement((Entity)this, vector3d.func_216372_d((this.field_70146_Z.nextFloat() * 0.2F), (this.field_70146_Z.nextFloat() * 0.2F), (this.field_70146_Z.nextFloat() * 0.2F)));
/*     */         } 
/*     */       } 
/*     */       
/* 146 */       if (!this.field_70170_p.field_72995_K) {
/* 147 */         AxisAlignedBB aabb = (new AxisAlignedBB(func_233580_cy_())).func_72314_b(1.5D, 1.5D, 1.5D);
/* 148 */         List<LivingEntity> nearbyLivings = new ArrayList<>();
/* 149 */         nearbyLivings.addAll(this.field_70170_p.func_175647_a(LivingEntity.class, aabb, null));
/*     */         
/* 151 */         for (LivingEntity living : nearbyLivings) {
/* 152 */           boolean isOwner = (func_234616_v_() != null && func_234616_v_().func_110124_au() == living.func_110124_au());
/* 153 */           if (isOwner && ItemsHelper.giveItemStackToEntity(living, this.itemStack, EquipmentSlotType.MAINHAND)) {
/* 154 */             func_70106_y();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/* 162 */     this.isStuck = true;
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/* 166 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70185_h() {
/* 171 */     return 0.05F;
/*     */   }
/*     */   
/*     */   public float getRotation() {
/* 175 */     return this.zRot;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 180 */     super.writeSpawnData(buffer);
/* 181 */     buffer.func_150788_a(this.itemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 186 */     super.readSpawnData(buffer);
/* 187 */     this.itemStack = buffer.func_150791_c();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\ThrowingWeaponEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */