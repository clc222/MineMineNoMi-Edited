/*    */ package xyz.pixelatedw.mineminenomi.abilities.horu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DeathWinkAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "death_wink", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("The user winks really hard creating a shockwave; Ganmen Seicho boosts it's power.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 120;
/* 34 */   public static final AbilityCore<DeathWinkAbility> INSTANCE = (new AbilityCore.Builder("Death Wink", AbilityCategory.DEVIL_FRUITS, DeathWinkAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F)
/* 37 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 38 */     .build();
/*    */   
/*    */   public DeathWinkAbility(AbilityCore<DeathWinkAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/*    */     
/* 45 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 49 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DEATH_WINK_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 50 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)entity, 1.0D);
/* 51 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HORU_WINK.get(), (Entity)entity, trace.func_216347_e().func_82615_a(), entity.func_226278_cu_(), trace.func_216347_e().func_82616_c());
/*    */     
/* 53 */     int power = entity.func_70644_a((Effect)ModEffects.GANMEN_SEICHO_HORMONE.get()) ? 3 : 2;
/* 54 */     boolean createExplosion = true;
/*    */     
/* 56 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, (8 * power));
/*    */     
/* 58 */     double x = (mop.func_216347_e()).field_72450_a;
/* 59 */     double y = (mop.func_216347_e()).field_72448_b;
/* 60 */     double z = (mop.func_216347_e()).field_72449_c;
/*    */     
/* 62 */     if (mop instanceof EntityRayTraceResult) {
/* 63 */       Entity e = ((EntityRayTraceResult)mop).func_216348_a();
/* 64 */       if (e instanceof AbilityProjectileEntity && (
/* 65 */         (AbilityProjectileEntity)e).getDamage() < (power * 5)) {
/* 66 */         createExplosion = false;
/* 67 */         AbilityHelper.setDeltaMovement(e, -(e.func_213322_ci()).field_72450_a, (e.func_213322_ci()).field_72448_b, -(e.func_213322_ci()).field_72450_a);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 72 */     if (createExplosion) {
/* 73 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, x, y, z, (1 + power));
/* 74 */       explosion.setStaticDamage((power * 10));
/* 75 */       explosion.setExplosionSound(false);
/* 76 */       explosion.setSmokeParticles((ParticleEffect)new WinkExplosionEffect(2));
/* 77 */       explosion.doExplosion();
/*    */       
/* 79 */       double distance = Math.sqrt(entity.func_70092_e(x, y, z));
/* 80 */       if (distance < 0.5D) {
/* 81 */         AbilityHelper.setDeltaMovement((Entity)entity, WyHelper.randomWithRange(-1, 1) * 0.5D * power, 1.5D * power, WyHelper.randomWithRange(-1, 1) * 0.5D * power);
/*    */       }
/*    */     } 
/*    */     
/* 85 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\horu\DeathWinkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */