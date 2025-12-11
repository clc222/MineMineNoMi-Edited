/*     */ package xyz.pixelatedw.mineminenomi.quests.sniper;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.quest.SDespawnQuestObjectivePacket;
/*     */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*     */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class SniperTrial04Quest extends Quest {
/*  31 */   public static final QuestId INSTANCE = (new QuestId.Builder("Trial: Sakuretsu Saboten Boshi", SniperTrial04Quest::new))
/*  32 */     .addRequirements(new QuestId[] { ModQuests.SNIPER_TRIAL_01, ModQuests.SNIPER_TRIAL_02
/*  33 */       }).build(); private static final Predicate<ItemStack> ITEM_WITH_PUNCH_2;
/*     */   static {
/*  35 */     ITEM_WITH_PUNCH_2 = (itemStack -> 
/*     */       
/*  37 */       (ItemsHelper.isBow(itemStack) && EnchantmentHelper.func_77506_a(Enchantments.field_185310_v, itemStack) > 1));
/*     */ 
/*     */     
/*  40 */     TARGET_CHECK = ((player, target, source) -> (target.func_200600_R() == ModEntities.SNIPER_TARGET.get()));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final KillEntityObjective.ICheckKill TARGET_CHECK;
/*  45 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a bow with Punch II", 1, ITEM_WITH_PUNCH_2);
/*  46 */   private Objective objective02 = (new ObtainItemObjective("Collect %s cacti", 50, () -> Items.field_221774_cw)).addRequirement(this.objective01);
/*  47 */   private Objective objective03 = (new KillEntityObjective("Hit all %s targets before they touch the ground", 10, TARGET_CHECK)).addRequirement(this.objective02);
/*     */   
/*  49 */   private List<SniperTargetEntity> targets = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public SniperTrial04Quest(QuestId id) {
/*  53 */     super(id);
/*     */     
/*  55 */     this.objective03.setHasEvent(true);
/*  56 */     this.objective03.onStartEvent = this::spawnTargets;
/*  57 */     this.objective03.onRestartEvent = this::respawnTargets;
/*     */     
/*  59 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*  60 */     this.onCompleteEvent = this::giveReward;
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnTargets(PlayerEntity player) {
/*  65 */     for (int i = 0; i < 10; i++) {
/*     */       
/*  67 */       SniperTargetEntity target = new SniperTargetEntity(player.field_70170_p);
/*  68 */       double posX = player.func_226277_ct_() + WyHelper.randomWithRange(-10, 10);
/*  69 */       double posY = player.func_226278_cu_() + 30.0D;
/*  70 */       double posZ = player.func_226281_cx_() + WyHelper.randomWithRange(-10, 10);
/*  71 */       target.func_70012_b(posX, posY, posZ, 0.0F, 0.0F);
/*     */       
/*  73 */       player.field_70170_p.func_217376_c((Entity)target);
/*  74 */       this.targets.add(target);
/*     */       
/*  76 */       if (target.func_70089_S()) {
/*  77 */         WyNetwork.sendToAll(new SDespawnQuestObjectivePacket(player.func_110124_au(), target.func_145782_y()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean respawnTargets(PlayerEntity player) {
/*  83 */     if (this.targets.size() <= 0) {
/*  84 */       return true;
/*     */     }
/*  86 */     for (SniperTargetEntity target : this.targets)
/*     */     {
/*  88 */       target.func_70106_y();
/*     */     }
/*     */     
/*  91 */     this.targets.clear();
/*  92 */     this.objective03.setProgress(player, 0.0D, true);
/*     */     
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean giveReward(PlayerEntity player) {
/*  99 */     if (!removeQuestItem(player, Items.field_221774_cw, 50)) {
/* 100 */       return false;
/*     */     }
/* 102 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 104 */     props.addUnlockedAbility(SakuretsuSabotenBoshiAbility.INSTANCE, AbilityUnlock.PROGRESSION);
/*     */     
/* 106 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\sniper\SniperTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */