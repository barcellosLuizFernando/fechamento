/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dirf;

import java.util.ArrayList;

/**
 *
 * @author luiz.barcellos
 */
public class PersonDirf {

    private final String nome;
    private final String registro;
    private final String cpfcnpj;
    private int janeiro;
    private int fevereiro;
    private int marco;
    private int abril;
    private int maio;
    private int junho;
    private int julho;
    private int agosto;
    private int setembro;
    private int outubro;
    private int novembro;
    private int dezembro;
    private int decimoterceirosalario;
    private ArrayList<Integer> retContribuicoes = new ArrayList();
    private ArrayList<Integer> retPIS = new ArrayList<Integer>();
    private ArrayList<Integer> retCOFINS = new ArrayList();
    private ArrayList<Integer> retCSLL = new ArrayList();
    private ArrayList<Integer> retIR = new ArrayList<Integer>();
    private ArrayList<Integer> rendIsentoComVinculo = new ArrayList<Integer>();
    private ArrayList<Integer> rendIsentoSemVinculo = new ArrayList<Integer>();
    private final String alimentando;
    private final String previdencia;
    private final String pessoaFJ;
    private final String dataMolestiaGrave = "";
    private String idRec;
    private String layout = "";
    private String layoutIRRF;
    private String layoutCSLL;
    private String layoutContribuicoes;
    private String layoutPIS;
    private String layoutCOFINS;
    private String layoutPFcomVinculo;
    private String layoutPFsemVinculo;

    public PersonDirf(String nome, String registro, String cpfcnpj,
            String alimentando, String previdencia, String pessoaFJ,
            String idRec) {

        for (int i = 0; i < 14; i++) {
            retCOFINS.add(0);
            retCSLL.add(0);
            retContribuicoes.add(0);
            retIR.add(0);
            retPIS.add(0);
            rendIsentoComVinculo.add(0);
            rendIsentoSemVinculo.add(0);

        }

        this.nome = nome;
        this.registro = registro.replace(" ", "");
        this.cpfcnpj = cpfcnpj;
        this.alimentando = alimentando;
        this.previdencia = previdencia;
        this.pessoaFJ = pessoaFJ;
        this.idRec = idRec;

        System.out.println("Iniciando " + nome);

    }

    public void getLayoutDefault() {

        buildDetails();

    }

    public String getLayoutPFVinculada() {
        return layoutPFcomVinculo;
    }

    public String getLayoutPFDesvinculada() {
        return layoutPFsemVinculo;
    }

    public String getLayoutIRRF() {

        return layoutIRRF;
    }

    public String getLayoutCSLL() {

        return layoutCSLL;
    }

    public String getLayoutContribuicoes() {

        return layoutContribuicoes;
    }

    public String getLayoutPIS() {

        return layoutPIS;
    }

    public String getLayoutCOFINS() {

        return layoutCOFINS;
    }

    private int sumArrays(ArrayList<Integer> x) {

        int sum = 0;

        for (Integer i : x) {
            sum += i;
        }

        //System.out.println("Soma de " + x + ": " + sum);
        return sum;
    }

    private void buildDetails() {

        boolean ir;
        boolean csll;
        boolean pis;
        boolean cofins;
        boolean contribuicoes;
        boolean comVinculo;
        boolean semVinculo;

        if (sumArrays(retIR) > 0) {
            ir = true;
        } else {
            ir = false;
        }
        if (sumArrays(retContribuicoes) > 0) {
            contribuicoes = true;
        } else {
            contribuicoes = false;
        }
        if (sumArrays(retPIS) > 0) {
            pis = true;
        } else {
            pis = false;
        }
        if (sumArrays(retCOFINS) > 0) {
            cofins = true;
        } else {
            cofins = false;
        }
        if (sumArrays(retCSLL) > 0) {
            csll = true;
        } else {
            csll = false;
        }

        comVinculo = sumArrays(rendIsentoComVinculo) > 0;
        semVinculo = sumArrays(rendIsentoSemVinculo) > 0;

        if (ir || pis || cofins || csll || contribuicoes) {
            if (ir) {
                this.idRec = "1708";
                buildHeaderDetails();
                buildDetails(retIR);
            }
            if (pis) {
                buildHeaderDetails();
                buildDetails(retPIS);
            }
            if (cofins) {
                buildHeaderDetails();
                buildDetails(retCOFINS);
            }
            if (csll) {
                buildHeaderDetails();
                buildDetails(retCSLL);
            }
            if (contribuicoes) {
                this.idRec = "5952";
                buildHeaderDetails();
                buildDetails(retContribuicoes);
            }
            if (comVinculo) {
                this.idRec = "0561";
                buildHeaderDetails();
                buildDetails(rendIsentoComVinculo);
            }
            if (semVinculo) {
                this.idRec = "0588";
                buildHeaderDetails();
                buildDetails(rendIsentoSemVinculo);
            }

        } else {
            buildHeaderDetails();
        }
    }

    private void buildHeaderDetails() {
        String typePersonLayout = "";

        if ("F".equals(pessoaFJ)) {
            typePersonLayout = "BPFDEC";
        } else {
            typePersonLayout = "BPJDEC";
        }

        layout = "IDREC|" + idRec + "|\n";
        layout += typePersonLayout + "|" + cpfcnpj + "|" + nome + "|";

        if ("F".equals(pessoaFJ)) {
            layout += dataMolestiaGrave + "|" + alimentando + "|" + previdencia + "|";
        }

        layout += "\n" + registro + "|" + janeiro + "|" + fevereiro + "|" + marco + "|"
                + abril + "|" + maio + "|" + junho + "|" + julho + "|"
                + agosto + "|" + setembro + "|" + outubro + "|" + novembro + "|"
                + dezembro + "|" + decimoterceirosalario + "|";

        if (idRec.equals("0561")) {
            layoutPFcomVinculo = layout;
        } else if (idRec.equals("0588")) {
            layoutPFsemVinculo = layout;
        }

    }

    private void buildDetails(ArrayList<Integer> x) {

        layout += "\nRTIRF|" + x.get(1) + "|" + x.get(2) + "|" + x.get(3) + "|"
                + x.get(4) + "|" + x.get(5) + "|" + x.get(6) + "|" + x.get(7) + "|"
                + x.get(8) + "|" + x.get(9) + "|" + x.get(10) + "|" + x.get(11) + "|"
                + x.get(12) + "|" + x.get(13) + "|";

        if (idRec.equals("1708")) {
            layoutIRRF = layout;
        } else if (idRec.equals("5952")) {
            layoutContribuicoes = layout;
        }
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public String getRegistro() {
        return registro;
    }

    public void setValue(int month, String bc, String retencao, String codRec) throws Exception {

        if ((!"".equals(retencao) && !"000".equals(retencao) && !"0".equals(retencao))) {
            if (codRec.equals("")) {
                throw new UnsupportedOperationException("Você precisa informar um código de retenção para continuar.\n"
                        + "Valor informado para retenção: " + retencao + ". Mês: " + month + ". Pessoa: " + nome + ". Inscrição Federal: " + cpfcnpj + ".");
            } else {

                int counter = 0;

                if (codRec.equals("1708")) {
                    //System.out.println("Atribuindo IRRF. " + retencao);

                    for (Integer i : retIR) {

                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            retIR.set(month, i);

                        }

                        counter++;
                    }

                } else if (codRec.equals("5952")) {
                    //System.out.println("Atribuindo Contribuições. " + retencao);
                    for (Integer i : retContribuicoes) {
                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            retContribuicoes.set(month, i);
                        }

                        counter++;
                    }

                } else if (codRec.equals("5987")) {
                    //System.out.println("Atribuindo CSLL.");
                    for (Integer i : retCSLL) {
                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            retCSLL.set(month, i);
                        }

                        counter++;
                    }

                } else if (codRec.equals("5979")) {
                    //System.out.println("Atribuindo PIS.");
                    for (Integer i : retPIS) {
                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            retCSLL.set(month, i);
                        }

                        counter++;
                    }

                } else if (codRec.equals("5960")) {
                    //System.out.println("Atribuindo COFINS.");
                    for (Integer i : retCOFINS) {
                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            retCOFINS.set(month, i);
                        }

                        counter++;
                    }

                } else if (codRec.equals("0561")) {
                    System.out.println("Atribuindo COFINS.");
                    for (Integer i : rendIsentoComVinculo) {
                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            rendIsentoComVinculo.set(month, i);
                        }

                        counter++;
                        System.exit(0);
                    }

                } else if (codRec.equals("0588")) {
                    //System.out.println("Atribuindo COFINS.");
                    for (Integer i : rendIsentoSemVinculo) {
                        if (counter == month) {
                            i += Integer.parseInt(retencao);
                            rendIsentoSemVinculo.set(month, i);
                        }

                        counter++;
                    }

                } else {
                    throw new UnsupportedOperationException("Código de receita " + codRec + " não configurado.");
                }

            }
        }

        switch (month) {

            case 1:
                janeiro += Integer.parseInt(bc);
                break;
            case 2:
                fevereiro += Integer.parseInt(bc);
                break;
            case 3:
                marco += Integer.parseInt(bc);
                break;
            case 4:
                abril += Integer.parseInt(bc);
                break;
            case 5:
                maio += Integer.parseInt(bc);
                break;
            case 6:
                junho += Integer.parseInt(bc);
                break;
            case 7:
                julho += Integer.parseInt(bc);
                break;
            case 8:
                agosto += Integer.parseInt(bc);
                break;
            case 9:
                setembro += Integer.parseInt(bc);
                break;
            case 10:
                outubro += Integer.parseInt(bc);
                break;
            case 11:
                novembro += Integer.parseInt(bc);
                break;
            case 12:
                dezembro += Integer.parseInt(bc);
                break;
        }
    }

}
