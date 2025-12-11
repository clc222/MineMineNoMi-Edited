/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.api.WantedPosterData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*    */ 
/*    */ public class GetWantedPosterCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 32 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("getwantedposter").requires(source -> source.func_197034_c(2));
/*    */     
/* 34 */     builder
/* 35 */       .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 36 */         .executes(context -> giveWantedPoster(context, EntityArgument.func_197089_d(context, "target"))));
/*    */     
/* 38 */     if (masterBuilder != null) {
/* 39 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 41 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static int giveWantedPoster(CommandContext<CommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
/* 46 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/* 47 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */     
/* 49 */     worldData.issueBounty(player.func_110124_au(), entityStatsProps.getBounty());
/*    */     
/* 51 */     if (WyDebug.isDebug()) {
/* 52 */       player.func_145747_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] A new bounty was issued on your name!"), Util.field_240973_b_);
/*    */     }
/* 54 */     ItemStack posterStack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER.get());
/*    */     
/* 56 */     WantedPosterData wantedPosterData = new WantedPosterData((LivingEntity)player, worldData.getBounty(player.func_110124_au()));
/* 57 */     CompoundNBT data = wantedPosterData.write();
/*    */     
/* 59 */     ServerPlayerEntity serverPlayerEntity = ((CommandSource)context.getSource()).func_197035_h();
/*    */     
/* 61 */     if (data.isEmpty()) {
/*    */       
/* 63 */       serverPlayerEntity.func_145747_a((ITextComponent)new StringTextComponent(TextFormatting.RED + "New Wanted Posters can only be generated for online players!"), Util.field_240973_b_);
/* 64 */       return 1;
/*    */     } 
/*    */     
/* 67 */     posterStack.func_196082_o().func_218657_a("WPData", (INBT)data);
/* 68 */     ((PlayerEntity)serverPlayerEntity).field_71071_by.func_70441_a(posterStack);
/*    */     
/* 70 */     WyNetwork.sendToAllAround(new SSyncEntityStatsPacket(player.func_145782_y(), entityStatsProps), (Entity)player);
/*    */     
/* 72 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\GetWantedPosterCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */