/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.passive.OcelotEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.passive.horse.AbstractHorseEntity;
/*     */ import net.minecraft.util.EntityPredicates;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.ITamableEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModEntityPredicates
/*     */ {
/*     */   public static final Predicate<Entity> ENTITY_EXISTS;
/*     */   
/*     */   static {
/*  43 */     ENTITY_EXISTS = (entity -> (entity != null));
/*  44 */   } public static final Predicate<Entity> IS_ALIVE_AND_SURVIVAL = ENTITY_EXISTS.and(EntityPredicates.field_188444_d).and(EntityPredicates.field_94557_a); static {
/*  45 */     IS_ENTITY_HARMLESS = (target -> {
/*     */         EntityClassification classification = target.func_200600_R().func_220339_d();
/*     */         
/*  48 */         return (classification.equals(EntityClassification.WATER_AMBIENT) || classification.equals(EntityClassification.AMBIENT)) ? true : (
/*     */ 
/*     */ 
/*     */           
/*  52 */           (target instanceof net.minecraft.entity.passive.AnimalEntity || target instanceof net.minecraft.entity.passive.AmbientEntity || target instanceof net.minecraft.entity.passive.fish.AbstractFishEntity || target instanceof net.minecraft.entity.passive.GolemEntity) ? true : (
/*     */ 
/*     */ 
/*     */           
/*  56 */           (target instanceof net.minecraft.entity.passive.SquidEntity || target instanceof xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity)));
/*     */       });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     IS_INVISIBLE = (target -> target.func_82150_aj());
/*     */ 
/*     */ 
/*     */     
/*  66 */     TARGET_HAS_ISSUED_BOUNTY = (targetEntity -> {
/*     */         if (!(targetEntity instanceof LivingEntity)) {
/*     */           return false;
/*     */         }
/*     */         LivingEntity target = (LivingEntity)targetEntity;
/*     */         ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */         IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */         if (targetProps.isPirate() || targetProps.isRevolutionary() || targetProps.isBandit()) {
/*     */           if (StructuresHelper.isInsideModStructure((ServerWorld)target.field_70170_p, target.func_233580_cy_(), StructuresHelper.StructureFaction.MARINE)) {
/*     */             return true;
/*     */           }
/*     */           if (target instanceof net.minecraft.entity.player.PlayerEntity && target.func_130014_f_().func_175659_aa().func_151525_a() <= Difficulty.NORMAL.func_151525_a() && !worldData.hasIssuedBounty(target)) {
/*     */             return false;
/*     */           }
/*     */         } 
/*     */         return true;
/*     */       });
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Predicate<Entity> IS_ENTITY_HARMLESS;
/*     */   
/*     */   public static final Predicate<Entity> IS_INVISIBLE;
/*     */   
/*     */   public static final Predicate<Entity> TARGET_HAS_ISSUED_BOUNTY;
/*     */   
/*     */   public static Predicate<Entity> canBeSeenBy(LivingEntity entity) {
/*  93 */     return IS_ALIVE_AND_SURVIVAL.and(target -> entity.func_70685_l(target));
/*     */   }
/*     */   
/*     */   public static Predicate<Entity> canSee(LivingEntity entity) {
/*  97 */     return IS_ALIVE_AND_SURVIVAL.and(target -> (target instanceof LivingEntity) ? ((LivingEntity)target).func_70685_l((Entity)entity) : false);
/*     */   }
/*     */   
/*     */   public static Predicate<Entity> hasTargetWithinDistance(LivingEntity entity, float distance) {
/* 101 */     return canSee(entity).and(EntityPredicates.func_188443_a((entity.func_213303_ch()).field_72450_a, (entity.func_213303_ch()).field_72448_b, (entity.func_213303_ch()).field_72449_c, distance));
/*     */   }
/*     */   
/*     */   public static Predicate<Entity> canBeTamed() {
/* 105 */     return target -> 
/* 106 */       (target instanceof TameableEntity || target instanceof AbstractHorseEntity || target instanceof OcelotEntity || target instanceof KungFuDugongEntity || target instanceof YagaraBullEntity || target instanceof ICommandReceiver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Predicate<Entity> isTamedBy(@Nullable LivingEntity tamer) {
/* 115 */     return target -> {
/*     */         if (tamer == null) {
/*     */           return false;
/*     */         }
/*     */         
/*     */         if (!canBeTamed().test(target)) {
/*     */           return false;
/*     */         }
/*     */         
/*     */         if (target instanceof TameableEntity) {
/* 125 */           return (((TameableEntity)target).func_70902_q() != null && ((TameableEntity)target).func_70902_q().equals(tamer));
/*     */         }
/*     */         
/*     */         if (target instanceof AbstractHorseEntity) {
/* 129 */           return (((AbstractHorseEntity)target).func_184780_dh() != null && ((AbstractHorseEntity)target).func_184780_dh().equals(tamer.func_110124_au()));
/*     */         }
/*     */         
/*     */         if (target instanceof OcelotEntity) {
/*     */           Method method = ObfuscationReflectionHelper.findMethod(OcelotEntity.class, "func_213530_dX", new Class[0]);
/*     */           
/*     */           try {
/*     */             method.setAccessible(true);
/*     */             
/*     */             return ((Boolean)method.invoke(target, new Object[0])).booleanValue();
/* 139 */           } catch (Exception e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */         
/*     */         if (target instanceof YagaraBullEntity) {
/* 145 */           return (((YagaraBullEntity)target).func_70902_q() != null && ((YagaraBullEntity)target).func_70902_q().equals(tamer));
/*     */         }
/*     */         
/*     */         if (target instanceof KungFuDugongEntity) {
/* 149 */           return (((KungFuDugongEntity)target).func_70902_q() != null && ((KungFuDugongEntity)target).func_70902_q().equals(tamer));
/*     */         }
/*     */         
/*     */         if (target instanceof ITamableEntity) {
/*     */           ITamableEntity tamableEntity = (ITamableEntity)target;
/* 154 */           return (tamableEntity.getOwnerIfAlive() != null && tamableEntity.getOwner().equals(tamer));
/*     */         } 
/*     */         
/* 157 */         return (target instanceof ICommandReceiver && ((ICommandReceiver)target).canReceiveCommandFrom(tamer));
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Predicate<Entity> getEnemyFactions(LivingEntity entity) {
/* 168 */     if (entity == null) {
/* 169 */       return (Predicate<Entity>)Predicates.alwaysTrue();
/*     */     }
/*     */     
/* 172 */     return targetEntity -> {
/*     */         if (targetEntity == null) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         if (!(targetEntity instanceof LivingEntity)) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         if (entity == targetEntity) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         LivingEntity target = (LivingEntity)targetEntity;
/*     */ 
/*     */         
/*     */         if (entity.field_70170_p instanceof ServerWorld && WyHelper.isInChallengeDimension(entity.field_70170_p)) {
/*     */           InProgressChallenge challenge = ChallengesWorldData.get().getInProgressChallengeFor((ServerWorld)entity.field_70170_p);
/*     */ 
/*     */           
/*     */           if (challenge != null) {
/*     */             if (challenge.getGroup().contains(entity) && !challenge.getGroup().contains(target)) {
/*     */               return true;
/*     */             }
/*     */ 
/*     */             
/*     */             if (challenge.getEnemies().contains(entity) && !challenge.getEnemies().contains(target)) {
/*     */               return true;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/*     */           return false;
/*     */         } 
/*     */ 
/*     */         
/*     */         if (target.func_184207_aI() && target.func_184179_bs() != null && getFriendlyFactions(entity).test(target.func_184179_bs())) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         boolean isSpectating = !EntityPredicates.field_180132_d.test(target);
/*     */ 
/*     */         
/*     */         if (isSpectating) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         IEntityStats livingProps = EntityStatsCapability.get(entity);
/*     */ 
/*     */         
/*     */         IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */ 
/*     */         
/*     */         if (livingProps.isRogue() || targetProps.isRogue()) {
/*     */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         if (isTamedBy(entity).test(target)) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         if (canBeTamed().test(entity) && isTamedBy(MobsHelper.getTamer(entity)).test(target)) {
/*     */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */ 
/*     */         
/*     */         if (livingProps.isPirate() && targetProps.isPirate()) {
/*     */           if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity && target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity) {
/*     */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           if (entity instanceof OPEntity && target instanceof OPEntity && ((OPEntity)entity).getCrew().equals(((OPEntity)target).getCrew())) {
/*     */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           if (worldData != null) {
/*     */             Crew livingCrew = worldData.getCrewWithMember(entity.func_110124_au());
/*     */ 
/*     */             
/*     */             if (livingCrew == null) {
/*     */               return true;
/*     */             }
/*     */             
/*     */             Crew targetCrew = worldData.getCrewWithMember(target.func_110124_au());
/*     */             
/*     */             return (targetCrew == null) ? true : (!livingCrew.equals(targetCrew));
/*     */           } 
/*     */         } 
/*     */         
/* 273 */         boolean isCivilian = (target instanceof net.minecraft.entity.merchant.villager.AbstractVillagerEntity || targetProps.isCivilian());
/*     */         if ((livingProps.isMarine() || livingProps.isBountyHunter()) && (targetProps.isMarine() || targetProps.isBountyHunter() || isCivilian)) {
/*     */           return false;
/*     */         }
/*     */         if (livingProps.isRevolutionary() && (targetProps.isRevolutionary() || isCivilian)) {
/*     */           return false;
/*     */         }
/*     */         if (livingProps.isBandit()) {
/*     */           if (targetProps.isBandit()) {
/*     */             return false;
/*     */           }
/*     */         }
/*     */         return true;
/*     */       };
/*     */   }
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
/*     */   public static Predicate<Entity> getFriendlyFactions(LivingEntity entity) {
/* 299 */     return getEnemyFactions(entity).negate();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModEntityPredicates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */