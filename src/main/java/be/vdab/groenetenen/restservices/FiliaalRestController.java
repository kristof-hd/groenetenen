package be.vdab.groenetenen.restservices;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.exceptions.FiliaalNietGevondenException;
import be.vdab.groenetenen.services.FiliaalService;

@RestController
@RequestMapping("/filialen")
class FiliaalRestController {
	private final FiliaalService filiaalService;

	FiliaalRestController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}

	@GetMapping("{filiaal}")
	Filiaal read(@PathVariable Optional<Filiaal> filiaal) {
		if (filiaal.isPresent()) {
			return filiaal.get();
		}
		throw new FiliaalNietGevondenException();
	}

	@ExceptionHandler(FiliaalNietGevondenException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void filiaalNietGevonden() {
	}

	@DeleteMapping("{filiaal}")
	void delete(@PathVariable Optional<Filiaal> filiaal) {
		if (!filiaal.isPresent()) {
			throw new FiliaalNietGevondenException();
		}
		filiaalService.delete(filiaal.get().getId());
	}

	@ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String filiaalHeeftNogWerknemers() {
		return "filiaal heeft nog werknemers";
	}

	@PostMapping
	void create(@RequestBody @Valid Filiaal filiaal) {
		filiaalService.create(filiaal);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String filiaalMetVerkeerdeProperties(MethodArgumentNotValidException ex) {
		StringBuilder fouten = new StringBuilder();
		ex.getBindingResult().getFieldErrors().forEach(
				error -> fouten.append(error.getField()).append(':').append(error.getDefaultMessage()).append('\n'));
		fouten.deleteCharAt(fouten.length() - 1);
		return fouten.toString();
	}

	@PutMapping("{id}")
	void update(@RequestBody @Valid Filiaal filiaal) {
		filiaalService.update(filiaal);
	}
}
