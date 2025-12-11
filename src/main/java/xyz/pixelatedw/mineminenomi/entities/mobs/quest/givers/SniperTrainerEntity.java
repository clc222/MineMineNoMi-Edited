/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhaseManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.MeleeRangedStyleSwitchGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KaenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.KemuriBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.NemuriBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.RenpatsuNamariBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.SakuretsuSabotenBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TetsuBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.sniper.TokuyoAburaBoshiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SniperTrainerEntity extends TrainerEntity implements IHakiTrainer, IRangedAttackMob {
/*  53 */   private final NPCPhaseManager phaseManager = new NPCPhaseManager((MobEntity)this);
/*     */   private NPCPhase<SniperTrainerEntity> meleePhase;
/*     */   private NPCPhase<SniperTrainerEntity> rangePhase;
/*     */   
/*     */   public SniperTrainerEntity(EntityType type, World world) {
/*  58 */     super(type, world, MobsHelper.SNIPER_TRAINER_TEXTURES);
/*     */     
/*  60 */     if (world != null && !world.field_72995_K) {
/*  61 */       this.meleePhase = (NPCPhase<SniperTrainerEntity>)new SimplePhase("Melee Phase", (MobEntity)this);
/*  62 */       this.rangePhase = (NPCPhase<SniperTrainerEntity>)new SimplePhase("Range Phase", (MobEntity)this);
/*     */       
/*  64 */       getEntityStats().setFaction(ModValues.CIVILIAN);
/*  65 */       getEntityStats().setFightingStyle(ModValues.SNIPER);
/*  66 */       getEntityStats().setRace(ModValues.HUMAN);
/*     */       
/*  68 */       ItemStack bowStack = new ItemStack((IItemProvider)Items.field_151031_f);
/*  69 */       func_184201_a(EquipmentSlotType.MAINHAND, bowStack);
/*     */       
/*  71 */       setDoriki(2000.0D + WyHelper.randomWithRange(0, 1000));
/*  72 */       setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*     */       
/*  74 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(12.0D);
/*     */       
/*  76 */       this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*  77 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*     */       
/*  79 */       this.field_70714_bg.func_75776_a(0, (Goal)(new MeleeRangedStyleSwitchGoal((MobEntity)this)).forceMeleeEmptyHanded().addStyleSwitchEvent(100, this::onStyleSwitched));
/*     */       
/*  81 */       this.meleePhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.15D, true));
/*  82 */       this.meleePhase.addGoal(1, (Goal)new DashDodgeTargetGoal((MobEntity)this, 40.0F, 6.0F));
/*     */       
/*  84 */       this.rangePhase.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 60, 120.0F));
/*     */       
/*  86 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/*  87 */       goals.addEntry(() -> new KaenBoshiWrapperGoal((MobEntity)this, 20), 3.0F);
/*  88 */       goals.addEntry(() -> new SakuretsuSabotenBoshiWrapperGoal((MobEntity)this), 3.0F);
/*  89 */       goals.addEntry(() -> { TokuyoAburaBoshiWrapperGoal goal = new TokuyoAburaBoshiWrapperGoal((MobEntity)this, 20); ((TokuyoAburaBoshiAbility)goal.getAbility()).addCanUseCheck(TrainerEntity.BELOW_90_CHECK); return (Goal)goal; }3.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       goals.addEntry(() -> new TetsuBoshiWrapperGoal((MobEntity)this), 2.0F);
/*  95 */       goals.addEntry(() -> { RenpatsuNamariBoshiWrapperGoal goal = new RenpatsuNamariBoshiWrapperGoal((MobEntity)this, 60); ((RenpatsuNamariBoshiAbility)goal.getAbility()).addCanUseCheck(TrainerEntity.BELOW_90_CHECK); return (Goal)goal; }2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       goals.addEntry(() -> new KemuriBoshiWrapperGoal((MobEntity)this), 1.0F);
/* 101 */       goals.addEntry(() -> new NemuriBoshiWrapperGoal((MobEntity)this, 60), 1.0F);
/*     */       
/* 103 */       MobsHelper.getRandomizedGoals((MobEntity)this, 5, goals).forEach(goal -> this.rangePhase.addGoal(2, goal));
/* 104 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 100);
/*     */       
/* 106 */       this.phaseManager.setPhase(this.rangePhase);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 111 */     return OPEntity.createAttributes()
/* 112 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 113 */       .func_233815_a_(Attributes.field_233821_d_, 0.2800000011920929D)
/* 114 */       .func_233815_a_(Attributes.field_233823_f_, 5.0D)
/* 115 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 116 */       .func_233815_a_(Attributes.field_233826_i_, 15.0D);
/*     */   }
/*     */   
/*     */   private void onStyleSwitched(LivingEntity entity, boolean isRanged) {
/* 120 */     if (isRanged) {
/* 121 */       this.phaseManager.setPhase(this.rangePhase);
/*     */     } else {
/*     */       
/* 124 */       this.phaseManager.setPhase(this.meleePhase);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 130 */     this.phaseManager.tick();
/*     */   }
/*     */   
/*     */   public void func_82196_d(LivingEntity target, float velocity) {
/*     */     KairosekiBulletProjectile kairosekiBulletProjectile;
/* 135 */     if (func_184614_ca() == null || !func_184614_ca().func_77973_b().equals(Items.field_151031_f)) {
/*     */       return;
/*     */     }
/*     */     
/* 139 */     NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 140 */     normalBulletProjectile.setDamage(5.0F);
/* 141 */     if (isAboveNormalDifficulty()) {
/* 142 */       kairosekiBulletProjectile = new KairosekiBulletProjectile(this.field_70170_p, (LivingEntity)this);
/* 143 */       kairosekiBulletProjectile.setDamage(7.0F);
/*     */     } 
/*     */     
/* 146 */     kairosekiBulletProjectile.func_234612_a_((Entity)this, this.field_70125_A, this.field_70177_z, 0.0F, 2.0F, 0.0F);
/* 147 */     this.field_70170_p.func_217376_c((Entity)kairosekiBulletProjectile);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/* 152 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 153 */     List<QuestId> availableQuests = new ArrayList<>();
/*     */     
/* 155 */     if (entityProps.isSniper()) {
/* 156 */       availableQuests.addAll((Collection<? extends QuestId>)ModQuests.SNIPER_TRIALS.stream().map(reg -> reg).collect(Collectors.toList()));
/*     */     }
/*     */     
/* 159 */     return availableQuests;
/*     */   }
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 164 */     return HakiType.BUSOSHOKU;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\SniperTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */