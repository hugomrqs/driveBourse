package fr.pantheonsorbonne.ufr27.miage;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le montant de votre levée de fond que vous voulez offrir ?");
        int leveeFond = sc.nextInt();


        System.out.print("Entrez la quantité de part que vous désirez proposez ?");
        int qtePart = sc.nextInt();



        sc.close();

    }
}