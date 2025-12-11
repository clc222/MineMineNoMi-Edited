/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.brawler.KingPunchProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.brawler.KingPunchChargeParticleEffect;
/*     */ 
/*     */ public class KingPunchAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "king_punch", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("A punch of exceptionally concentrated strength, capable of releasing an immense amount of air pressure, but needs a long time to fully charge", null)
/*     */       });
/*  37 */   private static final KingPunchChargeParticleEffect PARTICLES = new KingPunchChargeParticleEffect();
/*     */   
/*     */   private static final int CHARGE_TIME = 1000;
/*     */   
/*     */   private static final float MIN_COOLDOWN = 400.0F;
/*     */   private static final float MAX_COOLDOWN = 1400.0F;
/*     */   private static final float MIN_DAMAGE = 33.333332F;
/*     */   private static final float MAX_DAMAGE = 83.333336F;
/*  45 */   public static final AbilityCore<KingPunchAbility> INSTANCE = (new AbilityCore.Builder("King Punch", AbilityCategory.STYLE, KingPunchAbility::new))
/*  46 */     .addDescriptionLine(DESCRIPTION)
/*  47 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F, 1400.0F), ChargeComponent.getTooltip(1000.0F), DealDamageComponent.getTooltip(33.333332F, 83.333336F)
/*  48 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  49 */     .setUnlockCheck(KingPunchAbility::canUnlock)
/*  50 */     .build();
/*     */   
/*     */   private final ChargeComponent chargeComponent;
/*     */   
/*     */   private final DealDamageComponent dealDamageComponent;
/*     */   private boolean cancelled;
/*     */   
/*     */   public KingPunchAbility(AbilityCore<KingPunchAbility> core) {
/*  58 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() > 0.2F))).addStartEvent(100, this::startChargingEvent).addTickEvent(100, this::duringChargingEvent).addEndEvent(100, this::endChargingEvent); this.dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */     this.cancelled = false;
/*  60 */     this.isNew = true;
/*  61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  63 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/*  64 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.chargeComponent.startCharging(entity, 1000.0F);
/*     */   }
/*     */   
/*     */   private void startChargingEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.cancelled = false;
/*     */   }
/*     */   
/*     */   private void duringChargingEvent(LivingEntity entity, IAbility ability) {
/*  76 */     if (entity.field_70172_ad > 0) {
/*  77 */       this.cancelled = true;
/*  78 */       this.chargeComponent.stopCharging(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  82 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1));
/*     */     
/*  84 */     if (this.chargeComponent.getChargeTime() % 2.0F == 0.0F) {
/*  85 */       PARTICLES.spawn(entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endChargingEvent(LivingEntity entity, IAbility ability) {
/*  90 */     if (this.cancelled) {
/*     */       return;
/*     */     }
/*     */     
/*  94 */     if (!entity.field_70170_p.field_72995_K) {
/*  95 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */     }
/*     */     
/*  98 */     float chargeTime = this.chargeComponent.getChargeTime();
/*  99 */     KingPunchProjectile kingPunchProjectile = new KingPunchProjectile(entity.field_70170_p, entity, this);
/* 100 */     kingPunchProjectile.setDamage(chargeTime / 12.0F);
/* 101 */     ((AbilityProjectileEntity)kingPunchProjectile).onBlockImpactEvent = (pos -> {
/*     */         ExplosionAbility explosion = proj.createExplosion((Entity)entity, entity.field_70170_p, proj.func_226277_ct_(), proj.func_226278_cu_(), proj.func_226281_cx_(), 1.0F + chargeTime / 70.0F);
/*     */         
/*     */         explosion.setStaticDamage(0.0F);
/*     */         explosion.setExplosionSound(false);
/*     */         explosion.setDamageOwner(false);
/*     */         explosion.setDestroyBlocks(true);
/*     */         explosion.setFireAfterExplosion(false);
/*     */         explosion.setDamageEntities(false);
/*     */         explosion.doExplosion();
/*     */       });
/* 112 */     entity.field_70170_p.func_217376_c((Entity)kingPunchProjectile);
/* 113 */     kingPunchProjectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/* 114 */     entity.func_195063_d((Effect)ModEffects.MOVEMENT_BLOCKED.get());
/*     */     
/* 116 */     this.cooldownComponent.startCooldown(entity, chargeTime + 400.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 120 */     if (!(entity instanceof PlayerEntity)) {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     PlayerEntity player = (PlayerEntity)entity;
/* 125 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 126 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 128 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_06));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\KingPunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */