/*     */ package xyz.pixelatedw.mineminenomi.abilities.karu;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSyncAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class KarmaAbility extends PassiveAbility2 {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "karma", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Converts received damage into karma.", null)
/*     */       });
/*  37 */   private static final DecimalFormat FORMAT = new DecimalFormat("#0.0");
/*     */   
/*     */   public static final float MAX_KARMA = 100.0F;
/*  40 */   public static final AbilityCore<KarmaAbility> INSTANCE = (new AbilityCore.Builder("Karma", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, KarmaAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .setHidden()
/*  43 */     .build();
/*     */   
/*  45 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnDamageEvent(this::onDamageTaken);
/*     */   
/*  47 */   private float karma = 0.0F;
/*  48 */   private float prevKarma = 0.0F;
/*     */   
/*     */   public KarmaAbility(AbilityCore<KarmaAbility> ability) {
/*  51 */     super(ability);
/*     */     
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*     */     
/*  55 */     if (isClientSide()) {
/*  56 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/*     */       
/*  58 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*     */     } 
/*     */   }
/*     */   
/*     */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/*  63 */     if (damageSource.func_82725_o() || this.karma >= 100.0F) {
/*  64 */       return damage;
/*     */     }
/*     */     
/*  67 */     if (damageSource instanceof ModDamageSource) {
/*  68 */       boolean isInternal = ((ModDamageSource)damageSource).isInternal();
/*  69 */       if (!isInternal) {
/*  70 */         addKarma(entity, damage);
/*     */       }
/*     */     } else {
/*     */       
/*  74 */       addKarma(entity, damage);
/*     */     } 
/*     */     
/*  77 */     return damage;
/*     */   }
/*     */   
/*     */   public void addKarma(LivingEntity entity, float amount) {
/*  81 */     this.prevKarma = this.karma;
/*  82 */     this.karma = MathHelper.func_76131_a(this.karma + amount, 0.0F, 100.0F);
/*  83 */     if (entity instanceof PlayerEntity) {
/*  84 */       WyNetwork.sendTo(new SSyncAbilityPacket(entity.func_145782_y(), (IAbility)this), (PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public float getKarma() {
/*  89 */     return this.karma;
/*     */   }
/*     */   
/*     */   public void setPrevKarma(float prevKarma) {
/*  93 */     this.prevKarma = prevKarma;
/*     */   }
/*     */   
/*     */   public float getPrevKarma() {
/*  97 */     return this.prevKarma;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 102 */     nbt.func_74776_a("karma", this.karma);
/* 103 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 108 */     this.karma = nbt.func_74760_g("karma");
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, KarmaAbility ability) {
/* 113 */     RenderSystem.enableBlend();
/* 114 */     Minecraft mc = Minecraft.func_71410_x();
/* 115 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*     */     
/* 117 */     RendererHelper.drawAbilityIcon(IngaZarashiAbility.INSTANCE, matrixStack, posX, (posY - 38), 0, 32.0F, 32.0F);
/*     */     
/* 119 */     String karma = FORMAT.format(ability.getKarma());
/* 120 */     WyHelper.drawStringWithBorder(
/* 121 */         (Minecraft.func_71410_x()).field_71466_p, matrixStack, karma, posX + 16 - mc.field_71466_p
/*     */ 
/*     */         
/* 124 */         .func_78256_a(karma) / 2, posY - 25, Color.WHITE
/*     */         
/* 126 */         .getRGB());
/* 127 */     RenderSystem.disableBlend();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\karu\KarmaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */