/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.FloatArgumentType;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.HakiTypeArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*    */ 
/*    */ public class HakiExpCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 31 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("hakiexp").requires(Requires.hasPermission(2, WyPermissions.HAKIEXP_COMMAND));
/*    */     
/* 33 */     int max = CommonConfig.INSTANCE.getHakiExpLimit();
/*    */     
/* 35 */     builder
/* 36 */       .then(Commands.func_197056_a("type", (ArgumentType)HakiTypeArgument.hakiType())
/* 37 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("amount", (ArgumentType)FloatArgumentType.floatArg(-max, max))
/* 38 */           .executes(context -> alterHakiExp(context, (HakiType)context.getArgument("type", HakiType.class), FloatArgumentType.getFloat(context, "amount"), getDefaultCollection(context))))
/* 39 */           .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/* 40 */             .executes(context -> alterHakiExp(context, (HakiType)context.getArgument("type", HakiType.class), FloatArgumentType.getFloat(context, "amount"), EntityArgument.func_197090_e(context, "targets"))))));
/*    */     
/* 42 */     if (masterBuilder != null) {
/* 43 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 45 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 50 */     return Lists.newArrayList((Object[])new ServerPlayerEntity[] { ((CommandSource)context.getSource()).func_197035_h() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterHakiExp(CommandContext<CommandSource> context, HakiType hakiType, float amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 55 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 57 */       IHakiData props = HakiDataCapability.get((LivingEntity)player);
/* 58 */       boolean flag = false;
/*    */       
/* 60 */       if (hakiType == HakiType.BUSOSHOKU) {
/* 61 */         flag = props.alterBusoshokuHakiExp(amount, StatChangeSource.COMMAND);
/* 62 */       } else if (hakiType == HakiType.KENBUNSHOKU) {
/* 63 */         flag = props.alterKenbunshokuHakiExp(amount, StatChangeSource.COMMAND);
/* 64 */       } else if (hakiType == HakiType.HAOSHOKU) {
/*    */         
/* 66 */         flag |= props.alterBusoshokuHakiExp(amount, StatChangeSource.COMMAND);
/* 67 */         flag |= props.alterKenbunshokuHakiExp(amount, StatChangeSource.COMMAND);
/*    */       } 
/*    */       
/* 70 */       if (flag) {
/*    */         
/* 72 */         WyNetwork.sendTo(new SSyncHakiDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/* 73 */         ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0.0F) ? "+" : "") + amount + " haki exp for " + player.func_145748_c_().getString()), true);
/*    */       } 
/*    */     } 
/*    */     
/* 77 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\HakiExpCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */