select 	c.empresa,
		a.data as competencia,
		case when c.estab_debito = 10 then d.cdebprod else d.cdebpad end as debito,
        c.estab_debito,
        d.ccred as credito,
        c.estab_debito as estab_credito,
        a.valor,
        d.historico,
        concat(d.complemento,' ', c.complemento,', Lançamento ',a.id) as complemento,
        a.parcela,
        a.id,
        e.numero,
        e.data
        
from desp_antecipada_detalhe a
left join desp_antecipada_provisao b on (b.id = a.id)
left join desp_antecipada c on (c.id = a.id)
left join lanc_padrao d on (d.id = b.id_lanc_padrao)
left join desp_antecipada_lancamentos e on (e.id = a.id and e.parcela = a.parcela)