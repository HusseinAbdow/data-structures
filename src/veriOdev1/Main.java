package veriOdev1;

import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        LinkedList liste = new LinkedList();
        // Rastgele sayıları saklamak ve tekrar eden sayıları önlemek için HashSet oluştur
        HashSet<Integer> set = new HashSet<>();

        // 25 adet rastgele sayı üret ve sıralanmamış halini göstermek için HashSet'e ekle
        while (set.size() < 25) {
            set.add(random.nextInt(100));
        }

        // Sıralanmamış rastgele sayıları yazdır
        System.out.println("Sıralanmamış 25 adet rastgele sayı:");
        System.out.println(set);
        System.out.println();

        // Üretilen 25 sayıyı bağlı listeye sıralı şekilde ekle
        for (Integer num : set) {
            insert(liste, num);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Çıkartmak istediğiniz sayıyı yazınız:");
        int sayi = scanner.nextInt();

        Node temp = liste.root;

        // Kullanıcının seçtiği sayıyı listede bul
        while (temp != null && temp.data != sayi) {
            temp = temp.next;
        }

        // Sayı bulunamadıysa
        if (temp == null) {
            System.out.println("Sayı " + sayi + " bulunamadı.");
        } 
        else {
            // Sayının ekleneceği konumu kullanıcıdan al
            System.out.println("\nSayı bulundu: " + temp.data);
            System.out.println("Yeni konumunu seçiniz:");

            System.out.println("1. Başa eklenecek");
            System.out.println("2. Sona eklenecek");
            System.out.println("3. İki sayının arasına eklenecek");
            System.out.println();

            // Kullanıcının seçimini al
            System.out.println("Bir seçenek seçiniz:");
            int secenek = scanner.nextInt();

            // Seçilen işleme göre kes-yapıştır işlemini gerçekleştir
            kesYapistir(liste, temp.data, secenek);
        }
    }


    public static void insert(LinkedList liste, int data) {

        // Yeni düğüm oluştur
        Node yeniDugum = new Node(data);

        // 1. Durum: Liste boş ise yeni düğüm root olur
        if (liste.root == null) {

            liste.root = yeniDugum;
            liste.root.next = null;
            liste.root.previous = null;

            System.out.println("İlk eleman eklendi: " + data);

            // Güncel bağlı listeyi yazdır
            print(liste);
            System.out.println();
        }

        // 2. Durum: Yeni düğüm en küçük eleman ise listenin başına eklenir
        else if (yeniDugum.data < liste.root.data) {

            Node temp = liste.root;

            liste.root = yeniDugum;
            liste.root.next = temp;
            liste.root.previous = null;

            temp.previous = liste.root;

            System.out.println(data + " başa eklendi.");

            print(liste);
            System.out.println();
        }

        // 3 ve 4. Durum: Yeni düğüm sona veya iki düğüm arasına eklenir
        else {

            Node iter = liste.root;

            // Yeni düğümün ekleneceği konumu bul
            while (iter.next != null && iter.next.data < data) {
                iter = iter.next;
            }

            // 3. Durum: Listenin sonuna ekleme
            if (iter.next == null) {

                iter.next = yeniDugum;
                yeniDugum.previous = iter;
                yeniDugum.next = null;

                System.out.println(data + " sona eklendi.");

                print(liste);
                System.out.println();
            }

            // 4. Durum: İki düğüm arasına ekleme
            else {

                yeniDugum.next = iter.next;
                yeniDugum.previous = iter;

                iter.next.previous = yeniDugum;
                iter.next = yeniDugum;

                System.out.println(data + " iki düğüm arasına eklendi.");

                print(liste);
                System.out.println();
            }
        }
    }
    
    
    // Kullanıcının seçimine göre sayıyı yeni konumuna ekle
    public static void kesYapistir(LinkedList liste, int data, int secenek) {

        switch (secenek) {

            // Sayıyı listenin başına ekle
            case 1:
                System.out.println(data + " başa eklendi.");
                insertBas(liste, data);
                break;

            // Sayıyı listenin sonuna ekle
            case 2:
                System.out.println(data + " sona eklendi.");
                insertSon(liste, data);
                break;

            // Sayıyı iki düğüm arasına ekle
            case 3:
                Scanner scanner = new Scanner(System.in);

                System.out.println("Sayı iki düğüm arasına eklenecek.");
                System.out.println();

                System.out.print("Mevcut LinkedList: ");
                print(liste);

                System.out.println("Birinci sayıyı seçiniz:");
                int sayi1 = scanner.nextInt();

                System.out.println("İkinci sayıyı seçiniz:");
                int sayi2 = scanner.nextInt();

                System.out.println(data + " sayısı " + sayi1 + " ve " + sayi2 + " arasına eklendi.");

                insertOrta(liste, sayi1, sayi2, data);
                break;

            // Geçersiz seçim yapılırsa
            default:
                System.out.println("Geçersiz seçim!");
        }
    }


    public static void insertBas(LinkedList liste, int data) {
        // Sayıyı mevcut konumundan sil
        delete(liste, data);

        // Başa eklenecek yeni düğümü oluştur
        Node yeniDugum = new Node(data);

        Node temp = liste.root;

        liste.root = yeniDugum;
        liste.root.next = temp;
        temp.previous = liste.root;

        print(liste);
    }


    public static void insertSon(LinkedList liste, int data) {
        // Sayıyı mevcut konumundan sil
        delete(liste, data);

        // Sona eklenecek yeni düğümü oluştur
        Node yeniDugum = new Node(data);

        // Listenin son düğümünü bul
        Node iter = liste.root;

        while (iter.next != null) {
            iter = iter.next;
        }

        // Yeni düğümü listenin sonuna ekle
        iter.next = yeniDugum;
        yeniDugum.previous = iter;

        print(liste);
    }


    public static void insertOrta(LinkedList liste, int sayi1, int sayi2, int data) {
        // Sayıyı mevcut konumundan sil
        delete(liste, data);

        Node yeniDugum = new Node(data);

        // Ekleme yapılacak konumu bul
        Node iter = liste.root;

        while (iter.data != sayi1 && iter.next.data != sayi2) {
            iter = iter.next;
        }

        // Yeni düğümü seçilen iki düğüm arasına ekle
        yeniDugum.next = iter.next;
        yeniDugum.previous = iter;

        iter.next.previous = yeniDugum;
        iter.next = yeniDugum;

        print(liste);
    }


    static void delete(LinkedList liste, int eleman) {

       // Silinecek eleman root ise
       if (liste.root.data == eleman) {
                
            // Root bir sonraki düğüme taşınır
            liste.root = liste.root.next;
            liste.root.previous = null;
                
            }
       
       // Silinecek eleman root dışında ise
       else {

            Node iter = liste.root;

            // Silinecek düğümü bul
            while (iter != null && iter.data != eleman) {
                   iter = iter.next;
                }

           // Aranan düğümün bulunup bulunmadığını kontrol et
             if (iter == null) {
                 System.out.println("Aranan sayı bulunamadı.");
                }
             
             // Düğüm bulunduysa silme işlemini gerçekleştir
             else {

             // Son elemanı silme
             if (iter.next == null) {
                 iter.previous.next = null;
                 
                    }
             
             // Son düğüm değilse bağlantıları güncelle
             else {
            	 
                 // Ortadaki elemanı silme
                 iter.previous.next = iter.next;
                 iter.next.previous = iter.previous;
                 
                    }
                }
            }
        }
    


    public static void print(LinkedList liste) {

        // Root düğümünden başlayarak listeyi dolaş
        Node iter = liste.root;

        // Listenin elemanlarını yazdır
        while (iter.next != null) {
            System.out.print(iter.data + " ");
            iter = iter.next;
        }

        // Son düğümü yazdır
        System.out.print(iter.data + " ");
        System.out.println();
    }
}