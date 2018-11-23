SELECT a.id as id_bc,
		a.tipo,
        a.imposto,
        a.numero,
        a.base_de_calculo,
        a.debito,
        a.estab_debito,
        a.credito,
        a.estab_credito,
        a.valor,
        a.historico,
        a.complemento,
        a.competencia,
        b.id as numero_lcto,
        b.data
FROM pis_cofins a
LEFT JOIN pis_cofins_lancamentos b on (b.id_bc = a.id);