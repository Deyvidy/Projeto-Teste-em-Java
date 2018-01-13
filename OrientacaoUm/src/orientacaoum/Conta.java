/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientacaoum;

import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
class Conta {
Cliente titular;
Agencias agencia;
int numero;
double saldo;
double renda;


    boolean saca(double quantidade) {
    if(this.saldo < quantidade){
        return false;
    } else {
        double novoSaldo = this.saldo - quantidade;	
        this.saldo = novoSaldo;
        return true;
    }
       
    }

    void depo(double quantidade){
        double novoSaldo = this.saldo + quantidade;
        this.saldo = novoSaldo;
    }

    boolean transfere(Conta contaorigem, Conta destino, double valor){
      if(contaorigem.saldo >= valor){
      
          contaorigem.saldo = contaorigem.saldo - valor;

      destino.saldo = destino.saldo + valor;

      return true;
      
      } else{
          return false;
      }
      
    }

    Agencias buildAgencia(){
   Agencias agenc = new Agencias();
   agenc.nome = "";
   agenc.num = 0;
    this.agencia = agenc;
    return this.agencia;
    }
  Cliente  buildClient(){
        Cliente client = new Cliente();
        //client.nome = "";
      //  client.cpf = "";
        this.titular = client;
  return this.titular;  
  }
 /*Conta buildConta(){
      Conta contaC = new Conta();
      this.numero = contaC.numero;
      this.renda = contaC.renda;
      this.saldo = contaC.saldo;
    return contaC;
    
  }*/
    
}