/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
/*    */ 
/*    */ public class KagemushaAbility
/*    */   extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kagemusha", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Allows the user to change its position with that of the Doppelman.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 100.0F;
/* 26 */   public static final AbilityCore<KagemushaAbility> INSTANCE = (new AbilityCore.Builder("Kagemusha", AbilityCategory.DEVIL_FRUITS, KagemushaAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 29 */       }).build();
/*    */   
/*    */   public KagemushaAbility(AbilityCore<KagemushaAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.isNew = true;
/*    */     
/* 36 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 40 */     IAbilityData props = AbilityDataCapability.get(entity);
/* 41 */     DoppelmanAbility doppelmanAbility = (DoppelmanAbility)props.getEquippedAbility(DoppelmanAbility.INSTANCE);
/*    */     
/* 43 */     if (doppelmanAbility != null && doppelmanAbility.isContinuous() && doppelmanAbility.getDoppelman() != null && doppelmanAbility
/* 44 */       .getDoppelman().func_70089_S()) {
/*    */       
/* 46 */       DoppelmanEntity doppelman = doppelmanAbility.getDoppelman();
/* 47 */       BlockPos temp = entity.func_233580_cy_();
/* 48 */       entity.func_184210_p();
/* 49 */       entity.func_223102_j(doppelman.func_226277_ct_(), doppelman.func_226278_cu_(), doppelman.func_226281_cx_());
/* 50 */       doppelman.func_184210_p();
/* 51 */       doppelman.func_223102_j(temp.func_177958_n(), temp.func_177956_o(), temp.func_177952_p());
/*    */     } 
/*    */     
/* 54 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KagemushaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */