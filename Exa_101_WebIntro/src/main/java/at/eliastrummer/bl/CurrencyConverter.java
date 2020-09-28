package at.eliastrummer.bl;

public class CurrencyConverter {
    public static float convert(float from, String toCurrency) {
        float conversionRate;
        
        switch(toCurrency.toLowerCase()) {
            case "dollar": conversionRate = 1.19f; break;
            case "yen": conversionRate = 0.0081f; break;
            case "rupien": conversionRate = 0.011f; break;
            default: throw new IllegalArgumentException("Invalid currency");
        }
        
        return from * conversionRate;
    }
}