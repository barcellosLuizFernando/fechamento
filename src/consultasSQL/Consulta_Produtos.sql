/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz.barcellos
 * Created: 29/11/2017
 */

with produtos as (
select a.PRODUTO
from com_entradas b
left join COM_ENTRADAS_PRODUTOS a on (a.EMPRESA = b.EMPRESA and a.CODIGO = b.CODIGO)
WHERE b.FORNECEDOR = 2 and b.empresa = 1 and b.DT_ENTRADA Between '01.01.2017' and '29.11.2017'

union all

select aa.PRODUTO
from ven_saidas bb
left join VEN_SAIDAS_PRODUTOS aa on (aa.EMPRESA = bb.EMPRESA and aa.CODIGO = bb.CODIGO)
WHERE bb.cliente = 2 and bb.empresa = 1 and bb.DT_emissao Between '01.01.2017' and '29.11.2017'
)

select z.CODIGO, 
        z.NOME, 
        case when z.NCM is null or z.NCM = '' then 99999999 else z.ncm end as ncm, 
        z.SETOR as grupo,
        z2.nome as grupo_nome,
        case  when z.NCM is null or z.NCM = '' or z.NCM = '99999999' then 'S'
            else 'N' end as trib_iss,
        'O' as tipo_produto,  
        case  when z.NCM is null or z.NCM = '' or z.NCM = '99999999' then 99
            when left(z2.CLASSIFICACAO,2) = '01' or z.ncm = '44012100' then 4
            when left(z.ncm,2) = '12' then 1
            when left(z2.CLASSIFICACAO,2) = '02' then 2
            else 10 end as sped_tipo_item,
        '01.01.2017' as data_cadastro,
        z.unidade,
        z3.nome_reduzido

from cad_produtos z
left join CAD_SETORES z2 on (z2.EMPRESA = z.EMPRESA and z2.CODIGO = z.SETOR)
inner join (select distinct produto from produtos) z1 on (z1.produto = z.codigo)
left join cad_unidades z3 on (z3.empresa = z.empresa and z3.codigo = z.unidade);
--where z.CODIGO in (781,2);