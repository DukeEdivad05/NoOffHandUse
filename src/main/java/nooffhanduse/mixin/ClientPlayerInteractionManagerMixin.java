package nooffhanduse.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void interactBlockMixin(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        boolean shield = player.getOffHandStack().getItem() == Items.SHIELD;
        boolean food = player.getOffHandStack().isFood();
        boolean rocket = player.getOffHandStack().getItem() == Items.FIREWORK_ROCKET;
        if(!shield && !food && !rocket) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactBlockInternal", at = @At("HEAD"), cancellable = true)
    private void interactBlockInternalMixin(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        boolean shield = player.getOffHandStack().getItem() == Items.SHIELD;
        boolean food = player.getOffHandStack().isFood();
        boolean rocket = player.getOffHandStack().getItem() == Items.FIREWORK_ROCKET;
        if(!shield && !food && !rocket) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
    private void interactItemMixin(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        boolean shield = player.getOffHandStack().getItem() == Items.SHIELD;
        boolean food = player.getOffHandStack().isFood();
        boolean rocket = player.getOffHandStack().getItem() == Items.FIREWORK_ROCKET;
        if(!shield && !food && !rocket) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Inject(method = "interactEntity", at = @At("HEAD"), cancellable = true)
    private void interactEntityMixin(PlayerEntity player, Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        cir.setReturnValue(ActionResult.FAIL);
    }

    @Inject(method = "interactEntityAtLocation", at = @At("HEAD"), cancellable = true)
    private void interactEntityAtLocationMixin(PlayerEntity player, Entity entity, EntityHitResult hitResult, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        cir.setReturnValue(ActionResult.FAIL);
    }
}
