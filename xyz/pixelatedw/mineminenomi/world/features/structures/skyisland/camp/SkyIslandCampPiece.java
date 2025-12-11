/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkyIslandCampPiece
/*    */   extends TemplateStructurePiece
/*    */ {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public SkyIslandCampPiece(TemplateManager template, CompoundNBT nbt) {
/* 30 */     super(ModStructures.Pieces.SKY_ISLAND_CAMP_PIECE, nbt);
/* 31 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 32 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 33 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkyIslandCampPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 38 */     super(ModStructures.Pieces.SKY_ISLAND_CAMP_PIECE, 0);
/* 39 */     this.field_186178_c = pos;
/* 40 */     this.rotation = rot;
/* 41 */     this.resourceLocation = new ResourceLocation("mineminenomi", "sky_islands/camp");
/* 42 */     build(template);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_143011_b(CompoundNBT nbt) {
/* 48 */     super.func_143011_b(nbt);
/* 49 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 50 */     nbt.func_74778_a("Rot", this.rotation.name());
/*    */   }
/*    */ 
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 55 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/* 56 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/* 57 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 66 */     if (function.equals("campers_spawn")) {
/*    */       
/* 68 */       int spawn = rand.nextInt(4);
/* 69 */       if (spawn > 0) {
/* 70 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.CIVILIAN, 3);
/* 71 */       } else if (spawn == 0) {
/* 72 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.TRADER, 1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\skyisland\camp\SkyIslandCampPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */