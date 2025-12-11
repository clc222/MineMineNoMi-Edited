/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.DopingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ 
/*    */ public class DoctorTrial03Quest extends Quest {
/* 27 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Medic Bag Explosion", DoctorTrial03Quest::new))
/* 28 */     .addRequirements(new QuestId[] { ModQuests.DOCTOR_TRIAL_02
/* 29 */       }).build(); public static final KillEntityObjective.ICheckKill MULTIPLE_DEBUFFS_CHECK;
/*    */   static {
/* 31 */     MULTIPLE_DEBUFFS_CHECK = ((player, target, source) -> {
/*    */         int debuffs = 0;
/*    */         for (EffectInstance inst : target.func_70651_bq()) {
/*    */           if (!inst.func_188419_a().func_188408_i()) {
/*    */             debuffs++;
/*    */           }
/*    */         } 
/*    */         return (debuffs >= 3);
/*    */       });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 44 */   private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, (Supplier)ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 45 */   private Objective objective02 = (new BrewPotionObjective("Brew %s healing splash potions", 12, new Item[] { Items.field_185155_bH }, new Effect[] { Effects.field_76432_h })).addRequirements(new Objective[] { this.objective01 });
/* 46 */   private Objective objective03 = (new KillEntityObjective("Kill an enemy afflicted with 3+ debuffs", 1, MULTIPLE_DEBUFFS_CHECK)).addRequirements(new Objective[] { this.objective01 });
/*    */ 
/*    */   
/*    */   public DoctorTrial03Quest(QuestId id) {
/* 50 */     super(id);
/* 51 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 52 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 57 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 59 */     props.addUnlockedAbility(MedicBagExplosionAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/* 60 */     props.addUnlockedAbility(DopingAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */