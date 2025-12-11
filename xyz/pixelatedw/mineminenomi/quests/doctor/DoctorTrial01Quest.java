/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ 
/*    */ public class DoctorTrial01Quest extends Quest {
/* 19 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: First Aid", DoctorTrial01Quest::new))
/* 20 */     .build();
/*    */   
/* 22 */   private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, (Supplier)ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 23 */   private Objective objective02 = (new ObtainItemObjective("Collect %s nether warts", 15, () -> Items.field_151075_bm)).addRequirement(this.objective01);
/* 24 */   private Objective objective03 = (new ObtainItemObjective("Collect %s glistering melon slices", 15, () -> Items.field_151060_bw)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public DoctorTrial01Quest(QuestId id) {
/* 28 */     super(id);
/* 29 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 30 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 35 */     if (!removeQuestItem(player, Items.field_151075_bm, 15)) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (!removeQuestItem(player, Items.field_151060_bw, 15)) {
/* 39 */       return false;
/*    */     }
/* 41 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 43 */     props.addUnlockedAbility(FirstAidAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */