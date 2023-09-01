package ti4.commands.custom;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.commands.game.SetDeck;
import ti4.generator.Mapper;
import ti4.helpers.Constants;
import ti4.helpers.Helper;
import ti4.map.Map;
import ti4.map.MapSaveLoadManager;
import ti4.message.MessageHelper;

public class ChangeToBaseGame extends CustomSubcommandData {
    public ChangeToBaseGame() {
        super(Constants.CHANGE_TO_BASE_GAME, "Remove PoK ACs/SOs/POs/Agendas");
        addOptions(new OptionData(OptionType.STRING, Constants.REMOVE_CODEX_AC, "Remove Codex AC too? (y/n)").setRequired(false));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Map activeMap = getActiveMap();
        OptionMapping codexOption = event.getOption(Constants.REMOVE_CODEX_AC);
        String codex = "";
        if (codexOption != null) {
            codex = codexOption.getAsString();
            if (codex.equalsIgnoreCase("y")) {
                MessageHelper.sendMessageToChannel(event.getChannel(), "Removed Codex ACs.");
            }

        }
        activeMap.setBaseGameMode(true);
        Helper.removePoKComponents(activeMap, codex);
        SetDeck.setDeck(event, activeMap, "agenda_deck", Mapper.getDecks().get("agendas_base_game"));
        MessageHelper.sendMessageToChannel(event.getChannel(), "Removed PoK components.");
        MapSaveLoadManager.saveMap(activeMap, event);

    }
}