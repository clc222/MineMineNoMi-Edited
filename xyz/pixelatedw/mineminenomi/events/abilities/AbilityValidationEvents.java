/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ 
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCoreUnlockWrapper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class AbilityValidationEvents {
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/*  39 */     if ((event.getEntity()).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  43 */     if (event.getEntity() instanceof PlayerEntity) {
/*  44 */       PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */       
/*  46 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*  47 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  48 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*  49 */       IChallengesData challengesProps = ChallengesDataCapability.get(player);
/*  50 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*     */       
/*  52 */       entityStatsProps.setCombatMode(false);
/*     */       
/*  54 */       for (int bar = 0; bar < 10; bar++) {
/*  55 */         if (bar >= CommonConfig.INSTANCE.getAbilityBars())
/*     */         {
/*     */ 
/*     */           
/*  59 */           for (int slot = 0; slot < 8; slot++) {
/*  60 */             abilityProps.setEquippedAbility(slot + bar * 8, null);
/*     */           }
/*     */         }
/*     */       } 
/*  64 */       if (CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*  65 */         ItemStack dfStack = new ItemStack((IItemProvider)devilFruitProps.getDevilFruitItem());
/*     */ 
/*     */         
/*  68 */         if (dfStack.func_77973_b() == Blocks.field_150350_a.func_199767_j()) {
/*  69 */           devilFruitProps.setDevilFruit((ResourceLocation)null);
/*     */         }
/*     */         
/*  72 */         AbilityProgressionEvents.checkAllForNewUnlocks(player);
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
/*  85 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), entityStatsProps), player);
/*  86 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), player);
/*  87 */       WyNetwork.sendTo(new SSyncHakiDataPacket(player.func_145782_y(), hakiProps), player);
/*  88 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.func_145782_y(), abilityProps), player);
/*  89 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.func_145782_y(), challengesProps), player);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean validateUnlockedAbilities(LivingEntity entity) {
/*  95 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  96 */     long updates = ((Long)props.getUnlockedAbilities().stream().map(wrapper -> wrapper.getAbilityCore()).map(core -> Boolean.valueOf(AbilityHelper.checkAndUnlockAbility(entity, core))).filter(b -> b.booleanValue()).collect(Collectors.counting())).longValue();
/*  97 */     return (updates != 0L);
/*     */   }
/*     */   
/*     */   public static void checkForPossibleFruitAbilities(LivingEntity entity) {
/* 101 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
/* 102 */     ItemStack df = new ItemStack((IItemProvider)devilFruitProps.getDevilFruitItem());
/*     */     
/* 104 */     if (!df.func_190926_b())
/* 105 */       for (AbilityCore<?> core : ((AkumaNoMiItem)df.func_77973_b()).getAbilities())
/* 106 */         AbilityHelper.checkAndUnlockAbility(entity, core);  
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityValidationEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */