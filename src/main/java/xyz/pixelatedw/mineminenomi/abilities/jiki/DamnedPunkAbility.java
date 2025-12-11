/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SwingTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.VectorHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.DamnedPunkProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DamnedPunkAbility extends Ability {
/*  40 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "damned_punk", new Pair[] {
/*  41 */         (Pair)ImmutablePair.of("Transforms the users arm into a railgun that shoots a projectile with every swing using %s magnetic objects from the users inventory per shoot, dealing massive damage on impact.", new Object[] { AbilityHelper.mentionText(Integer.valueOf(15)) }) });
/*     */   
/*     */   private static final int VALUE_PER_PROJECTILE = 15;
/*     */   private static final int HOLD_TIME = 500;
/*     */   private static final int COOLDOWN = 400;
/*  46 */   public static final AbilityCore<DamnedPunkAbility> INSTANCE = (new AbilityCore.Builder("Damned Punk", AbilityCategory.DEVIL_FRUITS, DamnedPunkAbility::new))
/*  47 */     .addDescriptionLine(DESCRIPTION)
/*  48 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/*  49 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F), ContinuousComponent.getTooltip(500.0F)
/*  50 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  51 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/*  52 */     .build();
/*     */   
/*  54 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  55 */   private final SwingTriggerComponent swingTriggerComponent = (new SwingTriggerComponent((IAbility)this)).addSwingEvent(this::swingEvent);
/*  56 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  57 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PUNK_GIBSON.get(), new MorphInfo[0]);
/*  58 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*     */   
/*     */   public DamnedPunkAbility(AbilityCore<DamnedPunkAbility> core) {
/*  61 */     super(core);
/*     */     
/*  63 */     this.isNew = true;
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.swingTriggerComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.requireMorphComponent, (AbilityComponent)this.morphComponent });
/*     */     
/*  66 */     addCanUseCheck(this::canUse);
/*  67 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  71 */     this.continuousComponent.triggerContinuity(entity, 500.0F);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.DAMNED_PUNK.get());
/*     */     
/*  77 */     PunkGibsonAbility punkGibson = (PunkGibsonAbility)AbilityDataCapability.get(entity).getEquippedAbility(PunkGibsonAbility.INSTANCE);
/*     */     
/*  79 */     if (punkGibson != null && punkGibson.isContinuous()) {
/*  80 */       punkGibson.activateDamnedPunk(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  85 */     this.morphComponent.stopMorph(entity);
/*     */     
/*  87 */     PunkGibsonAbility punkGibson = (PunkGibsonAbility)AbilityDataCapability.get(entity).getEquippedAbility(PunkGibsonAbility.INSTANCE);
/*     */     
/*  89 */     if (punkGibson != null && punkGibson.isContinuous()) {
/*  90 */       punkGibson.activatePunkGibson(entity);
/*     */     }
/*     */     
/*  93 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*     */   }
/*     */   
/*     */   private void swingEvent(LivingEntity entity, IAbility ability) {
/*  97 */     if (!this.continuousComponent.isContinuous()) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*     */     
/* 103 */     if (!JikiHelper.hasEnoughIron(inventory, 15.0F)) {
/* 104 */       entity.func_145747_a((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, Util.field_240973_b_);
/* 105 */       this.continuousComponent.stopContinuity(entity);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 111 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)entity, 64.0D);
/*     */     
/* 113 */     double beamDistance = Math.sqrt(entity.func_70092_e((blockRayTraceResult.func_216347_e()).field_72450_a, (blockRayTraceResult.func_216347_e()).field_72448_b, (blockRayTraceResult.func_216347_e()).field_72449_c));
/*     */     
/* 115 */     float damage = 60.0F;
/* 116 */     float size = 0.1F;
/* 117 */     float length = 50.0F;
/*     */     
/* 119 */     Vector3d pos = VectorHelper.calculateRotationBasedOffsetPosition(entity.func_213303_ch(), entity.field_70761_aq, 0.0D, 1.15D, 0.0D);
/*     */     
/* 121 */     LightningEntity bolt = new LightningEntity((Entity)entity, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, entity.field_70177_z, entity.field_70125_A, length + (float)beamDistance, 7.0F, getCore());
/* 122 */     bolt.hitBlockEvent = (e -> {
/*     */         if (e.getThrower() != null && e.getThrower() instanceof net.minecraft.entity.player.PlayerEntity) {
/*     */           List<ItemStack> components = JikiHelper.getMagneticItemsNeeded(inventory, 15.0F);
/*     */           
/*     */           JikiHelper.dropComponentItems(e.getThrower(), e.func_213303_ch(), components);
/*     */         } 
/*     */       });
/*     */     
/* 130 */     bolt.setBlocksAffectedLimit(1508);
/* 131 */     bolt.setMaxLife(7);
/* 132 */     bolt.setDamage(damage);
/*     */     
/* 134 */     bolt.setExplosion(0, false);
/*     */     
/* 136 */     bolt.setSize(size);
/* 137 */     bolt.setBoxSizeDivision(1.0D);
/* 138 */     bolt.setColor(new Color(11693284));
/* 139 */     bolt.setAngle(100);
/* 140 */     bolt.setTargetTimeToReset(6000);
/* 141 */     bolt.disableExplosionKnockback();
/* 142 */     bolt.setBranches(1);
/* 143 */     bolt.setSegments(1);
/*     */     
/* 145 */     entity.field_70170_p.func_217376_c((Entity)bolt);
/*     */     
/* 147 */     JikiHelper.spawnAttractEffect((Entity)entity);
/*     */   }
/*     */   
/*     */   private DamnedPunkProjectile createProjectile(LivingEntity entity) {
/* 151 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/* 152 */     List<ItemStack> components = JikiHelper.getMagneticItemsNeeded(inventory, 15.0F);
/*     */     
/* 154 */     DamnedPunkProjectile proj = new DamnedPunkProjectile(entity.field_70170_p, entity, components);
/* 155 */     return proj;
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUse(LivingEntity entity, IAbility ability) {
/* 159 */     Ability abl = (Ability)AbilityDataCapability.get(entity).getEquippedAbility(PunkGibsonAbility.INSTANCE);
/*     */     
/* 161 */     if (abl != null && abl.isContinuous()) {
/* 162 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 165 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*     */     
/* 167 */     if (!JikiHelper.hasEnoughIron(inventory, 15.0F)) {
/* 168 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS);
/*     */     }
/*     */     
/* 171 */     return AbilityUseResult.fail(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\DamnedPunkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */