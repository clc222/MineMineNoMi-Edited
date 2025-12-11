/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeArena;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ChallengesHelper {
/*    */   public static ChallengeArena.SpawnPosition[] get4DefaultSpawnPoints(BlockPos pos, float yaw) {
/* 17 */     return get4DefaultSpawnPoints(pos, yaw, 0.0F);
/*    */   }
/*    */   
/*    */   public static ChallengeArena.SpawnPosition[] get4DefaultSpawnPoints(BlockPos pos, float yaw, float pitch) {
/* 21 */     ChallengeArena.SpawnPosition first = new ChallengeArena.SpawnPosition(pos, yaw, 0.0F);
/* 22 */     ChallengeArena.SpawnPosition second = new ChallengeArena.SpawnPosition(pos.func_177982_a(-1, 0, 0), yaw, 0.0F);
/* 23 */     ChallengeArena.SpawnPosition third = new ChallengeArena.SpawnPosition(pos.func_177982_a(0, 0, -1), yaw, 0.0F);
/* 24 */     ChallengeArena.SpawnPosition fourth = new ChallengeArena.SpawnPosition(pos.func_177982_a(-1, 0, -1), yaw, 0.0F);
/* 25 */     return new ChallengeArena.SpawnPosition[] { first, second, third, fourth };
/*    */   }
/*    */ 
/*    */   
/*    */   public static Map<String, List<ChallengeCore>> getChallengesByGroup(String category) {
/* 30 */     List<ChallengeCore> challenges = new ArrayList<>();
/* 31 */     WyRegistry.CHALLENGES.getEntries().stream().forEach(ro -> challenges.add(ro.get()));
/* 32 */     Map<String, List<ChallengeCore>> map = (Map<String, List<ChallengeCore>>)challenges.stream().collect(Collectors.groupingBy(ChallengeCore::getCategory));
/* 33 */     return map;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static ChallengeCore getFirstAvailableChallenge(PlayerEntity player, String category) {
/* 39 */     Map<String, List<ChallengeCore>> map = getChallengesByGroup(category);
/*    */     
/* 41 */     if (!map.containsKey(category)) {
/* 42 */       return null;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\ChallengesHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */