/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityGroupArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class AbilityCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  45 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("ability").requires(Requires.hasEitherPermission(2, new WyPermissions.Permission[] { WyPermissions.ABILITY_COMMAND_RESET_COOLDOWN, WyPermissions.ABILITY_COMMAND }));
/*     */     
/*  47 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  48 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("give")
/*  49 */         .requires(Requires.hasPermission(2, WyPermissions.ABILITY_COMMAND)))
/*  50 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("ability", (ArgumentType)AbilityArgument.ability())
/*  51 */           .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/*  52 */             .executes(context -> addAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.func_197090_e(context, "targets")))))
/*  53 */           .executes(context -> addAbility(context, AbilityArgument.getAbility(context, "ability"), getDefaultCollection(context))))))
/*  54 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("remove")
/*  55 */         .requires(Requires.hasPermission(2, WyPermissions.ABILITY_COMMAND)))
/*  56 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("ability", (ArgumentType)new AbilityArgument())
/*  57 */           .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/*  58 */             .executes(context -> removeAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.func_197090_e(context, "targets")))))
/*  59 */           .executes(context -> removeAbility(context, AbilityArgument.getAbility(context, "ability"), getDefaultCollection(context))))))
/*  60 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("unlock_group")
/*  61 */         .requires(Requires.hasPermission(2, WyPermissions.ABILITY_COMMAND)))
/*  62 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("group", (ArgumentType)AbilityGroupArgument.abilityGroup())
/*  63 */           .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/*  64 */             .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), 1, EntityArgument.func_197090_e(context, "targets")))))
/*  65 */           .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), 1, getDefaultCollection(context))))))
/*  66 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("lock_group")
/*  67 */         .requires(Requires.hasPermission(2, WyPermissions.ABILITY_COMMAND)))
/*  68 */         .then(((RequiredArgumentBuilder)Commands.func_197056_a("group", (ArgumentType)AbilityGroupArgument.abilityGroup())
/*  69 */           .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/*  70 */             .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), -1, EntityArgument.func_197090_e(context, "targets")))))
/*  71 */           .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), -1, getDefaultCollection(context))))))
/*  72 */       .then(((LiteralArgumentBuilder)Commands.func_197057_a("reset_cooldown")
/*  73 */         .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/*  74 */           .executes(context -> resetCooldown(context, EntityArgument.func_197090_e(context, "targets")))))
/*  75 */         .executes(context -> resetCooldown(context, getDefaultCollection(context))));
/*     */     
/*  77 */     if (CommonConfig.INSTANCE.hasAwakeningsEnabled()) {
/*  78 */       builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.func_197057_a("awaken")
/*  79 */           .requires(Requires.hasPermission(2, WyPermissions.ABILITY_COMMAND)))
/*  80 */           .then(Commands.func_197056_a("targets", (ArgumentType)EntityArgument.func_197094_d())
/*  81 */             .executes(context -> awakeFruit(context, EntityArgument.func_197090_e(context, "targets")))))
/*  82 */           .executes(context -> awakeFruit(context, getDefaultCollection(context))));
/*     */     }
/*     */     
/*  85 */     if (masterBuilder != null) {
/*  86 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*  88 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */   private static int awakeFruit(CommandContext<CommandSource> context, Collection<ServerPlayerEntity> players) {
/*  92 */     for (ServerPlayerEntity player : players) {
/*  93 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  94 */       props.setAwakenedFruit(!props.hasAwakenedFruit());
/*  95 */       AbilityValidationEvents.checkForPossibleFruitAbilities((LivingEntity)player);
/*  96 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } 
/*     */     
/*  99 */     return 1;
/*     */   }
/*     */   
/*     */   private static int abilityGroup(CommandContext<CommandSource> context, AbilityCommandGroup group, int op, Collection<ServerPlayerEntity> players) {
/* 103 */     for (ServerPlayerEntity player : players) {
/* 104 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 106 */       for (AbilityCore core : group.getAbilities()) {
/* 107 */         if (AbilityHelper.verifyIfAbilityIsBanned(core)) {
/*     */           continue;
/*     */         }
/*     */         
/* 111 */         if (op == 1) {
/* 112 */           abilityProps.addUnlockedAbility(core, AbilityUnlock.COMMAND);
/*     */           continue;
/*     */         } 
/* 115 */         abilityProps.removeUnlockedAbility(core);
/*     */       } 
/*     */ 
/*     */       
/* 119 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityProps), (PlayerEntity)player);
/*     */     } 
/* 121 */     return 1;
/*     */   }
/*     */   
/*     */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 125 */     return Lists.newArrayList((Object[])new ServerPlayerEntity[] { ((CommandSource)context.getSource()).func_197035_h() });
/*     */   }
/*     */   
/*     */   private static int resetCooldown(CommandContext<CommandSource> context, Collection<ServerPlayerEntity> players) {
/* 129 */     for (Iterator<ServerPlayerEntity> iterator = players.iterator(); iterator.hasNext(); ) { ServerPlayerEntity player = iterator.next();
/* 130 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 131 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*     */       
/* 133 */       for (IAbility ability : props.getEquippedAbilities()) {
/* 134 */         if (ability == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 138 */         ability.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(c -> c.stopCooldown((LivingEntity)player));
/*     */ 
/*     */ 
/*     */         
/* 142 */         if (ability instanceof Ability && !((Ability)ability).isNew && ((Ability)ability).isOnCooldown()) {
/* 143 */           ((Ability)ability).stopCooldown((PlayerEntity)player);
/*     */         }
/*     */         
/* 146 */         ability.getComponent(ModAbilityKeys.STACK).ifPresent(stackComp -> stackComp.revertStacksToDefault((LivingEntity)player, ability));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 151 */       hakiProps.setHakiOveruse(0); }
/*     */ 
/*     */     
/* 154 */     return 1;
/*     */   }
/*     */   
/*     */   private static int addAbility(CommandContext<CommandSource> context, AbilityCore core, Collection<ServerPlayerEntity> targets) {
/* 158 */     for (ServerPlayerEntity player : targets) {
/* 159 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 161 */       if (AbilityHelper.verifyIfAbilityIsBanned(core)) {
/* 162 */         return -1;
/*     */       }
/*     */       
/* 165 */       props.addUnlockedAbility(core, AbilityUnlock.COMMAND);
/*     */       
/* 167 */       if (WyDebug.isDebug()) {
/* 168 */         player.func_145747_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + core.getLocalizedName().getString() + " unlocked for " + player
/* 169 */               .func_200200_C_().getString()), Util.field_240973_b_);
/*     */       }
/*     */       
/* 172 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } 
/*     */     
/* 175 */     return 1;
/*     */   }
/*     */   
/*     */   private static int removeAbility(CommandContext<CommandSource> context, AbilityCore ability, Collection<ServerPlayerEntity> targets) {
/* 179 */     for (ServerPlayerEntity player : targets) {
/* 180 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 182 */       props.removeUnlockedAbility(ability);
/*     */       
/* 184 */       if (WyDebug.isDebug()) {
/* 185 */         player.func_145747_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getUnlocalizedName() + " removed for " + player
/* 186 */               .func_200200_C_().getString()), Util.field_240973_b_);
/*     */       }
/*     */       
/* 189 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */     } 
/*     */     
/* 192 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\AbilityCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */