package br.pucrs.seguranca.frequencia;

import java.util.HashMap;
import java.util.Map;

public class FrequenciaLetras {

	public Map<Character, Double> frequenciaLetrasFixa(){
		Map<Character, Double> indiceFixo = new HashMap<>();
		indiceFixo.put('a', 0.14634);
		indiceFixo.put('b', 0.1043);
		indiceFixo.put('c', 0.03882);
		indiceFixo.put('d', 0.04992);
		indiceFixo.put('e', 0.1257);
		indiceFixo.put('f', 0.01023);
		indiceFixo.put('g', 0.01303);
		indiceFixo.put('h', 0.00781);
		indiceFixo.put('i', 0.06186);
		indiceFixo.put('j', 0.00397);
		indiceFixo.put('k', 0.00015);
		indiceFixo.put('l', 0.02779);
		indiceFixo.put('m', 0.04738);
		indiceFixo.put('n', 0.04446);
		indiceFixo.put('o', 0.09735);
		indiceFixo.put('p', 0.02523);
		indiceFixo.put('q', 0.01204);
		indiceFixo.put('r', 0.06530);
		indiceFixo.put('s', 0.06805);
		indiceFixo.put('t', 0.04336);
		indiceFixo.put('u', 0.03639);
		indiceFixo.put('v', 0.01575);
		indiceFixo.put('w', 0.00037);
		indiceFixo.put('x', 0.00253);
		indiceFixo.put('y', 0.00006);
		indiceFixo.put('z', 0.0047);
		
		return indiceFixo;
	}
	
}
