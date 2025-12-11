/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.FlyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.OFPWWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProgressionEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class RemoveDFCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  38 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("removedf").requires(Requires.hasEitherPermission(((Boolean)GeneralConfig.PUBLIC_REMOVEDF.get()).booleanValue() ? 0 : 2, new WyPermissions.Permission[] { WyPermissions.REMOVE_DF_COMMAND, WyPermissions.REMOVE_DF_COMMAND_SELF }));
/*     */     
/*  40 */     ((LiteralArgumentBuilder)builder
/*  41 */       .executes(context -> removesDF(context, ((CommandSource)context.getSource()).func_197035_h())))
/*  42 */       .then(((RequiredArgumentBuilder)Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  43 */         .requires(Requires.hasPermission(2, WyPermissions.REMOVE_DF_COMMAND)))
/*  44 */         .executes(context -> removesDF(context, EntityArgument.func_197089_d(context, "target"))));
/*     */     
/*  46 */     if (masterBuilder != null) {
/*  47 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*     */       
/*  50 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int removesDF(CommandContext<CommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
/*     */     try {
/*  58 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  59 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*  60 */       OFPWWorldData worldData = OFPWWorldData.get();
/*     */       
/*  62 */       if (devilFruitProps.hasAnyDevilFruit()) {
/*  63 */         worldData.lostOneFruit(devilFruitProps.getDevilFruit().get(), (LivingEntity)player, "Removed via Command");
/*  64 */         if (devilFruitProps.hasYamiPower()) {
/*  65 */           worldData.lostOneFruit(ModAbilities.YAMI_YAMI_NO_MI.getRegistryName(), (LivingEntity)player, "Removed via Command");
/*     */         }
/*     */         
/*  68 */         devilFruitProps.removeDevilFruit();
/*  69 */         devilFruitProps.clearMorphs();
/*  70 */         devilFruitProps.setAwakenedFruit(false);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */       
/*  86 */       boolean hasUnlockedFlying = (abilityDataProps.getPassiveAbilities(abl -> (abl instanceof FlyAbility && !((FlyAbility)abl).isPaused())).size() > 0);
/*  87 */       if (hasUnlockedFlying && !player.func_184812_l_() && !player.func_175149_v()) {
/*  88 */         player.field_71075_bZ.field_75101_c = false;
/*  89 */         player.field_71075_bZ.field_75100_b = false;
/*  90 */         player.field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */       } 
/*     */       
/*  93 */       abilityDataProps.clearUnlockedAbilities(AbilityCategory.DEVIL_FRUITS.isCorePartofCategory());
/*  94 */       abilityDataProps.clearPassiveAbilities(AbilityCategory.DEVIL_FRUITS.isAbilityPartofCategory());
/*  95 */       abilityDataProps.clearEquippedAbilities(AbilityCategory.DEVIL_FRUITS.isAbilityPartofCategory());
/*  96 */       AbilityProgressionEvents.checkForRacialUnlocks((PlayerEntity)player);
/*     */       
/*  98 */       if (WyDebug.isDebug()) {
/*  99 */         player.func_195061_cb();
/*     */       }
/*     */       
/* 102 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), (Entity)player);
/* 103 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.func_145782_y(), abilityDataProps), (Entity)player);
/*     */ 
/*     */       
/* 106 */       ForgeRegistries.ATTRIBUTES.forEach(attr -> {
/*     */             if (player.func_110148_a(attr) != null) {
/*     */               player.func_110148_a(attr).func_225505_c_().stream().filter(AbilityAttributeModifier.class::isInstance).map(AbilityAttributeModifier.class::cast).forEach(());
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       AbilityHelper.enableAbilities((LivingEntity)player, ability -> true);
/*     */       
/* 117 */       ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Removed Devil Fruit for " + player.func_145748_c_().getString()), true);
/*     */     }
/* 119 */     catch (Exception e) {
/*     */       
/* 121 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 124 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\RemoveDFCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */