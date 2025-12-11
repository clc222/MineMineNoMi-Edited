/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityStat;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SSetStacksPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class StackComponent extends AbilityComponent<IAbility> {
/*  25 */   public static final TranslationTextComponent STACKS_STAT = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_STAT_NAME_STACKS);
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(int stacks) {
/*  28 */     return getTooltip(stacks, stacks);
/*     */   }
/*     */   
/*     */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(int min, int max) {
/*  32 */     return (e, a) -> {
/*     */         AbilityStat.Builder statBuilder = new AbilityStat.Builder((ITextComponent)STACKS_STAT, min, max);
/*     */         return statBuilder.build().getStatDescription();
/*     */       };
/*     */   }
/*     */   
/*  38 */   private int defaultStacks = 0;
/*  39 */   private int stacks = -1;
/*     */   
/*  41 */   private final PriorityEventPool<IStacksChangeEvent> changeStackEvents = new PriorityEventPool();
/*     */   
/*     */   public StackComponent(IAbility ability) {
/*  44 */     this(ability, 0);
/*     */   }
/*     */   
/*     */   public StackComponent(IAbility ability, int defaultStacks) {
/*  48 */     super(ModAbilityKeys.STACK, ability);
/*  49 */     this.defaultStacks = defaultStacks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  54 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> component.addPostRenderEvent(500, ()));
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
/*  65 */     if (ability instanceof Ability) {
/*  66 */       ((Ability)ability).addEquipEvent(this::onAbilityEquipped);
/*     */     }
/*     */   }
/*     */   
/*     */   public StackComponent addStackChangeEvent(IStacksChangeEvent event) {
/*  71 */     this.changeStackEvents.addEvent(event);
/*  72 */     return this;
/*     */   }
/*     */   
/*     */   public StackComponent addStackChangeEvent(int priority, IStacksChangeEvent event) {
/*  76 */     this.changeStackEvents.addEvent(priority, event);
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public void revertStacksToDefault(LivingEntity entity, IAbility ability) {
/*  81 */     setStacks(entity, ability, this.defaultStacks);
/*     */   }
/*     */   
/*     */   public void addStacks(LivingEntity entity, IAbility ability, int stacks) {
/*  85 */     setStacks(entity, ability, this.stacks + stacks);
/*     */   }
/*     */   
/*     */   public void setStacks(LivingEntity entity, IAbility ability, int stacks) {
/*  89 */     ensureIsRegistered();
/*     */     
/*  91 */     if (this.stacks == stacks) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     this.stacks = stacks;
/*  96 */     this.changeStackEvents.dispatch(event -> event.onStacksChange(entity, getAbility(), stacks));
/*     */     
/*  98 */     if (!entity.field_70170_p.field_72995_K) {
/*  99 */       IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */       
/* 101 */       int usedStacks = 0;
/* 102 */       int maxDefaultStacks = 0;
/*     */       
/* 104 */       for (IAbility abl : abilityDataProps.getEquippedAndPassiveAbilities()) {
/* 105 */         Optional<PoolComponent> poolComponent = abl.getComponent(ModAbilityKeys.POOL);
/* 106 */         Optional<StackComponent> stackComponent = abl.getComponent(ModAbilityKeys.STACK);
/*     */         
/* 108 */         if (poolComponent.isPresent() && stackComponent.isPresent()) {
/* 109 */           boolean sharesStacks = ((PoolComponent)poolComponent.get()).getPools().stream().anyMatch(pool -> ((Boolean)pool.getFlagValue("shareStacks", ())).booleanValue());
/*     */           
/* 111 */           if (sharesStacks) {
/* 112 */             int defaultStacks = ((StackComponent)stackComponent.get()).getDefaultStacks();
/*     */             
/* 114 */             if (((StackComponent)stackComponent.get()).getDefaultStacks() > maxDefaultStacks) {
/* 115 */               maxDefaultStacks = defaultStacks;
/*     */             }
/*     */             
/* 118 */             usedStacks += defaultStacks - ((StackComponent)stackComponent.get()).getStacks();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 123 */       for (IAbility abl : abilityDataProps.getEquippedAndPassiveAbilities()) {
/* 124 */         Optional<PoolComponent> poolComponent = abl.getComponent(ModAbilityKeys.POOL);
/* 125 */         Optional<StackComponent> stackComponent = abl.getComponent(ModAbilityKeys.STACK);
/*     */         
/* 127 */         if (poolComponent.isPresent() && stackComponent.isPresent()) {
/* 128 */           boolean sharesStacks = ((PoolComponent)poolComponent.get()).getPools().stream().anyMatch(pool -> ((Boolean)pool.getFlagValue("shareStacks", ())).booleanValue());
/*     */           
/* 130 */           if (sharesStacks && usedStacks >= maxDefaultStacks && ((StackComponent)stackComponent.get()).getStacks() != ((StackComponent)stackComponent.get()).getDefaultStacks()) {
/* 131 */             ((StackComponent)stackComponent.get()).stacks = 0;
/*     */             
/* 133 */             WyNetwork.sendToAllTrackingAndSelf(new SSetStacksPacket(entity, abl, stacks), (Entity)entity);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 138 */       WyNetwork.sendToAllTrackingAndSelf(new SSetStacksPacket(entity, ability, stacks), (Entity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDefaultStacks(int defaultStacks) {
/* 143 */     this.defaultStacks = defaultStacks;
/*     */   }
/*     */   
/*     */   public int getDefaultStacks() {
/* 147 */     return this.defaultStacks;
/*     */   }
/*     */   
/*     */   public int getStacks() {
/* 151 */     return this.stacks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onAbilityEquipped(LivingEntity entity, IAbility ability) {
/* 158 */     if (this.stacks < 0) {
/* 159 */       this.stacks = this.defaultStacks;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/* 166 */     CompoundNBT nbt = super.save();
/* 167 */     nbt.func_74768_a("defaultStacks", this.defaultStacks);
/* 168 */     nbt.func_74768_a("stacks", this.stacks);
/* 169 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 174 */     super.load(nbt);
/* 175 */     this.defaultStacks = nbt.func_74762_e("defaultStacks");
/* 176 */     this.stacks = nbt.func_74762_e("stacks");
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStacksChangeEvent {
/*     */     void onStacksChange(LivingEntity param1LivingEntity, IAbility param1IAbility, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\StackComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */