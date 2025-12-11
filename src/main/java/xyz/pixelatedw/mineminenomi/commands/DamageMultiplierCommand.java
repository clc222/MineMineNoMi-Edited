/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class DamageMultiplierCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 23 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("damagem").requires(source -> source.func_197034_c(2));
/*    */     
/* 25 */     builder
/* 26 */       .then(Commands.func_197056_a("multiplier", (ArgumentType)DoubleArgumentType.doubleArg(0.0D, 10.0D))
/* 27 */         .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 28 */           .executes(context -> applyMultiplier(context, DoubleArgumentType.getDouble(context, "multiplier"), EntityArgument.func_197089_d(context, "target")))));
/*    */     
/* 30 */     if (masterBuilder != null) {
/* 31 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 33 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int applyMultiplier(CommandContext<CommandSource> context, double multiplier, ServerPlayerEntity player) {
/* 38 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 40 */     props.setDamageMultiplier(multiplier);
/*    */     
/* 42 */     ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Damage Multiplier set to " + props.getDamageMultiplier() + " for " + player.func_145748_c_().getString()), true);
/*    */     
/* 44 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\DamageMultiplierCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */