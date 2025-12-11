/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.datasync.DataParameter;
/*    */ import net.minecraft.network.datasync.DataSerializers;
/*    */ import net.minecraft.network.datasync.EntityDataManager;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.IFlexibleSizeProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class DragonsRoarProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/* 20 */   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(DragonsRoarProjectile.class, DataSerializers.field_187193_c);
/*    */ 
/*    */   
/*    */   public DragonsRoarProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonsRoarProjectile(World world, LivingEntity player, float size, Ability ability) {
/* 29 */     super((EntityType)GoeProjectiles.DRAGONS_ROAR.get(), world, player, ability);
/*    */     
/* 31 */     setDamage(10.0F);
/* 32 */     setMaxLife(30);
/* 33 */     setPassThroughEntities();
/* 34 */     setArmorPiercing(1.0F);
/* 35 */     setUnavoidable();
/*    */     
/* 37 */     setSize(size);
/* 38 */     setEntityCollisionSize((size / 2.0F), 3.0D, (size / 2.0F));
/*    */     
/* 40 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 45 */     ExplosionAbility explosion = createExplosion((Entity)getThrower(), this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), getSize() / 2.0F);
/* 46 */     explosion.setHeightDifference(45);
/* 47 */     explosion.setStaticBlockResistance(1.35F);
/* 48 */     explosion.setProtectOwnerFromFalling(true);
/* 49 */     explosion.setExplosionSound(true);
/* 50 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)getSize()));
/* 51 */     explosion.setDamageSource((DamageSource)this.bypassingSource);
/* 52 */     explosion.setStaticDamage(0.0F);
/* 53 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70088_a() {
/* 59 */     super.func_70088_a();
/* 60 */     func_184212_Q().func_187214_a(SIZE, Float.valueOf(1.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(float size) {
/* 66 */     func_184212_Q().func_187227_b(SIZE, Float.valueOf(MathHelper.func_76131_a(size, 1.0F, 50.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSize() {
/* 72 */     return ((Float)func_184212_Q().func_187225_a(SIZE)).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goe\DragonsRoarProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */