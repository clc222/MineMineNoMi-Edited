/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public abstract class SourceImmunityAbility extends PassiveAbility2 {
/*    */   public static final SourceImmunityInfo FIRE_IMMUNITY;
/*    */   public static final SourceImmunityInfo MAGMA_IMMUNITY;
/* 21 */   public static final SourceImmunityInfo EMPTY = new SourceImmunityInfo();
/*    */   static {
/* 23 */     FIRE_IMMUNITY = (new SourceImmunityInfo()).addSourceRule(ds -> (ds.func_76347_k() && ds != DamageSource.field_76371_c && ds != ModDamageSource.SUN_INCINERATION));
/* 24 */     MAGMA_IMMUNITY = (new SourceImmunityInfo()).addSourceRule(ds -> (ds.func_76347_k() && ds != ModDamageSource.SUN_INCINERATION));
/* 25 */   } public static final SourceImmunityInfo LIGHTNING_IMMUNITY = (new SourceImmunityInfo()).addSourceImmunities(new DamageSource[] { DamageSource.field_180137_b });
/*    */   
/*    */   protected final SourceImmunityInfo immunityInfo;
/*    */   
/* 29 */   protected final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageCheck);
/*    */   
/*    */   public SourceImmunityAbility(AbilityCore<SourceImmunityAbility> ability, @Nullable SourceImmunityInfo immunityInfo) {
/* 32 */     super(ability);
/* 33 */     this.immunityInfo = (immunityInfo == null) ? EMPTY : immunityInfo;
/*    */     
/* 35 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float damageCheck(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 41 */     if (!isDamageTaken(user, damageSource, damage)) {
/* 42 */       return 0.0F;
/*    */     }
/*    */     
/* 45 */     if (this.immunityInfo.isImmune(damageSource)) {
/* 46 */       return 0.0F;
/*    */     }
/*    */     
/* 49 */     return damage;
/*    */   }
/*    */   public abstract boolean isDamageTaken(LivingEntity paramLivingEntity, DamageSource paramDamageSource, float paramFloat);
/*    */   
/* 53 */   public static class SourceImmunityInfo { private static final List<DamageSource> DEFAULT_LOGIA_IMMUNITIES = Arrays.asList(new DamageSource[] { DamageSource.field_76367_g, DamageSource.field_220302_v, DamageSource.field_82728_o, DamageSource.field_188406_j, DamageSource.field_76379_h, DamageSource.field_82729_p });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     private ArrayList<DamageSource> sourceImmunities = new ArrayList<>();
/* 62 */     private ArrayList<Pair<DamageSource, Integer>> sourceResistances = new ArrayList<>();
/* 63 */     private ArrayList<Predicate<DamageSource>> sourceRules = new ArrayList<>();
/*    */     
/*    */     public SourceImmunityInfo() {
/* 66 */       addSourceImmunities(DEFAULT_LOGIA_IMMUNITIES.<DamageSource>toArray(new DamageSource[0]));
/*    */     }
/*    */     
/*    */     public boolean isImmune(DamageSource source) {
/* 70 */       boolean hasRulePass = this.sourceRules.stream().anyMatch(p -> p.test(source));
/* 71 */       boolean hasImmunity = this.sourceImmunities.contains(source);
/* 72 */       return (hasRulePass || hasImmunity);
/*    */     }
/*    */     
/*    */     public <T extends SourceImmunityInfo> T addSourceRule(Predicate<DamageSource> rule) {
/* 76 */       this.sourceRules.add(rule);
/* 77 */       return (T)this;
/*    */     }
/*    */     
/*    */     public <T extends SourceImmunityInfo> T addSourceImmunities(DamageSource... sources) {
/* 81 */       this.sourceImmunities.addAll(Arrays.asList(sources));
/* 82 */       return (T)this;
/*    */     }
/*    */     
/*    */     public <T extends SourceImmunityInfo> T addSourceResistance(DamageSource source, int resistance) {
/* 86 */       this.sourceResistances.add(Pair.of(source, Integer.valueOf(resistance)));
/* 87 */       return (T)this;
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\SourceImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */