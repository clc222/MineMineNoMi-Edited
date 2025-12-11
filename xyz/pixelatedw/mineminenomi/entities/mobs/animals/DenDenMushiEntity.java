/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.PanicGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IRandomTexture;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ 
/*     */ public class DenDenMushiEntity extends AnimalEntity implements IRandomTexture {
/*  37 */   private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.func_187226_a(DenDenMushiEntity.class, DataSerializers.field_187192_b);
/*     */ 
/*     */   
/*     */   public DenDenMushiEntity(EntityType type, World world) {
/*  41 */     super(type, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  47 */     this.field_70714_bg.func_75776_a(1, (Goal)new PanicGoal((CreatureEntity)this, 0.75D));
/*  48 */     this.field_70714_bg.func_75776_a(6, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 1.0D));
/*  49 */     this.field_70714_bg.func_75776_a(7, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 6.0F));
/*  50 */     this.field_70714_bg.func_75776_a(8, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  56 */     super.func_70088_a();
/*  57 */     func_184212_Q().func_187214_a(TEXTURE_ID, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  62 */     return CreatureEntity.func_233666_p_()
/*  63 */       .func_233815_a_(Attributes.field_233821_d_, 0.11999999731779099D)
/*  64 */       .func_233815_a_(Attributes.field_233818_a_, 5.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  71 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  73 */     setTexture(this.field_70146_Z.nextInt(MobsHelper.DEN_DEN_MUSHI_TEXTURES.length));
/*     */     
/*  75 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/*  81 */     ItemStack stack = player.func_184586_b(hand);
/*  82 */     if (stack.func_77973_b() == Items.field_151042_j) {
/*     */       
/*  84 */       ItemStack denStack = new ItemStack((IItemProvider)ModBlocks.DEN_DEN_MUSHI.get());
/*  85 */       denStack.func_196082_o().func_74768_a("type", getTextureId());
/*  86 */       player.field_71071_by.func_70441_a(denStack);
/*  87 */       stack.func_190918_g(1);
/*  88 */       func_70106_y();
/*  89 */       return ActionResultType.SUCCESS;
/*     */     } 
/*     */     
/*  92 */     return ActionResultType.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/*  98 */     super.func_213281_b(nbt);
/*  99 */     nbt.func_74768_a("Texture", getTextureId());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 105 */     super.func_70037_a(nbt);
/* 106 */     setTexture(nbt.func_74762_e("Texture"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextureId() {
/* 117 */     return ((Integer)func_184212_Q().func_187225_a(TEXTURE_ID)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTexture(int texture) {
/* 122 */     func_184212_Q().func_187227_b(TEXTURE_ID, Integer.valueOf(texture));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getCurrentTexture() {
/* 128 */     return MobsHelper.DEN_DEN_MUSHI_TEXTURES[getTextureId()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getDefaultTexture() {
/* 134 */     return MobsHelper.DEN_DEN_MUSHI_TEXTURES[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\DenDenMushiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */