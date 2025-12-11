/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sabi.RustSkinAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkCrossProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class PunkCrossAbility extends Ability {
/*  36 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "punk_cross", new Pair[] {
/*  37 */         (Pair)ImmutablePair.of("Uses %s magnetic items and stuns the target in a cross-shaped structure.", new Object[] { AbilityHelper.mentionText(Integer.valueOf(50)) }) });
/*     */   
/*     */   private static final int REQUIRED_IRON = 50;
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int EFFECT_TIMER = 80;
/*  42 */   public static final AbilityCore<PunkCrossAbility> INSTANCE = (new AbilityCore.Builder("Punk Cross", AbilityCategory.DEVIL_FRUITS, PunkCrossAbility::new))
/*  43 */     .addDescriptionLine(DESCRIPTION)
/*  44 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(80.0F)
/*  45 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  46 */     .setSourceElement(SourceElement.METAL)
/*  47 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  48 */       }).build();
/*     */   
/*  50 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  51 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   private LivingEntity target;
/*     */   private boolean hasTarget;
/*     */   private Vector3d lastKnownTargetPos;
/*     */   private Vector3d lastKnownProjPos;
/*  57 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */   
/*     */   public PunkCrossAbility(AbilityCore<PunkCrossAbility> core) {
/*  60 */     super(core);
/*     */     
/*  62 */     this.isNew = true;
/*  63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  65 */     addCanUseCheck(JikiHelper.getMetalicItemsCheck(50));
/*     */     
/*  67 */     addUseEvent(this::useEvent);
/*  68 */     addTickEvent(this::tickEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.continuousComponent.triggerContinuity(entity, 80.0F);
/*     */   }
/*     */   
/*     */   private void tickEvent(LivingEntity entity, IAbility ability) {
/*  76 */     if (this.hasTarget && (
/*  77 */       this.target == null || !this.target.func_70089_S() || !this.target.func_70644_a((Effect)ModEffects.PUNK_CROSS.get()))) {
/*  78 */       JikiHelper.dropComponentItems(entity, this.lastKnownTargetPos, this.magneticItems);
/*  79 */       this.target = null;
/*  80 */       this.hasTarget = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void startContinuityEvent(LivingEntity player, IAbility ability) {
/*  86 */     this.target = null;
/*  87 */     this.hasTarget = false;
/*     */     
/*  89 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(player);
/*     */     
/*  91 */     this.magneticItems = JikiHelper.getMagneticItemsNeeded(inventory, 50.0F);
/*     */     
/*  93 */     this.projectileComponent.shoot(player, 6.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity player, IAbility ability) {
/*  97 */     if (this.target != null) {
/*  98 */       this.lastKnownTargetPos = this.target.func_213303_ch();
/*     */     }
/*     */     
/* 101 */     if (!this.hasTarget && this.projectileComponent.hasProjectileAlive()) {
/* 102 */       this.lastKnownProjPos = this.projectileComponent.getShotProjectile().func_213303_ch();
/*     */     }
/* 104 */     else if (!this.hasTarget && !this.projectileComponent.hasProjectileAlive()) {
/* 105 */       JikiHelper.dropComponentItems(player, this.lastKnownProjPos, this.magneticItems);
/* 106 */       this.continuousComponent.stopContinuity(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity player, IAbility ability) {
/* 111 */     if (this.target != null) {
/* 112 */       this.target.func_195063_d((Effect)ModEffects.PUNK_CROSS.get());
/*     */     }
/*     */     
/* 115 */     if (this.target != null) {
/* 116 */       JikiHelper.dropComponentItems(player, this.lastKnownTargetPos, this.magneticItems);
/*     */     }
/*     */     
/* 119 */     this.hasTarget = false;
/* 120 */     this.target = null;
/* 121 */     this.lastKnownProjPos = null;
/*     */     
/* 123 */     this.cooldownComponent.startCooldown(player, 200.0F);
/*     */   }
/*     */   
/*     */   private PunkCrossProjectile createProjectile(LivingEntity entity) {
/* 127 */     PunkCrossProjectile proj = new PunkCrossProjectile(entity.field_70170_p, entity);
/*     */     
/* 129 */     proj.onEntityImpactEvent = (target -> {
/*     */         IAbilityData abilityDataProps = AbilityDataCapability.get(target);
/*     */         
/*     */         if (abilityDataProps != null) {
/*     */           RustSkinAbility rustSkinAbility = (RustSkinAbility)abilityDataProps.getPassiveAbility(RustSkinAbility.INSTANCE);
/*     */           
/*     */           if (rustSkinAbility != null && !rustSkinAbility.isPaused()) {
/*     */             proj.func_174812_G();
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         
/*     */         target.func_195064_c(new EffectInstance((Effect)ModEffects.PUNK_CROSS.get(), 80, 1));
/*     */         
/*     */         JikiHelper.spawnAttractEffect((Entity)target);
/*     */         
/*     */         this.hasTarget = true;
/*     */         this.target = target;
/*     */         this.lastKnownTargetPos = target.func_213303_ch();
/*     */       });
/* 150 */     proj.onBlockImpactEvent = (hitPos -> {
/*     */         JikiHelper.dropComponentItems(entity, proj.func_213303_ch(), this.magneticItems);
/*     */         
/*     */         this.continuousComponent.stopContinuity(entity);
/*     */       });
/* 155 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkCrossAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */