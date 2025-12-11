/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class RequireMorphComponent extends AbilityComponent<IAbility> {
/*    */   public static AbilityDescriptionLine.IDescriptionLine<IAbility> getTooltip() {
/* 21 */     return (entity, ability) -> {
/*    */         TranslationTextComponent tooltip = null;
/*    */         Optional<RequireMorphComponent> comp = ability.getComponent(ModAbilityKeys.REQUIRE_MORPH);
/*    */         if (comp.isPresent()) {
/*    */           if (((RequireMorphComponent)comp.get()).morphs.length == 1) {
/*    */             tooltip = new TranslationTextComponent(ModI18n.ABILITY_DEPENDENCY_SINGLE_ACTIVE, new Object[] { "§a" + ((RequireMorphComponent)comp.get()).morphs[0].getDisplayName() + "§r" });
/*    */           } else if (((RequireMorphComponent)comp.get()).morphs.length > 1) {
/*    */             tooltip = new TranslationTextComponent(ModI18n.ABILITY_DEPENDENCY_DOUBLE_ACTIVE, new Object[] { "§a" + ((RequireMorphComponent)comp.get()).morphs[0].getDisplayName() + "§r", "§a" + ((RequireMorphComponent)comp.get()).morphs[1].getDisplayName() + "§r" });
/*    */           } 
/*    */         }
/*    */         return (ITextComponent)tooltip;
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private MorphInfo[] morphs = new MorphInfo[0];
/* 38 */   private Interval checkInterval = new Interval(10);
/*    */   
/*    */   public RequireMorphComponent(IAbility ability, MorphInfo mainMorph, MorphInfo... infos) {
/* 41 */     super(ModAbilityKeys.REQUIRE_MORPH, ability);
/* 42 */     this.morphs = new MorphInfo[infos.length + 1];
/* 43 */     this.morphs[0] = mainMorph;
/* 44 */     int i = 1;
/* 45 */     for (MorphInfo morph : infos) {
/* 46 */       this.morphs[i] = morph;
/* 47 */       i++;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void doTick(LivingEntity entity) {
/* 53 */     if (getAbility().hasComponent(ModAbilityKeys.CONTINUOUS)) {
/* 54 */       Optional<ContinuousComponent> comp = getAbility().getComponent(ModAbilityKeys.CONTINUOUS);
/* 55 */       if (comp.isPresent() && ((ContinuousComponent)comp.get()).isContinuous() && this.checkInterval.canTick() && !checkRequirements(entity)) {
/* 56 */         ((ContinuousComponent)comp.get()).stopContinuity(entity);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void postInit(IAbility iability) {
/* 63 */     if (iability instanceof Ability) {
/* 64 */       Ability abl = (Ability)iability;
/*    */       
/* 66 */       abl.addCanUseCheck((entity, ability) -> AbilityHelper.canUseMorphAbility(entity, (IAbility)abl, this.morphs));
/*    */     }
/* 68 */     else if (iability instanceof PassiveAbility2) {
/* 69 */       PassiveAbility2 abl = (PassiveAbility2)iability;
/*    */       
/* 71 */       abl.addCanUseCheck((entity, ability) -> AbilityHelper.canUseMorphAbility(entity, (IAbility)abl, this.morphs));
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean checkRequirements(LivingEntity entity) {
/* 76 */     boolean result = false;
/* 77 */     for (MorphInfo morph : this.morphs) {
/* 78 */       if (DevilFruitCapability.get(entity).hasMorphInQueue(morph)) {
/* 79 */         result = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 83 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\RequireMorphComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */