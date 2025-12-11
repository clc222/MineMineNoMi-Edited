/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
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
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ 
/*    */ public class LoyaltyCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 28 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("loyalty").requires(Requires.hasPermission(2, WyPermissions.LOYALTY_COMMAND));
/*    */     
/* 30 */     int min = -100;
/* 31 */     int max = 100;
/*    */     
/* 33 */     builder
/* 34 */       .then(((RequiredArgumentBuilder)Commands.func_197056_a("amount", (ArgumentType)IntegerArgumentType.integer(min, max))
/* 35 */         .executes(context -> alterLoyalty(context, IntegerArgumentType.getInteger(context, "amount"), getDefaultCollection(context))))
/* 36 */         .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/* 37 */           .executes(context -> alterLoyalty(context, IntegerArgumentType.getInteger(context, "amount"), EntityArgument.func_197090_e(context, "targets")))));
/*    */     
/* 39 */     if (masterBuilder != null) {
/* 40 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 42 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 47 */     return Lists.newArrayList((Object[])new ServerPlayerEntity[] { ((CommandSource)context.getSource()).func_197035_h() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterLoyalty(CommandContext<CommandSource> context, int amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 52 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 54 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 56 */       if (entityStatsProps.alterLoyalty(amount, StatChangeSource.COMMAND)) {
/*    */         
/* 58 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), entityStatsProps), (PlayerEntity)player);
/* 59 */         ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0) ? "+" : "") + amount + " loyalty for " + player.func_145748_c_().getString()), true);
/*    */       } 
/*    */     } 
/*    */     
/* 63 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\LoyaltyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */