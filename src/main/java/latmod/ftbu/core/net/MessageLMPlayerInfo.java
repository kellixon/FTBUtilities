package latmod.ftbu.core.net;
import io.netty.buffer.ByteBuf;
import latmod.ftbu.core.world.*;
import latmod.ftbu.mod.FTBU;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.*;

public class MessageLMPlayerInfo extends MessageLM<MessageLMPlayerInfo> implements IClientMessageLM<MessageLMPlayerInfo>
{
	public NBTTagCompound info;
	
	public MessageLMPlayerInfo() { }
	
	public MessageLMPlayerInfo(LMPlayerServer p0)
	{
		info = new NBTTagCompound();
		
		if(p0 == null)
		{
			for(LMPlayerServer p : LMWorldServer.inst.players)
			{
				NBTTagCompound tag = new NBTTagCompound();
				p.getInfo(tag);
				info.setTag("" + p.playerID, tag);
			}
		}
		else
		{
			NBTTagCompound tag = new NBTTagCompound();
			p0.getInfo(tag);
			info.setTag("" + p0.playerID, tag);
		}
	}
	
	public void fromBytes(ByteBuf bb)
	{
		info = LMNetHelper.readTagCompound(bb);
	}
	
	public void toBytes(ByteBuf bb)
	{
		LMNetHelper.writeTagCompound(bb, info);
	}
	
	public IMessage onMessage(MessageLMPlayerInfo m, MessageContext ctx)
	{ FTBU.proxy.handleClientMessage(m, ctx); return null; }
	
	@SideOnly(Side.CLIENT)
	public void onMessageClient(MessageLMPlayerInfo m, MessageContext ctx)
	{
		for(LMPlayerClient p : LMWorldClient.inst.players)
		{
			NBTTagCompound tag = (NBTTagCompound)m.info.getTag("" + p.playerID);
			if(tag != null) p.receiveInfo(tag);
		}
	}
}