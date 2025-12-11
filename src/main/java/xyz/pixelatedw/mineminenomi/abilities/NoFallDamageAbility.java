/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ 
/*    */ public class NoFallDamageAbility extends PassiveAbility2 {
/* 13 */   public static final AbilityCore<NoFallDamageAbility> INSTANCE = (new AbilityCore.Builder("No Fall Damage", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, NoFallDamageAbility::new)).setHidden().build();
/*    */   
/* 15 */   protected final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*    */   
/*    */   public NoFallDamageAbility(AbilityCore<? extends NoFallDamageAbility> ability) {
/* 18 */     super(ability);
/*    */     
/* 20 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 24 */     if (damageSource == DamageSource.field_76379_h) {
/* 25 */       return 0.0F;
/*    */     }
/*    */     
/* 28 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\NoFallDamageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */