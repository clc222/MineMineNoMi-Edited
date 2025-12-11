/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.loot.ConstantRange;
/*    */ import net.minecraft.loot.IRandomRange;
/*    */ import net.minecraft.loot.LootContext;
/*    */ import net.minecraft.loot.LootFunction;
/*    */ import net.minecraft.loot.LootFunctionType;
/*    */ import net.minecraft.loot.LootParameters;
/*    */ import net.minecraft.loot.conditions.ILootCondition;
/*    */ import net.minecraft.loot.functions.ILootFunction;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class IncreaseBellyFunction extends IncreaseStatFunction {
/*    */   protected IncreaseBellyFunction(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/* 31 */     super(conditions, amount, source);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack func_215859_a(ItemStack stack, LootContext context) {
/* 36 */     Entity entity = (Entity)context.func_216031_c(LootParameters.field_216281_a);
/* 37 */     if (entity != null && entity instanceof LivingEntity) {
/* 38 */       LivingEntity target = (LivingEntity)entity;
/* 39 */       Entity killer = (Entity)context.func_216031_c(LootParameters.field_216284_d);
/*    */       
/* 41 */       int amount = this.amount.func_186511_a(context.func_216032_b());
/*    */       
/* 43 */       if (killer != null && killer instanceof LivingEntity) {
/* 44 */         target = (LivingEntity)killer;
/*    */         
/* 46 */         if (this.source == StatChangeSource.KILL_NPC) {
/* 47 */           if (CommonConfig.INSTANCE.isMobRewardsEnabled()) {
/* 48 */             amount = (int)(amount * CommonConfig.INSTANCE.getBellyRewardMultiplier());
/*    */           } else {
/*    */             
/* 51 */             amount = 0;
/*    */           } 
/*    */         }
/*    */       } 
/*    */       
/* 56 */       if (this.source == StatChangeSource.CHALLENGE && entity instanceof PlayerEntity && this.scaleDownChallengeCompletion) {
/* 57 */         ChallengeCore<?> core = (ChallengeCore)context.func_216031_c(ModLootTypes.COMPLETED_CHALLENGE);
/* 58 */         IChallengesData challengeData = ChallengesDataCapability.get((PlayerEntity)entity);
/* 59 */         Challenge challenge = challengeData.getChallenge(core);
/* 60 */         amount = scaleValueFromCompletions(challenge, amount);
/*    */       } 
/*    */       
/* 63 */       IEntityStats props = EntityStatsCapability.get(target);
/*    */       
/* 65 */       boolean hasUpdate = props.alterBelly(amount, this.source);
/*    */       
/* 67 */       if (target instanceof net.minecraft.entity.player.ServerPlayerEntity && hasUpdate) {
/* 68 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), props), (PlayerEntity)target);
/*    */       }
/*    */       
/* 71 */       stack.func_200302_a((ITextComponent)new StringTextComponent("_rewards"));
/* 72 */       stack.func_196082_o().func_74768_a("_rewardsBelly", amount);
/*    */     } 
/* 74 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 79 */     return ModLootTypes.INCREASE_BELLY;
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(int amount, StatChangeSource source) {
/* 83 */     return builder((IRandomRange)ConstantRange.func_215835_a(amount), source);
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source) {
/* 87 */     return builder(amount, source, false);
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source, boolean scaleDown) {
/* 91 */     return func_215860_a(condition -> {
/*    */           IncreaseBellyFunction func = new IncreaseBellyFunction(condition, amount, source);
/*    */           func.scaleDownChallengeCompletion = scaleDown;
/*    */           return (ILootFunction)func;
/*    */         });
/*    */   }
/*    */   
/*    */   public static IncreaseBellyFunction create(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/* 99 */     return new IncreaseBellyFunction(conditions, amount, source);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\IncreaseBellyFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */