package at.eliastrummer.pojo;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PriceAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) throws Exception {
        return Double.parseDouble(v.replace("€", "").trim());
    }

    @Override
    public String marshal(Double v) throws Exception {
        return String.format("%.2f€", v).replace(",", ".");
    }
    
}
