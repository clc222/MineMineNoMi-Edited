/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkGibsonProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RepelAbility extends Ability {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "repel", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Repels all metallic objects or projectiles near the user.", null), 
/*  40 */         (Pair)ImmutablePair.of("When %s is active this ability will instead shoot the metalic arm forward like a projectile.", new Object[] { PunkGibsonAbility.INSTANCE
/*  41 */           }), (Pair)ImmutablePair.of("Repeling enemies affected by %s into other enemies will deal damage and knock them back.", new Object[] { ModEffects.PUNK_CROSS })
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 100;
/*     */   private static final int HOLD_TIME = 100;
/*     */   private static final int RANGE = 20;
/*     */   private static final int PUNK_CROSS_RANGE = 3;
/*     */   private static final int PUNK_CROSS_DAMAGE = 20;
/*  49 */   public static final AbilityCore<RepelAbility> INSTANCE = (new AbilityCore.Builder("Repel", AbilityCategory.DEVIL_FRUITS, RepelAbility::new))
/*  50 */     .addDescriptionLine(new ITextComponent[] { DESCRIPTION[0]
/*  51 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(100.0F), RangeComponent.getTooltip(20.0F, RangeComponent.RangeType.AOE)
/*  52 */       }).addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/*  53 */       }).addDescriptionLine(new ITextComponent[] { DESCRIPTION[1]
/*  54 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/*  55 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  56 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE
/*  57 */       }).addDescriptionLine(new ITextComponent[] { DESCRIPTION[2]
/*  58 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, DealDamageComponent.getTooltip(20.0F)
/*  59 */       }).build();
/*     */   
/*  61 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  62 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  63 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  64 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*     */   
/*     */   private PunkGibsonAbility punkGibson;
/*  67 */   private HashSet<LivingEntity> punkCrossTargets = new HashSet<>();
/*     */   
/*     */   public RepelAbility(AbilityCore<RepelAbility> core) {
/*  70 */     super(core);
/*     */     
/*  72 */     this.isNew = true;
/*  73 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent });
/*     */     
/*  75 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  79 */     this.punkGibson = (PunkGibsonAbility)AbilityDataCapability.get(entity).getEquippedAbility(PunkGibsonAbility.INSTANCE);
/*  80 */     boolean isGibsonActive = (this.punkGibson != null && this.punkGibson.isContinuous());
/*     */     
/*  82 */     if (isGibsonActive) {
/*  83 */       this.projectileComponent.shoot(entity, 4.0F, 0.5F);
/*  84 */       this.punkGibson.stopItemDrops();
/*  85 */       this.punkGibson.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity(entity));
/*     */     } else {
/*     */       
/*  88 */       this.continuousComponent.triggerContinuity(entity, 100.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  93 */     List<Entity> targets = WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 20.0D, null, new Class[] { Entity.class });
/*  94 */     for (Entity target : targets) {
/*  95 */       boolean flag = false;
/*     */       
/*  97 */       if (target.func_200600_R().func_220341_a((ITag)ModTags.Entities.MAGNETIC)) {
/*  98 */         flag = true;
/*     */       }
/*     */       
/* 101 */       if (target instanceof LivingEntity) {
/* 102 */         if (getIronArmorCoverPercentage((LivingEntity)target) >= 0.5F) {
/* 103 */           flag = true;
/*     */         }
/* 105 */         else if (EntityStatsCapability.get((LivingEntity)target).isCyborg()) {
/* 106 */           flag = true;
/*     */         }
/* 108 */         else if (((LivingEntity)target).func_70644_a((Effect)ModEffects.PUNK_CROSS.get())) {
/* 109 */           flag = true;
/* 110 */           ((EffectInstanceMixin)((LivingEntity)target).func_70660_b((Effect)ModEffects.PUNK_CROSS.get())).setAmplifier(0);
/* 111 */           this.punkCrossTargets.add((LivingEntity)target);
/*     */         } 
/*     */       }
/*     */       
/* 115 */       if (flag) {
/* 116 */         Vector3d dist = target.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b().func_186678_a(2.0D);
/* 117 */         AbilityHelper.setDeltaMovement(target, dist.field_72450_a, dist.field_72448_b, dist.field_72449_c);
/*     */       } 
/*     */       
/* 120 */       if (target instanceof ItemEntity) {
/* 121 */         ItemEntity item = (ItemEntity)target;
/* 122 */         if (item.func_92059_d().func_190926_b() || !item.func_92059_d().func_77973_b().func_206844_a((ITag)ModTags.Items.MAGNETIC)) {
/*     */           continue;
/*     */         }
/* 125 */         Vector3d vec = item.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72441_c(0.0D, -1.0D, 0.0D);
/* 126 */         double speedReduction = 8.0D;
/* 127 */         double speed = 2.0D;
/* 128 */         double xSpeed = Math.min(speed, -vec.field_72450_a / speedReduction);
/* 129 */         double zSpeed = Math.min(speed, -vec.field_72449_c / speedReduction);
/* 130 */         AbilityHelper.setDeltaMovement((Entity)item, -xSpeed, 0.1D, -zSpeed);
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     for (LivingEntity target : this.punkCrossTargets) {
/* 135 */       if (target.func_70089_S() && !target.func_233570_aj_()) {
/* 136 */         List<LivingEntity> nearby = this.rangeComponent.getTargetsInArea(entity, 3.0F);
/* 137 */         nearby.remove(target);
/* 138 */         for (LivingEntity target2 : nearby) {
/* 139 */           if (this.dealDamageComponent.hurtTarget(entity, target2, 20.0F)) {
/* 140 */             Vector3d speed = WyHelper.propulsion(entity, 2.0D, 2.0D);
/* 141 */             AbilityHelper.setDeltaMovement((Entity)target2, speed.field_72450_a, 0.5D, speed.field_72449_c);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 149 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*     */   }
/*     */   
/*     */   private PunkGibsonProjectile createProjectile(LivingEntity entity) {
/* 153 */     if (this.punkGibson != null) {
/* 154 */       PunkGibsonProjectile punkGibsonProjectile = new PunkGibsonProjectile(entity.field_70170_p, entity, this.punkGibson.getMagneticItems());
/* 155 */       return punkGibsonProjectile;
/*     */     } 
/*     */     
/* 158 */     PunkGibsonProjectile proj = new PunkGibsonProjectile(entity.field_70170_p, entity, new ArrayList());
/* 159 */     return proj;
/*     */   }
/*     */   
/*     */   public float getIronArmorCoverPercentage(LivingEntity target) {
/* 163 */     Iterable<ItemStack> iterable = target.func_184193_aE();
/* 164 */     int i = 0;
/* 165 */     int j = 0;
/*     */     
/* 167 */     for (ItemStack itemstack : iterable) {
/* 168 */       if (!itemstack.func_190926_b() && itemstack.func_77973_b().func_206844_a((ITag)ModTags.Items.MAGNETIC)) {
/* 169 */         j++;
/*     */       }
/*     */       
/* 172 */       i++;
/*     */     } 
/*     */     
/* 175 */     return (i > 0) ? (j / i) : 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\RepelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */