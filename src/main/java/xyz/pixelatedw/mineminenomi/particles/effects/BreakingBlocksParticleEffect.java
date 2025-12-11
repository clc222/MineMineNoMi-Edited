/*     */ package xyz.pixelatedw.mineminenomi.particles.effects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BreakingBlocksParticleEffect extends ParticleEffect<BreakingBlocksParticleEffect.Details> {
/*     */   public BreakingBlocksParticleEffect() {
/*  20 */     super(Details::new);
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawn(Entity entity, World world, double posX, double posY, double posZ, Details details) {
/*  25 */     if (details.isVecList) {
/*  26 */       for (Vector3d pos : details.vecPositions) {
/*  27 */         posX = pos.func_82615_a();
/*  28 */         posY = pos.func_82617_b();
/*  29 */         posZ = pos.func_82616_c();
/*     */         
/*  31 */         for (int i = 0; i < details.amount; i++) {
/*  32 */           double offsetX = world.field_73012_v.nextDouble();
/*  33 */           double offsetY = 1.0D;
/*  34 */           double offsetZ = world.field_73012_v.nextDouble();
/*     */           
/*  36 */           BlockState blockState = world.func_180495_p((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).func_177977_b());
/*  37 */           BlockParticleData particleData = new BlockParticleData(ParticleTypes.field_197611_d, blockState);
/*     */           
/*  39 */           world.func_195590_a((IParticleData)particleData, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  46 */     for (BlockPos pos : details.positions) {
/*  47 */       posX = pos.func_177958_n();
/*  48 */       posY = pos.func_177956_o();
/*  49 */       posZ = pos.func_177952_p();
/*     */       
/*  51 */       for (int i = 0; i < details.amount; i++) {
/*  52 */         double offsetX = world.field_73012_v.nextDouble();
/*  53 */         double offsetY = 1.0D;
/*  54 */         double offsetZ = world.field_73012_v.nextDouble();
/*     */         
/*  56 */         BlockState blockState = world.func_180495_p((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).func_177977_b());
/*  57 */         BlockParticleData particleData = new BlockParticleData(ParticleTypes.field_197611_d, blockState);
/*     */         
/*  59 */         world.func_195590_a((IParticleData)particleData, true, posX + offsetX, posY + offsetY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class Details extends ParticleEffect.Details {
/*  65 */     private int amount = 60;
/*     */     
/*     */     private boolean isVecList;
/*     */     
/*  69 */     private List<BlockPos> positions = new ArrayList<>();
/*  70 */     private List<Vector3d> vecPositions = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*     */     public Details(int amount) {
/*  75 */       this.amount = amount;
/*     */     }
/*     */ 
/*     */     
/*     */     public void save(CompoundNBT nbt) {
/*  80 */       nbt.func_74768_a("amount", this.amount);
/*  81 */       nbt.func_74757_a("isVec", this.isVecList);
/*     */       
/*  83 */       ListNBT positionsList = new ListNBT();
/*     */       
/*  85 */       if (this.positions.size() > 0) {
/*  86 */         for (BlockPos pos : this.positions) {
/*  87 */           CompoundNBT entry = new CompoundNBT();
/*     */           
/*  89 */           entry.func_74768_a("x", pos.func_177958_n());
/*  90 */           entry.func_74768_a("y", pos.func_177956_o());
/*  91 */           entry.func_74768_a("z", pos.func_177952_p());
/*     */           
/*  93 */           positionsList.add(entry);
/*     */         } 
/*     */       }
/*  96 */       nbt.func_218657_a("positions", (INBT)positionsList);
/*     */       
/*  98 */       ListNBT vecPositionsList = new ListNBT();
/*     */       
/* 100 */       if (this.vecPositions.size() > 0) {
/* 101 */         for (Vector3d pos : this.vecPositions) {
/* 102 */           CompoundNBT entry = new CompoundNBT();
/*     */           
/* 104 */           entry.func_74780_a("x", pos.func_82615_a());
/* 105 */           entry.func_74780_a("y", pos.func_82617_b());
/* 106 */           entry.func_74780_a("z", pos.func_82616_c());
/*     */           
/* 108 */           vecPositionsList.add(entry);
/*     */         } 
/*     */       }
/*     */       
/* 112 */       nbt.func_218657_a("vecPositions", (INBT)vecPositionsList);
/*     */     }
/*     */ 
/*     */     
/*     */     public void load(CompoundNBT nbt) {
/* 117 */       this.amount = nbt.func_74762_e("amount");
/* 118 */       this.isVecList = nbt.func_74767_n("isVec");
/*     */       
/* 120 */       ListNBT positionsList = nbt.func_150295_c("positions", 10);
/*     */       
/* 122 */       this.positions.clear();
/*     */       
/* 124 */       for (int i = 0; i < positionsList.size(); i++) {
/* 125 */         CompoundNBT entry = positionsList.func_150305_b(i);
/*     */         
/* 127 */         BlockPos pos = new BlockPos(entry.func_74762_e("x"), entry.func_74762_e("y"), entry.func_74762_e("z"));
/*     */         
/* 129 */         this.positions.add(pos);
/*     */       } 
/*     */       
/* 132 */       ListNBT vecPositionsList = nbt.func_150295_c("vecPositions", 10);
/*     */       
/* 134 */       this.vecPositions.clear();
/*     */       
/* 136 */       for (int j = 0; j < vecPositionsList.size(); j++) {
/* 137 */         CompoundNBT entry = vecPositionsList.func_150305_b(j);
/*     */         
/* 139 */         Vector3d pos = new Vector3d(entry.func_74769_h("x"), entry.func_74769_h("y"), entry.func_74769_h("z"));
/*     */         
/* 141 */         this.vecPositions.add(pos);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void setParticleAmount(int amount) {
/* 146 */       this.amount = amount;
/*     */     }
/*     */     
/*     */     public void setPositions(List<BlockPos> positions) {
/* 150 */       this.positions = positions;
/*     */     }
/*     */     
/*     */     public void setPositions(BlockPos[] positions) {
/* 154 */       this.positions.addAll(Arrays.asList(positions));
/*     */     }
/*     */     
/*     */     public void setVecPositions(List<Vector3d> positions) {
/* 158 */       this.vecPositions = positions;
/* 159 */       this.isVecList = true;
/*     */     }
/*     */     
/*     */     public void addPosition(BlockPos pos) {
/* 163 */       this.positions.add(pos);
/*     */     }
/*     */     
/*     */     public void clearPositions() {
/* 167 */       this.positions.clear();
/*     */     }
/*     */     
/*     */     public Details() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\particles\effects\BreakingBlocksParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */