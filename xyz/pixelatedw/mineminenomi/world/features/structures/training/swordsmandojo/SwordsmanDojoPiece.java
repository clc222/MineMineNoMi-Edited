/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.training.swordsmandojo;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Rotation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.ISeedReader;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*    */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*    */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*    */ 
/*    */ public class SwordsmanDojoPiece
/*    */   extends TemplateStructurePiece {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public SwordsmanDojoPiece(TemplateManager template, CompoundNBT nbt) {
/* 35 */     super(ModStructures.Pieces.SWORDSMAN_DOJO_PIECE, nbt);
/* 36 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 37 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 38 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public SwordsmanDojoPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 43 */     super(ModStructures.Pieces.SWORDSMAN_DOJO_PIECE, 0);
/* 44 */     this.field_186178_c = pos;
/* 45 */     this.rotation = rot;
/* 46 */     this.resourceLocation = new ResourceLocation("mineminenomi", "unaligned/swordsman_dojo");
/* 47 */     build(template);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_143011_b(CompoundNBT nbt) {
/* 53 */     super.func_143011_b(nbt);
/* 54 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 55 */     nbt.func_74778_a("Rot", this.rotation.name());
/*    */   }
/*    */ 
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 60 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/* 61 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/* 62 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 68 */     if (function.equals("trainer_spawn")) {
/*    */       
/* 70 */       world.func_180501_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P(), 3);
/* 71 */       TileEntity spawner = world.func_175625_s(pos);
/* 72 */       if (spawner instanceof CustomSpawnerTileEntity) {
/*    */         
/* 74 */         ((CustomSpawnerTileEntity)spawner).setSpawnerLimit(1);
/* 75 */         ((CustomSpawnerTileEntity)spawner).setSpawnerMob((EntityType)ModEntities.SWORDSMAN_TRAINER.get());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_230383_a_(ISeedReader pLevel, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 85 */     boolean flag = super.func_230383_a_(pLevel, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/*    */     
/* 87 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\training\swordsmandojo\SwordsmanDojoPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */