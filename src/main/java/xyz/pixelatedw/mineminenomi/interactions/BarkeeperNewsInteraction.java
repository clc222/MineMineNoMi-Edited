/*    */ package xyz.pixelatedw.mineminenomi.interactions;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.api.poi.NTEventTarget;
/*    */ import xyz.pixelatedw.mineminenomi.api.poi.NewsEntry;
/*    */ import xyz.pixelatedw.mineminenomi.api.poi.TrackedNPC;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.EventsWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18nInteractions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*    */ 
/*    */ public class BarkeeperNewsInteraction
/*    */   extends Interaction {
/*    */   public BarkeeperNewsInteraction() {}
/*    */   
/*    */   public BarkeeperNewsInteraction(ITextComponent title) {
/* 25 */     super(title);
/*    */   }
/*    */   
/*    */   public static BarkeeperNewsInteraction news() {
/* 29 */     BarkeeperNewsInteraction interaction = new BarkeeperNewsInteraction((ITextComponent)ModI18nInteractions.NEWS_TITLE);
/* 30 */     interaction.setTriggerAction(interaction::news);
/* 31 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_RUMOR_CONTINUE });
/* 32 */     return interaction;
/*    */   }
/*    */   
/*    */   public static BarkeeperNewsInteraction close() {
/* 36 */     BarkeeperNewsInteraction interaction = new BarkeeperNewsInteraction((ITextComponent)ModI18nInteractions.CONTINUE_TITLE);
/* 37 */     interaction.setTriggerAction(interaction::closeMenu);
/* 38 */     return interaction;
/*    */   }
/*    */   
/*    */   public Interaction.InteractionResult closeMenu(PlayerEntity player, LivingEntity entity) {
/* 42 */     return Interaction.InteractionResult.close();
/*    */   }
/*    */   
/*    */   public Interaction.InteractionResult news(PlayerEntity player, LivingEntity entity) {
/* 46 */     NPCWorldData npcWorldData = NPCWorldData.get();
/* 47 */     EventsWorldData eventWorldData = EventsWorldData.get();
/*    */     
/* 49 */     List<TrackedNPC> trackedList = (List<TrackedNPC>)eventWorldData.getNotoriousTargets().stream().map(target -> target.getTrackedNPC()).collect(Collectors.toList());
/* 50 */     TrackedNPC tracked = null;
/* 51 */     if (trackedList != null && !trackedList.isEmpty()) {
/* 52 */       Collections.shuffle(trackedList);
/* 53 */       tracked = trackedList.get(0);
/*    */     } else {
/*    */       
/* 56 */       trackedList = new ArrayList<>(npcWorldData.getTrackedMobs());
/* 57 */       Collections.shuffle(trackedList);
/* 58 */       tracked = trackedList.get(0);
/*    */     } 
/*    */     
/* 61 */     if (tracked != null) {
/* 62 */       NewsEntry newsEntry = tracked.getNewsEntry(player.field_70170_p);
/* 63 */       if (newsEntry != null) {
/* 64 */         if (newsEntry.getMessage() == null) {
/* 65 */           return Interaction.InteractionResult.close();
/*    */         }
/* 67 */         setMessage((ITextComponent)newsEntry.getMessage());
/*    */       } 
/*    */     } 
/*    */     
/* 71 */     return Interaction.InteractionResult.continueInteraction();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\interactions\BarkeeperNewsInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */