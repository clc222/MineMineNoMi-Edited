/*    */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SSyncAbilityPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class MagneticItemsAbility
/*    */   extends PassiveAbility2 {
/* 35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "magnetic_items", new Pair[] {
/* 36 */         (Pair)ImmutablePair.of("Displays the total value of maginetic items in the user's inventory", null)
/*    */       });
/* 38 */   private static final DecimalFormat FORMAT = new DecimalFormat("#0.0");
/*    */   
/*    */   private float ironValue;
/* 41 */   private float prevIronValue = -1.0F;
/*    */   
/* 43 */   public static final AbilityCore<MagneticItemsAbility> INSTANCE = (new AbilityCore.Builder("Magnetic Items", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, MagneticItemsAbility::new))
/* 44 */     .addDescriptionLine(DESCRIPTION)
/* 45 */     .build();
/*    */   
/*    */   public MagneticItemsAbility(AbilityCore<MagneticItemsAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     if (isClientSide()) {
/* 51 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/*    */       
/* 53 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*    */     } 
/*    */     
/* 56 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   public void duringPassiveEvent(LivingEntity entity) {
/* 60 */     if (!entity.field_70170_p.field_72995_K) {
/* 61 */       List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/* 62 */       this.ironValue = JikiHelper.getIronAmount(inventory);
/* 63 */       if (this.ironValue != this.prevIronValue) {
/* 64 */         if (entity instanceof PlayerEntity) {
/* 65 */           WyNetwork.sendTo(new SSyncAbilityPacket(entity.func_145782_y(), (IAbility)this), (PlayerEntity)entity);
/*    */         }
/* 67 */         this.prevIronValue = this.ironValue;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT save(CompoundNBT nbt) {
/* 74 */     nbt = super.save(nbt);
/* 75 */     nbt.func_74776_a("ironValue", this.ironValue);
/* 76 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT nbt) {
/* 81 */     super.load(nbt);
/* 82 */     this.ironValue = nbt.func_74760_g("ironValue");
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, MagneticItemsAbility ability) {
/* 87 */     RenderSystem.enableBlend();
/* 88 */     Minecraft mc = Minecraft.func_71410_x();
/* 89 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*    */     
/* 91 */     RendererHelper.drawAbilityIcon(INSTANCE, matrixStack, posX, (posY - 38), 0, 32.0F, 32.0F);
/* 92 */     String value = FORMAT.format(ability.ironValue);
/*    */     
/* 94 */     WyHelper.drawStringWithBorder(mc.field_71466_p, matrixStack, value, posX + 16 - mc.field_71466_p.func_78256_a(value) / 2, posY - 15, Color.WHITE.getRGB());
/* 95 */     RenderSystem.disableBlend();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\MagneticItemsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */