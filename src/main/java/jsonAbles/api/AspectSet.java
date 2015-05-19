package jsonAbles.api;

import jsonAbles.config.json.AspectType;
import thaumcraft.api.aspects.Aspect;

public class AspectSet {
	public String tag;
	public int color;
	public Aspect aspect1;
	public Aspect aspect2;

	public AspectSet(AspectType type) {
		this(type.tag, type.color, Aspect.getAspect(type.aspect1), Aspect.getAspect(type.aspect2));
	}

	public AspectSet(String tag, int color, Aspect aspect1, Aspect aspect2) {
		this.tag = tag;
		this.color = color;
		this.aspect1 = aspect1;
		this.aspect2 = aspect2;
	}

}
