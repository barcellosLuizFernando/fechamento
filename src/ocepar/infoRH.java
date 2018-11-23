package ocepar;

import conn.ConexaoMySQL;
import conn.ConexaoORCL;
import java.io.IOException;
import javax.swing.JOptionPane;
import tools.FolhaPagamentos;
import tools.Funcionarios;
import tools.SalvarArquivoTxt;
import view.TelaPrincipal;

public class infoRH {

    private final conn.ConexaoORCL orcn = new ConexaoORCL();
    private String sql;
    private String layout;

    private final int idCoop;
    private final int month;
    private final int year;

    private double fpgto = 0.00;
    private double desl_total = 0.00;
    private double adm = 0.00;
    private double adm_total = 0.00;
    private double desl3 = 0.00;
    private double desl6 = 0.00;
    private double desl12 = 0.00;
    private double crrv = 0.00;
    private double efp = 0;
    private double hpa = 0.00;
    private double terceirizados = 0.00;
    private double hep = 0.00;
    private double terceirizados_permanentes = 0.00;
    private double ppn = 0.00;
    private double ppp = 0.00;
    private double dp = 0.00;
    private double dd = 0.00;
    private double hpd = 0.00;
    private double naca = 0.00;
    private double rt_ativas = 0.00;
    private double rt = 0.00;
    private double hht = 0.00;
    private double hep_valor = 0.00;
    private double vitc = 0.00;
    private double vits = 0.00;
    private double vitt = 0.00;
    private double jovens = 0.00;
    private double jovens_efetivados = 0.00;
    private double snom = 0.00;
    private double sval = 0.00;
    private double ben = 0.00;
    private double fap = 0.00;
    private double desl = 0.00;
    private double deslped = 0.00;

    /**
     * FPGTO: VALOR TOTAL DA FOLHA DE PAGAMENTO (INCLUINDO ENCARGOS) OBS: ** NÃO
     * CONSIDERAR PLR
     *
     * @param fpgto
     */
    public void setFpgto(double fpgto) {
        this.fpgto += fpgto;
    }

    /**
     * DESL TOTAL: DESLIGADOS (INICIATIVA DA ORGANIZAÇÃO OU NÃO) NO PERÍODO,
     * INCLUI POSTOS DE TRABALHO ELIMINADOS
     *
     * @param desl_total
     */
    public void setDesl_total(int desl_total) {
        this.desl_total += desl_total;
    }

    /**
     * ADM: QUANTIDADE DE ADMISSÕES DE REPOSIÇÃO (MÊS CORRENTE)
     *
     * @param adm
     */
    public void setAdm(int adm) {
        this.adm += adm;
    }

    /**
     * ADM TOTAL: QUANTIDADE DE ADMISSÕES (TOTAL) NO MÊS CORRENTE
     *
     * @param adm_total
     */
    public void setAdm_total(int adm_total) {
        this.adm_total += adm_total;
    }

    /**
     * DESL3: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA
     * IGUAL OU INFERIOR A 3 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.
     *
     * @param desl3
     */
    public void setDesl3(int desl3) {
        this.desl3 += desl3;
    }

    /**
     * DESL6: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA
     * IGUAL OU INFERIOR A 6 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.
     *
     * @param desl6
     */
    public void setDesl6(int desl6) {
        this.desl6 += desl6;
    }

    /**
     * DESL12: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA
     * IGUAL OU INFERIOR A 12 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.
     *
     * @param desl12
     */
    public void setDesl12(int desl12) {
        this.desl12 += desl12;
    }

    /**
     * CRRV: QUANTIDADE DE COLABORADORES QUE TIVERAM DIREITO À REMUNERAÇÃO
     * VARIÁVEL NO MÊS EM REFERÊNCIA (CONFORME DESEMBOLSO DA COOPERATIVA) (NÃO
     * CONSIDERAR HORA EXTRA COMO REMUNERAÇÃO VARIÁVEL). (CONCEITO REMUNERAÇÃO
     * VARIÁVEL = SVAL)
     *
     * @param crrv
     */
    public void setCrrv(int crrv) {
        this.crrv += crrv;
    }

    /**
     * EFP: QUANTIDADE DE COLABORADORES EFETIVOS (CLT) NO ÚLTIMO DIA DO MÊS
     *
     * @param efp
     */
    public void setEfp(int efp) {
        this.efp += efp;
    }

    /**
     * HPA: FALTAS OU ATRASOS = QUANTIDADE DE HORAS PERDIDAS POR AUSÊNCIA DOS
     * COLABORADORES (FALTAS COM OU SEM JUSTIFICATIVA) NO MÊS CORRENTE
     *
     * @param hpa
     */
    public void setHpa(double hpa) {
        this.hpa += hpa;
    }

    /**
     * 3°T/A: NÚMERO DE EMPREGADOS TERCEIRIZADOS AVULSOS E TEMPORÁRIOS.(BASE
     * ÚLTIMO DIA DO MÊS)
     *
     * @param terceirizados
     */
    public void setTerceirizados(int terceirizados) {
        this.terceirizados += terceirizados;
    }

    /**
     * HEP: QUANTIDADE DE HORAS EXTRAS PAGAS NO MÊS
     *
     * @param hep
     */
    public void setHep(double hep) {
        this.hep += hep;
    }

    /**
     * 3°P: NÚMERO DE EMPREGADOS TERCEIRIZADOS EM CARÁTER PERMANENTE, NO MOMENTO
     * CONSIDERADO. (NÃO CONSIDERA-SE TEMPORÁRIOS E AVULSOS) (BASE ÚLTIMO DIA DO
     * MÊS)
     *
     * @param terceirizados_permanentes
     */
    public void setTerceirizados_permanentes(int terceirizados_permanentes) {
        this.terceirizados_permanentes += terceirizados_permanentes;
    }

    /**
     * PPN: PERÍODO PRODUTIVO NORMAL. (EM HORAS)
     *
     * @param ppn
     */
    public void setPpn(double ppn) {
        this.ppn += ppn;
    }

    /**
     * PPP: PERÍODO PRODUTIVO PRÓPRIO, OU SEJA, O SOMATÓRIO DAS HORAS
     * EFETIVAMENTE TRABALHADAS (NÃO INCLUI DSR) NOTA: INCLUI APENAS O EFETIVO
     * PRÓPRIO, PORÉM, TODAS AS HORAS EFETIVAMENTE TRABALHADAS (NORMAIS E
     * EXTRAS). NÃO INCLUI FÉRIAS.
     *
     * @param ppp
     */
    public void setPpp(double ppp) {
        this.ppp += ppp;
    }

    /**
     * DP: DIAS  (CORRIDOS) PERDIDOS NO MÊS EM REFERÊNCIA COM AFASTAMENTO DO
     * TRABALHO , SOMENTE ACIDENTES DE TRABALHO. (ACIDENTES DE TRAJETO NÃO DEVEM
     * SER CONSIDERADOS, POIS CONF. NBR 14280 ITEM 3.7.2, O ACIDENTE DE TRAJETO
     * DEVE SER TRATADO À PARTE, NÃO SENDO INCLUÍDO NO CÁLCULO USUAL DAS TAXAS
     * DE FREQÜÊNCIA E DE GRAVIDADE.)
     *
     * @param dp
     */
    public void setDp(int dp) {
        this.dp += dp;
    }

    /**
     * DD: DIAS DEBITADOS POR MORTE OU INCAPACIDADE PERMANENTE (ACIDENTES DE
     * TRAJETO NÃO DEVEM SER CONSIDERADOS, POIS CONF. NBR 14280 ITEM 3.7.2, O
     * ACIDENTE DE TRAJETO DEVE SER TRATADO À PARTE, NÃO SENDO INCLUÍDO NO
     * CÁLCULO USUAL DAS TAXAS DE FREQÜÊNCIA E DE GRAVIDADE.)
     *
     * @param dd
     */
    public void setDd(int dd) {
        this.dd += dd;
    }

    /**
     * HPD: QUANTIDADE DE HORAS PERDIDAS POR AUSÊNCIA (ATESTADOS, OU SEJA OU
     * SEJA, AFASTAMENTOS MÉDICOS E ACIDENTES DO TRABALHO DOS COLABORADORES NO
     * MÊS CORRENTE
     *
     * @param hpd
     */
    public void setHpd(double hpd) {
        this.hpd += hpd;
    }

    /**
     * NACA: NÚMERO DE ACIDENTES COM AFASTAMENTO NO PERÍODO. (DEVIDAMENTE
     * INFORMADOS À PREVIDÊNCIA) ***NÃO CONSIDERAR ACIDENTE DE TRAJETO:
     *
     * @param naca
     */
    public void setNaca(int naca) {
        this.naca += naca;
    }

    /**
     * RT ATIVAS: RECLAMATÓRIAS TRABALHISTAS "EM SER" (QUANTIDADE), OU SEJA
     * QUANTIDADE DE AÇÕES EM ABERTO SEM SENTENÇA FINAL. (CONSIDERAR RT E AIND)
     *
     * @param rt_ativas
     */
    public void setRt_ativas(int rt_ativas) {
        this.rt_ativas += rt_ativas;
    }

    /**
     * RT: RECLAMATÓRIAS TRABALHISTAS NOTIFICADAS NO PERÍODO.(SOMA DA QUANTIDADE
     * DESDE O 1º ATÉ O ÚLTIMO DIA DO MÊS) (CONSIDERA-SE NOTIFICAÇÃO O MOMENTO
     * DA INTIMAÇÃO DA COOPERATIVA) (CONSIDERAR RTS E AIND)
     *
     * @param rt
     */
    public void setRt(int rt) {
        this.rt += rt;
    }

    /**
     * HHT: TOTAL DE HORAS DE TREINAMENTO = HORAS-HOMEM TREINAMENTO, OU SEJA,
     * TEMPO TOTAL DE TREINAMENTO (DENTRO E FORA DO EXPEDIENTE) DA EQUIPE.
     *
     * @param hht
     */
    public void setHht(double hht) {
        this.hht += hht;
    }

    /**
     * HEP R$: VALOR DE HORAS EXTRAS PAGAS NO MÊS
     *
     * @param hep_valor
     */
    public void setHep_valor(double hep_valor) {
        this.hep_valor += hep_valor;
    }

    /**
     * VITC: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DA COOPERATIVA)
     *
     * @param vitc
     */
    public void setVitc(double vitc) {
        this.vitc += vitc;
    }

    /**
     * VITS: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DESCENTRALIZADOS DO
     * SESCOOP)
     *
     * @param vits
     */
    public void setVits(double vits) {
        this.vits += vits;
    }

    /**
     * VITT: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DE TERCEIROS)
     *
     * @param vitt
     */
    public void setVitt(double vitt) {
        this.vitt += vitt;
    }

    /**
     * TOTAL DE JOVENS APRENDIZ
     *
     * @param jovens
     */
    public void setJovens(int jovens) {
        this.jovens += jovens;
    }

    /**
     * QUANTIDADE DE JOVENS APRENDIZES EFETIVADOS NO MÊS (APÓS ENCERRAMENTO DO
     * CONTRATO DE APRENDIZAGEM -CONTRATO PERMANENTE, PRAZO INDETERMINADO),
     * CONSIDERA-SE EGRESSO DO PROGRAMA ATÉ 12 MESES APÓS O ENCERRAMENTO DO
     * CONTRATO)
     *
     * @param jovens_efetivados
     */
    public void setJovens_efetivados(int jovens_efetivados) {
        this.jovens_efetivados += jovens_efetivados;
    }

    /**
     * SNOM: SALÁRIOS NOMINAIS, OU SEJA, SOMA DOS SALÁRIOS FIXOS (NOMINAIS)
     * PAGOS, TAMBÉM DENOMINADO DE SALÁRIO BASE. EXEMPLO: O SALÁRIO CONTRATADO
     * REGISTRADO NA CARTEIRA DE TRABALHO ( NO CASO DE CONTRATAÇÕES NO MEIO DO
     * MÊS CONSIDERAR O VALOR PROPORCIONAL (DESEMBOLSO)
     *
     * @param snom
     */
    public void setSnom(double snom) {
        this.snom += snom;
    }

    /**
     * SVAL: SALÁRIOS VARIÁVEIS, OU SEJA, SOMA DAS PARCELAS VARIÁVEIS PAGAS AOS
     * COLABORADORES (BÔNUS, PREMIAÇÃO POR VENDAS, COMISSÕES, ASSIDUIDADE,
     * ANUÊNIO, PLR, ETC.) NÃO SE CONSIDERA AS HORAS EXTRAS., CONSIDERAR O VALOR
     * NO PERÍODO DESEMBOLSADO. CONSIDERA-SE COMO VARIÁVEL A REMUNERAÇÃO QUE
     * DEPENDA DO ESFORÇO DO FUNCIONÁRIO.
     *
     * @param sval
     */
    public void setSval(double sval) {
        this.sval += sval;
    }

    /**
     * BEN: VALOR DOS BENEFÍCIOS CONCEDIDOS AOS COLABORADORES, E EFETIVAMENTE
     * DESEMBOLSADOS PELA COOPERATIVA
     *
     * @param ben
     */
    public void setBen(double ben) {
        this.ben += ben;
    }

    /**
     * FAP (INFORMAR O PERCENTUAL APLICÁVEL À COOPERATIVA)
     *
     * @param fap
     */
    public void setFap(double fap) {
        this.fap += fap;
    }

    /**
     * DESL: DESLIGADOS (INICIATIVA DA ORGANIZAÇÃO OU NÃO) NO PERÍODO.(BASE
     * ULTIMO DIA DO MÊS) SOMENTE POSTOS DE TRABALHOS MANTIDOS
     *
     * @param desl
     */
    public void setDesl(int desl) {
        this.desl += desl;
    }

    /**
     * DESLPED: DESLIGADOS APENAS POR INICIATIVA DO COLABORADOR NO PERÍODO.
     * (SOMA DA QUANTIDADE DESDE O 1º ATÉ O ULTIMO DIA DO MÊS)
     *
     * @param deslped
     */
    public void setDeslped(int deslped) {
        this.deslped += deslped;
    }

    /**
     * INFORMAÇÕES DE RH PARA O SEGMENTO AGROPECUÁRIO
     *
     * @param idCoop
     * @param month
     * @param year
     * @param conn
     */
    public infoRH(int idCoop, int month, int year, ConexaoMySQL conn) {
        this.idCoop = idCoop;
        this.month = month;
        this.year = year;

        int empresa;

        if (this.idCoop == 314) {
            empresa = 1;
        } else {
            empresa = 7;
        }

        /**
         * PESQUISA DADOS DA FOLHA.
         */
        try {

            /**
             * BUSCANDO DADOS DOS FUNCIONÁRIOS.
             */
            Funcionarios func = new Funcionarios(conn);
            func.buscaDados(true, 0, empresa, "01/" + this.month + "/" + this.year, tools.ManipulaData.lastDayToBR(this.month + "/" + this.year));
            setAdm(func.getAdmitidos());
            setAdm_total(func.getAdmitidos());
            setDesl_total(func.getDemitidos());
            setDesl(func.getDemitidos());
            setEfp(func.getAtivos());
            setDesl12(func.getDesl12());
            setDesl3(func.getDesl3());
            setDesl6(func.getDesl6());
            setDeslped(func.getDeslPed());

            /**
             * BUSCANDO DADOS DOS PAGAMENTOS.
             */
            FolhaPagamentos fopag = new FolhaPagamentos(empresa, this.month, this.year);
            setFpgto(fopag.getFpgto());
            setBen(fopag.getBen());
            setSval(fopag.getSval());
            setSnom(fopag.getSnom());
            setHep(fopag.getHep());
            setHep_valor(fopag.getHep_valor());
            setHpd(fopag.getHpd());
            setHpa(fopag.getHpa());

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void fileHeader() {

        layout += "inicio;" + this.idCoop + ";" + this.month + ";" + this.year + "\n";

    }

    private void fileFooter() {

        layout += "fim;" + this.idCoop + ";" + this.month + ";" + this.year;

    }

    public String getLayout() {

        layout = "";

        fileHeader();

        if (this.idCoop == 314) {
            layout += "1;DP: DIAS  (CORRIDOS) PERDIDOS NO MÊS EM REFERÊNCIA COM AFASTAMENTO DO TRABALHO , SOMENTE ACIDENTES DE TRABALHO. (ACIDENTES DE TRAJETO NÃO DEVEM SER CONSIDERADOS, POIS CONF. NBR 14280 ITEM 3.7.2, O ACIDENTE DE TRAJETO DEVE SER TRATADO À PARTE, NÃO SENDO INCLUÍDO NO CÁLCULO USUAL DAS TAXAS DE FREQÜÊNCIA E DE GRAVIDADE.);" + tools.FormatNumbers.numUsToBrNoDots(dp) + "+\n";
            layout += "3;DD: DIAS DEBITADOS POR MORTE OU INCAPACIDADE PERMANENTE (ACIDENTES DE TRAJETO NÃO DEVEM SER CONSIDERADOS, POIS CONF. NBR 14280 ITEM 3.7.2, O ACIDENTE DE TRAJETO DEVE SER TRATADO À PARTE, NÃO SENDO INCLUÍDO NO CÁLCULO USUAL DAS TAXAS DE FREQÜÊNCIA E DE GRAVIDADE.);" + tools.FormatNumbers.numUsToBrNoDots(dd) + "+\n";
            layout += "4;HPD: QUANTIDADE DE HORAS PERDIDAS POR AUSÊNCIA (ATESTADOS, OU SEJA OU SEJA, AFASTAMENTOS MÉDICOS E ACIDENTES DO TRABALHO DOS COLABORADORES NO MÊS CORRENTE;" + tools.FormatNumbers.numUsToBrNoDots(hpd) + "+\n";
            layout += "5;NACA: NÚMERO DE ACIDENTES COM AFASTAMENTO NO PERÍODO. (DEVIDAMENTE INFORMADOS À PREVIDÊNCIA) ***NÃO CONSIDERAR ACIDENTE DE TRAJETO:;" + tools.FormatNumbers.numUsToBrNoDots(naca) + "+\n";
            layout += "7;CRRV: QUANTIDADE DE COLABORADORES QUE TIVERAM DIREITO À REMUNERAÇÃO VARIÁVEL NO MÊS EM REFERÊNCIA (CONFORME DESEMBOLSO DA COOPERATIVA) (NÃO CONSIDERAR HORA EXTRA COMO REMUNERAÇÃO VARIÁVEL). (CONCEITO REMUNERAÇÃO VARIÁVEL = SVAL);" + tools.FormatNumbers.numUsToBrNoDots(crrv) + "+\n";
            layout += "8;EFP: QUANTIDADE DE COLABORADORES EFETIVOS (CLT) NO ÚLTIMO DIA DO MÊS;" + tools.FormatNumbers.numUsToBrNoDots(efp) + "+\n";
            layout += "10;HPA: FALTAS OU ATRASOS = QUANTIDADE DE HORAS PERDIDAS POR AUSÊNCIA DOS COLABORADORES (FALTAS COM OU SEM JUSTIFICATIVA) NO MÊS CORRENTE;" + tools.FormatNumbers.numUsToBrNoDots(hpa) + "+\n";
            layout += "12;3°T/A: NÚMERO DE EMPREGADOS TERCEIRIZADOS AVULSOS E TEMPORÁRIOS.(BASE ÚLTIMO DIA DO MÊS);" + tools.FormatNumbers.numUsToBrNoDots(terceirizados) + "+\n";
            layout += "13;3°P: NÚMERO DE EMPREGADOS TERCEIRIZADOS EM CARÁTER PERMANENTE, NO MOMENTO CONSIDERADO. (NÃO CONSIDERA-SE TEMPORÁRIOS E AVULSOS) (BASE ÚLTIMO DIA DO MÊS);" + tools.FormatNumbers.numUsToBrNoDots(terceirizados_permanentes) + "+\n";
            layout += "14;PPN: PERÍODO PRODUTIVO NORMAL. (EM HORAS);" + tools.FormatNumbers.numUsToBrNoDots(ppn) + "+\n";
            layout += "15;PPP: PERÍODO PRODUTIVO PRÓPRIO, OU SEJA, O SOMATÓRIO DAS HORAS EFETIVAMENTE TRABALHADAS (NÃO INCLUI DSR) NOTA: INCLUI APENAS O EFETIVO PRÓPRIO, PORÉM, TODAS AS HORAS EFETIVAMENTE TRABALHADAS (NORMAIS E EXTRAS). NÃO INCLUI FÉRIAS.;" + tools.FormatNumbers.numUsToBrNoDots(ppp) + "+\n";
            layout += "16;HHT: TOTAL DE HORAS DE TREINAMENTO = HORAS-HOMEM TREINAMENTO, OU SEJA, TEMPO TOTAL DE TREINAMENTO (DENTRO E FORA DO EXPEDIENTE) DA EQUIPE.;" + tools.FormatNumbers.numUsToBrNoDots(hht) + "+\n";
            layout += "17;VITC: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DA COOPERATIVA);" + tools.FormatNumbers.numUsToBrNoDots(vitc) + "+\n";
            layout += "18;VITS: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DESCENTRALIZADOS DO SESCOOP);" + tools.FormatNumbers.numUsToBrNoDots(vits) + "+\n";
            layout += "19;VITT: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DE TERCEIROS);" + tools.FormatNumbers.numUsToBrNoDots(vitt) + "+\n";
            layout += "20;RT ATIVAS: RECLAMATÓRIAS TRABALHISTAS \"EM SER\" (QUANTIDADE), OU SEJA QUANTIDADE DE AÇÕES EM ABERTO SEM SENTENÇA FINAL. (CONSIDERAR RT E AIND);" + tools.FormatNumbers.numUsToBrNoDots(rt_ativas) + "+\n";
            layout += "21;RT: RECLAMATÓRIAS TRABALHISTAS NOTIFICADAS NO PERÍODO.(SOMA DA QUANTIDADE DESDE O 1º ATÉ O ÚLTIMO DIA DO MÊS) (CONSIDERA-SE NOTIFICAÇÃO O MOMENTO DA INTIMAÇÃO DA COOPERATIVA) (CONSIDERAR RTS E AIND);" + tools.FormatNumbers.numUsToBrNoDots(rt) + "+\n";
            layout += "22;DESL: DESLIGADOS (INICIATIVA DA ORGANIZAÇÃO OU NÃO) NO PERÍODO.(BASE ULTIMO DIA DO MÊS) SOMENTE POSTOS DE TRABALHOS MANTIDOS;" + tools.FormatNumbers.numUsToBrNoDots(desl) + "+\n";
            layout += "23;DESLPED: DESLIGADOS APENAS POR INICIATIVA DO COLABORADOR NO PERÍODO. (SOMA DA QUANTIDADE DESDE O 1º ATÉ O ULTIMO DIA DO MÊS);" + tools.FormatNumbers.numUsToBrNoDots(deslped) + "+\n";
            layout += "24;DESL TOTAL: DESLIGADOS (INICIATIVA DA ORGANIZAÇÃO OU NÃO) NO PERÍODO, INCLUI POSTOS DE TRABALHO ELIMINADOS.;" + tools.FormatNumbers.numUsToBrNoDots(desl_total) + "+\n";
            layout += "25;ADM: QUANTIDADE DE ADMISSÕES DE REPOSIÇÃO (MÊS CORRENTE);" + tools.FormatNumbers.numUsToBrNoDots(adm) + "+\n";
            layout += "26;ADM TOTAL: QUANTIDADE DE ADMISSÕES (TOTAL) NO MÊS CORRENTE;" + tools.FormatNumbers.numUsToBrNoDots(adm_total) + "+\n";
            layout += "30;DESL12: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA IGUAL OU INFERIOR A 12 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.;" + tools.FormatNumbers.numUsToBrNoDots(desl12) + "+\n";
            layout += "31;DESL6: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA IGUAL OU INFERIOR A 6 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.;" + tools.FormatNumbers.numUsToBrNoDots(desl6) + "+\n";
            layout += "32;DESL3: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA IGUAL OU INFERIOR A 3 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.;" + tools.FormatNumbers.numUsToBrNoDots(desl3) + "+\n";
            layout += "34;FPGTO: VALOR TOTAL DA FOLHA DE PAGAMENTO (INCLUINDO ENCARGOS) OBS: ** NÃO CONSIDERAR PLR;" + tools.FormatNumbers.numUsToBrNoDots(fpgto) + "+\n";
            layout += "35;HEP: QUANTIDADE DE HORAS EXTRAS PAGAS NO MÊS;" + tools.FormatNumbers.numUsToBrNoDots(hep) + "+\n";
            layout += "36;HEP R$: VALOR DE HORAS EXTRAS PAGAS NO MÊS;" + tools.FormatNumbers.numUsToBrNoDots(hep_valor) + "+\n";
            layout += "37;SNOM: SALÁRIOS NOMINAIS, OU SEJA, SOMA DOS SALÁRIOS FIXOS (NOMINAIS) PAGOS, TAMBÉM DENOMINADO DE SALÁRIO BASE. EXEMPLO: O SALÁRIO CONTRATADO REGISTRADO NA CARTEIRA DE TRABALHO ( NO CASO DE CONTRATAÇÕES NO MEIO DO MÊS CONSIDERAR O VALOR PROPORCIONAL (DESEMBOLSO);" + tools.FormatNumbers.numUsToBrNoDots(snom) + "+\n";
            layout += "38;SVAL: SALÁRIOS VARIÁVEIS, OU SEJA, SOMA DAS PARCELAS VARIÁVEIS PAGAS AOS COLABORADORES (BÔNUS, PREMIAÇÃO POR VENDAS, COMISSÕES, ASSIDUIDADE, ANUÊNIO, PLR, ETC.) NÃO SE CONSIDERA AS HORAS EXTRAS., CONSIDERAR O VALOR NO PERÍODO DESEMBOLSADO. CONSIDERA-SE COMO VARIÁVEL A REMUNERAÇÃO QUE DEPENDA DO ESFORÇO DO FUNCIONÁRIO.;" + tools.FormatNumbers.numUsToBrNoDots(sval) + "+\n";
            layout += "39;BEN: VALOR DOS BENEFÍCIOS CONCEDIDOS AOS COLABORADORES, E EFETIVAMENTE DESEMBOLSADOS PELA COOPERATIVA;" + tools.FormatNumbers.numUsToBrNoDots(ben) + "+\n";
            layout += "633;TOTAL DE JOVENS APRENDIZ - ADMINISTRATIVO (PROGRAMA SESCOOP);0000000000000+\n";
            layout += "634;TOTAL DE JOVENS APRENDIZ - SUPERMERCADO (PROGRAMA SESCOOP);0000000000000+\n";
            layout += "635;TOTAL DE JOVENS APRENDIZ - INDUSTRIAL (PROGRAMA SESCOOP);0000000000000+\n";
            layout += "636;TOTAL DE JOVENS APRENDIZ - EAD (PROGRAMA SESCOOP);0000000000000+\n";
            layout += "637;COTA DE APRENDIZES A SER ATENDIDA;0000000000000+\n";
            layout += "638;QUANTIDADE DE DESLIGAMENTOS NO MÊS;0000000000000+\n";
            //layout += "639;QUANTIDADE DE MUNICÍPIOS QUE PARTICIPAM DO PROGRAMA DE APRENFIZAGEM;0000000000000+\n";
            layout += "561;TOTAL DE JOVENS APRENDIZ;" + tools.FormatNumbers.numUsToBrNoDots(jovens) + "+\n";
            layout += "562;QUANTIDADE DE JOVENS APRENDIZES EFETIVADOS NO MÊS (APÓS ENCERRAMENTO DO CONTRATO DE APRENDIZAGEM -CONTRATO PERMANENTE, PRAZO INDETERMINADO), CONSIDERA-SE EGRESSO DO PROGRAMA ATÉ 12 MESES APÓS O ENCERRAMENTO DO CONTRATO);" + tools.FormatNumbers.numUsToBrNoDots(jovens_efetivados) + "+\n";
            layout += "563;FAP (INFORMAR O PERCENTUAL APLICÁVEL À COOPERATIVA);" + tools.FormatNumbers.numUsToBrNoDots(fap) + "+\n";
        } else {
            layout += "469;DP: DIAS  (CORRIDOS) PERDIDOS NO MÊS EM REFERÊNCIA COM AFASTAMENTO DO TRABALHO , SOMENTE ACIDENTES DE TRABALHO. (ACIDENTES DE TRAJETO NÃO DEVEM SER CONSIDERADOS, POIS CONF. NBR 14280 ITEM 3.7.2, O ACIDENTE DE TRAJETO DEVE SER TRATADO À PARTE, NÃO SENDO INCLUÍDO NO CÁLCULO USUAL DAS TAXAS DE FREQÜÊNCIA E DE GRAVIDADE.);0000000000000+\n";
            layout += "471;DD: DIAS DEBITADOS POR MORTE OU INCAPACIDADE PERMANENTE (ACIDENTES DE TRAJETO NÃO DEVEM SER CONSIDERADOS, POIS CONF. NBR 14280 ITEM 3.7.2, O ACIDENTE DE TRAJETO DEVE SER TRATADO À PARTE, NÃO SENDO INCLUÍDO NO CÁLCULO USUAL DAS TAXAS DE FREQÜÊNCIA E DE GRAVIDADE.);0000000000000+\n";
            layout += "472;HPD: QUANTIDADE DE HORAS PERDIDAS POR AUSÊNCIA (ATESTADOS, OU SEJA OU SEJA, AFASTAMENTOS MÉDICOS E ACIDENTES DO TRABALHO DOS COLABORADORES NO MÊS CORRENTE;" + tools.FormatNumbers.numUsToBrNoDots(hpd) + "+\n";
            layout += "473;NACA: NÚMERO DE ACIDENTES COM AFASTAMENTO NO PERÍODO. (DEVIDAMENTE INFORMADOS À PREVIDÊNCIA) ***NÃO CONSIDERAR ACIDENTE DE TRAJETO:;0000000000000+\n";
            layout += "475;CRRV: QUANTIDADE DE COLABORADORES QUE TIVERAM DIREITO À REMUNERAÇÃO VARIÁVEL NO MÊS EM REFERÊNCIA (CONFORME DESEMBOLSO DA COOPERATIVA) (NÃO CONSIDERAR HORA EXTRA COMO REMUNERAÇÃO VARIÁVEL). (CONCEITO REMUNERAÇÃO VARIÁVEL = SVAL);0000000000000+\n";
            layout += "476;EFP: QUANTIDADE DE COLABORADORES EFETIVOS (CLT) NO ÚLTIMO DIA DO MÊS;" + tools.FormatNumbers.numUsToBrNoDots(efp) + "+\n";
            layout += "478;HPA: FALTAS OU ATRASOS = QUANTIDADE DE HORAS PERDIDAS POR AUSÊNCIA DOS COLABORADORES (FALTAS COM OU SEM JUSTIFICATIVA) NO MÊS CORRENTE;" + tools.FormatNumbers.numUsToBrNoDots(hpa) + "+\n";
            layout += "480;3°T/A: NÚMERO DE EMPREGADOS TERCEIRIZADOS AVULSOS E TEMPORÁRIOS.(BASE ÚLTIMO DIA DO MÊS);0000000000000+\n";
            layout += "481;3°P: NÚMERO DE EMPREGADOS TERCEIRIZADOS EM CARÁTER PERMANENTE, NO MOMENTO CONSIDERADO. (NÃO CONSIDERA-SE TEMPORÁRIOS E AVULSOS) (BASE ÚLTIMO DIA DO MÊS);0000000000000+\n";
            layout += "482;PPN: PERÍODO PRODUTIVO NORMAL. (EM HORAS);0000000000000+\n";
            layout += "483;PPP: PERÍODO PRODUTIVO PRÓPRIO, OU SEJA, O SOMATÓRIO DAS HORAS EFETIVAMENTE TRABALHADAS (NÃO INCLUI DSR) NOTA: INCLUI APENAS O EFETIVO PRÓPRIO, PORÉM, TODAS AS HORAS EFETIVAMENTE TRABALHADAS (NORMAIS E EXTRAS). NÃO INCLUI FÉRIAS.;0000000000000+\n";
            layout += "484;HHT: TOTAL DE HORAS DE TREINAMENTO = HORAS-HOMEM TREINAMENTO, OU SEJA, TEMPO TOTAL DE TREINAMENTO (DENTRO E FORA DO EXPEDIENTE) DA EQUIPE.;0000000000000+\n";
            layout += "485;VITC: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DA COOPERATIVA);0000000000000+\n";
            layout += "486;VITS: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DESCENTRALIZADOS DO SESCOOP);0000000000000+\n";
            layout += "487;VITT: VALOR INVESTIDO EM TREINAMENTO.(SOMAR VALORES DE TERCEIROS);0000000000000+\n";
            layout += "488;RT ATIVAS: RECLAMATÓRIAS TRABALHISTAS \"EM SER\" (QUANTIDADE), OU SEJA QUANTIDADE DE AÇÕES EM ABERTO SEM SENTENÇA FINAL. (CONSIDERAR RT E AIND);0000000000000+\n";
            layout += "489;RT: RECLAMATÓRIAS TRABALHISTAS NOTIFICADAS NO PERÍODO.(SOMA DA QUANTIDADE DESDE O 1º ATÉ O ÚLTIMO DIA DO MÊS) (CONSIDERA-SE NOTIFICAÇÃO O MOMENTO DA INTIMAÇÃO DA COOPERATIVA) (CONSIDERAR RTS E AIND);0000000000000+\n";
            layout += "490;DESL: DESLIGADOS (INICIATIVA DA ORGANIZAÇÃO OU NÃO) NO PERÍODO.(BASE ULTIMO DIA DO MÊS) SOMENTE POSTOS DE TRABALHOS MANTIDOS;" + tools.FormatNumbers.numUsToBrNoDots(desl) + "+\n";
            layout += "491;DESLPED: DESLIGADOS APENAS POR INICIATIVA DO COLABORADOR NO PERÍODO. (SOMA DA QUANTIDADE DESDE O 1º ATÉ O ULTIMO DIA DO MÊS);" + tools.FormatNumbers.numUsToBrNoDots(deslped) + "+\n";
            layout += "492;DESL TOTAL: DESLIGADOS (INICIATIVA DA ORGANIZAÇÃO OU NÃO) NO PERÍODO, INCLUI POSTOS DE TRABALHO ELIMINADOS.;" + tools.FormatNumbers.numUsToBrNoDots(desl_total) + "+\n";
            layout += "493;ADM: QUANTIDADE DE ADMISSÕES DE REPOSIÇÃO (MÊS CORRENTE);" + tools.FormatNumbers.numUsToBrNoDots(adm) + "+\n";
            layout += "494;ADM TOTAL: QUANTIDADE DE ADMISSÕES (TOTAL) NO MÊS CORRENTE;" + tools.FormatNumbers.numUsToBrNoDots(adm_total) + "+\n";
            layout += "498;DESL12: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA IGUAL OU INFERIOR A 12 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.;" + tools.FormatNumbers.numUsToBrNoDots(desl12) + "+\n";
            layout += "499;DESL6: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA IGUAL OU INFERIOR A 6 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.;" + tools.FormatNumbers.numUsToBrNoDots(desl6) + "+\n";
            layout += "500;DESL3: QUANTIDADE DE DESLIGAMENTOS DE COLABORADORES COM TEMPO DE EMPRESA IGUAL OU INFERIOR A 3 MESES, REALIZADOS NO MÊS EM REFERÊNCIA.;" + tools.FormatNumbers.numUsToBrNoDots(desl3) + "+\n";
            layout += "502;FPGTO: VALOR TOTAL DA FOLHA DE PAGAMENTO (INCLUINDO ENCARGOS) OBS: ** NÃO CONSIDERAR PLR;" + tools.FormatNumbers.numUsToBrNoDots(fpgto) + "+\n";
            layout += "503;HEP: QUANTIDADE DE HORAS EXTRAS PAGAS NO MÊS;" + tools.FormatNumbers.numUsToBrNoDots(hep) + "+\n";
            layout += "504;HEP R$: VALOR DE HORAS EXTRAS PAGAS NO MÊS;" + tools.FormatNumbers.numUsToBrNoDots(hep_valor) + "+\n";
            layout += "505;SNOM: SALÁRIOS NOMINAIS, OU SEJA, SOMA DOS SALÁRIOS FIXOS (NOMINAIS) PAGOS, TAMBÉM DENOMINADO DE SALÁRIO BASE. EXEMPLO: O SALÁRIO CONTRATADO REGISTRADO NA CARTEIRA DE TRABALHO ( NO CASO DE CONTRATAÇÕES NO MEIO DO MÊS CONSIDERAR O VALOR PROPORCIONAL (DESEMBOLSO);" + tools.FormatNumbers.numUsToBrNoDots(snom) + "+\n";
            layout += "506;SVAL: SALÁRIOS VARIÁVEIS, OU SEJA, SOMA DAS PARCELAS VARIÁVEIS PAGAS AOS COLABORADORES (BÔNUS, PREMIAÇÃO POR VENDAS, COMISSÕES, ASSIDUIDADE, ANUÊNIO, PLR, ETC.) NÃO SE CONSIDERA AS HORAS EXTRAS., CONSIDERAR O VALOR NO PERÍODO DESEMBOLSADO. CONSIDERA-SE COMO VARIÁVEL A REMUNERAÇÃO QUE DEPENDA DO ESFORÇO DO FUNCIONÁRIO.;" + tools.FormatNumbers.numUsToBrNoDots(sval) + "+\n";
            layout += "507;BEN: VALOR DOS BENEFÍCIOS CONCEDIDOS AOS COLABORADORES, E EFETIVAMENTE DESEMBOLSADOS PELA COOPERATIVA;" + tools.FormatNumbers.numUsToBrNoDots(ben) + "+\n";
        }

        fileFooter();
        System.out.println(layout);

        return layout;
    }

    public static void main(String[] args) throws Exception {
        conn.ConexaoMySQL conn = new ConexaoMySQL();
        try {
            conn.tela = new TelaPrincipal();
            conn.arquivo = "mysql.properties";
            conn.conecta("root", "01lety04");

            String texto = "";

            for (int i = 1; i <= 12; i++) {

                //COOPERTRADIÇÃO = 314
                //TRANSCOOPER = 441
                infoRH x = new infoRH(441, i, 2017, conn);

                texto += x.getLayout() + "\n";

            }

            SalvarArquivoTxt txt = new SalvarArquivoTxt(texto, "d:\\", "aaaaa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            conn.desconecta();
            conn.tela.dispose();
        }
    }

}
