/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HealEntityObjective;
/*    */ 
/*    */ public class DoctorTrial02Quest extends Quest {
/* 25 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Failed Experiment", DoctorTrial02Quest::new))
/* 26 */     .addRequirements(new QuestId[] { ModQuests.DOCTOR_TRIAL_01
/* 27 */       }).build();
/*    */   static {
/* 29 */     FIRST_AID_VILLAGERS_CHECK = ((player, target, amount) -> {
/*    */         IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */         
/*    */         FirstAidAbility ability = (FirstAidAbility)props.getEquippedAbility(FirstAidAbility.INSTANCE);
/* 33 */         boolean hasAbility = (ability != null && ability.isContinuous());
/*    */         
/*    */         boolean hasMedicBag = ItemsHelper.hasItemInSlot((LivingEntity)player, EquipmentSlotType.CHEST, (Item)ModArmors.MEDIC_BAG.get());
/*    */         
/*    */         boolean hasHealNeed = (target.func_110143_aJ() < target.func_110138_aP());
/*    */         
/* 39 */         return (hasHealNeed && hasAbility && hasMedicBag);
/*    */       });
/*    */   }
/* 42 */   private static final HealEntityObjective.ICheckHeal FIRST_AID_VILLAGERS_CHECK; private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, (Supplier)ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 43 */   private Objective objective02 = (new BrewPotionObjective("Brew %s splash potions", 12, new Item[] { Items.field_185155_bH }, null)).addRequirements(new Objective[] { this.objective01 });
/* 44 */   private Objective objective03 = (new HealEntityObjective("Heal %s Villagers using First Aid", 10, FIRST_AID_VILLAGERS_CHECK)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public DoctorTrial02Quest(QuestId id) {
/* 48 */     super(id);
/* 49 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 50 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 55 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 57 */     props.addUnlockedAbility(FailedExperimentAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */