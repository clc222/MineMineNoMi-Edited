/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.time.LocalDateTime;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.util.text.event.ClickEvent;
/*     */ import net.minecraftforge.fml.loading.FMLPaths;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.CrewArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CrewCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  37 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("crew").requires(source -> source.func_197034_c(2));
/*     */     
/*  39 */     builder.then(Commands.func_197057_a("crew_info").executes(context -> getAllCrewsInfo(context)));
/*     */     
/*  41 */     builder.then(Commands.func_197057_a("crew_info_by_name")
/*  42 */         .then(Commands.func_197056_a("crew", (ArgumentType)CrewArgument.crew()).executes(context -> getCrewInfo(context, CrewArgument.getCrew(context, "crew")))));
/*     */ 
/*     */     
/*  45 */     builder.then(Commands.func_197057_a("create_crew")
/*  46 */         .then(Commands.func_197056_a("crew_name", (ArgumentType)StringArgumentType.string())
/*  47 */           .then(Commands.func_197056_a("player", (ArgumentType)EntityArgument.func_197096_c())
/*  48 */             .executes(context -> createCrew(context, StringArgumentType.getString(context, "crew_name"), EntityArgument.func_197089_d(context, "player"))))));
/*     */ 
/*     */ 
/*     */     
/*  52 */     builder.then(((LiteralArgumentBuilder)Commands.func_197057_a("remove_all_crews")
/*  53 */         .executes(context -> removeAllCrews(context, false)))
/*  54 */         .then(Commands.func_197056_a("--i-am-sure", (ArgumentType)BoolArgumentType.bool())
/*  55 */           .executes(context -> removeAllCrews(context, BoolArgumentType.getBool(context, "--i-am-sure")))));
/*     */ 
/*     */     
/*  58 */     builder.then(Commands.func_197057_a("remove_crew")
/*  59 */         .then(Commands.func_197056_a("crew", (ArgumentType)CrewArgument.crew()).executes(context -> removeCrew(context, CrewArgument.getCrew(context, "crew")))));
/*     */ 
/*     */     
/*  62 */     builder.then(Commands.func_197057_a("add_member_to_crew")
/*  63 */         .then(Commands.func_197056_a("crew", (ArgumentType)CrewArgument.crew())
/*  64 */           .then(Commands.func_197056_a("player", (ArgumentType)EntityArgument.func_197096_c())
/*  65 */             .executes(context -> addMemberToCrew(context, CrewArgument.getCrew(context, "crew"), EntityArgument.func_197089_d(context, "player"))))));
/*     */ 
/*     */ 
/*     */     
/*  69 */     builder.then(Commands.func_197057_a("remove_member_from_crew")
/*  70 */         .then(Commands.func_197056_a("crew", (ArgumentType)CrewArgument.crew())
/*  71 */           .then(Commands.func_197056_a("player", (ArgumentType)EntityArgument.func_197096_c())
/*  72 */             .executes(context -> removeMemberFromCrew(context, CrewArgument.getCrew(context, "crew"), EntityArgument.func_197089_d(context, "player"))))));
/*     */ 
/*     */ 
/*     */     
/*  76 */     builder.then(Commands.func_197057_a("make_captain")
/*  77 */         .then(Commands.func_197056_a("crew", (ArgumentType)CrewArgument.crew())
/*  78 */           .then(Commands.func_197056_a("player", (ArgumentType)EntityArgument.func_197096_c())
/*  79 */             .executes(context -> makeCaptain(context, CrewArgument.getCrew(context, "crew"), EntityArgument.func_197089_d(context, "player"))))));
/*     */ 
/*     */ 
/*     */     
/*  83 */     builder.then(Commands.func_197057_a("download_jolly_roger")
/*  84 */         .executes(ctx -> downloadJollyRoger(ctx, ((CommandSource)ctx.getSource()).func_197035_h())));
/*     */     
/*  86 */     if (masterBuilder != null) {
/*  87 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*     */       
/*  90 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int downloadJollyRoger(CommandContext<CommandSource> ctx, ServerPlayerEntity player) {
/*  95 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*  96 */     Crew crew = worldData.getCrewWithCaptain(player.func_110124_au());
/*     */     
/*  98 */     if (crew != null) {
/*  99 */       String crewNameSnakeCase = WyHelper.stringToSnakeCase(crew.getName());
/*     */       
/* 101 */       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH.mm.ss");
/* 102 */       LocalDateTime now = LocalDateTime.now();
/*     */       
/* 104 */       String screenshotPath = String.format("%s/screenshots", new Object[] { FMLPaths.GAMEDIR.get() });
/* 105 */       String filePath = String.format("%s/jolly_roger_%s_%s.png", new Object[] { screenshotPath, crewNameSnakeCase, dtf.format(now) });
/* 106 */       TranslationTextComponent textComp = new TranslationTextComponent(ModI18n.GUI_SAVED_JOLLY_ROGER);
/*     */ 
/*     */       
/* 109 */       StringTextComponent emptyComp = new StringTextComponent(" ");
/* 110 */       File outputFile = new File(filePath);
/*     */       
/*     */       try {
/* 113 */         outputFile.getParentFile().mkdirs();
/* 114 */         ImageIO.write(crew.getJollyRoger().getAsBufferedImage().get(), "png", outputFile);
/*     */         
/* 116 */         StringTextComponent fileComp = new StringTextComponent(String.format("%s_%s", new Object[] { crewNameSnakeCase, dtf.format(now) }));
/* 117 */         fileComp.func_230530_a_(textComp.func_150256_b().func_240715_a_(new ClickEvent(ClickEvent.Action.OPEN_FILE, outputFile.getCanonicalFile().getAbsolutePath())).setUnderlined(Boolean.valueOf(true)));
/*     */         
/* 119 */         textComp.func_230529_a_((ITextComponent)emptyComp);
/* 120 */         textComp.func_230529_a_((ITextComponent)fileComp);
/*     */         
/* 122 */         (Minecraft.func_71410_x()).field_71439_g.func_145747_a((ITextComponent)textComp, (Minecraft.func_71410_x()).field_71439_g.func_110124_au());
/*     */       }
/* 124 */       catch (IOException e) {
/* 125 */         ModMain.LOGGER.error(e.getMessage());
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     return 1;
/*     */   }
/*     */   
/*     */   private static int getAllCrewsInfo(CommandContext<CommandSource> context) {
/* 133 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 135 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 137 */     for (Crew crew : worldData.getCrews()) {
/* 138 */       builder.append("\n=================\nName: \n " + crew.getName() + "\n");
/* 139 */       builder.append("Members: \n");
/* 140 */       for (Crew.Member member : crew.getMembers()) {
/* 141 */         builder.append(" " + member
/* 142 */             .getUsername() + (member.isCaptain() ? " (Captain)" : "") + "\n");
/*     */       }
/* 144 */       builder.append("Jolly Roger: \n");
/* 145 */       builder.append(" Base: \n " + crew.getJollyRoger().getBase().getTexture());
/* 146 */       builder.append(" Backgrounds: \n");
/* 147 */       for (JollyRogerElement elem : crew.getJollyRoger().getBackgrounds()) {
/* 148 */         if (elem != null)
/*     */         {
/*     */           
/* 151 */           builder.append(" " + elem.getTexture() + "\n"); } 
/*     */       } 
/* 153 */       builder.append(" Details: \n");
/* 154 */       for (JollyRogerElement elem : crew.getJollyRoger().getDetails()) {
/* 155 */         if (elem != null)
/*     */         {
/*     */           
/* 158 */           builder.append(" " + elem.getTexture() + "\n"); } 
/*     */       } 
/* 160 */       builder.append("=================\n");
/*     */     } 
/*     */     
/* 163 */     ((CommandSource)context.getSource())
/* 164 */       .func_197030_a((ITextComponent)new TranslationTextComponent(
/* 165 */           builder.toString().isEmpty() ? ModI18n.COMMAND_CREW_NO_CREW_FOUND : builder.toString()), false);
/*     */ 
/*     */     
/* 168 */     return 1;
/*     */   }
/*     */   
/*     */   private static int getCrewInfo(CommandContext<CommandSource> context, Crew crew) {
/* 172 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 174 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 176 */     builder.append("\n=================\nName: \n " + crew.getName() + "\n");
/* 177 */     builder.append("Members: \n");
/* 178 */     for (Crew.Member member : crew.getMembers()) {
/* 179 */       builder.append(" " + member
/* 180 */           .getUsername() + (member.isCaptain() ? " (Captain)" : "") + "\n");
/*     */     }
/* 182 */     builder.append("Jolly Roger: \n");
/* 183 */     builder.append(" Base: \n " + crew.getJollyRoger().getBase().getTexture());
/* 184 */     builder.append(" Backgrounds: \n");
/* 185 */     for (JollyRogerElement elem : crew.getJollyRoger().getBackgrounds()) {
/* 186 */       if (elem != null)
/*     */       {
/*     */         
/* 189 */         builder.append(" " + elem.getTexture() + "\n"); } 
/*     */     } 
/* 191 */     builder.append(" Details: \n");
/* 192 */     for (JollyRogerElement elem : crew.getJollyRoger().getDetails()) {
/* 193 */       if (elem != null)
/*     */       {
/*     */         
/* 196 */         builder.append(" " + elem.getTexture() + "\n"); } 
/*     */     } 
/* 198 */     builder.append("=================\n");
/*     */     
/* 200 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), false);
/*     */     
/* 202 */     return 1;
/*     */   }
/*     */   
/*     */   private static int removeAllCrews(CommandContext<CommandSource> context, boolean isSure) {
/* 206 */     if (!isSure) {
/* 207 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)new StringTextComponent(ModI18n.COMMAND_CREW_DELETE_ALL_CONFIRM));
/*     */       
/* 209 */       return 0;
/*     */     } 
/*     */     
/* 212 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 214 */     for (Crew crew : worldData.getCrews()) {
/* 215 */       worldData.removeCrew(crew);
/*     */     }
/*     */     
/* 218 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_DELETED_ALL_CREWS), false);
/* 219 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int createCrew(CommandContext<CommandSource> context, String name, ServerPlayerEntity player) {
/* 224 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 226 */     if (!EntityStatsCapability.get((LivingEntity)player).isPirate()) {
/* 227 */       ((CommandSource)context.getSource())
/* 228 */         .func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_PLAYER_NO_PIRATE));
/* 229 */       return 0;
/*     */     } 
/*     */     
/* 232 */     if (worldData.getCrewWithMember(player.func_110124_au()) != null) {
/* 233 */       ((CommandSource)context.getSource())
/* 234 */         .func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_ALREADY_IN_CREW));
/* 235 */       return 0;
/*     */     } 
/*     */     
/* 238 */     if (worldData.getCrewByName(name) != null) {
/* 239 */       ((CommandSource)context.getSource())
/* 240 */         .func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_NAME_ALREADY_EXISTS));
/* 241 */       return 0;
/*     */     } 
/*     */     
/* 244 */     Crew crew = new Crew(name, (LivingEntity)player);
/*     */     
/* 246 */     worldData.addCrew(crew);
/*     */     
/* 248 */     Crew.Member member = crew.getMember(player.func_110124_au());
/* 249 */     member.setIsCaptain(true);
/*     */     
/* 251 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_CREATED_CREW), false);
/* 252 */     return 1;
/*     */   }
/*     */   
/*     */   private static int removeCrew(CommandContext<CommandSource> context, Crew crew) {
/* 256 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 258 */     worldData.removeCrew(crew);
/*     */     
/* 260 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_DELETED_CREW), false);
/* 261 */     return 1;
/*     */   }
/*     */   
/*     */   private static int addMemberToCrew(CommandContext<CommandSource> context, Crew crew, ServerPlayerEntity player) {
/* 265 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 267 */     if (!EntityStatsCapability.get((LivingEntity)player).isPirate()) {
/* 268 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_PLAYER_NO_PIRATE));
/* 269 */       return 0;
/*     */     } 
/*     */     
/* 272 */     if (worldData.getCrewWithMember(player.func_110124_au()) != null) {
/* 273 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_ALREADY_IN_CREW));
/* 274 */       return 0;
/*     */     } 
/* 276 */     worldData.addCrewMember(crew, (LivingEntity)player);
/*     */     
/* 278 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_PLAYER_ADDED_TO_CREW), false);
/* 279 */     return 1;
/*     */   }
/*     */   
/*     */   private static int removeMemberFromCrew(CommandContext<CommandSource> context, Crew crew, ServerPlayerEntity player) {
/* 283 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/* 285 */     Crew.Member member = crew.getMember(player.func_110124_au());
/* 286 */     if (member == null) {
/* 287 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_NOT_IN_CREW));
/* 288 */       return 0;
/*     */     } 
/*     */     
/* 291 */     worldData.removeCrewMember(player.field_70170_p, crew, player.func_110124_au());
/*     */     
/* 293 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_PLAYER_REMOVED_FROM_CREW), false);
/* 294 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int makeCaptain(CommandContext<CommandSource> context, Crew crew, ServerPlayerEntity player) {
/* 299 */     Crew.Member member = crew.getMember(player.func_110124_au());
/* 300 */     if (member == null) {
/* 301 */       ((CommandSource)context.getSource())
/* 302 */         .func_197021_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_NOT_IN_CREW));
/* 303 */       return 0;
/*     */     } 
/*     */     
/* 306 */     crew.getCaptain().setIsCaptain(false);
/* 307 */     member.setIsCaptain(true);
/*     */     
/* 309 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_CREW_CAPTAIN_CHANGE), false);
/*     */     
/* 311 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\CrewCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */