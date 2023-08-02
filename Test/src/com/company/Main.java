package com.company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Creiez functia pe care o voi folosi ca sa citesc valori de la tastatura

        int N, R, A, M, V = 1;
        int[] P = new int[2];
        // Creiez variabile pentru dimensiunea terenului, nr de fantome rosii, nr de fantome albastre, nr de mutari, variabila semafor pentru a vedea daca packman este in viata si un vector pentru pozitile lui packman
        System.out.println('n');
        N = scanner.nextInt();
        // aflu dimensiunea terenului

        do{
            System.out.println("Xn Yn:");
            P[0] = scanner.nextInt();
            P[1] = scanner.nextInt();
        }while((P[0] < 1 || P[0] > N) || (P[1] < 1 || P[1] > N));
        // afle pozitile lui packman

        System.out.println('R');
        R = scanner.nextInt();
        // aflu nr de fantome rosii

        int [][] FR = new int[R][3]; // creiez o matrice cu nr de fantome ca lungime si pe latime x, y si directia in care se indreapta
        for(int i=0; i < R; i++){ // trec ijndividual prin toate fantomele
            do{
                System.out.println("Xr" + i + " Yr" + i);
                FR[i][0] = scanner.nextInt();
                FR[i][1] = scanner.nextInt();
            }while((FR[i][0] < 1 || FR[i][0] >N) || (FR[i][1] < 1 || FR[i][1] > N));
            // aflu poitia fantomei nr i

            if(FR[i][0] == 1){
                FR[i][2] = 1;
            }
            else{
                FR[i][2] = -1;
            }
            // aflu directia fantomei nr i
        }

        System.out.println('A');
        A = scanner.nextInt();
        // aflu nr de fantome albastre

        int [][] FA = new int[A][3]; // creiez o matrice cu nr de fantome ca lungime si pe latime x, y si directia in care se indreapta
        for(int i=0; i < A; i++){ // trec ijndividual prin toate fantomele
            do{
                System.out.println("Xr" + i + " Yr" + i);
                FA[i][0] = scanner.nextInt();
                FA[i][1] = scanner.nextInt();
            }while((FA[i][0] < 1 || FA[i][0] >N) || (FA[i][1] < 1 || FA[i][1] > N));
            // aflu poitia fantomei nr i

            if(FA[i][1] == 1){
                FA[i][2] = 1;
            }
            else{
                FA[i][2] = -1;
            }
            // aflu directia fantomei nr i
        }

        System.out.println('M');
        M = scanner.nextInt();
        // aflu nr de mutari

        for(int i = 0; i < M && V == 1; i ++){ // trec individual prin fiecare mutare oprinduma ori dupa ce nr max de mutari a fost atins sau packman moare
            String D; // creiez o variabila care afla mutarea lui packman (stiu ca char memoreaza doar un semn iar String memoreaza un intreg sir dar nu stiu functia pentru a accepta input la char deci am folosit string)
            System.out.println('D');
            D = scanner.next(); // citim urmatoarea miscare a lui packman

            if(D.equals("U")){
                P[1] = P[1] + 1;
                if(P[1] > N){
                    P[1] = N;
                }
            }
            else if(D.equals("D")){
                P[1] = P[1] - 1;
                if(P[1] < 1){
                    P[1] = 1;
                }
            }
            else if(D.equals("L")){
                P[0] = P[0] - 1;
                if(P[0] < 1){
                    P[0] = 1;
                }
            }
            else if(D.equals("R")){
                P[0] = P[0] + 1;
                if(P[0] > N){
                    P[0] = N;
                }
            }
            // verificam ce miscare a facut packman si daca aceasta mutare l-ar da afara de pe harta

            for(int j = 0; j <R; j++){ // trecem individual prin toate fantomele rosii
                FR[j][0] = FR[j][0] + FR[j][2]; // miscam fantoma folosind variabila care determina directia in care se indreapta fantoma

                if(FR[j][0] == N){
                    FR[j][2] = 1;
                }
                else if(FR[j][0] == 1){
                    FR[j][2] = 1;
                }
                // verificam daca directia a ramas aceeasi sau fantoma a atims marginea

                if(FR[j][0] == P[0] && FR[j][1] == P[1]){
                    V = 0;
                }
                // verificam daca fantoma l-a mancat pe packman
            }
            for(int j = 0; j <A; j++){ // trecem individual prin toate fantomele albastre
                FA[j][0] = FA[j][0] + FA[j][2]; // miscam fantoma folosind variabila care determina directia in care se indreapta fantoma

                if(FA[j][0] == N){
                    FA[j][2] = 1;
                }
                else if(FA[j][0] == 1){
                    FA[j][2] = 1;
                }
                // verificam daca directia a ramas aceeasi sau fantoma a atims marginea

                if(FA[j][0] == P[0] && FA[j][1] == P[1]){
                    V = 0;
                }
                // verificam daca fantoma l-a mancat pe packman
            }
        }

        if(V == 0){ // verificam daca packman a murit
            System.out.println("PackMan a Murit!!"); // in caz ca da, anuntam ca packman a murit
        }

        System.out.println("P " + P[0] + ' ' + P[1]); // dam pozitia lui packman

        for(int i = 1; i<= N; i++){ // luam coloana cu coloana
            for(int j = N; j >=1; j--){ //luam linie cu linie
                for(int a = 0; a < A; a++){ // luam fiecare fantoma albastra individual
                    if(FA[a][0] == i && FA[a][1] == j){ // verificam daca este pe pozitia necesara
                        System.out.println("B " + FA[a][0] + ' ' + FA[a][1]); // daca da o afisam
                    }
                }
                for(int r = 0; r < R; r++){// luam fiecare fantoma rosii individual
                    if(FR[r][0] == i && FR[r][1] == j){ // verificam daca este pe pozitia necesara
                        System.out.println("R " + FR[r][0] + ' ' + FR[r][1]); // daca da o afisam
                    }
                }
            }
        }
    }
}