/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ope.RoomAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SpatialSlashProjectile extends AbilityProjectileEntity {
/*    */   public SpatialSlashProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpatialSlashProjectile(World world, LivingEntity player) {
/* 28 */     super((EntityType)OpeProjectiles.SPATIAL_SLASH.get(), world, player);
/* 29 */     setDamageSource((new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, null)).setProjectile());
/* 30 */     setDamage(5.0F);
/* 31 */     setPhysical();
/* 32 */     setMaxLife(20);
/*    */     
/* 34 */     setCanGetStuckInGround();
/* 35 */     this.onBlockImpactEvent = this::onBlockImpact;
/* 36 */     this.onEntityImpactEvent = this::onEntityImpact;
/*    */   }
/*    */   
/*    */   private void onEntityImpact(LivingEntity entity) {
/* 40 */     boolean hakiCondition = (HakiDataCapability.get(entity).getBusoshokuHakiExp() + WyHelper.randomWithRange(0, 5) >= HakiDataCapability.get(getThrower()).getBusoshokuHakiExp());
/* 41 */     boolean specialCondition = (DevilFruitCapability.get(entity).isLogia() && !HakiHelper.hasHardeningActive(entity));
/* 42 */     boolean hpCondition = (getThrower().func_110143_aJ() >= entity.func_110143_aJ() + 10.0F);
/* 43 */     boolean blocking = AbilityHelper.isTargetBlocking(getThrower(), entity);
/*    */     
/* 45 */     if (!hakiCondition && !specialCondition && !hpCondition && !blocking)
/*    */     {
/*    */       
/* 48 */       entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 200, 0, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpact(BlockPos blockPos) {
/* 53 */     this.field_70170_p.func_217377_a(blockPos, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 59 */     super.func_70071_h_();
/* 60 */     if (this.field_70170_p.field_72995_K)
/*    */       return; 
/* 62 */     if (getThrower() != null && this.field_70173_aa > 0 && (getThrower()).field_70173_aa > 0) {
/*    */       
/* 64 */       RoomAbility a = (RoomAbility)AbilityDataCapability.get(getThrower()).getEquippedAbility(RoomAbility.INSTANCE);
/* 65 */       if (a == null) {
/* 66 */         func_70106_y();
/*    */       }
/* 68 */       if (!a.isPositionInRoom(func_233580_cy_()))
/* 69 */         func_70106_y(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\SpatialSlashProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */