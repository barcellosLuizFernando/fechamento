/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import static tools.RemoveSpecialChar.removeSpecialChar;

/**
 *
 * @author luiz.barcellos
 */
public class Reg0200_Contas_Contabeis implements Exportacao_DomSis {

    private final String id = "0200";
    private String codigo = "";
    private String classificacao = "";
    private String tipo = "";
    private String descricao = "";
    private String dt_cadastro = "";
    private String situacao = "";
    private String dt_inativação = "";
    private String cod_rel_DLPA = "";
    private String cod_rel_DOAR = "";
    private String cod_rel_DRE = "";

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao.replaceAll("[^0-9]", "");
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = removeSpecialChar(descricao);
    }

    public void setDt_cadastro(String dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setDt_inativação(String dt_inativação) {
        this.dt_inativação = dt_inativação;
    }

    public void setCod_rel_DLPA(String cod_rel_DLPA) {
        this.cod_rel_DLPA = cod_rel_DLPA;
    }

    public void setCod_rel_DOAR(String cod_rel_DOAR) {
        this.cod_rel_DOAR = cod_rel_DOAR;
    }

    public void setCod_rel_DRE(String cod_rel_DRE) {
        this.cod_rel_DRE = cod_rel_DRE;
    }

    @Override
    public String getLayout() throws Exception {
        String linha = "|" + id + "|"
                + codigo + "|"
                + classificacao + "|"
                + tipo + "|"
                + descricao + "|"
                + dt_cadastro + "|"
                + situacao + "|"
                + dt_inativação + "|"
                + cod_rel_DLPA + "|"
                + cod_rel_DOAR + "|"
                + cod_rel_DRE + "|";

        System.out.println("Registro: " + linha);

        return linha;
    }

}
