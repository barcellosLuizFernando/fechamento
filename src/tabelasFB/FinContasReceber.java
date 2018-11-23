/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelasFB;

import tools.FormatNumbers;
import tools.ManipulaData;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luiz.barcellos
 */
@Entity
@Table(name = "FIN_CONTAS_RECEBER")
@NamedQueries({
    @NamedQuery(name = "FinContasReceber.findAll", query = "SELECT f FROM FinContasReceber f")})
public class FinContasReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FinContasReceberPK finContasReceberPK;
    @Basic(optional = false)
    @Column(name = "DOC")
    private String doc;
    @Basic(optional = false)
    @Column(name = "HISTORICO")
    private String historico;
    @Basic(optional = false)
    @Column(name = "DT_EMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dtEmissao;
    @Basic(optional = false)
    @Column(name = "DT_VENC")
    @Temporal(TemporalType.DATE)
    private Date dtVenc;
    @Column(name = "QUITADA")
    private Character quitada;
    @Column(name = "ATIVO")
    private Character ativo;
    @Basic(optional = false)
    @Column(name = "DATA_INC")
    @Temporal(TemporalType.DATE)
    private Date dataInc;
    @Column(name = "DATA_ALT")
    @Temporal(TemporalType.DATE)
    private Date dataAlt;
    @Column(name = "DATA_EXC")
    @Temporal(TemporalType.DATE)
    private Date dataExc;
    @Basic(optional = false)
    @Column(name = "HORA_INC")
    @Temporal(TemporalType.TIME)
    private Date horaInc;
    @Column(name = "HORA_ALT")
    @Temporal(TemporalType.TIME)
    private Date horaAlt;
    @Column(name = "HORA_EXC")
    @Temporal(TemporalType.TIME)
    private Date horaExc;
    @Column(name = "ID_TRANSACAO")
    private Integer idTransacao;
    @Column(name = "PARCELA")
    private Short parcela;
    @Column(name = "MES")
    private Short mes;
    @Column(name = "ANO")
    private Short ano;
    @Column(name = "ORIGEM_RENEGOCIACAO")
    private Character origemRenegociacao;
    @Column(name = "NRO_DUPLICATA")
    private Integer nroDuplicata;
    @Column(name = "PARCELA_DUPLICATA")
    private Short parcelaDuplicata;
    @Column(name = "CARTEIRA_NOSSO_NRO")
    private Integer carteiraNossoNro;
    @Column(name = "CARTEIRA_NOSSO_NRO_CALC")
    private String carteiraNossoNroCalc;
    @Column(name = "INTEGRA_CONTABIL")
    private Character integraContabil;
    @Column(name = "DT_PREVISAO_RECEB")
    @Temporal(TemporalType.DATE)
    private Date dtPrevisaoReceb;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PERC_JUROS_ATRASO")
    private Double percJurosAtraso;
    @Column(name = "PERC_MULTA_ATRASO")
    private Double percMultaAtraso;
    @Column(name = "PERC_DESC_ANTECIPACAO")
    private Double percDescAntecipacao;
    @Column(name = "TIPO_JUROS_ATRASO")
    private Character tipoJurosAtraso;
    @Column(name = "TIPO_DESC_ATRASO")
    private Character tipoDescAtraso;
    @Column(name = "VENDEDOR")
    private Integer vendedor;
    @Column(name = "PERC_PONTUALIDADE")
    private Double percPontualidade;
    @Column(name = "OLD_CODIGO")
    private Integer oldCodigo;
    @Column(name = "CLB_TIPO_MENSALIDADE")
    private Character clbTipoMensalidade;
    @Column(name = "OBS_PARCELA")
    private String obsParcela;
    @Column(name = "POS_MANUAL")
    private Character posManual;
    @Column(name = "CARTAO_NSU")
    private String cartaoNsu;
    @Column(name = "TIPODOC")
    private int tipoDoc;
    @Column(name = "USU_INC")
    private int usuInc;
    @Column(name = "ESTABELEC")
    private int estabelec;
    @Column(name = "CLIENTE")
    private int cliente;
    @Column(name = "PORTADOR")
    private int portador;
    @Column(name = "LOTE")
    private int lote;
    @Column(name = "CONTA_CLIENTE")
    private int contaCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "finContasReceber")
    private Collection<FinContasReceberDetalhe> finContasReceberDetalheCollection;

    public FinContasReceber() {
    }

    public FinContasReceber(FinContasReceberPK finContasReceberPK) {
        this.finContasReceberPK = finContasReceberPK;
    }

    public FinContasReceber(FinContasReceberPK finContasReceberPK, String doc, String historico, Date dtEmissao, Date dtVenc, Date dataInc, Date horaInc) {
        this.finContasReceberPK = finContasReceberPK;
        this.doc = doc;
        this.historico = historico;
        this.dtEmissao = dtEmissao;
        this.dtVenc = dtVenc;
        this.dataInc = dataInc;
        this.horaInc = horaInc;
    }

    public FinContasReceber(FinContasReceberPK finContasReceberPK, String doc,
            String historico, Date dtEmissao, Date dtVenc, int portador,
            Character integraContabil, int contaCliente, int cliente,
            int idTransacao, int estacelec, int usuInc, int tipoDoc) {
        this.finContasReceberPK = finContasReceberPK;
        this.doc = doc;
        this.historico = historico;
        this.dtEmissao = dtEmissao;
        this.dtVenc = dtVenc;
        this.portador = portador;
        this.integraContabil = integraContabil;
        this.contaCliente = contaCliente;
        this.cliente = cliente;
        this.idTransacao = idTransacao;
        this.estabelec = estacelec;
        this.usuInc = usuInc;
        this.tipoDoc = tipoDoc;
    }

    public String getQueryInsert() throws Exception {

        if (null == getHistorico()) {
            throw new Exception("Você precisa informar um histórico para gravar o lançamento.");
        }
        if (0 == getPortador()) {
            throw new Exception("Você precisa informar o portador para gravar o lançamento " + getHistorico());
        }
        if (null == getIntegraContabil()) {
            throw new Exception("Você precisa informar se há integração contábil para gravar o lançamento " + getHistorico());
        }
        /*if (0 == getContaCliente()) {
            throw new Exception("Você precisa informar uma conta alternativa para gravar o lançamento " + getHistorico());
        }*/
        if (null == getDoc()) {
            throw new Exception("Você precisa informar um número de documento para gravar o lançamento " + getHistorico());
        }
        if (0 == getCliente()) {
            throw new Exception("Você precisa informar um código de cliente para gravar o lançamento " + getHistorico());
        }
        if (null == getIdTransacao()) {
            throw new Exception("Você precisa informar uma transação para gravar o lançamento " + getHistorico());
        }
        if (0 == getEstabelec()) {
            throw new Exception("Você precisa informar um estabelecimento para gravar o lançamento " + getHistorico());
        }
        if (0 == getUsuInc()) {
            throw new Exception("Você precisa informar um usuário para gravar o lançamento " + getHistorico());
        }
        if (null == getDtVenc()) {
            throw new Exception("Você precisa informar uma data de vencimento para gravar o lançamento " + getHistorico());
        }
        if (null == getDtEmissao()) {
            throw new Exception("Você precisa informar uma data de emissão para gravar o lançamento " + getHistorico());
        }
        if (0 == getTipoDoc()) {
            throw new Exception("Você precisa informar um tipo de documento para gravar o lançamento " + getHistorico());
        }

        String sql = "INSERT INTO FIN_CONTAS_RECEBER (EMPRESA, CODIGO, TIPODOC, "
                + "DOC, HISTORICO, DT_EMISSAO, DT_VENC, DATA_INC, HORA_INC, "
                + "USU_INC, ID_TRANSACAO, ESTABELEC, CLIENTE, PORTADOR, "
                + "ESTABELEC_CAD, INTEGRA_CONTABIL ";

        if (0 != contaCliente) {
            sql += ", CONTA_CLIENTE ";
        }

        sql += ") VALUES ("
                + "'" + finContasReceberPK.getEmpresa() + "',"
                + "'" + finContasReceberPK.getCodigo() + "',"
                + "'" + getTipoDoc() + "','" + getDoc() + "',"
                + "'" + getHistorico() + "',"
                + "'" + tools.FormatNumbers.dateBrDefault(getDtEmissao()).replace("/", ".") + "',"
                + "'" + tools.FormatNumbers.dateBrDefault(getDtVenc()).replace("/", ".") + "',"
                + "current_date,current_time,"
                + "'" + getUsuInc() + "','" + getIdTransacao() + "',"
                + "'" + getEstabelec() + "','" + getCliente() + "',"
                + "'" + getPortador() + "',"
                + "'" + getEstabelec() + "',"
                + "'" + getIntegraContabil() + "'";

        if (0 != contaCliente) {
            sql += ",'" + getContaCliente() + "'";
        }

        sql += ");";

        System.out.println(sql);

        return sql;
    }

    public static void main(String[] args) {
        try {
            FinContasReceberPK ff = new FinContasReceberPK(Short.parseShort("1"), 54548);
            FinContasReceber f = new FinContasReceber(ff, "doc2938", "histórico de teste",
                    new java.sql.Date(117, 10, 30), new java.sql.Date(118, 00, 31), 1,
                    "S".charAt(0), 0, 2, 66556, 18, 167, 40);

            f.getQueryInsert();
        } catch (Exception ex) {
            Logger.getLogger(FinContasReceber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FinContasReceber(short empresa, int codigo) {
        this.finContasReceberPK = new FinContasReceberPK(empresa, codigo);
    }

    public FinContasReceberPK getFinContasReceberPK() {
        return finContasReceberPK;
    }

    public void setFinContasReceberPK(FinContasReceberPK finContasReceberPK) {
        this.finContasReceberPK = finContasReceberPK;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public int getUsuInc() {
        return usuInc;
    }

    public int getEstabelec() {
        return estabelec;
    }

    public int getCliente() {
        return cliente;
    }

    public int getPortador() {
        return portador;
    }

    public int getLote() {
        return lote;
    }

    public int getContaCliente() {
        return contaCliente;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public Date getDtVenc() {
        return dtVenc;
    }

    public void setDtVenc(Date dtVenc) {
        this.dtVenc = dtVenc;
    }

    public Character getQuitada() {
        return quitada;
    }

    public void setQuitada(Character quitada) {
        this.quitada = quitada;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Date getDataInc() {
        return dataInc;
    }

    public void setDataInc(Date dataInc) {
        this.dataInc = dataInc;
    }

    public Date getDataAlt() {
        return dataAlt;
    }

    public void setDataAlt(Date dataAlt) {
        this.dataAlt = dataAlt;
    }

    public Date getDataExc() {
        return dataExc;
    }

    public void setDataExc(Date dataExc) {
        this.dataExc = dataExc;
    }

    public Date getHoraInc() {
        return horaInc;
    }

    public void setHoraInc(Date horaInc) {
        this.horaInc = horaInc;
    }

    public Date getHoraAlt() {
        return horaAlt;
    }

    public void setHoraAlt(Date horaAlt) {
        this.horaAlt = horaAlt;
    }

    public Date getHoraExc() {
        return horaExc;
    }

    public void setHoraExc(Date horaExc) {
        this.horaExc = horaExc;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Short getParcela() {
        return parcela;
    }

    public void setParcela(Short parcela) {
        this.parcela = parcela;
    }

    public Short getMes() {
        return mes;
    }

    public void setMes(Short mes) {
        this.mes = mes;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public Character getOrigemRenegociacao() {
        return origemRenegociacao;
    }

    public void setOrigemRenegociacao(Character origemRenegociacao) {
        this.origemRenegociacao = origemRenegociacao;
    }

    public Integer getNroDuplicata() {
        return nroDuplicata;
    }

    public void setNroDuplicata(Integer nroDuplicata) {
        this.nroDuplicata = nroDuplicata;
    }

    public Short getParcelaDuplicata() {
        return parcelaDuplicata;
    }

    public void setParcelaDuplicata(Short parcelaDuplicata) {
        this.parcelaDuplicata = parcelaDuplicata;
    }

    public Integer getCarteiraNossoNro() {
        return carteiraNossoNro;
    }

    public void setCarteiraNossoNro(Integer carteiraNossoNro) {
        this.carteiraNossoNro = carteiraNossoNro;
    }

    public String getCarteiraNossoNroCalc() {
        return carteiraNossoNroCalc;
    }

    public void setCarteiraNossoNroCalc(String carteiraNossoNroCalc) {
        this.carteiraNossoNroCalc = carteiraNossoNroCalc;
    }

    public Character getIntegraContabil() {
        return integraContabil;
    }

    public void setIntegraContabil(Character integraContabil) {
        this.integraContabil = integraContabil;
    }

    public Date getDtPrevisaoReceb() {
        return dtPrevisaoReceb;
    }

    public void setDtPrevisaoReceb(Date dtPrevisaoReceb) {
        this.dtPrevisaoReceb = dtPrevisaoReceb;
    }

    public Double getPercJurosAtraso() {
        return percJurosAtraso;
    }

    public void setPercJurosAtraso(Double percJurosAtraso) {
        this.percJurosAtraso = percJurosAtraso;
    }

    public Double getPercMultaAtraso() {
        return percMultaAtraso;
    }

    public void setPercMultaAtraso(Double percMultaAtraso) {
        this.percMultaAtraso = percMultaAtraso;
    }

    public Double getPercDescAntecipacao() {
        return percDescAntecipacao;
    }

    public void setPercDescAntecipacao(Double percDescAntecipacao) {
        this.percDescAntecipacao = percDescAntecipacao;
    }

    public Character getTipoJurosAtraso() {
        return tipoJurosAtraso;
    }

    public void setTipoJurosAtraso(Character tipoJurosAtraso) {
        this.tipoJurosAtraso = tipoJurosAtraso;
    }

    public Character getTipoDescAtraso() {
        return tipoDescAtraso;
    }

    public void setTipoDescAtraso(Character tipoDescAtraso) {
        this.tipoDescAtraso = tipoDescAtraso;
    }

    public Integer getVendedor() {
        return vendedor;
    }

    public void setVendedor(Integer vendedor) {
        this.vendedor = vendedor;
    }

    public Double getPercPontualidade() {
        return percPontualidade;
    }

    public void setPercPontualidade(Double percPontualidade) {
        this.percPontualidade = percPontualidade;
    }

    public Integer getOldCodigo() {
        return oldCodigo;
    }

    public void setOldCodigo(Integer oldCodigo) {
        this.oldCodigo = oldCodigo;
    }

    public Character getClbTipoMensalidade() {
        return clbTipoMensalidade;
    }

    public void setClbTipoMensalidade(Character clbTipoMensalidade) {
        this.clbTipoMensalidade = clbTipoMensalidade;
    }

    public String getObsParcela() {
        return obsParcela;
    }

    public void setObsParcela(String obsParcela) {
        this.obsParcela = obsParcela;
    }

    public Character getPosManual() {
        return posManual;
    }

    public void setPosManual(Character posManual) {
        this.posManual = posManual;
    }

    public String getCartaoNsu() {
        return cartaoNsu;
    }

    public void setCartaoNsu(String cartaoNsu) {
        this.cartaoNsu = cartaoNsu;
    }

    public Collection<FinContasReceberDetalhe> getFinContasReceberDetalheCollection() {
        return finContasReceberDetalheCollection;
    }

    public void setFinContasReceberDetalheCollection(Collection<FinContasReceberDetalhe> finContasReceberDetalheCollection) {
        this.finContasReceberDetalheCollection = finContasReceberDetalheCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (finContasReceberPK != null ? finContasReceberPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContasReceber)) {
            return false;
        }
        FinContasReceber other = (FinContasReceber) object;
        if ((this.finContasReceberPK == null && other.finContasReceberPK != null) || (this.finContasReceberPK != null && !this.finContasReceberPK.equals(other.finContasReceberPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ferramentas.FinContasReceber[ finContasReceberPK=" + finContasReceberPK + " ]";
    }

}
