package jsonAbles.api;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.wiki.IWikiProvider;
import jsonAbles.config.json.BrewType;

public class BrewSet {

	public String name;
	public String key;
	public int color;
	public int cost;
	public String[] potionEffectNames;
	public int[] potionEffectDuration;
	public int[] potionEffectAmplifier;
	public boolean[] potionEffectParticles;
	
	public BrewSet(BrewType type) {
		this(type.name, type.key, type.color, type.cost, type.potionEffectNames, type.potionEffectDuration, type.potionEffectAmplifier, type.potionEffectParticles);
	}

	public BrewSet(String name, String key, int color, int cost, String[] potionEffectNames, int[] potionEffectDuration, int[] potionEffectAmplifier, boolean[] potionEffectParticles) {
		this.name = name;
		this.key = key;
		this.color = color;
		this.cost = cost;
		this.potionEffectNames = potionEffectNames;
		this.potionEffectDuration = potionEffectDuration;
		this.potionEffectAmplifier = potionEffectAmplifier;
		this.potionEffectParticles = potionEffectParticles;
	}
	
}
