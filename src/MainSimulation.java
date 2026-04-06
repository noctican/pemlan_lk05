package src;

public class MainSimulation {

    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("    PT Asuransi BPJS dengan RS Mitra Keluarga   ");
        System.out.println("=".repeat(50));
        System.out.println();

        System.out.println("=".repeat(50));;
        System.out.println("SIMULASI 1: Data PatientProfileV1");
        System.out.println("=".repeat(50));;

        patientProfileV1 patientV1_1 = new patientProfileV1(
                "P001", "Budi Santoso", "3201234567890001", 2);

        IntegrationGateway<patientProfileV1> gatewayV1_1 = new IntegrationGateway<>(patientV1_1);

        System.out.println("\n--- Dokter Akses Rendah (Clearance Level: 1) mengakses data ---");
        SecureResponse<patientProfileV1> response1 = gatewayV1_1.fetchData("P001", 1);
        System.out.println(response1);

        patientProfileV1 patientV1_2 = new patientProfileV1(
                "P001", "Budi Santoso", "3201234567890001", 2);
        IntegrationGateway<patientProfileV1> gatewayV1_2 = new IntegrationGateway<>(patientV1_2);

        System.out.println("\n--- Dokter Akses Tinggi(Clearance Level: 3) mengakses data ---");
        SecureResponse<patientProfileV1> response2 = gatewayV1_2.fetchData("P001", 3);
        System.out.println(response2);

        System.out.println("\n"+"=".repeat(50));
        System.out.println("SIMULASI 2: Data PatientProfileV2");
        System.out.println("=".repeat(50));;

        patientProfileV2 patientV2_1 = new patientProfileV2(
                "P002", "Siti Rahayu", "3201987654321002",
                "Alergi Penisilin, Alergi Seafood",
                "Diabetes Mellitus Tipe 2", 3);

        IntegrationGateway<patientProfileV2> gatewayV2_1 = new IntegrationGateway<>(patientV2_1);

        System.out.println("\n--- Dokter Akses Menengah (Clearance Level: 2) mengakses data ---");
        SecureResponse<patientProfileV2> response3 = gatewayV2_1.fetchData("P002", 2);
        System.out.println(response3);

        patientProfileV2 patientV2_2 = new patientProfileV2(
                "P002", "Siti Rahayu", "3201987654321002",
                "Alergi Penisilin, Alergi Seafood",
                "Diabetes Mellitus Tipe 2", 3);
        IntegrationGateway<patientProfileV2> gatewayV2_2 = new IntegrationGateway<>(patientV2_2);

        System.out.println("\n--- Dokter Akses Tinggi (Clearance Level: 3) mengakses data ---");
        SecureResponse<patientProfileV2> response4 = gatewayV2_2.fetchData("P002", 3);
        System.out.println(response4);
    }
}
