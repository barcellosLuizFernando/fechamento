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
public class FinContasReceberPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "EMPRESA")
    private short empresa;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private int codigo;

    public FinContasReceberPK() {
    }

    public FinContasReceberPK(short empresa, int codigo) {
        this.empresa = empresa;
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) empresa;
        hash += (int) codigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContasReceberPK)) {
            return false;
        }
        FinContasReceberPK other = (FinContasReceberPK) object;
        if (this.empresa != other.empresa) {
            return false;
        }
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ferramentas.FinContasReceberPK[ empresa=" + empresa + ", codigo=" + codigo + " ]";
    }
    
}
