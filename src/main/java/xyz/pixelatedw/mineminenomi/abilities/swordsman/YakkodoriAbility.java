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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class YakkodoriAbility extends Ability {
/* 34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yakkodori", new Pair[] {
/* 35 */         (Pair)ImmutablePair.of("Launches a crescent moon-shaped slash, which destroys everything in its path", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/*    */   private static final float DAMAGE = 15.0F;
/*    */   private static final int ANIMATION_TICKS = 7;
/* 41 */   public static final AbilityCore<YakkodoriAbility> INSTANCE = (new AbilityCore.Builder("Yakkodori", AbilityCategory.STYLE, YakkodoriAbility::new))
/* 42 */     .addDescriptionLine(DESCRIPTION)
/* 43 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), DealDamageComponent.getTooltip(15.0F)
/* 44 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 45 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 46 */       }).setSourceElement(SourceElement.AIR)
/* 47 */     .setUnlockCheck(YakkodoriAbility::canUnlock)
/* 48 */     .build();
/*    */   
/* 50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public YakkodoriAbility(AbilityCore<YakkodoriAbility> core) {
/* 53 */     super(core);
/*    */     
/* 55 */     this.isNew = true;
/* 56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*    */     
/* 58 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/* 59 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 63 */     ItemStack stack = entity.func_184614_ca();
/* 64 */     stack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*    */     
/* 66 */     this.animationComponent.start(entity, ModAnimations.UPPER_SLASH, 7);
/*    */     
/* 68 */     YakkodoriProjectile proj = new YakkodoriProjectile(entity.field_70170_p, entity);
/* 69 */     entity.field_70170_p.func_217376_c((Entity)proj);
/* 70 */     proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/* 71 */     if (!entity.field_70170_p.field_72995_K) {
/* 72 */       ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*    */     }
/*    */     
/* 75 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 79 */     if (!(entity instanceof PlayerEntity)) {
/* 80 */       return false;
/*    */     }
/*    */     
/* 83 */     PlayerEntity player = (PlayerEntity)entity;
/* 84 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 85 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 87 */     return (props.isSwordsman() && questProps.hasFinishedQuest(ModQuests.SWORDSMAN_TRIAL_02));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\YakkodoriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */