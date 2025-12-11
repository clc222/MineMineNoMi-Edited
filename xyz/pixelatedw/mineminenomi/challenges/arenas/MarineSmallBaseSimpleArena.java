/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.FourWayBlock;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ChallengesHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.BlockPilePart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.FlagPolePart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.HayDummyPart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.StairsPart;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MarineSmallBaseSimpleArena extends ChallengeArena {
/*  28 */   public static final MarineSmallBaseSimpleArena INSTANCE = new MarineSmallBaseSimpleArena();
/*     */   
/*     */   private static final int ARENA_XZ_SIZE_RADIUS = 30;
/*     */   private static final int ARENA_Y_SIZE_RADIUS = 50;
/*     */   private static final int ARENA_XZ_SIZE = 60;
/*     */   
/*     */   private MarineSmallBaseSimpleArena() {
/*  35 */     super(ArenaStyle.SIMPLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/*  40 */     int bottomLayerOffset = 49;
/*  41 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/*  42 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*     */ 
/*     */     
/*  45 */     int extraOffset1 = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -10, 10);
/*  46 */     int extraOffset2 = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -7, 7);
/*  47 */     int extraOffset3 = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -5, 5);
/*     */     
/*  49 */     boolean blocksVsDummies = challenge.getRNG().nextBoolean();
/*     */     
/*  51 */     BlockPos neCorner = groundLayer.func_177964_d(15).func_177965_g(15);
/*  52 */     if (blocksVsDummies) {
/*  53 */       (new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), neCorner.func_177982_a(extraOffset3, 0, extraOffset3), queue);
/*  54 */       (new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), neCorner.func_177982_a(extraOffset2, 0, extraOffset2), queue);
/*     */     } else {
/*     */       
/*  57 */       (new HayDummyPart(challenge)).buildPart(challenge.getShard(), neCorner, queue);
/*  58 */       (new HayDummyPart(challenge)).buildPart(challenge.getShard(), neCorner.func_177964_d(5), queue);
/*     */     } 
/*     */     
/*  61 */     BlockPos swCorner = groundLayer.func_177970_e(15).func_177985_f(15);
/*  62 */     (new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), swCorner.func_177982_a(extraOffset1, 0, extraOffset1), queue);
/*  63 */     (new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), swCorner.func_177982_a(extraOffset2, 0, extraOffset2), queue);
/*     */     
/*  65 */     BlockPos seCorner = groundLayer.func_177970_e(10).func_177965_g(20);
/*  66 */     if (!blocksVsDummies) {
/*  67 */       (new HayDummyPart(challenge)).buildPart(challenge.getShard(), seCorner, queue);
/*  68 */       (new HayDummyPart(challenge)).buildPart(challenge.getShard(), seCorner.func_177964_d(5), queue);
/*  69 */       (new HayDummyPart(challenge)).buildPart(challenge.getShard(), seCorner.func_177964_d(10), queue);
/*     */     } 
/*     */     
/*  72 */     int wallHeight = 5;
/*  73 */     int wallWidth = 5;
/*     */ 
/*     */     
/*  76 */     BlockPos stairs1Pos = groundLayer.func_177981_b(4).func_177970_e(23).func_177965_g(24);
/*  77 */     BlockPos stairs1Top = stairs1Pos.func_177984_a().func_177976_e().func_177978_c();
/*  78 */     (new StairsPart(challenge, 3, 5, Blocks.field_150390_bg, Blocks.field_196696_di, Direction.WEST)).buildPart(challenge.getShard(), stairs1Pos, queue);
/*  79 */     StructuresHelper.calcFillCube((World)challenge.getShard(), stairs1Top, stairs1Top.func_177964_d(2), Blocks.field_150350_a.func_176223_P(), queue);
/*     */     
/*  81 */     BlockPos stairs2Pos = groundLayer.func_177981_b(4).func_177964_d(24).func_177985_f(24);
/*  82 */     BlockPos stairs2Top = stairs2Pos.func_177984_a().func_177968_d();
/*  83 */     (new StairsPart(challenge, 3, 5, Blocks.field_150390_bg, Blocks.field_196696_di, Direction.EAST)).buildPart(challenge.getShard(), stairs2Pos, queue);
/*  84 */     StructuresHelper.calcFillCube((World)challenge.getShard(), stairs2Top, stairs2Top.func_177970_e(2), Blocks.field_150350_a.func_176223_P(), queue);
/*     */ 
/*     */     
/*  87 */     BlockState ironBarsState = (BlockState)((BlockState)Blocks.field_150411_aY.func_176223_P().func_206870_a((Property)FourWayBlock.field_196411_b, Boolean.valueOf(true))).func_206870_a((Property)FourWayBlock.field_196414_y, Boolean.valueOf(true));
/*     */     
/*  89 */     int westDistance = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -5, 20);
/*  90 */     int cage2Spread = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, 5, 15);
/*  91 */     int cage3Spread = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, 3, 10);
/*     */     
/*  93 */     BlockPos cage1Pos = groundLayer.func_177985_f(westDistance).func_177970_e(24);
/*     */     
/*  95 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage1Pos.func_177978_c().func_177974_f(), cage1Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 0), ironBarsState, queue);
/*  96 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage1Pos.func_177978_c().func_177974_f(), cage1Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 1), Blocks.field_150350_a.func_176223_P(), queue);
/*  97 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage1Pos.func_177977_b(), cage1Pos.func_177977_b().func_177982_a(4, 4, 1), Blocks.field_196858_iR.func_176223_P(), queue);
/*     */     
/*  99 */     BlockPos cage2Pos = cage1Pos.func_177965_g(cage2Spread);
/*     */     
/* 101 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage2Pos.func_177978_c().func_177974_f(), cage2Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 0), ironBarsState, queue);
/* 102 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage2Pos.func_177978_c().func_177974_f(), cage2Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 1), Blocks.field_150350_a.func_176223_P(), queue);
/* 103 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage2Pos.func_177977_b(), cage2Pos.func_177977_b().func_177982_a(4, 4, 1), Blocks.field_196858_iR.func_176223_P(), queue);
/*     */     
/* 105 */     BlockPos cage3Pos = cage2Pos.func_177965_g(cage3Spread);
/*     */     
/* 107 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage3Pos.func_177978_c().func_177974_f(), cage3Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 0), ironBarsState, queue);
/* 108 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage3Pos.func_177978_c().func_177974_f(), cage3Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 1), Blocks.field_150350_a.func_176223_P(), queue);
/* 109 */     StructuresHelper.calcFillCube((World)challenge.getShard(), cage3Pos.func_177977_b(), cage3Pos.func_177977_b().func_177982_a(4, 4, 1), Blocks.field_196858_iR.func_176223_P(), queue);
/*     */ 
/*     */     
/* 112 */     BlockPos wallLayer = groundLayer.func_177981_b(5);
/* 113 */     boolean flag1Set = challenge.getRNG().nextBoolean();
/*     */     
/* 115 */     if (flag1Set) {
/* 116 */       BlockPos flag1Pos = wallLayer.func_177984_a().func_177965_g(23).func_177964_d(24);
/* 117 */       (new FlagPolePart(challenge, 5, CanvasSize.MEDIUM, ModValues.MARINE)).buildPart(challenge.getShard(), flag1Pos, queue);
/*     */     } else {
/*     */       
/* 120 */       BlockPos flag2Pos = wallLayer.func_177984_a().func_177970_e(23).func_177985_f(24);
/* 121 */       (new FlagPolePart(challenge, 5, CanvasSize.MEDIUM, ModValues.MARINE)).buildPart(challenge.getShard(), flag2Pos, queue);
/*     */     } 
/*     */ 
/*     */     
/* 125 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177965_g(26), 5, 5, 60, Blocks.field_196696_di.func_176223_P(), queue);
/* 126 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177985_f(26), 5, 5, 60, Blocks.field_196696_di.func_176223_P(), queue);
/* 127 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177964_d(26), 60, 5, 5, Blocks.field_196696_di.func_176223_P(), queue);
/* 128 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177970_e(26), 60, 5, 5, Blocks.field_196696_di.func_176223_P(), queue);
/*     */     
/* 130 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177965_g(28).func_177964_d(29), wallLayer.func_177965_g(28).func_177970_e(28), Blocks.field_196696_di.func_176223_P(), queue);
/* 131 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177965_g(23).func_177964_d(24), wallLayer.func_177965_g(23).func_177970_e(23), Blocks.field_196696_di.func_176223_P(), queue);
/*     */     
/* 133 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177985_f(29).func_177964_d(29), wallLayer.func_177985_f(29).func_177970_e(28), Blocks.field_196696_di.func_176223_P(), queue);
/* 134 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177985_f(24).func_177964_d(24), wallLayer.func_177985_f(24).func_177970_e(23), Blocks.field_196696_di.func_176223_P(), queue);
/*     */     
/* 136 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177970_e(28).func_177965_g(28), wallLayer.func_177970_e(28).func_177985_f(29), Blocks.field_196696_di.func_176223_P(), queue);
/* 137 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177970_e(23).func_177965_g(23), wallLayer.func_177970_e(23).func_177985_f(24), Blocks.field_196696_di.func_176223_P(), queue);
/*     */     
/* 139 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177964_d(29).func_177965_g(28), wallLayer.func_177964_d(29).func_177985_f(29), Blocks.field_196696_di.func_176223_P(), queue);
/* 140 */     StructuresHelper.calcFillCube((World)challenge.getShard(), wallLayer.func_177964_d(24).func_177965_g(23), wallLayer.func_177964_d(24).func_177985_f(23), Blocks.field_196696_di.func_176223_P(), queue);
/*     */     
/* 142 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 60, 1, 60, Blocks.field_150322_A.func_176223_P(), queue);
/*     */     
/* 144 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 60, 17, 60, Blocks.field_150348_b.func_176223_P(), queue);
/*     */     
/* 146 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/* 151 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     
/* 153 */     int bottomLayerOffset = 49;
/* 154 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(49);
/* 155 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*     */     
/* 157 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 60, 17, 60, Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */     
/* 159 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 60, 1, 60, Blocks.field_150322_A.func_176223_P(), 0, null));
/*     */ 
/*     */     
/* 162 */     int extraOffset1 = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -10, 10);
/* 163 */     int extraOffset2 = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -7, 7);
/* 164 */     int extraOffset3 = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -5, 5);
/*     */     
/* 166 */     boolean blocksVsDummies = challenge.getRNG().nextBoolean();
/*     */     
/* 168 */     BlockPos neCorner = groundLayer.func_177964_d(15).func_177965_g(15);
/* 169 */     if (blocksVsDummies) {
/* 170 */       blocks.addAll((new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), neCorner.func_177982_a(extraOffset3, 0, extraOffset3)));
/* 171 */       blocks.addAll((new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), neCorner.func_177982_a(extraOffset2, 0, extraOffset2)));
/*     */     } else {
/*     */       
/* 174 */       blocks.addAll((new HayDummyPart(challenge)).buildPart(challenge.getShard(), neCorner));
/* 175 */       blocks.addAll((new HayDummyPart(challenge)).buildPart(challenge.getShard(), neCorner.func_177964_d(5)));
/* 176 */       blocks.addAll((new HayDummyPart(challenge)).buildPart(challenge.getShard(), neCorner.func_177964_d(10)));
/*     */     } 
/*     */     
/* 179 */     BlockPos swCorner = groundLayer.func_177970_e(15).func_177985_f(15);
/* 180 */     blocks.addAll((new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), swCorner.func_177982_a(extraOffset1, 0, extraOffset1)));
/* 181 */     blocks.addAll((new BlockPilePart(challenge, Blocks.field_150407_cf.func_176223_P())).buildPart(challenge.getShard(), swCorner.func_177982_a(extraOffset2, 0, extraOffset2)));
/*     */     
/* 183 */     BlockPos seCorner = groundLayer.func_177970_e(10).func_177965_g(20);
/* 184 */     if (!blocksVsDummies) {
/* 185 */       blocks.addAll((new HayDummyPart(challenge)).buildPart(challenge.getShard(), seCorner));
/* 186 */       blocks.addAll((new HayDummyPart(challenge)).buildPart(challenge.getShard(), seCorner.func_177964_d(5)));
/* 187 */       blocks.addAll((new HayDummyPart(challenge)).buildPart(challenge.getShard(), seCorner.func_177964_d(10)));
/*     */     } 
/*     */ 
/*     */     
/* 191 */     int wallHeight = 5;
/* 192 */     int wallWidth = 5;
/*     */     
/* 194 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177965_g(26), 5, 5, 60, Blocks.field_196696_di.func_176223_P(), 0, null));
/* 195 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177985_f(26), 5, 5, 60, Blocks.field_196696_di.func_176223_P(), 0, null));
/* 196 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177964_d(26), 60, 5, 5, Blocks.field_196696_di.func_176223_P(), 0, null));
/* 197 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), groundLayer.func_177970_e(26), 60, 5, 5, Blocks.field_196696_di.func_176223_P(), 0, null));
/*     */     
/* 199 */     BlockPos wallLayer = groundLayer.func_177981_b(5);
/*     */     
/* 201 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177965_g(28).func_177964_d(29), wallLayer.func_177965_g(28).func_177970_e(28), Blocks.field_196696_di.func_176223_P(), 0, null));
/* 202 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177965_g(23).func_177964_d(24), wallLayer.func_177965_g(23).func_177970_e(23), Blocks.field_196696_di.func_176223_P(), 0, null));
/*     */     
/* 204 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177985_f(29).func_177964_d(29), wallLayer.func_177985_f(29).func_177970_e(28), Blocks.field_196696_di.func_176223_P(), 0, null));
/* 205 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177985_f(24).func_177964_d(24), wallLayer.func_177985_f(24).func_177970_e(23), Blocks.field_196696_di.func_176223_P(), 0, null));
/*     */     
/* 207 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177970_e(28).func_177965_g(28), wallLayer.func_177970_e(28).func_177985_f(29), Blocks.field_196696_di.func_176223_P(), 0, null));
/* 208 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177970_e(23).func_177965_g(23), wallLayer.func_177970_e(23).func_177985_f(24), Blocks.field_196696_di.func_176223_P(), 0, null));
/*     */     
/* 210 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177964_d(29).func_177965_g(28), wallLayer.func_177964_d(29).func_177985_f(29), Blocks.field_196696_di.func_176223_P(), 0, null));
/* 211 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), wallLayer.func_177964_d(24).func_177965_g(23), wallLayer.func_177964_d(24).func_177985_f(23), Blocks.field_196696_di.func_176223_P(), 0, null));
/*     */     
/* 213 */     boolean flag1Set = challenge.getRNG().nextBoolean();
/*     */     
/* 215 */     if (flag1Set) {
/* 216 */       BlockPos flag1Pos = wallLayer.func_177984_a().func_177965_g(23).func_177964_d(24);
/* 217 */       blocks.addAll((new FlagPolePart(challenge, 5, CanvasSize.MEDIUM, ModValues.MARINE)).buildPart(challenge.getShard(), flag1Pos));
/*     */     } else {
/*     */       
/* 220 */       BlockPos flag2Pos = wallLayer.func_177984_a().func_177970_e(23).func_177985_f(24);
/* 221 */       blocks.addAll((new FlagPolePart(challenge, 5, CanvasSize.MEDIUM, ModValues.MARINE)).buildPart(challenge.getShard(), flag2Pos));
/*     */     } 
/*     */ 
/*     */     
/* 225 */     BlockPos stairs1Pos = groundLayer.func_177981_b(4).func_177970_e(23).func_177965_g(24);
/* 226 */     BlockPos stairs1Top = stairs1Pos.func_177984_a().func_177976_e().func_177978_c();
/* 227 */     blocks.addAll((new StairsPart(challenge, 3, 5, Blocks.field_150390_bg, Blocks.field_196696_di, Direction.WEST)).buildPart(challenge.getShard(), stairs1Pos));
/* 228 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), stairs1Top, stairs1Top.func_177964_d(2), Blocks.field_150350_a.func_176223_P(), 0, null));
/*     */     
/* 230 */     BlockPos stairs2Pos = groundLayer.func_177981_b(4).func_177964_d(24).func_177985_f(24);
/* 231 */     BlockPos stairs2Top = stairs2Pos.func_177984_a().func_177968_d();
/* 232 */     blocks.addAll((new StairsPart(challenge, 3, 5, Blocks.field_150390_bg, Blocks.field_196696_di, Direction.EAST)).buildPart(challenge.getShard(), stairs2Pos));
/* 233 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), stairs2Top, stairs2Top.func_177970_e(2), Blocks.field_150350_a.func_176223_P(), 0, null));
/*     */ 
/*     */     
/* 236 */     BlockState ironBarsState = (BlockState)((BlockState)Blocks.field_150411_aY.func_176223_P().func_206870_a((Property)FourWayBlock.field_196411_b, Boolean.valueOf(true))).func_206870_a((Property)FourWayBlock.field_196414_y, Boolean.valueOf(true));
/*     */     
/* 238 */     int westDistance = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, -5, 20);
/* 239 */     int cage2Spread = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, 5, 15);
/* 240 */     int cage3Spread = (int)WyHelper.randomWithRange((challenge.getShard()).field_73012_v, 3, 10);
/*     */     
/* 242 */     BlockPos cage1Pos = groundLayer.func_177985_f(westDistance).func_177970_e(24);
/*     */     
/* 244 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage1Pos.func_177977_b(), cage1Pos.func_177977_b().func_177982_a(4, 4, 1), Blocks.field_196858_iR.func_176223_P(), 3, null));
/* 245 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage1Pos.func_177978_c().func_177974_f(), cage1Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 1), Blocks.field_150350_a.func_176223_P(), 3, null));
/* 246 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage1Pos.func_177978_c().func_177974_f(), cage1Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 0), ironBarsState, 3, null));
/*     */     
/* 248 */     BlockPos cage2Pos = cage1Pos.func_177965_g(cage2Spread);
/*     */     
/* 250 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage2Pos.func_177977_b(), cage2Pos.func_177977_b().func_177982_a(4, 4, 1), Blocks.field_196858_iR.func_176223_P(), 3, null));
/* 251 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage2Pos.func_177978_c().func_177974_f(), cage2Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 1), Blocks.field_150350_a.func_176223_P(), 3, null));
/* 252 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage2Pos.func_177978_c().func_177974_f(), cage2Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 0), ironBarsState, 3, null));
/*     */     
/* 254 */     BlockPos cage3Pos = cage2Pos.func_177965_g(cage3Spread);
/*     */     
/* 256 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage3Pos.func_177977_b(), cage3Pos.func_177977_b().func_177982_a(4, 4, 1), Blocks.field_196858_iR.func_176223_P(), 3, null));
/* 257 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage3Pos.func_177978_c().func_177974_f(), cage3Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 1), Blocks.field_150350_a.func_176223_P(), 3, null));
/* 258 */     blocks.addAll(StructuresHelper.fillCube((World)challenge.getShard(), cage3Pos.func_177978_c().func_177974_f(), cage3Pos.func_177978_c().func_177974_f().func_177982_a(2, 2, 0), ironBarsState, 3, null));
/*     */     
/* 260 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*     */     
/* 262 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public MutableBoundingBox getArenaLimits() {
/* 267 */     return new MutableBoundingBox(-30, -50, -30, 30, 50, 30);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/* 271 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 15 - posId + 1, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() + 15);
/* 272 */     return new ChallengeArena.SpawnPosition(pos, 135.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 276 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 15, challenge.getArenaPos().func_177956_o() - 30, challenge.getArenaPos().func_177952_p() - 15);
/* 277 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 140.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\MarineSmallBaseSimpleArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */