package br.com.wesguedesas.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wesguedesas.model.Cambio;
import br.com.wesguedesas.repository.CambioRepository;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	@Autowired 
	private Environment environment;
	@Autowired
	private CambioRepository repository;
	
	//http:localhost:8000/cambio-service/5/USD/BRL
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCabio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) {
		
		
		var cambio = repository.findByFromAndTo(from, to);
		if(cambio == null) throw new RuntimeException("Currency Unsupported");
		
		var port = environment.getProperty("local.server.port");
		cambio.setEnvironment(port);
		
		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		
		return cambio;
	}
	

}
