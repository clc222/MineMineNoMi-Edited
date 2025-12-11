/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class HeartRegenAbility extends PassiveAbility2 {
/* 12 */   public static final AbilityCore<HeartRegenAbility> INSTANCE = (new AbilityCore.Builder("Heart Regen", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, HeartRegenAbility::new)).setHidden().build();
/*    */   
/* 14 */   private int ticksWithoutHeart = 0;
/*    */   
/*    */   public HeartRegenAbility(AbilityCore<HeartRegenAbility> ability) {
/* 17 */     super(ability);
/*    */     
/* 19 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 23 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 24 */     if (!props.hasHeart()) {
/* 25 */       this.ticksWithoutHeart++;
/* 26 */       if (this.ticksWithoutHeart == 100) {
/* 27 */         props.setHeart(true);
/* 28 */         this.ticksWithoutHeart = 0;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\HeartRegenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */