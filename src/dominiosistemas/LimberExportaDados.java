/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static tools.CopyToClipboard.copyToClipboard;
import tools.SalvarArquivoTxt;
import tools.TelaDeAvisos;

/**
 *
 * @author luiz.barcellos
 */
public class LimberExportaDados {

    private ConexaoFB cnfb;
    private ConexaoFB cnfb2;
    private ConexaoMySQL cn;
    private String sql;
    private ArrayList layout = new ArrayList();
    private ArrayList grupos_produtos = new ArrayList();
    private ArrayList unidades_medida = new ArrayList();
    private ArrayList lancamentos = new ArrayList();
    private ArrayList lancamentos_saidas = new ArrayList();
    private final double TOLERANCE = 0.99;

    private final DecimalFormat df = new DecimalFormat("0.00");
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");

    private Reg0000_Ident_Empresa cliente;
    private Reg0100_Cadastro_Produtos produtos;
    private Reg0200_Contas_Contabeis pc;
    private Reg0160_Produtos_Grupos grupos;
    private Reg1000_Notas_Fiscais_Entrada notas;
    private Reg2000_Notas_Fiscais_Saidas notas_saidas;
    private Reg1030_Notas_Fiscais_Entrada_Estoque notas_produtos;
    private Reg1020_Notas_Fiscais_Entrada_Impostos notas_impostos;
    private Reg1500_Notas_Fiscais_Entrada_Parcelas gera_financeiro;
    private Reg5100_Recebimentos_Notas_Fiscais_Entrada baixa_entradas;
    private Reg5110_Recebimentos_Notas_Fiscais_Entrada_Lcto baixa_entradas_lctos;
    private Reg2020_Notas_Fiscais_Saida_Impostos notas_saidas_impostos;
    private Reg2030_Notas_Fiscais_Saida_Estoque notas_saidas_produtos;
    private Reg2500_Notas_Fiscais_Saidas_Parcelas gera_financeiro_saida;
    private Reg5200_Recebimentos_Notas_Fiscais_Saida baixas_saidas;
    private Reg5210_Recebimentos_Notas_Fiscais_Saida_Lcto baixas_saidas_lctos;
    private TelaDeAvisos avisos;
    private int qtdRegistros;
    private int qtdRegistroAtual;

    /**
     * Monta o layout Domínio Sistemas com separador.
     *
     * @param conn Conexão com o Banco MySQL
     * @param plano_contas Exportar o plano de contas
     * @param cli Exportar cadastro de clientes
     * @param fornec Exportar cadastro de fornecedores
     * @param prod Exportar cadastro de produtos
     * @param nf_ent Exportar notas fiscais de entrada (saída no Limber)
     * @param nf_sai Exportar notas fiscais de saída (entrada no Limber)
     * @param dti Data inicial
     * @param dtf Data final
     * @param pessoa Código da pessoa
     * @param caminho Local onde será gerado o arquivo TXT
     * @param origem_dados 1 - Cooper; 2 - TRR
     * @throws Exception
     */
    public LimberExportaDados(ConexaoMySQL conn, boolean plano_contas, boolean cli, boolean fornec,
            boolean prod, boolean nf_ent, boolean nf_sai, boolean nf_ant, String dti,
            String dtf, String pessoa, String caminho, int origem_dados) throws Exception {

        avisos = new TelaDeAvisos();

        cnfb = new ConexaoFB();
        cnfb2 = new ConexaoFB();
        this.cn = conn;

        switch (origem_dados) {
            case 2:
                ConexaoFB.arquivo = "fbtrr.properties";
        }

        cliente = new Reg0000_Ident_Empresa();

        sql = "";
        layout.clear();

        avisos.setMsgStatus("Conectando com o Banco de Dados...", 0, 0);
        //CONSULTAS SÃO SEPARADAS PARA QUE SEJA POSSÍVEL OPTAR SOBRE O QUE EXPORTAR
        if (cnfb.conecta() && cnfb2.conecta()) {
            try {

                if (plano_contas) {
                    avisos.setMsgStatus("Exportando Plano de Contas...", 0, 0);

                    throw new UnsupportedOperationException("Exportação de Plano de Contas não implementada.");
                    //pc = new Reg0200_Contas_Contabeis();

                    //layout.add(pc.getLayout());
                }

                //BUSCA DADOS PESSOA
                sql = "select a.CPF_CNPJ "
                        + "from CAD_TERCEIROS a "
                        + "where a.EMPRESA = 1 and a.CODIGO = '" + pessoa + "';";

                avisos.setMsgStatus("Exportando dados da pessoa...", 0, 0);

                cnfb.executeConsulta(sql);
                while (cnfb.rs.next()) {
                    cliente.setInscricao(cnfb.rs.getString("cpf_cnpj"));

                    layout.add(cliente.getLayout());

                }
                cnfb.rs.close();

                if (cli) {
                    avisos.setMsgStatus("Exportando dados dos clientes...", 0, 0);

                    throw new UnsupportedOperationException("Exportação de Clientes não disponível.");

                }
                if (fornec) {
                    avisos.setMsgStatus("Exportando dados dos fornecedores...", 0, 0);

                    throw new UnsupportedOperationException("Exportação de Fornecedores não disponível.");

                }
                if (prod) {
                    avisos.setMsgStatus("Exportando dados dos produtos...", 0, 0);

                    //DEFINE QUAIS PRODUTOS SERÃO IMPORTADOS
                    sql = "with produtos as ( "
                            + "select a.PRODUTO "
                            + "from com_entradas b "
                            + "left join COM_ENTRADAS_PRODUTOS a on (a.EMPRESA = b.EMPRESA and a.CODIGO = b.CODIGO) "
                            + "WHERE b.FORNECEDOR = '" + pessoa + "' and b.empresa = 1 "
                            + "and b.DT_ENTRADA Between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "' "
                            + "union all "
                            + "select aa.PRODUTO from ven_saidas bb "
                            + "left join VEN_SAIDAS_PRODUTOS aa on (aa.EMPRESA = bb.EMPRESA and aa.CODIGO = bb.CODIGO) "
                            + "WHERE bb.cliente = '" + pessoa + "' and bb.empresa = 1 "
                            + "and bb.DT_emissao Between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "' ) "
                            + "select z.CODIGO, "
                            + "         z.NOME, "
                            + "         case when z.NCM is null or z.NCM = '' then 99999999 else z.ncm end as ncm, "
                            + "         z.SETOR as grupo, "
                            + "         z2.nome as grupo_nome, "
                            + "         case  when z.NCM is null or z.NCM = '' or z.NCM = '99999999' then 'S' "
                            + "            else 'N' end as trib_iss,"
                            + "         'O' as tipo_produto,  "
                            + "         case  when z.NCM is null or z.NCM = '' or z.NCM = '99999999' then 99 "
                            + "         when left(z2.CLASSIFICACAO,2) = '01' or z.ncm = '44012100' then 4 "
                            + "         when left(z.ncm,2) = '12' then 1 "
                            + "         when left(z2.CLASSIFICACAO,2) = '02' then 2 "
                            + "         else 10 end as sped_tipo_item, "
                            + "        '" + dti.replace("/", ".") + "' as data_cadastro, "
                            + "         z.unidade, "
                            + "         z3.nome_reduzido as un_nome_reduzido "
                            + "from cad_produtos z "
                            + "left join CAD_SETORES z2 on (z2.EMPRESA = z.EMPRESA and z2.CODIGO = z.SETOR) "
                            + "inner join (select distinct produto from produtos) z1 on (z1.produto = z.codigo) "
                            + "left join cad_unidades z3 on (z3.empresa = z.empresa and z3.codigo = z.unidade);";

                    cnfb.executeConsulta(sql);

                    qtdRegistroAtual = 1;
                    cnfb.rs.last();
                    qtdRegistros = cnfb.rs.getRow();
                    cnfb.rs.beforeFirst();

                    while (cnfb.rs.next()) {

                        avisos.setProgresso(qtdRegistroAtual, qtdRegistros);

                        produtos = new Reg0100_Cadastro_Produtos();

                        if (origem_dados == 1) {
                            produtos.setCod_produto(cnfb.rs.getString("codigo"));
                            produtos.setCod_grupo_produtos(cnfb.rs.getString("grupo"));
                        } else {
                            produtos.setCod_grupo_produtos("99" + cnfb.rs.getString("grupo"));
                            produtos.setCod_produto("9999" + cnfb.rs.getString("codigo"));

                        }
                        produtos.setDescricao_produto(cnfb.rs.getString("nome"));
                        produtos.setCod_NCM(cnfb.rs.getString("ncm"));
                        produtos.setTipo_produto(cnfb.rs.getString("tipo_produto"));
                        produtos.setSped_tipo_item(cnfb.rs.getString("sped_tipo_item"));
                        produtos.setData_cadastro(cnfb.rs.getString("data_cadastro").replace(".", "/"));
                        produtos.setPr_equip_ecf("N");
                        produtos.setPeriodo_ipi("M");
                        produtos.setSrv_trib_iss(cnfb.rs.getString("trib_iss"));
                        produtos.setUnidade_medida(cnfb.rs.getString("un_nome_reduzido"));

                        layout.add(produtos.getLayout());

                        //PREPARA O LAYOUT DOS GRUPOS (SETORES)
                        if (!grupos_produtos.contains(cnfb.rs.getString("grupo"))) {
                            grupos_produtos.add(cnfb.rs.getString("grupo"));

                            grupos = new Reg0160_Produtos_Grupos();
                            if (origem_dados == 1) {
                                grupos.setCodigo(cnfb.rs.getString("grupo"));
                            } else {
                                grupos.setCodigo("99" + cnfb.rs.getString("grupo"));
                            }
                            grupos.setDescricao(cnfb.rs.getString("grupo_nome"));

                            layout.add(grupos.getLayout());

                        }

                        qtdRegistroAtual++;

                    }
                    cnfb.rs.close();

                    //SELECIONA OS GRUPOS A SEREM IMPORTADOS
                }

                //IMPORTA NOTAS FISCAIS DE ENTRADA
                if (nf_ent) {
                    if (nf_ant) {
                        buscaNotasAnteriores(pessoa, dti, dtf, true, false);
                    }
                    avisos.setMsgStatus("Exportando notas fiscais de compra...", 0, 0);
                    switch (origem_dados) {
                        default:
                            sql = "select a.codigo as lancamento, "
                                    + "a.cliente, "
                                    + "case when a.ESPECIE = 'NFE' then 36 "
                                    + "     when a.ESPECIE_NFS = 'NFSE' then 73 "
                                    + "else 0 end as cod_especie, "
                                    + "b.CNPJ as inscr_fornecedor, "
                                    + "b.INSC as inscr_est, "
                                    + "b.INSC_MUNICIPAL, "
                                    + " case (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) "
                                    + "     when '6231' then '29' "
                                    + "     when '2103' then '29' "
                                    + "     when '2801' then '106' "
                                    + "     when '2802' then '107' "
                                    + "     when '2701' then '109' "
                                    + "     when '2104' then '110' "
                                    + "     when '6712' then '112' "
                                    + "     when '2744' then '19' "
                                    + "     else (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cod_acum, "
                                    + "case (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) "
                                    + "     when '5101' then '1101' "
                                    + "     when '5102' then '1101' "
                                    + "      when '5117' then '1116' "
                                    + "      when '5910' then '1910' "
                                    + "      when '5949' then '1949' "
                                    + "      when '5922' then '1922' "
                                    + "     else (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cfop, "
                                    + "case when d.codigo is null then a.nro_nfs else d.codigo end as nro_nota, "
                                    + "'001' as SERIE, "
                                    + "a.DT_EMISSAO, "
                                    + "a.NF_DATA_SAIDA, "
                                    + "round((select sum(c.VLR_CONTABIL_ITEM) from VEN_SAIDAS_PRODUTOS c where c.EMPRESA = a.EMPRESA and c.CODIGO = a.CODIGO),2) as vlr_contabil, "
                                    + "round((select sum(c1.VLR_DESCONTO) from VEN_SAIDAS_PRODUTOS c1 where c1.EMPRESA = a.EMPRESA and c1.CODIGO = a.CODIGO),2) as vlr_desconto, "
                                    + "a.VLR_BASEICMS, "
                                    + "a.VLR_ICMS, "
                                    + "case when a.VLR_FRETE is null then 0.00 else a.VLR_FRETE end as vlr_frete, "
                                    + "case when a.VLR_SEGURO is null then 0.00 else a.VLR_SEGURO end as vlr_seguro, "
                                    + "a.AUTORIZADO, "
                                    + "a.OBSERVACAO, "
                                    + "case a.NFFRETE when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete, "
                                    + "'T' as emitente_nf, "
                                    + "case when d.NFE_ID is null then '' else d.NFE_ID end as chave, "
                                    + "e.PRODUTO, "
                                    + "e.QUANTIDADE, "
                                    + "e.VLR_CONTABIL_ITEM, "
                                    + "e.CFOP, "
                                    + "e.OBSERVACAO, "
                                    + "e.VLR_BASE_ICMS, "
                                    + "e.ALIQUOTA_ICMS, "
                                    + "e.VLR_UNITARIO, "
                                    + "e.VALOR_ICMS, "
                                    + "e.SITUACAO_TRIBUTARIA, "
                                    + "case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod, "
                                    + "e.LOTE,"
                                    + "case when o.COMPRA_FIXAR = 'S' "
                                    + "         or o.COMPLEMENTO_FIXAR = 'S' "
                                    + "         or o.VENDA_COMPRA_PRODUTOS = 'S' "
                                    + "         or o.VENDA_ENTREGA_FUTURA = 'S' "
                                    + "     then 1 else 0 "
                                    + "     end as gera_financeiro "
                                    + "from ven_saidas a "
                                    + "LEFT JOIN CAD_ESTABELECIMENTOS b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.ESTABELEC_CAD) "
                                    + "INNER JOIN VEN_NOTAFISCAL d on (d.EMPRESA = a.EMPRESA and d.CODIGO = a.NRO_NOTA and d.SERIE = a.SERIE and d.CLIENTE = a.CLIENTE) "
                                    + "LEFT JOIN VEN_SAIDAS_PRODUTOS e on (e.EMPRESA = a.EMPRESA and e.CODIGO = a.CODIGO) "
                                    + "left join CAD_PRODUTOS p on (p.EMPRESA = e.EMPRESA and p.CODIGO = e.PRODUTO) "
                                    + "left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.CODIGO) "
                                    + "LEFT JOIN CAD_OPERACOES_FISC o on (o.EMPRESA = e.EMPRESA and o.CODIGO = e.OPERACAO) "
                                    + "where a.EMPRESA = 1 and a.CLIENTE = '" + pessoa + "' "
                                    + "and a.DT_EMISSAO BETWEEN '" + dti.replace("/", ".") + "' AND '" + dtf.replace("/", ".") + "';";
                            break;
                        case 2:
                            sql = "select a.codigo as lancamento, "
                                    + "a.cliente, "
                                    + "case when a.ESPECIE = 'NFE' then 36 "
                                    + "     when a.ESPECIE_NFS = 'NFSE' then 73 "
                                    + "else 0 end as cod_especie, "
                                    + "b.CNPJ as inscr_fornecedor, "
                                    + "b.INSC as inscr_est, "
                                    + "b.INSC_MUNICIPAL, "
                                    + " case (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) "
                                    + "     when '41' then '54' "
                                    + "     when '47' then '56' "
                                    + "     else (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cod_acum, "
                                    + "case (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) "
                                    + "     when '5656' then '1653' "
                                    + "     when '5405' then '1407' "
                                    + "     else (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cfop, "
                                    + "case when d.codigo is null then a.nro_nfs else d.codigo end as nro_nota, "
                                    + "'001' as SERIE, "
                                    + "a.DT_EMISSAO, "
                                    + "case when a.NF_DATA_SAIDA is null then a.dt_emissao else a.NF_DATA_SAIDA end as nf_data_saida, "
                                    + "(select sum(c.VLR_CONTABIL_ITEM) from VEN_SAIDAS_PRODUTOS c where c.EMPRESA = a.EMPRESA and c.CODIGO = a.CODIGO) as vlr_contabil, "
                                    + "(select sum(c1.VLR_DESCONTO) from VEN_SAIDAS_PRODUTOS c1 where c1.EMPRESA = a.EMPRESA and c1.CODIGO = a.CODIGO) as vlr_desconto, "
                                    + "a.VLR_BASEICMS, "
                                    + "a.VLR_ICMS, "
                                    + "case when a.VLR_FRETE is null then 0.00 else a.VLR_FRETE end as vlr_frete, "
                                    + "case when a.VLR_SEGURO is null then 0.00 else a.VLR_SEGURO end as vlr_seguro, "
                                    + "a.AUTORIZADO, "
                                    + "a.OBSERVACAO, "
                                    + "case a.NFFRETE when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete, "
                                    + "'T' as emitente_nf, "
                                    + "case when d.NFE_ID is null then '' else d.NFE_ID end as chave, "
                                    + "'9999'||e.PRODUTO as produto, "
                                    + "e.QUANTIDADE, "
                                    + "e.VLR_CONTABIL_ITEM, "
                                    + "e.CFOP, "
                                    + "e.OBSERVACAO, "
                                    + "e.VLR_BASE_ICMS, "
                                    + "e.ALIQUOTA_ICMS, "
                                    + "e.VLR_UNITARIO, "
                                    + "e.VALOR_ICMS, "
                                    + "e.SITUACAO_TRIBUTARIA, "
                                    + "case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod, "
                                    + "e.LOTE,"
                                    + "case when o.COMPRA_FIXAR = 'S' "
                                    + "         or o.COMPLEMENTO_FIXAR = 'S' "
                                    + "         or o.VENDA_COMPRA_PRODUTOS = 'S' "
                                    + "         or o.VENDA_ENTREGA_FUTURA = 'S' "
                                    + "     then 1 else 0 "
                                    + "     end as gera_financeiro "
                                    + "from ven_saidas a "
                                    + "LEFT JOIN CAD_ESTABELECIMENTOS b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.ESTABELEC_CAD) "
                                    + "INNER JOIN VEN_NOTAFISCAL d on (d.EMPRESA = a.EMPRESA and d.CODIGO = a.NRO_NOTA and d.SERIE = a.SERIE and d.CLIENTE = a.CLIENTE) "
                                    + "LEFT JOIN VEN_SAIDAS_PRODUTOS e on (e.EMPRESA = a.EMPRESA and e.CODIGO = a.CODIGO) "
                                    + "left join CAD_PRODUTOS p on (p.EMPRESA = e.EMPRESA and p.CODIGO = e.PRODUTO) "
                                    + "left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.CODIGO) "
                                    + "LEFT JOIN CAD_OPERACOES_FISC o on (o.EMPRESA = e.EMPRESA and o.CODIGO = e.OPERACAO) "
                                    + "where a.EMPRESA = 1 and a.CLIENTE = '" + pessoa + "' "
                                    + "and a.DT_EMISSAO BETWEEN '" + dti.replace("/", ".") + "' AND '" + dtf.replace("/", ".") + "';";
                            break;

                    }
                    cnfb.executeConsulta(sql);

                    lancamentos.clear();

                    /**
                     * IMPORTAÇÃO DAS NOTAS FISCAIS. Valida se nota fiscal já
                     * foi importada. Próximo passo lista os produtos.
                     */
                    qtdRegistroAtual = 1;
                    cnfb.rs.last();
                    qtdRegistros = cnfb.rs.getRow();
                    cnfb.rs.beforeFirst();

                    //JOptionPane.showMessageDialog(null, "Registros: " + qtdRegistros);
                    while (cnfb.rs.next()) {

                        avisos.setProgresso(qtdRegistroAtual, qtdRegistros);

                        if (!lancamentos.contains(cnfb.rs.getString("lancamento"))) {
                            lancamentos.add(cnfb.rs.getString("lancamento"));

                            notas = new Reg1000_Notas_Fiscais_Entrada();

                            notas.setCod_especie(cnfb.rs.getString("cod_especie"));
                            notas.setInscricao_fornecedor(cnfb.rs.getString("inscr_fornecedor"));
                            notas.setInsc_est_fornecedor(cnfb.rs.getString("inscr_est"));
                            notas.setInsc_mun_fornecedor(cnfb.rs.getString("insc_municipal"));
                            notas.setCod_acum(cnfb.rs.getString("cod_acum"));
                            notas.setCfop(cnfb.rs.getString("cfop"));
                            notas.setNro_doc(cnfb.rs.getString("nro_nota"));
                            notas.setSerie(cnfb.rs.getString("serie"));
                            notas.setDt_emissao(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                            notas.setDt_entrada(dateIn.format(cnfb.rs.getDate("nf_data_saida")));
                            notas.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            notas.setVlr_produtos(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            notas.setVlr_frete(df.format(cnfb.rs.getDouble("vlr_frete")));
                            notas.setVlr_seguro(df.format(cnfb.rs.getDouble("vlr_seguro")));
                            notas.setObservacao(cnfb.rs.getString("observacao"));
                            notas.setMod_frete(cnfb.rs.getString("mod_frete"));
                            notas.setEmitente_nf(cnfb.rs.getString("emitente_nf"));
                            notas.setChave_nfe(cnfb.rs.getString("chave"));

                            layout.add(notas.getLayout());

                            /**
                             * Controla apenas o imposto ICMS nas notas de
                             * entrada.
                             */
                            notas_impostos = new Reg1020_Notas_Fiscais_Entrada_Impostos();

                            notas_impostos.setCod_imposto("1");
                            notas_impostos.setBc(df.format(cnfb.rs.getDouble("vlr_baseicms")));
                            notas_impostos.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            notas_impostos.setVlr_imposto(df.format(cnfb.rs.getDouble("vlr_icms")));

                            if (cnfb.rs.getString("situacao_tributaria").equals("040")) {
                                notas_impostos.setVlr_isentas(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            } else if (cnfb.rs.getString("situacao_tributaria").equals("051") | (cnfb.rs.getString("situacao_tributaria").equals("050"))) {
                                notas_impostos.setVlr_outras(df.format(cnfb.rs.getDouble("vlr_contabil")));

                            } else {
                                notas_impostos.setVlr_outras(df.format(cnfb.rs.getDouble("vlr_contabil") - cnfb.rs.getDouble("vlr_baseicms")));
                            }

                            layout.add(notas_impostos.getLayout());

                            /**
                             * CONSULTA AS PARCELAS DO FINANCEIRO AO LER A
                             * PRIMEIRA LINHA DA NOTA FISCAL.
                             */
                            if (cnfb.rs.getBoolean("gera_financeiro")) {
                                avisos.setMsgStatus("Exportando parcelas das notas fiscais de compra...", qtdRegistroAtual, qtdRegistros);

                                gera_financeiro = null;

                                sql = "SELECT b.CODIGO, "
                                        + "b.DOC, "
                                        + "b.DT_VENC, "
                                        + "sum(c.VALOR) as valor "
                                        + "FROM REL_VENDAS_CRECEBER a "
                                        + "LEFT JOIN FIN_CONTAS_RECEBER b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.CODIGO_RECEBER) "
                                        + "INNER JOIN FIN_CONTAS_RECEBER_DETALHE c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO and c.TIPO_PLANO = 'C') "
                                        + "where a.EMPRESA = 1 and a.CODIGO_VENDA = '" + cnfb.rs.getString("lancamento") + "' "
                                        + "and b.cliente = '" + pessoa + "' "
                                        + "GROUP BY 1,2,3;";

                                cnfb2.executeConsulta(sql);

                                //Double vlr_parcelas = 0.00;
                                while (cnfb2.rs.next()) {
                                    gera_financeiro = new Reg1500_Notas_Fiscais_Entrada_Parcelas();

                                    gera_financeiro.setNro_titulo(cnfb.rs.getString("nro_nota"));
                                    gera_financeiro.setValor(df.format(cnfb2.rs.getDouble("valor")));
                                    gera_financeiro.setVencimento(dateIn.format(cnfb2.rs.getDate("dt_venc")));

                                    //vlr_parcelas += cnfb2.rs.getDouble("valor");
                                    layout.add(gera_financeiro.getLayout());
                                }
                                cnfb2.rs.close();

                                if (gera_financeiro == null) {

                                    gera_financeiro = new Reg1500_Notas_Fiscais_Entrada_Parcelas();

                                    gera_financeiro.setNro_titulo(cnfb.rs.getString("nro_nota"));
                                    gera_financeiro.setValor(df.format(cnfb.rs.getDouble("vlr_contabil")));
                                    gera_financeiro.setVencimento(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                                    layout.add(gera_financeiro.getLayout());

                                }
                            }
                        }

                        avisos.setMsgStatus("Exportando produtos das notas fiscais de compra...", qtdRegistroAtual, qtdRegistros);
                        notas_produtos = new Reg1030_Notas_Fiscais_Entrada_Estoque();

                        notas_produtos.setTp_lancamento("1");
                        notas_produtos.setCod_produto(cnfb.rs.getString("produto"));
                        notas_produtos.setQuantidade(df.format(cnfb.rs.getDouble("quantidade")));
                        notas_produtos.setVlr_cont(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                        notas_produtos.setCfop(cnfb.rs.getString("cfop"));
                        notas_produtos.setBc(df.format(cnfb.rs.getDouble("vlr_base_icms")));
                        notas_produtos.setAliq_icms(df.format(cnfb.rs.getDouble("aliquota_icms")));
                        notas_produtos.setVlr_unit(df.format(cnfb.rs.getDouble("vlr_unitario")));
                        notas_produtos.setMovim_fis_prod(cnfb.rs.getString("movim_fis_prod"));
                        notas_produtos.setVlt_total(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                        notas_produtos.setCus_total_produto(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                        notas_produtos.setVlr_bruto_prod(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                        notas_produtos.setVlr_icms(df.format(cnfb.rs.getDouble("valor_icms")));
                        notas_produtos.setCod_st(cnfb.rs.getString("situacao_tributaria"));

                        layout.add(notas_produtos.getLayout());

                        qtdRegistroAtual++;
                    }
                    cnfb.rs.close();
                    // FIM DA IMPORTAÇÃO DE NOTAS FISCAIS

                    //IMPORTAÇÃO DE BAIXAS DE PARCELAS
                    avisos.setMsgStatus("Exportando baixas das notas fiscais de compra...", 0, 0);
                    sql = "with baixas as ( "
                            + "SELECT  case when e.nro_nota is null then e.NRO_NFS else e.NRO_NOTA end as nro_nota, "
                            + "t.CNPJ, c.DT_VENC, a.DATA, b.VALOR, p.nome, "
                            + "left(p.classificacao,1) as class, b.tipo_plano, "
                            + "case when e.ESPECIE is null then e.ESPECIE_NFS else e.ESPECIE end as especie "
                            + "FROM FIN_CONTAS_RECEBER_BAIXAS a "
                            + "LEFT JOIN FIN_CONTAS_RECEBER_BAIXAS_DET b on (b.EMPRESA = a.EMPRESA and b.CODIGO_BAIXA = a.CODIGO) "
                            + "LEFT JOIN FIN_CONTAS_RECEBER c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO_RECEBER) "
                            + "INNER JOIN REL_VENDAS_CRECEBER d on (d.EMPRESA = c.EMPRESA and d.CODIGO_RECEBER = c.CODIGO) "
                            + "LEFT JOIN VEN_SAIDAS e on (e.EMPRESA = d.EMPRESA and e.CODIGO = d.CODIGO_VENDA) "
                            + "LEFT JOIN CAD_ESTABELECIMENTOS t on (t.EMPRESA = c.EMPRESA and t.CODIGO = c.ESTABELEC) "
                            + "LEFT JOIN CAD_PLANOCONTAS p on (p.EMPRESA = b.EMPRESA and p.CODIGO = b.PLANOCONTA) "
                            + "WHERE a.empresa = '1' and c.cliente = '" + pessoa + "' and a.DATA between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "' "
                            + "union all "
                            + "select distinct case when a1.nro_nota is null then a1.NRO_NFS else a1.NRO_NOTA end as nro_nota, "
                            + "c1.CNPJ, "
                            + "a1.DT_EMISSAO as dt_venc, "
                            + "a1.DT_EMISSAO as data, "
                            + "sum(b1.VLR_CONTABIL_ITEM) as valor, '' as nome, "
                            + "1 as class, "
                            + "'D' as tipo_plano, "
                            + "case when a1.ESPECIE is null then a1.ESPECIE_NFS else a1.ESPECIE end as especie "
                            + "from ven_saidas a1 "
                            + "left join VEN_SAIDAS_PRODUTOS b1 on (b1.EMPRESA = a1.EMPRESA and b1.CODIGO = a1.CODIGO) "
                            + "left join CAD_ESTABELECIMENTOS c1 on (c1.EMPRESA = a1.EMPRESA and c1.CODIGO = a1.ESTABELEC) "
                            + "inner join CAD_OPERACOES_FISC o on (o.EMPRESA = b1.EMPRESA and o.CODIGO = b1.OPERACAO and "
                            + "     (o.COMPRA_FIXAR = 'S' or o.COMPLEMENTO_FIXAR = 'S' or o.VENDA_COMPRA_PRODUTOS = 'S' "
                            + "     or o.VENDA_ENTREGA_FUTURA = 'S' or o.COMPLEM_VALORES = 'S')) "
                            + "where a1.EMPRESA = 1 and a1.CLIENTE = '" + pessoa + "' and a1.DT_EMISSAO between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "' "
                            + "and not exists (select * from REL_VENDAS_CRECEBER x where x.EMPRESA = a1.EMPRESA and x.CODIGO_VENDA = a1.CODIGO) "
                            + "group by 1,2,3,4,9 ), "
                            + "lancamentos as ( select z.nro_nota, z.especie, "
                            + "z.cnpj, z.dt_venc, z.data,"
                            + "case when z.class = 3 and z.tipo_plano = 'D' then z.valor else 0.00 end as desconto, "
                            + "case when z.class = 3 and z.tipo_plano = 'C' then z.valor else 0.00 end as juros, "
                            + "case when z.class = 1 or z.class = 2 and z.tipo_plano = 'D' then z.valor else 0.00 end as valor "
                            + "from baixas z ) "
                            + "select x.nro_nota, "
                            + "case x.especie when 'NFE' then 36 "
                            + "when 'NFSE' then 73 "
                            + "else 99 end as especie, "
                            + "x.cnpj, x.dt_venc, x.data, sum(desconto) as desconto, "
                            + "sum(juros) as juros, sum(valor) as valor "
                            + "from lancamentos x "
                            + "group by 1,2,3,4,5 having sum(valor) > 0.00;";

                    cnfb.executeConsulta(sql);

                    qtdRegistroAtual = 1;
                    cnfb.rs.last();
                    qtdRegistros = cnfb.rs.getRow();
                    cnfb.rs.beforeFirst();

                    while (cnfb.rs.next()) {

                        avisos.setProgresso(qtdRegistroAtual, qtdRegistros);

                        baixa_entradas = new Reg5100_Recebimentos_Notas_Fiscais_Entrada();
                        baixa_entradas_lctos = new Reg5110_Recebimentos_Notas_Fiscais_Entrada_Lcto();

                        baixa_entradas.setCnpj_fornecedor(cnfb.rs.getString("cnpj"));
                        baixa_entradas.setCod_especie(cnfb.rs.getString("especie"));
                        baixa_entradas.setDt_pgto(dateIn.format(cnfb.rs.getDate("data")));
                        baixa_entradas.setDt_venc_parcela(dateIn.format(cnfb.rs.getDate("dt_venc")));
                        baixa_entradas.setNro_doc(cnfb.rs.getString("nro_nota"));
                        baixa_entradas.setSerie("1");
                        baixa_entradas.setSub_serie("0");
                        baixa_entradas.setSegmento("0");
                        baixa_entradas.setVlr_desconto(df.format(cnfb.rs.getDouble("desconto")));
                        baixa_entradas.setVlr_juros(df.format(cnfb.rs.getDouble("juros")));
                        baixa_entradas.setVlr_recebido_total(df.format(cnfb.rs.getDouble("valor")));
                        baixa_entradas.setVlr_cofins("0,00");
                        baixa_entradas.setVlr_crf("0,00");
                        baixa_entradas.setVlr_csoc("0,00");
                        baixa_entradas.setVlr_irrf("0,00");
                        baixa_entradas.setVlr_irrfp("0,00");
                        baixa_entradas.setVlr_multa("0,00");
                        baixa_entradas.setVlr_outras("0,00");
                        baixa_entradas.setVlr_pis("0,00");
                        baixa_entradas.setVlr_devolucao("0,00");

                        layout.add(baixa_entradas.getLayout());

                        baixa_entradas_lctos.setCod_hist("15");
                        baixa_entradas_lctos.setCta_cred("5");
                        baixa_entradas_lctos.setCta_deb("444");
                        baixa_entradas_lctos.setDt_lanc(dateIn.format(cnfb.rs.getDate("data")));
                        //baixa_entradas_lctos.setFilial("45");
                        baixa_entradas_lctos.setHistorico("Histórico Teste");
                        baixa_entradas_lctos.setValor(df.format(cnfb.rs.getDouble("valor")));

                        layout.add(baixa_entradas_lctos.getLayout());

                        if (cnfb.rs.getDouble("desconto") > 0.00) {
                            baixa_entradas_lctos.setCta_deb("445");
                            baixa_entradas_lctos.setValor(df.format(cnfb.rs.getDouble("desconto")));

                            layout.add(baixa_entradas_lctos.getLayout());
                        }

                        if (cnfb.rs.getDouble("juros") > 0.00) {
                            baixa_entradas_lctos.setCta_deb("446");
                            baixa_entradas_lctos.setCta_cred("444");
                            baixa_entradas_lctos.setValor(df.format(cnfb.rs.getDouble("juros")));

                            layout.add(baixa_entradas_lctos.getLayout());
                        }

                        qtdRegistroAtual++;

                    }
                    cnfb.rs.close();

                }

                //IMPORTA NOTAS FISCAIS DE SAIDA
                if (nf_sai) {

                    if (nf_ant) {
                        buscaNotasAnteriores(pessoa, dti, dtf, false, true);
                    }

                    avisos.setMsgStatus("Exportando notas fiscais de venda...", qtdRegistroAtual, qtdRegistros);
                    sql = "select a.codigo as lancamento, "
                            + "case a.especie when 'NFE' then 36 else 99 end as especie, "
                            + "b.cnpj as inscricao_cliente, b.insc_municipal, "
                            + "b.insc, "
                            + "case (select first 1 a1.operacao from com_entradas_produtos a1 where a1.empresa = a.empresa and a1.codigo = a.codigo) "
                            + " when '1602' then '75' "
                            + " when '1701' then '108' "
                            + " when '1105' then '113' "
                            + " when '6212' then '111' "
                            + " when '1109' then '113' "
                            + " when '6216' then '114' "
                            + " when '6313' then '115' "
                            + " else (select first 1 a1.operacao from com_entradas_produtos a1 where a1.empresa = a.empresa and a1.codigo = a.codigo) end as cod_acumulador, "
                            + "case (select first 1 a2.cfop from com_entradas_produtos a2 where a2.empresa = a.empresa and a2.codigo = a.codigo) "
                            + " when '1202' then '5201' "
                            + " when '1949' then '5949' "
                            + " when '1102' then '5101' "
                            + " when '1101' then '5101' "
                            + " when '1556' then '5101' "
                            + " else (select first 1 a2.cfop from com_entradas_produtos a2 where a2.empresa = a.empresa and a2.codigo = a.codigo) end as cfop, "
                            + "m.uf as sigla_uf_cliente, "
                            + "a.numero as nro_doc, a.serie, "
                            + "a.dt_entrada as dt_saida, "
                            + "a.dt_emissao, "
                            + "(select sum(a3.vlr_contabil_item) from com_entradas_produtos a3 where a3.empresa = a.empresa and a3.codigo = a.codigo) as vlr_contabil, "
                            + "a.observacao, a.nfe_chave_acesso, "
                            + "a.total_produtos, "
                            + "case a.cif_fob when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete, "
                            + "(case when a.vlr_funrural is null then 0.00 else a.VLR_FUNRURAL end + case when a.VLR_SENAR is null then 0.00 else a.VLR_SENAR end) as funrural, "
                            + "a.TOTAL_BASE_ICMS, "
                            + "a.TOTAL_VALOR_ICMS,"
                            + "o.FUNRURAL as desconta_funrural, "
                            + "p.produto as cod_produto, "
                            + "p.QUANTIDADE, "
                            + "p.VLR_CONTABIL_ITEM, "
                            + "p.VLR_BASE_ICMS, "
                            + "p.ALIQ_ICMS,"
                            + "p.UNITARIO,"
                            + "p.SITUACAO_TRIBUTARIA,"
                            + "case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod,"
                            + "case when o.COMPRA_FIXAR = 'S' or o.COMPLEMENTO_FIXAR = 'S' or o.VENDA_COMPRA_PRODUTOS = 'S' or o.VENDA_ENTREGA_FUTURA = 'S' or o.COMPLEM_VALORES = 'S' then 1 else 0 end as gera_financeiro, "
                            + "(case when a.VLR_SENAR is null then 0.00 else 0.20 end + case when a.VLR_FUNRURAL is null then 0.00 else 2.10 end) as aliq_funrural "
                            + "from com_entradas a "
                            + "left join cad_estabelecimentos b on (b.empresa = a.empresa and b.codigo = a.estabelec) "
                            + "left join cad_municipios m on (m.codigo = b.municipio) "
                            + "left join COM_ENTRADAS_PRODUTOS p on (p.EMPRESA = a.EMPRESA and p.CODIGO = a.CODIGO) "
                            + "left join CAD_OPERACOES_FISC o on (o.EMPRESA = p.EMPRESA and o.CODIGO = p.OPERACAO) "
                            + "left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.PRODUTO) "
                            + "where a.empresa = 1 and a.fornecedor = '" + pessoa + "' "
                            + "and a.dt_entrada between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "';";
                    cnfb.executeConsulta(sql);
                    lancamentos_saidas.clear();

                    qtdRegistroAtual = 1;
                    cnfb.rs.last();
                    qtdRegistros = cnfb.rs.getRow();
                    cnfb.rs.beforeFirst();

                    while (cnfb.rs.next()) {
                        avisos.setProgresso(qtdRegistroAtual, qtdRegistros);
                        notas_saidas = new Reg2000_Notas_Fiscais_Saidas();

                        if (!lancamentos_saidas.contains(cnfb.rs.getString("lancamento"))) {
                            lancamentos_saidas.add(cnfb.rs.getString("lancamento"));

                            notas_saidas.setCod_especie(cnfb.rs.getString("especie"));
                            notas_saidas.setInscricao_cliente(cnfb.rs.getString("inscricao_cliente"));
                            notas_saidas.setCod_acumulador(cnfb.rs.getString("cod_acumulador"));
                            notas_saidas.setCfop(cnfb.rs.getString("cfop"));
                            notas_saidas.setSigla_uf_cliente(cnfb.rs.getString("sigla_uf_cliente"));
                            notas_saidas.setNro_doc(cnfb.rs.getString("nro_doc"));
                            notas_saidas.setSerie("1");
                            notas_saidas.setDt_saida(dateIn.format(cnfb.rs.getDate("dt_saida")));
                            notas_saidas.setDt_emissao(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                            notas_saidas.setObservacao(cnfb.rs.getString("observacao"));
                            notas_saidas.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            notas_saidas.setMod_frete(cnfb.rs.getString("mod_frete"));
                            notas_saidas.setVlr_produtos(df.format(cnfb.rs.getDouble("total_produtos")));
                            notas_saidas.setChave_NFe(cnfb.rs.getString("nfe_chave_acesso"));
                            notas_saidas.setInscr_est_cli(cnfb.rs.getString("insc"));
                            notas_saidas.setInscr_mun_cli(cnfb.rs.getString("insc_municipal"));
                            notas_saidas.setTp_titulo("5");
                            notas_saidas.setEmitente("T");
                            notas_saidas.setCod_mod_doc_fis("9");

                            layout.add(notas_saidas.getLayout());

                            /**
                             * Exportação de dados de ICMS
                             *
                             */
                            notas_saidas_impostos = new Reg2020_Notas_Fiscais_Saida_Impostos();

                            notas_saidas_impostos.setCod_imp("1");
                            notas_saidas_impostos.setVlr_contabil(df.format(cnfb.rs.getDouble("total_produtos")));
                            notas_saidas_impostos.setBc(df.format(cnfb.rs.getDouble("total_base_icms")));
                            notas_saidas_impostos.setVlr_imp(df.format(cnfb.rs.getDouble("total_valor_icms")));
                            notas_saidas_impostos.setVlr_outras(df.format(cnfb.rs.getDouble("vlr_contabil") - cnfb.rs.getDouble("total_base_icms")));

                            layout.add(notas_saidas_impostos.getLayout());

                            /**
                             * Exportação de dados do FUNRURAL
                             *
                             */
                            if (cnfb.rs.getString("desconta_funrural").equals("S")) {
                                notas_saidas_impostos = new Reg2020_Notas_Fiscais_Saida_Impostos();

                                notas_saidas_impostos.setCod_imp("28");
                                notas_saidas_impostos.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                                notas_saidas_impostos.setBc(df.format(cnfb.rs.getDouble("total_produtos")));
                                notas_saidas_impostos.setVlr_imp(df.format(cnfb.rs.getDouble("funrural")));
                                notas_saidas_impostos.setAliq(df.format(cnfb.rs.getDouble("aliq_funrural")));

                                layout.add(notas_saidas_impostos.getLayout());

                            }
                            /**
                             * Exportação das parcelas das notas de saída.
                             */
                            if (cnfb.rs.getBoolean("gera_financeiro")) {
                                avisos.setMsgStatus("Exportando parcelas das notas fiscais de venda...", qtdRegistroAtual, qtdRegistros);
                                sql = "SELECT b.CODIGO, b.DOC, b.DT_VENC, sum(c.VALOR) as valor "
                                        + "FROM REL_COMPRAS_CPAGAR a "
                                        + "left join FIN_CONTAS_PAGAR b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.CODIGO_PAGAR) "
                                        + "inner join FIN_CONTAS_PAGAR_DETALHE c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO and c.TIPO_PLANO = 'D') "
                                        + "where a.EMPRESA = '1' and a.CODIGO_ENTRADA = '" + cnfb.rs.getString("lancamento") + "' "
                                        + "and b.FORNECEDOR = '" + pessoa + "' "
                                        + "GROUP BY 1,2,3;";

                                cnfb2.executeConsulta(sql);

                                while (cnfb2.rs.next()) {
                                    gera_financeiro_saida = new Reg2500_Notas_Fiscais_Saidas_Parcelas();

                                    //gera_financeiro_saida.setNro_titulo(cnfb.rs.getString("nro_doc"));
                                    gera_financeiro_saida.setValor(df.format(cnfb2.rs.getDouble("valor") - cnfb.rs.getDouble("funrural")));
                                    gera_financeiro_saida.setVencimento(dateIn.format(cnfb2.rs.getDate("dt_venc")));
                                    gera_financeiro_saida.setVlr_funrural(df.format(cnfb.rs.getDouble("funrural")));
                                }
                                cnfb2.rs.close();

                                if (gera_financeiro_saida == null) {

                                    gera_financeiro_saida = new Reg2500_Notas_Fiscais_Saidas_Parcelas();

                                    //gera_financeiro_saida.setNro_titulo(cnfb.rs.getString("doc"));
                                    gera_financeiro_saida.setValor(df.format(cnfb.rs.getDouble("vlr_contabil") - cnfb.rs.getDouble("funrural")));
                                    gera_financeiro_saida.setVencimento(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                                    gera_financeiro_saida.setVlr_funrural(df.format(cnfb.rs.getDouble("funrural")));

                                }
                                layout.add(gera_financeiro_saida.getLayout());
                            }
                        }

                        /**
                         * Exportação de produtos.
                         */
                        avisos.setMsgStatus("Exportando produtos das notas fiscais de venda...", qtdRegistroAtual, qtdRegistros);
                        notas_saidas_produtos = new Reg2030_Notas_Fiscais_Saida_Estoque();

                        notas_saidas_produtos.setCod_produto(cnfb.rs.getString("cod_produto"));
                        notas_saidas_produtos.setQtd(df.format(cnfb.rs.getDouble("quantidade")));
                        notas_saidas_produtos.setVlr_bruto_prod(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                        notas_saidas_produtos.setBc_icms(df.format(cnfb.rs.getDouble("vlr_base_icms")));
                        notas_saidas_produtos.setAliq_icms(df.format(cnfb.rs.getDouble("aliq_icms")));
                        notas_saidas_produtos.setQuantidade(df.format(cnfb.rs.getDouble("quantidade")));
                        notas_saidas_produtos.setVlr_unit(df.format(cnfb.rs.getDouble("unitario")));
                        notas_saidas_produtos.setTp_lancamento("1");
                        notas_saidas_produtos.setCod_st(cnfb.rs.getString("situacao_tributaria"));
                        notas_saidas_produtos.setMovim_fis_prod(cnfb.rs.getString("movim_fis_prod"));

                        layout.add(notas_saidas_produtos.getLayout());

                        qtdRegistroAtual++;
                    }
                    cnfb.rs.close();

                    avisos.setMsgStatus("Exportando baixas de parcelas das notas fiscais de venda...", qtdRegistroAtual, qtdRegistros);
                    sql = "with baixas as ( "
                            + "SELECT  e.NUMERO AS NRO_NOTA, t.CNPJ, c.DT_VENC, a.DATA, b.VALOR, p.nome, "
                            + "left(p.classificacao,1) as class, b.tipo_plano, e.especie "
                            + "FROM FIN_CONTAS_PAGAR_BAIXAS a "
                            + "LEFT JOIN FIN_CONTAS_PAGAR_BAIXAS_DETALHE b on (b.EMPRESA = a.EMPRESA and b.CODIGO_BAIXA = a.CODIGO) "
                            + "LEFT JOIN FIN_CONTAS_PAGAR c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO_PAGAR) "
                            + "INNER JOIN REL_COMPRAS_CPAGAR d on (d.EMPRESA = c.EMPRESA and d.CODIGO_PAGAR = c.CODIGO) "
                            + "LEFT JOIN COM_ENTRADAS e on (e.EMPRESA = d.EMPRESA and e.CODIGO = d.CODIGO_ENTRADA) "
                            + "LEFT JOIN CAD_ESTABELECIMENTOS t on (t.EMPRESA = c.EMPRESA and t.CODIGO = c.ESTABELEC) "
                            + "LEFT JOIN CAD_PLANOCONTAS p on (p.EMPRESA = b.EMPRESA and p.CODIGO = b.PLANOCONTA) "
                            + "WHERE a.empresa = '1' and c.FORNECEDOR = '" + pessoa + "' and a.DATA between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "' ), "
                            + "lancamentos as ( "
                            + "select z.nro_nota, "
                            + "z.especie, "
                            + "z.cnpj, "
                            + "z.dt_venc, "
                            + "z.data, "
                            + "case when z.class = 3 and z.tipo_plano = 'C' then z.valor else 0.00 end as desconto, "
                            + "case when z.class = 3 and z.tipo_plano = 'D' then z.valor else 0.00 end as juros, "
                            + "case when z.class = 1 and z.tipo_plano = 'C' then z.valor else 0.00 end as valor "
                            + "from baixas z ) "
                            + "select x.nro_nota, "
                            + "case x.especie when 'NFE' then 36 else 99 end as especie, "
                            + "x.cnpj, "
                            + "x.dt_venc, "
                            + "x.data, "
                            + "sum(desconto) as desconto, "
                            + "sum(juros) as juros, "
                            + "sum(valor) as valor "
                            + "from lancamentos x "
                            + "group by 1,2,3,4,5 having sum(valor) > 0.00 ;";

                    cnfb.executeConsulta(sql);

                    qtdRegistroAtual = 1;
                    cnfb.rs.last();
                    qtdRegistros = cnfb.rs.getRow();
                    cnfb.rs.beforeFirst();

                    while (cnfb.rs.next()) {
                        avisos.setProgresso(qtdRegistroAtual, qtdRegistros);

                        baixas_saidas = new Reg5200_Recebimentos_Notas_Fiscais_Saida();
                        baixas_saidas_lctos = new Reg5210_Recebimentos_Notas_Fiscais_Saida_Lcto();

                        baixas_saidas.setCod_especie(cnfb.rs.getString("especie"));
                        baixas_saidas.setDt_pgto(dateIn.format(cnfb.rs.getDate("data")));
                        baixas_saidas.setDt_venc_parcela(dateIn.format(cnfb.rs.getDate("dt_venc")));
                        baixas_saidas.setNro_doc(cnfb.rs.getString("nro_nota"));
                        baixas_saidas.setSerie("1");
                        baixas_saidas.setSub_serie("0");
                        baixas_saidas.setSegmento("0");
                        baixas_saidas.setVlr_desconto(df.format(cnfb.rs.getDouble("desconto")));
                        baixas_saidas.setVlr_juros(df.format(cnfb.rs.getDouble("juros")));
                        baixas_saidas.setVlr_recebido_total(df.format(cnfb.rs.getDouble("valor")));
                        baixas_saidas.setVlr_cofins("0,00");
                        baixas_saidas.setVlr_csoc("0,00");
                        baixas_saidas.setVlr_irrf("0,00");
                        baixas_saidas.setVlr_irrfp("0,00");
                        baixas_saidas.setVlr_multa("0,00");
                        baixas_saidas.setVlr_outras("0,00");
                        baixas_saidas.setVlr_pis("0,00");
                        baixas_saidas.setVlr_devolucao("0,00");

                        layout.add(baixas_saidas.getLayout());

                        baixas_saidas_lctos.setCod_hist("24");
                        baixas_saidas_lctos.setCta_cred("444");
                        baixas_saidas_lctos.setCta_deb("5");
                        baixas_saidas_lctos.setDt_lanc(dateIn.format(cnfb.rs.getDate("data")));
                        baixas_saidas_lctos.setFilial("45");
                        baixas_saidas_lctos.setHistorico("Histórico Teste");
                        baixas_saidas_lctos.setValor(df.format(cnfb.rs.getDouble("valor")));

                        layout.add(baixas_saidas_lctos.getLayout());

                        if (cnfb.rs.getDouble("desconto") > 0.00) {
                            baixas_saidas_lctos.setCta_deb("445");
                            baixas_saidas_lctos.setCta_cred("444");
                            baixas_saidas_lctos.setValor(df.format(cnfb.rs.getDouble("desconto")));

                            layout.add(baixas_saidas_lctos.getLayout());
                        }

                        if (cnfb.rs.getDouble("juros") > 0.00) {
                            baixas_saidas_lctos.setCta_deb("444");
                            baixas_saidas_lctos.setCta_cred("446");
                            baixas_saidas_lctos.setValor(df.format(cnfb.rs.getDouble("juros")));

                            layout.add(baixas_saidas_lctos.getLayout());
                        }
                        qtdRegistroAtual++;
                    }
                    cnfb.rs.close();

                }

                if (caminho != null
                        | !caminho.equals("")) {
                    avisos.setMsgStatus("Gerando arquivo para importação.", 0, 0);

                    String conteudo = "";

                    qtdRegistros = getLayout().size();
                    qtdRegistroAtual = 1;

                    for (int i = 0; i < getLayout().size(); i++) {
                        avisos.setProgresso(i, qtdRegistros);
                        conteudo += getLayout().get(i) + "\n";
                        qtdRegistroAtual++;
                    }

                    avisos.setMsgStatus("Salvando arquivo.", 0, 0);
                    new SalvarArquivoTxt(conteudo.replace("=", "").replace("%", ""), caminho, "MOVIMENTACAO_PRODUTOR_" + cliente.inscricao);
                }

            } catch (SQLException e) {
                throw new SQLException(e);
            } finally {
                cnfb.desconecta();
                cnfb2.desconecta();
                avisos.dispose();
            }

        } else {
            avisos.dispose();
        }
    }

    private void buscaNotasAnteriores(String pessoa, String dti, String dtf, boolean entradas, boolean saidas) throws Exception {

        if (entradas) {

            avisos.setMsgStatus("Exportando notas fiscais de compra anteriores...", 0, 0);
            
            
            sql = "select a.codigo as lancamento, "
                    + "a.cliente, "
                    + "case a.ESPECIE when 'NFE' then 36 else 0 end as cod_especie, "
                    + "b.CNPJ as inscr_fornecedor, "
                    + "b.INSC as inscr_est, "
                    + "b.INSC_MUNICIPAL, "
                    + " case (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) "
                    + "     when '6231' then '29' "
                    + "     when '2103' then '29' "
                    + "     when '2801' then '106' "
                    + "     when '2802' then '107' "
                    + "     when '2701' then '109' "
                    + "     when '2104' then '110' "
                    + "     when '6712' then '112' "
                    + "     when '2744' then '19' "
                    + "     else (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cod_acum, "
                    + "case (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) "
                    + "     when '5101' then '1101' "
                    + "     when '5102' then '1101' "
                    + "      when '5117' then '1116' "
                    + "      when '5910' then '1910' "
                    + "      when '5949' then '1949' "
                    + "      when '5922' then '1922' "
                    + "     else (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cfop, "
                    + "d.codigo as nro_nota, "
                    + "'001' as SERIE, "
                    + "a.DT_EMISSAO, "
                    + "a.NF_DATA_SAIDA, "
                    + "round((select sum(c.VLR_CONTABIL_ITEM) from VEN_SAIDAS_PRODUTOS c where c.EMPRESA = a.EMPRESA and c.CODIGO = a.CODIGO),2) as vlr_contabil, "
                    + "round((select sum(c1.VLR_DESCONTO) from VEN_SAIDAS_PRODUTOS c1 where c1.EMPRESA = a.EMPRESA and c1.CODIGO = a.CODIGO),2) as vlr_desconto, "
                    + "a.VLR_BASEICMS, "
                    + "a.VLR_ICMS, "
                    + "case when a.VLR_FRETE is null then 0.00 else a.VLR_FRETE end as vlr_frete, "
                    + "case when a.VLR_SEGURO is null then 0.00 else a.VLR_SEGURO end as vlr_seguro, "
                    + "a.AUTORIZADO, "
                    + "a.OBSERVACAO, "
                    + "case a.NFFRETE when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete, "
                    + "'T' as emitente_nf, "
                    + "case when d.NFE_ID is null then '' else d.NFE_ID end as chave, "
                    + "e.PRODUTO, "
                    + "e.QUANTIDADE, "
                    + "e.VLR_CONTABIL_ITEM, "
                    + "e.CFOP, "
                    + "e.OBSERVACAO, "
                    + "e.VLR_BASE_ICMS, "
                    + "e.ALIQUOTA_ICMS, "
                    + "e.VLR_UNITARIO, "
                    + "e.VALOR_ICMS, "
                    + "e.SITUACAO_TRIBUTARIA, "
                    + "case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod, "
                    + "e.LOTE,"
                    + "case when o.COMPRA_FIXAR = 'S' "
                    + "         or o.COMPLEMENTO_FIXAR = 'S' "
                    + "         or o.VENDA_COMPRA_PRODUTOS = 'S' "
                    + "         or o.VENDA_ENTREGA_FUTURA = 'S' "
                    + "     then 1 else 0 "
                    + "     end as gera_financeiro "
                    + "from ven_saidas a "
                    + "LEFT JOIN CAD_ESTABELECIMENTOS b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.ESTABELEC_CAD) "
                    + "INNER JOIN VEN_NOTAFISCAL d on (d.EMPRESA = a.EMPRESA and d.CODIGO = a.NRO_NOTA and d.SERIE = a.SERIE and d.CLIENTE = a.CLIENTE) "
                    + "LEFT JOIN VEN_SAIDAS_PRODUTOS e on (e.EMPRESA = a.EMPRESA and e.CODIGO = a.CODIGO) "
                    + "left join CAD_PRODUTOS p on (p.EMPRESA = e.EMPRESA and p.CODIGO = e.PRODUTO) "
                    + "left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.CODIGO) "
                    + "LEFT JOIN CAD_OPERACOES_FISC o on (o.EMPRESA = e.EMPRESA and o.CODIGO = e.OPERACAO) "
                    + "where a.EMPRESA = 1 and a.CLIENTE = '" + pessoa + "' "
                    + "and a.DT_EMISSAO < '" + dti.replace("/", ".") + "' "
                    + "and exists ("
                    + "select * from REL_VENDAS_CRECEBER cr "
                    + "left join FIN_CONTAS_RECEBER fcr "
                    + "     on (fcr.EMPRESA = cr.EMPRESA "
                    + "     and fcr.CODIGO = cr.CODIGO_RECEBER "
                    + "     and fcr.CLIENTE = '" + pessoa + "') "
                    + "left join FIN_CONTAS_RECEBER_BAIXAS_DET bxd on (bxd.EMPRESA = fcr.EMPRESA and bxd.CODIGO_RECEBER = fcr.CODIGO) "
                    + "inner join FIN_CONTAS_RECEBER_BAIXAS bx on (bx.EMPRESA = bxd.EMPRESA "
                    + "     and bx.CODIGO = bxd.CODIGO_BAIXA "
                    + "     and bx.DATA between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "') "
                    + "where cr.empresa = a.empresa and cr.CODIGO_VENDA = a.codigo);";

            cnfb.executeConsulta(sql);

            lancamentos.clear();

            /**
             * IMPORTAÇÃO DAS NOTAS FISCAIS. Valida se nota fiscal já foi
             * importada. Próximo passo lista os produtos.
             */
            qtdRegistroAtual = 1;
            cnfb.rs.last();
            qtdRegistros = cnfb.rs.getRow();
            cnfb.rs.beforeFirst();

            //JOptionPane.showMessageDialog(null, "Registros: " + qtdRegistros);
            while (cnfb.rs.next()) {

                avisos.setProgresso(qtdRegistroAtual, qtdRegistros);

                if (!lancamentos.contains(cnfb.rs.getString("lancamento"))) {
                    lancamentos.add(cnfb.rs.getString("lancamento"));

                    notas = new Reg1000_Notas_Fiscais_Entrada();

                    notas.setCod_especie(cnfb.rs.getString("cod_especie"));
                    notas.setInscricao_fornecedor(cnfb.rs.getString("inscr_fornecedor"));
                    notas.setInsc_est_fornecedor(cnfb.rs.getString("inscr_est"));
                    notas.setInsc_mun_fornecedor(cnfb.rs.getString("insc_municipal"));
                    notas.setCod_acum(cnfb.rs.getString("cod_acum"));
                    notas.setCfop(cnfb.rs.getString("cfop"));
                    notas.setNro_doc(cnfb.rs.getString("nro_nota"));
                    notas.setSerie(cnfb.rs.getString("serie"));
                    notas.setDt_emissao(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                    notas.setDt_entrada(dateIn.format(cnfb.rs.getDate("nf_data_saida")));
                    notas.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                    notas.setVlr_produtos(df.format(cnfb.rs.getDouble("vlr_contabil")));
                    notas.setVlr_frete(df.format(cnfb.rs.getDouble("vlr_frete")));
                    notas.setVlr_seguro(df.format(cnfb.rs.getDouble("vlr_seguro")));
                    notas.setObservacao(cnfb.rs.getString("observacao"));
                    notas.setMod_frete(cnfb.rs.getString("mod_frete"));
                    notas.setEmitente_nf(cnfb.rs.getString("emitente_nf"));
                    notas.setChave_nfe(cnfb.rs.getString("chave"));

                    layout.add(notas.getLayout());

                    /**
                     * Controla apenas o imposto ICMS nas notas de entrada.
                     */
                    notas_impostos = new Reg1020_Notas_Fiscais_Entrada_Impostos();

                    notas_impostos.setCod_imposto("1");
                    notas_impostos.setBc(df.format(cnfb.rs.getDouble("vlr_baseicms")));
                    notas_impostos.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                    notas_impostos.setVlr_imposto(df.format(cnfb.rs.getDouble("vlr_icms")));

                    if (cnfb.rs.getString("situacao_tributaria").equals("040")) {
                        notas_impostos.setVlr_isentas(df.format(cnfb.rs.getDouble("vlr_contabil")));
                    } else if (cnfb.rs.getString("situacao_tributaria").equals("051") | (cnfb.rs.getString("situacao_tributaria").equals("050"))) {
                        notas_impostos.setVlr_outras(df.format(cnfb.rs.getDouble("vlr_contabil")));

                    } else {
                        notas_impostos.setVlr_outras(df.format(cnfb.rs.getDouble("vlr_contabil") - cnfb.rs.getDouble("vlr_baseicms")));
                    }

                    layout.add(notas_impostos.getLayout());

                    /**
                     * CONSULTA AS PARCELAS DO FINANCEIRO AO LER A PRIMEIRA
                     * LINHA DA NOTA FISCAL.
                     */
                    if (cnfb.rs.getBoolean("gera_financeiro")) {
                        avisos.setMsgStatus("Exportando parcelas das notas fiscais de compra...", qtdRegistroAtual, qtdRegistros);

                        gera_financeiro = null;

                        sql = "SELECT b.CODIGO, "
                                + "b.DOC, "
                                + "b.DT_VENC, "
                                + "sum(c.VALOR) as valor "
                                + "FROM REL_VENDAS_CRECEBER a "
                                + "LEFT JOIN FIN_CONTAS_RECEBER b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.CODIGO_RECEBER) "
                                + "INNER JOIN FIN_CONTAS_RECEBER_DETALHE c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO and c.TIPO_PLANO = 'C') "
                                + "where a.EMPRESA = 1 and a.CODIGO_VENDA = '" + cnfb.rs.getString("lancamento") + "' "
                                + "and b.cliente = '" + pessoa + "' "
                                + "GROUP BY 1,2,3;";

                        cnfb2.executeConsulta(sql);

                        while (cnfb2.rs.next()) {
                            gera_financeiro = new Reg1500_Notas_Fiscais_Entrada_Parcelas();

                            gera_financeiro.setNro_titulo(cnfb.rs.getString("nro_nota"));
                            gera_financeiro.setValor(df.format(cnfb2.rs.getDouble("valor")));
                            gera_financeiro.setVencimento(dateIn.format(cnfb2.rs.getDate("dt_venc")));
                        }
                        cnfb2.rs.close();

                        if (gera_financeiro == null) {

                            gera_financeiro = new Reg1500_Notas_Fiscais_Entrada_Parcelas();

                            gera_financeiro.setNro_titulo(cnfb.rs.getString("nro_nota"));
                            gera_financeiro.setValor(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            gera_financeiro.setVencimento(dateIn.format(cnfb.rs.getDate("dt_emissao")));

                        }
                        layout.add(gera_financeiro.getLayout());
                    }
                }

                avisos.setMsgStatus("Exportando produtos das notas fiscais de compra anteriores...", qtdRegistroAtual, qtdRegistros);
                notas_produtos = new Reg1030_Notas_Fiscais_Entrada_Estoque();

                notas_produtos.setTp_lancamento("1");
                notas_produtos.setCod_produto(cnfb.rs.getString("produto"));
                notas_produtos.setQuantidade(df.format(cnfb.rs.getDouble("quantidade")));
                notas_produtos.setVlr_cont(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                notas_produtos.setCfop(cnfb.rs.getString("cfop"));
                notas_produtos.setBc(df.format(cnfb.rs.getDouble("vlr_base_icms")));
                notas_produtos.setAliq_icms(df.format(cnfb.rs.getDouble("aliquota_icms")));
                notas_produtos.setVlr_unit(df.format(cnfb.rs.getDouble("vlr_unitario")));
                notas_produtos.setMovim_fis_prod(cnfb.rs.getString("movim_fis_prod"));
                notas_produtos.setVlt_total(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                notas_produtos.setCus_total_produto(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                notas_produtos.setVlr_bruto_prod(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                notas_produtos.setVlr_icms(df.format(cnfb.rs.getDouble("valor_icms")));
                notas_produtos.setCod_st(cnfb.rs.getString("situacao_tributaria"));

                layout.add(notas_produtos.getLayout());

                qtdRegistroAtual++;
            }
            cnfb.rs.close();
            // FIM DA IMPORTAÇÃO DE NOTAS FISCAIS
        }
        if (saidas) {

            avisos.setMsgStatus("Exportando notas fiscais de venda anteriores...", qtdRegistroAtual, qtdRegistros);
            sql = "select a.codigo as lancamento, "
                    + "case a.especie when 'NFE' then 36 else 99 end as especie, "
                    + "b.cnpj as inscricao_cliente, b.insc_municipal, "
                    + "b.insc, "
                    + "case (select first 1 a1.operacao from com_entradas_produtos a1 where a1.empresa = a.empresa and a1.codigo = a.codigo) "
                    + " when '1602' then '75' "
                    + " when '1701' then '108' "
                    + " when '1105' then '113' "
                    + " when '6212' then '111' "
                    + " when '1109' then '113' "
                    + " when '6216' then '114' "
                    + " when '6313' then '115' "
                    + " else (select first 1 a1.operacao from com_entradas_produtos a1 where a1.empresa = a.empresa and a1.codigo = a.codigo) end as cod_acumulador, "
                    + "case (select first 1 a2.cfop from com_entradas_produtos a2 where a2.empresa = a.empresa and a2.codigo = a.codigo) "
                    + " when '1202' then '5201' "
                    + " when '1949' then '5949' "
                    + " when '1102' then '5101' "
                    + " when '1101' then '5101' "
                    + " when '1556' then '5101' "
                    + " else (select first 1 a2.cfop from com_entradas_produtos a2 where a2.empresa = a.empresa and a2.codigo = a.codigo) end as cfop, "
                    + "m.uf as sigla_uf_cliente, "
                    + "a.numero as nro_doc, a.serie, "
                    + "a.dt_entrada as dt_saida, "
                    + "a.dt_emissao, "
                    + "(select sum(a3.vlr_contabil_item) from com_entradas_produtos a3 where a3.empresa = a.empresa and a3.codigo = a.codigo) as vlr_contabil, "
                    + "a.observacao, a.nfe_chave_acesso, "
                    + "a.total_produtos, "
                    + "case a.cif_fob when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete, "
                    + "a.vlr_funrural as funrural, "
                    + "a.TOTAL_BASE_ICMS, "
                    + "a.TOTAL_VALOR_ICMS,"
                    + "o.FUNRURAL as desconta_funrural, "
                    + "p.produto as cod_produto, "
                    + "p.QUANTIDADE, "
                    + "p.VLR_CONTABIL_ITEM, "
                    + "p.VLR_BASE_ICMS, "
                    + "p.ALIQ_ICMS,"
                    + "p.UNITARIO,"
                    + "p.SITUACAO_TRIBUTARIA,"
                    + "case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod,"
                    + "case when o.COMPRA_FIXAR = 'S' or o.COMPLEMENTO_FIXAR = 'S' or o.VENDA_COMPRA_PRODUTOS = 'S' or o.VENDA_ENTREGA_FUTURA = 'S' then 1 else 0 end as gera_financeiro "
                    + "from com_entradas a "
                    + "left join cad_estabelecimentos b on (b.empresa = a.empresa and b.codigo = a.estabelec) "
                    + "left join cad_municipios m on (m.codigo = b.municipio) "
                    + "left join COM_ENTRADAS_PRODUTOS p on (p.EMPRESA = a.EMPRESA and p.CODIGO = a.CODIGO) "
                    + "left join CAD_OPERACOES_FISC o on (o.EMPRESA = p.EMPRESA and o.CODIGO = p.OPERACAO) "
                    + "left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.PRODUTO) "
                    + "where a.empresa = 1 and a.fornecedor = '" + pessoa + "' "
                    + "and a.dt_entrada < '" + dti.replace("/", ".") + "' "
                    + "and exists ( select * from REL_COMPRAS_CPAGAR pag "
                    + "left join FIN_CONTAS_PAGAR_BAIXAS_DETALHE crb on (crb.EMPRESA = pag.EMPRESA and crb.CODIGO_PAGAR = pag.CODIGO_PAGAR) "
                    + "inner JOIN FIN_CONTAS_PAGAR_BAIXAS cr "
                    + "     on (cr.EMPRESA = crb.EMPRESA "
                    + "     and cr.CODIGO = crb.CODIGO_BAIXA "
                    + "     and cr.DATA between '" + dti.replace("/", ".") + "' and '" + dtf.replace("/", ".") + "') "
                    + "where pag.EMPRESA = a.empresa and pag.CODIGO_ENTRADA = a.codigo);";

            cnfb.executeConsulta(sql);
            lancamentos_saidas.clear();

            qtdRegistroAtual = 1;
            cnfb.rs.last();
            qtdRegistros = cnfb.rs.getRow();
            cnfb.rs.beforeFirst();

            while (cnfb.rs.next()) {
                avisos.setProgresso(qtdRegistroAtual, qtdRegistros);
                notas_saidas = new Reg2000_Notas_Fiscais_Saidas();

                if (!lancamentos_saidas.contains(cnfb.rs.getString("lancamento"))) {
                    lancamentos_saidas.add(cnfb.rs.getString("lancamento"));

                    notas_saidas.setCod_especie(cnfb.rs.getString("especie"));
                    notas_saidas.setInscricao_cliente(cnfb.rs.getString("inscricao_cliente"));
                    notas_saidas.setCod_acumulador(cnfb.rs.getString("cod_acumulador"));
                    notas_saidas.setCfop(cnfb.rs.getString("cfop"));
                    notas_saidas.setSigla_uf_cliente(cnfb.rs.getString("sigla_uf_cliente"));
                    notas_saidas.setNro_doc(cnfb.rs.getString("nro_doc"));
                    notas_saidas.setSerie("1");
                    notas_saidas.setDt_saida(dateIn.format(cnfb.rs.getDate("dt_saida")));
                    notas_saidas.setDt_emissao(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                    notas_saidas.setObservacao(cnfb.rs.getString("observacao"));
                    notas_saidas.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                    notas_saidas.setMod_frete(cnfb.rs.getString("mod_frete"));
                    notas_saidas.setVlr_produtos(df.format(cnfb.rs.getDouble("total_produtos")));
                    notas_saidas.setChave_NFe(cnfb.rs.getString("nfe_chave_acesso"));
                    notas_saidas.setInscr_est_cli(cnfb.rs.getString("insc"));
                    notas_saidas.setInscr_mun_cli(cnfb.rs.getString("insc_municipal"));
                    notas_saidas.setTp_titulo("5");
                    notas_saidas.setEmitente("T");
                    notas_saidas.setCod_mod_doc_fis("9");

                    layout.add(notas_saidas.getLayout());

                    /**
                     * Exportação de dados de ICMS
                     *
                     */
                    notas_saidas_impostos = new Reg2020_Notas_Fiscais_Saida_Impostos();

                    notas_saidas_impostos.setCod_imp("1");
                    notas_saidas_impostos.setVlr_contabil(df.format(cnfb.rs.getDouble("total_produtos")));
                    notas_saidas_impostos.setBc(df.format(cnfb.rs.getDouble("total_base_icms")));
                    notas_saidas_impostos.setVlr_imp(df.format(cnfb.rs.getDouble("total_valor_icms")));
                    notas_saidas_impostos.setVlr_outras(df.format(cnfb.rs.getDouble("vlr_contabil") - cnfb.rs.getDouble("total_base_icms")));

                    layout.add(notas_saidas_impostos.getLayout());

                    /**
                     * Exportação de dados do FUNRURAL
                     *
                     */
                    if (cnfb.rs.getString("desconta_funrural").equals("X")) {
                        notas_saidas_impostos = new Reg2020_Notas_Fiscais_Saida_Impostos();

                        notas_saidas_impostos.setCod_imp("28");
                        notas_saidas_impostos.setVlr_contabil(df.format(cnfb.rs.getDouble("vlr_contabil")));
                        notas_saidas_impostos.setBc(df.format(cnfb.rs.getDouble("total_produtos")));
                        notas_saidas_impostos.setVlr_imp(df.format(cnfb.rs.getDouble("funrural")));

                        layout.add(notas_saidas_impostos.getLayout());

                    }
                    /**
                     * Exportação das parcelas das notas de saída.
                     */
                    if (cnfb.rs.getBoolean("gera_financeiro")) {
                        avisos.setMsgStatus("Exportando parcelas das notas fiscais de venda...", qtdRegistroAtual, qtdRegistros);
                        sql = "SELECT b.CODIGO, b.DOC, b.DT_VENC, sum(c.VALOR) as valor "
                                + "FROM REL_COMPRAS_CPAGAR a "
                                + "left join FIN_CONTAS_PAGAR b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.CODIGO_PAGAR) "
                                + "inner join FIN_CONTAS_PAGAR_DETALHE c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO and c.TIPO_PLANO = 'D') "
                                + "where a.EMPRESA = '1' and a.CODIGO_ENTRADA = '" + cnfb.rs.getString("lancamento") + "' "
                                + "and b.FORNECEDOR = '" + pessoa + "' "
                                + "GROUP BY 1,2,3;";

                        cnfb2.executeConsulta(sql);

                        while (cnfb2.rs.next()) {
                            gera_financeiro_saida = new Reg2500_Notas_Fiscais_Saidas_Parcelas();

                            //gera_financeiro_saida.setNro_titulo(cnfb.rs.getString("nro_doc"));
                            gera_financeiro_saida.setValor(df.format(cnfb2.rs.getDouble("valor")));
                            gera_financeiro_saida.setVencimento(dateIn.format(cnfb2.rs.getDate("dt_venc")));
                            gera_financeiro_saida.setVlr_funrural(df.format(cnfb.rs.getDouble("funrural")));
                        }
                        cnfb2.rs.close();

                        if (gera_financeiro_saida == null) {

                            gera_financeiro_saida = new Reg2500_Notas_Fiscais_Saidas_Parcelas();

                            //gera_financeiro_saida.setNro_titulo(cnfb.rs.getString("doc"));
                            gera_financeiro_saida.setValor(df.format(cnfb.rs.getDouble("vlr_contabil")));
                            gera_financeiro_saida.setVencimento(dateIn.format(cnfb.rs.getDate("dt_emissao")));
                            gera_financeiro_saida.setVlr_funrural(df.format(cnfb.rs.getDouble("funrural")));

                        }
                        layout.add(gera_financeiro_saida.getLayout());
                    }
                }

                /**
                 * Exportação de produtos.
                 */
                avisos.setMsgStatus("Exportando produtos das notas fiscais de venda...", qtdRegistroAtual, qtdRegistros);
                notas_saidas_produtos = new Reg2030_Notas_Fiscais_Saida_Estoque();

                notas_saidas_produtos.setCod_produto(cnfb.rs.getString("cod_produto"));
                notas_saidas_produtos.setQtd(df.format(cnfb.rs.getDouble("quantidade")));
                notas_saidas_produtos.setVlr_bruto_prod(df.format(cnfb.rs.getDouble("vlr_contabil_item")));
                notas_saidas_produtos.setBc_icms(df.format(cnfb.rs.getDouble("vlr_base_icms")));
                notas_saidas_produtos.setAliq_icms(df.format(cnfb.rs.getDouble("aliq_icms")));
                notas_saidas_produtos.setQuantidade(df.format(cnfb.rs.getDouble("quantidade")));
                notas_saidas_produtos.setVlr_unit(df.format(cnfb.rs.getDouble("unitario")));
                notas_saidas_produtos.setTp_lancamento("1");
                notas_saidas_produtos.setCod_st(cnfb.rs.getString("situacao_tributaria"));
                notas_saidas_produtos.setMovim_fis_prod(cnfb.rs.getString("movim_fis_prod"));

                layout.add(notas_saidas_produtos.getLayout());

                qtdRegistroAtual++;
            }
            cnfb.rs.close();

        }

    }

    public ArrayList getLayout() {
        return layout;
    }

    public static void main(String[] args) {

        ConexaoFB.arquivo = "fbcasa.properties";
        String x = "";
        try {

            LimberExportaDados exp = new LimberExportaDados(null, false, false, false, true, true, true, true, "01.01.2017", "31.12.2017", "2", "D:/", 1);
            /*
            for (int i = 0; i < exp.getLayout().size(); i++) {
                
                System.out.println(exp.getLayout().get(i));
                x += exp.getLayout().get(i) + "\n";
            }
            
            copyToClipboard(x);
             */
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
