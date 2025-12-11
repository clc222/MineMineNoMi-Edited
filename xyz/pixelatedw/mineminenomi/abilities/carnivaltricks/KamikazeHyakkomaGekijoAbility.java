/*    */ package xyz.pixelatedw.mineminenomi.abilities.carnivaltricks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.TopEntity;
/*    */ 
/*    */ public class KamikazeHyakkomaGekijoAbility extends RepeaterAbility2 {
/* 18 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kamikaze_hyakkoma_gekijo", new Pair[] {
/* 19 */         (Pair)ImmutablePair.of("The user throws several tops at their opponent, some of them exploding on impact.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/*    */   private static final int TRIGGERS = 10;
/*    */   private static final int INTERVAL = 2;
/* 25 */   public static final AbilityCore<KamikazeHyakkomaGekijoAbility> INSTANCE = (new AbilityCore.Builder("Kamikaze Hyakkoma Gekijo", AbilityCategory.STYLE, KamikazeHyakkomaGekijoAbility::new))
/* 26 */     .addDescriptionLine(DESCRIPTION)
/* 27 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 28 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 29 */     .build();
/*    */   
/*    */   public KamikazeHyakkomaGekijoAbility(AbilityCore<KamikazeHyakkomaGekijoAbility> core) {
/* 32 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxTriggers() {
/* 37 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTriggerInterval() {
/* 42 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getRepeaterCooldown() {
/* 47 */     return 100.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public TopEntity getProjectileFactory(LivingEntity entity) {
/* 52 */     TopEntity proj = new TopEntity(entity.field_70170_p, entity, (Ability)this);
/* 53 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\carnivaltricks\KamikazeHyakkomaGekijoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */