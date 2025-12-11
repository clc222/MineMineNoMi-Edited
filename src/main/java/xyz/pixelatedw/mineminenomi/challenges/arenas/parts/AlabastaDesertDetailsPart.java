/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ 
/*     */ public class AlabastaDesertDetailsPart
/*     */   extends ArenaPart {
/*     */   private final int arenaSizeRadius;
/*     */   
/*     */   public AlabastaDesertDetailsPart(InProgressChallenge challenge, int arenaSizeRadius) {
/*  16 */     super(challenge);
/*  17 */     this.arenaSizeRadius = arenaSizeRadius;
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/*  22 */     BlockPos nwCorner = spawnPos.func_177985_f(this.arenaSizeRadius / 2).func_177964_d(this.arenaSizeRadius / 2);
/*  23 */     BlockPos neCorner = spawnPos.func_177965_g(this.arenaSizeRadius / 4).func_177964_d(this.arenaSizeRadius / 4);
/*  24 */     BlockPos seCorner = spawnPos.func_177965_g(this.arenaSizeRadius / 2).func_177970_e(this.arenaSizeRadius / 2);
/*  25 */     BlockPos swCorner = spawnPos.func_177985_f(this.arenaSizeRadius / 4).func_177970_e(this.arenaSizeRadius / 2);
/*     */     
/*  27 */     boolean nwCornerAlt = getRNG().nextBoolean();
/*  28 */     boolean neCornerAlt = getRNG().nextBoolean();
/*  29 */     boolean swCornerAlt = getRNG().nextBoolean();
/*  30 */     boolean seCornerAlt = getRNG().nextBoolean();
/*     */     
/*  32 */     if (nwCornerAlt) {
/*  33 */       (new SandstonePillarPart(getChallenge())).buildPart(world, nwCorner, queue);
/*  34 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, nwCorner.func_177965_g(11), queue);
/*  35 */       (new SandstonePillarPart(getChallenge())).buildPart(world, nwCorner.func_177965_g(7).func_177964_d(7), queue);
/*     */     } else {
/*     */       
/*  38 */       (new LargeSandPilePart(getChallenge())).buildPart(world, nwCorner.func_177964_d(5), queue);
/*  39 */       (new LargeSandPilePart(getChallenge())).buildPart(world, nwCorner.func_177965_g(21), queue);
/*  40 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, nwCorner.func_177965_g(2).func_177970_e(10), queue);
/*     */     } 
/*     */     
/*  43 */     if (seCornerAlt) {
/*  44 */       (new SandstonePillarPart(getChallenge())).buildPart(world, seCorner, queue);
/*  45 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177985_f(10), queue);
/*  46 */       (new SandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177965_g(5).func_177964_d(7), queue);
/*  47 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177970_e(7).func_177965_g(7), queue);
/*     */     } else {
/*     */       
/*  50 */       (new SandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177970_e(10).func_177965_g(4), queue);
/*  51 */       (new SandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177965_g(15).func_177964_d(7), queue);
/*  52 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177970_e(2).func_177965_g(17), queue);
/*     */     } 
/*     */     
/*  55 */     if (neCornerAlt) {
/*  56 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, neCorner.func_177985_f(7), queue);
/*  57 */       (new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177970_e(10).func_177965_g(10), queue);
/*  58 */       (new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(20).func_177965_g(13), queue);
/*     */     } else {
/*     */       
/*  61 */       (new BrokenSandstonePillarPart(getChallenge())).buildPart(world, neCorner.func_177985_f(4), queue);
/*  62 */       (new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(4).func_177965_g(11), queue);
/*  63 */       (new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(15).func_177965_g(18), queue);
/*  64 */       (new SmallSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(3).func_177985_f(13), queue);
/*     */     } 
/*     */     
/*  67 */     if (swCornerAlt) {
/*  68 */       (new SmallSandPilePart(getChallenge())).buildPart(world, swCorner.func_177985_f(7), queue);
/*  69 */       (new SandstonePillarPart(getChallenge())).buildPart(world, swCorner.func_177970_e(10).func_177965_g(10), queue);
/*  70 */       (new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177964_d(20).func_177985_f(6), queue);
/*  71 */       (new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177970_e(4).func_177985_f(20), queue);
/*  72 */       (new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177964_d(24).func_177985_f(8), queue);
/*     */     } else {
/*     */       
/*  75 */       (new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177985_f(12), queue);
/*  76 */       (new SandstonePillarPart(getChallenge())).buildPart(world, swCorner.func_177970_e(2).func_177965_g(6), queue);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/*  82 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     
/*  84 */     BlockPos nwCorner = spawnPos.func_177985_f(this.arenaSizeRadius / 2).func_177964_d(this.arenaSizeRadius / 2);
/*  85 */     BlockPos neCorner = spawnPos.func_177965_g(this.arenaSizeRadius / 4).func_177964_d(this.arenaSizeRadius / 4);
/*  86 */     BlockPos seCorner = spawnPos.func_177965_g(this.arenaSizeRadius / 2).func_177970_e(this.arenaSizeRadius / 2);
/*  87 */     BlockPos swCorner = spawnPos.func_177985_f(this.arenaSizeRadius / 4).func_177970_e(this.arenaSizeRadius / 2);
/*     */     
/*  89 */     boolean nwCornerAlt = getRNG().nextBoolean();
/*  90 */     boolean neCornerAlt = getRNG().nextBoolean();
/*  91 */     boolean swCornerAlt = getRNG().nextBoolean();
/*  92 */     boolean seCornerAlt = getRNG().nextBoolean();
/*     */     
/*  94 */     if (nwCornerAlt) {
/*  95 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, nwCorner));
/*  96 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, nwCorner.func_177965_g(11)));
/*  97 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, nwCorner.func_177965_g(7).func_177964_d(7)));
/*     */     } else {
/*     */       
/* 100 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, nwCorner.func_177964_d(5)));
/* 101 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, nwCorner.func_177965_g(21)));
/* 102 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, nwCorner.func_177965_g(2).func_177970_e(10)));
/*     */     } 
/*     */     
/* 105 */     if (seCornerAlt) {
/* 106 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, seCorner));
/* 107 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177985_f(10)));
/* 108 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177965_g(5).func_177964_d(7)));
/* 109 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177970_e(7).func_177965_g(7)));
/*     */     } else {
/*     */       
/* 112 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177970_e(10).func_177965_g(4)));
/* 113 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177965_g(15).func_177964_d(7)));
/* 114 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, seCorner.func_177970_e(2).func_177965_g(17)));
/*     */     } 
/*     */     
/* 117 */     if (neCornerAlt) {
/* 118 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, neCorner.func_177985_f(7)));
/* 119 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177970_e(10).func_177965_g(10)));
/* 120 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(20).func_177965_g(13)));
/*     */     } else {
/*     */       
/* 123 */       blocks.addAll((new BrokenSandstonePillarPart(getChallenge())).buildPart(world, neCorner.func_177985_f(4)));
/* 124 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(4).func_177965_g(11)));
/* 125 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(15).func_177965_g(18)));
/* 126 */       blocks.addAll((new SmallSandPilePart(getChallenge())).buildPart(world, neCorner.func_177964_d(3).func_177985_f(13)));
/*     */     } 
/*     */     
/* 129 */     if (swCornerAlt) {
/* 130 */       blocks.addAll((new SmallSandPilePart(getChallenge())).buildPart(world, swCorner.func_177985_f(7)));
/* 131 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, swCorner.func_177970_e(10).func_177965_g(10)));
/* 132 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177964_d(20).func_177985_f(6)));
/* 133 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177970_e(4).func_177985_f(20)));
/* 134 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177964_d(24).func_177985_f(8)));
/*     */     } else {
/*     */       
/* 137 */       blocks.addAll((new LargeSandPilePart(getChallenge())).buildPart(world, swCorner.func_177985_f(12)));
/* 138 */       blocks.addAll((new SandstonePillarPart(getChallenge())).buildPart(world, swCorner.func_177970_e(2).func_177965_g(6)));
/*     */     } 
/*     */     
/* 141 */     return blocks;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\AlabastaDesertDetailsPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */