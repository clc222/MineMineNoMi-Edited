/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WeatherCloudEntity
/*     */   extends EntityCloud {
/*     */   private static final int PARTICLE_RATE = 5;
/*     */   private static final int TICK_RATE = 50;
/*  22 */   private List<WeatherBallProjectile> weatherBalls = new ArrayList<>();
/*     */   private boolean charged = false;
/*     */   private boolean superCharged = false;
/*  25 */   private int particleTick = 5;
/*  26 */   private int updateTick = 50;
/*     */   
/*     */   private IAbilityData props;
/*     */ 
/*     */   
/*     */   public WeatherCloudEntity(World world) {
/*  32 */     super(world);
/*  33 */     setLife(200);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  39 */     super.func_70071_h_();
/*     */     
/*  41 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  43 */       if (--this.particleTick <= 0) {
/*  44 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.WEATHER_CLOUD.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*  45 */         if (this.charged || this.superCharged) {
/*  46 */           WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHARGED_WEATHER_CLOUD.get(), (Entity)this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*     */         }
/*  48 */         this.particleTick = 5;
/*     */       } 
/*     */       
/*  51 */       if (--this.updateTick > 0) {
/*     */         return;
/*     */       }
/*     */       
/*  55 */       this.updateTick = 50;
/*     */       
/*  57 */       if (this.props == null) {
/*  58 */         this.props = AbilityDataCapability.get(getThrower());
/*     */       }
/*     */       
/*  61 */       if (getLife() <= 0 || getThrower() == null) {
/*  62 */         func_70106_y();
/*     */       }
/*     */       
/*  65 */       setLife(getLife() - 1);
/*     */       
/*  67 */       List<ThunderBallProjectile> thunderBallsList = (List<ThunderBallProjectile>)this.weatherBalls.stream().filter(ThunderBallProjectile.class::isInstance).map(ThunderBallProjectile.class::cast).collect(Collectors.toList());
/*  68 */       List<CoolBallProjectile> coolBallsList = (List<CoolBallProjectile>)this.weatherBalls.stream().filter(CoolBallProjectile.class::isInstance).map(CoolBallProjectile.class::cast).collect(Collectors.toList());
/*     */       
/*  70 */       int thunderBalls = thunderBallsList.size();
/*  71 */       int coolBalls = coolBallsList.size();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  79 */       ThunderstormTempo thunderstormTempo = (ThunderstormTempo)this.props.getPassiveAbility(ThunderstormTempo.INSTANCE);
/*  80 */       boolean canUseThunderstorm = (thunderstormTempo != null && thunderstormTempo.canUse(getThrower()).isSuccess() && thunderBalls >= 5);
/*  81 */       if (canUseThunderstorm) {
/*  82 */         this.superCharged = true;
/*  83 */         thunderstormTempo.useTempo(getThrower(), this);
/*     */         
/*     */         return;
/*     */       } 
/*  87 */       ThunderboltTempo thunderboltTempo = (ThunderboltTempo)this.props.getPassiveAbility(ThunderboltTempo.INSTANCE);
/*  88 */       boolean canUseThunderbolt = (thunderboltTempo != null && thunderboltTempo.canUse(getThrower()).isSuccess() && thunderBalls > 0);
/*     */       
/*  90 */       if (canUseThunderbolt) {
/*  91 */         this.charged = true;
/*  92 */         thunderboltTempo.useTempo(getThrower(), this);
/*     */         
/*     */         return;
/*     */       } 
/*  96 */       RainTempo rainTempo = (RainTempo)this.props.getEquippedOrPassiveAbility(RainTempo.INSTANCE);
/*  97 */       boolean canUseRain = (rainTempo != null && rainTempo.canUse(getThrower()).isSuccess() && coolBalls >= 3);
/*     */       
/*  99 */       if (canUseRain) {
/* 100 */         rainTempo.use(getThrower());
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCharged() {
/* 203 */     return this.charged;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSuperCharged() {
/* 208 */     return this.superCharged;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addWeatherBall(WeatherBallProjectile ball) {
/* 213 */     this.weatherBalls.add(ball);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\WeatherCloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */