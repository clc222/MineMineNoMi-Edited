/*    */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.SanbyakurokujuPoundHoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class SanbyakurokujuPoundHoAbility extends Ability {
/* 34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sanbyakurokuju_pound_ho", new Pair[] {
/* 35 */         (Pair)ImmutablePair.of("The user launches a powerful ranged slash, causing great destruction", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 320.0F;
/*    */   private static final float DAMAGE = 30.0F;
/*    */   private static final int ANIMATION_TICKS = 7;
/* 41 */   public static final AbilityCore<SanbyakurokujuPoundHoAbility> INSTANCE = (new AbilityCore.Builder("Sanbyakurokuju Pound Ho", AbilityCategory.STYLE, SanbyakurokujuPoundHoAbility::new))
/* 42 */     .addDescriptionLine(DESCRIPTION)
/* 43 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(320.0F)
/* 44 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 45 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 46 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 47 */       }).setSourceElement(SourceElement.AIR)
/* 48 */     .setUnlockCheck(SanbyakurokujuPoundHoAbility::canUnlock)
/* 49 */     .build();
/*    */   
/* 51 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/* 52 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public SanbyakurokujuPoundHoAbility(AbilityCore<SanbyakurokujuPoundHoAbility> core) {
/* 55 */     super(core);
/*    */     
/* 57 */     this.isNew = true;
/* 58 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 60 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/* 61 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 65 */     ItemStack stack = entity.func_184614_ca();
/* 66 */     stack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/* 67 */     this.animationComponent.start(entity, ModAnimations.CANNON_SLASH, 7);
/*    */     
/* 69 */     this.projectileComponent.shoot(entity);
/* 70 */     if (!entity.field_70170_p.field_72995_K) {
/* 71 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*    */     }
/*    */     
/* 74 */     this.cooldownComponent.startCooldown(entity, 320.0F);
/*    */   }
/*    */   
/*    */   private SanbyakurokujuPoundHoProjectile createProjectile(LivingEntity entity) {
/* 78 */     SanbyakurokujuPoundHoProjectile proj = new SanbyakurokujuPoundHoProjectile(entity.field_70170_p, entity, this);
/* 79 */     proj.setDamage(30.0F);
/* 80 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 84 */     if (!(entity instanceof PlayerEntity)) {
/* 85 */       return false;
/*    */     }
/*    */     
/* 88 */     PlayerEntity player = (PlayerEntity)entity;
/* 89 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 90 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 92 */     return (props.isSwordsman() && questProps.hasFinishedQuest(ModQuests.SWORDSMAN_TRIAL_03));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\SanbyakurokujuPoundHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */