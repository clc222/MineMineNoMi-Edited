/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.FollowOwnerGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SitGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.BrawlerPassiveBonusesAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.TameableHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.AlwaysActiveAbilityWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.HakaiHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.JishinHoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.AttackCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.FollowCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.GuardCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.commands.StayCommandGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongRageGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.dugong.DugongTrainGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WanderingDugongEntity extends AbstractDugongEntity implements IEntityAdditionalSpawnData {
/*  59 */   private int headScarId = 0;
/*  60 */   private int chestScarId = 0;
/*  61 */   private int armsScarId = 0;
/*  62 */   private int tailScarId = 0;
/*     */   
/*     */   public WanderingDugongEntity(EntityType<? extends TameableEntity> type, World world) {
/*  65 */     super(type, world);
/*     */     
/*  67 */     if (world != null && !world.field_72995_K) {
/*  68 */       this.headScarId = this.field_70146_Z.nextInt(MobsHelper.DUGONG_HEAD_SCARS_TEXTURES.length);
/*  69 */       this.chestScarId = this.field_70146_Z.nextInt(MobsHelper.DUGONG_CHEST_SCARS_TEXTURES.length);
/*  70 */       this.armsScarId = this.field_70146_Z.nextBoolean() ? this.field_70146_Z.nextInt(MobsHelper.DUGONG_ARMS_SCARS_TEXTURES.length) : -1;
/*  71 */       this.tailScarId = this.field_70146_Z.nextBoolean() ? this.field_70146_Z.nextInt(MobsHelper.DUGONG_TAIL_SCARS_TEXTURES.length) : -1;
/*     */       
/*  73 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  74 */       props.setFightingStyle(ModValues.BRAWLER);
/*     */       
/*  76 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/*     */       
/*  78 */       func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */       
/*  80 */       this.field_70714_bg.func_75776_a(0, (Goal)new AlwaysActiveAbilityWrapperGoal((MobEntity)this, BrawlerPassiveBonusesAbility.INSTANCE));
/*  81 */       this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  82 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.2000000476837158D, true));
/*     */       
/*  84 */       Predicate<LivingEntity> dugongCheck = target -> (target instanceof AbstractDugongEntity) ? ((target instanceof WanderingDugongEntity)) : true;
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
/*  96 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, true, true));
/*  97 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, true, true));
/*  98 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, CreatureEntity.class, 10, true, true, dugongCheck));
/*     */       
/* 100 */       WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 101 */       goals.addEntry(() -> new HakaiHoWrapperGoal((MobEntity)this), 3.0F);
/* 102 */       goals.addEntry(() -> new JishinHoWrapperGoal((MobEntity)this), 2.0F);
/* 103 */       goals.addEntry(() -> new ChargedPunchWrapperGoal((MobEntity)this), 2.0F);
/*     */       
/* 105 */       MobsHelper.getRandomizedGoals((MobEntity)this, 2, goals).forEach(goal -> this.field_70714_bg.func_75776_a(2, goal));
/* 106 */       MobsHelper.addBusoshokuHaki((MobEntity)this, 70);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/* 112 */     this.field_70714_bg.func_75776_a(0, (Goal)new StayCommandGoal((MobEntity)this));
/* 113 */     this.field_70714_bg.func_75776_a(0, (Goal)new FollowCommandGoal((MobEntity)this));
/* 114 */     this.field_70714_bg.func_75776_a(0, (Goal)new GuardCommandGoal((MobEntity)this));
/*     */     
/* 116 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/* 117 */     this.field_70714_bg.func_75776_a(0, (Goal)new SitGoal(this));
/* 118 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongRageGoal(this));
/* 119 */     this.field_70714_bg.func_75776_a(0, (Goal)new DugongTrainGoal(this));
/*     */     
/* 121 */     this.field_70714_bg.func_75776_a(3, (Goal)new FollowOwnerGoal(this, 1.0D, 6.0F, 2.0F, false));
/* 122 */     this.field_70714_bg.func_75776_a(4, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 0.8D));
/* 123 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 124 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractDugongEntity.class, 8.0F));
/* 125 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 127 */     this.field_70715_bh.func_75776_a(0, (Goal)new AttackCommandGoal((MobEntity)this));
/* 128 */     this.field_70715_bh.func_75776_a(0, (Goal)new StayCommandGoal((MobEntity)this));
/* 129 */     this.field_70715_bh.func_75776_a(0, (Goal)new FollowCommandGoal((MobEntity)this));
/* 130 */     this.field_70715_bh.func_75776_a(0, (Goal)new GuardCommandGoal((MobEntity)this));
/*     */     
/* 132 */     this.field_70715_bh.func_75776_a(1, (Goal)new TameableHurtByTargetGoal(this, new Class[0]));
/* 133 */     this.field_70715_bh.func_75776_a(1, (Goal)new OwnerHurtByTargetGoal(this));
/* 134 */     this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/* 135 */     this.field_70715_bh.func_75776_a(2, (Goal)new OwnerHurtTargetGoal(this));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 139 */     return OPEntity.createAttributes()
/* 140 */       .func_233815_a_(Attributes.field_233823_f_, 10.0D)
/* 141 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D)
/* 142 */       .func_233815_a_(Attributes.field_233821_d_, 0.3D)
/* 143 */       .func_233815_a_(Attributes.field_233818_a_, WyHelper.randomWithRange(60, 100))
/* 144 */       .func_233815_a_(Attributes.field_233826_i_, WyHelper.randomWithRange(4, 8));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_193101_c(PlayerEntity player) {
/* 149 */     if (this.field_70146_Z.nextInt(64) == 0) {
/* 150 */       super.func_193101_c(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getHeadScarId() {
/* 155 */     return this.headScarId;
/*     */   }
/*     */   
/*     */   public int getChestScarId() {
/* 159 */     return this.chestScarId;
/*     */   }
/*     */   
/*     */   public int getArmsScarId() {
/* 163 */     return this.armsScarId;
/*     */   }
/*     */   
/*     */   public int getTailScarId() {
/* 167 */     return this.tailScarId;
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 177 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 182 */     buffer.writeInt(this.headScarId);
/* 183 */     buffer.writeInt(this.chestScarId);
/* 184 */     buffer.writeInt(this.armsScarId);
/* 185 */     buffer.writeInt(this.tailScarId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 190 */     this.headScarId = buffer.readInt();
/* 191 */     this.chestScarId = buffer.readInt();
/* 192 */     this.armsScarId = buffer.readInt();
/* 193 */     this.tailScarId = buffer.readInt();
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<WanderingDugongEntity> entity, IWorld world, SpawnReason spawnType, BlockPos pos, Random random) {
/* 197 */     BlockPos worldSpawn = new BlockPos(world.func_72912_H().func_76079_c(), world.func_72912_H().func_76075_d(), world.func_72912_H().func_76074_e());
/* 198 */     if (pos.func_218141_a((Vector3i)worldSpawn, 512.0D)) {
/* 199 */       return false;
/*     */     }
/*     */     
/* 202 */     return MobEntity.func_223315_a(entity, world, spawnType, pos, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\WanderingDugongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */