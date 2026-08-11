# 🚗 Desafio Prático: Sistema de Mobilidade / Frota de Veículos
## Os 4 Pilares da Programação Orientada a Objetos em Java

Este desafio guiará você na construção progressiva de um sistema de gerenciamento de veículos, aplicando de forma prática os 4 pilares da POO: **Abstração**, **Encapsulamento**, **Herança** e **Polimorfismo**.

---

## 📁 Estrutura de Diretórios Esperada

Organize o seu projeto dentro do diretório do projeto respeitando a seguinte estrutura de pacotes:

```text
projeto-poo-veiculos/
├── README.md
└── src/
   ├── app_menu/
   │   └── MainMenuPilares.java
    ├── pilar1_abstracao/
    │   └── Carro.java
    ├── pilar2_encapsulamento/
    │   └── ContaVeiculo.java
    ├── pilar3_heranca/
    │   ├── Veiculo.java
    │   ├── CarroEletrico.java
    │   └── Moto.java
    └── pilar4_polimorfismo/
        ├── Veiculo.java
        ├── Carro.java
        ├── Moto.java
        ├── Caminhao.java
        └── PostoDeCombustivel.java
```

---

## 🔹 Etapa 1: Abstração (Diretório: `src/pilar1_abstracao/`)

### 📌 Conceito
**Abstração** consiste em isolar e focar apenas nas características essenciais de um objeto para o domínio do problema, ignorando detalhes irrelevantes.

### 🎯 O Desafio
Imagine que estamos modelando um carro simples para um sistema de trânsito. Precisa-se apenas de dados e comportamentos essenciais para movimentar o veículo.

### 📝 Requisitos:
1. Crie a classe `Carro.java`.
2. Defina os atributos: `marca` (String), `modelo` (String), `velocidade` (int).
3. Crie os métodos:
   - `acelerar(int incremento)`: Aumenta a velocidade do carro.
   - `frear(int decremento)`: Diminui a velocidade do carro.
   - `exibirStatus()`: Imprime a marca, modelo e velocidade atual no console.

---

## 🔹 Etapa 2: Encapsulamento (Diretório: `src/pilar2_encapsulamento/`)

### 📌 Conceito
**Encapsulamento** é o ato de proteger e esconder os dados internos de uma classe contra acessos e modificações indevidas, disponibilizando apenas métodos seguros para interação.

### 🎯 O Desafio
Atualmente, qualquer parte do código pode definir a velocidade para um valor inválido (como `-500 km/h`) ou alterar o número do chassi de um veículo. Vamos proteger a classe `ContaVeiculo.java` (representando um veículo registrado no sistema com hodômetro).

### 📝 Requisitos:
1. Crie a classe `ContaVeiculo.java`.
2. Torne todos os atributos **privados** (`private`):
   - `chassi` (String)
   - `modelo` (String)
   - `quilometragem` (double)
3. Crie um **construtor** público para inicializar `chassi` e `modelo` (`quilometragem` inicia em `0.0`).
4. Implemente **Getters** para todos os campos e **Setters** apenas onde fizer sentido.
5. Regra de Negócio no método `registrarViagem(double km)`:
   - A quilometragem **nunca** pode ser reduzida ou receber valores negativos. Se o valor for negativo ou zero, exiba uma mensagem de erro.

---

## 🔹 Etapa 3: Herança (Diretório: `src/pilar3_heranca/`)

### 📌 Conceito
**Herança** permite reaproveitar atributos e comportamentos comuns de uma classe base (Superclasse) em novas classes derivadas (Subclasses), evitando duplicação de código e promovendo reutilização.

### 🎯 O Desafio
Em uma frota de veículos, **Carro Elétrico** e **Moto** compartilham atributos comuns (como `marca`, `modelo` e `ano`), mas possuem características e comportamentos específicos.

### 📝 Requisitos:
1. Crie a superclasse `Veiculo.java` no pacote `pilar3_heranca`:
   - Atributos `protected`: `marca`, `modelo`, `ano`.
   - Construtor com todos os parâmetros.
   - Método `ligar()`: Exibe "Veículo ligado."
2. Crie a subclasse `CarroEletrico.java` que herda (`extends`) de `Veiculo`:
   - Atributo próprio: `capacidadeBateria` (int - em kWh).
   - Construtor utilizando `super(...)`.
   - Método próprio: `carregarBateria()`.
3. Crie a subclasse `Moto.java` que herda (`extends`) de `Veiculo`:
   - Atributo próprio: `cilindradas` (int).
   - Método próprio: `empinar()`.

---

## 🔹 Etapa 4: Polimorfismo (Diretório: `src/pilar4_polimorfismo/`)

### 📌 Conceito
**Polimorfismo** ("muitas formas") permite que objetos de diferentes classes derivadas respondam à mesma chamada de método, cada um de seu jeito específico.

### 🎯 O Desafio
No posto de combustível ou serviço de manutenção, todos são veículos, mas a forma como cada um calcula o custo do abastecimento ou a revisão varia drasticamente.

### 📝 Requisitos:
1. Crie a classe abstrata `Veiculo.java` no pacote `pilar4_polimorfismo`:
   - Atributos: `modelo`, `tanqueCapacidade` (double).
   - Método abstrato: `public abstract double calcularCustoAbastecimento(double precoCombustivel);`
2. Crie as classes concretas:
   - `Carro.java`: O custo é `tanqueCapacidade * precoCombustivel`.
   - `Moto.java`: A moto tem 10% de desconto no abastecimento (`custo * 0.90`).
   - `Caminhao.java`: O caminhão utiliza diesel, acrescentando uma taxa ambiental fixa de R$ 50,00 no cálculo do abastecimento.
3. Crie a classe `PostoDeCombustivel.java` com o método `executarSimulacao()`:
   - Crie um `ArrayList<Veiculo>` contendo um Carro, uma Moto e um Caminhão.
   - Percorra a lista e execute `calcularCustoAbastecimento(6.00)` para cada um de forma polimórfica (sem usar `if/else` ou `instanceof` para checar o tipo do veículo).
4. Crie a classe `MainMenuPilares.java` no pacote `app_menu` com método `main`:
   - Exiba menu para testar cada pilar individualmente (1 a 4), executar todos (5) e sair (0).
   - O menu deve funcionar de forma **progressiva**: se uma etapa ainda não foi criada, informe no console que ela está pendente e continue executável.

---

## 🚀 Como Testar o Projeto
1. Compile cada módulo em seu respetivo diretório utilizando o `javac`.
2. Verifique se os encapsulamentos impedem dados inválidos.
3. Garanta que o método polimórfico no `PostoDeCombustivel` trate cada veículo de maneira distinta utilizando a mesma chamada de método.
4. Execute o `main` de `app_menu.MainMenuPilares` para validar o fluxo progressivo do projeto.



## 🧩 No NetBeans (Passo a Passo Atualizado)

Abaixo está o passo a passo completo para montar e executar o desafio dentro do NetBeans:

### 1. Criar o Projeto no NetBeans
Abra o NetBeans.

Vá no menu Arquivo (File) > Novo Projeto... (New Project...) ou pressione Ctrl + Shift + N.

Escolha as opções:

Categorias: Java com Ant (ou Java com Maven / Java Application).

Projetos: Aplicação Java (Java Application).

Clique em Próximo (Next).

Configuração do Projeto:

Nome do Projeto: DesafioPOO

Criar Classe Principal (Main Class): Desmarque essa opção (o `main` será criado manualmente no pacote `app_menu`).

Clique em Finalizar (Finish).

### 2. Estrutura de Pacotes no NetBeans
Na aba Projetos (lado esquerdo), expanda o projeto DesafioPOO > Pacotes de Fonte (Source Packages).

Para criar os pacotes:

Clique com o botão direito em Pacotes de Fonte (Source Packages) > Novo (New) > Pacote Java... (Java Package...).

Crie os 5 pacotes abaixo (um por um):

app_menu

pilar1_abstracao

pilar2_encapsulamento

pilar3_heranca

pilar4_polimorfismo

A sua árvore no NetBeans ficará assim:
DesafioPOO/
└── Pacotes de Fonte (Source Packages)/
   ├── app_menu/
    ├── pilar1_abstracao/
    ├── pilar2_encapsulamento/
    ├── pilar3_heranca/
    └── pilar4_polimorfismo/

### 3. Como Criar as Classes em Cada Pacote
Para criar uma classe dentro de um pacote específico:

Clique com o botão direito no pacote correspondente (ex: pilar1_abstracao).

Vá em Novo (New) > Classe Java (Java Class).

Digite o nome da classe (ex: Carro) e clique em Finalizar.

O NetBeans já colocará automaticamente a linha package pilar1_abstracao; no topo do arquivo.

### 4. Organização Recomendada para Aula Progressiva
Use **apenas um main central** no pacote `app_menu` (classe `MainMenuPilares`).

Vantagens didáticas:

- Mantém um único ponto de execução para toda a turma.
- Permite testar por etapa (1, 2, 3 ou 4) sem trocar de arquivo.
- Permite iniciar a aula já na etapa 1, mesmo sem ter criado as etapas 2, 3 e 4.

Comportamento esperado do menu progressivo:

- Opção 1: testa Abstração.
- Opção 2: testa Encapsulamento.
- Opção 3: testa Herança.
- Opção 4: testa Polimorfismo.
- Opção 5: executa todas as etapas em sequência.
- Opção 0: encerra.
- Se a etapa escolhida ainda não existir, o menu informa que está pendente e continua funcionando.

### 5. Como Executar no NetBeans
Abra a classe `app_menu.MainMenuPilares`.

Clique com o botão direito em qualquer lugar do editor de código desse arquivo.

Clique em Executar Arquivo (Run File) ou pressione Shift + F6.

O resultado aparecerá na janela Output / Saída na parte inferior do NetBeans.

### 6. Roteiro de Uso em Sala (Sugestão)
1. Inicie a aula com apenas `pilar1_abstracao` e `app_menu`.
2. Execute o menu e escolha a opção 1.
3. Após concluir `pilar2_encapsulamento`, execute opção 2.
4. Após concluir `pilar3_heranca`, execute opção 3.
5. Após concluir `pilar4_polimorfismo`, execute opção 4.
6. Ao final, execute opção 5 para revisão geral dos 4 pilares.