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
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.QuestArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProgressionEvents;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class QuestCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  30 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("quest").requires(Requires.hasPermission(2, WyPermissions.QUEST_COMMAND));
/*     */     
/*  32 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  33 */       .then(Commands.func_197057_a("finish")
/*  34 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("quest", (ArgumentType)QuestArgument.quest())
/*  35 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  36 */             .executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.func_197089_d(context, "target")))))
/*  37 */           .executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), ((CommandSource)context.getSource()).func_197035_h())))))
/*  38 */       .then(Commands.func_197057_a("give")
/*  39 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("quest", (ArgumentType)QuestArgument.quest())
/*  40 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  41 */             .executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.func_197089_d(context, "target")))))
/*  42 */           .executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), ((CommandSource)context.getSource()).func_197035_h())))))
/*  43 */       .then(Commands.func_197057_a("unfinish")
/*  44 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("quest", (ArgumentType)QuestArgument.quest())
/*  45 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  46 */             .executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.func_197089_d(context, "target")))))
/*  47 */           .executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), ((CommandSource)context.getSource()).func_197035_h())))))
/*  48 */       .then(Commands.func_197057_a("remove")
/*  49 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("quest", (ArgumentType)QuestArgument.quest())
/*  50 */           .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  51 */             .executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.func_197089_d(context, "target")))))
/*  52 */           .executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), ((CommandSource)context.getSource()).func_197035_h()))));
/*     */     
/*  54 */     if (masterBuilder != null) {
/*  55 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*  57 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int unfinishQuest(CommandContext<CommandSource> context, QuestId quest, ServerPlayerEntity player) {
/*  62 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*  63 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  65 */     if (props.hasFinishedQuest(quest)) {
/*     */       
/*  67 */       props.removeFinishedQuest(quest);
/*  68 */       AbilityProgressionEvents.checkForStyleUnlocks((PlayerEntity)player);
/*  69 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*  70 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityData), (PlayerEntity)player);
/*     */     } else {
/*     */       
/*  73 */       player.func_145747_a((ITextComponent)new StringTextComponent("You haven't finished this quest!"), Util.field_240973_b_);
/*     */     } 
/*  75 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int finishQuest(CommandContext<CommandSource> context, QuestId questId, ServerPlayerEntity player) {
/*  82 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*     */     
/*  84 */     if (props.hasInProgressQuest(questId)) {
/*     */       
/*  86 */       Quest quest = props.getInProgressQuest(questId);
/*  87 */       if (quest.triggerCompleteEvent((PlayerEntity)player))
/*     */       {
/*  89 */         props.addFinishedQuest(quest.getCore());
/*  90 */         props.removeInProgressQuest(quest);
/*  91 */         WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */       }
/*     */     
/*  94 */     } else if (!props.hasInProgressQuest(questId)) {
/*  95 */       player.func_145747_a((ITextComponent)new StringTextComponent("You don't have this quest!"), Util.field_240973_b_);
/*     */     } 
/*  97 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int giveQuest(CommandContext<CommandSource> context, QuestId quest, ServerPlayerEntity player) {
/* 102 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*     */     
/* 104 */     if (!props.hasInProgressQuest(quest)) {
/*     */       
/* 106 */       props.addInProgressQuest(quest.createQuest());
/* 107 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/* 110 */       player.func_145747_a((ITextComponent)new StringTextComponent("You aleady have this quest!"), Util.field_240973_b_);
/*     */     } 
/* 112 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int removeQuest(CommandContext<CommandSource> context, QuestId quest, ServerPlayerEntity player) {
/* 117 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*     */     
/* 119 */     if (props.hasInProgressQuest(quest)) {
/*     */       
/* 121 */       props.removeInProgressQuest(quest);
/* 122 */       props.removeFinishedQuest(quest);
/* 123 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/* 126 */       player.func_145747_a((ITextComponent)new StringTextComponent("You don't have this quest!"), Util.field_240973_b_);
/*     */     } 
/* 128 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\QuestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */