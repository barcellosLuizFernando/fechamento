ALTER TABLE `fechamento`.`balancete` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`empresa`, `data_inicial`, `data_final`, `conta`);

CREATE TABLE `fechamento`.`empresa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NULL,
  `id_folha` INT NULL,
  `id_ocepar` INT NULL,
  `id_erp` INT NULL,
  `id_dominio` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_folha_UNIQUE` (`id_folha` ASC),
  UNIQUE INDEX `id_ocepar_UNIQUE` (`id_ocepar` ASC),
  UNIQUE INDEX `id_dominio_UNIQUE` (`id_dominio` ASC));