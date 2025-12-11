/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.FactionId;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.StyleId;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.FactionArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.FightingStyleArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.RaceArgument;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class ChangeCharacterCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 29 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("change_character").requires(Requires.hasPermission(3, WyPermissions.CHANGE_CHARACTER_COMMAND));
/*    */     
/* 31 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/* 32 */       .then(Commands.func_197057_a("faction")
/* 33 */         .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 34 */           .then(Commands.func_197056_a("value", (ArgumentType)FactionArgument.faction())
/* 35 */             .executes(context -> changeFaction(context, EntityArgument.func_197089_d(context, "target"), FactionArgument.getFaction(context, "value")))))))
/* 36 */       .then(Commands.func_197057_a("race")
/* 37 */         .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 38 */           .then(Commands.func_197056_a("value", (ArgumentType)RaceArgument.race())
/* 39 */             .executes(context -> changeRace(context, EntityArgument.func_197089_d(context, "target"), RaceArgument.getRace(context, "value")))))))
/* 40 */       .then(Commands.func_197057_a("style")
/* 41 */         .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 42 */           .then(Commands.func_197056_a("value", (ArgumentType)FightingStyleArgument.fightingStyle())
/* 43 */             .executes(context -> changeStyle(context, EntityArgument.func_197089_d(context, "target"), FightingStyleArgument.getFightingStyle(context, "value"))))));
/*    */     
/* 45 */     if (masterBuilder != null) {
/* 46 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/*    */       
/* 49 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int changeFaction(CommandContext<CommandSource> context, ServerPlayerEntity target, FactionId faction) throws CommandSyntaxException {
/* 54 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)target);
/*    */     
/* 56 */     entityStatsProps.setFaction(faction.getRegistryName());
/* 57 */     ModAdvancements.SELECT_FACTION.trigger(target, faction.getRegistryName());
/* 58 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), entityStatsProps), (PlayerEntity)target);
/* 59 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Changed " + target.func_145748_c_().getString() + "'s faction to " + faction.getRegistryName()), true);
/*    */     
/* 61 */     return 1;
/*    */   }
/*    */   
/*    */   private static int changeRace(CommandContext<CommandSource> context, ServerPlayerEntity target, RaceId race) throws CommandSyntaxException {
/* 65 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)target);
/*    */     
/* 67 */     entityStatsProps.setRace(race.getRegistryName());
/* 68 */     ModAdvancements.SELECT_RACE.trigger(target, race.getRegistryName());
/* 69 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), entityStatsProps), (PlayerEntity)target);
/* 70 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Changed " + target.func_145748_c_().getString() + "'s race to " + race.getRegistryName()), true);
/*    */     
/* 72 */     return 1;
/*    */   }
/*    */   
/*    */   private static int changeStyle(CommandContext<CommandSource> context, ServerPlayerEntity target, StyleId style) throws CommandSyntaxException {
/* 76 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)target);
/*    */     
/* 78 */     entityStatsProps.setFightingStyle(style.getRegistryName());
/* 79 */     ModAdvancements.SELECT_STYLE.trigger(target, style.getRegistryName());
/* 80 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), entityStatsProps), (PlayerEntity)target);
/* 81 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Changed " + target.func_145748_c_().getString() + "'s fighting style to " + style.getRegistryName()), true);
/*    */     
/* 83 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\ChangeCharacterCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */