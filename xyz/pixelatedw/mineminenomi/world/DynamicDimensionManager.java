/*     */ package xyz.pixelatedw.mineminenomi.world;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.BiFunction;
/*     */ import net.minecraft.client.world.DimensionRenderInfo;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.registry.MutableRegistry;
/*     */ import net.minecraft.util.registry.Registry;
/*     */ import net.minecraft.util.registry.SimpleRegistry;
/*     */ import net.minecraft.world.Dimension;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeManager;
/*     */ import net.minecraft.world.border.IBorderListener;
/*     */ import net.minecraft.world.border.WorldBorder;
/*     */ import net.minecraft.world.chunk.listener.IChunkStatusListener;
/*     */ import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.DerivedWorldInfo;
/*     */ import net.minecraft.world.storage.IServerConfiguration;
/*     */ import net.minecraft.world.storage.IServerWorldInfo;
/*     */ import net.minecraft.world.storage.SaveFormat;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDynDimensionsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class DynamicDimensionManager {
/*  45 */   private static final Set<RegistryKey<World>> VANILLA_WORLDS = (Set<RegistryKey<World>>)ImmutableSet.of(World.field_234918_g_, World.field_234919_h_, World.field_234920_i_);
/*  46 */   private static Set<RegistryKey<World>> pendingWorldsToUnregister = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerWorld getOrCreateWorld(MinecraftServer server, RegistryKey<World> worldKey, BiFunction<MinecraftServer, RegistryKey<Dimension>, Dimension> dimensionFactory) {
/*  75 */     Map<RegistryKey<World>, ServerWorld> map = server.forgeGetWorldMap();
/*     */ 
/*     */     
/*  78 */     ServerWorld existingWorld = map.get(worldKey);
/*  79 */     if (existingWorld != null)
/*     */     {
/*  81 */       return existingWorld;
/*     */     }
/*     */     
/*  84 */     return createAndRegisterWorldAndDimension(server, map, worldKey, dimensionFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void markDimensionForUnregistration(MinecraftServer server, RegistryKey<World> WorldToRemove) {
/* 112 */     if (!VANILLA_WORLDS.contains(WorldToRemove))
/*     */     {
/* 114 */       pendingWorldsToUnregister.add(WorldToRemove);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set<RegistryKey<World>> getWorldsPendingUnregistration() {
/* 123 */     return Collections.unmodifiableSet(pendingWorldsToUnregister);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void unregisterScheduledDimensions(MinecraftServer server) {
/* 135 */     Set<RegistryKey<World>> keysToRemove = pendingWorldsToUnregister;
/* 136 */     pendingWorldsToUnregister = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     DimensionGeneratorSettings worldGenSettings = server.func_240793_aU_().func_230418_z_();
/* 145 */     Set<RegistryKey<World>> removedWorldKeys = new HashSet<>();
/* 146 */     ServerWorld overworld = server.func_71218_a(World.field_234918_g_);
/*     */     
/* 148 */     for (RegistryKey<World> WorldKeyToRemove : keysToRemove) {
/*     */       
/* 150 */       ServerWorld removedWorld = (ServerWorld)server.forgeGetWorldMap().remove(WorldKeyToRemove);
/* 151 */       if (removedWorld != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 156 */         for (ServerPlayerEntity player : Lists.newArrayList(removedWorld.func_217369_A())) {
/*     */ 
/*     */           
/* 159 */           RegistryKey<World> respawnKey = player.func_241141_L_();
/*     */           
/* 161 */           if (keysToRemove.contains(respawnKey)) {
/*     */             
/* 163 */             respawnKey = World.field_234918_g_;
/* 164 */             player.func_242111_a(World.field_234918_g_, null, 0.0F, false, false);
/*     */           } 
/* 166 */           if (respawnKey == null)
/*     */           {
/* 168 */             respawnKey = World.field_234918_g_;
/*     */           }
/* 170 */           ServerWorld destinationWorld = server.func_71218_a(respawnKey);
/* 171 */           BlockPos destinationPos = player.func_241140_K_();
/* 172 */           if (destinationPos == null)
/*     */           {
/* 174 */             destinationPos = destinationWorld.func_241135_u_();
/*     */           }
/* 176 */           float respawnAngle = player.func_242109_L();
/*     */ 
/*     */ 
/*     */           
/* 180 */           player.func_200619_a(destinationWorld, destinationPos.func_177958_n(), destinationPos.func_177956_o(), destinationPos.func_177952_p(), respawnAngle, 0.0F);
/*     */         } 
/*     */         
/* 183 */         removedWorld.func_217445_a(null, false, removedWorld.func_217402_u());
/*     */ 
/*     */         
/* 186 */         MinecraftForge.EVENT_BUS.post((Event)new WorldEvent.Unload((IWorld)removedWorld));
/*     */ 
/*     */         
/* 189 */         WorldBorder overworldBorder = overworld.func_175723_af();
/* 190 */         WorldBorder removedWorldBorder = removedWorld.func_175723_af();
/* 191 */         List<IBorderListener> listeners = overworldBorder.field_177758_a;
/* 192 */         IBorderListener targetListener = null;
/* 193 */         for (IBorderListener listener : listeners) {
/*     */           
/* 195 */           if (listener instanceof IBorderListener.Impl && removedWorldBorder == ((IBorderListener.Impl)listener).field_219590_a) {
/*     */             
/* 197 */             targetListener = listener;
/*     */             break;
/*     */           } 
/*     */         } 
/* 201 */         if (targetListener != null)
/*     */         {
/* 203 */           overworldBorder.removeListener(targetListener);
/*     */         }
/*     */ 
/*     */         
/* 207 */         removedWorldKeys.add(WorldKeyToRemove);
/*     */       } 
/*     */     } 
/*     */     
/* 211 */     if (!removedWorldKeys.isEmpty()) {
/*     */ 
/*     */       
/* 214 */       SimpleRegistry simpleRegistry = worldGenSettings.func_236224_e_();
/* 215 */       SimpleRegistry<Dimension> newRegistry = new SimpleRegistry(Registry.field_239700_af_, simpleRegistry.func_241875_b());
/*     */       
/* 217 */       for (Map.Entry<RegistryKey<Dimension>, Dimension> entry : (Iterable<Map.Entry<RegistryKey<Dimension>, Dimension>>)simpleRegistry.func_239659_c_()) {
/*     */         
/* 219 */         RegistryKey<Dimension> oldKey = entry.getKey();
/* 220 */         RegistryKey<World> oldWorldKey = RegistryKey.func_240903_a_(Registry.field_239699_ae_, oldKey.func_240901_a_());
/* 221 */         Dimension dimension = entry.getValue();
/* 222 */         if (oldKey != null && dimension != null && !removedWorldKeys.contains(oldWorldKey))
/*     */         {
/*     */           
/* 225 */           Registry.func_218322_a((Registry)newRegistry, oldKey.getRegistryName(), dimension);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 230 */       worldGenSettings.field_236208_h_ = newRegistry;
/*     */ 
/*     */       
/* 233 */       server.markWorldsDirty();
/*     */       
/* 235 */       WyNetwork.sendToAll(new SSyncDynDimensionsPacket((Set)ImmutableSet.of(), removedWorldKeys));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ServerWorld createAndRegisterWorldAndDimension(MinecraftServer server, Map<RegistryKey<World>, ServerWorld> map, RegistryKey<World> worldKey, BiFunction<MinecraftServer, RegistryKey<Dimension>, Dimension> dimensionFactory) {
/* 243 */     ServerWorld overworld = server.func_71218_a(World.field_234918_g_);
/*     */ 
/*     */     
/* 246 */     RegistryKey<Dimension> dimensionKey = RegistryKey.func_240903_a_(Registry.field_239700_af_, worldKey.func_240901_a_());
/* 247 */     Dimension dimension = dimensionFactory.apply(server, dimensionKey);
/*     */ 
/*     */     
/* 250 */     IChunkStatusListener chunkProgressListener = server.field_213220_d.create(11);
/* 251 */     Executor executor = server.field_213217_au;
/* 252 */     SaveFormat.LevelSave anvilConverter = server.field_71310_m;
/* 253 */     IServerConfiguration worldData = server.func_240793_aU_();
/* 254 */     DimensionGeneratorSettings worldGenSettings = worldData.func_230418_z_();
/* 255 */     DerivedWorldInfo derivedWorldData = new DerivedWorldInfo(worldData, worldData.func_230407_G_());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     SimpleRegistry simpleRegistry = worldGenSettings.func_236224_e_();
/* 265 */     if (simpleRegistry instanceof MutableRegistry) {
/*     */       
/* 267 */       ((MutableRegistry)simpleRegistry).func_218381_a(dimensionKey, dimension, Lifecycle.stable());
/*     */     }
/*     */     else {
/*     */       
/* 271 */       throw new IllegalStateException("Unable to register dimension '" + dimensionKey.func_240901_a_() + "'! Registry not writable!");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     ServerWorld newWorld = new ServerWorld(server, executor, anvilConverter, (IServerWorldInfo)derivedWorldData, worldKey, dimension.func_236059_a_().get(), chunkProgressListener, dimension.func_236064_c_(), worldGenSettings.func_236227_h_(), BiomeManager.func_235200_a_(worldGenSettings.func_236221_b_()), (List)ImmutableList.of(), false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     newWorld.field_73058_d = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     overworld.func_175723_af().func_177737_a((IBorderListener)new IBorderListener.Impl(newWorld.func_175723_af()));
/*     */ 
/*     */     
/* 302 */     map.put(worldKey, newWorld);
/*     */ 
/*     */     
/* 305 */     server.markWorldsDirty();
/*     */ 
/*     */     
/* 308 */     MinecraftForge.EVENT_BUS.post((Event)new WorldEvent.Load((IWorld)newWorld));
/*     */ 
/*     */     
/* 311 */     WyNetwork.sendToAll(new SSyncDynDimensionsPacket((Set)ImmutableSet.of(worldKey), (Set)ImmutableSet.of()));
/*     */     
/* 313 */     return newWorld;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static class ChallengeDimensionRenderInfo extends DimensionRenderInfo {
/*     */     public ChallengeDimensionRenderInfo() {
/* 319 */       super(Float.NaN, false, DimensionRenderInfo.FogType.NORMAL, false, false);
/*     */     }
/*     */ 
/*     */     
/*     */     public Vector3d func_230494_a_(Vector3d p_230494_1_, float p_230494_2_) {
/* 324 */       return p_230494_1_.func_216372_d((p_230494_2_ * 0.94F + 0.06F), (p_230494_2_ * 0.94F + 0.06F), (p_230494_2_ * 0.91F + 0.09F));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_230493_a_(int p_230493_1_, int p_230493_2_) {
/* 329 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\DynamicDimensionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */