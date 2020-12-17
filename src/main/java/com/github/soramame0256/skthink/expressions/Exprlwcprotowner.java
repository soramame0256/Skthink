package com.github.soramame0256.skthink.expressions;

import org.bukkit.event.Event;

import java.util.Iterator;

import org.bukkit.OfflinePlayer;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.eclipse.jdt.annotation.Nullable;
import com.griefcraft.lwc.LWC;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.*;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserUtils;
import ch.njol.util.Checker;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
@SuppressWarnings("unused")
public class Exprlwcprotowner extends SimpleExpression<OfflinePlayer> {

	LWC lwcinstance = com.griefcraft.lwc.LWC.getInstance();
	Expression<Block> block;
	
	static {
		Skript.registerExpression(Exprlwcprotowner.class, OfflinePlayer.class, ExpressionType.COMBINED, "%block%'[s] [lwc] (protect|lock)ed owner");
	}
	public boolean isSingle() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
	
	public Class<? extends OfflinePlayer> getReturnType() {
		// TODO 自動生成されたメソッド・スタブ
		return OfflinePlayer.class;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		// TODO 自動生成されたメソッド・スタブ
		this.block = (Expression<Block>) exprs[0];
		return true;
	}

	public String toString(@Nullable Event e, boolean debug) {
		// TODO 自動生成されたメソッド・スタブ
		return "protection block" + block.toString(e, debug);
	}

	@Override
	@Nullable
	protected OfflinePlayer[] get(Event e) {
		// TODO 自動生成されたメソッド・スタブ
		Block b = block.getSingle(e);
		if (lwcinstance.findProtection(b).getBukkitOwner() == null) return null;
		return new OfflinePlayer[] {lwcinstance.findProtection(b).getBukkitOwner()};
	}
	@SuppressWarnings("unchecked")
	@Override
	public Class <?>[] acceptChange(final ChangeMode mode) {
		if (mode == ChangeMode.SET) {
			return CollectionUtils.array(String.class);
		}
		return null;
	}
	@Override
	public void change(Event e, Object[] o, ChangeMode mode) {
		Block b = block.getSingle(e);
		if (mode == ChangeMode.SET) {
			lwcinstance.findProtection(b).setOwner((String) o[0]);
		}
	}

}
