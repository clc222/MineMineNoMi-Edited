/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.BlockPilePart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BaratieSimpleArena extends ChallengeArena {
/*  22 */   public static final BaratieSimpleArena INSTANCE = new BaratieSimpleArena();
/*     */   
/*     */   private static final int ARENA_XZ_SIZE_RADIUS = 70;
/*     */   private static final int ARENA_Y_SIZE_RADIUS = 50;
/*     */   private static final int ARENA_XZ_SIZE = 140;
/*     */   private static final int GROUND_LEVEL = 30;
/*     */   
/*     */   private BaratieSimpleArena() {
/*  30 */     super(ArenaStyle.SIMPLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/*  35 */     int bottomLayerOffset = 49;
/*  36 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*  37 */     BlockPos groundLayer = bottomLayer.func_177981_b(30);
/*     */     
/*  39 */     BlockPos neCorner = groundLayer.func_177982_a(15, 0, -15);
/*  40 */     for (int i = 0; i < 1 + challenge.getRNG().nextInt(3); i++) {
/*  41 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/*  42 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/*  43 */       BlockPos pos = neCorner.func_177982_a(xoffset, 1, zoffset);
/*  44 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/*  45 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/*  46 */       pile.buildPart(challenge.getShard(), pos, queue);
/*     */     } 
/*     */     
/*  49 */     BlockPos nCorner = groundLayer.func_177982_a(0, 0, -20);
/*  50 */     for (int j = 0; j < 1 + challenge.getRNG().nextInt(2); j++) {
/*  51 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 5);
/*  52 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 5);
/*  53 */       BlockPos pos = nCorner.func_177982_a(xoffset, 1, zoffset);
/*  54 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/*  55 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/*  56 */       pile.buildPart(challenge.getShard(), pos, queue);
/*     */     } 
/*     */     
/*  59 */     BlockPos eCorner = groundLayer.func_177982_a(20, 0, 0);
/*  60 */     for (int k = 0; k < 1 + challenge.getRNG().nextInt(2); k++) {
/*  61 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/*  62 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/*  63 */       BlockPos pos = eCorner.func_177982_a(xoffset, 1, zoffset);
/*  64 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/*  65 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/*  66 */       pile.buildPart(challenge.getShard(), pos, queue);
/*     */     } 
/*     */     
/*  69 */     BlockPos sCorner = groundLayer.func_177982_a(0, 0, 20);
/*  70 */     for (int m = 0; m < 1 + challenge.getRNG().nextInt(1); m++) {
/*  71 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 10);
/*  72 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 5);
/*  73 */       BlockPos pos = sCorner.func_177982_a(xoffset, 1, zoffset);
/*  74 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/*  75 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/*  76 */       pile.buildPart(challenge.getShard(), pos, queue);
/*     */     } 
/*     */     
/*  79 */     BlockPos swCorner = groundLayer.func_177982_a(-15, 0, 15);
/*  80 */     for (int n = 0; n < 1 + challenge.getRNG().nextInt(2); n++) {
/*  81 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 10);
/*  82 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/*  83 */       BlockPos pos = swCorner.func_177982_a(xoffset, 1, zoffset);
/*  84 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/*  85 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/*  86 */       pile.buildPart(challenge.getShard(), pos, queue);
/*     */     } 
/*     */     
/*  89 */     BlockPos wCorner = groundLayer.func_177982_a(-20, 0, 0);
/*  90 */     for (int i1 = 0; i1 < 1 + challenge.getRNG().nextInt(2); i1++) {
/*  91 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/*  92 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 10);
/*  93 */       BlockPos pos = wCorner.func_177982_a(xoffset, 1, zoffset);
/*  94 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/*  95 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/*  96 */       pile.buildPart(challenge.getShard(), pos, queue);
/*     */     } 
/*     */     
/*  99 */     StructuresHelper.calcCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177977_b(), 40, 2, Blocks.field_196662_n.func_176223_P(), queue);
/*     */     
/* 101 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 140, 2, 140, Blocks.field_150354_m.func_176223_P(), queue);
/*     */     
/* 103 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 139, 30, 139, Blocks.field_150355_j.func_176223_P(), queue);
/*     */     
/* 105 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 110 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     
/* 112 */     int bottomLayerOffset = 49;
/* 113 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 114 */     BlockPos groundLayer = bottomLayer.func_177981_b(30);
/*     */     
/* 116 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 139, 30, 139, Blocks.field_150355_j.func_176223_P(), 0, null));
/*     */     
/* 118 */     blocks.addAll(StructuresHelper.createCenteredFilledCircle((World)challenge.getShard(), groundLayer.func_177977_b(), 40, 2, Blocks.field_196662_n.func_176223_P(), 0, null));
/*     */     
/* 120 */     BlockPos neCorner = groundLayer.func_177982_a(15, 0, -15);
/* 121 */     for (int i = 0; i < 1 + challenge.getRNG().nextInt(3); i++) {
/* 122 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/* 123 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/* 124 */       BlockPos pos = neCorner.func_177982_a(xoffset, 1, zoffset);
/* 125 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/* 126 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/* 127 */       blocks.addAll(pile.buildPart(challenge.getShard(), pos));
/*     */     } 
/*     */     
/* 130 */     BlockPos nCorner = groundLayer.func_177982_a(0, 0, -20);
/* 131 */     for (int j = 0; j < 1 + challenge.getRNG().nextInt(2); j++) {
/* 132 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 5);
/* 133 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 5);
/* 134 */       BlockPos pos = nCorner.func_177982_a(xoffset, 1, zoffset);
/* 135 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/* 136 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/* 137 */       blocks.addAll(pile.buildPart(challenge.getShard(), pos));
/*     */     } 
/*     */     
/* 140 */     BlockPos eCorner = groundLayer.func_177982_a(20, 0, 0);
/* 141 */     for (int k = 0; k < 1 + challenge.getRNG().nextInt(2); k++) {
/* 142 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/* 143 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/* 144 */       BlockPos pos = eCorner.func_177982_a(xoffset, 1, zoffset);
/* 145 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/* 146 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/* 147 */       blocks.addAll(pile.buildPart(challenge.getShard(), pos));
/*     */     } 
/*     */     
/* 150 */     BlockPos sCorner = groundLayer.func_177982_a(0, 0, 20);
/* 151 */     for (int m = 0; m < 1 + challenge.getRNG().nextInt(1); m++) {
/* 152 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 10);
/* 153 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 5);
/* 154 */       BlockPos pos = sCorner.func_177982_a(xoffset, 1, zoffset);
/* 155 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/* 156 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/* 157 */       blocks.addAll(pile.buildPart(challenge.getShard(), pos));
/*     */     } 
/*     */     
/* 160 */     BlockPos swCorner = groundLayer.func_177982_a(-15, 0, 15);
/* 161 */     for (int n = 0; n < 1 + challenge.getRNG().nextInt(2); n++) {
/* 162 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 10);
/* 163 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/* 164 */       BlockPos pos = swCorner.func_177982_a(xoffset, 1, zoffset);
/* 165 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/* 166 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/* 167 */       blocks.addAll(pile.buildPart(challenge.getShard(), pos));
/*     */     } 
/*     */     
/* 170 */     BlockPos wCorner = groundLayer.func_177982_a(-20, 0, 0);
/* 171 */     for (int i1 = 0; i1 < 1 + challenge.getRNG().nextInt(2); i1++) {
/* 172 */       int xoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -5, 5);
/* 173 */       int zoffset = (int)WyHelper.randomWithRange(challenge.getRNG(), -10, 10);
/* 174 */       BlockPos pos = wCorner.func_177982_a(xoffset, 1, zoffset);
/* 175 */       BlockPilePart pile = new BlockPilePart(challenge, ((Block)ModBlocks.DESIGN_BARREL.get()).func_176223_P());
/* 176 */       pile.setChangeFunc(state -> (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208155_H, (Comparable)Direction.func_239631_a_(challenge.getRNG())));
/* 177 */       blocks.addAll(pile.buildPart(challenge.getShard(), pos));
/*     */     } 
/*     */     
/* 180 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 140, 2, 140, Blocks.field_150354_m.func_176223_P(), 0, null));
/*     */     
/* 182 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*     */     
/* 184 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public MutableBoundingBox getArenaLimits() {
/* 189 */     return new MutableBoundingBox(-70, -50, -70, 70, 50, 70);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 193 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 20 - posId + 1, challenge.getArenaPos().func_177956_o() - 16, challenge.getArenaPos().func_177952_p() - 20);
/* 194 */     return new ChallengeArena.SpawnPosition(pos, -45.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 198 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 20, challenge.getArenaPos().func_177956_o() - 16, challenge.getArenaPos().func_177952_p() + 20);
/* 199 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 140.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\BaratieSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */