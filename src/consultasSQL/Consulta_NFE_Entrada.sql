/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luiz.barcellos
 * Created: 29/11/2017
 *
 * FALTA VERIFICAR COMO CAPTURAR AS NOTAS DE SERVIÇOS. DIFERENÇA DE R$ 6.255,50 EM 30/11/2017
 *
 */

select  a.CODIGO as lancamento,
        a.CLIENTE,
        case a.ESPECIE when 'NFE' then 36 else 0 end as cod_especie,
        b.CNPJ as inscr_fornecedor,
        b.INSC as inscr_est,
        b.INSC_MUNICIPAL,
        case (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO)
            when '2103' then '29'
            when '2801' then '106'
            when '2802' then '107'
            when '2701' then '109'
            when '2104' then '110'
            when '2744' then '19'
            else (select first 1 cc.OPERACAO from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cod_acum,
        case (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO)
            when '5102' then '1101'
            when '5117' then '1116'
            when '5910' then '1910'
            when '5949' then '1949'
            when '5922' then '1922'
            else (select first 1 cc.cfop from VEN_SAIDAS_PRODUTOS cc where cc.EMPRESA = a.EMPRESA and cc.CODIGO = a.CODIGO) end as cfop,
        d.codigo as NRO_NOTA,
        '001' as SERIE,
        a.DT_EMISSAO,
        a.NF_DATA_SAIDA,
        round((select sum(c.VLR_CONTABIL_ITEM) from VEN_SAIDAS_PRODUTOS c where c.EMPRESA = a.EMPRESA and c.CODIGO = a.CODIGO),2) as vlr_contabil,
        round((select sum(c1.VLR_DESCONTO) from VEN_SAIDAS_PRODUTOS c1 where c1.EMPRESA = a.EMPRESA and c1.CODIGO = a.CODIGO),2) as vlr_desconto,
        a.VLR_BASEICMS,
        a.VLR_ICMS,
        case when a.VLR_FRETE is null then 0.00 else a.VLR_FRETE end as vlr_frete,
        case when a.VLR_SEGURO is null then 0.00 else a.VLR_SEGURO end as vlr_seguro,
        a.AUTORIZADO,
        a.OBSERVACAO,
        case a.NFFRETE when 'D' then 'F' when 'E' then 'C' else 'S' end as mod_frete,
        'T' as emitente_nf,
        case when d.NFE_ID is null then '' else d.NFE_ID end as chave,
        e.PRODUTO,
        e.QUANTIDADE,
        e.VLR_CONTABIL_ITEM,
        e.CFOP,
        e.OBSERVACAO,
        e.VLR_BASE_ICMS,
        e.ALIQUOTA_ICMS,
        e.VLR_UNITARIO,
        e.VALOR_ICMS,
        e.SITUACAO_TRIBUTARIA,
        case left(s.CLASSIFICACAO,2) when '01' then 'S' else 'N' end as movim_fis_prod,
        e.LOTE,
        case when o.COMPRA_FIXAR = 'S' or o.COMPLEMENTO_FIXAR = 'S' or o.VENDA_COMPRA_PRODUTOS = 'S' or o.VENDA_ENTREGA_FUTURA = 'S' then 0 else 1 end as gera_financeiro
        
from ven_saidas a
LEFT JOIN CAD_ESTABELECIMENTOS b on (b.EMPRESA = a.EMPRESA and b.CODIGO = a.ESTABELEC_CAD)
LEFT JOIN VEN_NOTAFISCAL d on (d.EMPRESA = a.EMPRESA and d.CODIGO = a.NRO_NOTA and d.SERIE = a.SERIE and d.CLIENTE = a.CLIENTE)
LEFT JOIN VEN_SAIDAS_PRODUTOS e on (e.EMPRESA = a.EMPRESA and e.CODIGO = a.CODIGO)
left join CAD_PRODUTOS p on (p.EMPRESA = e.EMPRESA and p.CODIGO = e.PRODUTO)
left join CAD_SETORES s on (s.EMPRESA = p.EMPRESA and s.CODIGO = p.CODIGO)
LEFT JOIN CAD_OPERACOES_FISC o on (o.EMPRESA = e.EMPRESA and o.CODIGO = e.OPERACAO)

where a.EMPRESA = 1 and a.CLIENTE = 2 and a.DT_EMISSAO BETWEEN '01.01.2017' AND '31.12.2017';