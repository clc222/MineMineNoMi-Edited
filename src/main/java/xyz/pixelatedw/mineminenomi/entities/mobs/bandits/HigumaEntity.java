/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.TargetFlyStallingRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.KamieWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.HiryuKaenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.DyeableModSwordItem;
/*     */ 
/*     */ public class HigumaEntity extends OPBossEntity<HigumaEntity> {
/*     */   public HigumaEntity(EntityType type, World world) {
/*  44 */     super(type, world);
/*     */   }
/*     */   private RevengeMeter revengeMeter;
/*     */   public HigumaEntity(InProgressChallenge challenge) {
/*  48 */     super((EntityType)ModEntities.HIGUMA.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  53 */     this.revengeMeter = new RevengeMeter((LivingEntity)this, 100, 1);
/*     */     
/*  55 */     this.entityStats.setFaction(ModValues.BANDIT);
/*  56 */     this.entityStats.setRace(ModValues.HUMAN);
/*  57 */     this.entityStats.setFightingStyle(ModValues.SWORDSMAN);
/*     */     
/*  59 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  60 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  61 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(2.5D);
/*     */     
/*  63 */     getRevengeMeter().addCheck((IRevengeCheck)new PhysicalHitRevengeCheck(1));
/*  64 */     getRevengeMeter().addCheck((IRevengeCheck)new DeadzoneRevengeCheck(10));
/*  65 */     getRevengeMeter().addCheck((IRevengeCheck)new TargetFlyStallingRevengeCheck(10));
/*     */     
/*  67 */     func_184201_a(EquipmentSlotType.CHEST, new ItemStack((IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE.get()));
/*     */     
/*  69 */     ShiShishiSonsonWrapperGoal shishishiWrapper = new ShiShishiSonsonWrapperGoal((MobEntity)this);
/*     */ 
/*     */     
/*  72 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/*  74 */     this.field_70714_bg.func_75776_a(0, (Goal)new ClimbOutOfHoleGoal((MobEntity)this));
/*  75 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  76 */     this.field_70714_bg.func_75776_a(1, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  77 */     this.field_70714_bg.func_75776_a(2, (Goal)shishishiWrapper);
/*     */     
/*  79 */     if (getChallengeInfo().isDifficultyStandard()) {
/*  80 */       func_184201_a(EquipmentSlotType.MAINHAND, ((DyeableModSwordItem)ModWeapons.CUTLASS.get()).func_190903_i());
/*     */       
/*  82 */       this.entityStats.setDoriki(100.0D);
/*  83 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*     */       
/*  85 */       ((ShiShishiSonsonAbility)shishishiWrapper.getAbility()).addCanUseCheck((entity, ability) -> (this.revengeMeter.getRevengeValue() >= 10) ? AbilityUseResult.success() : AbilityUseResult.fail(null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       ((ShiShishiSonsonAbility)shishishiWrapper.getAbility()).addUseEvent((entity, ability) -> getRevengeMeter().reduceRevengeValue(10));
/*     */ 
/*     */ 
/*     */       
/*  95 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 250.0F, 2.0F));
/*     */     } else {
/*     */       
/*  98 */       this.entityStats.setDoriki(10000.0D);
/*  99 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 100 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 101 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 102 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(6.0D);
/* 103 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(12.0D);
/* 104 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 105 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(16.0D);
/* 106 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 107 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(250.0D);
/* 108 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/* 109 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 111 */       func_184201_a(EquipmentSlotType.MAINHAND, ((DyeableModSwordItem)ModWeapons.KATANA.get()).func_190903_i());
/*     */       
/* 113 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 200.0F, 3.0F));
/* 114 */       this.field_70714_bg.func_75776_a(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 1.25F));
/* 115 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 116 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/* 117 */       this.field_70714_bg.func_75776_a(2, (Goal)new SoruWrapperGoal((MobEntity)this));
/* 118 */       this.field_70714_bg.func_75776_a(2, (Goal)new KamieWrapperGoal((MobEntity)this));
/* 119 */       this.field_70714_bg.func_75776_a(2, (Goal)new GeppoWrapperGoal((MobEntity)this));
/* 120 */       this.field_70714_bg.func_75776_a(2, (Goal)new YakkodoriWrapperGoal((MobEntity)this));
/* 121 */       this.field_70714_bg.func_75776_a(2, (Goal)new OTatsumakiWrapperGoal((MobEntity)this));
/* 122 */       this.field_70714_bg.func_75776_a(2, (Goal)new HiryuKaenWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 127 */     return OPEntity.createAttributes()
/* 128 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 129 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 130 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 131 */       .func_233815_a_(Attributes.field_233818_a_, 100.0D)
/* 132 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 137 */     super.func_70071_h_();
/* 138 */     if (!this.field_70170_p.field_72995_K && func_70089_S()) {
/* 139 */       getRevengeMeter().tick();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public RevengeMeter getRevengeMeter() {
/* 145 */     return this.revengeMeter;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\bandits\HigumaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */