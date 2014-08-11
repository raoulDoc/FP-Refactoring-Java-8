package firstclassfunctions;

public class Invoice {

    private final int id;
    private final String name;
    private final Customer customer;

    public Invoice(int id, String name, Customer customer) {
        this.id = id;
        this.name = name;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }


    enum Customer { ORACLE, GOOGLE, APPLE, FACEBOOK};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (customer != invoice.customer) return false;
        if (!name.equals(invoice.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + customer.hashCode();
        return result;
    }
}
