/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.PunchRushWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class BoxingDugongEntity extends AbstractDugongEntity {
/*     */   public BoxingDugongEntity(EntityType type, World world) {
/*  38 */     this(type, world, false);
/*     */   }
/*     */   
/*     */   public BoxingDugongEntity(EntityType<? extends TameableEntity> type, World world, boolean spawnAsKid) {
/*  42 */     super(type, world);
/*     */     
/*  44 */     if (world != null && !world.field_72995_K) {
/*  45 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  46 */       props.setFightingStyle(ModValues.BRAWLER);
/*     */       
/*  48 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*     */       
/*  50 */       PunchRushWrapperGoal punchRushWrapper = new PunchRushWrapperGoal((MobEntity)this);
/*  51 */       ((PunchRushAbility)punchRushWrapper.getAbility()).setWaveDetails(10, 2);
/*     */       
/*  53 */       this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*  54 */       this.field_70714_bg.func_75776_a(0, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/*  55 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.2000000476837158D, true));
/*  56 */       this.field_70714_bg.func_75776_a(2, (Goal)new DamageAbsorptionWrapperGoal((MobEntity)this));
/*     */       
/*  58 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/*  59 */       goals.addEntry(() -> punchRushWrapper, 1.0F);
/*  60 */       goals.addEntry(() -> new HakaiHoWrapperGoal((MobEntity)this), 1.0F);
/*     */       
/*  62 */       MobsHelper.getRandomizedGoals((MobEntity)this, 2, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/*  63 */       if (!spawnAsKid) {
/*  64 */         MobsHelper.addBusoshokuHaki((MobEntity)this, 10);
/*     */       }
/*     */       
/*  67 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
/*  68 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, WrestlingDugongEntity.class, true, false));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  73 */     return OPEntity.createAttributes()
/*  74 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld level, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*     */     AgeableEntity.AgeableData ageableData;
/*  80 */     if (spawnData == null) {
/*  81 */       ageableData = new AgeableEntity.AgeableData(0.2F);
/*     */     }
/*     */ 
/*     */     
/*  85 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/*  86 */     func_110148_a(Attributes.field_233826_i_).func_111128_a(generateRandomArmor());
/*  87 */     func_110148_a(Attributes.field_233823_f_).func_111128_a(generateRandomAttack());
/*  88 */     func_110148_a(Attributes.field_233821_d_).func_111128_a(generateRandomSpeed());
/*     */     
/*  90 */     func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     
/*  92 */     return super.func_213386_a(level, difficulty, reason, (ILivingEntityData)ageableData, dataTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/*  97 */     BoxingDugongEntity offspring = new BoxingDugongEntity((EntityType)ModEntities.BOXING_DUGONG.get(), (World)world, true);
/*  98 */     setOffspringAttributes(mate, offspring);
/*     */     
/* 100 */     if (hasHakiLearned() && mate instanceof AbstractDugongEntity && ((AbstractDugongEntity)mate).hasHakiLearned()) {
/* 101 */       MobsHelper.addBusoshokuHaki((MobEntity)offspring, 80);
/*     */     }
/*     */     
/* 104 */     return (AgeableEntity)offspring;
/*     */   }
/*     */   
/*     */   protected void setOffspringAttributes(AgeableEntity mate, BoxingDugongEntity offspring) {
/* 108 */     double hp = func_233638_c_(Attributes.field_233818_a_) + mate.func_233638_c_(Attributes.field_233818_a_) + generateRandomHealth();
/* 109 */     offspring.func_110148_a(Attributes.field_233818_a_).func_111128_a(hp / 3.0D);
/*     */     
/* 111 */     double armor = func_233638_c_(Attributes.field_233826_i_) + mate.func_233638_c_(Attributes.field_233826_i_) + generateRandomArmor();
/* 112 */     offspring.func_110148_a(Attributes.field_233826_i_).func_111128_a(armor / 3.0D);
/*     */     
/* 114 */     double speed = func_233638_c_(Attributes.field_233821_d_) + mate.func_233638_c_(Attributes.field_233821_d_) + generateRandomSpeed();
/* 115 */     offspring.func_110148_a(Attributes.field_233821_d_).func_111128_a(speed / 3.0D);
/*     */     
/* 117 */     double damage = func_233638_c_(Attributes.field_233823_f_) + mate.func_233638_c_(Attributes.field_233823_f_) + generateRandomAttack();
/* 118 */     offspring.func_110148_a(Attributes.field_233823_f_).func_111128_a(damage / 3.0D);
/*     */   }
/*     */   
/*     */   protected double generateRandomHealth() {
/* 122 */     return 60.0D + this.field_70146_Z.nextDouble() * 20.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomArmor() {
/* 126 */     return 2.0D + this.field_70146_Z.nextDouble() * 4.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSpeed() {
/* 130 */     return 0.28D + this.field_70146_Z.nextDouble() * 0.06D;
/*     */   }
/*     */   
/*     */   protected double generateRandomAttack() {
/* 134 */     return 7.0D + this.field_70146_Z.nextDouble() * 3.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\BoxingDugongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */