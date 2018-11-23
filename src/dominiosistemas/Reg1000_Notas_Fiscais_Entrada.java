/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import static tools.RemoveSpecialChar.removeSpecialChar;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg1000_Notas_Fiscais_Entrada implements Exportacao_DomSis {

    private final String id = "1000";
    private String cod_especie = "";
    private String inscricao_fornecedor = "";
    private String cod_excl_DIEF = "";
    private String cod_acum = "";
    private String cfop = "";
    private String segmento = "";
    private String nro_doc = "";
    private String serie = "";
    private String nro_doc_final = "";
    private String dt_entrada = "";
    private String dt_emissao = "";
    private String vlr_contabil = "";
    private String vlr_excl_DIEF = "";
    private String observacao = "";
    private String mod_frete = "";
    private String emitente_nf = "";
    private String cfop_estend = "";
    private String cod_transf_cred = "";
    private String cod_rec_iss_ret = "";
    private String cod_rec_irrf = "";
    private String cod_obs = "";
    private String dt_visto_nf_transf_cred_icms = "";
    private String fato_gerador_CRF = "";
    private String fato_gerador_IRRF = "";
    private String vlr_frete = "";
    private String vlr_seguro = "";
    private String vlr_despesas = "";
    private String vlr_pis = "";
    private String cod_antec_trib = "";
    private String vlr_cofins = "";
    private String vlr_dare = "";
    private String aliq_dare = "";
    private String bc_icms_st = "";
    private String entrada_saida_isenta = "";
    private String outras_entradas = "";
    private String vlr_transporte_bc = "";
    private String cod_ressarc = "";
    private String vlr_produtos = "";
    private String mun_origem = "";
    private String cod_mod_doc_fis = "";
    private String cod_sit_trib = "";
    private String sub_serie = "";
    private String insc_est_fornecedor = "";
    private String insc_mun_fornecedor = "";
    private String cod_oper_prest = "";
    private String vlr_ded_rec = "";
    private String competencia = "";
    private String operacao = "";
    private String nro_parecer_fiscal = "";
    private String dt_parecer_fiscal = "";
    private String nro_decl_import = "";
    private String beneficio_fiscal = "";
    private String chave_nfe = "";
    private String cod_fethab = "";
    private String resposavel_fethab = "";
    private String cfop_doc_fisc = "";
    private String tp_cte = "";
    private String cte_ref = "";
    private String mod_import = "";
    private String cod_inf_compl = "";
    private String inf_compl = "";
    private String classe_cons = "";
    private String tp_ligacao = "";
    private String grupo_tensao = "";
    private String tipo_assinante = "";
    private String kwh_consumido = "";
    private String vlr_fornecido_gas_energia = "";
    private String vlr_cobrado_terceiros = "";
    private String tp_doc_imp = "";
    private String nro_ato_concess_drawback = "";
    private String nat_frete_pis_cofins = "";
    private String cst_pis_cofins = "";
    private String bc_pis_cofins = "";
    private String vlr_serv_pis_cofins = "";
    private String bc_pis_cofins_2 = "";
    private String aliq_pis = "";
    private String aliq_cofins = "";
    private String chave_nfse = "";
    private String nro_processo_concessorio = "";
    private String orig_processo = "";
    private String dt_escrituracao = "";
    private String CFPS = "";
    private String nat_rec_pis_cofins = "";
    private String cst_ipi_cod_sit_trib = "";
    private String lanc_scp = "";
    private String tp_servico = "";
    private String mun_destino = "";
    private String pedagio = "";
    private String ipi = "";
    private String icms_st = "";

    public Reg1000_Notas_Fiscais_Entrada() {
        System.out.println("Iniciando Registro: 1000");
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setCod_especie(String cod_especie) {
        this.cod_especie = cod_especie;
    }

    /**
     * CNPJ, CPF, CEI ou Outros.
     *
     * @param inscricao_fornecedor
     */
    public void setInscricao_fornecedor(String inscricao_fornecedor) {
        this.inscricao_fornecedor = inscricao_fornecedor.replaceAll("[^0-9]", "");
    }

    public void setCod_excl_DIEF(String cod_excl_DIEF) {
        this.cod_excl_DIEF = cod_excl_DIEF;
    }

    public void setCod_acum(String cod_acum) {
        this.cod_acum = cod_acum;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
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

    public void setNro_doc_final(String nro_doc_final) {
        this.nro_doc_final = nro_doc_final;
    }

    public void setDt_entrada(String dt_entrada) {
        try {
            this.dt_entrada = dt_entrada;
        } catch (Exception e)  {
            this.dt_entrada = this.dt_emissao;
        }
    }

    public void setDt_emissao(String dt_emissao) {
        this.dt_emissao = dt_emissao;
    }

    public void setVlr_contabil(String vlr_contabil) {
        this.vlr_contabil = vlr_contabil;
    }

    public void setVlr_excl_DIEF(String vlr_excl_DIEF) {
        this.vlr_excl_DIEF = vlr_excl_DIEF;
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
     * Informar: C = CIF ou F = FOB.
     *
     * @param mod_frete
     */
    public void setMod_frete(String mod_frete) {
        this.mod_frete = mod_frete;
    }

    /**
     * Informar: P=Próprio ou T=Terceiros.
     *
     * @param emitente_nf
     */
    public void setEmitente_nf(String emitente_nf) {
        this.emitente_nf = emitente_nf;
    }

    /**
     * Apenas para estado de SE.
     *
     * @param cfop_estend
     */
    public void setCfop_estend(String cfop_estend) {
        this.cfop_estend = cfop_estend;
    }

    /**
     * Apenas para estado de RS.
     *
     * @param cod_transf_cred
     */
    public void setCod_transf_cred(String cod_transf_cred) {
        this.cod_transf_cred = cod_transf_cred;
    }

    public void setCod_rec_iss_ret(String cod_rec_iss_ret) {
        this.cod_rec_iss_ret = cod_rec_iss_ret;
    }

    public void setCod_rec_irrf(String cod_rec_irrf) {
        this.cod_rec_irrf = cod_rec_irrf;
    }

    public void setCod_obs(String cod_obs) {
        this.cod_obs = cod_obs;
    }

    /**
     * Apenas para estado de MG.
     *
     * @param dt_visto_nf_transf_cred_icms
     */
    public void setDt_visto_nf_transf_cred_icms(String dt_visto_nf_transf_cred_icms) {
        this.dt_visto_nf_transf_cred_icms = dt_visto_nf_transf_cred_icms;
    }

    /**
     * Informar: E = Emissão ou P = Pagamento
     *
     * @param fato_gerador_CRF
     */
    public void setFato_gerador_CRF(String fato_gerador_CRF) {
        this.fato_gerador_CRF = fato_gerador_CRF;
    }

    /**
     * Informar: E = Emissão ou P = Pagamento
     *
     * @param fato_gerador_IRRF
     */
    public void setFato_gerador_IRRF(String fato_gerador_IRRF) {
        this.fato_gerador_IRRF = fato_gerador_IRRF;
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

    public void setVlr_pis(String vlr_pis) {
        this.vlr_pis = vlr_pis;
    }

    public void setCod_antec_trib(String cod_antec_trib) {
        this.cod_antec_trib = cod_antec_trib;
    }

    public void setVlr_cofins(String vlr_cofins) {
        this.vlr_cofins = vlr_cofins;
    }

    /**
     * Apenas para o estado SE.
     *
     * @param vlr_dare
     */
    public void setVlr_dare(String vlr_dare) {
        this.vlr_dare = vlr_dare;
    }

    /**
     * Apenas para o estado SE.
     *
     * @param aliq_dare
     */
    public void setAliq_dare(String aliq_dare) {
        this.aliq_dare = aliq_dare;
    }

    /**
     * Informar: 0=informações Complementares / 1=Quadro calculado do imposto /
     * 2=Apurado pelo informante.
     *
     * @param bc_icms_st
     */
    public void setBc_icms_st(String bc_icms_st) {
        this.bc_icms_st = bc_icms_st;
    }

    /**
     * Apenas para o estado MG.
     *
     * @param entrada_saida_isenta
     */
    public void setEntrada_saida_isenta(String entrada_saida_isenta) {
        this.entrada_saida_isenta = entrada_saida_isenta;
    }

    /**
     * Apenas para o estado MG.
     *
     * @param outras_entradas
     */
    public void setOutras_entradas(String outras_entradas) {
        this.outras_entradas = outras_entradas;
    }

    /**
     * Apenas para o estado MG.
     *
     * @param vlr_transporte_bc
     */
    public void setVlr_transporte_bc(String vlr_transporte_bc) {
        this.vlr_transporte_bc = vlr_transporte_bc;
    }

    public void setCod_ressarc(String cod_ressarc) {
        this.cod_ressarc = cod_ressarc;
    }

    public void setVlr_produtos(String vlr_produtos) {
        this.vlr_produtos = vlr_produtos;
    }

    public void setMun_origem(String mun_origem) {
        this.mun_origem = mun_origem;
    }

    public void setCod_mod_doc_fis(String cod_mod_doc_fis) {
        this.cod_mod_doc_fis = cod_mod_doc_fis;
    }

    public void setCod_sit_trib(String cod_sit_trib) {
        this.cod_sit_trib = cod_sit_trib;
    }

    public void setSub_serie(String sub_serie) {
        this.sub_serie = sub_serie;
    }

    public void setInsc_est_fornecedor(String insc_est_fornecedor) {
        this.insc_est_fornecedor = insc_est_fornecedor.replaceAll("[^0-9]", "");;
    }

    public void setInsc_mun_fornecedor(String insc_mun_fornecedor) {
        this.insc_mun_fornecedor = insc_mun_fornecedor;
    }

    public void setCod_oper_prest(String cod_oper_prest) {
        this.cod_oper_prest = cod_oper_prest;
    }

    public void setVlr_ded_rec(String vlr_ded_rec) {
        this.vlr_ded_rec = vlr_ded_rec;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    /**
     * Apenas para o estado PA.
     *
     * @param operacao
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setNro_parecer_fiscal(String nro_parecer_fiscal) {
        this.nro_parecer_fiscal = nro_parecer_fiscal;
    }

    public void setDt_parecer_fiscal(String dt_parecer_fiscal) {
        this.dt_parecer_fiscal = dt_parecer_fiscal;
    }

    public void setNro_decl_import(String nro_decl_import) {
        this.nro_decl_import = nro_decl_import;
    }

    /**
     * Informar: S ou N.
     *
     * @param beneficio_fiscal
     */
    public void setBeneficio_fiscal(String beneficio_fiscal) {
        this.beneficio_fiscal = beneficio_fiscal;
    }

    public void setChave_nfe(String chave_nfe) {
        this.chave_nfe = chave_nfe.replaceAll("[^0-9]", "");
    }

    public void setCod_fethab(String cod_fethab) {
        this.cod_fethab = cod_fethab;
    }

    /**
     * Informar: E=Empresa / C=Cliente.
     *
     * @param resposavel_fethab
     */
    public void setResposavel_fethab(String resposavel_fethab) {
        this.resposavel_fethab = resposavel_fethab;
    }

    public void setCfop_doc_fisc(String cfop_doc_fisc) {
        this.cfop_doc_fisc = cfop_doc_fisc;
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

    public void setCte_ref(String cte_ref) {
        this.cte_ref = cte_ref;
    }

    /**
     * Informar: 1=Com direito a crédito / 2=Compensação / 3=Regime especial /
     * 4=Sem direito a crédito / 5= Outras situações – sem crédito.
     *
     * @param mod_import
     */
    public void setMod_import(String mod_import) {
        this.mod_import = mod_import;
    }

    public void setCod_inf_compl(String cod_inf_compl) {
        this.cod_inf_compl = cod_inf_compl;
    }

    public void setInf_compl(String inf_compl) {
        this.inf_compl = inf_compl;
    }

    public void setClasse_cons(String classe_cons) {
        this.classe_cons = classe_cons;
    }

    public void setTp_ligacao(String tp_ligacao) {
        this.tp_ligacao = tp_ligacao;
    }

    public void setGrupo_tensao(String grupo_tensao) {
        this.grupo_tensao = grupo_tensao;
    }

    public void setTipo_assinante(String tipo_assinante) {
        this.tipo_assinante = tipo_assinante;
    }

    public void setKwh_consumido(String kwh_consumido) {
        this.kwh_consumido = kwh_consumido;
    }

    public void setVlr_fornecido_gas_energia(String vlr_fornecido_gas_energia) {
        this.vlr_fornecido_gas_energia = vlr_fornecido_gas_energia;
    }

    public void setVlr_cobrado_terceiros(String vlr_cobrado_terceiros) {
        this.vlr_cobrado_terceiros = vlr_cobrado_terceiros;
    }

    /**
     * Informar: 10=Declaração de Importação / 1=Declaração Simplificada de
     * Importação.
     *
     * @param tp_doc_imp
     */
    public void setTp_doc_imp(String tp_doc_imp) {
        this.tp_doc_imp = tp_doc_imp;
    }

    public void setNro_ato_concess_drawback(String nro_ato_concess_drawback) {
        this.nro_ato_concess_drawback = nro_ato_concess_drawback;
    }

    /**
     * Apenas para modelos de espécies 08, 08B, 09, 10, 11, 26, 27 e 57.
     * Informar: 0=Op. de venda, com ônus suportado pelo estab. Vendedor / 1=Op.
     * de venda, com ônus suportado pelo adquirente / 2=Op. de compra (bens para
     * revenda, matérias - prima e outros produtos geradores de crédito) / 3=Op.
     * de compra (bens para revenda, matérias - prima e outros produtos não
     * geradores de crédito) / 4=Transf. de prod. acabados entre estabelecimento
     * pessoa jurídica / 5=Transf. De prod. em elaboração entre estabelecimento
     * pessoa jurídica / 9=Outras.
     *
     * @param nat_frete_pis_cofins
     */
    public void setNat_frete_pis_cofins(String nat_frete_pis_cofins) {
        this.nat_frete_pis_cofins = nat_frete_pis_cofins;
    }

    /**
     * Informar somente CSTs de código 50 à 99 quando utilizado um dos seguintes
     * modelos de espécie: 08, 08B, 09, 10, 11, 26, 27 e 57.
     *
     * @param cst_pis_cofins
     */
    public void setCst_pis_cofins(String cst_pis_cofins) {
        this.cst_pis_cofins = cst_pis_cofins;
    }

    /**
     * Apenas para os modelos de espécie 08, 08B, 09, 10, 11, 26, 27 e 57.
     * Informar: 03=Aquisição de serviços utilizados como insumo /
     * 07=Armazenagem de mercadoria e frete na operação de venda / 13=Outras
     * operações com direito a crédito / 14=Atividades de transporte de cargas –
     * Subcontratação.
     *
     * @param bc_pis_cofins
     */
    public void setBc_pis_cofins(String bc_pis_cofins) {
        this.bc_pis_cofins = bc_pis_cofins;
    }

    /**
     * Apenas para os modelos de espécie 08, 08B, 09, 10, 11, 26, 27 e 57.
     *
     * @param vlr_serv_pis_cofins
     */
    public void setVlr_serv_pis_cofins(String vlr_serv_pis_cofins) {
        this.vlr_serv_pis_cofins = vlr_serv_pis_cofins;
    }

    /**
     * Apenas para os modelos de espécie 08, 08B, 09, 10, 11, 26, 27 e 57.
     *
     * @param bc_pis_cofins_2
     */
    public void setBc_pis_cofins_2(String bc_pis_cofins_2) {
        this.bc_pis_cofins_2 = bc_pis_cofins_2;
    }

    public void setAliq_pis(String aliq_pis) {
        this.aliq_pis = aliq_pis;
    }

    public void setAliq_cofins(String aliq_cofins) {
        this.aliq_cofins = aliq_cofins;
    }

    public void setChave_nfse(String chave_nfse) {
        this.chave_nfse = chave_nfse;
    }

    /**
     * Apenas quando informado para a Natureza do frete os códigos 1, 3, 4 ou 5.
     *
     * @param nro_processo_concessorio
     */
    public void setNro_processo_concessorio(String nro_processo_concessorio) {
        this.nro_processo_concessorio = nro_processo_concessorio;
    }

    /**
     * Apenas para os modelos de espécie 08, 08B, 09, 10, 11, 26, 27 e 57 e
     * quando informado para a Natureza do frete os códigos 1, 3, 4 e 5.
     * Informar: 1=Justiça Federal/3=Secretaria da Receita Federal do Brasil /
     * 9=Outros.
     *
     * @param orig_processo
     */
    public void setOrig_processo(String orig_processo) {
        this.orig_processo = orig_processo;
    }

    /**
     * Data da escrituração quando a situação for Documento Extemporâneo.
     *
     * @param dt_escrituracao
     */
    public void setDt_escrituracao(String dt_escrituracao) {
        this.dt_escrituracao = dt_escrituracao;
    }

    /**
     * Apenas para o estado de DF
     *
     * @param CFPS
     */
    public void setCFPS(String CFPS) {
        this.CFPS = CFPS;
    }

    /**
     * Apenas para os modelos de espécie 07, 08, 08B, 09, 10, 11 e 57
     *
     * @param nat_rec_pis_cofins
     */
    public void setNat_rec_pis_cofins(String nat_rec_pis_cofins) {
        this.nat_rec_pis_cofins = nat_rec_pis_cofins;
    }

    /**
     * Somente informar para notas de modelo/espécie "Nota Fiscal Eletrônica,
     * código 55", ou "Nota Fiscal, modelo 01, código 01", "Nota Fiscal Avulsa,
     * código 1B", "Nota Fiscal de Produtor, modelo 04, código 04" com situação
     * do documento igual a "Documento fiscal emitido com base em regime
     * especial" e que possuam os impostos 2-IPI ou 30- IPIM. Valores válidos:
     * 00 - Entrada com recuperação de crédito 01 - Entrada tributada com
     * alíquota zero 02 - Entrada isenta 03 - Entrada nãotributada 04 - Entrada
     * imune 05 - Entrada com suspensão 49 - Outras entradas
     *
     * @param cst_ipi_cod_sit_trib
     */
    public void setCst_ipi_cod_sit_trib(String cst_ipi_cod_sit_trib) {
        this.cst_ipi_cod_sit_trib = cst_ipi_cod_sit_trib;
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
     * Informar 1 = "Transporte de cargas" ou 2 = "Transporte de passageiros"’
     *
     * @param tp_servico
     */
    public void setTp_servico(String tp_servico) {
        this.tp_servico = tp_servico;
    }

    /**
     * Somente importar quando a espécie da nota estiver vinculada a "CT-e
     * modelo 57", no acumulador estiver selecionada a opção "Devolução", na
     * guia "Impostos" do acumulador estiver informado o imposto "145-DIFAL" e o
     * CFOP inicie em 2- XXX.
     *
     * @param mun_destino
     */
    public void setMun_destino(String mun_destino) {
        this.mun_destino = mun_destino;
    }

    public void setPedagio(String pedagio) {
        this.pedagio = pedagio;
    }

    public void setIpi(String ipi) {
        this.ipi = ipi;
    }

    public void setIcms_st(String icms_st) {
        this.icms_st = icms_st;
    }

    @Override
    public String getLayout() throws IOException {
        String linha = "|" + id + "|" + cod_especie + "|" + inscricao_fornecedor
                + "|" + cod_excl_DIEF + "|" + cod_acum + "|" + cfop
                + "|" + segmento + "|" + nro_doc + "|" + serie
                + "|" + nro_doc_final + "|" + dt_entrada + "|" + dt_emissao
                + "|" + vlr_contabil + "|" + vlr_excl_DIEF + "|" + observacao
                + "|" + mod_frete + "|" + emitente_nf + "|" + cfop_estend
                + "|" + cod_transf_cred + "|" + cod_rec_iss_ret
                + "|" + cod_rec_irrf + "|" + cod_obs
                + "|" + dt_visto_nf_transf_cred_icms + "|" + fato_gerador_CRF
                + "|" + fato_gerador_IRRF + "|" + vlr_frete + "|" + vlr_seguro
                + "|" + vlr_despesas + "|" + vlr_pis + "|" + cod_antec_trib
                + "|" + vlr_cofins + "|" + vlr_dare + "|" + aliq_dare
                + "|" + bc_icms_st + "|" + entrada_saida_isenta
                + "|" + outras_entradas + "|" + vlr_transporte_bc
                + "|" + cod_ressarc + "|" + vlr_produtos + "|" + mun_origem
                + "|" + cod_mod_doc_fis + "|" + cod_sit_trib + "|" + sub_serie
                + "|" + insc_est_fornecedor + "|" + insc_mun_fornecedor
                + "|" + cod_oper_prest + "|" + vlr_ded_rec + "|" + competencia
                + "|" + operacao + "|" + nro_parecer_fiscal + "|" + dt_parecer_fiscal
                + "|" + nro_decl_import + "|" + beneficio_fiscal + "|" + chave_nfe
                + "|" + cod_fethab + "|" + resposavel_fethab + "|" + cfop_doc_fisc
                + "|" + tp_cte + "|" + cte_ref + "|" + mod_import + "|" + cod_inf_compl
                + "|" + inf_compl + "|" + classe_cons + "|" + tp_ligacao
                + "|" + grupo_tensao + "|" + tipo_assinante + "|" + kwh_consumido
                + "|" + vlr_fornecido_gas_energia + "|" + vlr_cobrado_terceiros
                + "|" + tp_doc_imp + "|" + nro_ato_concess_drawback
                + "|" + nat_frete_pis_cofins + "|" + cst_pis_cofins
                + "|" + bc_pis_cofins + "|" + vlr_serv_pis_cofins
                + "|" + bc_pis_cofins_2 + "|" + aliq_pis + "|" + aliq_cofins
                + "|" + chave_nfse + "|" + nro_processo_concessorio
                + "|" + orig_processo + "|" + dt_escrituracao + "|" + CFPS
                + "|" + nat_rec_pis_cofins + "|" + cst_ipi_cod_sit_trib
                + "|" + lanc_scp + "|" + tp_servico + "|" + mun_destino
                + "|" + pedagio + "|" + ipi + "|" + icms_st + "|";

        System.out.println("\nRegistro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg1000_Notas_Fiscais_Entrada i = new Reg1000_Notas_Fiscais_Entrada();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
