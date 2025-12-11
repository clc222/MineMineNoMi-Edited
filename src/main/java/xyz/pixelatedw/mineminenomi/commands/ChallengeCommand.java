/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.ChallengeArgument;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ChallengeCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  28 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("challenge").requires(Requires.hasPermission(3, WyPermissions.CHALLENGE_COMMAND));
/*     */     
/*  30 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  31 */       .then(Commands.func_197057_a("start")
/*  32 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("challenge", (ArgumentType)ChallengeArgument.challenge())
/*  33 */           .executes(context -> startChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), ((CommandSource)context.getSource()).func_197035_h())))
/*  34 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  35 */             .executes(context -> startChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.func_197089_d(context, "target")))))))
/*  36 */       .then(Commands.func_197057_a("finish")
/*  37 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("challenge", (ArgumentType)ChallengeArgument.challenge())
/*  38 */           .executes(context -> finishChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), ((CommandSource)context.getSource()).func_197035_h())))
/*  39 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  40 */             .executes(context -> finishChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.func_197089_d(context, "target")))))))
/*  41 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("give")
/*  42 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("challenge", (ArgumentType)ChallengeArgument.challenge())
/*  43 */           .executes(context -> giveChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), ((CommandSource)context.getSource()).func_197035_h())))
/*  44 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  45 */             .executes(context -> giveChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.func_197089_d(context, "target"))))))
/*  46 */         .then(Commands.func_197057_a("ALL")
/*  47 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197094_d())
/*  48 */             .executes(context -> giveAllChallenges(context, EntityArgument.func_197089_d(context, "target")))))))
/*  49 */       .then(Commands.func_197057_a("unfinish")
/*  50 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("challenge", (ArgumentType)ChallengeArgument.challenge())
/*  51 */           .executes(context -> unfinishChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), ((CommandSource)context.getSource()).func_197035_h())))
/*  52 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  53 */             .executes(context -> unfinishChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.func_197089_d(context, "target")))))))
/*  54 */       .then(Commands.func_197057_a("remove")
/*  55 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("challenge", (ArgumentType)ChallengeArgument.challenge())
/*  56 */           .executes(context -> removeChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), ((CommandSource)context.getSource()).func_197035_h())))
/*  57 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  58 */             .executes(context -> removeChallenge(context, ChallengeArgument.getChallenge(context, "challenge"), EntityArgument.func_197089_d(context, "target"))))));
/*     */     
/*  60 */     if (masterBuilder != null) {
/*  61 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*     */       
/*  64 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int startChallenge(CommandContext<CommandSource> context, ChallengeCore<?> core, ServerPlayerEntity player) {
/*  69 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/*  71 */     Challenge challenge = props.getChallenge(core);
/*     */     
/*  73 */     if (challenge != null) {
/*  74 */       ChallengesWorldData.get().startChallenge(player, null, core, false);
/*     */     } else {
/*     */       
/*  77 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_NOT_UNLOCKED), Util.field_240973_b_);
/*     */     } 
/*     */     
/*  80 */     return 1;
/*     */   }
/*     */   
/*     */   private static int unfinishChallenge(CommandContext<CommandSource> context, ChallengeCore core, ServerPlayerEntity player) {
/*  84 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/*  86 */     Challenge challenge = props.getChallenge(core);
/*  87 */     if (challenge != null) {
/*  88 */       challenge.setComplete((PlayerEntity)player, false);
/*  89 */       challenge.resetBestTime();
/*  90 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/*  93 */       player.func_145747_a((ITextComponent)new StringTextComponent("You haven't finished this challenge!"), Util.field_240973_b_);
/*     */     } 
/*     */     
/*  96 */     return 1;
/*     */   }
/*     */   
/*     */   private static int finishChallenge(CommandContext<CommandSource> context, ChallengeCore core, ServerPlayerEntity player) {
/* 100 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/* 102 */     Challenge challenge = props.getChallenge(core);
/* 103 */     if (challenge != null) {
/* 104 */       challenge.setComplete((PlayerEntity)player, true);
/* 105 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/* 108 */       player.func_145747_a((ITextComponent)new StringTextComponent("You don't have this challenge!"), Util.field_240973_b_);
/*     */     } 
/*     */     
/* 111 */     return 1;
/*     */   }
/*     */   
/*     */   private static int giveChallenge(CommandContext<CommandSource> context, ChallengeCore core, ServerPlayerEntity player) {
/* 115 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/* 117 */     if (!props.hasChallenge(core)) {
/* 118 */       props.addChallenge(core);
/* 119 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/* 122 */       player.func_145747_a((ITextComponent)new StringTextComponent("You aleady have this challenge!"), Util.field_240973_b_);
/*     */     } 
/*     */     
/* 125 */     return 1;
/*     */   }
/*     */   
/*     */   private static int giveAllChallenges(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 129 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/* 131 */     for (ChallengeCore<?> core : (Iterable<ChallengeCore<?>>)ModRegistries.CHALLENGES.getValues()) {
/* 132 */       if (!props.hasChallenge(core)) {
/* 133 */         props.addChallenge(core);
/* 134 */         WyNetwork.sendTo(new SSyncChallengeDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     return 1;
/*     */   }
/*     */   
/*     */   private static int removeChallenge(CommandContext<CommandSource> context, ChallengeCore core, ServerPlayerEntity player) {
/* 142 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/* 144 */     if (props.hasChallenge(core)) {
/* 145 */       props.removeChallenge(core);
/* 146 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/* 149 */       player.func_145747_a((ITextComponent)new StringTextComponent("You don't have this challenge!"), Util.field_240973_b_);
/*     */     } 
/*     */     
/* 152 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\ChallengeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */