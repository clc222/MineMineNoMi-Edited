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
/*    */ public class IncreaseLoyaltyFunction extends IncreaseStatFunction {
/*    */   protected IncreaseLoyaltyFunction(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
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
/* 43 */       IEntityStats props = EntityStatsCapability.get(target);
/*    */       
/* 45 */       if (killer != null && killer instanceof LivingEntity) {
/* 46 */         target = (LivingEntity)killer;
/*    */         
/* 48 */         boolean hasLoyalty = (props.isMarine() || props.isRevolutionary() || props.isBountyHunter());
/*    */         
/* 50 */         if (this.source == StatChangeSource.KILL_NPC && (
/* 51 */           !CommonConfig.INSTANCE.isMobRewardsEnabled() || !hasLoyalty)) {
/* 52 */           amount = 0;
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 57 */       if (this.source == StatChangeSource.CHALLENGE && entity instanceof PlayerEntity && this.scaleDownChallengeCompletion) {
/* 58 */         ChallengeCore<?> core = (ChallengeCore)context.func_216031_c(ModLootTypes.COMPLETED_CHALLENGE);
/* 59 */         IChallengesData challengeData = ChallengesDataCapability.get((PlayerEntity)entity);
/* 60 */         Challenge challenge = challengeData.getChallenge(core);
/* 61 */         amount = scaleValueFromCompletions(challenge, amount);
/*    */       } 
/*    */       
/* 64 */       boolean hasUpdate = props.alterLoyalty(amount, this.source);
/*    */       
/* 66 */       if (target instanceof net.minecraft.entity.player.ServerPlayerEntity && hasUpdate) {
/* 67 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(target.func_145782_y(), props), (PlayerEntity)target);
/*    */       }
/*    */       
/* 70 */       stack.func_200302_a((ITextComponent)new StringTextComponent("_rewards"));
/* 71 */       stack.func_196082_o().func_74768_a("_rewardsLoyalty", amount);
/*    */     } 
/* 73 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 78 */     return ModLootTypes.INCREASE_BELLY;
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(int amount, StatChangeSource source) {
/* 82 */     return builder((IRandomRange)ConstantRange.func_215835_a(amount), source);
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source) {
/* 86 */     return builder(amount, source, false);
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source, boolean scaleDown) {
/* 90 */     return func_215860_a(condition -> {
/*    */           IncreaseLoyaltyFunction func = new IncreaseLoyaltyFunction(condition, amount, source);
/*    */           func.scaleDownChallengeCompletion = scaleDown;
/*    */           return (ILootFunction)func;
/*    */         });
/*    */   }
/*    */   
/*    */   public static IncreaseLoyaltyFunction create(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/* 98 */     return new IncreaseLoyaltyFunction(conditions, amount, source);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\IncreaseLoyaltyFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */