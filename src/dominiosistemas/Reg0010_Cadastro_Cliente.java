/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg0010_Cadastro_Cliente extends Exception implements Exportacao_DomSis {

    String id = "0010";
    String inscricao = "";
    String razao_social = "";
    String apelido = ""; //MÁXIMO DE 40 CARACTERES
    String endereco = "";
    String numero = "";
    String complemento = "";
    String bairro = "";
    String cod_mun = ""; //CÓDIGO ESTADUAL, FEDERAL OU IBGE/RAIS
    String uf = "";
    String cod_pais = "";
    String cep = "";
    String inscricao_estadual = "";
    String inscricao_municipal = "";
    String inscricao_suframa = "";
    String ddd = "";
    String telefone = "";
    String fax = "";
    String data_cadastro = ""; // dd/mm/aaaa
    String conta_contabil = "";
    String conta_fornecedor = "";
    String agropecuário = ""; // INFORMAR S = SIM ou N - NÃO
    String natureza_juridica = ""; // Informar 1=Órgão Público Federal, 2=Órgão Público Estadual, 3=Órgão Público Municipal, 4=Empresa Pública Federal, 5=Empresa Pública Estadual, 6=Empresa Pública Municipal, 7=Empresa Privada, 8=Sociedade Cooperativa ou 9=Empresa Privada-Fab. máq. veíc. e autopeças.
    String regime_apuracao = ""; // Informar N=Normal, M=Microempresa, E=Empresa de pequeno porte ou O=Outros.
    String contribuinte_icms = ""; // INFORMAR S = SIM ou N - NÃO
    String aliquota_icms = ""; // Quando contribuinte do ICMS=Sim, informar a alíquota de ICMS aplicável ao cliente.
    String categoria_estab = ""; // Apenas para scanc
    String interdependencia = ""; // INFORMAR S = SIM ou N - NÃO
    String mt_perc_carga_media = ""; // Informar apenas se empresa for de MT
    String insc_prog_aquis_alim = ""; // INFORMAR S = SIM ou N - NÃO - Informar somente se no campo "AGROPECUÁRIO" estiver informado "S"

    public Reg0010_Cadastro_Cliente() {

        throw new UnsupportedOperationException("Classe não implementada.");

    }

    public static void main(String[] args) {
        Reg0010_Cadastro_Cliente i = new Reg0010_Cadastro_Cliente();

    }

    @Override
    public String getLayout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
