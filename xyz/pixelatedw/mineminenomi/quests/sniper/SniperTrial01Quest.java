/*    */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HiNoToriBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ 
/*    */ public class SniperTrial01Quest extends Quest {
/*    */   private static final Predicate<ItemStack> ITEM_IS_BOW;
/*    */   private static final KillEntityObjective.ICheckKill BOW_DISTANCE_CHECK;
/* 25 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Hi no Tori Boshi", SniperTrial01Quest::new))
/* 26 */     .build();
/*    */   static {
/* 28 */     ITEM_IS_BOW = (itemStack -> ItemsHelper.isBow(itemStack));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     BOW_DISTANCE_CHECK = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.func_184614_ca();
/*    */         
/*    */         boolean isBow = ItemsHelper.isBow(heldItem);
/* 37 */         boolean isArrow = (source.func_76364_f() instanceof net.minecraft.entity.projectile.ArrowEntity || source.func_76364_f() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile || source.func_76364_f() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile);
/*    */         
/*    */         boolean distance = (player.func_70032_d((Entity)target) >= 30.0F);
/* 40 */         return (isBow && isArrow && distance);
/*    */       });
/*    */   }
/* 43 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a bow", 1, ITEM_IS_BOW);
/* 44 */   private Objective objective02 = (new ObtainItemObjective("Collect %s strings", 30, () -> Items.field_151007_F)).addRequirement(this.objective01);
/* 45 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies from at least 30 blocks away using a bow", 5, BOW_DISTANCE_CHECK)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public SniperTrial01Quest(QuestId id) {
/* 49 */     super(id);
/* 50 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 51 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 56 */     if (!removeQuestItem(player, Items.field_151007_F, 30)) {
/* 57 */       return false;
/*    */     }
/* 59 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 61 */     props.addUnlockedAbility(HiNoToriBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */