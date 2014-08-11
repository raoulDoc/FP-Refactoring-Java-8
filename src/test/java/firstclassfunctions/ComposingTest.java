package firstclassfunctions;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ComposingTest {

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

    @Test
    public void testPredicateComposition() {

        Predicate<Invoice> isFacebookInvoice = this::isFacebookInvoice;

        Predicate<Invoice> isTrainingInvoice = this::isTrainingInvoice;

        Predicate<Invoice> isAppleInvoice = this::isAppleInvoice;

        List<Invoice> facebookAndTraining =
                invoices.stream()
                        .filter(isFacebookInvoice.and(isTrainingInvoice))
                        .collect(toList());

        assertThat(facebookAndTraining, is(Arrays.asList(facebookInvoice1)));

        List<Invoice> facebookOrApple =
                invoices.stream()
                        .filter(isFacebookInvoice.or(isAppleInvoice))
                        .collect(toList());

        assertThat(facebookOrApple, is(Arrays.asList(appleInvoice, facebookInvoice1, facebookInvoice2)));
    }

    public boolean isFacebookInvoice(Invoice invoice) {
        return invoice.getCustomer() == Invoice.Customer.FACEBOOK;
    }

    public boolean isAppleInvoice(Invoice invoice) {
        return invoice.getCustomer() == Invoice.Customer.APPLE;
    }

    public boolean isTrainingInvoice(Invoice invoice) {
        return invoice.getName().endsWith("Training");
    }


    @Test
    public void testLetterPipeline() {
        Function<Letter, Letter> addHeader = Letter::addDefaultHeader;

        Function<Letter, Letter> processingPipeline =
                addHeader.andThen(Letter::checkSpelling).andThen(Letter::addDefaultFooter);

        Letter result = processingPipeline.apply(new Letter("Java 8 FTW!"));

        assertEquals("From her majesty:\nJava 8 for the win!\nKind regards", result.getMessage());

    }


}
