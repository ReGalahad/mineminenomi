package xyz.pixelatedw.mineminenomi.api;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.wypi.abilities.Ability;

public class AbilityArgument implements ArgumentType<Ability>
{
	private StringReader reader;
	private static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> DEFAULT_SUGGESTIONS_BUILDER = SuggestionsBuilder::buildFuture;
	private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggestionsBuilder = DEFAULT_SUGGESTIONS_BUILDER;

	@Override
	public Ability parse(StringReader reader) throws CommandSyntaxException
	{
		ResourceLocation resourcelocation = ResourceLocation.read(reader);
		Ability ability = GameRegistry.findRegistry(Ability.class).getValue(resourcelocation);
		return ability;
	}

	public static <S> Ability getAbility(CommandContext<S> context, String name)
	{
		return context.getArgument(name, Ability.class);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder)
	{
		StringReader stringreader = new StringReader(builder.getInput());
		stringreader.setCursor(builder.getStart());
		this.reader = stringreader;
		ResourceLocation res = null;
		try
		{
			res = ResourceLocation.read(this.reader);
			this.suggestionsBuilder = this::suggestAbility;
		}
		catch (CommandSyntaxException e)
		{
			e.printStackTrace();
		}

		if (res == null)
			return null;

		return this.fillSuggestions(builder);
	}

	private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder)
	{
		return ISuggestionProvider.suggestIterable(GameRegistry.findRegistry(Ability.class).getKeys(), builder);
	}

	public CompletableFuture<Suggestions> fillSuggestions(SuggestionsBuilder builder)
	{
		return this.suggestionsBuilder.apply(builder.createOffset(this.reader.getCursor()));
	}
}
