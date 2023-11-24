package net.kappa.boxpvp.files;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.decoration.ScoreboardFile;
import net.kappa.boxpvp.files.list.decoration.TabFile;
import net.kappa.boxpvp.files.list.messages.AdminFile;
import net.kappa.boxpvp.files.list.messages.MessagesFile;
import net.kappa.boxpvp.files.list.system.DataFile;

public class FileProvider {
    public FileProvider() {
        this.setup();
    }

    private void setup() {
        new DataFile();
        new OptionsFile();
        new TabFile();
        new ScoreboardFile();
        new AdminFile();
        new MessagesFile();
    }
}
