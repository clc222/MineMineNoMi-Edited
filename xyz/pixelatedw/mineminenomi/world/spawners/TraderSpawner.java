/*    */ package xyz.pixelatedw.mineminenomi.world.spawners;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TraderSpawner {
/* 23 */   private Random random = new Random();
/*    */   private int cooldown;
/* 25 */   private static final List<Supplier<? extends EntityType<?>>> TRADER_TYPES = Arrays.asList((Supplier<? extends EntityType<?>>[])new Supplier[] { (Supplier)ModEntities.MARINE_TRADER, (Supplier)ModEntities.PIRATE_TRADER });
/*    */   
/*    */   public void tick(ServerWorld world) {
/* 28 */     world.func_217381_Z().func_76320_a("traderSpawnerTick");
/* 29 */     if (--this.cooldown <= 0) {
/* 30 */       this.cooldown = CommonConfig.INSTANCE.getTimeBetweenTraderSpawns();
/* 31 */       if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForTraderSpawn()) {
/* 32 */         spawn(world);
/*    */       }
/*    */     } 
/* 35 */     world.func_217381_Z().func_76319_b();
/*    */   }
/*    */   
/*    */   private void spawn(ServerWorld world) {
/* 39 */     world.func_217381_Z().func_76320_a("traderSpawnerSpawn");
/*    */     
/* 41 */     int listSize = MathHelper.func_76125_a(world.func_217369_A().size() / 3, 1, 10);
/* 42 */     PlayerEntity[] cachedPlayers = new PlayerEntity[listSize];
/*    */     
/* 44 */     for (int i = 0; i < cachedPlayers.length; i++) {
/* 45 */       ServerPlayerEntity serverPlayerEntity = world.func_217472_l_();
/*    */       
/* 47 */       if (serverPlayerEntity != null) {
/*    */ 
/*    */ 
/*    */         
/* 51 */         boolean alreadyCached = Arrays.<PlayerEntity>stream(cachedPlayers).anyMatch(target -> (target == player));
/* 52 */         if (!alreadyCached) {
/*    */ 
/*    */ 
/*    */           
/* 56 */           cachedPlayers[i] = (PlayerEntity)serverPlayerEntity;
/*    */           
/* 58 */           int r = this.random.nextInt(TRADER_TYPES.size());
/* 59 */           EntityType entityType = ((Supplier<EntityType>)TRADER_TYPES.get(r)).get();
/* 60 */           BlockPos targetPos = serverPlayerEntity.func_233580_cy_();
/* 61 */           BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, entityType, targetPos, 20);
/* 62 */           List<TraderEntity> traders = WyHelper.getNearbyEntities(serverPlayerEntity.func_213303_ch(), (IWorld)world, 40.0D, null, new Class[] { TraderEntity.class });
/*    */           
/* 64 */           if (spawnPos == null) {
/*    */             return;
/*    */           }
/*    */           
/* 68 */           boolean canSpawnInBiome = (world.func_226691_t_(targetPos).func_201856_r() != Biome.Category.OCEAN);
/*    */           
/* 70 */           if (traders.size() < 3 && canSpawnInBiome)
/* 71 */           { entityType.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/* 72 */             WyDebug.debug("Trader (" + entityType + ") spawned at: " + spawnPos); } 
/*    */         } 
/*    */       } 
/* 75 */     }  world.func_217381_Z().func_76319_b();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\spawners\TraderSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */