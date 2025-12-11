/*    */ package xyz.pixelatedw.mineminenomi.challenges.arenas.parts;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.FourWayBlock;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.BlockQueue;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaPart;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class HayDummyPart extends ArenaPart {
/*    */   public HayDummyPart(InProgressChallenge challenge) {
/* 18 */     super(challenge);
/*    */   }
/*    */ 
/*    */   
/*    */   public void buildPart(ServerWorld world, BlockPos spawnPos, BlockQueue queue) {
/* 23 */     BlockPos basePos = spawnPos;
/* 24 */     BlockPos bodyPos = basePos.func_177984_a();
/* 25 */     BlockPos arm1Pos = bodyPos.func_177978_c();
/* 26 */     BlockPos arm2Pos = bodyPos.func_177968_d();
/*    */     
/* 28 */     BlockState arm1FenceState = (BlockState)Blocks.field_180407_aO.func_176223_P().func_206870_a((Property)FourWayBlock.field_196413_c, Boolean.valueOf(true));
/* 29 */     BlockState arm2FenceState = (BlockState)Blocks.field_180407_aO.func_176223_P().func_206870_a((Property)FourWayBlock.field_196409_a, Boolean.valueOf(true));
/*    */     
/* 31 */     queue.add(arm1Pos, arm1FenceState);
/* 32 */     queue.add(arm2Pos, arm2FenceState);
/* 33 */     queue.add(bodyPos, Blocks.field_150407_cf.func_176223_P());
/* 34 */     queue.add(basePos, Blocks.field_180407_aO.func_176223_P());
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockPos> buildPart(ServerWorld world, BlockPos spawnPos) {
/* 39 */     Set<BlockPos> blocks = new HashSet<>();
/*    */     
/* 41 */     BlockPos basePos = spawnPos;
/*    */     
/* 43 */     if (AbilityHelper.placeBlockIfAllowed((World)world, basePos, Blocks.field_180407_aO.func_176223_P(), 0, null)) {
/* 44 */       blocks.add(basePos);
/*    */     }
/*    */     
/* 47 */     BlockPos bodyPos = basePos.func_177984_a();
/* 48 */     BlockPos arm1Pos = bodyPos.func_177978_c();
/* 49 */     BlockPos arm2Pos = bodyPos.func_177968_d();
/*    */     
/* 51 */     BlockState arm1FenceState = (BlockState)Blocks.field_180407_aO.func_176223_P().func_206870_a((Property)FourWayBlock.field_196413_c, Boolean.valueOf(true));
/* 52 */     BlockState arm2FenceState = (BlockState)Blocks.field_180407_aO.func_176223_P().func_206870_a((Property)FourWayBlock.field_196409_a, Boolean.valueOf(true));
/*    */     
/* 54 */     if (AbilityHelper.placeBlockIfAllowed((World)world, bodyPos, Blocks.field_150407_cf.func_176223_P(), 0, null)) {
/* 55 */       blocks.add(bodyPos);
/*    */     }
/*    */     
/* 58 */     if (AbilityHelper.placeBlockIfAllowed((World)world, arm1Pos, arm1FenceState, 0, null)) {
/* 59 */       blocks.add(arm1Pos);
/*    */     }
/*    */     
/* 62 */     if (AbilityHelper.placeBlockIfAllowed((World)world, arm2Pos, arm2FenceState, 0, null)) {
/* 63 */       blocks.add(arm2Pos);
/*    */     }
/*    */     
/* 66 */     return blocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\challenges\arenas\parts\HayDummyPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */