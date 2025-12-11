/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ProjectileComponent extends AbilityComponent<IAbility> {
/*  23 */   private static final UUID PROJ_DAMAGE_BONUS_MANAGER_UUID = UUID.fromString("2ab0d674-f9ca-4c11-976a-c8a105332318");
/*  24 */   private static final UUID PROJ_INACCURACY_BONUS_MANAGER_UUID = UUID.fromString("2ad65e4e-5edf-4c95-a826-d1f7bba33c23");
/*     */   
/*  26 */   private static final StringTextComponent PERCENTAGE_SIGN = new StringTextComponent("%"); public static final Predicate<Entity> TARGET_CHECK;
/*     */   static {
/*  28 */     TARGET_CHECK = (target -> 
/*  29 */       !(target == null || !target.func_70089_S() || !(target instanceof LivingEntity)));
/*     */   }
/*     */   private final IProjectileFactory factory;
/*     */   private AbilityProjectileEntity cachedProjectile;
/*     */   private AbilityProjectileEntity projectileShot;
/*     */   private boolean isHitScan;
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine[] getProjectileTooltips() {
/*  37 */     AbilityDescriptionLine.IDescriptionLine[] list = new AbilityDescriptionLine.IDescriptionLine[3];
/*  38 */     list[0] = ((entity, ability) -> new StringTextComponent("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_PROJECTILE.getString() + "§r"));
/*  39 */     list[1] = getDamageFromProjectileTooltip();
/*  40 */     list[2] = getPiercingFromProjectileTooltip();
/*  41 */     return list;
/*     */   }
/*     */   
/*     */   private static AbilityDescriptionLine.IDescriptionLine getDamageFromProjectileTooltip() {
/*  45 */     return (entity, ability) -> {
/*     */         AbilityProjectileEntity proj = ability.getComponent(ModAbilityKeys.PROJECTILE).map(()).orElse(null);
/*     */         if (proj != null && proj.getDamage() > 0.0F) {
/*     */           float bonus = ((Float)ability.getComponent(ModAbilityKeys.PROJECTILE).map(ProjectileComponent::getDamageBonusManager).map(()).orElse(Float.valueOf(0.0F))).floatValue();
/*     */           AbilityStat.AbilityStatType bonusType = (bonus > 0.0F) ? AbilityStat.AbilityStatType.BUFF : ((bonus < 0.0F) ? AbilityStat.AbilityStatType.DEBUFF : AbilityStat.AbilityStatType.NEUTRAL);
/*     */           AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_DAMAGE, proj.getDamage())).withBonus(bonus, bonusType);
/*     */           return statBuilder.build().getStatDescription(2);
/*     */         } 
/*     */         return null;
/*     */       };
/*     */   }
/*     */   
/*     */   private static AbilityDescriptionLine.IDescriptionLine getPiercingFromProjectileTooltip() {
/*  58 */     return (e, a) -> {
/*     */         AbilityProjectileEntity proj = a.getComponent(ModAbilityKeys.PROJECTILE).map(()).orElse(null);
/*     */         if (proj != null && proj.getArmorPiercing() > 0.0F) {
/*     */           AbilityStat.Builder statBuilder = (new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_PIERCING, proj.getArmorPiercing() * 100.0F)).withUnit((ITextComponent)PERCENTAGE_SIGN);
/*     */           return statBuilder.build().getStatDescription(2);
/*     */         } 
/*     */         return null;
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private final PriorityEventPool<IAfterShootEvent> afterShootEvents = new PriorityEventPool();
/*     */   
/*  75 */   private final BonusManager damageBonusManager = new BonusManager(PROJ_DAMAGE_BONUS_MANAGER_UUID);
/*  76 */   private final BonusManager inaccuracyBonusManager = new BonusManager(PROJ_INACCURACY_BONUS_MANAGER_UUID);
/*     */   
/*     */   public <P extends AbilityProjectileEntity> ProjectileComponent(IAbility ability, IProjectileFactory<P> projectileFactory) {
/*  79 */     super(ModAbilityKeys.PROJECTILE, ability);
/*  80 */     this.factory = projectileFactory;
/*  81 */     addBonusManager(this.damageBonusManager);
/*  82 */     addBonusManager(this.inaccuracyBonusManager);
/*     */   }
/*     */   
/*     */   public ProjectileComponent addAfterShootEvent(int priority, IAfterShootEvent event) {
/*  86 */     this.afterShootEvents.addEvent(priority, event);
/*  87 */     return this;
/*     */   }
/*     */   
/*     */   public void shoot(LivingEntity entity) {
/*  91 */     shoot(entity, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void shoot(LivingEntity entity, float speed, float inaccuracy) {
/*  95 */     AbilityProjectileEntity proj = getNewProjectile(entity);
/*     */     
/*  97 */     shoot(proj, entity, speed, inaccuracy);
/*     */   }
/*     */   
/*     */   public void shootWithSpread(LivingEntity entity, float speed, float inaccuracy, int spread) {
/* 101 */     AbilityProjectileEntity proj = getNewProjectile(entity);
/* 102 */     shootWithSpread(proj, entity, speed, inaccuracy, spread);
/*     */   }
/*     */   
/*     */   public void shootWithSpread(AbilityProjectileEntity projectile, LivingEntity entity, float speed, float inaccuracy, int spread) {
/* 106 */     double px = entity.func_226277_ct_() + WyHelper.randomWithRange(-spread, spread) + WyHelper.randomDouble();
/* 107 */     double py = entity.func_226280_cw_() + WyHelper.randomWithRange(0, spread) + WyHelper.randomDouble();
/* 108 */     double pz = entity.func_226281_cx_() + WyHelper.randomWithRange(-spread, spread) + WyHelper.randomDouble();
/* 109 */     projectile.func_70012_b(px, py, pz, 0.0F, 0.0F);
/*     */     
/* 111 */     shoot(projectile, entity, speed, inaccuracy);
/*     */   }
/*     */   
/*     */   public void shoot(AbilityProjectileEntity projectile, LivingEntity entity, float speed, float inaccuracy) {
/* 115 */     ensureIsRegistered();
/*     */     
/* 117 */     if (this.isHitScan) {
/* 118 */       float distance = projectile.getMaxLife() * speed;
/* 119 */       EntityRayTraceResult entityResult = WyHelper.rayTraceEntities((Entity)entity, distance, projectile.func_213311_cf(), TARGET_CHECK);
/*     */       
/* 121 */       if (entityResult.func_216348_a() != null && entityResult.func_216348_a() instanceof LivingEntity) {
/* 122 */         projectile.func_70107_b((entityResult.func_216348_a().func_213303_ch()).field_72450_a, (entityResult.func_216348_a().func_213303_ch()).field_72448_b, (entityResult.func_216348_a().func_213303_ch()).field_72449_c);
/* 123 */         projectile.onModHit((RayTraceResult)entityResult);
/* 124 */       } else if (entityResult.func_216348_a() == null) {
/* 125 */         BlockRayTraceResult blockResult = WyHelper.rayTraceBlocks((Entity)entity, distance);
/* 126 */         projectile.func_70107_b((blockResult.func_216347_e()).field_72450_a, (entityResult.func_216347_e()).field_72448_b, (entityResult.func_216347_e()).field_72449_c);
/* 127 */         projectile.onModHit((RayTraceResult)blockResult);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 132 */       inaccuracy = ItemsHelper.getSniperInaccuracy(inaccuracy, entity);
/*     */ 
/*     */       
/* 135 */       inaccuracy = this.inaccuracyBonusManager.applyBonus(inaccuracy);
/*     */       
/* 137 */       this.projectileShot = projectile;
/* 138 */       entity.field_70170_p.func_217376_c((Entity)projectile);
/* 139 */       projectile.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, speed, inaccuracy);
/*     */     } 
/*     */     
/* 142 */     this.afterShootEvents.dispatch(event -> event.triggerAfterEvent(entity));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AbilityProjectileEntity getShotProjectile() {
/* 147 */     return this.projectileShot;
/*     */   }
/*     */   
/*     */   public boolean hasProjectileAlive() {
/* 151 */     return (this.projectileShot != null && this.projectileShot.func_70089_S());
/*     */   }
/*     */   
/*     */   public <P extends AbilityProjectileEntity> P getNewProjectile(LivingEntity entity) {
/* 155 */     P proj = this.factory.createProjectile(entity);
/* 156 */     proj.setDamage(this.damageBonusManager.applyBonus(proj.getDamage()));
/* 157 */     return proj;
/*     */   }
/*     */   
/*     */   public BonusManager getDamageBonusManager() {
/* 161 */     return this.damageBonusManager;
/*     */   }
/*     */   
/*     */   public BonusManager getInaccuracyBonusManager() {
/* 165 */     return this.inaccuracyBonusManager;
/*     */   }
/*     */   
/*     */   public AbilityProjectileEntity getCachedProjectile(LivingEntity entity) {
/* 169 */     if (this.cachedProjectile == null) {
/* 170 */       this.cachedProjectile = this.factory.createProjectile(entity);
/*     */     }
/* 172 */     return this.cachedProjectile;
/*     */   }
/*     */   
/*     */   public void setHitScan(boolean flag) {
/* 176 */     this.isHitScan = flag;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IAfterShootEvent {
/*     */     void triggerAfterEvent(LivingEntity param1LivingEntity);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IProjectileFactory<P extends AbilityProjectileEntity> {
/*     */     P createProjectile(LivingEntity param1LivingEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\ProjectileComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */