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
SELECT  case when e.nro_nota is null then e.NRO_NFS else e.NRO_NOTA end as nro_nota, t.CNPJ, c.DT_VENC, a.DATA, b.VALOR, p.nome, 
left(p.classificacao,1) as class, b.tipo_plano, case when e.ESPECIE is null then e.ESPECIE_NFS else e.ESPECIE end as especie

FROM FIN_CONTAS_RECEBER_BAIXAS a
LEFT JOIN FIN_CONTAS_RECEBER_BAIXAS_DET b on (b.EMPRESA = a.EMPRESA and b.CODIGO_BAIXA = a.CODIGO)
LEFT JOIN FIN_CONTAS_RECEBER c on (c.EMPRESA = b.EMPRESA and c.CODIGO = b.CODIGO_RECEBER)
INNER JOIN REL_VENDAS_CRECEBER d on (d.EMPRESA = c.EMPRESA and d.CODIGO_RECEBER = c.CODIGO)
LEFT JOIN VEN_SAIDAS e on (e.EMPRESA = d.EMPRESA and e.CODIGO = d.CODIGO_VENDA)
LEFT JOIN CAD_ESTABELECIMENTOS t on (t.EMPRESA = c.EMPRESA and t.CODIGO = c.ESTABELEC)
LEFT JOIN CAD_PLANOCONTAS p on (p.EMPRESA = b.EMPRESA and p.CODIGO = b.PLANOCONTA)

WHERE a.empresa = '1' and c.cliente = '2' and a.DATA between '01.01.2017' and '31.12.2017' 

union all

select distinct case when a1.nro_nota is null then a1.NRO_NFS else a1.NRO_NOTA end as nro_nota, c1.CNPJ, a1.DT_EMISSAO as dt_venc, a1.DT_EMISSAO as data, sum(b1.VLR_CONTABIL_ITEM) as valor, '' as nome, 1 as class, 'D' as tipo_plano, case when a1.ESPECIE is null then a1.ESPECIE_NFS else a1.ESPECIE end as especie
from ven_saidas a1
left join VEN_SAIDAS_PRODUTOS b1 on (b1.EMPRESA = a1.EMPRESA and b1.CODIGO = a1.CODIGO)
left join CAD_ESTABELECIMENTOS c1 on (c1.EMPRESA = a1.EMPRESA and c1.CODIGO = a1.ESTABELEC)
inner join CAD_OPERACOES_FISC o on (o.EMPRESA = b1.EMPRESA and o.CODIGO = b1.OPERACAO and (o.COMPRA_FIXAR = 'S' or o.COMPLEMENTO_FIXAR = 'S' 
or o.VENDA_COMPRA_PRODUTOS = 'S' or o.VENDA_ENTREGA_FUTURA = 'S' or o.COMPLEM_VALORES = 'S'))
where a1.EMPRESA = 1 and a1.CLIENTE = '2' and a1.DT_EMISSAO between '01.01.2017' and '31.12.2017' 
and not exists (select * from REL_VENDAS_CRECEBER x where x.EMPRESA = a1.EMPRESA and x.CODIGO_VENDA = a1.CODIGO)

group by 1,2,3,4,9

),

lancamentos as (
select z.nro_nota,
z.especie,
z.cnpj,
z.dt_venc,
z.data,
case when z.class = 3 and z.tipo_plano = 'D' then z.valor else 0.00 end as desconto,
case when z.class = 3 and z.tipo_plano = 'C' then z.valor else 0.00 end as juros,
case when z.class = 1 and z.tipo_plano = 'D' then z.valor else 0.00 end as valor
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