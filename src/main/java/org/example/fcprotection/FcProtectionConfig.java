
package org.example.fcprotection;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("fcprotection")
public interface FcProtectionConfig extends Config
{
	@ConfigItem(
			keyName = "enabled",
			name = "Enable protection",
			description = "Prevent attacks against Friends Chat members",
			position = 1
	)
	default boolean enabled()
	{
		return true;
	}
}
