/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Registro: 2081 - Notas Fiscais de Saídas - Documentos Referenciados - Notas.
 * Pg 1236.
 *
 * @author luiz.barcellos
 */
public class Reg2081_Notas_Fiscais_Saida_Referenciadas implements Exportacao_DomSis {

    private final String id = "2081";
    private String operacao = "";
    private String documento = "";
    private String cliente_fornecedor = "";
    private String modelo = "";
    private String dt_emmissao = "";
    private String serie = "";
    private String sub_serie = "";
    private String emitente = "";
    private String chave_nf_cte = "";

    /**
     * 0 - Entrada e 1 - Saída
     *
     * @param operacao
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Informar inscrição do cliente / fornecedor cadastrado no sistema. Quando
     * encontrar mais de um utilizar o de menor código.
     *
     * @param cliente_fornecedor
     */
    public void setCliente_fornecedor(String cliente_fornecedor) {
        this.cliente_fornecedor = cliente_fornecedor;
    }

    /**
     * Informar o código equivalente ao do modelo do Documento fiscal conforme
     * relação abaixo: 1-Nota Fiscal, modelo 01, código 01; 4-Nota Fiscal de
     * Produtor, modelo 04, código 04; 5-Nota Fiscal/Conta de Energia Elétrica,
     * modelo 06, código 06; 6-Nota Fiscal de Serviço de Transporte, modelo 07,
     * código 07; 7-Conhecimento de Transporte Rodoviário de Cargas, modelo 08,
     * código 08; 8-Conhecimento de Transporte Aquaviário de Cargas, modelo 09,
     * código 09; 9-Conhecimento Aéreo, modelo 10, código 10; 10-Conhecimento de
     * Transporte Ferroviário de Cargas, modelo 11, código 11; 11-Bilhete de
     * Passagem Rodoviário, modelo 13, código 13; 12-Bilhete de Passagem
     * Aquaviário, modelo 14, código 14; 13-Bilhete de Passagem e Nota de
     * Bagagem, modelo 15, código 15; 14-Bilhete de Passagem Ferroviário, modelo
     * 16, código 16; 15-Despacho de Transporte, modelo 17, código 17; 16-Resumo
     * Movimento Diário, modelo 18, código 18; 17-Ordem de Coleta de Carga,
     * modelo 20, código 20; 18-Nota Fiscal de Serviço de Comunicação, modelo
     * 21, código 21; 19-Nota Fiscal de Serviço de Telecomunicações, modelo 22,
     * código 22; 20-Autorização de Carregamento e Transporte, modelo 24, código
     * 24; 21-Manifesto de Carga, modelo 25, código 25; 23-Nota Fiscal Fatura
     * Serviço, código 03; 24-Nota Fiscal Serviço, código 03; 25-GNRE, código
     * 23; 26-Conhecimento de Transporte Multimodal de Cargas, código 26;
     * 27-Nota Fiscal/Conta de Fornecimento de Água Canalizada, código 29;
     * 28-Nota Fiscal/Conta de Fornecimento de Gás Canalizado, código 28;
     * 29-Manifesto de Vôo, código 30; 30-Bilhete/Recibo do Passageiro, código
     * 31; 31-Nota Fiscal Eletrônica, código 55; 32-Nota Fiscal Avulsa, código
     * 1B; 34-Nota Fiscal de Serviços - Modelo Simplificado, código 3A; 35-Nota
     * Fiscal de Serviços - Modelo Avulso, 3B; 36-Conhecimento de Transporte de
     * Cargas Avulso, código 8B; 38-Nota Fiscal de Transporte Ferroviário de
     * Carga, código 27; 39-Nota Fiscal Eletrônica de Serviço, Código 03;
     * 40-Conhecimento de Transporte Eletrônico - CT-e, Modelo 57; 43- Nota
     * Fiscal Eletrônica para Consumidor Final - NFC-e, código 65.
     *
     * @param modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setDt_emmissao(String dt_emmissao) {
        this.dt_emmissao = dt_emmissao;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setSub_serie(String sub_serie) {
        this.sub_serie = sub_serie;
    }

    /**
     * 0 - Emissão própria / 1 – Terceiros
     *
     * @param emitente
     */
    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    public void setChave_nf_cte(String chave_nf_cte) {
        this.chave_nf_cte = chave_nf_cte;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|" + operacao + "|" + documento + "|"
                + cliente_fornecedor + "|" + modelo + "|" + dt_emmissao + "|"
                + serie + "|" + sub_serie + "|" + emitente + "|"
                + chave_nf_cte + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg2081_Notas_Fiscais_Saida_Referenciadas i = new Reg2081_Notas_Fiscais_Saida_Referenciadas();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
