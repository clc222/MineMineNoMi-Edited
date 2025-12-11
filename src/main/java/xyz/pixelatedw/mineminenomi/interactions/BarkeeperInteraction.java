/*    */ package xyz.pixelatedw.mineminenomi.interactions;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18nInteractions;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModInteractions;
/*    */ 
/*    */ public class BarkeeperInteraction extends Interaction {
/* 13 */   private static final ITextComponent[] MESSAGES = new ITextComponent[] { (ITextComponent)ModI18nInteractions.RANDOM_INTRO_1_TITLE, (ITextComponent)ModI18nInteractions.RANDOM_INTRO_2_TITLE };
/*    */   
/*    */   public BarkeeperInteraction(ITextComponent title) {
/* 16 */     super(title);
/*    */   }
/*    */   
/*    */   public static BarkeeperInteraction interaction() {
/* 20 */     BarkeeperInteraction interaction = new BarkeeperInteraction(StringTextComponent.field_240750_d_);
/* 21 */     interaction.setInteractions(new RegistryObject[] { ModInteractions.BARKEEPER_NEWS, ModInteractions.BARKEEPER_RUMOR, ModInteractions.BARKEEPER_BUY_RUM });
/* 22 */     return interaction;
/*    */   }
/*    */ 
/*    */   
/*    */   public ITextComponent getMessage() {
/* 27 */     return MESSAGES[getRandom().nextInt(MESSAGES.length)];
/*    */   }
/*    */ 
/*    */   
/*    */   public Interaction.InteractionResult trigger(PlayerEntity player, LivingEntity entity) {
/* 32 */     return Interaction.InteractionResult.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\interactions\BarkeeperInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */