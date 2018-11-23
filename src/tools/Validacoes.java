/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import conn.ConexaoMySQL;
import view.TelaPrincipal;
import java.text.DecimalFormat;

/**
 *
 * @author Luiz Fernando Dill Barcellos
 */
public class Validacoes {

    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    private Inventario inv;
    private Setores str;
    
    public Validacoes(ConexaoMySQL conn){
        inv = new Inventario(conn);
        str = new Setores(conn);
    }

    public void configSetor(String data, int setor, int empresa) {
        str.carregaDados(setor, empresa, true, data);

        System.out.println("Carregando totalizadora.");
        Double totalizadora = str.getV_estTot();
        Double calculado;
        Double valida;
        Double entradas;
        Double saidas;

        System.out.println("Carregando contas que somam.");
        //CONTAS QUE SOMAM O ESTOQUE
        Double estoqueInicial = str.getV_estIni();
        Double compraCoop = str.getV_estCompCoop();
        Double compraTerc = str.getV_estCompTerc();
        Double descarte = str.getV_estDescarte();
        Double provisao = str.getV_estProvAq();
        Double transferenciaE = str.getV_estTransf_E();
        Double produtoAcabado = str.getV_estProdAcabado();

        System.out.println("Carregando contas de reduzem.");
        //CONTAS DE REDUZEM O ESTOQUE
        Double remessa = str.getV_estRem();
        Double perda = str.getV_estPerda();
        Double devCoop = str.getV_estDevCoop();
        Double devTerc = str.getV_estDevTerc();
        Double custoCoop = str.getV_estCustoCoop();
        Double custoTerc = str.getV_estCustoTerc();
        Double transferenciaS = str.getV_estTransf_S();
        Double bonCoop = str.getV_estBonCoop();
        Double bonTerc = str.getV_estBonTerc();
        Double transfProducao = str.getV_estTransfProd();
        Double consumoInterno = str.getV_estConsInt();
        Double transfTercE = str.getV_estTransfTercE();
        Double transfTercS = str.getV_estTransfTercS();

        entradas = estoqueInicial + compraCoop + compraTerc + descarte + provisao + transfTercE;
        saidas = remessa + perda + devCoop + devTerc + custoCoop + custoTerc
                + transferenciaS + bonCoop + bonTerc + transfProducao + consumoInterno + transfTercS;
        calculado = entradas - saidas;

        //VALIDAÇÃO DAS CONFIGURAÇÕES
        valida = totalizadora - calculado;

        System.out.println("Entradas: " + df.format(entradas));
        System.out.println("Saidas: " + df.format(saidas));
        System.out.println("Resultado do setor estoque: " + df.format(valida));

        //VALIDAÇÃO DAS TRANSFERÊNCIAS
        valida = transfTercE + transferenciaE - transfTercS - transferenciaS;
        System.out.println("Resultado das transferências: " + df.format(valida));

        //ESTOQUE DE TERCEIROS
        totalizadora = str.getV_estTercTot();

        Double depositoFixar = str.getV_estTercFix();
        Double depositoFixarDPS = str.getV_estTercFixDps();
        Double deposito = str.getV_estTerc();
        Double depositoDPS = str.getV_estTercDPS();
        Double consignacao = str.getV_estTercCons();

        entradas = depositoFixar + depositoFixarDPS + deposito + depositoDPS + consignacao;

        valida = totalizadora - entradas;

        System.out.println("Resultado do Estoque de Terceiros: " + df.format(valida));

        //ESTOQUE EM TERCEIROS
        totalizadora = str.getV_estPropTot();

        Double comprasFutura = str.getV_estPropFut();
        Double contaOrdem = str.getV_estPropConOr();
        
        entradas = comprasFutura + contaOrdem;
        
        valida = totalizadora - entradas;
        System.out.println("Resultado do Estoque de Próprio em terceiros: " + df.format(valida));
        
        
        //PRODUCAO
        
        totalizadora = str.getV_prod_tot();
        
        Double materiaPrima = str.getV_prod_mp();
        Double embalagem = str.getV_prod_embalagem();
        Double insumos = str.getV_prod_insumos();
        Double custoFixo = str.getV_prod_cf();
        Double transfE = str.getV_prod_transf_e();
        
        Double transfS = str.getV_prod_transf_s();
        Double transfProdutoAcabado = str.getV_prod_acabado();
        
        entradas = materiaPrima + embalagem + insumos + custoFixo + transfE;
        saidas = transfS + transfProdutoAcabado;
        
        valida = totalizadora -(entradas - saidas);
        
        //RESULTADO DA CONFIGURAÇÃO DA PRODUÇÃO
        System.out.println("Resultado da produção: " + df.format(valida));
        
        valida = transfS - transfE;
        System.out.println("Resultado da transferência produção: " + df.format(valida));
        
        
        //VALIDA RESULTADO COOPERADO
        Double receita;
        Double deducao;
        
        totalizadora = str.getV_resIngTot();
        //RECEITAS
        Double receitaInterna = str.getV_resIngInt();
        Double receitaExterna = str.getV_resIngExt();
        Double outrasReceitas = str.getV_resOutIng();
        Double bonificações = str.getV_resBonCoop();
        //DEDUÇÕES
        Double icms = str.getV_resIcmsCoop();
        Double pis = str.getV_resPisCoop();
        Double cofins = str.getV_resCofinsCoop();
        Double iss = str.getV_resIssCoop();
        Double ipi = str.getV_resIpiCoop();
        Double devolucao = str.getV_resDevCoop();
        Double custo = str.getV_resRepasse();
        
        receita = receitaInterna + receitaExterna + outrasReceitas + bonificações;
        deducao = icms + pis + cofins + iss + ipi + devolucao + custo;
        
        valida = totalizadora - (receita - deducao);
                
        System.out.println("Resultado do ato cooperado: " + df.format(valida));
        
        

        //VALIDAÇÃO DO ATO NÃO COOPERADO
        totalizadora = str.getV_resRecTot();
        //RECEITAS
        receitaInterna = str.getV_resRecInt();
        receitaExterna = str.getV_resRecExt();
        outrasReceitas = str.getV_resOutRec();
        bonificações = str.getV_resBonTerc();
        //DEDUÇÕES
        icms = str.getV_resIcmsTerc();
        pis = str.getV_resPisTerc();
        cofins = str.getV_resCofinsTerc();
        iss = str.getV_resIssTerc();
        ipi = str.getV_resIpiTerc();
        devolucao = str.getV_resDevTerc();
        custo = str.getV_resCustoTerc();
        
        receita = receitaInterna + receitaExterna + outrasReceitas + bonificações;
        deducao = icms + pis + cofins + iss + ipi + devolucao + custo;
        
        valida = totalizadora - (receita - deducao);
                
        System.out.println("Resultado do ato com terceiros: " + df.format(valida));
        

    }

//    public static void main(String[] args) {
//
//        Validacoes valid = new Validacoes();
//        TelaPrincipal tp = new TelaPrincipal();
//        valid.configSetor("31/05/2017", 3, 1);
//        tp.dispose();
//
//    }

}
