/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public class RequireAbilityComponent extends AbilityComponent<IAbility> {
/*     */   public static final IRequireAbilityCheck<?> IS_ACTIVE;
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine<IAbility> getTooltip() {
/*  19 */     return (entity, ability) -> {
/*     */         TranslationTextComponent tooltip = null;
/*     */         Optional<RequireAbilityComponent> comp = ability.getComponent(ModAbilityKeys.REQUIRE_ABILITY);
/*     */         if (comp.isPresent())
/*     */           if (((RequireAbilityComponent)comp.get()).checks.length == 1) {
/*     */             tooltip = new TranslationTextComponent(ModI18n.ABILITY_DEPENDENCY_SINGLE_ACTIVE, new Object[] { AbilityHelper.mentionAbility(CheckData.access$000(((RequireAbilityComponent)comp.get()).checks[0])).getString() });
/*     */           } else if (((RequireAbilityComponent)comp.get()).checks.length > 1) {
/*     */             tooltip = new TranslationTextComponent(ModI18n.ABILITY_DEPENDENCY_DOUBLE_ACTIVE, new Object[] { AbilityHelper.mentionAbility(CheckData.access$000(((RequireAbilityComponent)comp.get()).checks[0])).getString(), AbilityHelper.mentionAbility(CheckData.access$000(((RequireAbilityComponent)comp.get()).checks[1])).getString() });
/*     */           }  
/*     */         return (ITextComponent)tooltip;
/*     */       };
/*     */   }
/*     */   
/*     */   public static final IRequireAbilityCheck<?> START_IF_NOT_ACTIVE;
/*     */   
/*     */   static {
/*  35 */     IS_ACTIVE = ((entity, core) -> {
/*     */         IAbility ability = AbilityDataCapability.get(entity).getEquippedAbility(core);
/*     */ 
/*     */ 
/*     */         
/*     */         return (ability != null) ? ((Boolean)ability.getComponent(ModAbilityKeys.CONTINUOUS).map(()).orElse(Boolean.valueOf(false))).booleanValue() : false;
/*     */       });
/*     */ 
/*     */ 
/*     */     
/*  45 */     START_IF_NOT_ACTIVE = ((entity, core) -> {
/*     */         IAbility ability = AbilityDataCapability.get(entity).getEquippedAbility(core);
/*     */         if (ability != null) {
/*     */           if (!((Boolean)ability.getComponent(ModAbilityKeys.CONTINUOUS).map(()).orElse(Boolean.valueOf(false))).booleanValue()) {
/*     */             ability.use(entity);
/*     */           }
/*     */           return ((Boolean)ability.getComponent(ModAbilityKeys.CONTINUOUS).map(()).orElse(Boolean.valueOf(false))).booleanValue();
/*     */         } 
/*     */         return false;
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  59 */   private CheckData[] checks = new CheckData[0];
/*  60 */   private Interval checkInterval = new Interval(10);
/*     */   
/*     */   public RequireAbilityComponent(IAbility ability, CheckData mainCheck, CheckData... checks) {
/*  63 */     super(ModAbilityKeys.REQUIRE_ABILITY, ability);
/*  64 */     this.checks = new CheckData[checks.length + 1];
/*  65 */     this.checks[0] = mainCheck;
/*  66 */     int i = 1;
/*  67 */     for (CheckData check : checks) {
/*  68 */       this.checks[i] = check;
/*  69 */       i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doTick(LivingEntity entity) {
/*  75 */     if (getAbility().hasComponent(ModAbilityKeys.CONTINUOUS)) {
/*  76 */       Optional<ContinuousComponent> comp = getAbility().getComponent(ModAbilityKeys.CONTINUOUS);
/*  77 */       if (comp.isPresent() && ((ContinuousComponent)comp.get()).isContinuous() && this.checkInterval.canTick() && !checkRequirements(entity)) {
/*  78 */         ((ContinuousComponent)comp.get()).stopContinuity(entity);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility iability) {
/*  85 */     if (iability instanceof Ability) {
/*  86 */       Ability abl = (Ability)iability;
/*     */       
/*  88 */       abl.addCanUseCheck((entity, ability) -> AbilityHelper.requireAbilityCheck(entity, (IAbility)abl, this.checks));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean checkRequirements(LivingEntity entity) {
/*  93 */     boolean result = false;
/*  94 */     for (CheckData checkData : this.checks) {
/*  95 */       if (checkData.checkAbility(entity)) {
/*  96 */         result = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 100 */     return result;
/*     */   }
/*     */   @FunctionalInterface
/*     */   public static interface IRequireAbilityCheck<A extends IAbility> {
/*     */     boolean checkAbility(LivingEntity param1LivingEntity, AbilityCore<A> param1AbilityCore); }
/*     */   public static class CheckData<A extends IAbility> { private AbilityCore<A> ability;
/*     */     
/*     */     public CheckData(AbilityCore<A> ability, RequireAbilityComponent.IRequireAbilityCheck<A> check) {
/* 108 */       this.ability = ability;
/* 109 */       this.check = check;
/*     */     }
/*     */     private RequireAbilityComponent.IRequireAbilityCheck<A> check;
/*     */     public AbilityCore<A> getAbility() {
/* 113 */       return this.ability;
/*     */     }
/*     */     
/*     */     public RequireAbilityComponent.IRequireAbilityCheck<A> getCheck() {
/* 117 */       return this.check;
/*     */     }
/*     */     
/*     */     public boolean checkAbility(LivingEntity entity) {
/* 121 */       return this.check.checkAbility(entity, this.ability);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\RequireAbilityComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */