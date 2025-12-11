/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.time.Instant;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ 
/*    */ public class AbilityCoreUnlockWrapper<A extends IAbility>
/*    */ {
/*    */   private final AbilityCore<A> abilityCore;
/*    */   private final AbilityUnlock unlockedMethod;
/*    */   private final long unlockedRealTimestamp;
/*    */   private final long unlockedGameTimestamp;
/*    */   
/*    */   public AbilityCoreUnlockWrapper(LivingEntity entity, AbilityCore<A> abilityCore, AbilityUnlock unlockedMethod) {
/* 17 */     this.abilityCore = abilityCore;
/* 18 */     this.unlockedMethod = unlockedMethod;
/* 19 */     this.unlockedRealTimestamp = Instant.now().getEpochSecond();
/* 20 */     this.unlockedGameTimestamp = entity.field_70170_p.func_82737_E();
/*    */   }
/*    */   
/*    */   private AbilityCoreUnlockWrapper(AbilityCore<A> abilityCore, AbilityUnlock unlockedMethod, long unlockedRealTimestamp, long unlockedGameTimestamp) {
/* 24 */     this.abilityCore = abilityCore;
/* 25 */     this.unlockedMethod = unlockedMethod;
/* 26 */     this.unlockedRealTimestamp = unlockedRealTimestamp;
/* 27 */     this.unlockedGameTimestamp = unlockedGameTimestamp;
/*    */   }
/*    */   
/*    */   public AbilityCore<A> getAbilityCore() {
/* 31 */     return this.abilityCore;
/*    */   }
/*    */   
/*    */   public AbilityUnlock getUnlockType() {
/* 35 */     return this.unlockedMethod;
/*    */   }
/*    */   
/*    */   public long getRealTimestamp() {
/* 39 */     return this.unlockedRealTimestamp;
/*    */   }
/*    */   
/*    */   public long getGameTimestamp() {
/* 43 */     return this.unlockedGameTimestamp;
/*    */   }
/*    */   
/*    */   public CompoundNBT save() {
/* 47 */     CompoundNBT nbt = new CompoundNBT();
/* 48 */     nbt.func_74778_a("id", this.abilityCore.getRegistryName().toString());
/* 49 */     nbt.func_74778_a("unlockedMethod", this.unlockedMethod.name());
/* 50 */     nbt.func_74772_a("realTimestamp", this.unlockedRealTimestamp);
/* 51 */     nbt.func_74772_a("gameTimestamp", this.unlockedGameTimestamp);
/* 52 */     return nbt;
/*    */   }
/*    */   
/*    */   public static <A extends IAbility> AbilityCoreUnlockWrapper of(CompoundNBT nbt) {
/* 56 */     AbilityCore<A> abilityCore = (AbilityCore<A>)ModRegistries.ABILITIES.getValue(new ResourceLocation(nbt.func_74779_i("id")));
/* 57 */     AbilityUnlock unlockedMethod = AbilityUnlock.valueOf(nbt.func_74779_i("unlockedMethod"));
/* 58 */     long unlockedRealTimestamp = nbt.func_74763_f("realTimestamp");
/* 59 */     long unlockedGameTimestamp = nbt.func_74763_f("gameTimestamp");
/* 60 */     AbilityCoreUnlockWrapper<A> details = new AbilityCoreUnlockWrapper<>(abilityCore, unlockedMethod, unlockedRealTimestamp, unlockedGameTimestamp);
/* 61 */     return details;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityCoreUnlockWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */