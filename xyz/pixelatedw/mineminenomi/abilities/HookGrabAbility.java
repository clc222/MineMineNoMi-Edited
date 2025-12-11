/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.HookEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class HookGrabAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hook_grab", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Shoot's the equipped Hook catching the enemy and dragging them towards the user.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*  35 */   public static final AbilityCore<HookGrabAbility> INSTANCE = (new AbilityCore.Builder("Hook Grab", AbilityCategory.EQUIPMENT, HookGrabAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/*  38 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  39 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  40 */       }).build();
/*     */   
/*  42 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  43 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*  45 */   private LivingEntity grabbedEntity = null;
/*  46 */   private ItemStack hookStack = ItemStack.field_190927_a;
/*  47 */   private Hand hookHand = Hand.MAIN_HAND;
/*     */   
/*  49 */   private HookEntity proj = null;
/*     */   
/*     */   public HookGrabAbility(AbilityCore<HookGrabAbility> core) {
/*  52 */     super(core);
/*     */     
/*  54 */     this.isNew = true;
/*  55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  57 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*  58 */     addCanUseCheck(this::canUseCheck);
/*     */     
/*  60 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  64 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.grabbedEntity = null;
/*     */     
/*  70 */     this.proj = (HookEntity)this.projectileComponent.getNewProjectile(entity);
/*  71 */     this.proj.onEntityImpactEvent = (hit -> this.grabbedEntity = hit);
/*     */ 
/*     */ 
/*     */     
/*  75 */     ItemStack mainHandStack = entity.func_184614_ca();
/*  76 */     ItemStack offHandStack = entity.func_184614_ca();
/*  77 */     boolean hasHookInMainHand = (!mainHandStack.func_190926_b() && mainHandStack.func_77973_b() == ModWeapons.HOOK.get());
/*  78 */     boolean hasHookInOffHand = (!offHandStack.func_190926_b() && offHandStack.func_77973_b() == ModWeapons.HOOK.get());
/*     */     
/*  80 */     if (hasHookInMainHand) {
/*  81 */       this.hookStack = entity.func_184614_ca();
/*  82 */       this.hookHand = Hand.MAIN_HAND;
/*     */     }
/*  84 */     else if (hasHookInOffHand) {
/*  85 */       this.hookStack = entity.func_184614_ca();
/*  86 */       this.hookHand = Hand.OFF_HAND;
/*     */     } 
/*     */     
/*  89 */     entity.func_184611_a(this.hookHand, ItemStack.field_190927_a);
/*     */     
/*  91 */     entity.field_70170_p.func_217376_c((Entity)this.proj);
/*     */     
/*  93 */     this.proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 4.0F, 0.0F);
/*     */   }
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  97 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     if ((this.proj == null || !this.proj.func_70089_S()) && this.grabbedEntity == null) {
/* 102 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 106 */     if (this.grabbedEntity != null && (!this.grabbedEntity.func_70089_S() || AbilityHelper.isGuardBlocking(this.grabbedEntity) || !entity.func_70685_l((Entity)this.grabbedEntity))) {
/* 107 */       this.continuousComponent.stopContinuity(entity);
/*     */       return;
/*     */     } 
/* 110 */     if (this.grabbedEntity != null) {
/* 111 */       if (this.grabbedEntity.func_70032_d((Entity)entity) < 2.0F) {
/* 112 */         AbilityHelper.setDeltaMovement((Entity)this.grabbedEntity, 0.0D, 0.0D, 0.0D);
/* 113 */         this.continuousComponent.stopContinuity(entity);
/*     */         
/*     */         return;
/*     */       } 
/* 117 */       if (this.continuousComponent.getContinueTime() % 2.0F == 0.0F) {
/* 118 */         Vector3d dirVec = this.grabbedEntity.func_213303_ch().func_178788_d(entity.func_213303_ch()).func_72432_b();
/* 119 */         AbilityHelper.setDeltaMovement((Entity)this.grabbedEntity, -dirVec.field_72450_a, -dirVec.field_72448_b, -dirVec.field_72449_c);
/* 120 */         this.grabbedEntity.field_70143_R = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 126 */     this.proj = null;
/* 127 */     this.grabbedEntity = null;
/*     */     
/* 129 */     entity.func_184611_a(this.hookHand, this.hookStack);
/*     */     
/* 131 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*     */   }
/*     */   
/*     */   private HookEntity createProjectile(LivingEntity entity) {
/* 135 */     HookEntity proj = new HookEntity(entity.field_70170_p, entity);
/* 136 */     return proj;
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUseCheck(LivingEntity entity, IAbility ability) {
/* 140 */     IDevilFruit dfProps = DevilFruitCapability.get(entity);
/* 141 */     if (!dfProps.hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI) && !dfProps.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI)) {
/* 142 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 145 */     ItemStack mainHandStack = entity.func_184614_ca();
/* 146 */     ItemStack offHandStack = entity.func_184614_ca();
/* 147 */     boolean hasHookInMainHand = (!mainHandStack.func_190926_b() && mainHandStack.func_77973_b() == ModWeapons.HOOK.get());
/* 148 */     boolean hasHookInOffHand = (!offHandStack.func_190926_b() && offHandStack.func_77973_b() == ModWeapons.HOOK.get());
/* 149 */     if (!hasHookInMainHand && !hasHookInOffHand) {
/* 150 */       return AbilityUseResult.fail(null);
/*     */     }
/*     */     
/* 153 */     return AbilityUseResult.success();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\HookGrabAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */