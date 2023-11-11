package tech.noetzold.anPerformaticEcommerce.service.payment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.InvoiceModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.InvoiceModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("invoice")
public class InvoiceModelService {

    @Autowired
    InvoiceModelRepository invoiceModelRepository;

    @Transactional
    public List<InvoiceModel> findAllInvoiceModel(){
        return invoiceModelRepository.findAll();
    }

    @Transactional
    public InvoiceModel findInvoiceModelById(UUID id){
        return invoiceModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public InvoiceModel updateInvoiceModel(UUID id, InvoiceModel invoiceModel){
        invoiceModel.setInvoiceId(id);
        return invoiceModelRepository.save(invoiceModel);
    }
    
    @Transactional
    public InvoiceModel saveInvoiceModel(InvoiceModel invoiceModel){
        return invoiceModelRepository.save(invoiceModel);
    }

    @Transactional
    public void deleteInvoiceModel(UUID id){
        invoiceModelRepository.deleteById(id);
    }
}
