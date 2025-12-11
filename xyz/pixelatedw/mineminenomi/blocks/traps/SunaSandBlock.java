/*    */ package xyz.pixelatedw.mineminenomi.blocks.traps;
/*    */ 
/*    */ import net.minecraft.block.AbstractBlock;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.DesertGirasoleAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public class SunaSandBlock extends TrapAbilityBlock {
/*    */   public SunaSandBlock() {
/* 16 */     super(AbstractBlock.Properties.func_200945_a(Material.field_151595_p).func_200948_a(5.0F, 3.0F).func_222380_e().harvestTool(ToolType.SHOVEL));
/* 17 */     setDamageDealt(4);
/* 18 */     setHorizontalFallSpeed(0.3D);
/* 19 */     setVerticalFallSpeed(0.15D);
/* 20 */     setPotionEffect(() -> new EffectInstance(Effects.field_76419_f, 80, 1, false, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(LivingEntity entity) {
/* 25 */     boolean hasFruit = DevilFruitCapability.get(entity).hasDevilFruit(ModAbilities.SUNA_SUNA_NO_MI);
/* 26 */     boolean hasAbility = AbilityDataCapability.get(entity).hasUnlockedAbility(DesertGirasoleAbility.INSTANCE);
/* 27 */     return (hasFruit || hasAbility);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\traps\SunaSandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */