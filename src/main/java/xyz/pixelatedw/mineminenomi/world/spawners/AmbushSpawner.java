/*     */ package xyz.pixelatedw.mineminenomi.world.spawners;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class AmbushSpawner {
/*  28 */   private Random random = new Random();
/*     */   
/*     */   private int cooldown;
/*     */   
/*     */   public void tick(ServerWorld world) {
/*  33 */     world.func_217381_Z().func_76320_a("ambushSpawnerTick");
/*  34 */     if (--this.cooldown <= 0) {
/*     */       
/*  36 */       this.cooldown = CommonConfig.INSTANCE.getTimeBetweenAmbushSpawns();
/*  37 */       if (this.random.nextInt(100) <= CommonConfig.INSTANCE.getChanceForAmbushSpawn()) {
/*  38 */         spawn(world);
/*     */       }
/*     */     } 
/*  41 */     world.func_217381_Z().func_76319_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawn(ServerWorld world) {
/*  46 */     world.func_217381_Z().func_76320_a("ambushSpawnerSpawn");
/*  47 */     ServerPlayerEntity serverPlayerEntity = world.func_217472_l_();
/*  48 */     if (serverPlayerEntity == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  53 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*     */     
/*  55 */     if (props.isPirate() || props.isBandit() || props.isRevolutionary()) {
/*     */       
/*  57 */       BlockPos targetPos = serverPlayerEntity.func_233580_cy_();
/*  58 */       long bounty = props.getBounty();
/*  59 */       boolean canSpawnInBiome = (world.func_226691_t_(targetPos).func_201856_r() != Biome.Category.OCEAN);
/*  60 */       boolean canSeeSky = ((PlayerEntity)serverPlayerEntity).field_70170_p.func_226660_f_(targetPos);
/*     */       
/*  62 */       if (!canSpawnInBiome || bounty < 10000L || !canSeeSky) {
/*     */         return;
/*     */       }
/*     */       
/*  66 */       EntityType captainEntity = (EntityType)ModEntities.MARINE_CAPTAIN.get();
/*  67 */       EntityType gruntEntity = (EntityType)ModEntities.MARINE_GRUNT.get();
/*     */       
/*  69 */       int r = this.random.nextInt(2);
/*  70 */       int r2 = this.random.nextInt(2);
/*  71 */       if (r == 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  79 */       List<OPEntity> dangers = WyHelper.getNearbyEntities(serverPlayerEntity.func_213303_ch(), (IWorld)world, 80.0D, null, new Class[] { OPEntity.class });
/*  80 */       if (dangers.size() > 50) {
/*     */         return;
/*     */       }
/*     */       
/*  84 */       int nrCaptains = 0 + (int)Math.ceil((bounty / 200000L));
/*  85 */       int nrGrunts = 3 + (int)Math.ceil((bounty / 100000L));
/*     */       
/*  87 */       String name = captainEntity.func_212546_e().getString();
/*     */       
/*  89 */       if (nrCaptains > 3) {
/*  90 */         nrCaptains = 3;
/*     */       }
/*     */       
/*  93 */       if (nrGrunts > 30) {
/*  94 */         nrGrunts = 30;
/*     */       }
/*     */       
/*     */       int i;
/*  98 */       for (i = 0; i < nrCaptains; i++) {
/*     */         
/* 100 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, captainEntity, targetPos, 10);
/* 101 */         if (spawnPos != null) {
/* 102 */           captainEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 107 */       for (i = 0; i < nrGrunts; i++) {
/*     */         
/* 109 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation((World)world, gruntEntity, targetPos, 20);
/* 110 */         if (spawnPos != null) {
/*     */           
/* 112 */           Entity e = gruntEntity.func_220342_a(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/* 113 */           if (e != null && e instanceof OPEntity)
/*     */           {
/* 115 */             ((OPEntity)e).setFear(false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 121 */       StringTextComponent stringTextComponent = new StringTextComponent("<" + name + "> We've come to arrest you, surrender now criminal scum!");
/* 122 */       if (r2 == 1) {
/* 123 */         stringTextComponent = new StringTextComponent("<" + name + "> You're surrounded and have no escape, surrender now!");
/*     */       }
/*     */       
/* 126 */       ((PlayerEntity)serverPlayerEntity).field_70170_p.func_184133_a(null, serverPlayerEntity.func_233580_cy_(), SoundEvents.field_219603_Y, SoundCategory.HOSTILE, 1.0F, 1.0F);
/*     */       
/* 128 */       serverPlayerEntity.func_145747_a((ITextComponent)stringTextComponent, Util.field_240973_b_);
/* 129 */       WyDebug.debug("Ambush spawned around these coords: " + targetPos);
/*     */     } 
/*     */     
/* 132 */     world.func_217381_Z().func_76319_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\spawners\AmbushSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */