/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.CommandAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IRandomTexture;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FactionHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.KageProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ 
/*     */ public class NightmareSoldierEntity
/*     */   extends CreatureEntity
/*     */   implements IRandomTexture, ICommandReceiver, IEntityAdditionalSpawnData, ITamableEntity {
/*     */   private static final int RESPAWN_TIMER = 600;
/*     */   @Nullable
/*     */   private UUID ownerId;
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*     */   private ResourceLocation currentTexture;
/*     */   private long lastCommandTime;
/*     */   private LivingEntity lastCommandSender;
/*  64 */   private NPCCommand currentCommand = NPCCommand.IDLE;
/*     */   
/*     */   private boolean respawning;
/*     */   private int respawnTimer;
/*     */   private boolean canDieTick;
/*     */   
/*     */   public NightmareSoldierEntity(EntityType type, World world) {
/*  71 */     this(world);
/*     */   }
/*     */   
/*     */   public NightmareSoldierEntity(World world) {
/*  75 */     super((EntityType)KageProjectiles.NIGHTMARE_SOLDIER.get(), world);
/*     */     
/*  77 */     if (world != null && !world.field_72995_K) {
/*  78 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */       
/*  80 */       props.setHeart(false);
/*  81 */       props.setShadow(true);
/*     */       
/*  83 */       int id = this.field_70146_Z.nextInt(MobsHelper.NIGHTMARE_SOLDIER_TEXTURES.length);
/*  84 */       this.currentTexture = MobsHelper.NIGHTMARE_SOLDIER_TEXTURES[id];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  90 */     CommandAbility.addCommandGoals((MobEntity)this);
/*     */     
/*  92 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/*     */     
/*  94 */     this.field_70714_bg.func_75776_a(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  95 */     this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  96 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  97 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractMarineEntity.class, 8.0F));
/*  98 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractPirateEntity.class, 8.0F));
/*  99 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AbstractBanditEntity.class, 8.0F));
/* 100 */     this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 104 */     return OPEntity.createAttributes()
/* 105 */       .func_233815_a_(Attributes.field_233823_f_, 6.0D)
/* 106 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 107 */       .func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D)
/* 108 */       .func_233815_a_(Attributes.field_233818_a_, 100.0D)
/* 109 */       .func_233815_a_(Attributes.field_233820_c_, 0.5D)
/* 110 */       .func_233815_a_(Attributes.field_233826_i_, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 115 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource damageSource, float damageValue) {
/* 120 */     if (damageSource.func_76357_e()) {
/* 121 */       this.canDieTick = true;
/* 122 */       return super.func_70097_a(damageSource, damageValue);
/*     */     } 
/*     */     
/* 125 */     if (damageSource.func_76364_f() != null) {
/* 126 */       if (ModTags.Entities.KAIROSEKI.func_230235_a_(damageSource.func_76364_f().func_200600_R())) {
/* 127 */         this.canDieTick = true;
/* 128 */         func_70097_a((DamageSource)ModDamageSource.DEVILS_CURSE, func_110138_aP());
/* 129 */         return false;
/*     */       } 
/*     */       
/* 132 */       if (this.respawning && damageSource.func_76364_f() instanceof PlayerEntity) {
/* 133 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 137 */     if (damageSource.func_76346_g() != null) {
/* 138 */       if (damageSource instanceof AbilityDamageSource && ((AbilityDamageSource)damageSource).getElement() == SourceElement.WATER) {
/* 139 */         this.canDieTick = true;
/* 140 */         func_70097_a((DamageSource)ModDamageSource.DEVILS_CURSE, func_110138_aP());
/* 141 */         return false;
/*     */       } 
/*     */       
/* 144 */       if (damageSource.func_76346_g() instanceof PlayerEntity && damageSource.func_76346_g() == getOwner()) {
/* 145 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return super.func_70097_a(damageSource, damageValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 154 */     if (!this.field_70170_p.field_72995_K) {
/* 155 */       if (getOwner() == null || !getOwner().func_70089_S()) {
/* 156 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/* 160 */       if (this.canDieTick) {
/* 161 */         func_70097_a((DamageSource)ModDamageSource.DEVILS_CURSE, func_110138_aP());
/*     */       }
/* 163 */       else if (!this.canDieTick && func_110143_aJ() <= 0.0F) {
/* 164 */         func_70606_j(1.0F);
/* 165 */         func_195064_c(new EffectInstance((Effect)ModEffects.UNCONSCIOUS.get(), 600, 0));
/* 166 */         this.respawning = true;
/* 167 */         this.respawnTimer = 600;
/*     */       } 
/*     */       
/* 170 */       if (this.respawning) {
/* 171 */         if (this.respawnTimer > 0) {
/* 172 */           this.respawnTimer--;
/*     */         } else {
/*     */           
/* 175 */           func_195063_d((Effect)ModEffects.UNCONSCIOUS.get());
/* 176 */           func_70606_j(func_110138_aP());
/* 177 */           this.respawning = false;
/*     */         } 
/*     */       }
/*     */       
/* 181 */       if (func_70089_S() && (
/* 182 */         func_233571_b_((ITag)FluidTags.field_206959_a) > 0.9D || func_70644_a((Effect)ModEffects.WET.get()))) {
/* 183 */         func_70097_a((DamageSource)ModDamageSource.DEVILS_CURSE, func_110138_aP());
/*     */       }
/*     */     } 
/*     */     
/* 187 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 192 */     super.func_213281_b(nbt);
/* 193 */     if (this.ownerId != null) {
/* 194 */       nbt.func_186854_a("ownerId", this.ownerId);
/*     */     }
/* 196 */     if (this.currentTexture != null) {
/* 197 */       nbt.func_74778_a("texture", this.currentTexture.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 203 */     super.func_70037_a(nbt);
/* 204 */     if (nbt.func_74764_b("ownerId")) {
/* 205 */       this.ownerId = nbt.func_186857_a("ownerId");
/*     */     }
/* 207 */     if (nbt.func_74764_b("texture")) {
/* 208 */       this.currentTexture = new ResourceLocation(nbt.func_74779_i("texture"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 214 */     buffer.func_179252_a(this.ownerId);
/* 215 */     int len = this.currentTexture.toString().length();
/* 216 */     buffer.writeInt(len);
/* 217 */     buffer.func_211400_a(this.currentTexture.toString(), len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer data) {
/* 222 */     this.ownerId = data.func_179253_g();
/* 223 */     int len = data.readInt();
/* 224 */     this.currentTexture = new ResourceLocation(data.func_150789_c(len));
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 229 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 233 */     this.owner = owner;
/* 234 */     this.ownerId = owner.func_110124_au();
/* 235 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)this);
/* 236 */     stats.setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */     
/* 238 */     Predicate<Entity> factionScope = ModEntityPredicates.getEnemyFactions((LivingEntity)this);
/* 239 */     Predicate<Entity> notSame = entity -> !(entity instanceof NightmareSoldierEntity);
/*     */     
/* 241 */     if (factionScope != null) {
/* 242 */       this.field_70715_bh.func_75776_a(1, (Goal)new FactionHurtByTargetGoal(this, factionScope, new Class[0]));
/* 243 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MobEntity.class, 10, true, true, factionScope.and(notSame)));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/* 249 */     if (this.owner == null && this.ownerId != null) {
/* 250 */       this.owner = (LivingEntity)this.field_70170_p.func_217371_b(this.ownerId);
/*     */     }
/* 252 */     return this.owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getCurrentTexture() {
/* 257 */     return this.currentTexture;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getDefaultTexture() {
/* 262 */     return MobsHelper.NIGHTMARE_SOLDIER_TEXTURES[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canReceiveCommandFrom(LivingEntity commandSender) {
/* 267 */     if (getOwner() == null || commandSender == null) {
/* 268 */       return false;
/*     */     }
/*     */     
/* 271 */     return getOwner().equals(commandSender);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentCommand(@Nullable LivingEntity commandSender, NPCCommand command) {
/* 276 */     this.lastCommandTime = this.field_70170_p.func_82737_E();
/* 277 */     this.lastCommandSender = commandSender;
/* 278 */     this.currentCommand = command;
/*     */   }
/*     */ 
/*     */   
/*     */   public NPCCommand getCurrentCommand() {
/* 283 */     return this.currentCommand;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getLastCommandSender() {
/* 289 */     return this.lastCommandSender;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastCommandTime() {
/* 294 */     return this.lastCommandTime;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\NightmareSoldierEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */