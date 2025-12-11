/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class BluntDamageImmunityAbility
/*    */   extends PassiveAbility2 {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "blunt_damage_immunity", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Makes the user immune to blunt based attacks", null)
/*    */       });
/* 23 */   public static final AbilityCore<BluntDamageImmunityAbility> INSTANCE = (new AbilityCore.Builder("Blunt Damage Immunity", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, BluntDamageImmunityAbility::new))
/* 24 */     .addDescriptionLine(DESCRIPTION)
/* 25 */     .setHidden()
/* 26 */     .build();
/*    */   
/* 28 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*    */   
/*    */   public BluntDamageImmunityAbility(AbilityCore<BluntDamageImmunityAbility> ability) {
/* 31 */     super(ability);
/*    */     
/* 33 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 37 */     boolean isBluntDamage = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isBlunt());
/*    */     
/* 39 */     if (isBluntDamage) {
/* 40 */       return 0.0F;
/*    */     }
/*    */     
/* 43 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\BluntDamageImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */