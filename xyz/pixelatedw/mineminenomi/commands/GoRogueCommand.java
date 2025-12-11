/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ 
/*    */ public class GoRogueCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 21 */     LiteralArgumentBuilder<CommandSource> builder = Commands.func_197057_a("gorogue");
/*    */     
/* 23 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/* 24 */       .then(((RequiredArgumentBuilder)Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 25 */         .requires(source -> source.func_197034_c(2)))
/* 26 */         .executes(ctx -> goRogue(ctx, EntityArgument.func_197089_d(ctx, "target")))))
/* 27 */       .requires(source -> source.func_197034_c(0)))
/* 28 */       .executes(ctx -> goRogue(ctx, ((CommandSource)ctx.getSource()).func_197035_h()));
/*    */     
/* 30 */     if (masterBuilder != null) {
/* 31 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 33 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int goRogue(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 38 */     boolean rogue = EntityStatsCapability.get((LivingEntity)player).isRogue();
/* 39 */     EntityStatsCapability.get((LivingEntity)player).setRogue(!rogue);
/* 40 */     StringTextComponent message = new StringTextComponent("§2Rogue mode " + (rogue ? "disabled" : "enabled") + " for " + player.func_145748_c_().getString() + "§r");
/* 41 */     player.func_145747_a((ITextComponent)message, Util.field_240973_b_);
/* 42 */     if (!((CommandSource)context.getSource()).func_197022_f().equals(player)) {
/* 43 */       ((CommandSource)context.getSource()).func_197030_a((ITextComponent)message, false);
/*    */     }
/* 45 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\GoRogueCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */