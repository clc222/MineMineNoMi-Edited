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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.FreshFireProjectile;
/*    */ 
/*    */ public class KajiOyajiAbility extends RepeaterAbility2 {
/* 19 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kaji_oyaji", new Pair[] {
/* 20 */         (Pair)ImmutablePair.of("Breathes fire.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/*    */   private static final int TRIGGERS = 12;
/*    */   private static final int INTERVAL = 1;
/* 26 */   public static final AbilityCore<KajiOyajiAbility> INSTANCE = (new AbilityCore.Builder("Kaji Oyaji", AbilityCategory.STYLE, KajiOyajiAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 29 */       }).setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 30 */     .setSourceElement(SourceElement.FIRE)
/* 31 */     .build();
/*    */ 
/*    */   
/*    */   public KajiOyajiAbility(AbilityCore<KajiOyajiAbility> core) {
/* 35 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxTriggers() {
/* 40 */     return 12;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTriggerInterval() {
/* 45 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getRepeaterCooldown() {
/* 50 */     return 160.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public FreshFireProjectile getProjectileFactory(LivingEntity entity) {
/* 55 */     FreshFireProjectile proj = new FreshFireProjectile(entity.field_70170_p, entity, (Ability)this);
/* 56 */     proj.setMaxLife(2);
/* 57 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\carnivaltricks\KajiOyajiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */