/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.NTEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.POIEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class EventsCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  26 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("events").requires(Requires.hasPermission(3, WyPermissions.EVENTS_COMMAND));
/*     */     
/*  28 */     ((LiteralArgumentBuilder)builder
/*  29 */       .then(Commands.func_197057_a("list")
/*  30 */         .executes(context -> listEvents(context, ((CommandSource)context.getSource()).func_197035_h()))))
/*  31 */       .then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.func_197057_a("start")
/*  32 */         .then(Commands.func_197057_a("notorious_target")
/*  33 */           .executes(context -> startNotoriousTarget(context, ((CommandSource)context.getSource()).func_197035_h()))))
/*  34 */         .then(Commands.func_197057_a("caravan")
/*  35 */           .executes(context -> startCaravan(context, ((CommandSource)context.getSource()).func_197035_h()))))
/*  36 */         .then(Commands.func_197057_a("celestial_dragon")
/*  37 */           .executes(context -> startCelestialDragon(context, ((CommandSource)context.getSource()).func_197035_h()))));
/*     */     
/*  39 */     if (masterBuilder != null) {
/*  40 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*     */       
/*  43 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int listEvents(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/*  48 */     EventsWorldData worldData = EventsWorldData.get();
/*  49 */     Set<NTEventTarget> targets = worldData.getNotoriousTargets();
/*  50 */     Set<POIEventTarget> caravans = worldData.getCaravanPOIs();
/*  51 */     Set<POIEventTarget> visits = worldData.getCelestialVisitsPOIs();
/*     */     
/*  53 */     StringBuilder sb = new StringBuilder();
/*  54 */     sb.append("§6Targets:§r " + targets.size() + "\n");
/*  55 */     for (NTEventTarget poi : targets) {
/*  56 */       sb.append("\n " + (int)(poi.getPosition()).field_72450_a + " " + (int)(poi.getPosition()).field_72448_b + " " + (int)(poi.getPosition()).field_72449_c);
/*     */     }
/*  58 */     sb.append("\n\n");
/*     */     
/*  60 */     sb.append("§6Caravans:§r " + caravans.size() + "\n");
/*  61 */     for (POIEventTarget poi : caravans) {
/*  62 */       sb.append("\n " + (int)(poi.getPosition()).field_72450_a + " " + (int)(poi.getPosition()).field_72448_b + " " + (int)(poi.getPosition()).field_72449_c);
/*     */     }
/*  64 */     sb.append("\n\n");
/*     */     
/*  66 */     sb.append("§6Visits:§r " + visits.size() + "\n");
/*  67 */     for (POIEventTarget poi : visits) {
/*  68 */       sb.append("\n " + (int)(poi.getPosition()).field_72450_a + " " + (int)(poi.getPosition()).field_72448_b + " " + (int)(poi.getPosition()).field_72449_c);
/*     */     }
/*  70 */     sb.append("\n\n");
/*     */     
/*  72 */     player.func_145747_a((ITextComponent)new StringTextComponent(sb.toString()), Util.field_240973_b_);
/*  73 */     return 1;
/*     */   }
/*     */   
/*     */   private static int startNotoriousTarget(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/*  77 */     EventsWorldData eventsWorldData = EventsWorldData.get();
/*     */     
/*  79 */     Optional<TrackedNPC> tracked = NPCWorldData.get().getRandomTrackedMob(ModValues.PIRATE);
/*  80 */     if (!tracked.isPresent()) {
/*  81 */       return 1;
/*     */     }
/*     */     
/*  84 */     Vector3d pos = player.func_213303_ch().func_72441_c(WyHelper.randomWithRange(-10, 10), 0.0D, WyHelper.randomWithRange(-10, 10));
/*     */     
/*  86 */     eventsWorldData.addNotoriousTarget(player.func_71121_q(), pos, 1200L, tracked.get());
/*  87 */     return 1;
/*     */   }
/*     */   
/*     */   private static int startCaravan(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/*  91 */     EventsWorldData eventsWorldData = EventsWorldData.get();
/*     */     
/*  93 */     Vector3d pos = player.func_213303_ch().func_72441_c(WyHelper.randomWithRange(-10, 10), -1.0D, WyHelper.randomWithRange(-10, 10));
/*     */     
/*  95 */     eventsWorldData.addCaravan(player.func_71121_q(), pos, 1200L);
/*  96 */     return 1;
/*     */   }
/*     */   
/*     */   private static int startCelestialDragon(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 100 */     EventsWorldData eventsWorldData = EventsWorldData.get();
/*     */     
/* 102 */     Vector3d pos = player.func_213303_ch().func_72441_c(WyHelper.randomWithRange(-10, 10), 0.0D, WyHelper.randomWithRange(-10, 10));
/*     */     
/* 104 */     eventsWorldData.addCelestialVisit(player.func_71121_q(), pos);
/* 105 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\EventsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */