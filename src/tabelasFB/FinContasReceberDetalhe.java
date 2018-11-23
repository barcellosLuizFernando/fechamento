/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelasFB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
@Entity
@Table(name = "FIN_CONTAS_RECEBER_DETALHE")
@NamedQueries({
    @NamedQuery(name = "FinContasReceberDetalhe.findAll", query = "SELECT f FROM FinContasReceberDetalhe f")})
public class FinContasReceberDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FinContasReceberDetalhePK finContasReceberDetalhePK;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private double valor;
    @Column(name = "ID_TRANSACAO")
    private Integer idTransacao;
    @Basic(optional = false)
    @Column(name = "TIPO_PLANO")
    private String tipoPlano;
    @Column(name = "HISTORICO")
    private String historico;
    @Column(name = "TIPO_LCTO")
    private String tipoLcto;
    @Column(name = "AJ_CORRUPT")
    private Character ajCorrupt;
    @JoinColumns({
        @JoinColumn(name = "EMPRESA", referencedColumnName = "EMPRESA", insertable = false, updatable = false)
        , @JoinColumn(name = "CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FinContasReceber finContasReceber;

    public String getQueryInsert() throws Exception {
        
        if(null == getHistorico()){
            throw new Exception("Você deve informar um histórico para o lançamento.");
        }
        
        if(0.00 == getValor()){
            throw new Exception("Você deve informar um valor para o lançamento " + getHistorico());
        }
        
        if(null == getTipoPlano()){
            throw new Exception("Você deve informar se o valor é devedor ou credor o lançamento " + getHistorico());
        }
        
        if(null == getIdTransacao()){
            throw new Exception("Você deve informar o número da transação para o lançamento " + getHistorico());
        }
        

        String sql = "INSERT INTO FIN_CONTAS_RECEBER_DETALHE (EMPRESA, CODIGO, "
                + "VALOR, ID_TRANSACAO, TIPO_PLANO, HISTORICO, PLANOCONTA) "
                + "VALUES ('" + finContasReceberDetalhePK.getEmpresa() + "',"
                + "'" + finContasReceberDetalhePK.getCodigo() + "',"
                + "'" + getValor() + "','" + getIdTransacao() + "',"
                + "'" + getTipoPlano() + "','" + getHistorico() + "',"
                + "'" + finContasReceberDetalhePK.getPlanoconta() + "');";
        
        System.out.println(sql);

        return sql;
    }
    
    public static void main(String[] args) {
        try {
            
            FinContasReceberDetalhePK ff =  new FinContasReceberDetalhePK(Short.parseShort("1"), 234, 1572);
            FinContasReceberDetalhe f = new FinContasReceberDetalhe(ff, 654987.55, "C", "Histórico aosisodi", 4545878);
            
            //f.setHistorico("histórico teste");
            /*f.setValor(6548.87);
            f.setTipoPlano("C");*/
            //f.setIdTransacao(3126548);
            
            f.getQueryInsert();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public FinContasReceberDetalhe() {
    }

    public FinContasReceberDetalhe(FinContasReceberDetalhePK finContasReceberDetalhePK) {
        this.finContasReceberDetalhePK = finContasReceberDetalhePK;
    }

    public FinContasReceberDetalhe(FinContasReceberDetalhePK finContasReceberDetalhePK, double valor, String tipoPlano) {
        this.finContasReceberDetalhePK = finContasReceberDetalhePK;
        this.valor = valor;
        this.tipoPlano = tipoPlano;
    }
    
    public FinContasReceberDetalhe(FinContasReceberDetalhePK finContasReceberDetalhePK, double valor, String tipoPlano, String historico, int idTransacao) {
        this.finContasReceberDetalhePK = finContasReceberDetalhePK;
        this.valor = valor;
        this.tipoPlano = tipoPlano;
        this.historico = historico;
        this.idTransacao = idTransacao;
    }

    public FinContasReceberDetalhe(short empresa, int codigo, int planoconta) {
        this.finContasReceberDetalhePK = new FinContasReceberDetalhePK(empresa, codigo, planoconta);
    }

    public FinContasReceberDetalhePK getFinContasReceberDetalhePK() {
        return finContasReceberDetalhePK;
    }

    public void setFinContasReceberDetalhePK(FinContasReceberDetalhePK finContasReceberDetalhePK) {
        this.finContasReceberDetalhePK = finContasReceberDetalhePK;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getTipoLcto() {
        return tipoLcto;
    }

    public void setTipoLcto(String tipoLcto) {
        this.tipoLcto = tipoLcto;
    }

    public Character getAjCorrupt() {
        return ajCorrupt;
    }

    public void setAjCorrupt(Character ajCorrupt) {
        this.ajCorrupt = ajCorrupt;
    }

    public FinContasReceber getFinContasReceber() {
        return finContasReceber;
    }

    public void setFinContasReceber(FinContasReceber finContasReceber) {
        this.finContasReceber = finContasReceber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (finContasReceberDetalhePK != null ? finContasReceberDetalhePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContasReceberDetalhe)) {
            return false;
        }
        FinContasReceberDetalhe other = (FinContasReceberDetalhe) object;
        if ((this.finContasReceberDetalhePK == null && other.finContasReceberDetalhePK != null) || (this.finContasReceberDetalhePK != null && !this.finContasReceberDetalhePK.equals(other.finContasReceberDetalhePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ferramentas.FinContasReceberDetalhe[ finContasReceberDetalhePK=" + finContasReceberDetalhePK + " ]";
    }

}
