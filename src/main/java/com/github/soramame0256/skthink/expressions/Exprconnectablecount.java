package com.github.soramame0256.skthink.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.*;
import ch.njol.util.Kleenean;
public class Exprconnectablecount extends SimpleExpression<Number>{

	Expression<Number> number;
	public boolean isSingle() {
		return true;
	}

	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	public String toString(@Nullable Event e, boolean debug) {
		return "connectable count" + number.toString(e, debug);
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		
		return null;
	}

}
