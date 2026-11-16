package org.example.fcprotection;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FcProtectionPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FcProtectionPlugin.class);
		RuneLite.main(args);
	}
}