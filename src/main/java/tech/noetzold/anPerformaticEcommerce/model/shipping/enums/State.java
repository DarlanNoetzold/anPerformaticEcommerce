package tech.noetzold.anPerformaticEcommerce.model.shipping.enums;

public enum State {
    INIT,
    PREPARING_PACKAGE,
    SENDING_TO_REGION,
    SENDING_TO_YOUR_ADDRESS,
    RETRY,
    RETURN,
    DELIVERED
}
