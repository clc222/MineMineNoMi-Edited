/*     */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.ArtOfWeatherHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ThunderLanceTempo extends ChargedTempoAbility {
/*  45 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "thunder_lance_tempo", new Pair[] {
/*  46 */         (Pair)ImmutablePair.of("Creates a lighting bolt that goes directly to the area the user is pointing at, exploding on impact and hurting entities in its path", null) }); public static final AbilityDescriptionLine.IDescriptionLine CUSTOM_CHARGE_TIME;
/*     */   static {
/*  48 */     CUSTOM_CHARGE_TIME = ((entity, ability) -> {
/*     */         if (!ItemsHelper.isClimaTact(entity.func_184614_ca())) {
/*     */           return null;
/*     */         }
/*     */         ClimaTactItem climaTact = (ClimaTactItem)entity.func_184614_ca().func_77973_b();
/*     */         int chargeTime = (4 - climaTact.getLevel()) * 4 * 20;
/*     */         return ChargeComponent.getTooltip(chargeTime).expand(entity, ability);
/*     */       });
/*     */   }
/*     */ 
/*     */   
/*     */   private static final float DAMAGE = 60.0F;
/*     */   
/*     */   private static final float MAX_DISTANCE = 80.0F;
/*  62 */   public static final AbilityCore<ThunderLanceTempo> INSTANCE = (new AbilityCore.Builder("Thunder Lance Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, ThunderLanceTempo::new))
/*  63 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CHARGED_TEMPO_DESCRIPTION
/*  64 */       }).addDescriptionLine(DESCRIPTION)
/*  65 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CUSTOM_CHARGE_TIME, DealDamageComponent.getTooltip(60.0F)
/*  66 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  67 */     .setUnlockCheck(ThunderLanceTempo::canUnlock)
/*  68 */     .build();
/*     */   
/*  70 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addTickEvent(100, this::duringChargeEvent).addEndEvent(100, this::endChargeEvent);
/*  71 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   private Vector3d pos;
/*     */   
/*     */   public ThunderLanceTempo(AbilityCore<ThunderLanceTempo> core) {
/*  76 */     super(core);
/*     */     
/*  78 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent });
/*     */     
/*  80 */     addCanUseCheck(ArtOfWeatherHelper::needsClimaTact);
/*     */   }
/*     */ 
/*     */   
/*     */   public WeatherBallKind[] getTempoOrder() {
/*  85 */     return new WeatherBallKind[] { WeatherBallKind.THUNDER, WeatherBallKind.THUNDER, WeatherBallKind.THUNDER };
/*     */   }
/*     */ 
/*     */   
/*     */   public void useTempo(LivingEntity entity) {
/*  90 */     if (entity.func_184614_ca().func_190926_b() || this.chargeComponent.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/*  94 */     ClimaTactItem climaTact = (ClimaTactItem)entity.func_184614_ca().func_77973_b();
/*  95 */     int chargeTime = (4 - climaTact.getLevel()) * 4 * 20;
/*     */     
/*  97 */     this.chargeComponent.startCharging(entity, chargeTime);
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 101 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 80.0D);
/*     */ 
/*     */     
/* 104 */     this.pos = blockRayTraceResult.func_216347_e();
/*     */     
/* 106 */     double i = this.pos.field_72450_a;
/* 107 */     double j = this.pos.field_72448_b;
/* 108 */     double k = this.pos.field_72449_c;
/*     */     
/* 110 */     if (entity.field_70173_aa % 2 == 0) {
/* 111 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.THUNDER_LANCE.get(), (Entity)entity, i, j, k);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 117 */     if (!entity.field_70170_p.field_72995_K) {
/* 118 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */     }
/* 120 */     int beamLength = 2;
/* 121 */     LightningEntity bolt = new LightningEntity((Entity)entity, (beamLength + 100), 6.0F, INSTANCE);
/* 122 */     bolt.setMaxLife(10);
/* 123 */     bolt.setTargetTimeToReset(20);
/* 124 */     bolt.setDamage(0.0F);
/* 125 */     bolt.setLightningMimic(false);
/* 126 */     bolt.disableExplosionKnockback();
/* 127 */     bolt.setColor(new Color(253, 208, 35, 205));
/* 128 */     bolt.setExplosion(1, false, 1.0F);
/* 129 */     bolt.setBoxSizeDivision(0.22499999403953552D);
/* 130 */     bolt.setAngle(20);
/* 131 */     bolt.setBranches(2);
/* 132 */     int segments = 5;
/* 133 */     bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 2, segments / 2)));
/* 134 */     entity.field_70170_p.func_217376_c((Entity)bolt);
/*     */     
/* 136 */     List<Entity> hurt = new ArrayList<>(); float f;
/* 137 */     for (f = 0.0F; f < 1.0F; f = (float)(f + 0.13D)) {
/* 138 */       double x = MathHelper.func_219803_d(f, entity.func_213303_ch().func_82615_a(), this.pos.func_82615_a());
/* 139 */       double y = MathHelper.func_219803_d(f, entity.func_213303_ch().func_82617_b(), this.pos.func_82617_b());
/* 140 */       double z = MathHelper.func_219803_d(f, entity.func_213303_ch().func_82616_c(), this.pos.func_82616_c());
/*     */       
/* 142 */       Vector3d pos = new Vector3d(x, y, z);
/* 143 */       List<LivingEntity> targets = WyHelper.getNearbyLiving(pos, (IWorld)entity.field_70170_p, 3.0D, ModEntityPredicates.getEnemyFactions(entity));
/* 144 */       for (LivingEntity target : targets) {
/* 145 */         if (entity.func_70685_l((Entity)target) && !hurt.contains(target)) {
/* 146 */           ModDamageSource source = (ModDamageSource)this.dealDamageComponent.getDamageSource(entity);
/* 147 */           source.bypassLogia().setPiercing(0.75F);
/* 148 */           this.dealDamageComponent.hurtTarget(entity, target, 60.0F, (DamageSource)source);
/* 149 */           hurt.add(target);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 156 */     if (!(entity instanceof PlayerEntity)) {
/* 157 */       return false;
/*     */     }
/*     */     
/* 160 */     PlayerEntity player = (PlayerEntity)entity;
/* 161 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 162 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 164 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_03));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ThunderLanceTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */