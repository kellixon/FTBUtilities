package com.feed_the_beast.ftbu.cmd.admin;

import com.feed_the_beast.ftbl.api.cmd.CommandLM;
import com.feed_the_beast.ftbl.util.LMAccessToken;
import com.feed_the_beast.ftbu.FTBUGuiHandler;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;

public class CmdUnclaim extends CommandLM
{
    public CmdUnclaim()
    {
        super("unclaim");
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender ics, @Nonnull String[] args) throws CommandException
    {
        EntityPlayerMP ep = getCommandSenderAsPlayer(ics);
        NBTTagCompound data = new NBTTagCompound();
        data.setLong("T", LMAccessToken.generate(ep));
        FTBUGuiHandler.instance.openGui(ep, FTBUGuiHandler.ADMIN_CLAIMS, data);
    }
}