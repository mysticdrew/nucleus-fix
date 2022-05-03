package net.mysticdrew.nucleusfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

@Mod(modid = NucleusFix.MODID, name = NucleusFix.NAME, version = NucleusFix.VERSION)
public class NucleusFix
{
    public static final String MODID = "nucleusfix";
    public static final String NAME = "nucleusfix";
    public static final String VERSION = "1.0";

    public static Logger getLogger()
    {
        return LogManager.getLogger(MODID);
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event)
    {
        AbstractClientPlayer player = Minecraft.getMinecraft().player;
        if (player != null && event.getEntity() instanceof EntityPlayer && event.getEntity().equals(player))
        {
            new Timer().schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    setDim();
                }
            }, 3000);
        }

    }

    private void setDim()
    {

        if (Minecraft.getMinecraft().world != null)
        {
            int worldDim = Minecraft.getMinecraft().world.provider.getDimension();
            if (Minecraft.getMinecraft().player.dimension != worldDim)
            {
                getLogger().info("Setting player dimension to: {}", worldDim);
                Minecraft.getMinecraft().player.dimension = worldDim;
            }
        }
    }

}
