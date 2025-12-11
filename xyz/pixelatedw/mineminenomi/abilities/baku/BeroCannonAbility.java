/*    */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class BeroCannonAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bero_cannon", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Transforms the user's tongue into a cannon and fires a random block from their inventory.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 40.0F;
/* 35 */   public static final AbilityCore<BeroCannonAbility> INSTANCE = (new AbilityCore.Builder("Bero Cannon", AbilityCategory.DEVIL_FRUITS, BeroCannonAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F)
/* 38 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 39 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 40 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 41 */       }).build();
/*    */   
/* 43 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */ 
/*    */   
/*    */   public BeroCannonAbility(AbilityCore<BeroCannonAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     this.isNew = true;
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 52 */     addCanUseCheck(this::canStartAbility);
/* 53 */     addUseEvent(this::useEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 58 */     List<ItemStack> projectiles = getBlocksInInventory(entity);
/*    */     
/* 60 */     if (!projectiles.isEmpty()) {
/* 61 */       this.projectileComponent.shoot(entity);
/* 62 */       ItemStack stack = projectiles.stream().findFirst().orElse(null);
/* 63 */       if (stack != null && stack.func_190916_E() > 1) {
/* 64 */         stack.func_190918_g(1);
/*    */       }
/*    */     } 
/*    */     
/* 68 */     this.cooldownComponent.startCooldown(entity, 40.0F);
/*    */   }
/*    */   
/*    */   private BeroCannonProjectile createProjectile(LivingEntity entity) {
/* 72 */     BeroCannonProjectile proj = new BeroCannonProjectile(entity.field_70170_p, entity, this);
/* 73 */     return proj;
/*    */   }
/*    */   
/*    */   private List<ItemStack> getBlocksInInventory(LivingEntity entity) {
/* 77 */     List<ItemStack> projectiles = new ArrayList<>();
/* 78 */     for (ItemStack item : ItemsHelper.getInventoryItems(entity)) {
/* 79 */       if (item != null && item.func_77973_b() instanceof BlockItem && !DefaultProtectionRules.CORE_FOLIAGE_ORE.getApprovedBlocks().stream().anyMatch(p -> (p == ((BlockItem)item.func_77973_b()).func_179223_d().getRegistryName()))) {
/* 80 */         projectiles.add(item);
/*    */         break;
/*    */       } 
/*    */     } 
/* 84 */     return projectiles;
/*    */   }
/*    */   
/*    */   private AbilityUseResult canStartAbility(LivingEntity entity, IAbility ability) {
/* 88 */     if (getBlocksInInventory(entity).isEmpty()) {
/* 89 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS);
/*    */     }
/* 91 */     return AbilityUseResult.success();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BeroCannonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */