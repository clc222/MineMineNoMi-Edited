/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.GenkotsuMeteorWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SpinningBrawlWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BrawlerTrainerEntity extends TrainerEntity implements IHakiTrainer {
/*     */   public BrawlerTrainerEntity(EntityType type, World world) {
/*  40 */     super(type, world, MobsHelper.BRAWLER_TRAINER_TEXTURES);
/*     */     
/*  42 */     if (world != null && !world.field_72995_K) {
/*  43 */       getEntityStats().setFaction(ModValues.CIVILIAN);
/*  44 */       getEntityStats().setFightingStyle(ModValues.BRAWLER);
/*  45 */       getEntityStats().setRace(ModValues.HUMAN);
/*     */       
/*  47 */       setDoriki(2000.0D + WyHelper.randomWithRange(0, 1000));
/*  48 */       setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*     */       
/*  50 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(12.0D);
/*     */       
/*  52 */       this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*  53 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*     */       
/*  55 */       this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*  56 */       this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  57 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.2D, true));
/*     */       
/*  59 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/*  60 */       goals.addEntry(() -> new HakaiHoWrapperGoal((MobEntity)this), 3.0F);
/*  61 */       goals.addEntry(() -> new JishinHoWrapperGoal((MobEntity)this), 3.0F);
/*  62 */       goals.addEntry(() -> new ChargedPunchWrapperGoal((MobEntity)this), 3.0F);
/*  63 */       goals.addEntry(() -> { GenkotsuMeteorWrapperGoal goal = new GenkotsuMeteorWrapperGoal((MobEntity)this); ((GenkotsuMeteorAbility)goal.getAbility()).addCanUseCheck(TrainerEntity.BELOW_90_CHECK); return (Goal)goal; }2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       goals.addEntry(() -> new SpinningBrawlWrapperGoal((MobEntity)this), 2.0F);
/*  69 */       goals.addEntry(() -> { SuplexWrapperGoal goal = new SuplexWrapperGoal((MobEntity)this); ((SuplexAbility)goal.getAbility()).addCanUseCheck(TrainerEntity.BELOW_90_CHECK); return (Goal)goal; }2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  75 */       MobsHelper.getRandomizedGoals((MobEntity)this, 5, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*  76 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 100);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  81 */     return OPEntity.createAttributes()
/*  82 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/*  83 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/*  84 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/*  85 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/*  86 */       .func_233815_a_(Attributes.field_233826_i_, 15.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/*  91 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/*  92 */     List<QuestId> availableQuests = new ArrayList<>();
/*     */     
/*  94 */     if (entityProps.isBrawler()) {
/*  95 */       availableQuests.addAll(ModQuests.BRAWLER_TRIALS);
/*     */     }
/*     */     
/*  98 */     return availableQuests;
/*     */   }
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 103 */     return HakiType.BUSOSHOKU;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\BrawlerTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */