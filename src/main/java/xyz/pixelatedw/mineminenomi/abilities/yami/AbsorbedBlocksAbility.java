/*     */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSyncAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class AbsorbedBlocksAbility
/*     */   extends PassiveAbility2 {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "absorbed_blocks", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Displays the total amount of blocks the user has absorbed.", null)
/*     */       });
/*  38 */   public static final AbilityCore<AbsorbedBlocksAbility> INSTANCE = (new AbilityCore.Builder("Absorbed Blocks", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, AbsorbedBlocksAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .build();
/*     */   
/*  42 */   private List<BlockData> absorbedBlocks = new ArrayList<>();
/*     */   
/*  44 */   private int absorbedBlocksAmount = 0;
/*     */   
/*  46 */   private int previousAbsorbedBlocksAmount = -1;
/*     */   
/*     */   public AbsorbedBlocksAbility(AbilityCore<AbsorbedBlocksAbility> core) {
/*  49 */     super(core);
/*     */     
/*  51 */     if (isClientSide()) {
/*  52 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/*     */       
/*  54 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*     */     } 
/*     */     
/*  57 */     addDuringPassiveEvent(this::onDuringPassive);
/*     */   }
/*     */   
/*     */   public void onDuringPassive(LivingEntity entity) {
/*  61 */     if (!entity.field_70170_p.field_72995_K) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  69 */       this.absorbedBlocksAmount = this.absorbedBlocks.size();
/*     */       
/*  71 */       if (this.absorbedBlocksAmount != this.previousAbsorbedBlocksAmount) {
/*  72 */         WyNetwork.sendTo(new SSyncAbilityPacket(entity.func_145782_y(), (IAbility)this), (PlayerEntity)entity);
/*     */         
/*  74 */         this.previousAbsorbedBlocksAmount = this.absorbedBlocksAmount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, AbsorbedBlocksAbility ability) {
/*  81 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/*  83 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*     */     
/*  85 */     RendererHelper.drawIcon(BlackHoleAbility.INSTANCE.getIcon(), matrixStack, posX, (posY - 38), 0.0F, 32.0F, 32.0F);
/*     */     
/*  87 */     String strAbsorbedBlocksAmount = Integer.toString(ability.absorbedBlocksAmount);
/*     */     
/*  89 */     WyHelper.drawStringWithBorder(mc.field_71466_p, matrixStack, strAbsorbedBlocksAmount, posX + 15 - mc.field_71466_p.func_78256_a(strAbsorbedBlocksAmount) / 2, posY - 25, Color.WHITE.getRGB());
/*     */   }
/*     */   
/*     */   public List<BlockData> getAbsorbedBlocks() {
/*  93 */     return this.absorbedBlocks;
/*     */   }
/*     */   
/*     */   public List<BlockData> getCompressedBlocks() {
/*  97 */     return (List<BlockData>)this.absorbedBlocks.stream()
/*  98 */       .filter(blockData -> blockData.isCompressed())
/*  99 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public List<BlockData> getUncompressedBlocks() {
/* 103 */     return (List<BlockData>)this.absorbedBlocks.stream()
/* 104 */       .filter(blockData -> !blockData.isCompressed())
/* 105 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public void addAbsorbedBlock(BlockState blockState, BlockPos blockPos) {
/* 109 */     this.absorbedBlocks.add(new BlockData(blockState, blockPos));
/*     */   }
/*     */   
/*     */   public void removeAbsorbedBlock(BlockData blockData) {
/* 113 */     this.absorbedBlocks.remove(blockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 118 */     nbt = super.save(nbt);
/*     */     
/* 120 */     nbt.func_74768_a("absorbedBlocksAmount", this.absorbedBlocksAmount);
/*     */     
/* 122 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 127 */     super.load(nbt);
/*     */     
/* 129 */     this.absorbedBlocksAmount = nbt.func_74762_e("absorbedBlocksAmount");
/*     */   }
/*     */ 
/*     */   
/*     */   public static class BlockData
/*     */   {
/*     */     private BlockState blockState;
/*     */     private BlockPos blockPos;
/*     */     private boolean isCompressed = false;
/*     */     
/*     */     public BlockData(BlockState blockState, BlockPos blockPos) {
/* 140 */       this.blockState = blockState;
/* 141 */       this.blockPos = blockPos;
/*     */     }
/*     */     
/*     */     public BlockState getBlockState() {
/* 145 */       return this.blockState;
/*     */     }
/*     */     
/*     */     public BlockPos getBlockPos() {
/* 149 */       return this.blockPos;
/*     */     }
/*     */     
/*     */     public boolean isCompressed() {
/* 153 */       return this.isCompressed;
/*     */     }
/*     */     
/*     */     public void setCompressed(boolean flag) {
/* 157 */       this.isCompressed = flag;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\AbsorbedBlocksAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */