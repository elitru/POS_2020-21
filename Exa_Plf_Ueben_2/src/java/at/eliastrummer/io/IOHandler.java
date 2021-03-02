package at.eliastrummer.io;

import at.eliastrummer.pojo.Market;
import java.io.File;
import javax.xml.bind.JAXB;

public class IOHandler {
    public static Market readMarket(String path) {
        return JAXB.unmarshal(new File(path), Market.class);
    }
}
