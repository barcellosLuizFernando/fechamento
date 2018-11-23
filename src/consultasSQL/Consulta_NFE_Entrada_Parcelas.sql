/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz.barcellos
 * Created: 03/12/2017
 */

SELECT b.CODIGO, b.DOC, b.DT_VENC, sum(c.VALOR) as valor
FROM REL_VENDAS_CRECEBER a
LEFT JOIN FIN_CONTAS_RECEBER b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.CODIGO_RECEBER)
INNER JOIN FIN_CONTAS_RECEBER_DETALHE c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO and c.TIPO_PLANO = 'C')

where a.EMPRESA = 1 and a.CODIGO_VENDA = 1

GROUP BY 1,2,3