/*    */ package xyz.pixelatedw.mineminenomi.abilities.hiso;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.passive.AnimalEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.GameRules;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.IServerWorldMixin;
/*    */ 
/*    */ 
/*    */ public class ForewarnAbility
/*    */   extends Ability
/*    */ {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "animal_forewarning", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Allows the user to communicate with nearby animals and learn when the next rain or clear weathers will come.", null)
/*    */       });
/* 33 */   private static final TargetsPredicate TARGETS_PREDICATE = (new TargetsPredicate()).selector(AnimalEntity.class::isInstance);
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   
/*    */   private static final int RANGE = 10;
/* 38 */   public static final AbilityCore<ForewarnAbility> INSTANCE = (new AbilityCore.Builder("Animal Forewarning", AbilityCategory.DEVIL_FRUITS, ForewarnAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/* 41 */       }).build();
/*    */   
/* 43 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public ForewarnAbility(AbilityCore<ForewarnAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 55 */     List<LivingEntity> nearby = this.rangeComponent.getTargetsInArea(entity, 10.0F, TARGETS_PREDICATE);
/*    */     
/* 57 */     if (nearby.size() <= 0) {
/* 58 */       this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */       
/*    */       return;
/*    */     } 
/* 62 */     if (nearby.size() > 0 && !entity.field_70170_p.field_72995_K) {
/* 63 */       LivingEntity animal = nearby.stream().findFirst().orElse(null);
/* 64 */       if (animal == null) {
/* 65 */         this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */         
/*    */         return;
/*    */       } 
/* 69 */       String moo = "";
/* 70 */       if (animal instanceof net.minecraft.entity.passive.CowEntity) {
/* 71 */         if (entity instanceof ServerPlayerEntity) {
/* 72 */           ModAdvancements.MOOTEOROLOGIST.trigger((ServerPlayerEntity)entity);
/*    */         }
/* 74 */         moo = "Moooooo!";
/*    */       } 
/*    */       
/* 77 */       int rainWeather = ((IServerWorldMixin)entity.field_70170_p).getServerLevelData().func_76083_p();
/* 78 */       int clearWeather = ((IServerWorldMixin)entity.field_70170_p).getServerLevelData().func_230395_g_();
/* 79 */       boolean weatherStoppedFlag = !entity.func_184102_h().func_200252_aR().func_223586_b(GameRules.field_223617_t);
/*    */       
/* 81 */       if (weatherStoppedFlag) {
/* 82 */         entity.func_145747_a((ITextComponent)new StringTextComponent("The weather won't ever change it seems. " + moo), Util.field_240973_b_);
/*    */         
/*    */         return;
/*    */       } 
/* 86 */       if (clearWeather == 0) {
/* 87 */         entity.func_145747_a((ITextComponent)new StringTextComponent("Next rain will happen in " + (rainWeather / 1200) + " minutes. " + moo), Util.field_240973_b_);
/*    */       }
/* 89 */       else if (rainWeather == 0) {
/* 90 */         entity.func_145747_a((ITextComponent)new StringTextComponent("Next clear weather will happen in " + (clearWeather / 1200) + " minutes. " + moo), Util.field_240973_b_);
/*    */       } 
/*    */     } 
/*    */     
/* 94 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hiso\ForewarnAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */