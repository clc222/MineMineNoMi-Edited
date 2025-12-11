/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.event.ClickEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.ProtectedArea;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ProtectedAreasData;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SViewProtectionPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*     */ 
/*     */ public class AbilityProtectionCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  37 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("abilityprotection").requires(Requires.hasPermission(3, WyPermissions.ABILITY_PROTECTION_COMMAND));
/*     */     
/*  39 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  40 */       .then(Commands.func_197057_a("new")
/*  41 */         .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  42 */           .then(Commands.func_197056_a("size", (ArgumentType)IntegerArgumentType.integer(1, 2048))
/*  43 */             .executes(context -> createProtection(context, IntegerArgumentType.getInteger(context, "size"), StringArgumentType.getString(context, "label")))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  49 */       .then(Commands.func_197057_a("resize")
/*  50 */         .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  51 */           .then(Commands.func_197056_a("size", (ArgumentType)IntegerArgumentType.integer(1, 2048))
/*  52 */             .executes(context -> resizeProtection(context, StringArgumentType.getString(context, "label"), IntegerArgumentType.getInteger(context, "size")))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       .then(Commands.func_197057_a("rename")
/*  59 */         .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  60 */           .then(Commands.func_197056_a("new_label", (ArgumentType)StringArgumentType.string())
/*  61 */             .executes(context -> renameProtection(context, StringArgumentType.getString(context, "label"), StringArgumentType.getString(context, "new_label")))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  67 */       .then(Commands.func_197057_a("info")
/*  68 */         .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  69 */           .executes(context -> printAreaInfo(context, StringArgumentType.getString(context, "label"))))))
/*     */ 
/*     */ 
/*     */       
/*  73 */       .then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.func_197057_a("props")
/*  74 */         .then(Commands.func_197057_a("block_destruction")
/*  75 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  76 */             .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/*  77 */               .executes(context -> changeBlockDestructionProp(context, StringArgumentType.getString(context, "label"), BoolArgumentType.getBool(context, "state")))))))
/*     */ 
/*     */         
/*  80 */         .then(Commands.func_197057_a("entity_damage")
/*  81 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  82 */             .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/*  83 */               .executes(context -> changeEntityDamageProp(context, StringArgumentType.getString(context, "label"), BoolArgumentType.getBool(context, "state")))))))
/*     */ 
/*     */         
/*  86 */         .then(Commands.func_197057_a("block_restoration")
/*  87 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  88 */             .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/*  89 */               .executes(context -> changeBlockRestorationProp(context, StringArgumentType.getString(context, "label"), BoolArgumentType.getBool(context, "state")))))))
/*     */ 
/*     */         
/*  92 */         .then(Commands.func_197057_a("abilities_use")
/*  93 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/*  94 */             .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/*  95 */               .executes(context -> changeAbilitiesUsageProp(context, StringArgumentType.getString(context, "label"), BoolArgumentType.getBool(context, "state")))))))
/*     */ 
/*     */         
/*  98 */         .then(Commands.func_197057_a("stat_loss")
/*  99 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 100 */             .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/* 101 */               .executes(context -> changeStatLossProp(context, StringArgumentType.getString(context, "label"), BoolArgumentType.getBool(context, "state")))))))
/*     */ 
/*     */         
/* 104 */         .then(Commands.func_197057_a("death")
/* 105 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 106 */             .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/* 107 */               .executes(context -> changeDeathProp(context, StringArgumentType.getString(context, "label"), BoolArgumentType.getBool(context, "state")))))))
/*     */ 
/*     */         
/* 110 */         .then(Commands.func_197057_a("unconscious_time")
/* 111 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 112 */             .then(Commands.func_197056_a("time", (ArgumentType)IntegerArgumentType.integer(0, 1200))
/* 113 */               .executes(context -> changeUnconsciousTimeProp(context, StringArgumentType.getString(context, "label"), IntegerArgumentType.getInteger(context, "time")))))))
/*     */ 
/*     */         
/* 116 */         .then(Commands.func_197057_a("restoration_interval")
/* 117 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 118 */             .then(Commands.func_197056_a("interval", (ArgumentType)IntegerArgumentType.integer(0, 1200))
/* 119 */               .executes(context -> changeRestorationIntervalProp(context, StringArgumentType.getString(context, "label"), IntegerArgumentType.getInteger(context, "interval")))))))
/*     */ 
/*     */         
/* 122 */         .then(Commands.func_197057_a("restoration_amount")
/* 123 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 124 */             .then(Commands.func_197056_a("amount", (ArgumentType)IntegerArgumentType.integer(1, 500))
/* 125 */               .executes(context -> changeRestorationAmountProp(context, StringArgumentType.getString(context, "label"), IntegerArgumentType.getInteger(context, "amount")))))))
/*     */ 
/*     */         
/* 128 */         .then(Commands.func_197057_a("restoration_distance")
/* 129 */           .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 130 */             .then(Commands.func_197056_a("amount", (ArgumentType)IntegerArgumentType.integer(0, 1000))
/* 131 */               .executes(context -> changeRestorationDistanceProp(context, StringArgumentType.getString(context, "label"), IntegerArgumentType.getInteger(context, "amount"))))))))
/*     */ 
/*     */ 
/*     */       
/* 135 */       .then(Commands.func_197057_a("view")
/* 136 */         .then(Commands.func_197056_a("state", (ArgumentType)BoolArgumentType.bool())
/* 137 */           .executes(context -> viewProtection(context, BoolArgumentType.getBool(context, "state"))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       .then(Commands.func_197057_a("list")
/* 144 */         .executes(context -> listProtections(context))))
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       .then(Commands.func_197057_a("remove")
/* 150 */         .then(Commands.func_197056_a("label", (ArgumentType)StringArgumentType.string())
/* 151 */           .executes(context -> deleteProtection(context, StringArgumentType.getString(context, "label")))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (masterBuilder != null) {
/* 159 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/* 161 */       dispatcher.register(builder);
/*     */     } 
/*     */   } private static final String RESET = "§r";
/*     */   private static int printAreaInfo(CommandContext<CommandSource> ctx, String label) throws CommandSyntaxException {
/* 165 */     ServerPlayerEntity player = ((CommandSource)ctx.getSource()).func_197035_h();
/* 166 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 167 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 169 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 170 */     StringTextComponent comp = new StringTextComponent("");
/*     */     
/* 172 */     comp.func_240702_b_("Label: " + area.getLabel() + "\n");
/* 173 */     comp.func_240702_b_("Size: " + area.getSize() + "\n");
/* 174 */     comp.func_240702_b_("Can Destroy Blocks: ");
/* 175 */     addInfoOption((IFormattableTextComponent)comp, area.canDestroyBlocks());
/* 176 */     comp.func_240702_b_("Can Restore Blocks: ");
/* 177 */     addInfoOption((IFormattableTextComponent)comp, area.canRestoreBlocks());
/* 178 */     comp.func_240702_b_("Restoration Amount: " + area.getRestoreAmount() + "\n");
/* 179 */     comp.func_240702_b_("Restoration Interval (ticks): " + area.getRestoreInterval() + "\n");
/* 180 */     comp.func_240702_b_("Restoration Distance: " + area.getRestoreDistance() + "\n");
/* 181 */     comp.func_240702_b_("Can Hurt Entities: ");
/* 182 */     addInfoOption((IFormattableTextComponent)comp, area.canHurtEntities());
/* 183 */     comp.func_240702_b_("Can Use Abilities: ");
/* 184 */     addInfoOption((IFormattableTextComponent)comp, area.canAbilitiesBeUsed());
/* 185 */     comp.func_240702_b_("Can Lose Stats: ");
/* 186 */     addInfoOption((IFormattableTextComponent)comp, area.canLoseStats());
/* 187 */     comp.func_240702_b_("Can Die: ");
/* 188 */     addInfoOption((IFormattableTextComponent)comp, area.canDie());
/* 189 */     comp.func_240702_b_("Unconscious Time (ticks): " + area.getUnconsciousTime() + "\n");
/*     */     
/* 191 */     if (WyDebug.isDebug() || !WyPatreon.isReleaseBuild()) {
/* 192 */       comp.func_240702_b_("Restoration Queue: " + area.getRestorationMap().size());
/*     */     }
/*     */     
/* 195 */     player.func_145747_a((ITextComponent)comp, Util.field_240973_b_);
/*     */     
/* 197 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static IFormattableTextComponent addInfoOption(IFormattableTextComponent comp, boolean option) {
/* 203 */     Style button1Style = Style.field_240709_b_.func_240712_a_(option ? TextFormatting.GREEN : TextFormatting.RED);
/* 204 */     Style button2Style = Style.field_240709_b_.func_240712_a_(!option ? TextFormatting.GREEN : TextFormatting.RED);
/* 205 */     comp.func_230529_a_((ITextComponent)(new StringTextComponent("[true]")).func_240703_c_(button1Style));
/* 206 */     comp.func_240702_b_(String.format("%s/", new Object[] { "§r" }));
/* 207 */     comp.func_230529_a_((ITextComponent)(new StringTextComponent("[false]")).func_240703_c_(button2Style));
/* 208 */     comp.func_240702_b_(String.format("%s\n", new Object[] { "§r" }));
/* 209 */     return comp;
/*     */   }
/*     */   
/*     */   private static int changeRestorationAmountProp(CommandContext<CommandSource> ctx, String label, int amount) {
/* 213 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 214 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 216 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 217 */     area.setRestoreAmount(amount);
/* 218 */     worldData.func_76185_a();
/*     */     
/* 220 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeRestorationDistanceProp(CommandContext<CommandSource> ctx, String label, int amount) {
/* 224 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 225 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 227 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 228 */     area.setRestoreDistance(amount);
/* 229 */     worldData.func_76185_a();
/*     */     
/* 231 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeRestorationIntervalProp(CommandContext<CommandSource> ctx, String label, int interval) {
/* 235 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 236 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 238 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 239 */     area.setRestoreInterval(interval);
/* 240 */     worldData.func_76185_a();
/*     */     
/* 242 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeAbilitiesUsageProp(CommandContext<CommandSource> ctx, String label, boolean state) {
/* 246 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 247 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 249 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 250 */     area.setAbilitiesUsage(state);
/* 251 */     worldData.func_76185_a();
/*     */     
/* 253 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeBlockDestructionProp(CommandContext<CommandSource> ctx, String label, boolean state) {
/* 257 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 258 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 260 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 261 */     area.setBlockDestruction(state);
/* 262 */     worldData.func_76185_a();
/*     */     
/* 264 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeEntityDamageProp(CommandContext<CommandSource> ctx, String label, boolean state) {
/* 268 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 269 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 271 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 272 */     area.setEntityDamage(state);
/* 273 */     worldData.func_76185_a();
/*     */     
/* 275 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeBlockRestorationProp(CommandContext<CommandSource> ctx, String label, boolean state) {
/* 279 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 280 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 282 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 283 */     area.setBlockRestoration(state);
/* 284 */     worldData.func_76185_a();
/*     */     
/* 286 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeStatLossProp(CommandContext<CommandSource> ctx, String label, boolean state) {
/* 290 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 291 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 293 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 294 */     area.setStatLoss(state);
/* 295 */     worldData.func_76185_a();
/*     */     
/* 297 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeDeathProp(CommandContext<CommandSource> ctx, String label, boolean state) {
/* 301 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 302 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 304 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 305 */     area.setDeath(state);
/* 306 */     worldData.func_76185_a();
/*     */     
/* 308 */     return 1;
/*     */   }
/*     */   
/*     */   private static int changeUnconsciousTimeProp(CommandContext<CommandSource> ctx, String label, int time) {
/* 312 */     ServerWorld world = ((CommandSource)ctx.getSource()).func_197023_e();
/* 313 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)world);
/*     */     
/* 315 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 316 */     area.setUnconsciousTime(time);
/* 317 */     worldData.func_76185_a();
/*     */     
/* 319 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int resizeProtection(CommandContext<CommandSource> context, String label, int size) throws CommandSyntaxException {
/* 324 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 325 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)serverWorld);
/*     */     
/* 327 */     worldData.resizeRestrictedArea(label, size);
/*     */     
/* 329 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int renameProtection(CommandContext<CommandSource> context, String label, String newLabel) throws CommandSyntaxException {
/* 334 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 335 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)serverWorld);
/*     */     
/* 337 */     ProtectedArea area = worldData.getProtectedArea(label);
/* 338 */     worldData.removeRestrictedArea(label);
/* 339 */     area.setLabel(newLabel);
/* 340 */     worldData.addRestrictedArea(area);
/*     */     
/* 342 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int deleteProtection(CommandContext<CommandSource> context, String label) throws CommandSyntaxException {
/* 347 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 348 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)serverWorld);
/*     */     
/* 350 */     worldData.removeRestrictedArea(label);
/*     */     
/* 352 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int listProtections(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 357 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 358 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)serverWorld);
/*     */     
/* 360 */     IFormattableTextComponent list = (new StringTextComponent("Protection Sites: \n\n")).func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN));
/*     */     
/* 362 */     int i = 1;
/* 363 */     for (ProtectedArea area : worldData.getAllRestrictions().values()) {
/*     */       
/* 365 */       int midX = area.getCenter().func_177958_n();
/* 366 */       int midY = area.getCenter().func_177956_o();
/* 367 */       int midZ = area.getCenter().func_177952_p();
/*     */       
/* 369 */       String label = "";
/* 370 */       if (!Strings.isNullOrEmpty(area.getLabel())) {
/* 371 */         label = "(" + area.getLabel() + ")";
/*     */       }
/*     */       
/* 374 */       Style style = Style.field_240709_b_.func_240715_a_(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p " + midX + " " + midY + " " + midZ));
/* 375 */       list.func_230529_a_((ITextComponent)(new StringTextComponent(i + ". " + label + " " + midX + ", " + midY + ", " + midZ + "\n")).func_230530_a_(style.func_240712_a_((i % 2 == 0) ? TextFormatting.GREEN : TextFormatting.DARK_GREEN)));
/* 376 */       i++;
/*     */     } 
/*     */     
/* 379 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)list, true);
/*     */     
/* 381 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int viewProtection(CommandContext<CommandSource> context, boolean state) throws CommandSyntaxException {
/* 386 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 387 */     ServerPlayerEntity player = ((CommandSource)context.getSource()).func_197035_h();
/* 388 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)serverWorld);
/*     */     
/* 390 */     WyNetwork.sendTo(new SViewProtectionPacket(state, worldData.getAllRestrictions()), (PlayerEntity)player);
/*     */     
/* 392 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int createProtection(CommandContext<CommandSource> context, int size, String label) throws CommandSyntaxException {
/* 397 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).func_197023_e();
/* 398 */     ProtectedAreasData worldData = ProtectedAreasData.get((World)serverWorld);
/*     */     
/* 400 */     if (worldData.getProtectedArea(label) != null) {
/* 401 */       ((CommandSource)context.getSource()).func_197021_a((ITextComponent)new StringTextComponent("Protection with this name already exists!"));
/* 402 */       return -1;
/*     */     } 
/*     */     
/* 405 */     Vector3d vec = ((CommandSource)context.getSource()).func_197036_d();
/* 406 */     BlockPos pos = new BlockPos(vec);
/*     */     
/* 408 */     worldData.addRestrictedArea(pos, size, label);
/*     */     
/* 410 */     StringBuilder builder = new StringBuilder();
/* 411 */     builder.append("Created an ability protection zone named " + label + " at " + pos.func_177958_n() + " " + pos.func_177956_o() + " " + pos.func_177952_p() + " of size " + size);
/* 412 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), true);
/*     */     
/* 414 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\AbilityProtectionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */