/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class KungFuDugongEntity extends AbstractDugongEntity {
/*     */   public KungFuDugongEntity(EntityType type, World world) {
/*  30 */     this(type, world, false);
/*     */   }
/*     */   
/*     */   public KungFuDugongEntity(EntityType<? extends TameableEntity> type, World world, boolean spawnAsKid) {
/*  34 */     super(type, world);
/*     */     
/*  36 */     if (world != null && !world.field_72995_K) {
/*  37 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  38 */       props.setFightingStyle(ModValues.BRAWLER);
/*     */       
/*  40 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(1.0D);
/*     */ 
/*     */       
/*  43 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/*  44 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(generateRandomArmor());
/*  45 */       func_110148_a(Attributes.field_233821_d_).func_111128_a(generateRandomSpeed());
/*  46 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(generateRandomAttack());
/*     */       
/*  48 */       this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*  49 */       this.field_70714_bg.func_75776_a(0, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/*  50 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.100000023841858D, true));
/*     */       
/*  52 */       if (!spawnAsKid) {
/*  53 */         MobsHelper.addBusoshokuHaki((MobEntity)this, 10);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  59 */     return OPEntity.createAttributes()
/*  60 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld level, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*     */     AgeableEntity.AgeableData ageableData;
/*  66 */     if (spawnData == null) {
/*  67 */       ageableData = new AgeableEntity.AgeableData(0.2F);
/*     */     }
/*     */ 
/*     */     
/*  71 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/*  72 */     func_110148_a(Attributes.field_233826_i_).func_111128_a(generateRandomArmor());
/*  73 */     func_110148_a(Attributes.field_233823_f_).func_111128_a(generateRandomAttack());
/*  74 */     func_110148_a(Attributes.field_233821_d_).func_111128_a(generateRandomSpeed());
/*     */     
/*  76 */     func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     
/*  78 */     return super.func_213386_a(level, difficulty, reason, (ILivingEntityData)ageableData, dataTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/*  83 */     KungFuDugongEntity offspring = new KungFuDugongEntity((EntityType)ModEntities.KUNG_FU_DUGONG.get(), (World)world, true);
/*  84 */     setOffspringAttributes(mate, offspring);
/*     */     
/*  86 */     if (hasHakiLearned() && mate instanceof AbstractDugongEntity && ((AbstractDugongEntity)mate).hasHakiLearned()) {
/*  87 */       MobsHelper.addBusoshokuHaki((MobEntity)offspring, 80);
/*     */     }
/*     */     
/*  90 */     return (AgeableEntity)offspring;
/*     */   }
/*     */   
/*     */   protected void setOffspringAttributes(AgeableEntity mate, KungFuDugongEntity offspring) {
/*  94 */     double hp = func_233638_c_(Attributes.field_233818_a_) + mate.func_233638_c_(Attributes.field_233818_a_) + generateRandomHealth();
/*  95 */     offspring.func_110148_a(Attributes.field_233818_a_).func_111128_a(hp / 3.0D);
/*     */     
/*  97 */     double armor = func_233638_c_(Attributes.field_233826_i_) + mate.func_233638_c_(Attributes.field_233826_i_) + generateRandomArmor();
/*  98 */     offspring.func_110148_a(Attributes.field_233826_i_).func_111128_a(armor / 3.0D);
/*     */     
/* 100 */     double speed = func_233638_c_(Attributes.field_233821_d_) + mate.func_233638_c_(Attributes.field_233821_d_) + generateRandomSpeed();
/* 101 */     offspring.func_110148_a(Attributes.field_233821_d_).func_111128_a(speed / 3.0D);
/*     */     
/* 103 */     double damage = func_233638_c_(Attributes.field_233823_f_) + mate.func_233638_c_(Attributes.field_233823_f_) + generateRandomAttack();
/* 104 */     offspring.func_110148_a(Attributes.field_233823_f_).func_111128_a(damage / 3.0D);
/*     */   }
/*     */   
/*     */   protected double generateRandomHealth() {
/* 108 */     return 30.0D + this.field_70146_Z.nextDouble() * 20.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomArmor() {
/* 112 */     return 2.0D + this.field_70146_Z.nextDouble() * 2.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSpeed() {
/* 116 */     return 0.28D + this.field_70146_Z.nextDouble() * 0.05D;
/*     */   }
/*     */   
/*     */   protected double generateRandomAttack() {
/* 120 */     return 5.0D + this.field_70146_Z.nextDouble() * 3.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\KungFuDugongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */