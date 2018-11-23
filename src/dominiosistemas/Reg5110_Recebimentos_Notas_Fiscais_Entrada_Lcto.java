/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

/**
 * Fixo 5110 – Identificador do registro. Registro filho do registro 5100. Pg
 * 1302
 *
 * @author luiz.barcellos
 */
public class Reg5110_Recebimentos_Notas_Fiscais_Entrada_Lcto implements Exportacao_DomSis {

    private final String id = "5110";
    private String dt_lanc = "";
    private String cta_deb = "";
    private String cta_cred = "";
    private String valor = "";
    private String cod_hist = "";
    private String historico = "";
    private String usuario = "";
    private String filial = "";

    public void setDt_lanc(String dt_lanc) {
        this.dt_lanc = dt_lanc;
    }

    public void setCta_deb(String cta_deb) {
        this.cta_deb = cta_deb;
    }

    public void setCta_cred(String cta_cred) {
        this.cta_cred = cta_cred;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setCod_hist(String cod_hist) {
        this.cod_hist = cod_hist;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    /**
     * Informar o nome do usuário responsável pelo lançamento. Deixar em branco
     * para utilizar o usuário que realizar a importação.
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Caso seja empresa filial, informar o código da filial conforme cadastro
     * no Domínio Contabilidade.
     *
     * @param filial
     */
    public void setFilial(String filial) {
        this.filial = filial;
    }

    @Override
    public String getLayout() throws Exception {
        String linha = "|" + id + "|"
                + dt_lanc + "|"
                + cta_deb + "|"
                + cta_cred + "|"
                + valor + "|"
                + cod_hist + "|"
                + historico + "|"
                + usuario + "|"
                + filial + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

}
