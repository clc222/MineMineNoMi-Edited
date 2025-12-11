/*    */ package xyz.pixelatedw.mineminenomi.api.events.ability;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ public class ModifyDevilFruitEvent extends Event {
/*    */   private final AkumaNoMiItem fruit;
/*    */   private final ImmutableList<AbilityCore<?>> originalAbilities;
/*    */   private List<AbilityCore<?>> abilities;
/*    */   
/*    */   public ModifyDevilFruitEvent(AkumaNoMiItem fruit, AbilityCore<?>... abilities) {
/* 17 */     this.fruit = fruit;
/* 18 */     this.originalAbilities = ImmutableList.copyOf((Object[])abilities);
/* 19 */     this.abilities = Lists.newArrayList((Object[])abilities);
/*    */   }
/*    */   
/*    */   public void addAbilityCore(AbilityCore<?> core) {
/* 23 */     this.abilities.add(core);
/*    */   }
/*    */   
/*    */   public void removeAbilityCore(AbilityCore<?> core) {
/* 27 */     this.abilities.remove(core);
/*    */   }
/*    */   
/*    */   public void removeAbilityCore(Class<? extends Ability> clz) {
/* 31 */     this.abilities.removeIf(abl -> abl.createAbility().getClass().equals(clz));
/*    */   }
/*    */   
/*    */   public AbilityCore<?>[] getAbilities() {
/* 35 */     return (AbilityCore<?>[])this.abilities.<AbilityCore>toArray(new AbilityCore[0]);
/*    */   }
/*    */   
/*    */   public final ImmutableList<AbilityCore<?>> getOriginalAbilities() {
/* 39 */     return this.originalAbilities;
/*    */   }
/*    */   
/*    */   public final AkumaNoMiItem getFruitItem() {
/* 43 */     return this.fruit;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\ability\ModifyDevilFruitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */