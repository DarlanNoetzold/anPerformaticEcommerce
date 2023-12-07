# anPerformaticEcommerce

## Desenvolvimento
* Desenvolvido com Java 20;
* Framework Spring Boot;
* Package manager Maven;
* Message Broker RabbitMQ;
* Cache com Redis;
* PostgreSQL Database;
* Docker para CI/CD;
* Spring Security para Authorization e Authetication;
* Rest Assured e Junit5 para testes de integração;
* FeignClient para comunicar com o API's Gateway.

## Projeto
* A ideia do projeto é criar um Ecommerce performático, escalável e com uma implatação rápida. Por isso, foram cria 4 API Gateways cada um com um objetivo específico;
* Este repositório tem o núcleo, o Back-End do Ecommerce desenvolvido em Spring Boot;
* O núcleo foi desenvolvido em Spring porque este framework tem melhores resultados em tempo de resposta de requisição, proporcinando ao usuário um experiência agradável ao navegar no site;
* Já os API Gateways foram desenvolvidos em Quarkus, pois este framework usa menos recurso e tem o uptime menor, proporcionando uma escalabilidade mais eficaz.

## Gateway's:
* Payment: https://github.com/DarlanNoetzold/payment-gateway
* Catalog: https://github.com/DarlanNoetzold/catalog-gateway
* Shipping: https://github.com/DarlanNoetzold/shipping-gateway
* Promotion: https://github.com/DarlanNoetzold/promotion-gateway

## Testes de performance:
* Up time do ecommerce central desenvolvido em Spring e média de Up time dos gateways em Quarkus, dividido em antes e depois de melhorias de poerformance aplicadas:
![spring_vs_quarkus_uptime](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/e35de3b8-a9aa-472f-a08b-3b0b1da07d31)

* Regressão Linear da aplicação Spring com testes em diferentes quantidades de requisições paralelas analisando diferntes recursos:
![post_spring_req_test](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/6a7dec1e-f186-42dd-995b-f249d8d8fbc7)
![post_spring_heap_test](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/1f050024-8473-4fe4-ad6b-02b4962c5f4e)
![post_spring_cpu_test](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/10abfe4d-97b3-4b0f-8a08-fba8efc46802)

* Regressão Linear da aplicação Quarkus com testes em diferentes quantidades de requisições paralelas analisando diferntes recursos:
![post_quarkus_req_test](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/30bde6d1-2352-4217-bcf8-237743f845ac)
![post_quarkus_heap_test](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/a746b7b0-a8f7-4b52-bb44-cf8f36783084)
![post_quarkus_cpu_test](https://github.com/DarlanNoetzold/anPerformaticEcommerce/assets/41628589/f85ccbbd-b65d-4087-8dda-1f046ccd794e)


## Documentation
* Postman: [https://documenter.getpostman.com/view/16000387/2s9YRGx9Hr](https://documenter.getpostman.com/view/16000387/2s9YRGx9Hr)

---

⭐️ From [DarlanNoetzold](https://github.com/DarlanNoetzold)
