
package org.example.fcprotection;

import javax.inject.Inject;

import com.google.inject.Provides;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.Player;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
		name = "FC Attack Protection",
		description = "Blocks attacks against Friends Chat members"
)
public class FcProtectionPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private FcProtectionConfig config;

	@Provides
	FcProtectionConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FcProtectionConfig.class);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (!config.enabled())
		{
			return;
		}

		if (!"Attack".equalsIgnoreCase(event.getMenuOption()))
		{
			return;
		}

		if (!isPlayerOption(event.getMenuAction()))
		{
			return;
		}

		Player target = event.getMenuEntry().getPlayer();

		if (target != null && target.isFriendsChatMember())
		{
			event.consume();

			client.addChatMessage(
					ChatMessageType.GAMEMESSAGE,
					"",
					"FC Protection: Attack blocked!",
					null
			);
		}
	}

	private boolean isPlayerOption(MenuAction action)
	{
		switch (action)
		{
			case PLAYER_FIRST_OPTION:
			case PLAYER_SECOND_OPTION:
			case PLAYER_THIRD_OPTION:
			case PLAYER_FOURTH_OPTION:
			case PLAYER_FIFTH_OPTION:
			case PLAYER_SIXTH_OPTION:
			case PLAYER_SEVENTH_OPTION:
			case PLAYER_EIGHTH_OPTION:
				return true;

			default:
				return false;
		}
	}
}
