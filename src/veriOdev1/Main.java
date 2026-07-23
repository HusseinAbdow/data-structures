package veriOdev1;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        LinkedList liste = new LinkedList();

        // 25 adet rastgele sayı üret ve listeye ekle
        for (int i = 0; i < 25; i++) {

            int randomSayi = random.nextInt(1, 100);
            insert(liste, randomSayi);

        }


        // Listeyi büyükten küçüğe ekrana yazdır
        print(liste);

    }

    public static void insert(LinkedList liste, int data) {

        // Yeni düğüm oluştur
        Node yeniDugum = new Node(data);

        // 1. Durum: Liste boş ise yeni düğüm root olur
        if (liste.root == null) {

            liste.root = yeniDugum;
            liste.root.next = null;
            liste.root.previous = null;

        }

        // 2. Durum: Yeni düğüm listedeki en büyük sayı ise(yani rooteki elemandan daha buyuk ise) başa eklenir
        else if (yeniDugum.data > liste.root.data) {

            Node temp = liste.root;

            liste.root = yeniDugum;
            liste.root.next = temp;
            liste.root.previous = null;

            temp.previous = liste.root;

        }

        // 3. ve 4. Durum: Yeni düğüm sona veya ortaya eklenecek
        else {

            Node iter = liste.root;

            // Yeni düğümün ekleneceği konumu bul
            while (iter.next != null && iter.next.data >= data) {
                iter = iter.next;
            }

            // 3. Durum: Listenin sonuna ekle
            if (iter.next == null) {

                iter.next = yeniDugum;
                yeniDugum.previous = iter;
                yeniDugum.next = null;

            }

            // 4. Durum: İki düğüm arasına ekle
            else {

                yeniDugum.next = iter.next;
                yeniDugum.previous = iter;

                iter.next.previous = yeniDugum;
                iter.next = yeniDugum;

            }

        }

    }

    static void print(LinkedList liste) {

        // Root düğümünden başlayarak listeyi dolaş
        Node iter = liste.root;

        // Son düğüme kadar yazdır
        while (iter.next != null) {
            System.out.print(iter.data + " ");
            iter = iter.next;
        }

        // Son düğümü yazdır
        System.out.print(iter.data + " ");
        System.out.println("");

    }

}