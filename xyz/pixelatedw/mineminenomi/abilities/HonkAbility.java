/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class HonkAbility extends Ability {
/* 16 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "honk", new Pair[] {
/* 17 */         (Pair)ImmutablePair.of("§8§ohonk honk§r", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 10.0F;
/* 21 */   public static final AbilityCore<HonkAbility> INSTANCE = (new AbilityCore.Builder("Honk", AbilityCategory.STYLE, HonkAbility::new))
/* 22 */     .addDescriptionLine(DESCRIPTION)
/* 23 */     .build();
/*    */   
/*    */   public HonkAbility(AbilityCore<HonkAbility> core) {
/* 26 */     super(core);
/*    */     
/* 28 */     this.isNew = true;
/*    */     
/* 30 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 34 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.HONK.get(), SoundCategory.PLAYERS, 1.0F, 0.9F + this.random.nextFloat() / 4.0F);
/* 35 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\HonkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */