//mapas são estrutras onde tem uma key e um valor
//nunca no hashmap vai ter dois valores iguais, é uma estrutua de dados onde o valor da chave passa por um algoritmo que consegue encontrar um valor sem mostrar ele, para o indice o hashmap é muito bom
// list é uma lista de valores númerados - arraylist

package br.univille.microkernel_interface.service;

import java.util.HashMap;
import org.springframework.http.HttpStatus;

public interface DefaultService {

    HttpStatus doWork(HashMap<String, String> params);

}
