/*     */ package xyz.pixelatedw.mineminenomi.api.protection;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ 
/*     */ public class BlockProtectionRule
/*     */ {
/*     */   private boolean forcePlacement = false;
/*     */   private boolean bypassGriefRule = false;
/*     */   private boolean coordsOnly = false;
/*  21 */   private Set<IReplaceBlockRule> replaceRules = new HashSet<>();
/*     */   
/*  23 */   private Set<ResourceLocation> approvedBlocks = new HashSet<>();
/*  24 */   private Set<Material> approvedMaterials = new HashSet<>();
/*  25 */   private Set<ITag<Block>> approvedBlockTags = new HashSet<>();
/*     */   
/*  27 */   private Set<ResourceLocation> bannedBlocks = new HashSet<>();
/*  28 */   private Set<Material> bannedMaterials = new HashSet<>();
/*  29 */   private Set<ITag<Block>> bannedBlockTags = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<ResourceLocation> getApprovedBlocks() {
/*  38 */     return this.approvedBlocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Material> getApprovedMaterials() {
/*  43 */     return this.approvedMaterials;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ITag<Block>> getApprovedTags() {
/*  48 */     return this.approvedBlockTags;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<ResourceLocation> getBannedBlocks() {
/*  53 */     return this.bannedBlocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<IReplaceBlockRule> getReplaceRules() {
/*  58 */     return this.replaceRules;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addReplaceRules(Set<IReplaceBlockRule> fns) {
/*  63 */     this.replaceRules = fns;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addApprovedBlocks(Set<ResourceLocation> blocks) {
/*  68 */     this.approvedBlocks = blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addApprovedMaterials(Set<Material> mats) {
/*  73 */     this.approvedMaterials = mats;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addApprovedTags(Set<ITag<Block>> tags) {
/*  78 */     this.approvedBlockTags = tags;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addBannedBlocks(Set<ResourceLocation> blocks) {
/*  83 */     this.bannedBlocks = blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBypassGriefRule() {
/*  88 */     return this.bypassGriefRule;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBypassGriefRule() {
/*  93 */     this.bypassGriefRule = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCoordsOnly() {
/*  98 */     return this.coordsOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setCoordsOnly() {
/* 103 */     this.coordsOnly = true;
/*     */   }
/*     */   
/*     */   private void setForced() {
/* 107 */     this.forcePlacement = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean check(World world, BlockPos pos, BlockState state) {
/* 112 */     if (this.forcePlacement) {
/* 113 */       return true;
/*     */     }
/*     */     
/* 116 */     if (isBanned(state)) {
/* 117 */       return false;
/*     */     }
/* 119 */     for (IReplaceBlockRule fn : this.replaceRules) {
/* 120 */       fn.replace(world, pos, state);
/*     */     }
/*     */     
/* 123 */     return isApproved(state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isApproved(BlockState state) {
/* 128 */     if (this.approvedBlocks.contains(state.func_177230_c().getRegistryName())) {
/* 129 */       return true;
/*     */     }
/* 131 */     if (this.approvedBlockTags.stream().anyMatch(tag -> tag.func_230235_a_(state.func_177230_c()))) {
/* 132 */       return true;
/*     */     }
/* 134 */     if (this.approvedMaterials.stream().anyMatch(mat -> (state.func_185904_a() == mat))) {
/* 135 */       return true;
/*     */     }
/* 137 */     return false;
/*     */   } @FunctionalInterface
/*     */   public static interface IReplaceBlockRule {
/*     */     boolean replace(World param1World, BlockPos param1BlockPos, BlockState param1BlockState); }
/*     */   public boolean isBanned(BlockState state) {
/* 142 */     if (this.bannedBlocks.contains(state.func_177230_c().getRegistryName())) {
/* 143 */       return true;
/*     */     }
/* 145 */     if (this.bannedBlockTags.stream().anyMatch(tag -> tag.func_230235_a_(state.func_177230_c()))) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (this.bannedMaterials.stream().anyMatch(mat -> (state.func_185904_a() == mat))) {
/* 149 */       return true;
/*     */     }
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public static class Builder
/*     */   {
/* 156 */     private Set<BlockProtectionRule.IReplaceBlockRule> replaceRules = new HashSet<>();
/*     */     
/* 158 */     private Set<ResourceLocation> approvedBlocks = new HashSet<>();
/* 159 */     private Set<Material> approvedMaterials = new HashSet<>();
/* 160 */     private Set<ITag<Block>> approvedBlockTags = new HashSet<>();
/*     */     
/* 162 */     private Set<ResourceLocation> bannedBlocks = new HashSet<>();
/* 163 */     private Set<Material> bannedMaterials = new HashSet<>();
/* 164 */     private Set<ITag<Block>> bannedBlockTags = new HashSet<>();
/*     */     
/*     */     private boolean bypassGriefFlag = false;
/*     */     
/*     */     private boolean coordsOnly = false;
/*     */     private boolean forcePlacement = false;
/*     */     
/*     */     public Builder(BlockProtectionRule... rules) {
/* 172 */       for (BlockProtectionRule rule : rules) {
/*     */         
/* 174 */         this.replaceRules.addAll(rule.getReplaceRules());
/*     */         
/* 176 */         this.approvedBlocks.addAll(rule.getApprovedBlocks());
/* 177 */         this.approvedMaterials.addAll(rule.getApprovedMaterials());
/* 178 */         this.approvedBlockTags.addAll(rule.getApprovedTags());
/*     */         
/* 180 */         this.bannedBlocks.addAll(rule.getBannedBlocks());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addReplaceRules(BlockProtectionRule.IReplaceBlockRule fn) {
/* 186 */       this.replaceRules.add(fn);
/* 187 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addApprovedBlocks(Block... blocks) {
/* 192 */       Arrays.<Block>stream(blocks).map(b -> b.getRegistryName()).forEach(this.approvedBlocks::add);
/* 193 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addApprovedBlocks(RegistryObject<Block>... blocks) {
/* 198 */       Arrays.<RegistryObject<Block>>stream(blocks).map(b -> b.getId()).forEach(this.approvedBlocks::add);
/* 199 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addApprovedMaterials(Material... mats) {
/* 204 */       this.approvedMaterials.addAll(Lists.newArrayList((Object[])mats));
/* 205 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addApprovedTags(ITag<Block>... tags) {
/* 210 */       this.approvedBlockTags.addAll(Lists.newArrayList((Object[])tags));
/* 211 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addBannedBlocks(BlockProtectionRule rule) {
/* 216 */       this.bannedBlocks.addAll(rule.getBannedBlocks());
/* 217 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addBannedBlocks(Block... blocks) {
/* 222 */       Arrays.<Block>stream(blocks).map(b -> b.getRegistryName()).forEach(this.bannedBlocks::add);
/* 223 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addBannedBlocks(RegistryObject<Block>... blocks) {
/* 228 */       Arrays.<RegistryObject<Block>>stream(blocks).map(b -> b.getId()).forEach(this.bannedBlocks::add);
/* 229 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder setBypassGriefRule() {
/* 234 */       this.bypassGriefFlag = true;
/* 235 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder setCoordsOnly() {
/* 240 */       this.coordsOnly = true;
/* 241 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setForced() {
/* 245 */       this.forcePlacement = true;
/* 246 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public BlockProtectionRule build() {
/* 251 */       BlockProtectionRule rule = new BlockProtectionRule();
/*     */       
/* 253 */       rule.addReplaceRules(this.replaceRules);
/*     */       
/* 255 */       rule.addApprovedBlocks(this.approvedBlocks);
/* 256 */       rule.addApprovedMaterials(this.approvedMaterials);
/* 257 */       rule.addApprovedTags(this.approvedBlockTags);
/*     */       
/* 259 */       rule.addBannedBlocks(this.bannedBlocks);
/*     */       
/* 261 */       if (this.bypassGriefFlag) {
/* 262 */         rule.setBypassGriefRule();
/*     */       }
/* 264 */       if (this.coordsOnly) {
/* 265 */         rule.setCoordsOnly();
/*     */       }
/*     */       
/* 268 */       if (this.forcePlacement) {
/* 269 */         rule.setForced();
/*     */       }
/*     */       
/* 272 */       return rule;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\protection\BlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */