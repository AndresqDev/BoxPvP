package lib.ziggurat;

import lib.ziggurat.tab.ZigguratAdapter;
import lib.ziggurat.tab.utils.BufferedTabObject;
import lib.ziggurat.utils.ObjectUtility;
import net.kappa.boxpvp.files.list.decoration.TabFile;
import net.kappa.boxpvp.utils.PlaceholderUtil;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TabAdapter implements ZigguratAdapter {
    @Override
    public Set<BufferedTabObject> getSlots(Player player) {
        return Stream.of(TabFile.left, TabFile.middle, TabFile.right, TabFile.far_right)
                .flatMap(List::stream)
                .map(obj -> ObjectUtility.createTabEntry(PlaceholderUtil.setPlaceholders(player, obj.getText()), obj.getColumn(), obj.getSlot(), obj.getPing(), obj.getHead()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public String getClassicSlot(Player player) {
        return PlaceholderUtil.setPlaceholders(player, TabFile.classic_format);
    }

    @Override
    public String getHeader() {
        return TabFile.header;
    }

    @Override
    public String getFooter() {
        return TabFile.footer;
    }
}
