package com.feed_the_beast.ftbquests.integration.jei;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.item.FTBQuestsItems;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * @author LatvianModder
 */
public class QuestCategory implements IRecipeCategory<QuestWrapper>
{
	public static final ResourceLocation UID = new ResourceLocation(FTBQuests.MOD_ID, "quests");
	public static final ResourceLocation TEXTURE = new ResourceLocation(FTBQuests.MOD_ID + ":textures/gui/jei/quest.png");
	public static QuestCategory instance;

	private final IDrawable background;
	private final IDrawable icon;

	public QuestCategory(IGuiHelper guiHelper)
	{
		instance = this;
		background = guiHelper.createDrawable(TEXTURE, 0, 0, 144, 74);
		icon = guiHelper.createDrawableIngredient(new ItemStack(FTBQuestsItems.BOOK));
	}

	@Override
	public ResourceLocation getUid()
	{
		return UID;
	}

	@Override
	public Class<QuestWrapper> getRecipeClass()
	{
		return QuestWrapper.class;
	}

	@Override
	public String getTitle()
	{
		return I18n.format("ftbquests.quests");
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public IDrawable getIcon()
	{
		return icon;
	}

	@Override
	public void setIngredients(QuestWrapper questWrapper, IIngredients iIngredients)
	{
		//FIXME
	}

	@Override
	public void setRecipe(IRecipeLayout layout, QuestWrapper entry, IIngredients ingredients)
	{
		IGuiItemStackGroup stacks = layout.getItemStacks();
		int is = Math.min(9, entry.input.size());

		for (int i = 0; i < is; i++)
		{
			stacks.init(i, true, (i % 3) * 18, (i / 3) * 18 + 20);
		}

		for (int i = 0; i < Math.min(9, entry.output.size()); i++)
		{
			stacks.init(i + is, false, (i % 3) * 18 + (5 * 18), (i / 3) * 18 + 20);
		}

		stacks.set(ingredients);
	}
}