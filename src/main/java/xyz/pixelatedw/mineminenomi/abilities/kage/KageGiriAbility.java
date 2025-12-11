/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class KageGiriAbility
/*    */   extends PassiveAbility2
/*    */ {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kage_giri", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Allows the user to cut an enemy's shadow using a pair of Scissors", null)
/*    */       });
/* 24 */   public static final AbilityCore<KageGiriAbility> INSTANCE = (new AbilityCore.Builder("Kage Giri", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, KageGiriAbility::new))
/* 25 */     .addDescriptionLine(DESCRIPTION)
/* 26 */     .build();
/*    */   
/* 28 */   private Map<UUID, Long> shadowsList = new HashMap<>();
/*    */   
/*    */   public KageGiriAbility(AbilityCore<KageGiriAbility> ability) {
/* 31 */     super(ability);
/*    */   }
/*    */   
/*    */   public boolean addIfValid(UUID id) {
/* 35 */     if (this.shadowsList.containsKey(id)) {
/* 36 */       if (canTakeShadow(((Long)this.shadowsList.get(id)).longValue())) {
/* 37 */         this.shadowsList.remove(id);
/* 38 */         return true;
/*    */       } 
/*    */       
/* 41 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 45 */     this.shadowsList.put(id, Long.valueOf(System.currentTimeMillis()));
/* 46 */     return true;
/*    */   }
/*    */   
/*    */   public boolean canTakeShadow(long t) {
/* 50 */     return (System.currentTimeMillis() - t >= 600000L);
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT save(CompoundNBT nbt) {
/* 55 */     nbt = super.save(nbt);
/*    */     
/* 57 */     ListNBT shadows = new ListNBT();
/* 58 */     for (Map.Entry<UUID, Long> entry : this.shadowsList.entrySet()) {
/* 59 */       if (canTakeShadow(((Long)entry.getValue()).longValue())) {
/*    */         continue;
/*    */       }
/* 62 */       CompoundNBT entryNbt = new CompoundNBT();
/* 63 */       entryNbt.func_186854_a("id", entry.getKey());
/* 64 */       entryNbt.func_74772_a("time", ((Long)entry.getValue()).longValue());
/* 65 */       shadows.add(entryNbt);
/*    */     } 
/* 67 */     nbt.func_218657_a("shadows", (INBT)shadows);
/*    */     
/* 69 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT nbt) {
/* 74 */     super.load(nbt);
/*    */     
/* 76 */     ListNBT shadows = nbt.func_150295_c("shadows", 10);
/* 77 */     this.shadowsList.clear();
/* 78 */     for (int i = 0; i < shadows.size(); i++) {
/* 79 */       CompoundNBT entryNbt = shadows.func_150305_b(i);
/* 80 */       UUID id = entryNbt.func_186857_a("id");
/* 81 */       long time = entryNbt.func_74763_f("time");
/* 82 */       if (!canTakeShadow(time))
/*    */       {
/*    */         
/* 85 */         this.shadowsList.put(id, Long.valueOf(time));
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KageGiriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */