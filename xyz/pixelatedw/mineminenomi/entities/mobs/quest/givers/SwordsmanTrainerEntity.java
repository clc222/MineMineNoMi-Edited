/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.HiryuKaenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.SanbyakurokujuPoundHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SwordsmanTrainerEntity extends TrainerEntity implements IHakiTrainer {
/*  45 */   protected static final List<Supplier<? extends Item>> SWORDS = Arrays.asList((Supplier<? extends Item>[])new Supplier[] { (Supplier)ModWeapons.SANDAI_KITETSU, (Supplier)ModWeapons.NIDAI_KITETSU, (Supplier)ModWeapons.WADO_ICHIMONJI, () -> Items.field_151048_u });
/*     */ 
/*     */   
/*     */   public SwordsmanTrainerEntity(EntityType type, World world) {
/*  49 */     super(type, world, MobsHelper.SWORDSMAN_TRAINER_TEXTURES);
/*     */     
/*  51 */     if (world != null && !world.field_72995_K) {
/*  52 */       getEntityStats().setFaction(ModValues.CIVILIAN);
/*  53 */       getEntityStats().setFightingStyle(ModValues.SWORDSMAN);
/*  54 */       getEntityStats().setRace(ModValues.HUMAN);
/*     */       
/*  56 */       ItemStack randomSword = new ItemStack(((Supplier<IItemProvider>)SWORDS.get(this.field_70146_Z.nextInt(SWORDS.size()))).get());
/*  57 */       randomSword.func_196082_o().func_74757_a("isClone", true);
/*  58 */       func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/*     */       
/*  60 */       setDoriki(2000.0D + WyHelper.randomWithRange(0, 1000));
/*  61 */       setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*     */       
/*  63 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(12.0D);
/*     */       
/*  65 */       this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*  66 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*     */       
/*  68 */       this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  69 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.2D, true));
/*     */       
/*  71 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/*  72 */       goals.addEntry(() -> new ShiShishiSonsonWrapperGoal((MobEntity)this), 3.0F);
/*  73 */       goals.addEntry(() -> { SanbyakurokujuPoundHoWrapperGoal goal = new SanbyakurokujuPoundHoWrapperGoal((MobEntity)this); ((SanbyakurokujuPoundHoAbility)goal.getAbility()).addCanUseCheck(TrainerEntity.BELOW_90_CHECK); return (Goal)goal; }3.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  78 */       goals.addEntry(() -> new HiryuKaenWrapperGoal((MobEntity)this), 3.0F);
/*  79 */       goals.addEntry(() -> new OTatsumakiWrapperGoal((MobEntity)this), 2.0F);
/*  80 */       goals.addEntry(() -> { YakkodoriWrapperGoal goal = new YakkodoriWrapperGoal((MobEntity)this); ((YakkodoriAbility)goal.getAbility()).addCanUseCheck(TrainerEntity.BELOW_90_CHECK); return (Goal)goal; }2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       MobsHelper.getRandomizedGoals((MobEntity)this, 5, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*  87 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 100);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  92 */     return OPEntity.createAttributes()
/*  93 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/*  94 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/*  95 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/*  96 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/*  97 */       .func_233815_a_(Attributes.field_233826_i_, 15.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/* 102 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 103 */     IQuestData questProps = QuestDataCapability.get(player);
/* 104 */     List<QuestId> availableQuests = new ArrayList<>();
/*     */     
/* 106 */     if (entityProps.isSwordsman()) {
/* 107 */       availableQuests.addAll((Collection<? extends QuestId>)ModQuests.SWORDSMAN_TRIALS.stream().map(reg -> reg).collect(Collectors.toList()));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     return availableQuests;
/*     */   }
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 130 */     return HakiType.BUSOSHOKU;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\SwordsmanTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */