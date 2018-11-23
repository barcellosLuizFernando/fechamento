/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custoMedio;

import conn.ConexaoFB;
import tools.TelaDeAvisos;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * Verifica as consignações que retornaram para o estoque pelo mesmo custo da
 * venda.
 *
 * @author ferna
 */
public class AjusteCustoMedio {
    
    private final ConexaoFB cnfb = new ConexaoFB();
    private String sql;
    private ArrayList codigos, produtos, sequencias, pessoas, estabelecimentos;
    private ArrayList emissao;
    private ArrayList nome_produto;
    private ArrayList custo_atual;
    
    private JInternalFrame tela;
    
    public AjusteCustoMedio(JInternalFrame x) {
        this.tela = x;
    }
    public AjusteCustoMedio() {
     
    }
    
    public void ajustaCusto(final String dti, final String dtf) {
        new Thread() {
            public void run() {
                
                if(tela != null) tela.setVisible(false);
                
                TelaDeAvisos msg = null;
                
                Double custo = 0.00;
                
                codigos = new ArrayList();
                produtos = new ArrayList();
                sequencias = new ArrayList();
                pessoas = new ArrayList();
                estabelecimentos = new ArrayList();
                emissao = new ArrayList();
                nome_produto = new ArrayList();
                custo_atual = new ArrayList();
                
                sql = "select ent.empresa, ent.codigo, ent.dt_entrada, ent.estabelec,"
                        + "ent.fornecedor, prod.produto, prod.sequencia, prod.custo_item, "
                        + "prod.unitario, p.nome as nome_produto "
                        + "from com_entradas ent "
                        + "inner join com_entradas_produtos prod on "
                        + "     (prod.empresa = ent.empresa and "
                        + "     prod.codigo = ent.codigo and "
                        + "     prod.operacao = 6833 and "
                        + "     round(prod.unitario,2) = round(prod.custo_item,2)) "
                        + "left join cad_produtos p on (p.empresa = prod.empresa and p.codigo = prod.produto) "
                        + "where ent.empresa = 1 and ent.dt_entrada between '" + dti + "' and '" + dtf + "'"
                        + "order by ent.dt_entrada;";
                
                if (cnfb.conecta()) {
                    try {
                        msg = new TelaDeAvisos();
                        
                        cnfb.executeConsulta(sql);
                        
                        msg.setMsgStatus("Consultando movimentos.", 0, 0);
                        while (cnfb.rs.next()) {
                            codigos.add(cnfb.rs.getString("codigo"));
                            produtos.add(cnfb.rs.getString("produto"));
                            sequencias.add(cnfb.rs.getString("sequencia"));
                            pessoas.add(cnfb.rs.getString("fornecedor"));
                            estabelecimentos.add(cnfb.rs.getString("estabelec"));
                            emissao.add(cnfb.rs.getString("dt_entrada"));
                            nome_produto.add(cnfb.rs.getString("nome_produto"));
                            custo_atual.add(cnfb.rs.getString("custo_item"));
                        }
                        cnfb.rs.close();
                        
                        for (int i = 0; i < codigos.size(); i++) {
                            msg.setMsgStatus("Atualizando produto " + nome_produto.get(i), i, codigos.size());
                            
                            custo = buscaUltimoCusto(produtos.get(i).toString(),
                                    pessoas.get(i).toString(),
                                    estabelecimentos.get(i).toString(),
                                    emissao.get(i).toString());
                            
                            System.out.println("Atualizando produto " + nome_produto.get(i)
                                    + " no lancamento " + codigos.get(i)
                                    + " em " + emissao.get(i)
                                    + ". Custo Atual: " + custo_atual.get(i)
                                    + ". Custo Corrigido: " + custo + "."
                            );
                            
                            sql = "UPDATE com_entradas_produtos SET custo_item = '" + custo + "' "
                                    + "where empresa = 1 and codigo = '" + codigos.get(i) + "' "
                                    + "and sequencia = '" + sequencias.get(i) + "' "
                                    + "and produto = '" + produtos.get(i) + "';";
                            
                            cnfb.executeAtualizacao(sql);
                        }
                        
                        msg.dispose();
                        JOptionPane.showMessageDialog(null, "Ajuste finalizado com sucesso!");
                        
                    } catch (Exception e) {
                        msg.dispose();
                        throw new UnsupportedOperationException(e);
                    } finally {
                        cnfb.desconecta();
                        
                        if (tela != null) {
                            tela.setVisible(true);
                        }
                        
                    }
                }
                
            }
        }.start();
    }
    
    public static void main(String[] args) {
        AjusteCustoMedio aj = new AjusteCustoMedio();
        
        ConexaoFB.arquivo = "fbcasa.properties";
        
        aj.ajustaCusto("01.01.2017", "31.12.2017");
    }

    /**
     * Busca última saída do produto para a pessoa e o estabelecimento citado.
     *
     * @param produto
     * @param pessoa
     * @param estabelecimento
     * @param emissao
     * @return
     */
    private Double buscaUltimoCusto(String produto, String pessoa,
            String estabelecimento, String emissao) throws Exception {
        
        String data = "";
        Double custo = 0.00;

        /**
         * Busca última remessa, para verificar a data do custo.
         */
        sql = "select first 1 cof.empresa, cof.codigo as cod_oper, "
                + "cof.nome as natureza, vsp.codigo as cod_saida, vsp.produto, "
                + "v.estabelec, v.dt_emissao, v.cliente "
                + "from cad_operacoes_fisc cof "
                + "left join ven_saidas_produtos vsp on (vsp.empresa = cof.empresa "
                + "     and vsp.operacao = cof.codigo and vsp.produto = '" + produto + "') "
                + "inner join ven_saidas v on(v.empresa = vsp.empresa "
                + "     and v.codigo = vsp.codigo and v.dt_emissao <= '" + emissao + "' "
                + "and v.cliente = '" + pessoa + "' ) "
                + "where cof.empresa = 1 and cof .consignacao_enviada = 'S' and "
                + "cof.tipo = 'S' "
                + "order by v.dt_emissao desc;";
        
        cnfb.executeConsulta(sql);
        while (cnfb.rs.next()) {
            data = cnfb.rs.getString("dt_emissao");
        }
        cnfb.rs.close();

        /**
         * Busca custo médio que estava na data da remessa do produto;
         */
        sql = "select first 1 mov.empresa, mov.estabelec, mov.sequencia, "
                + "mov.produto, mov.data, mov.valor, mov.quantidade, "
                + "mov.custo_medio_estoque "
                + "from est_mov_estoque mov "
                + "where mov.empresa = 1 and mov.custo_medio_estoque > 0.00 "
                + "and mov.produto = '" + produto + "' "
                + "and mov.estabelec = '" + estabelecimento + "' "
                + "and mov.data <= '" + data + "' "
                + "order by mov.data desc, mov.sequencia desc;";
        
        cnfb.executeConsulta(sql);
        while (cnfb.rs.next()) {
            custo = cnfb.rs.getDouble("custo_medio_estoque");
        }
        cnfb.rs.close();
        
        return custo;
    }
    
}
