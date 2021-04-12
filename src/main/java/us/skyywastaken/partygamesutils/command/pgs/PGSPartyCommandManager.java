package us.skyywastaken.partygamesutils.command.pgs;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.actors.threadpool.Arrays;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSListCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.misc.SeekManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PGSPartyCommandManager {
    private final SeekManager SEEK_MANAGER;
    private final HashMap<String, PartyCommand> partyCommandHashMap;
    private final HashMap<String, Boolean> partyCommandPermissionsHashMap;

    public PGSPartyCommandManager(SeekManager passedSeekManager) {
        SEEK_MANAGER = passedSeekManager;
        partyCommandHashMap = new HashMap<>();
        partyCommandPermissionsHashMap = new HashMap<>();
        registerPartyCommands();
        initializeCommandPermissions();
    }

    @SubscribeEvent
    public void onReceiveChatMessage(ClientChatReceivedEvent chatReceivedEvent) {
        String receivedMessage = chatReceivedEvent.message.getUnformattedText();
        if(receivedMessage.startsWith("Party", 2) && receivedMessage.contains(".pgs")) {
            int commandStartIndex = receivedMessage.indexOf(".pgs");
            String commandBody = receivedMessage.substring(commandStartIndex+5);
            String[] commandArgs = commandBody.split(" ");
            executePartyCommand(commandArgs);
        }
    }

    private void executePartyCommand(String[] passedArgs) {
        if(partyCommandHashMap.containsKey(passedArgs[0]) && partyCommandPermissionsHashMap.get(passedArgs[0])) {
            partyCommandHashMap.get(passedArgs[0]).onPartyCommand(passedArgs);
        }
    }

    private void initializeCommandPermissions() {
        partyCommandPermissionsHashMap.put("clear", true);
        partyCommandPermissionsHashMap.put("add", true);
        partyCommandPermissionsHashMap.put("remove", true);
        partyCommandPermissionsHashMap.put("list", true);
        partyCommandPermissionsHashMap.put("start", false);
        partyCommandPermissionsHashMap.put("stop", false);
    }

    private void registerPartyCommands() {
        registerPartyCommand("add", new PGSAddCommand(SEEK_MANAGER));
        registerPartyCommand("remove", new PGSRemoveCommand(SEEK_MANAGER));
        registerPartyCommand("clear", new PGSClearCommand(SEEK_MANAGER));
        registerPartyCommand("list", new PGSListCommand(SEEK_MANAGER));
        registerPartyCommand("start", new PGSStartCommand(SEEK_MANAGER));
        registerPartyCommand("stop", new PGSStopCommand(SEEK_MANAGER));
    }

    private void registerPartyCommand(String partyCommandName, PartyCommand partyCommand) {
        partyCommandHashMap.put(partyCommandName, partyCommand);
    }

    public List<String> getPartyCommandPermissions() {
        return new ArrayList<>(partyCommandPermissionsHashMap.keySet());
    }
}
