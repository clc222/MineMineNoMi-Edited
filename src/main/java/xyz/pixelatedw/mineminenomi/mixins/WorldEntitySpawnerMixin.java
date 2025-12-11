/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.spawner.WorldEntitySpawner;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.events.WorldEvents;
/*    */ 
/*    */ @Mixin({WorldEntitySpawner.class})
/*    */ public class WorldEntitySpawnerMixin
/*    */ {
/*    */   @Inject(method = {"spawnMobsForChunkGeneration"}, at = {@At("HEAD")})
/*    */   private static void spawnMobsForChunkGeneration(IServerWorld world, Biome biome, int chunkX, int chunkZ, Random rand, CallbackInfo ci) {
/* 18 */     WorldEvents.spawnOPChunkEntities(world, biome, chunkX, chunkZ, rand);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\WorldEntitySpawnerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */