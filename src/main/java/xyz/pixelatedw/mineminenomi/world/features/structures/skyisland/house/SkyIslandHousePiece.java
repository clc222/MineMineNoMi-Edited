/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
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
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*    */ 
/*    */ public class SkyIslandHousePiece
/*    */   extends TemplateStructurePiece
/*    */ {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public SkyIslandHousePiece(TemplateManager template, CompoundNBT nbt) {
/* 33 */     super(ModStructures.Pieces.SKY_ISLAND_HOUSE_PIECE, nbt);
/* 34 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 35 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 36 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public SkyIslandHousePiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 41 */     super(ModStructures.Pieces.SKY_ISLAND_HOUSE_PIECE, 0);
/* 42 */     this.field_186178_c = pos;
/* 43 */     this.rotation = rot;
/* 44 */     this.resourceLocation = new ResourceLocation("mineminenomi", "sky_islands/house");
/* 45 */     build(template);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_143011_b(CompoundNBT nbt) {
/* 51 */     super.func_143011_b(nbt);
/* 52 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 53 */     nbt.func_74778_a("Rot", this.rotation.name());
/*    */   }
/*    */ 
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 58 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/* 59 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/* 60 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 69 */     if (function.equals("islander_spawn")) {
/*    */       
/* 71 */       int spawn = rand.nextInt(3);
/* 72 */       if (spawn == 0) {
/* 73 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.CIVILIAN, 3);
/* 74 */       } else if (spawn == 1) {
/* 75 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.TRADER, 1);
/* 76 */       } else if (spawn == 2) {
/*    */         
/* 78 */         world.func_180501_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P(), 3);
/* 79 */         if (world.func_175625_s(pos) instanceof CustomSpawnerTileEntity) {
/*    */           
/* 81 */           CustomSpawnerTileEntity spawner = null;
/* 82 */           spawner = (CustomSpawnerTileEntity)world.func_175625_s(pos);
/* 83 */           spawner.setSpawnerLimit(1);
/* 84 */           spawner.setSpawnerMob((EntityType)ModEntities.ART_OF_WEATHER_TRAINER.get());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\skyisland\house\SkyIslandHousePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */