/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ItemAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class DoruDoruArtsPickaxeAbility extends ItemAbility2 {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doru_doru_arts_pickaxe", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("The user uses hardened wax to create a pickaxe", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 10.0F;
/* 31 */   public static final AbilityCore<DoruDoruArtsPickaxeAbility> INSTANCE = (new AbilityCore.Builder("Doru Doru Arts: Pickaxe", AbilityCategory.DEVIL_FRUITS, DoruDoruArtsPickaxeAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip()
/* 34 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 35 */     .setSourceElement(SourceElement.WAX)
/* 36 */     .build();
/*    */   
/*    */   public DoruDoruArtsPickaxeAbility(AbilityCore<DoruDoruArtsPickaxeAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     this.isNew = true;
/*    */     
/* 43 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 47 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack createItemStack(LivingEntity entity) {
/* 52 */     ItemStack itemStack = new ItemStack((IItemProvider)ModWeapons.DORU_PICKAXE.get());
/* 53 */     float red = 1.0F;
/* 54 */     float green = 1.0F;
/* 55 */     float blue = 1.0F;
/* 56 */     if (ItemsHelper.countItemInInventory(entity, (Item)ModItems.COLOR_PALETTE.get()) > 0) {
/* 57 */       red = this.random.nextFloat();
/* 58 */       green = this.random.nextFloat();
/* 59 */       blue = this.random.nextFloat();
/*    */     } 
/* 61 */     itemStack.func_190925_c("display").func_74768_a("color", (new Color(red, green, blue)).getRGB());
/* 62 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruArtsPickaxeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */