package lib.ziggurat.tab;

import net.kappa.boxpvp.files.list.decoration.TabFile;

public class ZigguratThread extends Thread {
    private final Ziggurat ziggurat;

    public ZigguratThread(Ziggurat ziggurat) {
        this.ziggurat = ziggurat;
        this.start();
    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (true) {
            try {
                if (TabFile.tab_type.equals("modern"))
                    this.ziggurat.getTablists().values().forEach(ZigguratTablistModern::update);
                else this.ziggurat.getTablistsClassic().values().forEach(ZigguratTablistClassic::update);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ZigguratThread.sleep(TabFile.tab_update_rate);
                continue;
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}

