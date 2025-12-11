/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SChangeAbilityAltModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class AltModeComponent<E extends Enum<E>> extends AbilityComponent<IAbility> {
/*  22 */   private final PriorityEventPool<IChangeModeEvent<E>> changeModeEvents = new PriorityEventPool();
/*     */   
/*     */   private final Class<E> modeClass;
/*     */   
/*     */   private final E defaultMode;
/*     */   
/*     */   private E currentMode;
/*     */   
/*     */   private boolean isModeChangeAutomatic;
/*     */   
/*     */   private boolean playReadyAnim = false;
/*  33 */   private float readyAnim = 0.0F;
/*     */   
/*     */   public AltModeComponent(IAbility ability, Class<E> clz, E defaultMode) {
/*  36 */     this(ability, clz, defaultMode, false);
/*     */   }
/*     */   
/*     */   public AltModeComponent(IAbility ability, Class<E> clz, E defaultMode, boolean isModeChangeAutomatic) {
/*  40 */     super(ModAbilityKeys.ALT_MODE, ability);
/*     */     
/*  42 */     this.modeClass = clz;
/*  43 */     this.defaultMode = defaultMode;
/*  44 */     this.currentMode = defaultMode;
/*  45 */     this.isModeChangeAutomatic = isModeChangeAutomatic;
/*     */   }
/*     */   
/*     */   public AltModeComponent<E> addChangeModeEvent(IChangeModeEvent<E> event) {
/*  49 */     this.changeModeEvents.addEvent(event);
/*  50 */     return this;
/*     */   }
/*     */   
/*     */   public AltModeComponent<E> addChangeModeEvent(int priority, IChangeModeEvent<E> event) {
/*  54 */     this.changeModeEvents.addEvent(priority, event);
/*  55 */     return this;
/*     */   }
/*     */   
/*     */   public void setMode(LivingEntity entity, Enum mode) {
/*  59 */     setMode(entity, (E)mode, true);
/*     */   }
/*     */   
/*     */   public void revertToDefault(LivingEntity entity) {
/*  63 */     setMode(entity, this.defaultMode, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setMode(LivingEntity entity, E mode, boolean update) {
/*  73 */     ensureIsRegistered();
/*  74 */     if (!isMode(mode)) {
/*     */       try {
/*  76 */         this.changeModeEvents.dispatch(event -> event.changeMode(entity, getAbility(), mode));
/*  77 */         this.currentMode = mode;
/*  78 */         this.playReadyAnim = true;
/*     */       }
/*  80 */       catch (Exception ex) {
/*  81 */         ex.printStackTrace();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/*  89 */     if (update && 
/*  90 */       entity instanceof ServerPlayerEntity) {
/*  91 */       ServerPlayerEntity player = (ServerPlayerEntity)entity;
/*     */       
/*  93 */       WyNetwork.sendToAllTrackingAndSelf(new SChangeAbilityAltModePacket((LivingEntity)player, (IAbility)getAbility(), (Enum)getCurrentMode()), (Entity)player);
/*     */       
/*  95 */       if (!isAutomatic()) {
/*  96 */         entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ALT_MODE_CHANGE, new Object[] { getAbility().getDisplayName().getString(), getCurrentMode() }), Util.field_240973_b_);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNextInCycle(LivingEntity entity) {
/* 103 */     Enum[] arrayOfEnum = (Enum[])this.modeClass.getEnumConstants();
/*     */     
/* 105 */     Enum enum_ = arrayOfEnum[(this.currentMode.ordinal() + 1) % arrayOfEnum.length];
/*     */     
/* 107 */     setMode(entity, enum_);
/*     */   }
/*     */   
/*     */   public boolean isMode(E mode) {
/* 111 */     return this.currentMode.equals(mode);
/*     */   }
/*     */   
/*     */   public E getCurrentMode() {
/* 115 */     return this.currentMode;
/*     */   }
/*     */   
/*     */   public Class<E> getAltMode() {
/* 119 */     return this.modeClass;
/*     */   }
/*     */   
/*     */   public boolean isAutomatic() {
/* 123 */     return this.isModeChangeAutomatic;
/*     */   }
/*     */   
/*     */   private int getModesAmount() {
/* 127 */     Enum[] arrayOfEnum = (Enum[])this.modeClass.getEnumConstants();
/* 128 */     return arrayOfEnum.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/* 133 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> component.addPostRenderEvent(100, ()));
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 170 */     CompoundNBT nbt = super.save();
/*     */     
/* 172 */     nbt.func_74778_a("currentMode", this.currentMode.toString());
/*     */     
/* 174 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 179 */     super.load(nbt);
/*     */     
/* 181 */     this.currentMode = Enum.valueOf(this.modeClass, nbt.func_74779_i("currentMode"));
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IChangeModeEvent<E extends Enum<E>> {
/*     */     void changeMode(LivingEntity param1LivingEntity, IAbility param1IAbility, E param1E);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\AltModeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */