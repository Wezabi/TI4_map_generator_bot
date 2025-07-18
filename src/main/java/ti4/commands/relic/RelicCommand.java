package ti4.commands.relic;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ti4.commands.ParentCommand;
import ti4.commands.Subcommand;
import ti4.helpers.Constants;

public class RelicCommand implements ParentCommand {

    private final Map<String, Subcommand> subcommands = Stream.of(
        new RelicInfo(),
        new RelicDraw(),
        new RelicPurge(),
        new RelicExhaust(),
        new RelicReady(),
        new RelicDrawSpecific(),
        new RelicLookAtTop(),
        new RelicSend(),
        new RelicShuffleBack(),
        new RelicShowRemaining(),
        new RelicAddBackIntoDeck(),
        new RelicAddCodexRelics(),
        new RelicSendFragments(),
        new RelicPurgeFragments()).collect(Collectors.toMap(Subcommand::getName, subcommand -> subcommand));

    @Override
    public String getName() {
        return Constants.RELIC;
    }

    public String getDescription() {
        return "Relic";
    }

    @Override
    public Map<String, Subcommand> getSubcommands() {
        return subcommands;
    }
}
