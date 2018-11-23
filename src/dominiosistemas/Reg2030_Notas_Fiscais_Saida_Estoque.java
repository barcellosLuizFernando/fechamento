/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class Reg2030_Notas_Fiscais_Saida_Estoque implements Exportacao_DomSis {

    private final String id = "2030";
    private String cod_produto = "";
    private String quantidade = "";
    private String vlt_total = "";
    private String vlr_ipi = "";
    private String bc = "";
    private String tp_lancamento = "";
    private String data = "";
    private String cod_st = "";
    private String vlr_bruto_prod = "";
    private String vlr_desc = "";
    private String bc_icms = "";
    private String bc_icms_st = "";
    private String aliq_icms = "";
    private String chassi_veiculo = "";
    private String prod_incetiv = "";
    private String cod_apur = "";
    private String sit_trib_prod_ecf = "";
    private String vlr_frete = "";
    private String vlr_seguro = "";
    private String vlr_despesas = "";
    private String qtd_gasolina = "";
    private String vlr_icms = "";
    private String vlr_st = "";
    private String vlr_isent_ipi = "";
    private String vlr_outras_ipi = "";
    private String vlr_unit = "";
    private String aliq_st = "";
    private String cod_trib_ipi = "";
    private String aliq_ipi = "";
    private String bc_issqn = "";
    private String aliq_issqn = "";
    private String vlr_issqn = "";
    private String lote_medic = "";
    private String dt_valid = "";
    private String ref_bc = "";
    private String vlr_tabelado = "";
    private String nro_serie_arma = "";
    private String nro_serie_cano = "";
    private String tp_oper_veic = "";
    private String qtd_cancelada = "";
    private String vlr_cancelada = "";
    private String isentas_icms = "";
    private String nao_incid_icms = "";
    private String vlr_acum_aquis_st = "";
    private String dt_fabric = "";
    private String serie_fab_ecf = "";
    private String cst_pis = "";
    private String bc_pis = "";
    private String aliq_pis = "";
    private String vlr_pis = "";
    private String cst_cofins = "";
    private String bc_cofins = "";
    private String aliq_cofins = "";
    private String vlr_cofins = "";
    private String qtd_item_lote_medic = "";
    private String enquad_ipi = "";
    private String qtd = "";
    private String movim_fis_prod = "";
    private String unid_comerc = "";
    private String cod_bico = "";
    private String vlr_cont = "";
    private String qtd_trib_pis = "";
    private String vlr_unid_pis = "";
    private String vlr_pis_unid_med = "";
    private String qtd_tib_cofins = "";
    private String vlr_unid_cofins = "";
    private String vlr_cofins_unid_med = "";
    private String nf_devolv_nf_entrada = "";
    private String desc_complementar = "";
    private String nat_rec_pis = "";
    private String nat_rec_cofins = "";
    private String exclusao_coop = "";
    private String nf_devolv_cst_pis = "";
    private String nf_devolv_cst_cofins = "";
    private String data2 = "";
    private String vin_cred_pis = "";
    private String vin_cred_cofins = "";
    private String bc_frete_pis = "";
    private String imp_frete_pis = "";
    private String bc_seguro_pis = "";
    private String imp_seguro_pis = "";
    private String bc_desp_acess_PIS = "";
    private String imp_desp_acess_PIS = "";
    private String bc_frete_cofins = "";
    private String imp_frete_cofins = "";
    private String bc_seguro_cofins = "";
    private String imp_seguro_cofins = "";
    private String bc_desp_acess_cofins = "";
    private String imp_desp_acess_cofins = "";
    private String cod_tanque = "";
    private String bc_icms_carga_med = "";
    private String aliq_icms_carga_med = "";
    private String vlr_icms_carga_med = "";
    private String pis_cofins_perc_red_bc = "";
    private String icms_difal_bc = "";
    private String icms_difal_aliq = "";
    private String icms_difal_uf_origem = "";
    private String icms_difal_uf_destino = "";
    private String icms_difal_vlr_fcp = "";
    private String cod_rec_pis_nota_devolv = "";
    private String cod_rec_cofins_nota_devolv = "";
    private String cod_rec_pis = "";
    private String cod_rec_cofins = "";
    private String cod_rec_ipi = "";
    private String cod_cest = "";

    public void setCod_produto(String cod_produto) {
        this.cod_produto = cod_produto;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Base Calculo + IPI.
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

    public void setChassi_veiculo(String chassi_veiculo) {
        this.chassi_veiculo = chassi_veiculo;
    }

    /**
     * Preencher com S ou N - Apenas para o Estado de PE.
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

    /**
     * Apenas para o Estado de SE
     *
     * @param sit_trib_prod_ecf
     */
    public void setSit_trib_prod_ecf(String sit_trib_prod_ecf) {
        this.sit_trib_prod_ecf = sit_trib_prod_ecf;
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

    public void setAliq_issqn(String aliq_issqn) {
        this.aliq_issqn = aliq_issqn;
    }

    public void setVlr_issqn(String vlr_issqn) {
        this.vlr_issqn = vlr_issqn;
    }

    public void setLote_medic(String lote_medic) {
        this.lote_medic = lote_medic;
    }

    public void setDt_valid(String dt_valid) {
        this.dt_valid = dt_valid;
    }

    /**
     * Informar: 00=Tabelado / 01=Negativa / 02=Positiva / 03=Neutra / 04=Margem
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

    /**
     * Informar: 00=Concessionária / 1=Faturamento / 02=Venda / 99=Outras.
     *
     * @param tp_oper_veic
     */
    public void setTp_oper_veic(String tp_oper_veic) {
        this.tp_oper_veic = tp_oper_veic;
    }

    public void setQtd_cancelada(String qtd_cancelada) {
        this.qtd_cancelada = qtd_cancelada;
    }

    public void setVlr_cancelada(String vlr_cancelada) {
        this.vlr_cancelada = vlr_cancelada;
    }

    public void setIsentas_icms(String isentas_icms) {
        this.isentas_icms = isentas_icms;
    }

    public void setNao_incid_icms(String nao_incid_icms) {
        this.nao_incid_icms = nao_incid_icms;
    }

    public void setVlr_acum_aquis_st(String vlr_acum_aquis_st) {
        this.vlr_acum_aquis_st = vlr_acum_aquis_st;
    }

    public void setDt_fabric(String dt_fabric) {
        this.dt_fabric = dt_fabric;
    }

    public void setSerie_fab_ecf(String serie_fab_ecf) {
        this.serie_fab_ecf = serie_fab_ecf;
    }

    public void setCst_pis(String cst_pis) {
        this.cst_pis = cst_pis;
    }

    public void setBc_pis(String bc_pis) {
        this.bc_pis = bc_pis;
    }

    public void setAliq_pis(String aliq_pis) {
        this.aliq_pis = aliq_pis;
    }

    public void setVlr_pis(String vlr_pis) {
        this.vlr_pis = vlr_pis;
    }

    public void setCst_cofins(String cst_cofins) {
        this.cst_cofins = cst_cofins;
    }

    public void setBc_cofins(String bc_cofins) {
        this.bc_cofins = bc_cofins;
    }

    public void setAliq_cofins(String aliq_cofins) {
        this.aliq_cofins = aliq_cofins;
    }

    public void setVlr_cofins(String vlr_cofins) {
        this.vlr_cofins = vlr_cofins;
    }

    public void setQtd_item_lote_medic(String qtd_item_lote_medic) {
        this.qtd_item_lote_medic = qtd_item_lote_medic;
    }

    public void setEnquad_ipi(String enquad_ipi) {
        this.enquad_ipi = enquad_ipi;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
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

    /**
     * Preencher caso a empresa seja do ramo varejista de combustíveis.
     *
     * @param cod_bico
     */
    public void setCod_bico(String cod_bico) {
        this.cod_bico = cod_bico;
    }

    public void setVlr_cont(String vlr_cont) {
        this.vlr_cont = vlr_cont;
    }

    public void setQtd_trib_pis(String qtd_trib_pis) {
        this.qtd_trib_pis = qtd_trib_pis;
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
     * Informar o número da nota
     *
     * @param nf_devolv_nf_entrada
     */
    public void setNf_devolv_nf_entrada(String nf_devolv_nf_entrada) {
        this.nf_devolv_nf_entrada = nf_devolv_nf_entrada;
    }

    public void setDesc_complementar(String desc_complementar) {
        this.desc_complementar = desc_complementar;
    }

    public void setNat_rec_pis(String nat_rec_pis) {
        this.nat_rec_pis = nat_rec_pis;
    }

    public void setNat_rec_cofins(String nat_rec_cofins) {
        this.nat_rec_cofins = nat_rec_cofins;
    }

    public void setExclusao_coop(String exclusao_coop) {
        this.exclusao_coop = exclusao_coop;
    }

    public void setNf_devolv_cst_pis(String nf_devolv_cst_pis) {
        this.nf_devolv_cst_pis = nf_devolv_cst_pis;
    }

    public void setNf_devolv_cst_cofins(String nf_devolv_cst_cofins) {
        this.nf_devolv_cst_cofins = nf_devolv_cst_cofins;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    /**
     * Valores válidos: 01- Crédito vinculado à alíquota básica 02 - Crédito
     * vinculado à alíquota Diferenciada 03 - Crédito vinculado à alíquota por
     * Unidade de Produto 05 - Crédito vinculado a aquisição de embalagem 06 -
     * Crédito presumido agroindústria 08 - Crédito de importação 99 - Outros
     * Créditos
     *
     * @param vin_cred_pis
     */
    public void setVin_cred_pis(String vin_cred_pis) {
        this.vin_cred_pis = vin_cred_pis;
    }

    /**
     * Valores válidos: 01- Crédito vinculado à alíquota básica 02 - Crédito
     * vinculado à alíquota Diferenciada 03 - Crédito vinculado à alíquota por
     * Unidade de Produto 05 - Crédito vinculado a aquisição de embalagem 06 -
     * Crédito presumido agroindústria 08 - Crédito de importação 99 - Outros
     * Créditos
     *
     * @param vin_cred_cofins
     */
    public void setVin_cred_cofins(String vin_cred_cofins) {
        this.vin_cred_cofins = vin_cred_cofins;
    }

    /**
     * Informar o mesmo valor do "Frete". Informar somente para notas com
     * "Espécie" vinculadas aos modelos "01-Nota Fiscal" e "55-Nota Fiscal
     * Eletrônica".
     *
     * @param bc_frete_pis
     */
    public void setBc_frete_pis(String bc_frete_pis) {
        this.bc_frete_pis = bc_frete_pis;
    }

    /**
     * Informar somente para notas com "Espécie" vinculadas aos modelos "01-Nota
     * Fiscal" e "55-Nota Fiscal Eletrônica".
     *
     * @param imp_frete_pis
     */
    public void setImp_frete_pis(String imp_frete_pis) {
        this.imp_frete_pis = imp_frete_pis;
    }

    /**
     * Informar o mesmo valor do "Seguro". Informar somente para notas com
     * "Espécie" vinculadas aos modelos "01-Nota Fiscal" e "55-Nota Fiscal
     * Eletrônica".
     *
     * @param bc_seguro_pis
     */
    public void setBc_seguro_pis(String bc_seguro_pis) {
        this.bc_seguro_pis = bc_seguro_pis;
    }

    /**
     * Informar somente para notas com "Espécie" vinculadas aos modelos "01-Nota
     * Fiscal" e "55-Nota Fiscal Eletrônica".
     *
     * @param imp_seguro_pis
     */
    public void setImp_seguro_pis(String imp_seguro_pis) {
        this.imp_seguro_pis = imp_seguro_pis;
    }

    /**
     * Informar o mesmo valor das "Despesas Acessórias". Informar somente para
     * notas com "Espécie" vinculadas aos modelos "01-Nota Fiscal" e "55-Nota
     * Fiscal Eletrônica".
     *
     * @param bc_desp_acess_PIS
     */
    public void setBc_desp_acess_PIS(String bc_desp_acess_PIS) {
        this.bc_desp_acess_PIS = bc_desp_acess_PIS;
    }

    /**
     * Informar somente para notas com "Espécie" vinculadas aos modelos "01-Nota
     * Fiscal" e "55-Nota Fiscal Eletrônica".
     *
     * @param imp_desp_acess_PIS
     */
    public void setImp_desp_acess_PIS(String imp_desp_acess_PIS) {
        this.imp_desp_acess_PIS = imp_desp_acess_PIS;
    }

    /**
     * Informar o mesmo valor do "Frete". Informar somente para notas com
     * "Espécie" vinculadas aos modelos "01-Nota Fiscal" e "55-Nota Fiscal
     * Eletrônica".
     *
     * @param bc_frete_cofins
     */
    public void setBc_frete_cofins(String bc_frete_cofins) {
        this.bc_frete_cofins = bc_frete_cofins;
    }

    /**
     * Informar somente para notas com "Espécie" vinculadas aos modelos "01-Nota
     * Fiscal" e "55-Nota Fiscal Eletrônica”.
     *
     * @param imp_frete_cofins
     */
    public void setImp_frete_cofins(String imp_frete_cofins) {
        this.imp_frete_cofins = imp_frete_cofins;
    }

    /**
     * Informar o mesmo valor do "Seguro". Informar somente para notas com
     * "Espécie" vinculadas aos modelos "01-Nota Fiscal" e "55-Nota Fiscal
     * Eletrônica".
     *
     * @param bc_seguro_cofins
     */
    public void setBc_seguro_cofins(String bc_seguro_cofins) {
        this.bc_seguro_cofins = bc_seguro_cofins;
    }

    /**
     * Informar somente para notas com "Espécie" vinculadas aos modelos "01-Nota
     * Fiscal" e "55-Nota Fiscal Eletrônica".
     *
     * @param imp_seguro_cofins
     */
    public void setImp_seguro_cofins(String imp_seguro_cofins) {
        this.imp_seguro_cofins = imp_seguro_cofins;
    }

    /**
     * Informar o mesmo valor das "Despesas Acessórias". Informar somente para
     * notas com "Espécie" vinculadas aos modelos "01-Nota Fiscal" e "55-Nota
     * Fiscal Eletrônica".
     *
     * @param bc_desp_acess_cofins
     */
    public void setBc_desp_acess_cofins(String bc_desp_acess_cofins) {
        this.bc_desp_acess_cofins = bc_desp_acess_cofins;
    }

    /**
     * Informar somente para notas com "Espécie" vinculadas aos modelos "01-Nota
     * Fiscal" e "55-Nota Fiscal Eletrônica".
     *
     * @param imp_desp_acess_cofins
     */
    public void setImp_desp_acess_cofins(String imp_desp_acess_cofins) {
        this.imp_desp_acess_cofins = imp_desp_acess_cofins;
    }

    /**
     * Preencher caso a empresa seja do ramo varejista de combustíveis e o campo
     * "Possui mais de um tanque interligado a um mesmo bico" estiver
     * selecionado.
     *
     * @param cod_tanque
     */
    public void setCod_tanque(String cod_tanque) {
        this.cod_tanque = cod_tanque;
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

    public void setPis_cofins_perc_red_bc(String pis_cofins_perc_red_bc) {
        this.pis_cofins_perc_red_bc = pis_cofins_perc_red_bc;
    }

    public void setIcms_difal_bc(String icms_difal_bc) {
        this.icms_difal_bc = icms_difal_bc;
    }

    public void setIcms_difal_aliq(String icms_difal_aliq) {
        this.icms_difal_aliq = icms_difal_aliq;
    }

    public void setIcms_difal_uf_origem(String icms_difal_uf_origem) {
        this.icms_difal_uf_origem = icms_difal_uf_origem;
    }

    public void setIcms_difal_uf_destino(String icms_difal_uf_destino) {
        this.icms_difal_uf_destino = icms_difal_uf_destino;
    }

    public void setIcms_difal_vlr_fcp(String icms_difal_vlr_fcp) {
        this.icms_difal_vlr_fcp = icms_difal_vlr_fcp;
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
    public String getLayout() throws Exception {
        String linha = "|" + id + "|"
                + cod_produto + "|"
                + quantidade + "|"
                + vlt_total + "|"
                + vlr_ipi + "|"
                + bc + "|"
                + tp_lancamento + "|"
                + data + "|"
                + cod_st + "|"
                + vlr_bruto_prod + "|"
                + vlr_desc + "|"
                + bc_icms + "|"
                + bc_icms_st + "|"
                + aliq_icms + "|"
                + chassi_veiculo + "|"
                + prod_incetiv + "|"
                + cod_apur + "|"
                + sit_trib_prod_ecf + "|"
                + vlr_frete + "|"
                + vlr_seguro + "|"
                + vlr_despesas + "|"
                + qtd_gasolina + "|"
                + vlr_icms + "|"
                + vlr_st + "|"
                + vlr_isent_ipi + "|"
                + vlr_outras_ipi + "|"
                + vlr_unit + "|"
                + aliq_st + "|"
                + cod_trib_ipi + "|"
                + aliq_ipi + "|"
                + bc_issqn + "|"
                + aliq_issqn + "|"
                + vlr_issqn + "|"
                + lote_medic + "|"
                + dt_valid + "|"
                + ref_bc + "|"
                + vlr_tabelado + "|"
                + nro_serie_arma + "|"
                + nro_serie_cano + "|"
                + tp_oper_veic + "|"
                + qtd_cancelada + "|"
                + vlr_cancelada + "|"
                + isentas_icms + "|"
                + nao_incid_icms + "|"
                + vlr_acum_aquis_st + "|"
                + dt_fabric + "|"
                + serie_fab_ecf + "|"
                + cst_pis + "|"
                + bc_pis + "|"
                + aliq_pis + "|"
                + vlr_pis + "|"
                + cst_cofins + "|"
                + bc_cofins + "|"
                + aliq_cofins + "|"
                + vlr_cofins + "|"
                + qtd_item_lote_medic + "|"
                + enquad_ipi + "|"
                + qtd + "|"
                + movim_fis_prod + "|"
                + unid_comerc + "|"
                + cod_bico + "|"
                + vlr_cont + "|"
                + qtd_trib_pis + "|"
                + vlr_unid_pis + "|"
                + vlr_pis_unid_med + "|"
                + qtd_tib_cofins + "|"
                + vlr_unid_cofins + "|"
                + vlr_cofins_unid_med + "|"
                + nf_devolv_nf_entrada + "|"
                + desc_complementar + "|"
                + nat_rec_pis + "|"
                + nat_rec_cofins + "|"
                + exclusao_coop + "|"
                + nf_devolv_cst_pis + "|"
                + nf_devolv_cst_cofins + "|"
                + data2 + "|"
                + vin_cred_pis + "|"
                + vin_cred_cofins + "|"
                + bc_frete_pis + "|"
                + imp_frete_pis + "|"
                + bc_seguro_pis + "|"
                + imp_seguro_pis + "|"
                + bc_desp_acess_PIS + "|"
                + imp_desp_acess_PIS + "|"
                + bc_frete_cofins + "|"
                + imp_frete_cofins + "|"
                + bc_seguro_cofins + "|"
                + imp_seguro_cofins + "|"
                + bc_desp_acess_cofins + "|"
                + imp_desp_acess_cofins + "|"
                + cod_tanque + "|"
                + bc_icms_carga_med + "|"
                + aliq_icms_carga_med + "|"
                + vlr_icms_carga_med + "|"
                + pis_cofins_perc_red_bc + "|"
                + icms_difal_bc + "|"
                + icms_difal_aliq + "|"
                + icms_difal_uf_origem + "|"
                + icms_difal_uf_destino + "|"
                + icms_difal_vlr_fcp + "|"
                + cod_rec_pis_nota_devolv + "|"
                + cod_rec_cofins_nota_devolv + "|"
                + cod_rec_pis + "|"
                + cod_rec_cofins + "|"
                + cod_rec_ipi + "|"
                + cod_cest + "|";
        System.out.println("Registro: " + linha);
        
        return linha;
    }

    public static void main(String[] args) {
        Reg2030_Notas_Fiscais_Saida_Estoque i = new Reg2030_Notas_Fiscais_Saida_Estoque();
        
        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
