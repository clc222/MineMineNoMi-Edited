/*     */ package xyz.pixelatedw.mineminenomi.api.interactions;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ 
/*     */ public class Interaction extends ForgeRegistryEntry<Interaction> {
/*  13 */   private ITextComponent title = StringTextComponent.field_240750_d_;
/*  14 */   private ITextComponent message = StringTextComponent.field_240750_d_;
/*     */   private RegistryObject<Interaction>[] interactions;
/*     */   private InteractionTrigger triggerAction;
/*  17 */   private final Random random = new Random();
/*     */   
/*     */   public Interaction() {}
/*     */   
/*     */   public Interaction(ITextComponent title) {
/*  22 */     this.title = title;
/*     */   }
/*     */   
/*     */   public ITextComponent getTitle() {
/*  26 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setMessage(ITextComponent message) {
/*  30 */     this.message = message;
/*     */   }
/*     */   
/*     */   public ITextComponent getMessage() {
/*  34 */     return this.message;
/*     */   }
/*     */   
/*     */   public void setInteractions(RegistryObject<Interaction>... interactions) {
/*  38 */     this.interactions = interactions;
/*     */   }
/*     */   
/*     */   public RegistryObject<Interaction>[] getInteractions() {
/*  42 */     return this.interactions;
/*     */   }
/*     */   
/*     */   public boolean hasTriggerAction() {
/*  46 */     return (this.triggerAction != null);
/*     */   }
/*     */   
/*     */   public void setTriggerAction(InteractionTrigger trigger) {
/*  50 */     this.triggerAction = trigger;
/*     */   }
/*     */   
/*     */   public InteractionTrigger getTriggerAction() {
/*  54 */     return this.triggerAction;
/*     */   }
/*     */   
/*     */   public Random getRandom() {
/*  58 */     return this.random;
/*     */   }
/*     */   
/*     */   public InteractionResult trigger(PlayerEntity player, LivingEntity entity) {
/*  62 */     return this.triggerAction.trigger(player, entity);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface InteractionTrigger {
/*     */     Interaction.InteractionResult trigger(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity);
/*     */   }
/*     */   
/*     */   public static class InteractionResult {
/*     */     @Nullable
/*     */     private Interaction nextInteraction;
/*     */     private Interaction.InteractionResultType type;
/*     */     
/*     */     public InteractionResult(@Nullable Interaction next, Interaction.InteractionResultType type) {
/*  76 */       this.nextInteraction = next;
/*  77 */       this.type = type;
/*     */     }
/*     */     
/*     */     public static InteractionResult continueInteraction() {
/*  81 */       return new InteractionResult(null, Interaction.InteractionResultType.CONTINUE);
/*     */     }
/*     */     
/*     */     public static InteractionResult next(Interaction next) {
/*  85 */       return new InteractionResult(next, Interaction.InteractionResultType.NEXT);
/*     */     }
/*     */     
/*     */     public static InteractionResult close() {
/*  89 */       return new InteractionResult(null, Interaction.InteractionResultType.CLOSE_DIALOGUE);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public Interaction getNextInteraction() {
/*  94 */       return this.nextInteraction;
/*     */     }
/*     */     
/*     */     public Interaction.InteractionResultType getType() {
/*  98 */       return this.type;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum InteractionResultType {
/* 103 */     CLOSE_DIALOGUE,
/* 104 */     CONTINUE,
/* 105 */     NEXT;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\interactions\Interaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */