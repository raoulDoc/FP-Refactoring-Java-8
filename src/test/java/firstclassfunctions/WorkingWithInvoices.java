package firstclassfunctions;

import org.junit.Before;
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
    private final Invoice facebookInvoice = new Invoice(4, "JavascriptTraining", Invoice.Customer.FACEBOOK);
    private final Invoice oracleInvoice = new Invoice(10, "Java8Training", Invoice.Customer.ORACLE);
    private List<Invoice> invoices = Arrays.asList(
            appleInvoice,
            facebookInvoice,
            oracleInvoice
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
        assertThat(trainingInvoices, is(Arrays.asList(facebookInvoice,
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

}
