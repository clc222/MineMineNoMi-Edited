/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureAttribute;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.SoulboundItemHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MesAbility
/*     */   extends PunchAbility2 {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mes", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Removes the heart of the user's target which they can then damage to hurt the opponent", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 600.0F;
/*  39 */   public static final AbilityCore<MesAbility> INSTANCE = (new AbilityCore.Builder("MES", AbilityCategory.DEVIL_FRUITS, MesAbility::new))
/*  40 */     .addDescriptionLine(DESCRIPTION)
/*  41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F)
/*  42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  43 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  44 */       }).build();
/*     */   
/*     */   public MesAbility(AbilityCore<MesAbility> core) {
/*  47 */     super(core);
/*     */     
/*  49 */     this.continuousComponent.addTickEvent(100, this::onContinuityTick);
/*     */     
/*  51 */     addCanUseCheck(OpeHelper::hasRoomActive);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  55 */     if (!entity.field_70170_p.field_72995_K && OpeHelper.hasRoomActive(entity, (IAbility)this).isFail()) {
/*  56 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/*  62 */     return 600.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  67 */     IDevilFruit props = DevilFruitCapability.get(target);
/*     */     
/*  69 */     if (props.hasDevilFruit(ModAbilities.WARA_WARA_NO_MI)) {
/*  70 */       for (int i = 0; i < ((PlayerEntity)target).field_71071_by.field_70462_a.size(); i++) {
/*  71 */         if (((ItemStack)((PlayerEntity)target).field_71071_by.field_70462_a.get(i)).func_77973_b() == ModItems.STRAW_DOLL.get()) {
/*  72 */           this.continuousComponent.stopContinuity(entity);
/*  73 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  78 */     if (AbilityHelper.isTargetBlocking(entity, target) || AbilityHelper.isHakiBlocking(entity, target)) {
/*  79 */       boolean bypassHakiBlock = (entity.func_110143_aJ() > target.func_110143_aJ() && target.func_110143_aJ() < WyHelper.percentage(15.0D, target.func_110138_aP()));
/*  80 */       if (!bypassHakiBlock) {
/*  81 */         this.continuousComponent.stopContinuity(entity);
/*  82 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */     
/*  88 */     boolean targetNoHeart = (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || target.func_70668_bt() == CreatureAttribute.field_223223_b_);
/*     */     
/*  90 */     if (targetProps.hasHeart() && !targetNoHeart) {
/*  91 */       ItemStack heart = new ItemStack((IItemProvider)ModItems.HEART.get());
/*     */       
/*  93 */       SoulboundItemHelper.setOwner(heart, target);
/*     */       
/*  95 */       heart.func_200302_a((ITextComponent)new StringTextComponent(target.func_145748_c_().getString() + "'s Heart"));
/*     */       
/*  97 */       ((PlayerEntity)entity).field_71071_by.func_70441_a(heart);
/*     */       
/*  99 */       targetProps.setHeart(false);
/*     */     } 
/*     */     
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/* 107 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/* 112 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\MesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */