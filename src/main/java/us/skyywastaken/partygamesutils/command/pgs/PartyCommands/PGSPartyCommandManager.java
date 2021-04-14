package us.skyywastaken.partygamesutils.command.pgs.PartyCommands;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.command.pgs.PGSManager;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSAddCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSClearCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSListCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSRemoveCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStartCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSStopCommand;
import us.skyywastaken.partygamesutils.command.pgs.subcommands.PGSToggleBlacklistCommand;

import java.util.HashMap;

public class PGSPartyCommandManager {
    private final PGSManager PGS_MANAGER;
    private final HashMap<PGSPartyCommandType, PartyCommand> partyCommandHashMap;

    public PGSPartyCommandManager(PGSManager passedPGSManager) {
        this.PGS_MANAGER = passedPGSManager;
        this.partyCommandHashMap = new HashMap<>();
        registerPartyCommands();
    }

    @SubscribeEvent
    public void onReceiveChatMessage(ClientChatReceivedEvent chatReceivedEvent) {
        if (!PGS_MANAGER.getPartyCommandsEnabled()) {
            return;
        }
        String receivedMessage = chatReceivedEvent.message.getUnformattedText();
        if (receivedMessage.startsWith("Party", 2) && receivedMessage.contains(".pgs")) {
            int commandStartIndex = receivedMessage.indexOf(".pgs");
            String commandBody = receivedMessage.substring(commandStartIndex + 5);
            String[] commandArgs = commandBody.split(" ");
            executePartyCommand(commandArgs);
        }
    }

    private void executePartyCommand(String[] passedArgs) {
        PGSPartyCommandType commandType = PGSPartyCommandType.fromString(passedArgs[0]);
        if (partyCommandHashMap.containsKey(commandType) && PGS_MANAGER.getPartyPermissionEnabled(commandType)) {
            partyCommandHashMap.get(commandType).onPartyCommand(passedArgs);
        }
    }

    private void registerPartyCommands() {
        registerPartyCommand(PGSPartyCommandType.ADD, new PGSAddCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.REMOVE, new PGSRemoveCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.CLEAR, new PGSClearCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.LIST, new PGSListCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.START, new PGSStartCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.STOP, new PGSStopCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.TOGGLEBLACKLIST, new PGSToggleBlacklistCommand(PGS_MANAGER));
    }

    private void registerPartyCommand(PGSPartyCommandType partyCommandType, PartyCommand partyCommand) {
        partyCommandHashMap.put(partyCommandType, partyCommand);
    }
}
