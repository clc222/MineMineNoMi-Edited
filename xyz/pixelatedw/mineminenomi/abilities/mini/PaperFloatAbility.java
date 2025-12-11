/*    */ package xyz.pixelatedw.mineminenomi.abilities.mini;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class PaperFloatAbility extends PassiveAbility2 {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "paper_float", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("While in the mini form and holding a piece of paper the user is able to float.", null)
/*    */       });
/* 24 */   public static final AbilityCore<PaperFloatAbility> INSTANCE = (new AbilityCore.Builder("Paper Float", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, PaperFloatAbility::new))
/* 25 */     .addDescriptionLine(DESCRIPTION)
/* 26 */     .build();
/*    */   
/* 28 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public PaperFloatAbility(AbilityCore<PaperFloatAbility> ability) {
/* 31 */     super(ability);
/*    */     
/* 33 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*    */     
/* 35 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/* 36 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 41 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*    */     
/* 43 */     Ability ability = (Ability)abilityProps.getEquippedAbility(MiniMiniAbility.INSTANCE);
/*    */     
/* 45 */     boolean isActive = (ability != null && ability.isContinuous());
/* 46 */     boolean hasPaper = (entity.func_184614_ca().func_77973_b() == Items.field_151121_aF || entity.func_184592_cb().func_77973_b() == Items.field_151121_aF);
/* 47 */     boolean inAir = (!entity.func_233570_aj_() && (entity.func_213322_ci()).field_72448_b < 0.0D);
/*    */     
/* 49 */     if (isActive && hasPaper && inAir) {
/* 50 */       entity.field_70143_R = 0.0F;
/*    */       
/* 52 */       if (this.animationComponent.isStopped()) {
/* 53 */         this.animationComponent.start(entity, ModAnimations.RAISE_ARMS, 20);
/*    */       }
/* 55 */       AbilityHelper.setDeltaMovement((Entity)entity, (entity.func_213322_ci()).field_72450_a, (entity.func_213322_ci()).field_72448_b / 2.0D, (entity.func_213322_ci()).field_72449_c);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mini\PaperFloatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */