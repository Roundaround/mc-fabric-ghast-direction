package me.roundaround.ghastdirection.mixin;

import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.mob.GhastEntity$LookAtTargetGoal")
public abstract class GhastEntityMixin {
  @Shadow
  @Final
  private GhastEntity ghast;

  @Inject(
      method = "tick",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/entity/mob/GhastEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;"
      ),
      cancellable = true
  )
  public void tick(CallbackInfo info) {
    Vec3d velocity = this.ghast.getVelocity();
    if (velocity.lengthSquared() < 0.01) {
      info.cancel();
    }
  }
}
