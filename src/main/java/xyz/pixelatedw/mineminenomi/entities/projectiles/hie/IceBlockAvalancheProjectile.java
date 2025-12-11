/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.datasync.DataSerializers;
/*    */ import net.minecraft.network.datasync.EntityDataManager;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class IceBlockAvalancheProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/* 18 */   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(IceBlockAvalancheProjectile.class, DataSerializers.field_187193_c);
/*    */   
/*    */   public boolean finalized = false;
/*    */   
/*    */   public IceBlockAvalancheProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */   
/*    */   public IceBlockAvalancheProjectile(World world, LivingEntity player) {
/* 27 */     super((EntityType)HieProjectiles.ICE_BLOCK_AVALANCHE.get(), world, player, IceBlockAvalancheAbility.INSTANCE);
/* 28 */     setDamage(50.0F);
/* 29 */     setMaxLife(150);
/* 30 */     setPassThroughEntities();
/* 31 */     setCanGetStuckInGround();
/* 32 */     setPhysical();
/*    */     
/* 34 */     this.onTickEvent = this::onTickEvent;
/* 35 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 39 */     float mult = getSize() / 6.0F;
/* 40 */     func_174826_a(func_174813_aQ().func_186662_g(mult));
/* 41 */     setEntityCollisionSize(mult);
/*    */     
/* 43 */     if (!this.finalized) {
/* 44 */       setSize(getSize() + 0.4F);
/*    */     }
/*    */     
/* 47 */     if (this.field_70170_p.func_180495_p(func_233580_cy_().func_177977_b()).func_185904_a().func_76220_a()) {
/* 48 */       AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onBlockImpactEvent(BlockPos pos) {
/* 53 */     if (!this.field_70170_p.field_72995_K) {
/* 54 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ICE_BLOCK_AVALANCHE.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70088_a() {
/* 61 */     super.func_70088_a();
/* 62 */     this.field_70180_af.func_187214_a(SIZE, Float.valueOf(1.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(float size) {
/* 68 */     func_184212_Q().func_187227_b(SIZE, Float.valueOf(Math.min(size, 50.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSize() {
/* 74 */     return ((Float)this.field_70180_af.func_187225_a(SIZE)).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBlockAvalancheProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */