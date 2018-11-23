/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author luiz.barcellos
 */
public class Reg0020_Cadastro_Fornecedores implements Exportacao_DomSis {

    private final String id = "0020";
    private String inscricao = ""; // CNPJ / CPF / CEI do cliente. Apenas números
    private String razao_social = "";
    private String apelido = "";
    private String endereco = "";
    private String numero = "";
    private String complemento = "";
    private String bairro = "";
    private String cod_mun = ""; //CÓDIGO ESTADUAL, FEDERAL OU IBGE/RAIS
    private String uf = "";
    private String cod_pais = "";
    private String cep = "";
    private String inscricao_estadual = "";
    private String inscricao_municipal = "";
    private String inscricao_suframa = "";
    private String ddd = "";
    private String telefone = "";
    private String fax = "";
    private String data_cadastro = ""; // dd/mm/aaaa
    private String conta_contabil = "";
    private String conta_cliente = "";
    private String agropecuário = ""; // INFORMAR S = SIM ou N - NÃO
    private String natureza_juridica = ""; // Informar 1=Órgão Público Federal, 2=Órgão Público Estadual, 3=Órgão Público Municipal, 4=Empresa Pública Federal, 5=Empresa Pública Estadual, 6=Empresa Pública Municipal, 7=Empresa Privada, 8=Sociedade Cooperativa ou 9=Empresa Privada-Fab. máq. veíc. e autopeças.
    private String regime_apuracao = ""; // Informar N=Normal, M=Microempresa, E=Empresa de pequeno porte ou O=Outros.
    private String contribuinte_icms = ""; // INFORMAR S = SIM ou N - NÃO
    private String aliquota_icms = ""; // Quando contribuinte do ICMS=Sim, informar a alíquota de ICMS aplicável ao cliente.
    private String categoria_estab = ""; // Apenas para scanc
    private String inscricao_estadual_st = "";
    private String interdependencia = ""; // INFORMAR S = SIM ou N - NÃO
    private String contribuinte_cprb = ""; // INFORMAR S = SIM ou N - NÃO

    @Override
    public String getLayout() throws IOException {

        String mensagem = "ERRO: ";

        if (inscricao.equals("")) {
            mensagem += "\nInformar número da inscrição federal do fornecedor " + razao_social + " para continuar.";
        }
        if (razao_social.equals("")) {
            mensagem += "\nInformar Razão Social do fornecedor " + inscricao + " para continuar.";
        }
        if (apelido.equals("")) {
            mensagem += "\nInformar apelido do fornecedor " + inscricao + " para continuar.";
        }
        if (endereco.equals("")) {
            mensagem += "\nInformar endereço do fornecedor " + inscricao + " para continuar.";
        }
        if (bairro.equals("")) {
            mensagem += "\nInformar bairro do fornecedor " + inscricao + " para continuar.";
        }
        if (cod_mun.equals("")) {
            mensagem += "\nInformar código do município do fornecedor " + inscricao + " para continuar.";
        }
        if (uf.equals("")) {
            mensagem += "\nInformar Unidade da Federação do fornecedor " + inscricao + " para continuar.";
        }
        if (cep.equals("")) {
            mensagem += "\nInformar CEP do fornecedor " + inscricao + " para continuar.";
        }
        if (data_cadastro.equals("")) {
            mensagem += "\nInformar data do cadastro do fornecedor " + inscricao + " para continuar.";
        }
        if (agropecuário.equals("")) {
            mensagem += "\nInformar condição agropecuária do fornecedor " + inscricao + " para continuar.";
        }
        if (natureza_juridica.equals("")) {
            mensagem += "\nInformar natureza jurídica do fornecedor " + inscricao + " para continuar.";
        }
        if (regime_apuracao.equals("")) {
            mensagem += "\nInformar regime de apuração do fornecedor " + inscricao + " para continuar.";
        }
        if (contribuinte_icms.equals("")) {
            mensagem += "\nInformar condição de contribuinte ICMS do fornecedor " + inscricao + " para continuar.";
        }
        if (interdependencia.equals("")) {
            mensagem += "\nInformar condição de interdependência do fornecedor " + inscricao + " para continuar.";
        }
        if (contribuinte_cprb.equals("")) {
            mensagem += "\nInformar condição de CPRB do fornecedor " + inscricao + " para continuar.";
        }

        if (!"ERRO: ".equals(mensagem)) {
            throw new UnsupportedOperationException(mensagem);
        } else {

            String linha = "|" + id + "|" + inscricao + "|" + razao_social + "|"
                    + apelido + "|" + endereco + "|" + numero + "|" + complemento
                    + "|" + bairro + "|" + cod_mun + "|" + uf + "|" + cod_pais + "|"
                    + cep + "|" + inscricao_estadual + "|" + inscricao_municipal + "|"
                    + inscricao_suframa + "|" + ddd + "|" + telefone + "|" + fax + "|"
                    + data_cadastro + "|" + conta_contabil + "|" + conta_cliente + "|"
                    + agropecuário + "|" + natureza_juridica + "|" + regime_apuracao + "|"
                    + contribuinte_icms + "|" + aliquota_icms + "|" + categoria_estab + "|"
                    + inscricao_estadual_st + "|" + interdependencia + "|" + contribuinte_cprb + "|";

            System.out.println("Registro: " + linha);

            return linha;
        }
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    /**
     * Número reduzido, máximo de 40 caracteres.
     *
     * @param apelido
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Será informado o código do município: estadual, federal ou IBGE/RAIS.
     *
     * @param cod_mun
     */
    public void setCod_mun(String cod_mun) {
        this.cod_mun = cod_mun;
    }

    /**
     * Quando for exterior, informar EX.
     *
     * @param uf
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Informar apenas quando for exterior, o código de cadastro do país.
     *
     * @param cod_pais
     */
    public void setCod_pais(String cod_pais) throws IOException {

        if (!uf.equals("EX")) {
            throw new UnsupportedOperationException("Não deve ser informado código do país.\nUnidade da federação é diferente de \"EX\".");
        } else {
            this.cod_pais = cod_pais;
        }
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public void setInscricao_municipal(String inscricao_municipal) {
        this.inscricao_municipal = inscricao_municipal;
    }

    public void setInscricao_suframa(String inscricao_suframa) {
        this.inscricao_suframa = inscricao_suframa;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public void setConta_contabil(String conta_contabil) {
        this.conta_contabil = conta_contabil;
    }

    /**
     * Informar a conta contábil como fornecedor, para quando ocorrer devolução
     * de vendas.
     *
     * @param conta_cliente
     */
    public void setConta_cliente(String conta_cliente) {
        this.conta_cliente = conta_cliente;
    }

    /**
     * Informar S=Sim ou N=Não.
     *
     * @param agropecuário
     */
    public void setAgropecuário(String agropecuário) {
        this.agropecuário = agropecuário;
    }

    /**
     * Informar 1=Órgão Público Federal, 2=Órgão Público Estadual, 3=Órgão
     * Público Municipal, 4=Empresa Pública Federal, 5=Empresa Pública Estadual,
     * 6=Empresa Pública Municipal, 7=Empresa Privada ou 8=Sociedade
     * Cooperativa.
     *
     * @param natureza_juridica
     */
    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    /**
     * Informar N=Normal, M=Microempresa , E=Empresa de pequeno porte ou
     * O=Outros.
     *
     * @param regime_apuracao
     */
    public void setRegime_apuracao(String regime_apuracao) {
        this.regime_apuracao = regime_apuracao;
    }

    /**
     * Informar S=Sim ou N=Não.
     *
     * @param contribuinte_icms
     */
    public void setContribuinte_icms(String contribuinte_icms) {
        this.contribuinte_icms = contribuinte_icms;
    }

    /**
     * Quando contribuinte do ICMS=Sim, informar a alíquota de ICMS aplicável ao
     * cliente.
     *
     * @param aliquota_icms
     */
    public void setAliquota_icms(String aliquota_icms) {
        this.aliquota_icms = aliquota_icms;
    }

    /**
     * Informar apenas se a empresa gera o informativo SCANC-CTB. Informar
     * ARM=Armazenador, CNF=Consumidor Final, CPQ=Central Petroquímica,
     * DIS=Distribuidor, FOR=Fornecedor, IMP=Importador, PRV=Posto Varejista,
     * REF=Refinaria, TRR=Transportador e Revendedor Retalhista, USI=Usina ou
     * VGL= Varejista de GLP.
     *
     * @param categoria_estab
     */
    public void setCategoria_estab(String categoria_estab) {
        this.categoria_estab = categoria_estab;
    }

    public void setInscricao_estadual_st(String inscricao_estadual_st) {
        this.inscricao_estadual_st = inscricao_estadual_st;
    }

    /**
     * Informar S=Sim ou N=Não.
     *
     * @param interdependencia
     */
    public void setInterdependencia(String interdependencia) {
        this.interdependencia = interdependencia;
    }

    /**
     * Informar S=Sim ou N=Não.
     *
     * @param contribuinte_cprb
     */
    public void setContribuinte_cprb(String contribuinte_cprb) {
        this.contribuinte_cprb = contribuinte_cprb;
    }

    public static void main(String[] args) {

        Reg0020_Cadastro_Fornecedores i = new Reg0020_Cadastro_Fornecedores();

        //i.setUf("EX");
        try {
            //i.setCod_pais("88");
            i.setInscricao("05528196000105");
            i.setRazao_social("COOPERATIVA AGROPECUARIA TRADICAO");
            i.setApelido("COOPER");
            i.setEndereco("aaaaaa");
            i.setBairro("bairro");
            i.setCod_mun("1111");
            i.setUf("PR");
            i.setCep("8550390");
            i.setData_cadastro("28/11/2017");
            i.setAgropecuário("S");
            i.setContribuinte_cprb("N");
            i.setInterdependencia("S");
            i.setNatureza_juridica("0");
            i.setRegime_apuracao("aaaa");
            i.setContribuinte_icms("S");
            i.getLayout();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
