import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws NotDirectoryException {
        Set<Provider> providers = new HashSet<>();
        Set<Provider> result = new HashSet<>();

        setMockData(providers);

        XStreamWorker worker = new XStreamWorker();

        worker.serialize( "C:\\tmp", "providers", providers);

        try {
            result = (HashSet<Provider>) worker.deserialize("C:\\tmp\\providers.xml");
        } catch (IOException e) {
            System.err.println("Unable to deserialize + \n" + e.toString());
        }

        TreeSet<Provider> sortedValues = Provider.sortByName(result);

    }

    private static void setMockData(Set<Provider> providers) {
        for (int id = 0; id < 10; id++) {
            List<Country> countries = getRandomCountries(3);
            List<Currency> currencies = getRandomCurrencies(3);
            providers.add(new Provider("provider id: " + (id + 1), currencies, countries));
        }
    }

    private static List<Country> getRandomCountries(int countryCount) {
        List<Country> list = new ArrayList<>();
        for (int i = 0; i < countryCount; i++) {
            list.add(Country.values()[(int) (Math.random() * Country.values().length)]);
        }
        return list;
    }

    private static List<Currency> getRandomCurrencies(int currencyCount) {
        List<Currency> list = new ArrayList<>();
        for (int i = 0; i < currencyCount; i++) {
            list.add(Currency.values()[(int) (Math.random() * Currency.values().length)]);
        }
        return list;
    }
}
