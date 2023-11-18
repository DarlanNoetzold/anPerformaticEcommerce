package tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoletoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID boletoId;
    private String codigoDeBarras;
    private String linhaDigitavel;
    private String beneficiarioNome;
    private String beneficiarioCnpjCpf;
    private String beneficiarioEndereco;
    private String sacadoNome;
    private String sacadoCnpjCpf;
    private String sacadoEndereco;
    private double valor;
    private Date dataVencimento;
    private String numeroDocumento;
    private String instrucoesPagamento;
    private double multa;
    private double jurosAtraso;
    private String logoBanco;
    private String codigoBanco;
    private String identificacaoBoleto;
    private Date dataEmissao;
    private String localPagamento;
    private String codigoAutenticacao;
    private String informacoesContato;
}
