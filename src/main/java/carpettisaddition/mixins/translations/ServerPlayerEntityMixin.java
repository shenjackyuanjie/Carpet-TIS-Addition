package carpettisaddition.mixins.translations;

import carpettisaddition.translations.ServerPlayerEntityWithClientLanguage;
import net.minecraft.network.packet.c2s.play.ClientSettingsC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements ServerPlayerEntityWithClientLanguage
{
	private String clientLanguage$CTA = "en_US";

	@Inject(method = "setClientSettings", at = @At("HEAD"))
	private void recordClientLanguage(ClientSettingsC2SPacket packet, CallbackInfo ci)
	{
		this.clientLanguage$CTA = ((ClientSettingsC2SPacketAccessor)packet).getLanguage();
	}

	@Override
	public String getClientLanguage$CTA()
	{
		return this.clientLanguage$CTA;
	}
}