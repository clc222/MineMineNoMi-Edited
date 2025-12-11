/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import xyz.pixelatedw.mineminenomi.api.ComponentNotRegisteredException;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TPSDelta;
/*     */ 
/*     */ public abstract class AbilityComponent<A extends IAbility>
/*     */ {
/*  19 */   private int tickRate = 0;
/*     */   private long timeToTick;
/*     */   private final AbilityComponentKey<?> key;
/*     */   private final A ability;
/*     */   private boolean isDisabled = false;
/*     */   private boolean isClientSided = false;
/*  25 */   private List<BonusManager> bonuses = new ArrayList<>();
/*     */   
/*     */   public AbilityComponent(AbilityComponentKey<? extends AbilityComponent> key, A ability) {
/*  28 */     this.key = key;
/*  29 */     this.ability = ability;
/*     */   }
/*     */   
/*     */   public AbilityComponentKey<?> getKey() {
/*  33 */     return this.key;
/*     */   }
/*     */   
/*     */   public A getAbility() {
/*  37 */     return this.ability;
/*     */   }
/*     */   
/*     */   public Iterator<BonusManager> getBonusManagers() {
/*  41 */     return this.bonuses.iterator();
/*     */   }
/*     */   
/*     */   public void addBonusManager(BonusManager manager) {
/*  45 */     this.bonuses.add(manager);
/*     */   }
/*     */   
/*     */   public float getTpsFactor() {
/*  49 */     return TPSDelta.INSTANCE.getDeltaTime();
/*     */   }
/*     */   
/*     */   public void setTickRate(int tickRate) {
/*  53 */     this.tickRate = tickRate;
/*     */   }
/*     */   
/*     */   public final void tick(LivingEntity entity) {
/*  57 */     if (this.isDisabled) {
/*     */       return;
/*     */     }
/*     */     
/*  61 */     if (--this.timeToTick <= 0L) {
/*  62 */       this.timeToTick = this.tickRate;
/*     */       
/*  64 */       doTick(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doTick(LivingEntity entity) {}
/*     */   
/*     */   public void postInit(IAbility ability) {}
/*     */   
/*     */   public boolean isDisabled() {
/*  73 */     return this.isDisabled;
/*     */   }
/*     */   
/*     */   public void setDisabled(boolean isDisabled) {
/*  77 */     this.isDisabled = isDisabled;
/*     */   }
/*     */   
/*     */   public boolean isClientSided() {
/*  81 */     return this.isClientSided;
/*     */   }
/*     */   
/*     */   public void setClientSide() {
/*  85 */     this.isClientSided = true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CompoundNBT save() {
/*  90 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/*  92 */     if (!this.bonuses.isEmpty()) {
/*  93 */       ListNBT bonusNBT = new ListNBT();
/*  94 */       for (BonusManager manager : this.bonuses) {
/*  95 */         if (manager.getBonuses().size() <= 0) {
/*     */           continue;
/*     */         }
/*  98 */         CompoundNBT managerNBT = new CompoundNBT();
/*  99 */         managerNBT.func_186854_a("id", manager.getId());
/* 100 */         ListNBT managerBonusesNBT = new ListNBT();
/* 101 */         for (Map.Entry<UUID, BonusManager.BonusValue> entry : manager.getBonuses()) {
/* 102 */           CompoundNBT entryNBT = new CompoundNBT();
/* 103 */           entryNBT.func_186854_a("id", entry.getKey());
/* 104 */           entryNBT.func_74778_a("name", ((BonusManager.BonusValue)entry.getValue()).getName());
/* 105 */           entryNBT.func_74768_a("type", ((BonusManager.BonusValue)entry.getValue()).getType().ordinal());
/* 106 */           entryNBT.func_74776_a("value", ((BonusManager.BonusValue)entry.getValue()).getValue());
/* 107 */           managerBonusesNBT.add(entryNBT);
/*     */         } 
/* 109 */         managerNBT.func_218657_a("list", (INBT)managerBonusesNBT);
/* 110 */         bonusNBT.add(managerNBT);
/*     */       } 
/*     */       
/* 113 */       if (!bonusNBT.isEmpty()) {
/* 114 */         nbt.func_218657_a("bonuses", (INBT)bonusNBT);
/*     */       }
/*     */     } 
/*     */     
/* 118 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 123 */     ListNBT bonusNBT = nbt.func_150295_c("bonuses", 10);
/* 124 */     for (int i = 0; i < bonusNBT.size(); i++) {
/* 125 */       CompoundNBT managerNBT = bonusNBT.func_150305_b(i);
/* 126 */       UUID id = managerNBT.func_186857_a("id");
/* 127 */       BonusManager manager = this.bonuses.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
/* 128 */       if (manager != null) {
/*     */ 
/*     */         
/* 131 */         manager.clearBonuses();
/* 132 */         ListNBT managerBonusesNBT = managerNBT.func_150295_c("list", 10);
/* 133 */         for (int j = 0; j < managerBonusesNBT.size(); j++) {
/* 134 */           CompoundNBT entryNBT = managerBonusesNBT.func_150305_b(j);
/* 135 */           UUID entryId = entryNBT.func_186857_a("id");
/* 136 */           String name = entryNBT.func_74779_i("name");
/* 137 */           BonusOperation op = BonusOperation.values()[entryNBT.func_74762_e("type")];
/* 138 */           float value = entryNBT.func_74760_g("value");
/* 139 */           manager.addBonus(entryId, name, op, value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void ensureIsRegistered() {
/* 145 */     boolean hasComponent = getAbility().hasComponent(getKey());
/* 146 */     if (!hasComponent)
/* 147 */       throw new ComponentNotRegisteredException(this); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\AbilityComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */