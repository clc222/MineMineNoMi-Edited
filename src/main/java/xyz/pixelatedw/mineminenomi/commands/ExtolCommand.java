/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ 
/*    */ public class ExtolCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 29 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("extol").requires(Requires.hasPermission(2, WyPermissions.EXTOL_COMMAND));
/*    */     
/* 31 */     builder
/* 32 */       .then(((RequiredArgumentBuilder)Commands.func_197056_a("amount", (ArgumentType)LongArgumentType.longArg(-999999999L, 999999999L))
/* 33 */         .executes(context -> alterExtol(context, LongArgumentType.getLong(context, "amount"), getDefaultCollection(context))))
/* 34 */         .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/* 35 */           .executes(context -> alterExtol(context, LongArgumentType.getLong(context, "amount"), EntityArgument.func_197090_e(context, "targets")))));
/*    */     
/* 37 */     if (masterBuilder != null) {
/* 38 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 40 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 45 */     return Lists.newArrayList((Object[])new ServerPlayerEntity[] { ((CommandSource)context.getSource()).func_197035_h() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterExtol(CommandContext<CommandSource> context, long amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 50 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 52 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 54 */       if (entityStatsProps.alterExtol(amount, StatChangeSource.COMMAND)) {
/*    */         
/* 56 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), entityStatsProps), (PlayerEntity)player);
/* 57 */         ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0L) ? "+" : "") + amount + " extol for " + player.func_145748_c_().getString()), true);
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\ExtolCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */