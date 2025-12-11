/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.ghostship;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Rotation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.IWorld;
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
/*    */ 
/*    */ 
/*    */ public class GhostShipPiece
/*    */   extends TemplateStructurePiece
/*    */ {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public GhostShipPiece(TemplateManager template, CompoundNBT nbt) {
/* 31 */     super(ModStructures.Pieces.GHOST_SHIP_PIECE, nbt);
/* 32 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 33 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 34 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public GhostShipPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 39 */     super(ModStructures.Pieces.GHOST_SHIP_PIECE, 0);
/* 40 */     this.rotation = rot;
/* 41 */     this.field_186178_c = pos;
/* 42 */     this.resourceLocation = new ResourceLocation("mineminenomi", "unaligned/ghost_ship");
/* 43 */     build(template);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_143011_b(CompoundNBT nbt) {
/* 49 */     super.func_143011_b(nbt);
/* 50 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 51 */     nbt.func_74778_a("Rot", this.rotation.name());
/*    */   }
/*    */ 
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 56 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/* 57 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/* 58 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 65 */     if (function.equals("captain_chest")) {
/* 66 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.GHOST_SHIP_CAPTAIN_CHEST);
/*    */     }
/* 68 */     if (function.equals("supplies_chest")) {
/* 69 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.GHOST_SHIP_SUPPLIES_CHEST);
/*    */     }
/* 71 */     if (function.equals("mast_chest")) {
/* 72 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.GHOST_SHIP_CAPTAIN_CHEST);
/*    */     }
/*    */     
/* 75 */     if (function.equals("deck_spawn")) {
/* 76 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*    */     }
/* 78 */     if (function.equals("captain_room_spawn")) {
/* 79 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.BRUTE, 2);
/*    */     }
/* 81 */     if (function.equals("cannons_spawn"))
/* 82 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\ghostship\GhostShipPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */