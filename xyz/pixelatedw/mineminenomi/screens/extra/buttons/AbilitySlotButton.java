/*     */ package xyz.pixelatedw.mineminenomi.screens.extra.buttons;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*     */ 
/*     */ public class AbilitySlotButton extends Button {
/*  20 */   private static final StringTextComponent EMPTY = new StringTextComponent("");
/*     */   
/*     */   private static final float FADE_IN_POWER = 0.15F;
/*     */   
/*     */   private static final float FADE_OUT_POWER = 0.1F;
/*     */   private final Minecraft mc;
/*     */   private final IAbilityData abilityDataProps;
/*     */   private final PlayerEntity player;
/*  28 */   private float red = 1.0F;
/*  29 */   private float green = 1.0F;
/*  30 */   private float blue = 1.0F;
/*     */   
/*     */   private float[] hoverColor;
/*     */   
/*     */   private float[] pressedColor;
/*     */   private int abilitySlot;
/*     */   private IAbility ability;
/*     */   private boolean isPressed;
/*     */   
/*     */   public AbilitySlotButton(IAbility ability, int posX, int posY, int width, int height, PlayerEntity player, Button.IPressable onPress) {
/*  40 */     super(posX, posY, width, height, (ITextComponent)EMPTY, onPress);
/*  41 */     this.mc = Minecraft.func_71410_x();
/*  42 */     this.player = player;
/*  43 */     this.abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*  44 */     this.ability = ability;
/*     */     
/*  46 */     setHoverColor(0.0F, 0.5F, 1.0F);
/*  47 */     setPressedColor(0.0F, 0.25F, 1.0F);
/*     */   }
/*     */   
/*     */   public void setHoverColor(float red, float green, float blue) {
/*  51 */     this.hoverColor = new float[] { red, green, blue };
/*     */   }
/*     */   
/*     */   public void setPressedColor(float red, float green, float blue) {
/*  55 */     this.pressedColor = new float[] { red, green, blue };
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
/*  60 */     matrixStack.func_227860_a_();
/*  61 */     matrixStack.func_227861_a_(0.0D, 0.0D, 250.0D);
/*  62 */     if (this.field_230694_p_) {
/*  63 */       this.field_230692_n_ = (mouseX >= this.field_230690_l_ && mouseY >= this.field_230691_m_ && mouseX < this.field_230690_l_ + this.field_230688_j_ && mouseY < this.field_230691_m_ + this.field_230689_k_);
/*     */       
/*  65 */       RenderSystem.enableBlend();
/*     */       
/*  67 */       if (this.field_230692_n_) {
/*  68 */         this.red -= 0.15F * partialTicks;
/*  69 */         this.green -= 0.15F * partialTicks;
/*  70 */         this.blue -= 0.15F * partialTicks;
/*     */         
/*  72 */         this.red = MathHelper.func_76131_a(this.red, this.hoverColor[0], 1.0F);
/*  73 */         this.green = MathHelper.func_76131_a(this.green, this.hoverColor[1], 1.0F);
/*  74 */         this.blue = MathHelper.func_76131_a(this.blue, this.hoverColor[2], 1.0F);
/*     */       } else {
/*     */         
/*  77 */         this.red += 0.1F * partialTicks;
/*  78 */         this.green += 0.1F * partialTicks;
/*  79 */         this.blue += 0.1F * partialTicks;
/*     */         
/*  81 */         if (this.isPressed) {
/*  82 */           this.red = MathHelper.func_76131_a(this.red, 0.0F, this.pressedColor[0]);
/*  83 */           this.green = MathHelper.func_76131_a(this.green, 0.0F, this.pressedColor[1]);
/*  84 */           this.blue = MathHelper.func_76131_a(this.blue, 0.0F, this.pressedColor[2]);
/*     */         } else {
/*     */           
/*  87 */           this.red = MathHelper.func_76131_a(this.red, 0.0F, 1.0F);
/*  88 */           this.green = MathHelper.func_76131_a(this.green, 0.0F, 1.0F);
/*  89 */           this.blue = MathHelper.func_76131_a(this.blue, 0.0F, 1.0F);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  94 */       this.mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*  95 */       RendererHelper.drawTexturedModalRect(matrixStack, this.field_230690_l_, this.field_230691_m_, 0.0F, 0.0F, 23.0F, 23.0F, 0.0F, this.red, this.green, this.blue, 1.0F);
/*  96 */       RenderSystem.disableBlend();
/*     */       
/*  98 */       if (getAbility() != null) {
/*     */         
/* 100 */         ResourceLocation res = getAbility().getIcon((LivingEntity)this.player);
/* 101 */         RendererHelper.drawIcon(res, matrixStack, (this.field_230690_l_ + 4), (this.field_230691_m_ + 4), 0.0F, 16.0F, 16.0F);
/*     */         
/* 103 */         if (this.ability != null && this.field_230692_n_ && SelectHotbarAbilitiesScreen.canShowTooltips()) {
/* 104 */           SelectHotbarAbilitiesScreen.renderAbilityTooltip(matrixStack, this.field_230690_l_, this.field_230691_m_, this.ability);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     matrixStack.func_227861_a_(0.0D, 0.0D, -250.0D);
/* 110 */     matrixStack.func_227865_b_();
/*     */   }
/*     */   
/*     */   public void setIsPressed(boolean flag) {
/* 114 */     this.isPressed = flag;
/*     */   }
/*     */   
/*     */   public void setAbility(IAbility ability) {
/* 118 */     this.ability = ability;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IAbility getAbility() {
/* 123 */     return this.ability;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\screens\extra\buttons\AbilitySlotButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */