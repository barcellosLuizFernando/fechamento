/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoMySQL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ferna
 */
public class FinContratosJuros {

    private ConexaoMySQL cn;
    private final ManipulaData mData = new ManipulaData();
    private final DateFormat dateOut = new SimpleDateFormat("yyyy/MM/dd");

    private Double saldoInicial, taxaAnual, taxaMensal,
            taxaDiaria, prazo, saldoFinal, jurosAcumulados, amortizacao;
    private String sql;

    public FinContratosJuros(ConexaoMySQL conn) {
        this.cn = conn;
    }

    public void calculaContrato(String contrato, String fornecedor, String competencia, ConexaoMySQL conn) throws Exception {

        if (conn == null) {
            cn = new ConexaoMySQL();
            cn.conecta("consultas", "consultas");
        } else {
            this.cn = conn;
        }

        taxaAnual = taxaMensal = taxaDiaria = prazo = amortizacao
                = saldoFinal = saldoInicial = jurosAcumulados = 0.00;

        Date dataInicial = mData.firstDay(competencia);
        Date dataFinal = mData.lastDay(competencia);
        Date emissao = null;
        int dias = 0;
        int dia1 = 0;
        int dia2 = 0;

        sql = "call fechamento.fin_endividamento('" + dateOut.format(mData.lastDay(competencia)) + "');";

        try {
            cn.executeAtualizacao(sql);
            sql = "SELECT * FROM tmp_endividamento "
                    + "WHERE numero = '" + contrato + "' "
                    + "AND fornecedor = '" + fornecedor + "'; ";
            cn.executeConsulta(sql);
            while (cn.rs.next()) {
                saldoInicial = cn.rs.getDouble("saldo_anterior");

                if (saldoInicial == 0.00) {
                    saldoInicial = cn.rs.getDouble("liberacoes");
                }

                System.out.println("\nSaldo Inicial: " + tools.FormatNumbers.numUsToBr(saldoInicial));
            }
            cn.rs.close();

            sql = "SELECT min(liberacao) as data, avg(taxa) as taxa "
                    + "from fin_contratos "
                    + "where numero = '" + contrato + "' "
                    + "AND fornecedor = '" + fornecedor + "'; ";
            cn.executeConsulta(sql);
            while (cn.rs.next()) {
                emissao = cn.rs.getDate("data");
                this.taxaAnual = cn.rs.getDouble("taxa");
            }
            cn.rs.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível calcular o contrato " + contrato + ".\n" + e);
        }

        ajustaTaxa(taxaAnual);

        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();

        data1.setTime(emissao);
        data2.setTime(dataFinal);

        if (data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR)) {

            dias = data2.get(Calendar.DAY_OF_MONTH) - data1.get(Calendar.DAY_OF_MONTH);
            System.out.println("\nCalculando proporcional aos dias.");
            calculaJuros(dias, taxaDiaria, saldoInicial);
        } else {
            dias = 1;
            System.out.println("Calculando o mês inteiro.");
            calculaJuros(dias, taxaMensal, saldoInicial);
        }

        System.out.println("Fim do procedimento para o contrato: " + contrato);

        if (conn == null) {
            try {
                cn.desconecta();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    private double calculaJuros(int dias, double taxa, double capital) {

        System.out.println("\nCapital: " + tools.FormatNumbers.numUsToBr(capital));
        System.out.println("Dias: " + dias);
        System.out.println("Taxa: " + taxa);

        saldoFinal = capital * Math.pow(1.00 + taxa, dias);
        jurosAcumulados += saldoFinal - capital;
        System.out.println("Saldo Final: " + tools.FormatNumbers.numUsToBr(saldoFinal));
        System.out.println("Juros Acumulados: " + tools.FormatNumbers.numUsToBr(jurosAcumulados));

        return jurosAcumulados;
    }

    private void ajustaTaxa(double taxa) {

        this.taxaAnual = taxa / 100.00;
        System.out.println("\nTaxa anual: " + taxaAnual * 100.00);

        this.taxaMensal = Math.pow((1.00 + taxaAnual), (1.00 / 12.00)) - 1.00;
        System.out.println("Taxa mensal: " + (taxaMensal * 100.00));

        this.taxaDiaria = Math.pow((1.00 + taxaAnual), (1.00 / 360.00)) - 1.00;
        System.out.println("Taxa Diária: " + (taxaDiaria * 100.00));

    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public Double getJurosAcumulados() {
        return jurosAcumulados;
    }

    public Double getAmortizacao() {
        return amortizacao;
    }

//    public static void main(String[] args) {
//        TelaPrincipal tp = new TelaPrincipal();
//        FinContratosJuros juros = new FinContratosJuros();
//        //juros.ajustaTaxa(9.50);
//        juros.calculaContrato("3045127", "5528", "07/2017");
//        tp.dispose();
//    }
}
