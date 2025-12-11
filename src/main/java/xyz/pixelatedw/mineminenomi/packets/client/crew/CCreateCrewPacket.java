/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.CrewEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class CCreateCrewPacket
/*    */ {
/*    */   private String name;
/*    */   
/*    */   public CCreateCrewPacket() {}
/*    */   
/*    */   public CCreateCrewPacket(String name) {
/* 33 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 38 */     buffer.writeInt(this.name.length());
/* 39 */     buffer.func_180714_a(this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CCreateCrewPacket decode(PacketBuffer buffer) {
/* 44 */     CCreateCrewPacket msg = new CCreateCrewPacket();
/* 45 */     int len = buffer.readInt();
/* 46 */     msg.name = buffer.func_150789_c(len);
/* 47 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CCreateCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 54 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             ExtendedWorldData worldProps = ExtendedWorldData.get();
/* 60 */             boolean hasSakeBottle = (!serverPlayerEntity.func_184614_ca().func_190926_b() && serverPlayerEntity.func_184614_ca().func_77973_b().equals(ModItems.SAKE_BOTTLE.get()));
/*    */             
/*    */             boolean isAlreadyInCrew = (worldProps.getCrewWithMember(serverPlayerEntity.func_110124_au()) != null);
/*    */             
/*    */             if (!hasSakeBottle || isAlreadyInCrew || !props.isPirate()) {
/*    */               return;
/*    */             }
/*    */             
/*    */             Crew crew = new Crew(message.name, (LivingEntity)serverPlayerEntity);
/*    */             
/*    */             CrewEvent.Create event = new CrewEvent.Create((PlayerEntity)serverPlayerEntity, crew);
/*    */             
/*    */             if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
/*    */               worldProps.addCrew(crew);
/*    */               
/*    */               crew.create(((PlayerEntity)serverPlayerEntity).field_70170_p);
/*    */               
/*    */               if (CommonConfig.INSTANCE.isCrewWorldMessageEnabled()) {
/*    */                 TranslationTextComponent newCrewMsg = new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_CREW, new Object[] { message.name });
/*    */                 for (PlayerEntity target : ((PlayerEntity)serverPlayerEntity).field_70170_p.func_217369_A()) {
/*    */                   target.func_145747_a((ITextComponent)new StringTextComponent(TextFormatting.GOLD + newCrewMsg.getString()), Util.field_240973_b_);
/*    */                 }
/*    */               } 
/*    */             } 
/*    */           });
/*    */     }
/* 86 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CCreateCrewPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */