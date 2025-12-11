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
/*    */ 
/*    */ public class ExplosionImmunityAbility
/*    */   extends PassiveAbility2 {
/* 19 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "explosion_immunity", new Pair[] {
/* 20 */         (Pair)ImmutablePair.of("Makes the user immune to explosions", null)
/*    */       });
/* 22 */   public static final AbilityCore<ExplosionImmunityAbility> INSTANCE = (new AbilityCore.Builder("Explosion Immunity", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, ExplosionImmunityAbility::new))
/* 23 */     .setHidden()
/* 24 */     .addDescriptionLine(DESCRIPTION)
/* 25 */     .build();
/*    */   
/* 27 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*    */   
/*    */   public ExplosionImmunityAbility(AbilityCore<ExplosionImmunityAbility> ability) {
/* 30 */     super(ability);
/*    */     
/* 32 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 36 */     if (canUse(entity).isFail()) {
/* 37 */       return damage;
/*    */     }
/*    */     
/* 40 */     if (damageSource.func_94541_c()) {
/* 41 */       return 0.0F;
/*    */     }
/*    */     
/* 44 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ExplosionImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */