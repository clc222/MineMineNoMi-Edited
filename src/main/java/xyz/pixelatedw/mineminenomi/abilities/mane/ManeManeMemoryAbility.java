/*     */ package xyz.pixelatedw.mineminenomi.abilities.mane;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.common.IExtensibleEnum;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.MorphComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ManeManeMemoryAbility
/*     */   extends Ability {
/*  46 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mane_mane_memory", new Pair[] {
/*  47 */         (Pair)ImmutablePair.of("While in COPY mode hitting others will memorize their properties, other modes represent the memories the user can change into.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 20;
/*  51 */   public static final AbilityCore<ManeManeMemoryAbility> INSTANCE = (new AbilityCore.Builder("Mane Mane Memory", AbilityCategory.DEVIL_FRUITS, ManeManeMemoryAbility::new))
/*  52 */     .addDescriptionLine(DESCRIPTION)
/*  53 */     .build();
/*     */   
/*  55 */   private static final ManeMemory EMPTY_MEMORY = new ManeMemory();
/*     */   
/*  57 */   private List<ManeMemory> memories = new ArrayList<>();
/*     */   
/*     */   private int memoryId;
/*  60 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  61 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::hitEvent);
/*  62 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.COPY)).addChangeModeEvent(this::changeModeEvent);
/*  63 */   private final MorphComponent morphComponent = new MorphComponent((IAbility)this);
/*     */   
/*     */   public ManeManeMemoryAbility(AbilityCore<ManeManeMemoryAbility> core) {
/*  66 */     super(core);
/*     */     
/*  68 */     this.isNew = true;
/*  69 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.morphComponent, (AbilityComponent)this.altModeComponent });
/*     */     
/*  71 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  75 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  79 */     if (!this.memories.isEmpty() && getMemory().isValidMemory()) {
/*  80 */       this.morphComponent.startMorph(entity, (MorphInfo)ModMorphs.MANE_MEMORY.get());
/*  81 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MANE_SWITCH.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  82 */       if (entity instanceof PlayerEntity) {
/*  83 */         ((PlayerEntity)entity).refreshDisplayName();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/*  89 */     if (this.continuousComponent.isContinuous() && this.altModeComponent.getCurrentMode() == Mode.COPY && 
/*  90 */       target instanceof PlayerEntity && !this.memories.stream().anyMatch(mem -> mem.getUUID().equals(target.func_110124_au()))) {
/*  91 */       source.canBypassFriendlyDamage();
/*  92 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MANE_SWITCH.get(), SoundCategory.PLAYERS, 1.0F, 0.2F);
/*  93 */       addMemory(entity, target);
/*     */     } 
/*     */ 
/*     */     
/*  97 */     return HitTriggerComponent.HitResult.PASS;
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 101 */     if (getMemory().isValidMemory()) {
/* 102 */       this.morphComponent.stopMorph(entity);
/* 103 */       if (entity instanceof PlayerEntity) {
/* 104 */         ((PlayerEntity)entity).refreshDisplayName();
/*     */       }
/*     */     } 
/*     */     
/* 108 */     this.cooldownComponent.startCooldown(entity, 20.0F);
/*     */   }
/*     */   
/*     */   private void changeModeEvent(LivingEntity entity, IAbility ability, Mode mode) {
/* 112 */     if (mode == Mode.COPY) {
/* 113 */       this.memoryId = -1;
/*     */       
/*     */       return;
/*     */     } 
/* 117 */     for (int i = 0; i < this.memories.size(); i++) {
/* 118 */       if (((ManeMemory)this.memories.get(i)).equals(mode.getMemory())) {
/* 119 */         this.memoryId = i;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addMemory(LivingEntity entity, LivingEntity target) {
/* 126 */     entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MANE_ADDED_MEMORY, new Object[] { target.func_145748_c_().getString() }), Util.field_240973_b_);
/* 127 */     ManeMemory memory = ManeMemory.from(target);
/* 128 */     this.memories.add(memory);
/* 129 */     Mode.create(target.func_145748_c_().getString(), memory);
/*     */   }
/*     */   
/*     */   public ManeMemory getMemory() {
/* 133 */     if (this.memoryId < 0) {
/* 134 */       return EMPTY_MEMORY;
/*     */     }
/*     */     
/* 137 */     if (this.memories.isEmpty()) {
/* 138 */       return EMPTY_MEMORY;
/*     */     }
/*     */     
/* 141 */     return this.memories.get(this.memoryId);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 146 */     nbt.func_74768_a("memoryId", this.memoryId);
/*     */     
/* 148 */     ListNBT list = new ListNBT();
/* 149 */     for (ManeMemory memory : this.memories) {
/* 150 */       list.add(memory.saveTargetData());
/*     */     }
/* 152 */     nbt.func_218657_a("memories", (INBT)list);
/*     */     
/* 154 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 159 */     this.memoryId = nbt.func_74762_e("memoryId");
/*     */     
/* 161 */     this.memories.clear();
/* 162 */     ListNBT list = (ListNBT)nbt.func_74781_a("memories");
/* 163 */     for (int i = 0; i < list.size(); i++) {
/* 164 */       CompoundNBT memoryNBT = list.func_150305_b(i);
/* 165 */       ManeMemory memory = new ManeMemory();
/* 166 */       memory.loadTargetData(memoryNBT);
/* 167 */       this.memories.add(memory);
/* 168 */       Mode.create(memory.getDisplayName(), memory);
/*     */     } 
/*     */   }
/*     */   
/*     */   private enum Mode implements IExtensibleEnum {
/* 173 */     COPY((String)ManeManeMemoryAbility.EMPTY_MEMORY);
/*     */     
/*     */     private ManeManeMemoryAbility.ManeMemory memory;
/*     */     
/*     */     Mode(ManeManeMemoryAbility.ManeMemory memory) {
/* 178 */       this.memory = memory;
/*     */     }
/*     */     
/*     */     public ManeManeMemoryAbility.ManeMemory getMemory() {
/* 182 */       return this.memory;
/*     */     }
/*     */     
/*     */     public static Mode create(String name, ManeManeMemoryAbility.ManeMemory memory) {
/* 186 */       throw new IllegalStateException("Enum not extended");
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ManeMemory {
/* 191 */     private UUID uuid = ModValues.NIL_UUID;
/* 192 */     private String displayName = "";
/* 193 */     private String profileName = "";
/* 194 */     private GameProfile gameProfile = null;
/*     */     private boolean isPlayer;
/*     */     private double doriki;
/*     */     private ResourceLocation faction;
/*     */     private ResourceLocation race;
/* 199 */     private String subRace = "";
/*     */     
/*     */     private ResourceLocation fightingStyle;
/*     */     
/*     */     public static ManeMemory from(LivingEntity target) {
/* 204 */       ManeMemory memory = new ManeMemory();
/*     */       
/* 206 */       IEntityStats stats = EntityStatsCapability.get(target);
/* 207 */       IHakiData hakiData = HakiDataCapability.get(target);
/*     */       
/* 209 */       memory.uuid = target.func_110124_au();
/* 210 */       memory.displayName = target.func_145748_c_().getString();
/* 211 */       memory.profileName = target.func_200200_C_().getString();
/* 212 */       if (target instanceof PlayerEntity) {
/* 213 */         memory.gameProfile = ((PlayerEntity)target).func_146103_bH();
/* 214 */         memory.profileName = memory.gameProfile.getName();
/* 215 */         memory.isPlayer = true;
/*     */       } 
/* 217 */       memory.doriki = stats.getDoriki();
/* 218 */       memory.faction = stats.getFaction();
/* 219 */       memory.race = stats.getRace();
/* 220 */       memory.subRace = stats.getSubRace();
/* 221 */       memory.fightingStyle = stats.getFightingStyle();
/* 222 */       memory.kenbunshokuExp = hakiData.getKenbunshokuHakiExp();
/* 223 */       memory.busoshokuHardeningExp = hakiData.getBusoshokuHakiExp();
/*     */       
/* 225 */       return memory;
/*     */     }
/*     */     private float kenbunshokuExp; private float busoshokuHardeningExp;
/*     */     public boolean isValidMemory() {
/* 229 */       return !this.uuid.equals(ModValues.NIL_UUID);
/*     */     }
/*     */     
/*     */     public UUID getUUID() {
/* 233 */       return this.uuid;
/*     */     }
/*     */     
/*     */     public String getDisplayName() {
/* 237 */       return this.displayName;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public GameProfile getGameProfile() {
/* 242 */       return this.gameProfile;
/*     */     }
/*     */     
/*     */     public String getProfileName() {
/* 246 */       return this.profileName;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 251 */       if (!(o instanceof ManeMemory)) {
/* 252 */         return false;
/*     */       }
/*     */       
/* 255 */       ManeMemory memory = (ManeMemory)o;
/*     */       
/* 257 */       if (memory.getUUID().equals(this.uuid)) {
/* 258 */         return true;
/*     */       }
/*     */       
/* 261 */       return false;
/*     */     }
/*     */     
/*     */     public CompoundNBT saveTargetData() {
/* 265 */       CompoundNBT nbt = new CompoundNBT();
/*     */       
/* 267 */       nbt.func_186854_a("uuid", this.uuid);
/* 268 */       nbt.func_74778_a("displayName", this.displayName);
/* 269 */       nbt.func_74778_a("profileName", this.profileName);
/* 270 */       nbt.func_74757_a("isPlayer", this.isPlayer);
/* 271 */       nbt.func_74780_a("doriki", this.doriki);
/* 272 */       nbt.func_74778_a("faction", this.faction.toString());
/* 273 */       nbt.func_74778_a("race", this.race.toString());
/* 274 */       nbt.func_74778_a("subRace", this.subRace);
/* 275 */       nbt.func_74778_a("fightingStyle", this.fightingStyle.toString());
/* 276 */       nbt.func_74776_a("kenbunshokuExp", this.kenbunshokuExp);
/* 277 */       nbt.func_74776_a("busoshokuHardeningExp", this.busoshokuHardeningExp);
/*     */       
/* 279 */       return nbt;
/*     */     }
/*     */     
/*     */     public void loadTargetData(CompoundNBT nbt) {
/* 283 */       this.uuid = nbt.func_186857_a("uuid");
/* 284 */       this.displayName = nbt.func_74779_i("displayName");
/* 285 */       this.profileName = nbt.func_74779_i("profileName");
/* 286 */       this.isPlayer = nbt.func_74767_n("isPlayer");
/* 287 */       this.doriki = nbt.func_74769_h("doriki");
/* 288 */       this.faction = WyHelper.getResourceLocation(nbt, "faction");
/* 289 */       this.race = WyHelper.getResourceLocation(nbt, "race");
/* 290 */       this.subRace = nbt.func_74779_i("subRace");
/* 291 */       this.fightingStyle = WyHelper.getResourceLocation(nbt, "fightingStyle");
/* 292 */       this.kenbunshokuExp = nbt.func_74760_g("kenbunshokuExp");
/* 293 */       this.busoshokuHardeningExp = nbt.func_74760_g("busoshokuHardeningExp");
/*     */       
/* 295 */       if (this.isPlayer)
/* 296 */         this.gameProfile = new GameProfile(this.uuid, this.profileName); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mane\ManeManeMemoryAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */