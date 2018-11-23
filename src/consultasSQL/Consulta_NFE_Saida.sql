/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ferna
 * Created: 04/12/2017
 */

select a.codigo as lancamento,
        case a.especie when 'NFE' then 36 else 99 end as especie,
        b.cnpj as inscricao_cliente,
        b.insc_municipal,
        b.insc,
        case (select first 1 a1.operacao from com_entradas_produtos a1 where a1.empresa = a.empresa and a1.codigo = a.codigo)
            when '1602' then '75'
            when '1701' then '108'
            when '1105' then '113'
            when '6212' then '111'
            when '1109' then '113'
            when '6216' then '114'
            else (select first 1 a1.operacao from com_entradas_produtos a1 where a1.empresa = a.empresa and a1.codigo = a.codigo) end as cod_acumulador,
        case (select first 1 a2.cfop from com_entradas_produtos a2 where a2.empresa = a.empresa and a2.codigo = a.codigo)
            when '1202' then '5201'
            when '1949' then '5949'
            when '1102' then '5101'
            when '1101' then '5101'
            when '1556' then '5101'
            else (select first 1 a2.cfop from com_entradas_produtos a2 where a2.empresa = a.empresa and a2.codigo = a.codigo) end as cfop,
        m.uf as sigla_uf_cliente,
        a.numero as nro_doc,
        a.serie,
        a.dt_entrada as dt_saida,
        a.dt_emissao,
        (select sum(a3.vlr_contabil_item) from com_entradas_produtos a3 where a3.empresa = a.empresa and a3.codigo = a.codigo) as vlr_contabil,
        a.observacao,
        a.nfe_chave_acesso,
        a.total_produtos,
        case a.cif_fob when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete,
        a.funrural,
        a.TOTAL_BASE_ICMS,
        a.TOTAL_VALOR_ICMS,
        o.FUNRURAL as desconta_funrural,
        p.CODIGO as cod_produto,
        p.QUANTIDADE,
        p.VLR_CONTABIL_ITEM,
        p.VLR_BASE_ICMS,
        p.ALIQ_ICMS,
        p.UNITARIO,
        p.SITUACAO_TRIBUTARIA,
        case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod,
        case when o.COMPRA_FIXAR = 'S' or o.COMPLEMENTO_FIXAR = 'S' or o.VENDA_COMPRA_PRODUTOS = 'S' or o.VENDA_ENTREGA_FUTURA = 'S' then 0 else 1 end as gera_financeiro,
        o.COMPRA_FIXAR, o.COMPLEMENTO_FIXAR, o.VENDA_COMPRA_PRODUTOS, o.VENDA_ENTREGA_FUTURA
from com_entradas a
left join cad_estabelecimentos b on (b.empresa = a.empresa and b.codigo = a.estabelec)
left join cad_municipios m on (m.codigo = b.municipio)
left join COM_ENTRADAS_PRODUTOS p on (p.EMPRESA = a.EMPRESA and p.CODIGO = a.CODIGO)
left join CAD_OPERACOES_FISC o on (o.EMPRESA = p.EMPRESA and o.CODIGO = p.OPERACAO)
left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.PRODUTO)
where a.empresa = 1 and a.fornecedor = 2 and a.dt_entrada between '01.01.2017' and '31.12.2017' and a.NUMERO = 18025