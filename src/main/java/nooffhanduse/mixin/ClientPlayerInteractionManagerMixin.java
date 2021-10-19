package nooffhanduse.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void interactBlockMixin(ClientPlayerEntity player, ClientWorld world, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (hand == Hand.OFF_HAND) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactEntity", at = @At("HEAD"), cancellable = true)
    private void interactEntityMixin(PlayerEntity player, Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (hand == Hand.OFF_HAND) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
    private void interactItemMixin(PlayerEntity player, World world, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (hand == Hand.OFF_HAND) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactEntityAtLocation", at = @At("HEAD"), cancellable = true)
    private void interactEntityAtLocationMixin(PlayerEntity player, Entity entity, EntityHitResult hitResult, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (hand == Hand.OFF_HAND) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }
}
