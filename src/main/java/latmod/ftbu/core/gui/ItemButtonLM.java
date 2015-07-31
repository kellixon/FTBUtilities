package latmod.ftbu.core.gui;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public abstract class ItemButtonLM extends ButtonLM
{
	public ItemStack item = null;
	
	public ItemButtonLM(GuiLM g, int x, int y, int w, int h)
	{ super(g, x, y, w, h); }
	
	public ItemButtonLM setItem(ItemStack is)
	{ item = is; return this; }
	
	public void setBackground(TextureCoords t)
	{ background = t; }
	
	public void render()
	{ if(item != null) gui.drawItem(item, posX, posY); }
}