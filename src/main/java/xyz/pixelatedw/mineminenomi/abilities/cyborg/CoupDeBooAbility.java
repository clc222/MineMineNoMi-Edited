/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateColaAmountPacket;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CoupDeBooAbility extends Ability {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "coup_de_boo", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Launches the user into the sky.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/*    */   private static final int COLA_REQUIRED = 20;
/*    */   private static final int RANGE = 5;
/* 39 */   public static final AbilityCore<CoupDeBooAbility> INSTANCE = (new AbilityCore.Builder("Coup De Boo", AbilityCategory.RACIAL, CoupDeBooAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), CyborgHelper.getColaTooltip(20.0F), RangeComponent.getTooltip(5.0F, RangeComponent.RangeType.AOE)
/* 42 */       }).setUnlockCheck(CoupDeBooAbility::canUnlock)
/* 43 */     .build();
/*    */   
/* 45 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/* 46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   private boolean hasFallDamage = true;
/*    */   
/*    */   public CoupDeBooAbility(AbilityCore<CoupDeBooAbility> core) {
/* 51 */     super(core);
/*    */     
/* 53 */     this.isNew = true;
/* 54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 56 */     addCanUseCheck(CyborgHelper.hasEnoughCola(20));
/* 57 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 61 */     this.hasFallDamage = false;
/*    */     
/* 63 */     IEntityStats props = EntityStatsCapability.get(entity);
/*    */     
/* 65 */     Vector3d speed = WyHelper.propulsion(entity, 2.0D, 1.5D, 2.0D);
/* 66 */     AbilityHelper.setDeltaMovement((Entity)entity, speed.field_72450_a, speed.field_72448_b + 3.5D, speed.field_72449_c);
/*    */     
/* 68 */     props.alterCola(-20);
/* 69 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 70 */       WyNetwork.sendTo(new SUpdateColaAmountPacket(entity), (PlayerEntity)entity);
/* 71 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.COUP_DE_BOO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     } 
/*    */     
/* 74 */     for (LivingEntity target : this.rangeComponent.getTargetsInArea(entity, 5.0F)) {
/* 75 */       target.func_195064_c(new EffectInstance(Effects.field_76436_u, 200, 1));
/*    */     }
/*    */     
/* 78 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 82 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 83 */       this.hasFallDamage = true;
/* 84 */       return 0.0F;
/*    */     } 
/*    */     
/* 87 */     return damage;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 91 */     IEntityStats props = EntityStatsCapability.get(user);
/* 92 */     return props.isCyborg();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\CoupDeBooAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */