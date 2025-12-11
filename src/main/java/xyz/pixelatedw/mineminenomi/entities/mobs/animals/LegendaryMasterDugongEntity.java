/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ITrainer;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SpinningBrawlWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestChooseScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class LegendaryMasterDugongEntity extends AbstractDugongEntity implements ITrainer {
/*     */   public LegendaryMasterDugongEntity(EntityType<? extends TameableEntity> type, World world) {
/*  49 */     super(type, world);
/*     */     
/*  51 */     if (world != null && !world.field_72995_K) {
/*  52 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  53 */       props.setFightingStyle(ModValues.BRAWLER);
/*     */       
/*  55 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(8.0D);
/*     */       
/*  57 */       func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */       
/*  59 */       this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*  60 */       this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  61 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.2000000476837158D, true));
/*     */       
/*  63 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*  64 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, 10, true, true, entity -> (EntityStatsCapability.get(entity).isBandit() || EntityStatsCapability.get(entity).isPirate())));
/*     */       
/*  66 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/*  67 */       goals.addEntry(() -> new HakaiHoWrapperGoal((MobEntity)this), 3.0F);
/*  68 */       goals.addEntry(() -> new JishinHoWrapperGoal((MobEntity)this), 3.0F);
/*  69 */       goals.addEntry(() -> new ChargedPunchWrapperGoal((MobEntity)this), 3.0F);
/*  70 */       goals.addEntry(() -> new SpinningBrawlWrapperGoal((MobEntity)this), 2.0F);
/*  71 */       goals.addEntry(() -> new SuplexWrapperGoal((MobEntity)this), 1.0F);
/*     */       
/*  73 */       this.field_70714_bg.func_75776_a(2, (Goal)new DamageAbsorptionWrapperGoal((MobEntity)this));
/*  74 */       MobsHelper.getRandomizedGoals((MobEntity)this, 5, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*  75 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 100);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  80 */     return OPEntity.createAttributes()
/*  81 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/*  82 */       .func_233815_a_(Attributes.field_233823_f_, 15.0D)
/*  83 */       .func_233815_a_(Attributes.field_233821_d_, 0.25D)
/*  84 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(100, 120))
/*  85 */       .func_233815_a_(Attributes.field_233826_i_, 10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/*  90 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/*  91 */     List<QuestId> availableQuests = new ArrayList<>();
/*     */     
/*  93 */     if (entityProps.isBrawler()) {
/*  94 */       availableQuests.addAll((Collection<? extends QuestId>)ModQuests.BRAWLER_TRIALS.stream().map(reg -> reg).collect(Collectors.toList()));
/*     */     }
/*     */     
/*  97 */     availableQuests.add(ModQuests.EMPTY_HANDS_TRIAL);
/*  98 */     availableQuests.add(ModQuests.KNOCKDOWN_TRIAL);
/*  99 */     availableQuests.add(ModQuests.TAKEDOWN_TRIAL);
/* 100 */     availableQuests.add(ModQuests.COMMAND_TRIAL);
/*     */     
/* 102 */     return availableQuests;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 107 */     if (hand != Hand.MAIN_HAND) {
/* 108 */       return ActionResultType.FAIL;
/*     */     }
/*     */     
/* 111 */     if (player.func_213453_ef()) {
/* 112 */       return super.func_230254_b_(player, hand);
/*     */     }
/*     */     
/* 115 */     if (!player.field_70170_p.field_72995_K) {
/* 116 */       WyNetwork.sendTo(new SOpenQuestChooseScreenPacket(func_145782_y(), WyHelper.isInCombat((LivingEntity)player)), player);
/* 117 */       if (this instanceof xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer) {
/* 118 */         WyNetwork.sendTo(new SSyncHakiDataPacket(player.func_145782_y(), HakiDataCapability.get((LivingEntity)player)), player);
/*     */       }
/* 120 */       return ActionResultType.PASS;
/*     */     } 
/*     */     
/* 123 */     return ActionResultType.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
/* 128 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\LegendaryMasterDugongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */