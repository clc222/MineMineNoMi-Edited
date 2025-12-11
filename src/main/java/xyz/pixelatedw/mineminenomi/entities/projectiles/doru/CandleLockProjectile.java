/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doru.CandleLockAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CandleLockProjectile extends AbilityProjectileEntity {
/*    */   public CandleLockProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandleLockProjectile(World world, LivingEntity player, CandleLockAbility ability) {
/* 31 */     super((EntityType)DoruProjectiles.CANDLE_LOCK.get(), world, player, (Ability)ability);
/*    */     
/* 33 */     setDamage(8.0F);
/* 34 */     setMaxLife(20);
/* 35 */     setPassThroughEntities();
/* 36 */     setPhysical();
/* 37 */     setEntityCollisionSize(3.0D);
/*    */     
/* 39 */     this.withEffects = this::withEffects;
/*    */     
/* 41 */     Ability candleChampion = (Ability)AbilityDataCapability.get(getThrower()).getEquippedAbility(CandleChampionAbility.INSTANCE);
/* 42 */     if (candleChampion != null && candleChampion.isContinuous()) {
/* 43 */       setEntityCollisionSize(5.0D, 3.0D, 5.0D);
/*    */     }
/*    */     
/* 46 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     BlockPos pos = null;
/* 52 */     int j = 1;
/*    */     
/* 54 */     while (pos == null) {
/*    */       
/* 56 */       BlockState state = this.field_70170_p.func_180495_p(func_233580_cy_().func_177979_c(j));
/*    */       
/* 58 */       if (state.func_185904_a().func_76220_a()) {
/*    */         
/* 60 */         pos = func_233580_cy_().func_177979_c(j);
/*    */         
/*    */         break;
/*    */       } 
/* 64 */       if (j > 5) {
/*    */         break;
/*    */       }
/* 67 */       j++;
/*    */     } 
/*    */     
/* 70 */     if (pos == null) {
/*    */       return;
/*    */     }
/* 73 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CANDLE_LOCK.get(), (Entity)this, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */   }
/*    */ 
/*    */   
/*    */   private EffectInstance[] withEffects() {
/* 78 */     if (getThrower() == null) {
/* 79 */       func_70106_y();
/*    */     }
/* 81 */     int time = 200;
/* 82 */     int modifier = 1;
/*    */     
/* 84 */     if (getThrower() instanceof PlayerEntity) {
/*    */       
/* 86 */       if (((PlayerEntity)getThrower()).field_71071_by.func_213902_a((Set)ImmutableSet.of(ModItems.COLOR_PALETTE.get()))) {
/* 87 */         modifier = 2;
/*    */       }
/* 89 */       Ability ability = (Ability)AbilityDataCapability.get(getThrower()).getEquippedAbility(CandleChampionAbility.INSTANCE);
/* 90 */       if (ability != null && ability.isContinuous()) {
/* 91 */         time = 300;
/*    */       }
/*    */     } 
/* 94 */     return new EffectInstance[] { new EffectInstance((Effect)ModEffects.CANDLE_LOCK.get(), time, modifier) };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\CandleLockProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */