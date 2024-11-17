
package meujogo.Modelo;

import java.io.*;

public class SalvarJogo {
    private static final String CAMINHO_ARQUIVO = "numero.txt";

    
    public static void compararEAtualizarArquivo(int numero) {
       
        int numeroDoArquivo = lerNumeroDoArquivo();

        
        if (numeroDoArquivo == Integer.MIN_VALUE) {
            System.out.println("Erro ao ler o número do arquivo. O arquivo pode estar vazio ou corrompido.");
            return;
        }

        
        if (numeroDoArquivo < numero) {
            System.out.println("Número no arquivo é menor que o fornecido. Atualizando...");
            atualizarArquivo(numero);  
        } else {
          
            System.out.println("Número no arquivo é maior ou igual ao fornecido. Nenhuma atualização necessária.");
        }
    }


    public static int lerNumeroDoArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);

       
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo.");
            return Integer.MIN_VALUE; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();

           
            if (linha != null && !linha.trim().isEmpty()) {
                try {
                    return Integer.parseInt(linha);  
                } catch (NumberFormatException e) {
                    System.out.println("Erro: O conteúdo do arquivo não é um número válido.");
                }
            } else {
                System.out.println("Erro: O arquivo está vazio.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return Integer.MIN_VALUE;  
    }

   
    public static void atualizarArquivo(int numero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            writer.write(String.valueOf(numero)); 
            
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
}
