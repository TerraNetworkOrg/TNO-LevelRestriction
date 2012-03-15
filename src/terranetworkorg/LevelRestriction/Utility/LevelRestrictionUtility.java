package terranetworkorg.LevelRestriction.Utility;

import java.io.*;

import terranetworkorg.LevelRestriction.LevelRestriction;

public class LevelRestrictionUtility {
    public static LevelRestriction plugin;

    public LevelRestrictionUtility(LevelRestriction instance) {
        plugin = instance;
    }

    public void copy(InputStream inputThis, File sFile) throws IOException{
        InputStream in = inputThis;
        OutputStream out = new FileOutputStream(sFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        in.close();
    }
}
