
# 🚗 **FindCars** 🚗
**FindCars** é uma API que permite buscar informações detalhadas sobre carros, motos e caminhões, incluindo marcas, modelos, anos e preços. 🚙🚚🏍️

## 🌐 **Acesse a API Publicada**
Explore a documentação interativa da API **FindCars** utilizando o Swagger UI:

🔗 [FindCars API - Swagger UI](https://findvehicles.onrender.com/swagger-ui/index.html)

## 📜 **Funcionalidades**

- 🔎 Listar marcas de veículos por tipo (carro, moto, caminhão)
- 🔎 Listar modelos de veículos de uma marca específica
- 🔎 Encontrar anos e modelos
- 🔎 Buscar veículos e seus respectivos preços por tipo, marca, modelo e ano
- 🔎 Filtros de pesquisa por marca e ano

## 📂 **Estrutura do Projeto**

O projeto utiliza o padrão MVC com os seguintes pacotes principais:

- `controller`: Onde ficam os controladores das rotas
- `services`: Serviços de integração e manipulação de dados da API
- `repository`: Repositório para persistência de dados
- `dtos`: Transferência de dados entre a API e a aplicação
- `model`: Modelos das entidades de veículos

## 🚀 **Endpoints**

### **Listar marcas de veículos**
```bash
GET /findcars/{tipo}
```
- **Parâmetros:** `tipo` (carro, moto, caminhao)

### **Listar modelos de uma marca**
```bash
GET /findcars/{tipo}/{codigoMarca}/modelos
```
- **Parâmetros:** `tipo`, `codigoMarca`

### **Buscar anos de modelos de veículos**
```bash
GET /findcars/{tipo}/{codigoMarca}/modelos/{nomeModelo}
```
- **Parâmetros:** `tipo`, `codigoMarca`, `nomeModelo`

### **Buscar veículos e preços**
```bash
GET /findcars/{tipo}/{codigoMarca}/modelos/{codigoModelo}/anos
```
- **Parâmetros:** `tipo`, `codigoMarca`, `codigoModelo`

## 🛠️ **Tecnologias Utilizadas**

- **Java** com **Spring Boot** ☕
- **Jackson** para manipulação de JSON 📄
- **API da Tabela FIPE** para dados dos veículos 🔗
- **H2 Database** para persistência local de dados 🗄️

## 🎨 **Exemplo de Uso**

Para listar todas as marcas de carros:
```bash
GET /findcars/carros
```

Retorna uma lista de objetos `DadosVeiculos` contendo as marcas.

## 📖 **Licença**

Este projeto é licenciado sob a [MIT License](LICENSE).

---

Desenvolvido com 💜 por Ana Cecília Simões
```

