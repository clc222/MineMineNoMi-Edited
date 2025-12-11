/*    */ package xyz.pixelatedw.mineminenomi.world.features.structures.training.sniperrange;
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
/*    */ import net.minecraft.util.math.MutableBoundingBox;
/*    */ import net.minecraft.world.IServerWorld;
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
/*    */ public class SniperRangePiece
/*    */   extends TemplateStructurePiece {
/*    */   private ResourceLocation resourceLocation;
/*    */   private Rotation rotation;
/*    */   
/*    */   public SniperRangePiece(TemplateManager template, CompoundNBT nbt) {
/* 31 */     super(ModStructures.Pieces.SNIPER_RANGE_PIECE, nbt);
/* 32 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/* 33 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/* 34 */     build(template);
/*    */   }
/*    */ 
/*    */   
/*    */   public SniperRangePiece(TemplateManager template, BlockPos pos, Rotation rot) {
/* 39 */     super(ModStructures.Pieces.SNIPER_RANGE_PIECE, 0);
/* 40 */     this.field_186178_c = pos;
/* 41 */     this.rotation = rot;
/* 42 */     this.resourceLocation = new ResourceLocation("mineminenomi", "unaligned/sniper_range");
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
/* 57 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215204_a);
/* 58 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/* 64 */     if (function.equals("trainer_spawn")) {
/*    */       
/* 66 */       world.func_180501_a(pos, ((Block)ModBlocks.CUSTOM_SPAWNER.get()).func_176223_P(), 3);
/* 67 */       TileEntity spawner = world.func_175625_s(pos);
/* 68 */       if (spawner instanceof CustomSpawnerTileEntity) {
/*    */         
/* 70 */         ((CustomSpawnerTileEntity)spawner).setSpawnerLimit(1);
/* 71 */         ((CustomSpawnerTileEntity)spawner).setSpawnerMob((EntityType)ModEntities.SNIPER_TRAINER.get());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\training\sniperrange\SniperRangePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */