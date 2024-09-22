package br.com.acsimoeschalegre.FindCars.controller;

import br.com.acsimoeschalegre.FindCars.dtos.DadosVeiculos;
import br.com.acsimoeschalegre.FindCars.dtos.ModelosDTO;
import br.com.acsimoeschalegre.FindCars.dtos.VeiculoDTO;
import br.com.acsimoeschalegre.FindCars.model.Veiculo;
import br.com.acsimoeschalegre.FindCars.repository.IVeiculoRepositoy;
import br.com.acsimoeschalegre.FindCars.services.ConsumoApi;
import br.com.acsimoeschalegre.FindCars.services.ConverteDados;
import br.com.acsimoeschalegre.FindCars.services.IConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/findcars")
public class Controller {

    @Autowired
    private IVeiculoRepositoy veiculoRepositoy;

    private ConsumoApi consumoApi = new ConsumoApi();
    private IConverteDados converteDados = new ConverteDados();

    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private static final String CARROS = "carros/marcas";
    private static final String MOTOS = "motos/marcas";
    private static final String CAMINHOES = "caminhoes/marcas";

    @GetMapping("/{tipo}")
    public List<DadosVeiculos> listarMarcas(@PathVariable String tipo) throws JsonProcessingException {
        String endereco = defineEndereco(tipo.toUpperCase());
        String json = consumoApi.obterDados(endereco);
        return converteDados.obterLista(json, DadosVeiculos.class)
                .stream()
                .sorted(Comparator.comparing(DadosVeiculos::codigo))
                .collect(Collectors.toList());
    }

    @GetMapping("/{tipo}/{codigoMarca}/modelos")
    public List<DadosVeiculos> listarModelosDeMarcas(@PathVariable String tipo, @PathVariable String codigoMarca) {
        String endereco = defineEndereco(tipo.toUpperCase()) + "/" + codigoMarca + "/modelos";
        String json = consumoApi.obterDados(endereco);
        ModelosDTO modeloLista = converteDados.obterDados(json, ModelosDTO.class);
        return modeloLista.modelos();
    }

    @GetMapping("/{tipo}/{codigoMarca}/modelos/{nomeModelo}")
    public List<DadosVeiculos>  encontrarAnosModelosDeMarcas(@PathVariable String tipo,
                                              @PathVariable String codigoMarca,
                                              @PathVariable String nomeModelo) {
        String endereco = defineEndereco(tipo.toUpperCase()) + "/" + codigoMarca + "/modelos";
        String json = consumoApi.obterDados(endereco);
        ModelosDTO modeloLista = converteDados.obterDados(json, ModelosDTO.class);

        return modeloLista.modelos()
                .stream()
                .filter(m -> m.nome().toUpperCase().contains(nomeModelo.toUpperCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{tipo}/{codigoMarca}/modelos/{codigoModelo}/anos")
    public List<VeiculoDTO> listarVeiculosEPrecos(@PathVariable String tipo,
                                           @PathVariable String codigoMarca,
                                           @PathVariable String codigoModelo) throws JsonProcessingException {
        String endereco = defineEndereco(tipo.toUpperCase()) + "/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";

        String json = consumoApi.obterDados(endereco);
        System.out.println("JSON retornado: " + json);


        List<DadosVeiculos> anos = converteDados.obterLista(json, DadosVeiculos.class);

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            System.out.println("URL gerada: " + enderecoAnos);
            json = consumoApi.obterDados(enderecoAnos);
            VeiculoDTO veiculo = converteDados.obterDados(json, VeiculoDTO.class);
            this.veiculoRepositoy.save(new Veiculo(veiculo));
        }
        List<VeiculoDTO> list = veiculoRepositoy.findAll().stream().map(v -> new VeiculoDTO(v)).toList();
        return list ;
    }

    @GetMapping("ano-modelo/{ano}/{modelo}")
    public List<VeiculoDTO> buscarPorModeloEAno(@PathVariable int  ano, @PathVariable String modelo){
        List<VeiculoDTO> veiculos = this.veiculoRepositoy.veiculosPorAnoEModelo(ano,modelo);
        return veiculos;
    }

    @GetMapping("marca/{marca}")
    public List<VeiculoDTO> buscarVeiculosPorMarca(@PathVariable String marca){
        List<VeiculoDTO> veiculos = this.veiculoRepositoy.findByMarcaContainingIgnoreCase(marca);
        return veiculos;
    }

    @GetMapping("ano/{ano}")
    public List<VeiculoDTO> buscarVeiculosPorAno(@PathVariable int ano){
        List<VeiculoDTO> veiculos = this.veiculoRepositoy.findByAno(ano);
        return veiculos;
    }

    private String defineEndereco(String tipo) {
        switch (tipo) {
            case "CARRO":
                return URL_BASE + CARROS;
            case "MOTO":
                return URL_BASE + MOTOS;
            case "CAMINHAO":
                return URL_BASE + CAMINHOES;
            default:
                throw new IllegalArgumentException("Tipo de veículo inválido.");
        }
    }
}
