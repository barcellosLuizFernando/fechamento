/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz.barcellos
 * Created: 05/12/2017
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz.barcellos
 * Created: 03/12/2017
 */

with baixas as (
SELECT  e.NUMERO AS NRO_NOTA, t.CNPJ, c.DT_VENC, a.DATA, b.VALOR, p.nome, 
left(p.classificacao,1) as class, b.tipo_plano, e.especie

FROM FIN_CONTAS_PAGAR_BAIXAS a
LEFT JOIN FIN_CONTAS_PAGAR_BAIXAS_DETALHE b on (b.EMPRESA = a.EMPRESA and b.CODIGO_BAIXA = a.CODIGO)
LEFT JOIN FIN_CONTAS_PAGAR c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO_PAGAR)
INNER JOIN REL_COMPRAS_CPAGAR d on (d.EMPRESA = c.EMPRESA and d.CODIGO_PAGAR = c.CODIGO)
LEFT JOIN COM_ENTRADAS e on (e.EMPRESA = d.EMPRESA and e.CODIGO = d.CODIGO_ENTRADA)
LEFT JOIN CAD_ESTABELECIMENTOS t on (t.EMPRESA = c.EMPRESA and t.CODIGO = c.ESTABELEC)
LEFT JOIN CAD_PLANOCONTAS p on (p.EMPRESA = b.EMPRESA and p.CODIGO = b.PLANOCONTA)

WHERE a.empresa = '1' and c.FORNECEDOR = '2' and a.DATA between '01.01.2017' and '31.12.2017' ),

lancamentos as (
select z.nro_nota,
z.especie,
z.cnpj,
z.dt_venc,
z.data,
case when z.class = 3 and z.tipo_plano = 'C' then z.valor else 0.00 end as desconto,
case when z.class = 3 and z.tipo_plano = 'D' then z.valor else 0.00 end as juros,
case when z.class = 1 and z.tipo_plano = 'C' then z.valor else 0.00 end as valor
from baixas z )

select x.nro_nota,
        case x.especie when 'NFE' then 36 else 99 end as especie,
        x.cnpj,
        x.dt_venc,
        x.data,
        sum(desconto) as desconto,
        sum(juros) as juros,
        sum(valor) as valor
from lancamentos x

group by 1,2,3,4,5 having sum(valor) > 0.00