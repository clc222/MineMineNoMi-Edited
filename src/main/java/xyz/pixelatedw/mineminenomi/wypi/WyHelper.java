/*      */ package xyz.pixelatedw.mineminenomi.wypi;
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.base.Predicates;
/*      */ import com.google.common.base.Strings;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.gson.Gson;
/*      */ import com.mojang.blaze3d.matrix.MatrixStack;
/*      */ import com.mojang.blaze3d.systems.RenderSystem;
/*      */ import java.awt.Color;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Writer;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.stream.Collector;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockState;
/*      */ import net.minecraft.block.Blocks;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.dispenser.IPosition;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntitySpawnPlacementRegistry;
/*      */ import net.minecraft.entity.EntityType;
/*      */ import net.minecraft.entity.LivingEntity;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*      */ import net.minecraft.entity.player.PlayerEntity;
/*      */ import net.minecraft.entity.player.ServerPlayerEntity;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.nbt.CompoundNBT;
/*      */ import net.minecraft.network.IPacket;
/*      */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*      */ import net.minecraft.network.play.server.SRemoveEntityEffectPacket;
/*      */ import net.minecraft.network.play.server.SSpawnParticlePacket;
/*      */ import net.minecraft.particles.IParticleData;
/*      */ import net.minecraft.particles.ParticleTypes;
/*      */ import net.minecraft.potion.Effect;
/*      */ import net.minecraft.potion.EffectInstance;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.Direction;
/*      */ import net.minecraft.util.EntityPredicates;
/*      */ import net.minecraft.util.IReorderingProcessor;
/*      */ import net.minecraft.util.Mirror;
/*      */ import net.minecraft.util.RegistryKey;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.ResourceLocationException;
/*      */ import net.minecraft.util.Rotation;
/*      */ import net.minecraft.util.Util;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.BlockRayTraceResult;
/*      */ import net.minecraft.util.math.ChunkPos;
/*      */ import net.minecraft.util.math.EntityRayTraceResult;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.RayTraceContext;
/*      */ import net.minecraft.util.math.RayTraceResult;
/*      */ import net.minecraft.util.math.shapes.VoxelShapes;
/*      */ import net.minecraft.util.math.vector.Vector3d;
/*      */ import net.minecraft.util.math.vector.Vector3f;
/*      */ import net.minecraft.util.math.vector.Vector3i;
/*      */ import net.minecraft.util.text.ITextComponent;
/*      */ import net.minecraft.world.IBlockReader;
/*      */ import net.minecraft.world.IServerWorld;
/*      */ import net.minecraft.world.IWorld;
/*      */ import net.minecraft.world.IWorldReader;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.chunk.ChunkSection;
/*      */ import net.minecraft.world.chunk.IChunk;
/*      */ import net.minecraft.world.gen.ChunkGenerator;
/*      */ import net.minecraft.world.gen.Heightmap;
/*      */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*      */ import net.minecraft.world.gen.feature.template.IntegrityProcessor;
/*      */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*      */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*      */ import net.minecraft.world.gen.feature.template.Template;
/*      */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*      */ import net.minecraft.world.server.ChunkHolder;
/*      */ import net.minecraft.world.server.ServerWorld;
/*      */ import net.minecraft.world.spawner.WorldEntitySpawner;
/*      */ import net.minecraftforge.common.extensions.IForgeWorld;
/*      */ import net.minecraftforge.entity.PartEntity;
/*      */ import net.minecraftforge.versions.mcp.MCPVersion;
/*      */ import xyz.pixelatedw.mineminenomi.ModMain;
/*      */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*      */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*      */ import xyz.pixelatedw.mineminenomi.data.world.ChallengesWorldData;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*      */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*      */ import xyz.pixelatedw.mineminenomi.mixins.ITemplateMixin;
/*      */ import xyz.pixelatedw.mineminenomi.packets.server.SSpawnParticleEffectPacket;
/*      */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*      */ 
/*      */ public class WyHelper {
/*  121 */   private static final Random RANDOM = new Random();
/*      */ 
/*      */   
/*      */   private static final Collector<?, ?, ?> SHUFFLER;
/*      */ 
/*      */ 
/*      */   
/*      */   public static String formatBytes(long bytes) {
/*  129 */     int unit = 1024;
/*  130 */     if (bytes < unit) {
/*  131 */       return bytes + " B";
/*      */     }
/*  133 */     int exp = (int)(Math.log(bytes) / Math.log(unit));
/*  134 */     String pre = "KMGTPE".charAt(exp - 1) + "";
/*  135 */     return String.format("%.1f %sB", new Object[] { Double.valueOf(bytes / Math.pow(unit, exp)), pre });
/*      */   }
/*      */ 
/*      */   
/*      */   public static String capitalize(String text) {
/*  140 */     return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getResourceName(String text) {
/*  145 */     return text
/*  146 */       .replaceAll("[ \\t]+$", "")
/*  147 */       .replaceAll("\\(", "")
/*  148 */       .replaceAll("\\)", "")
/*  149 */       .replaceAll("\\s+", "_")
/*  150 */       .replaceAll("[\\'\\:\\-\\,\\#]", "")
/*  151 */       .replaceAll("\\&", "and").toLowerCase();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String escapeJSON(String raw) {
/*  156 */     String escaped = raw;
/*  157 */     escaped = escaped.replace("\\", "\\\\");
/*  158 */     escaped = escaped.replace("\"", "\\\"");
/*  159 */     escaped = escaped.replace("\b", "\\b");
/*  160 */     escaped = escaped.replace("\f", "\\f");
/*  161 */     escaped = escaped.replace("\n", "\\n");
/*  162 */     escaped = escaped.replace("\r", "\\r");
/*  163 */     escaped = escaped.replace("\t", "\\t");
/*  164 */     return escaped;
/*      */   }
/*      */   
/*      */   public static String formatTimeMMSS(long time) {
/*  168 */     return String.format("%02d:%02d", new Object[] { Long.valueOf(time / 60L), Long.valueOf(time % 60L) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Color hslToColor(float h, float s, float l) {
/*  179 */     if (s < 0.0F || s > 100.0F) {
/*      */       
/*  181 */       String message = "Color parameter outside of expected range - Saturation";
/*  182 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/*  185 */     if (l < 0.0F || l > 100.0F) {
/*      */       
/*  187 */       String message = "Color parameter outside of expected range - Luminance";
/*  188 */       throw new IllegalArgumentException(message);
/*      */     } 
/*      */     
/*  191 */     h %= 360.0F;
/*  192 */     h /= 360.0F;
/*  193 */     s /= 100.0F;
/*  194 */     l /= 100.0F;
/*      */     
/*  196 */     float q = 0.0F;
/*      */     
/*  198 */     if (l < 0.5D) {
/*  199 */       q = l * (1.0F + s);
/*      */     } else {
/*      */       
/*  202 */       q = l + s - s * l;
/*      */     } 
/*      */     
/*  205 */     float p = 2.0F * l - q;
/*      */     
/*  207 */     float r = Math.max(0.0F, hueToRGB(p, q, h + 0.33333334F));
/*  208 */     float g = Math.max(0.0F, hueToRGB(p, q, h));
/*  209 */     float b = Math.max(0.0F, hueToRGB(p, q, h - 0.33333334F));
/*      */     
/*  211 */     r = Math.min(r, 1.0F);
/*  212 */     g = Math.min(g, 1.0F);
/*  213 */     b = Math.min(b, 1.0F);
/*      */     
/*  215 */     return new Color(r, g, b);
/*      */   }
/*      */ 
/*      */   
/*      */   private static float hueToRGB(float p, float q, float h) {
/*  220 */     if (h < 0.0F) {
/*  221 */       h++;
/*      */     }
/*      */     
/*  224 */     if (h > 1.0F) {
/*  225 */       h--;
/*      */     }
/*      */     
/*  228 */     if (6.0F * h < 1.0F) {
/*  229 */       return p + (q - p) * 6.0F * h;
/*      */     }
/*      */     
/*  232 */     if (2.0F * h < 1.0F) {
/*  233 */       return q;
/*      */     }
/*      */     
/*  236 */     if (3.0F * h < 2.0F) {
/*  237 */       return p + (q - p) * 6.0F * (0.6666667F - h);
/*      */     }
/*      */     
/*  240 */     return p;
/*      */   }
/*      */   
/*      */   public static Color intToRGB(int rgb, int alpha) {
/*  244 */     int val = 0xFF000000 | rgb;
/*  245 */     int r = val >> 16 & 0xFF;
/*  246 */     int g = val >> 8 & 0xFF;
/*  247 */     int b = val >> 0 & 0xFF;
/*  248 */     return new Color(r, g, b, alpha);
/*      */   }
/*      */   
/*      */   public static String rgbToHex(int red, int green, int blue) {
/*  252 */     return String.format("#%02X%02X%02X", new Object[] { Integer.valueOf(red), Integer.valueOf(green), Integer.valueOf(blue) });
/*      */   }
/*      */   
/*      */   public static Color hexToRGB(String hexColor) {
/*  256 */     if (Strings.isNullOrEmpty(hexColor)) {
/*  257 */       return Color.WHITE;
/*      */     }
/*      */     
/*  260 */     if (hexColor.startsWith("#")) {
/*  261 */       hexColor = hexColor.substring(1);
/*      */     }
/*      */     
/*  264 */     if (hexColor.length() == 8) {
/*  265 */       int r = Integer.parseInt(hexColor.substring(0, 2), 16);
/*  266 */       int g = Integer.parseInt(hexColor.substring(2, 4), 16);
/*  267 */       int b = Integer.parseInt(hexColor.substring(4, 6), 16);
/*  268 */       int a = Integer.parseInt(hexColor.substring(6, 8), 16);
/*  269 */       return new Color(r, g, b, a);
/*      */     } 
/*      */     
/*  272 */     return Color.decode("#" + hexColor);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Color getComplementaryColor(Color color) {
/*  278 */     return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
/*      */   }
/*      */ 
/*      */   
/*      */   public static float colorTolerance(float tolerance) {
/*  283 */     return colorTolerance(tolerance, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float colorTolerance(float tolerance, boolean hasDisturbance) {
/*  288 */     float color = RANDOM.nextFloat();
/*      */     
/*  290 */     if (color <= tolerance || (!hasDisturbance && color >= tolerance + 0.3D)) {
/*  291 */       return tolerance;
/*      */     }
/*      */     
/*  294 */     return color;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isInCombat(LivingEntity entity) {
/*  306 */     LivingEntity lastAttacker = entity.func_70643_av();
/*  307 */     LivingEntity lastAttackerCache = EntityStatsCapability.get(entity).getLastAttacker();
/*      */     
/*  309 */     if (lastAttacker == null && lastAttackerCache != null) {
/*  310 */       lastAttacker = lastAttackerCache;
/*      */     }
/*      */ 
/*      */     
/*  314 */     if (lastAttacker != null && lastAttacker.func_70089_S() && Math.abs(lastAttacker.func_70068_e((Entity)entity)) <= 10000.0D) {
/*  315 */       return true;
/*      */     }
/*      */     
/*  318 */     IEntityStats props = EntityStatsCapability.get(entity);
/*  319 */     if (props.isInCombatCache()) {
/*  320 */       return true;
/*      */     }
/*      */     
/*  323 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean isInChallengeDimension(World world) {
/*  327 */     return isInChallengeDimension(world.func_234923_W_());
/*      */   }
/*      */   
/*      */   public static boolean isInChallengeDimension(RegistryKey<World> world) {
/*  331 */     return isInChallengeDimension(world.func_240901_a_().toString());
/*      */   }
/*      */   
/*      */   public static boolean isInChallengeDimension(String dimensionId) {
/*  335 */     return dimensionId.contains("challenges_");
/*      */   }
/*      */   
/*      */   public static void sendApplyEffectToAllNearby(LivingEntity player, Vector3d pos, int distance, EffectInstance effect) {
/*  339 */     player.func_184102_h().func_184103_al().func_148543_a(null, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, distance, player.func_130014_f_().func_234923_W_(), (IPacket)new SPlayEntityEffectPacket(player.func_145782_y(), effect));
/*      */   }
/*      */   
/*      */   public static void sendRemoveEffectToAllNearby(LivingEntity player, Vector3d pos, int distance, Effect effect) {
/*  343 */     player.func_184102_h().func_184103_al().func_148543_a(null, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, distance, player.func_130014_f_().func_234923_W_(), (IPacket)new SRemoveEntityEffectPacket(player.func_145782_y(), effect));
/*      */   }
/*      */   
/*      */   public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ) {
/*  347 */     spawnParticles(data, world, posX, posY, posZ, 0.0F, 0.0F, 0.0F);
/*      */   }
/*      */   
/*      */   public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ, float offsetX, float offsetY, float offsetZ) {
/*  351 */     spawnParticles(data, world, posX, posY, posZ, offsetX, offsetY, offsetZ, 1);
/*      */   }
/*      */   
/*      */   public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ, float offsetX, float offsetY, float offsetZ, int amount) {
/*  355 */     if (world.field_72995_K) {
/*      */       return;
/*      */     }
/*      */     
/*  359 */     SSpawnParticlePacket sSpawnParticlePacket = new SSpawnParticlePacket(data, true, (float)posX, (float)posY, (float)posZ, offsetX, offsetY, offsetZ, 0.0F, amount);
/*      */     
/*  361 */     for (int j = 0; j < world.func_217369_A().size(); j++) {
/*  362 */       ServerPlayerEntity player = world.func_217369_A().get(j);
/*  363 */       BlockPos blockpos = new BlockPos(player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_());
/*  364 */       if (blockpos.func_218137_a((IPosition)new Vector3d(posX, posY, posZ), 512.0D)) {
/*  365 */         player.field_71135_a.func_147359_a((IPacket)sSpawnParticlePacket);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void spawnParticleEffect(ParticleEffect effect, Entity spawner, double posX, double posY, double posZ) {
/*  371 */     spawnParticleEffect(effect, spawner, posX, posY, posZ, null);
/*      */   }
/*      */   
/*      */   public static void spawnParticleEffect(ParticleEffect effect, Entity spawner, double posX, double posY, double posZ, @Nullable ParticleEffect.Details details) {
/*  375 */     if (spawner == null) {
/*      */       return;
/*      */     }
/*      */     
/*  379 */     if (spawner.field_70170_p.field_72995_K) {
/*      */       return;
/*      */     }
/*      */     
/*  383 */     WyNetwork.sendToAllAroundDistance(new SSpawnParticleEffectPacket(effect, spawner, posX, posY, posZ, details), spawner.field_70170_p, spawner.func_213303_ch(), 256);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void spawnParticleEffectForOwner(ParticleEffect effect, PlayerEntity spawner, double posX, double posY, double posZ, @Nullable ParticleEffect.Details details) {
/*  388 */     if (spawner == null || spawner.field_70170_p.field_72995_K) {
/*      */       return;
/*      */     }
/*      */     
/*  392 */     WyNetwork.sendTo(new SSpawnParticleEffectPacket(effect, (Entity)spawner, posX, posY, posZ, details), spawner);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static Vector3d propulsion(LivingEntity entity, double extraVelX, double extraVelZ) {
/*  399 */     return propulsion(entity, extraVelX, 0.0D, extraVelZ);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static Vector3d propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ) {
/*  405 */     return entity.func_70040_Z().func_216372_d(extraVelX, extraVelY, extraVelZ);
/*      */   }
/*      */   
/*      */   public static <T extends Entity> List<T> getNearbyEntities(Vector3d pos, IWorld world, double radius, @Nullable Predicate<Entity> predicate, Class<? extends T>... clazzez) {
/*  409 */     return getNearbyEntities(pos, world, radius, radius, radius, predicate, clazzez);
/*      */   }
/*      */   
/*      */   public static <T extends Entity> List<T> getNearbyEntities(Vector3d pos, IWorld world, double sizeX, double sizeY, double sizeZ, @Nullable Predicate<Entity> predicate, Class<? extends T>... clazzez) {
/*  413 */     AxisAlignedBB aabb = (new AxisAlignedBB(pos, pos.func_72441_c(1.0D, 1.0D, 1.0D))).func_72314_b(sizeX, sizeY, sizeZ);
/*      */     
/*  415 */     return getNearbyEntities(world, aabb, predicate, clazzez);
/*      */   } @SafeVarargs
/*      */   public static <T extends Entity> List<T> getNearbyEntities(IWorld world, AxisAlignedBB aabb, @Nullable Predicate<Entity> predicate, Class<? extends T>... clazzez) {
/*      */     Predicate predicate2;
/*      */     Class[] arrayOfClass;
/*  420 */     if (clazzez.length <= 0) {
/*  421 */       arrayOfClass = new Class[] { Entity.class };
/*      */     }
/*      */     
/*  424 */     if (predicate == null) {
/*  425 */       predicate2 = Predicates.alwaysTrue();
/*      */     }
/*      */     
/*  428 */     Predicate<PartEntity<? extends Entity>> predicate1 = ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.and(e -> !(e instanceof xyz.pixelatedw.mineminenomi.entities.SphereEntity)).and((Predicate)predicate2);
/*      */     
/*  430 */     List<T> list = new ArrayList<>();
/*      */     
/*  432 */     for (Class<? extends T> clz : arrayOfClass) {
/*  433 */       list.addAll(world.func_175647_a(clz, aabb, predicate1));
/*      */       
/*  435 */       if (world instanceof IForgeWorld) {
/*  436 */         for (PartEntity<? extends Entity> partEntity : (Iterable<PartEntity<? extends Entity>>)((IForgeWorld)world).getPartEntities()) {
/*  437 */           if (clz.isAssignableFrom(partEntity.getClass()) && partEntity.func_174813_aQ().func_72326_a(aabb) && (predicate1 == null || predicate1.test(partEntity))) {
/*  438 */             list.add((T)partEntity);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  444 */     return list;
/*      */   }
/*      */   
/*      */   public static <T extends LivingEntity> List<T> getNearbyLiving(Vector3d pos, IWorld world, double sizeX, double sizeY, double sizeZ, @Nullable Predicate<Entity> predicate) {
/*  448 */     return (List)getNearbyEntities(pos, world, sizeX, sizeY, sizeZ, predicate, (Class<? extends Entity>[])new Class[] { LivingEntity.class });
/*      */   }
/*      */   
/*      */   public static <T extends LivingEntity> List<T> getNearbyLiving(Vector3d pos, IWorld world, double radius, @Nullable Predicate<Entity> predicate) {
/*  452 */     return (List)getNearbyEntities(pos, world, radius, predicate, (Class<? extends Entity>[])new Class[] { LivingEntity.class });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends PlayerEntity> List<T> getNearbyPlayers(Vector3d pos, IWorld world, double radius, @Nullable Predicate<Entity> predicate) {
/*      */     Predicate predicate2;
/*  459 */     if (predicate == null) {
/*  460 */       predicate2 = Predicates.alwaysTrue();
/*      */     }
/*      */     
/*  463 */     Predicate predicate1 = ModEntityPredicates.IS_ALIVE_AND_SURVIVAL.and((Predicate)predicate2);
/*      */     
/*  465 */     return (List<T>)world.func_217369_A().stream()
/*  466 */       .filter(predicate1)
/*  467 */       .filter(target -> {
/*      */           double d0 = target.func_70092_e(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
/*      */           double d1 = radius * radius;
/*  470 */           return (d1 < 0.0D || d0 < d1);
/*      */         
/*  472 */         }).collect(Collectors.toList());
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T extends Entity> List<T> getEntitiesNearSphere(Vector3d pos, IWorld world, double radius, @Nullable Predicate<Entity> predicate, Class<? extends T>... classEntities) {
/*  477 */     radius *= 2.0D;
/*      */     
/*  479 */     if (predicate != null) {
/*  480 */       predicate = predicate.and(EntityPredicates.field_180132_d);
/*      */     } else {
/*  482 */       predicate = EntityPredicates.field_180132_d;
/*      */     } 
/*      */     
/*  485 */     double x = pos.field_72450_a;
/*  486 */     double y = pos.field_72448_b;
/*  487 */     double z = pos.field_72449_c;
/*      */     
/*  489 */     AxisAlignedBB aabb = (new AxisAlignedBB(x, y, z, x, y, z)).func_72314_b(radius, radius, radius);
/*      */     
/*  491 */     List<T> list = new ArrayList<>();
/*      */     
/*  493 */     for (Class<? extends T> clzz : classEntities) {
/*  494 */       list.addAll(world.func_175647_a(clzz, aabb, predicate));
/*      */     }
/*      */     
/*  497 */     for (int i = 0; i < list.size(); i++) {
/*  498 */       if (((Entity)list.get(i)).func_70092_e(x, y, z) >= radius) {
/*  499 */         list.remove(i);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/*  505 */     return list;
/*      */   }
/*      */   
/*      */   public static <T extends Entity> List<T> getEntitiesAroundCircle(Vector3d pos, IWorld world, double radius, double innerDepth, @Nullable Predicate<Entity> predicate, Class<? extends T>... classEntities) {
/*  509 */     if (predicate != null) {
/*  510 */       predicate = predicate.and(EntityPredicates.field_180132_d);
/*      */     } else {
/*  512 */       predicate = EntityPredicates.field_180132_d;
/*      */     } 
/*      */     
/*  515 */     double x = pos.field_72450_a;
/*  516 */     double y = pos.field_72448_b;
/*  517 */     double z = pos.field_72449_c;
/*      */     
/*  519 */     AxisAlignedBB aabb = (new AxisAlignedBB(x, y, z, x, y, z)).func_72314_b(radius, radius, radius);
/*      */     
/*  521 */     List<T> list = new ArrayList<>();
/*      */     
/*  523 */     for (Class<? extends T> clzz : classEntities) {
/*  524 */       List<? extends T> entities = world.func_175647_a(clzz, aabb, predicate);
/*      */       
/*  526 */       for (Entity entity : entities) {
/*  527 */         double distanceSqr = entity.func_70092_e(x, y, z);
/*  528 */         double maxDistanceSqr = (radius - innerDepth) * (radius - innerDepth);
/*      */         
/*  530 */         if (distanceSqr >= maxDistanceSqr) {
/*  531 */           list.add((T)entity);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  536 */     return list;
/*      */   }
/*      */   
/*      */   public static RayTraceResult rayTraceBlocksAndEntities(Entity entity) {
/*  540 */     return rayTraceBlocksAndEntities(entity, 1024.0D, 0.4F);
/*      */   }
/*      */   
/*      */   public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance) {
/*  544 */     return rayTraceBlocksAndEntities(entity, distance, 0.2F);
/*      */   }
/*      */   public static RayTraceResult rayTraceBlocksAndEntities(Entity entity, double distance, float entityBoxRange) {
/*      */     EntityRayTraceResult entityRayTraceResult;
/*  548 */     Vector3d lookVec = entity.func_70040_Z();
/*  549 */     Vector3d startVec = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*  550 */     Vector3d endVec = startVec.func_178787_e(entity.func_70040_Z().func_186678_a(distance));
/*  551 */     BlockRayTraceResult blockRayTraceResult = entity.field_70170_p.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
/*  552 */     RayTraceResult entityResult = null;
/*      */     
/*  554 */     for (int i = 0; i < distance * 2.0D; i++) {
/*      */       
/*  556 */       if (entityResult != null) {
/*      */         break;
/*      */       }
/*      */       
/*  560 */       float scale = i / 2.0F;
/*  561 */       Vector3d pos = startVec.func_178787_e(lookVec.func_186678_a(scale));
/*      */       
/*  563 */       Vector3d min = pos.func_72441_c(entityBoxRange, entityBoxRange, entityBoxRange);
/*  564 */       Vector3d max = pos.func_72441_c(-entityBoxRange, -entityBoxRange, -entityBoxRange);
/*  565 */       List<Entity> list = entity.field_70170_p.func_72839_b(entity, new AxisAlignedBB(min.field_72450_a, min.field_72448_b, min.field_72449_c, max.field_72450_a, max.field_72448_b, max.field_72449_c));
/*  566 */       list.remove(entity);
/*  567 */       for (Entity e : list) {
/*  568 */         if (e instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity) {
/*      */           continue;
/*      */         }
/*      */         
/*  572 */         entityRayTraceResult = new EntityRayTraceResult(e, pos);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  577 */     if (entityRayTraceResult != null && entityRayTraceResult.func_216347_e().func_72438_d(startVec) <= blockRayTraceResult.func_216347_e().func_72438_d(startVec)) {
/*  578 */       return (RayTraceResult)entityRayTraceResult;
/*      */     }
/*  580 */     return (RayTraceResult)blockRayTraceResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockPos rayTraceBlockSafe(LivingEntity entity, float range) {
/*  587 */     World world = entity.field_70170_p;
/*  588 */     Vector3d startVec = entity.func_213303_ch().func_72441_c(0.0D, entity.func_70047_e(), 0.0D);
/*  589 */     Vector3d endVec = startVec.func_178787_e(entity.func_70040_Z().func_186678_a(range));
/*  590 */     BlockRayTraceResult result = world.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)entity));
/*  591 */     BlockPos dashPos = result.func_216354_b().equals(Direction.DOWN) ? result.func_216350_a().func_177979_c(2) : result.func_216350_a().func_177971_a(result.func_216354_b().func_176730_m());
/*      */     
/*  593 */     if (dashPos.func_177956_o() > entity.field_70170_p.func_217301_I()) {
/*  594 */       dashPos = dashPos.func_177982_a(0, entity.field_70170_p.func_217301_I() - dashPos.func_177956_o(), 0);
/*      */     }
/*      */     
/*  597 */     return dashPos;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPosClearForPlayer(World world, BlockPos pos) {
/*  602 */     return ((world.func_175623_d(pos) || world.func_180495_p(pos).func_196952_d((IBlockReader)world, pos).func_197766_b()) && (world
/*  603 */       .func_175623_d(pos.func_177984_a()) || world.func_180495_p(pos.func_177984_a()).func_196952_d((IBlockReader)world, pos.func_177984_a()).func_197766_b()));
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockPos getClearPositionForPlayer(LivingEntity entity, BlockPos pos) {
/*  608 */     boolean posIsFree = isPosClearForPlayer(entity.field_70170_p, pos);
/*  609 */     int i = 0;
/*      */     
/*  611 */     while (!posIsFree) {
/*      */       
/*  613 */       Direction dir = Direction.values()[i];
/*  614 */       pos = pos.func_177982_a(
/*  615 */           -dir.func_82601_c() * 2, 
/*  616 */           -dir.func_96559_d() * 2, 
/*  617 */           -dir.func_82599_e() * 2);
/*  618 */       posIsFree = isPosClearForPlayer(entity.field_70170_p, pos);
/*  619 */       if (posIsFree) {
/*      */         break;
/*      */       }
/*  622 */       if (i < (Direction.values()).length - 1) {
/*  623 */         i++;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  630 */     return posIsFree ? pos : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockRayTraceResult rayTraceBlocks(Entity source, double distance) {
/*  635 */     Vector3d startVec = source.func_213303_ch().func_72441_c(0.0D, source.func_70047_e(), 0.0D);
/*  636 */     Vector3d endVec = startVec.func_178787_e(source.func_70040_Z().func_186678_a(distance));
/*  637 */     return source.field_70170_p.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, source));
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockRayTraceResult rayTraceBlocks(World world, Vector3d startVec, Vector3d endVec) {
/*  642 */     return world.func_217299_a(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null));
/*      */   }
/*      */   
/*      */   public static EntityRayTraceResult rayTraceEntities(Entity source, double distance) {
/*  646 */     return rayTraceEntities(source, distance, 1.25D, null);
/*      */   }
/*      */   
/*      */   public static EntityRayTraceResult rayTraceEntities(Entity source, double distance, double width, Predicate<Entity> check) {
/*  650 */     Vector3d startVec = source.func_213303_ch().func_72441_c(0.0D, source.func_70047_e(), 0.0D);
/*  651 */     Vector3d endVec = startVec.func_178787_e(source.func_70040_Z().func_186678_a(distance));
/*  652 */     AxisAlignedBB boundingBox = source.func_174813_aQ().func_186662_g(distance);
/*      */     
/*  654 */     List<Entity> entities = source.field_70170_p.func_175674_a(source, boundingBox, entity -> (entity != source));
/*      */ 
/*      */     
/*  657 */     for (Entity entity : entities) {
/*  658 */       AxisAlignedBB entityBB = entity.func_174813_aQ().func_186662_g(1.0D);
/*  659 */       Optional<Vector3d> optional = entityBB.func_216365_b(startVec, endVec);
/*      */       
/*  661 */       if (optional.isPresent()) {
/*  662 */         Vector3d targetVec = optional.get();
/*  663 */         double distFromSource = MathHelper.func_76133_a(startVec.func_72436_e(targetVec));
/*      */         
/*  665 */         if (distFromSource < distance) {
/*  666 */           List<Entity> targets = getNearbyEntities(targetVec, (IWorld)source.field_70170_p, width, check, (Class<? extends Entity>[])new Class[0]);
/*  667 */           targets.remove(source);
/*  668 */           Optional<Entity> target = targets.stream().findFirst();
/*      */           
/*  670 */           if (target.isPresent()) {
/*  671 */             return new EntityRayTraceResult(target.get(), endVec);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  677 */     return new EntityRayTraceResult(null, endVec);
/*      */   }
/*      */   
/*      */   public static boolean isBlockNearby(Entity entity, int radius, Predicate<BlockState> test) {
/*  681 */     BlockPos.Mutable pos = new BlockPos.Mutable();
/*  682 */     for (int x = -radius; x <= radius; x++) {
/*  683 */       for (int y = -radius; y <= radius; y++) {
/*  684 */         for (int z = -radius; z <= radius; z++) {
/*  685 */           pos.func_189532_c(entity.func_226277_ct_() + x, entity.func_226278_cu_() + y, entity.func_226281_cx_() + z);
/*  686 */           BlockState state = entity.field_70170_p.func_180495_p((BlockPos)pos);
/*  687 */           if (test.test(state)) {
/*  688 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  694 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBlockNearby(Entity entity, int radius, Block... blocks) {
/*  699 */     List<Block> list = Arrays.asList(blocks);
/*  700 */     return isBlockNearby(entity, radius, state -> list.contains(state.func_177230_c()));
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(Entity entity, int radius) {
/*  705 */     return getNearbyBlocks(entity.func_233580_cy_(), (IWorld)entity.field_70170_p, radius);
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius) {
/*  710 */     return getNearbyBlocks(pos, world, radius, radius, radius, state -> !(state.func_177230_c() instanceof net.minecraft.block.AirBlock));
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius, List<Block> bannedBlocks) {
/*  716 */     return getNearbyBlocks(pos, world, radius, null, bannedBlocks);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int radius, @Nullable Predicate<BlockPos> predicate, List<Block> bannedBlocks) {
/*  722 */     predicate = (predicate == null) ? (blockPos -> true) : predicate;
/*      */     
/*  724 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  725 */     List<BlockPos> blockLocations = new ArrayList<>();
/*  726 */     for (int x = -radius; x <= radius; x++) {
/*  727 */       for (int y = -radius; y <= radius; y++) {
/*  728 */         for (int z = -radius; z <= radius; z++) {
/*  729 */           mutpos.func_181079_c(pos.func_177958_n() + x, pos.func_177956_o() + y, pos.func_177952_p() + z);
/*  730 */           if (predicate.test(mutpos)) {
/*      */ 
/*      */ 
/*      */             
/*  734 */             Block block = world.func_180495_p((BlockPos)mutpos).func_177230_c();
/*  735 */             if (!bannedBlocks.contains(block)) {
/*  736 */               blockLocations.add(mutpos.func_185334_h());
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  742 */     return blockLocations;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> getNearbyBlocks(BlockPos pos, IWorld world, int sizeX, int sizeY, int sizeZ, @Nullable Predicate<BlockState> predicate) {
/*  746 */     predicate = (predicate == null) ? (blockPos -> true) : predicate;
/*      */     
/*  748 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  749 */     List<BlockPos> blockLocations = new ArrayList<>();
/*  750 */     for (int x = -sizeX; x <= sizeX; x++) {
/*  751 */       for (int y = -sizeY; y <= sizeY; y++) {
/*  752 */         for (int z = -sizeZ; z <= sizeZ; z++) {
/*  753 */           mutpos.func_181079_c(pos.func_177958_n() + x, pos.func_177956_o() + y, pos.func_177952_p() + z);
/*  754 */           BlockState state = world.func_180495_p((BlockPos)mutpos);
/*  755 */           if (predicate.test(state))
/*      */           {
/*      */ 
/*      */             
/*  759 */             blockLocations.add(mutpos.func_185334_h());
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  764 */     return blockLocations;
/*      */   }
/*      */   
/*      */   public static List<BlockPos> getNearbyTileEntities(Entity player, int radius) {
/*  768 */     return getNearbyTileEntities(player.func_233580_cy_(), player.field_70170_p, radius);
/*      */   }
/*      */   
/*      */   public static List<BlockPos> getNearbyTileEntities(BlockPos pos, World world, int radius) {
/*  772 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  773 */     List<BlockPos> blockLocations = new ArrayList<>();
/*  774 */     for (int x = -radius; x <= radius; x++) {
/*  775 */       for (int y = -radius; y <= radius; y++) {
/*  776 */         for (int z = -radius; z <= radius; z++) {
/*  777 */           mutpos.func_181079_c(pos.func_177958_n() + x, pos.func_177956_o() + y, pos.func_177952_p() + z);
/*  778 */           if (world.func_180495_p((BlockPos)mutpos).func_177230_c() != Blocks.field_150350_a && world.func_175625_s((BlockPos)mutpos) != null) {
/*  779 */             blockLocations.add(mutpos.func_185334_h());
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  785 */     return blockLocations;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius) {
/*  790 */     return findOnGroundSpawnLocation(world, type, spawnLocation, radius, 0);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius, int offset) {
/*  795 */     BlockPos blockpos = null;
/*  796 */     for (int i = 0; i < 10; i++) {
/*  797 */       int x = (int)randomWithRange(spawnLocation.func_177958_n() - offset - radius, spawnLocation.func_177958_n() + offset + radius);
/*  798 */       int z = (int)randomWithRange(spawnLocation.func_177952_p() - offset - radius, spawnLocation.func_177952_p() + offset + radius);
/*  799 */       int y = world.func_201676_a(Heightmap.Type.WORLD_SURFACE, x, z);
/*  800 */       BlockPos blockpos1 = new BlockPos(x, y, z);
/*  801 */       if (WorldEntitySpawner.canSpawnAtBody(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, (IWorldReader)world, blockpos1, type)) {
/*  802 */         blockpos = blockpos1;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  807 */     return blockpos;
/*      */   }
/*      */   
/*      */   public static BlockPos findValidGroundLocation(World level, BlockPos spawnPos, int radius, int offset) {
/*  811 */     BlockPos blockpos = null;
/*      */     
/*  813 */     for (int i = 0; i < 10; i++) {
/*  814 */       int x = (int)randomWithRange(spawnPos.func_177958_n() - offset - radius, spawnPos.func_177958_n() + offset + radius);
/*  815 */       int z = (int)randomWithRange(spawnPos.func_177952_p() - offset - radius, spawnPos.func_177952_p() + offset + radius);
/*      */       
/*  817 */       for (int j = -5; j < 0; j++) {
/*  818 */         int y = spawnPos.func_177956_o() + j;
/*  819 */         BlockPos pos = new BlockPos(x, y, z);
/*  820 */         BlockState state = level.func_180495_p(pos.func_177977_b());
/*      */         
/*  822 */         if (!state.func_196958_f() && state.func_185904_a().func_76220_a() && !state.func_196952_d((IBlockReader)level, pos).equals(VoxelShapes.func_197880_a())) {
/*  823 */           blockpos = pos;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  828 */       if (blockpos != null) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/*  833 */     return blockpos;
/*      */   }
/*      */   
/*      */   public static BlockPos findValidGroundLocation(Entity entity, BlockPos spawnPos, int radius, int offset) {
/*  837 */     BlockPos blockpos = entity.func_233580_cy_();
/*  838 */     for (int i = 0; i < 10; i++) {
/*  839 */       int x = (int)randomWithRange(spawnPos.func_177958_n() - offset - radius, spawnPos.func_177958_n() + offset + radius);
/*  840 */       int z = (int)randomWithRange(spawnPos.func_177952_p() - offset - radius, spawnPos.func_177952_p() + offset + radius);
/*      */       
/*  842 */       BlockPos pos = new BlockPos(x, spawnPos.func_177956_o(), z);
/*  843 */       BlockState state = entity.field_70170_p.func_180495_p(pos.func_177977_b());
/*  844 */       if (!state.func_196958_f() && state.func_185904_a().func_76220_a() && !state.func_196952_d((IBlockReader)entity.field_70170_p, pos).equals(VoxelShapes.func_197880_a())) {
/*  845 */         blockpos = pos;
/*      */         break;
/*      */       } 
/*      */     } 
/*  849 */     return blockpos;
/*      */   }
/*      */   
/*      */   public static Vector3d findValidGroundLocation(World level, Vector3d origin, int radius, int offset) {
/*  853 */     for (int i = 0; i < 10; i++) {
/*  854 */       int x = (int)randomWithRange(-offset - radius, offset + radius);
/*  855 */       int z = (int)randomWithRange(-offset - radius, offset + radius);
/*  856 */       int y = level.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int)origin.field_72450_a, (int)origin.field_72449_c);
/*      */       
/*  858 */       BlockPos check = new BlockPos(origin.func_82615_a() + x, y, origin.func_82616_c() + z);
/*  859 */       BlockState state = level.func_180495_p(check.func_177977_b());
/*  860 */       if (!state.func_196958_f() && state.func_185904_a().func_76220_a() && !state.func_196952_d((IBlockReader)level, check).equals(VoxelShapes.func_197880_a())) {
/*  861 */         origin = origin.func_72441_c(x, y - origin.func_82617_b(), z);
/*      */         break;
/*      */       } 
/*      */     } 
/*  865 */     return origin;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getTextureName(String texture) {
/*  870 */     for (String s : texture.split("/")) {
/*      */       
/*  872 */       if (s.contains(".png"))
/*      */       {
/*  874 */         return s.replace(".png", "");
/*      */       }
/*      */     } 
/*  877 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean setBlockStateInChunk(World world, BlockPos pos, BlockState newState, int flags) {
/*  895 */     if (world instanceof ServerWorld && isInChallengeDimension(world) && (
/*  896 */       flags & 0x200) != 0) {
/*      */       
/*      */       try {
/*  899 */         boolean placed = (swapBlockData((IWorld)world, pos, newState) != null);
/*  900 */         return placed;
/*      */       }
/*  902 */       catch (Exception ex) {
/*  903 */         ex.printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  908 */     if (World.func_189509_E(pos)) {
/*  909 */       return false;
/*      */     }
/*  911 */     if (!world.field_72995_K && world.func_234925_Z_()) {
/*  912 */       return false;
/*      */     }
/*      */     
/*  915 */     Chunk chunk = world.func_175726_f(pos);
/*  916 */     pos = pos.func_185334_h();
/*      */     
/*  918 */     BlockState old = world.func_180495_p(pos);
/*  919 */     int oldLight = old.getLightValue((IBlockReader)world, pos);
/*  920 */     int oldOpacity = old.func_200016_a((IBlockReader)world, pos);
/*      */     
/*  922 */     BlockState blockstate = chunk.func_177436_a(pos, newState, ((flags & 0x40) != 0));
/*      */     
/*  924 */     if (world instanceof ServerWorld && !newState.func_196958_f() && isInChallengeDimension(world)) {
/*  925 */       InProgressChallenge challenge = ChallengesWorldData.get().getInProgressChallengeFor((ServerWorld)world);
/*  926 */       challenge.trackBlockPos(pos);
/*      */     } 
/*      */     
/*  929 */     if (blockstate != null) {
/*  930 */       BlockState blockstate1 = world.func_180495_p(pos);
/*  931 */       if ((flags & 0x80) == 0 && blockstate1 != blockstate && (blockstate1.func_200016_a((IBlockReader)world, pos) != oldOpacity || blockstate1.getLightValue((IBlockReader)world, pos) != oldLight || blockstate1
/*  932 */         .func_215691_g() || blockstate.func_215691_g())) {
/*  933 */         world.func_217381_Z().func_76320_a("queueCheckLight");
/*  934 */         world.func_72863_F().func_212863_j_().func_215568_a(pos);
/*  935 */         world.func_217381_Z().func_76319_b();
/*      */       } 
/*      */       
/*  938 */       if ((flags & 0x100) != 0) {
/*  939 */         world.markAndNotifyBlock(pos, chunk, blockstate, newState, flags, 512);
/*      */       }
/*  941 */       else if ((flags & 0x2) != 0 && (!world.field_72995_K || (flags & 0x4) == 0) && (world.field_72995_K || chunk == null || (chunk.func_217321_u() != null && chunk.func_217321_u().func_219065_a(ChunkHolder.LocationType.TICKING)))) {
/*  942 */         world.func_184138_a(pos, blockstate, newState, flags);
/*      */       } 
/*      */     } 
/*  945 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static BlockState swapBlockData(IWorld world, BlockPos pos, BlockState newState) {
/*  951 */     int i = pos.func_177958_n() & 0xF;
/*  952 */     int j = Math.abs(pos.func_177956_o());
/*  953 */     int k = pos.func_177952_p() & 0xF;
/*  954 */     IChunk chunk = world.func_217349_x(pos);
/*  955 */     ChunkSection cs = chunk.func_76587_i()[j >> 4];
/*  956 */     if (cs == Chunk.field_186036_a) {
/*  957 */       if (newState.func_196958_f()) {
/*  958 */         return null;
/*      */       }
/*  960 */       cs = new ChunkSection(j >> 4 << 4);
/*  961 */       chunk.func_76587_i()[j >> 4] = cs;
/*      */     } 
/*      */     
/*  964 */     boolean flag = cs.func_76663_a();
/*  965 */     BlockState state = cs.func_222629_a(i, j & 0xF, k, newState);
/*      */     
/*  967 */     if (state == newState) {
/*  968 */       return null;
/*      */     }
/*      */     
/*  971 */     boolean flag1 = cs.func_76663_a();
/*  972 */     if (flag != flag1) {
/*  973 */       world.func_72863_F().func_212863_j_().func_215567_a(pos, flag1);
/*      */     }
/*      */     
/*  976 */     int pRecursionLeft = 25;
/*  977 */     state.func_241483_b_(world, pos, i, pRecursionLeft - 1);
/*  978 */     newState.func_241482_a_(world, pos, i, pRecursionLeft - 1);
/*  979 */     newState.func_241483_b_(world, pos, i, pRecursionLeft - 1);
/*      */     
/*  981 */     chunk.func_177427_f(true);
/*  982 */     return state;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void spawnDamageIndicatorParticles(World world, LivingEntity target, int amount) {
/*  987 */     if (world instanceof ServerWorld) {
/*  988 */       ((ServerWorld)world).func_195598_a((IParticleData)ParticleTypes.field_197615_h, target.func_226277_ct_(), target.func_226283_e_(0.5D), target.func_226281_cx_(), amount, 0.1D, 0.0D, 0.1D, 0.2D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAprilFirst() {
/*  998 */     Calendar cal = Calendar.getInstance();
/*  999 */     int day = cal.get(5);
/* 1000 */     int month = cal.get(2) + 1;
/* 1001 */     return (month == 4 && day == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getDaysSince(Date date) {
/* 1006 */     Date now = new Date();
/*      */     
/* 1008 */     long diffInMillies = Math.abs(now.getTime() - date.getTime());
/* 1009 */     long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
/*      */     
/* 1011 */     return diff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int roundToNiceNumber(float n) {
/* 1028 */     float n1 = n - n % 10.0F;
/* 1029 */     float n2 = n + 10.0F - n % 10.0F;
/* 1030 */     float res = (n - n1 > n2 - n) ? n2 : n1;
/* 1031 */     return (int)res;
/*      */   }
/*      */   
/*      */   public static float minutesToTicks(float minutes) {
/* 1035 */     return secondsToTicks(minutes * 60.0F);
/*      */   }
/*      */   
/*      */   public static float secondsToTicks(float seconds) {
/* 1039 */     return seconds * 20.0F;
/*      */   }
/*      */   
/*      */   public static double percentage(double percent, double value) {
/* 1043 */     return percent / 100.0D * value;
/*      */   }
/*      */   
/*      */   public static double randomWithRange(int min, int max) {
/* 1047 */     return (RANDOM.nextInt(max + 1 - min) + min);
/*      */   }
/*      */   
/*      */   public static double randomWithRange(Random rand, int min, int max) {
/* 1051 */     return (rand.nextInt(max + 1 - min) + min);
/*      */   }
/*      */   
/*      */   public static double randomDouble(Random rand) {
/* 1055 */     return rand.nextDouble() * 2.0D - 1.0D;
/*      */   }
/*      */   
/*      */   public static double randomDouble() {
/* 1059 */     return RANDOM.nextDouble() * 2.0D - 1.0D;
/*      */   }
/*      */   
/*      */   public static double getScaledRandomValue(double index, double minRange, double maxRange, double maxIndex) {
/* 1063 */     return randomDouble() * (maxRange - (maxRange - minRange) * index / maxIndex);
/*      */   }
/*      */   
/*      */   public static int round(int value) {
/* 1067 */     String valueString = "" + value;
/*      */     
/* 1069 */     if (valueString.length() < 1) {
/* 1070 */       return value;
/*      */     }
/*      */     
/* 1073 */     return round(value, valueString.length() - 1);
/*      */   }
/*      */   
/*      */   public static int round(int value, int nth) {
/* 1077 */     String valueString = "" + value;
/*      */     
/* 1079 */     if (valueString.length() < 1 || nth < 0) {
/* 1080 */       return value;
/*      */     }
/*      */     
/* 1083 */     if (nth == 0) {
/* 1084 */       nth = 1;
/*      */     }
/*      */     
/* 1087 */     int n = (int)Math.pow(10.0D, (nth - 1));
/* 1088 */     int r = 5 * n / 10;
/*      */     
/* 1090 */     return (value + r) / n * n;
/*      */   }
/*      */ 
/*      */   
/*      */   public static long clamp(long num, long min, long max) {
/* 1095 */     return (num < min) ? min : Math.min(num, max);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, IReorderingProcessor text, int posX, int posY, int color) {
/* 1104 */     matrixStack.func_227860_a_();
/* 1105 */     font.func_238407_a_(matrixStack, text, posX, posY - 0.7F, 0);
/* 1106 */     font.func_238407_a_(matrixStack, text, posX, posY + 0.7F, 0);
/* 1107 */     font.func_238407_a_(matrixStack, text, posX + 0.7F, posY, 0);
/* 1108 */     font.func_238407_a_(matrixStack, text, posX - 0.7F, posY, 0);
/* 1109 */     matrixStack.func_227861_a_(0.0D, 0.0D, 1.0D);
/* 1110 */     font.func_238422_b_(matrixStack, text, posX, posY, color);
/* 1111 */     matrixStack.func_227865_b_();
/* 1112 */     RenderSystem.enableAlphaTest();
/* 1113 */     RenderSystem.enableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, String text, int posX, int posY, int color) {
/* 1118 */     matrixStack.func_227860_a_();
/* 1119 */     String unformattedText = escapeTextFormattingChars(text);
/* 1120 */     font.func_238405_a_(matrixStack, unformattedText, posX, posY - 0.7F, 0);
/* 1121 */     font.func_238405_a_(matrixStack, unformattedText, posX, posY + 0.7F, 0);
/* 1122 */     font.func_238405_a_(matrixStack, unformattedText, posX + 0.7F, posY, 0);
/* 1123 */     font.func_238405_a_(matrixStack, unformattedText, posX - 0.7F, posY, 0);
/* 1124 */     matrixStack.func_227861_a_(0.0D, 0.0D, 1.0D);
/* 1125 */     font.func_238421_b_(matrixStack, text, posX, posY, color);
/* 1126 */     matrixStack.func_227865_b_();
/* 1127 */     RenderSystem.enableAlphaTest();
/* 1128 */     RenderSystem.enableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawStringWithBorder(FontRenderer font, MatrixStack matrixStack, ITextComponent text, int posX, int posY, int color) {
/* 1133 */     matrixStack.func_227860_a_();
/* 1134 */     font.func_243246_a(matrixStack, text, posX, posY - 0.7F, 0);
/* 1135 */     font.func_243246_a(matrixStack, text, posX, posY + 0.7F, 0);
/* 1136 */     font.func_243246_a(matrixStack, text, posX + 0.7F, posY, 0);
/* 1137 */     font.func_243246_a(matrixStack, text, posX - 0.7F, posY, 0);
/* 1138 */     matrixStack.func_227861_a_(0.0D, 0.0D, 1.0D);
/* 1139 */     font.func_243248_b(matrixStack, text, posX, posY, color);
/* 1140 */     matrixStack.func_227865_b_();
/* 1141 */     RenderSystem.enableAlphaTest();
/* 1142 */     RenderSystem.enableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static String escapeTextFormattingChars(String text) {
/* 1147 */     return text.replaceAll("§[0-9a-f]", "");
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void rotateCorpse(MatrixStack matrixStack, LivingEntity entityLiving, float ageInTicks, float headYawOffset, float partialTicks) {
/* 1153 */     matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F + headYawOffset));
/*      */     
/* 1155 */     if (entityLiving.field_70725_aQ > 0) {
/*      */       
/* 1157 */       float animTime = (entityLiving.field_70725_aQ + partialTicks - 1.0F) / 20.0F * 1.6F;
/* 1158 */       animTime = MathHelper.func_76129_c(animTime);
/* 1159 */       if (animTime > 1.0F) {
/* 1160 */         animTime = 1.0F;
/*      */       }
/*      */       
/* 1163 */       matrixStack.func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(animTime * 90.0F));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateJSONLangs() {
/* 1173 */     Map<String, String> sorted = sortAlphabetically(WyRegistry.getLangMap());
/* 1174 */     Set<Map.Entry<String, String>> set = sorted.entrySet();
/* 1175 */     Iterator<Map.Entry<String, String>> iter = set.iterator();
/*      */     
/* 1177 */     Map.Entry<String, String> prevEntry = null;
/*      */     
/* 1179 */     File langFolder = new File(ModMain.getResourceFolderPath() + "/assets/" + "mineminenomi" + "/lang/");
/* 1180 */     langFolder.mkdirs();
/*      */     
/* 1182 */     if (langFolder.exists())
/*      */     {
/* 1184 */       try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ModMain.getResourceFolderPath() + "/assets/" + "mineminenomi" + "/lang/en_us.json"), "UTF-8"))) {
/*      */         
/* 1186 */         writer.write("{\n");
/* 1187 */         while (iter.hasNext()) {
/*      */           
/* 1189 */           Map.Entry<String, String> entry = iter.next();
/*      */           
/* 1191 */           if (prevEntry != null)
/*      */           {
/* 1193 */             if (!((String)prevEntry.getKey()).substring(0, 2).equals(((String)entry.getKey()).substring(0, 2)))
/*      */             {
/* 1195 */               writer.write("\n");
/*      */             }
/*      */           }
/*      */           
/* 1199 */           String value = escapeJSON(entry.getValue());
/* 1200 */           if (iter.hasNext()) {
/* 1201 */             writer.write("\t\"" + (String)entry.getKey() + "\": \"" + value + "\",\n");
/*      */           } else {
/*      */             
/* 1204 */             writer.write("\t\"" + (String)entry.getKey() + "\": \"" + value + "\"\n");
/*      */           } 
/*      */           
/* 1207 */           prevEntry = entry;
/*      */         } 
/* 1209 */         writer.write("}\n");
/* 1210 */         writer.close();
/*      */       }
/* 1212 */       catch (Exception e) {
/*      */         
/* 1214 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> List<T> shuffle(List<T> ar) {
/* 1222 */     List<T> newList = new ArrayList<>(ar);
/* 1223 */     Collections.shuffle(newList);
/* 1224 */     return newList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> List<T> shuffle(List<T> ar, long seed) {
/* 1230 */     List<T> newList = new ArrayList<>(ar);
/* 1231 */     Random rand = new Random(seed);
/* 1232 */     Collections.shuffle(newList, rand);
/* 1233 */     return newList;
/*      */   }
/*      */ 
/*      */   
/*      */   public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map) {
/* 1238 */     List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());
/*      */     
/* 1240 */     Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
/*      */         {
/*      */           
/*      */           public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
/*      */           {
/* 1245 */             return ((Comparable)o1.getKey()).compareTo(o2.getKey());
/*      */           }
/*      */         });
/*      */     
/* 1249 */     Map<K, V> sortedMap = new LinkedHashMap<>();
/*      */     
/* 1251 */     for (Map.Entry<K, V> entry : entries)
/*      */     {
/* 1253 */       sortedMap.put(entry.getKey(), entry.getValue());
/*      */     }
/*      */     
/* 1256 */     return sortedMap;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockPos[][] splitArray(BlockPos[] arrayToSplit, int chunkSize) {
/* 1262 */     if (chunkSize <= 0)
/*      */     {
/* 1264 */       return (BlockPos[][])null;
/*      */     }
/*      */ 
/*      */     
/* 1268 */     int rest = arrayToSplit.length % chunkSize;
/*      */     
/* 1270 */     int chunks = arrayToSplit.length / chunkSize + ((rest > 0) ? 1 : 0);
/*      */     
/* 1272 */     BlockPos[][] arrays = new BlockPos[chunks][];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1277 */     for (int i = 0; i < ((rest > 0) ? (chunks - 1) : chunks); i++)
/*      */     {
/*      */       
/* 1280 */       arrays[i] = Arrays.<BlockPos>copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
/*      */     }
/* 1282 */     if (rest > 0)
/*      */     {
/*      */       
/* 1285 */       arrays[chunks - 1] = Arrays.<BlockPos>copyOfRange(arrayToSplit, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
/*      */     }
/* 1287 */     return arrays;
/*      */   }
/*      */ 
/*      */   
/*      */   public static final int getIndexOfItemStack(Item item, IInventory inven) {
/* 1292 */     for (int i = 0; i < inven.func_70302_i_(); i++) {
/*      */       
/* 1294 */       if (inven.func_70301_a(i).func_77973_b() == item)
/*      */       {
/* 1296 */         return i;
/*      */       }
/*      */     } 
/* 1299 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean saveNBTStructure(ServerWorld world, String name, BlockPos pos, BlockPos size, List<Block> toIgnore) {
/* 1304 */     if (!world.field_72995_K) {
/*      */       Template template;
/* 1306 */       ServerWorld serverworld = world;
/* 1307 */       TemplateManager templatemanager = serverworld.func_184163_y();
/* 1308 */       ResourceLocation res = new ResourceLocation("mineminenomi", name);
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1313 */         template = templatemanager.func_200220_a(res);
/*      */       }
/* 1315 */       catch (ResourceLocationException ex) {
/*      */         
/* 1317 */         ex.printStackTrace();
/* 1318 */         return false;
/*      */       } 
/*      */       
/* 1321 */       toIgnore.add(Blocks.field_189881_dj);
/* 1322 */       toIgnore.add(Blocks.field_150357_h);
/* 1323 */       takeBlocksFromWorld(template, (World)world, pos, size, toIgnore);
/* 1324 */       template.func_186254_a((World)serverworld, pos, size, true, Blocks.field_189881_dj);
/* 1325 */       template.func_186252_a("?");
/*      */       
/*      */       try {
/* 1328 */         return templatemanager.func_195429_b(res);
/*      */       }
/* 1330 */       catch (ResourceLocationException var7) {
/*      */         
/* 1332 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1337 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos) {
/* 1343 */     PlacementSettings placement = (new PlacementSettings()).func_186214_a(Mirror.NONE).func_186220_a(Rotation.NONE).func_186222_a(true).func_186218_a((ChunkPos)null);
/* 1344 */     placement.func_215219_b()
/* 1345 */       .func_215222_a((StructureProcessor)BlockIgnoreStructureProcessor.field_215206_c)
/* 1346 */       .func_215222_a((StructureProcessor)new IntegrityProcessor(1.0F)).func_189950_a(new Random(Util.func_211177_b()));
/*      */     
/* 1348 */     return loadNBTStructure(world, name, pos, placement);
/*      */   }
/*      */   
/*      */   public static Optional<Template> getNBTStructure(ServerWorld world, String name, PlacementSettings settings) {
/* 1352 */     if (!world.field_72995_K) {
/* 1353 */       Template template; TemplateManager templatemanager = world.func_184163_y();
/* 1354 */       ResourceLocation res = new ResourceLocation("mineminenomi", name);
/*      */ 
/*      */       
/*      */       try {
/* 1358 */         template = templatemanager.func_200219_b(res);
/* 1359 */       } catch (ResourceLocationException ex) {
/* 1360 */         ex.printStackTrace();
/* 1361 */         return Optional.empty();
/*      */       } 
/*      */       
/* 1364 */       if (template == null) {
/* 1365 */         return Optional.empty();
/*      */       }
/*      */       
/* 1368 */       return Optional.of(template);
/*      */     } 
/* 1370 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos, PlacementSettings settings) {
/* 1375 */     if (!world.field_72995_K) {
/* 1376 */       Optional<Template> template = getNBTStructure(world, name, settings);
/*      */       
/* 1378 */       if (template.isPresent()) {
/* 1379 */         ((Template)template.get()).func_237144_a_((IServerWorld)world, pos, settings, new Random(Util.func_211177_b()));
/* 1380 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1384 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void takeBlocksFromWorld(Template template, World world, BlockPos startPos, BlockPos size, @Nullable List<Block> toIgnore) {
/* 1389 */     if (size.func_177958_n() >= 1 && size.func_177956_o() >= 1 && size.func_177952_p() >= 1) {
/*      */       
/* 1391 */       BlockPos blockpos = startPos.func_177971_a((Vector3i)size).func_177982_a(-1, -1, -1);
/* 1392 */       List<Template.BlockInfo> list = Lists.newArrayList();
/* 1393 */       List<Template.BlockInfo> list1 = Lists.newArrayList();
/* 1394 */       List<Template.BlockInfo> list2 = Lists.newArrayList();
/* 1395 */       BlockPos blockpos1 = new BlockPos(Math.min(startPos.func_177958_n(), blockpos.func_177958_n()), Math.min(startPos.func_177956_o(), blockpos.func_177956_o()), Math.min(startPos.func_177952_p(), blockpos.func_177952_p()));
/* 1396 */       BlockPos blockpos2 = new BlockPos(Math.max(startPos.func_177958_n(), blockpos.func_177958_n()), Math.max(startPos.func_177956_o(), blockpos.func_177956_o()), Math.max(startPos.func_177952_p(), blockpos.func_177952_p()));
/* 1397 */       ((ITemplateMixin)template).setSize(size);
/*      */       
/* 1399 */       BlockPos.func_218281_b(blockpos1, blockpos2).forEach(blockpos3 -> {
/*      */             BlockPos blockpos4 = blockpos3.func_177973_b((Vector3i)blockpos1);
/*      */ 
/*      */             
/*      */             BlockState blockstate = world.func_180495_p(blockpos3);
/*      */ 
/*      */             
/*      */             if (toIgnore != null && toIgnore.contains(blockstate.func_177230_c())) {
/*      */               world.func_175656_a(blockpos3, Blocks.field_150350_a.func_176223_P());
/*      */               
/*      */               blockstate = world.func_180495_p(blockpos3);
/*      */             } 
/*      */             
/*      */             TileEntity tileentity = world.func_175625_s(blockpos3);
/*      */             
/*      */             if (tileentity != null) {
/*      */               CompoundNBT compoundnbt = tileentity.func_189515_b(new CompoundNBT());
/*      */               
/*      */               compoundnbt.func_82580_o("x");
/*      */               
/*      */               compoundnbt.func_82580_o("y");
/*      */               
/*      */               compoundnbt.func_82580_o("z");
/*      */               
/*      */               list1.add(new Template.BlockInfo(blockpos4, blockstate, compoundnbt));
/*      */             } else if (!blockstate.func_200015_d((IBlockReader)world, blockpos3) && !blockstate.func_235785_r_((IBlockReader)world, blockpos3)) {
/*      */               list2.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT)null));
/*      */             } else {
/*      */               list.add(new Template.BlockInfo(blockpos4, blockstate, (CompoundNBT)null));
/*      */             } 
/*      */           });
/*      */       
/* 1431 */       List<Template.BlockInfo> list3 = Lists.newArrayList();
/* 1432 */       list3.addAll(list);
/* 1433 */       list3.addAll(list1);
/* 1434 */       list3.addAll(list2);
/* 1435 */       ((ITemplateMixin)template).getBlocks().clear();
/*      */       
/* 1437 */       ((ITemplateMixin)template).getEntities().clear();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isSurfaceFlat(IWorld world, int posX, int posZ, int difference) {
/* 1442 */     int offset = 16;
/*      */     
/* 1444 */     int x0 = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, posX, posZ);
/* 1445 */     int z0 = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, posX, posZ + offset);
/* 1446 */     int x1 = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, posX + offset, posZ);
/* 1447 */     int z1 = world.func_201676_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, posX + offset, posZ + offset);
/*      */     
/* 1449 */     int[] points = { x0, z0, x1, z1 };
/*      */     
/* 1451 */     for (int point : points) {
/* 1452 */       BlockPos centerOfChunk = new BlockPos(posX, 0, posZ);
/* 1453 */       BlockState midState = world.func_180495_p(centerOfChunk.func_177981_b(point - 1));
/* 1454 */       boolean isSolidGround = (!midState.func_196958_f() && midState.func_204520_s().func_206888_e() && midState.func_185904_a().func_76220_a());
/* 1455 */       if (!isSolidGround) {
/* 1456 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1460 */     int minHeight = Math.min(Math.min(x0, z0), Math.min(x1, z1));
/* 1461 */     int maxHeight = Math.max(Math.max(x0, z0), Math.max(x1, z1));
/*      */     
/* 1463 */     return (Math.abs(maxHeight - minHeight) <= difference);
/*      */   }
/*      */   
/*      */   public static boolean isSurfaceFlat(ChunkGenerator chunkGen, int chunkPosX, int chunkPosZ, int difference) {
/* 1467 */     int offset = 16;
/*      */     
/* 1469 */     int xStart = (chunkPosX << 4) + 7 - offset / 2;
/* 1470 */     int zStart = (chunkPosZ << 4) + 7 - offset / 2;
/*      */     
/* 1472 */     int x0 = chunkGen.func_222531_c(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
/* 1473 */     int z0 = chunkGen.func_222531_c(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
/* 1474 */     int x1 = chunkGen.func_222531_c(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
/* 1475 */     int z1 = chunkGen.func_222531_c(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
/*      */     
/* 1477 */     int[] points = { x0, z0, x1, z1 };
/*      */     
/* 1479 */     for (int point : points) {
/* 1480 */       BlockPos centerOfChunk = new BlockPos(xStart, 0, zStart);
/* 1481 */       IBlockReader columnOfBlocks = chunkGen.func_230348_a_(centerOfChunk.func_177958_n(), centerOfChunk.func_177952_p());
/* 1482 */       BlockState midState = columnOfBlocks.func_180495_p(centerOfChunk.func_177981_b(point));
/* 1483 */       boolean isSolidGround = (!midState.func_196958_f() && midState.func_204520_s().func_206888_e() && midState.func_185904_a().func_76220_a());
/* 1484 */       if (!isSolidGround) {
/* 1485 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1489 */     int minHeight = Math.min(Math.min(x0, z0), Math.min(x1, z1));
/* 1490 */     int maxHeight = Math.max(Math.max(x0, z0), Math.max(x1, z1));
/*      */     
/* 1492 */     return (Math.abs(maxHeight - minHeight) <= difference);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static <T> T sendGET(String sendUrl, Class resultType) throws IOException {
/* 1498 */     T result = null;
/*      */ 
/*      */     
/* 1501 */     URL url = new URL("https://pixelatedw.xyz/api/v1" + sendUrl);
/*      */ 
/*      */     
/* 1504 */     HttpURLConnection connection = (HttpURLConnection)url.openConnection();
/*      */ 
/*      */     
/* 1507 */     connection.setRequestMethod("GET");
/* 1508 */     connection.setRequestProperty("User-Agent", "mineminenomi/0.10.8-" + MCPVersion.getMCVersion() + "-" + WyPatreon.BUILD_MODE.toString().toLowerCase());
/* 1509 */     connection.setConnectTimeout(30000);
/*      */     
/* 1511 */     int responseCode = connection.getResponseCode();
/* 1512 */     if (responseCode == 200 || responseCode == 202) {
/*      */       
/* 1514 */       BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/* 1515 */       StringBuilder sb = new StringBuilder();
/*      */       
/*      */       String line;
/* 1518 */       while ((line = in.readLine()) != null)
/*      */       {
/* 1520 */         sb.append(line + "\n");
/*      */       }
/*      */       
/* 1523 */       in.close();
/*      */       
/* 1525 */       result = (T)(new Gson()).fromJson(sb.toString(), resultType);
/*      */     }
/*      */     else {
/*      */       
/* 1529 */       System.out.println("==============ERROR WHILE RETRIEVING SERVER DATA==============");
/* 1530 */       System.out.println("Response Code: " + responseCode + " - " + connection.getResponseMessage());
/* 1531 */       System.out.println("=============================================================");
/*      */     } 
/*      */     
/* 1534 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void removeAllModifiers(ModifiableAttributeInstance attr) {
/* 1539 */     Collection<AttributeModifier> collection = attr.func_225505_c_();
/* 1540 */     if (collection != null)
/*      */     {
/* 1542 */       for (AttributeModifier attributemodifier : Lists.newArrayList(collection))
/*      */       {
/* 1544 */         attr.func_111124_b(attributemodifier);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T> T[] concatAllArrays(T[] first, T[]... rest) {
/* 1551 */     int totalLength = first.length;
/* 1552 */     for (T[] array : rest) {
/* 1553 */       totalLength += array.length;
/*      */     }
/* 1555 */     T[] result = Arrays.copyOf(first, totalLength);
/* 1556 */     int offset = first.length;
/* 1557 */     for (T[] array : rest) {
/* 1558 */       System.arraycopy(array, 0, result, offset, array.length);
/* 1559 */       offset += array.length;
/*      */     } 
/* 1561 */     return result;
/*      */   }
/*      */   
/*      */   public static String stringToSnakeCase(String input) {
/* 1565 */     String cleanedInput = input.replaceAll("[^a-zA-Z0-9 ]", "");
/* 1566 */     return Arrays.<String>stream(cleanedInput.split(" "))
/* 1567 */       .map(String::toLowerCase)
/* 1568 */       .collect(Collectors.joining("_"));
/*      */   }
/*      */   
/*      */   public static ResourceLocation getResourceLocation(CompoundNBT nbt, String string) {
/* 1572 */     if (!nbt.func_150297_b(string, 8) && string.equalsIgnoreCase("faction"))
/* 1573 */       return ModValues.CIVILIAN; 
/* 1574 */     if (!nbt.func_150297_b(string, 8)) {
/* 1575 */       return ModValues.EMPTY;
/*      */     }
/* 1577 */     String id = nbt.func_74779_i(string);
/* 1578 */     if (!id.contains(":")) {
/* 1579 */       return new ResourceLocation("mineminenomi", id);
/*      */     }
/* 1581 */     return new ResourceLocation(id);
/*      */   }
/*      */   
/*      */   static {
/* 1585 */     SHUFFLER = Collectors.collectingAndThen(
/* 1586 */         Collectors.toCollection(ArrayList::new), list -> {
/*      */           Collections.shuffle(list);
/*      */           return list;
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public static <T> Collector<T, ?, List<T>> toShuffledList() {
/* 1594 */     return (Collector)SHUFFLER;
/*      */   }
/*      */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\WyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */