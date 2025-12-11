/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BlackLegPassiveBonusesAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.AntiMannerKickCourseWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.ConcasseWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.ExtraHachisWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.blackleg.PartyTableKickCourseWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BlackLegTrainerEntity extends TrainerEntity implements IHakiTrainer {
/*    */   public BlackLegTrainerEntity(EntityType type, World world) {
/* 42 */     super(type, world, MobsHelper.BLACK_LEG_TRAINERS_TEXTURES);
/*    */     
/* 44 */     if (world != null && !world.field_72995_K) {
/* 45 */       getEntityStats().setFaction(ModValues.CIVILIAN);
/* 46 */       getEntityStats().setFightingStyle(ModValues.BLACK_LEG);
/* 47 */       getEntityStats().setRace(ModValues.HUMAN);
/*    */       
/* 49 */       func_184201_a(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR.get()));
/*    */       
/* 51 */       setDoriki(2000.0D + WyHelper.randomWithRange(0, 1000));
/* 52 */       setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*    */       
/* 54 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(12.0D);
/*    */       
/* 56 */       this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/* 57 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*    */       
/* 59 */       this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BlackLegPassiveBonusesAbility.INSTANCE));
/* 60 */       this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/* 61 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.3D, true));
/*    */       
/* 63 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 64 */       goals.addEntry(() -> new PartyTableKickCourseWrapperGoal((MobEntity)this), 3.0F);
/* 65 */       goals.addEntry(() -> new AntiMannerKickCourseWrapperGoal((MobEntity)this), 3.0F);
/* 66 */       goals.addEntry(() -> new ConcasseWrapperGoal((MobEntity)this), 3.0F);
/* 67 */       goals.addEntry(() -> new ExtraHachisWrapperGoal((MobEntity)this), 3.0F);
/*    */       
/* 69 */       MobsHelper.getRandomizedGoals((MobEntity)this, 5, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/* 70 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 100);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 75 */     return OPEntity.createAttributes()
/* 76 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 77 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 78 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 79 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 80 */       .func_233815_a_(Attributes.field_233826_i_, 15.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/* 85 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 86 */     List<QuestId> availableQuests = new ArrayList<>();
/*    */     
/* 88 */     if (entityProps.isBlackLeg()) {
/* 89 */       availableQuests.addAll((Collection<? extends QuestId>)ModQuests.BLACK_LEG_TRIALS.stream().map(reg -> reg).collect(Collectors.toList()));
/*    */     }
/*    */     
/* 92 */     return availableQuests;
/*    */   }
/*    */ 
/*    */   
/*    */   public HakiType getTrainingHaki() {
/* 97 */     return HakiType.BUSOSHOKU;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\BlackLegTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */