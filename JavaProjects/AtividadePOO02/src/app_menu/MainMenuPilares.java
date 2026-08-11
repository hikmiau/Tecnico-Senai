package app_menu;

import java.lang.reflect.Method;
import java.util.Scanner;

// Main central da aplicacao: executa os testes dos pilares por menu.
public class MainMenuPilares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    executarEtapa(1, testarAbstracao());
                    break;
                case 2:
                    executarEtapa(2, testarEncapsulamento());
                    break;
                case 3:
                    executarEtapa(3, testarHeranca());
                    break;
                case 4:
                    executarEtapa(4, testarPolimorfismo());
                    break;
                case 5:
                    executarTodosOsPilares();
                    break;
                case 0:
                    System.out.println("Encerrando o menu de testes.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("============================================");
        System.out.println("   MENU DE TESTES - PILARES DA POO");
        System.out.println("============================================");
        System.out.println("1 - Testar Pilar 1 (Abstracao)");
        System.out.println("2 - Testar Pilar 2 (Encapsulamento)");
        System.out.println("3 - Testar Pilar 3 (Heranca)");
        System.out.println("4 - Testar Pilar 4 (Polimorfismo)");
        System.out.println("5 - Executar todos os pilares em sequencia");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static int lerOpcao(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }

        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private static void executarEtapa(int etapa, boolean ok) {
        if (ok) {
            System.out.println("Etapa " + etapa + " concluida com sucesso.");
        } else {
            System.out.println("Etapa " + etapa + " apresentou inconsistencias.");
        }
    }

    private static void executarTodosOsPilares() {
        System.out.println("\nExecutando todas as etapas progressivamente...\n");

        executarEtapa(1, testarAbstracao());
        executarEtapa(2, testarEncapsulamento());
        executarEtapa(3, testarHeranca());
        executarEtapa(4, testarPolimorfismo());
    }

    private static boolean testarAbstracao() {
        System.out.println("\n--- Pilar 1: Abstracao ---");

        try {
            Class<?> carroClass = Class.forName("pilar1_abstracao.Carro");
            Object carro = carroClass.getConstructor(String.class, String.class)
                    .newInstance("Toyota", "Corolla");

            carroClass.getMethod("acelerar", int.class).invoke(carro, 40);
            carroClass.getMethod("frear", int.class).invoke(carro, 10);
            carroClass.getMethod("frear", int.class).invoke(carro, 100);
            carroClass.getMethod("exibirStatus").invoke(carro);

            Method getVelocidade = carroClass.getMethod("getVelocidade");
            int velocidadeFinal = (int) getVelocidade.invoke(carro);

            // Esperado: apos frear alem do limite, velocidade deve ficar em 0.
            return velocidadeFinal == 0;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            informarEtapaNaoCriada(1, "pilar1_abstracao.Carro");
            return false;
        } catch (Exception e) {
            informarFalhaTecnica(1, e);
            return false;
        }
    }

    private static boolean testarEncapsulamento() {
        System.out.println("\n--- Pilar 2: Encapsulamento ---");

        try {
            Class<?> contaClass = Class.forName("pilar2_encapsulamento.ContaVeiculo");
            Object conta = contaClass.getConstructor(String.class, String.class)
                    .newInstance("CHS12345", "Modelo Inicial");

            contaClass.getMethod("registrarViagem", double.class).invoke(conta, -20.0);
            contaClass.getMethod("registrarViagem", double.class).invoke(conta, 120.5);
            contaClass.getMethod("setModelo", String.class).invoke(conta, "");
            contaClass.getMethod("setModelo", String.class).invoke(conta, "Modelo Atualizado");

            String chassi = (String) contaClass.getMethod("getChassi").invoke(conta);
            String modelo = (String) contaClass.getMethod("getModelo").invoke(conta);
            double quilometragem = (double) contaClass.getMethod("getQuilometragem").invoke(conta);

            boolean quilometragemOk = Math.abs(quilometragem - 120.5) < 0.0001;
            boolean modeloOk = "Modelo Atualizado".equals(modelo);

            System.out.println("Chassi: " + chassi);
            System.out.println("Modelo: " + modelo);
            System.out.println("Quilometragem: " + quilometragem);

            return quilometragemOk && modeloOk;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            informarEtapaNaoCriada(2, "pilar2_encapsulamento.ContaVeiculo");
            return false;
        } catch (Exception e) {
            informarFalhaTecnica(2, e);
            return false;
        }
    }

    private static boolean testarHeranca() {
        System.out.println("\n--- Pilar 3: Heranca ---");

        try {
            Class<?> carroEletricoClass = Class.forName("pilar3_heranca.CarroEletrico");
            Object carroEletrico = carroEletricoClass
                    .getConstructor(String.class, String.class, int.class, int.class)
                    .newInstance("BYD", "Dolphin", 2025, 60);

            Class<?> motoClass = Class.forName("pilar3_heranca.Moto");
            Object moto = motoClass.getConstructor(String.class, String.class, int.class, int.class)
                    .newInstance("Honda", "CB 500", 2024, 500);

            carroEletricoClass.getMethod("ligar").invoke(carroEletrico);
            carroEletricoClass.getMethod("carregarBateria").invoke(carroEletrico);

            motoClass.getMethod("ligar").invoke(moto);
            motoClass.getMethod("empinar").invoke(moto);

            // Se os metodos herdados e especificos executaram sem erro, a etapa esta ok.
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            informarEtapaNaoCriada(3, "pilar3_heranca.CarroEletrico / pilar3_heranca.Moto");
            return false;
        } catch (Exception e) {
            informarFalhaTecnica(3, e);
            return false;
        }
    }

    private static boolean testarPolimorfismo() {
        System.out.println("\n--- Pilar 4: Polimorfismo ---");

        try {
            Class<?> postoClass = Class.forName("pilar4_polimorfismo.PostoDeCombustivel");
            Method executarSimulacao = postoClass.getMethod("executarSimulacao");
            return (boolean) executarSimulacao.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            informarEtapaNaoCriada(4, "pilar4_polimorfismo.PostoDeCombustivel");
            return false;
        } catch (Exception e) {
            informarFalhaTecnica(4, e);
            return false;
        }
    }

    private static void informarEtapaNaoCriada(int etapa, String classeEsperada) {
        System.out.println("Etapa " + etapa + " ainda nao foi criada completamente.");
        System.out.println("Classe esperada: " + classeEsperada);
        System.out.println("Finalize essa etapa e execute novamente.");
    }

    private static void informarFalhaTecnica(int etapa, Exception e) {
        System.out.println("Falha tecnica ao testar a etapa " + etapa + ".");
        System.out.println("Detalhe: " + e.getClass().getSimpleName() + " - " + e.getMessage());
    }
}
