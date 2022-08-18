package dev.carlvs.shopping.services;

import org.springframework.stereotype.Service;

import dev.carlvs.shopping.client.CustomerClient;
import dev.carlvs.shopping.client.ProductClient;
import dev.carlvs.shopping.domain.Invoice;
import dev.carlvs.shopping.domain.InvoiceItem;
import dev.carlvs.shopping.imports.Customer;
import dev.carlvs.shopping.imports.Product;
import dev.carlvs.shopping.repository.InvoiceItemRepository;
import dev.carlvs.shopping.repository.InvoiceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    private InvoiceItemRepository invoiceItemRepository;

    private CustomerClient customerClient;

    private ProductClient productClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceItemRepository invoiceItemRepository,
            CustomerClient customerClient, ProductClient productClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemRepository = invoiceItemRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
    }

    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }


    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());

        if (invoiceDB !=null){
            return  invoiceDB;
        }

        invoice.setState("CREATED");

        invoiceDB = invoiceRepository.save(invoice);

        invoiceDB.getItems().forEach(invoiceItem -> {
            productClient.addStockInProduct(invoiceItem.getProductId(), invoiceItem.getQuantity());
        });
        return invoiceDB;
    }


    @Override
    public Invoice updateInvoice(Invoice invoice) {

        Invoice invoiceDB = getInvoice(invoice.getId());

        if (invoiceDB == null){
            return  null;
        }

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());

        return invoiceRepository.save(invoiceDB);
    }


    @Override
    public Invoice setStateDeletedInvoice(Invoice invoice) {

        Invoice invoiceDB = getInvoice(invoice.getId());

        if (invoiceDB == null){
            return  null;
        }

        invoiceDB.setState("DELETED");

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if(invoice != null){
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());

            invoice.setItems(listItem);
        }
        return invoice;
    }
}
