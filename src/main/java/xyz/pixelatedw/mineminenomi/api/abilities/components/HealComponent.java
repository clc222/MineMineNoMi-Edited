/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.LivingHealByEvent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class HealComponent extends AbilityComponent<IAbility> {
/* 16 */   private static final UUID HEAL_BONUS_MANAGER_UUID = UUID.fromString("c778872e-258a-4672-8cd6-0f930ad9adba");
/*    */   
/*    */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float heal) {
/* 19 */     return getTooltip(heal, heal);
/*    */   }
/*    */   
/*    */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(float min, float max) {
/* 23 */     return (e, a) -> {
/*    */         AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)ModI18n.ABILITY_DESCRIPTION_STAT_NAME_HEAL, min, max);
/*    */         a.getComponent(ModAbilityKeys.HEAL).ifPresent(());
/*    */         return statBuilder.build().getStatDescription();
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   private final BonusManager bonusManager = new BonusManager(HEAL_BONUS_MANAGER_UUID);
/*    */   
/*    */   public HealComponent(IAbility ability) {
/* 39 */     super(ModAbilityKeys.HEAL, ability);
/* 40 */     addBonusManager(this.bonusManager);
/*    */   }
/*    */   
/*    */   public boolean healTarget(LivingEntity healer, LivingEntity target, float baseHeal) {
/* 44 */     ensureIsRegistered();
/* 45 */     float finalHeal = this.bonusManager.applyBonus(baseHeal);
/*    */     
/* 47 */     LivingHealByEvent event = new LivingHealByEvent(healer, target, finalHeal);
/* 48 */     if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/* 49 */       target.func_70691_i(finalHeal);
/* 50 */       return true;
/*    */     } 
/*    */     
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public BonusManager getBonusManager() {
/* 57 */     return this.bonusManager;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\HealComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */