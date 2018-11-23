/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelasFB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author luiz.barcellos
 */
@Embeddable
public class FinContasReceberDetalhePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "EMPRESA")
    private short empresa;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private int codigo;
    @Basic(optional = false)
    @Column(name = "PLANOCONTA")
    private int planoconta;

    public FinContasReceberDetalhePK() {
    }

    public FinContasReceberDetalhePK(short empresa, int codigo, int planoconta) {
        this.empresa = empresa;
        this.codigo = codigo;
        this.planoconta = planoconta;
    }

    public short getEmpresa() {
        return empresa;
    }

    public void setEmpresa(short empresa) {
        this.empresa = empresa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPlanoconta() {
        return planoconta;
    }

    public void setPlanoconta(int planoconta) {
        this.planoconta = planoconta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empresa;
        hash += (int) codigo;
        hash += (int) planoconta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContasReceberDetalhePK)) {
            return false;
        }
        FinContasReceberDetalhePK other = (FinContasReceberDetalhePK) object;
        if (this.empresa != other.empresa) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.planoconta != other.planoconta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ferramentas.FinContasReceberDetalhePK[ empresa=" + empresa + ", codigo=" + codigo + ", planoconta=" + planoconta + " ]";
    }
    
}
