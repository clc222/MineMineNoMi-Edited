/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Rotation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*    */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*    */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*    */ 
/*    */ public class PirateSmallShipPiece
/*    */   extends TemplateStructurePiece
/*    */ {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public PirateSmallShipPiece(TemplateManager template, CompoundNBT nbt) {
/* 37 */     super(ModStructures.Pieces.PIRATE_SMALL_SHIP_PIECE, nbt);
/* 38 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 39 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 40 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public PirateSmallShipPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 45 */     super(ModStructures.Pieces.PIRATE_SMALL_SHIP_PIECE, 0);
/* 46 */     this.rotation = rot;
/* 47 */     this.field_186178_c = pos;
/* 48 */     this.resourceLocation = new ResourceLocation("mineminenomi", "pirate/small_ship");
/* 49 */     build(template);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_143011_b(CompoundNBT nbt) {
/* 55 */     super.func_143011_b(nbt);
/* 56 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 57 */     nbt.func_74778_a("Rot", this.rotation.name());
/*    */   }
/*    */ 
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 62 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/* 63 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/* 64 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 71 */     if (function.equals("supplies_chest")) {
/* 72 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.SMALL_PIRATE_SHIP_SUPPLIES_CHEST);
/*    */     }
/*    */     
/* 75 */     if (function.equals("deck_spawn_1")) {
/* 76 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*    */     }
/* 78 */     if (function.equals("deck_spawn_2")) {
/* 79 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_230383_a_(ISeedReader world, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 85 */     boolean flag = super.func_230383_a_(world, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/*    */     
/* 87 */     for (Template.BlockInfo info : this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_150486_ae)) {
/* 88 */       world.func_180501_a(info.field_186242_a, (BlockState)info.field_186243_b.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false)), 3);
/*    */     }
/* 90 */     for (Template.BlockInfo info : this.field_186176_a.func_215381_a(this.field_186178_c, this.field_186177_b, Blocks.field_196630_bt)) {
/* 91 */       world.func_180501_a(info.field_186242_a, (BlockState)info.field_186243_b.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false)), 3);
/*    */     }
/* 93 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallship\pirate\PirateSmallShipPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */