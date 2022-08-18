package dev.carlvs.shopping.services;

import java.util.List;

import dev.carlvs.shopping.domain.Invoice;

public interface InvoiceService {
    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice setStateDeletedInvoice(Invoice invoice);

    public Invoice getInvoice(Long id);
}
