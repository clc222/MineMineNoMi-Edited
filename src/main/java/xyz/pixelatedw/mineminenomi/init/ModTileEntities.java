/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.tileentity.TileEntityType;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.PoneglyphTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModTileEntities
/*    */ {
/* 22 */   private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "mineminenomi");
/*    */   
/* 24 */   public static final RegistryObject<TileEntityType<CustomSpawnerTileEntity>> CUSTOM_SPAWNER = registerTileEntity("custom_spawner", () -> TileEntityType.Builder.func_223042_a(CustomSpawnerTileEntity::new, new Block[] { (Block)ModBlocks.CUSTOM_SPAWNER.get() }).func_206865_a(null));
/* 25 */   public static final RegistryObject<TileEntityType<WantedPosterPackageTileEntity>> WANTED_POSTER_PACKAGE = registerTileEntity("wanted_poster_package", () -> TileEntityType.Builder.func_223042_a(WantedPosterPackageTileEntity::new, new Block[] { (Block)ModBlocks.WANTED_POSTER_PACKAGE.get() }).func_206865_a(null));
/* 26 */   public static final RegistryObject<TileEntityType<WantedPosterTileEntity>> WANTED_POSTER = registerTileEntity("wanted_poster", () -> TileEntityType.Builder.func_223042_a(WantedPosterTileEntity::new, new Block[] { (Block)ModBlocks.WANTED_POSTER.get() }).func_206865_a(null));
/* 27 */   public static final RegistryObject<TileEntityType<DenDenMushiTileEntity>> DEN_DEN_MUSHI = registerTileEntity("den_den_mushi", () -> TileEntityType.Builder.func_223042_a(DenDenMushiTileEntity::new, new Block[] { (Block)ModBlocks.DEN_DEN_MUSHI.get() }).func_206865_a(null));
/* 28 */   public static final RegistryObject<TileEntityType<PoneglyphTileEntity>> PONEGLYPH = registerTileEntity("poneglyph", () -> TileEntityType.Builder.func_223042_a(PoneglyphTileEntity::new, new Block[] { (Block)ModBlocks.PONEGLYPH.get() }).func_206865_a(null));
/* 29 */   public static final RegistryObject<TileEntityType<FlagTileEntity>> FLAG = registerTileEntity("flag", () -> TileEntityType.Builder.func_223042_a(FlagTileEntity::new, new Block[] { (Block)ModBlocks.FLAG.get() }).func_206865_a(null));
/*    */ 
/*    */ 
/*    */   
/*    */   private static <T extends net.minecraft.tileentity.TileEntity> RegistryObject<TileEntityType<T>> registerTileEntity(String localizedName, Supplier<TileEntityType<T>> type) {
/* 34 */     String resourceName = WyHelper.getResourceName(localizedName);
/*    */     
/* 36 */     RegistryObject<TileEntityType<T>> reg = TILE_ENTITIES.register(resourceName, type);
/*    */     
/* 38 */     return reg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 43 */     TILE_ENTITIES.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModTileEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */