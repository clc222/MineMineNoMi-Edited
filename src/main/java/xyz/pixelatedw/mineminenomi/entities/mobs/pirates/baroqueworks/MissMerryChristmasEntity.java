/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.OPBossEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FindPartnerGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.IGoalPartner;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RandomWalkingAroundTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiFullbodyHardeningWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.SoruWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas.CreateMoleHoleGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas.MoleDiggingGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas.MoleGrabAndDragGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.missmerrychristmas.MoleHoleJumpGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class MissMerryChristmasEntity extends OPBossEntity<MissMerryChristmasEntity> implements IGoalPartner<Mr4Entity> {
/*  42 */   private Set<BlockPos> holePositions = new HashSet<>();
/*     */   
/*     */   private Mr4Entity partner;
/*     */   private NPCPhase<MissMerryChristmasEntity> abovegroundPhase;
/*     */   private NPCPhase<MissMerryChristmasEntity> undergroundPhase;
/*     */   private MoleHoleJumpGoal moleHoleJumpGoal;
/*     */   
/*     */   public MissMerryChristmasEntity(EntityType type, World world) {
/*  50 */     super(type, world);
/*     */   }
/*     */   
/*     */   public MissMerryChristmasEntity(InProgressChallenge challenge) {
/*  54 */     super((EntityType)ModEntities.MISS_MERRY_CHRISTMAS.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  59 */     this.abovegroundPhase = (NPCPhase<MissMerryChristmasEntity>)new SimplePhase("Above Ground Phase", (MobEntity)this);
/*  60 */     this.undergroundPhase = (NPCPhase<MissMerryChristmasEntity>)new SimplePhase("Under Ground Phase", (MobEntity)this);
/*     */     
/*  62 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  64 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  65 */     this.entityStats.setRace(ModValues.HUMAN);
/*  66 */     this.devilFruitData.setDevilFruit(ModAbilities.MOGU_MOGU_NO_MI);
/*     */     
/*  68 */     this.entityStats.setDoriki(2000.0D);
/*  69 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*     */     
/*  71 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(1.0D);
/*  72 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */ 
/*     */     
/*  75 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/*  77 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindPartnerGoal((MobEntity)this, (EntityType)ModEntities.MR4.get()));
/*  78 */     this.field_70714_bg.func_75776_a(0, (Goal)new MoleDiggingGoal(this));
/*     */     
/*  80 */     this.abovegroundPhase.addGoal(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.100000023841858D, true));
/*  81 */     this.abovegroundPhase.addGoal(3, (Goal)new ChargedPunchWrapperGoal((MobEntity)this));
/*     */     
/*  83 */     this.undergroundPhase.addGoal(0, (Goal)new CreateMoleHoleGoal(this));
/*  84 */     this.undergroundPhase.addGoal(1, (Goal)new RandomWalkingAroundTargetGoal((CreatureEntity)this, 1.100000023841858D, 600, 100));
/*  85 */     this.undergroundPhase.addGoal(3, (Goal)new MoleGrabAndDragGoal(this));
/*     */     
/*  87 */     getPhaseManager().setPhase(this.abovegroundPhase);
/*     */     
/*  89 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  90 */       this.moleHoleJumpGoal = new MoleHoleJumpGoal(this);
/*  91 */       this.undergroundPhase.addGoal(1, (Goal)this.moleHoleJumpGoal);
/*     */     } else {
/*     */       
/*  94 */       this.field_70714_bg.func_75776_a(1, (Goal)new BusoshokuHakiFullbodyHardeningWrapperGoal((MobEntity)this));
/*     */       
/*  96 */       this.abovegroundPhase.addGoal(2, (Goal)new SoruWrapperGoal((MobEntity)this));
/*  97 */       this.abovegroundPhase.addGoal(3, (Goal)new HakaiHoWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 102 */     return OPEntity.createAttributes()
/* 103 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 104 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 105 */       .func_233815_a_(Attributes.field_233823_f_, 3.0D)
/* 106 */       .func_233815_a_(Attributes.field_233818_a_, 120.0D)
/* 107 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D)
/* 108 */       .func_233815_a_(Attributes.field_233820_c_, 0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 113 */     this.field_70143_R = 0.0F;
/* 114 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public Mr4Entity getPartner() {
/* 119 */     return this.partner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPartner(Mr4Entity partner) {
/* 124 */     this.partner = partner;
/*     */   }
/*     */   
/*     */   public void addHole(BlockPos pos) {
/* 128 */     this.holePositions.add(pos);
/* 129 */     AbilityHelper.createFilledCube(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 1, 10, 1, Blocks.field_150350_a, DefaultProtectionRules.CORE_FOLIAGE_ORE);
/* 130 */     System.out.println("New hole at pos: " + pos.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> getHoles() {
/* 135 */     return this.holePositions;
/*     */   }
/*     */   
/*     */   public void setUnderGround() {
/* 139 */     System.out.println("Under Ground");
/* 140 */     getPhaseManager().setPhase(this.undergroundPhase);
/*     */   }
/*     */   
/*     */   public void setAboveGround() {
/* 144 */     System.out.println("Above Ground");
/* 145 */     getPhaseManager().setPhase(this.abovegroundPhase);
/*     */   }
/*     */   
/*     */   public boolean isUnderground() {
/* 149 */     return getPhaseManager().getCurrentPhase().equals(this.undergroundPhase);
/*     */   }
/*     */   
/*     */   public boolean isUndergroundStandby() {
/* 153 */     if (!isUnderground()) {
/* 154 */       return false;
/*     */     }
/*     */     
/* 157 */     if (this.moleHoleJumpGoal != null && !this.moleHoleJumpGoal.hasTimePassedSinceStart(40.0F)) {
/* 158 */       return false;
/*     */     }
/*     */     
/* 161 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\MissMerryChristmasEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */