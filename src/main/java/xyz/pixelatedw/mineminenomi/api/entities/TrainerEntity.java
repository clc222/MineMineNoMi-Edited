/*     */ package xyz.pixelatedw.mineminenomi.api.entities;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.JumpOutOfHoleGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RandomWalkingAroundHomeGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SelfHealEatGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestChooseScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public abstract class TrainerEntity extends OPEntity implements ITrainer {
/*     */   static {
/*  36 */     BELOW_90_CHECK = ((entity, ability) -> {
/*     */         float per = entity.func_110143_aJ() / entity.func_110138_aP();
/*     */         return (per > 0.9D) ? AbilityUseResult.fail(null) : AbilityUseResult.success();
/*     */       });
/*     */   }
/*     */ 
/*     */   
/*     */   private static final float SPAWNER_DESPAWN_DISTANCE = 40000.0F;
/*     */   public static final Ability.ICanUseEvent BELOW_90_CHECK;
/*     */   
/*     */   public TrainerEntity(EntityType type, World world) {
/*  47 */     this(type, world, (ResourceLocation[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TrainerEntity(EntityType type, World world, ResourceLocation[] textures) {
/*  52 */     super(type, world, textures);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_184651_r() {
/*  57 */     super.func_184651_r();
/*     */     
/*  59 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*  60 */     this.field_70714_bg.func_75776_a(1, (Goal)new SwimGoal((MobEntity)this));
/*  61 */     this.field_70714_bg.func_75776_a(3, (Goal)new RandomWalkingAroundHomeGoal((CreatureEntity)this, 0.8D));
/*  62 */     this.field_70714_bg.func_75776_a(4, (Goal)new SelfHealEatGoal((MobEntity)this));
/*  63 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  64 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  66 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  67 */     props.setFaction(ModValues.CIVILIAN);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/*  72 */     if (isSpawnedViaSpawner() && distance < 40000.0D) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     if (func_145818_k_() && !CommonConfig.INSTANCE.getDespawnWithNametag()) {
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  85 */     if (!this.field_70170_p.field_72995_K && 
/*  86 */       this.field_70170_p.func_175659_aa() == Difficulty.PEACEFUL && !WyHelper.isInChallengeDimension(this.field_70170_p)) {
/*  87 */       func_70624_b(null);
/*  88 */       func_70604_c(null);
/*  89 */       func_130011_c(null);
/*     */     } 
/*     */ 
/*     */     
/*  93 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/*  99 */     if (hand != Hand.MAIN_HAND) {
/* 100 */       return ActionResultType.FAIL;
/*     */     }
/* 102 */     ItemStack stack = player.func_184586_b(hand);
/* 103 */     if (!stack.func_190926_b() && stack.func_77973_b() == Items.field_151057_cb) {
/* 104 */       return ActionResultType.FAIL;
/*     */     }
/* 106 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 108 */       WyNetwork.sendTo(new SOpenQuestChooseScreenPacket(func_145782_y(), WyHelper.isInCombat((LivingEntity)player)), player);
/* 109 */       if (this instanceof xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer)
/*     */       {
/* 111 */         WyNetwork.sendTo(new SSyncHakiDataPacket(player.func_145782_y(), HakiDataCapability.get((LivingEntity)player)), player);
/*     */       }
/* 113 */       return ActionResultType.PASS;
/*     */     } 
/*     */     
/* 116 */     return ActionResultType.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\entities\TrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */