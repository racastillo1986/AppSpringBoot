package fin.coop1504.rpa.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Utilerias {
	
public String fechaHora() {
		
		LocalDateTime fechaHora = LocalDateTime.now();
		return String.valueOf(fechaHora);
		
	}

}
