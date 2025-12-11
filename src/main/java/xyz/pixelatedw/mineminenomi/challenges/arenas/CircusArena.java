/*     */ package xyz.pixelatedw.mineminenomi.challenges.arenas;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ChallengesHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.arenas.parts.SimpleBasePart;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ 
/*     */ public class CircusArena extends ChallengeArena {
/*  22 */   public static final CircusArena INSTANCE = new CircusArena();
/*     */ 
/*     */   
/*     */   private static final int ARENA_SIZE_RADIUS = 30;
/*     */ 
/*     */   
/*     */   private static final int ARENA_SIZE = 60;
/*     */ 
/*     */   
/*     */   private static final BlockProtectionRule CIRCUS_TENT_RULE;
/*     */   
/*     */   private static final Function<BlockPos, BlockState> CIRCUS_TENT_FUNC;
/*     */ 
/*     */   
/*     */   static {
/*  37 */     CIRCUS_TENT_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[0])).addReplaceRules((world, pos, oldState) -> { int id = (Math.abs(pos.func_177958_n()) + Math.abs(pos.func_177952_p())) % 3; if (id == 0) { world.func_175656_a(pos, Blocks.field_196557_aM.func_176223_P()); } else if (id == 1) { world.func_175656_a(pos, Blocks.field_196570_aZ.func_176223_P()); } else { world.func_175656_a(pos, Blocks.field_196556_aL.func_176223_P()); }  return true; }).build();
/*     */     
/*  39 */     CIRCUS_TENT_FUNC = (pos -> {
/*     */         int id = (Math.abs(pos.func_177958_n()) + Math.abs(pos.func_177952_p())) % 3;
/*     */         return (id == 0) ? Blocks.field_196557_aM.func_176223_P() : ((id == 1) ? Blocks.field_196570_aZ.func_176223_P() : Blocks.field_196556_aL.func_176223_P());
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CircusArena() {
/*  51 */     super(ArenaStyle.SIMPLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildArena(InProgressChallenge challenge, BlockQueue queue) {
/*  56 */     int bottomLayerOffset = 29;
/*  57 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(29);
/*  58 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*     */     
/*  60 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 60, 1, 60, Blocks.field_150354_m.func_176223_P(), queue);
/*     */     
/*  62 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 60, 17, 60, Blocks.field_150348_b.func_176223_P(), queue);
/*     */     
/*  64 */     StructuresHelper.calcHollowSphere((World)challenge.getShard(), bottomLayer.func_177981_b(15), 30, 30, 3, CIRCUS_TENT_FUNC, queue);
/*     */     
/*  66 */     StructuresHelper.calcCenteredFilledCube((World)challenge.getShard(), challenge.getArenaPos().func_177979_c(11), 60, 10, 60, ((Block)ModBlocks.CHALLENGE_AIR.get()).func_176223_P(), queue);
/*     */     
/*  68 */     (new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos(), queue);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockPos> buildArena(InProgressChallenge challenge) {
/*  73 */     Set<BlockPos> blocks = Sets.newHashSet();
/*     */     
/*  75 */     int bottomLayerOffset = 29;
/*  76 */     BlockPos bottomLayer = challenge.getArenaPos().func_185334_h().func_177979_c(29);
/*  77 */     BlockPos groundLayer = bottomLayer.func_177981_b(18);
/*     */     
/*  79 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), challenge.getArenaPos().func_177979_c(11), 60, 10, 60, ((Block)ModBlocks.CHALLENGE_AIR.get()).func_176223_P(), 0, null));
/*     */     
/*  81 */     blocks.addAll(StructuresHelper.createHollowSphere((World)challenge.getShard(), bottomLayer.func_177981_b(15), 30, 30, 3, Blocks.field_196557_aM.func_176223_P(), 0, CIRCUS_TENT_RULE));
/*     */     
/*  83 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177984_a(), 60, 17, 60, Blocks.field_150348_b.func_176223_P(), 0, null));
/*     */     
/*  85 */     blocks.addAll(StructuresHelper.createCenteredFilledCube((World)challenge.getShard(), bottomLayer.func_177981_b(17), 60, 1, 60, Blocks.field_150354_m.func_176223_P(), 0, null));
/*     */     
/*  87 */     blocks.addAll((new SimpleBasePart(challenge)).buildPart(challenge.getShard(), challenge.getArenaPos()));
/*     */     
/*  89 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public MutableBoundingBox getArenaLimits() {
/*  94 */     return new MutableBoundingBox(-30, -30, -30, 31, 30, 31);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition getChallengerSpawnPos(int posId, InProgressChallenge challenge) {
/*  98 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() + 15 - posId + 1, challenge.getArenaPos().func_177956_o() - 10, challenge.getArenaPos().func_177952_p() + 15);
/*  99 */     return new ChallengeArena.SpawnPosition(pos, 135.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public static ChallengeArena.SpawnPosition[] getEnemySpawnPos(InProgressChallenge challenge) {
/* 103 */     BlockPos pos = new BlockPos(challenge.getArenaPos().func_177958_n() - 15, challenge.getArenaPos().func_177956_o() - 10, challenge.getArenaPos().func_177952_p() - 15);
/* 104 */     return ChallengesHelper.get4DefaultSpawnPoints(pos, 140.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\CircusArena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */