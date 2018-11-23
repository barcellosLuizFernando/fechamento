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
public class Reg1030_Notas_Fiscais_Entrada_Estoque implements Exportacao_DomSis {

    private final String id = "1030";
    private String cod_produto = "";
    private String quantidade = "";
    private String vlt_total = "";
    private String vlr_ipi = "";
    private String bc = "";
    private String tp_lancamento = "";
    private String data = "";
    private String nro_di = "";
    private String cod_st = "";
    private String vlr_bruto_prod = "";
    private String vlr_desc = "";
    private String bc_icms = "";
    private String bc_icms_st = "";
    private String aliq_icms = "";
    private String prod_incetiv = "";
    private String cod_apur = "";
    private String vlr_frete = "";
    private String vlr_seguro = "";
    private String vlr_despesas = "";
    private String qtd_gasolina = "";
    private String vlr_icms = "";
    private String vlr_st = "";
    private String vlr_isent_ipi = "";
    private String vlr_outras_ipi = "";
    private String icms_nfp = "";
    private String vlr_unit = "";
    private String aliq_st = "";
    private String cod_trib_ipi = "";
    private String aliq_ipi = "";
    private String bc_issqn = "";
    private String vlr_issqn = "";
    private String cfop = "";
    private String serie_fab_ecf = "";
    private String aliq_pis = "";
    private String vlr_pis = "";
    private String aliq_cofins = "";
    private String cus_total_produto = "";
    private String cst_pis = "";
    private String bc_pis = "";
    private String cst_cofins = "";
    private String bc_cofins = "";
    private String chassi_veiculo = "";
    private String tp_oper_veic = "";
    private String lote_medic = "";
    private String qtd_item_lote_medic = "";
    private String dt_valid = "";
    private String dt_fabric = "";
    private String ref_bc = "";
    private String vlr_tabelado = "";
    private String nro_serie_arma = "";
    private String nro_serie_cano = "";
    private String enquad_ipi = "";
    private String movim_fis_prod = "";
    private String unid_comerc = "";
    private String compl_cfop = "";
    private String tanque_comb = "";
    private String vlr_cont = "";
    private String qtd_tib_pis = "";
    private String vlr_unid_pis = "";
    private String vlr_pis_unid_med = "";
    private String qtd_tib_cofins = "";
    private String vlr_unid_cofins = "";
    private String vlr_cofins_unid_med = "";
    private String bc2 = "";
    private String nro_redZ_devolv = "";
    private String desc_compl = "";
    private String nf_devolv = "";
    private String nf_devolv_cst_cofins = "";
    private String nf_devolv_vinc_pis = "";
    private String nf_devolv_vinc_cofins = "";
    private String exclusao_pis = "";
    private String exclusao_cofins = "";
    private String bc_icms_carga_med = "";
    private String aliq_icms_carga_med = "";
    private String vlr_icms_carga_med = "";
    private String nro_serie_maq_ecf = "";
    private String pis_cofins_perc_red_bc = "";
    private String cod_rec_pis_nota_devolv = "";
    private String cod_rec_cofins_nota_devolv = "";
    private String cod_rec_pis = "";
    private String cod_rec_cofins = "";
    private String cred_presu_pis_cofins = "";
    private String cred_presu_pis = "";
    private String cred_presu_cofins = "";
    private String icms_st_antec = "";
    private String icms_st_antec_bc = "";
    private String icms_st_antec_aliq = "";
    private String icms_st_antec_vlr = "";
    private String cod_rec_ipi = "";
    private String cod_cest = "";

    public void setCod_produto(String cod_produto) {
        this.cod_produto = cod_produto;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Base Cal. + IPI.
     *
     * @param vlt_total
     */
    public void setVlt_total(String vlt_total) {
        this.vlt_total = vlt_total;
    }

    public void setVlr_ipi(String vlr_ipi) {
        this.vlr_ipi = vlr_ipi;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    /**
     * Informar: 1=Produto vinculado a nota / 2=Lançamento Extra.
     *
     * @param tp_lancamento
     */
    public void setTp_lancamento(String tp_lancamento) {
        this.tp_lancamento = tp_lancamento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNro_di(String nro_di) {
        this.nro_di = nro_di;
    }

    public void setCod_st(String cod_st) {
        this.cod_st = cod_st;
    }

    public void setVlr_bruto_prod(String vlr_bruto_prod) {
        this.vlr_bruto_prod = vlr_bruto_prod;
    }

    public void setVlr_desc(String vlr_desc) {
        this.vlr_desc = vlr_desc;
    }

    public void setBc_icms(String bc_icms) {
        this.bc_icms = bc_icms;
    }

    public void setBc_icms_st(String bc_icms_st) {
        this.bc_icms_st = bc_icms_st;
    }

    public void setAliq_icms(String aliq_icms) {
        this.aliq_icms = aliq_icms;
    }

    /**
     * Preencher com S ou N. Apenas para o Estado de PE.
     *
     * @param prod_incetiv
     */
    public void setProd_incetiv(String prod_incetiv) {
        this.prod_incetiv = prod_incetiv;
    }

    /**
     * Apenas para o Estado de PE.
     *
     * @param cod_apur
     */
    public void setCod_apur(String cod_apur) {
        this.cod_apur = cod_apur;
    }

    public void setVlr_frete(String vlr_frete) {
        this.vlr_frete = vlr_frete;
    }

    public void setVlr_seguro(String vlr_seguro) {
        this.vlr_seguro = vlr_seguro;
    }

    public void setVlr_despesas(String vlr_despesas) {
        this.vlr_despesas = vlr_despesas;
    }

    public void setQtd_gasolina(String qtd_gasolina) {
        this.qtd_gasolina = qtd_gasolina;
    }

    public void setVlr_icms(String vlr_icms) {
        this.vlr_icms = vlr_icms;
    }

    public void setVlr_st(String vlr_st) {
        this.vlr_st = vlr_st;
    }

    public void setVlr_isent_ipi(String vlr_isent_ipi) {
        this.vlr_isent_ipi = vlr_isent_ipi;
    }

    public void setVlr_outras_ipi(String vlr_outras_ipi) {
        this.vlr_outras_ipi = vlr_outras_ipi;
    }

    public void setIcms_nfp(String icms_nfp) {
        this.icms_nfp = icms_nfp;
    }

    public void setVlr_unit(String vlr_unit) {
        this.vlr_unit = vlr_unit;
    }

    public void setAliq_st(String aliq_st) {
        this.aliq_st = aliq_st;
    }

    public void setCod_trib_ipi(String cod_trib_ipi) {
        this.cod_trib_ipi = cod_trib_ipi;
    }

    public void setAliq_ipi(String aliq_ipi) {
        this.aliq_ipi = aliq_ipi;
    }

    public void setBc_issqn(String bc_issqn) {
        this.bc_issqn = bc_issqn;
    }

    public void setVlr_issqn(String vlr_issqn) {
        this.vlr_issqn = vlr_issqn;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public void setSerie_fab_ecf(String serie_fab_ecf) {
        this.serie_fab_ecf = serie_fab_ecf;
    }

    public void setAliq_pis(String aliq_pis) {
        this.aliq_pis = aliq_pis;
    }

    public void setVlr_pis(String vlr_pis) {
        this.vlr_pis = vlr_pis;
    }

    public void setAliq_cofins(String aliq_cofins) {
        this.aliq_cofins = aliq_cofins;
    }

    public void setCus_total_produto(String cus_total_produto) {
        this.cus_total_produto = cus_total_produto;
    }

    public void setCst_pis(String cst_pis) {
        this.cst_pis = cst_pis;
    }

    public void setBc_pis(String bc_pis) {
        this.bc_pis = bc_pis;
    }

    public void setCst_cofins(String cst_cofins) {
        this.cst_cofins = cst_cofins;
    }

    public void setBc_cofins(String bc_cofins) {
        this.bc_cofins = bc_cofins;
    }

    /**
     * Informar: 00=Concessionária / 01=Faturamento / 02=Venda / 99=Outras.
     *
     * @param chassi_veiculo
     */
    public void setChassi_veiculo(String chassi_veiculo) {
        this.chassi_veiculo = chassi_veiculo;
    }

    /**
     * Informar: 00=Concessionária / 01=Faturamento / 02=Venda / 99=Outras.
     *
     * @param tp_oper_veic
     */
    public void setTp_oper_veic(String tp_oper_veic) {
        this.tp_oper_veic = tp_oper_veic;
    }

    public void setLote_medic(String lote_medic) {
        this.lote_medic = lote_medic;
    }

    public void setQtd_item_lote_medic(String qtd_item_lote_medic) {
        this.qtd_item_lote_medic = qtd_item_lote_medic;
    }

    public void setDt_valid(String dt_valid) {
        this.dt_valid = dt_valid;
    }

    public void setDt_fabric(String dt_fabric) {
        this.dt_fabric = dt_fabric;
    }

    /**
     * Informar: 00=Tabelado / 01=Negativa / 02=Positiva /03=Neutra / 04=Margem
     * Valor Agregado / 05=Pauta.
     *
     * @param ref_bc
     */
    public void setRef_bc(String ref_bc) {
        this.ref_bc = ref_bc;
    }

    public void setVlr_tabelado(String vlr_tabelado) {
        this.vlr_tabelado = vlr_tabelado;
    }

    public void setNro_serie_arma(String nro_serie_arma) {
        this.nro_serie_arma = nro_serie_arma;
    }

    public void setNro_serie_cano(String nro_serie_cano) {
        this.nro_serie_cano = nro_serie_cano;
    }

    public void setEnquad_ipi(String enquad_ipi) {
        this.enquad_ipi = enquad_ipi;
    }

    /**
     * Movimentação física de produto (S/N).
     *
     * @param movim_fis_prod
     */
    public void setMovim_fis_prod(String movim_fis_prod) {
        this.movim_fis_prod = movim_fis_prod;
    }

    public void setUnid_comerc(String unid_comerc) {
        this.unid_comerc = unid_comerc;
    }

    public void setCompl_cfop(String compl_cfop) {
        this.compl_cfop = compl_cfop;
    }

    public void setTanque_comb(String tanque_comb) {
        this.tanque_comb = tanque_comb;
    }

    public void setVlr_cont(String vlr_cont) {
        this.vlr_cont = vlr_cont;
    }

    public void setQtd_tib_pis(String qtd_tib_pis) {
        this.qtd_tib_pis = qtd_tib_pis;
    }

    public void setVlr_unid_pis(String vlr_unid_pis) {
        this.vlr_unid_pis = vlr_unid_pis;
    }

    public void setVlr_pis_unid_med(String vlr_pis_unid_med) {
        this.vlr_pis_unid_med = vlr_pis_unid_med;
    }

    public void setQtd_tib_cofins(String qtd_tib_cofins) {
        this.qtd_tib_cofins = qtd_tib_cofins;
    }

    public void setVlr_unid_cofins(String vlr_unid_cofins) {
        this.vlr_unid_cofins = vlr_unid_cofins;
    }

    public void setVlr_cofins_unid_med(String vlr_cofins_unid_med) {
        this.vlr_cofins_unid_med = vlr_cofins_unid_med;
    }

    /**
     * Informar 01=Aquisição de bens para revenda; 02=Aquisição de bens
     * utilizados como insumo; 03=Aquisição de serviços utilizados como insumo;
     * 04=Energia elétrica e térmica, inclusive sob a forma de vapor;
     * 05=Aluguéis de prédios; 06=Aluguéis de máquinas e equipamentos;
     * 07=Armazenagem de mercadoria e frete na operação de venda;
     * 08=Contraprestaçõe s de arrendamento mercantil; 09=Máquinas, equipamentos
     * e outros bens incorporados ao ativo imobilizado (crédito sobre encargos
     * de depreciação); 10=Máquinas, equipamentos e outros bens incorporados ao
     * ativo imobilizado (crédito com base no valor de aquisição);
     * 11=Amortização de edificações e benfeitorias em imóveis; 12=Devolução de
     * Vendas Sujeitas à Incidência Não- Cumulativa; 13=Outras Operações com
     * Direito a Crédito; 14=Atividade de Transporte de Cargas – Subcontratação;
     * 15=Atividade Imobiliária – Custo Incorrido de Unidade Imobiliária;
     * 16=Atividade Imobiliária – Custo Orçado de unidade não concluída;
     * 17=Atividade de Prestação de Serviços de Limpeza, Conservação e
     * Manutenção – valetransporte, valerefeição ou valealimentação, fardamento
     * ou uniforme; 18=Estoque de abertura de bens.
     *
     * @param bc2
     */
    public void setBc2(String bc2) {
        this.bc2 = bc2;
    }

    /**
     * Informar: Número da nota fiscal; Contador da redução Z; Número do Cupom
     * fiscal.
     *
     * @param nro_redZ_devolv
     */
    public void setNro_redZ_devolv(String nro_redZ_devolv) {
        this.nro_redZ_devolv = nro_redZ_devolv;
    }

    public void setDesc_compl(String desc_compl) {
        this.desc_compl = desc_compl;
    }

    public void setNf_devolv(String nf_devolv) {
        this.nf_devolv = nf_devolv;
    }

    public void setNf_devolv_cst_cofins(String nf_devolv_cst_cofins) {
        this.nf_devolv_cst_cofins = nf_devolv_cst_cofins;
    }

    public void setNf_devolv_vinc_pis(String nf_devolv_vinc_pis) {
        this.nf_devolv_vinc_pis = nf_devolv_vinc_pis;
    }

    public void setNf_devolv_vinc_cofins(String nf_devolv_vinc_cofins) {
        this.nf_devolv_vinc_cofins = nf_devolv_vinc_cofins;
    }

    public void setExclusao_pis(String exclusao_pis) {
        this.exclusao_pis = exclusao_pis;
    }

    public void setExclusao_cofins(String exclusao_cofins) {
        this.exclusao_cofins = exclusao_cofins;
    }

    public void setBc_icms_carga_med(String bc_icms_carga_med) {
        this.bc_icms_carga_med = bc_icms_carga_med;
    }

    public void setAliq_icms_carga_med(String aliq_icms_carga_med) {
        this.aliq_icms_carga_med = aliq_icms_carga_med;
    }

    public void setVlr_icms_carga_med(String vlr_icms_carga_med) {
        this.vlr_icms_carga_med = vlr_icms_carga_med;
    }

    public void setNro_serie_maq_ecf(String nro_serie_maq_ecf) {
        this.nro_serie_maq_ecf = nro_serie_maq_ecf;
    }

    /**
     * Informar o percentual de redução.
     *
     * @param pis_cofins_perc_red_bc
     */
    public void setPis_cofins_perc_red_bc(String pis_cofins_perc_red_bc) {
        this.pis_cofins_perc_red_bc = pis_cofins_perc_red_bc;
    }

    public void setCod_rec_pis_nota_devolv(String cod_rec_pis_nota_devolv) {
        this.cod_rec_pis_nota_devolv = cod_rec_pis_nota_devolv;
    }

    public void setCod_rec_cofins_nota_devolv(String cod_rec_cofins_nota_devolv) {
        this.cod_rec_cofins_nota_devolv = cod_rec_cofins_nota_devolv;
    }

    public void setCod_rec_pis(String cod_rec_pis) {
        this.cod_rec_pis = cod_rec_pis;
    }

    public void setCod_rec_cofins(String cod_rec_cofins) {
        this.cod_rec_cofins = cod_rec_cofins;
    }

    public void setCred_presu_pis_cofins(String cred_presu_pis_cofins) {
        this.cred_presu_pis_cofins = cred_presu_pis_cofins;
    }

    public void setCred_presu_pis(String cred_presu_pis) {
        this.cred_presu_pis = cred_presu_pis;
    }

    public void setCred_presu_cofins(String cred_presu_cofins) {
        this.cred_presu_cofins = cred_presu_cofins;
    }

    public void setIcms_st_antec(String icms_st_antec) {
        this.icms_st_antec = icms_st_antec;
    }

    public void setIcms_st_antec_bc(String icms_st_antec_bc) {
        this.icms_st_antec_bc = icms_st_antec_bc;
    }

    public void setIcms_st_antec_aliq(String icms_st_antec_aliq) {
        this.icms_st_antec_aliq = icms_st_antec_aliq;
    }

    public void setIcms_st_antec_vlr(String icms_st_antec_vlr) {
        this.icms_st_antec_vlr = icms_st_antec_vlr;
    }

    public void setCod_rec_ipi(String cod_rec_ipi) {
        this.cod_rec_ipi = cod_rec_ipi;
    }

    /**
     * Informar o código CEST conforme tabela Código Especificador da
     * Substituição Tributária - CEST
     *
     * @param cod_cest
     */
    public void setCod_cest(String cod_cest) {
        this.cod_cest = cod_cest;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|" + cod_produto + "|" + quantidade + "|"
                + vlt_total + "|" + vlr_ipi + "|" + bc + "|" + tp_lancamento + "|"
                + data + "|" + nro_di + "|" + cod_st + "|" + vlr_bruto_prod + "|"
                + vlr_desc + "|" + bc_icms + "|" + bc_icms_st + "|" + aliq_icms + "|"
                + prod_incetiv + "|" + cod_apur + "|" + vlr_frete + "|"
                + vlr_seguro + "|" + vlr_despesas + "|" + qtd_gasolina + "|"
                + vlr_icms + "|" + vlr_st + "|" + vlr_isent_ipi + "|"
                + vlr_outras_ipi + "|" + icms_nfp + "|" + vlr_unit + "|"
                + aliq_st + "|" + cod_trib_ipi + "|" + aliq_ipi + "|"
                + bc_issqn + "|" + vlr_issqn + "|" + cfop + "|"
                + serie_fab_ecf + "|" + aliq_pis + "|" + vlr_pis + "|"
                + aliq_cofins + "|" + cus_total_produto + "|" + cst_pis + "|"
                + bc_pis + "|" + cst_cofins + "|" + bc_cofins + "|"
                + chassi_veiculo + "|" + tp_oper_veic + "|" + lote_medic + "|"
                + qtd_item_lote_medic + "|" + dt_valid + "|" + dt_fabric + "|"
                + ref_bc + "|" + vlr_tabelado + "|" + nro_serie_arma + "|"
                + nro_serie_cano + "|" + enquad_ipi + "|" + movim_fis_prod + "|"
                + unid_comerc + "|" + compl_cfop + "|" + tanque_comb + "|"
                + vlr_cont + "|" + qtd_tib_pis + "|" + vlr_unid_pis + "|"
                + vlr_pis_unid_med + "|" + qtd_tib_cofins + "|"
                + vlr_unid_cofins + "|" + vlr_cofins_unid_med + "|"
                + bc2 + "|" + nro_redZ_devolv + "|" + desc_compl + "|"
                + nf_devolv + "|" + nf_devolv_cst_cofins + "|"
                + nf_devolv_vinc_pis + "|" + nf_devolv_vinc_cofins + "|"
                + exclusao_pis + "|" + exclusao_cofins + "|"
                + bc_icms_carga_med + "|" + aliq_icms_carga_med + "|"
                + vlr_icms_carga_med + "|" + nro_serie_maq_ecf + "|"
                + pis_cofins_perc_red_bc + "|" + cod_rec_pis_nota_devolv + "|"
                + cod_rec_cofins_nota_devolv + "|" + cod_rec_pis + "|"
                + cod_rec_cofins + "|" + cred_presu_pis_cofins + "|"
                + cred_presu_pis + "|" + cred_presu_cofins + "|"
                + icms_st_antec + "|" + icms_st_antec_bc + "|"
                + icms_st_antec_aliq + "|" + icms_st_antec_vlr + "|"
                + cod_rec_ipi + "|" + cod_cest + "|";

        System.out.println("Registro: " + linha);
        
        return linha;
    }
    
    public static void main(String[] args) {
        Reg1030_Notas_Fiscais_Entrada_Estoque i = new Reg1030_Notas_Fiscais_Entrada_Estoque();
        
        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
