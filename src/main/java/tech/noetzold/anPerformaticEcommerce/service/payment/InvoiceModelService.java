package tech.noetzold.anPerformaticEcommerce.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.payment.InvoiceModel;
import tech.noetzold.anPerformaticEcommerce.repository.payment.InvoiceModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceModelService {

    @Autowired
    InvoiceModelRepository invoiceModelRepository;

    public List<InvoiceModel> findAllInvoiceModel(){
        return invoiceModelRepository.findAll();
    }

    public InvoiceModel findInvoiceModelById(UUID id){
        return invoiceModelRepository.findById(id).orElse(null);
    }

    public InvoiceModel saveInvoiceModel(InvoiceModel invoiceModel){
        return invoiceModelRepository.save(invoiceModel);
    }

    public void deleteInvoiceModel(UUID id){
        invoiceModelRepository.deleteById(id);
    }
}
