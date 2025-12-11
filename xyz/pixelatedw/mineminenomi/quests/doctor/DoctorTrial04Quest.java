/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.AntidoteShotAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.VirusZoneAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.UseAbilityObjective;
/*    */ 
/*    */ public class DoctorTrial04Quest extends Quest {
/* 24 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Virus Zone", DoctorTrial04Quest::new)).addRequirements(new QuestId[] { ModQuests.DOCTOR_TRIAL_03 }).build();
/*    */   static {
/* 26 */     MEDIC_BAG_CHECK = ((player, ability) -> {
/*    */         List<LivingEntity> targets = TargetHelper.getEntitiesInArea((LivingEntity)player, 10.0D, null, new Class[] { LivingEntity.class });
/*    */         return (targets.size() >= 2);
/*    */       });
/*    */   }
/*    */   public static final UseAbilityObjective.ICheckAbilityUse MEDIC_BAG_CHECK;
/* 32 */   private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, (Supplier)ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 33 */   private Objective objective02 = (new UseAbilityObjective("Catch at least 2 enemies in Medic Bag Explosion's radius", 1, MEDIC_BAG_CHECK)).addRequirements(new Objective[] { this.objective01 });
/*    */   
/*    */   public DoctorTrial04Quest(QuestId id) {
/* 36 */     super(id);
/* 37 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/* 38 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 42 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 44 */     props.addUnlockedAbility(VirusZoneAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 45 */     props.addUnlockedAbility(AntidoteShotAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */