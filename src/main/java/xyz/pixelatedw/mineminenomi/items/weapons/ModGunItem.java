/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.enchantment.EnchantmentType;
/*     */ import net.minecraft.enchantment.Enchantments;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.stats.Stats;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bomu.BreezeBreathBombAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.HissatsuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ModGunItem extends Item {
/*  52 */   private int maxGunpowder = 3;
/*  53 */   private float bulletSpeed = 2.0F;
/*  54 */   private float bulletAccuracy = 2.0F;
/*  55 */   private int shotCooldown = 10;
/*  56 */   private int reloadCooldown = 20;
/*  57 */   private float damageMultiplier = 1.0F; public Predicate<ItemStack> bulletCheck; public static final Predicate<ItemStack> GUN_AMMO;
/*     */   public static final Predicate<ItemStack> BAZOOKA_AMMO;
/*     */   
/*     */   static {
/*  61 */     GUN_AMMO = (itemStack -> 
/*  62 */       (itemStack.func_77973_b() == ModItems.BULLET.get() || itemStack.func_77973_b() == ModItems.KAIROSEKI_BULLET.get()));
/*     */ 
/*     */     
/*  65 */     BAZOOKA_AMMO = (itemStack -> (itemStack.func_77973_b() == ModItems.CANNON_BALL.get()));
/*     */   }
/*     */ 
/*     */   
/*     */   public ModGunItem(int maxDamage) {
/*  70 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.WEAPONS).func_200917_a(1).func_200918_c(maxDamage));
/*  71 */     this.bulletCheck = GUN_AMMO;
/*     */   }
/*     */   
/*     */   public ModGunItem(int maxDamage, Predicate<ItemStack> bulletPredicate) {
/*  75 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.WEAPONS).func_200917_a(1).func_200918_c(maxDamage));
/*  76 */     this.bulletCheck = bulletPredicate;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/*  81 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*  82 */     BreezeBreathBombAbility ability = (BreezeBreathBombAbility)abilityProps.getEquippedAbility(BreezeBreathBombAbility.INSTANCE);
/*  83 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*  84 */     boolean hasBullets = (findBulletStack(player) != null);
/*     */     
/*  86 */     Item bulletType = (findBulletStack(player) != null) ? findBulletStack(player).func_77973_b() : null;
/*  87 */     ItemStack heldItemStack = player.func_184586_b(hand);
/*  88 */     if (hasAbility) {
/*  89 */       playLoadSound((LivingEntity)player);
/*  90 */       player.func_184598_c(hand);
/*  91 */       return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
/*     */     } 
/*     */     
/*  94 */     boolean flag = !player.func_213356_f(heldItemStack).func_190926_b();
/*  95 */     if (flag) {
/*  96 */       playLoadSound((LivingEntity)player);
/*  97 */       player.func_184598_c(hand);
/*  98 */       return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
/*     */     } 
/*     */     
/* 101 */     if (!hasBullets || bulletType == null) {
/* 102 */       return new ActionResult(ActionResultType.FAIL, heldItemStack);
/*     */     }
/*     */     
/* 105 */     boolean hasGunPowder = (getLoadedGunPowder(heldItemStack) > 0);
/*     */     
/* 107 */     if (!hasGunPowder) {
/* 108 */       for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/* 109 */         ItemStack stack = player.field_71071_by.func_70301_a(i);
/* 110 */         if (stack.func_77973_b() == Items.field_151016_H) {
/* 111 */           int count = this.maxGunpowder;
/* 112 */           if (stack.func_190916_E() < count) {
/* 113 */             count = stack.func_190916_E();
/*     */           }
/*     */           
/* 116 */           setLoadedGunPowder(heldItemStack, count * 5);
/* 117 */           player.field_71071_by.func_70298_a(i, count);
/* 118 */           hasGunPowder = true;
/* 119 */           player.func_184811_cZ().func_185145_a(this, this.reloadCooldown);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 125 */     if (!hasGunPowder) {
/* 126 */       return new ActionResult(ActionResultType.FAIL, heldItemStack);
/*     */     }
/*     */     
/* 129 */     playLoadSound((LivingEntity)player);
/*     */     
/* 131 */     player.func_184598_c(hand);
/* 132 */     return new ActionResult(ActionResultType.SUCCESS, heldItemStack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack itemStack, World world, LivingEntity entityLiving, int timeLeft) {
/* 137 */     if (!(entityLiving instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 141 */     PlayerEntity player = (PlayerEntity)entityLiving;
/* 142 */     Item bulletType = (findBulletStack(player) != null) ? findBulletStack(player).func_77973_b() : null;
/* 143 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 144 */     BreezeBreathBombAbility ability = (BreezeBreathBombAbility)abilityProps.getEquippedAbility(BreezeBreathBombAbility.INSTANCE);
/* 145 */     if (!player.field_70170_p.field_72995_K && ability != null && (
/* 146 */       (Boolean)ability.getComponent(ModAbilityKeys.CONTINUOUS).map(ContinuousComponent::isContinuous).orElse(Boolean.valueOf(false))).booleanValue()) {
/* 147 */       ability.getComponent(ModAbilityKeys.PROJECTILE).ifPresent(comp -> comp.shoot(entityLiving));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 152 */     int powder = getLoadedGunPowder(itemStack);
/* 153 */     if (!world.field_72995_K) {
/* 154 */       boolean flag = (player.func_184812_l_() || EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, itemStack) > 0);
/* 155 */       int j = func_77626_a(itemStack) - timeLeft;
/* 156 */       j = ForgeEventFactory.onArrowLoose(itemStack, world, player, j, (!itemStack.func_190926_b() || flag));
/* 157 */       if (j < 0) {
/*     */         return;
/*     */       }
/*     */       
/* 161 */       boolean isHitScan = HissatsuAbility.checkHitScan((LivingEntity)player);
/* 162 */       int amount = (EnchantmentHelper.func_77506_a(Enchantments.field_222192_G, itemStack) > 0) ? 3 : 1;
/*     */       
/* 164 */       for (int n = 0; n < amount; n++) {
/* 165 */         CannonBallProjectile cannonBallProjectile; NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(player.field_70170_p, (LivingEntity)player);
/* 166 */         if (bulletType == ModItems.BULLET.get()) {
/* 167 */           normalBulletProjectile = new NormalBulletProjectile(player.field_70170_p, (LivingEntity)player);
/*     */         }
/* 169 */         else if (bulletType == ModItems.KAIROSEKI_BULLET.get()) {
/* 170 */           KairosekiBulletProjectile kairosekiBulletProjectile = new KairosekiBulletProjectile(player.field_70170_p, (LivingEntity)player);
/*     */         }
/* 172 */         else if (bulletType == ModItems.CANNON_BALL.get()) {
/* 173 */           cannonBallProjectile = new CannonBallProjectile(player.field_70170_p, (LivingEntity)player);
/*     */         } 
/*     */         
/* 176 */         int m = EnchantmentHelper.func_77506_a(Enchantments.field_185309_u, itemStack);
/* 177 */         if (m > 0) {
/* 178 */           cannonBallProjectile.setDamage((float)(cannonBallProjectile.getDamage() + m * 0.5D + 0.5D));
/*     */         }
/*     */         
/* 181 */         int k = EnchantmentHelper.func_77506_a(Enchantments.field_185310_v, itemStack);
/* 182 */         if (k > 0) {
/* 183 */           cannonBallProjectile.setKnockbackStrength(k);
/*     */         }
/*     */         
/* 186 */         if (EnchantmentHelper.func_77506_a(Enchantments.field_185311_w, itemStack) > 0) {
/* 187 */           cannonBallProjectile.func_70015_d(100);
/*     */         }
/*     */         
/* 190 */         cannonBallProjectile.setDamage(Math.round(cannonBallProjectile.getDamage() * this.damageMultiplier));
/*     */         
/* 192 */         if (isHitScan) {
/* 193 */           float distance = cannonBallProjectile.getMaxLife();
/* 194 */           EntityRayTraceResult result = WyHelper.rayTraceEntities((Entity)player, distance, 1.25D, ProjectileComponent.TARGET_CHECK);
/*     */           
/* 196 */           if (result.func_216348_a() != null && result.func_216348_a() instanceof LivingEntity) {
/* 197 */             cannonBallProjectile.onModHit((RayTraceResult)result);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 202 */           player.field_70170_p.func_217376_c((Entity)cannonBallProjectile);
/*     */           
/* 204 */           if (n > 0) {
/* 205 */             Vector3d vector3d1 = player.func_213286_i(1.0F);
/* 206 */             Quaternion quaternion = new Quaternion(new Vector3f(vector3d1), (n == 1) ? -10.0F : 10.0F, true);
/* 207 */             Vector3d vector3d = player.func_70676_i(1.0F);
/* 208 */             Vector3f vector3f = new Vector3f(vector3d);
/* 209 */             vector3f.func_214905_a(quaternion);
/* 210 */             cannonBallProjectile.func_70186_c(vector3f.func_195899_a(), vector3f.func_195900_b(), vector3f.func_195902_c(), this.bulletSpeed, this.bulletAccuracy);
/*     */           } else {
/*     */             
/* 213 */             cannonBallProjectile.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, this.bulletSpeed, this.bulletAccuracy);
/*     */           } 
/*     */         } 
/*     */       } 
/* 217 */       playShootSound((LivingEntity)player);
/*     */       
/* 219 */       Vector3d lookVec = player.func_70040_Z().func_178785_b((float)Math.toRadians(-30.0D));
/* 220 */       Vector3d particlePos = player.func_213303_ch().func_72441_c(0.0D, player.func_70047_e(), 0.0D).func_178787_e(lookVec);
/*     */       
/* 222 */       for (int p = 0; p < 10; p++) {
/* 223 */         if (p % 2 == 0) {
/* 224 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197598_I, (ServerWorld)player.field_70170_p, particlePos.field_72450_a, particlePos.field_72448_b, particlePos.field_72449_c);
/*     */         } else {
/*     */           
/* 227 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197601_L, (ServerWorld)player.field_70170_p, particlePos.field_72450_a, particlePos.field_72448_b, particlePos.field_72449_c);
/*     */         } 
/*     */       } 
/*     */       
/* 231 */       itemStack.func_222118_a(1, (LivingEntity)player, user -> user.func_213334_d(player.func_184600_cs()));
/*     */ 
/*     */       
/* 234 */       player.func_71029_a(Stats.field_75929_E.func_199076_b(this));
/*     */     } 
/*     */     
/* 237 */     player.func_184811_cZ().func_185145_a(this, this.shotCooldown);
/* 238 */     boolean hasInfinite = (player.func_184812_l_() || EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, itemStack) > 0);
/* 239 */     if (hasInfinite) {
/*     */       return;
/*     */     }
/* 242 */     setLoadedGunPowder(itemStack, Math.max(0, --powder));
/* 243 */     for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*     */       
/* 245 */       ItemStack stack = player.field_71071_by.func_70301_a(i);
/* 246 */       if (this.bulletCheck.test(stack)) {
/*     */         
/* 248 */         player.field_71071_by.func_70298_a(i, 1);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void playLoadSound(LivingEntity entity) {
/* 255 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GUN_LOAD.get(), SoundCategory.PLAYERS, 1.5F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*     */   }
/*     */   
/*     */   public void playShootSound(LivingEntity entity) {
/* 259 */     if (this == ModWeapons.FLINTLOCK.get() || this == ModWeapons.WALKER.get()) {
/* 260 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PISTOL_SHOOT.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*     */     }
/* 262 */     else if (this == ModWeapons.BAZOOKA.get()) {
/* 263 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.GENERIC_EXPLOSION.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*     */     }
/* 265 */     else if (this == ModWeapons.SENRIKU.get()) {
/* 266 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.SNIPER_SHOOT.get(), SoundCategory.PLAYERS, 2.0F, 0.75F + entity.func_70681_au().nextFloat() / 4.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 272 */     if (!itemStack.func_77942_o()) {
/* 273 */       itemStack.func_77982_d(new CompoundNBT());
/* 274 */       itemStack.func_77978_p().func_74768_a("gunPowder", 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLoadedGunPowder(ItemStack itemStack, int powder) {
/* 279 */     itemStack.func_77978_p().func_74768_a("gunPowder", powder);
/*     */   }
/*     */   
/*     */   public int getLoadedGunPowder(ItemStack itemStack) {
/* 283 */     return itemStack.func_77978_p().func_74762_e("gunPowder");
/*     */   }
/*     */   
/*     */   public ItemStack findBulletStack(PlayerEntity player) {
/* 287 */     for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/* 288 */       ItemStack stack = player.field_71071_by.func_70301_a(i);
/* 289 */       if (this.bulletCheck.test(stack)) {
/* 290 */         return stack;
/*     */       }
/*     */     } 
/*     */     
/* 294 */     return null;
/*     */   }
/*     */   
/*     */   public ModGunItem setGunpowderLimit(int limit) {
/* 298 */     this.maxGunpowder = limit;
/* 299 */     return this;
/*     */   }
/*     */   
/*     */   public ModGunItem setDamageMultiplier(float damage) {
/* 303 */     this.damageMultiplier = damage;
/* 304 */     return this;
/*     */   }
/*     */   
/*     */   public ModGunItem setShotCooldown(int cd) {
/* 308 */     this.shotCooldown = cd;
/* 309 */     return this;
/*     */   }
/*     */   
/*     */   public ModGunItem setReloadCooldown(int cd) {
/* 313 */     this.reloadCooldown = cd;
/* 314 */     return this;
/*     */   }
/*     */   
/*     */   public ModGunItem setBulletAccuracy(float acc) {
/* 318 */     this.bulletAccuracy = acc;
/* 319 */     return this;
/*     */   }
/*     */   
/*     */   public ModGunItem setBulletSpeed(float speed) {
/* 323 */     this.bulletSpeed = speed;
/* 324 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack stack) {
/* 329 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public UseAction func_77661_b(ItemStack stack) {
/* 334 */     return UseAction.BOW;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 339 */     if (itemStack.func_77942_o()) {
/* 340 */       list.add(new StringTextComponent("Gun Powder: " + itemStack.func_77978_p().func_74762_e("gunPowder")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_77619_b() {
/* 346 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 351 */     return (enchantment.field_77351_y == EnchantmentType.BOW || enchantment.field_77351_y == EnchantmentType.BREAKABLE);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\ModGunItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */