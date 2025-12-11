/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*    */ import net.minecraft.entity.passive.IronGolemEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.GlobalPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ import net.minecraftforge.event.world.ExplosionEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.IGoalMemoriesEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMemoryModuleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.IExplosionMixin;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class EntityGoalEvents {
/*    */   private static final int LAST_EXPLOSION_TICKS = 6000;
/*    */   
/*    */   static {
/* 31 */     RABBIT_HELP_CALL_PREDICATE = (new TargetsPredicate()).selector(entity -> entity instanceof LapahnEntity);
/*    */   } private static final TargetsPredicate RABBIT_HELP_CALL_PREDICATE;
/*    */   @SubscribeEvent
/*    */   public static void onEntitySpawn(EntityJoinWorldEvent event) {
/* 35 */     Entity entity = event.getEntity();
/* 36 */     if (!(event.getWorld()).field_72995_K) {
/* 37 */       if (event.getEntity() instanceof MobEntity) {
/* 38 */         StatsGainEvents.setSpawnStats((MobEntity)event.getEntity());
/*    */       }
/*    */       
/* 41 */       if (entity instanceof IronGolemEntity) {
/* 42 */         IronGolemEntity golem = (IronGolemEntity)entity;
/* 43 */         golem.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)golem, MobEntity.class, 5, false, false, target -> 
/* 44 */               (target instanceof OPEntity && (((OPEntity)target).getEntityStats().isBandit() || ((OPEntity)target).getEntityStats().isPirate()))));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onExplosionDetonates(ExplosionEvent.Detonate event) {
/* 57 */     BlockPos pos = new BlockPos(event.getExplosion().getPosition());
/*    */     
/* 59 */     float radius = ((IExplosionMixin)event.getExplosion()).getRadius() * 8.0F;
/*    */     
/* 61 */     List<LivingEntity> targets = WyHelper.getNearbyEntities(event.getExplosion().getPosition(), (IWorld)event.getWorld(), radius, radius, radius, null, new Class[] { LivingEntity.class });
/* 62 */     for (LivingEntity target : targets) {
/* 63 */       if (target instanceof IGoalMemoriesEntity) {
/* 64 */         ((IGoalMemoriesEntity)target).setMemoryWithExpiry((MemoryModuleType)ModMemoryModuleTypes.LAST_EXPLOSION_HEARD.get(), GlobalPos.func_239648_a_(event.getWorld().func_234923_W_(), pos), 6000L);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onHurtEntity(LivingHurtEvent event) {
/* 71 */     LivingEntity attacker = null;
/*    */     
/* 73 */     Entity directAttacker = event.getSource().func_76364_f();
/* 74 */     if (directAttacker instanceof LivingEntity) {
/* 75 */       attacker = (LivingEntity)directAttacker;
/*    */     }
/*    */     
/* 78 */     if (event.getEntityLiving() instanceof net.minecraft.entity.passive.RabbitEntity) {
/* 79 */       List<LapahnEntity> lapahns = TargetHelper.getEntitiesInArea(event.getEntityLiving(), 10.0D, RABBIT_HELP_CALL_PREDICATE, new Class[] { LapahnEntity.class });
/* 80 */       for (LapahnEntity lapahn : lapahns) {
/* 81 */         if (lapahn.func_70089_S() && (lapahn.func_70638_az() == null || !lapahn.func_70638_az().func_70089_S()))
/* 82 */           lapahn.func_70624_b(attacker); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\EntityGoalEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */