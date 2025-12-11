/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.ConfiguredFeature;
/*     */ import net.minecraft.world.gen.feature.Features;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ChallengesHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.RampPart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.TreePart;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SyrupHillSimpleArena extends ChallengeArena {
/*  28 */   public static final SyrupHillSimpleArena INSTANCE = new SyrupHillSimpleArena();
/*     */   
/*     */   private static final int ARENA_X_SIZE_RADIUS = 70;
/*     */   
/*     */   private static final int ARENA_Z_SIZE_RADIUS = 50;
/*     */   
/*     */   private static final int ARENA_Y_SIZE_RADIUS = 50;
/*     */   private static final int ARENA_X_SIZE = 140;
/*     */   private static final int ARENA_Z_SIZE = 100;
/*     */   private static final int GROUND_LEVEL = 30;
/*     */   private final WeightedList<BlockState> floorBlocks;
/*     */   private final WeightedList<BlockState> cliffBlocks;
/*     */   private final WeightedList<BlockState> slabsBlocks;
/*  41 */   private static final BlockProtectionRule ONLY_AIR_AND_WATER = (new BlockProtectionRule.Builder(new BlockProtectionRule[0])).addApprovedMaterials(new Material[] { Material.field_151579_a, Material.field_151586_h }).build();
/*     */   
/*     */   private SyrupHillSimpleArena() {
/*  44 */     super(ArenaStyle.SIMPLE);
/*  45 */     this
/*  46 */       .floorBlocks = new WeightedList(new Object[] { Blocks.field_150348_b.func_176223_P(), Integer.valueOf(100), Blocks.field_196656_g.func_176223_P(), Integer.valueOf(50), Blocks.field_150351_n.func_176223_P(), Integer.valueOf(40), Blocks.field_150347_e.func_176223_P(), Integer.valueOf(20), Blocks.field_150341_Y.func_176223_P(), Integer.valueOf(5) });
/*     */     
/*  48 */     this
/*  49 */       .cliffBlocks = new WeightedList(new Object[] { Blocks.field_150348_b.func_176223_P(), Integer.valueOf(100), Blocks.field_196656_g.func_176223_P(), Integer.valueOf(50), Blocks.field_150351_n.func_176223_P(), Integer.valueOf(40) });
/*     */     
/*  51 */     this
/*     */       
/*  53 */       .slabsBlocks = new WeightedList(new Object[] { Blocks.field_150333_U.func_176223_P(), Integer.valueOf(100), Blocks.field_222455_ls.func_176223_P(), Integer.valueOf(50), Blocks.field_196646_bz.func_176223_P(), Integer.valueOf(40), Blocks.field_150351_n.func_176223_P(), Integer.valueOf(40), Blocks.field_150348_b.func_176223_P(), Integer.valueOf(10), Blocks.field_150347_e.func_176223_P(), Integer.valueOf(20), Blocks.field_222450_ln.func_176223_P(), Integer.valueOf(5) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/*  58 */     int bottomLayerOffset = 49;
/*  59 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*  60 */     BlockPos groundLayer = bottomLayer.func_177981_b(30);
/*     */ 
/*     */     
/*  63 */     BlockPos topRightLayer = groundLayer.func_177982_a(-15, 26, 32);
/*  64 */     populateTrees(challenge, topRightLayer, queue);
/*     */     
/*  66 */     BlockPos topRightLayer2 = groundLayer.func_177982_a(-40, 26, 32);
/*  67 */     populateTrees(challenge, topRightLayer2, queue);
/*     */     
/*  69 */     BlockPos topLeftLayer = groundLayer.func_177982_a(-15, 26, -32);
/*  70 */     populateTrees(challenge, topLeftLayer, queue);
/*     */     
/*  72 */     BlockPos topLeftLayer2 = groundLayer.func_177982_a(-40, 26, -32);
/*  73 */     populateTrees(challenge, topLeftLayer2, queue);
/*     */     
/*  75 */     populateTrees(challenge, topLeftLayer2, queue);
/*     */     
/*  77 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*     */ 
/*     */     
/*  80 */     (new RampPart(challenge, 26, 12, this.slabsBlocks, this.cliffBlocks, Direction.EAST)).buildPart(challenge.getShard(), groundLayer.func_177982_a(-71, 20, -14), queue);
/*  81 */     (new RampPart(challenge, 26, 12, this.slabsBlocks, this.cliffBlocks, Direction.EAST)).buildPart(challenge.getShard(), groundLayer.func_177982_a(-41, 14, -14), queue);
/*  82 */     (new RampPart(challenge, 26, 16, this.slabsBlocks, this.cliffBlocks, Direction.EAST)).buildPart(challenge.getShard(), groundLayer.func_177982_a(-14, 8, -14), queue);
/*     */ 
/*     */     
/*  85 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177982_a(17, 1, 0), 60, 25, 25, Blocks.field_150350_a.func_176223_P(), queue);
/*  86 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177982_a(-10, 9, 0), 60, 25, 25, Blocks.field_150350_a.func_176223_P(), queue);
/*  87 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177982_a(-20, 15, 0), 100, 25, 25, Blocks.field_150350_a.func_176223_P(), queue);
/*  88 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177982_a(-74, 25, 0), 90, 1, Blocks.field_196658_i.func_176223_P(), queue);
/*  89 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177982_a(-74, 0, 0), 90, 25, this.cliffBlocks, queue);
/*     */ 
/*     */     
/*  92 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), bottomLayer.func_177981_b(3).func_177982_a(-70, 0, 0), 98, 6, this.floorBlocks, queue);
/*  93 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), bottomLayer.func_177981_b(9).func_177982_a(-70, 0, 0), 95, 8, this.floorBlocks, queue);
/*  94 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), bottomLayer.func_177977_b().func_177982_a(-70, 0, 0), 90, 30, this.floorBlocks, queue);
/*  95 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177977_b().func_177982_a(-70, 0, 0), 89, 2, this.floorBlocks, queue);
/*     */     
/*  97 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 140, 2, 100, Blocks.field_150354_m.func_176223_P(), queue);
/*     */     
/*  99 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 139, 30, 99, Blocks.field_150355_j.func_176223_P(), queue);
/*     */     
/* 101 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 106 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     
/* 108 */     int bottomLayerOffset = 49;
/* 109 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 110 */     BlockPos groundLayer = bottomLayer.func_177981_b(30);
/*     */     
/* 112 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 139, 30, 99, Blocks.field_150355_j.func_176223_P(), 0, null));
/*     */ 
/*     */     
/* 115 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), bottomLayer.func_177981_b(3).func_177982_a(-70, 0, 0), 98, 6, this.floorBlocks, 0, ONLY_AIR_AND_WATER));
/* 116 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), bottomLayer.func_177981_b(9).func_177982_a(-70, 0, 0), 95, 8, this.floorBlocks, 0, ONLY_AIR_AND_WATER));
/* 117 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), bottomLayer.func_177977_b().func_177982_a(-70, 0, 0), 90, 30, this.floorBlocks, 0, ONLY_AIR_AND_WATER));
/* 118 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177977_b().func_177982_a(-70, 0, 0), 89, 2, this.floorBlocks, 0, ONLY_AIR_AND_WATER));
/*     */ 
/*     */     
/* 121 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177982_a(-74, 0, 0), 90, 25, this.cliffBlocks, 0, ONLY_AIR_AND_WATER));
/* 122 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177982_a(-74, 25, 0), 90, 1, Blocks.field_196658_i.func_176223_P(), 0, null));
/* 123 */     StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177982_a(-20, 15, 0), 100, 25, 25, Blocks.field_150350_a.func_176223_P(), 3, null);
/* 124 */     StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177982_a(-10, 9, 0), 60, 25, 25, Blocks.field_150350_a.func_176223_P(), 3, null);
/* 125 */     StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177982_a(17, 1, 0), 60, 25, 25, Blocks.field_150350_a.func_176223_P(), 3, null);
/*     */ 
/*     */     
/* 128 */     blocks.addAll((new RampPart(challenge, 26, 12, this.slabsBlocks, this.cliffBlocks, Direction.EAST)).buildPart(challenge.getShard(), groundLayer.func_177982_a(-71, 20, -14)));
/* 129 */     blocks.addAll((new RampPart(challenge, 26, 12, this.slabsBlocks, this.cliffBlocks, Direction.EAST)).buildPart(challenge.getShard(), groundLayer.func_177982_a(-41, 14, -14)));
/* 130 */     blocks.addAll((new RampPart(challenge, 26, 16, this.slabsBlocks, this.cliffBlocks, Direction.EAST)).buildPart(challenge.getShard(), groundLayer.func_177982_a(-14, 8, -14)));
/*     */ 
/*     */     
/* 133 */     BlockPos topRightLayer = groundLayer.func_177982_a(-15, 26, 32);
/* 134 */     blocks.addAll(populateTrees(challenge, topRightLayer));
/*     */     
/* 136 */     BlockPos topRightLayer2 = groundLayer.func_177982_a(-40, 26, 32);
/* 137 */     blocks.addAll(populateTrees(challenge, topRightLayer2));
/*     */     
/* 139 */     BlockPos topLeftLayer = groundLayer.func_177982_a(-15, 26, -32);
/* 140 */     blocks.addAll(populateTrees(challenge, topLeftLayer));
/*     */     
/* 142 */     BlockPos topLeftLayer2 = groundLayer.func_177982_a(-40, 26, -32);
/* 143 */     blocks.addAll(populateTrees(challenge, topLeftLayer2));
/*     */     
/* 145 */     blocks.addAll(populateTrees(challenge, topLeftLayer2));
/*     */     
/* 147 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 140, 2, 100, Blocks.field_150354_m.func_176223_P(), 0, null));
/*     */     
/* 149 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*     */     
/* 151 */     return blocks;
/*     */   }
/*     */   
/*     */   private void populateTrees(InProgressChallenge challenge, BlockPos pos, BlockQueue queue) {
/* 155 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 156 */     for (int i = 0; i < 40; i++) {
/* 157 */       int offsetX = (int)WyHelper.randomWithRange(challenge.getRNG(), -30, 30);
/* 158 */       int offsetZ = (int)WyHelper.randomWithRange(challenge.getRNG(), -30, 30);
/*     */       
/* 160 */       mutpos.func_189533_g((Vector3i)pos.func_177982_a(offsetX, -1, offsetZ));
/* 161 */       if (challenge.getShard().func_180495_p((BlockPos)mutpos).func_177230_c() == Blocks.field_196658_i) {
/* 162 */         ConfiguredFeature<?, ?> treeType = challenge.getRNG().nextBoolean() ? Features.field_243862_bH : Features.field_243864_bJ;
/* 163 */         (new TreePart(challenge, treeType, challenge.getRNG().nextInt(10))).buildPart(challenge.getShard(), mutpos.func_185334_h(), queue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Set<BlockPos> populateTrees(InProgressChallenge challenge, BlockPos pos) {
/* 169 */     Set<BlockPos> blocks = new HashSet<>();
/* 170 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 171 */     for (int i = 0; i < 40; i++) {
/* 172 */       int offsetX = (int)WyHelper.randomWithRange(challenge.getRNG(), -30, 30);
/* 173 */       int offsetZ = (int)WyHelper.randomWithRange(challenge.getRNG(), -30, 30);
/*     */       
/* 175 */       mutpos.func_189533_g((Vector3i)pos.func_177982_a(offsetX, -1, offsetZ));
/* 176 */       if (challenge.getShard().func_180495_p((BlockPos)mutpos).func_177230_c() == Blocks.field_196658_i) {
/* 177 */         ConfiguredFeature<?, ?> treeType = challenge.getRNG().nextBoolean() ? Features.field_243862_bH : Features.field_243864_bJ;
/* 178 */         blocks.addAll((new TreePart(challenge, treeType, challenge.getRNG().nextInt(10))).buildPart(challenge.getShard(), mutpos.func_185334_h()));
/*     */       } 
/*     */     } 
/* 181 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public MutableBoundingBox getArenaLimits() {
/* 186 */     return new MutableBoundingBox(-70, -50, -50, 70, 50, 50);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 190 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 14 - posId + 1, challenge.getArenaPos().func_177956_o() - 10, challenge.getArenaPos().func_177952_p());
/* 191 */     return new ChallengeArena.SpawnPosition(pos, -90.0F, 23.0F);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 195 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 10, challenge.getArenaPos().func_177956_o() - 17, challenge.getArenaPos().func_177952_p());
/* 196 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 90.0F, -23.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\SyrupHillSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */