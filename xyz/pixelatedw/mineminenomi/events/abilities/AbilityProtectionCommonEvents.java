/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraftforge.common.util.BlockSnapshot;
/*    */ import net.minecraftforge.event.TickEvent;
/*    */ import net.minecraftforge.event.world.ExplosionEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.ProjectileHitEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class AbilityProtectionCommonEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void restoreWorld(TickEvent.WorldTickEvent event) {
/* 27 */     if (event.world == null || event.phase == TickEvent.Phase.START || event.world.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     event.world.func_217381_Z().func_76320_a("world restoration");
/* 32 */     ProtectedAreasData worldData = ProtectedAreasData.get(event.world);
/* 33 */     for (ProtectedArea area : worldData.getAllRestrictions().values()) {
/*    */       
/*    */       try {
/* 36 */         area.restoreBlocks(event.world);
/*    */       }
/* 38 */       catch (Exception e) {
/* 39 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 42 */     event.world.func_217381_Z().func_76319_b();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
/* 47 */     if (!(event.getWorld()).field_72995_K) {
/* 48 */       ProtectedAreasData worldData = ProtectedAreasData.get(event.getWorld());
/* 49 */       Vector3d pos = event.getExplosion().getPosition();
/* 50 */       ProtectedArea area = worldData.getProtectedArea((int)pos.field_72450_a, (int)pos.field_72448_b, (int)pos.field_72449_c);
/* 51 */       if (area != null) {
/* 52 */         if (!area.canDestroyBlocks()) {
/* 53 */           event.getAffectedBlocks().clear();
/*    */         
/*    */         }
/* 56 */         else if (area.canRestoreBlocks()) {
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 61 */           Map<BlockPlacingHelper.DistanceBlockPos, ProtectedArea.RestorationEntry> map = (Map<BlockPlacingHelper.DistanceBlockPos, ProtectedArea.RestorationEntry>)event.getAffectedBlocks().stream().filter(p -> (!event.getWorld().func_180495_p(p).func_196958_f() && area.isInside(p.func_177958_n(), p.func_177956_o(), p.func_177952_p()))).map(p -> new BlockPlacingHelper.DistanceBlockPos(p.func_177958_n(), p.func_177956_o(), p.func_177952_p())).distinct().collect(Collectors.toMap(p -> p, p -> new ProtectedArea.RestorationEntry(event.getWorld().func_82737_E(), BlockSnapshot.create(event.getWorld().func_234923_W_(), (IWorld)event.getWorld(), (BlockPos)p, 2))));
/* 62 */           area.queueForRestoration(map);
/*    */         } 
/*    */         
/* 65 */         if (!area.canHurtEntities()) {
/* 66 */           event.getAffectedEntities().clear();
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onAbilityHit(ProjectileHitEvent event) {
/* 74 */     if (event.getHit().func_216346_c() == RayTraceResult.Type.ENTITY) {
/* 75 */       boolean isWhitelisted = CommonConfig.INSTANCE.isAbilityProtectionWhitelisted(event.getProjectile().getParent());
/* 76 */       if (isWhitelisted) {
/*    */         return;
/*    */       }
/* 79 */       EntityRayTraceResult entityHit = (EntityRayTraceResult)event.getHit();
/* 80 */       if (entityHit.func_216348_a() instanceof LivingEntity) {
/* 81 */         LivingEntity hitEntity = (LivingEntity)entityHit.func_216348_a();
/* 82 */         ProtectedArea area = ProtectedAreasData.get(hitEntity.field_70170_p).getProtectedArea((int)hitEntity.func_226277_ct_(), (int)hitEntity.func_226278_cu_(), (int)hitEntity.func_226281_cx_());
/* 83 */         if (area != null && !area.canHurtEntities())
/* 84 */           event.setCanceled(true); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityProtectionCommonEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */