/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.gcd.GCDCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlotDecorationComponent
/*     */   extends AbilityComponent<IAbility>
/*     */ {
/*     */   private static final String FLUSH = "";
/*  19 */   private float slotRed = 1.0F;
/*  20 */   private float slotGreen = 1.0F;
/*  21 */   private float slotBlue = 1.0F;
/*  22 */   private float iconRed = 1.0F;
/*  23 */   private float iconGreen = 1.0F;
/*  24 */   private float iconBlue = 1.0F;
/*  25 */   private float iconAlpha = 1.0F;
/*  26 */   private float maxValue = 1.0F;
/*  27 */   private float currentValue = 0.0F;
/*  28 */   private String displayText = "";
/*     */   
/*  30 */   private final PriorityEventPool<IPreRenderEvent> preRenderEvents = new PriorityEventPool();
/*  31 */   private final PriorityEventPool<IPostRenderEvent> postRenderEvents = new PriorityEventPool();
/*     */   
/*     */   public SlotDecorationComponent(IAbility ability) {
/*  34 */     super(ModAbilityKeys.SLOT_DECORATION, ability);
/*     */     
/*  36 */     setClientSide();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  41 */     addPreRenderEvent(999, (entity, minecraft, matrix, x, y, partialTicks) -> {
/*     */           if (GCDCapability.isOnGCD(entity) && ((Integer)GCDCapability.getGCD(entity).getKey()).intValue() > 0) {
/*     */             ImmutablePair immutablePair = GCDCapability.getGCD(entity);
/*     */             setCurrentValue(((Integer)immutablePair.getKey()).intValue());
/*     */             setMaxValue(((Integer)immutablePair.getValue()).intValue());
/*     */             setSlotColor(0.7F, 0.0F, 0.0F);
/*     */             setIconColor(0.4F, 0.4F, 0.4F);
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   public SlotDecorationComponent addPreRenderEvent(int priority, IPreRenderEvent event) {
/*  53 */     this.preRenderEvents.addEvent(priority, event);
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public SlotDecorationComponent addPostRenderEvent(int priority, IPostRenderEvent event) {
/*  58 */     this.postRenderEvents.addEvent(priority, event);
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public void setSlotColor(float red, float green, float blue) {
/*  63 */     this.slotRed = red;
/*  64 */     this.slotGreen = green;
/*  65 */     this.slotBlue = blue;
/*     */   }
/*     */   
/*     */   public void setIconColor(float red, float green, float blue) {
/*  69 */     this.iconRed = red;
/*  70 */     this.iconGreen = green;
/*  71 */     this.iconBlue = blue;
/*     */   }
/*     */   
/*     */   public void setIconAlpha(float alpha) {
/*  75 */     this.iconAlpha = alpha;
/*     */   }
/*     */   
/*     */   public void setCurrentValue(float value) {
/*  79 */     this.currentValue = value;
/*  80 */     this.displayText = "";
/*     */   }
/*     */   
/*     */   public void setDisplayText(String value) {
/*  84 */     this.displayText = value;
/*     */   }
/*     */   
/*     */   public void setMaxValue(float max) {
/*  88 */     this.maxValue = max;
/*     */   }
/*     */   
/*     */   public void triggerPreRenderEvents(LivingEntity entity, Minecraft minecraft, MatrixStack matrixStack, float posX, float posY, float partialTicks) {
/*  92 */     this.preRenderEvents.dispatch(event -> event.preRender(entity, minecraft, matrixStack, posX, posY, partialTicks));
/*     */   }
/*     */   
/*     */   public void triggerPostRenderEvents(LivingEntity entity, Minecraft minecraft, MatrixStack matrixStack, float posX, float posY, float partialTicks) {
/*  96 */     this.postRenderEvents.dispatch(event -> event.postRender(entity, minecraft, matrixStack, posX, posY, partialTicks));
/*     */   }
/*     */   
/*     */   public float getSlotRed() {
/* 100 */     return this.slotRed;
/*     */   }
/*     */   
/*     */   public float getSlotGreen() {
/* 104 */     return this.slotGreen;
/*     */   }
/*     */   
/*     */   public float getSlotBlue() {
/* 108 */     return this.slotBlue;
/*     */   }
/*     */   
/*     */   public float getIconRed() {
/* 112 */     return this.iconRed;
/*     */   }
/*     */   
/*     */   public float getIconGreen() {
/* 116 */     return this.iconGreen;
/*     */   }
/*     */   
/*     */   public float getIconBlue() {
/* 120 */     return this.iconBlue;
/*     */   }
/*     */   
/*     */   public float getIconAlpha() {
/* 124 */     return this.iconAlpha;
/*     */   }
/*     */   
/*     */   public float getCurrentValue() {
/* 128 */     return this.currentValue;
/*     */   }
/*     */   
/*     */   public String getDisplayText() {
/* 132 */     return this.displayText;
/*     */   }
/*     */   
/*     */   public boolean hasDisplayText() {
/* 136 */     return !Strings.isNullOrEmpty(this.displayText);
/*     */   }
/*     */   
/*     */   public float getMaxValue() {
/* 140 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public void resetDecoration() {
/* 144 */     setSlotColor(1.0F, 1.0F, 1.0F);
/* 145 */     setIconColor(1.0F, 1.0F, 1.0F);
/* 146 */     setIconAlpha(1.0F);
/* 147 */     setCurrentValue(0.0F);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IPostRenderEvent {
/*     */     void postRender(LivingEntity param1LivingEntity, Minecraft param1Minecraft, MatrixStack param1MatrixStack, float param1Float1, float param1Float2, float param1Float3);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IPreRenderEvent {
/*     */     void preRender(LivingEntity param1LivingEntity, Minecraft param1Minecraft, MatrixStack param1MatrixStack, float param1Float1, float param1Float2, float param1Float3);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\SlotDecorationComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */