package com.github.soramame0256.skthink.conditions;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.block.*;
import org.eclipse.jdt.annotation.Nullable;
import com.griefcraft.lwc.LWC;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserUtils;
import ch.njol.util.Kleenean;
@SuppressWarnings("unused")
public class Condlwcislockableblock extends Condition {

	LWC lwcinstance = com.griefcraft.lwc.LWC.getInstance();
	Expression<Block> block;
	
	static {
		Skript.registerCondition(Condlwcislockableblock.class, "%block% (1¦is|2¦is(n't| not)) [lwc] (protect|lock)able");
	}
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.block = (Expression<Block>) exprs[0];
		setNegated(parseResult.mark == 1);
		return true;
	}

	public String toString(@Nullable Event e, boolean debug) {
		return "islockableblock" + block.toString(e, debug);
	}

	@Override
	public boolean check(Event e) {
		Block b = block.getSingle(e);
		if (lwcinstance.isProtectable(b)) return isNegated();
		return (lwcinstance.isProtectable(b)) ? isNegated() : ! isNegated();
	}
	
}
