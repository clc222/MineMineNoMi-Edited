/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentType;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.IItemTier;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.SwordItem;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ 
/*     */ public class ModSwordItem extends SwordItem {
/*     */   protected boolean isPoisonous = false;
/*  34 */   public double damage = 1.0D;
/*     */   protected boolean isFireAspect = false;
/*  36 */   public double range = 0.0D; protected boolean isSlownessInducing = false;
/*     */   protected boolean isStackable = false;
/*  38 */   protected int poisonTimer = 100; protected int fireAspectTimer = 10; protected int slownessTimer = 100; protected int frostBiteTimer = 0;
/*     */   
/*     */   private boolean isBlunt = false;
/*     */   private boolean rustImmunity = false;
/*     */   private boolean independentImbuing = false;
/*     */   private Ingredient repairIngredient;
/*  44 */   protected static final UUID ATTACK_RANGE_MODIFIER = UUID.fromString("06256896-00c1-45b4-bc71-514ee36310bd");
/*     */ 
/*     */   
/*     */   public ModSwordItem(Item.Properties props, int damage, float attackSpeed) {
/*  48 */     super((IItemTier)AbilityItemTier.WEAPON, damage, attackSpeed, props);
/*  49 */     this.damage = (damage - 1);
/*     */   }
/*     */   
/*     */   public ModSwordItem(int damage, int durability) {
/*  53 */     this(damage, -2.4F, durability);
/*  54 */     this.damage = (damage - 1);
/*     */   }
/*     */   
/*     */   public ModSwordItem(int damage, float attackSpeed, int durability) {
/*  58 */     super((IItemTier)AbilityItemTier.WEAPON, damage, attackSpeed, (new Item.Properties()).func_200916_a(ModCreativeTabs.WEAPONS).func_200917_a(1).func_200915_b(durability));
/*  59 */     this.damage = (damage - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/*  64 */     return (enchantment.field_77351_y == EnchantmentType.WEAPON || enchantment.field_77351_y == EnchantmentType.BREAKABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
/*  74 */     if (!world.field_72995_K) {
/*  75 */       CompoundNBT nbt = itemStack.func_196082_o();
/*  76 */       if (nbt.func_74767_n("isClone") && !nbt.func_74767_n("hasCloneTag")) {
/*  77 */         itemStack.func_200302_a((ITextComponent)new StringTextComponent(TextFormatting.RESET + itemStack.func_200301_q().getString() + " (Replica)"));
/*  78 */         nbt.func_74757_a("hasCloneTag", true);
/*     */       }
/*  80 */       else if (!nbt.func_74764_b("isClone")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  89 */         nbt.func_74757_a("isClone", false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  97 */     return new ActionResult(ActionResultType.PASS, player.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77619_b() {
/* 103 */     return 14;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77644_a(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/* 109 */     IAbilityData abilityProps = AbilityDataCapability.get(attacker);
/*     */     
/* 111 */     BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
/* 112 */     boolean hasBusoHaki = (ability != null && ability.isContinuous());
/*     */     
/* 114 */     if (!hasBusoHaki) {
/*     */       
/* 116 */       int damage = itemStack.func_196082_o().func_74767_n("isClone") ? 3 : 1;
/* 117 */       itemStack.func_222118_a(damage, attacker, entity -> entity.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (hasBusoHaki || this.isBlunt) {
/*     */       
/* 125 */       Item mainShield = target.func_184614_ca().func_77973_b();
/* 126 */       Item secondaryShield = target.func_184592_cb().func_77973_b();
/* 127 */       if (target instanceof PlayerEntity && Math.random() > 0.5D && (mainShield.equals(Items.field_185159_cQ) || secondaryShield.equals(Items.field_185159_cQ))) {
/*     */         
/* 129 */         ((PlayerEntity)target).func_184811_cZ().func_185145_a(Items.field_185159_cQ, 100);
/* 130 */         target.func_184602_cy();
/* 131 */         target.field_70170_p.func_72960_a((Entity)target, (byte)30);
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     if (this.isPoisonous) {
/* 136 */       target.func_195064_c(new EffectInstance(Effects.field_76436_u, this.poisonTimer, 0));
/*     */     }
/* 138 */     if (this.isFireAspect)
/*     */     {
/* 140 */       AbilityHelper.setSecondsOnFireBy((Entity)target, this.fireAspectTimer, attacker);
/*     */     }
/*     */     
/* 143 */     if (this.isSlownessInducing)
/*     */     {
/* 145 */       if (this.isStackable) {
/*     */         
/* 147 */         if (target.func_70644_a(Effects.field_76421_d)) {
/*     */           
/* 149 */           int timer = target.func_70660_b(Effects.field_76421_d).func_76459_b();
/* 150 */           target.func_195064_c(new EffectInstance(Effects.field_76421_d, timer + this.slownessTimer, 0));
/*     */         } else {
/*     */           
/* 153 */           target.func_195064_c(new EffectInstance(Effects.field_76421_d, this.slownessTimer, 0));
/*     */         } 
/*     */       } else {
/* 156 */         target.func_195064_c(new EffectInstance(Effects.field_76421_d, this.slownessTimer, 0));
/*     */       } 
/*     */     }
/* 159 */     if (this.frostBiteTimer > 0) {
/* 160 */       AbilityHelper.addFrostbiteStacks(target, attacker, 1);
/*     */     }
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsPoisonous() {
/* 167 */     this.isPoisonous = true;
/* 168 */     this.poisonTimer = 100;
/* 169 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsPoisonous(int timer) {
/* 174 */     this.isPoisonous = true;
/* 175 */     this.poisonTimer = timer;
/* 176 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setRepairIngredient(Ingredient ingredient) {
/* 186 */     this.repairIngredient = ingredient;
/* 187 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Ingredient getRepairIngredient() {
/* 192 */     return this.repairIngredient;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setBlunt() {
/* 197 */     this.isBlunt = true;
/* 198 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setSharp() {
/* 203 */     this.isBlunt = false;
/* 204 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlunt() {
/* 209 */     return this.isBlunt;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setRustImmunity() {
/* 214 */     this.rustImmunity = true;
/* 215 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isRustImmune() {
/* 221 */     return this.rustImmunity;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsFireAspect() {
/* 226 */     this.isFireAspect = true;
/* 227 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsFireAspect(int timer) {
/* 232 */     this.isFireAspect = true;
/* 233 */     this.fireAspectTimer = timer;
/* 234 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsSlownessInducing() {
/* 239 */     this.isSlownessInducing = true;
/* 240 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsSlownessInducing(int timer) {
/* 245 */     this.isSlownessInducing = true;
/* 246 */     this.slownessTimer = timer;
/* 247 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setFrosbiteTimer(int timer) {
/* 252 */     this.frostBiteTimer = timer;
/* 253 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setIsSlownessInducing(int timer, boolean isStackable) {
/* 258 */     this.isSlownessInducing = true;
/* 259 */     this.slownessTimer = timer;
/* 260 */     this.isStackable = isStackable;
/*     */     
/* 262 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends ModSwordItem> T setExtraAttackRange(double range) {
/* 267 */     this.range = range;
/* 268 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_82789_a(ItemStack toRepair, ItemStack repair) {
/*     */     try {
/* 275 */       return this.repairIngredient.test(repair);
/* 276 */     } catch (Exception ex) {
/* 277 */       ex.printStackTrace();
/*     */       
/* 279 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean usesIndependentImbuing() {
/* 283 */     return this.independentImbuing;
/*     */   }
/*     */   
/*     */   public void setIndependentImbuing() {
/* 287 */     this.independentImbuing = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ModSwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */