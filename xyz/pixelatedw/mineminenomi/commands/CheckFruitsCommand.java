/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.ResourceLocationArgument;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.common.UsernameCache;
/*     */ import xyz.pixelatedw.mineminenomi.api.OneFruitEntry;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.GeneralConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ 
/*     */ public class CheckFruitsCommand {
/*     */   private static final Comparator<OneFruitEntry> COMPARE_ENTRIES_ALPHABETICALLY;
/*     */   
/*     */   static {
/*  41 */     COMPARE_ENTRIES_ALPHABETICALLY = ((o1, o2) -> {
/*     */         String fruitName1 = o1.getItemFromKey().map(()).orElse(o1.getKey().toString());
/*     */         
/*     */         String fruitName2 = o2.getItemFromKey().map(()).orElse(o2.getKey().toString());
/*     */         return fruitName1.compareToIgnoreCase(fruitName2);
/*     */       });
/*  47 */     COMPARE_FRUITS_ALPHABETICALLY = ((i1, i2) -> {
/*     */         String fruitName1 = (new TranslationTextComponent(i1.func_77658_a())).getString();
/*     */         String fruitName2 = (new TranslationTextComponent(i2.func_77658_a())).getString();
/*     */         return fruitName1.compareToIgnoreCase(fruitName2);
/*     */       });
/*     */   } private static final Comparator<Item> COMPARE_FRUITS_ALPHABETICALLY;
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  54 */     LiteralArgumentBuilder<CommandSource> builder = Commands.func_197057_a("check_fruits");
/*     */     
/*  56 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  57 */       .requires(source -> source.func_197034_c(((Boolean)GeneralConfig.PUBLIC_CHECK_FRUITS.get()).booleanValue() ? 0 : 2)))
/*  58 */       .executes(context -> checkFruitsInWorld(context)))
/*  59 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("fruit")
/*  60 */         .requires(source -> source.func_197034_c(((Boolean)GeneralConfig.PUBLIC_CHECK_FRUITS.get()).booleanValue() ? 0 : 2)))
/*  61 */         .then(Commands.func_197056_a("fruit", (ArgumentType)ResourceLocationArgument.func_197197_a())
/*  62 */           .executes(context -> checkFruitInWorld(context, ResourceLocationArgument.func_197195_e(context, "fruit"))))))
/*  63 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("list")
/*  64 */         .requires(source -> source.func_197034_c(((Boolean)GeneralConfig.PUBLIC_CHECK_FRUITS.get()).booleanValue() ? 0 : 2)))
/*  65 */         .executes(context -> checkFruitsInWorld(context))))
/*  66 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("history")
/*  67 */         .requires(source -> source.func_197034_c(2)))
/*  68 */         .then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Commands.func_197056_a("fruit", (ArgumentType)ResourceLocationArgument.func_197197_a())
/*  69 */           .then(Commands.func_197057_a("export")
/*  70 */             .executes(context -> exportFruitHistory(context, ResourceLocationArgument.func_197195_e(context, "fruit")))))
/*  71 */           .then(Commands.func_197056_a("page", (ArgumentType)IntegerArgumentType.integer())
/*  72 */             .executes(context -> fruitHistory(context, ResourceLocationArgument.func_197195_e(context, "fruit"), IntegerArgumentType.getInteger(context, "page")))))
/*  73 */           .executes(context -> fruitHistory(context, ResourceLocationArgument.func_197195_e(context, "fruit"), -1))));
/*     */     
/*  75 */     if (masterBuilder != null) {
/*  76 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*     */       
/*  79 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int checkFruitInWorld(CommandContext<CommandSource> context, ResourceLocation fruit) {
/*  84 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*  85 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)ModI18n.COMMAND_CHECK_FRUIT_OFPW_ONLY);
/*  86 */       return 0;
/*     */     } 
/*     */     
/*  89 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/*  90 */     OFPWWorldData worldData = OFPWWorldData.get();
/*  91 */     OneFruitEntry entry = worldData.getOneFruitEntry(fruit);
/*     */     
/*  93 */     if (entry == null) {
/*  94 */       return 0;
/*     */     }
/*     */     
/*  97 */     Item fruitItem = (Item)ForgeRegistries.ITEMS.getValue(entry.getKey());
/*  98 */     if (fruitItem == null) {
/*  99 */       return 0;
/*     */     }
/*     */     
/* 102 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 104 */     String fruitName = (new TranslationTextComponent(fruitItem.func_77658_a())).getString();
/* 105 */     String status = getStatusColor(entry);
/*     */     
/* 107 */     boolean hasPermission = ((CommandSource)context.getSource()).func_197034_c(2);
/*     */     
/* 109 */     builder.append("===============================================\n");
/* 110 */     if (hasPermission) {
/* 111 */       builder.append("§l§6Fruit Name | Player Name | Status §r\n");
/* 112 */       String playerName = getOwnerName(entry, (IWorld)serverWorld);
/* 113 */       builder.append(fruitName + " | " + playerName + " | " + status + "\n");
/*     */     } else {
/*     */       
/* 116 */       builder.append("§l§6Fruit Name | Status §r\n");
/* 117 */       builder.append(fruitName + " | " + status + "\n");
/*     */     } 
/* 119 */     builder.append("===============================================");
/*     */     
/* 121 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), false);
/*     */     
/* 123 */     return 0;
/*     */   }
/*     */   
/*     */   private static int exportFruitHistory(CommandContext<CommandSource> context, ResourceLocation fruit) {
/* 127 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 128 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)ModI18n.COMMAND_CHECK_FRUIT_OFPW_ONLY);
/* 129 */       return 0;
/*     */     } 
/*     */     
/* 132 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 133 */     OFPWWorldData worldData = OFPWWorldData.get();
/*     */     
/* 135 */     Optional<OneFruitEntry> oneFruit = worldData.getOneFruitEntries().stream().filter(entry -> entry.getKey().equals(fruit)).findFirst();
/*     */     
/* 137 */     if (oneFruit.isPresent()) {
/*     */       
/* 139 */       StringBuilder builder = new StringBuilder();
/*     */       
/* 141 */       List<OneFruitEntry.HistoryEntry> history = ((OneFruitEntry)oneFruit.get()).getHistory();
/*     */       
/* 143 */       for (int i = 0; i < history.size(); i++) {
/*     */         
/* 145 */         OneFruitEntry.HistoryEntry current = history.get(i);
/* 146 */         OneFruitEntry.HistoryEntry previous = history.get(MathHelper.func_76125_a(i - 1, 0, history.size()));
/*     */         
/* 148 */         String status = "";
/* 149 */         String owner = getOwnerName(current, (IWorld)serverWorld);
/*     */         
/* 151 */         if (current == previous) {
/* 152 */           status = current.getStatus().name();
/*     */         } else {
/*     */           
/* 155 */           status = previous.getStatus().name() + " → " + current.getStatus().name();
/*     */         } 
/*     */         
/* 158 */         builder.append(current.getDate().toString() + ":\n");
/* 159 */         builder.append(status + " by " + owner + (!Strings.isNullOrEmpty(current.getStatusMessage()) ? (" due to: " + current.getStatusMessage()) : "") + "\n\n");
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 164 */         String path = System.getProperty("user.dir");
/* 165 */         File exportFolder = new File(path, "onefruit-exports");
/* 166 */         if (!exportFolder.exists()) {
/* 167 */           exportFolder.mkdir();
/*     */         }
/* 169 */         File exportFile = new File(exportFolder, fruit + ".txt");
/* 170 */         if (exportFile.exists()) {
/* 171 */           exportFile.delete();
/*     */         }
/* 173 */         exportFile.createNewFile();
/*     */         
/* 175 */         PrintWriter out = new PrintWriter(new FileWriter(exportFile, true));
/* 176 */         out.append(builder.toString());
/* 177 */         out.close();
/*     */         
/* 179 */         ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent("Exported file as " + fruit + ".txt"), false);
/*     */       }
/* 181 */       catch (Exception e) {
/*     */         
/* 183 */         e.printStackTrace();
/* 184 */         ((CommandSource)context.getSource()).func_197030_a((ITextComponent)ModI18n.COMMAND_CHECK_FRUIT_ERROR_EXPORTING, false);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 189 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int fruitHistory(CommandContext<CommandSource> context, ResourceLocation fruit, int page) {
/* 194 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       
/* 196 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)ModI18n.COMMAND_CHECK_FRUIT_OFPW_ONLY);
/* 197 */       return 0;
/*     */     } 
/*     */     
/* 200 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 201 */     OFPWWorldData worldData = OFPWWorldData.get();
/*     */     
/* 203 */     Optional<OneFruitEntry> oneFruit = worldData.getOneFruitEntries().stream().filter(entry -> entry.getKey().equals(fruit)).findFirst();
/*     */     
/* 205 */     if (oneFruit.isPresent()) {
/*     */       
/* 207 */       StringBuilder builder = new StringBuilder();
/* 208 */       int elements = 100;
/* 209 */       List<OneFruitEntry.HistoryEntry> history = ((OneFruitEntry)oneFruit.get()).getHistory();
/* 210 */       if (page >= 0) {
/* 211 */         history = (List<OneFruitEntry.HistoryEntry>)history.stream().skip((elements * page)).limit(elements).collect(Collectors.toList());
/*     */       }
/* 213 */       builder.append("===============================================\n");
/* 214 */       builder.append("§l§6" + ((OneFruitEntry)oneFruit.get()).getKey() + "'s History - Page " + (page + 1) + ":§r \n");
/* 215 */       for (int i = 0; i < history.size(); i++) {
/*     */         
/* 217 */         OneFruitEntry.HistoryEntry current = history.get(i);
/* 218 */         OneFruitEntry.HistoryEntry previous = history.get(MathHelper.func_76125_a(i - 1, 0, history.size()));
/*     */         
/* 220 */         String status = "";
/* 221 */         String owner = getOwnerName(current, (IWorld)serverWorld);
/*     */         
/* 223 */         if (current == previous) {
/* 224 */           status = getStatusColor(current);
/*     */         } else {
/*     */           
/* 227 */           status = getStatusColor(previous) + " → " + getStatusColor(current);
/*     */         } 
/*     */         
/* 230 */         builder.append(current.getDate().toString() + ":\n");
/* 231 */         builder.append(status + " by " + owner + (!Strings.isNullOrEmpty(current.getStatusMessage()) ? (" due to: " + current.getStatusMessage()) : "") + "\n");
/*     */       } 
/*     */       
/* 234 */       builder.append("===============================================");
/*     */       
/* 236 */       ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), false);
/*     */     } 
/*     */     
/* 239 */     return 1;
/*     */   }
/*     */   
/*     */   private static int checkFruitsInWorld(CommandContext<CommandSource> context) {
/* 243 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/* 244 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)ModI18n.COMMAND_CHECK_FRUIT_OFPW_ONLY);
/* 245 */       return 0;
/*     */     } 
/*     */     
/* 248 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 249 */     OFPWWorldData worldData = OFPWWorldData.get();
/*     */     
/* 251 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 253 */     List<ResourceLocation> foundKeys = (List<ResourceLocation>)worldData.getOneFruitEntries().stream().map(e -> e.getKey()).collect(Collectors.toList());
/*     */     
/* 255 */     List<OneFruitEntry> existingFruits = (List<OneFruitEntry>)worldData.getOneFruitEntries().stream().sorted(COMPARE_ENTRIES_ALPHABETICALLY).collect(Collectors.toList());
/*     */     
/* 257 */     builder.append("===============================================\n");
/* 258 */     boolean hasPermission = ((CommandSource)context.getSource()).func_197034_c(2);
/* 259 */     if (hasPermission) {
/* 260 */       builder.append("§l§6Fruit Name | Player Name | Status §r\n");
/*     */     } else {
/*     */       
/* 263 */       builder.append("§l§6Fruit Name | Status §r\n");
/*     */     } 
/*     */     
/* 266 */     for (OneFruitEntry entry : existingFruits) {
/* 267 */       String fruitName = entry.getItemFromKey().map(item -> (new TranslationTextComponent(item.func_77658_a())).getString()).orElse(entry.getKey().toString());
/* 268 */       String status = getStatusColor(entry);
/* 269 */       if (hasPermission) {
/* 270 */         String playerName = getOwnerName(entry, (IWorld)serverWorld);
/* 271 */         builder.append(fruitName + " | " + playerName + " | " + status + "\n");
/*     */         continue;
/*     */       } 
/* 274 */       builder.append(fruitName + " | " + status + "\n");
/*     */     } 
/*     */ 
/*     */     
/* 278 */     builder.append("\n");
/*     */     
/* 280 */     List<AkumaNoMiItem> registeredFruits = (List<AkumaNoMiItem>)ModValues.DEVIL_FRUITS.stream().sorted(COMPARE_FRUITS_ALPHABETICALLY).collect(Collectors.toList());
/*     */     
/* 282 */     for (AkumaNoMiItem fruit : registeredFruits) {
/* 283 */       ResourceLocation key = fruit.getRegistryName();
/* 284 */       if (!foundKeys.contains(key)) {
/* 285 */         builder.append((new TranslationTextComponent(fruit.func_77658_a())).getString() + " | §5NEVER_FOUND§r\n");
/*     */       }
/*     */     } 
/*     */     
/* 289 */     builder.append("===============================================");
/*     */     
/* 291 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), false);
/*     */     
/* 293 */     return 1;
/*     */   }
/*     */   
/*     */   private static String getOwnerName(OneFruitEntry entry, IWorld world) {
/* 297 */     String playerName = "System";
/* 298 */     if (entry.getOwner().isPresent()) {
/* 299 */       if (world.func_217371_b(entry.getOwner().get()) != null) {
/* 300 */         playerName = world.func_217371_b(entry.getOwner().get()).func_145748_c_().getString();
/*     */       }
/* 302 */       else if (UsernameCache.getLastKnownUsername(entry.getOwner().get()) != null) {
/* 303 */         playerName = UsernameCache.getLastKnownUsername(entry.getOwner().get());
/*     */       } else {
/*     */         
/* 306 */         playerName = "Unknown Player Name";
/*     */       } 
/*     */     }
/* 309 */     return playerName;
/*     */   }
/*     */   
/*     */   private static String getOwnerName(OneFruitEntry.HistoryEntry entry, IWorld world) {
/* 313 */     String playerName = "System";
/* 314 */     if (entry.getOwner().isPresent()) {
/* 315 */       if (world.func_217371_b(entry.getOwner().get()) != null) {
/* 316 */         playerName = world.func_217371_b(entry.getOwner().get()).func_145748_c_().getString();
/*     */       }
/* 318 */       else if (UsernameCache.getLastKnownUsername(entry.getOwner().get()) != null) {
/* 319 */         playerName = UsernameCache.getLastKnownUsername(entry.getOwner().get());
/*     */       } else {
/*     */         
/* 322 */         playerName = "Unknown Player Name";
/*     */       } 
/*     */     }
/* 325 */     return playerName;
/*     */   }
/*     */   
/*     */   private static String getStatusColor(OneFruitEntry entry) {
/* 329 */     String status = entry.getStatus().name();
/* 330 */     if (entry.getStatus() == OneFruitEntry.Status.LOST) {
/* 331 */       status = "§c" + entry.getStatus().name() + "§r";
/*     */     }
/* 333 */     else if (entry.getStatus() == OneFruitEntry.Status.DROPPED) {
/* 334 */       status = "§d" + entry.getStatus().name() + "§r";
/*     */     }
/* 336 */     else if (entry.getStatus() == OneFruitEntry.Status.IN_USE || entry.getStatus() == OneFruitEntry.Status.INVENTORY) {
/* 337 */       status = "§a" + entry.getStatus().name() + "§r";
/*     */     } 
/* 339 */     return status;
/*     */   }
/*     */   
/*     */   private static String getStatusColor(OneFruitEntry.HistoryEntry entry) {
/* 343 */     String status = entry.getStatus().name();
/* 344 */     if (entry.getStatus() == OneFruitEntry.Status.LOST) {
/* 345 */       status = "§c" + entry.getStatus().name() + "§r";
/*     */     }
/* 347 */     else if (entry.getStatus() == OneFruitEntry.Status.DROPPED) {
/* 348 */       status = "§d" + entry.getStatus().name() + "§r";
/*     */     }
/* 350 */     else if (entry.getStatus() == OneFruitEntry.Status.IN_USE || entry.getStatus() == OneFruitEntry.Status.INVENTORY) {
/* 351 */       status = "§a" + entry.getStatus().name() + "§r";
/*     */     } 
/* 353 */     return status;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\CheckFruitsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */