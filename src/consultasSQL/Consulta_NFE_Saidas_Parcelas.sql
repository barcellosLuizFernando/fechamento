/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz.barcellos
 * Created: 05/12/2017
 */
SELECT b.CODIGO, b.DOC, b.DT_VENC, sum(c.VALOR) as valor
FROM REL_COMPRAS_CPAGAR a
left join FIN_CONTAS_PAGAR b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.CODIGO_PAGAR)
inner join FIN_CONTAS_PAGAR_DETALHE c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO and c.TIPO_PLANO = 'D')

where a.EMPRESA = '1' and a.CODIGO_ENTRADA = '1376' and b.FORNECEDOR = '2'

group by 1,2,3
