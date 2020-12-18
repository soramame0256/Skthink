package com.github.soramame0256.skthink.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.github.soramame0256.Skthink;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.*;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
public class Exprconnectablecount extends SimpleExpression<Integer>{

	Expression<Integer> integer;
	public boolean isSingle() {
		return true;
	}
	static {
		Skript.registerExpression(Exprconnectablecount.class, Integer.class, ExpressionType.COMBINED, "[skthink] [player] connect[ion|able] limit");
	}
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	public String toString(@Nullable Event e, boolean debug) {
		return integer.toString(e, debug);
//		return "connectable count" + integer.toString(e, debug);
	}

	@Override
	@Nullable
	protected Integer[] get(Event e) {
		Number n = Skthink.getInstance().getConfig().getLong("effects.connectlimit.count");
		return new Integer[] {(Integer) n};
	}
	@SuppressWarnings("unchecked")
	public Class <?>[] acceptChange(final ChangeMode mode) {
		if (mode == ChangeMode.SET||mode == ChangeMode.ADD||mode == ChangeMode.REMOVE||mode == ChangeMode.RESET||mode == ChangeMode.DELETE) {
			return CollectionUtils.array(Integer.class);
		}
		return null;
	}
	public void change(Event e, Object[] o, ChangeMode mode) {
		Integer i = (Integer) o[0];
		if (mode == ChangeMode.SET) {
			Skthink.getInstance().getConfig().set("effect.connectionlimit.count", i);
		}
		if (mode == ChangeMode.ADD) {
			Skthink.getInstance().getConfig().set("effect.connectionlimit.count", (Skthink.getInstance().getConfig().getInt("effect.connectionlimit.count") + i));
		}
		if (mode == ChangeMode.REMOVE) {
			Skthink.getInstance().getConfig().set("effect.connectionlimit.count", (Skthink.getInstance().getConfig().getInt("effect.connectionlimit.count") - i));
		}
		if (mode == ChangeMode.RESET) {
			Skthink.getInstance().getConfig().set("effect.connectionlimit.count", 32767);
		}
		if (mode == ChangeMode.DELETE) {
			Skthink.getInstance().getConfig().set("effect.connectionlimit.enable", false);
			Skthink.getInstance().getConfig().set("effect.connectionlimit.count", 32767);
		}
		
	}

}
