/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg0110_Produtos_Vigencia implements Exportacao_DomSis {

    private final String id = "0110";
    private final String descricao = "VIGENCIA INICIAL";
    private String cst_entrada = "";
    private String vin_credito = "";
    private String base_credito = "";
    private String aproveita_cred_prop = "";
    private String cred_aliq_dif = "";
    private String aliq_pis_entr = "";
    private String aliq_cofins_entr = "";
    private String cred_unid_med_entr = "";
    private String unid_trib_dif_entr = "";
    private String unid_trib_entr = "";
    private String fator_conv_entr = "";
    private String vlr_pis_entr = "";
    private String vlr_cofins_entr = "";
    private String cst_saidas = "";
    private String tp_contr = "";
    private String nat_receita = "";
    private String cod_rec_pis_saida = "";
    private String cod_rec_cofins_saida = "";
    private String deb_ali_dif_saida = "";
    private String aliq_pis_saida = "";
    private String aliq_cofins_saida = "";
    private String deb_unid_med_saidas = "";
    private String unid_trib_dif = "";
    private String unid_trib_saidas = "";
    private String fator_conv_saidas = "";
    private String vlr_pis_saidas = "";
    private String vlr_cofins_saidas = "";
    private String tabela_sped = "";
    private String marca_grupo_sped = "";
    private String pis_incid_cumul = "";
    private String cofins_incid_cumul = "";
    private String icms_cst_cson_entr = "";
    private String icms_cst_cson_saidas = "";
    private String icms_aliq = "";
    private String ipi_cst_entr = "";
    private String ipi_cst_saidas = "";
    private String ipi_periodicidade = "";
    private String ipi_aliq = "";
    private String simp_nac_trib_pis_cofins = "";
    private String excl_bc_outras_desp = "";
    private String fundepec = "";
    private String tp_produto = "";
    private String prodepe = "";
    private String cod_apur = "";
    private String redu_bc = "";
    private String pis_cofins_perc_redu = "";
    private String simp_nac_tp_trib_pis_cofins = "";
    private String cod_rec_pis_entr = "";
    private String cod_rec_cofins_entr = "";
    private String bc_st = "";
    private String perc_st = "";
    private String vlr_unir_st = "";
    private String ipi_cod_rec = "";

    public void setCst_entrada(String cst_entrada) {
        this.cst_entrada = cst_entrada;
    }

    public void setVin_credito(String vin_credito) {
        this.vin_credito = vin_credito;
    }

    /**
     * Informar 01=Aquisição de bens para revenda; 02=Aquisição de bens
     * utilizados como insumo; 03=Aquisição de serviços utilizados como insumo;
     * 04=Energia elétrica e térmica, inclusive sob a forma de vapor;
     * 05=Aluguéis de prédios; 06=Aluguéis de máquinas e equipamentos;
     * 07=Armazenagem de mercadoria e frete na operação de venda;
     * 08=Contraprestaçõ es de arrendamento mercantil; 09=Máquinas, equipamentos
     * e outros bens incorporados ao ativo imobilizado (crédito sobre encargos
     * de depreciação); 10=Máquinas, equipamentos e outros bens incorporados ao
     * ativo imobilizado (crédito com base no valor de aquisição);
     * 11=Amortização de edificações e benfeitorias em imóveis; 12=Devolução de
     * Vendas Sujeitas à Incidência Não- Cumulativa; 13=Outras Operações com
     * Direito a Crédito; 14=Atividade de Transporte de Cargas – Subcontratação;
     * 15=Atividade Imobiliária – Custo Incorrido de Unidade Imobiliária;
     * 16=Atividade Imobiliária – Custo Orçado de unidade não concluída;
     * 17=Atividade de Prestação de Serviços de Limpeza, Conservação e
     * Manutenção – vale-transporte, vale-refeição ou vale-alimentação,
     * fardamento ou uniforme; 18=Estoque de abertura de bens.
     *
     * @param base_credito
     */
    public void setBase_credito(String base_credito) {
        this.base_credito = base_credito;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param aproveita_cred_prop
     */
    public void setAproveita_cred_prop(String aproveita_cred_prop) {
        this.aproveita_cred_prop = aproveita_cred_prop;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param cred_aliq_dif
     */
    public void setCred_aliq_dif(String cred_aliq_dif) {
        this.cred_aliq_dif = cred_aliq_dif;
    }

    public void setAliq_pis_entr(String aliq_pis_entr) {
        this.aliq_pis_entr = aliq_pis_entr;
    }

    public void setAliq_cofins_entr(String aliq_cofins_entr) {
        this.aliq_cofins_entr = aliq_cofins_entr;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param cred_unid_med_entr
     */
    public void setCred_unid_med_entr(String cred_unid_med_entr) {
        this.cred_unid_med_entr = cred_unid_med_entr;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param unid_trib_dif_entr
     */
    public void setUnid_trib_dif_entr(String unid_trib_dif_entr) {
        this.unid_trib_dif_entr = unid_trib_dif_entr;
    }

    public void setUnid_trib_entr(String unid_trib_entr) {
        this.unid_trib_entr = unid_trib_entr;
    }

    public void setFator_conv_entr(String fator_conv_entr) {
        this.fator_conv_entr = fator_conv_entr;
    }

    public void setVlr_pis_entr(String vlr_pis_entr) {
        this.vlr_pis_entr = vlr_pis_entr;
    }

    public void setVlr_cofins_entr(String vlr_cofins_entr) {
        this.vlr_cofins_entr = vlr_cofins_entr;
    }

    public void setCst_saidas(String cst_saidas) {
        this.cst_saidas = cst_saidas;
    }

    /**
     * Informar: N=Não cumulativo, C= Cumulativo ou S=Sem incidência.
     *
     * @param tp_contr
     */
    public void setTp_contr(String tp_contr) {
        this.tp_contr = tp_contr;
    }

    public void setNat_receita(String nat_receita) {
        this.nat_receita = nat_receita;
    }

    /**
     * Informar o código de recolhimento existente no cadastro do imposto
     * COFINS-ST.
     *
     * @param cod_rec_pis_saida
     */
    public void setCod_rec_pis_saida(String cod_rec_pis_saida) {
        this.cod_rec_pis_saida = cod_rec_pis_saida;
    }

    /**
     * Informar o código de recolhimento existente no cadastro do imposto
     * COFINS-ST.
     *
     * @param cod_rec_cofins_saida
     */
    public void setCod_rec_cofins_saida(String cod_rec_cofins_saida) {
        this.cod_rec_cofins_saida = cod_rec_cofins_saida;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param deb_ali_dif_saida
     */
    public void setDeb_ali_dif_saida(String deb_ali_dif_saida) {
        this.deb_ali_dif_saida = deb_ali_dif_saida;
    }

    public void setAliq_pis_saida(String aliq_pis_saida) {
        this.aliq_pis_saida = aliq_pis_saida;
    }

    public void setAliq_cofins_saida(String aliq_cofins_saida) {
        this.aliq_cofins_saida = aliq_cofins_saida;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param deb_unid_med_saidas
     */
    public void setDeb_unid_med_saidas(String deb_unid_med_saidas) {
        this.deb_unid_med_saidas = deb_unid_med_saidas;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param unid_trib_dif
     */
    public void setUnid_trib_dif(String unid_trib_dif) {
        this.unid_trib_dif = unid_trib_dif;
    }

    public void setUnid_trib_saidas(String unid_trib_saidas) {
        this.unid_trib_saidas = unid_trib_saidas;
    }

    public void setFator_conv_saidas(String fator_conv_saidas) {
        this.fator_conv_saidas = fator_conv_saidas;
    }

    public void setVlr_pis_saidas(String vlr_pis_saidas) {
        this.vlr_pis_saidas = vlr_pis_saidas;
    }

    public void setVlr_cofins_saidas(String vlr_cofins_saidas) {
        this.vlr_cofins_saidas = vlr_cofins_saidas;
    }

    public void setTabela_sped(String tabela_sped) {
        this.tabela_sped = tabela_sped;
    }

    public void setMarca_grupo_sped(String marca_grupo_sped) {
        this.marca_grupo_sped = marca_grupo_sped;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param pis_incid_cumul
     */
    public void setPis_incid_cumul(String pis_incid_cumul) {
        this.pis_incid_cumul = pis_incid_cumul;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param cofins_incid_cumul
     */
    public void setCofins_incid_cumul(String cofins_incid_cumul) {
        this.cofins_incid_cumul = cofins_incid_cumul;
    }

    /**
     * Informar somente os códigos CST/CSOSN constantes nas tabelas CRT - Código
     * do Regime Tributário Normal e CSOSN - Código da Situação da Operação no
     * Simples Nacional.
     *
     * @param icms_cst_cson_entr
     */
    public void setIcms_cst_cson_entr(String icms_cst_cson_entr) {
        this.icms_cst_cson_entr = icms_cst_cson_entr;
    }

    /**
     * Informar somente os códigos CST/CSOSN constantes nas tabelas CRT - Código
     * do Regime Tributário Normal e CSOSN - Código da Situação da Operação no
     * Simples Nacional
     *
     * @param icms_cst_cson_saidas
     */
    public void setIcms_cst_cson_saidas(String icms_cst_cson_saidas) {
        this.icms_cst_cson_saidas = icms_cst_cson_saidas;
    }

    public void setIcms_aliq(String icms_aliq) {
        this.icms_aliq = icms_aliq;
    }

    /**
     * Informar somente os códigos CST constantes na tabela CST do IPI
     *
     * @param ipi_cst_entr
     */
    public void setIpi_cst_entr(String ipi_cst_entr) {
        this.ipi_cst_entr = ipi_cst_entr;
    }

    /**
     * Informar somente os códigos CST constantes na tabela CST do IPI
     *
     * @param ipi_cst_saidas
     */
    public void setIpi_cst_saidas(String ipi_cst_saidas) {
        this.ipi_cst_saidas = ipi_cst_saidas;
    }

    /**
     * Informar: D =Decendial, M=Mensal.
     *
     * @param ipi_periodicidade
     */
    public void setIpi_periodicidade(String ipi_periodicidade) {
        this.ipi_periodicidade = ipi_periodicidade;
    }

    public void setIpi_aliq(String ipi_aliq) {
        this.ipi_aliq = ipi_aliq;
    }

    /**
     * Informar S=Sim ou N=Não
     *
     * @param simp_nac_trib_pis_cofins
     */
    public void setSimp_nac_trib_pis_cofins(String simp_nac_trib_pis_cofins) {
        this.simp_nac_trib_pis_cofins = simp_nac_trib_pis_cofins;
    }

    /**
     * Informar: S=Sim ou N=Não
     *
     * @param excl_bc_outras_desp
     */
    public void setExcl_bc_outras_desp(String excl_bc_outras_desp) {
        this.excl_bc_outras_desp = excl_bc_outras_desp;
    }

    /**
     * Valores válidos: Informar S-Sim ou N-Não. Informar somente para empresa
     * com UF GO
     *
     * @param fundepec
     */
    public void setFundepec(String fundepec) {
        this.fundepec = fundepec;
    }

    /**
     * Informar somente para empresa com UF GO. Informar o código constante da
     * tabela do FUNDEPEC
     *
     * @param tp_produto
     */
    public void setTp_produto(String tp_produto) {
        this.tp_produto = tp_produto;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param prodepe
     */
    public void setProdepe(String prodepe) {
        this.prodepe = prodepe;
    }

    /**
     * Informar o código da tabela de Incentivo PRODEPE.
     *
     * @param cod_apur
     */
    public void setCod_apur(String cod_apur) {
        this.cod_apur = cod_apur;
    }

    /**
     * Informar S-Sim e N-Não
     *
     * @param redu_bc
     */
    public void setRedu_bc(String redu_bc) {
        this.redu_bc = redu_bc;
    }

    /**
     * Informar o percentual de redução.
     *
     * @param pis_cofins_perc_redu
     */
    public void setPis_cofins_perc_redu(String pis_cofins_perc_redu) {
        this.pis_cofins_perc_redu = pis_cofins_perc_redu;
    }

    /**
     * Informar: 1= Tributação monofásica ou 2=Substituição tributária
     *
     * @param simp_nac_tp_trib_pis_cofins
     */
    public void setSimp_nac_tp_trib_pis_cofins(String simp_nac_tp_trib_pis_cofins) {
        this.simp_nac_tp_trib_pis_cofins = simp_nac_tp_trib_pis_cofins;
    }

    public void setCod_rec_pis_entr(String cod_rec_pis_entr) {
        this.cod_rec_pis_entr = cod_rec_pis_entr;
    }

    public void setCod_rec_cofins_entr(String cod_rec_cofins_entr) {
        this.cod_rec_cofins_entr = cod_rec_cofins_entr;
    }

    /**
     * Informar: M = "Margem Valor Agregado (Percentual)" P = "Pauta(Valor)" A =
     * "Maior valor entre Margem de valor agregado(%) e Pauta(valor)"
     * Observação: Campo disponivel somente para empresa com UF SC, RS, DF, PR,
     * RO, RJ, SP, GO e CE.
     *
     * @param bc_st
     */
    public void setBc_st(String bc_st) {
        this.bc_st = bc_st;
    }

    /**
     * Apenas Informar quando no campo base de cálculo ST estiver informado "M".
     * Observação: Campo disponivel somente para empresa com UF SC, RS, DF, PR,
     * RO, RJ, SP, GO e CE.
     *
     * @param perc_st
     */
    public void setPerc_st(String perc_st) {
        this.perc_st = perc_st;
    }

    /**
     * Apenas Informar quando no campo base de cálculo ST estiver informado "P".
     * Observação: Campo disponivel somente para empresa com UF SC, RS, DF, PR,
     * RO, RJ, SP, GO e CE.
     *
     * @param vlr_unir_st
     */
    public void setVlr_unir_st(String vlr_unir_st) {
        this.vlr_unir_st = vlr_unir_st;
    }

    public void setIpi_cod_rec(String ipi_cod_rec) {
        this.ipi_cod_rec = ipi_cod_rec;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|" + descricao + "|" 
                + cst_entrada + "|" + vin_credito + "|" + base_credito + "|"
                + aproveita_cred_prop + "|" + cred_aliq_dif + "|" + aliq_pis_entr + "|"
                + aliq_cofins_entr + "|" + cred_unid_med_entr + "|"
                + unid_trib_dif_entr + "|" + unid_trib_entr + "|"
                + fator_conv_entr + "|" + vlr_pis_entr + "|"
                + vlr_cofins_entr + "|" + cst_saidas + "|"
                + tp_contr + "|" + nat_receita + "|"
                + cod_rec_pis_saida + "|" + cod_rec_cofins_saida + "|"
                + deb_ali_dif_saida + "|" + aliq_pis_saida + "|"
                + aliq_cofins_saida + "|" + deb_unid_med_saidas + "|"
                + unid_trib_dif + "|" + unid_trib_saidas + "|"
                + fator_conv_saidas + "|" + vlr_pis_saidas + "|"
                + vlr_cofins_saidas + "|" + tabela_sped + "|"
                + marca_grupo_sped + "|" + pis_incid_cumul + "|"
                + cofins_incid_cumul + "|" + icms_cst_cson_entr + "|"
                + icms_cst_cson_saidas + "|" + icms_aliq + "|"
                + ipi_cst_entr + "|" + ipi_cst_saidas + "|"
                + ipi_periodicidade + "|" + ipi_aliq + "|"
                + simp_nac_trib_pis_cofins + "|" + excl_bc_outras_desp + "|"
                + fundepec + "|" + tp_produto + "|" + prodepe + "|"
                + cod_apur + "|" + redu_bc + "|" + pis_cofins_perc_redu + "|"
                + simp_nac_tp_trib_pis_cofins + "|" + cod_rec_pis_entr + "|"
                + cod_rec_cofins_entr + "|" + bc_st + "|" + perc_st + "|"
                + vlr_unir_st + "|" + ipi_cod_rec + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg0110_Produtos_Vigencia i = new Reg0110_Produtos_Vigencia();
        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
