/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class CyborgHelper {
/*    */   public static Ability.ICanUseEvent<LivingEntity, IAbility> hasEnoughCola(int colaNeeded) {
/* 16 */     return (entity, ability) -> {
/*    */         if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.marines.PacifistaEntity) {
/*    */           return AbilityUseResult.success();
/*    */         }
/*    */         IEntityStats props = EntityStatsCapability.get(entity);
/*    */         return (props.getCola() - colaNeeded < 0) ? AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA) : AbilityUseResult.success();
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AbilityDescriptionLine.IDescriptionLine getColaTooltip(float cola) {
/* 31 */     return (e, a) -> {
/*    */         AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_COLA, cola);
/*    */         return statBuilder.build().getStatDescription();
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\CyborgHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */