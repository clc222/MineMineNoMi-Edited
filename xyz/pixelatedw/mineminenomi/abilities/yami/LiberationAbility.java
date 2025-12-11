/*     */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.LiberationProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class LiberationAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "liberation", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("The user sucks up any Darkness they've placed.", null), 
/*  37 */         (Pair)ImmutablePair.of("The user releases all the Darkness stored at the location they're looking at.", null)
/*     */       });
/*  39 */   private static final TranslationTextComponent LIBERATION_ABSORPB_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.liberation", "Liberation"));
/*  40 */   private static final TranslationTextComponent LIBERATION_RELEASE_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.liberation_release", "Liberation: Release"));
/*     */   
/*  42 */   private static final ResourceLocation LIBERATION_ABSORB_ICON = new ResourceLocation("mineminenomi", "textures/abilities/liberation.png");
/*  43 */   private static final ResourceLocation LIBERATION_RELEASE_ICON = new ResourceLocation("mineminenomi", "textures/abilities/liberation_release.png");
/*     */   
/*     */   private static final float COOLDOWN = 80.0F;
/*     */   
/*  47 */   public static final AbilityCore<LiberationAbility> INSTANCE = (new AbilityCore.Builder("Liberation", AbilityCategory.DEVIL_FRUITS, LiberationAbility::new))
/*  48 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CooldownComponent.getTooltip(80.0F)
/*  49 */       }).addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */         
/*     */         AbilityDescriptionLine.NEW_LINE, (e, a) -> LIBERATION_ABSORPB_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[0]
/*  52 */       }).addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */         
/*     */         AbilityDescriptionLine.NEW_LINE, (e, a) -> LIBERATION_RELEASE_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), (e, a) -> DESCRIPTION[1]
/*  55 */       }).build();
/*     */   
/*  57 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.ABSORB)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/*     */   private static final int BATCH_SIZE = 256;
/*     */   
/*     */   public LiberationAbility(AbilityCore<LiberationAbility> core) {
/*  62 */     super(core);
/*     */     
/*  64 */     this.isNew = true;
/*     */     
/*  66 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent });
/*     */     
/*  68 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  72 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     IAbilityData abilityDataProps = AbilityDataCapability.get(entity);
/*     */     
/*  78 */     AbsorbedBlocksAbility absorbedBlocksAbility = (AbsorbedBlocksAbility)abilityDataProps.getPassiveAbility(AbsorbedBlocksAbility.INSTANCE);
/*     */     
/*  80 */     if (absorbedBlocksAbility == null) {
/*     */       return;
/*     */     }
/*     */     
/*  84 */     if (this.altModeComponent.getCurrentMode() == Mode.ABSORB) {
/*  85 */       List<BlockPos> blockPositions = WyHelper.getNearbyBlocks(entity.func_233580_cy_(), (IWorld)entity.field_70170_p, 40, 40, 40, blockState -> (blockState.func_177230_c() == ModBlocks.DARKNESS.get()));
/*     */       
/*  87 */       for (BlockPos blockPos : blockPositions) {
/*  88 */         for (AbsorbedBlocksAbility.BlockData blockData : absorbedBlocksAbility.getAbsorbedBlocks()) {
/*  89 */           if (blockData.getBlockPos().equals(blockPos)) {
/*  90 */             blockData.setCompressed(true);
/*     */           }
/*     */         } 
/*     */         
/*  94 */         entity.field_70170_p.func_175656_a(blockPos, Blocks.field_150350_a.func_176223_P());
/*     */       } 
/*  96 */     } else if (this.altModeComponent.getCurrentMode() == Mode.RELEASE) {
/*  97 */       int batchSize = Math.min(256, absorbedBlocksAbility.getCompressedBlocks().size());
/*     */       
/*  99 */       for (int i = 0; i < batchSize; i++) {
/* 100 */         AbsorbedBlocksAbility.BlockData blockData = absorbedBlocksAbility.getCompressedBlocks().get(this.random.nextInt(absorbedBlocksAbility.getCompressedBlocks().size()));
/*     */         
/* 102 */         LiberationProjectile proj = new LiberationProjectile(entity.field_70170_p, entity, blockData.getBlockState().func_177230_c(), this);
/*     */         
/* 104 */         Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/*     */         
/* 106 */         RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity);
/*     */         
/* 108 */         double x = (mop.func_216347_e()).field_72450_a;
/* 109 */         double y = (mop.func_216347_e()).field_72448_b;
/* 110 */         double z = (mop.func_216347_e()).field_72449_c;
/*     */         
/* 112 */         if (lookVec.field_72448_b > 0.7D) {
/* 113 */           proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 3.0F, 6.0F);
/* 114 */           proj.setMaxLife(300);
/* 115 */           proj.setGravity(0.05F);
/*     */         } else {
/* 117 */           proj.func_70012_b(x + WyHelper.randomWithRange(-3, 3), y + 14.0D + WyHelper.randomWithRange(0, 4), z + WyHelper.randomWithRange(-3, 3), 0.0F, 0.0F);
/* 118 */           AbilityHelper.setDeltaMovement((Entity)proj, 0.0D, -0.7D - entity.field_70170_p.field_73012_v.nextDouble(), 0.0D);
/*     */         } 
/*     */         
/* 121 */         entity.field_70170_p.func_217376_c((Entity)proj);
/*     */         
/* 123 */         absorbedBlocksAbility.removeAbsorbedBlock(blockData);
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     this.cooldownComponent.startCooldown(entity, 80.0F);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 131 */     if (mode == Mode.ABSORB) {
/* 132 */       setDisplayIcon(LIBERATION_ABSORB_ICON);
/* 133 */     } else if (mode == Mode.RELEASE) {
/* 134 */       setDisplayIcon(LIBERATION_RELEASE_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 139 */     ABSORB, RELEASE;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\LiberationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */