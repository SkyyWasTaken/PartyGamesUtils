package us.skyywastaken.partygamesutils.command.PGU.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import us.skyywastaken.partygamesutils.command.SubCommand;
import us.skyywastaken.partygamesutils.util.minigame.MiniGame;
import us.skyywastaken.partygamesutils.util.minigame.MiniGameManager;

import java.util.List;

public class PGUDisplayGameInfoCommand implements SubCommand {
    @Override
    public void onCommand(ICommandSender commandSender, String[] args) {
        MiniGame miniGame = MiniGameManager.getCurrentMiniGame();
        commandSender.addChatMessage(new ChatComponentText(
                "GAME INFO:\n"
                        + "Game name: " + miniGame.getMiniGameType().name()
                        + "Game number: " + miniGame.getGameNumber() + "/8"
        ));
    }

    @Override
    public List<String> getTabCompletions(ICommandSender sender, String[] args, BlockPos blockPos) {
        return null;
    }

    @Override
    public String getHelpInformation() {
        return null;
    }
}
