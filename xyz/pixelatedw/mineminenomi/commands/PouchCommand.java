/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.LongArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.CurrencyTypeArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class PouchCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  33 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("pouch").requires(source -> source.func_197034_c(0));
/*     */     
/*  35 */     builder
/*  36 */       .then(Commands.func_197057_a("ALL")
/*  37 */         .then(Commands.func_197056_a("currency", (ArgumentType)CurrencyTypeArgument.currencyType())
/*  38 */           .executes(ctx -> createPouch(ctx, (Currency)ctx.getArgument("currency", Currency.class), 999999999L))));
/*     */     
/*  40 */     builder
/*  41 */       .then(Commands.func_197056_a("amount", (ArgumentType)LongArgumentType.longArg(1L, 999999999L))
/*  42 */         .then(Commands.func_197056_a("currency", (ArgumentType)CurrencyTypeArgument.currencyType())
/*  43 */           .executes(ctx -> createPouch(ctx, (Currency)ctx.getArgument("currency", Currency.class), LongArgumentType.getLong(ctx, "amount")))));
/*     */     
/*  45 */     builder
/*  46 */       .then(Commands.func_197057_a("take")
/*  47 */         .then(Commands.func_197056_a("amount", (ArgumentType)LongArgumentType.longArg(1L, 999999999L))
/*  48 */           .executes(ctx -> takeFromPouch(ctx, ((CommandSource)ctx.getSource()).func_197035_h(), LongArgumentType.getLong(ctx, "amount")))));
/*     */     
/*  50 */     if (masterBuilder != null) {
/*  51 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*  53 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   private static int takeFromPouch(CommandContext<CommandSource> context, ServerPlayerEntity player, long amount) {
/*  57 */     if (amount <= 0L) {
/*  58 */       return 1;
/*     */     }
/*     */     
/*  61 */     ItemStack stack = player.func_184614_ca();
/*  62 */     if (stack.func_190926_b()) {
/*  63 */       stack = player.func_184592_cb();
/*  64 */       if (stack.func_190926_b()) {
/*  65 */         return 1;
/*     */       }
/*     */     } 
/*     */     
/*  69 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/*  71 */     if (stack.func_77973_b() == ModItems.BELLY_POUCH.get()) {
/*  72 */       long belly = stack.func_196082_o().func_74763_f("belly");
/*  73 */       if (belly < amount) {
/*  74 */         amount = belly;
/*     */       }
/*     */       
/*  77 */       props.alterBelly(amount, StatChangeSource.COMMAND);
/*     */       
/*  79 */       long remains = belly - amount;
/*  80 */       if (remains > 0L) {
/*  81 */         stack.func_196082_o().func_74772_a("belly", remains);
/*     */       } else {
/*     */         
/*  84 */         stack.func_190918_g(1);
/*     */       } 
/*     */       
/*  87 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     }
/*  89 */     else if (stack.func_77973_b() == ModItems.EXTOL_POUCH.get()) {
/*  90 */       long extol = stack.func_196082_o().func_74763_f("extol");
/*  91 */       if (extol < amount) {
/*  92 */         amount = extol;
/*     */       }
/*     */       
/*  95 */       props.alterExtol(amount, StatChangeSource.COMMAND);
/*     */       
/*  97 */       long remains = extol - amount;
/*  98 */       if (remains > 0L) {
/*  99 */         stack.func_196082_o().func_74772_a("extol", remains);
/*     */       } else {
/*     */         
/* 102 */         stack.func_190918_g(1);
/*     */       } 
/*     */       
/* 105 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } 
/*     */     
/* 108 */     return 1;
/*     */   }
/*     */   
/*     */   private static int createPouch(CommandContext<CommandSource> context, Currency currency, long amount) {
/* 112 */     if (amount <= 0L) {
/* 113 */       return 1;
/*     */     }
/*     */ 
/*     */     
/* 117 */     try { ServerPlayerEntity player = ((CommandSource)context.getSource()).func_197035_h();
/*     */       
/* 119 */       switch (currency)
/*     */       { case EXTOL:
/* 121 */           createExtolPouch(player, amount);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 133 */           return 1; }  createBellyPouch(player, amount); } catch (CommandSyntaxException e) { e.printStackTrace(); }  return 1;
/*     */   }
/*     */   
/*     */   private static void createBellyPouch(ServerPlayerEntity player, long amount) {
/* 137 */     if (ItemsHelper.hasInventoryFull((LivingEntity)player)) {
/* 138 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_POUCH_MESSAGE_INVENTORY_FULL), Util.field_240973_b_);
/*     */       
/*     */       return;
/*     */     } 
/* 142 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 144 */     if (props.getBelly() <= 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 148 */     if (props.getBelly() - amount >= 0L) {
/* 149 */       props.alterBelly(-amount, StatChangeSource.COMMAND);
/*     */     } else {
/*     */       
/* 152 */       amount = props.getBelly();
/*     */       
/* 154 */       props.alterBelly(-amount, StatChangeSource.COMMAND);
/*     */     } 
/*     */     
/* 157 */     ItemStack pouch = new ItemStack((IItemProvider)ModItems.BELLY_POUCH.get());
/*     */     
/* 159 */     pouch.func_196082_o().func_74772_a("belly", amount);
/*     */     
/* 161 */     player.field_71071_by.func_70441_a(pouch);
/*     */     
/* 163 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */   }
/*     */   
/*     */   private static void createExtolPouch(ServerPlayerEntity player, long amount) {
/* 167 */     if (ItemsHelper.hasInventoryFull((LivingEntity)player)) {
/* 168 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_POUCH_MESSAGE_INVENTORY_FULL), Util.field_240973_b_);
/*     */       
/*     */       return;
/*     */     } 
/* 172 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/* 174 */     if (props.getExtol() <= 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 178 */     if (props.getExtol() - amount >= 0L) {
/* 179 */       props.alterExtol(-amount, StatChangeSource.COMMAND);
/*     */     } else {
/*     */       
/* 182 */       amount = props.getExtol();
/*     */       
/* 184 */       props.alterExtol(-amount, StatChangeSource.COMMAND);
/*     */     } 
/*     */     
/* 187 */     ItemStack pouch = new ItemStack((IItemProvider)ModItems.EXTOL_POUCH.get());
/*     */     
/* 189 */     pouch.func_196082_o().func_74772_a("extol", amount);
/*     */     
/* 191 */     player.field_71071_by.func_70441_a(pouch);
/*     */     
/* 193 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\PouchCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */