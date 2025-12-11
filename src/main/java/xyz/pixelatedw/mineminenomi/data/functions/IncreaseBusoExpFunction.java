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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTypes;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class IncreaseBusoExpFunction extends IncreaseStatFunction {
/*    */   protected IncreaseBusoExpFunction(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
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
/* 46 */         if (this.source == StatChangeSource.KILL_NPC && 
/* 47 */           !CommonConfig.INSTANCE.isMobRewardsEnabled()) {
/* 48 */           amount = 0;
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 53 */       if (this.source == StatChangeSource.CHALLENGE && entity instanceof PlayerEntity && this.scaleDownChallengeCompletion) {
/* 54 */         ChallengeCore<?> core = (ChallengeCore)context.func_216031_c(ModLootTypes.COMPLETED_CHALLENGE);
/* 55 */         IChallengesData challengeData = ChallengesDataCapability.get((PlayerEntity)entity);
/* 56 */         Challenge challenge = challengeData.getChallenge(core);
/* 57 */         amount = scaleValueFromCompletions(challenge, amount);
/*    */       } 
/*    */       
/* 60 */       IHakiData props = HakiDataCapability.get(target);
/*    */       
/* 62 */       boolean hasUpdate = props.alterBusoshokuHakiExp(amount, this.source);
/*    */       
/* 64 */       if (target instanceof net.minecraft.entity.player.ServerPlayerEntity && hasUpdate) {
/* 65 */         WyNetwork.sendTo(new SSyncHakiDataPacket(target.func_145782_y(), props), (PlayerEntity)target);
/*    */       }
/*    */       
/* 68 */       stack.func_200302_a((ITextComponent)new StringTextComponent("_rewards"));
/* 69 */       stack.func_196082_o().func_74768_a("_rewardsBusoExp", amount);
/*    */     } 
/* 71 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootFunctionType func_230425_b_() {
/* 76 */     return ModLootTypes.INCREASE_BUSO_EXP;
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(int amount, StatChangeSource source) {
/* 80 */     return builder((IRandomRange)ConstantRange.func_215835_a(amount), source);
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source) {
/* 84 */     return builder(amount, source, false);
/*    */   }
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange amount, StatChangeSource source, boolean scaleDown) {
/* 88 */     return func_215860_a(condition -> {
/*    */           IncreaseBusoExpFunction func = new IncreaseBusoExpFunction(condition, amount, source);
/*    */           func.scaleDownChallengeCompletion = scaleDown;
/*    */           return (ILootFunction)func;
/*    */         });
/*    */   }
/*    */   
/*    */   public static IncreaseBusoExpFunction create(ILootCondition[] conditions, IRandomRange amount, StatChangeSource source) {
/* 96 */     return new IncreaseBusoExpFunction(conditions, amount, source);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\functions\IncreaseBusoExpFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */