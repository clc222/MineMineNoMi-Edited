/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.BombEntity;
/*    */ 
/*    */ public class BombThrowAbility extends Ability {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bomb_throw", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Throws a bomb.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 240.0F;
/* 25 */   public static final AbilityCore<BombThrowAbility> INSTANCE = (new AbilityCore.Builder("Bomb Throw", AbilityCategory.STYLE, BombThrowAbility::new))
/* 26 */     .addDescriptionLine(DESCRIPTION)
/* 27 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 28 */       }).setSourceElement(SourceElement.EXPLOSION)
/* 29 */     .build();
/*    */   
/*    */   public BombThrowAbility(AbilityCore<BombThrowAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.isNew = true;
/*    */     
/* 36 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 40 */     BombEntity bomb = new BombEntity(entity.field_70170_p, entity);
/* 41 */     bomb.setExplodeOnImpact();
/* 42 */     Vector3d look = entity.func_70040_Z().func_216372_d(2.0D, 2.0D, 2.0D);
/* 43 */     bomb.func_70012_b(entity.func_213303_ch().func_82615_a() + look.func_82615_a(), entity.func_226280_cw_(), entity.func_213303_ch().func_82616_c() + look.func_82616_c(), entity.func_70681_au().nextInt(360), entity.func_70681_au().nextInt(360));
/* 44 */     AbilityHelper.setDeltaMovement((Entity)bomb, look);
/* 45 */     entity.field_70170_p.func_217376_c((Entity)bomb);
/*    */     
/* 47 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\BombThrowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */