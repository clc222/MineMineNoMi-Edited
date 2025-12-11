/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.tileentity.ITickableTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityType;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ 
/*     */ public class CustomSpawnerTileEntity
/*     */   extends TileEntity implements ITickableTileEntity {
/*  26 */   private EntityType entityToSpawn = EntityType.field_200784_X;
/*  27 */   private int spawnLimit = 5;
/*  28 */   private int playerDistance = 100;
/*  29 */   private ArrayList<UUID> spawnedEntities = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity() {
/*  33 */     super((TileEntityType)ModTileEntities.CUSTOM_SPAWNER.get());
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity setSpawnerMob(EntityType toSpawn) {
/*  38 */     this.entityToSpawn = toSpawn;
/*  39 */     func_70296_d();
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity setSpawnerLimit(int limit) {
/*  45 */     this.spawnLimit = limit;
/*  46 */     func_70296_d();
/*  47 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomSpawnerTileEntity setPlayerDistance(int distance) {
/*  52 */     this.playerDistance = distance;
/*  53 */     func_70296_d();
/*  54 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isActivated() {
/*  59 */     BlockPos blockpos = func_174877_v();
/*  60 */     return func_145831_w().func_217358_a(blockpos.func_177958_n() + 0.5D, blockpos.func_177956_o() + 0.5D, blockpos.func_177952_p() + 0.5D, this.playerDistance);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  66 */     if (this.field_145850_b == null) {
/*     */       return;
/*     */     }
/*  69 */     if (!this.field_145850_b.field_72995_K && this.field_145850_b.func_82737_E() % 5L == 0L)
/*     */     {
/*  71 */       if (isActivated()) {
/*     */         
/*  73 */         if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177977_b()).func_177230_c() == Blocks.field_150350_a) {
/*  74 */           this.field_145850_b.func_175656_a(this.field_174879_c, Blocks.field_150350_a.func_176223_P());
/*     */         }
/*  76 */         if (this.spawnedEntities.size() > 0) {
/*     */           
/*  78 */           int alive = 0;
/*  79 */           for (UUID spawnUUID : this.spawnedEntities) {
/*     */             
/*  81 */             Entity target = ((ServerWorld)this.field_145850_b).func_217461_a(spawnUUID);
/*  82 */             if (target != null && target.func_70089_S()) {
/*  83 */               alive++;
/*     */             }
/*     */           } 
/*  86 */           if (CommonConfig.INSTANCE.getDestroySpawner() && alive == 0) {
/*  87 */             this.field_145850_b.func_175656_a(this.field_174879_c, Blocks.field_150350_a.func_176223_P());
/*     */           }
/*     */         } 
/*  90 */         if (this.entityToSpawn != null)
/*     */         {
/*  92 */           if (this.spawnedEntities.size() < this.spawnLimit)
/*     */           {
/*  94 */             CompoundNBT nbt = new CompoundNBT();
/*  95 */             nbt.func_74757_a("isSpawned", true);
/*  96 */             LivingEntity newSpawn = (LivingEntity)this.entityToSpawn.func_220342_a((ServerWorld)this.field_145850_b, nbt, (ITextComponent)null, (PlayerEntity)null, this.field_174879_c.func_177984_a(), SpawnReason.STRUCTURE, false, false);
/*  97 */             if (newSpawn != null)
/*     */             {
/*  99 */               double r1 = (this.field_145850_b.field_73012_v.nextDouble() - this.field_145850_b.field_73012_v.nextDouble()) * 2.0D + 0.5D;
/* 100 */               double r2 = (this.field_145850_b.field_73012_v.nextDouble() - this.field_145850_b.field_73012_v.nextDouble()) * 2.0D + 0.5D;
/* 101 */               BlockPos newPos = func_174877_v().func_177963_a(r1, 0.0D, r2);
/* 102 */               newSpawn.func_70012_b(newPos.func_177958_n(), newPos.func_177956_o(), newPos.func_177952_p(), 0.0F, 0.0F);
/* 103 */               this.spawnedEntities.add(newSpawn.func_110124_au());
/* 104 */               func_70296_d();
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       }
/* 111 */       else if (this.spawnedEntities.size() > 0) {
/*     */         
/* 113 */         for (UUID spawnUUID : this.spawnedEntities) {
/*     */           
/* 115 */           Entity target = ((ServerWorld)this.field_145850_b).func_217461_a(spawnUUID);
/* 116 */           if (target != null && target.func_70089_S())
/* 117 */             target.func_70106_y(); 
/*     */         } 
/* 119 */         this.spawnedEntities.clear();
/* 120 */         func_70296_d();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_230337_a_(BlockState state, CompoundNBT nbt) {
/* 129 */     super.func_230337_a_(state, nbt);
/* 130 */     this.spawnLimit = nbt.func_74762_e("spawnLimit");
/* 131 */     this.playerDistance = nbt.func_74762_e("playerDistance");
/* 132 */     if (this.playerDistance <= 0)
/* 133 */       this.playerDistance = 30; 
/* 134 */     this.entityToSpawn = EntityType.func_220327_a(nbt.func_74779_i("entityToSpawn")).orElse(EntityType.field_200784_X);
/*     */     
/* 136 */     ListNBT spawnedEntities = nbt.func_150295_c("spawns", 10);
/* 137 */     for (int i = 0; i < spawnedEntities.size(); i++) {
/*     */       
/* 139 */       CompoundNBT nbtEntity = spawnedEntities.func_150305_b(i);
/* 140 */       UUID nbtUUID = nbtEntity.func_186857_a("uuid");
/* 141 */       this.spawnedEntities.add(nbtUUID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT func_189515_b(CompoundNBT nbt) {
/* 148 */     super.func_189515_b(nbt);
/* 149 */     nbt.func_74768_a("spawnLimit", this.spawnLimit);
/* 150 */     nbt.func_74768_a("playerDistance", this.playerDistance);
/* 151 */     nbt.func_74778_a("entityToSpawn", EntityType.func_200718_a(this.entityToSpawn).toString());
/*     */     
/* 153 */     ListNBT spawnedEntities = new ListNBT();
/* 154 */     for (UUID uuid : this.spawnedEntities) {
/*     */       
/* 156 */       CompoundNBT nbtEntity = new CompoundNBT();
/* 157 */       nbtEntity.func_186854_a("uuid", uuid);
/* 158 */       spawnedEntities.add(nbtEntity);
/*     */     } 
/* 160 */     nbt.func_218657_a("spawns", (INBT)spawnedEntities);
/*     */     
/* 162 */     return nbt;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\CustomSpawnerTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */