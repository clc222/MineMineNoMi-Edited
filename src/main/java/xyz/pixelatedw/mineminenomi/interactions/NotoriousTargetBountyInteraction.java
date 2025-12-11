/*    */ package xyz.pixelatedw.mineminenomi.interactions;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18nInteractions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*    */ 
/*    */ public class NotoriousTargetBountyInteraction extends Interaction {
/*    */   public NotoriousTargetBountyInteraction(ITextComponent title) {
/* 16 */     super(title);
/*    */   }
/*    */   
/*    */   public static NotoriousTargetBountyInteraction start() {
/* 20 */     NotoriousTargetBountyInteraction interaction = new NotoriousTargetBountyInteraction((ITextComponent)ModI18nInteractions.BOUNTIES_TITLE);
/* 21 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_RUMOR_CONTINUE });
/* 22 */     return interaction;
/*    */   }
/*    */   
/*    */   public static NotoriousTargetBountyInteraction accept() {
/* 26 */     return new NotoriousTargetBountyInteraction((ITextComponent)ModI18nInteractions.ACCEPT_TITLE);
/*    */   }
/*    */ 
/*    */   
/*    */   public Interaction.InteractionResult trigger(PlayerEntity player, LivingEntity entity) {
/* 31 */     if (this == ModInteractions.BARKEEPER_RUMOR_CONTINUE.get()) {
/* 32 */       return Interaction.InteractionResult.close();
/*    */     }
/*    */     
/* 35 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 37 */     TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18nInteractions.NOTORIOUS_PIRATE_RUMOR_1_MESSAGE, new Object[] { "test" });
/* 38 */     setMessage((ITextComponent)translationTextComponent);
/*    */     
/* 40 */     return Interaction.InteractionResult.continueInteraction();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\interactions\NotoriousTargetBountyInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */