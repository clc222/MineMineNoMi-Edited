/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IThreatLevel;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.cyborg.RadicalBeamWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ 
/*     */ public class PacifistaEntity extends OPEntity implements ICommandReceiver, IThreatLevel {
/*     */   private long lastCommandTime;
/*  35 */   private NPCCommand currentCommand = NPCCommand.IDLE; private LivingEntity lastCommandSender;
/*     */   
/*     */   public PacifistaEntity(World world) {
/*  38 */     this((EntityType)ModEntities.PACIFISTA.get(), world);
/*     */   }
/*     */   
/*     */   public PacifistaEntity(EntityType type, World world) {
/*  42 */     super(type, world);
/*     */     
/*  44 */     if (world != null && !world.field_72995_K) {
/*  45 */       getEntityStats().setRace(ModValues.CYBORG);
/*  46 */       getEntityStats().setFaction(ModValues.MARINE);
/*  47 */       getEntityStats().setFightingStyle(ModValues.BRAWLER);
/*     */       
/*  49 */       MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*  50 */       CommandAbility.addCommandGoals((MobEntity)this);
/*     */       
/*  52 */       this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  53 */       this.field_70714_bg.func_75776_a(2, (Goal)new RadicalBeamWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  58 */     return OPEntity.createAttributes()
/*  59 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/*  60 */       .func_233815_a_(Attributes.field_233821_d_, 0.25D)
/*  61 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/*  62 */       .func_233815_a_(Attributes.field_233818_a_, 150.0D)
/*  63 */       .func_233815_a_(Attributes.field_233826_i_, 20.0D)
/*  64 */       .func_233815_a_(Attributes.field_233820_c_, 0.8D)
/*  65 */       .func_233815_a_(Attributes.field_233824_g_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getThreatLevel() {
/*  70 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/*  75 */     super.func_70645_a(cause);
/*  76 */     if (!this.field_70170_p.field_72995_K) {
/*  77 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)this, this.field_70170_p, func_226277_ct_(), func_226278_cu_(), func_226281_cx_(), 3.0F);
/*  78 */       explosion.setStaticDamage(5.0F);
/*  79 */       explosion.setDestroyBlocks(false);
/*  80 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/*  81 */       explosion.doExplosion();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/*  87 */     IEntityStats props = EntityStatsCapability.get(commandSender);
/*  88 */     if (!props.isMarine()) {
/*  89 */       return false;
/*     */     }
/*  91 */     if (!props.hasMarineRank(FactionHelper.MarineRank.COMMODORE)) {
/*  92 */       return false;
/*     */     }
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/*  99 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 100 */     this.lastCommandSender = commandSender;
/* 101 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMaintainCommand() {
/* 106 */     if (this.lastCommandSender != null && this.lastCommandSender.func_70089_S() && 
/* 107 */       EntityStatsCapability.get(this.lastCommandSender).isRogue()) {
/* 108 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 117 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 123 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 128 */     return this.lastCommandTime;
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<? extends OPEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
/* 132 */     double chance = random.nextDouble() * 100.0D;
/* 133 */     if (chance > CommonConfig.INSTANCE.getPacifistaSpawnChance()) {
/* 134 */       return false;
/*     */     }
/*     */     
/* 137 */     return OPEntity.checkSpawnRules(type, world, reason, pos, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\PacifistaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */