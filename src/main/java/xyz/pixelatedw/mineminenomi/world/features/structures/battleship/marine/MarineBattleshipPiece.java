/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.battleship.marine;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.Template;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.CanvasSize;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.FlagBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModStructures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ 
/*     */ public class MarineBattleshipPiece extends TemplateStructurePiece {
/*     */   private ResourceLocation resourceLocation;
/*  48 */   private Set<UUID> posters = new HashSet<>(); private Rotation rotation;
/*     */   
/*     */   public MarineBattleshipPiece(TemplateManager template, CompoundNBT nbt) {
/*  51 */     super(ModStructures.Pieces.MARINE_BATTLESHIP_PIECE, nbt);
/*  52 */     this.resourceLocation = new ResourceLocation(nbt.func_74779_i("Template"));
/*  53 */     this.rotation = Rotation.valueOf(nbt.func_74779_i("Rot"));
/*  54 */     build(template);
/*     */   }
/*     */   
/*     */   public MarineBattleshipPiece(TemplateManager template, BlockPos pos, Rotation rot) {
/*  58 */     super(ModStructures.Pieces.MARINE_BATTLESHIP_PIECE, 0);
/*  59 */     this.rotation = rot;
/*  60 */     this.field_186178_c = pos;
/*  61 */     this.resourceLocation = new ResourceLocation("mineminenomi", "marine/battle_ship");
/*  62 */     build(template);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_143011_b(CompoundNBT nbt) {
/*  67 */     super.func_143011_b(nbt);
/*  68 */     nbt.func_74778_a("Template", this.resourceLocation.toString());
/*  69 */     nbt.func_74778_a("Rot", this.rotation.name());
/*     */   }
/*     */   
/*     */   private void build(TemplateManager templateManager) {
/*  73 */     Template template = templateManager.func_200220_a(this.resourceLocation);
/*  74 */     PlacementSettings placementsettings = (new PlacementSettings()).func_186220_a(this.rotation).func_186214_a(Mirror.NONE).func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c);
/*  75 */     func_186173_a(template, this.field_186178_c, placementsettings);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_186175_a(String function, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox sbb) {
/*  81 */     if (function.equals("supplies_chest")) {
/*  82 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_SHIP_SUPPLIES_CHEST);
/*     */     }
/*     */     
/*  85 */     if (function.equals("captain_chest")) {
/*  86 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_SHIP_CAPTAIN_CHEST);
/*     */     }
/*     */     
/*  89 */     if (function.equals("rum_chest")) {
/*  90 */       StructuresHelper.spawnLoot((IWorld)world, pos, ModLootTables.LARGE_MARINE_SHIP_RUM_CHEST);
/*     */     }
/*     */ 
/*     */     
/*  94 */     if (function.equals("deck_spawn")) {
/*  95 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 15);
/*     */     }
/*     */     
/*  98 */     if (function.equals("back_deck_spawn")) {
/*  99 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BRUTE, 5);
/*     */     }
/*     */     
/* 102 */     if (function.equals("tower_spawn")) {
/* 103 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 2);
/*     */     }
/*     */     
/* 106 */     if (function.equals("cannons_spawn_1")) {
/* 107 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 7);
/*     */     }
/*     */     
/* 110 */     if (function.equals("cannons_spawn_2")) {
/* 111 */       StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */     }
/*     */     
/* 114 */     if (function.equals("captain_room_spawn")) {
/* 115 */       if (rand.nextInt(20) == 0) {
/* 116 */         NPCWorldData npcWorldData = NPCWorldData.get();
/* 117 */         EventsWorldData eventsWorldData = EventsWorldData.get();
/* 118 */         Optional<TrackedNPC> npc = npcWorldData.getRandomTrackedMob(ModValues.MARINE);
/* 119 */         if (npc.isPresent() && !eventsWorldData.hasNTEventFor(npc.get())) {
/* 120 */           NotoriousEntity entity = ((TrackedNPC)npc.get()).createTrackedMob((World)world.func_201672_e());
/* 121 */           NPCWorldData.get().updateTrackedMob(world.func_201672_e(), npc.get());
/* 122 */           entity.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 123 */           world.func_217376_c((Entity)entity);
/*     */         } 
/*     */       } else {
/*     */         
/* 127 */         StructuresHelper.spawnMobs((IWorld)world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       } 
/*     */     }
/*     */     
/* 131 */     Rotation rot = this.field_186177_b.func_186215_c();
/*     */ 
/*     */     
/* 134 */     if (function.startsWith("wanted_poster_")) {
/* 135 */       ExtendedWorldData worldData = ExtendedWorldData.get();
/* 136 */       Optional<Map.Entry<UUID, Long>> bounty = worldData.getAllBounties().entrySet().stream().findAny();
/*     */       
/* 138 */       generateWantedPoster(world, pos, rot, Direction.SOUTH, bounty);
/*     */     } 
/*     */     
/* 141 */     if (function.equals("flag")) {
/* 142 */       BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*     */       
/* 144 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(Direction.SOUTH));
/* 145 */       state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)CanvasSize.HUGE);
/*     */       
/* 147 */       world.func_180501_a(pos, state, 3);
/* 148 */       FlagBlock.placeSubs((IWorld)world, pos, state, false);
/*     */       
/* 150 */       TileEntity flagTile = world.func_175625_s(pos);
/* 151 */       if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 152 */         ((FlagTileEntity)flagTile).setFaction(ResourceLocation.func_208304_a(ModValues.MARINE.toString()));
/*     */       }
/*     */     } 
/*     */     
/* 156 */     if (function.equals("flag2")) {
/* 157 */       BlockState state = ((Block)ModBlocks.FLAG.get()).func_176223_P();
/*     */       
/* 159 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(Direction.SOUTH));
/* 160 */       state = (BlockState)state.func_206870_a((Property)FlagBlock.SIZE, (Comparable)CanvasSize.LARGE);
/*     */       
/* 162 */       world.func_180501_a(pos, state, 3);
/* 163 */       FlagBlock.placeSubs((IWorld)world, pos, state, false);
/*     */       
/* 165 */       TileEntity flagTile = world.func_175625_s(pos);
/* 166 */       if (flagTile != null && flagTile instanceof FlagTileEntity) {
/* 167 */         ((FlagTileEntity)flagTile).setFaction(ResourceLocation.func_208304_a(ModValues.MARINE.toString()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void generateWantedPoster(IServerWorld world, BlockPos pos, Rotation rot, Direction dir, Optional<Map.Entry<UUID, Long>> bounty) {
/* 173 */     if (world.func_201670_d()) {
/*     */       return;
/*     */     }
/*     */     
/* 177 */     if (!world.func_201674_k().nextBoolean()) {
/*     */       return;
/*     */     }
/*     */     
/* 181 */     if (bounty.isPresent()) {
/* 182 */       UUID key = (UUID)((Map.Entry)bounty.get()).getKey();
/* 183 */       PlayerEntity player = world.func_217371_b(key);
/*     */       
/* 185 */       if (this.posters.contains(key) || player == null) {
/* 186 */         world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */         
/*     */         return;
/*     */       } 
/* 190 */       WantedPosterData wantedPosterData = new WantedPosterData((LivingEntity)player, ((Long)((Map.Entry)bounty.get()).getValue()).longValue());
/*     */       
/* 192 */       BlockState state = ((Block)ModBlocks.WANTED_POSTER.get()).func_176223_P();
/* 193 */       state = (BlockState)state.func_206870_a((Property)WantedPosterBlock.FACING, (Comparable)rot.func_185831_a(dir));
/* 194 */       world.func_180501_a(pos, state, 2);
/* 195 */       TileEntity tile = world.func_175625_s(pos);
/* 196 */       if (tile instanceof WantedPosterTileEntity) {
/* 197 */         ((WantedPosterTileEntity)tile).setWantedPosterData(wantedPosterData);
/* 198 */         tile.func_70296_d();
/*     */       } 
/* 200 */       this.posters.add(key);
/*     */     } else {
/*     */       
/* 203 */       world.func_180501_a(pos, ((Block)ModBlocks.STRUCTURE_AIR.get()).func_176223_P(), 3);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\battleship\marine\MarineBattleshipPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */