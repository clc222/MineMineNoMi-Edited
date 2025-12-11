/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ 
/*    */ public abstract class PassiveStatBonusAbility
/*    */   extends PassiveAbility2
/*    */ {
/* 17 */   private final ChangeStatsComponent statsComponent = new ChangeStatsComponent(this);
/*    */   
/* 19 */   private HashMap<Attribute, Function<LivingEntity, AttributeModifier>> dynamicAttributes = new HashMap<>();
/* 20 */   private HashMap<Attribute, Double> cache = new HashMap<>();
/*    */   
/*    */   public PassiveStatBonusAbility(AbilityCore<?> core) {
/* 23 */     super(core);
/*    */     
/* 25 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.statsComponent });
/*    */     
/* 27 */     this.pauseTickComponent.addResumeEvent(100, this::resumeEvent);
/* 28 */     this.pauseTickComponent.addPauseEvent(100, this::pauseEvent);
/*    */     
/* 30 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */     
/* 32 */     setTickRate(40.0F);
/*    */   }
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 36 */     if (!entity.field_70170_p.field_72995_K) {
/* 37 */       updateStats(entity);
/*    */     }
/*    */     
/* 40 */     if (this.pauseTickComponent.isPaused()) {
/* 41 */       this.statsComponent.removeModifiers(entity);
/*    */       
/*    */       return;
/*    */     } 
/* 45 */     if (!getCheck().test(entity)) {
/* 46 */       this.statsComponent.removeModifiers(entity);
/*    */       
/*    */       return;
/*    */     } 
/* 50 */     this.statsComponent.applyModifiers(entity);
/*    */   }
/*    */   
/*    */   private void resumeEvent(LivingEntity entity, IAbility ability) {
/* 54 */     if (getCheck().test(entity)) {
/* 55 */       this.statsComponent.applyModifiers(entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void pauseEvent(LivingEntity entity, IAbility ability) {
/* 60 */     this.statsComponent.removeModifiers(entity);
/*    */   }
/*    */   
/*    */   private boolean updateStats(LivingEntity entity) {
/* 64 */     boolean updates = false;
/* 65 */     for (Map.Entry<Attribute, Function<LivingEntity, AttributeModifier>> entry : this.dynamicAttributes.entrySet()) {
/* 66 */       AttributeModifier mod = ((Function<LivingEntity, AttributeModifier>)entry.getValue()).apply(entity);
/* 67 */       double bonus = mod.func_111164_d();
/* 68 */       Double lastStat = this.cache.get(entry.getKey());
/* 69 */       if (lastStat == null || lastStat.doubleValue() != bonus) {
/* 70 */         this.statsComponent.removeModifier(entity, entry.getKey(), mod);
/* 71 */         this.statsComponent.removeAttributeModifier(entry.getKey());
/* 72 */         this.statsComponent.addAttributeModifier(entry.getKey(), mod);
/* 73 */         this.cache.put(entry.getKey(), Double.valueOf(bonus));
/* 74 */         updates = true;
/*    */       } 
/*    */     } 
/* 77 */     return updates;
/*    */   }
/*    */   
/*    */   public abstract Predicate<LivingEntity> getCheck();
/*    */   
/*    */   public void pushStaticAttribute(Attribute attr, AttributeModifier mod) {
/* 83 */     this.statsComponent.addAttributeModifier(attr, mod, getCheck());
/*    */   }
/*    */   
/*    */   public void pushStaticAttribute(Attribute attr, AttributeModifier mod, @Nullable Predicate<LivingEntity> test) {
/* 87 */     this.statsComponent.addAttributeModifier(attr, mod, test);
/*    */   }
/*    */   
/*    */   public void pushDynamicAttribute(Attribute attr, Function<LivingEntity, AttributeModifier> func) {
/* 91 */     this.dynamicAttributes.put(attr, func);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PassiveStatBonusAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */