package tech.noetzold.anPerformaticEcommerce.model.payment.enums;

public enum PaymentState {
    INIT,
    FRAUD_CHECK_APPROVED,
    FRAUD_CHECK_DENIED,
    WITH_ERROR,
    APPROVED,
    NO_PENDING_ACTION
}
