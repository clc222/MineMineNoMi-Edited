/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyDebug;
/*     */ 
/*     */ public class CheckPlayerCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher, @Nullable LiteralArgumentBuilder<CommandSource> masterBuilder) {
/*  35 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.func_197057_a("check_player").requires(Requires.hasPermission(2, WyPermissions.CHECK_PLAYER_COMMAND));
/*     */     
/*  37 */     ((LiteralArgumentBuilder)builder
/*  38 */       .executes(context -> checkPlayer(context, ((CommandSource)context.getSource()).func_197035_h(), true)))
/*  39 */       .then(Commands.func_197056_a("target", (ArgumentType)EntityArgument.func_197096_c())
/*  40 */         .then(Commands.func_197056_a("show_attributes", (ArgumentType)BoolArgumentType.bool())
/*  41 */           .executes(context -> checkPlayer(context, EntityArgument.func_197089_d(context, "target"), BoolArgumentType.getBool(context, "show_attributes")))));
/*     */     
/*  43 */     if (masterBuilder != null) {
/*  44 */       masterBuilder.then((ArgumentBuilder)builder);
/*     */     } else {
/*  46 */       dispatcher.register(builder);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkPlayer(CommandContext<CommandSource> context, ServerPlayerEntity player, boolean showAttributes) {
/*     */     try {
/*  53 */       IEntityStats statsData = EntityStatsCapability.get((LivingEntity)player);
/*  54 */       IDevilFruit fruitData = DevilFruitCapability.get((LivingEntity)player);
/*  55 */       IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
/*     */       
/*  57 */       ExtendedWorldData worldData = ExtendedWorldData.get((IWorld)player.field_70170_p);
/*  58 */       Crew crew = worldData.getCrewWithMember(player.func_110124_au());
/*     */       
/*  60 */       StringBuilder builder = new StringBuilder();
/*     */       
/*  62 */       builder.append("===============================================\n");
/*     */       
/*  64 */       builder.append("Name: " + player.func_145748_c_().getString() + "\n");
/*  65 */       builder.append("Faction: " + statsData.getFaction() + "\n");
/*  66 */       if (statsData.isMarine() || statsData.isRevolutionary() || statsData.isBountyHunter()) {
/*     */         
/*  68 */         builder.append("Loyalty: " + statsData.getLoyalty() + "\n");
/*  69 */         String rank = "None";
/*  70 */         if (statsData.isMarine()) {
/*  71 */           rank = statsData.getMarineRank().getLocalizedName();
/*     */         }
/*  73 */         else if (statsData.isRevolutionary()) {
/*  74 */           rank = statsData.getRevolutionaryRank().getLocalizedName();
/*     */         } 
/*  76 */         builder.append("Rank: " + rank + "\n");
/*     */       } 
/*  78 */       builder.append("Race: " + statsData.getRace() + "\n");
/*  79 */       if (statsData.isCyborg()) {
/*     */         
/*  81 */         builder.append("Max Cola: " + statsData.getMaxCola() + "\n");
/*  82 */         builder.append("Ultra Cola: " + statsData.getUltraCola() + "\n");
/*     */       } 
/*  84 */       builder.append("Style: " + statsData.getFightingStyle() + "\n");
/*  85 */       builder.append("Doriki: " + statsData.getDoriki() + "\n");
/*  86 */       builder.append("Busoshoku Haki: " + hakiData.getBusoshokuHakiExp() + "\n");
/*  87 */       builder.append("Kenbunshoku Haki: " + hakiData.getKenbunshokuHakiExp() + "\n");
/*  88 */       builder.append("Belly: " + statsData.getBelly() + "\n");
/*  89 */       builder.append("Extol: " + statsData.getExtol() + "\n");
/*  90 */       if (BountyHelper.canGainBounty((LivingEntity)player)) {
/*     */         
/*  92 */         builder.append("Bounty: " + statsData.getBounty() + "\n");
/*  93 */         builder.append("Issued Bounty: " + worldData.getBounty(player.func_110124_au()) + "\n");
/*     */       } 
/*  95 */       if (statsData.isPirate())
/*  96 */         builder.append("Crew: " + ((crew != null) ? crew.getName() : "None") + "\n"); 
/*  97 */       ItemStack fruitStack = new ItemStack((IItemProvider)fruitData.getDevilFruitItem());
/*  98 */       builder.append("Devil Fruit: " + (!fruitStack.func_190926_b() ? fruitStack.func_200301_q().getString() : "None") + "\n");
/*  99 */       builder.append("Damage Multiplier: " + statsData.getDamageMultiplier() + "\n");
/*     */       
/* 101 */       if (WyDebug.isDebug()) {
/* 102 */         builder.append("In Combat ?: " + WyHelper.isInCombat((LivingEntity)player) + "\n");
/* 103 */         builder.append("Combat Cache Timer: " + statsData.getLastAttackTime() + "\n");
/*     */       } 
/*     */       
/* 106 */       if (showAttributes) {
/*     */         
/* 108 */         builder.append("-----\n");
/* 109 */         builder.append("§2Attribute | Current Value / Base Value§r\n");
/* 110 */         for (Attribute attr : ForgeRegistries.ATTRIBUTES.getValues()) {
/*     */           
/* 112 */           ModifiableAttributeInstance modInst = player.func_233645_dx_().func_233779_a_(attr);
/* 113 */           if (modInst != null && modInst.func_111126_e() != modInst.func_111125_b()) {
/*     */             
/* 115 */             builder.append("- §9" + modInst.func_111123_a().getRegistryName() + " | " + modInst.func_111126_e() + "/" + modInst.func_111125_b() + "§r\n");
/* 116 */             for (AttributeModifier mod : modInst.func_225505_c_())
/*     */             {
/* 118 */               builder.append("  " + mod.func_111166_b() + " | " + mod.func_111164_d() + "\n");
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 124 */       builder.append("===============================================");
/*     */       
/* 126 */       ((CommandSource)context.getSource()).func_197030_a((ITextComponent)new StringTextComponent(builder.toString()), true);
/*     */     }
/* 128 */     catch (Exception e) {
/*     */       
/* 130 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 133 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\CheckPlayerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */