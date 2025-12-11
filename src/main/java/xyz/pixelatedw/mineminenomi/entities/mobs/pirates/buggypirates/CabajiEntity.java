/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.common.ForgeMod;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IPhasesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.NPCPhase;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.DeadzoneRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.IRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.PhysicalDamageDealtRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.ProjectileHitRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.RevengeMeter;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.revenge.TargetRunningAwayRevengeCheck;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.DashDodgeTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.JumpOverProjectilesGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.carnivaltricks.IchirinZashiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.carnivaltricks.KajiOyajiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.carnivaltricks.KamikazeHyakkomaGekijoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiInternalDestructionWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.rokushiki.GeppoWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.HiryuKaenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.OTatsumakiWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.phases.SimplePhase;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.DyeableModSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CabajiEntity extends OPBossEntity<CabajiEntity> {
/*  53 */   private NPCPhase<CabajiEntity> normalPhase = (NPCPhase<CabajiEntity>)new SimplePhase("Normal Phase", (MobEntity)this); private static final byte NO_UNICYCLE_EVENT = 120;
/*  54 */   private NPCPhase<CabajiEntity> noUnicyclePhase = (NPCPhase<CabajiEntity>)new SimplePhase("No Unicycle Phase", (MobEntity)this);
/*     */   
/*  56 */   private RevengeMeter damageTakenMeter = new RevengeMeter((LivingEntity)this, 200, 0);
/*     */   
/*     */   private boolean hasUnicycle = true;
/*     */   
/*     */   public CabajiEntity(EntityType type, World world) {
/*  61 */     super(type, world);
/*     */   }
/*     */   
/*     */   public CabajiEntity(InProgressChallenge challenge) {
/*  65 */     super((EntityType)ModEntities.CABAJI.get(), challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBoss() {
/*  70 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */     
/*  72 */     this.entityStats.setFaction(ModValues.PIRATE);
/*  73 */     this.entityStats.setRace(ModValues.HUMAN);
/*  74 */     this.entityStats.setFightingStyle(ModValues.SWORDSMAN);
/*     */     
/*  76 */     worldData.addTemporaryCrewMember(ModNPCGroups.BUGGY_PIRATES, (LivingEntity)this);
/*     */     
/*  78 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(2.0D);
/*  79 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*  80 */     func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(2.5D);
/*     */     
/*  82 */     func_184201_a(EquipmentSlotType.HEAD, ((Item)ModArmors.CABAJI_SCARF.get()).func_190903_i());
/*  83 */     func_184201_a(EquipmentSlotType.MAINHAND, ((DyeableModSwordItem)ModWeapons.CUTLASS.get()).func_190903_i());
/*     */     
/*  85 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation((LivingEntity)this, ModAnimations.RIDE_UNICYCLE, -1, true), (Entity)this);
/*     */ 
/*     */     
/*  88 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*     */     
/*  90 */     this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*  91 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.3D, true));
/*  92 */     this.field_70714_bg.func_75776_a(1, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*  93 */     this.field_70714_bg.func_75776_a(2, (Goal)new KajiOyajiWrapperGoal((MobEntity)this));
/*  94 */     this.field_70714_bg.func_75776_a(2, (Goal)new KamikazeHyakkomaGekijoWrapperGoal((MobEntity)this));
/*     */     
/*  96 */     this.normalPhase.addGoal(3, (Goal)new IchirinZashiWrapperGoal((MobEntity)this));
/*     */     
/*  98 */     getPhaseManager().setPhase(this.normalPhase);
/*     */     
/* 100 */     if (getChallengeInfo().isDifficultyStandard()) {
/* 101 */       this.damageTakenMeter.addCheck((IRevengeCheck)new PhysicalDamageDealtRevengeCheck());
/* 102 */       this.damageTakenMeter.addCheck((IRevengeCheck)new ProjectileHitRevengeCheck(10));
/* 103 */       this.damageTakenMeter.addCheck((IRevengeCheck)new DeadzoneRevengeCheck(10));
/* 104 */       this.damageTakenMeter.addCheck((IRevengeCheck)new TargetRunningAwayRevengeCheck(10, 10.0F));
/*     */       
/* 106 */       this.entityStats.setDoriki(1000.0D);
/* 107 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(40.0D);
/*     */       
/* 109 */       this.normalPhase.addGoal(0, (Goal)new JumpOverProjectilesGoal((MobEntity)this, 150.0F, 1.5F));
/*     */       
/* 111 */       this.noUnicyclePhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 250.0F, 1.5F));
/* 112 */       this.noUnicyclePhase.addGoal(2, (Goal)new ShiShishiSonsonWrapperGoal((MobEntity)this));
/*     */     } else {
/*     */       
/* 115 */       this.damageTakenMeter.addCheck((IRevengeCheck)new PhysicalDamageDealtRevengeCheck(1.25F));
/* 116 */       this.damageTakenMeter.addCheck((IRevengeCheck)new ProjectileHitRevengeCheck(25));
/* 117 */       this.damageTakenMeter.addCheck((IRevengeCheck)new DeadzoneRevengeCheck(25));
/* 118 */       this.damageTakenMeter.addCheck((IRevengeCheck)new TargetRunningAwayRevengeCheck(25, 10.0F));
/*     */       
/* 120 */       this.entityStats.setDoriki(10000.0D);
/* 121 */       this.hakiCapability.setBusoshokuHakiExp(100.0F);
/* 122 */       this.hakiCapability.setKenbunshokuHakiExp(100.0F);
/* 123 */       func_110148_a((Attribute)ModAttributes.GCD.get()).func_111128_a(20.0D);
/* 124 */       func_110148_a((Attribute)ModAttributes.PUNCH_DAMAGE.get()).func_111128_a(6.0D);
/* 125 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(12.0D);
/* 126 */       func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(4.0D);
/* 127 */       func_110148_a(Attributes.field_233826_i_).func_111128_a(16.0D);
/* 128 */       func_110148_a(Attributes.field_233827_j_).func_111128_a(8.0D);
/* 129 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(250.0D);
/* 130 */       func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111128_a(3.0D);
/* 131 */       func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get()).func_111128_a(16.0D);
/*     */       
/* 133 */       func_184201_a(EquipmentSlotType.OFFHAND, ((DyeableModSwordItem)ModWeapons.CUTLASS.get()).func_190903_i());
/*     */       
/* 135 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiInternalDestructionWrapperGoal((MobEntity)this));
/* 136 */       this.field_70714_bg.func_75776_a(0, (Goal)new BusoshokuHakiImbuingWrapperGoal((MobEntity)this));
/*     */       
/* 138 */       this.field_70714_bg.func_75776_a(2, (Goal)new YakkodoriWrapperGoal((MobEntity)this));
/* 139 */       this.field_70714_bg.func_75776_a(2, (Goal)new HiryuKaenWrapperGoal((MobEntity)this));
/*     */       
/* 141 */       this.normalPhase.addGoal(0, (Goal)new JumpOverProjectilesGoal((MobEntity)this, 70.0F, 1.5F));
/*     */       
/* 143 */       this.noUnicyclePhase.addGoal(0, (Goal)new DashDodgeTargetGoal((MobEntity)this, 200.0F, 3.0F));
/* 144 */       this.noUnicyclePhase.addGoal(0, (Goal)new DashDodgeProjectilesGoal((MobEntity)this, 200.0F, 1.25F));
/* 145 */       this.noUnicyclePhase.addGoal(0, (Goal)new GeppoWrapperGoal((MobEntity)this));
/* 146 */       this.noUnicyclePhase.addGoal(2, (Goal)new ShiShishiSonsonWrapperGoal((MobEntity)this));
/* 147 */       this.noUnicyclePhase.addGoal(2, (Goal)new OTatsumakiWrapperGoal((MobEntity)this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 152 */     return OPEntity.createAttributes()
/* 153 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 154 */       .func_233815_a_(Attributes.field_233821_d_, 0.3100000023841858D)
/* 155 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 156 */       .func_233815_a_(Attributes.field_233818_a_, 150.0D)
/* 157 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 162 */     super.func_70071_h_();
/* 163 */     this.damageTakenMeter.tick();
/* 164 */     if (!this.field_70170_p.field_72995_K && 
/* 165 */       isDifficultyHardOrAbove() && this.normalPhase.isActive((IPhasesEntity)this) && this.damageTakenMeter.isRevengeMaxed()) {
/* 166 */       getPhaseManager().setPhase(this.noUnicyclePhase);
/* 167 */       this.field_70170_p.func_72960_a((Entity)this, (byte)120);
/* 168 */       WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.stopAnimation((LivingEntity)this, ModAnimations.RIDE_UNICYCLE), (Entity)this);
/* 169 */       this.hasUnicycle = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 177 */     switch (id) {
/*     */       case 120:
/* 179 */         this.hasUnicycle = false;
/*     */         break;
/*     */     } 
/* 182 */     super.func_70103_a(id);
/*     */   }
/*     */   
/*     */   public boolean hasUnicycle() {
/* 186 */     return this.hasUnicycle;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\buggypirates\CabajiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */