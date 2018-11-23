/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominiosistemas;

import java.io.IOException;

/**
 *
 * @author luiz.barcellos
 */
public interface Exportacao_DomSis {
    
    /**
     * Método que retorna o Layout formatado de acordo com manual da Domínio Sistemas.
     * @return 
     */
    public String getLayout() throws Exception;
    
    /**
     * Classes devem implementar o método que tratará os campos obrigatórios não preenchidos.
     */
    //public UnsupportedOperationException campoObrigatorioException(String mensagem);
    
}
