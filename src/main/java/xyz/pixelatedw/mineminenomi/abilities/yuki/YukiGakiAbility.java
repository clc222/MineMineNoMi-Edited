/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yuki.KamakuraParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class YukiGakiAbility extends WallAbility {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yuki_gaki", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Creates a wall made of hardened snow to protect the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 30 */   public static final AbilityCore<YukiGakiAbility> INSTANCE = (new AbilityCore.Builder("Yuki Gaki", AbilityCategory.DEVIL_FRUITS, YukiGakiAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 33 */       }).build();
/*    */   
/* 35 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).build();
/* 36 */   private static final KamakuraParticleEffect.Details DETAILS = new KamakuraParticleEffect.Details(4);
/*    */ 
/*    */   
/*    */   public YukiGakiAbility(AbilityCore<YukiGakiAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/*    */     
/* 44 */     addUseEvent(this::onUseEvent);
/*    */     
/* 46 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 50 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.KAMAKURA.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), (ParticleEffect.Details)DETAILS);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getThickness() {
/* 59 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 64 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 69 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getWallBlock() {
/* 74 */     return (Block)ModBlocks.HARDENED_SNOW.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockProtectionRule getGriefingRule() {
/* 79 */     return GRIEF_RULE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean stopAfterUse() {
/* 84 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\YukiGakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */