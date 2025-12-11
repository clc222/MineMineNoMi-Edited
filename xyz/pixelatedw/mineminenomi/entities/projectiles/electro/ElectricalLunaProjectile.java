/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.electro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalLunaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElectricalLunaProjectile extends AbilityProjectileEntity {
/*    */   public ElectricalLunaProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */   private ElectricalLunaAbility ability;
/*    */   public ElectricalLunaProjectile(World world, LivingEntity player, ElectricalLunaAbility ability) {
/* 26 */     super((EntityType)ElectroProjectiles.ELECTRICAL_LUNA.get(), world, player, (Ability)ability);
/*    */     
/* 28 */     this.ability = ability;
/*    */     
/* 30 */     setMaxLife(20);
/* 31 */     setPassThroughEntities();
/*    */     
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */     
/* 35 */     this.onBlockImpactEvent = this::onHitBlock;
/*    */   }
/*    */   
/*    */   private void onHitBlock(BlockPos pos) {
/* 39 */     this.ability.getComponent(ModAbilityKeys.RANGE).ifPresent(comp -> {
/*    */           int range = 6;
/*    */           List<LivingEntity> targets = comp.getTargetsInArea(getThrower(), range);
/*    */           for (LivingEntity target : targets) {
/*    */             target.func_195064_c(new EffectInstance((Effect)ModEffects.PARALYSIS.get(), 30, 0));
/*    */             int amount = 16;
/*    */             for (int j = 0; j < amount; j++) {
/*    */               float boltSize = (float)WyHelper.randomWithRange(3, range);
/*    */               LightningEntity bolt = new LightningEntity((Entity)this, target.func_233580_cy_().func_177958_n(), target.func_233580_cy_().func_177956_o() + 0.25D, target.func_233580_cy_().func_177952_p(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(0, 5), boltSize, 8.0F, this.ability.getCore());
/*    */               bolt.setAngle(60);
/*    */               bolt.setMaxLife(20);
/*    */               bolt.setDamage(0.0F);
/*    */               bolt.setExplosion(0, false);
/*    */               bolt.setSize(boltSize / 600.0F);
/*    */               bolt.setBranches((int)WyHelper.randomWithRange(1, 3));
/*    */               bolt.setSegments((int)(boltSize * 0.6D));
/*    */               bolt.setLightningMimic(false);
/*    */               this.field_70170_p.func_217376_c((Entity)bolt);
/*    */             } 
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 66 */     if (this.field_70173_aa < 0) {
/*    */       return;
/*    */     }
/*    */     
/* 70 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.ELECTRICAL_LUNA.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\electro\ElectricalLunaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */