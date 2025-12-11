/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanaHelper {
/* 17 */   public static final UUID MIL_DAMAGE_BONUS = UUID.fromString("9fab4eda-6b81-4602-90f9-d2373304604f"); public static final AbilityDescriptionLine.IDescriptionLine<?> REQUIRES_MIL_FLEUR = (entity, ability) -> new TranslationTextComponent(ModI18n.ABILITY_DEPENDENCY_SINGLE_ACTIVE, new Object[] { "§a" + MilFleurAbility.INSTANCE.getLocalizedName().getString() + "§r" });
/*    */   
/*    */   public static void spawnBlossomEffect(LivingEntity source) {
/* 20 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.BLOOM.get(), (Entity)source, source.func_226277_ct_(), source.func_226278_cu_(), source.func_226281_cx_());
/* 21 */     source.field_70170_p.func_184133_a(null, source.func_233580_cy_(), (SoundEvent)ModSounds.HANA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\HanaHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */