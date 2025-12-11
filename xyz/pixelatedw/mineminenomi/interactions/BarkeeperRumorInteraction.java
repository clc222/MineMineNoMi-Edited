/*     */ package xyz.pixelatedw.mineminenomi.interactions;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.NTEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.POIEventTarget;
/*     */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18nInteractions;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class BarkeeperRumorInteraction extends Interaction {
/*     */   private static final int PRICE = 10;
/*  27 */   private static final String[] PIRATE_MESSAGES = new String[] { ModI18nInteractions.NOTORIOUS_PIRATE_RUMOR_1_MESSAGE, ModI18nInteractions.NOTORIOUS_PIRATE_RUMOR_2_MESSAGE };
/*  28 */   private static final String[] MARINE_MESSAGES = new String[] { ModI18nInteractions.NOTORIOUS_MARINE_RUMOR_1_MESSAGE, ModI18nInteractions.NOTORIOUS_MARINE_RUMOR_2_MESSAGE };
/*     */ 
/*     */   
/*     */   public BarkeeperRumorInteraction() {}
/*     */ 
/*     */   
/*     */   public BarkeeperRumorInteraction(ITextComponent title) {
/*  35 */     super(title);
/*     */   }
/*     */   
/*     */   public static BarkeeperRumorInteraction rumor() {
/*  39 */     TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18nInteractions.RUMORS_WITH_PRICE_TITLE, new Object[] { Integer.valueOf(10) });
/*  40 */     BarkeeperRumorInteraction interaction = new BarkeeperRumorInteraction((ITextComponent)translationTextComponent);
/*  41 */     interaction.setTriggerAction(interaction::rumor);
/*  42 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_RUMOR_CONTINUE });
/*  43 */     return interaction;
/*     */   }
/*     */   
/*     */   public static BarkeeperRumorInteraction close() {
/*  47 */     BarkeeperRumorInteraction interaction = new BarkeeperRumorInteraction((ITextComponent)ModI18nInteractions.CONTINUE_TITLE);
/*  48 */     interaction.setTriggerAction(interaction::closeMenu);
/*  49 */     return interaction;
/*     */   }
/*     */   
/*     */   public static BarkeeperRumorInteraction noEvent() {
/*  53 */     BarkeeperRumorInteraction interaction = new BarkeeperRumorInteraction();
/*  54 */     interaction.setMessage((ITextComponent)ModI18nInteractions.BARKEEPER_NO_RUMOR_MESSAGE);
/*  55 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_RUMOR_CONTINUE });
/*  56 */     return interaction;
/*     */   }
/*     */   
/*     */   public static BarkeeperRumorInteraction noBelly() {
/*  60 */     BarkeeperRumorInteraction interaction = new BarkeeperRumorInteraction();
/*  61 */     interaction.setMessage((ITextComponent)ModI18nInteractions.BARKEEPER_NO_BELLY_MESSAGE);
/*  62 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_RUMOR_CONTINUE });
/*  63 */     return interaction;
/*     */   }
/*     */   
/*     */   public Interaction.InteractionResult closeMenu(PlayerEntity player, LivingEntity entity) {
/*  67 */     return Interaction.InteractionResult.close();
/*     */   }
/*     */   
/*     */   public Interaction.InteractionResult rumor(PlayerEntity player, LivingEntity entity) {
/*  71 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/*  73 */     if (props.getBelly() < 10L) {
/*  74 */       return Interaction.InteractionResult.next((Interaction)ModInteractions.BARKEEPER_RUMOR_NO_BELLY.get());
/*     */     }
/*     */     
/*  77 */     props.alterBelly(-10L, StatChangeSource.STORE);
/*  78 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*     */     
/*  80 */     boolean isMarine = (props.isMarine() || props.isBountyHunter());
/*     */     
/*  82 */     if (isMarine) {
/*  83 */       return findTargetEvent(player, entity, true);
/*     */     }
/*  85 */     int kind = getRandom().nextInt(2);
/*  86 */     if (kind == 0)
/*  87 */       return findTargetEvent(player, entity, false); 
/*  88 */     if (kind == 1) {
/*  89 */       return findCaravanEvent(player, entity);
/*     */     }
/*     */ 
/*     */     
/*  93 */     return Interaction.InteractionResult.close();
/*     */   }
/*     */   
/*     */   private Interaction.InteractionResult findCaravanEvent(PlayerEntity player, LivingEntity entity) {
/*  97 */     EventsWorldData eventsWorldData = EventsWorldData.get();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     Optional<POIEventTarget> event = eventsWorldData.getCaravanPOIs().stream().map(ev -> ImmutablePair.of(ev, Double.valueOf(ev.getPosition().func_72438_d(entity.func_213303_ch())))).sorted((p1, p2) -> (((Double)p2.right).doubleValue() - ((Double)p1.right).doubleValue() > 0.0D) ? 1 : -1).map(pair -> (POIEventTarget)pair.left).findFirst();
/*     */     
/* 106 */     if (!event.isPresent()) {
/* 107 */       return Interaction.InteractionResult.next((Interaction)ModInteractions.BARKEEPER_RUMOR_NONE.get());
/*     */     }
/*     */     
/* 110 */     String locationString = (int)(((POIEventTarget)event.get()).getPosition()).field_72450_a + " " + (int)(((POIEventTarget)event.get()).getPosition()).field_72448_b + " " + (int)(((POIEventTarget)event.get()).getPosition()).field_72449_c;
/*     */     
/* 112 */     TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18nInteractions.CARAVAN_RUMOR_1_MESSAGE, new Object[] { locationString });
/*     */     
/* 114 */     setMessage((ITextComponent)translationTextComponent);
/* 115 */     player.func_145747_a(getMessage(), Util.field_240973_b_);
/*     */     
/* 117 */     return Interaction.InteractionResult.continueInteraction();
/*     */   }
/*     */   private Interaction.InteractionResult findTargetEvent(PlayerEntity player, LivingEntity entity, boolean isMarine) {
/*     */     TranslationTextComponent translationTextComponent;
/* 121 */     EventsWorldData eventsWorldData = EventsWorldData.get();
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
/* 135 */     Optional<NTEventTarget> event = eventsWorldData.getNotoriousTargets().stream().filter(ev -> !(isMarine && ev.getTrackedNPC().isMarine())).map(ev -> ImmutablePair.of(ev, Double.valueOf(ev.getPosition().func_72438_d(entity.func_213303_ch())))).sorted((p1, p2) -> (((Double)p2.right).doubleValue() - ((Double)p1.right).doubleValue() > 0.0D) ? 1 : -1).map(pair -> (NTEventTarget)pair.left).findFirst();
/*     */     
/* 137 */     if (!event.isPresent()) {
/* 138 */       return Interaction.InteractionResult.next((Interaction)ModInteractions.BARKEEPER_RUMOR_NONE.get());
/*     */     }
/*     */     
/* 141 */     TrackedNPC npc = ((NTEventTarget)event.get()).getTrackedNPC();
/* 142 */     String npcName = npc.createTrackedMob(entity.field_70170_p).func_145748_c_().getString();
/*     */     
/* 144 */     String locationString = (int)(((NTEventTarget)event.get()).getPosition()).field_72450_a + " " + (int)(((NTEventTarget)event.get()).getPosition()).field_72448_b + " " + (int)(((NTEventTarget)event.get()).getPosition()).field_72449_c;
/*     */     
/* 146 */     ITextComponent message = null;
/* 147 */     if (npc.getFaction().equals(ModValues.PIRATE)) {
/* 148 */       translationTextComponent = new TranslationTextComponent(PIRATE_MESSAGES[getRandom().nextInt(PIRATE_MESSAGES.length)], new Object[] { locationString, npcName });
/* 149 */     } else if (((NTEventTarget)event.get()).getTrackedNPC().getFaction().equals(ModValues.MARINE)) {
/* 150 */       translationTextComponent = new TranslationTextComponent(MARINE_MESSAGES[getRandom().nextInt(MARINE_MESSAGES.length)], new Object[] { locationString, npcName });
/*     */     } 
/*     */     
/* 153 */     setMessage((ITextComponent)translationTextComponent);
/* 154 */     player.func_145747_a(getMessage(), Util.field_240973_b_);
/*     */     
/* 156 */     return Interaction.InteractionResult.continueInteraction();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\interactions\BarkeeperRumorInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */