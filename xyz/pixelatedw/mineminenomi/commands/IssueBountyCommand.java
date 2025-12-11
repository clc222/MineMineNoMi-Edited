/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.IssueBountyEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*    */ 
/*    */ public class IssueBountyCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/* 30 */     LiteralArgumentBuilder<CommandSource> builder = Commands.func_197057_a("issuebounty");
/*    */     
/* 32 */     builder
/* 33 */       .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/* 34 */         .then(Commands.func_197056_a("bounty", (ArgumentType)LongArgumentType.longArg())
/* 35 */           .executes(context -> issueBounty(context, EntityArgument.func_197089_d(context, "target"), LongArgumentType.getLong(context, "bounty")))));
/*    */     
/* 37 */     if (masterBuilder != null) {
/* 38 */       masterBuilder.then((ArgumentBuilder)builder);
/*    */     } else {
/* 40 */       dispatcher.register(builder);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static int issueBounty(CommandContext<CommandSource> context, ServerPlayerEntity player, long bounty) {
/*    */     try {
/* 47 */       ServerPlayerEntity serverPlayerEntity = ((CommandSource)context.getSource()).func_197035_h();
/*    */       
/* 49 */       IEntityStats propz = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/* 50 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 52 */       IssueBountyEvent event = new IssueBountyEvent((PlayerEntity)player, bounty, (PlayerEntity)serverPlayerEntity);
/* 53 */       MinecraftForge.EVENT_BUS.post((Event)event);
/*    */       
/* 55 */       boolean hasPermission = WyPermissions.hasPermission((PlayerEntity)serverPlayerEntity, WyPermissions.ISSUE_BOUNTY_COMMAND);
/* 56 */       if (hasPermission || (propz.isMarine() && ((event.getResult() == Event.Result.DEFAULT && propz.hasMarineRank(FactionHelper.MarineRank.CAPTAIN)) || event.getResult() == Event.Result.ALLOW))) {
/*    */         
/* 58 */         if (props.isPirate() || props.isBandit() || props.isRevolutionary())
/*    */         {
/* 60 */           if (bounty <= props.getBounty()) {
/* 61 */             serverPlayerEntity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_ONLY_UP), Util.field_240973_b_);
/* 62 */             return 1;
/*    */           } 
/* 64 */           long costOfAdding = bounty - props.getBounty();
/* 65 */           if (propz.getBelly() > costOfAdding) {
/* 66 */             propz.setBelly(propz.getBelly() - costOfAdding);
/* 67 */             props.setBounty(bounty);
/*    */           } else {
/* 69 */             serverPlayerEntity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_NOT_ENOUGH_BELLY), Util.field_240973_b_);
/* 70 */             return 1;
/*    */           
/*    */           }
/*    */         
/*    */         }
/*    */         else
/*    */         {
/* 77 */           serverPlayerEntity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_TARGET_REQUIREMENTS), Util.field_240973_b_);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 82 */         serverPlayerEntity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_REQUIREMENTS), Util.field_240973_b_);
/*    */       }
/*    */     
/* 85 */     } catch (CommandSyntaxException e) {
/*    */       
/* 87 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 90 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\IssueBountyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */