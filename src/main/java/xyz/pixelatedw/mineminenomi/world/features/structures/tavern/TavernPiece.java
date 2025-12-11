/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.tavern;
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
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.gen.ChunkGenerator;
/*    */ import net.minecraft.world.gen.feature.structure.StructureManager;
/*    */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*    */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*    */ 
/*    */ public class TavernPiece extends TemplateStructurePiece {
/*    */   private ResourceLocation resourceLocation;
/*    */   
/*    */   public TavernPiece(TemplateManager template, CompoundNBT nbt) {
/* 34 */     super(ModStructures.Pieces.TAVERN_PIECE, nbt);
/* 35 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 36 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 37 */     build(template);
/*    */   }
/*    */   private Rotation rotation;
/*    */   public TavernPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 41 */     super(ModStructures.Pieces.TAVERN_PIECE, 0);
/* 42 */     this.field_186178_c = pos;
/* 43 */     this.rotation = rot;
/* 44 */     this.resourceLocation = new ResourceLocation("mineminenomi", "unaligned/tavern");
/* 45 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_143011_b(CompoundNBT nbt) {
/* 50 */     super.func_143011_b(nbt);
/* 51 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/* 52 */     nbt.func_74778_a("Rot", this.rotation.name());
/*    */   }
/*    */   
/*    */   private void build(TemplateManager templateManager) {
/* 56 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/* 57 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_186222_a(true).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/* 58 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 63 */     if (function.equals("food_chest")) {
/* 64 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.TAVERN_CHEST);
/*    */     }
/*    */     
/* 67 */     if (function.equals("barkeeper_spawn")) {
/* 68 */       world.func_180501_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P(), 3);
/* 69 */       TileEntity spawner = world.func_175625_s(pos);
/* 70 */       if (spawner instanceof CustomSpawnerTileEntity) {
/* 71 */         ((CustomSpawnerTileEntity)spawner).setSpawnerLimit(1);
/* 72 */         ((CustomSpawnerTileEntity)spawner).setSpawnerMob((EntityType)ModEntities.BARKEEPER.get());
/*    */       } 
/*    */     } 
/*    */     
/* 76 */     if (function.equals("trader_spawn")) {
/* 77 */       world.func_180501_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P(), 3);
/* 78 */       TileEntity spawner = world.func_175625_s(pos);
/* 79 */       if (spawner instanceof CustomSpawnerTileEntity) {
/* 80 */         boolean isMarine = rand.nextBoolean();
/* 81 */         ((CustomSpawnerTileEntity)spawner).setSpawnerLimit(1);
/* 82 */         ((CustomSpawnerTileEntity)spawner).setSpawnerMob(isMarine ? (EntityType)ModEntities.MARINE_TRADER.get() : (EntityType)ModEntities.PIRATE_TRADER.get());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_230383_a_(ISeedReader pLevel, StructureManager pStructureManager, ChunkGenerator pChunkGenerator, Random pRandom, MutableBoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
/* 89 */     BlockPos ogPos = this.field_186178_c;
/* 90 */     this.field_186178_c = this.field_186178_c.func_177982_a(0, -1, 0);
/* 91 */     boolean flag = super.func_230383_a_(pLevel, pStructureManager, pChunkGenerator, pRandom, pBox, pChunkPos, pPos);
/* 92 */     this.field_186178_c = ogPos;
/* 93 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\tavern\TavernPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */