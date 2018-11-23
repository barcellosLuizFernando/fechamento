/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import static tools.RemoveSpecialChar.removeSpecialChar;
import javax.swing.JOptionPane;

/**
 * Registro: 2000 – Notas Fiscais de Saídas. Pg 1216
 *
 * @author ferna
 */
public class Reg2000_Notas_Fiscais_Saidas implements Exportacao_DomSis {

    private final String id = "2000";
    private String cod_especie = "";
    private String inscricao_cliente = "";
    private String cod_acumulador = "";
    private String cfop = "";
    private String cod_exc_dief = "";
    private String sigla_uf_cliente = "";
    private String segmento = "";
    private String nro_doc = "";
    private String serie = "";
    private String doc_final = "";
    private String dt_saida = "";
    private String dt_emissao = "";
    private String vlr_contabil = "";
    private String vlr_exc_dief = "";
    private String observacao = "";
    private String cod_mun = "";
    private String mod_frete = "";
    private String cfop_estendido = "";
    private String cod_transf_cred = "";
    private String cod_obs = "";
    private String dt_visto_nf_transf = "";
    private String cod_antec_trib = "";
    private String fato_ger_CRF = "";
    private String fato_ger_COSIRF = "";
    private String fato_ger_IRRFP = "";
    private String tp_receita = "";
    private String vlr_frete = "";
    private String vlr_seguro = "";
    private String vlr_desp_acess = "";
    private String vlr_produtos = "";
    private String vlr_bc_icms_st = "";
    private String out_saidas = "";
    private String out_isentas = "";
    private String sai_isentas_cf = "";
    private String sai_isentas_nf = "";
    private String cod_mod_doc_fis = "";
    private String cod_fis_prest_serv = "";
    private String cod_sit_trib = "";
    private String sub_serie = "";
    private String tp_titulo = "";
    private String id_titulo = "";
    private String inscr_est_cli = "";
    private String inscr_mun_cli = "";
    private String chave_NFe = "";
    private String cod_rec_fethab = "";
    private String resp_fethab = "";
    private String tp_cte = "";
    private String cte_ref = "";
    private String cod_inf_compl = "";
    private String inf_compl = "";
    private String cst_pis_cofins = "";
    private String nat_receita = "";
    private String vlr_srv_pis_cofins = "";
    private String bc_pis_cofins = "";
    private String aliq_pis = "";
    private String aliq_cofins = "";
    private String qtd_kwh = "";
    private String cst_ipi_cos_sit_trib = "";
    private String tp_assinante = "";
    private String tp_credito_acum = "";
    private String lanc_scp = "";
    private String per_prest_srv = "";
    private String dt_inicial_prest_srv = "";
    private String dt_final_prest_srv = "";
    private String tp_serv = "";
    private String emitente = "";
    private String pedagio = "";
    private String ipi = "";
    private String icms_st = "";

    public void setPedagio(String pedagio) {
        this.pedagio = pedagio;
    }

    public void setIpi(String ipi) {
        this.ipi = ipi;
    }

    public void setIcms_st(String icms_st) {
        this.icms_st = icms_st;
    }
    
    

    public void setCod_especie(String cod_especie) {
        this.cod_especie = cod_especie;
    }

    public void setInscricao_cliente(String inscricao_cliente) {
        this.inscricao_cliente = inscricao_cliente.replaceAll("[^0-9]", "");
    }

    public void setCod_acumulador(String cod_acumulador) {
        this.cod_acumulador = cod_acumulador;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public void setCod_exc_dief(String cod_exc_dief) {
        this.cod_exc_dief = cod_exc_dief;
    }

    public void setSigla_uf_cliente(String sigla_uf_cliente) {
        this.sigla_uf_cliente = sigla_uf_cliente;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setDoc_final(String doc_final) {
        this.doc_final = doc_final;
    }

    public void setDt_saida(String dt_saida) {
        this.dt_saida = dt_saida;
    }

    public void setDt_emissao(String dt_emissao) {
        this.dt_emissao = dt_emissao;
    }

    public void setVlr_contabil(String vlr_contabil) {
        this.vlr_contabil = vlr_contabil;
    }

    public void setVlr_exc_dief(String vlr_exc_dief) {
        this.vlr_exc_dief = vlr_exc_dief;
    }

    /**
     * Quando a Nota Fiscal importada possuir observação, a mesma deverá ser
     * gerada na guia na Complementar subguia Observações no grupo Observação de
     * interrese do fisco.
     *
     * @param observacao
     */
    public void setObservacao(String observacao) {
        this.observacao = removeSpecialChar(observacao).replace("\n", "").replace("\t", "").replace("\r", "");
    }

    /**
     * Quando no acumulador estiver selecionado o campo Habilitar digitação do
     * código do município, deve gerar o município de origem caso a modalidade
     * do frete for CIF, ou gerar o município de destino caso a modalidade do
     * frete for FOB.Quando a modalidade do frete for Sem Frete ou Terceiros irá
     * gerar o código do município do cadastro do cliente
     *
     * @param cod_mun
     */
    public void setCod_mun(String cod_mun) {
        this.cod_mun = cod_mun;
    }

    /**
     * Informar C=CIF ou F=FOB.
     *
     * @param mod_frete
     */
    public void setMod_frete(String mod_frete) {
        this.mod_frete = mod_frete;
    }

    /**
     * Apenas para o Estado de SE.
     *
     * @param cfop_estendido
     */
    public void setCfop_estendido(String cfop_estendido) {
        this.cfop_estendido = cfop_estendido;
    }

    /**
     * Apenas para o Estado de RS.
     *
     * @param cod_transf_cred
     */
    public void setCod_transf_cred(String cod_transf_cred) {
        this.cod_transf_cred = cod_transf_cred;
    }

    public void setCod_obs(String cod_obs) {
        this.cod_obs = cod_obs;
    }

    /**
     * Apenas para o Estado de MG.
     *
     * @param dt_visto_nf_transf
     */
    public void setDt_visto_nf_transf(String dt_visto_nf_transf) {
        this.dt_visto_nf_transf = dt_visto_nf_transf;
    }

    public void setCod_antec_trib(String cod_antec_trib) {
        this.cod_antec_trib = cod_antec_trib;
    }

    /**
     * Informar E=Emissão ou P=Pagamento.
     *
     * @param fato_ger_CRF
     */
    public void setFato_ger_CRF(String fato_ger_CRF) {
        this.fato_ger_CRF = fato_ger_CRF;
    }

    /**
     * Informar E=Emissão ou P=Pagamento.
     *
     * @param fato_ger_COSIRF
     */
    public void setFato_ger_COSIRF(String fato_ger_COSIRF) {
        this.fato_ger_COSIRF = fato_ger_COSIRF;
    }

    /**
     * Informar E=Emissão ou P=Pagamento.
     *
     * @param fato_ger_IRRFP
     */
    public void setFato_ger_IRRFP(String fato_ger_IRRFP) {
        this.fato_ger_IRRFP = fato_ger_IRRFP;
    }

    /**
     * Informar: 1=Receita Própria - serv. prestados / 2=Receita de Terceiros
     * (co - faturamento) / 3=Receita própria - cobrança de débitos / 4=Receita
     * Própria - venda de merc. / 5= Receita Própria - venda de serv. Prépago /
     * 6=Outras receitas próprias / 7=Outras Receitas de terceiros.
     *
     * @param tp_receita
     */
    public void setTp_receita(String tp_receita) {
        this.tp_receita = tp_receita;
    }

    public void setVlr_frete(String vlr_frete) {
        this.vlr_frete = vlr_frete;
    }

    public void setVlr_seguro(String vlr_seguro) {
        this.vlr_seguro = vlr_seguro;
    }

    public void setVlr_desp_acess(String vlr_desp_acess) {
        this.vlr_desp_acess = vlr_desp_acess;
    }

    public void setVlr_produtos(String vlr_produtos) {
        this.vlr_produtos = vlr_produtos;
    }

    /**
     * Origem da SUBTRI na nota: 0=Informações complementares ou 1=Quadro
     * Calculo do Imposto.
     *
     * @param vlr_bc_icms_st
     */
    public void setVlr_bc_icms_st(String vlr_bc_icms_st) {
        this.vlr_bc_icms_st = vlr_bc_icms_st;
    }

    /**
     * Apenas para o Estado de MG.
     *
     * @param out_saidas
     */
    public void setOut_saidas(String out_saidas) {
        this.out_saidas = out_saidas;
    }

    /**
     * Apenas para o Estado de MG.
     *
     * @param out_isentas
     */
    public void setOut_isentas(String out_isentas) {
        this.out_isentas = out_isentas;
    }

    /**
     * Apenas para o Estado de MG.
     *
     * @param sai_isentas_cf
     */
    public void setSai_isentas_cf(String sai_isentas_cf) {
        this.sai_isentas_cf = sai_isentas_cf;
    }

    /**
     * Apenas para o Estado de MG.
     *
     * @param sai_isentas_nf
     */
    public void setSai_isentas_nf(String sai_isentas_nf) {
        this.sai_isentas_nf = sai_isentas_nf;
    }

    public void setCod_mod_doc_fis(String cod_mod_doc_fis) {
        this.cod_mod_doc_fis = cod_mod_doc_fis;
    }

    public void setCod_fis_prest_serv(String cod_fis_prest_serv) {
        this.cod_fis_prest_serv = cod_fis_prest_serv;
    }

    public void setCod_sit_trib(String cod_sit_trib) {
        this.cod_sit_trib = cod_sit_trib;
    }

    public void setSub_serie(String sub_serie) {
        this.sub_serie = sub_serie;
    }

    public void setTp_titulo(String tp_titulo) {
        this.tp_titulo = tp_titulo;
    }

    public void setId_titulo(String id_titulo) {
        this.id_titulo = id_titulo;
    }

    public void setInscr_est_cli(String inscr_est_cli) {
        this.inscr_est_cli = inscr_est_cli.replaceAll("[^0-9]", "");
    }

    public void setInscr_mun_cli(String inscr_mun_cli) {
        this.inscr_mun_cli = inscr_mun_cli.replaceAll("[^0-9]", "");
    }

    /**
     * Informar apenas caracteres numéricos.
     *
     * @param chave_NFe
     */
    public void setChave_NFe(String chave_NFe) {
        try {
            this.chave_NFe = chave_NFe.replaceAll("[^0-9]", "");
        } catch (Exception e) {
            this.chave_NFe = "";
        }
    }

    public void setCod_rec_fethab(String cod_rec_fethab) {
        this.cod_rec_fethab = cod_rec_fethab;
    }

    /**
     * Informar: E=Empresa/C=Client e
     *
     * @param resp_fethab
     */
    public void setResp_fethab(String resp_fethab) {
        this.resp_fethab = resp_fethab;
    }

    /**
     * Informar: 0=CT-e Normal / 1=Ct-e de Complemento de valores / 2=CT-e
     * Emitido em hipótese de anulação de débito.
     *
     * @param tp_cte
     */
    public void setTp_cte(String tp_cte) {
        this.tp_cte = tp_cte;
    }

    /**
     * Informar apenas caracteres numéricos.
     *
     * @param cte_ref
     */
    public void setCte_ref(String cte_ref) {
        this.cte_ref = cte_ref;
    }

    public void setCod_inf_compl(String cod_inf_compl) {
        this.cod_inf_compl = cod_inf_compl;
    }

    public void setInf_compl(String inf_compl) {
        this.inf_compl = inf_compl;
    }

    /**
     * Apenas os CSTs 01, 02, 07, 08, 09, 49 e 99 e quando utilizado os modelos
     * de espécie 02E, 08, 08B, 09, 10, 11, 13, 14, 15, 18, 26,27 e 57.
     *
     * @param cst_pis_cofins
     */
    public void setCst_pis_cofins(String cst_pis_cofins) {
        this.cst_pis_cofins = cst_pis_cofins;
    }

    public void setNat_receita(String nat_receita) {
        this.nat_receita = nat_receita;
    }

    /**
     * Apenas para os modelos de espécie 02E, 08, 08B, 09, 10, 11, 13, 14, 15,
     * 18, 26, 27 e 57.
     *
     * @param vlr_srv_pis_cofins
     */
    public void setVlr_srv_pis_cofins(String vlr_srv_pis_cofins) {
        this.vlr_srv_pis_cofins = vlr_srv_pis_cofins;
    }

    /**
     * Apenas para os modelos de espécie 02E, 08, 08B, 09, 10, 11, 13, 14, 15,
     * 18, 26, 27 e 57
     *
     * @param bc_pis_cofins
     */
    public void setBc_pis_cofins(String bc_pis_cofins) {
        this.bc_pis_cofins = bc_pis_cofins;
    }

    public void setAliq_pis(String aliq_pis) {
        this.aliq_pis = aliq_pis;
    }

    public void setAliq_cofins(String aliq_cofins) {
        this.aliq_cofins = aliq_cofins;
    }

    public void setQtd_kwh(String qtd_kwh) {
        this.qtd_kwh = qtd_kwh;
    }

    /**
     * Somente informar para notas de modelo/espécie "Nota Fiscal Eletrônica,
     * código 55", ou "Nota Fiscal, modelo 01, código 01", "Nota Fiscal Avulsa,
     * código 1B", "Nota Fiscal de Produtor, modelo 04, código 04" com situação
     * do documento igual a "Documento fiscal emitido com base em regime
     * especial" e que possuam os impostos 2-IPI ou 30- IPIM. Valores válidos:
     * 50 - Saída tributada 51 - Saída tributada com alíquota zero 52 - Saída
     * isenta 53 - Saída nãotributada 54 - Saída imune 55 - Saída com suspensão
     * 99 - Outras saídas
     *
     * @param cst_ipi_cos_sit_trib
     */
    public void setCst_ipi_cos_sit_trib(String cst_ipi_cos_sit_trib) {
        this.cst_ipi_cos_sit_trib = cst_ipi_cos_sit_trib;
    }

    /**
     * Valores Válidos: 1 - Comercial/Industrial 2 - Poder Público 3 -
     * Residencial/Pessoa Física 4 - Público 5 - Semi-Público 6 – Outros
     *
     * @param tp_assinante
     */
    public void setTp_assinante(String tp_assinante) {
        this.tp_assinante = tp_assinante;
    }

    /**
     * Apenas para o Estado de SP. Informar código da tabela - Enquadramento
     * Legal - Crédito Acumulado.
     *
     * @param tp_credito_acum
     */
    public void setTp_credito_acum(String tp_credito_acum) {
        this.tp_credito_acum = tp_credito_acum;
    }

    /**
     * Informar correspondente ao código SCP
     *
     * @param lanc_scp
     */
    public void setLanc_scp(String lanc_scp) {
        this.lanc_scp = lanc_scp;
    }

    /**
     * Apenas para o estado de SP.
     *
     * @param per_prest_srv
     */
    public void setPer_prest_srv(String per_prest_srv) {
        this.per_prest_srv = per_prest_srv;
    }

    /**
     * Apenas para o estado de SP.
     *
     * @param dt_inicial_prest_srv
     */
    public void setDt_inicial_prest_srv(String dt_inicial_prest_srv) {
        this.dt_inicial_prest_srv = dt_inicial_prest_srv;
    }

    /**
     * Apenas para o estado de SP.
     *
     * @param dt_final_prest_srv
     */
    public void setDt_final_prest_srv(String dt_final_prest_srv) {
        this.dt_final_prest_srv = dt_final_prest_srv;
    }

    /**
     * Informar 1 = "Transporte de cargas" ou 2 = "Transporte de passageiros".
     *
     * @param tp_serv
     */
    public void setTp_serv(String tp_serv) {
        this.tp_serv = tp_serv;
    }

    /**
     * Informar: P=Próprio ou T=Terceiros. Caso não tenha informação no campo,
     * deverá considerar como Próprio
     *
     * @param emitente
     */
    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    @Override
    public String getLayout() throws Exception {
        String linha = "|" + id + "|"
                + cod_especie + "|"
                + inscricao_cliente + "|"
                + cod_acumulador + "|"
                + cfop + "|"
                + cod_exc_dief + "|"
                + sigla_uf_cliente + "|"
                + segmento + "|"
                + nro_doc + "|"
                + serie + "|"
                + doc_final + "|"
                + dt_saida + "|"
                + dt_emissao + "|"
                + vlr_contabil + "|"
                + vlr_exc_dief + "|"
                + observacao + "|"
                + cod_mun + "|"
                + mod_frete + "|"
                + cfop_estendido + "|"
                + cod_transf_cred + "|"
                + cod_obs + "|"
                + dt_visto_nf_transf + "|"
                + cod_antec_trib + "|"
                + fato_ger_CRF + "|"
                + fato_ger_COSIRF + "|"
                + fato_ger_IRRFP + "|"
                + tp_receita + "|"
                + vlr_frete + "|"
                + vlr_seguro + "|"
                + vlr_desp_acess + "|"
                + vlr_produtos + "|"
                + vlr_bc_icms_st + "|"
                + out_saidas + "|"
                + out_isentas + "|"
                + sai_isentas_cf + "|"
                + sai_isentas_nf + "|"
                + cod_mod_doc_fis + "|"
                + cod_fis_prest_serv + "|"
                + cod_sit_trib + "|"
                + sub_serie + "|"
                + tp_titulo + "|"
                + id_titulo + "|"
                + inscr_est_cli + "|"
                + inscr_mun_cli + "|"
                + chave_NFe + "|"
                + cod_rec_fethab + "|"
                + resp_fethab + "|"
                + tp_cte + "|"
                + cte_ref + "|"
                + cod_inf_compl + "|"
                + inf_compl + "|"
                + cst_pis_cofins + "|"
                + nat_receita + "|"
                + vlr_srv_pis_cofins + "|"
                + bc_pis_cofins + "|"
                + aliq_pis + "|"
                + aliq_cofins + "|"
                + qtd_kwh + "|"
                + cst_ipi_cos_sit_trib + "|"
                + tp_assinante + "|"
                + tp_credito_acum + "|"
                + lanc_scp + "|"
                + per_prest_srv + "|"
                + dt_inicial_prest_srv + "|"
                + dt_final_prest_srv + "|"
                + tp_serv + "|"
                + emitente + "|" 
                + pedagio + "|" 
                + ipi + "|" 
                + icms_st + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg2000_Notas_Fiscais_Saidas i = new Reg2000_Notas_Fiscais_Saidas();

        try {
            i.getLayout();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
