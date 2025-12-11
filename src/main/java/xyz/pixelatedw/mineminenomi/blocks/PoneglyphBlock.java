/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.AbstractBlock;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.state.IntegerProperty;
/*     */ import net.minecraft.state.Property;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeDifficulty;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.PoneglyphTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PoneglyphBlock extends Block {
/*  42 */   public static final IntegerProperty TEXTURE = IntegerProperty.func_177719_a("texture", 0, 2);
/*     */ 
/*     */   
/*     */   public PoneglyphBlock() {
/*  46 */     super(AbstractBlock.Properties.func_200945_a(Material.field_175972_I).func_200943_b(Float.MAX_VALUE).func_222380_e());
/*  47 */     func_180632_j((BlockState)((BlockState)this.field_176227_L.func_177621_b()).func_206870_a((Property)TEXTURE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_200123_i(BlockState state, IBlockReader reader, BlockPos pos) {
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initPoneglyph(IWorld world, BlockPos pos) {
/*  59 */     ChallengeCore<?> challenge = null;
/*  60 */     for (BlockPos checkPos : WyHelper.getNearbyBlocks(pos, world, 2)) {
/*  61 */       TileEntity te = world.func_175625_s(checkPos);
/*  62 */       if (checkPos.equals(pos)) {
/*     */         continue;
/*     */       }
/*     */       
/*  66 */       if (te instanceof PoneglyphTileEntity) {
/*  67 */         challenge = ((PoneglyphTileEntity)te).getChallenge();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  72 */     if (challenge == null) {
/*  73 */       List<ChallengeCore<?>> list = (List<ChallengeCore<?>>)ModRegistries.CHALLENGES.getValues().stream().filter(core -> (core.getDifficulty() == ChallengeDifficulty.STANDARD)).collect(Collectors.toList());
/*  74 */       Collections.shuffle(list);
/*  75 */       ChallengeCore<?> possible = list.get(0);
/*  76 */       challenge = (possible != null) ? possible : (ChallengeCore)ModChallenges.MORGAN.get();
/*     */     } 
/*     */     
/*  79 */     PoneglyphTileEntity tileEntity = (PoneglyphTileEntity)world.func_175625_s(pos);
/*  80 */     tileEntity.setChallenge(challenge);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState func_196258_a(BlockItemUseContext context) {
/*  86 */     BlockState blockstate = func_176223_P();
/*  87 */     blockstate = (BlockState)blockstate.func_206870_a((Property)TEXTURE, Integer.valueOf(context.func_195991_k().func_201674_k().nextInt(3)));
/*     */     
/*  89 */     return blockstate;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
/*  94 */     PoneglyphTileEntity tileEntity = (PoneglyphTileEntity)world.func_175625_s(pos);
/*     */     
/*  96 */     if (WyHelper.isInChallengeDimension(world)) {
/*  97 */       return ActionResultType.PASS;
/*     */     }
/*     */     
/* 100 */     if (world.field_72995_K) {
/* 101 */       return ActionResultType.SUCCESS;
/*     */     }
/*     */     
/* 104 */     if (tileEntity.getChallenge() == null) {
/* 105 */       initPoneglyph((IWorld)world, pos);
/*     */     }
/*     */     
/* 108 */     if (tileEntity.getEntryType() == PoneglyphTileEntity.Type.CHALLENGE) {
/* 109 */       boolean hasPaper = player.field_71071_by.func_213902_a((Set)ImmutableSet.of(Items.field_151121_aF));
/* 110 */       if (hasPaper) {
/* 111 */         for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/* 112 */           ItemStack stack = player.field_71071_by.func_70301_a(i);
/*     */           
/* 114 */           if (stack != null && !stack.func_190926_b() && stack.func_77973_b() == Items.field_151121_aF && stack.func_196082_o().func_74762_e("type") <= 0) {
/* 115 */             stack.func_190918_g(1);
/* 116 */             IChallengesData props = ChallengesDataCapability.get(player);
/* 117 */             ChallengeCore<?> challenge = tileEntity.getChallenge();
/*     */             
/* 119 */             if (props.hasChallenge(challenge)) {
/* 120 */               player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_ALREADY_UNLOCKED, new Object[] { challenge.getLocalizedTitle() }), Util.field_240973_b_);
/*     */             } else {
/*     */               
/* 123 */               props.addChallenge(challenge);
/* 124 */               player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_UNLOCKED, new Object[] { challenge.getLocalizedTitle() }), Util.field_240973_b_);
/*     */             } 
/*     */             
/* 127 */             return ActionResultType.SUCCESS;
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 132 */         player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION_NO_PAPER), Util.field_240973_b_);
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     return ActionResultType.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 142 */     if (world instanceof World && WyHelper.isInChallengeDimension((World)world)) {
/* 143 */       return null;
/*     */     }
/* 145 */     return (TileEntity)new PoneglyphTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_206840_a(StateContainer.Builder<Block, BlockState> builder) {
/* 157 */     builder.func_206894_a(new Property[] { (Property)TEXTURE });
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\blocks\PoneglyphBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */