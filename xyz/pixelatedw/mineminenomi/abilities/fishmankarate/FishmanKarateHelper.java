/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sui.FreeSwimmingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class FishmanKarateHelper {
/*    */   public static boolean isInWater(LivingEntity entity) {
/* 19 */     boolean isInWater = entity.func_70090_H();
/* 20 */     boolean isFreeSwimming = false;
/*    */     
/* 22 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 23 */     FreeSwimmingAbility freeSwimming = (FreeSwimmingAbility)props.getEquippedAbility(FreeSwimmingAbility.INSTANCE);
/* 24 */     if (freeSwimming != null && freeSwimming.isContinuous() && freeSwimming.isSwimming()) {
/* 25 */       isFreeSwimming = true;
/*    */     }
/*    */     
/* 28 */     return (isInWater || isFreeSwimming);
/*    */   }
/*    */   
/*    */   public static AbilityDescriptionLine.IDescriptionLine getWaterBuffedProjectileDamageStat(float waterMultiplier) {
/* 32 */     return (entity, ability) -> {
/*    */         AbilityProjectileEntity proj = ability.getComponent(ModAbilityKeys.PROJECTILE).map(()).orElse(null);
/*    */         if (proj != null && proj.getDamage() > 0.0F) {
/*    */           float waterDamage = proj.getDamage() * waterMultiplier;
/*    */           AbilityStat.Builder baseStatBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_BASE_DAMAGE, proj.getDamage());
/*    */           AbilityStat.Builder waterStatBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_WATER_DAMAGE, waterDamage);
/*    */           ability.getComponent(ModAbilityKeys.PROJECTILE).ifPresent(());
/*    */           ITextComponent baseStat = baseStatBuilder.build().getStatDescription(2);
/*    */           ITextComponent waterStat = waterStatBuilder.build().getStatDescription(2);
/*    */           StringBuilder sb = new StringBuilder();
/*    */           sb.append("§a" + ModI18n.ABILITY_DESCRIPTION_STAT_NAME_PROJECTILE.getString() + "§r\n");
/*    */           sb.append(baseStat.getString() + "\n");
/*    */           sb.append(waterStat.getString());
/*    */           return (ITextComponent)new StringTextComponent(sb.toString());
/*    */         } 
/*    */         return null;
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AbilityDescriptionLine.IDescriptionLine getWaterBuffedDamageStat(float normalDamage, float waterMultiplier) {
/* 67 */     return (entity, ability) -> {
/*    */         float waterDamage = normalDamage * waterMultiplier;
/*    */         AbilityStat.Builder baseStatBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_BASE_DAMAGE, normalDamage, normalDamage);
/*    */         AbilityStat.Builder waterStatBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_WATER_DAMAGE, waterDamage, waterDamage);
/*    */         ability.getComponent(ModAbilityKeys.DAMAGE).ifPresent(());
/*    */         ITextComponent baseStat = baseStatBuilder.build().getStatDescription();
/*    */         ITextComponent waterStat = waterStatBuilder.build().getStatDescription();
/*    */         StringBuilder sb = new StringBuilder();
/*    */         sb.append(baseStat.getString() + "\n");
/*    */         sb.append(waterStat.getString());
/*    */         return (ITextComponent)new StringTextComponent(sb.toString());
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\FishmanKarateHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */