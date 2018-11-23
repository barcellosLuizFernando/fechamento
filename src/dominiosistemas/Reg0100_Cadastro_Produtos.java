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
public class Reg0100_Cadastro_Produtos implements Exportacao_DomSis {

    private final String id = "0100";
    private String cod_produto = "";
    private String descricao_produto = "";
    private String cod_NBM = "";
    private String cod_NCM = "";
    private String cod_NCM_ex = "";
    private String cod_barras = "";
    private String cod_imp_impor = "";
    private String cod_grupo_produtos = "";
    private String unidade_medida = "";
    private String unidade_medida_invent = "";
    private String tipo_produto = "";
    private String tipo_arma_fogo = "";
    private String descricao_arma_fogo = "";
    private String tipo_medicamento = "";
    private String srv_trib_iss = "";
    private String cod_chassi = "";
    private String valor_unit = "";
    private String qtd_ini_estoque = "";
    private String vlr_ini_estoque = "";
    private String cod_sit_trib_icms = "";
    private String aliq_icms = "";
    private String aliq_ipi = "";
    private String periodo_ipi = "";
    private String observacao = "";
    private String exp_DNF = "";
    private String exp_IPI = "";
    private String DNF_cod_especie = "";
    private String DNF_unidade_medida_padrao = "";
    private String DNF_fator_conv = "";
    private String DNF_cod_prod = "";
    private String DNF_capacidade = "";
    private String sedic_cod_EAN = "";
    private String sedic_cod_prod_relev = "";
    private String scanc_gerar = "";
    private String scanc_doc_prod = "";
    private String scanc_contem_gas = "";
    private String scanc_tipo_produ = "";
    private String grf_ctb_gerar = "";
    private String grf_cod_prod = "";
    private String dief_unidade = "";
    private String dief_tipo_prod = "";
    private String sintegra_88st_gerar = "";
    private String sintegra_88st_cod_prod = "";
    private String go_inf_compl = "";
    private String go_cod_prod = "";
    private String go_prod_rel = "";
    private String am_cesta_basica = "";
    private String am_cod_prod_DAM = "";
    private String rs_prod_ST = "";
    private String rs_dt_ini_ST = "";
    private String rs_preco_tabelado = "";
    private String rs_valor_unit_ST = "";
    private String rs_MVA_ST = "";
    private String rs_grupo_ST = "";
    private String pr_equip_ecf = "";
    private String ms_incentivo_fiscal = "";
    private String df_regime_especial = "";
    private String df_item_padrao = "";
    private String pe_tipo_produto = "";
    private String sp_controla_ressarcimento = "";
    private String sp_data_saldo_inicial = "";
    private String sp_valor_unitario = "";
    private String sp_quantidade_controle = "";
    private String sp_valor_final = "";
    private String sped_genero = "";
    private String sped_cod_servico = "";
    private String sped_tipo_item = "";
    private String sped_classificacao = "";
    private String sped_cta_estoque_proprio = "";
    private String sped_cta_estoque_em_terceiros = "";
    private String sped_cta_de_terceiros = "";
    private String sped_receita = "";
    private String sped_energia = "";
    private String data_cadastro = "";
    private String escriturado_LMC = "";
    private String cod_comb_DF = "";
    private String cod_comb_ANP = "";
    private String produto_relacionado_MP540 = "";
    private String permite_descr_compl = "";
    private String cod_ativ_INSS = "";
    private String dacon_tipo_produto = "";
    private String dacon_credito_presumido = "";
    private String desconsiderar = "";
    private String sped_cta_em_processo = "";
    private String sped_cta_em_processo_hist = "";
    private String sped_cta_em_processo_acabado = "";
    private String sped_cta_em_processo_hist_acabado = "";
    private String codigo_cest = "";

    public String getDescricao_produto() {
        return descricao_produto;
    }
    
    public void setCod_produto(String cod_produto) {
        this.cod_produto = cod_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = removeSpecialChar(descricao_produto);
    }

    public void setCod_NBM(String cod_NBM) {
        this.cod_NBM = cod_NBM;
    }

    public void setCod_NCM(String cod_NCM) {
        this.cod_NCM = cod_NCM;
    }

    public void setCod_NCM_ex(String cod_NCM_ex) {
        this.cod_NCM_ex = cod_NCM_ex;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public void setCod_imp_impor(String cod_imp_impor) {
        this.cod_imp_impor = cod_imp_impor;
    }

    /**
     * Informar código CFME registro 0160.
     *
     * @param cod_grupo_produtos
     */
    public void setCod_grupo_produtos(String cod_grupo_produtos) {
        this.cod_grupo_produtos = cod_grupo_produtos;
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = removeSpecialChar(unidade_medida).toUpperCase();
    }

    /**
     * Informar S=Sim ou N=Não.
     *
     * @param unidade_medida_invent
     */
    public void setUnidade_medida_invent(String unidade_medida_invent) {
        this.unidade_medida_invent = unidade_medida_invent;
    }

    /**
     * Informar : A=Arma de fogo, M=Medicamentos, V=Veículos novos ou O=Outros.
     *
     * @param tipo_produto
     */
    public void setTipo_produto(String tipo_produto) {
        this.tipo_produto = tipo_produto;
    }

    /**
     * Preencher apenas quando o tipo do produto for igual a Arma de fogo.
     * Informar: 0=Uso permitido ou 1=Uso restrito.
     *
     * @param tipo_arma_fogo
     */
    public void setTipo_arma_fogo(String tipo_arma_fogo) {
        this.tipo_arma_fogo = tipo_arma_fogo;
    }

    /**
     * Preencher apenas quando tipo de produto for igual a Arma de fogo.
     *
     * @param descricao_arma_fogo
     */
    public void setDescricao_arma_fogo(String descricao_arma_fogo) {
        this.descricao_arma_fogo = descricao_arma_fogo;
    }

    /**
     * Preencher apenas quando tipo do produto for igual a Medicamento.
     * Informar: 0=Similar, 1=Genérico, 2=Ético ou marca.
     *
     * @param tipo_medicamento
     */
    public void setTipo_medicamento(String tipo_medicamento) {
        this.tipo_medicamento = tipo_medicamento;
    }

    /**
     * Preencher apenas quando tipo de produto igual a Outros. Informar: S=Sim
     * ou N=Não.
     *
     * @param srv_trib_iss
     */
    public void setSrv_trib_iss(String srv_trib_iss) {
        this.srv_trib_iss = srv_trib_iss;
    }

    /**
     * Preencher apenas quando o tipo do produto for igual a veículos novos.
     *
     * @param cod_chassi
     */
    public void setCod_chassi(String cod_chassi) {
        this.cod_chassi = cod_chassi;
    }

    public void setValor_unit(String valor_unit) {
        this.valor_unit = valor_unit;
    }

    public void setQtd_ini_estoque(String qtd_ini_estoque) {
        this.qtd_ini_estoque = qtd_ini_estoque;
    }

    public void setVlr_ini_estoque(String vlr_ini_estoque) {
        this.vlr_ini_estoque = vlr_ini_estoque;
    }

    /**
     * CFME tabela oficial do CONFAZ (www.fazenda.gov.br/confaz)
     *
     * @param cod_sit_trib_icms
     */
    public void setCod_sit_trib_icms(String cod_sit_trib_icms) {
        this.cod_sit_trib_icms = cod_sit_trib_icms;
    }

    public void setAliq_icms(String aliq_icms) {
        this.aliq_icms = aliq_icms;
    }

    public void setAliq_ipi(String aliq_ipi) {
        this.aliq_ipi = aliq_ipi;
    }

    /**
     * Informar: D=Decendial, M=Mensal.
     *
     * @param periodo_ipi
     */
    public void setPeriodo_ipi(String periodo_ipi) {
        this.periodo_ipi = periodo_ipi;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * Informar: S=Sim ou N=Não
     *
     * @param exp_DNF
     */
    public void setExp_DNF(String exp_DNF) {
        this.exp_DNF = exp_DNF;
    }

    public void setExp_IPI(String exp_IPI) {
        this.exp_IPI = exp_IPI;
    }

    /**
     * Informar apenas quando exporta para DNF.
     *
     * @param DNF_cod_especie
     */
    public void setDNF_cod_especie(String DNF_cod_especie) {
        this.DNF_cod_especie = DNF_cod_especie;
    }

    /**
     * Informar apenas quando exporta para DNF.
     *
     * @param DNF_unidade_medida_padrao
     */
    public void setDNF_unidade_medida_padrao(String DNF_unidade_medida_padrao) {
        this.DNF_unidade_medida_padrao = DNF_unidade_medida_padrao;
    }

    /**
     * Informar apenas quando exporta para DNF.
     *
     * @param DNF_fator_conv
     */
    public void setDNF_fator_conv(String DNF_fator_conv) {
        this.DNF_fator_conv = DNF_fator_conv;
    }

    /**
     * Informar apenas quando exporta para DNF. Preencher com código CFME Anexo
     * I ou II da DNF.
     *
     * @param DNF_cod_prod
     */
    public void setDNF_cod_prod(String DNF_cod_prod) {
        this.DNF_cod_prod = DNF_cod_prod;
    }

    /**
     * Informar apenas quando exporta para DNF.
     *
     * @param DNF_capacidade
     */
    public void setDNF_capacidade(String DNF_capacidade) {
        this.DNF_capacidade = DNF_capacidade;
    }

    /**
     * Informar apenas se a empresa for do SE e gerar o informativo DIC.
     *
     * @param sedic_cod_EAN
     */
    public void setSedic_cod_EAN(String sedic_cod_EAN) {
        this.sedic_cod_EAN = sedic_cod_EAN;
    }

    /**
     * Informar apenas se a empresa for do SE e gerar o informativo DIC.
     *
     * @param sedic_cod_prod_relev
     */
    public void setSedic_cod_prod_relev(String sedic_cod_prod_relev) {
        this.sedic_cod_prod_relev = sedic_cod_prod_relev;
    }

    /**
     * Informar: S=Sim ou N=Não
     *
     * @param scanc_gerar
     */
    public void setScanc_gerar(String scanc_gerar) {
        this.scanc_gerar = scanc_gerar;
    }

    /**
     * Informar apenas se gera para o SCANC
     *
     * @param scanc_doc_prod
     */
    public void setScanc_doc_prod(String scanc_doc_prod) {
        this.scanc_doc_prod = scanc_doc_prod;
    }

    /**
     * Informar apenas se gera para o SCANC. Informar: S=Sim ou N=Não.
     *
     * @param scanc_contem_gas
     */
    public void setScanc_contem_gas(String scanc_contem_gas) {
        this.scanc_contem_gas = scanc_contem_gas;
    }

    /**
     * Informar apenas se gera para o SCANC.
     *
     * @param scanc_tipo_produ
     */
    public void setScanc_tipo_produ(String scanc_tipo_produ) {
        this.scanc_tipo_produ = scanc_tipo_produ;
    }

    /**
     * Informar: S=Sim ou N=Não.
     *
     * @param grf_ctb_gerar
     */
    public void setGrf_ctb_gerar(String grf_ctb_gerar) {
        this.grf_ctb_gerar = grf_ctb_gerar;
    }

    /**
     * Informar apenas se gera para o GRF – CTB. Informar: S=Sim ou N=Não.
     *
     * @param grf_cod_prod
     */
    public void setGrf_cod_prod(String grf_cod_prod) {
        this.grf_cod_prod = grf_cod_prod;
    }

    /**
     * Informar apenas se gera para a DIEF. Informar: UN=Unidade, KG=Quilograma,
     * LT=Litro, MT=Metro Linear, M2=Metro quadrado, M3=Metro cúbico,
     * KW=Quilowatt hora ou PR=Par.
     *
     * @param dief_unidade
     */
    public void setDief_unidade(String dief_unidade) {
        this.dief_unidade = dief_unidade;
    }

    /**
     * Informar apenas se gera para a DIEF. Informar: 1=Mercadoria, 2= Serviço
     * com incidência de ICMS ou 3=Serviço sem incidência do ICMS.
     *
     * @param dief_tipo_prod
     */
    public void setDief_tipo_prod(String dief_tipo_prod) {
        this.dief_tipo_prod = dief_tipo_prod;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param sintegra_88st_gerar
     */
    public void setSintegra_88st_gerar(String sintegra_88st_gerar) {
        this.sintegra_88st_gerar = sintegra_88st_gerar;
    }

    /**
     * Informar apenas se gera para o 88STdo Sintegra.
     *
     * @param sintegra_88st_cod_prod
     */
    public void setSintegra_88st_cod_prod(String sintegra_88st_cod_prod) {
        this.sintegra_88st_cod_prod = sintegra_88st_cod_prod;
    }

    /**
     * Informar apenas se a empresa for de GO e gera informações complementares
     * do IPM da DPI.
     *
     * @param go_inf_compl
     */
    public void setGo_inf_compl(String go_inf_compl) {
        this.go_inf_compl = go_inf_compl;
    }

    /**
     * Informar apenas se a empresa for de GO e gera informações complementares
     * do IPM da DPI.
     *
     * @param go_cod_prod
     */
    public void setGo_cod_prod(String go_cod_prod) {
        this.go_cod_prod = go_cod_prod;
    }

    /**
     * Informar apenas se a empresa for de GO. Produto relacionado na Posição
     * 3301 a 3307 do Anexo VII do CTE. Informar: S=Sim ou N=Não.
     *
     * @param go_prod_rel
     */
    public void setGo_prod_rel(String go_prod_rel) {
        this.go_prod_rel = go_prod_rel;
    }

    /**
     * Informar apenas se a empresa for de AM. Informar: S=Sim ou N=Não.
     *
     * @param am_cesta_basica
     */
    public void setAm_cesta_basica(String am_cesta_basica) {
        this.am_cesta_basica = am_cesta_basica;
    }

    /**
     * Informar apenas se a empresa for de AM.
     *
     * @param am_cod_prod_DAM
     */
    public void setAm_cod_prod_DAM(String am_cod_prod_DAM) {
        this.am_cod_prod_DAM = am_cod_prod_DAM;
    }

    public void setRs_prod_ST(String rs_prod_ST) {
        this.rs_prod_ST = rs_prod_ST;
    }

    /**
     * Informar apenas se a empresa for do RS.
     *
     * @param rs_dt_ini_ST
     */
    public void setRs_dt_ini_ST(String rs_dt_ini_ST) {
        this.rs_dt_ini_ST = rs_dt_ini_ST;
    }

    /**
     * Informar apenas se a empresa for de RS. Informar: S=Sim ou N=Não.
     *
     * @param rs_preco_tabelado
     */
    public void setRs_preco_tabelado(String rs_preco_tabelado) {
        this.rs_preco_tabelado = rs_preco_tabelado;
    }

    /**
     * Informar apenas se a empresa for de RS.
     *
     * @param rs_valor_unit_ST
     */
    public void setRs_valor_unit_ST(String rs_valor_unit_ST) {
        this.rs_valor_unit_ST = rs_valor_unit_ST;
    }

    /**
     * Informar apenas se a empresa for de RS.
     *
     * @param rs_MVA_ST
     */
    public void setRs_MVA_ST(String rs_MVA_ST) {
        this.rs_MVA_ST = rs_MVA_ST;
    }

    /**
     * Informar apenas se a empresa for do RS. Informar: 001-Autopeças,
     * 002-Rações, 003-Colchões, 004-Cosméticos, 005-Arroz beneficiado,
     * 006-Rolamentos e Correias de Transmissão, 007- Tintas, 008-Sucos de
     * frutas e Outras Bebidas Não Alcoólicas, 009-Ferramentas, 010-Materiais
     * Elétricos, 011-Materiais de construção, acabamento, Bricolagem ou Adorno,
     * 012-Bicicletas, 013-Brinquedos, 014-Materiais de Limpeza, 015-Produtos
     * Alimentícios, 016-Artefatos de uso doméstico, 017-Bebidas Quentes,
     * 018-Artigos de papelaria, 019-Instrumentos musicais, 020-Prod.
     * Eletrônicos, Eletroeletrônicos e Eletrodomésticos, 021-PROT 160/09
     * Artefatos de uso Doméstico, 022-PROT 160/09 Prod. Eletrônicos,
     * Eletroeletrônicos e Eletrodomésticos, 023-PROT 163/09 Material de
     * Limpeza, 024-PROT 167/09 Produtos Alimentícios, 025-PROT 207/09 Artefatos
     * de uso Doméstico 026-PROT 208/09 Ferramentas, 027-PROT 210/09 Materiais
     * Elétricos, 028-PROT 211/09 Materiais de construção, acabamento,
     * Bricolagem ou Adorno, 029-PROT 212/09 Artigos de papelaria, 030-PROT
     * 213/09 Produtos Alimentícios, 031-PROT 058/10 Artigos de papelaria,
     * 032-PROT 116 e 148/10 Produtos Alimentícios, 033-PROT 117 e 143/10
     * Artigos de Papelaria, 034-PROT 119 e 147/10 Prod. Eletrônicos,
     * Eletroeletrônicos e Eletrodomésticos, 035-PROT 120 e 140/10 Materiais
     * Elétricos, 036- PROT 121 e 137/10 Bicicletas, 037-PROT 122 e 145/10
     * Brinquedos, 038-PROT 123 e 142/10 Material de Limpeza, 039-PROT 124 e
     * 163/10 Cosméticos, 040-PROT 125 e 139/10 Instrumentos Musicais, 041-PROT
     * 126 e 138/10 Ferramentas, 042-PROT 127 e 146/10 Artefatos de uso
     * domésticos, 043-PROT 136/10 Colchoaria, 044-PROT 141 e 152/10 Materiais
     * de construção, Acabamento, Bricolagem ou Adorno, 045-PROT 144/10 Bebidas
     * Quentes, 046-PROT 05/11 Autopeças, 047-PROT 195/09 Maq., AP. Mec., Elet.,
     * Eletroelet. e AutoM. 048-Artigos para Bebê, 049-Artigos de Vestuário.
     *
     * @param rs_grupo_ST
     */
    public void setRs_grupo_ST(String rs_grupo_ST) {
        this.rs_grupo_ST = rs_grupo_ST;
    }

    /**
     * Informar somente se a empresa for do PR. Informar: S=Sim ou N=Não.
     *
     * @param pr_equip_ecf
     */
    public void setPr_equip_ecf(String pr_equip_ecf) {
        this.pr_equip_ecf = pr_equip_ecf;
    }

    /**
     * Informar somente se a empresa for do MS. Informar: S=Sim ou N=Não.
     *
     * @param ms_incentivo_fiscal
     */
    public void setMs_incentivo_fiscal(String ms_incentivo_fiscal) {
        this.ms_incentivo_fiscal = ms_incentivo_fiscal;
    }

    /**
     * Informar somente se a empresa for do DF. Informar: 1=Sim ou 0=Não.
     *
     * @param df_regime_especial
     */
    public void setDf_regime_especial(String df_regime_especial) {
        this.df_regime_especial = df_regime_especial;
    }

    /**
     * Informar apenas se a empresa for do DF. Item padrão referente ao produto
     * sujeito ao Regime Especial de Apuração REA/ICMS (Decreto 29179/2008).
     *
     * @param df_item_padrao
     */
    public void setDf_item_padrao(String df_item_padrao) {
        this.df_item_padrao = df_item_padrao;
    }

    /**
     * Informar apenas se a empresa for do PE. Informar: 1=Mercadoria, 2=Matéria
     * Prima, 3=Produto Intermediário, 4=Materiais de embalagens, 5=Produtos
     * manufaturados, 6=Produtos em fabricação.
     *
     * @param pe_tipo_produto
     */
    public void setPe_tipo_produto(String pe_tipo_produto) {
        this.pe_tipo_produto = pe_tipo_produto;
    }

    /**
     * Informar apenas se a empresa for de SP. Informar: S=Sim ou N=Não.
     *
     * @param sp_controla_ressarcimento
     */
    public void setSp_controla_ressarcimento(String sp_controla_ressarcimento) {
        this.sp_controla_ressarcimento = sp_controla_ressarcimento;
    }

    /**
     * Informar apenas se a empresa for de SP.
     *
     * @param sp_data_saldo_inicial
     */
    public void setSp_data_saldo_inicial(String sp_data_saldo_inicial) {
        this.sp_data_saldo_inicial = sp_data_saldo_inicial;
    }

    /**
     * Informar apenas se a empresa for de SP.
     *
     * @param sp_valor_unitario
     */
    public void setSp_valor_unitario(String sp_valor_unitario) {
        this.sp_valor_unitario = sp_valor_unitario;
    }

    /**
     * Informar apenas se a empresa for de SP.
     *
     * @param sp_quantidade_controle
     */
    public void setSp_quantidade_controle(String sp_quantidade_controle) {
        this.sp_quantidade_controle = sp_quantidade_controle;
    }

    /**
     * Informar apenas se empresa for de SP.
     *
     * @param sp_valor_final
     */
    public void setSp_valor_final(String sp_valor_final) {
        this.sp_valor_final = sp_valor_final;
    }

    /**
     * Informar apenas se a empresa gera o SPED Fiscal. Preencher CFME tabela de
     * Gênero do SPED. Quando não houver a informação neste campo, será gerado
     * os dois primeiros dígitos do campo
     *
     * @param sped_genero
     */
    public void setSped_genero(String sped_genero) {
        this.sped_genero = sped_genero;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal e se o Gênero do produto
     * for 00-Serviço. Preencher CFME tabela de Gênero do SPED.
     *
     * @param sped_cod_servico
     */
    public void setSped_cod_servico(String sped_cod_servico) {
        this.sped_cod_servico = sped_cod_servico;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal. Informar: 0=Mercadoria,
     * 1=Matéria Prima, 2=Produto Intermediário, 3=Produto em Fabricação,
     * 4=Produto Acabado, 5=Embalagem, 6=Subproduto, 7=Material de Uso e
     * Consumo, 8=Ativo Imobilizado, 9=Serviços, 10=Outros Insumos ou 99=Outros.
     *
     * @param sped_tipo_item
     */
    public void setSped_tipo_item(String sped_tipo_item) {
        this.sped_tipo_item = sped_tipo_item;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal. Preencher CFME tabela de
     * classificação do SPED.
     *
     * @param sped_classificacao
     */
    public void setSped_classificacao(String sped_classificacao) {
        this.sped_classificacao = sped_classificacao;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal.
     *
     * @param sped_cta_estoque_proprio
     */
    public void setSped_cta_estoque_proprio(String sped_cta_estoque_proprio) {
        this.sped_cta_estoque_proprio = sped_cta_estoque_proprio;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal.
     *
     * @param sped_cta_estoque_em_terceiros
     */
    public void setSped_cta_estoque_em_terceiros(String sped_cta_estoque_em_terceiros) {
        this.sped_cta_estoque_em_terceiros = sped_cta_estoque_em_terceiros;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal.
     *
     * @param sped_cta_de_terceiros
     */
    public void setSped_cta_de_terceiros(String sped_cta_de_terceiros) {
        this.sped_cta_de_terceiros = sped_cta_de_terceiros;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal. Informar: 0=Receita
     * própria ou 1=Receita de terceiros.
     *
     * @param sped_receita
     */
    public void setSped_receita(String sped_receita) {
        this.sped_receita = sped_receita;
    }

    /**
     * Informar apenas se a empresa gera SPED Fiscal.
     *
     * @param sped_energia
     */
    public void setSped_energia(String sped_energia) {
        this.sped_energia = sped_energia;
    }

    /**
     * Informar a data do cadastro do produto.
     *
     * @param data_cadastro
     */
    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param escriturado_LMC
     */
    public void setEscriturado_LMC(String escriturado_LMC) {
        this.escriturado_LMC = escriturado_LMC;
    }

    public void setCod_comb_DF(String cod_comb_DF) {
        this.cod_comb_DF = cod_comb_DF;
    }

    public void setCod_comb_ANP(String cod_comb_ANP) {
        this.cod_comb_ANP = cod_comb_ANP;
    }

    /**
     * Informar: S= Sim ou N=Não
     *
     * @param produto_relacionado_MP540
     */
    public void setProduto_relacionado_MP540(String produto_relacionado_MP540) {
        this.produto_relacionado_MP540 = produto_relacionado_MP540;
    }

    public void setPermite_descr_compl(String permite_descr_compl) {
        this.permite_descr_compl = permite_descr_compl;
    }

    /**
     * Produto relacionado nos Arts. 7º e 8º da Lei 12.546/2011.
     *
     * @param cod_ativ_INSS
     */
    public void setCod_ativ_INSS(String cod_ativ_INSS) {
        this.cod_ativ_INSS = cod_ativ_INSS;
    }

    /**
     * Informar somente se a empresa gerar SPED PIS/COFINS e o produto possuir
     * Crédito por Alíquota Diferenciada, Débito por Alíquota Diferenciada,
     * Crédito por unidade de medida ou Débito por unidade de medida. Preencher
     * conforme tabela tipo de produto da DACON
     *
     * @param dacon_tipo_produto
     */
    public void setDacon_tipo_produto(String dacon_tipo_produto) {
        this.dacon_tipo_produto = dacon_tipo_produto;
    }

    /**
     * Informar somente se a empresa gerar SPED PIS/COFINS e o produto possuir
     * em alguma vigência a opção 06-Crédito presumido agroindustria. Informar :
     * "1"-Insumos de origem animal; "2"-Insumos de origem vegetal e "0"-sem
     * informação.
     *
     * @param dacon_credito_presumido
     */
    public void setDacon_credito_presumido(String dacon_credito_presumido) {
        this.dacon_credito_presumido = dacon_credito_presumido;
    }

    public void setDesconsiderar(String desconsiderar) {
        this.desconsiderar = desconsiderar;
    }

    /**
     * Informar apenas se a empresa gera o bloco K no SPED Fiscal e o "Tipo do
     * Item" for "Produto em processo" ou "Produto acabado".
     *
     * @param sped_cta_em_processo
     */
    public void setSped_cta_em_processo(String sped_cta_em_processo) {
        this.sped_cta_em_processo = sped_cta_em_processo;
    }

    /**
     * Informar apenas se a empresa gera o bloco K no SPED Fiscal e o "Tipo do
     * Item" for "Produto em processo" ou "Produto acabado".
     *
     * @param sped_cta_em_processo_hist
     */
    public void setSped_cta_em_processo_hist(String sped_cta_em_processo_hist) {
        this.sped_cta_em_processo_hist = sped_cta_em_processo_hist;
    }

    /**
     * Informar apenas se a empresa gera o bloco K no SPED Fiscal e o "Tipo do
     * Item" for "Produto em processo" ou "Produto acabado".
     *
     * @param sped_cta_em_processo_acabado
     */
    public void setSped_cta_em_processo_acabado(String sped_cta_em_processo_acabado) {
        this.sped_cta_em_processo_acabado = sped_cta_em_processo_acabado;
    }

    /**
     * Informar apenas se a empresa gera o bloco K no SPED Fiscal e o "Tipo do
     * Item" for "Produto em processo" ou "Produto acabado".
     *
     * @param sped_cta_em_processo_hist_acabado
     */
    public void setSped_cta_em_processo_hist_acabado(String sped_cta_em_processo_hist_acabado) {
        this.sped_cta_em_processo_hist_acabado = sped_cta_em_processo_hist_acabado;
    }

    /**
     * Informar o código CEST conforme tabela Código Especificador da
     * Substituição Tributária - CEST
     *
     * @param codigo_cest
     */
    public void setCodigo_cest(String codigo_cest) {
        this.codigo_cest = codigo_cest;
    }

    @Override
    public String getLayout() throws IOException {

        String linha = "|" + id + "|" + cod_produto + "|" + descricao_produto
                + "|" + cod_NBM + "|" + cod_NCM + "|" + cod_NCM_ex + "|" + cod_barras
                + "|" + cod_imp_impor + "|" + cod_grupo_produtos + "|" + unidade_medida
                + "|" + unidade_medida_invent + "|" + tipo_produto + "|" + tipo_arma_fogo
                + "|" + descricao_arma_fogo + "|" + tipo_medicamento + "|" + srv_trib_iss
                + "|" + cod_chassi + "|" + valor_unit + "|" + qtd_ini_estoque
                + "|" + vlr_ini_estoque + "|" + cod_sit_trib_icms + "|" + aliq_icms
                + "|" + aliq_ipi + "|" + periodo_ipi + "|" + observacao
                + "|" + exp_DNF + "|" + exp_IPI + "|" + DNF_cod_especie
                + "|" + DNF_unidade_medida_padrao + "|" + DNF_fator_conv
                + "|" + DNF_cod_prod + "|" + DNF_capacidade + "|" + sedic_cod_EAN
                + "|" + sedic_cod_prod_relev + "|" + scanc_gerar + "|" + scanc_doc_prod
                + "|" + scanc_contem_gas + "|" + scanc_tipo_produ + "|" + grf_ctb_gerar
                + "|" + grf_cod_prod + "|" + dief_unidade + "|" + dief_tipo_prod
                + "|" + sintegra_88st_gerar + "|" + sintegra_88st_cod_prod
                + "|" + go_inf_compl + "|" + go_cod_prod + "|" + go_prod_rel
                + "|" + am_cesta_basica + "|" + am_cod_prod_DAM + "|" + rs_prod_ST
                + "|" + rs_dt_ini_ST + "|" + rs_preco_tabelado + "|" + rs_valor_unit_ST
                + "|" + rs_MVA_ST + "|" + rs_grupo_ST + "|" + pr_equip_ecf
                + "|" + ms_incentivo_fiscal + "|" + df_regime_especial
                + "|" + df_item_padrao + "|" + pe_tipo_produto + "|" + sp_controla_ressarcimento
                + "|" + sp_data_saldo_inicial + "|" + sp_valor_unitario
                + "|" + sp_quantidade_controle + "|" + sp_valor_final + "|" + sped_genero
                + "|" + sped_cod_servico + "|" + sped_tipo_item + "|" + sped_classificacao
                + "|" + sped_cta_estoque_proprio + "|" + sped_cta_estoque_em_terceiros
                + "|" + sped_cta_de_terceiros + "|" + sped_receita + "|" + sped_energia
                + "|" + data_cadastro + "|" + escriturado_LMC + "|" + cod_comb_DF
                + "|" + cod_comb_ANP + "|" + produto_relacionado_MP540
                + "|" + permite_descr_compl + "|" + cod_ativ_INSS + "|" + dacon_tipo_produto
                + "|" + dacon_credito_presumido + "|" + desconsiderar
                + "|" + sped_cta_em_processo + "|" + sped_cta_em_processo_hist
                + "|" + sped_cta_em_processo_acabado + "|" + sped_cta_em_processo_hist_acabado
                + "|" + codigo_cest + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

    public static void main(String[] args) {
        Reg0100_Cadastro_Produtos i = new Reg0100_Cadastro_Produtos();

        try {
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
