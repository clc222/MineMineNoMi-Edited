/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraftforge.common.ForgeMod;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class GuardingEffect
/*    */   extends ModEffect
/*    */ {
/*    */   public boolean useOnlySources = false;
/*    */   public boolean reduceSpeedAfterHit = false;
/*    */   public boolean blockSwings = false;
/* 27 */   public ArrayList<String> acceptableSources = new ArrayList<>(Collections.emptyList());
/*    */   
/*    */   public GuardingEffect(boolean canMove, boolean blockSwings) {
/* 30 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/* 31 */     if (canMove) {
/* 32 */       func_220304_a(Attributes.field_233820_c_, "7d355019-7ef9-4beb-bcba-8b2608a73380", 0.5D, AttributeModifier.Operation.ADDITION)
/* 33 */         .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "80abd0fe-3fec-42ac-8563-e39f82ab9c59", -1.0D, AttributeModifier.Operation.ADDITION)
/* 34 */         .func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "7b3a9108-6a36-11eb-9439-0242ac130002", 0.15D, AttributeModifier.Operation.ADDITION);
/*    */     } else {
/*    */       
/* 37 */       func_220304_a(Attributes.field_233821_d_, "94822875-5c1c-4b25-ba22-44ee9d50717c", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 38 */         .func_220304_a((Attribute)ForgeMod.SWIM_SPEED.get(), "b2144abf-f6cb-4994-9acd-721f949140cb", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 39 */         .func_220304_a(Attributes.field_233820_c_, "7d355019-7ef9-4beb-bcba-8b2608a73380", 1.0D, AttributeModifier.Operation.ADDITION)
/* 40 */         .func_220304_a((Attribute)ModAttributes.JUMP_HEIGHT.get(), "80abd0fe-3fec-42ac-8563-e39f82ab9c59", -1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/* 41 */         .func_220304_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get(), "7b3a9108-6a36-11eb-9439-0242ac130002", 0.25D, AttributeModifier.Operation.ADDITION);
/*    */     } 
/* 43 */     this.blockSwings = blockSwings;
/*    */   }
/*    */   
/*    */   public GuardingEffect(boolean useOnlySources, boolean makeUserSlow, boolean blockSwings, String... s) {
/* 47 */     super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
/*    */     
/* 49 */     this.useOnlySources = useOnlySources;
/* 50 */     this.reduceSpeedAfterHit = makeUserSlow;
/* 51 */     this.blockSwings = blockSwings;
/* 52 */     this.acceptableSources.addAll(Arrays.asList(s));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingRotations() {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBlockingSwings() {
/* 77 */     return this.blockSwings;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\GuardingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */