package dev.carlvs.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.carlvs.shopping.domain.InvoiceItem;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long> {
}
