/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.NoFallDamageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class BouncyAbility extends NoFallDamageAbility {
/* 17 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bouncy", new Pair[] {
/* 18 */         (Pair)ImmutablePair.of("Makes the user bounce upon landing", null)
/*    */       });
/* 20 */   public static final AbilityCore<BouncyAbility> INSTANCE = (new AbilityCore.Builder("Bouncy", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, BouncyAbility::new))
/* 21 */     .addDescriptionLine(DESCRIPTION)
/* 22 */     .build();
/*    */   
/*    */   private boolean touchedGround = true;
/*    */   
/* 26 */   private float bounceValue = 0.0F;
/*    */   
/*    */   public BouncyAbility(AbilityCore<BouncyAbility> ability) {
/* 29 */     super(ability);
/*    */     
/* 31 */     this.damageTakenComponent.addOnAttackEvent(this::onDamageTaken);
/*    */     
/* 33 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   protected void duringPassiveEvent(LivingEntity entity) {
/* 37 */     if (entity.field_70143_R > 12.0F || !this.touchedGround) {
/* 38 */       this.touchedGround = false;
/*    */       
/* 40 */       if (entity.field_70143_R != 0.0F) {
/* 41 */         this.bounceValue = entity.field_70143_R;
/*    */       }
/*    */       
/* 44 */       if (entity.func_233570_aj_() && this.bounceValue / 30.0F > 0.0F) {
/* 45 */         this.touchedGround = true;
/*    */         
/* 47 */         AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, this.bounceValue / 30.0D, (entity.func_213322_ci()).field_72449_c);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 53 */     if (damageSource == DamageSource.field_188406_j) {
/* 54 */       return 0.0F;
/*    */     }
/*    */     
/* 57 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\BouncyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */