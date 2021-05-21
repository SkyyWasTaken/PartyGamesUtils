package us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import us.skyywastaken.partygamesutils.command.PartyCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyAddCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyClearCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyListCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyRemoveCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyStartCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyStopCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.command.partycommands.commands.PGSPartyToggleBlacklistCommand;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.PartyCommandSettings;
import us.skyywastaken.partygamesutils.feature.gameseeking.settings.SeekSettings;

import java.util.Arrays;
import java.util.HashMap;

public class PGSPartyCommandManager {
    private final SeekSettings PGS_MANAGER;
    private final PartyCommandSettings PARTY_COMMAND_SETTINGS;
    private final HashMap<PGSPartyCommandType, PartyCommand> partyCommandHashMap;

    public PGSPartyCommandManager(SeekSettings passedSeekSettings, PartyCommandSettings passedPartyCommandSettings) {
        this.PGS_MANAGER = passedSeekSettings;
        this.PARTY_COMMAND_SETTINGS = passedPartyCommandSettings;
        this.partyCommandHashMap = new HashMap<>();
        registerPartyCommands();
    }

    @SubscribeEvent
    public void onReceiveChatMessage(ClientChatReceivedEvent chatReceivedEvent) {
        if (!PARTY_COMMAND_SETTINGS.getPartyCommandsEnabled()) {
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
        if (partyCommandHashMap.containsKey(commandType) && PARTY_COMMAND_SETTINGS.getPartyPermissionEnabled(commandType)) {
            String[] argsToPass = Arrays.copyOfRange(passedArgs, 1, passedArgs.length);
            partyCommandHashMap.get(commandType).onPartyCommand(argsToPass);
        }
    }

    private void registerPartyCommands() {
        registerPartyCommand(PGSPartyCommandType.ADD, new PGSPartyAddCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.REMOVE, new PGSPartyRemoveCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.CLEAR, new PGSPartyClearCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.LIST, new PGSPartyListCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.START, new PGSPartyStartCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.STOP, new PGSPartyStopCommand(PGS_MANAGER));
        registerPartyCommand(PGSPartyCommandType.TOGGLEBLACKLIST, new PGSPartyToggleBlacklistCommand(PGS_MANAGER));
    }

    private void registerPartyCommand(PGSPartyCommandType partyCommandType, PartyCommand partyCommand) {
        partyCommandHashMap.put(partyCommandType, partyCommand);
    }
}
