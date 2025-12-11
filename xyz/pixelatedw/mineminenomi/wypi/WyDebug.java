/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.loading.FMLEnvironment;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*     */ 
/*     */ public class WyDebug
/*     */ {
/*  24 */   public static final HashMap<EntityType, Long> ENTITY_COUNTER = new HashMap<>();
/*  25 */   public static final Set<Long> PARTICLE_PACKETS = new HashSet<>();
/*  26 */   private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("########0.000");
/*     */   
/*  28 */   private static Logger logger = Logger.getLogger("mineminenomi");
/*     */   
/*     */   public static boolean isDebug() {
/*  31 */     return (!FMLEnvironment.production || ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getCallerClassName() {
/*  38 */     StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
/*  39 */     for (int i = 1; i < stElements.length; i++) {
/*     */       
/*  41 */       StackTraceElement ste = stElements[i];
/*  42 */       if (!ste.getClassName().equals(WyDebug.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0)
/*     */       {
/*  44 */         return ste.getClassName();
/*     */       }
/*     */     } 
/*  47 */     return null;
/*     */   }
/*     */   
/*     */   public static void printStackTrace() {
/*  51 */     if (!isDebug()) {
/*     */       return;
/*     */     }
/*  54 */     StringBuilder sb = new StringBuilder();
/*  55 */     sb.append("==========THIS IS NOT AN ERROR, ITS DEBUG TEXT============\n");
/*  56 */     StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
/*  57 */     for (int i = 1; i < stElements.length; i++) {
/*  58 */       StackTraceElement ste = stElements[i];
/*  59 */       sb.append(ste.toString() + "\n");
/*     */     } 
/*  61 */     sb.append("==========END DEBUG TEXT============\n");
/*  62 */     System.out.println(sb.toString());
/*     */   }
/*     */   
/*     */   public static void debug(Object msg) {
/*  66 */     if (isDebug()) {
/*  67 */       logger.log(Level.INFO, getCallerClassName() + ": " + String.valueOf(msg));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void info(Object message) {
/*  72 */     LogManager.getLogger(getCallerClassName()).info(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void countEntity(MobEntity entity) {
/*  77 */     EntityType type = entity.func_200600_R();
/*  78 */     if (ENTITY_COUNTER.containsKey(type)) {
/*     */       
/*  80 */       long v = ((Long)ENTITY_COUNTER.get(type)).longValue() + 1L;
/*  81 */       ENTITY_COUNTER.replace(type, Long.valueOf(v));
/*     */     }
/*     */     else {
/*     */       
/*  85 */       ENTITY_COUNTER.put(type, Long.valueOf(1L));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void trackPacket() {
/*  91 */     if (!isDebug()) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     PARTICLE_PACKETS.add(Long.valueOf(System.nanoTime()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void showTPSInChat(PlayerEntity player) {
/* 100 */     if (isDebug() && FGCommand.SHOW_TPS && !player.field_70170_p.field_72995_K) {
/* 101 */       RegistryKey<World> dim = World.field_234918_g_;
/* 102 */       long[] times = player.func_184102_h().getTickTime(dim);
/*     */       
/* 104 */       if (times == null) {
/* 105 */         times = new long[] { 0L };
/*     */       }
/*     */       
/* 108 */       long sum = 0L;
/* 109 */       for (long v : times) {
/* 110 */         sum += v;
/*     */       }
/*     */       
/* 113 */       double worldTickTime = (sum / times.length) * 1.0E-6D;
/* 114 */       double worldTPS = Math.min(1000.0D / worldTickTime, 20.0D);
/* 115 */       debug("\nTPS: " + worldTPS + "\nMSPT: " + worldTickTime + "\n\n");
/* 116 */       if (worldTPS < 19.0D) {
/* 117 */         String color = "§2";
/* 118 */         if (worldTPS >= 15.0D && worldTPS < 18.0D) {
/* 119 */           color = "§a";
/*     */         }
/* 121 */         else if (worldTPS >= 10.0D && worldTPS < 15.0D) {
/* 122 */           color = "§6";
/*     */         }
/* 124 */         else if (worldTPS >= 5.0D && worldTPS < 10.0D) {
/* 125 */           color = "§d";
/*     */         }
/* 127 */         else if (worldTPS < 5.0D) {
/* 128 */           color = "§c";
/*     */         } 
/* 130 */         player.func_145747_a((ITextComponent)new TranslationTextComponent("commands.forge.tps.summary.all", new Object[] { color + " " + TIME_FORMATTER.format(worldTickTime), TIME_FORMATTER.format(worldTPS) }), Util.field_240973_b_);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\wypi\WyDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */