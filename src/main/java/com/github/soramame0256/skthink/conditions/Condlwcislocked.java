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

public class Condlwcislocked extends Condition {
	LWC lwcinstance = com.griefcraft.lwc.LWC.getInstance();
	Expression<Block> block;
	
    static {
        Skript.registerCondition(Condlwcislocked.class, "%block% (1¦is|2¦is(n't| not)) [lwc] (protect|lock)ed");
    }
    
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		// TODO 自動生成されたメソッド・スタブ
		this.block = (Expression<Block>) exprs[0];
		setNegated(parseResult.mark == 1);
		return true;
	}

	public String toString(@Nullable Event e, boolean debug) {
		return "protect" + block.toString(e, debug);
	}

	@Override
	public boolean check(Event e) {
		Block b = block.getSingle(e);
		if (lwcinstance.findProtection(b) != null) return isNegated();
		return (lwcinstance.findProtection(b) != null) ? isNegated() : !isNegated();
	}
	
	
}
