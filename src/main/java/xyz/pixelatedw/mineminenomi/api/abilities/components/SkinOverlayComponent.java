/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SSetOverlayLayersPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class SkinOverlayComponent
/*     */   extends AbilityComponent<IAbility>
/*     */ {
/*  23 */   private Map<Integer, AbilityOverlay> overlays = new HashMap<>();
/*  24 */   private Set<Integer> shownOverlays = new HashSet<>();
/*     */   
/*     */   public SkinOverlayComponent(IAbility ability, AbilityOverlay mainOverlay, AbilityOverlay... abilityOverlays) {
/*  27 */     super(ModAbilityKeys.SKIN_OVERLAY, ability);
/*  28 */     addOverlay(mainOverlay);
/*  29 */     for (AbilityOverlay overlay : abilityOverlays) {
/*  30 */       addOverlay(overlay);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addOverlay(AbilityOverlay overlay) {
/*  35 */     this.overlays.put(Integer.valueOf(overlay.hashCode()), overlay);
/*     */   }
/*     */   
/*     */   public void removeOverlay(AbilityOverlay overlay) {
/*  39 */     this.overlays.remove(Integer.valueOf(overlay.hashCode()));
/*     */   }
/*     */   
/*     */   public Optional<AbilityOverlay> getShownOverlay(AbilityOverlay.OverlayPart... parts) {
/*  43 */     if (parts.length <= 0) {
/*  44 */       return Optional.empty();
/*     */     }
/*     */     
/*  47 */     List<AbilityOverlay.OverlayPart> overlays = new ArrayList<>();
/*  48 */     Collections.addAll(overlays, parts);
/*     */     
/*  50 */     return this.shownOverlays.stream()
/*  51 */       .map(hash -> (AbilityOverlay)this.overlays.get(hash))
/*  52 */       .filter(Objects::nonNull)
/*  53 */       .filter(overlay -> overlays.contains(overlay.getOverlayPart()))
/*  54 */       .findFirst();
/*     */   }
/*     */   
/*     */   public boolean hasAnyShownOverlay(AbilityOverlay.OverlayPart... parts) {
/*  58 */     if (parts.length <= 0) {
/*  59 */       return false;
/*     */     }
/*     */     
/*  62 */     List<AbilityOverlay.OverlayPart> overlays = new ArrayList<>();
/*  63 */     Collections.addAll(overlays, parts);
/*     */     
/*  65 */     return 
/*     */ 
/*     */ 
/*     */       
/*  69 */       (this.shownOverlays.stream().map(hash -> (AbilityOverlay)this.overlays.get(hash)).filter(Objects::nonNull).filter(overlay -> overlays.contains(overlay.getOverlayPart())).count() > 0L);
/*     */   }
/*     */   
/*     */   public void showAll(LivingEntity entity) {
/*  73 */     ensureIsRegistered();
/*  74 */     this.shownOverlays.addAll(this.overlays.keySet());
/*  75 */     updateShownOverlays(entity);
/*     */   }
/*     */   
/*     */   public void show(LivingEntity entity, AbilityOverlay overlay) {
/*  79 */     ensureIsRegistered();
/*  80 */     if (!this.overlays.containsKey(Integer.valueOf(overlay.hashCode()))) {
/*  81 */       addOverlay(overlay);
/*     */     }
/*  83 */     this.shownOverlays.add(Integer.valueOf(overlay.hashCode()));
/*  84 */     updateShownOverlays(entity);
/*     */   }
/*     */   
/*     */   public void hideAll(LivingEntity entity) {
/*  88 */     this.shownOverlays.clear();
/*  89 */     updateShownOverlays(entity);
/*     */   }
/*     */   
/*     */   public void hide(LivingEntity entity, AbilityOverlay overlay) {
/*  93 */     this.shownOverlays.remove(Integer.valueOf(overlay.hashCode()));
/*  94 */     updateShownOverlays(entity);
/*     */   }
/*     */   
/*     */   public void setShownOverlays(Set<Integer> overlays) {
/*  98 */     this.shownOverlays = overlays;
/*     */   }
/*     */   
/*     */   private void updateShownOverlays(LivingEntity entity) {
/* 102 */     if (!entity.field_70170_p.field_72995_K)
/* 103 */       WyNetwork.sendToAllTrackingAndSelf(new SSetOverlayLayersPacket(entity, getAbility(), this.shownOverlays), (Entity)entity); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\SkinOverlayComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */