/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DoruDoruNoYakataAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doru_doru_no_yakata", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("The user creates a few wax copies of themselves (Use the Color Palette for color on the clones)", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 800.0F;
/* 26 */   public static final AbilityCore<DoruDoruNoYakataAbility> INSTANCE = (new AbilityCore.Builder("Doru Doru no Yakata", AbilityCategory.DEVIL_FRUITS, DoruDoruNoYakataAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(800.0F)
/* 29 */       }).build();
/*    */   
/*    */   public DoruDoruNoYakataAbility(AbilityCore<DoruDoruNoYakataAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.isNew = true;
/*    */     
/* 36 */     addUseEvent(this::oneUseEvent);
/*    */   }
/*    */   
/*    */   private void oneUseEvent(LivingEntity player, IAbility ability) {
/* 40 */     for (int i = 0; i < 7; i++) {
/* 41 */       int offsetX = (int)WyHelper.randomWithRange(-2, 2);
/* 42 */       int offsetZ = (int)WyHelper.randomWithRange(-2, 2);
/*    */       
/* 44 */       WaxCloneEntity clone = new WaxCloneEntity(player.field_70170_p);
/* 45 */       clone.func_70012_b(player.func_226277_ct_() + offsetX, player.func_226278_cu_(), player.func_226281_cx_() + offsetZ, 180.0F, 0.0F);
/* 46 */       clone.setOwner(player);
/* 47 */       clone.func_70604_c(player);
/* 48 */       if (DoruHelper.hasColorPalette(player)) {
/* 49 */         clone.setUseOwnerTexture();
/* 50 */         for (EquipmentSlotType slot : EquipmentSlotType.values()) {
/* 51 */           ItemStack stack = player.func_184582_a(slot);
/* 52 */           clone.func_184201_a(slot, stack);
/*    */         } 
/*    */       } 
/* 55 */       player.field_70170_p.func_217376_c((Entity)clone);
/*    */     } 
/* 57 */     this.cooldownComponent.startCooldown(player, 800.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruNoYakataAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */