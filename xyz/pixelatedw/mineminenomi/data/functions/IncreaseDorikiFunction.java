/*     */ package xyz.pixelatedw.mineminenomi.data.functions;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.loot.ConstantRange;
/*     */ import net.minecraft.loot.IRandomRange;
/*     */ import net.minecraft.loot.LootContext;
/*     */ import net.minecraft.loot.LootFunction;
/*     */ import net.minecraft.loot.LootFunctionType;
/*     */ import net.minecraft.loot.LootParameters;
/*     */ import net.minecraft.loot.conditions.ILootCondition;
/*     */ import net.minecraft.loot.functions.ILootFunction;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class IncreaseDorikiFunction extends IncreaseStatFunction {
/*     */   protected IncreaseDorikiFunction(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/*  32 */     super(conditions, amount, source);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/*  37 */     Entity entity = (Entity)context.func_216031_c(LootParameters.field_216281_a);
/*  38 */     if (entity != null && entity instanceof LivingEntity) {
/*  39 */       LivingEntity target = (LivingEntity)entity;
/*  40 */       Entity killer = (Entity)context.func_216031_c(LootParameters.field_216284_d);
/*     */       
/*  42 */       int amount = this.amount.func_186511_a(context.func_216032_b());
/*     */       
/*  44 */       if (killer != null && killer instanceof LivingEntity) {
/*  45 */         target = (LivingEntity)killer;
/*  46 */         IEntityStats iEntityStats = EntityStatsCapability.get(target);
/*     */         
/*  48 */         if (this.source == StatChangeSource.KILL_NPC) {
/*  49 */           if (CommonConfig.INSTANCE.isMobRewardsEnabled()) {
/*  50 */             double doriki = MathHelper.func_76125_a(amount * 100, 0, CommonConfig.INSTANCE.getDorikiLimit());
/*  51 */             if (iEntityStats.getDoriki() > doriki) {
/*  52 */               amount /= 100;
/*  53 */               if (amount < 1 && CommonConfig.INSTANCE.isMinimumDorikiPerKillEnabled()) {
/*  54 */                 amount = 1;
/*     */               }
/*     */             } 
/*     */             
/*  58 */             amount = (int)(amount * CommonConfig.INSTANCE.getDorikiRewardMultiplier());
/*     */           } else {
/*     */             
/*  61 */             amount = 0;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/*  66 */       if (this.source == StatChangeSource.CHALLENGE && entity instanceof PlayerEntity && this.scaleDownChallengeCompletion) {
/*  67 */         ChallengeCore<?> core = (ChallengeCore)context.func_216031_c(ModLootTypes.COMPLETED_CHALLENGE);
/*  68 */         IChallengesData challengeData = ChallengesDataCapability.get((PlayerEntity)entity);
/*  69 */         Challenge challenge = challengeData.getChallenge(core);
/*  70 */         amount = scaleValueFromCompletions(challenge, amount);
/*     */       } 
/*     */       
/*  73 */       IEntityStats props = EntityStatsCapability.get(target);
/*     */       
/*  75 */       boolean hasUpdate = props.alterDoriki(amount, this.source);
/*     */       
/*  77 */       if (target instanceof net.minecraft.entity.player.ServerPlayerEntity && hasUpdate) {
/*  78 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), props), (PlayerEntity)target);
/*     */       }
/*     */       
/*  81 */       stack.func_200302_a((ITextComponent)new StringTextComponent("_rewards"));
/*  82 */       stack.func_196082_o().func_74768_a("_rewardsDoriki", amount);
/*     */     } 
/*  84 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootFunctionType func_230425_b_() {
/*  89 */     return ModLootTypes.INCREASE_DORIKI;
/*     */   }
/*     */   
/*     */   public static LootFunction.Builder<?> builder(int amount, StatChangeSource source) {
/*  93 */     return builder((IRandomRange)ConstantRange.func_215835_a(amount), source);
/*     */   }
/*     */   
/*     */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source) {
/*  97 */     return builder(amount, source, false);
/*     */   }
/*     */   
/*     */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source, boolean scaleDown) {
/* 101 */     return func_215860_a(condition -> {
/*     */           IncreaseDorikiFunction func = new IncreaseDorikiFunction(condition, amount, source);
/*     */           func.scaleDownChallengeCompletion = scaleDown;
/*     */           return (ILootFunction)func;
/*     */         });
/*     */   }
/*     */   
/*     */   public static IncreaseDorikiFunction create(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/* 109 */     return new IncreaseDorikiFunction(conditions, amount, source);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\IncreaseDorikiFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */