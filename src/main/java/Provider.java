import java.util.*;

public class Provider implements Comparable<Provider> {

    private String name;
    private List<Currency> currencies = new ArrayList<>();
    private List<Country> countries = new ArrayList<>();

    public Provider() {
    }

    public Provider(String name, List<Currency> currencies, List<Country> countries) {
        this.name = name;
        this.currencies = currencies;
        this.countries = countries;
    }


    @Override
    public String toString() {
        return "Provider " + name + "\n Currencies: " + currencies.toString() + "\n" + "Countries" + countries.toString() + "\n";
    }

    public static TreeSet<Provider> sortByName(Set<Provider> values) {
        Comparator<Provider> comp = (Provider o1, Provider o2) -> (o1.compareTo(o2));
        TreeSet<Provider> result = new TreeSet<>(comp);
        values.stream().forEach(provider -> result.add(provider));
        System.out.println(result);
        return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public int compareTo(Provider o) {
        return (o.name.compareTo(this.name));
    }
}
