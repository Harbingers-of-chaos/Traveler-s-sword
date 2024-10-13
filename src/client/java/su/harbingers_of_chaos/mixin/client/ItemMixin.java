package su.harbingers_of_chaos.mixin.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import su.harbingers_of_chaos.TravelersSwordClient.*;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mixin(Item.class)
public class ItemMixin {
    @Shadow @Final private static Logger LOGGER;
    private Item item = (Item) (Object) this;
    @Inject(method = "use", at = @At("HEAD"))
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (item == Items.GOLDEN_SWORD) {
            long a = System.currentTimeMillis();
            double yaw = Math.toRadians(user.getYaw()+180);
            double pitch = Math.toRadians(user.getPitch()+90);
            double cosYaw = Math.cos(yaw);
            double sinYaw = Math.sin(yaw);
            double cosPitch = Math.cos(pitch);
            double sinPitch = Math.sin(pitch);
            double x = sinYaw * sinPitch;
            double y = cosPitch;
            double z = cosYaw * sinPitch;
            LOGGER.info("Yaw: " + user.getYaw());
            LOGGER.info("Pitch: " + user.getPitch());
            LOGGER.info("X: " + x);
            LOGGER.info("Y: " + y);
            LOGGER.info("Z: " + z);
            user.addVelocity(x, y,-z);
            LOGGER.info("Time " + System.currentTimeMillis());
        }
    }
}
