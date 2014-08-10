package firstclassfunctions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static firstclassfunctions.Invoice.Customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by raoul-gabrielurma on 09/08/2014.
 */
public class WorkingWithInvoices {

    private final Invoice appleInvoice = new Invoice(1, "DesignConsulting", Invoice.Customer.APPLE);
    private final Invoice facebookInvoice1 = new Invoice(4, "JavascriptTraining", Invoice.Customer.FACEBOOK);
    private final Invoice oracleInvoice = new Invoice(10, "Java8Training", Invoice.Customer.ORACLE);
    private final Invoice facebookInvoice2 = new Invoice(12, "PenTesting", Invoice.Customer.FACEBOOK);
    private List<Invoice> invoices = Arrays.asList(
            appleInvoice,
            facebookInvoice1,
            oracleInvoice,
            facebookInvoice2
    );

    // Step 1
    @Test
    public void testFilterInvoicesFromOracle(){
        List<Invoice> result = filterInvoicesFromOracle(invoices);
        assertThat(result, is(Arrays.asList(oracleInvoice)));
    }

    private List<Invoice> filterInvoicesFromOracle(List<Invoice> invoices){
        List<Invoice> result = new ArrayList<>();
        for(Invoice invoice: invoices){
            if(invoice.getCustomer() == Invoice.Customer.ORACLE){
                result.add(invoice);
            }
        }
        return result;
    }

    // Step 2a
    @Test
    public void testFilterInvoicesFromCustomer(){
        List<Invoice> appleInvoices = filterInvoicesFromCustomer(invoices, Customer.APPLE);
        assertThat(appleInvoices, is(Arrays.asList(appleInvoice)));

        List<Invoice> oracleInvoices = filterInvoicesFromCustomer(invoices, Customer.ORACLE);
        assertThat(oracleInvoices, is(Arrays.asList(oracleInvoice)));
    }


    private List<Invoice> filterInvoicesFromCustomer(List<Invoice> invoices, Customer customer){
        List<Invoice> result = new ArrayList<>();
        for(Invoice invoice: invoices){
            if(invoice.getCustomer() == customer){
                result.add(invoice);
            }
        }
        return result;
    }

    // Step 2b
    @Test
    public void testFilterInvoicesEndingWith(){
        List<Invoice> trainingInvoices = filterInvoicesEndingWith(invoices, "Training");
        assertThat(trainingInvoices, is(Arrays.asList(facebookInvoice1,
                                                      oracleInvoice)));
    }


    private List<Invoice> filterInvoicesEndingWith(List<Invoice> invoices, String suffix){
        List<Invoice> result = new ArrayList<>();
        for(Invoice invoice: invoices){
            if(invoice.getName().endsWith(suffix)){
                result.add(invoice);
            }
        }
        return result;
    }

    // Step 3
    @Test
    public void testFilterInvoicesReuseCode(){
        List<Invoice> oracleInvoices = filterInvoicesBad(invoices, Customer.ORACLE, "", true);
        assertThat(oracleInvoices, is(Arrays.asList(oracleInvoice)));
    }

    private List<Invoice> filterInvoicesBad(List<Invoice> invoices, Customer customer, String suffix, boolean flag){
        List<Invoice> result = new ArrayList<>();
        for(Invoice invoice: invoices){
            if((flag && invoice.getCustomer() == customer)
               || (!flag && invoice.getName().endsWith(suffix))){
                result.add(invoice);
            }
        }
        return result;
    }

    // Step 4
    public void testFilterWithObjects(){
        List<Invoice> specificInvoices = filterInvoices(invoices, new FacebookTraining());
        assertThat(specificInvoices, is(Arrays.asList(facebookInvoice2)));
    }

    private List<Invoice> filterInvoices(List<Invoice> invoices, InvoicePredicate p){
        List<Invoice> result = new ArrayList<>();
        for(Invoice invoice: invoices){
            if(p.test(invoice)){
                result.add(invoice);
            }
        }
        return result;
    }

    interface InvoicePredicate{
        boolean test(Invoice invoice);
    }

    private class FacebookTraining implements InvoicePredicate{
        @Override
        public boolean test(Invoice invoice) {
            return invoice.getCustomer() == Customer.FACEBOOK && invoice.getName().endsWith("Training");
        }
    }

    // Step 5
    public void testFilterWithMethodReferences(){
        List<Invoice> oracleInvoices = filterInvoices(invoices, this::isOracleInvoice);
        assertThat(oracleInvoices, is(Arrays.asList(oracleInvoice)));

        List<Invoice> trainingInvoices = filterInvoices(invoices, this::isTrainingInvoice);
        assertThat(oracleInvoices, is(Arrays.asList(facebookInvoice1, oracleInvoice)));
    }

    public boolean isOracleInvoice(Invoice invoice){
        return invoice.getCustomer() == Customer.ORACLE;
    }

    public boolean isTrainingInvoice(Invoice invoice){
        return invoice.getName().endsWith("Training");
    }

    // Step 6
    public void testFilterWithLambdas(){
        List<Invoice> oracleInvoices =
                filterInvoices(invoices,
                               (Invoice invoice) -> invoice.getCustomer() == Customer.ORACLE);
        assertThat(oracleInvoices, is(Arrays.asList(oracleInvoice)));

        List<Invoice> trainingInvoices =
                filterInvoices(invoices,
                        (Invoice invoice) -> invoice.getName().endsWith(("Training")));
        assertThat(oracleInvoices, is(Arrays.asList(facebookInvoice1, oracleInvoice)));
    }
}
