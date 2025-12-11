/*     */ package xyz.pixelatedw.mineminenomi.abilities.wara;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
/*     */ 
/*     */ public class StrawManAbility extends PunchAbility2 {
/*  31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "straw_man", new Pair[] {
/*  32 */         (Pair)ImmutablePair.of("Creates a doll that representing the entity you attacked, any damage will get redirected to it.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 600;
/*  36 */   public static final AbilityCore<StrawManAbility> INSTANCE = (new AbilityCore.Builder("Straw Man", AbilityCategory.DEVIL_FRUITS, StrawManAbility::new))
/*  37 */     .addDescriptionLine(DESCRIPTION)
/*  38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(600.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/*  39 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/*  40 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  41 */       }).build();
/*     */   
/*     */   public StrawManAbility(AbilityCore<StrawManAbility> core) {
/*  44 */     super(core);
/*     */     
/*  46 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchDamage() {
/*  51 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/*  56 */     if (canActivate().test(entity) && entity instanceof PlayerEntity) {
/*  57 */       PlayerEntity player = (PlayerEntity)entity;
/*     */       
/*  59 */       source.setBypassFriendlyDamage();
/*     */       
/*  61 */       IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */       
/*  63 */       int dolls = player.field_71071_by.func_213901_a((Item)ModItems.STRAW_DOLL.get());
/*     */       
/*  65 */       if (dolls < 10) {
/*  66 */         if (AbilityHelper.isTargetBlocking((LivingEntity)player, target) || AbilityHelper.isHakiBlocking((LivingEntity)player, target)) {
/*  67 */           return true;
/*     */         }
/*     */         
/*  70 */         if (targetProps.hasStrawDoll()) {
/*  71 */           ItemStack doll = new ItemStack((IItemProvider)ModItems.STRAW_DOLL.get());
/*     */           
/*  73 */           StrawDollItem.setStrawDollOwner(doll, target);
/*  74 */           StrawDollItem.setStrawDollHealth(doll, target.func_110143_aJ());
/*     */           
/*  76 */           TranslationTextComponent dollName = new TranslationTextComponent(ModI18n.ITEM_STRAW_DOLL, new Object[] { target.func_145748_c_().getString() });
/*  77 */           doll.func_200302_a((ITextComponent)dollName);
/*     */           
/*  79 */           player.field_71071_by.func_70441_a(doll);
/*     */           
/*  81 */           targetProps.setStrawDoll(false);
/*     */         } 
/*     */       } 
/*     */       
/*  85 */       this.continuousComponent.stopContinuity((LivingEntity)player);
/*     */     } 
/*     */     
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<LivingEntity> canActivate() {
/*  93 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUseLimit() {
/*  98 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPunchCooldown() {
/* 103 */     return 600.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\wara\StrawManAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */