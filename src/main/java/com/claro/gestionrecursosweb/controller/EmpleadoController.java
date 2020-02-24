package com.claro.gestionrecursosweb.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claro.gestionrecursosweb.domain.ApiService;
import com.claro.gestionrecursosweb.dto.EmpleadoDto;
import com.claro.gestionrecursosweb.dto.EmpleadoVUDto;
import com.claro.gestionrecursosweb.dto.LineasProductoDto;
import com.claro.gestionrecursosweb.dto.PerfilDto;
import com.claro.gestionrecursosweb.dto.PerfilnivelDto;
import com.claro.gestionrecursosweb.dto.PerfiltipoDto;
import com.claro.gestionrecursosweb.dto.PersonaDto;
import com.claro.gestionrecursosweb.dto.ProveedorDto;
import com.claro.gestionrecursosweb.dto.TipoDocumentoDto;

@Controller
@RequestMapping("/Empleado")
public class EmpleadoController extends BaseController {
		
	@Autowired
	private ApiService<EmpleadoDto, Integer> service;
	@Autowired
	private ApiService<EmpleadoVUDto, Integer> serviceVU;
	@Autowired
	private ApiService<PerfilDto, Integer> servicePerfil;
	@Autowired
	private ApiService<PerfiltipoDto, Integer> servicePerfilTipo;
	@Autowired
	private ApiService<PerfilnivelDto, Integer> servicePerfilNivel;
	@Autowired
	private ApiService<LineasProductoDto, Integer> serviceLineasProducto;
	@Autowired
	private ApiService<ProveedorDto, Integer> serviceProveedor;
	@Autowired
	private ApiService<PersonaDto, Integer> servicePersona;
	@Autowired
	private ApiService<TipoDocumentoDto, Integer> serviceTipoDocumento;
	
		
	public void configurarService() {
		service.setapiservicename(dominio_empleado);
	}
	
	@GetMapping("/Filtro")
	public String filtro(Model model) {
		configurarService();
		
		serviceVU.setapiservicename(dominio_empleado + "/vu");
		Iterable<EmpleadoVUDto> dto = serviceVU.findAll(EmpleadoVUDto.class);
		
		model.addAttribute("modelo", dto);
		return dominio_empleado + "/Filtro";
	}
	
	@GetMapping("/Crear")
	public String crear(Model modelo) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		modelo.addAttribute("modelo", new EmpleadoVUDto());
		cargarListas(modelo);
		return dominio_empleado + "/Empleado";
	}
	
	@PostMapping("/Crear")
	public String crear(EmpleadoDto dto, EmpleadoVUDto dto2, BindingResult result, Model modelo, HttpServletRequest request) {
		configurarService();
		modelo.addAttribute("cl_formaction", "Crear");
		
		EmpleadoDto dtoResultado = service.insert(dto, EmpleadoDto.class);
		
		return redireccion("Editar", dtoResultado.getId().toString(), "S", "C", request);
	}
	
	@GetMapping("/Editar/{id}")
	public String editar(@PathVariable Integer id, Model modelo, @RequestParam(required = false) String cla) {
		configurarService();
		mostrarMensajes(modelo, cla);		
		modelo.addAttribute("cl_formaction", "Editar");
		
		Optional<EmpleadoDto> dtoResultado = service.findById(id, EmpleadoDto.class);
		if (dtoResultado == null)
			dtoResultado = Optional.of(new EmpleadoDto());
				
		modelo.addAttribute("modelo", dtoResultado.get());
		cargarListas(modelo);
		return dominio_empleado + "/Empleado";
	}
	
	@PostMapping("/Editar")
	public String editar(EmpleadoDto dto, BindingResult result, Model modelo) {
		configurarService();		
		modelo.addAttribute("cl_formaction", "Editar");
		
		EmpleadoDto dtoResultado = service.update(dto.getId(), dto, EmpleadoDto.class);				
		modelo.addAttribute("modelo", dtoResultado);
		
		mostrarMensajes(modelo, "S", "U");
		cargarListas(modelo);
		return dominio_empleado + "/Empleado";
	}
	
	private void cargarListas(Model modelo) {
		servicePerfil.setapiservicename(dominio_perfil);
		modelo.addAttribute("perfiles", servicePerfil.findAll(PerfilDto.class));
		
		servicePerfilTipo.setapiservicename(dominio_perfiltipo);
		modelo.addAttribute("perfiltipos", servicePerfilTipo.findAll(PerfiltipoDto.class));
		
		servicePerfilNivel.setapiservicename(dominio_perfilnivel);
		modelo.addAttribute("perfilniveles", servicePerfilNivel.findAll(PerfilnivelDto.class));
		
		serviceLineasProducto.setapiservicename(dominio_lineasproducto);
		modelo.addAttribute("lineasproducto", serviceLineasProducto.findAll(LineasProductoDto.class));
		
		serviceProveedor.setapiservicename(dominio_proveedor);
		modelo.addAttribute("proveedores", serviceProveedor.findAll(ProveedorDto.class));
		
		servicePersona.setapiservicename(dominio_persona);
		modelo.addAttribute("personas", servicePersona.findAll(PersonaDto.class));
		
		serviceTipoDocumento.setapiservicename(dominio_tipodocumento);
		modelo.addAttribute("tiposdocumento", serviceTipoDocumento.findAll(TipoDocumentoDto.class));
	}

}
