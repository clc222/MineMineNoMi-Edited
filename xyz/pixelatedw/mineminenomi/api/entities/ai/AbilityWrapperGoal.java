/*     */ package xyz.pixelatedw.mineminenomi.api.entities.ai;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DisableComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class AbilityWrapperGoal<E extends MobEntity, A extends IAbility>
/*     */   extends TickedGoal<E> {
/*  29 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private AbilityCore<A> abilityCore;
/*     */   
/*     */   private A ability;
/*     */   private IAbilityData props;
/*     */   private boolean trackContinuity = false;
/*     */   
/*     */   public AbilityWrapperGoal(E entity, AbilityCore<A> core) {
/*  38 */     super(entity);
/*  39 */     this.abilityCore = core;
/*  40 */     this.ability = core.getType().equals(AbilityType.ACTION) ? (A)MobsHelper.unlockAndEquipAbility((LivingEntity)entity, core) : (A)MobsHelper.unlockAbility((LivingEntity)entity, core);
/*  41 */     this.props = AbilityDataCapability.get((LivingEntity)entity);
/*     */     
/*  43 */     this.ability.getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.addStartEvent(150, ()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  52 */     if (!this.entity.func_70089_S()) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     if (((MobEntity)this.entity).field_70170_p.func_175659_aa() == Difficulty.PEACEFUL && !WyHelper.isInChallengeDimension(((MobEntity)this.entity).field_70170_p)) {
/*  57 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  61 */     if (this.entity.func_70638_az() != null && this.entity.func_70638_az() instanceof net.minecraft.entity.monster.MonsterEntity) {
/*  62 */       return false;
/*     */     }
/*     */     
/*  65 */     for (EffectInstance effectInst : this.entity.func_70651_bq()) {
/*  66 */       if (effectInst.func_188419_a() instanceof IBindHandsEffect && ((IBindHandsEffect)effectInst.func_188419_a()).isBlockingSwings()) {
/*  67 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  71 */     if (this.entity instanceof AgeableEntity && ((AgeableEntity)this.entity).func_70874_b() < 0) {
/*  72 */       return false;
/*     */     }
/*     */     
/*  75 */     if (!canUseWrapper()) {
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     if (this.ability.canUse((LivingEntity)this.entity).isFail()) {
/*  80 */       return false;
/*     */     }
/*     */     
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  90 */     if (!this.entity.func_70089_S()) {
/*  91 */       return false;
/*     */     }
/*     */     
/*  94 */     boolean isDisabled = ((Boolean)this.ability.getComponent(ModAbilityKeys.DISABLE).map(DisableComponent::isDisabled).orElse(Boolean.valueOf(false))).booleanValue();
/*  95 */     if (isDisabled) {
/*  96 */       return false;
/*     */     }
/*     */     
/*  99 */     boolean isOnCooldown = ((Boolean)this.ability.getComponent(ModAbilityKeys.COOLDOWN).map(CooldownComponent::isOnCooldown).orElse(Boolean.valueOf(false))).booleanValue();
/* 100 */     if (isOnCooldown) {
/* 101 */       return false;
/*     */     }
/*     */     
/* 104 */     if (this.trackContinuity) {
/* 105 */       Optional<ContinuousComponent> comp = this.ability.getComponent(ModAbilityKeys.CONTINUOUS);
/* 106 */       if (comp.isPresent()) {
/* 107 */         boolean shouldBeContinuous = !((ContinuousComponent)comp.get()).isContinuous();
/* 108 */         if (shouldBeContinuous) {
/* 109 */           this.trackContinuity = false;
/* 110 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     if (!((MobEntity)this.entity).field_70170_p.field_72995_K) {
/* 116 */       ProtectedArea area = ProtectedAreasData.get(((MobEntity)this.entity).field_70170_p).getProtectedArea(this.entity.func_233580_cy_().func_177958_n(), this.entity.func_233580_cy_().func_177956_o(), this.entity.func_233580_cy_().func_177952_p());
/* 117 */       if (area != null && !area.canUseAbility(getCore())) {
/* 118 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 122 */     if (!canContinueToUseWrapper()) {
/* 123 */       return false;
/*     */     }
/*     */     
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void func_75249_e() {
/* 133 */     super.func_75249_e();
/* 134 */     startWrapper();
/* 135 */     this.ability.use((LivingEntity)this.entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void func_75246_d() {
/* 143 */     tickWrapper();
/* 144 */     super.func_75246_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void func_75251_c() {
/* 151 */     super.func_75251_c();
/* 152 */     stopWrapper();
/* 153 */     getAbility().getComponent(ModAbilityKeys.CONTINUOUS).ifPresent(comp -> comp.stopContinuity((LivingEntity)this.entity));
/*     */     
/* 155 */     if (!this.ability.isOGCD()) {
/* 156 */       GCDCapability.startGCD((LivingEntity)this.entity);
/*     */     }
/* 158 */     LOGGER.debug("{} stopped {} at tick {}", this.entity.func_200200_C_().getString(), this.ability.getDisplayName().getString(), Integer.valueOf(((MobEntity)this.entity).field_70173_aa));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public A getAbility() {
/* 164 */     return this.ability;
/*     */   }
/*     */   
/*     */   public AbilityCore<A> getCore() {
/* 168 */     return this.abilityCore;
/*     */   }
/*     */   
/*     */   public IAbilityData getProps() {
/* 172 */     return this.props;
/*     */   }
/*     */   
/*     */   public abstract boolean canUseWrapper();
/*     */   
/*     */   public abstract boolean canContinueToUseWrapper();
/*     */   
/*     */   public abstract void startWrapper();
/*     */   
/*     */   public abstract void tickWrapper();
/*     */   
/*     */   public abstract void stopWrapper();
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\ai\AbilityWrapperGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */