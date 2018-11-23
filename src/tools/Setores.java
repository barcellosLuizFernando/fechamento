package tools;

import view.TelaPrincipal;
import conn.ConexaoFB;
import conn.ConexaoMySQL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Luiz Fernando Dill Barcellos
 */
public class Setores {

    private ConexaoMySQL cn;
    private final conn.ConexaoFB cnfb = new ConexaoFB();

    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    private String empresa, codigo, nomeSetor, classificacao, tipo, resRecInt,
            resRecExt, resOutRec, resBonTerc, resIcmsTerc, resPisTerc, resCofinsTerc,
            resIssTerc, resIpiTerc, resDevTerc, resCustoTerc, resIngInt, resIngExt,
            resOutIng, resBonCoop, resIcmsCoop, resPisCoop, resCofinsCoop,
            resIssCoop, resIpiCoop, resDevCoop, resRepasse, controlaCmv, resRecTot, resIngTot;
    private String estTot, estIni, estCompCoop, estCompTerc, estDescarte,
            estProvAq, estTransf_E, estRem, estPerda, estDevCoop, estDevTerc,
            estCustoCoop, estCustoTerc, estTransf_S, estBonCoop, estBonTerc,
            estTransfProd, estConsInt, estProdAcabado, estTransfTercE, estTransfTercS;
    private String estTercTot, estTercFix, estTercFixDps, estTerc, estTercDPS,
            estTercCons;

    private String estPropTot, estPropFut, estPropConOr;

    private String prod_tot, prod_mp, prod_embalagem, prod_insumos, prod_cf,
            prod_transf_e, prod_transf_s, prod_acabado;

    //*********************************
    private Double v_resRecInt = 0.00, v_resRecExt = 0.00, v_resOutRec = 0.00, v_resBonTerc = 0.00,
            v_resIcmsTerc = 0.00, v_resPisTerc = 0.00, v_resCofinsTerc = 0.00, v_resIssTerc = 0.00,
            v_resIpiTerc = 0.00, v_resDevTerc = 0.00, v_resCustoTerc = 0.00, v_resIngInt = 0.00, v_resIngExt = 0.00,
            v_resOutIng = 0.00, v_resBonCoop = 0.00, v_resIcmsCoop = 0.00, v_resPisCoop = 0.00, v_resCofinsCoop = 0.00,
            v_resIssCoop = 0.00, v_resIpiCoop = 0.00, v_resDevCoop = 0.00, v_resRepasse = 0.00, v_estTot = 0.00,
            v_estIni = 0.00, v_estCompCoop = 0.00, v_estCompTerc = 0.00, v_estDescarte = 0.00, v_estProvAq = 0.00,
            v_estTransf_E = 0.00, v_estRem = 0.00, v_estPerda = 0.00, v_estDevCoop = 0.00, v_estDevTerc = 0.00,
            v_estCustoCoop = 0.00, v_estCustoTerc = 0.00, v_estTransf_S = 0.00, v_estBonCoop = 0.00, v_estBonTerc = 0.00,
            v_estTransfProd = 0.00, v_estConsInt = 0.00, v_estProdAcabado = 0.00, v_estTercTot = 0.00,
            v_estTercFix = 0.00, v_estTercFixDps = 0.00, v_estTerc = 0.00, v_estTercDPS = 0.00, v_estTercCons = 0.00,
            v_estPropTot = 0.00, v_estPropFut = 0.00, v_estPropConOr = 0.00, v_prod_tot = 0.00, v_prod_mp = 0.00,
            v_prod_embalagem = 0.00, v_prod_insumos = 0.00, v_prod_cf = 0.00, v_prod_transf_e = 0.00,
            v_prod_transf_s = 0.00, v_prod_acabado = 0.00, v_estTransfTercE = 0.00, v_estTransfTercS = 0.00,
            v_resRecTot = 0.00, v_resIngTot = 0.00;
    //*****************************

    private String sql;

    private boolean resposta, cadastrado, producao;

    private final ArrayList codigos = new ArrayList();
    private final DateFormat dateIn = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");
    
    public Setores(ConexaoMySQL conn){
        this.cn = conn;
    }

    public String getEstProdAcabado() {
        return estProdAcabado;
    }

    public void setEstProdAcabado(String estProdAcabado) {

        if ("".equals(estProdAcabado)) {
            this.estProdAcabado = null;
        } else {
            this.estProdAcabado = estProdAcabado;
        }

    }

    public String getProd_tot() {
        return prod_tot;
    }

    public String getResRecTot() {
        return resRecTot;
    }

    public void setResRecTot(String resRecTot) {
        if ("".equals(resRecTot)) {
            this.resRecTot = null;
        } else {
            this.resRecTot = resRecTot;
        }
    }

    public String getResIngTot() {
        return resIngTot;
    }

    public void setResIngTot(String resIngTot) {
        if ("".equals(resIngTot)) {
            this.resIngTot = null;
        } else {
            this.resIngTot = resIngTot;
        }
    }

    public Double getV_resRecTot() {
        return v_resRecTot;
    }

    public Double getV_resIngTot() {
        return v_resIngTot;
    }

    public void setProd_tot(String prod_tot) {
        if ("".equals(prod_tot)) {
            this.prod_tot = null;
        } else {
            this.prod_tot = prod_tot;
        }
    }

    public String getEstTransfTercE() {
        return estTransfTercE;
    }

    public void setEstTransfTercE(String estTransfTercE) {
        if ("".equals(estTransfTercE)) {
            this.estTransfTercE = null;
        } else {
            this.estTransfTercE = estTransfTercE;
        }
    }

    public String getEstTransfTercS() {
        return estTransfTercS;
    }

    public void setEstTransfTercS(String estTransfTercS) {
        if ("".equals(estTransfTercS)) {
            this.estTransfTercS = null;
        } else {
            this.estTransfTercS = estTransfTercS;
        }
    }

    public String getProd_mp() {
        return prod_mp;
    }

    public void setProd_mp(String prod_mp) {
        if ("".equals(prod_mp)) {
            this.prod_mp = null;
        } else {
            this.prod_mp = prod_mp;
        }
    }

    public String getProd_embalagem() {
        return prod_embalagem;
    }

    public void setProd_embalagem(String prod_embalagem) {
        if ("".equals(prod_embalagem)) {
            this.prod_embalagem = null;

        } else {
            this.prod_embalagem = prod_embalagem;
        }
    }

    public String getProd_insumos() {
        return prod_insumos;
    }

    public void setProd_insumos(String prod_insumos) {
        if ("".equals(prod_insumos)) {
            this.prod_insumos = null;

        } else {
            this.prod_insumos = prod_insumos;
        }
    }

    public String getProd_cf() {
        return prod_cf;
    }

    public void setProd_cf(String prod_cf) {
        if ("".equals(prod_cf)) {
            this.prod_cf = null;
        } else {
            this.prod_cf = prod_cf;
        }
    }

    public String getProd_transf_e() {
        return prod_transf_e;
    }

    public void setProd_transf_e(String prod_transf_e) {
        if ("".equals(prod_transf_e)) {
            this.prod_transf_e = null;
        } else {
            this.prod_transf_e = prod_transf_e;
        }
    }

    public String getProd_transf_s() {
        return prod_transf_s;
    }

    public void setProd_transf_s(String prod_transf_s) {
        if ("".equals(prod_transf_s)) {
            this.prod_transf_s = null;

        } else {
            this.prod_transf_s = prod_transf_s;
        }
    }

    public String getProd_acabado() {
        return prod_acabado;
    }

    public void setProd_acabado(String prod_acabado) {
        if ("".equals(prod_acabado)) {
            this.prod_acabado = null;

        } else {
            this.prod_acabado = prod_acabado;
        }
    }

    public boolean isProducao() {
        return producao;
    }

    public void setProducao(boolean producao) {
        this.producao = producao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResRecInt() {
        return resRecInt;
    }

    public void setResRecInt(String resRecInt) {
        if ("".equals(resRecInt)) {
            this.resRecInt = null;
        } else {
            this.resRecInt = resRecInt;
        }
    }

    public String getResRecExt() {
        return resRecExt;
    }

    public void setResRecExt(String resRecExt) {
        if ("".equals(resRecExt)) {
            this.resRecExt = null;

        } else {
            this.resRecExt = resRecExt;
        }
    }

    public String getResOutRec() {
        return resOutRec;
    }

    public void setResOutRec(String resOutRec) {
        if ("".equals(resOutRec)) {
            this.resOutRec = null;
        } else {
            this.resOutRec = resOutRec;
        }
    }

    public String getResBonTerc() {
        return resBonTerc;
    }

    public void setResBonTerc(String resBonTerc) {
        if ("".equals(resBonTerc)) {
            this.resBonTerc = null;

        } else {
            this.resBonTerc = resBonTerc;
        }
    }

    public String getResIcmsTerc() {
        return resIcmsTerc;
    }

    public void setResIcmsTerc(String resIcmsTerc) {
        if ("".equals(resIcmsTerc)) {
            this.resIcmsTerc = null;

        } else {
            this.resIcmsTerc = resIcmsTerc;
        }
    }

    public String getResPisTerc() {
        return resPisTerc;
    }

    public void setResPisTerc(String resPisTerc) {
        if ("".equals(resPisTerc)) {
            this.resPisTerc = null;

        } else {
            this.resPisTerc = resPisTerc;
        }
    }

    public String getResCofinsTerc() {
        return resCofinsTerc;
    }

    public void setResCofinsTerc(String resCofinsTerc) {
        if ("".equals(resCofinsTerc)) {
            this.resCofinsTerc = null;

        } else {
            this.resCofinsTerc = resCofinsTerc;
        }
    }

    public String getResIssTerc() {
        return resIssTerc;
    }

    public void setResIssTerc(String resIssTerc) {
        if ("".equals(resIssTerc)) {
            this.resIssTerc = null;

        } else {
            this.resIssTerc = resIssTerc;
        }
    }

    public String getResIpiTerc() {
        return resIpiTerc;
    }

    public void setResIpiTerc(String resIpiTerc) {
        if ("".equals(resIpiTerc)) {
            this.resIpiTerc = null;

        } else {
            this.resIpiTerc = resIpiTerc;
        }
    }

    public String getResDevTerc() {
        return resDevTerc;
    }

    public void setResDevTerc(String resDevTerc) {
        if ("".equals(resDevTerc)) {
            this.resDevTerc = null;
        } else {
            this.resDevTerc = resDevTerc;
        }
    }

    public String getResCustoTerc() {
        return resCustoTerc;
    }

    public void setResCustoTerc(String resCustoTerc) {
        if ("".equals(resCustoTerc)) {
            this.resCustoTerc = null;

        } else {
            this.resCustoTerc = resCustoTerc;
        }
    }

    public String getResIngInt() {
        return resIngInt;
    }

    public void setResIngInt(String resIngInt) {
        if ("".equals(resIngInt)) {
            this.resIngInt = null;

        } else {
            this.resIngInt = resIngInt;
        }
    }

    public String getResIngExt() {
        return resIngExt;
    }

    public void setResIngExt(String resIngExt) {
        if ("".equals(resIngExt)) {
            this.resIngExt = null;

        } else {
            this.resIngExt = resIngExt;
        }
    }

    public String getResOutIng() {
        return resOutIng;
    }

    public void setResOutIng(String resOutIng) {
        if ("".equals(resOutIng)) {
            this.resOutIng = null;

        } else {
            this.resOutIng = resOutIng;
        }
    }

    public String getResBonCoop() {
        return resBonCoop;
    }

    public void setResBonCoop(String resBonCoop) {
        if ("".equals(resBonCoop)) {
            this.resBonCoop = null;

        } else {
            this.resBonCoop = resBonCoop;
        }
    }

    public String getResIcmsCoop() {
        return resIcmsCoop;
    }

    public void setResIcmsCoop(String resIcmsCoop) {
        if ("".equals(resIcmsCoop)) {
            this.resIcmsCoop = null;

        } else {
            this.resIcmsCoop = resIcmsCoop;
        }
    }

    public String getResPisCoop() {
        return resPisCoop;
    }

    public void setResPisCoop(String resPisCoop) {
        if ("".equals(resPisCoop)) {
            this.resPisCoop = null;

        } else {
            this.resPisCoop = resPisCoop;
        }
    }

    public String getResCofinsCoop() {
        return resCofinsCoop;
    }

    public void setResCofinsCoop(String resCofinsCoop) {
        if ("".equals(resCofinsCoop)) {
            this.resCofinsCoop = null;

        } else {
            this.resCofinsCoop = resCofinsCoop;
        }
    }

    public String getResIssCoop() {
        return resIssCoop;
    }

    public void setResIssCoop(String resIssCoop) {
        if ("".equals(resIssCoop)) {
            this.resIssCoop = null;

        } else {
            this.resIssCoop = resIssCoop;
        }
    }

    public String getResIpiCoop() {
        return resIpiCoop;
    }

    public void setResIpiCoop(String resIpiCoop) {
        if ("".equals(resIpiCoop)) {
            this.resIpiCoop = null;

        } else {
            this.resIpiCoop = resIpiCoop;
        }
    }

    public String getResDevCoop() {
        return resDevCoop;
    }

    public void setResDevCoop(String resDevCoop) {
        if ("".equals(resDevCoop)) {
            this.resDevCoop = null;

        } else {
            this.resDevCoop = resDevCoop;
        }
    }

    public String getResRepasse() {
        return resRepasse;
    }

    public void setResRepasse(String resRepasse) {
        if ("".equals(resRepasse)) {
            this.resRepasse = null;

        } else {
            this.resRepasse = resRepasse;
        }
    }

    public String getEstTot() {
        return estTot;
    }

    public void setEstTot(String estTot) {
        if ("".equals(estTot)) {
            this.estTot = null;

        } else {
            this.estTot = estTot;
        }
    }

    public String getEstIni() {
        return estIni;
    }

    public void setEstIni(String estIni) {
        if ("".equals(estIni)) {
            this.estIni = null;

        } else {
            this.estIni = estIni;
        }
    }

    public String getEstCompCoop() {
        return estCompCoop;
    }

    public void setEstCompCoop(String estCompCoop) {
        if ("".equals(estCompCoop)) {
            this.estCompCoop = null;

        } else {
            this.estCompCoop = estCompCoop;
        }
    }

    public String getEstCompTerc() {
        return estCompTerc;
    }

    public void setEstCompTerc(String estCompTerc) {
        if ("".equals(estCompTerc)) {
            this.estCompTerc = null;

        } else {
            this.estCompTerc = estCompTerc;
        }
    }

    public String getEstDescarte() {
        return estDescarte;
    }

    public void setEstDescarte(String estDescarte) {
        if ("".equals(estDescarte)) {
            this.estDescarte = null;

        } else {
            this.estDescarte = estDescarte;
        }
    }

    public String getEstProvAq() {
        return estProvAq;
    }

    public void setEstProvAq(String estProvAq) {
        if ("".equals(estProvAq)) {
            this.estProvAq = null;

        } else {
            this.estProvAq = estProvAq;
        }
    }

    public String getEstTransf_E() {
        return estTransf_E;
    }

    public void setEstTransf_E(String estTransf_E) {
        if ("".equals(estTransf_E)) {
            this.estTransf_E = null;

        } else {
            this.estTransf_E = estTransf_E;
        }
    }

    public String getEstRem() {
        return estRem;
    }

    public void setEstRem(String estRem) {
        if ("".equals(estRem)) {
            this.estRem = null;

        } else {
            this.estRem = estRem;
        }
    }

    public String getEstPerda() {
        return estPerda;
    }

    public void setEstPerda(String estPerda) {
        if ("".equals(estPerda)) {
            this.estPerda = null;

        } else {
            this.estPerda = estPerda;
        }
    }

    public String getEstDevCoop() {
        return estDevCoop;
    }

    public void setEstDevCoop(String estDevCoop) {
        if ("".equals(estDevCoop)) {
            this.estDevCoop = null;

        } else {
            this.estDevCoop = estDevCoop;
        }
    }

    public String getEstDevTerc() {
        return estDevTerc;
    }

    public void setEstDevTerc(String estDevTerc) {
        if ("".equals(estDevTerc)) {
            this.estDevTerc = null;

        } else {
            this.estDevTerc = estDevTerc;
        }
    }

    public String getEstCustoCoop() {
        return estCustoCoop;
    }

    public void setEstCustoCoop(String estCustoCoop) {
        if ("".equals(estCustoCoop)) {
            this.estCustoCoop = null;

        } else {
            this.estCustoCoop = estCustoCoop;
        }
    }

    public String getEstCustoTerc() {
        return estCustoTerc;
    }

    public void setEstCustoTerc(String estCustoTerc) {
        if ("".equals(estCustoTerc)) {
            this.estCustoTerc = null;

        } else {
            this.estCustoTerc = estCustoTerc;
        }
    }

    public String getEstTransf_S() {
        return estTransf_S;
    }

    public void setEstTransf_S(String estTransf_S) {
        if ("".equals(estTransf_S)) {
            this.estTransf_S = null;

        } else {
            this.estTransf_S = estTransf_S;
        }
    }

    public String getEstBonCoop() {
        return estBonCoop;
    }

    public void setEstBonCoop(String estBonCoop) {
        if ("".equals(estBonCoop)) {
            this.estBonCoop = null;

        } else {
            this.estBonCoop = estBonCoop;
        }
    }

    public String getEstBonTerc() {
        return estBonTerc;
    }

    public void setEstBonTerc(String estBonTerc) {
        if ("".equals(estBonTerc)) {
            this.estBonTerc = null;

        } else {
            this.estBonTerc = estBonTerc;
        }
    }

    public String getEstTransfProd() {
        return estTransfProd;
    }

    public void setEstTransfProd(String estTransfProd) {
        if ("".equals(estTransfProd)) {
            this.estTransfProd = null;

        } else {
            this.estTransfProd = estTransfProd;
        }
    }

    public String getEstConsInt() {
        return estConsInt;
    }

    public void setEstConsInt(String estConsInt) {
        if ("".equals(estConsInt)) {
            this.estConsInt = null;

        } else {
            this.estConsInt = estConsInt;
        }
    }

    public String getEstTercTot() {
        return estTercTot;
    }

    public void setEstTercTot(String estTercTot) {
        if ("".equals(estTercTot)) {
            this.estTercTot = null;

        } else {
            this.estTercTot = estTercTot;
        }
    }

    public String getEstTercFix() {
        return estTercFix;
    }

    public void setEstTercFix(String estTercFix) {
        if ("".equals(estTercFix)) {
            this.estTercFix = null;

        } else {
            this.estTercFix = estTercFix;
        }
    }

    public String getEstTercFixDps() {
        return estTercFixDps;
    }

    public void setEstTercFixDps(String estTercFixDps) {
        if ("".equals(estTercFixDps)) {
            this.estTercFixDps = null;

        } else {
            this.estTercFixDps = estTercFixDps;
        }
    }

    public String getEstTerc() {
        return estTerc;
    }

    public void setEstTerc(String estTerc) {
        if ("".equals(estTerc)) {
            this.estTerc = null;

        } else {
            this.estTerc = estTerc;
        }
    }

    public String getEstTercDPS() {
        return estTercDPS;
    }

    public void setEstTercDPS(String estTercDPS) {
        if ("".equals(estTercDPS)) {
            this.estTercDPS = null;

        } else {
            this.estTercDPS = estTercDPS;
        }
    }

    public String getEstTercCons() {
        return estTercCons;
    }

    public void setEstTercCons(String estTercCons) {
        if ("".equals(estTercCons)) {
            this.estTercCons = null;

        } else {
            this.estTercCons = estTercCons;
        }
    }

    public String getEstPropTot() {
        return estPropTot;
    }

    public void setEstPropTot(String estPropTot) {
        if ("".equals(estPropTot)) {
            this.estPropTot = null;

        } else {
            this.estPropTot = estPropTot;
        }
    }

    public String getEstPropFut() {
        return estPropFut;
    }

    public void setEstPropFut(String estPropFut) {
        if ("".equals(estPropFut)) {
            this.estPropFut = null;

        } else {
            this.estPropFut = estPropFut;
        }
    }

    public String getEstPropConOr() {
        return estPropConOr;
    }

    public void setEstPropConOr(String estPropConOr) {
        if ("".equals(estPropConOr)) {
            this.estPropConOr = null;

        } else {
            this.estPropConOr = estPropConOr;
        }
    }

    /**
     * Carrega todos os dados do setor. Busca as contas no cadastro de setor, e
     * carrega os valores relativos a cada conta.
     *
     * @param codigo Setor
     * @param empresa Empresa a ser pesquisada
     * @param completo True, busca os dados locais. False, complementa o
     * cadastro com dados do Firebird.
     * @param data Em qual momento está sendo analisado o cadastro.
     */
    public void carregaDados(int codigo, int empresa, boolean completo, String data) {
        System.out.println("Carregando dados consolidados.");
        carregaDados(codigo, empresa, completo);
        carregaBalancete(data, empresa + "");
    }

    public boolean carregaDados(int codigo, int empresa, boolean completo) {
        System.out.println("Carregando contas do Setor.");
        cadastrado = false;

        sql = "SELECT * FROM cad_setores WHERE empresa = '" + empresa + "' "
                + "AND id = '" + codigo + "';";

        if (cn.iniciarTransacao()) {
            try {
                resposta = cn.executeConsulta(sql);
                while (cn.rs.next()) {

                    if (completo) {
                        this.empresa = cn.rs.getString("empresa");
                        this.codigo = cn.rs.getString("id");
                        this.nomeSetor = cn.rs.getString("nome");
                        this.classificacao = cn.rs.getString("classificacao");
                        this.tipo = cn.rs.getString("tipo");
                        setControlaCmv(cn.rs.getString("controla_cmv"));
                    }

                    this.estTot = cn.rs.getString("est_tot");
                    this.estIni = cn.rs.getString("est_ini");
                    this.estCompCoop = cn.rs.getString("est_comp_coop");
                    this.estCompTerc = cn.rs.getString("est_comp_terc");
                    this.estDescarte = cn.rs.getString("est_descarte");
                    this.estProvAq = cn.rs.getString("est_prov_aq");
                    this.estTransf_E = cn.rs.getString("est_transf_e");
                    this.estRem = cn.rs.getString("est_rem");
                    this.estPerda = cn.rs.getString("est_perda");
                    this.estDevCoop = cn.rs.getString("est_dev_coop");
                    this.estDevTerc = cn.rs.getString("est_dev_terc");
                    this.estCustoCoop = cn.rs.getString("est_custo_coop");
                    this.estCustoTerc = cn.rs.getString("est_custo_terc");
                    this.estTransf_S = cn.rs.getString("est_transf_s");
                    this.estBonCoop = cn.rs.getString("est_bon_coop");
                    this.estBonTerc = cn.rs.getString("est_bon_terc");
                    this.estTransfProd = cn.rs.getString("est_transf_prod");
                    this.estConsInt = cn.rs.getString("est_cons_int");
                    this.estTercTot = cn.rs.getString("est_terc_tot");
                    this.estTercFix = cn.rs.getString("est_terc_fix");
                    this.estTercFixDps = cn.rs.getString("est_terc_fix_dps");
                    this.estTerc = cn.rs.getString("est_terc");
                    this.estTercDPS = cn.rs.getString("est_terc_dps");
                    this.estTercCons = cn.rs.getString("est_terc_cons");
                    this.estTercTot = cn.rs.getString("est_terc_tot");
                    this.estPropFut = cn.rs.getString("est_prop_fut");
                    this.estPropConOr = cn.rs.getString("est_prop_con_or");
                    this.estProdAcabado = cn.rs.getString("est_prod_acabado");
                    this.resRecInt = cn.rs.getString("res_rec_int");
                    this.resRecExt = cn.rs.getString("res_rec_ext");
                    this.resOutRec = cn.rs.getString("res_out_rec");
                    this.resBonTerc = cn.rs.getString("res_bon_terc");
                    this.resIcmsTerc = cn.rs.getString("res_icms_terc");
                    this.resPisTerc = cn.rs.getString("res_pis_terc");
                    this.resCofinsTerc = cn.rs.getString("res_cofins_terc");
                    this.resIssTerc = cn.rs.getString("res_iss_terc");
                    this.resIpiTerc = cn.rs.getString("res_ipi_terc");
                    this.resDevTerc = cn.rs.getString("res_dev_terc");
                    this.resCustoTerc = cn.rs.getString("res_custo_terc");
                    this.resIngInt = cn.rs.getString("res_ing_int");
                    this.resIngExt = cn.rs.getString("res_ing_ext");
                    this.resOutIng = cn.rs.getString("res_out_ing");
                    this.resBonCoop = cn.rs.getString("res_bon_coop");
                    this.resIcmsCoop = cn.rs.getString("res_icms_coop");
                    this.resPisCoop = cn.rs.getString("res_pis_coop");
                    this.resCofinsCoop = cn.rs.getString("res_cofins_coop");
                    this.resIssCoop = cn.rs.getString("res_iss_coop");
                    this.resIpiCoop = cn.rs.getString("res_ipi_coop");
                    this.resDevCoop = cn.rs.getString("res_dev_coop");
                    this.resRepasse = cn.rs.getString("res_repasse");
                    this.prod_tot = cn.rs.getString("prod_tot");
                    this.prod_mp = cn.rs.getString("prod_mp");
                    this.prod_embalagem = cn.rs.getString("prod_embalagem");
                    this.prod_insumos = cn.rs.getString("prod_Insumos");
                    this.prod_cf = cn.rs.getString("prod_custo_fixo");
                    this.prod_transf_e = cn.rs.getString("prod_transf_e");
                    this.prod_transf_s = cn.rs.getString("prod_transf_s");
                    this.prod_acabado = cn.rs.getString("prod_acabado");
                    this.producao = cn.rs.getBoolean("producao");
                    this.estPropTot = cn.rs.getString("est_prop_tot");
                    this.estTransfTercE = cn.rs.getString("est_transf_terc_e");
                    this.estTransfTercS = cn.rs.getString("est_transf_terc_s");
                    this.resRecTot = cn.rs.getString("resRecTot");
                    this.resIngTot = cn.rs.getString("resIngTot");

                    cadastrado = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                resposta = false;
            } finally {
                cn.finalizarTransacao();
            }
        } else {
            resposta = false;
        }

        if (resposta) {
            System.out.println("Dados Carregados");
        } else {
            System.out.println("Dados não carregados");
        }

        return resposta;
    }

    private boolean atualizaDados() {

        sql = "UPDATE `fechamento`.`cad_setores` SET `empresa` = '" + empresa + "',"
                + "`id` = " + codigo + ",`nome` = '" + nomeSetor + "',"
                + "`classificacao` = '" + classificacao + "',`tipo` = '" + tipo + "',"
                + "`est_tot` = " + estTot + ",`est_ini` = " + estIni + ","
                + "`est_comp_coop` = " + estCompCoop + ", `est_comp_terc` = " + estCompTerc + ","
                + "`est_descarte` = " + estDescarte + ",`est_prov_aq` = " + estProvAq + ","
                + "`est_transf_e` = " + estTransf_E + ",`est_rem` = " + estRem + ","
                + "`est_perda` = " + estPerda + ",`est_dev_coop` = " + estDevCoop + ","
                + "`est_dev_terc` = " + estDevTerc + ",`est_custo_coop` = " + estCustoCoop + ","
                + "`est_custo_terc` = " + estCustoTerc + ",`est_transf_s` = " + estTransf_S + ","
                + "`est_bon_coop` = " + estBonCoop + ",`est_bon_terc` = " + estBonTerc + ","
                + "`est_transf_prod` = " + estTransfProd + ",`est_cons_int` = " + estConsInt + ","
                + "`est_terc_tot` = " + estTercTot + ",`est_terc_fix` = " + estTercFix + ","
                + "`est_terc_fix_dps` = " + estTercFixDps + ",`est_terc` = " + estTerc + ","
                + "`est_terc_dps` = " + estTercDPS + ",`est_terc_cons` = " + estTercCons + ","
                + "`est_prop_tot` = " + estPropTot + ",`est_prop_fut` = " + estPropFut + ","
                + "`est_prop_con_or` = " + estPropConOr + ",`est_prod_acabado` = " + estProdAcabado + ","
                + "`res_rec_int` = " + resRecInt + ",`res_rec_ext` = " + resRecExt + ","
                + "`res_out_rec` = " + resOutRec + ",`res_bon_terc` = " + resBonTerc + ","
                + "`res_icms_terc` = " + resIcmsTerc + ",`res_pis_terc` = " + resPisTerc + ","
                + "`res_cofins_terc` = " + resCofinsTerc + ",`res_iss_terc` = " + resIssTerc + ","
                + "`res_ipi_terc` = " + resIpiTerc + ",`res_dev_terc` = " + resDevTerc + ","
                + "`res_custo_terc` = " + resCustoTerc + ",`res_ing_int` = " + resIngInt + ","
                + "`res_ing_ext` = " + resIngExt + ",`res_out_ing` = " + resOutIng + ","
                + "`res_bon_coop` = " + resBonCoop + ",`res_icms_coop` = " + resIcmsCoop + ","
                + "`res_pis_coop` = " + resPisCoop + ",`res_cofins_coop` = " + resCofinsCoop + ","
                + "`res_iss_coop` = " + resIssCoop + ",`res_ipi_coop` = " + resIpiCoop + ","
                + "`res_dev_coop` = " + resDevCoop + ",`res_repasse` = " + resRepasse + ","
                + "`controla_cmv` = " + controlaCmv + ",`prod_tot` = " + prod_tot + ","
                + "`prod_mp` = " + prod_mp + ",`prod_embalagem` = " + prod_embalagem + ","
                + "`prod_insumos` = " + prod_insumos + ",`prod_custo_fixo` = " + prod_cf + ","
                + "`prod_transf_e` = " + prod_transf_e + ",`prod_transf_s` = " + prod_transf_s + ","
                + "`prod_acabado` = " + prod_acabado + ",`producao` = " + producao + ", "
                + "`est_transf_terc_e` = " + estTransfTercE + ", `est_transf_terc_s` = " + estTransfTercS + ", "
                + "`resRecTot` = " + resRecTot + ", `resIngTot` = " + resIngTot + " "
                + "WHERE `empresa` = " + empresa + " AND `id` = " + codigo + ";";

        if (cn.iniciarTransacao()) {
            try {
                resposta = cn.executeAtualizacao(sql);
            } catch (Exception e) {
                resposta = false;
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }
        return resposta;
    }

    private boolean insereDados() {

        sql = "INSERT INTO `fechamento`.`cad_setores` (`empresa`,`id`,`nome`,"
                + "`classificacao`,`tipo`,`est_tot`,`est_ini`,`est_comp_coop`,"
                + "`est_comp_terc`,`est_descarte`,`est_prov_aq`,`est_transf_e`,"
                + "`est_rem`,`est_perda`,`est_dev_coop`,`est_dev_terc`,"
                + "`est_custo_coop`,`est_custo_terc`,`est_transf_s`,"
                + "`est_bon_coop`,`est_bon_terc`,`est_transf_prod`,"
                + "`est_cons_int`,`est_terc_tot`,`est_terc_fix`,`est_terc_fix_dps`,"
                + "`est_terc`,`est_terc_dps`,`est_terc_cons`,`est_prop_tot`,"
                + "`est_prop_fut`,`est_prop_con_or`,`est_prod_acabado`,"
                + "`res_rec_int`,`res_rec_ext`,`res_out_rec`,`res_bon_terc`,"
                + "`res_icms_terc`,`res_pis_terc`,`res_cofins_terc`,`res_iss_terc`,"
                + "`res_ipi_terc`,`res_dev_terc`,`res_custo_terc`,`res_ing_int`,"
                + "`res_ing_ext`,`res_out_ing`,`res_bon_coop`,`res_icms_coop`,"
                + "`res_pis_coop`,`res_cofins_coop`,`res_iss_coop`,`res_ipi_coop`,"
                + "`res_dev_coop`,`res_repasse`,`controla_cmv`,`prod_tot`,"
                + "`prod_mp`,`prod_embalagem`,`prod_insumos`,`prod_custo_fixo`,"
                + "`prod_transf_e`,`prod_transf_s`,`prod_acabado`,`producao`,"
                + "`est_transf_terc_e`, `est_transf_terc_s`, `resRecTot`, `resIngTot`) VALUES "
                + "('" + empresa + "','" + codigo + "','" + nomeSetor + "',"
                + "'" + classificacao + "','" + tipo + "'," + estTot + ""
                + "," + estIni + "," + estCompCoop + "," + estCompTerc + ""
                + "," + estDescarte + "," + estProvAq + "," + estTransf_E + ""
                + "," + estRem + "," + estPerda + "," + estDevCoop + ""
                + "," + estDevTerc + "," + estDevCoop + "," + estCustoTerc + ""
                + "," + estTransf_S + "," + estBonCoop + "," + estBonTerc + ""
                + "," + estTransfProd + "," + estConsInt + "," + estTercTot + ""
                + "," + estTercFix + "," + estTercFixDps + "," + estTerc + ""
                + "," + estTercDPS + "," + estTercCons + "," + estPropTot + ""
                + "," + estPropFut + "," + estPropConOr + "," + estProdAcabado + ""
                + "," + resRecInt + "," + resRecExt + "," + resOutRec + ""
                + "," + resBonTerc + "," + resIcmsTerc + "," + resPisTerc + ""
                + "," + resCofinsTerc + "," + resIssTerc + "," + resIpiTerc + ""
                + "," + resDevTerc + "," + resCustoTerc + "," + resIngInt + ""
                + "," + resIngExt + "," + resOutIng + "," + resBonCoop + ""
                + "," + resIcmsCoop + "," + resPisCoop + "," + resCofinsCoop + ""
                + "," + resIssCoop + "," + resIpiCoop + "," + resDevCoop + ""
                + "," + resRepasse + "," + controlaCmv + "," + prod_tot + ""
                + "," + prod_mp + "," + prod_embalagem + "," + prod_insumos + ""
                + "," + prod_cf + "," + prod_transf_e + "," + prod_transf_s + ""
                + "," + prod_acabado + "," + producao + "," + estTransfTercE + ""
                + "," + estTransfTercS + "," + resRecTot + "," + resIngTot + ");";

        if (cn.iniciarTransacao()) {
            try {
                resposta = cn.executeAtualizacao(sql);

            } catch (Exception e) {
                resposta = false;
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }

        return resposta;
    }

    public boolean gravaDados() {

        if (cadastrado) {
            atualizaDados();
        } else {
            insereDados();
        }
        return resposta;
    }

    private boolean isResposta() {
        return resposta;
    }

    public String getControlaCmv() {
        return controlaCmv;
    }

    public boolean isCadastrado() {
        return cadastrado;
    }

    public void setControlaCmv(String controlaCmv) {

        System.out.println("Controla CMV: " + controlaCmv);

        if (controlaCmv == null | "".equals(controlaCmv)) {
            this.controlaCmv = "0";
        } else {
            this.controlaCmv = controlaCmv;
        }
    }

    public Double getV_resRecInt() {
        return v_resRecInt;
    }

    public Double getV_resRecExt() {
        return v_resRecExt;
    }

    public Double getV_resOutRec() {
        return v_resOutRec;
    }

    public Double getV_resBonTerc() {
        return v_resBonTerc;
    }

    public Double getV_resIcmsTerc() {
        return v_resIcmsTerc;
    }

    public Double getV_resPisTerc() {
        return v_resPisTerc;
    }

    public Double getV_resCofinsTerc() {
        return v_resCofinsTerc;
    }

    public Double getV_resIssTerc() {
        return v_resIssTerc;
    }

    public Double getV_resIpiTerc() {
        return v_resIpiTerc;
    }

    public Double getV_resDevTerc() {
        return v_resDevTerc;
    }

    public Double getV_resCustoTerc() {
        return v_resCustoTerc;
    }

    public Double getV_resIngInt() {
        return v_resIngInt;
    }

    public Double getV_resIngExt() {
        return v_resIngExt;
    }

    public Double getV_resOutIng() {
        return v_resOutIng;
    }

    public Double getV_resBonCoop() {
        return v_resBonCoop;
    }

    public Double getV_resIcmsCoop() {
        return v_resIcmsCoop;
    }

    public Double getV_resPisCoop() {
        return v_resPisCoop;
    }

    public Double getV_resCofinsCoop() {
        return v_resCofinsCoop;
    }

    public Double getV_resIssCoop() {
        return v_resIssCoop;
    }

    public Double getV_resIpiCoop() {
        return v_resIpiCoop;
    }

    public Double getV_resDevCoop() {
        return v_resDevCoop;
    }

    public Double getV_resRepasse() {
        return v_resRepasse;
    }

    public Double getV_estTot() {
        return v_estTot;
    }

    public Double getV_estIni() {
        return v_estIni;
    }

    public Double getV_estCompCoop() {
        return v_estCompCoop;
    }

    public Double getV_estCompTerc() {
        return v_estCompTerc;
    }

    public Double getV_estDescarte() {
        return v_estDescarte;
    }

    public Double getV_estProvAq() {
        return v_estProvAq;
    }

    public Double getV_estTransf_E() {
        return v_estTransf_E;
    }

    public Double getV_estRem() {
        return v_estRem;
    }

    public Double getV_estPerda() {
        return v_estPerda;
    }

    public Double getV_estDevCoop() {
        return v_estDevCoop;
    }

    public Double getV_estDevTerc() {
        return v_estDevTerc;
    }

    public Double getV_estCustoCoop() {
        return v_estCustoCoop;
    }

    public Double getV_estCustoTerc() {
        return v_estCustoTerc;
    }

    public Double getV_estTransf_S() {
        return v_estTransf_S;
    }

    public Double getV_estBonCoop() {
        return v_estBonCoop;
    }

    public Double getV_estBonTerc() {
        return v_estBonTerc;
    }

    public Double getV_estTransfProd() {
        return v_estTransfProd;
    }

    public Double getV_estConsInt() {
        return v_estConsInt;
    }

    public Double getV_estProdAcabado() {
        return v_estProdAcabado;
    }

    public Double getV_estTercTot() {
        return v_estTercTot;
    }

    public Double getV_estTercFix() {
        return v_estTercFix;
    }

    public Double getV_estTercFixDps() {
        return v_estTercFixDps;
    }

    public Double getV_estTerc() {
        return v_estTerc;
    }

    public Double getV_estTercDPS() {
        return v_estTercDPS;
    }

    public Double getV_estTercCons() {
        return v_estTercCons;
    }

    public Double getV_estPropTot() {
        return v_estPropTot;
    }

    public Double getV_estPropFut() {
        return v_estPropFut;
    }

    public Double getV_estPropConOr() {
        return v_estPropConOr;
    }

    public Double getV_prod_tot() {
        return v_prod_tot;
    }

    public Double getV_prod_mp() {
        return v_prod_mp;
    }

    public Double getV_prod_embalagem() {
        return v_prod_embalagem;
    }

    public Double getV_prod_insumos() {
        return v_prod_insumos;
    }

    public Double getV_prod_cf() {
        return v_prod_cf;
    }

    public Double getV_prod_transf_e() {
        return v_prod_transf_e;
    }

    public Double getV_prod_transf_s() {
        return v_prod_transf_s;
    }

    public Double getV_prod_acabado() {
        return v_prod_acabado;
    }

    public Double getV_estTransfTercE() {
        return v_estTransfTercE;
    }

    public Double getV_estTransfTercS() {
        return v_estTransfTercS;
    }

    public void carregaBalancete(String data, String empresa) {
        System.out.println("Carregando dados do balancete.");
        Date competencia = null;
        String conta;
        Double valor;

        try {
            competencia = dateIn.parse(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        sql = "SELECT * FROM balancete WHERE empresa = '" + empresa + "' "
                + "AND data_final = '" + dateOut.format(competencia) + "';";

        if (cn.iniciarTransacao()) {
            try {
                cn.executeConsulta(sql);
                while (cn.rs.next()) {
                    conta = cn.rs.getString("conta");
                    valor = cn.rs.getDouble("saldo_atual");

                    if (conta.equals(resRecInt)) {

                        v_resRecInt = valor;

                    } else if (conta.equals(resRecExt)) {

                        v_resRecExt = valor;

                    } else if (conta.equals(resOutRec)) {

                        v_resOutRec = valor;

                    } else if (conta.equals(resBonTerc)) {

                        v_resBonTerc = valor;

                    } else if (conta.equals(resIcmsTerc)) {

                        v_resIcmsTerc = valor;

                    } else if (conta.equals(resPisTerc)) {

                        v_resPisTerc = valor;

                    } else if (conta.equals(resCofinsTerc)) {

                        v_resCofinsTerc = valor;

                    } else if (conta.equals(resIssTerc)) {

                        v_resIssTerc = valor;

                    } else if (conta.equals(resIpiTerc)) {

                        v_resIpiTerc = valor;

                    } else if (conta.equals(resDevTerc)) {

                        v_resDevTerc = valor;

                    } else if (conta.equals(resCustoTerc)) {

                        v_resCustoTerc = valor;

                    } else if (conta.equals(resIngInt)) {

                        v_resIngInt = valor;

                    } else if (conta.equals(resIngExt)) {

                        v_resIngExt = valor;

                    } else if (conta.equals(resOutIng)) {

                        v_resOutIng = valor;

                    } else if (conta.equals(resBonCoop)) {

                        v_resBonCoop = valor;

                    } else if (conta.equals(resIcmsCoop)) {

                        v_resIcmsCoop = valor;

                    } else if (conta.equals(resPisCoop)) {

                        v_resPisCoop = valor;

                    } else if (conta.equals(resCofinsCoop)) {

                        v_resCofinsCoop = valor;

                    } else if (conta.equals(resIssCoop)) {

                        v_resIssCoop = valor;

                    } else if (conta.equals(resIpiCoop)) {

                        v_resIpiCoop = valor;

                    } else if (conta.equals(resDevCoop)) {

                        v_resDevCoop = valor;

                    } else if (conta.equals(resRepasse)) {

                        v_resRepasse = valor;

                    } else if (conta.equals(estTot)) {

                        v_estTot = valor;

                    } else if (conta.equals(estIni)) {

                        v_estIni = valor;

                    } else if (conta.equals(estCompCoop)) {

                        v_estCompCoop = valor;

                    } else if (conta.equals(estCompTerc)) {

                        v_estCompTerc = valor;

                    } else if (conta.equals(estDescarte)) {

                        v_estDescarte = valor;

                    } else if (conta.equals(estProvAq)) {

                        v_estProvAq = valor;

                    } else if (conta.equals(estTransf_E)) {

                        v_estTransf_E = valor;

                    } else if (conta.equals(estRem)) {

                        v_estRem = valor;

                    } else if (conta.equals(estPerda)) {

                        v_estPerda = valor;

                    } else if (conta.equals(estDevCoop)) {

                        v_estDevCoop = valor;

                    } else if (conta.equals(estDevTerc)) {

                        v_estDevTerc = valor;

                    } else if (conta.equals(estCustoCoop)) {

                        v_estCustoCoop = valor;

                    } else if (conta.equals(estCustoTerc)) {

                        v_estCustoTerc = valor;

                    } else if (conta.equals(estTransf_S)) {

                        v_estTransf_S = valor;

                    } else if (conta.equals(estBonCoop)) {

                        v_estBonCoop = valor;

                    } else if (conta.equals(estBonTerc)) {

                        v_estBonTerc = valor;

                    } else if (conta.equals(estTransfProd)) {

                        v_estTransfProd = valor;

                    } else if (conta.equals(estConsInt)) {

                        v_estConsInt = valor;

                    } else if (conta.equals(estProdAcabado)) {

                        v_estProdAcabado = valor;

                    } else if (conta.equals(estTercTot)) {

                        v_estTercTot = valor;

                    } else if (conta.equals(estTercFix)) {

                        v_estTercFix = valor;

                    } else if (conta.equals(estTercFixDps)) {

                        v_estTercFixDps = valor;

                    } else if (conta.equals(estTerc)) {

                        v_estTerc = valor;

                    } else if (conta.equals(estTercDPS)) {

                        v_estTercDPS = valor;

                    } else if (conta.equals(estTercCons)) {

                        v_estTercCons = valor;

                    } else if (conta.equals(estPropTot)) {

                        v_estPropTot = valor;

                    } else if (conta.equals(estPropFut)) {

                        v_estPropFut = valor;

                    } else if (conta.equals(estPropConOr)) {

                        v_estPropConOr = valor;

                    } else if (conta.equals(prod_tot)) {

                        v_prod_tot = valor;

                    } else if (conta.equals(prod_mp)) {

                        v_prod_mp = valor;

                    } else if (conta.equals(prod_embalagem)) {

                        v_prod_embalagem = valor;

                    } else if (conta.equals(prod_insumos)) {

                        v_prod_insumos = valor;

                    } else if (conta.equals(prod_cf)) {

                        v_prod_cf = valor;

                    } else if (conta.equals(prod_transf_e)) {

                        v_prod_transf_e = valor;

                    } else if (conta.equals(prod_transf_s)) {

                        v_prod_transf_s = valor;

                    } else if (conta.equals(prod_acabado)) {

                        v_prod_acabado = valor;

                    } else if (conta.equals(estTransfTercE)) {

                        v_estTransfTercE = valor;

                    } else if (conta.equals(estTransfTercS)) {

                        v_estTransfTercS = valor;

                    } else if (conta.equals(resRecTot)) {

                        v_resRecTot = valor;

                    } else if (conta.equals(resIngTot)) {

                        v_resIngTot = valor;
                        
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                cn.finalizarTransacao();
            }
        }

        System.out.print("Setor: " + nomeSetor);
        System.out.println(". Valor: " + df.format(v_estTot));
    }

//    public static void main(String[] args) {
//        Setores str = new Setores();
//        view.TelaPrincipal tp = new TelaPrincipal();
//        str.carregaDados(106, 1, true, "31/05/2017");
//        //str.carregaBalancete("31/05/2017", "1");
//        //str.setEmpresa("99");
//        //str.setEstTot("");
//
//        // str.setCadastrado(false);
//        //str.atualizaDados();
//        tp.dispose();
//
//        System.out.print("Classificação: " + str.getClassificacao());
//        System.out.println(". Nome: " + str.getNomeSetor());
//        System.out.println("Resposta da classe setor: " + str.isResposta());
//        System.out.println("Está cadastrado: " + str.isCadastrado());
//        System.out.println("Controla produção: " + str.isProducao());
//
//    }
}
