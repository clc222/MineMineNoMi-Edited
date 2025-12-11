/*    */ package xyz.pixelatedw.mineminenomi.abilities.hiso;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.passive.AnimalEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class LookoutAbility extends Ability {
/* 33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "animal_lookout", new Pair[] {
/* 34 */         (Pair)ImmutablePair.of("Allows the user to communicate with nearby animals and learn if other players passed near them.", null)
/*    */       });
/* 36 */   private static final TargetsPredicate TARGETS_PREDICATE = (new TargetsPredicate()).selector(AnimalEntity.class::isInstance);
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   
/*    */   private static final int RANGE = 10;
/* 41 */   public static final AbilityCore<LookoutAbility> INSTANCE = (new AbilityCore.Builder("Animal Lookout", AbilityCategory.DEVIL_FRUITS, LookoutAbility::new))
/* 42 */     .addDescriptionLine(DESCRIPTION)
/* 43 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE)
/* 44 */       }).build();
/*    */   
/* 46 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public LookoutAbility(AbilityCore<LookoutAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent });
/*    */     
/* 54 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 58 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F, TARGETS_PREDICATE);
/*    */     
/* 60 */     for (LivingEntity target : targets) {
/* 61 */       if (target.func_184582_a(EquipmentSlotType.CHEST).func_77973_b() == Items.field_221585_m) {
/* 62 */         CompoundNBT tag = target.func_184582_a(EquipmentSlotType.CHEST).func_77978_p();
/* 63 */         ListNBT seen = (ListNBT)tag.func_74781_a("seen");
/* 64 */         entity.func_145747_a((ITextComponent)new StringTextComponent("Recently:"), Util.field_240973_b_);
/*    */         
/* 66 */         seen.forEach(string -> {
/*    */               String[] split = string.func_150285_a_().split(" ");
/*    */               
/*    */               entity.func_145747_a((ITextComponent)new StringTextComponent(split[0] + " was around here " + (entity.field_70170_p.func_82737_E() - Long.parseLong(split[1])) + " ticks ago"), Util.field_240973_b_);
/*    */             });
/*    */       } 
/*    */     } 
/* 73 */     if (targets.size() > 0) {
/* 74 */       LivingEntity target = targets.get(0);
/* 75 */       target.func_195064_c(new EffectInstance((Effect)ModEffects.ANIMAL_LOOKOUT.get(), 9999));
/* 76 */       if (entity instanceof ServerPlayerEntity) {
/* 77 */         ((ServerPlayerEntity)entity).field_71135_a.func_147359_a((IPacket)new SPlayEntityEffectPacket(target.func_145782_y(), target.func_70660_b((Effect)ModEffects.ANIMAL_LOOKOUT.get())));
/*    */       }
/*    */     } 
/*    */     
/* 81 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hiso\LookoutAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */