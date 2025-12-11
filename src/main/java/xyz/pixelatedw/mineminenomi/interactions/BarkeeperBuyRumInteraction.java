/*    */ package xyz.pixelatedw.mineminenomi.interactions;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18nInteractions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class BarkeeperBuyRumInteraction
/*    */   extends Interaction {
/*    */   private static final int PRICE = 100;
/*    */   
/*    */   public BarkeeperBuyRumInteraction() {}
/*    */   
/*    */   public BarkeeperBuyRumInteraction(ITextComponent title) {
/* 26 */     super(title);
/*    */   }
/*    */   
/*    */   public static BarkeeperBuyRumInteraction buyRum() {
/* 30 */     TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18nInteractions.BUY_RUM_TITLE, new Object[] { Integer.valueOf(100) });
/* 31 */     BarkeeperBuyRumInteraction interaction = new BarkeeperBuyRumInteraction((ITextComponent)translationTextComponent);
/* 32 */     interaction.setTriggerAction(interaction::buyRum);
/* 33 */     return interaction;
/*    */   }
/*    */   
/*    */   public static BarkeeperRumorInteraction close() {
/* 37 */     BarkeeperRumorInteraction interaction = new BarkeeperRumorInteraction((ITextComponent)ModI18nInteractions.CONTINUE_TITLE);
/* 38 */     interaction.setTriggerAction(interaction::closeMenu);
/* 39 */     return interaction;
/*    */   }
/*    */   
/*    */   public static BarkeeperBuyRumInteraction noBelly() {
/* 43 */     BarkeeperBuyRumInteraction interaction = new BarkeeperBuyRumInteraction();
/* 44 */     interaction.setMessage((ITextComponent)ModI18nInteractions.BARKEEPER_NO_BELLY_MESSAGE);
/* 45 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_BUY_RUM_CONTINUE });
/* 46 */     return interaction;
/*    */   }
/*    */   
/*    */   public Interaction.InteractionResult closeMenu(PlayerEntity player, LivingEntity entity) {
/* 50 */     return Interaction.InteractionResult.close();
/*    */   }
/*    */   
/*    */   public Interaction.InteractionResult buyRum(PlayerEntity player, LivingEntity entity) {
/* 54 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 56 */     if (props.getBelly() < 100L) {
/* 57 */       return Interaction.InteractionResult.next((Interaction)ModInteractions.BARKEEPER_RUMOR_NO_BELLY.get());
/*    */     }
/*    */     
/* 60 */     props.alterBelly(-100L, StatChangeSource.STORE);
/* 61 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), player);
/*    */     
/* 63 */     player.func_191521_c(((Item)ModItems.BOTTLE_OF_RUM.get()).func_190903_i());
/*    */     
/* 65 */     return Interaction.InteractionResult.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\interactions\BarkeeperBuyRumInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */