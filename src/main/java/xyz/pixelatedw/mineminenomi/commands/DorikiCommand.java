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
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ 
/*    */ public class DorikiCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 31 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("doriki").requires(Requires.hasPermission(2, WyPermissions.DORIKI_COMMAND));
/*    */     
/* 33 */     int min = -CommonConfig.INSTANCE.getDorikiLimit();
/* 34 */     int max = CommonConfig.INSTANCE.getDorikiLimit();
/*    */     
/* 36 */     builder
/* 37 */       .then(((RequiredArgumentBuilder)Commands.func_197056_a("amount", (ArgumentType)IntegerArgumentType.integer(min, max))
/* 38 */         .executes(context -> alterDoriki(context, IntegerArgumentType.getInteger(context, "amount"), getDefaultCollection(context))))
/* 39 */         .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197093_b())
/* 40 */           .executes(context -> alterDoriki(context, IntegerArgumentType.getInteger(context, "amount"), EntityArgument.func_197097_b(context, "targets")))));
/*    */     
/* 42 */     if (masterBuilder != null) {
/* 43 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 45 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   private static Collection<Entity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 49 */     return Lists.newArrayList((Object[])new Entity[] { (Entity)((CommandSource)context.getSource()).func_197035_h() });
/*    */   }
/*    */   
/*    */   private static int alterDoriki(CommandContext<CommandSource> context, int amount, Collection<? extends Entity> targets) throws CommandSyntaxException {
/* 53 */     for (Entity target : targets) {
/* 54 */       if (target instanceof LivingEntity) {
/* 55 */         LivingEntity livingTarget = (LivingEntity)target;
/* 56 */         IEntityStats entityStatsProps = EntityStatsCapability.get(livingTarget);
/*    */         
/* 58 */         if (entityStatsProps.alterDoriki(amount, StatChangeSource.COMMAND))
/*    */         {
/* 60 */           if (livingTarget instanceof ServerPlayerEntity) {
/* 61 */             ServerPlayerEntity playerTarget = (ServerPlayerEntity)livingTarget;
/* 62 */             WyNetwork.sendTo(new SSyncEntityStatsPacket(playerTarget.func_145782_y(), entityStatsProps), (PlayerEntity)playerTarget);
/* 63 */             ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0) ? "+" : "") + amount + " doriki for " + target.func_145748_c_().getString()), true);
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\DorikiCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */