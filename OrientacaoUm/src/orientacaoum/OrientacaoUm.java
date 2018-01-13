/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientacaoum;

import java.util.ArrayList;
import java.util.Scanner;


public class OrientacaoUm {

public static final int TAM_CONTAS = 3;
public static int pos_conta=0 , contascadastradas=0, pos_contad;

static void criaConta(double qtd, Conta contaclient, ArrayList<Conta> listacontas, Scanner teclado){
    Conta c = new Conta();
   c = contaclient; 
   c.buildAgencia();
   c.buildClient();
   //contaclient.buildConta();
   
   System.out.println("SELECIONE A AGENCIA:");
   
    c.agencia.nome = teclado.next();
    
    System.out.println("INFORME O NUMERO DA AGENCIA");
   c.agencia.num = teclado.nextInt();
    
    System.out.println("INFORME O NOME DO TITULAR DA CONTA");
   c.titular.nome = teclado.next();
    
    System.out.println("INFORME O CPF DO TITULAR DA CONTA");
  c.titular.cpf  = teclado.next();
    
    System.out.println("INFORME A RENDA MENSAL DO TITULAR DA CONTA");
    c.renda  = teclado.nextDouble();    
    
    System.out.println("INFORME O NUMERO DA CONTA");
   c.numero = teclado.nextInt();
   
   listacontas.add(c);
    menuPrincipal(contaclient,qtd, listacontas, teclado);
    
}
    


static boolean verificaConta(Conta contaclient, ArrayList<Conta> listacontas, Scanner teclado){
    int numconta;
    System.out.println("NUMERO DA SUA CONTA:");
    numconta = teclado.nextInt();
    
    for(int i = 0; i < listacontas.size(); i++){
        
        if(numconta == listacontas.get(i).numero){
            pos_conta = i;
            return true;
           
        }
        
   }
   
    return false;
}

static boolean verificaContaD(Conta contaclient, ArrayList<Conta> listacontas, Scanner teclado){
    int numconta;
    System.out.println("NUMERO DA CONTA:");
    numconta = teclado.nextInt();
    
    for(int i = 0; i < listacontas.size(); i++){
        
        if(listacontas.get(i).numero == numconta){
            pos_contad = i;
            return true;
           
        }
    }
    return false;

}

static void saqueoudep(Conta contaclient, double qtd, ArrayList<Conta> listacontas, Scanner teclado){
     //   verificaConta(contaclient, listacontas, teclado);
        
        if(verificaConta(contaclient, listacontas , teclado)){
          listacontas.set(pos_conta, contaclient);
            System.out.println("Deseja sacar ou depositar?");
            String resp = teclado.next();
       
        switch(resp){
            case "S":
                 System.out.println("==========================================");   
                 System.out.println(" SALDO ATUAL: " + listacontas.get(pos_conta).saldo);
                 System.out.println("DIGITE O VALOR QUE DESEJA SACAR");
                 qtd = teclado.nextDouble();
                 System.out.println("==========================================");
               
                 if(!listacontas.set(pos_conta, contaclient).saca(qtd)){
                 System.out.println("\n\n\nSAQUE NÃO REALIZADO. SALDO INSUFICIENTE.");
                 System.out.println("SALDO ATUAL: " +  listacontas.get(pos_conta).saldo);
                 break;
                 } else{
                 System.out.println("\n\n\nSAQUE REALIZADO COM SUCESSO!  ");
                 break;
                 }
   
            case "D":
                System.out.println("==========================================");
                System.out.println(" SALDO ATUAL: " +  listacontas.get(pos_conta).saldo);
                System.out.println("VALOR DO DEPÓSITO?");
                qtd = teclado.nextDouble();
                System.out.println("==========================================");
                listacontas.set(pos_conta, contaclient).depo(qtd); // TEM QUE MUDAR
                System.out.println("\n\nSALDO ATUAL: " +  listacontas.get(pos_conta).saldo);
              break;
            default:
                System.out.println("\n\n\n OPÇÃO INVÁLIDA, VOCÊ SERÁ REDIRECIONADO AO MENU PRINCIPAL");
                menuPrincipal(contaclient, qtd, listacontas, teclado);
                break;
        }  
   }
        
        menuPrincipal(contaclient, qtd, listacontas, teclado);
}
 
static void transferePara(Conta contaclient, double qtd, ArrayList<Conta> listacontas, Scanner teclado){
    String resp;
    
    if(verificaConta(contaclient, listacontas, teclado));{
        do{
       
       System.out.println("CONTA QUE IRÁ RECEBER O VALOR DA TRANSFERÊNCIA:");       
      // verificaContaD(contaclient, listacontas, teclado);
       
       if(verificaContaD(contaclient, listacontas, teclado)){
            
           System.out.println("VALOR A SER TRANSFERIDO:");
            qtd = teclado.nextDouble();
            Conta contaorigem = listacontas.get(pos_conta);
            Conta destino = listacontas.get(pos_contad);
            
       if(destino.transfere(contaorigem, destino, qtd)){
           System.out.println("==========================================");
           System.out.println("TRANSFERENCIA REALIZADA COM SUCESSO!");
           System.out.println("\n\nSALDO DA DESTINO " + destino.saldo);
           System.out.println("\n\nSALDO DA MINHACONTA: " + contaclient.saldo);
           System.out.println("==========================================");

       }   else{
            System.out.println("TRANSFERENCIA NAO REALIZADA, SALDO INSUFICIENTE");
          } 
    } else{
           System.out.println("CONTA NAO ENCONTRADA! VOCE SERA REDIRECIONADO AO MENU PRINCIPAL");
           menuPrincipal(contaclient, qtd, listacontas, teclado);
       }
   
          System.out.println("\n\n\nDESEJA REALIZAR OUTRA TRANSFERENCIA ENTRE AS MESMAS CONTAS? [S/N]");
          resp = teclado.nextLine();
     } while(!"N".equals(resp));
 } 
   
    
}

 static void menuPrincipal(Conta contaclient, double qtd, ArrayList<Conta> listacontas, Scanner teclado){
        String opcao;
        System.out.println("\n\n\n==========================================");
        System.out.println("SISTEMA DE GERENCIAMENTO FINANCEIRO");
        System.out.println("==========================================");
        System.out.print("\n\n\n1. SAQUE OU DEPOSITO \n 2.  TRANSFERENCIA \n 3. CRIAR NOVA CONTA \n");
        opcao = teclado.next();
        switch(opcao){
            case "1":
                saqueoudep(contaclient, qtd, listacontas, teclado); break;
            case "2":
                transferePara(contaclient, qtd, listacontas, teclado); break;
            case "3":
                criaConta(qtd, contaclient, listacontas, teclado); break;
            default:
            System.out.println("\n\n\n OPCAO INVALIDA, VOCE SERA REDIRECIONADO AO MENU PRINCIPAL ");
            menuPrincipal(contaclient, qtd, listacontas, teclado);
            break;
        }
}   

public static void main(String[] args) {
    /* OBJETOS:
    */
        ArrayList<Conta> listacontas = new ArrayList<>();
       Conta contaclient = new  Conta();
        Cliente titu = new Cliente();
        Agencias agenc = new Agencias();
        double qtd = 0;
        Scanner teclado = new Scanner(System.in);
        
    /*
    */
        menuPrincipal(contaclient, qtd, listacontas, teclado);
        
       
       
               
    }
    
}
